/**
 * @Title: TT.java
 * @Package com.intimes.biz
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 敬小虎
 * @date 2015年3月20日 下午1:32:43
 * @version V1.0
 */
package com.park.transaction;

import com.alibaba.fastjson.JSON;
import com.park.bean.Park_info;
import com.park.bean.Pay_month_park;
import com.park.bean.Pay_park;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_info;
import com.park.bean.User_pay;
import com.park.constants.Constants;
import com.park.exception.QzException;
import com.park.jpush.PDA.bean.PDAPushMessage;
import com.park.jpush.bean.JPushMessageBean;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.RentDeferCoreBiz;
import com.park.mvc.service.common.ActivityPB;
import com.park.mvc.service.common.ParkCouponPB;
import com.park.mvc.service.common.PayMonthParkPB;
import com.park.mvc.service.common.PayParkPB;
import com.park.mvc.service.common.UserPB;
import com.park.type.PayTypeEnum;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author 敬小虎
 * @ClassName: TT
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2015年3月20日 下午1:32:43
 */
@Transactional(rollbackFor = QzException.class)
@Service
public class PayTransaction extends BaseBiz {

  @Autowired
  private UserPB userPB;
  @Autowired
  protected PayParkPB payParkPB;
  @Autowired
  protected PayMonthParkPB payMonthParkPB;
  @Autowired
  protected ParkCouponPB parkCouponPB;
  @Autowired
  protected ActivityPB activityPB;

  @Autowired
  private RentDeferCoreBiz rentDeferCoreBiz;

