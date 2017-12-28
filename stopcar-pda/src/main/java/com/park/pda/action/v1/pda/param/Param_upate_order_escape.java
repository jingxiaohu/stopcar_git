package com.park.pda.action.v1.pda.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 更新订单的 逃逸 状态 
 * @author jingxiaohu
 *
 */
public class Param_upate_order_escape extends BaseParam {
	 private String orderid;//订单ID
	 private Integer type;//类型 0:正常  1:逃逸 2:未交费   3:已经缴费--编辑逃逸状态为--已经缴费
	 private Integer money;//金额 单位分
	 /*********************2017-4-13为了处理客户端离线数据的异步上传问题新增参数*******/
	  public Long sync_time;//离线端入库时间或者出库时间  13位毫秒数
	  public Integer is_sync;// 如果是异步上传  is_sync=1 
	/*****************************************************************/ 
	 
	 
	 
	 
	 
	 public Integer getMoney() {
		return money;
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
	public void setMoney(Integer money) {
		this.money = money;
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
	 
	
}
