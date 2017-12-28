package com.park.gate.action.v1.order.param.rentDefer;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * Created by zyy on 2017/07/01.
 */
public class RentDeferSelect extends BaseParam{
	
    private String rent_order_id;  //租赁订单号
    
    private Long pi_id;   //停车场主键id
    
    private String area_code;   //区域代码
    
    private Integer pay_state;//支付状态（0：未支付1：支付成功2：支付失败）
    
    private Integer is_expire;//是否已经到期（0：未到期1：已经到期）
    
	public String getRent_order_id() {
		return rent_order_id;
	}
	
	public void setRent_order_id(String rent_order_id) {
		this.rent_order_id = rent_order_id;
	}
	
	public Long getPi_id() {
		return pi_id;
	}
	
	public void setPi_id(Long pi_id) {
		this.pi_id = pi_id;
	}
	
	public String getArea_code() {
		return area_code;
	}
	
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	
	public Integer getPay_state() {
		return pay_state;
	}
	
	public void setPay_state(Integer pay_state) {
		this.pay_state = pay_state;
	}
	
	public Integer getIs_expire() {
		return is_expire;
	}
	
	public void setIs_expire(Integer is_expire) {
		this.is_expire = is_expire;
	}

}
