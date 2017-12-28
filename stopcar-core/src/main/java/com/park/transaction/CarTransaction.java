/**
 * @Title: TT.java
 * @Package com.intimes.biz
 * @Description:
 * @author 敬小虎
 * @date 2015年3月20日 下午1:32:43
 * @version V1.0
 */
package com.park.transaction;

import com.alibaba.fastjson.JSON;
import com.park.bean.*;
import com.park.constants.AppProperties;
import com.park.dao.Carcode_park_rentDao;
import com.park.exception.QzException;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.ActivityPB;
import com.park.mvc.service.common.CarPB;
import com.park.mvc.service.common.EtcPB;
import com.park.mvc.service.common.JpushPB;
import com.park.mvc.service.common.ParkCouponPB;
import com.park.mvc.service.common.ParkInfoPB;
import com.park.mvc.service.common.PayParkPB;
import com.park.mvc.service.common.UserPB;
import com.park.service.CxfServerXHelper;
import com.park.service.ETCHelper;
import com.park.service.etc.etcapi.ETCResponse;
import com.park.type.ActivityEnum;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import zk.jni.Zkfp;
import zk.jni.Zkfv;

/**
 * 车辆相关事务类
 *
 * @author jingxiaohu
 */
@Transactional(rollbackFor = QzException.class)
@Service
public class CarTransaction extends BaseBiz {

  @Autowired
  private PayParkPB payParkPB;
  @Autowired
  private UserPB userPB;
  @Autowired
  private ParkInfoPB parkInfoPB;
  @Autowired
  protected ActivityPB activityPB;
  @Autowired
  protected ParkCouponPB parkCouponPB;
  @Autowired
  protected CarPB carPB;
  @Autowired
  private JpushPB jpushPB;
  @Autowired
  private Carcode_park_rentDao carcode_park_rentDao;

  /**
   * 用户绑定或者更新车牌事物处理
   */
  public void bindCarCode(User_carcode user_carcode, ReturnDataNew returnData) throws QzException {
    try {
      //插入数据库
      int id = user_carcodeDao.insert(user_carcode);
      if (id < 1) {
        returnData.setReturnData(errorcode_data, "绑定失败", "");
        return;
      }
      user_carcode.setUc_id(id);
      /**
       * 获取某车牌还没有支付且没有绑定用户的最后一条某停车场的订单
       * 如果存在则 把该订单的用户信息设置进去---订单与用户关联
       */
      Pay_park pay_park = carPB.Query_PayParkByCarCode(user_carcode.getCar_code());
      if (pay_park != null) {
        //把用户与该订单绑定
        pay_park.setUi_id(user_carcode.getUi_id());
        pay_park.setUi_nd(user_carcode.getUi_nd());
        pay_park.setUi_tel(user_carcode.getUi_tel());
        int count = pay_parkDao.updateByKey(pay_park);
        if (count != 1) {
          returnData.setReturnData(errorcode_data, "绑定失败", "");
          throw new QzException("绑定失败");
        }
      }

      bindCarCodeParkRentUser(user_carcode);

      /**
       * by jxh 2017-4-21
       * 处理用户PDA 欠费订单跟用户的关联
       */
      doOwingOrderBind(returnData, user_carcode);

      //返回数据
      returnData.setReturnData(errorcode_success, "绑定成功", "");
      return;
    } catch (Throwable e) {
      returnData.setReturnData(errorcode_data, "绑定失败", "");
      throw new QzException("事物异常 bindCarCodet", e);
    }
  }

  /**
   * 绑定相关用户车牌--停车场租赁映射表用户信息
   *
   * @param user_carcode 用户车牌绑定信息
   */
  private void bindCarCodeParkRentUser(User_carcode user_carcode) throws Exception {
    List<Carcode_park_rent> carcode_park_rents = getMySelfService().queryListT(
        "SELECT * FROM carcode_park_rent o WHERE o.car_code=?",
        Carcode_park_rent.class, user_carcode.getCar_code());
    if (carcode_park_rents != null && carcode_park_rents.size() > 0) {
      for (Carcode_park_rent carcode_park_rent : carcode_park_rents) {
        //把用户与该订单绑定
        carcode_park_rent.setUi_id(user_carcode.getUi_id());
        carcode_park_rent.setUi_nd(user_carcode.getUi_nd());
        int count = carcode_park_rentDao.updateByKey(carcode_park_rent);
        if (count != 1) {
          throw new QzException("绑定失败");
        }
      }
    }
  }


  /**
   * 处理调度轮询  预约超时的订单进行扣费处理
   */
  public void ExpectOrderCheckTask(Pay_park pay_park) throws QzException {

    try {
      payParkPB.expectOrderOutTime(pay_park);
    } catch (Exception e) {
      log.error(" UserTransaction.ExpectOrderCheckTask is error", e);
      throw new QzException("事物异常 ExpectOrderCheckTask", e);
    }
  }

