/**
 * @Title: TT.java
 * @Package com.intimes.biz
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 敬小虎
 * @date 2015年3月20日 下午1:32:43
 * @version V1.0
 */
package com.park.app.transaction;

import com.park.bean.*;
import com.park.dao.Rent_deferDao;
import com.park.exception.QzException;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.RentDeferCoreBiz;
import com.park.mvc.service.common.*;
import com.park.type.ActivityEnum;
import com.park.util.RequestUtil;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;

/**
 * @author 敬小虎
 * @ClassName: TT
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2015年3月20日 下午1:32:43
 */
@Transactional(rollbackFor = QzException.class)
@Service
public class AppPayTransaction extends BaseBiz {

  @Autowired
  private UserPB userPB;
  @Autowired
  protected ParkCouponPB parkCouponPB;
  @Autowired
  protected ActivityPB activityPB;
  @Autowired
  private Rent_deferDao rentDeferDao;
  @Autowired
  private RentDeferCoreBiz rentDeferCoreBiz;

  /**
   * 用户充值订单和停车支付订单流水表
   *
   * @@有事务处理
   */
  public User_pay MakeUserReCharge(long ui_id, String ui_nd, int pay_type, long money, int version,
      int system_type, String subject,
      String ip, String callbackurl, int type, String orderid, String tel,
      int escape_money, String escape_orderids) throws QzException {
    try {
      money += escape_money;

      User_pay user_pay = new User_pay();
      //type  是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付   4：租赁订单续约支付
      //生成订单
      Date date = new Date();
      user_pay.setAct_type(type);//INT    行为类型（1：用户充值）
      user_pay.setCtime(date);
      user_pay.setEtime(date);
      user_pay.setUtime(date);
      user_pay.setMoney(money);
      user_pay.setOrder_id(returnUUID());
      user_pay.setReturn_url(callbackurl);
      user_pay.setState(0);//交易状态(0:未支付1：已支付2：支付失败)
      user_pay.setSystem_type(system_type);
      user_pay.setTransaction_id("");
      user_pay.setType(pay_type);//支付类型1:支付宝 2：微信 3：银联
      user_pay.setUi_id(ui_id);
      user_pay.setUi_nd(ui_nd);
      user_pay.setVersion_code(version);
      user_pay.setCar_order_id(orderid);
      if (RequestUtil.checkObjectBlank(subject)) {
        subject = "吾泊充值";
      }
      user_pay.setSubject(subject);
      user_pay.setIp(ip);
      user_pay.setTel(tel);
      user_pay.setEscape_orderids(escape_orderids);

      int id = user_payDao.insert(user_pay);
      if (id < 1) {
        //插入失败
        throw new QzException("下订单失败");
      }
      user_pay.setId(id);
      return user_pay;
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      throw new QzException("下订单失败", e);
    }
  }

