package com.park.mvc.service.common;

import com.park.bean.Pay_month_park;
import com.park.bean.Pay_park;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_info;
import com.park.bean.User_park_coupon;
import com.park.bean.User_pay;
import com.park.exception.QzException;
import com.park.mvc.service.BaseBiz;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 普通订单公用方法
 *
 * @author jingxiaohu
 */
@Service
public class PayMonthParkPB extends BaseBiz {

  @Autowired
  protected ParkCouponPB parkCouponPB;
  @Autowired
  private ParkInfoPB parkInfoPB;
  @Autowired
  private UserPB userPB;

  /**
   * 通过订单编号获取某条订单详情
   */
  public Pay_month_park selectOnePayMonthPark(String orderid) {
    try {
      String sql = "SELECT *  FROM pay_month_park WHERE my_order=?";
      return getMySelfService().queryUniqueT(sql, Pay_month_park.class, orderid);
    } catch (Exception e) {
      e.printStackTrace();
      // TODO Auto-generated catch block
      log.error("PayMonthParkPB.selectOnePayMonthPark 通过订单编号获取某条订单详情错误", e);
    }
    return null;
  }


  /**
   * 获取当前临停车费用
   */
  public int returnCarMoney(Pay_park pay_park) {
    Date date = new Date();
    //时间差
    long diff_time =
        date.getTime() - pay_park.getCtime().getTime() - pay_park.getStart_time() * 60 * 1000;
    int hours = 0;//超时时间 单位小时
    if (diff_time > 0) {
      //超时
      //超时时间
      hours = (int) diff_time / (3600 * 1000);
      if (hours < 1) {
        //不到一个小时就按一个小时计算
        hours = 1;
      }
    }
    //总计费金额
    int total_money = pay_park.getStart_price() + pay_park.getCharging() * hours;

    return total_money;
  }


  /**
   * 第三方回调通知 更改租赁订单扣款成功
   */
  public Pay_month_park upPayMonthParkNotify(User_pay user_pay) {
    try {
      String sql = "SELECT *  FROM pay_month_park WHERE my_order=? LIMIT 1";
      Pay_month_park pay_month_park = getMySelfService()
          .queryUniqueT(sql, Pay_month_park.class, user_pay.getCar_order_id());
      if (pay_month_park == null) {
        return null;
      }
      //验证是否金额一致 如果不一致有可能是被抓包  恶意刷我们的钱包
      if (pay_month_park.getMoney() != (int) user_pay.getMoney()) {
        //金额不匹配
        return null;
      }
      if (pay_month_park.getPp_state() == 0) {

        pay_month_park.setPp_state(1);
        pay_month_park.setOther_order(user_pay.getTransaction_id());
        pay_month_park.setPay_source(user_pay.getType());
        pay_month_park.setUtime(new Date());
        //这里设置 租赁状态为租赁中
        pay_month_park.setRent_state(1);//租赁状态1：租赁中2：租赁成功3：租赁失败--解绑租赁金额

        int count = pay_month_parkDao.updateByKey(pay_month_park);
        if (count == 1) {
          if (pay_month_park.getUpc_id() > 0) {
            //如果使用了优惠券 那么需要更改优惠券的使用状态
            User_park_coupon user_park_coupon1 = parkCouponPB
                .upUserParkCouponState(pay_month_park.getUpc_id(),pay_month_park.getUi_id());
            if (user_park_coupon1 == null) {
              //更新失败
              return null;
            } else {
              asyncJpushTask.pushUseCouponMsg(pay_month_park.getUi_nd(), user_park_coupon1);
            }
          }
          //更新成功  （by jxh 2016-11-24 这里需要给商户账户上面资金增加 这块业务放到 车辆出库的地方处理  ）
          return pay_month_park;
        }
      }
      return null;
    } catch (Exception e) {
      log.error("PayParkUtil.upPayMonthParkNotify 第三方回调通知::通过订单编号第三方回调通知 更改租赁订单扣款成功错误", e);
    }
    return null;
  }

  /**
   * 获取某用户对应得某绑定车牌还没有支付的订单
   */
  public List<Pay_park> selectAllpayMonthParkBYcar_code(long ui_id, String car_code) {
    try {
      String sql = "select *  from pay_month_park where ui_id=? and car_code=? and ((pp_state=0  and  is_over=0 and cancel_state=0 and is_del=0)  or (pp_state=1 and  UNIX_TIMESTAMP(end_time) - unix_timestamp() >= 0)) order by ctime desc limit 1";
      return getMySelfService().queryListT(sql, Pay_park.class, ui_id, car_code);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("PayMonthParkPB.selectAllpayMonthParkBYcar_code 获取某用户对应得某绑定车牌还没有支付的订单错误", e);
    }
    return null;
  }