  /*  *//**
   * 扫描到车辆出库扣费
   *
   * @param is_free :0 收费  1：免费
   *//*
  public void payMent(Pay_park pay_park, User_info userinfo, int total_money,
      ReturnDataNew returnData, Date date, int is_free) throws QzException {
    try {
      if (is_free == 1) {
        //免费
        pay_park.setScan_time(date);//道闸停车  摄像头扫描到车辆  需要记录扫描时间点
        pay_park.setIs_over(1);//订单是否完成(0:没有完成1：完成)
        int count = pay_parkDao.updateByKey(pay_park);
        if (count != 1) {
          //更新失败
          returnData.setReturnData(errorcode_data, "请求失败", "");
          return;
        }
        return;
      }

      //检查是否开启自动支付
      if (userinfo.getUi_autopay() == 0) {
        returnData.setReturnData(errorcode_data, "请求失败-没有开启自动支付", "", "5");
        return;
      }

      *//**
   * 注意事项  占道停车  暂时不参与自动扣款处理   park_info.getPark_type() != 1
   *//*
      //第一情况：首先如果用户有吾泊平台未过期的代金券 则检查该代金券是否可以完全抵扣该次停车费用 
      //如果可以则完成该次支付开闸  如果不可以则需要判断用户是否开启了自动支付和钱包金额是否够该次支付
      //默认如果有优惠券则 使用代金券最大面值的一张
      //默认选择抵扣最大的优惠券
      User_park_coupon user_park_coupon = parkCouponPB
          .ReturnMaxMoneyCoupon(pay_park.getUi_id(), total_money);
      if (user_park_coupon != null) {
        total_money = parkCouponPB.doCouponOrder(pay_park, user_park_coupon, total_money);
      }
      *//********使用优惠券****start**************//*
      //by jxh 2017-2-17
      if (total_money == 0) {
        //int money = pay_park.getMoney();
        //扣款成功
        pay_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
        pay_park.setPay_source(4);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
        int count = pay_parkDao.updateByKey(pay_park);
        if (count < 1) {
          //扣款失败
          throw new QzException("扣款失败");
        }

        *//**
   * 更新商户账户金额
   *//*
        userPB.upManchentMoney(pay_park.getPu_id(), pay_park.getPu_nd(), pay_park.getMoney(), 1);

        *//**
   * 更新优惠券使用状态
   *//*
        if (user_park_coupon != null) {
          //如果使用了优惠券 那么需要更改优惠券的使用状态
          User_park_coupon user_park_coupon1 = parkCouponPB
              .upUserParkCouponState(user_park_coupon.getUpc_id());
          if (user_park_coupon1 != null) {
            asyncJpushTask.pushUseCouponMsg(pay_park.getUi_nd(), user_park_coupon1);
          } else {
            //更新失败
            returnData.setReturnData(errorcode_data, "优惠券更新使用状态失败", "");
            throw new QzException("record_user_vc_act 优惠券更新使用状态失败");
          }
        }

        //记录该次用户虚拟币更改记录
        //String act_name="优惠券支付";
        //userPB.recordVC( 0, total_money, pay_park.getMy_order(), pay_park.getOrder_type(), pay_park.getUi_id(), returnData,act_name,pay_park.getUi_nd(),pay_park.getUi_tel());
        *//**
   * 这里处理推送---车辆临停扣款
   *//*
        String title = "系统消息";
        String message = "亲，你的订单【" + pay_park.getMy_order() + "】，临停扣款成功。";
        payParkPB.pushOrderSate(userinfo.getUuid(), message, title, pay_park);
        return;
      }
      *//********使用优惠券****be over**************//*

      if (userinfo != null && total_money > 0) {

        if (userinfo.getUi_vc() - total_money >= 0) {
          //虚拟币足够 且 是自动扣费
          userinfo.setUi_vc(userinfo.getUi_vc() - total_money); //分
          int count = user_infoDao.updateByKey(userinfo);
          if (count < 1) {
            //更新用户扣款数据失败
            //这里需要抛出异常
            throw new QzException("扣款失败");
          } else {
            //扣款成功
            pay_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
            pay_park.setPay_source(4);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
            pay_park.setUtime(date);//交易时间
            count = pay_parkDao.updateByKey(pay_park);
            if (count < 1) {
              //扣款失败
              throw new QzException("扣款失败");
            }
            //扣款记录到用户金额明细中去  *** 后续是否进行异常捕获
            String act_name = "自动支付";
            userPB.recordVC(0, total_money, pay_park.getMy_order(), pay_park.getOrder_type(),
                pay_park.getUi_id(), returnData, act_name, pay_park.getUi_nd(),
                pay_park.getUi_tel(),
                pay_park.getPay_source()
                , pay_park.getUpc_id(), pay_park.getDiscount_type(),
                (int) pay_park.getDiscount_money(), date);
            *//**
   * 更新商户账户金额
   *//*
            userPB
                .upManchentMoney(pay_park.getPu_id(), pay_park.getPu_nd(), pay_park.getMoney(), 1);
            *//**
   * 记录随机立免金额的事件
   * 异步操作
   * @param pay_park
   *//*
            asyncOrderTask.record_random_Event(pay_park, getMySelfService(), activityPB);
            //这里处理订单状态变更推送----车辆临停扣款
            *//**
   * 更新优惠券使用状态
   *//*
            if (user_park_coupon != null) {
              //如果使用了优惠券 那么需要更改优惠券的使用状态
              User_park_coupon user_park_coupon1 = parkCouponPB
                  .upUserParkCouponState(user_park_coupon.getUpc_id());
              if (user_park_coupon1 != null) {
                asyncJpushTask.pushUseCouponMsg(pay_park.getUi_nd(), user_park_coupon1);
              } else {
                //更新失败
                returnData.setReturnData(errorcode_data, "优惠券更新使用状态失败", "");
                throw new QzException("record_user_vc_act 优惠券更新使用状态失败");
              }
            }
            *//**
   * 这里处理推送---车辆临停扣款
   *//*
            String title = "系统消息";
            String message = "亲，你的订单【" + pay_park.getMy_order() + "】，临停扣款成功。";
            payParkPB.pushOrderSate(userinfo.getUuid(), message, title, pay_park);
          }
        } else {
          //开启了自动支付但是钱包金额不足
          *//**
   * 这里处理推送---车辆临停扣款
   *//*
          String title = "系统消息";
          String message = "亲，你的订单【" + pay_park.getMy_order() + "】，因钱包余额不足,自动扣款失败。";
          asyncJpushTask.pushOrderNoMoney(userinfo.getUuid(), message, title, pay_park);
          returnData.setReturnData(errorcode_data, "亲,你钱包余额不足", "", "6");
          return;
        }
      }
    } catch (Exception e) {
      throw new QzException("事物异常 payMent", e);
    }


  }*/