  /**
   * 用户充值订单和停车支付订单流水表
   *
   * @@有事务处理
   */
  public User_pay MakeUserReCharge_LZF(long ui_id, String ui_nd, int pay_type, long money,
      int version,
      int system_type, String subject,
      String ip, String callbackurl, int type, String orderid, String tel, Pay_park pay_park,
      int escape_money, String escape_orderids)
      throws QzException {
    try {
      boolean flag = true;//优惠券抵扣 和  随机立减免活动  互斥量 true：表示可以进行 随机立减免活动
      //自动抵扣优惠券
      if (ui_id > 0) {
        //默认选择抵扣最大的优惠券
        User_park_coupon user_park_coupon = parkCouponPB
            .ReturnZhandaoMaxAllCoupon_LZF(ui_id, money);
        if (user_park_coupon != null) {
          try {
            money = parkCouponPB
                .doCouponOrder(pay_park, user_park_coupon, Long.valueOf(money).intValue());
            int count = pay_parkDao.updateByKey(pay_park);
            if (count == 1) {
              //成功 修改实际支付金额
              flag = false;
            } else {
              throw new QzException("代金券抵扣设置错误");
            }
          } catch (Exception e) {
            throw new QzException("代金券抵扣设置错误");
          }
        } else {
          pay_park.setUpc_id(0);
          pay_park.setDiscount_money(0);
          pay_park.setDiscount_type(0);
        }
      }

      //这里处理 龙支付 随机立减免活动
      if (flag) {
        try {
          /**
           * by jxh 2017-3-14 这里设置PDA龙支付的随机金额
           */
          long ai_id = ActivityEnum.activity_RandomMoney_lzf.getValue();

          //之前没有设置过 龙支付的随机金额
          Activity_info activity_info = activityPB.selectActivityByid(ai_id);
          //验证龙支付随机立减活动是否开启
          int ai_money = 0;
          if (activityPB.isActivityEffect(activity_info)) {

            if (pay_park.getAi_id() != ai_id) {
              //之前没有设置龙支付 随机立即减免金额
              //有效
              //龙支付 0.4-0.8
              ai_money = RandomUtils.nextInt(4, 8) * 10;

              //把龙支付的随机数 设置进去
              pay_park.setAi_id(ai_id);
              pay_park.setAi_money(ai_money);
              //更新该订单信息
              int count = pay_parkDao.updateByKey(pay_park);
              if (count != 1) {
                throw new QzException("修改实际支付金额设置错误");
              }
            }

            //随机立减免优惠金额
            ai_money = pay_park.getAi_money();
          }
          money = (money >= ai_money) ? (money - ai_money) : 0;
        } catch (Exception e) {
          // TODO Auto-generated catch block
          log.error("这里设置PDA龙支付的随机金额 错误");
          throw new QzException("这里设置PDA龙支付的随机金额 错误");
        }
      }

      money += escape_money;

      User_pay user_pay = new User_pay();

      //type  是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付
      //生成订单
      Date date = new Date();
      user_pay.setAct_type(type);//INT    行为类型（1：用户充值）
      user_pay.setCtime(date);
      user_pay.setEtime(date);
      user_pay.setUtime(date);
      user_pay.setMoney(money);
      user_pay.setOrder_id(returnUUID());
      user_pay.setReturn_url(callbackurl);
      user_pay.setState(0);//交易状态(0:未支付1：已支付2：支付失败)
      user_pay.setSystem_type(system_type);
      user_pay.setTransaction_id("");
      user_pay.setType(pay_type);//支付类型1:支付宝 2：微信 3：银联
      user_pay.setUi_id(ui_id);
      user_pay.setUi_nd(ui_nd);
      user_pay.setVersion_code(version);
      user_pay.setCar_order_id(orderid);
      if (RequestUtil.checkObjectBlank(subject)) {
        subject = "吾泊充值";
      }
      user_pay.setSubject(subject);
      user_pay.setIp(ip);
      user_pay.setTel(tel);
      user_pay.setEscape_orderids(escape_orderids);

      int id = user_payDao.insert(user_pay);
      if (id < 1) {
        //插入失败
        throw new QzException("下订单失败");
      }
      user_pay.setId(id);
      return user_pay;
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      throw new QzException("下订单失败", e);
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

  /**
   * APP用户租赁续租余额支付
   * @throws QzException
   */
  public void appRentBalancePay(Rent_defer rentDefer,User_info userinfo,ReturnDataNew returnData) throws QzException{
    try{

        if(null == userinfo){
          throw new QzException("用户不存在");
        }
        int money = rentDefer.getMoney(); //支付金额
        //钱包金额判断
        if(isNotSureMoney(userinfo,money)){
          //抛出钱包金额不足的异常
          String message = "亲，你的租赁续租订单【" + rentDefer.getRent_order_id() + "】，因钱包余额不足,自动扣款失败。";
          rentDeferCoreBiz.jpushMessage(message,rentDefer);
          throw new QzException("扣款钱包金额不足");
        }
        //虚拟币足够
        userinfo.setUi_vc(userinfo.getUi_vc() - money); //分
        int count = user_infoDao.updateByKey(userinfo);
        if (count < 1) {
          rentDefer.setPay_state(2);  //支付状态（0：未支付 1：支付成功  2：支付失败）
          rentDefer.setNote("支付失败");
          rentDefer.setUtime(new Date());
          count = rentDeferDao.updateByKey(rentDefer);
          if (count < 1) {
            //扣款失败
            throw new QzException("扣款失败--更新租赁订单失败rentDefer");
          }
          throw new QzException("扣款失败");
        } else {
          rentDefer.setPay_state(1);  //支付状态（0：未支付 1：支付成功  2：支付失败）
          rentDefer.setNote("钱包支付成功");
          rentDefer.setUtime(new Date());
          count = rentDeferDao.updateByKey(rentDefer);
          if (count < 1) {
            //扣款失败
            throw new QzException("扣款失败--更新租赁订单失败rentDefer");
          }
          //记录用户账户变更

          String act_name = "APP租赁续租钱包支付";
          Date date = new Date();
          userPB.recordVC(0, money, rentDefer.getRent_order_id(), 3, rentDefer.getUi_id(),
                  returnData, act_name, rentDefer.getUi_nd(), userinfo.getUi_tel(),
                  rentDefer.getPay_source()
                  , 0, 0,
                  0, date);
        }

        returnData.setReturnData(errorcode_success,"APP租赁续租钱包支付成功",null);
    }catch (Exception e){
      throw new QzException("钱包支付失败");
    }
  }
}
