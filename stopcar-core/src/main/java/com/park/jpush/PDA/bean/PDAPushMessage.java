package com.park.jpush.PDA.bean;

import java.util.Date;

/**
 * PDA推送预约消息体结构
 * @author jingxiaohu
 *
 */
public class PDAPushMessage {
	//车牌号
	private String  car_code;
	//订单表主键ID
	private long id;
	//预约订单号
	private String  orderid;
	//预约用户手机号码
	private String  ui_tel;
	//用户ID
	private long ui_id;
	//用户唯一标识符
	private String uiid;
	//预约时间
	private Date time;
	//类型 1:预约下单  2：取消预约  3: 临停下单
	private int type;
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCar_code() {
		return car_code;
	}
	public void setCar_code(String car_code) {
		this.car_code = car_code;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getUi_tel() {
		return ui_tel;
	}
	public void setUi_tel(String ui_tel) {
		this.ui_tel = ui_tel;
	}
	public long getUi_id() {
		return ui_id;
	}
	public void setUi_id(long ui_id) {
		this.ui_id = ui_id;
	}
	public String getUiid() {
		return uiid;
	}
	public void setUiid(String uiid) {
		this.uiid = uiid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
}
