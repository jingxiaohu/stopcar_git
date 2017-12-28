package com.park.app.action.v1.user.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 用户申诉退费
 * @author jingxiaohu
 *
 */
public class Param_usermoneyback extends BaseParam {
	/********************接收参数区*************************/
	public String order_id;//停车下订单表主键ID
	public long pi_id;//停车场主键ID
	public int um_money;//退款金额(单位 分)
	public String car_code;//退款车牌号
	public int um_state;//退款状态 0：未退款 1：已退款
	public int check_state;//审核状态 0：未审核 1：已审核
	public long admin_userid;//审核人后台管理表主键ID
	public int is_rent;//是否是租赁订单 0：临停订单  1：预约临停订单  2：租赁包月订单 3:租赁续租
	public String area_code;//地区区域编号
	public int type;//申诉类型 0：临停扣款问题 1：预约超时扣费问题  2：其它
	public String content;//申诉原因
	/************************get set 方法区****************************/


	public String getCar_code() {
		return car_code;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public void setCar_code(String car_code) {
		this.car_code = car_code;
	}
	public long getPi_id() {
		return pi_id;
	}
	public void setPi_id(long pi_id) {
		this.pi_id = pi_id;
	}
	public int getUm_money() {
		return um_money;
	}
	public void setUm_money(int um_money) {
		this.um_money = um_money;
	}
	public int getUm_state() {
		return um_state;
	}
	public void setUm_state(int um_state) {
		this.um_state = um_state;
	}
	public int getCheck_state() {
		return check_state;
	}
	public void setCheck_state(int check_state) {
		this.check_state = check_state;
	}
	public long getAdmin_userid() {
		return admin_userid;
	}
	public void setAdmin_userid(long admin_userid) {
		this.admin_userid = admin_userid;
	}
	public int getIs_rent() {
		return is_rent;
	}
	public void setIs_rent(int is_rent) {
		this.is_rent = is_rent;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}



	


}