  /**
   * 扫描到车辆出库扣费--租赁产生临停费用
   */
  /*public void payMentRent(Pay_month_park pay_month_park,User_info userinfo ,ReturnDataNew returnData,long pi_id, String area_code,String car_code,Date date,long difftime) throws QzException{

		try{
			*//**
   * 这里租赁分时间端包月产生了临停费用 则要动态创建 临停订单
   *//*
      Pay_park pay_park  = payParkPB.MakeAutoOrder(pi_id, area_code, car_code, userinfo.getUi_id(),difftime);
			if(pay_park == null){
				returnData.setReturnData(errorcode_data, "请求失败", ""); 
				return;
			}
        	*//**
   * 道闸停车  摄像头扫描到车辆  需要记录扫描时间点
   *//*
          pay_month_park.setScan_time(date);
        	int count = pay_month_parkDao.updateByKey(pay_month_park);
        	if(count != 1){
        		//更新失败
        		returnData.setReturnData(errorcode_data, "请求失败", ""); 
				return;
        	}
			*//**
   * 注意事项  占道停车  暂时不参与自动扣款处理   park_info.getPark_type() != 1
   *//*
          *//**
   * 获取当前临停车费用
   *//*
          int total_money = payParkPB.returnCarMoney( pay_park, difftime);
        	
			if(      userinfo != null 
					 && !isNotSureMoney(userinfo,total_money)
					 && userinfo.getUi_autopay() == 1){
				//虚拟币足够 且 是自动扣费
				userinfo.setUi_vc(userinfo.getUi_vc()-total_money); //分
				count = user_infoDao.updateByKey(userinfo);
				if(count < 1){
					//更新用户扣款数据失败
					//这里需要抛出异常
					throw new QzException("扣款失败");
				}else{
					//扣款成功
					pay_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
					pay_park.setMoney(total_money);//单位 分
					
					count = pay_parkDao.updateByKey(pay_park);
					if(count < 1){
						//扣款失败
						throw new QzException("扣款失败");
					}
					//扣款记录到用户金额明细中去  *** 后续是否进行异常捕获
    				userPB.recordVC( 0, pay_park.getMoney(), pay_park.getMy_order(), pay_park.getOrder_type(), pay_park.getUi_id(), returnData);
					*//**
   * 更新商户账户金额
   *//*
            userPB.upManchentMoney(pay_park.getPu_id(),pay_park.getPu_nd(),pay_park.getMoney(), 1);
				}
			}
		}catch(Exception e){
			throw new QzException("事物异常 payMent",e);
		}
		
		
	}*/