  /**
   * 用户支付通知：修改用户钱包金额、更改订单状态
   */
  public User_pay NotifyUserPay(User_pay user_pay) throws QzException {
    // TODO Auto-generated method stub
    try {
      int orderType = 0;
      if (user_pay == null) {
        return user_pay;
      }

      //更新订单第三方支付相关ID
      up_orderid(user_pay);

      int order_type = 0;//int(11)    下单类型0:普通下单1：预约下单2：租赁包月订单
      int discount_money = 0;//int(11)    抵扣优惠金额（单位分）
      int discount_type = 0;//int(11)    优惠券类型0:金额券1：折扣券
      long upc_id = 0;//bigint(20)    用户优惠券表主键ID
      //判断是 用户充值回调 还是  用户支付回调
      //行为类型 1：充值  2：普通订单支付  3：租赁订单支付
      int type = user_pay.getAct_type();
      //更新通知状态
      user_pay.setState(1);//交易状态(0:未支付1：已支付2：支付失败)
      Date date = new Date();
      user_pay.setUtime(date);
      user_pay.setEtime(date);
      int count = user_payDao.updateByKey(user_pay);
      if (count < 1) {
        //更新失败
        log.error("更新通知状态失败 type=" + type + "  orderid=" + user_pay.getOrder_id());
        throw new QzException("更新通知状态失败 type=" + type + "  orderid=" + user_pay.getOrder_id());
      }

      if (type == 1) {
        //充值
        //钱包增加金额
        User_info user_info = userPB
            .updateUserMoney(user_pay.getUi_id(), user_pay.getUi_nd(), 0, user_pay.getMoney());
        if (user_info == null) {
          //更新充值金额失败
          log.error("更新用户充值金额失败 orderid=" + user_pay.getOrder_id());
          throw new QzException("更新用户充值金额失败 orderid=" + user_pay.getOrder_id());
        }
        if (user_pay.getType() == 5) {
          //龙支付
          //首次充值活动记录事件
          asyncOrderTask.recordEvent_coupon_firstRecharge(
              user_info.getUi_id(), user_info.getUuid(), user_info.getUi_tel(),
              (int) user_pay.getMoney(), getMySelfService(), activityPB);
        }


      } else if (type == 2) {
        //2：普通订单支付
        Pay_park pay_park = payParkPB.upPayParkNotify(user_pay);
        if (pay_park == null) {
          //更新失败
          log.error(
              "更新--普通订单支付--用户支付状态失败user_pay.orderid=" + user_pay.getOrder_id() + "car_order_id="
                  + user_pay.getCar_order_id());
          throw new QzException(
              "更新--普通订单支付--用户支付状态失败user_pay.orderid=" + user_pay.getOrder_id() + "car_order_id="
                  + user_pay.getCar_order_id());
        } else {
          //设置优惠券数据
          upc_id = pay_park.getUpc_id();//bigint(20)
          discount_type = pay_park.getDiscount_type();//int(11)    优惠券类型0:金额券1：折扣券
          discount_money = (int) pay_park.getDiscount_money();//int(11)    抵扣优惠金额（单位分）
          //设置订单类型
          order_type = pay_park.getOrder_type();
        }
        /**
         * 更新商户账户金额
         */
        if (pay_park.getPu_id() > 0) {
          userPB.upManchentMoney(pay_park.getPu_id(), pay_park.getPu_nd(), pay_park.getMoney(), 1);
        }

        /**
         * 记录随机立免金额的事件
         * 异步操作
         * @param pay_park
         */
        asyncOrderTask.record_random_Event(pay_park, getMySelfService(), activityPB);

        if (user_pay.getSystem_type() == 4) {
          //如果是PDA 则直接返回 不做下面的逻辑处理
          //操作系统类型（IOSAndroidweb）1:android2:IOS3:web 4:PDA
          //这里处理PDA支付扣款推送
          Park_info park_info = park_infoDao
              .selectByKey(pay_park.getPi_id(), ReturnParkTableName(pay_park.getArea_code()));
          if (park_info != null) {
            try {
              //预约成功 进行JPUSH推送
              PDAPushMessage pDAPushMessage = new PDAPushMessage();
              pDAPushMessage.setCar_code(pay_park.getArea_code());
              pDAPushMessage.setOrderid(pay_park.getMy_order());
              pDAPushMessage.setTime(pay_park.getCtime());
              pDAPushMessage.setUi_id(pay_park.getUi_id());
              pDAPushMessage.setUi_tel("");
              pDAPushMessage.setUiid("");
              pDAPushMessage.setId(pay_park.getId());
              pDAPushMessage.setType(3);

              JPushMessageBean jPushMessageBean = new JPushMessageBean();
              jPushMessageBean.setType(4);
              jPushMessageBean.setMessage("PDA扫码支付");
              jPushMessageBean.setMessageJson((JSON) JSON.toJSON(pDAPushMessage));
              asyncJpushTask.doPdaJpushPDA(jPushMessageBean, park_info.getMac());
            } catch (Exception e) {
              // TODO Auto-generated catch block
              log.error("PDA支付推送失败", e);
            }

          }
        } else {
          //这里处理订单状态变更推送----车辆临停扣款
          /**
           * 这里处理推送---车辆临停扣款
           */
          String title = "系统消息";
          String message = "亲，你的订单【" + pay_park.getMy_order() + "】，临停扣款成功。";
          payParkPB.pushOrderSate(user_pay.getUi_nd(), message, title, pay_park);
        }

      } else if (type == 3) {
        //租赁订单支付
        Pay_month_park pay_month_park = payMonthParkPB.upPayMonthParkNotify(user_pay);
        if (pay_month_park == null) {
          //更新失败
          log.error(
              "更新--租赁订单支付--用户支付状态失败user_pay.orderid=" + user_pay.getOrder_id() + "car_order_id="
                  + user_pay.getCar_order_id());
          throw new QzException(
              "更新--租赁订单支付--用户支付状态失败user_pay.orderid=" + user_pay.getOrder_id() + "car_order_id="
                  + user_pay.getCar_order_id());
        } else {
          //设置优惠券数据
          upc_id = pay_month_park.getUpc_id();//bigint(20)
          discount_type = pay_month_park.getDiscount_type();//int(11)    优惠券类型0:金额券1：折扣券
          discount_money = (int) pay_month_park.getDiscount_money();//int(11)    抵扣优惠金额（单位分）
          //设置订单类型
          order_type = pay_month_park.getOrder_type();
        }
        /**
         * 更新商户账户金额
         */
        userPB.upManchentMoney(pay_month_park.getPu_id(), pay_month_park.getPu_nd(),
            pay_month_park.getMoney(), 1);
        /**
         * 这里处理推送
         */
        //这里处理订单状态变更推送----车辆租赁扣款
        String title = "系统消息";
        String message = "亲，你的车牌号为【" + pay_month_park.getCar_code() + "】车，已租赁扣款成功。";
        payParkPB.pushRentOrderSate(user_pay.getUi_nd(), message, title, pay_month_park);
      } else if (type == 4) {
        //租赁订单续约支付结果修改  --同步处理
        rentDeferCoreBiz.updateRentDeferInfo(user_pay.getOrder_id());
        orderType = 3;
        order_type = 3;
        //新增续约订单事件记录
        //rentDeferCoreBiz.addEventSchedule(user_pay);
      }

      /**
       * 记录充值或者支付
       */
      ReturnDataNew returnData = new ReturnDataNew();
      if (user_pay.getAct_type() == 1) {
        //充值
        String act_name = PayTypeEnum.returnObj(user_pay.getType()) + "充值";
        userPB
            .recordVC(1, (int) user_pay.getMoney(), user_pay.getOrder_id(), orderType, user_pay.getUi_id(),
                returnData, act_name, user_pay.getUi_nd(), user_pay.getTel(), user_pay.getType(), 0,
                0, 0, date);
        try {
          //2.	充值到账：您充值的XX元电子劵已经到账，可以在吾泊电子钱包明细处查询。
          /**
           * 这里进行推送
           */
          JPushMessageBean jPushMessageBean = new JPushMessageBean();
          jPushMessageBean.setType(0);
          jPushMessageBean
              .setMessage("您充值" + (int) user_pay.getMoney() / 100 + "元人民币已经到账，可以在电子钱包明细处查询。");
          jPushMessageBean.setImgurl(Constants.JPUSH_LOGO);
          jPushMessageBean.setTitle("充值到账");
          jPushMessageBean.setDate(date);
          User_info userinfo = user_infoDao.selectByKey(user_pay.getUi_id());
          if (userinfo != null) {
            asyncJpushTask.doAppJpush(jPushMessageBean, userinfo.getUuid());
          }

        } catch (Exception e) {
          // TODO Auto-generated catch block
          log.error("充值到账 推送出现错误", e);
        }
      } else {
        //属于支付
        String act_name = PayTypeEnum.returnObj(user_pay.getType()) + "支付";
        userPB.recordVC(0, (int) user_pay.getMoney(), user_pay.getCar_order_id(), order_type,
            user_pay.getUi_id(), returnData, act_name, user_pay.getUi_nd(), user_pay.getTel(),
            user_pay.getType()
            , upc_id, discount_type, discount_money, date);
      }
      return user_pay;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      throw new QzException("更新用户充值状态失败 orderid=" + user_pay.getOrder_id());
    }
  }

