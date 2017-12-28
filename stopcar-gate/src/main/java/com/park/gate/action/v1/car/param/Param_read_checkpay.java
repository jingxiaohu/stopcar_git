package com.park.gate.action.v1.car.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 检查某停车场某车牌号是否已经付款
 * @author jingxiaohu
 *
 */
public class Param_read_checkpay extends BaseParam {
	public long pi_id;//预约停车场主键ID;
	public String car_code;//车牌号
	public String area_code;//省市区区域代码  四川省 成都市 龙泉驿区  510112
	public String orderid;//我们的订单号  字符串 
	
	
	
	public long getPi_id() {
		return pi_id;
	}
	public void setPi_id(long pi_id) {
		this.pi_id = pi_id;
	}
	public String getCar_code() {
		return car_code;
	}
	public void setCar_code(String car_code) {
		this.car_code = car_code;
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
	
	
}
