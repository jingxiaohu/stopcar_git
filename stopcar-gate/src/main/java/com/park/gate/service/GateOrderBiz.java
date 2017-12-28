package com.park.gate.service;

import com.alibaba.fastjson.JSONObject;
import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.*;
import com.park.gate.action.v1.order.param.Param_TemporaryOrder;
import com.park.gate.transaction.GateCarTransaction;
import com.park.gate.transaction.GateUserTransaction;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.PayMonthParkPB;
import com.park.mvc.service.common.PayParkPB;
import com.park.mvc.service.common.UserPB;
import com.park.transaction.CarTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理用户订单管理
 *
 * @author jingxiaohu
 */
@Service
public class GateOrderBiz extends BaseBiz {

  @Resource
  private GateUserTransaction gateUserTransaction;
  @Resource
  private GateCarTransaction gateCarTransaction;
  @Autowired
  protected GateCarBiz gateCarBiz;
  @Autowired
  protected PayParkPB payParkPB;
  @Autowired
  protected PayMonthParkPB payMonthParkPB;
  @Autowired
  private UserPB userPB;

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
   * 道闸停车场的更新用户现金支付状态和金额
   */
  public void pay_sure(ReturnDataNew returnData, int dtype, String orderid,
      long pi_id, String area_code, int money, int type, boolean is_sync, Long sync_time) {

    try {
      //首先判断是否已经进行了录入  如果数据库中没有该停车场的数据则不进行插入  否则不予处理
      Pay_park pay_park = payParkPB.selectOnePayPark(orderid);// QueryByOrderId(orderid);
      if (pay_park == null) {
        returnData.setReturnData(errorcode_data, "该订单不存在", "");
        return;
      } else {
        if (pay_park.getPp_state() == 1) {
          //返回结果
          //道闸在线支付服务端成功，本地未处理成功，本地现金支付，防止反复调pay_sure 返回码改为errorcode_success
          returnData.setReturnData(errorcode_success, "该订单已经支付过了", "");
          return;
        }
      }
      pay_park.setMoney(money);
      //现金支付
      Date date;
      if (is_sync) {
        pay_park.setCancel_state(3);
        date = new Date(sync_time);
      } else {
        date = new Date();
      }
      pay_park.setPp_state(1);//设置已经支付
      pay_park.setUtime(date);//设置更新时间
      pay_park.setLeave_time(date);//离开时间
      //type :                0:常规类型  1：免费分钟类型 2:免费车类型 3：包月车类型 4：租赁车类型
      //is_cash:  0：在线支付  1：现金支付 2：免费支付 3:免费车类型  4：包月车类型   5：租赁车类型';
      switch (type) {
        case 0:
          pay_park.setIs_cash(1);//是否现金支付0：在线支付1：现金支付
          if (money == 0 && !is_sync) {
            //表示道闸 手动开闸---手机APP订单应该显示异常
            pay_park.setCancel_state(2);// 订单关闭状态0:没有关闭1：已经关闭2：订单异常
          }
          break;
        case 1:
          pay_park.setIs_free_minute(1);//多少分钟之内进出免费是否开启  0:不开启  1：开启
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
      }
      pay_park.setIs_over(1);// 订单是否完成或者逃逸(0:没有完成1：完成2：车辆逃逸3：未交费)

      /**
       * by jxh 2017-3-16  如果是现金支付则 把订单已经写入的代金券和优惠金额 清零
       */
      pay_park.setUpc_id(0);
      pay_park.setDiscount_money(0);
      pay_park.setDiscount_name("");
      pay_park.setDiscount_type(0);
      pay_park.setAi_id(0);
      pay_park.setAi_effect(0);
      pay_park.setAi_money(0);

      //把该订单的支付类型设置为 0 表示未知支付类型或者是现金
      pay_park.setPay_source(0);
      int count = pay_parkDao.updateByKey(pay_park);
      if (count == 1) {
        //这里处理订单状态变更推送---现金支付
        if (money > 0) {
          /**
           * 这里处理推送---现金支付
           */
          String title = "系统消息";
          String message = "亲，你的订单【" + pay_park.getMy_order() + "】已现金支付成功。";
          payParkPB.pushOrderSate(pay_park.getUi_nd(), message, title, pay_park);
        }
        //返回结果
        returnData.setReturnData(errorcode_success, "处理成功", "");
        return;
      } else {
        //返回结果
        returnData.setReturnData(errorcode_data, "处理失败", "", "1");
        return;
      }

    } catch (Exception e) {
      log.error("ParkinfoBiz pay_sure is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 道闸停车场的更新用户现金和在线支付的支付状态和金额
   * 处理客户端对订单支付的确认信息记录，同时记录异常订单信息
   * 新增支付类型参数is_cash(0：在线支付  1：现金支付)
   */
  public JSONObject pay_sure_new(ReturnDataNew returnData, int dtype, String orderid,
                          long pi_id, String area_code, int money, int type, int is_sync, Long sync_time, Integer is_cash) {

    JSONObject returnJson = new JSONObject();
    try {
      //首先判断是否已经进行了录入  如果数据库中没有该停车场的数据则不进行插入  否则不予处理
      Pay_park pay_park = payParkPB.selectOnePayPark(orderid);// QueryByOrderId(orderid);
      if (pay_park == null) {
        returnData.setReturnData(errorcode_data, "该订单不存在", "");
        returnJson.put("returnData", returnData);
        return returnJson;
      }/* else {
        if (pay_park.getPp_state() == 1) {
          //返回结果
          //道闸在线支付服务端成功，本地未处理成功，本地现金支付，防止反复调pay_sure 返回码改为errorcode_success
          returnData.setReturnData(errorcode_success, "该订单已经支付过了", "");
          return;
        }
      }*/
      pay_park.setMoney(money);
      //现金/线上支付
      Date date;
      if (is_sync==1) {
        pay_park.setCancel_state(3);
        date = new Date(sync_time);
        pay_park.setClose_time(sync_time);//结算时间 （异步传输的采用客户端时间  同步传送的用服务器时间）
      } else {
        date = new Date();
        pay_park.setClose_time(System.currentTimeMillis());
      }
      returnJson.put("utime",date.getTime());//设置订单确认更新时间

      pay_park.setUtime(date);//设置更新时间
      pay_park.setLeave_time(date);//离开时间

      //type :                0:常规类型  1：免费分钟类型 2:免费车类型 3：包月车类型 4：租赁车类型
      //is_cash:  0：在线支付  1：现金支付 2：免费支付 3:免费车类型  4：包月车类型   5：租赁车类型';
      switch (type) {
        case 0:
          pay_park.setIs_cash(is_cash);//是否现金支付0：在线支付1：现金支付  by zyy 20171102修改

          //订单支付类型道闸为现金支付、服务端为线上支付---订单异常
          if(pay_park.getPp_state() == 1 && (is_cash == 1 && pay_park.getPay_source() != 0)){
           //异常订单日志记录
            Order_abnormal_log order_abnormal_log = new Order_abnormal_log();
            order_abnormal_log.setOrder_id(orderid);
            order_abnormal_log.setServer_type(pay_park.getPay_source());
            order_abnormal_log.setClient_type(0);
            order_abnormal_log.setMoney(money);
            order_abnormal_log.setCtime(date);
            order_abnormal_log.setPi_id(pi_id);
            order_abnormal_log.setArea_code(area_code);
            //获取停车场信息
            Park_info park_info = returnParkInfo(pi_id, area_code);
            if (park_info != null) {
              order_abnormal_log.setPi_name(park_info.getPi_name());
            }
            order_abnormal_log.setIs_asyn(is_sync);
            order_abnormal_log.setNote("服务器与客户端对订单的支付类型不一致");
            int id = order_abnormal_logDao.insert(order_abnormal_log);
            order_abnormal_log.setId(id);

            pay_park.setCancel_state(2);// 订单关闭状态0:没有关闭1：已经关闭2：订单异常
            pay_park.setPay_source(0);
          }
          break;
        case 1:
          pay_park.setIs_free_minute(1);//多少分钟之内进出免费是否开启  0:不开启  1：开启
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
      }
      pay_park.setIs_over(1);// 订单是否完成或者逃逸(0:没有完成1：完成2：车辆逃逸3：未交费)
      pay_park.setPp_state(1);//设置已经支付
      pay_park.setSure_type(1);//订单结算确认状态（0：未结算确认  1：客户端已支付确认）

      /**
       * by jxh 2017-3-16  如果是现金支付则 把订单已经写入的代金券和优惠金额 清零
       */
      pay_park.setUpc_id(0);
      pay_park.setDiscount_money(0);
      pay_park.setDiscount_name("");
      pay_park.setDiscount_type(0);
      pay_park.setAi_id(0);
      pay_park.setAi_effect(0);
      pay_park.setAi_money(0);

      //把该订单的支付类型设置为 0 表示未知支付类型或者是现金
//      pay_park.setPay_source(0);    //by zyy 20171103 此接口处理客户端线上/线下(现金)支付确认
      int count = pay_parkDao.updateByKey(pay_park);
      if (count == 1) {
        //这里处理订单状态变更推送---现金支付
        if (money > 0) {
          /**
           * 这里处理推送---现金支付
           */
          String title = "系统消息";
          String message = "亲，你的订单【" + pay_park.getMy_order() + "】已支付成功。";
          payParkPB.pushOrderSate(pay_park.getUi_nd(), message, title, pay_park);
        }
        //返回结果
        returnData.setReturnData(errorcode_success, "处理成功", "");
      } else {
        //返回结果
        returnData.setReturnData(errorcode_data, "处理失败", "", "1");
      }

    } catch (Exception e) {
      log.error("ParkinfoBiz pay_sure_new is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
    returnJson.put("returnData", returnData);
    return returnJson;
  }

  /**
   * 检查该车牌号是否是预约车
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void check_expectcar(ReturnDataNew returnData, String area_code,
      long pi_id, String car_code, int car_type, int car_code_color) {

    try {
      JSONObject obj = new JSONObject();
      //首先判断是否已经进行了录入  如果数据库中没有该停车场的数据则不进行插入  否则不予处理
      //获取该场地的信息
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        returnData.setReturnData(errorcode_data, "该停车场不存在", "");
        return;
      }
      /**
       * 检查该车牌号 该次订单是否已经支付 如果已经在线支付了 那么就只记录该次订单的车辆已经开闸出库和开闸时间
       * 如果没有在线支付 那么就是现金支付 则更改支付状态为已经支付
       */
      //获取订单信息
      String sql = "SELECT *  FROM pay_park WHERE   pi_id = ? AND area_code=? AND car_code=? AND order_type=1 AND pp_state=0  AND is_del=0 AND cancel_state=0 AND  unix_timestamp()-UNIX_TIMESTAMP(ctime) <= expect_time*60 AND expect_state=2 ORDER BY  ctime DESC LIMIT 1";
      Pay_park pay_park = getMySelfService()
          .queryUniqueT(sql, Pay_park.class, pi_id, area_code, car_code);

      if (pay_park != null) {
        //预约成功且未超时
        obj.put("type", 1);//0:临停车 1:预约车 2：租赁车
        obj.put("expect_state", 2);//1:预约超时 2：预约未超时
        obj.put("rent_state", 0);//1:租期超时 2：租期未超时  3：不在租期时间段内入场
        obj.put("orderid", pay_park.getMy_order());
        returnData.setReturnData(errorcode_success, "获取成功", obj);
        return;
      }

      //获取租赁订单信息
      sql = "SELECT *  FROM pay_month_park WHERE pi_id = ? AND area_code=? AND car_code=?  AND pp_state=1 AND  UNIX_TIMESTAMP(end_time) - unix_timestamp() >= 0 AND rent_state=2  ORDER BY  ctime DESC LIMIT 1";
      Pay_month_park pay_month_park = getMySelfService()
          .queryUniqueT(sql, Pay_month_park.class, pi_id, area_code, car_code);
      if (pay_month_park != null) {
        obj.put("type", 2);//0:临停车 1:预约车 2：租赁车
        obj.put("expect_state", 0);//1:预约超时 2：预约未超时
        if (pay_month_park.getPermit_time() == null) {

        }
        obj.put("rent_state", 2);//1:租期超时 2：租期未超时  3：不在租期时间段内入场
        obj.put("orderid", pay_month_park.getMy_order());
        returnData.setReturnData(errorcode_success, "获取成功", obj);
        return;
      }

      //返回结果
      obj.put("type", 0);//0:临停车 1:预约车 2：租赁车
      obj.put("expect_state", 0);//1:预约超时 2：预约未超时
      obj.put("rent_state", 0);//1:租期超时 2：租期未超时  3：不在租期时间段内入场
      obj.put("orderid", "");
      returnData.setReturnData(errorcode_success, "获取成功", obj);
      return;

    } catch (Exception e) {
      log.error("ParkinfoBiz check_expectcar is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


  /**
   * 扫描到车辆出库扣费
   *
   * @param is_rent 是否是租赁车辆 0:不是 1：是
   */
  public void payment(ReturnDataNew returnData, int dtype, String orderid, int is_rent,
      long pi_id, String car_code, String area_code, int money, int final_time) {

    try {
      Date date = new Date();
      //用户ID 通过车牌号获取用户ID
      long ui_id = 0;
      //出库 扣款
      /**
       * 计算出库的账单结算金额
       * 两种情况 第一种 用户开启了自动扣款 那么就进行自动扣款
       * 第二种 用户没有开启自动扣款 那么就只需要计算出来费用然后等待 用户手动去支付
       */
      int total_money = 0;//计费总金额
      //临停车辆
      //获取用户还没有支付的最后一条某停车场的订单
      Pay_park pay_park = payParkPB.selectOnePayPark(orderid);
      if (pay_park == null) {
        returnData.setReturnData(errorcode_data, "车辆不存在", "");
        return;
      }
      if (pay_park.getPp_state() == 1) {
        returnData.setReturnData(errorcode_data, "亲，你已经交过费了", "", "1");
        return;
      }

      /**
       * 检查订单当前状态 是否已经订单异常 如果订单异常 则不进行在线扣费  全部走离线扣费
       */
      if (pay_park.getCancel_state() == 2) {
        returnData.setReturnData(errorcode_data, "该订单是异常订单，请走线下扣费流程", "", "2");
        return;
      }

      ui_id = pay_park.getUi_id();
      if (ui_id < 1) {
        //本地的临停车辆 没有在APP上面进行注册绑定车牌号
        returnData.setReturnData(errorcode_data, "本地的临停车辆 没有在APP上面进行注册绑定车牌号", "", "3");
        return;
      }
      //by jxh 2017-4-27 郑虎那边需要知道扣款失败的信息
      JSONObject obj = new JSONObject();
      int flag = 0;// 1: 自动支付失败
      if (pay_park != null) {
        int is_free = 0; //:0 收费  1：免费
        //是否具有 多少分钟之内进出免费是否开启  0:不开启  1：开启
        if (pay_park.getIs_free_minute() == 1
            && date.getTime() - pay_park.getCtime().getTime() <= pay_park.getFree_minute()) {
          //开启 且 pay_park.getFree_minute() 分钟内 可以免费进出
          is_free = 1;
        }
        if (pay_park.getOrder_type() == 2) {
          //表示 租赁产生的  临停费用
          total_money = pay_park.getMoney();
        } else {
          //计算当前应付金额
//          if (money == 0 && final_time == 0) {
//            total_money = payParkPB.returnCarMoney(pay_park);
//          } else {
//            //这里设置郑虎道闸计算的金额
//            total_money = money;
//            pay_park.setMoney(total_money);
//            pay_park.setFinal_time(final_time);
//          }
          //by zzy 2016-6-22 道闸支付金额使用客户端上传的金额
          //这里设置郑虎道闸计算的金额
            total_money = money;
            pay_park.setMoney(total_money);
            pay_park.setFinal_time(final_time);

        }
        //这里查看用户是自动扣费还是手动扣费
        //虚拟币比例1元比100 分
        User_info userinfo = user_infoDao.selectByKey(ui_id);
        if (userinfo == null) {
          returnData.setReturnData(errorcode_data, "用户不存在", "", "4");
          return;
        }
        /**
         * 道闸停车  摄像头扫描到车辆  需要记录扫描时间点
         */
        pay_park.setScan_time(date);
        int count = pay_parkDao.updateByKey(pay_park);
        if (count != 1) {
          //更新失败
          returnData.setReturnData(errorcode_data, "请求失败", "");
          return;
        }
        try {
          //事物处理  该车辆出入的细节
          gateCarTransaction
              .payMent(pay_park, userinfo, total_money, returnData, date, is_free);
          if (errorcode_data.equalsIgnoreCase(returnData.getErrorno())) {
            //有错误信息
            flag = 1;
            if("6".equalsIgnoreCase(returnData.getErrorcode())){
              //by jxh 2017-7-26 商户要求对用户开启了自动支付的 但是钱包余额不足的  进行提醒
              //开启了自动支付且钱包余额不足
              flag = 2;
            }
          }
        } catch (Exception e) {

          log.error("扫描到车辆出库扣费错误", e);
          returnData.setReturnData(errorcode_data, "请求失败", "");
        }
      }

      obj.put("auto_state", flag);
      returnData.setReturnData(errorcode_success, "请求成功", obj);
      return;
    } catch (Exception e) {

      log.error("orderBiz.payment is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }

  }


  /**
   * 更新订单的 预约  取消预约  租赁 状态------道闸专用 --郑虎
   *
   * @param type 0：预约   1：取消预约  2：租赁
   * @param state 处理结果状态 0:成功 1：失败
   */
  public void upate_order_state(ReturnDataNew returnData, int dtype, long pi_id,
      String car_code, String area_code, String orderid, Integer type,
      Integer state) {

    try {
      Date date = new Date();
      int money = 0;
      long ui_id = 0;
      Pay_month_park pay_month_park = null;
      Pay_park pay_park = null;
      if (type != 2) {//预约 和 取消预约

        pay_park = payParkPB.selectOnePayPark(orderid);
        if (pay_park == null) {
          //订单不存在
          returnData.setReturnData(errorcode_data, "订单不存在", "" + orderid, "1");
          return;
        } else {
          //int(11)    预约状态 0:未预约 1：预约中  2：预约成功  3：预约失败 4：取消预约中 5：取消预约成功 6：取消预约失败
          if (pay_park.getExpect_state() == 3 || pay_park.getExpect_state() == 6) {
            returnData.setReturnData(errorcode_data, "更新失败", "" + orderid, "0");
            return;
          }

          ui_id = pay_park.getUi_id();
          money = pay_park.getExpect_money();
        }
      } else {
        //租赁
        pay_month_park = payMonthParkPB.selectOnePayMonthPark(orderid);
        if (pay_month_park == null) {
          //订单不存在
          returnData.setReturnData(errorcode_data, "订单不存在", "" + orderid, "1");
          return;
        } else {

          //int(11)    租赁状态1：租赁中2：租赁成功3：租赁失败--解绑租赁金额
          if (pay_month_park.getRent_state() == 3) {
            returnData.setReturnData(errorcode_data, "更新失败", "" + orderid, "0");
            return;
          }
          ui_id = pay_month_park.getUi_id();
          money = pay_month_park.getMoney();
        }
      }
      if (money > 0 && ui_id > 0) {
        //记录该次请求数据
        //检查该手机号码是否已经注册
        User_info userinfo = user_infoDao.selectByKey(ui_id);
        if (userinfo == null) {
          returnData.setReturnData(errorcode_data, "用户不存在!", "", "3");
          return;
        }
        Lock_money_log lock_money_log = new Lock_money_log();
        lock_money_log.setArea_code(area_code);
        lock_money_log.setCtime(date);
        lock_money_log.setMoney(money);
        lock_money_log.setOder_type(type == 2 ? 1 : 0);
        lock_money_log.setOrderid(orderid);
        lock_money_log.setPi_id(pi_id);
        lock_money_log.setType(type);
        lock_money_log.setUi_id(userinfo.getUi_id());
        lock_money_log.setUi_nd(userinfo.getUuid());
        lock_money_log.setState(state);
        lock_money_log.setNote(car_code);
        int id = lock_money_logDao.insert(lock_money_log);
        if (id < 1) {
          returnData.setReturnData(errorcode_data, "处理失败--没有记录该次数据", "", "4");
          return;
        }

        try {
          //这里调用  通过订单类型  和  订单状态  进行处理  解绑金额和返还金额等操作 --- 这里需要通过事务处理
          int order_type = 0;//order_type 0:预约 1：租赁
          if (type == 2) {//0：预约   1：取消预约  2：租赁
            order_type = 1;
          }
          gateUserTransaction
              .doUnLockMoney(type, order_type, state, money, userinfo, orderid, pi_id, area_code,
                  pay_month_park, pay_park, returnData);
        } catch (Exception e) {

          log.error("调用事务  baseTransaction.getUserTransaction().doUnLockMoney is error", e);
        }
      } else {
        //错误
        returnData.setReturnData(errorcode_data, "用户不存在或者金额为零", "" + orderid, "1");
        return;
      }
      /*returnData.setReturnData(errorcode_success, "请求成功--没有执行内部逻辑", "","1000");
      return;*/
    } catch (Exception e) {

      log.error("orderBiz.upate_order_state is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 租赁订单产生了临时费用  直接生成一个临停订单
   * 创建时间、下单类型、订单号由客户端提供  2017-06-12
   */
  public void temporaryOrder(ReturnDataNew returnData, Param_TemporaryOrder param) {

    try {

      long ui_id = 0L;
      String ui_nd = "";
      String ui_tel = "";

      User_carcode userCarcode = gateCarBiz.queryUserCarBycode(param.getCar_code());
      if(null != userCarcode){
        ui_id = userCarcode.getUi_id();
        ui_nd = userCarcode.getUi_nd();
        ui_tel = userCarcode.getUi_tel();
      }

      String sql = "select * from pay_park t where t.my_order = ?";
      Pay_park payPark = getMySelfService().queryUniqueT(sql,Pay_park.class,param.getOrder_id());
      if(null != payPark){
        log.info("订单[{}]已经存在",param.getOrder_id());
        returnData.setReturnData(errorcode_success, "订单已经存在，返回订单信息", payPark);
        return;
      }

      Pay_park pay_park = payParkPB
          .makeAutoOrder(param.getPi_id(), param.getArea_code(), param.getCar_code(), ui_id, ui_nd,
              ui_tel, param.getFinal_time(), param.getMoney(), param.getCar_type(), param.getCar_code_color(), param.getCreate_time(),
              param.getOrder_type(), param.getOrder_id());
      if (pay_park == null) {
        //下单失败
        log.error("OrderBiz.temporaryOrder is error ");
        returnData.setReturnData(errorcode_data, "生成订单失败", "");
        return;
      }

      //返回结果
      returnData.setReturnData(errorcode_success, "生成订单成功", pay_park);
      return;
    } catch (Exception e) {
      log.error("OrderBiz.temporaryOrder is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 租赁订单产生了临时费用  直接生成一个临停订单
   */
  public void temporary_Order(ReturnDataNew returnData, int dtype, Integer pi_id,
      String car_code, String area_code, Integer final_time,
      Integer car_type, Integer car_code_color, Integer money) {

    try {
      //用户ID 通过车牌号获取用户ID
      long ui_id = 0;
      User_carcode user_carcode = gateCarBiz.queryUserCarBycode(car_code);
      if (user_carcode == null) {
        returnData.setReturnData(errorcode_data, "没有该车牌绑定的用户信息", "");
        return;
      } else {
        ui_id = user_carcode.getUi_id();
      }
      /**
       * 创建临时订单
       */
      //创建前先检查是否存在该停车场还未支付的临停订单
      Pay_park pay_park = QueryPayPark(ui_id, pi_id, area_code, car_code);
      if (pay_park != null) {
        if (pay_park.getOrder_type() == 1 && pay_park.getArrive_time().getTime() > pay_park
            .getCtime().getTime()) {
          //已到达停车场 但是没有付款的订单
          //您还有该停车场未完成的订单
          returnData.setReturnData(errorcode_data, "您还有该停车场未完成的订单，亲！", "");
          return;
        } else {
          //已经预约了该停车场 还未扣款
          //您还有该停车场未完成的预约订单
          returnData.setReturnData(errorcode_data, "您还有该停车场未完成的预约订单，亲！", "");
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
      }
      pay_park = payParkPB
          .MakeAutoOrder(pi_id, area_code, car_code, user_info.getUi_id(), user_info.getUuid(),
              user_info.getUi_tel(), final_time, money, car_type, car_code_color);
      if (pay_park == null) {
        //下单失败
        log.error("OrderBiz.temporary_Order is error ");
        returnData.setReturnData(errorcode_data, "生成订单失败", "");
        return;
      }

      //返回结果
      returnData.setReturnData(errorcode_success, "生成订单成功", pay_park);
      return;
    } catch (Exception e) {

      log.error("OrderBiz.temporary_Order is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 获取PDA的对应的车是否是逃逸车辆或者未交费车辆
   */
  @Deprecated
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void pda_check_escape_car(ReturnDataNew returnData, Long pi_id, String area_code,
      Integer type,
      String car_code) {

    try {
      //is_over int default 0 comment '订单是否完成或者逃逸(0:没有完成 1：完成 2：车辆逃逸   3：未交费)',
      //通过车牌号查询 是否存在逃逸或者未缴费车辆
      String name = "";
      int is_over = 2;
      if (type == 0) {
        is_over = 2;
        name = "逃逸";
      } else {
        is_over = 3;
        name = "未交费";
      }

      //获取商户ID
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        //该停车场不存在
        returnData.setReturnData(errorcode_data, "该停车场不存在", "");
        return;
      }
      //商户ID
      long pu_id = park_info.getPu_id();
      if (pu_id == 0) {
        //不是逃逸或者未缴费车辆
        returnData.setReturnData(errorcode_success, "不是" + name + "车辆", "");
        return;
      }

      String sql = "select * from pay_park where pu_id=?  and car_code=? and is_over=? and pp_state=0 and is_del=0";
      List<Pay_park> pay_park_list = getMySelfService()
          .queryListT(sql, Pay_park.class, pu_id, car_code, is_over);
      if (pay_park_list == null || pay_park_list.size() == 0) {
        //不是逃逸或者未缴费车辆
        returnData.setReturnData(errorcode_success, "不是" + name + "车辆", "");
        return;
      } else {
        //检查该车辆是否
        returnData.setReturnData(errorcode_success, "是" + name + "车辆", pay_park_list);
        return;
      }
    } catch (Exception e) {

      log.error("orderBiz.pda_check_escape_car is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /****************************下面是分离出来的方法***************************************/


  /**
   * 获取用户临停订单
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


  @Autowired
  private CarTransaction carTransaction;

  /**
   * 更新预约/租赁超时状态
   */
  public void upate_order_overtime(ReturnDataNew returnData, int dtype, String orderid,
      Integer type) {
    try {
      switch (type) {
        case 0://预约
          Pay_park pay_park = getMySelfService()
              .queryUniqueT("SELECT *  FROM pay_park WHERE my_order=? ORDER BY ctime DESC LIMIT 1",
                  Pay_park.class, orderid);
          if (pay_park == null) {
            returnData.setReturnData(errorcode_data, "更新预约超时状态失败,未找到相关订单", "" + orderid, "0");
            return;
          }
          if (pay_park.cancel_state != 1) {//未处理
            carTransaction.ExpectOrderCheckTask(pay_park);
          }
          break;
        case 1://租赁
          Pay_month_park pay_month_park = getMySelfService()
              .queryUniqueT(
                  "SELECT *  FROM pay_month_park WHERE my_order=? ORDER BY ctime DESC LIMIT 1",
                  Pay_month_park.class, orderid);
          if (pay_month_park == null) {
            returnData.setReturnData(errorcode_data, "更新租赁超时状态失败,未找到相关订单", "" + orderid, "0");
            return;
          }
          if (pay_month_park.cancel_state != 1) {//未处理
            payMonthParkPB.doOvertimeLease(pay_month_park);
          }
          break;
      }

      returnData.setReturnData(errorcode_success, "更新预约/租赁超时状态成功", "");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      returnData.setReturnData(errorcode_data, e.getMessage(), "");
    }
  }
}
