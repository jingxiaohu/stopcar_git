package com.park.gate.action.v1.car.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 道闸停车场的更新用户现金和在线支付的支付状态和金额
 * 处理客户端对订单支付的确认信息记录，同时记录异常订单信息
 * 新增支付类型参数is_cash(0：在线支付  1：现金支付)
 * @author zyy 20171102
 *
 */
public class Param_pay_sure_new extends BaseParam {
	public Long pi_id;//预约停车场主键ID
	public String car_code;//车牌号
	public Integer money;//金额（ 单位分）
	public String area_code;//省市区区域代码
	public String orderid;//我们的订单号  字符串
	public Integer type;//处理类型  0:常规类型  1：免费分钟类型 2:免费车类型 3：包月车类型 4：租赁车类型
	public Integer is_cash;  //支付类型 0：在线支付  1：现金支付 2：免费支付 3:免费车类型  4：包月车类型   5：租赁车类型;
	/*********************2017-4-13为了处理客户端离线数据的异步上传问题新增参数*******/
	  public Long sync_time;//离线端入库时间或者出库时间  13位毫秒数
	  public Integer is_sync;// 是否异步上传  0：否 1：是
	/*****************************************************************/
	public Long getPi_id() {
		return pi_id;
	}
	public void setPi_id(Long pi_id) {
		this.pi_id = pi_id;
	}
	public String getCar_code() {
		return car_code;
	}
	public void setCar_code(String car_code) {
		this.car_code = car_code;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIs_cash() {
		return is_cash;
	}
	public void setIs_cash(Integer is_cash) {
		this.is_cash = is_cash;
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
}
