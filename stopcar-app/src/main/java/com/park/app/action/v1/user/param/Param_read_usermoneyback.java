package com.park.app.action.v1.user.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 *  获取用户申诉退费审核结果
 * @author jingxiaohu
 *
 */
public class Param_read_usermoneyback extends BaseParam {
	/********************接收参数区*************************/
	public String order_id;//停车下订单表主键ID
	public int is_rent;//是否是租赁订单 0：临停订单  1：预约临停订单  2：租赁包月订单
	/************************get set 方法区****************************/


	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public int getIs_rent() {
		return is_rent;
	}
	public void setIs_rent(int is_rent) {
		this.is_rent = is_rent;
	}


}