  /*******************************下面是分出的方法*************************************/
  @Autowired
  private CxfServerXHelper cxfServerXHelper;
  @Autowired
  private ETCHelper etcHelper;
  @Autowired
  private EtcPB etcPB;
  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * PDA----处理更新逃逸订单或者未交费订单 状态
   */
  public void upEscapseOrderState(ReturnDataNew returnData, Pay_park pay_park,
      int pay_type, long pi_id, String area_code, int money, String escape_orderids,
      boolean is_sync, Long sync_time, int type, String face_url, String fingerprint,
      String finger_veno)
      throws QzException {

    try {
      String title = null;
      String message = null;
      int pay_source = 0;//支付类型0：现金支付 1:支付宝2：微信3：银联4：钱包5:龙支付 6:ETC快捷支付  7：扫脸支付

      Date date;
      if (is_sync) {//异步上传依客户端时间
        pay_park.setCancel_state(3);
        date = new Date(sync_time);
      } else {
        date = new Date();
      }
      //支付类型 0:自动扣款（钱包）  1：现金支付 2：微信  3：银联  4：钱包 5:龙支付 6:支付宝
      switch (pay_type) {
        case 0://自动扣款（钱包）
        {
          pay_source = 4;
          pay_park.setPay_source(pay_source);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
          //钱包自动支付
          if (pay_park.getPp_state() == 0) {
            //未支付
            //事物处理  该车辆出入的细节
            payParkPB.pda_sure(pay_park, date);
            title = "系统消息";
            message = "亲，你的订单【" + pay_park.getMy_order() + "】，钱包扣款成功。";
          }
        }
        break;
        case 1://现金支付
        {
          if (pay_park.getUpc_id() > 0) {//取消代金券设置
            pay_park.setUpc_id(0);
            pay_park.setDiscount_money(0);
            pay_park.setDiscount_type(0);
            pay_park.setDiscount_name("");
          }

          if (pay_park.getAi_id() > 0) {//取消立减金额
            pay_park.setAi_id(0);
            pay_park.setAi_money(0);
            pay_park.setAi_effect(0);
          }

          pay_source = 0;
          pay_park.setPay_source(pay_source);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
          //现金支付
          pay_park.setPp_state(1);//设置已经支付
          pay_park.setIs_cash(1);//是否现金支付0：在线支付1：现金支付
          pay_park.setUtime(date);//
          //设置是否刷新离开时间
          payParkPB.SetLeaveTime(pay_park, date);
          payParkPB.setIs_over(pay_park, pay_park, date);
          //这里处理订单状态变更推送----PDA现金支付
          if (pay_park.getUi_id() > 0) {
            /**
             * 这里处理推送---PDA现金支付
             */
            title = "系统消息";
            message = "亲，你的订单【" + pay_park.getMy_order() + "】已现金支付成功。";
          }

        }
        break;
        case 2://微信
        {
          pay_source = 2;
          pay_park.setPay_source(pay_source);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
          checkPayed(pay_park);
          pay_park.setPp_state(1);//设置已经支付
          pay_park.setIs_cash(0);//是否现金支付0：在线支付1：现金支付
          pay_park.setUtime(date);//
          //设置是否刷新离开时间
          payParkPB.SetLeaveTime(pay_park, date);
          payParkPB.setIs_over(pay_park, pay_park, date);
          //这里处理订单状态变更推送----PDA现金支付
          if (pay_park.getUi_id() > 0) {
            /**
             * 这里处理推送---微信支付
             */
            title = "系统消息";
            message = "亲，你的订单【" + pay_park.getMy_order() + "】已微信支付成功。";
          }
        }
        break;
        case 3://银联
        {
          pay_source = 3;
          pay_park.setPay_source(pay_source);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
          checkPayed(pay_park);
          pay_park.setPp_state(1);//设置已经支付
          pay_park.setIs_cash(0);//是否现金支付0：在线支付1：现金支付
          pay_park.setUtime(date);//
          //设置是否刷新离开时间
          payParkPB.SetLeaveTime(pay_park, date);
          payParkPB.setIs_over(pay_park, pay_park, date);
          //这里处理订单状态变更推送----银联支付
          if (pay_park.getUi_id() > 0) {
            /**
             * 这里处理推送---银联支付
             */
            title = "系统消息";
            message = "亲，你的订单【" + pay_park.getMy_order() + "】已银联支付成功。";
          }
        }
        break;
        case 4://钱包
        {
          pay_source = 4;
          pay_park.setPay_source(pay_source);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
          //钱包自动支付
          if (pay_park.getPp_state() == 0) {
            //未支付
            //事物处理  该车辆出入的细节
            payParkPB.pda_sure(pay_park, date);
            /**
             * 这里处理推送---车辆临停扣款
             */
            title = "系统消息";
            message = "亲，你的订单【" + pay_park.getMy_order() + "】，钱包扣款成功。";
          }
        }
        break;
        case 5://龙支付
        {
          pay_source = 5;
          pay_park.setPay_source(pay_source);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
          checkPayed(pay_park);
          pay_park.setPp_state(1);//设置已经支付
          pay_park.setIs_cash(0);//是否现金支付0：在线支付1：现金支付
          pay_park.setUtime(date);//
          //设置是否刷新离开时间
          payParkPB.SetLeaveTime(pay_park, date);
          payParkPB.setIs_over(pay_park, pay_park, date);
          //这里处理订单状态变更推送----PDA龙支付支付
          if (pay_park.getUi_id() > 0) {
            /**
             * 这里处理推送---龙支付支付
             */
            title = "系统消息";
            message = "亲，你的订单【" + pay_park.getMy_order() + "】已龙支付支付成功。";
          }
        }
        break;
        case 6://支付宝
        {

          pay_source = 1;
          pay_park.setPay_source(pay_source);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
          checkPayed(pay_park);
          pay_park.setPp_state(1);//设置已经支付
          pay_park.setIs_cash(0);//是否现金支付0：在线支付1：现金支付
          pay_park.setUtime(date);//
          //设置是否刷新离开时间
          payParkPB.SetLeaveTime(pay_park, date);
          payParkPB.setIs_over(pay_park, pay_park, date);
          //这里处理订单状态变更推送----PDA现金支付
          if (pay_park.getUi_id() > 0) {
            /**
             * 这里处理推送---支付宝
             */
            title = "系统消息";
            message = "亲，你的订单【" + pay_park.getMy_order() + "】已支付宝支付成功。";
          }
        }
        break;
        //by jxh 2017-7-6 扫脸支付
        case 7://自动扣款（钱包）--- 扫脸支付：还是扣取用户钱包的钱
        {
          //有用户绑定了的车牌且 是  扫脸支付
          pay_source = 7;
          //未支付
          //这里先进行扫脸认证
          User_info userinfo = user_infoDao.selectByKey(pay_park.getUi_id());
          if (userinfo.ui_face_state == 2) {
//            boolean flag = cxfServerXHelper
//                .doFaceRecon(pay_park.getUi_nd(), getImgPath(face_url),
//                    getImgPath(userinfo.getUi_face_imgs().split(",")[0].trim()));
            boolean flag = cxfServerXHelper
                .faceplusplusCompare(getImgPath(face_url),
                    getImgPath(userinfo.getUi_face_imgs().split(",")[0].trim()));

            if (flag) {
              pay_park.setPay_source(pay_source);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付 6:ETC快捷支付  7：扫脸支付
              //事物处理  该车辆出入的细节
              payParkPB.pda_sure(pay_park, date);
              title = "系统消息";
              message = "亲，你的订单【" + pay_park.getMy_order() + "】，钱包扣款成功。";
            } else {
              throw new QzException("人脸识别失败");
            }
          } else {
            throw new QzException("未开启人脸支付");
          }
        }
        break;
        case 8://指纹支付
        case 9://指纹支付
          //可以进行ETC调用
          if (pay_type == 8) {
            pay_source = 8;
          } else if (pay_type == 9) {
            pay_source = 9;
          }
          int state;//'是否支付成功(0:未支付 1：支付成功 2：支付失败)'
          int is_over;//订单事件是否完成ETC支付（0：未完成  1：完成）
          String pay_orderid = return16UUID();
          String sql = "SELECT t1.* " +
                  " FROM" +
                  " finger_userinfo_new t1 INNER JOIN finger_userinfo_carcode t2" +
                  "    ON t1.fu_id = t2.fu_id" +
                  "  INNER JOIN finger_userinfo_bank t3" +
                  "    ON t1.fu_id = t3.fu_id" +
                  " WHERE" +
                  " t2.car_code = ? " +
                  " AND t1.is_del = 0" +
                  "  AND t1.state = 1" +
                  "  AND t2.isi_del = 0" +
                 // "  AND t2.is_run = 0" +
                  "  AND t3.is_del = 0" +
                  "  AND t3.state = 1" +
                  "  AND t3.is_sign = 1" +
                  "  AND t3.verify_sign = 1" +
                  "  ";
          Finger_userinfo_new finger_userinfo = getMySelfService().queryUniqueT(
              //"SELECT * FROM finger_userinfo WHERE car_code=? AND is_del=0 AND state=1 AND is_sign=1 AND verify_sign=1",
                  sql,
                  Finger_userinfo_new.class, pay_park.getCar_code());
          if (finger_userinfo != null) {
            if (pay_type == 8) {
              if (!StringUtils.hasText(fingerprint)) {
                throw new QzException("指纹支付失败,未上传指纹信息");
              }
              //服务端验证指纹
              List<String> fingerprints = jdbcTemplate.queryForList(
                  "SELECT fingerprint FROM finger_userinfo_relation WHERE fu_id=? AND is_del=0",
                  String.class, finger_userinfo.fu_id);
              boolean fingerprintPass = false;
              for (String fp : fingerprints) {
                if (log.isInfoEnabled()) {
                  log.info("zkfp验证结果：{}", Zkfp.SingleMatch(Base64Utils.decode(fp.getBytes()),
                      Base64Utils.decode(fingerprint.getBytes())));
                }
                if (Zkfp.SingleMatch(Base64Utils.decode(fp.getBytes()),
                    Base64Utils.decode(fingerprint.getBytes())) > 60) {
                  fingerprintPass = true;
                  break;
                }
              }
              if (!fingerprintPass) {
                throw new QzException("指纹支付失败,指纹不匹配");
              }
            } else if (pay_type == 9) {
              if (!StringUtils.hasText(finger_veno)) {
                throw new QzException("指静脉支付失败,未上传指静脉信息");
              }
              //服务端验证指静脉
              List<String> finger_venos = jdbcTemplate.queryForList(
                  "SELECT finger_veno FROM finger_userinfo_relation WHERE fu_id=? AND is_del=0",
                  String.class, finger_userinfo.fu_id);
              boolean finger_venoPass = false;
              out:
              for (String fv : finger_venos) {
                for (String fvu : fv.split(",")) {
                  if (log.isInfoEnabled()) {
                    log.info("zkfv验证结果：{}",
                        Zkfv.SingleMatch(Base64Utils.decode(finger_veno.getBytes()),
                            Base64Utils.decode(fvu.getBytes())));
                  }
                  if (Zkfv.SingleMatch(Base64Utils.decode(finger_veno.getBytes()),
                      Base64Utils.decode(fvu.getBytes())) > 60) {
                    finger_venoPass = true;
                    break out;
                  }
                }
              }
              if (!finger_venoPass) {
                throw new QzException("指静脉支付失败,指静脉不匹配");
              }
            }

            int escape_moneys = 0;//未交费总金额
            if (StringUtils.hasText(escape_orderids)) {
              String[] orderids = escape_orderids.split(",");

              if (orderids != null && orderids.length > 0) {
                for (String orderid : orderids) {
                  Pay_park pay_park2 = payParkPB
                      .selectOnePayPark(orderid);// QueryByOrderId(orderid);
                  if (pay_park2 == null) {
                    throw new QzException("orderids[i]=" + orderid);
                  }
                  //累积未交费金额
                  escape_moneys += pay_park2.getMoney();
                }
              }
            }

            Finger_userinfo_bank bank = getMySelfService().queryUniqueT(
                    "SELECT * FROM finger_userinfo_bank t WHERE t.fu_id = ? LIMIT 1",
                    Finger_userinfo_bank.class,
                    finger_userinfo.getFu_id());
            if(bank == null){
              log.info("银行卡信息不存在");
              throw new QzException("扣款失败");
            }
            ETCResponse eTCResponse = etcHelper.torope(pay_orderid,
                    bank.getFu_nd(),
                    bank.getName(),
                    bank.getSfz_number(),
                    bank.getBank_card_number(),
                money + escape_moneys);
            if (eTCResponse == null || !eTCResponse.isSucceed()) {
              //扣款失败
              throw new QzException("扣款失败");
            } else {
              try {
                //扣款成功
                pay_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
                pay_park.setPay_source(pay_source);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付 6:ETC快捷支付
                pay_park.setUtime(date);//交易时间
                //扣款记录到用户金额明细中去  *** 后续是否进行异常捕获
                String act_name = "ETC支付";
                userPB.recordVC(0, money + escape_moneys, pay_park.getMy_order(),
                    pay_park.getOrder_type(),
                    pay_park.getUi_id(), returnData, act_name, pay_park.getUi_nd(),
                    pay_park.getUi_tel(),
                    pay_park.getPay_source()
                    , pay_park.getUpc_id(), pay_park.getDiscount_type(),
                    (int) pay_park.getDiscount_money(), date);
                /**
                 * 更新商户账户金额
                 */
                userPB
                    .upManchentMoney(pay_park.getPu_id(), pay_park.getPu_nd(), pay_park.getMoney(),
                        1);

                //这里处理订单状态变更推送----车辆临停扣款
                title = "系统消息";
                message = "亲，你的订单【" + pay_park.getMy_order() + "】，ETC扣款成功。";
                state = 1;
                is_over = 1;
              } catch (QzException e) {
                //事务失败 交易冲正
                etcHelper
                    .strikebal(return16UUID(), eTCResponse.getTransDate(), eTCResponse.getSerial(),
                        money + escape_moneys);
                throw e;
              }
            }
            /**
             * 记录ETC扣款调用数据
             */
            etcPB.recordETC_PayLog(finger_userinfo, pay_orderid, pay_park,
                is_over, state, money + escape_moneys, date);
          } else {
            //扣款失败
            throw new QzException("扣款失败");
          }

          break;
        default:
          break;
      }
//      is_cash 是否现金支付0：在线支付1：现金支付2：免费支付3:免费车类型4：包月车类型5：租赁车类型
      switch (type) {//处理类型  0:常规类型  1：免费分钟类型 2:免费车类型 3：包月车类型 4：租赁车类型
        case 0:
          break;
        case 1:
          pay_park.setIs_cash(2);
          break;
        case 2:
          pay_park.setIs_cash(3);
          break;
        case 3:
          pay_park.setIs_cash(4);
          break;
        case 4:
          pay_park.setIs_cash(5);
          break;
        default:
          break;
      }

      if (pay_park.is_over == 2 || pay_park.is_over == 3) {
        payParkPB.setEscapeIs_over(pay_park, pay_park, date);
      }

      int count = pay_parkDao.updateByKey(pay_park);
      if (count == 1) {
        //处理 逃逸或者未支付订单状态
        doEscapeUpdate(pay_park, escape_orderids, pay_source, pay_type, returnData, date);

        //记录随机立免金额的事件
        long upc_id = pay_park.getUpc_id();
        if (pay_type != 1 && upc_id <= 0 && (pay_park.getAi_id() <= 0
            || pay_park.getMoney() > pay_park.getAi_money())) {
          //不是现金支付
          /**
           * 记录随机立免金额的事件
           * 异步操作
           * @param pay_park
           */
          asyncOrderTask.record_random_Event(pay_park, getMySelfService(), activityPB);
        }

        //订单状态变更推送
        if (message != null && title != null && money > 0) {
          payParkPB.pushOrderSate(pay_park.getUi_nd(), message, title, pay_park);
        }

        //使用优惠券
        if (upc_id > 0) {
          User_park_coupon user_park_coupon = parkCouponPB
              .upUserParkCouponState(upc_id, pay_park.getUi_id());
          if (user_park_coupon != null) {
            asyncJpushTask.pushUseCouponMsg(pay_park.getUi_nd(), user_park_coupon);
          }
        }
        //返回结果
        returnData.setReturnData(errorcode_success, "处理成功", JSON.toJSONString(pay_park));
        return;
      } else {
        //返回结果
        returnData.setReturnData(errorcode_data, "处理失败", "", "2");
        throw new QzException("pay_park.orderid=" + pay_park.getMy_order());
      }


    } catch (Exception e) {
      log.error("露天停车场的PDA更新用户自动支付异常", e);
      throw new QzException(e.getMessage());
    }
  }

