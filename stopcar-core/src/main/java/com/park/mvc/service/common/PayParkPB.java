package com.park.mvc.service.common;

import com.alibaba.fastjson.JSON;
import com.park.bean.*;
import com.park.constants.Constants;
import com.park.dao.Pda_owe_order_coverDao;
import com.park.exception.QzException;
import com.park.jpush.bean.JPushMessageBean;
import com.park.mvc.service.BaseBiz;
import com.park.transaction.CarTransaction;
import com.park.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 普通订单公用方法
 *
 * @author jingxiaohu
 */
@Service
public class PayParkPB extends BaseBiz {

  @Autowired
  protected ParkCouponPB parkCouponPB;
  @Autowired
  private UserPB userPB;
  @Autowired
  private ParkInfoPB parkInfoPB;
  @Autowired
  protected ActivityPB activityPB;
  @Autowired
  private CarTransaction carTransaction;

  /**
   * 通过订单编号获取某条订单详情
   */
  public Pay_park selectOnePayPark(String orderid) {
    try {
      String sql = "SELECT *  FROM pay_park WHERE my_order=? ORDER BY pp_state ASC,ctime DESC LIMIT 1";
      return getMySelfService().queryUniqueT(sql, Pay_park.class, orderid);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("PayParkUtil.selectOnePayPark 通过订单编号获取某条订单详情错误", e);
    }
    return null;
  }

  /**
   * 通过订单编号获取某条包月订单详情
   */
  public Pay_month_park selectOnePayMonthPark(String orderid) {
    try {
      String sql = "SELECT *  FROM pay_month_park WHERE my_order=?";
      return getMySelfService().queryUniqueT(sql, Pay_month_park.class, orderid);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("PayParkUtil.selectOnePayMonthPark 通过订单编号获取某条订单详情错误", e);
    }
    return null;
  }


  /**
   * 获取当前临停车费用
   */
  @Deprecated
  public int returnCarMoney(Pay_park pay_park) {
    //检查是否是租赁产生的临停订单
    if (pay_park.getOrder_type() == 2) {
      /*Date date = new Date();
      if(date.getTime() - pay_park.getCtime().getTime() >= 10*60*1000){
				//超过10分钟后不允许进行支付
				return 0;
			}*/
      //时间差
      long diff_time = pay_park.getFinal_time() * 60 * 1000 - pay_park.getStart_time() * 60 * 1000;
      if (diff_time > 0) {
        //总计费金额
        int count = 0;
        int diff_time_minute = (int) diff_time / (60 * 1000);//分钟
        if (pay_park.getCharging_time() > 0
            && diff_time_minute % pay_park.getCharging_time() == 0) {
          count = diff_time_minute / pay_park.getCharging_time();
        } else {
          count = diff_time_minute / pay_park.getCharging_time();
          count = count + 1;
        }
        int total_money = pay_park.getStart_price() + pay_park.getCharging() * count;
        return total_money;
      } else {
        return pay_park.getStart_price();
      }
    } else {
      Date date = new Date();
      //时间差
      long diff_time =
          date.getTime() - pay_park.getCtime().getTime() - pay_park.getStart_time() * 60 * 1000;

      pay_park.setFinal_time((int) (date.getTime() - pay_park.getCtime().getTime()) / (60 * 1000));
      if (diff_time > 0) {
        if (pay_park.getFinal_time() == 0) {
          pay_park.setFinal_time(1);
        }
        //总计费金额
        int count = 0;
        int diff_time_minute = (int) diff_time / (60 * 1000);//分钟
        if (pay_park.getCharging_time() > 0
            && diff_time_minute % pay_park.getCharging_time() == 0) {
          count = diff_time_minute / pay_park.getCharging_time();
        } else {
          count = diff_time_minute / pay_park.getCharging_time();
          count = count + 1;
        }
        int total_money = pay_park.getStart_price() + pay_park.getCharging() * count;
        return total_money;
      } else {
        return pay_park.getStart_price();
      }


    }


  }

  /**
   * 获取当前临停车费用
   */
  public int returnCarMoney(Pay_park pay_park, long diff_time) {
    //时间差
    if (diff_time > 0) {
      //总计费金额
      double price_ms = (pay_park.getCharging() * 1.0) / (pay_park.getCharging_time() * 60 * 1000);
      int total_money = pay_park.getStart_price() + (int) (price_ms * diff_time);
      return total_money;
    } else {
      return pay_park.getStart_price();
    }

  }

  /**
   * 用户充值::通过订单编号获取某条用户充值订单详情
   */
  public User_pay selectOneUserPay(String orderid) {
    try {
      String sql = "SELECT *  FROM user_pay WHERE order_id=? LIMIT 1";
      return getMySelfService().queryUniqueT(sql, User_pay.class, orderid);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("PayParkUtil.selectOneUserPay 用户充值::通过订单编号获取某条用户充值订单详情错误", e);
    }
    return null;
  }

  /**
   * 第三方回调通知 更改普通订单扣款成功
   */
//  public Pay_park upPayParkNotify(String orderid, String other_orderid, long money) {
  public Pay_park upPayParkNotify(User_pay user_pay) {
    try {
      String sql = "SELECT *  FROM pay_park WHERE my_order=? LIMIT 1";
      Pay_park pay_park = getMySelfService()
          .queryUniqueT(sql, Pay_park.class, user_pay.getCar_order_id());
      if (pay_park == null) {
        return null;
      }
      //by jxh 2017-3-18 这里 只处理 非 占道停车的订单的优惠券更新状态  pay_park.getPark_type() != 1
      if (pay_park.getPp_state() == 0 && pay_park.getPark_type() != 1) {
        pay_park.setPp_state(1);
        pay_park.setOther_order(user_pay.getTransaction_id());
        pay_park.setUtime(new Date());
        pay_park.setPay_source(user_pay.getType());
        int count = pay_parkDao.updateByKey(pay_park);
        if (count == 1) {
          //更新成功  （by jxh 2016-11-24 这里需要给商户账户上面资金增加 这块业务放到 车辆出库的地方处理  ）
          if (pay_park.getUpc_id() > 0) {
            //如果使用了优惠券 那么需要更改优惠券的使用状态
            User_park_coupon user_park_coupon1 = parkCouponPB
                .upUserParkCouponState(pay_park.getUpc_id(),pay_park.getUi_id());
            if (user_park_coupon1 == null) {
              //更新失败
              //throw new QzException("record_user_vc_act 优惠券更新使用状态失败");
              return null;
            } else {
              asyncJpushTask.pushUseCouponMsg(pay_park.getUi_nd(), user_park_coupon1);
            }
          }
          return pay_park;
        }
      } else {

        return pay_park;

      }
    } catch (Exception e) {
      log.error("PayParkUtil.upPayParkNotify 第三方回调通知::通过订单编号第三方回调通知 更改普通订单扣款成功错误", e);
    }
    return null;
  }


  /**
   * 获取用户下单
   *
   * @param ui_id 用户ID
   * @param pi_id 车库表主键ID
   * @param car_code 车牌号
   * @param order_type 下单类型 0: 普通下单  1：预约下单 2：租赁包月订单
   */
  public Pay_park QueryPayPark(long ui_id, long pi_id, String car_code, int order_type,
      String area_code) {
    //入库之前先检查该用户对该车辆进行该停车场预约下单没有   如果已经进行了预约下单则直接更新对应得数据
    try {
      Pay_park pay_park = null;
      String sql = "SELECT *  FROM pay_park WHERE pi_id = ? AND area_code=? AND car_code=? AND ui_id=? AND pp_state=0 AND  order_type=?  AND is_del=0 AND cancel_state=0 ORDER BY  ctime DESC LIMIT 1";
      pay_park = getMySelfService()
          .queryUniqueT(sql, Pay_park.class, pi_id, area_code, car_code, ui_id, order_type);
      return pay_park;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("	QueryPayPark(long ui_id,long pi_id,String car_code,int order_type) is error" + e
          .getMessage(), e);
    }
    return null;
  }