  /**
   * 更新订单第三方支付相关ID
   */
  private void up_orderid(User_pay user_pay) {
    try {
      //行为类型 1：充值  2：普通订单支付  3：租赁订单支付
      int type = user_pay.getAct_type();
      if (type == 2) {
        Pay_park pay_park = payParkPB.selectOnePayPark(user_pay.getCar_order_id());
        pay_park.setUp_orderid(user_pay.getOrder_id());
        pay_parkDao.updateByKey(pay_park);
        String escape_orderids = user_pay.getEscape_orderids();
        if (StringUtils.hasText(escape_orderids)) {
          String[] split = escape_orderids.split(",");
          for (String s : split) {
            Pay_park p = payParkPB.selectOnePayPark(s);
            p.setUp_orderid(user_pay.getOrder_id());
            pay_parkDao.updateByKey(p);
          }
        }
      } else if (type == 3) {
        Pay_month_park p = payMonthParkPB.selectOnePayMonthPark(user_pay.getCar_order_id());
        p.setUp_orderid(user_pay.getOrder_id());
        pay_month_parkDao.updateByKey(p);
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

	

/*	*//**
   * 更新普通订单支付
   * @throws QzException
   *//*
  public void updateOrderidPay(User_pay user_pay,String car_order_id) throws QzException{
		//2：普通订单支付
		Pay_park pay_park = payParkPB.upPayParkNotify(car_order_id, user_pay.getTransaction_id(), user_pay.getMoney());
		if(pay_park == null){
			//更新失败
			log.error("更新--普通订单支付--用户支付状态失败user_pay.orderid="+user_pay.getOrder_id() +"car_order_id="+user_pay.getCar_order_id());
			throw new QzException("更新--普通订单支付--用户支付状态失败user_pay.orderid="+user_pay.getOrder_id() +"car_order_id="+user_pay.getCar_order_id());
		}
		*//**
   * 更新商户账户金额
   *//*
    if(pay_park.getPu_id() > 0){
			userPB.upManchentMoney(pay_park.getPu_id(),pay_park.getPu_nd(), (int)user_pay.getMoney(), 1);
		}
		if(user_pay.getSystem_type() == 4){
			//如果是PDA 则直接返回 不做下面的逻辑处理
			//操作系统类型（IOSAndroidweb）1:android2:IOS3:web 4:PDA
			//这里处理PDA支付扣款推送
			Park_info park_info = park_infoDao.selectByKey(pay_park.getPi_id(),ReturnParkTableName(pay_park.getArea_code()));
        	if(park_info != null ){
    			try {
    				//预约成功 进行JPUSH推送
    				PDAPushMessage pDAPushMessage = new PDAPushMessage();
    				pDAPushMessage.setCar_code(pay_park.getArea_code());
    				pDAPushMessage.setOrderid(pay_park.getMy_order());
    				pDAPushMessage.setTime(pay_park.getCtime());
    				pDAPushMessage.setUi_id(pay_park.getUi_id());
    				pDAPushMessage.setUi_tel("");
    				pDAPushMessage.setUiid("");
    				pDAPushMessage.setId(pay_park.getId());
    				pDAPushMessage.setType(3);
    				
    				JPushMessageBean jPushMessageBean = new JPushMessageBean();
    				jPushMessageBean.setType(4);
    				jPushMessageBean.setMessage("PDA扫码支付");
    				jPushMessageBean.setMessageJson((JSON)JSON.toJSON(pDAPushMessage));
    				asyncJpushTask.doPdaJpushPDA(jPushMessageBean,park_info.getMac());
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				log.error("PDA支付推送失败", e); 
    			}
        		
        	}
		}else{
			//这里处理订单状态变更推送----车辆临停扣款
			*//**
   * 这里处理推送---车辆临停扣款
   *//*
      String title = "系统消息";
			String message = "亲，你的订单【"+pay_park.getMy_order()+"】，临停扣款成功。";
			payParkPB.pushOrderSate(user_pay.getUi_nd(), message, title, pay_park);
		}
	}*/
  /**
   * 租赁订单支付 更新
   * @throws QzException
   *//*
  public void  updateRentOrderidPay(User_pay user_pay) throws QzException{
		//租赁订单支付
		Pay_month_park pay_month_park = payMonthParkPB.upPayMonthParkNotify(user_pay.getCar_order_id(), user_pay.getTransaction_id(), user_pay.getMoney());
		if(pay_month_park == null){
			//更新失败
			log.error("更新--租赁订单支付--用户支付状态失败user_pay.orderid="+user_pay.getOrder_id() +"car_order_id="+user_pay.getCar_order_id());
			throw new QzException("更新--租赁订单支付--用户支付状态失败user_pay.orderid="+user_pay.getOrder_id() +"car_order_id="+user_pay.getCar_order_id());
		}
		*//**
   * 更新商户账户金额
   *//*
    userPB.upManchentMoney(pay_month_park.getPu_id(),pay_month_park.getPu_nd(), (int)user_pay.getMoney(), 1);
		*//**
   * 这里处理推送
   *//*
    //这里处理订单状态变更推送----车辆租赁扣款
		String title = "系统消息";
		String message = "亲，你的车牌号为【"+pay_month_park.getCar_code()+"】车，已租赁扣款成功。";
		payParkPB.pushRentOrderSate(user_pay.getUi_nd(), message, title, pay_month_park);
	}*/
}