  /**
   * 获取绝对地址
   *
   * @param url URL 访问路径
   * @turn 文件绝对路径
   */
  private String getImgPath(String url) {
    log.info("获取绝对地址:{}",
        AppProperties.getBaseDir() + "/" + url.replace(AppProperties.getBaseUrl(), ""));
    return AppProperties.getBaseDir() + "/" + url.replace(AppProperties.getBaseUrl(), "");
  }

  /**
   * 检查是否支付完成,如果 完成且参与了随机立减setAi_effect 为1
   */
  private void checkPayed(Pay_park pay_park) throws QzException {
    /**
     * 首先进行完全抵扣情况判断
     */
    int money = pay_park.getMoney();
    Date date = new Date();
    if (pay_park.getUpc_id() > 0 && pay_park.getDiscount_money() > 0) {//有代金券
      //在当面付下单的时候进行了代金券默认抵扣设置
      //首先检查该代金券是否已经被使用
      //如果有折扣券或者金额券
      User_park_coupon user_park_coupon = user_park_couponDao.selectByKey(pay_park.getUpc_id());
      if (user_park_coupon == null) {
        throw new QzException("抵扣的代金券不存在");
      } else {
        //检查该代金券是否过期或者被使用
        /*if (user_park_coupon.getUpc_state() == 1
            || user_park_coupon.getUi_id() != pay_park.getUi_id()
            || user_park_coupon.getEnd_time().getTime() - date.getTime() < 0) {*/
        if (parkCouponPB.isOverTime(user_park_coupon, pay_park.getUi_id())) {
          //这里会存在一种漏洞 就是代金券过期  但是用户又进行了随机立即减免活动
          throw new QzException("抵扣的代金券已经被使用 或者 已经过期 Upc_state="
              + user_park_coupon.getUpc_state()
              + " user_park_coupon.getUi_id()=" + user_park_coupon.getUi_id()
              + " pay_park.getUi_id()=" + pay_park.getUi_id()
              + " user_park_coupon.getEnd_time().getTime() -  date.getTime()="
              + (user_park_coupon.getEnd_time().getTime() - date.getTime() < 0));
        }
      }
      //这里处理优惠券逻辑
      try {
        money = parkCouponPB.doCouponOrder(pay_park, user_park_coupon, money);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        throw new QzException("抵扣的代金券不存在");
      }
      if (money == 0) {
        //完全抵扣情况
        return;
      } else {
        //没有完全抵扣 则需要检查第三方回调表中的支付金额  第三方是否回调成功
        /**
         * 进行非完全抵扣情况判断
         */
        User_pay callPay = payParkPB.isCallPay(pay_park.getMy_order());
        if (callPay == null) {
          //第三方还没有回调成功
          throw new QzException("支付失败-第三方支付还没有回调成功");
        }
      }
    } else {

      //by jxh 2017-3-21 首先验证是否进行了随机活动开启
      //如果没有开启 首次支付赠券活动 【“吾泊首单立返“活动】 则检查是否开启了随机立免
      long ai_id = 0;
      if (pay_park.getPay_source() == 5) {
        //龙支付 0.5-1
        ai_id = ActivityEnum.activity_RandomMoney_lzf.getValue();
      } else {
        ai_id = ActivityEnum.activity_RandomMoney.getValue();
      }
      Activity_info activity_info = activityPB.selectActivityByid(ai_id);
      if (!activityPB.isActivityEffect(activity_info)) {
        //没有开启或者无效 则不做后续工作
        return;
      }

      //没有代金券
      if (pay_park.getAi_id() > 0 && pay_park.getMoney() - pay_park.getAi_money() <= 0) {
        //随机立即减免完全抵扣
        if (activity_info.getAi_id() == ActivityEnum.activity_RandomMoney_lzf.getValue()
            && pay_park.getPay_source() == 5) {
          //龙支付随机立即减免
          pay_park.setAi_effect(1);
          return;
        }
        if (activity_info.getAi_id() == ActivityEnum.activity_RandomMoney.getValue()
            && pay_park.getPay_source() != 5) {
          //吾泊随机立即减免
          pay_park.setAi_effect(1);
          return;
        }

      } else {
        //非随机立即减免完全抵扣
        /**
         * 进行非完全抵扣情况判断
         */
        User_pay callPay = payParkPB.isCallPay(pay_park.getMy_order());
        if (callPay == null) {
          //第三方还没有回调成功
          throw new QzException("支付失败-第三方支付还没有回调成功");
        } else if (pay_park.getAi_id() > 0 && pay_park.getMoney() - pay_park.getAi_money() > 0) {
          //随机立即减免完全抵扣
          if (activity_info.getAi_id() == ActivityEnum.activity_RandomMoney_lzf.getValue()
              && pay_park.getPay_source() == 5) {
            //龙支付随机立即减免
            pay_park.setAi_effect(1);
            return;
          }
          if (activity_info.getAi_id() == ActivityEnum.activity_RandomMoney.getValue()
              && pay_park.getPay_source() != 5) {
            //吾泊随机立即减免
            pay_park.setAi_effect(1);
            return;
          }
        }
      }
    }

  }

