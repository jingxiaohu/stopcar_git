package com.park.pda.action.v1.pda.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 获取我的某条订单 （1：临时停车订单  2： 租赁订单）
 * @author jingxiaohu
 *
 */
public class Param_my_one_order extends BaseParam {
	/********************接收参数区*************************/
	public Integer type;// 获取订单类型  0:普通停车订单  1：租赁停车订单
	public String orderid;//我的订单ID
	/************************get set 方法区****************************/


	public String getOrderid() {
		return orderid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	


    
}
