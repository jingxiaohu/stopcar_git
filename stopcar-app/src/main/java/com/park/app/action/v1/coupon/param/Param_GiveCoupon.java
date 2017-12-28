package com.park.app.action.v1.coupon.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 
 * 扫码赠送优惠券
 * @author jingxiaohu
 *
 */
public class Param_GiveCoupon extends BaseParam {
	/**
	 * 该次赠送请求 是有被赠送方 使用吾泊APP发起
	 */
	//优惠券原属用户ID
	public Long from_ui_id;
	//赠送目标用户ID
	//public Long to_ui_id;
	//用户优惠券主键ID
	public Long upc_id;
	
	
	
	public Long getFrom_ui_id() {
		return from_ui_id;
	}
	public void setFrom_ui_id(Long from_ui_id) {
		this.from_ui_id = from_ui_id;
	}
	public Long getUpc_id() {
		return upc_id;
	}
	public void setUpc_id(Long upc_id) {
		this.upc_id = upc_id;
	}
	
	
	
}
