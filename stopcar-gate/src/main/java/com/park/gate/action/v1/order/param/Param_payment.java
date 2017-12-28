package com.park.gate.action.v1.order.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 扫描到车辆出库扣费
 * @author jingxiaohu
 *
 */
public class Param_payment extends BaseParam {
	/********************接收参数区*************************/
	public long pi_id;//预约停车场主键ID
	public String car_code;//车牌号
	public String area_code;//省市区区域代码
	public String orderid;//我们的订单号  字符串
	public int is_rent;//是否是租赁车辆 0:不是 1：是
	public int money;//实际扣费金额 单位分
	public int final_time;//计费时长 （单位分钟）
	/************************get set 方法区****************************/

	public int getFinal_time() {
		return final_time;
	}
	public void setFinal_time(int final_time) {
		this.final_time = final_time;
	}
	
    
	public String getCar_code() {
		return car_code;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getIs_rent() {
		return is_rent;
	}
	public void setIs_rent(int is_rent) {
		this.is_rent = is_rent;
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
	public void setCar_code(String car_code) {
		this.car_code = car_code;
	}

	
	
}
