package com.park.app.action.v1.order.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 准备用户手动下单的数据 （1：读取预约下单普通车位  2：读取下单租赁包月车位  3:  读取用户停车缴费读取订单）
 * @author jingxiaohu
 *
 */
public class Param_Read_makeOrder extends BaseParam {
	/********************接收参数区*************************/
	public long pi_id;//预约停车场主键ID
	public String car_code;//车牌号
	public String area_code;//省市区区域代码  四川省 成都市 龙泉驿区  510112
	public String orderid;//订单号
	/************************get set 方法区****************************/
	
	public long getPi_id() {
		return pi_id;
	}
	public String getOrderid() {
		return orderid;
	}


	public void setOrderid(String orderid) {
		this.orderid = orderid;
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
	


    
}
