package com.park.gate.action.v1.order.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 更新预约/租赁超时状态
 *
 * @author Peter Wu
 */
public class Param_upate_order_overtime extends BaseParam {

  public String orderid;//我们的订单号 字符串
  public Integer type;//0：预约   1：租赁

  public String getOrderid() {
    return orderid;
  }

  public void setOrderid(String orderid) {
    this.orderid = orderid;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }
}
