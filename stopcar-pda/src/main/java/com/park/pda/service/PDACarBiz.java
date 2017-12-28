
package com.park.pda.service;

import com.alibaba.fastjson.JSONObject;
import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.Pay_park;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_carcode;
import com.park.bean.User_pay;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.PayParkPB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理车辆信息的业务逻辑管理类
 *
 * @author jingxiaohu
 */
@Service
public class PDACarBiz extends BaseBiz {

//  @Resource(name = "baseTransaction")
//  protected BaseTransaction baseTransaction;
  @Autowired
  private PayParkPB payParkPB;
//  @Autowired
//  private PayMonthParkPB payMonthParkPB;
//  @Autowired
//  protected CarPB carPB;

  /**
   * 检查某停车场某车牌号是否已经付款 ---  PDA专用 by jxh 2017-4-6
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void read_pda_checkpay(ReturnDataNew returnData, long pi_id,
      String car_code, String area_code, String orderid) {
    // TODO Auto-generated method stub
    try {
      JSONObject returnobj = new JSONObject();
      long ui_id = 0;
      //从订单表中 获取该用户的停车缴费订单信息
      Pay_park pay_park = payParkPB.selectOnePayPark(orderid);
      if (pay_park == null) {
        //返回结果
        returnData.setReturnData(errorcode_data, "该临停订单不存在", returnobj);
        return;
      } else {
        ui_id = pay_park.getUi_id();
      }
      //订单信息
      returnobj.put("ui_id", ui_id);
      returnobj.put("car_code", car_code);
      //这里检查该订单是否是 占道停车场订单    Park_type停车场类型0：道闸停车场1：占道车场2：露天立体车库停车场
      if (pay_park.getPark_type() == 1) {
        //占道停车场
        //先检查  第三方支付回调表是否有订单且  是否已经支付
        /**
         * 进行非完全抵扣情况判断
         */
        User_pay callPay = payParkPB.isCallPay(pay_park.getMy_order());
        if (callPay == null) {
          //第三方还没有回调成功 或者 没有订单 在 User_pay里面
          //非占道停车场
          returnobj.put("state", pay_park.getPp_state());//是否支付  0：未支付 1：已经支付
          returnobj.put("pay_source", 0);//支付类型
          returnobj.put("escape_orderids", "");//补交订单集合
          returnobj.put("money", pay_park.getMoney());//支付金额 单位 分
        } else {
          //已经支付 设置
          returnobj.put("state", 1);//是否支付  0：未支付 1：已经支付
          returnobj.put("pay_source", callPay.getType());//支付类型
          returnobj.put("escape_orderids", callPay.getEscape_orderids());//补交订单集合
          returnobj.put("money", callPay.getMoney());//支付金额 单位 分
        }
      } else {
        //非占道停车场
    	  returnData.setReturnData(errorcode_data, "停车场类型错误", "");
          return;
      }
   
      
      returnobj.put("is_cash", pay_park.getIs_cash());//是否现金支付0：在线支付1：现金支付
      //返回结果
      returnData.setReturnData(errorcode_success, "查询是否已经付款成功", returnobj);
      return;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("carBiz.read_pda_checkpay is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }
  
//查询用户和车牌号绑定关系
  public User_carcode queryUserCarBycode(String car_code) {
    try {
      //首先判断是否已经绑定了该量车
      String sql = "select *  from user_carcode where car_code=? limit 1";
      User_carcode user_carcode = getMySelfService()
          .queryUniqueT(sql, User_carcode.class, car_code);
      return user_carcode;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("queryUserCarBycode is error" + e.getMessage(), e);
    }
    return null;
  }

}