  /**
   * 更新车牌绑定
   */
  public void update_car(User_carcode user_carcode, ReturnDataNew returnData) throws Exception {
    /**
     * 获取某车牌还没有支付且没有绑定用户的最后一条某停车场的订单
     * 如果存在则 把该订单的用户信息设置进去---订单与用户关联
     */
    Pay_park pay_park = carPB.Query_PayParkByCarCode(user_carcode.getCar_code());
    if (pay_park != null) {
      //把用户与该订单绑定
      pay_park.setUi_id(user_carcode.getUi_id());
      pay_park.setUi_nd(user_carcode.getUi_nd());
      pay_park.setUi_tel(user_carcode.getUi_tel());
      int count = pay_parkDao.updateByKey(pay_park);
      if (count != 1) {
        returnData.setReturnData(errorcode_data, "绑定失败", "");
        throw new QzException("绑定失败");
      }
    }
    int count = user_carcodeDao.updateByKey(user_carcode);
    if (count < 1) {
      //更新失败
      returnData.setReturnData(errorcode_data, "更新用户车辆信息失败", "");
      return;

    }

    /**
     * by jxh 2017-4-21
     * 处理用户PDA 欠费订单跟用户的关联
     */
    doOwingOrderBind(returnData, user_carcode);

    bindCarCodeParkRentUser(user_carcode);

    returnData.setReturnData(errorcode_success, "更新用户车辆信息成功", user_carcode);
  }


