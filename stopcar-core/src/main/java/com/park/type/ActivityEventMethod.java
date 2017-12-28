package com.park.type;
/**
 * 执行事件的方法
 * @author jingxiaohu
 *
 */
public enum ActivityEventMethod {
	/**
	 * 活动的事件执行方法
	 */
	register_addUserParkCoupon("1.吾泊下载送优惠","register_addUserParkCoupon"),
	FirstPay_addUserParkCoupon("2.吾泊首单立返","FirstPay_addUserParkCoupon"),
	FirstPay_lzf_addUserParkCoupon("3.吾泊首单立返","FirstPay_lzf_addUserParkCoupon"),
	FirstPay_lzf2_addUserParkCoupon("4.吾泊首单立返","FirstPay_lzf2_addUserParkCoupon"),
	recharge_addUserParkCoupon("5.龙支付充30送30","recharge_addUserParkCoupon"),
	doRandomMoney("4.吾泊随机立减","doRandomMoney");
	
	
	
	
	
	
	
	
	
	
	
	private String name;
	private String value;
	
	private   ActivityEventMethod(String name,String value){
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

	
	
	

}
