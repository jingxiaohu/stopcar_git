package com.park.pda.service;

import com.park.bean.Pay_park;
import com.park.bean.ReturnDataNew;
import com.park.mvc.service.BaseBiz;
import com.park.pda.action.v1.pda.param.Param_parkConfirmCarOut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * PDA确认车辆已出库服务层
 *
 * @author zzy
 */
@Service
public class PDAParkConfirmCarOutBiz extends BaseBiz {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  /**
   * 用户支付停车下单表
   */
  private static String orderType_1 = "1";
  /**
   * 用户支付租赁停车下单表
   */
  private static String orderType_2 = "2";

  /**
   * 更新 智能磁场入库出库状态（1:已入库    2:已出库    3:车辆逃逸     4:其它故障）
   */
  public void excute(String tableName, Param_parkConfirmCarOut param, ReturnDataNew returnData)
      throws Exception {
    //检查订单号是否存在
    String sql = "select * from " + tableName + " where my_order = ?";
    Pay_park park = getMySelfService().queryUniqueT(sql, Pay_park.class, param.getMy_order());
    if (null == park) {
      returnData.setReturnData(errorcode_systerm, "该订单不存在", null);
      return;
    }

    //强制设为离场
    if (StringUtils.hasText(park.gov_num)) {
      int update = getMySelfService()
          .update(
              "UPDATE magnetic_device SET fault_count=fault_count+1,state=0 WHERE gov_num=? AND pi_id=? AND area_code=? LIMIT 1",
              park.gov_num, park.pi_id, park.area_code);
      if (update != 1) {
        log.error("更新地磁设备错误数失败");
      }
    }

    sql = "update " + tableName + " set magnetic_state=? where my_order = ?";
    int result = getMySelfService().update(sql, 2, param.getMy_order());
    if (result >= 1) {
      returnData.setReturnData(errorcode_success, "操作成功", null);
    } else {
      returnData.setReturnData(errorcode_systerm, "操作失败", null);
    }
  }

  /**
   * PDA 确认强制出库
   */
  public void updatePdaMagneticState(ReturnDataNew returnData, Param_parkConfirmCarOut param) {
    try {

      String orderType = param.getOrder_type();

      if (orderType_1.equals(orderType)) {//非包月车辆订单
        excute("pay_park", param, returnData);
      } else if (orderType_2.equals(orderType)) {//包月车辆订单
        excute("pay_month_park", param, returnData);
      } else {
        returnData.setReturnData(errorcode_systerm, "订单类型不正确", null);
      }

    } catch (Exception e) {
      log.error("异常-->", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }
}
