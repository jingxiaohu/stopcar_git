package com.park.app.action.v1.order.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 *  获取我的订单 （1：临时停车订单  2： 租赁订单）
 * @author jingxiaohu
 *
 */
public class Param_my_order extends BaseParam {
	/********************接收参数区*************************/
	public int type;// 获取订单类型  0:普通停车订单  1：租赁停车订单
	public String car_code;//车牌号
	public String area_code;//省市区区域代码  四川省 成都市 龙泉驿区  510112
	public int page  = 1;
	public int size = 20;
	/************************get set 方法区****************************/

	public String getCar_code() {
		return car_code;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
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
