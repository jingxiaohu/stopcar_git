package com.park.pda.action.v1.pay.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 支付宝 -- 用户充值
 * @author jingxiaohu
 *
 */
public class Param_zfb_charge_face extends BaseParam {
	/********************接收参数区*************************/
	public int pay_type;//支付类型 1:支付宝  2：微信  3：银联  4：钱包 5:龙支付 
	public int pay_price;//充值金额 单位 分
	public int version;//当前版本编号
	public String subject;//商品名称
	public int system_type;//操作系统类型（IOS Android web） 1:android 2:IOS 3:web  4:PDA
	public long t;//时间戳ms
	public String token;//令牌
	public int type;//是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付
	public String orderid;//付款 订单ID 如果多个 中间逗号分割 例如（a123,b123,c123）
	
	//by jxh PDA 未交费总金额
	public int escape_money;
	//by jxh PDA 补交订单集合
	public String escape_orderids;
	/************************get set 方法区****************************/
	
	public int getType() {
		return type;
	}

	public String getEscape_orderids() {
		return escape_orderids;
	}

	public void setEscape_orderids(String escape_orderids) {
		this.escape_orderids = escape_orderids;
	}

	public int getEscape_money() {
		return escape_money;
	}

	public void setEscape_money(int escape_money) {
		this.escape_money = escape_money;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public int getSystem_type() {
		return system_type;
	}

	public void setSystem_type(int system_type) {
		this.system_type = system_type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public int getPay_type() {
		return pay_type;
	}
	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}
	public int getPay_price() {
		return pay_price;
	}
	public void setPay_price(int pay_price) {
		this.pay_price = pay_price;
	}
	public int getTerminal_type() {
		return system_type;
	}
	public void setTerminal_type(int terminal_type) {
		this.system_type = terminal_type;
	}
	public long getT() {
		return t;
	}
	public void setT(long t) {
		this.t = t;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
}
