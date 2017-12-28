package com.park.type;
/**
 * 活动管理
 * @author jingxiaohu
 *
 */
public enum ActivityEnum {
	/**
	 * 活动的主键ID
	 */
	activity_register("1.首次注册活动",1),
	activity_FirstPay("2.首次支付活动",2),
	activity_recharge("3.充30送30活动",3),
	activity_RandomMoney("4.随机立免活动",4),
	activity_RandomMoney_lzf("5.随机立免活动-龙支付",5);
	
	
	
	
	
	
	
	
	
	
	private String name;
	private int value;
	
	private   ActivityEnum(String name,int value){
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