  /**
   * 处理未交费订单的处理
   */
  public void doEscapeUpdate(Pay_park pay_park, String escape_orderids, int pay_source,
      int pay_type, ReturnDataNew returnData, Date date) throws QzException {
    if (escape_orderids != null && !"".equalsIgnoreCase(escape_orderids)) {
      String[] orderids = escape_orderids.split(",");

      if (orderids != null && orderids.length > 0) {
        int escape_moneys = 0;//未交费总金额
        for (int i = 0; i < orderids.length; i++) {
          Pay_park pay_park2 = payParkPB.selectOnePayPark(orderids[i]);// QueryByOrderId(orderid);
          if (pay_park2 == null) {
            throw new QzException("orderids[i]=" + orderids[i]);
          }
          //累积未交费金额
          escape_moneys += pay_park2.getMoney();

          pay_park2.setPp_state(1);

          if (pay_type == 1) {
            //现金支付
            pay_park2.setIs_cash(1);
          } else {
            pay_park2.setIs_cash(0);
          }

          //设置是否刷新离开时间
//          payParkPB.SetLeaveTime(pay_park2, date);
          pay_park2.setUtime(date);
          pay_park2.setPay_source(pay_source);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付

          payParkPB.setEscapeIs_over(pay_park2, pay_park, date);

          //by jxh 2017-7-5 如果用户用钱包支付 则续约把欠费订单的用户绑定关系设置进去
          if (pay_type == 0 || pay_type == 4 || pay_type == 7) {
            pay_park2.setUi_id(pay_park.getUi_id());
            pay_park2.setUi_nd(pay_park.getUi_nd());
            pay_park2.setUi_tel(pay_park.getUi_tel());
          }

          int count = pay_parkDao.updateByKey(pay_park2);
          if (count != 1) {
            throw new QzException("updateByKey orderids[i]=" + orderids[i]);
          } else {
            //更新成功则 记录明细
            //记录用户账户变更
            String act_name = "PDA欠费补交";
            userPB
                .recordVC(0, pay_park2.getMoney(), pay_park2.getMy_order(), 0, pay_park.getUi_id(),
                    returnData, act_name, pay_park.getUi_nd(), pay_park.getUi_tel(),
                    pay_park2.getPay_source()
                    , pay_park2.getUpc_id(), pay_park2.getDiscount_type(),
                    (int) pay_park2.getDiscount_money(), date);
          }
        }//for 循环结束

        //这里处理未交费金额的 扣除
        //支付类型 0:自动扣款（钱包）  1：现金支付 2：微信  3：银联  4：钱包 5:龙支付 6:支付宝
        if ((pay_type == 0 || pay_type == 4 || pay_type == 7) && escape_moneys > 0) {
          // 虚拟币比例1元比100 分
          User_info userinfo = user_infoDao.selectByKey(pay_park.getUi_id());
          if (userinfo == null) {
            throw new QzException("用户不存在");
          }
          // 钱包金额判断
          if (userinfo.getUi_vc() - escape_moneys < 0) {
            // 抛出钱包金额不足的异常
            String title = "系统消息";
            String message = "亲，你进行补交欠费，因钱包余额不足,自动扣款失败。";
            asyncJpushTask.pushOrderNoMoney(
                userinfo.getUuid(), message, title,
                pay_park);

            Park_info park_info = returnParkInfo(
                pay_park.getPi_id(),
                pay_park.getArea_code());
            jpushPB.pushPDAOrderNoMoney(park_info.getMac(),
                "订单【" + pay_park.getMy_order()
                    + "】，因钱包余额不足,自动扣款失败。", title,
                pay_park);
            throw new QzException("扣款钱包金额不足");
          }
          // 虚拟币足够 且 是自动扣费
          userinfo.setUi_vc(userinfo.getUi_vc() - escape_moneys); // 分
          int count = user_infoDao.updateByKey(userinfo);
          if (count < 1) {
            // 更新用户扣款数据失败
            // 这里需要抛出异常
            // 扣款失败
            throw new QzException("扣款失败");
          }

          // 更新商户金额
          /**
           * 更新商户账户金额
           */
          //userPB.upManchentMoney(pay_park.getPu_id(), pay_park.getPu_nd(), pay_park.getMoney(), 1);

        }

      }

    }
  }


  /**
   * by jxh 2017-4-21 处理用户PDA 欠费订单跟用户的关联
   */
  public void doOwingOrderBind(ReturnDataNew returnData, User_carcode user_carcode)
      throws QzException {
    List<Pay_park> pay_park_list = carPB.Query_PayParkByCarCodeOwing(user_carcode.getCar_code());
    if (pay_park_list != null && pay_park_list.size() > 0) {
      for (Pay_park pay_park : pay_park_list) {
        //把用户与该订单绑定
        //备注：这里以后进行 批量更新处理提升性能   确保不锁定数据库表 这里进行妥协
        pay_park.setUi_id(user_carcode.getUi_id());
        pay_park.setUi_nd(user_carcode.getUi_nd());
        pay_park.setUi_tel(user_carcode.getUi_tel());
        int count = pay_parkDao.updateByKey(pay_park);
        if (count != 1) {
          returnData.setReturnData(errorcode_data, "绑定失败", "");
          throw new QzException("绑定失败");
        }
      }
    }
  }
/*****************************下面是分离的方法************************************/

}
