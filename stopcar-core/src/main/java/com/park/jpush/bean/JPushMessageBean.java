package com.park.jpush.bean;

import java.util.Date;

import com.alibaba.fastjson.JSON;

/**
 * JPUSH PDA 消息格式
 * @author jingxiaohu
 *
 */
public class JPushMessageBean {
	//类型 0：普通消息（包括 手机预约下单推送 手机临停下单成功推送 手机租赁下单成功推送 出库扣款推送等）  1：PDA预约推送通知  2：停车场状态变更推送 3： 订单状态变更推送
	private int type;
	private JSON MessageJson;
	private String message;
	private String imgurl;
	private Date date;
	private String title;
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public JSON getMessageJson() {
		return MessageJson;
	}
	public void setMessageJson(JSON messageJson) {
		MessageJson = messageJson;
	}
	
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	/*public static void main(String[] args) {
		JPushMessageBean pDAPushMessage = new JPushMessageBean();
		pDAPushMessage.setTitle("系统消息");
		pDAPushMessage.setType(0);
		pDAPushMessage.setDate(new Date());
		pDAPushMessage.setImgurl("http://p3.qhimg.com/t011df209f8904decf8.jpg?size=1000x727");
		pDAPushMessage.setMessage("当前系统已经升级到XXX版本，增加了XXX功能，修改了当前版本的BUG，为了保证您的APP稳定运行，请尽快更新");
		System.out.println(JSON.toJSONString(pDAPushMessage));
	}*/
	
}
