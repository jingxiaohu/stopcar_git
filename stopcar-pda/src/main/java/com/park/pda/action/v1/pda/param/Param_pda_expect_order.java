package com.park.pda.action.v1.pda.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 通过GPS导航获取 该经纬度范围内的停车场数据列表
 * @author jingxiaohu
 *
 */
public class Param_pda_expect_order extends BaseParam {
	/********************接收参数区*************************/
	public int type;// 获取订单类型  0:普通停车订单  1：租赁停车订单
	public String area_code;//省市区区域代码  四川省 成都市 龙泉驿区  510112
	public long pi_id;//停车场ID
	public int page  = 1;
	public int size = 20;
	/************************get set 方法区****************************/



	public int getPage() {
		return page;
	}


	public long getPi_id() {
		return pi_id;
	}


	public void setPi_id(long pi_id) {
		this.pi_id = pi_id;
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




	public String getArea_code() {
		return area_code;
	}


	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	


}