  /**
   * 获取用户还没有支付的最后一条某停车场的订单
   *
   * @param ui_id 用户ID
   * @param pi_id 车库表主键ID
   * @param car_code 车牌号
   */
  public Pay_park QueryPayPark(long ui_id, long pi_id, String car_code, String area_code) {
    //入库之前先检查该用户对该车辆进行该停车场预约下单没有   如果已经进行了预约下单则直接更新对应得数据
    try {
      Pay_park pay_park = null;
      String sql = "SELECT *  FROM pay_park WHERE pi_id = ? AND  area_code=? AND car_code=? AND ui_id=? AND pp_state=0 AND cancel_state=0  AND is_del=0 ORDER BY  ctime DESC LIMIT 1";
      pay_park = getMySelfService()
          .queryUniqueT(sql, Pay_park.class, pi_id, area_code, car_code, ui_id);
      return pay_park;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error(
          "获取用户下单全部	QueryPayPark(long ui_id,long pi_id,String car_code) is error" + e.getMessage(),
          e);
    }
    return null;
  }

  /**
   * 获取用户已经支付的最后一条某停车场的订单
   *
   * @param ui_id 用户ID
   * @param pi_id 车库表主键ID
   * @param car_code 车牌号
   */
  public Pay_park QueryPayParkOverPay(long ui_id, long pi_id, String car_code, String area_code) {
    //入库之前先检查该用户对该车辆进行该停车场预约下单没有   如果已经进行了预约下单则直接更新对应得数据
    try {
      Pay_park pay_park = null;
      String sql = "SELECT *  FROM pay_park WHERE pi_id = ? AND  area_code=? AND car_code=? AND ui_id=? AND pp_state=1 AND cancel_state=0  AND is_del=0 ORDER BY  ctime DESC LIMIT 1";
      pay_park = getMySelfService()
          .queryUniqueT(sql, Pay_park.class, pi_id, area_code, car_code, ui_id);
      return pay_park;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error(
          "获取用户下单全部	QueryPayPark(long ui_id,long pi_id,String car_code) is error" + e.getMessage(),
          e);
    }
    return null;
  }

  /**
   * 摄像头扫描到下单
   */
  public Pay_park cameraCarOrder(int dtype, String order_id, Car_in_out car_in_out,
      Park_info park_info,
      String gov_num) throws QzException {
    try {
      String area_code = park_info.getArea_code();
      long ui_id = car_in_out.getUi_id();
      //车牌号
      String car_code = car_in_out.getCar_code();
      //获取该停车场的计费规则 rcr_type ：停车类型 0：普通车位停车 1：时间段包月停车
      Rental_charging_rule charging_rule = queryChargeRule(car_in_out.getPi_id(), 0,
          car_in_out.getCar_type(), area_code, car_in_out.getCar_code_color());
      if (charging_rule == null) {
        //没有获取到停车规则，获取默认规则，如果默认规则还没有获取到，抛出异常   by zzy 2017-07-06
        charging_rule = queryChargeDefaultRule(car_in_out.getPi_id(),0,car_in_out.getArea_code());
        if(null == charging_rule){
          log.info("没有获取到停车场规则，获取停车场默认规则，获取默认规则失败 " +
                          "pi_id:{}，area_code:{},car_type:{},car_code_color:{}",car_in_out.getPi_id(),
                  car_in_out.getArea_code(),car_in_out.getCar_type(),car_in_out.getCar_code_color());
          //下单失败
          throw new QzException(
                  "下单失败，[区域：" + car_in_out.getArea_code() + "，停车场ID:" + car_in_out.getPi_id() + "，车辆类型："
                          + car_in_out
                          .getCar_type() + "车辆颜色：" + car_in_out.getCar_code_color() + "]未设置收费规则");
        }

      }
      Date date = car_in_out.getCtime();//时间依入库时间
      Pay_park pay_park = new Pay_park();
      if (car_in_out.is_sync == 1) {
        pay_park.setCancel_state(3);
      }
      pay_park.setGov_num(gov_num);
      pay_park.setMagnetic_state(1);
      pay_park.setStart_price(charging_rule.getStart_price());//起步价格（单位 分）
      pay_park.setStart_time(charging_rule.getStart_time());//起步时长（分钟）
      pay_park.setCharging(charging_rule.getCharging());//计费价格(单位 分)
      pay_park.setCharging_time(charging_rule.getCharging_time());//计费时长(分钟)

      pay_park.setAllege_state(0);//申述状态 0:未申述 1：已申述
      pay_park.setArrive_time(date);//到场时间
      pay_park.setLeave_time(date);//离场时间
      pay_park.setOpen_time(date);
      pay_park.setScan_time(date);
      pay_park.setCar_code(car_code);//车牌号
      pay_park.setCtime(date);//创建时间
      if (StringUtils.hasText(order_id)) {
        pay_park.setMy_order(order_id);
      } else {
        int product_type = 0;//产品标号1位：0 道闸 1 PDA  2 地磁PDA  3 mepos道闸
        switch (dtype) {//（0:android 1:ios 2:PDA 3: web 4:道闸 5：MEPOS 6：地磁设备android板）
          case 2:
            product_type = 1;
            break;
          case 4:
            product_type = 0;
            break;
          case 5:
            product_type = 3;
            break;
          case 6:
            product_type = 2;
            break;
        }
        int order_type = 0;//订单类型：0 临停订单 1 本地包月订单 2 本地免费订单 3  预约订单 4 租赁订单 （其中预约订单和租赁订单生成都由服务器端生成）
        switch (car_in_out.out_type) {//;//入库/出库类型:
          // (0:正常出入库 1：道闸本地临停出入库  2：道闸本地包月出入库   3：异常出入库   4：道闸本地免费车出入库   5:预约车辆出入库  6:租赁车辆出入库)
//          case 0:
          case 1:
            order_type = 0;
            break;
          case 2:
            order_type = 1;
            break;
          case 4:
            order_type = 2;
            break;
          case 5:
            order_type = 3;
            break;
          case 6:
            order_type = 4;
            break;
        }
        pay_park.setMy_order(
            returnNewOrderId(area_code, product_type, park_info.pi_id, order_type));//我们自己生成的订单号
      }
      pay_park.setOrder_type(0);//下单类型 0: 普通下单  1：预约下单 2：租赁包月订单
//	    	    pay_park.setOther_order(value);//第三方的 支付单号
//	    	    pay_park.setPay_source(value);//支付类型 1:支付宝 2：微信 3：银联
//	    	    pay_park.setPay_type(value);//支付类型 0:手动输入密码支付 1：快捷支付（服务器可以请求第三方直接扣款）
//	    	    pay_park.setPhone_type(value);//手机类型 0:android 1：IOS
      pay_park.setPi_id(car_in_out.getPi_id());//支付停车场主键ID
      pay_park.setPp_state(0);//支付状态 0:未支付 1：已经支付
//	    	    pay_park.setPp_versioncode(value);//当前APPSDK版本号 （内部升级版本代号）
      pay_park.setUi_id(ui_id);//用户ID
      if (car_in_out.getUi_nd() != null) {
        pay_park.setUi_nd(car_in_out.getUi_nd());//用户唯一标识符
      }
      if (car_in_out.getUi_tel() != null) {
        pay_park.setUi_tel(car_in_out.getUi_tel());
      }
      pay_park.setUtime(date);//更新时间
//	    	    pay_park.setExpect_info(value);//预定提示信息
//	       	    pay_park.setExpect_money(value);//预定价格
//	    	    pay_park.setExpect_time(value);//预定时间
//	    	    pay_park.setMoney(value);//支付金额（单位 分）
//				pay_park.setNote("自动下订单"); 
      pay_park.setAddress_name(park_info.getAddress_name());//停车场地址
      pay_park.setPi_name(park_info.getPi_name());//停车场名称
      pay_park.setArea_code(area_code);//省市县编号
      pay_park.setPark_type(park_info.getPark_type());

      //省市县区域代码
      pay_park.setArea_code(car_in_out.getArea_code());

      pay_park.setFree_minute(charging_rule.getFree_minute());//多少分钟之内进出免费
      pay_park.setIs_free_minute(charging_rule.getIs_free_minute());//多少分钟之内进出免费是否开启  0:不开启  1：开启

      //设置商户唯一标识
      pay_park.setPu_id(park_info.getPu_id());
      pay_park.setPu_nd(park_info.getPu_nd());

      //设置经纬度
      pay_park.setLng(park_info.getLng());
      pay_park.setLat(park_info.getLat());
      /**
       * 这里处理随机立减免活动事件
       */
      activityPB.record_random(pay_park);

      int id = pay_parkDao.insert(pay_park);
      if (id < 1) {
        //下单失败
        log.error("ParkinfoBiz.cameraCarOrder is error 下单插入的时候失败");
        throw new QzException("ParkinfoBiz.cameraCarOrder is error 下单插入的时候失败");
      }
      //设置车辆进入时候的 订单
      car_in_out.setOrder_id(pay_park.getMy_order());

      return pay_park;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.warn("ParkinfoBiz.cameraCarOrder is error" + e.getMessage(), e);
      throw new QzException("摄像头扫描到下单 失败", e);
    }


  }


