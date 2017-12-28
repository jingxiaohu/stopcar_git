package com.park.gate.action.v1.order.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 更新订单的 预约  取消预约  租赁 状态 
 * @author jingxiaohu
 *
 */
public class Param_upate_order_state extends BaseParam {
	/********************接收参数区*************************/
	public long pi_id;//预约停车场主键ID
	public String car_code;//车牌号
	public String area_code;//省市区区域代码
	public String orderid;//我们的订单号  字符串
	public Integer type;//0：预约   1：取消预约  2：租赁
	public Integer state;//处理结果状态 0:成功 1：失败
	/************************get set 方法区****************************/

    
	public String getCar_code() {
		return car_code;
	}
	public long getPi_id() {
		return pi_id;
	}
	public void setPi_id(long pi_id) {
		this.pi_id = pi_id;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public void setCar_code(String car_code) {
		this.car_code = car_code;
	}
}
