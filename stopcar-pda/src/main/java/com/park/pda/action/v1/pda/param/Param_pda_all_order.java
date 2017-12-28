package com.park.pda.action.v1.pda.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * PDA停车场的所有未删除订单
 * @author zyy
 *
 */
public class Param_pda_all_order extends BaseParam {
	/********************接收参数区*************************/
	public String area_code;//省市区区域代码  四川省 成都市 龙泉驿区  510112
	public Long pi_id;//停车场ID
	public Long start_time = 0L;//开始时间
	public Long end_time = System.currentTimeMillis();//结束时间
	
	/************************get set 方法区****************************/

	public Long getPi_id() {
		return pi_id;
	}

	public void setPi_id(Long pi_id) {
		this.pi_id = pi_id;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public Long getStart_time() {
		return start_time;
	}

	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}

	public Long getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Long end_time) {
		this.end_time = end_time;
	}
	
}
