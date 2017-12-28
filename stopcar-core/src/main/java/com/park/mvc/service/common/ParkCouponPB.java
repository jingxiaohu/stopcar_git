package com.park.mvc.service.common;

import com.park.bean.Pay_park;
import com.park.bean.User_park_coupon;
import com.park.bean.User_park_coupon_log;
import com.park.dao.User_park_coupon_logDao;
import com.park.mvc.service.BaseBiz;
import java.util.Date;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 优惠券公用方法
 *
 * @author jingxiaohu
 */
@Service
public class ParkCouponPB extends BaseBiz {

  /**
   * 这里处理优惠券逻辑
   */
  public int doCouponOrder(Pay_park pay_park, User_park_coupon user_park_coupon, int money){
    /**
     * 这里处理优惠券逻辑
     */
    Date date = new Date();
    //如果有折扣券或者金额券
    if (user_park_coupon != null && user_park_coupon.getEnd_time().getTime() > date.getTime()
        && user_park_coupon.getUpc_state() == 0) {
      pay_park.setUpc_id(user_park_coupon.getUpc_id());
      //没有过期且有效
      if (user_park_coupon.getUpc_type() == 0) {
        //金额券
        if (user_park_coupon.getHigh_money() > user_park_coupon.getMoney()) {
          if (money - user_park_coupon.getMoney() > 0) {
            money = money - user_park_coupon.getMoney();
          } else {
            money = 0;
          }
          pay_park.setDiscount_money(user_park_coupon.getMoney());
        } else {
          pay_park.setDiscount_money(user_park_coupon.getHigh_money());
          money = money - user_park_coupon.getHigh_money() > 0 ? money - user_park_coupon
              .getHigh_money() : 0;
        }

        pay_park.setDiscount_name(user_park_coupon.getMoney() / 100 + "元券");

      } else {
        //折扣券
        if (user_park_coupon.getHigh_money() > (money
            - (int) user_park_coupon.getDiscount() * money)) {
          pay_park.setDiscount_money(money - (int) user_park_coupon.getDiscount() * money);
          money = (int) user_park_coupon.getDiscount() * money;
        } else {
          pay_park.setDiscount_money(user_park_coupon.getHigh_money());
          money = money - user_park_coupon.getHigh_money() > 0 ? money - user_park_coupon
              .getHigh_money() : 0;
        }
        pay_park.setDiscount_name((int) user_park_coupon.getDiscount() + "折券");
      }
      pay_park.setDiscount_type(user_park_coupon.getUpc_type());
    }

    return money;
  }
/**
 *  使用一张该用户优惠券库中抵扣最多且没有过期的券-----暂时不考虑折扣券
 */
/*public void UseOneMaxMoneyCoupon(Pay_park pay_park){
  try {
		User_park_coupon user_park_coupon = ReturnMaxMoneyCoupon(pay_park.getUi_id());
		if(user_park_coupon != null){
			pay_park.setUpc_id(user_park_coupon.getUpc_id());
			pay_park.setDiscount_type(user_park_coupon.getUpc_type());
			if(user_park_coupon.getUpc_type() == 0){
				pay_park.setDiscount_name(user_park_coupon.getMoney()+"元券");
			}else{
				pay_park.setDiscount_name((int)user_park_coupon.getDiscount()+"折券");
			}
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		log.error("使用一张该用户优惠券库中抵扣最多且没有过期的券 UseOneMaxMoneyCoupon is error", e);
	}
}*/

@Autowired
private User_park_coupon_logDao user_park_coupon_logDao;
  /**
   * 更新优惠券使用状态 为已经使用
   *
   * @param upc_id :用户优惠券主键ID
   */
  public User_park_coupon upUserParkCouponState(long upc_id,long ui_id) {
    try {
      User_park_coupon user_park_coupon = user_park_couponDao.selectByKey(upc_id);
      User_park_coupon_log user_park_coupon_log = new User_park_coupon_log();
      BeanUtils.copyProperties(user_park_coupon,user_park_coupon_log);
      if (user_park_coupon != null && !isOverTime(user_park_coupon,ui_id)) {
    	  
        //upc_state : 使用状态 0：未使用 1：已使用
        user_park_coupon.setUpc_state(1);
        user_park_coupon.setUtime(new Date());
        int count = user_park_couponDao.updateByKey(user_park_coupon);
        if (count == 1) {
          user_park_coupon_log.setType(1);//记录类型（0：未指定1：用户停车使用2：用户扫码转赠3：系统赠送）
          user_park_coupon_logDao.insert(user_park_coupon_log);
          //更新成功
          return user_park_coupon;
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("ParkInfoPB.upUserParkCouponState is error", e);
    }
    return null;
  }

  /**
   * 获取未过期的最小面额的一张代金券
   */
  public User_park_coupon ReturnMaxMoneyCoupon(long ui_id, long money) {
    try {
      String sql =
          "select * from user_park_coupon where ui_id=? and money<=? and upc_state=0 and end_time > now() and send_unit=0 "
              + " order by end_time asc, money asc limit 1";
      return getMySelfService().queryUniqueT(sql, User_park_coupon.class, ui_id, money);
    } catch (Exception e) {
      log.error("获取未过期的最大面额的一张代金券 ReturnMaxMoneyCoupon is error", e);
    }
    return null;
  }

  /**
   * 获取未过期的最小面额的一张代金券
   */
  public User_park_coupon ReturnMaxAllCoupon(long ui_id) {
    try {
      String sql =
          "select * from user_park_coupon where ui_id=? and upc_state=0 and end_time > now()"
              + " order by  money asc,end_time asc limit 1";
      return getMySelfService().queryUniqueT(sql, User_park_coupon.class, ui_id);
    } catch (Exception e) {
      log.error("获取未过期的最大面额的一张代金券 ReturnMaxMoneyCoupon is error", e);
    }
    return null;
  }

  /**
   * 获取未过期的最小面额的一张代金券  -- 龙支付的时候调用该方法
   * 2017/3/22 修改：龙支付优先使用吾泊券
   */
  public User_park_coupon ReturnMaxAllCoupon_LZF(long ui_id) {
    try {
      String sql =
          "select * from user_park_coupon where ui_id=? and upc_state=0 and end_time > now()"
              + " order by send_unit asc, money asc limit 1";
      return getMySelfService().queryUniqueT(sql, User_park_coupon.class, ui_id);
    } catch (Exception e) {
      log.error("获取未过期的最大面额的一张代金券 ReturnMaxMoneyCoupon is error", e);
    }
    return null;
  }

  /**
   * 获取未过期的最小面额的一张代金券  -- 龙支付的时候调用该方法
   * 2017/3/22 修改：龙支付优先使用吾泊券 /占道停车只能用小于等于2元的劵；
   */
  public User_park_coupon ReturnZhandaoMaxAllCoupon_LZF(long ui_id, long money) {
    try {
      String sql =
          "select * from user_park_coupon where ui_id=? and money<=? and upc_state=0 and end_time > now()"
              + " order by send_unit asc, money asc limit 1";
      return getMySelfService().queryUniqueT(sql, User_park_coupon.class, ui_id, money);
    } catch (Exception e) {
      log.error("获取未过期的最大面额的一张代金券 ReturnMaxMoneyCoupon is error", e);
    }
    return null;
  }
	
  /**
   * 检查代金券是否 已经过期 或者 已经被使用过了 或者 不属于同一个人
   * @param user_park_coupon : 用户停车场代金券表对象
   * @param ui_id : 用户主键ID
   * @return true: 已经过期 或者 已经被使用过了 或者 不属于同一个人   false:反之亦然
   */
  public boolean isOverTime(User_park_coupon user_park_coupon,long ui_id){
	  //检查该代金券是否过期或者被使用过了
      if (user_park_coupon.getUpc_state() == 1
          || user_park_coupon.getUi_id() != ui_id
          || user_park_coupon.getEnd_time().getTime() - System.currentTimeMillis() < 0) {
    	  //是
    	  return true;
      }
      return false;
  }
}
