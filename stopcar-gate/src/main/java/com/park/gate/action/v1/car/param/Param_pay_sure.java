package com.park.gate.action.v1.car.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 道闸停车场的更新用户现金支付状态和金额
 * @author jingxiaohu
 *
 */
public class Param_pay_sure extends BaseParam {
	public long pi_id;//预约停车场主键ID
	public String car_code;//车牌号
	public int money;//金额（ 单位分）
	public String area_code;//省市区区域代码
	public String orderid;//我们的订单号  字符串
	public int type;//处理类型  0:常规类型  1：免费分钟类型 2:免费车类型 3：包月车类型 4：租赁车类型
	/*********************2017-4-13为了处理客户端离线数据的异步上传问题新增参数*******/
	  public Long sync_time;//离线端入库时间或者出库时间  13位毫秒数
	  public Integer is_sync;// 如果是异步上传  is_sync=1 
	/*****************************************************************/ 
	
	
	public int getType() {
		return type;
	}
	public Long getSync_time() {
		return sync_time;
	}
	public void setSync_time(Long sync_time) {
		this.sync_time = sync_time;
	}
	public Integer getIs_sync() {
		return is_sync;
	}
	public void setIs_sync(Integer is_sync) {
		this.is_sync = is_sync;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getPi_id() {
		return pi_id;
	}
	public void setPi_id(long pi_id) {
		this.pi_id = pi_id;
	}
	public String getCar_code() {
		return car_code;
	}
	public void setCar_code(String car_code) {
		this.car_code = car_code;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	
	
}
