package com.park.type;
/**
 * 支付或者充值类型
 * @author jingxiaohu
 *
 */
public enum PayTypeEnum {
	/**
	 * 支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
	 */
	paytype_zfb("支付宝",1),
	paytype_wx("微信",2),
	paytype_unionpay("银联",3),
	paytype_wallet("钱包",4),
	paytype_lzf("龙支付",5);
	
	
	/**
	 * 通过类型获取对象
	 */
	public static String returnObj(int type){
		switch(type){
		case 1:return paytype_zfb.name;
		case 2:return paytype_wx.name;
		case 3:return paytype_unionpay.name;
		case 4:return paytype_wallet.name;
		case 5:return paytype_lzf.name;
		default:break;
		}
		return "";
	} 
	
	
	
	private String name;
	private int value;
	
	private   PayTypeEnum(String name,int value){
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

	/*public static void main(String[] args) {
		System.out.println(PayTypeEnum.returnObj(5)); 
	}*/
}
