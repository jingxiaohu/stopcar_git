package com.park.app.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.Park_info;
import com.park.bean.Pay_month_park;
import com.park.bean.Pay_park;
import com.park.bean.Rental_charging_rule;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_info;
import com.park.bean.User_park_coupon;
import com.park.bean.User_vc_act;
import com.park.exception.QzException;
import com.park.jpush.PDA.bean.PDAPushMessage;
import com.park.jpush.bean.JPushMessageBean;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.ActivityPB;
import com.park.mvc.service.common.ParkCouponPB;
import com.park.mvc.service.common.ParkInfoPB;
import com.park.mvc.service.common.PayMonthParkPB;
import com.park.mvc.service.common.PayParkPB;
import com.park.mvc.service.common.UserPB;
import com.park.transaction.CarTransaction;
import com.park.transaction.UserTransaction;
import com.park.util.RequestUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理用户订单管理
 *
 * @author jingxiaohu
 */
@Service
public class AppOrderBiz extends BaseBiz {

  @Autowired
  private UserTransaction userTransaction;
  @Autowired
  private CarTransaction carTransaction;
  @Autowired
  protected CarBiz carBiz;
  @Autowired
  protected PayParkPB payParkPB;
  @Autowired
  protected PayMonthParkPB payMonthParkPB;

  @Autowired
  protected ParkInfoPB parkInfoPB;
  @Autowired
  protected ActivityPB activityPB;
  @Autowired
  protected ParkCouponPB parkCouponPB;