  /**
   * 调度处理租赁订单超过了结束时间更新
   */
  public synchronized void upRentOrderOutTime() {
    // TODO Auto-generated method stub
    try {
      String sql = "select *  from pay_month_park where  is_expire=0 and  pp_state=1  and cancel_state=0  and  unix_timestamp()-UNIX_TIMESTAMP(end_time) > 0 and rent_state=2";
      List<Pay_month_park> list = getMySelfService().queryListT(sql, Pay_month_park.class);
      if (list != null && list.size() > 0) {
        //有预约超时的 订单
        for (Pay_month_park pay_month_park : list) {
          try {
            //修改成关闭
            pay_month_park.setIs_expire(1);//是否已到期（0：没有到期 1：已经到期）',
            pay_month_park.setCancel_state(1);//关闭
            pay_month_parkDao.updateByKey(pay_month_park);
            //这里更新租赁车辆出入库的数量变化----只针占道停车场
            /*sql = "update park_info set time_car_num_space=time_car_num_space+1 where pi_id="+pay_month_park.getPi_id()+"  and  time_car_num > 0 and   time_car_num_space >= 0 and (time_car_num_space+1 <= time_car_num)";
            getMySelfService().execute(sql);*/
          } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("处理 upRentOrderOutTime  调度处理租赁订单超过了结束时间更新失败", e);
            return;
          }
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("调度处理租赁订单超过了结束时间更新 upRentOrderOutTime() is error" + e.getMessage(), e);
    }
  }

  /**
   * 调度处理租赁订单 ---还处于租赁中--10分钟-租赁车超时
   */
  public synchronized void upRentOrderOutTime10() {
    // TODO Auto-generated method stub
    try {
      String sql = "select *  from pay_month_park where  is_expire=0 and  pp_state=1  and cancel_state=0  and  unix_timestamp()-UNIX_TIMESTAMP(end_time) > 0  and unix_timestamp()-UNIX_TIMESTAMP(ctime) > 10*60 and rent_state=1";
      List<Pay_month_park> list = getMySelfService().queryListT(sql, Pay_month_park.class);
      if (list != null && list.size() > 0) {
        //有预约超时的 订单
        for (Pay_month_park pay_month_park : list) {
          try {
            doOvertimeLease(pay_month_park);
          } catch (QzException e) {
            log.error("处理 upRentOrderOutTime  调度处理租赁订单超过了结束时间更新失败", e);
          }
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("调度处理租赁订单 ---还处于租赁中--10分钟-租赁车超时 upRentOrderOutTime10() is error" + e.getMessage(),
          e);
    }
  }

  /**
   * 处理超时租赁订单 .
   */
  public void doOvertimeLease(Pay_month_park pay_month_park) throws QzException {
    //修改成关闭
    /**
     * 租赁车超时---还处于租赁中
     */
    User_info user_info = user_infoDao.selectByKey(pay_month_park.getUi_id());
    if (user_info == null) {
      throw new QzException("租赁订单[" + pay_month_park.getMy_order() + "]异常,缺失租赁用户信息");
    }
    ReturnDataNew returnData = new ReturnDataNew();
    if (pay_month_park.getPark_type() == 0) {
      //道闸停车
      userPB.doUnLockMoney(2, 1, 1, pay_month_park.getMoney(),
          user_info, pay_month_park.getMy_order(),
          pay_month_park.getPi_id(), pay_month_park.getArea_code(),
          pay_month_park,
          null, returnData);
    }


  }

  /**
   * 检查该用户是否存在租赁确没有付款的  --- 租赁订单
   *
   * @return true:有没有付款的租赁订单或者该租赁订单还未到期
   */
  public boolean CheckPayOrOutTime(ReturnDataNew returnData, long pi_id, String car_code,
      long ui_id, Date date) {
    try {
      String sql = "SELECT * FROM pay_month_park WHERE pi_id=? AND car_code=?   AND ui_id=? AND cancel_state=0  ORDER BY ctime DESC LIMIT 1";
      Pay_month_park pay_month_park2 = getMySelfService()
          .queryUniqueT(sql, Pay_month_park.class, pi_id, car_code, ui_id);
      if (pay_month_park2 != null) {
        if (pay_month_park2.getPp_state() == 0) {
          returnData.setReturnData(errorcode_data, "亲，你还有没有支付完成的订单", "");
          return true;
        } else {
          if (pay_month_park2.getEnd_time().getTime() - date.getTime() > 0) {
            //你的包月订单还没有到期
            returnData.setReturnData(errorcode_data, "亲，你的租赁时间还未到期!", "");
            return true;
          }
        }
      }
    } catch (Exception e) {

      log.error("CheckPayOrOutTime 亲，你还有没有支付完成的订单 失败", e);
    }
    return false;
  }

  /**
   * 租赁订单代金券抵扣处理
   */
  public void RentOrderCoupon(Pay_month_park pay_month_park, User_park_coupon user_park_coupon,
      long upc_id, int money, Date date) throws Exception {
    //如果有折扣券或者金额券
    user_park_coupon = user_park_couponDao.selectByKey(upc_id);
    if (user_park_coupon != null && user_park_coupon.getEnd_time().getTime() < date.getTime()
        && user_park_coupon.getUpc_state() == 0) {
      //没有过期且有效
      if (user_park_coupon.getUpc_type() == 0) {
        //金额券
        if (user_park_coupon.getHigh_money() > user_park_coupon.getMoney()) {
          if (money - user_park_coupon.getMoney() > 0) {
            pay_month_park.setMoney(money - user_park_coupon.getMoney());
          } else {
            pay_month_park.setMoney(0);
          }
          pay_month_park.setDiscount_money(user_park_coupon.getMoney());
          pay_month_park.setDiscount_name(user_park_coupon.getMoney() / 100 + "元券");
        } else {
          pay_month_park.setDiscount_money(user_park_coupon.getHigh_money());
          pay_month_park.setMoney(money - user_park_coupon.getHigh_money());
        }

      } else {
        //折扣券
        if (user_park_coupon.getHigh_money() > (money
            - (int) user_park_coupon.getDiscount() * money)) {
          pay_month_park
              .setDiscount_money(money - (int) user_park_coupon.getDiscount() * money);
          pay_month_park.setMoney((int) user_park_coupon.getDiscount() * money);
        } else {
          pay_month_park.setDiscount_money(user_park_coupon.getHigh_money());
          pay_month_park.setMoney(money - user_park_coupon.getHigh_money());
        }

        pay_month_park.setDiscount_name((int) user_park_coupon.getDiscount() + "折券");
      }
      pay_month_park.setDiscount_type(user_park_coupon.getUpc_type());
    }
  }


  /**
   * 租赁开始和过期时间设置
   */
  public void PutRentOutTime(Pay_month_park pay_month_park, int month_num, Date date,
      String permit_time) throws Exception {
    //08:00-21:00
    String prex = "-";
    String inex = ":";
    if (permit_time == null || permit_time.indexOf(prex) == -1) {
      throw new Exception("时间段格式错误");
    }
    String[] pt = permit_time.split(prex);
    if (pt == null || pt.length != 2) {
      throw new Exception("时间段格式错误");
    }

    String[] start = pt[0].split(inex);
    if (start == null || start.length != 2) {
      throw new Exception("时间段格式错误");
    }

    int hourofday = Integer.parseInt(start[0]);
    int minute = Integer.parseInt(start[1]);
    //包月起始日 包月截止日
    Calendar cl = Calendar.getInstance(Locale.CHINESE);
    cl.setTime(date);
      /*cl.set(cl.get(Calendar.YEAR),
          cl.get(Calendar.MONTH),
	    		cl.get(Calendar.DAY_OF_MONTH),
	    		hourofday, minute);*/
    cl.set(Calendar.HOUR_OF_DAY, hourofday);
    cl.set(Calendar.MINUTE, minute);
    pay_month_park.setStart_time(cl.getTime());

    cl = Calendar.getInstance(Locale.CHINESE);
    cl.setTime(date);
    cl.add(Calendar.MONTH, month_num);

    String[] end = pt[1].split(inex);
    if (end == null || end.length != 2) {
      throw new Exception("时间段格式错误");
    }
    hourofday = Integer.parseInt(end[0]);
    minute = Integer.parseInt(end[1]);
    cl.set(Calendar.HOUR_OF_DAY, hourofday);
    cl.set(Calendar.MINUTE, minute);
    pay_month_park.setEnd_time(cl.getTime());
    pay_month_park.setPermit_time(permit_time);
  }
}
