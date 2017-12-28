package com.park.app.action.v1.order.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 用户手动下单 （1：预约下单普通车位  2：下单租赁包月车位 3:用户取消预约订单 4:删除订单）
 * @author jingxiaohu
 *
 */
public class Param_expect_order extends BaseParam {
	/********************接收参数区*************************/
	public long pi_id;//预约停车场主键ID
	public int pay_type;//支付类型 0:手动输入密码支付 1：快捷支付（服务器可以请求第三方直接扣款）
	public String car_code;//车牌号
	public String pp_versioncode;//当前APPSDK版本号 （内部升级版本代号）
	public String expect_info;//预约提示信息
	public int expect_money;//预约价格（ 单位分）
	public int expect_time;//预约时间 单位分钟
	public String area_code;//省市区区域代码
	
	//下单租赁包月车位
	public int month_num;//包月租凭月数
	public String month_info;//包月提示信息
	public String permit_time;//准入时间段（17:00-08:30）
	public int is_24hours;//是否是24小时包月  0:不是24小时包月  1：是24小时包月
	
	
	//用户取消预约下单  用户删除订单  或者 用户进行普通停车位下单更新 用户使用的优惠券 应缴金额  计费时间
	public String orderid;//我们的订单号  字符串
	public int type;//0：普通车位  1：租赁车位
	public long upc_id;//用户优惠券表主键ID
	
	//用户进行普通停车位下单更新 用户使用的优惠券 应缴金额  计费时间
	public int pay_source;//支付类型 1:支付宝  2：微信  3：银联  4：钱包 5:龙支付
//	public int discount_money;//抵扣金额
//	public int discount_type;//优惠券类型 0:金额券 1：折扣券
//	public String discount_name;//抵扣优惠券名称
	/************************get set 方法区****************************/

    
	public String getCar_code() {
		return car_code;
	}
	public int getIs_24hours() {
		return is_24hours;
	}


	public void setIs_24hours(int is_24hours) {
		this.is_24hours = is_24hours;
	}




	public String getPermit_time() {
		return permit_time;
	}


	public void setPermit_time(String permit_time) {
		this.permit_time = permit_time;
	}







	public long getUpc_id() {
		return upc_id;
	}


	public void setUpc_id(long upc_id) {
		this.upc_id = upc_id;
	}


	public int getPay_source() {
		return pay_source;
	}


	public void setPay_source(int pay_source) {
		this.pay_source = pay_source;
	}


	public String getOrderid() {
		return orderid;
	}


	public void setOrderid(String orderid) {
		this.orderid = orderid;
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


	public long getPi_id() {
		return pi_id;
	}


	public void setPi_id(long pi_id) {
		this.pi_id = pi_id;
	}


	public int getPay_type() {
		return pay_type;
	}


	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}


	public String getExpect_info() {
		return expect_info;
	}


	public void setExpect_info(String expect_info) {
		this.expect_info = expect_info;
	}


	public int getExpect_money() {
		return expect_money;
	}


	public void setExpect_money(int expect_money) {
		this.expect_money = expect_money;
	}


	public int getExpect_time() {
		return expect_time;
	}


	public void setExpect_time(int expect_time) {
		this.expect_time = expect_time;
	}




	public int getMonth_num() {
		return month_num;
	}


	public void setMonth_num(int month_num) {
		this.month_num = month_num;
	}


	public String getMonth_info() {
		return month_info;
	}


	public void setMonth_info(String month_info) {
		this.month_info = month_info;
	}


	public String getPp_versioncode() {
		return pp_versioncode;
	}
	public void setPp_versioncode(String pp_versioncode) {
		this.pp_versioncode = pp_versioncode;
	}
	public void setCar_code(String car_code) {
		this.car_code = car_code;
	}



	

}
