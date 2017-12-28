/**
 * @Title: TT.java
 * @Package com.intimes.biz
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 敬小虎
 * @date 2015年3月20日 下午1:32:43
 * @version V1.0
 */
package com.park.pda.transaction;

import com.park.bean.User_pay;
import com.park.exception.QzException;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.ActivityPB;
import com.park.mvc.service.common.ParkCouponPB;
import com.park.mvc.service.common.PayMonthParkPB;
import com.park.mvc.service.common.PayParkPB;
import com.park.mvc.service.common.UserPB;
import com.park.util.RequestUtil;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 敬小虎
 * @ClassName: TT
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2015年3月20日 下午1:32:43
 */
@Transactional(rollbackFor = QzException.class)
@Service
public class PDAPayTransaction extends BaseBiz {

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

  /**
   * 插入用户充值订单表
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
}