  /**
   * 用户预约下单普通车位
   */
  public void expect_order(ReturnDataNew returnData, int dtype, long ui_id,
      long pi_id, int pay_type, String expect_info, int expect_money,
      int expect_time, String car_code, String pp_versioncode, String area_code) {

    try {
      //用户预约车位前的检查
      Pay_park pay_park = QueryPayPark(ui_id, pi_id, area_code, car_code);
      if (pay_park != null) {
    	  //有未支付且没有关闭且没有逻辑删除的订单

           if (pay_park.getOrder_type() == 1) {
	        	//是预约订单  ( 3：预约失败  5：取消预约成功  这两种不带入计算 )
	        	//预约状态 0:未预约 1：预约中  2：预约成功  3：预约失败 4：取消预约中 5：取消预约成功 6：取消预约失败'
	            if (pay_park.getExpect_state() == 1
	                || pay_park.getExpect_state() == 2
	                || pay_park.getExpect_state() == 4
	                || pay_park.getExpect_state() == 6) {
	              //已经预约了该停车场 还未扣款
	              //您还有该停车场未完成的预约订单
	              returnData.setReturnData(errorcode_data, "您还有该停车场未完成的预约订单，亲！", "");
	              return;
	            }
	        } else {
	        	 //已到达停车场 但是没有付款的订单
	            //您还有该停车场未完成的订单
	            returnData.setReturnData(errorcode_data, "您还有该停车场未完成的订单，亲！", "");
	            return;
	        }
      }


      //首先检查该用户账户上是否钱足够担负的起预约超时扣款
      User_info user_info = user_infoDao.selectByKey(ui_id);
      if (user_info == null) {
        //用户不存在
        returnData.setReturnData(errorcode_data, "用户不存在", "");
        return;
      }
      if (user_info.getUi_autopay() == 0) {//是否自动支付 ：0 ：不是 1：是
        //没有开启自动扣款
        returnData.setReturnData(errorcode_data, "没有开启自动扣款", "");
        return;
      }
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        //该停车场不存在
        returnData.setReturnData(errorcode_data, "该停车场不存在", "");
        return;
      } else {
        //检查该停车场是否出现故障
        if (park_info.getIs_fault() == 1) {
          //不允许进行预约和租赁
          returnData.setReturnData(errorcode_data, "该停车场目前出现预约故障，暂时不能进行预约服务", "");
          return;
        }

        //检查该停车场是否开启了预约
        if (park_info.getIs_expect() == 0) {
          //检查该停车场是否开启了预约
          returnData.setReturnData(errorcode_data, "该停车场目前还未开启预约服务", "");
          return;
        }
        //by jxh 2016-12-28
        //检查该停车场是否还有临停空位如果没有不允许进行预约
        if (park_info.getCarport_space() == 0) {
          returnData.setReturnData(errorcode_data, "该停车场目前已无可预约的车位", "");
          return;
        }

      }

      //by jxh 2017-6-22  从数据库拿取最新的预约价格
      expect_money = park_info.getExpect_money();


      if (isNotSureMoney(user_info, park_info.getExpect_money())) {
        //账户余额不足
        returnData.setReturnData(errorcode_data, "账户余额不足", "", "1");
        return;
      }
      Date date = new Date();
      //已经租赁了该停车场且未到期的车辆不允许再次对该停车场进行预约
      Pay_month_park pay_month_park = payParkPB
          .queryRentOrder(pi_id, car_code, area_code, ui_id, date);
      if (pay_month_park != null) {
        //已经租赁了该停车场且未到期的车辆不允许再次对该停车场进行预约
        returnData.setReturnData(errorcode_data, "已经租赁了该停车场且未到期的车辆不允许再次对该停车场进行预约!", "");
        return;
      }

      pay_park = new Pay_park();
      pay_park.setAllege_state(0);//申述状态 0:未申述 1：已申述
      pay_park.setArrive_time(date);//到场时间
      pay_park.setLeave_time(date);//离场时间
      pay_park.setCar_code(car_code);//车牌号
      pay_park.setCtime(date);//创建时间
//park_type;//int(11)    停车场类型0：道闸停车场1：占道车场2：露天立体车库停车场
//      product_type 产品标号1位：0 道闸 1 PDA  2 地磁PDA  3 mepos道闸
      int product_type = 0;
      switch (park_info.park_type) {
        case 0:
          product_type = 0;
        case 1:
          product_type = 1;
      }
      pay_park.setMy_order(returnNewOrderId(area_code, product_type, pi_id, 3));//我们自己生成的订单号
      pay_park.setOrder_type(1);//下单类型 0: 普通下单  1：预约下单 2：租赁包月订单
//    	    pay_park.setOther_order(value);//第三方的 支付单号
//    	    pay_park.setPay_source(pay_source);//支付类型 1:支付宝 2：微信 3：银联
      pay_park.setPay_type(pay_type);//支付类型 0:手动输入密码支付 1：快捷支付（服务器可以请求第三方直接扣款）
      pay_park.setPhone_type(dtype);//手机类型 0:android 1：IOS
      pay_park.setPi_id(pi_id);//支付停车场主键ID
      pay_park.setPp_state(0);//支付状态 0:未支付 1：已经支付
      pay_park.setPp_versioncode(pp_versioncode);//当前APPSDK版本号 （内部升级版本代号）
      pay_park.setUi_id(ui_id);//用户ID
      pay_park.setUi_nd(user_info.getUuid());//用户唯一标识符
      pay_park.setUi_tel(user_info.getUi_tel());//用户电话号码
      pay_park.setUtime(date);//更新时间
      pay_park.setExpect_info(expect_info);//预约提示信息
      pay_park.setExpect_money(expect_money);//预约价格
      pay_park.setExpect_time(expect_time);//预约时间 单位分钟
//    	    pay_park.setMoney(value);//支付金额（单位 分）
      pay_park.setNote("用户预约下单");
      //省市县区域代码
      pay_park.setAddress_name(park_info.getAddress_name());//停车场地址
      pay_park.setPi_name(park_info.getPi_name());//停车场名称
      pay_park.setArea_code(area_code);//省市县编号
      pay_park.setPark_type(park_info.getPark_type());

      //设置商户唯一标识
      pay_park.setPu_id(park_info.getPu_id());
      pay_park.setPu_nd(park_info.getPu_nd());

      //设置经纬度
      pay_park.setLng(park_info.getLng());
      pay_park.setLat(park_info.getLat());
      //设置预约状态
      if (park_info.getPark_type() == 0) {
        //道闸停车场
        pay_park.setExpect_state(1);
      } else {
        pay_park.setExpect_state(2);
      }

      int id = pay_parkDao.insert(pay_park);
      if (id < 1) {
        //下单失败
        log.error("ParkinfoBiz.cameraCarOrder is error 下单插入的时候失败");
        returnData.setReturnData(errorcode_data, "预约失败", "");
        return;
      }
      //设置订单主键
      pay_park.setId(id);

      if (park_info.getPark_type() == 1) {
        //占道停车场
        //这里更新预约车辆的数量变化
        parkInfoPB.upCarNumExpect(park_info, 1);

        try {
          //预约成功 进行JPUSH推送
          PDAPushMessage pDAPushMessage = new PDAPushMessage();
          pDAPushMessage.setCar_code(car_code);
          pDAPushMessage.setOrderid(pay_park.getMy_order());
          pDAPushMessage.setTime(pay_park.getCtime());
          pDAPushMessage.setUi_id(ui_id);
          pDAPushMessage.setUi_tel(user_info.getUi_tel());
          pDAPushMessage.setUiid(user_info.getUuid());
          pDAPushMessage.setId(id);
          pDAPushMessage.setType(1);

          JPushMessageBean jPushMessageBean = new JPushMessageBean();
          jPushMessageBean.setType(1);
          jPushMessageBean.setMessage("预约成功");
          jPushMessageBean.setTitle("系统消息");
          jPushMessageBean.setMessageJson((JSON) JSON.toJSON(pDAPushMessage));
          asyncJpushTask.doPdaJpushPDA(jPushMessageBean, park_info.getMac());
        } catch (Exception e) {

          log.error("预约下单推送失败", e);
        }
      } else {
        //道闸停车场和立体停车场
        //这里设置预约锁定金额
        try {
          userTransaction
              .doUnLockMoney(0, 0, null, pay_park.getExpect_money(), user_info
                  , pay_park.getMy_order(), pi_id, area_code, pay_month_park, pay_park, returnData);
        } catch (Exception e) {

          log.error("预约设置锁定预约金额失败", e);
        }

        //mq 推送
        try {
			JPushMessageBean jPushMessageBean = new JPushMessageBean();
			jPushMessageBean.setType(1);
			jPushMessageBean.setDate(new Date());
			jPushMessageBean.setMessage("预约成功");
			jPushMessageBean.setTitle("预约消息");
			JSONObject object = new JSONObject();
			object.put("pay_park", pay_park);
			object.put("type", 1);// 类型 1:预约  2：取消预约
			jPushMessageBean.setMessageJson(object);
			rabbitPublisher.publish2Gate(area_code, pi_id, jPushMessageBean, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("用户预约  mq 推送 失败", e);
		}
      }

      //返回结果
      returnData.setReturnData(errorcode_success,  "预约下单成功", pay_park);
      return;
    } catch (Exception e) {

      log.error("ParkinfoBiz.expect_order is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 用户下单租赁包月车位
   */
  public void pay_rent_order(ReturnDataNew returnData, int dtype,
      long ui_id, long pi_id, int pay_type, int month_num,
      String month_info, String car_code, String pp_versioncode, String area_code, long upc_id,
      int pay_source, String permit_time, int is_24hours, String orderid) {
    // pay_source;//支付类型 1:支付l宝 2：微信 3：银联  4：钱包
    try {
      //先检查该用户在该停车场已经出现了临停费用  则不允许没有缴费临停   之前进行租赁
      Pay_park pay_park = payParkPB.QueryPayPark(ui_id, pi_id, car_code, area_code);
      if (pay_park != null) {
        returnData.setReturnData(errorcode_data, "你还没有进行临停缴费，不允许进行租赁", "");
        return;
      }

      //获取该场地的信息
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        returnData.setReturnData(errorcode_data, "该停车场不存在", "");
        return;
      } else {
        //检查该停车场是否出现故障
        if (park_info.getIs_fault() == 1) {
          //不允许进行预约和租赁
          returnData.setReturnData(errorcode_data, "该停车场目前出现租赁故障，暂时不能进行租赁服务", "");
          return;
        }
      }
      Pay_month_park pay_month_park = null;
      User_info user_info = null;
      if (ui_id > 0) {
        user_info = user_infoDao.selectByKey(ui_id);
        //首先判断用户是否存在
        if (user_info == null) {
          //更新失败
          returnData.setReturnData(errorcode_data, "用户不存在", "");
          return;
        }
      }
      User_park_coupon user_park_coupon = null;
      if (!RequestUtil.checkObjectBlank(orderid)) {
        pay_month_park = pay_rent_order_haveOrderid(returnData, dtype, ui_id, pi_id, pay_type,
            month_num
            , month_info, car_code, pp_versioncode, area_code, upc_id
            , pay_source, permit_time, is_24hours, orderid
            , user_info, park_info, user_park_coupon);
      } else {
        pay_month_park = pay_rent_order_NoneOrderid(returnData, dtype, ui_id
            , pi_id, pay_type, month_num, month_info, car_code
            , pp_versioncode, area_code, upc_id, pay_source
            , permit_time, is_24hours, orderid, user_info
            , park_info, user_park_coupon);
      }

      if (pay_month_park == null) {
        //租赁失败
        return;
      }

      //设置返回的消息
      String message = "下单成功";

      if (user_info != null) {
        //处理扣款等 --- 支付的时候才进行扣款处理
        //支付类型 1:支付宝  2：微信  3：银联  4：钱包 5:龙支付
        //钱包   Park_type   INT    停车场类型0：道闸停车场1：占道车场2：露天立体车库停车场
        if (pay_source == 4 && park_info.getPark_type() == 0) {
          //处理扣款等
//          if (user_info.getUi_vc() < pay_month_park.getMoney()) {
          if (isNotSureMoney(user_info, pay_month_park.getMoney())) {
            returnData.setReturnData(errorcode_data, "钱包金额不足", "");
            return;
          }

          try {
            //事物处理
            userTransaction
                .record_rent_user_vc_act(returnData, user_info, pay_month_park.getMoney(),
                    pay_month_park.getMy_order(), 0, 0, user_park_coupon);
            message = "支付成功";
            //这里更新车辆出入库的数量变化 ****** 这里需要再次思考
          /*if (park_info.getPark_type() == 1) {
            parkInfoPB.upCarNum(park_info, 0, 2);
          }*/

          } catch (Exception e) {
            if (!e.getMessage().contains("账户余额不足")) {
              log.error("事物处理 record_user_vc_act  付款失败", e);
            }
            returnData.setReturnData(errorcode_data, "租赁失败", "");
            return;
          }
          //mq 推送
          JPushMessageBean jPushMessageBean = new JPushMessageBean();
          jPushMessageBean.setType(2);
          jPushMessageBean.setDate(new Date());
          jPushMessageBean.setMessage("租赁成功");
          jPushMessageBean.setTitle("租赁消息");
          JSONObject object = new JSONObject();
          object.put("pay_month_park", pay_month_park);
          object.put("type", 1);// 类型 1:租赁  2：取消租赁
          jPushMessageBean.setMessageJson(object);
          rabbitPublisher.publish2Gate(area_code, pi_id, jPushMessageBean, true);
        }
      }
      //返回结果
      returnData.setReturnData(errorcode_success, message, pay_month_park);
      return;
    } catch (Exception e) {

      log.error("ParkinfoBiz.pay_rent_order is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 用户取消订单
   *
   * @param type 0：普通车位预约  1：租赁车位预约
   * @param area_code 省市区代码
   * @param pi_id 停车场主键ID
   */
  public void cancel_order(ReturnDataNew returnData, int dtype, long ui_id,
      String orderid, int type, long pi_id, String area_code) {

    try {
      if (type == 0) {
        //首先查询订单是否存在  然后比对订单时间是否超过5分钟 如果超过5分钟则不能进行取消订单
        Pay_park pay_park = QueryByOrderId(orderid, ui_id);
        if (pay_park == null) {
          returnData.setReturnData(errorcode_data, "该订单不存在", "");
          return;
        }
        //下单类型0:普通下单1：预约下单2：租赁包月订单
        int order_type = pay_park.getOrder_type();
        if (order_type == 1) {
          //预约订单
          if (System.currentTimeMillis() - pay_park.getCtime().getTime() > 5 * 60 * 1000) {
            //超过5分钟
            returnData.setReturnData(errorcode_data, "超过5分钟不允许取消订单", "");
            return;
          }
        }
        //预约下单已到达停车场不允许取消
        if (pay_park.getArrive_time().getTime() > pay_park.getCtime().getTime()) {
          //到场的车不能取消订单
          returnData.setReturnData(errorcode_data, "进入停车场后不允许取消订单", "");
          return;
        }
        //已经超时的订单不允许取消
        if (pay_park.getIs_expect_outtime() == 1) {
          //是否预约已经超时0：未超时1：已经超时
          returnData.setReturnData(errorcode_data, "该订单已经超时，不允许取消", "");
          return;
        }
        if (pay_park.getExpect_state() >= 4) {
          //已经取消过了
          returnData.setReturnData(errorcode_data, "该订单已经取消过了", "");
          return;
        }
        Park_info park_info = returnParkInfo(pi_id, area_code);
        if (park_info == null) {
          //该停车场不存在
          returnData.setReturnData(errorcode_data, "该停车场不存在", "");
          return;
        }
        if (park_info.getPark_type() == 1) {
          //占道停车
          // 取消普通车位预约订单
          pay_park.setExpect_state(5);//取消成功
          pay_park.setCancel_state(1);//关闭成功
          int count = pay_parkDao.updateByKey(pay_park);
          if (count < 1) {
            //取消普通车位订单失败
            returnData.setReturnData(errorcode_data, "取消订单失败", "");
            return;
          }

          if (order_type == 1) {
            //预约订单
            try {
              // 车位数变动
              /**
               * 2016-11-24
               * 车库车辆数量变更
               */
              //占道停车场
              if (park_info.getPark_type() == 1) {
                parkInfoPB.upCarNumExpect(park_info, 2);
              }

              try {
                //取消预约成功 进行JPUSH推送
                PDAPushMessage pDAPushMessage = new PDAPushMessage();
                pDAPushMessage.setCar_code(pay_park.getCar_code());
                pDAPushMessage.setOrderid(pay_park.getMy_order());
                pDAPushMessage.setTime(pay_park.getCtime());
                pDAPushMessage.setUi_id(ui_id);
                User_info user_info = user_infoDao.selectByKey(ui_id);
                if (user_info == null) {
                  //用户不存在
                  log.error("取消预约下单推送失败  用户不存在 ui_id=" + ui_id);
                  return;
                }
                pDAPushMessage.setUi_tel(user_info.getUi_tel());
                pDAPushMessage.setUiid(user_info.getUuid());
                pDAPushMessage.setId(pay_park.getId());
                pDAPushMessage.setType(2);

                JPushMessageBean jPushMessageBean = new JPushMessageBean();
                jPushMessageBean.setType(1);
                jPushMessageBean.setTitle("系统消息");
                jPushMessageBean.setMessage("取消预约成功");
                jPushMessageBean.setMessageJson((JSON) JSON.toJSON(pDAPushMessage));
                asyncJpushTask.doPdaJpushPDA(jPushMessageBean, park_info.getMac());

              } catch (Exception e) {

                log.error("取消预约下单推送失败", e);
              }
            } catch (Exception e) {

              log.error("取消订单失败", e);
            }
          }
        } else {
          //道闸停车
          // 取消普通车位预约订单
          pay_park.setExpect_state(4);//取消中
          int count = pay_parkDao.updateByKey(pay_park);
          if (count < 1) {
            //取消普通车位订单失败
            returnData.setReturnData(errorcode_data, "取消订单失败", "");
            return;
          }

        }
        //mq 推送
        JPushMessageBean mqMsg = new JPushMessageBean();
        mqMsg.setType(1);
        mqMsg.setDate(new Date());
        mqMsg.setMessage("取消预约成功");
        mqMsg.setTitle("预约消息");
        JSONObject object = new JSONObject();
        object.put("pay_park", pay_park);
        object.put("type", 2);// 类型 1:预约  2：取消预约
        mqMsg.setMessageJson(object);
        rabbitPublisher.publish2Gate(area_code, pi_id, mqMsg, true);
        //取消普通车位订单成功
        returnData.setReturnData(errorcode_success, "取消订单成功", pay_park);
        return;

      } else {
        /**
         * 暂时不考虑 租赁车位的订单取消问题
         */
        Pay_month_park pay_month_park = payMonthParkPB.selectOnePayMonthPark(orderid);
        if (pay_month_park == null) {
          returnData.setReturnData(errorcode_data, "该订单不存在", "");
          return;
        }
        if (pay_month_park.getRent_state() == 1) {
          returnData.setReturnData(errorcode_data, "租赁中且扣款的订单不允许删除，亲！", "");
          return;
        }

        // 关闭租赁待支付订单
        String sql = "update pay_month_park set cancel_state=1 ,is_over=1 where my_order=:orderid";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orderid", orderid);
        int count = getMySelfService().updateBySQL(sql, paramMap);
        if (count == 1) {
          //mq 推送
          JPushMessageBean jPushMessageBean = new JPushMessageBean();
          jPushMessageBean.setType(2);
          jPushMessageBean.setDate(new Date());
          jPushMessageBean.setMessage("关闭订单成功");
          jPushMessageBean.setTitle("租赁消息");
          JSONObject object = new JSONObject();
          object.put("pay_month_park", pay_month_park);
          object.put("type", 2);// 类型 1:租赁  2：取消租赁
          jPushMessageBean.setMessageJson(object);
          rabbitPublisher.publish2Gate(area_code, pi_id, jPushMessageBean, true);
          //取消租赁车位订单成功
          returnData.setReturnData(errorcode_success, "关闭订单成功", "");
          return;
        }
        //取消租赁车位订单失败
        returnData.setReturnData(errorcode_data, "取消订单失败", "");
        return;
      }
    } catch (Exception e) {
      log.error("CarBiz cancel_order is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 用户删除订单
   */
  public void delete_order(ReturnDataNew returnData, int dtype, long ui_id,
      String orderid, int type, long pi_id, String area_code) {

    try {
      //type 0：普通车位  1：租赁车位
      if (type == 0) {
        //获取订单
        Pay_park pay_park = payParkPB.selectOnePayPark(orderid);// QueryByOrderId(orderid);
        if (pay_park == null) {
          //该订单不存在
          returnData.setReturnData(errorcode_data, "该订单不存在,亲!", "");
          return;
        }
        if (pay_park.getOrder_type() == 0) {
          //普通订单
          if (pay_park.getPp_state() == 0) {
            //未支付
            returnData.setReturnData(errorcode_data, "未支付的订单不允许删除,亲!", "");
            return;
          }
        } else {
          //预约订单
          if (pay_park.getExpect_state() == 2 && pay_park.getPp_state() == 0) {
            //预约成功 且 没有支付
            returnData.setReturnData(errorcode_data, "预约成功且没有支付的订单不能删除,亲!", "");
            return;
          }
          if (pay_park.getExpect_state() == 6 && pay_park.getPp_state() == 0) {
            //取消预约失败 且 没有支付
            returnData.setReturnData(errorcode_data, "取消预约失败且没有支付的订单不能删除,亲!", "");
            return;
          }
        }
        //申诉中的订单不能删除
        if (pay_park.getAllege_state() == 1) {
          //预约成功 且 没有支付
          returnData.setReturnData(errorcode_data, "申诉中的订单不能删除,亲!", "");
          return;
        }

        //普通车位
        String sql = "update pay_park set is_del=1 where my_order=:orderid";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orderid", orderid);
        int count = getMySelfService().updateBySQL(sql, paramMap);
        if (count == 1) {
          //删除普通车位预约订单成功
          returnData.setReturnData(errorcode_success, "删除订单成功", "");
          return;
        }
        //删除普通车位预约订单失败
        returnData.setReturnData(errorcode_data, "删除订单失败", "");
        return;

      } else {
        Date date = new Date();
        Pay_month_park pay_month_park = payMonthParkPB.selectOnePayMonthPark(orderid);
        if (pay_month_park == null) {
          //该订单不存在
          returnData.setReturnData(errorcode_data, "该订单不存在,亲!", "");
          return;
        }
        if (pay_month_park.getPp_state() == 0 && pay_month_park.getCancel_state() == 0) {
          //没有支付的租赁订单不允许删除
          returnData.setReturnData(errorcode_data, "该订单还未支付不允许删除,亲!", "");
          return;
        }
        if (pay_month_park.getPp_state() == 1 && (
            pay_month_park.getEnd_time().getTime() - date.getTime() > 0)) {
          //没有支付的租赁订单不允许删除
          returnData.setReturnData(errorcode_data, "已支付且未到期的租赁订单不允许删除,亲!", "");
          return;
        }
        //申诉中的订单不能删除
        if (pay_month_park.getAllege_state() == 1) {
          //预约成功 且 没有支付
          returnData.setReturnData(errorcode_data, "申诉中的订单不能删除,亲!", "");
          return;
        }
        // 删除租赁车位订单
        String sql = "update pay_month_park set is_del=1 where my_order=:orderid";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orderid", orderid);
        int count = getMySelfService().updateBySQL(sql, paramMap);
        if (count == 1) {
          //删除租赁车位预约订单成功
          returnData.setReturnData(errorcode_success, "删除订单成功", "");
          return;
        }
        //删除租赁车位预约订单失败
        returnData.setReturnData(errorcode_data, "删除订单失败", "");
        return;
      }
    } catch (Exception e) {
      log.error("CarBiz delete_order is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


  /**
   * 读取停车场租赁详情
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void read_parkrent_info(ReturnDataNew returnData, int dtype, long ui_id,
      long pi_id, String area_code) {

    try {
      JSONObject returnobj = new JSONObject();

      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        //该停车场不存在
        returnData.setReturnData(errorcode_data, "该停车场不存在", "");
        return;
      }
      //停车场基本信息
      returnobj.put("park_info", park_info);
      //获取该停车场  蓝牌小汽车的包月规则
      String sql = "SELECT *  FROM rental_charging_rule WHERE pi_id=? AND area_code=? AND  car_type=1 AND car_code_color=1  AND  rcr_type=1 LIMIT 1";
      Rental_charging_rule rule = getMySelfService()
          .queryUniqueT(sql, Rental_charging_rule.class, pi_id, area_code);
      if (rule == null) {
        //该停车场不存在包月租赁业务
        returnData.setReturnData(errorcode_data, "该停车场不存在包月租赁业务", "");
        return;
      }
      //填充 包月规则详情
      returnobj.put("rule", rule);

      //优惠券列表数据
      sql = "SELECT * FROM user_park_coupon WHERE ui_id =:ui_id AND upc_state=0 AND end_time > :time ORDER BY ctime  DESC";
      Map<String, Object> paramMap = new HashMap<String, Object>();
      paramMap.put("ui_id", ui_id);
      paramMap.put("time", new Date());
      List<Map<String, Object>> list = getMySelfService().queryForList(sql, paramMap);

      //填充优惠券列表
      if (list == null || list.size() == 0) {
        returnobj.put("coupon", "");
      } else {
        returnobj.put("coupon", list);
      }

      returnData.setReturnData(errorcode_success, "读取停车场租赁详情成功", returnobj);
      return;
    } catch (Exception e) {
      log.error("orderBiz read_parkrent_info is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


  /**
   * 用户停车缴费读取订单
   */
  public void read_pay_order(ReturnDataNew returnData, int dtype, long ui_id,
      String orderid) {

    try {
      JSONObject returnobj = new JSONObject();
      //从订单表中 获取该用户的停车缴费订单信息
      Pay_park pay_park = payParkPB.selectOnePayPark(orderid);
      if (pay_park == null) {
        //该车辆未出入
        returnData.setReturnData(errorcode_data, "该车辆未出入", "");
        return;
      }

      Date date = new Date();

      //时间差
      //计算当前应付金额
      /**
       * 是否具有 多少分钟之内进出免费是否开启  0:不开启  1：开启
       *
       * 验证是否该停车场允许 某15分钟内 进出免费
       */
      if (pay_park.getOrder_type() != 2) {
        if (pay_park.getIs_free_minute() == 1
            && date.getTime() - pay_park.getCtime().getTime() <= pay_park.getFree_minute()) {
          //开启 且 pay_park.getFree_minute() 分钟内 可以免费进出
          if (pay_park.getFinal_time() == 0 && pay_park.getMoney() == 0) {
            //结算时计费时长（单位分钟）
            pay_park.setFinal_time(
                (int) (date.getTime() - pay_park.getCtime().getTime()) / (60 * 1000));
            if (pay_park.getFinal_time() == 0) {
              pay_park.setFinal_time(1);
            }
            int count = pay_parkDao.updateByKey(pay_park);
            if (count < 1) {
              //更新失败
              //该车辆未出入
              returnData.setReturnData(errorcode_data, "获取停车缴费信息失败", "");
              return;
            }
          }
          returnData.setReturnData(errorcode_success, "获取停车缴费信息成功", pay_park);
          return;
        }
      }

      //计算当前应付金额
      int money = pay_park.getMoney();
      if (pay_park.getOrder_type() != 2) {
        //表示 不是租赁产生的  临停费用
        if (pay_park.getMoney() == 0) {
          money = payParkPB.returnCarMoney(pay_park);
          if (money < 1) {
            returnData.setReturnData(errorcode_data, "获取停车缴费信息失败", "");
            return;
          }
          //总计费金额
          pay_park.setMoney(money);
        }

      }

      //订单信息
      returnobj.put("pay_park", pay_park);
      //获取用户优惠券
      //获取用户抵扣券
      //优惠券列表数据
      String sql = "SELECT * FROM user_park_coupon WHERE ui_id =:ui_id "
          + " AND upc_state=0 AND end_time > :time ORDER BY money  ASC,discount ASC";
      Map<String, Object> paramMap = new HashMap<String, Object>();
      paramMap.put("ui_id", ui_id);
      paramMap.put("time", new Date());
      List<Map<String, Object>> list = getMySelfService().queryForList(sql, paramMap);
      //填充优惠券列表
      if (list == null || list.size() == 0) {
        returnobj.put("coupon", "");
        /**
         * 处理是否有随机立免优惠
         */
        if (pay_park.getAi_id() > 0 && pay_park.getAi_money() > 0) {
          //表示 有随机立免金额--优惠金额
          //returnobj.put("discount_money", pay_park.getAi_money());
          returnobj.put("discount_money", 0);
        } else {
          returnobj.put("discount_money", 0);
        }
      } else {
        returnobj.put("coupon", list);
        returnobj.put("discount_money", 0);
      }
      //验证是否可以进行临停订单支付
      //车辆还没有出库  还没有进行金额计算  该车辆还未被道闸系统扫描到车牌
      if (date.getTime() - pay_park.getScan_time().getTime() >= 30 * 60 * 1000
          || pay_park.getCtime().getTime() - pay_park.getScan_time().getTime() == 0) {
        //车辆还没有出库  还没有进行金额计算  该车辆还未被道闸系统扫描到车牌
        //亲！请耐心等待费用计算....
        returnobj.put("is_show", 0);
      } else {
        returnobj.put("is_show", 1);
      }

      //返回结果
      returnData.setReturnData(errorcode_success, "用户停车缴费读取订单成功", returnobj);
      return;
    } catch (Exception e) {

      log.error("orderBiz.Read_pay_order is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


  /**
   * 用户普通停车位直接正式付款下单(e泊的停车缴费)
   */
  public void pay_order(ReturnDataNew returnData, int dtype, long ui_id,
      String orderid, long upc_id, int pay_source) {

    //pay_source;//支付类型 1:支付l宝 2：微信 3：银联  4：钱包
    try {
      Date date = new Date();
      //从订单表中 获取该用户的停车缴费订单信息
      Pay_park pay_park = null;
      String sql = "SELECT *  FROM  pay_park WHERE my_order=? LIMIT 1";
      pay_park = getMySelfService().queryUniqueT(sql, Pay_park.class, orderid);
      if (pay_park == null) {
        //该车辆未出入
        returnData.setReturnData(errorcode_data, "该车辆未出入", "");
        return;
      }
      if (pay_park.getPp_state() == 1) {
        returnData.setReturnData(errorcode_data, "亲，你已经交过费了", "");
        return;
      }
      //更新该订单对应得 应付金额  结算时计费时长 抵扣优惠券名称  优惠券类型   抵扣优惠金额
      pay_park.setPay_source(pay_source);
      if (pay_source == 5 && pay_park.getAi_id() != 5) {
        //龙支付 且 是第一次进行赋值
        /**
         * 这里处理随机立减免活动事件
         */
        activityPB.record_random(pay_park);
      }
      //每次刷新订单的实时算费 都要刷新时间
      pay_park.setUtime(date);
      //车辆还没有出库  还没有进行金额计算  该车辆还未被道闸系统扫描到车牌
      if (pay_park.getCtime().getTime() - pay_park.getScan_time().getTime() == 0) {
        returnData.setReturnData(errorcode_data, "亲！设备没有检查到你的车辆进行出库.", "");
        return;
      }
      if (date.getTime() - pay_park.getScan_time().getTime() >= 6 * 60 * 1000) {
        //摄像头扫描到车辆出库 超时时间为6分钟  超过了则不允许进行在线支付
        returnData.setReturnData(errorcode_data, "亲！在线支付允许时长6分钟，您已超时，请进行现金支付", "");
        return;
      }

      /**
       * 是否具有 多少分钟之内进出免费是否开启  0:不开启  1：开启
       *
       * 验证是否该停车场允许 某15分钟内 进出免费
       */
      if (pay_park.getIs_free_minute() == 1
          && date.getTime() - pay_park.getCtime().getTime() <= pay_park.getFree_minute()) {
        //开启 且 pay_park.getFree_minute() 分钟内 可以免费进出
        //结算时计费时长（单位分钟）
        if (pay_park.getFinal_time() == 0) {
          pay_park
              .setFinal_time((int) (date.getTime() - pay_park.getCtime().getTime()) / (60 * 1000));
          if (pay_park.getFinal_time() == 0) {
            pay_park.setFinal_time(1);
          }
        }
        int count = pay_parkDao.updateByKey(pay_park);
        if (count < 1) {
          //更新失败
          //该车辆未出入
          returnData.setReturnData(errorcode_data, "用户停车缴费下单信息更新失败", "");
          return;
        }
        returnData.setReturnData(errorcode_success, "用户停车缴费下单信息更新成功", pay_park);
        return;
      }

      int money = 0;
      if (pay_park.getOrder_type() == 2) {
        //表示 租赁产生的  临停费用
        money = pay_park.getMoney();
      } else {
        if (pay_park.getMoney() > 0) {
          money = pay_park.getMoney();
        } else {
          //计算当前应付金额
          money = payParkPB.returnCarMoney(pay_park);
        }
        if (money < 1) {
          returnData.setReturnData(errorcode_data, "用户停车缴费下单信息更新失败", "");
          return;
        }
      }
      /**
       * 这里处理优惠券逻辑
       */
      User_park_coupon user_park_coupon = null;
      if (upc_id > 0) {//有优惠券 且 之前没有进行优惠券计算
        //如果有折扣券或者金额券
        user_park_coupon = user_park_couponDao.selectByKey(upc_id);
        if (user_park_coupon == null) {
          returnData.setReturnData(errorcode_data, "优惠券不存在", "");
          return;
        }
        //by jxh 2017-3-14 龙支付专用券只能龙支付的时候使用 2017-3-14 因为这个问题  APP端当金额被代金券抵扣完全的时候 则pay_source=4 因此这里被屏蔽了代码
        /*if (pay_source != 5 && user_park_coupon.getSend_unit() == 1){
           returnData.setReturnData(errorcode_data, "龙支付专用券,只能用于龙支付", "");
             return;
        }*/

        //这里处理优惠券逻辑
        money = parkCouponPB.doCouponOrder(pay_park, user_park_coupon, money);

      } else {
        /**
         * 使用一张该用户优惠券库中抵扣最多且没有过期的券
         */
        //默认选择抵扣最大的优惠券
        if (pay_source == 5) {
          //龙支付
          user_park_coupon = parkCouponPB.ReturnZhandaoMaxAllCoupon_LZF(pay_park.getUi_id(), money);
        } else {
          user_park_coupon = parkCouponPB.ReturnMaxMoneyCoupon(pay_park.getUi_id(), money);
        }

        money = parkCouponPB.doCouponOrder(pay_park, user_park_coupon, money);
      }
      User_info user_info = null;
      if (ui_id > 0) {
        user_info = user_infoDao.selectByKey(ui_id);
        if (user_info == null) {
          returnData.setReturnData(errorcode_data, "用户不存在", "");
          return;
        }
      }
      if (money == 0 && pay_source != 4 && user_park_coupon != null
          && pay_park.getPark_type() == 0) {//非钱包支付，优惠券抵扣完的情况
        deductionAll(returnData, user_info, pay_park, user_park_coupon);
      }

      int count = pay_parkDao.updateByKey(pay_park);
      if (count < 1) {
        //更新失败
        //该车辆未出入
        returnData.setReturnData(errorcode_data, "该车辆未出入", "");
        return;
      }
      if (user_info != null && pay_source == 4 && pay_park.getPark_type() == 0) {
        //钱包   Park_type   INT    停车场类型0：道闸停车场1：占道车场2：露天立体车库停车场
        try {
          if (money > 0 && isNotSureMoney(user_info, money)) {
            returnData.setReturnData(errorcode_data, "钱包金额不足", "");
            return;
          }
          userTransaction.record_user_vc_act(returnData, user_info,
              money, pay_park, 0, 0, user_park_coupon);
        } catch (Exception e) {
          log.error("事物处理 record_user_vc_act  付款失败", e);
          //返回结果
          returnData.setReturnData(errorcode_data, "用户支付失败", "");
          return;
        }
      }

      //返回结果
      returnData.setReturnData(errorcode_success, "用户停车缴费下单信息更新成功", pay_park);
      return;
    } catch (Exception e) {

      log.error("orderBiz.pay_order is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  @Autowired
  private UserPB userPB;

  /**
   * 优惠券抵扣完的情况
   */
  public void deductionAll(ReturnDataNew returnData, User_info user_info, Pay_park pay_park,
      User_park_coupon user_park_coupon)
      throws QzException {
    try {
      Date date = new Date();
      //扣款成功
      pay_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
      pay_park.setUtime(date);//更新时间
      pay_park.setLeave_time(date);//离开时间
      /**
       * 更新商户账户金额
       */
      userPB.upManchentMoney(pay_park.getPu_id(), pay_park.getPu_nd(), pay_park.getMoney(),
          1);

      if (user_park_coupon != null) {
        //如果使用了优惠券 那么需要更改优惠券的使用状态
        User_park_coupon user_park_coupon1 = parkCouponPB
            .upUserParkCouponState(user_park_coupon.getUpc_id(),pay_park.getUi_id());
        if (user_park_coupon1 == null) {
          //更新失败
          returnData.setReturnData(errorcode_data, "优惠券更新使用状态失败", "");
          throw new QzException("record_user_vc_act 优惠券更新使用状态失败");
        } else {
          asyncJpushTask.pushUseCouponMsg(pay_park.getUi_nd(), user_park_coupon);
        }
      }
      /**
       * 这里处理推送---车辆临停扣款
       */
      String title = "系统消息";
      String message = "亲，你的订单【" + pay_park.getMy_order() + "】，临停扣款成功。";
      payParkPB.pushOrderSate(user_info.getUuid(), message, title, pay_park);

    } catch (Exception e) {
      log.error("VcBiz record_user_vc_act is error", e);
      throw new QzException("事物异常 record_user_vc_act", e);
    }
  }

  /**
   * 获取我的某条订单 （1：临时停车订单  2： 租赁订单）
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void my_one_order(ReturnDataNew returnData, int dtype, long ui_id,
      Integer type, String orderid) {

    try {
      if (type == 0) {
        //0:普通停车订单
        String sql = "SELECT *  FROM  pay_park  WHERE ui_id=? AND my_order=? LIMIT 1";
        Pay_park pay_park = getMySelfService().queryUniqueT(sql, Pay_park.class, ui_id, orderid);
        if (pay_park == null) {
          returnData.setReturnData(errorcode_data, "订单不存在", "");
          return;
        }
        //返回数据
        returnData.setReturnData(errorcode_success, "普通停车订单", pay_park);
        return;
      } else {
        //1：租赁停车订单
        String sql = "SELECT *  FROM  pay_month_park  WHERE ui_id=? AND  my_order=? LIMIT 1";
        Pay_month_park pay_month_park = getMySelfService()
            .queryUniqueT(sql, Pay_month_park.class, ui_id, orderid);
        if (pay_month_park == null) {
          returnData.setReturnData(errorcode_data, "订单不存在", "");
          return;
        }
        //返回数据
        returnData.setReturnData(errorcode_success, "租赁停车订单", pay_month_park);
        return;
      }
    } catch (Exception e) {

      log.error("orderBiz.my_one_order is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 获取我的订单 （1：临时停车订单  2： 租赁订单  3：全部订单）
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void my_order(ReturnDataNew returnData, int dtype, long ui_id, int type,
      String car_code, String area_code, int page, int size) {
//     获取订单类型  0:普通停车订单  1：租赁停车订单
    try {
      if (page < 1) {
        page = 1;
      }
      int start = (page - 1) * size;
      if (type == 0) {
        //0:普通停车订单
        String sql =
            "select *  from  pay_park  where ui_id=? and is_del=0 and cancel_state !=1 order by ctime desc limit "
                + start + "," + size;
        List<Pay_park> list = getMySelfService().queryListT(sql, Pay_park.class, ui_id);
        //返回数据
        returnData.setReturnData(errorcode_success, "普通停车订单", list);
        return;
      } else {
        //1：租赁停车订单
        String sql =
            "select *  from  pay_month_park  where ui_id=? and is_del=0 and cancel_state !=1 order by ctime desc limit "
                + start + "," + size;
        List<Pay_month_park> list = getMySelfService().queryListT(sql, Pay_month_park.class, ui_id);
        //返回数据
        returnData.setReturnData(errorcode_success, "租赁停车订单", list);
        return;
      }
    } catch (Exception e) {

      log.error("orderBiz.my_order is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


  /**
   * 获取我的账户出入记录
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void vc_record(ReturnDataNew returnData, int dtype, long ui_id,
      int type, int page, int size) {

    try {
      if (page < 1) {
        page = 1;
      }
      int start = (page - 1) * size;
      if (type == 0) {
        //0:全部的出入记录
        String sql =
            "select *  from  user_vc_act  where ui_id=? order by ctime desc limit " + start + ","
                + size;
        List<User_vc_act> list = getMySelfService().queryListT(sql, User_vc_act.class, ui_id);
        //返回数据
        returnData.setReturnData(errorcode_success, "全部的出入记录", list);
        return;
      } else if (type == 1) {
        String sql =
            "select *  from  user_vc_act  where ui_id=? and is_add=? order by ctime desc limit "
                + start + "," + size;
        List<User_vc_act> list = getMySelfService().queryListT(sql, User_vc_act.class, ui_id, 0);
        //返回数据
        returnData.setReturnData(errorcode_success, "减少的虚拟币记录", list);
        return;
      } else {
        String sql =
            "select *  from  user_vc_act  where ui_id=? and is_add=? order by ctime desc limit "
                + start + "," + size;
        List<User_vc_act> list = getMySelfService().queryListT(sql, User_vc_act.class, ui_id, 1);
        //返回数据
        returnData.setReturnData(errorcode_success, "增加的虚拟币记录", list);
        return;
      }
    } catch (Exception e) {

      log.error("orderBiz.vc_record is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /****************************下面是分离出来的方法***************************************/


  /**
   * 获取普通订单
   */
  public Pay_park QueryByOrderId(String orderid, long ui_id) {
    try {
      String sql = "SELECT *  FROM pay_park WHERE ui_id=? AND  my_order=? LIMIT 1";
      return getMySelfService().queryUniqueT(sql, Pay_park.class, ui_id, orderid);
    } catch (Exception e) {

      log.error("获取普通订单 QueryByOrderId(String orderid,long ui_id) is error" + e.getMessage(), e);
    }
    return null;
  }


  /**
   * 获取用户未支付且没有关闭且没有逻辑删除的订单
   *
   * @param ui_id 用户ID
   * @param pi_id 车库表主键ID
   * @param car_code 车牌号
   */
  public Pay_park QueryPayPark(long ui_id, long pi_id, String area_code, String car_code) {
    //入库之前先检查该用户对该车辆进行该停车场预约下单没有   如果已经进行了预约下单则直接更新对应得数据
    try {
      Pay_park pay_park = null;
      String sql = "SELECT *  FROM pay_park WHERE pi_id=? AND area_code=? AND car_code=? AND ui_id=? AND pp_state=0   AND is_del=0 AND cancel_state=0  ORDER BY  ctime DESC LIMIT 1";
      pay_park = getMySelfService()
          .queryUniqueT(sql, Pay_park.class, pi_id, area_code, car_code, ui_id);
      return pay_park;
    } catch (Exception e) {

      log.error("	QueryPayPark(long ui_id,long pi_id,String car_code,int order_type) is error" + e
          .getMessage(), e);
    }
    return null;
  }


  /**
   * 用户进行租赁下单当---传递了订单号的情况
   */
  public Pay_month_park pay_rent_order_haveOrderid(ReturnDataNew returnData, int dtype,
      long ui_id, long pi_id, int pay_type, int month_num,
      String month_info, String car_code, String pp_versioncode, String area_code, long upc_id,
      int pay_source, String permit_time, int is_24hours, String orderid
      , User_info user_info, Park_info park_info
      , User_park_coupon user_park_coupon) {
    // pay_source;//支付类型 1:支付l宝 2：微信 3：银联  4：钱包
    try {
      Date date = new Date();
      if (RequestUtil.checkObjectBlank(orderid)) {
        returnData.setReturnData(errorcode_data, "该租赁订单不存在", "");
        return null;
      }
      //获取该未支付的 租赁订单信息
      Pay_month_park pay_month_park = payMonthParkPB.selectOnePayMonthPark(orderid);
      if (pay_month_park == null) {
        returnData.setReturnData(errorcode_data, "该租赁订单不存在", "");
        return null;
      }
      if (pay_month_park.getPp_state() == 1) {
        returnData.setReturnData(errorcode_data, "亲，你已经交过费了", "");
        return null;
      }
      //占道停车不允许租赁
      if (park_info.getPark_type() == 1) {
        //占道停车不允许租赁
        returnData.setReturnData(errorcode_data, "占道停车场，暂时未开通租赁服务", "");
        return null;
      }

      /**
       * 创建租赁订单
       */
      MakeRentOrder(returnData, user_info, pay_month_park, park_info, month_num, upc_id
          , date, car_code, pay_source, pay_type, dtype, pp_versioncode
          , permit_time, is_24hours, ui_id, user_park_coupon);

      //获取该未支付的 租赁订单信息
      int count = pay_month_parkDao.updateByKey(pay_month_park);
      if (count < 1) {
        //更新失败
        returnData.setReturnData(errorcode_data, "支付失败", "");
        return null;
      }

      return pay_month_park;

    } catch (Exception e) {
      log.error("ParkinfoBiz.pay_rent_order_haveOrderid is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return null;
    }
  }

  /**
   * 用户进行租赁下单当---没有传递了订单号的情况
   */
  public Pay_month_park pay_rent_order_NoneOrderid(ReturnDataNew returnData, int dtype,
      long ui_id, long pi_id, int pay_type, int month_num,
      String month_info, String car_code, String pp_versioncode, String area_code, long upc_id,
      int pay_source, String permit_time, int is_24hours, String orderid
      , User_info user_info, Park_info park_info
      , User_park_coupon user_park_coupon) {
    // pay_source;//支付类型 1:支付l宝 2：微信 3：银联  4：钱包
    try {
      Date date = new Date();
      //获取该未支付的 租赁订单信息
      Pay_month_park pay_month_park = new Pay_month_park();
      int product_type = 0;
      switch (park_info.park_type) {
        case 0:
          product_type = 0;
        case 1:
          product_type = 1;
      }
      pay_month_park.setMy_order(returnNewOrderId(area_code, product_type, pi_id, 4));//我们自己生成的订单号
      //***暂时不处理***  检查该用户是否存在租赁确没有付款的  --- 租赁订单
      if (payMonthParkPB.CheckPayOrOutTime(returnData, pi_id, car_code, ui_id, date)) {
        return null;
      }
      //占道停车不允许租赁
      if (park_info.getPark_type() == 1) {
        //占道停车不允许租赁
        returnData.setReturnData(errorcode_data, "占道停车场，暂时未开通租赁服务", "");
        return null;
      }

      /**
       * 创建租赁订单
       */
      MakeRentOrder(returnData, user_info, pay_month_park, park_info, month_num
          , upc_id, date, car_code, pay_source, pay_type, dtype
          , pp_versioncode, permit_time, is_24hours, ui_id, user_park_coupon);

      int id = pay_month_parkDao.insert(pay_month_park);
      if (id < 1) {
        //下单失败
        log.error("ParkinfoBiz.cameraCarOrder is error 下单插入的时候失败");
        returnData.setReturnData(errorcode_data, "预约失败", "");
        return null;
      }
      //设置主键
      pay_month_park.setId(id);

      return pay_month_park;

    } catch (Exception e) {
      log.error("ParkinfoBiz.pay_rent_order_haveOrderid is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return null;
    }
  }

  /**
   * 租赁订单创建
   */
  public void MakeRentOrder(ReturnDataNew returnData, User_info user_info,
      Pay_month_park pay_month_park, Park_info park_info, int month_num, long upc_id, Date date
      , String car_code, int pay_source, int pay_type, int dtype, String pp_versioncode,
      String permit_time
      , int is_24hours, long ui_id, User_park_coupon user_park_coupon) throws Exception {
    int money = park_info.getMonth_price() * month_num;
    //用户下单租赁车位前的检查
    //用户ID 通过车牌号获取用户ID

    pay_month_park.setAllege_state(0);//申述状态 0:未申述 1：已申述
    pay_month_park.setMoney(money);//支付金额（单位 分）
    if (upc_id > 0) {
      payMonthParkPB.RentOrderCoupon(pay_month_park, user_park_coupon, upc_id, money, date);
    }
    pay_month_park.setMonth_num(month_num);//包月租凭月数
    pay_month_park.setMonth_info(park_info.getRent_info());//包月提示信息
    //包月起始日 包月截止日
    /*pay_month_park.setStart_time(date);
    Calendar cl = Calendar.getInstance(Locale.CHINESE);
    cl.setTime(date);
//    cl.add(Calendar.DATE, month_num*30);
    cl.add(Calendar.MONTH, month_num);
    pay_month_park.setEnd_time(cl.getTime());*/
    payMonthParkPB.PutRentOutTime(pay_month_park, month_num, date, permit_time);

    pay_month_park.setCar_code(car_code);//车牌号
    pay_month_park.setCtime(date);//创建时间
    pay_month_park.setOrder_type(2);//下单类型 0: 普通下单  1：预约下单 2：租赁包月订单
//    pay_park.setOther_order(value);//第三方的 支付单号
    pay_month_park.setPay_source(pay_source);//支付类型 1:支付宝 2：微信 3：银联 4：钱包
    pay_month_park.setPay_type(pay_type);//支付类型 0:手动输入密码支付 1：快捷支付（服务器可以请求第三方直接扣款）
    pay_month_park.setPhone_type(dtype);//手机类型 0:android 1：IOS
    pay_month_park.setPi_id(park_info.getPi_id());//支付停车场主键ID
    pay_month_park.setPp_state(0);//支付状态 0:未支付 1：已经支付
    pay_month_park.setPp_versioncode(pp_versioncode);//当前APPSDK版本号 （内部升级版本代号）
    pay_month_park.setUtime(date);//更新时间
    pay_month_park.setNote("用户租赁下单");
    //优惠券
    pay_month_park.setUpc_id(upc_id);
    pay_month_park.setAddress_name(park_info.getAddress_name());
    pay_month_park.setPi_name(park_info.getPi_name());//停车场名称

    //省市县区域代码
    pay_month_park.setArea_code(park_info.getArea_code());
    //允许的时间段
//    pay_month_park.setPermit_time(permit_time);
    //是否是24小时包月
    pay_month_park.setIs_24hours(is_24hours);
    pay_month_park.setPark_type(park_info.getPark_type());

    //设置商户唯一标识
    pay_month_park.setPu_id(park_info.getPu_id());
    pay_month_park.setPu_nd(park_info.getPu_nd());
    //设置经纬度
    pay_month_park.setLng(park_info.getLng());
    pay_month_park.setLat(park_info.getLat());

    if (user_info != null && ui_id > 0) {
      pay_month_park.setUi_id(ui_id);
      pay_month_park.setUi_nd(user_info.getUuid());
      pay_month_park.setUi_tel(user_info.getUi_tel());
    }
  }
}