  /**
   * 租赁分时间段包月--产生了临停订单
   */
  public Pay_park MakeAutoOrder(long pi_id, String area_code, String car_code, long ui_id,
      String ui_nd, String ui_tel, int final_time, int money, Integer car_type,
      Integer car_code_color) throws QzException {
    try {
      //获取该车辆在该停车场的入库信息
      if (car_type == null) {
        String sql = "SELECT * FROM car_in_out WHERE pi_id=? AND area_code=? AND car_code=? AND is_rent=1 AND is_enter=0 ORDER BY ctime DESC LIMIT 1";
        Car_in_out car_in_out = getMySelfService()
            .queryUniqueT(sql, Car_in_out.class, pi_id, area_code, car_code);
        if (car_in_out == null) {
          return null;
        }
        car_type = car_in_out.getCar_type();
        car_code_color = car_in_out.getCar_code_color();
      }

      //获取停车场基本信息
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        return null;
      }
      //车牌号
      //获取该停车场的计费规则 rcr_type ：停车类型 0：普通车位停车 1：时间段包月停车
      Rental_charging_rule charging_rule = queryChargeRule(pi_id, 0, car_type, area_code,
          car_code_color);
      if (charging_rule == null) {
        //下单失败
        return null;
      }

      Calendar cl = Calendar.getInstance(Locale.CHINA);
      Date date = cl.getTime();
      Pay_park pay_park = new Pay_park();
      //结算时计费时长（单位分钟）
      pay_park.setFinal_time(final_time);
      cl.setTimeInMillis(date.getTime() + 5000);//增加5秒
      pay_park.setScan_time(date);

      pay_park.setStart_price(charging_rule.getStart_price());//起步价格（单位 分）
      pay_park.setStart_time(charging_rule.getStart_time());//起步时长（分钟）
      pay_park.setCharging(charging_rule.getCharging());//计费价格(单位 分)
      pay_park.setCharging_time(charging_rule.getCharging_time());//计费时长(分钟)

      pay_park.setAllege_state(0);//申述状态 0:未申述 1：已申述
//				cl.setTimeInMillis(date.getTime() - final_time);;
      pay_park.setArrive_time(date);//到场时间==== 租赁分时段包月产生临停费用  到达时间设置为 当前时间减去 超时时间
//	    	    pay_park.setLeave_time(date);//离场时间
      pay_park.setCar_code(car_code);//车牌号
      pay_park.setCtime(date);//创建时间
      int product_type = 0;
      switch (park_info.park_type) {
        case 0:
          product_type = 0;
        case 1:
          product_type = 1;
      }
      pay_park.setMy_order(returnNewOrderId(area_code, product_type, pi_id, 0));//我们自己生成的订单号
      pay_park.setOrder_type(2);//下单类型 0: 普通下单  1：预约下单 2：租赁临停订单
//	    	    pay_park.setOther_order(value);//第三方的 支付单号
//	    	    pay_park.setPay_source(value);//支付类型 1:支付宝 2：微信 3：银联
//	    	    pay_park.setPay_type(value);//支付类型 0:手动输入密码支付 1：快捷支付（服务器可以请求第三方直接扣款）
//	    	    pay_park.setPhone_type(value);//手机类型 0:android 1：IOS
      pay_park.setPi_id(pi_id);//支付停车场主键ID
      pay_park.setPp_state(0);//支付状态 0:未支付 1：已经支付
//	    	    pay_park.setPp_versioncode(value);//当前APPSDK版本号 （内部升级版本代号）
      pay_park.setUi_id(ui_id);//用户ID
      pay_park.setUi_nd(ui_nd);//用户唯一标识符
      pay_park.setUi_tel(ui_tel);
      pay_park.setUtime(date);//更新时间
//	    	    pay_park.setExpect_info(value);//预定提示信息
//	       	    pay_park.setExpect_money(value);//预定价格
//	    	    pay_park.setExpect_time(value);//预定时间
      pay_park.setMoney(money);//支付金额（单位 分）
//				pay_park.setNote("自动下订单"); 
      pay_park.setAddress_name(park_info.getAddress_name());//停车场地址
      pay_park.setPi_name(park_info.getPi_name());//停车场名称
      pay_park.setArea_code(park_info.getArea_code());//省市县编号
      pay_park.setPark_type(park_info.getPark_type());

      pay_park.setFree_minute(charging_rule.getFree_minute());//多少分钟之内进出免费
      pay_park.setIs_free_minute(charging_rule.getIs_free_minute());//多少分钟之内进出免费是否开启  0:不开启  1：开启
      //设置商户唯一标识
      pay_park.setPu_id(park_info.getPu_id());
      pay_park.setPu_nd(park_info.getPu_nd());

      //设置经纬度
      pay_park.setLng(park_info.getLng());
      pay_park.setLat(park_info.getLat());
      /**
       * 这里处理随机立减免活动事件
       */
      activityPB.record_random(pay_park);

      int id = pay_parkDao.insert(pay_park);
      if (id < 1) {
        //下单失败
        log.error("PayParkPB.MakeAutoOrder is error 下单插入的时候失败");
      }
      return pay_park;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("PayParkPB.MakeAutoOrder is error" + e.getMessage(), e);
      throw new QzException("租赁临停订单创建 失败", e);
    }
  }

  /**
   * 租赁分时间段包月--产生了临停订单
   *
   * 创建时间、下单类型、订单号由客户端提供  2017-06-12
   */
  public Pay_park makeAutoOrder(long pi_id, String area_code, String car_code, long ui_id,
      String ui_nd, String ui_tel, int final_time, int money, Integer car_type,
      Integer car_code_color, long createTime, String orderType, String orderId)
      throws QzException {
    try {
      //获取该车辆在该停车场的入库信息
      if (car_type == null) {
        String sql = "SELECT * FROM car_in_out WHERE pi_id=? AND area_code=? AND car_code=? AND is_rent=1 AND is_enter=0 ORDER BY ctime DESC LIMIT 1";
        Car_in_out car_in_out = getMySelfService()
            .queryUniqueT(sql, Car_in_out.class, pi_id, area_code, car_code);
        if (car_in_out == null) {
          log.info("没有查询到该车辆在该停车场的入库信息{},{},{}", pi_id, area_code, car_code);
          return null;
        }
        car_type = car_in_out.getCar_type();
        car_code_color = car_in_out.getCar_code_color();
      }

      //获取停车场基本信息
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        log.info("没有查询到停车场基本信息{},{}", pi_id, area_code);
        return null;
      }
      //车牌号
      //获取该停车场的计费规则 rcr_type ：停车类型 0：普通车位停车 1：时间段包月停车
      Rental_charging_rule charging_rule = queryChargeRule(pi_id, 0, car_type, area_code,
          car_code_color);
      if (charging_rule == null) {
        log.info("获取该停车场的计费规则失败");
        //下单失败
        return null;
      }

      Date date = new Date(createTime);
      Pay_park pay_park = new Pay_park();
      //结算时计费时长（单位分钟）
      pay_park.setFinal_time(final_time);
      pay_park.setScan_time(date);

      pay_park.setStart_price(charging_rule.getStart_price());//起步价格（单位 分）
      pay_park.setStart_time(charging_rule.getStart_time());//起步时长（分钟）
      pay_park.setCharging(charging_rule.getCharging());//计费价格(单位 分)
      pay_park.setCharging_time(charging_rule.getCharging_time());//计费时长(分钟)

      pay_park.setAllege_state(0);//申述状态 0:未申述 1：已申述
      pay_park.setArrive_time(date);//到场时间==== 租赁分时段包月产生临停费用  到达时间设置为 当前时间减去 超时时间
      pay_park.setCar_code(car_code);//车牌号
      pay_park.setCtime(date);//创建时间
      int product_type = 0;
      switch (park_info.park_type) {
        case 0:
          product_type = 0;
        case 1:
          product_type = 1;
      }
      //pay_park.setMy_order(returnNewOrderId(area_code, product_type, pi_id, 0));//我们自己生成的订单号
      pay_park.setMy_order(orderId);//订单号由客户端上传
      pay_park.setOrder_type(
          Integer.parseInt(orderType));//下单类型 0: 普通下单  1：预约下单 2：租赁临停订单 3:免费转临停  4：包月转临停
      pay_park.setPi_id(pi_id);//支付停车场主键ID
      pay_park.setPp_state(0);//支付状态 0:未支付 1：已经支付
      pay_park.setUi_id(ui_id);//用户ID
      pay_park.setUi_nd(ui_nd);//用户唯一标识符
      pay_park.setUi_tel(ui_tel);
      pay_park.setUtime(date);//更新时间
      pay_park.setMoney(money);//支付金额（单位 分）
      pay_park.setAddress_name(park_info.getAddress_name());//停车场地址
      pay_park.setPi_name(park_info.getPi_name());//停车场名称
      pay_park.setArea_code(park_info.getArea_code());//省市县编号
      pay_park.setPark_type(park_info.getPark_type());

      pay_park.setFree_minute(charging_rule.getFree_minute());//多少分钟之内进出免费
      pay_park.setIs_free_minute(charging_rule.getIs_free_minute());//多少分钟之内进出免费是否开启  0:不开启  1：开启
      //设置商户唯一标识
      pay_park.setPu_id(park_info.getPu_id());
      pay_park.setPu_nd(park_info.getPu_nd());

      //设置经纬度
      pay_park.setLng(park_info.getLng());
      pay_park.setLat(park_info.getLat());
      /**
       * 这里处理随机立减免活动事件
       */
      activityPB.record_random(pay_park);

      int id = pay_parkDao.insert(pay_park);
      if (id < 1) {
        //下单失败
        log.error("PayParkPB.MakeAutoOrder is error 下单插入的时候失败");
      }
      return pay_park;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("PayParkPB.MakeAutoOrder is error" + e.getMessage(), e);
      throw new QzException("租赁临停订单创建 失败", e);
    }
  }


  /**
   * 查询场地规则数据
   *
   * @param rcr_type ：停车类型 0：普通车位停车 1：时间段包月停车
   * @param car_type 车牌类型 0：未知车牌:、1：蓝牌小汽车、2：: 黑牌小汽车、3：单排黄牌、4：双排黄牌、 5： 警车车牌、6：武警车牌、7：个性化车牌、8：单
   * 排军车牌、9：双排军车牌、10：使馆车牌、11： 香港进出中国大陆车牌、12：农用车牌、13：教 练车牌、14：澳门进出中国大陆车牌、15：双层
   * 武警车牌、16：武警总队车牌、17：双层武警总 队车牌
   * @param car_code_color 车牌颜色 0：未知、1：蓝色、2：黄色、3：白色、 4：黑色、5：绿色
   */
  public Rental_charging_rule queryChargeRule(long pi_id, int rcr_type, int car_type,
      String area_code, int car_code_color) {
    try {
      String sql = "SELECT *  FROM  rental_charging_rule WHERE pi_id=? AND area_code=? AND rcr_state=0 AND rcr_type=? AND car_code_color=? AND car_type=? LIMIT 1";
      Rental_charging_rule cr = getMySelfService()
          .queryUniqueT(sql, Rental_charging_rule.class, pi_id, area_code, rcr_type, car_code_color,
              car_type);
//				String sql = "select *  from  rental_charging_rule where pi_id=? and area_code=? and rcr_state=0 and rcr_type=? and car_code_color=? limit 1";
//				Rental_charging_rule cr = getMySelfService().queryUniqueT(sql, Rental_charging_rule.class,pi_id,area_code,rcr_type,car_code_color);
      return cr;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("queryRentChargeRuleByPiId is error " + e.getMessage(), e);
    }
    return null;
  }

  /**
   * 查询场地默认规则数据
   *
   * @param rcr_type ：停车类型 0：普通车位停车 1：时间段包月停车
   * 排军车牌、9：双排军车牌、10：使馆车牌、11： 香港进出中国大陆车牌、12：农用车牌、13：教 练车牌、14：澳门进出中国大陆车牌、15：双层
   * 武警车牌、16：武警总队车牌、17：双层武警总 队车牌
   * is_default 是否是默认规则0:不是1：是
   */
  public Rental_charging_rule queryChargeDefaultRule(long pi_id, int rcr_type, String area_code) {
    try {
      String sql = "SELECT *  FROM  rental_charging_rule WHERE pi_id=? AND area_code=? AND rcr_state=0 AND rcr_type=? AND is_default = 1 LIMIT 1";
      Rental_charging_rule cr = getMySelfService()
              .queryUniqueT(sql, Rental_charging_rule.class, pi_id, area_code, rcr_type);
      return cr;
    } catch (Exception e) {
      log.error("queryChargeDefaultRule is error " + e.getMessage(), e);
    }
    return null;
  }

  /**
   * 获取某车辆在某停车场最近一次的未到期的租赁订单
   */
  public Pay_month_park queryRentOrder(long pi_id, String car_code, String area_code, long ui_id,
      Date arrive_time) {
    try {
      String sql = "SELECT * FROM pay_month_park  WHERE pi_id=? AND car_code=? AND area_code=? AND pp_state=1 AND ui_id=? AND end_time>? ORDER BY ctime DESC LIMIT 1";
      return getMySelfService()
          .queryUniqueT(sql, Pay_month_park.class, pi_id, car_code, area_code, ui_id, arrive_time);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("queryRentOrder is error " + e.getMessage(), e);
    }
    return null;
  }


  /**
   * 标记该停车场部分订单异常----cancel_state = 2
   */
  public void upOrderFault(Park_info park_info) {
    try {
      String sql = "select *  from pay_park where pi_id=? and area_code=? and pp_state=0  and cancel_state=0  and is_del=0 and expect_state !=2";
      List<Pay_park> list = getMySelfService()
          .queryListT(sql, Pay_park.class, park_info.getPi_id(), park_info.getArea_code());
      if (list != null && list.size() > 0) {
        for (Pay_park pay_park : list) {
          try {
          /*if(pay_park.getExpect_state() == 2){
            //预约成功的排除
        		continue;
        	}  */
            pay_park.setCancel_state(2);//订单关闭状态0:没有关闭1：已经关闭2：订单异常
            int count = pay_parkDao.updateByKey(pay_park);
            if (count != 1) {
              //更新失败
              log.error("pay_parkDao.updateByKey is error pay_park.id=" + pay_park.getId());
            }
          } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("标记该停车场部分订单异常----cancel_state = 2 失败", e);
            return;
          }
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("标记该停车场部分订单异常----cancel_state = 2 upOrderFault() is error" + e.getMessage(), e);
    }
  }
/****************************下面是任务调度器处理的工具方法**************************************/
  /**
   * 从普通订单表里面获取 预约订单 超时的  进行扣款处理
   */
  public synchronized void upExpectOrderOutTime() {
    try {
      String sql = "select *  from pay_park where  pp_state=0  and cancel_state !=1   and order_type=1 and expect_state !=3 and expect_state !=5   and  unix_timestamp()-UNIX_TIMESTAMP(ctime)>expect_time*60 and arrive_time=ctime";
      List<Pay_park> list = getMySelfService().queryListT(sql, Pay_park.class);
      if (list != null && list.size() > 0) {
        //有预约超时的 订单
        for (Pay_park pay_park : list) {
          //处理扣款等
          try {
            //事物处理
            carTransaction.ExpectOrderCheckTask(pay_park);
          } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("事物处理 ExpectOrderCheckTask  预付款超时扣款失败", e);
            return;
          }
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("从普通订单表里面获取 预约订单 超时的  进行扣款处理 upExpectOrderOutTime() is error" + e.getMessage(), e);
    }
  }


  /**
   * 获取某用户对应得某绑定车牌还没有支付的订单
   */
  public List<Pay_park> selectAllPayParkBYcar_code(long ui_id, String car_code) {
    try {
      String sql = "select *  from pay_park where ui_id=? and car_code=? and pp_state=0  and  is_over=0 and cancel_state=0 order by ctime desc limit 1";
      return getMySelfService().queryListT(sql, Pay_park.class, ui_id, car_code);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("PayParkPB.selectAllPayParkBYcar_code 获取某用户对应得某绑定车牌还没有支付的订单错误", e);
    }
    return null;
  }

  /**
   * 处理订单状态变化推送-----改进版本
   */
  public void pushOrderSate(long ui_id, User_info user_info, String message, String title,
      Pay_park pay_park, Pay_month_park pay_month_park) {
    try {
      String uuid = null;
      if (user_info != null) {
        uuid = user_info.getUuid();
      }
      if (ui_id > 0) {
        user_info = user_infoDao.selectByKey(ui_id);
        if (user_info != null) {
          uuid = user_info.getUuid();
        }
      }
      if (uuid == null) {
        return;
      }
      /**
       * 这里进行推送
       */
      //订单预约状态变更 进行JPUSH推送
      JPushMessageBean jPushMessageBean = new JPushMessageBean();
      jPushMessageBean.setMessage(message);
      jPushMessageBean.setImgurl(Constants.JPUSH_LOGO);
      jPushMessageBean.setTitle(title);
      jPushMessageBean.setDate(new Date());
      if (pay_park != null) {
        jPushMessageBean.setType(3);
        jPushMessageBean.setMessageJson((JSON) JSON.toJSON(pay_park));
      }
      if (pay_month_park != null) {
        jPushMessageBean.setType(2);
        jPushMessageBean.setMessageJson((JSON) JSON.toJSON(pay_month_park));
      }
      asyncJpushTask.doAppJpush(jPushMessageBean, uuid);


    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("处理订单状态变化推送-----改进版本", e);
    }
  }

  /**
   * 处理租赁订单状态变化推送-----改进版本
   */
  public void pushRentOrderSate(String uuid, String message, String title,
      Pay_month_park pay_month_park) {
    try {
      if (uuid == null) {
        return;
      }
      /**
       * 这里进行推送
       */
      //订单预约状态变更 进行JPUSH推送
      JPushMessageBean jPushMessageBean = new JPushMessageBean();
      jPushMessageBean.setMessage(message);
      jPushMessageBean.setImgurl(Constants.JPUSH_LOGO);
      jPushMessageBean.setTitle(title);
      jPushMessageBean.setDate(new Date());
      jPushMessageBean.setType(2);
      if (pay_month_park != null) {
        jPushMessageBean.setMessageJson((JSON) JSON.toJSON(pay_month_park));
      }
      asyncJpushTask.doAppJpush(jPushMessageBean, uuid);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("处理订单状态变化推送pushRentOrderSate-----改进版本", e);
    }
  }

  /**
   * 处理临停订单状态变化推送-----改进版本
   */
  public void pushOrderSate(String uuid, String message, String title, Pay_park pay_park) {
    try {
      if (uuid == null) {
        return;
      }
      /**
       * 这里进行推送
       */
      //订单预约状态变更 进行JPUSH推送
      JPushMessageBean jPushMessageBean = new JPushMessageBean();
      jPushMessageBean.setMessage(message);
      jPushMessageBean.setImgurl(Constants.JPUSH_LOGO);
      jPushMessageBean.setTitle(title);
      jPushMessageBean.setDate(new Date());
      jPushMessageBean.setType(3);
      if (pay_park != null) {
        jPushMessageBean.setMessageJson((JSON) JSON.toJSON(pay_park));
      }
      asyncJpushTask.doAppJpush(jPushMessageBean, uuid);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("处理订单状态变化推送pushOrderSate-----改进版本", e);
    }
  }

  /**
   * 处理预约订单超时扣款
   */
  public void expectOrderOutTime(Pay_park pay_park) throws QzException {
    try {
      //获取用户信息
      //虚拟币比例1元比100 分
      User_info userinfo = user_infoDao.selectByKey(pay_park.getUi_id());
      if (userinfo == null) {
        return;
      }
      // 车位数变动
      Park_info park_info = park_infoDao
          .selectByKey(pay_park.getPi_id(), ReturnParkTableName(pay_park.getArea_code()));
      if (park_info == null) {
        //该停车场不存在
        return;
      }
      //停车场类型0：道闸停车场1：占道车场2：露天立体车库停车场
      if (park_info.getPark_type() == 0) {
        //道闸停车场
        switch (pay_park.getExpect_state()) {
          case 1: {//预约中------ 等待道闸停车场，处理该预约订单
            //预约中超时 且 车辆未到达 则解除绑定 并 关闭该预约订单
            if (pay_park.getIs_expect_deduct() == 0) {
              //解除锁定
              userinfo.setLock_expect_money(
                  userinfo.getLock_expect_money() - pay_park.getExpect_money() >= 0 ?
                      userinfo.getLock_expect_money() - pay_park.getExpect_money() : 0);
              int count = user_infoDao.updateByKey(userinfo);
              if (count < 1) {
                //更新失败
                throw new QzException("道闸停车场 调度器处理预约超时 失败");
              }
              //Is_expect_deduct 是否已经扣除预约超时钱0：已锁定扣款金额1：已经扣款成功2：已解绑锁定金额
              pay_park.setIs_expect_deduct(2);
            }
            pay_park.setIs_expect_outtime(1);//是否预约已经超时 0：未超时 1：已经超时
            pay_park.setExpect_state(3);//预约状态  1：预约中  2：预约成功  3：预约失败 4：取消预约中 5：取消预约成功 6：取消预约失败
            pay_park.setCancel_state(1);//关闭该订单
            //-------
            //更新预约订单状态
            pay_park.setUtime(new Date());//设置订单更新时间
            int count = pay_parkDao.updateByKey(pay_park);
            if (count == 1) {
              //处理成功
              return;
            } else {
              //处理失败
              log.error("处理预约订单超时扣款 失败");
              throw new QzException("处理预约订单超时扣款 失败");
            }

          }
          case 2: {//预约成功
            if (pay_park.getIs_expect_deduct() == 0) {
              //Is_expect_deduct 是否已经扣除预约超时钱0：已锁定扣款金额1：已经扣款成功2：已解绑锁定金额
              pay_park.setIs_expect_deduct(2);
              //解除锁定
              userinfo.setLock_expect_money(
                  userinfo.getLock_expect_money() - pay_park.getExpect_money() >= 0 ?
                      userinfo.getLock_expect_money() - pay_park.getExpect_money() : 0);
              //处理预约成功且超时扣款
              doExpectTimeOutMoney(pay_park, userinfo);
            }
          }
          ;
          break;
          case 3: {//预约失败----- 道闸停车场已经没有预约车位进行预约了

          }
          ;
          break;
          case 4: {//取消预约中
            if (pay_park.getIs_expect_deduct() == 0) {
              //Is_expect_deduct 是否已经扣除预约超时钱0：已锁定扣款金额1：已经扣款成功2：已解绑锁定金额
              pay_park.setIs_expect_deduct(2);
              //解除锁定
              userinfo.setLock_expect_money(
                  userinfo.getLock_expect_money() - pay_park.getExpect_money() >= 0 ?
                      userinfo.getLock_expect_money() - pay_park.getExpect_money() : 0);
              //处理预约成功且超时扣款
              doExpectTimeOutMoney(pay_park, userinfo);
            }
          }
          ;
          break;
          case 5: {//取消预约成功

          }
          ;
          break;
          case 6: {//取消预约失败---- 道闸停车场一些其它原因不允许 取消该订单

          }
          ;
          break;
          default:
            break;
        }

      } else {//1：占道车场2：露天立体车库停车场

        doExpectTimeOutMoney(pay_park, userinfo);

      }

    } catch (Exception e) {
      log.error(" UserTransaction.ExpectOrderCheckTask is error", e);
      throw new QzException("事物异常 ExpectOrderCheckTask", e);
    }
  }


  /**
   * 处理预约成功且超时扣款
   */
  public void doExpectTimeOutMoney(Pay_park pay_park, User_info userinfo) throws QzException {
    try {
      //获取用户信息
      //虚拟币比例1元比100 分
      if (userinfo.getUi_vc() - pay_park.getExpect_money() >= 0) {
        //虚拟币足够 且 是自动扣费
        userinfo.setUi_vc(userinfo.getUi_vc() - pay_park.getExpect_money()); //分
        int count = user_infoDao.updateByKey(userinfo);
        if (count < 1) {
          //更新用户扣款数据失败
          //这里需要抛出异常
          //扣款失败
          throw new QzException("扣款失败");
        } else {
          //扣款成功
          pay_park.setUtime(new Date());
          pay_park.setPay_source(4);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
          pay_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
          pay_park.setIs_expect_outtime(1);//是否预约已经超时 0：未超时 1：已经超时
          pay_park.setIs_expect_deduct(1);//是否已经扣除预约超时钱0：已锁定扣款金额1：已经扣款成功2：已解绑锁定金额
          pay_park.setIs_over(1);//订单是否完成或者逃逸(0:没有完成1：完成2：车辆逃逸3：未交费4：PDA补交欠费已完成5：PDA补交逃逸已完成)
          pay_park.setMoney(pay_park.getExpect_money());//单位 分
          count = pay_parkDao.updateByKey(pay_park);
          if (count < 1) {
            //扣款失败
            throw new QzException("扣款失败");
          }
          //记录用户账户变更
          ReturnDataNew returnData = new ReturnDataNew();
          String act_name = "预约超时扣款";
          userPB.recordVC(0, pay_park.getExpect_money(), pay_park.getMy_order(), 1,
              pay_park.getUi_id(), returnData, act_name, pay_park.getUi_nd(), pay_park.getUi_tel(),
              pay_park.getPay_source()
              , pay_park.getUpc_id(), pay_park.getDiscount_type(),
              (int) pay_park.getDiscount_money(), new Date());
          /**
           * 更新商户账户金额
           */
          userPB
              .upManchentMoney(pay_park.getPu_id(), pay_park.getPu_nd(), pay_park.getExpect_money(),
                  1);
          try {
            //4.	预约扣款完成：由于您未在规定时间内到达停车场，扣除XX元电子劵。
            /**
             * 这里进行推送
             */
            JPushMessageBean jPushMessageBean = new JPushMessageBean();
            jPushMessageBean.setType(0);
            jPushMessageBean.setMessage(
                "由于您未在规定时间内到达停车场，扣除" + formatDouble((pay_park.getExpect_money() * 1.0) / 100)
                    + "元人民币。");
            jPushMessageBean.setImgurl(Constants.JPUSH_LOGO);
            jPushMessageBean.setTitle("预约超时扣款");
            jPushMessageBean.setDate(new Date());
            asyncJpushTask.doAppJpush(jPushMessageBean, userinfo.getUuid());
          } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("预约超时扣款 推送出现错误", e);
          }

          //这里处理订单状态变更推送
          /**
           * 这里处理推送---预约订单超时扣款
           */
          String title = "系统消息";
          String message = "亲，你的订单【" + pay_park.getMy_order() + "】，已超时扣款成功。";
          pushOrderSate(userinfo.getUuid(), message, title, pay_park);
        }
      }
      // 车位数变动
      Park_info park_info = park_infoDao
          .selectByKey(pay_park.getPi_id(), ReturnParkTableName(pay_park.getArea_code()));
      if (park_info == null) {
        //该停车场不存在
        throw new QzException("该停车场不存在");
      }
      //这里更新车辆预约超时的数量变化
      if (park_info.getPark_type() == 1) {
        //占道停车场
        parkInfoPB.upCarNumExpect(park_info, 3);
      }

    } catch (Exception e) {
      log.error(" UserTransaction.ExpectOrderCheckTask is error", e);
      throw new QzException("事物异常 ExpectOrderCheckTask", e);
    }
  }


  /**
   * 车辆入库的时候 计费 状态设定 等细则进行事物处理
   */
  public void doCar_In(int dtype, String order_id, Car_in_out car_in_out, Park_info park_info,
      ReturnDataNew returnData,
      String gov_num)
      throws QzException {

    try {
      String area_code = park_info.getArea_code();
      Date date = car_in_out.getCtime();
      long ui_id = car_in_out.getUi_id();
      long pi_id = car_in_out.getPi_id();
      String car_code = car_in_out.getCar_code();
      //入库之前先检查该用户对该车辆进行该停车场预约下单没有   如果已经进行了预约下单则直接更新对应得数据
      if (ui_id > 0) {
        User_info user_info = user_infoDao.selectByKey(ui_id);
        //有用户注册了  检查是否已经进行了预约下单
        Pay_park pay_park = QueryPayPark(ui_id, pi_id, car_code, 1, area_code);
        if (pay_park == null
            || pay_park.getExpect_state() == 1 //预约中
            || pay_park.getExpect_state() == 3 //预约失败
            || pay_park.getExpect_state() == 5 //取消预约成功
            ) {
          //没有预约下单  摄像头扫描到后进行普通下单
          pay_park = cameraCarOrder(dtype, order_id, car_in_out, park_info, gov_num);
        } else {
          //预约下单  判断当前为止   当前时间减去预约时间 是否已经超过了60分钟  如果超过则预约扣款  如果没有超过则不进行扣款
          if (date.getTime() - pay_park.getCtime().getTime()
              <= pay_park.getExpect_time() * 60 * 1000) {
            //预约未超时
            //获取该停车场的计费规则 rcr_type ：停车类型 0：普通车位停车 1：时间段包月停车
            Rental_charging_rule charging_rule = queryChargeRule(car_in_out.getPi_id(), 0,
                car_in_out.getCar_type(), area_code, car_in_out.getCar_code_color());
            if (charging_rule == null) {
              //没有获取到停车规则，获取默认规则，如果默认规则还没有获取到，抛出异常   by zzy 2017-06-29
              charging_rule = queryChargeDefaultRule(car_in_out.getPi_id(),0,car_in_out.getArea_code());
              if(null == charging_rule){
                log.info("没有获取到停车场规则，获取停车场默认规则，获取默认规则失败 " +
                        "pi_id:{}，area_code:{},car_type:{},car_code_color:{}",car_in_out.getPi_id(),
                        car_in_out.getArea_code(),car_in_out.getCar_type(),car_in_out.getCar_code_color());
                //下单失败
                throw new QzException("预约未超时--无对应的停车场规则信息");
              }
            }
            //预约到场时间在规定时间内 则不扣预约费
            if (car_in_out.is_sync == 1) {
              pay_park.setCancel_state(3);
            }
            pay_park.setGov_num(gov_num);
            pay_park.setMagnetic_state(1);
            pay_park.setArrive_time(date);//预约到场时间
            pay_park.setStart_price(charging_rule.getStart_price());//起步价格（单位 分）
            pay_park.setStart_time(charging_rule.getStart_time());//起步时长（分钟）
            pay_park.setCharging(charging_rule.getCharging());//计费价格(单位 分)
            pay_park.setCharging_time(charging_rule.getCharging_time());//计费时长(分钟)

            pay_park.setFree_minute(charging_rule.getFree_minute());//多少分钟之内进出免费
            pay_park
                .setIs_free_minute(charging_rule.getIs_free_minute());//多少分钟之内进出免费是否开启  0:不开启  1：开启
            if (pay_park.getExpect_state() == 1) {
              //设置预约状态
              pay_park.setExpect_state(2);//预约状态  1：预约中  2：预约成功  3：预约失败 4：取消预约中 5：取消预约成功 6：取消预约失败
            }
            //这里更新预约车辆入库的数量变化
            if (park_info.getPark_type() == 1) {
              //占道停车场
              parkInfoPB.upCarNumExpect(park_info, 4);
            }

            //by zzy 2017-6-26 设置 是否已经扣除预约超时钱
            pay_park.setIs_expect_deduct(2); //0：已锁定扣款金额1：已经扣款成功2：已解绑锁定金额

            //解除锁定预约金额
            if (user_info != null) {
              //解除锁定
              user_info.setLock_expect_money(
                  user_info.getLock_expect_money() - pay_park.getExpect_money() >= 0 ?
                      user_info.getLock_expect_money() - pay_park.getExpect_money() : 0);
              int count = user_infoDao.updateByKey(user_info);
              if (count != 1) {
                throw new QzException("预约成功车辆未超时到场，解除锁定失败");
              }
            }

          } else {
            //预约超时且扣款后  进行入库  新生成一个临停订单
            cameraCarOrder(dtype, order_id, car_in_out, park_info, gov_num);
          }

          //设置车辆进入时候的 订单
          if (RequestUtil.checkObjectBlank(car_in_out.getOrder_id())) {
            car_in_out.setOrder_id(pay_park.getMy_order());
          }
          //预约订单 且 未超时到达
          /**
           * 这里处理随机立减免活动事件
           */
          activityPB.record_random(pay_park);

          int count = pay_parkDao.updateByKey(pay_park);
          if (count < 1) {
            //扣款失败
            throw new QzException("扣款失败");
          }
        }

        //这里处理绑定了用户的车辆--当用户钱包余额不足10元钱的时候给予推送提醒
        //这里处理订单状态变更推送----车辆临停到达
        /**
         * 这里处理推送---车辆临停到达
         */
        if (user_info != null) {

          String title = "系统消息";
          String message =
              "亲，你的车牌号为【" + pay_park.getCar_code() + "】的车辆已到达【" + pay_park.getPi_name() + "】停车场";
          //if (user_info.getUi_vc() < 1000) {
          //by zzy 2017-6-23 判断用户钱包余额不足10元钱 需要扣除用户预约锁定金额
          if(user_info.getUi_vc() - user_info.getLock_expect_money() < 1000){
            //如果钱包金额小于10元钱
            message += ",您钱包余额已经不足10元钱";
            if (user_info.getUi_autopay() == 0) {
              //没有开启自动支付
              message += ",且没有开启自动支付功能。";
            }
          } else {
            if (user_info.getUi_autopay() == 0) {
              //没有开启自动支付
              message += ",您还没有开启自动支付功能哦!";
            }
          }

          pushOrderSate(pay_park.getUi_nd(), message, title, pay_park);
        }


      } else {
        //该用户还没有注册   也要记录该车辆的下单情况
        /**
         * 摄像头扫描到下单
         */
        cameraCarOrder(dtype, order_id, car_in_out, park_info, gov_num);
      }


    } catch (Exception e) {
      throw new QzException("事物异常 doCar_In", e);
    }
  }

  /**
   * 车辆出库的时候 计费 状态设定 等细则进行事物处理
   */
  public void doCar_Out(String order_id, Car_in_out car_in_out, Park_info park_info,
      ReturnDataNew returnData)
      throws QzException {

    try {
      String area_code = park_info.getArea_code();
      long ui_id = car_in_out.getUi_id();
      long pi_id = car_in_out.getPi_id();
      String car_code = car_in_out.getCar_code();
      //出库
      //获取用户已经支付的最后一条某停车场的订单
      Pay_park pay_park;
      if (StringUtils.hasText(order_id)) {
        pay_park = selectOnePayPark(order_id);
      } else {
        pay_park = QueryPayParkOverPay(ui_id, pi_id, car_code, area_code);
      }
      if (pay_park != null) {
        Date date = car_in_out.getCtime();
        pay_park.setLeave_time(date);
        if (pay_park.getIs_over() == 0) {
          pay_park.setIs_over(1);//订单是否完成(0:没有完成1：完成)
        }
        //pay_park.setMagnetic_state(2);     //by zzy 2017-6-23  出库时无需将智能磁场入库出库状态更新为 已出库

        //更新离开时间
        int count = pay_parkDao.updateByKey(pay_park);
        if (count < 1) {
          //更新离开时间失败
          throw new QzException("更新离开时间失败");
        }
        //设置车辆进入时候的 订单
        if (RequestUtil.checkObjectBlank(car_in_out.getOrder_id())) {
          car_in_out.setOrder_id(pay_park.getMy_order());
        }
      }
    } catch (Exception e) {
      throw new QzException("事物异常 doCar_Out", e);
    }
  }


  /**
   * 获取停车场所有的未支付的且未关闭的订单
   */
  public List<Pay_park> selectAllByPiID(Park_info park_info) {
    try {
      String sql = "select *  from pay_park where pi_id=? and area_code=? and pp_state=0  and  is_over=0 and cancel_state=0";
      return getMySelfService()
          .queryListT(sql, Pay_park.class, park_info.getPi_id(), park_info.getArea_code());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("PayParkPB.selectAllByPiID 获取停车场所有的未支付的且未关闭的订单", e);
    }
    return null;
  }

  @Autowired
  private JpushPB jpushPB;
  @Autowired
  private Pda_owe_order_coverDao pda_owe_order_coverDao;

  /**
   * 记录订单补缴记录
   *
   * @param pay_park 补缴的订单
   * @param now 当前缴费停车场,null 表示未改变
   * @param date 补缴时间
   */
  public void recorde_pda_owe_order_cover(Pay_park pay_park, Park_info now,
      Date date)
      throws QzException {
    try {
      Park_info old = returnParkInfo(pay_park.pi_id, pay_park.area_code);
      if (now == null) {
        now = old;
      }
      Pda_owe_order_cover pda_owe_order_cover = new Pda_owe_order_cover();
      String car_code = pay_park.getCar_code();
      pda_owe_order_cover.setCar_code(car_code);
      pda_owe_order_cover.setCtime(date);
      pda_owe_order_cover.setMoney(pay_park.getMoney());
      pda_owe_order_cover.setOrder_id(pay_park.getMy_order());
      pda_owe_order_cover.setNow_area_code(now.getArea_code());
      pda_owe_order_cover.setNow_mac(now.getMac());
      pda_owe_order_cover.setNow_pi_id(now.getPi_id());
      pda_owe_order_cover.setNow_pu_id(now.getPu_id());
      pda_owe_order_cover.setNow_pu_nd(now.getPu_nd());
      pda_owe_order_cover.setPay_source(pay_park.getPay_source());
//    {//查询车牌当前绑定的用户
//      User_carcode user_carcode = user_carcodeDao
//          .queryUniqueT("select * from user_carcode where car_code=? LIMIT 1",
//              User_carcode.class, car_code);
//      if (user_carcode != null) {
//      pda_owe_order_cover.setNow_ui_id(user_carcode.getUi_id());
//      pda_owe_order_cover.setNow_uuid(user_carcode.getUi_nd());
//      }
//    }

      pda_owe_order_cover.setOld_area_code(old.getArea_code());
      pda_owe_order_cover.setOld_mac(old.getMac());
      pda_owe_order_cover.setOld_pi_id(old.getPi_id());
      pda_owe_order_cover.setOld_pu_id(old.getPu_id());
      pda_owe_order_cover.setOld_pu_nd(old.getPu_nd());
      pda_owe_order_cover.setOld_ui_id(pay_park.getUi_id());
      pda_owe_order_cover.setOld_uuid(pay_park.getUi_nd());

      pda_owe_order_cover.setPooc_nd(returnUUID());
      pda_owe_order_cover.setState(1);
      pda_owe_order_cover.setUtime(date);

      pda_owe_order_coverDao.insert(pda_owe_order_cover);
    } catch (Exception e) {
      log.error(" 记录订单补缴记录失败", e);
      throw new QzException("记录订单补缴记录事物异常", e);
    }

  }

  /**
   * PDA用户自动扣款
   */
  public void pda_sure(Pay_park pay_park, Date date) throws QzException {
      //获取用户信息
      if (pay_park.getUi_id() < 1) {
        throw new QzException("用户不存在");
      }
      //虚拟币比例1元比100 分
      User_info userinfo = user_infoDao.selectByKey(pay_park.getUi_id());
      if (userinfo == null) {
        throw new QzException("用户不存在");
      }
      //检查是否开启了自动扣款
      if (userinfo.getUi_autopay() == 0) {
        //没有开启自动扣款
        throw new QzException("没有开启自动扣款");
      }
      int money = pay_park.getMoney();

      //默认选择抵扣最大的优惠券
      User_park_coupon user_park_coupon = parkCouponPB
          .ReturnMaxMoneyCoupon(pay_park.getUi_id(), money);
      money = parkCouponPB.doCouponOrder(pay_park, user_park_coupon, money);

      if (money == 0) {
        //当代金券完全抵扣金额的情况
        pay_park.setUtime(date);//
        //设置是否刷新离开时间
        SetLeaveTime(pay_park, date);

        pay_park.setIs_cash(0);//是否现金支付0：在线支付1：现金支付
        setIs_over(pay_park, pay_park, date);

        pay_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
        int count = pay_parkDao.updateByKey(pay_park);
        if (count < 1) {
          //扣款失败
          throw new QzException("扣款失败");
        }
        //更新商户金额
        /**
         * 更新商户账户金额
         */
        //userPB.upManchentMoney(pay_park.getPu_id(), pay_park.getPu_nd(), pay_park.getMoney(), 1);

        //记录用户账户变更
        ReturnDataNew returnData = new ReturnDataNew();
        String act_name = "PDA钱包支付";
        userPB.recordVC(0, money, pay_park.getMy_order(), 0, pay_park.getUi_id(),
            returnData, act_name, pay_park.getUi_nd(), pay_park.getUi_tel(),
            pay_park.getPay_source()
            , pay_park.getUpc_id(), pay_park.getDiscount_type(),
            (int) pay_park.getDiscount_money(), date);

      } else {
        //钱包金额判断
        //if (userinfo.getUi_vc() - money < 0) {
        //by zzy 2017-6-23 判断余额是否不足时增加预约锁定金额（（用户余额 - 预约锁定金额 - 支付金额）> 0 ）
        if(isNotSureMoney(userinfo,money)){
          //抛出钱包金额不足的异常
          String title = "系统消息";
          String message = "亲，你的订单【" + pay_park.getMy_order() + "】，因钱包余额不足,自动扣款失败。";
          asyncJpushTask.pushOrderNoMoney(userinfo.getUuid(), message, title, pay_park);

          Park_info park_info = returnParkInfo(pay_park.getPi_id(), pay_park.getArea_code());
          jpushPB.pushPDAOrderNoMoney(park_info.getMac(),
              "订单【" + pay_park.getMy_order() + "】，因钱包余额不足,自动扣款失败。", title, pay_park);

          throw new QzException("扣款钱包金额不足");
        }
        //虚拟币足够 且 是自动扣费
        userinfo.setUi_vc(userinfo.getUi_vc() - money); //分
        int count = user_infoDao.updateByKey(userinfo);
        if (count < 1) {
          //更新用户扣款数据失败
          //这里需要抛出异常
          //扣款失败
          throw new QzException("扣款失败");
        } else {
          pay_park.setUtime(date);
          pay_park.setIs_cash(0);//是否现金支付0：在线支付1：现金支付
          SetLeaveTime(pay_park, date);
          setIs_over(pay_park, pay_park, date);
          pay_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
          count = pay_parkDao.updateByKey(pay_park);
          if (count < 1) {
            //扣款失败
            throw new QzException("扣款失败");
          }
          //记录用户账户变更
          ReturnDataNew returnData = new ReturnDataNew();
          String act_name = "PDA钱包支付";
          userPB.recordVC(0, money, pay_park.getMy_order(), 0, pay_park.getUi_id(),
              returnData, act_name, pay_park.getUi_nd(), pay_park.getUi_tel(),
              pay_park.getPay_source()
              , pay_park.getUpc_id(), pay_park.getDiscount_type(),
              (int) pay_park.getDiscount_money(), date);
        }
      }
      /**
       * 更新优惠券使用状态
       */
//      if (user_park_coupon != null) {
//        //如果使用了优惠券 那么需要更改优惠券的使用状态
//        User_park_coupon user_park_coupon1 = parkCouponPB
//            .upUserParkCouponState(user_park_coupon.getUpc_id());
//        if (user_park_coupon1 == null) {
//          //更新失败
//          throw new QzException("record_user_vc_act 优惠券更新使用状态失败");
//        } else {
//          asyncJpushTask.pushUseCouponMsg(pay_park.getUi_nd(), user_park_coupon);
//        }
//      }
  }

  /**
   * 设置订单完成状态
   *
   * @param pay_park 要补缴的订单
   * @param nowPayPark 当前订单
   * @param date 时间  @throws QzException QzException
   */
  public void setIs_over(Pay_park pay_park, Pay_park nowPayPark, Date date) throws QzException {
    //0:没有完成 1：完成 2：车辆逃逸  3：未交费  4：PDA 补交欠费已完成  5：PDA补交逃逸已完成);
    Park_info now = null;
    if (nowPayPark.pi_id != pay_park.pi_id || nowPayPark.area_code != pay_park.area_code) {
      now = returnParkInfo(nowPayPark.pi_id, nowPayPark.area_code);
    }
    switch (pay_park.getIs_over()) {
      case 2:
        pay_park.setIs_over(5);
        recorde_pda_owe_order_cover(pay_park, now, date);
        break;
      case 3:
        pay_park.setIs_over(4);
        recorde_pda_owe_order_cover(pay_park, now, date);
        break;
      default:
        pay_park.setIs_over(1);
        break;
    }
  }

  /**
   * 设置订单完成状态
   *
   * @param pay_park 要补缴的订单
   * @param nowPayPark 当前订单
   * @param date 时间  @throws QzException QzException
   */
  public void setEscapeIs_over(Pay_park pay_park, Pay_park nowPayPark, Date date)
      throws QzException {
    //0:没有完成 1：完成 2：车辆逃逸  3：未交费  4：PDA 补交欠费已完成  5：PDA补交逃逸已完成);
    Park_info now = null;
    if (nowPayPark.pi_id != pay_park.pi_id || nowPayPark.area_code != pay_park.area_code) {
      now = returnParkInfo(nowPayPark.pi_id, nowPayPark.area_code);
    }
    switch (pay_park.getIs_over()) {
      case 2:
        pay_park.setIs_over(5);
        break;
      default:
        pay_park.setIs_over(4);
        break;
    }
    recorde_pda_owe_order_cover(pay_park, now, date);
  }

  //by jxh 2017-2-27

  /**
   * 通过PDA占道停车订单 从第三方支付回调表查询是否支付成功
   */
  public User_pay findByOrderid(String orderid) {
    try {
      String sql = "SELECT * FROM user_pay WHERE car_order_id=? LIMIT 1";
      return getMySelfService().queryUniqueT(sql, User_pay.class, orderid);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("findByOrderid is error", e);
    }
    return null;
  }
  //by jxh 2017-2-27

  /**
   * 通过PDA占道停车订单 从第三方支付回调表查询是否支付成功
   *
   * @return true:成功 false：失败
   */
  public User_pay isCallPay(String orderid) {
    try {
      String sql = "SELECT * FROM user_pay WHERE car_order_id=? AND state=1 LIMIT 1";
      User_pay user_pay = getMySelfService().queryUniqueT(sql, User_pay.class, orderid);
      if (user_pay != null) {
        return user_pay;
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("isCallPay is error", e);
    }
    return null;
  }

  /**
   * 设置 PDA刷新 车辆离开时间
   */
  public void SetLeaveTime(Pay_park pay_park, Date date) {
    if (pay_park.getIs_over() != 2 && pay_park.getIs_over() != 3) {
      //订单是否完成或者逃逸(0:没有完成1：完成2：车辆逃逸3：未交费4：PDA补交欠费已完成5：PDA补交逃逸已完成)
      pay_park.setLeave_time(date);//离开时间
    }
  }
}
