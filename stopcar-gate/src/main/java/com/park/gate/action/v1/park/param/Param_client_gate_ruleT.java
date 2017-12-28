package com.park.gate.action.v1.park.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 道闸客户端规则记录管理
 * 根据ID查询和删除共用参数类
 * @author zyy
 *
 */
public class Param_client_gate_ruleT extends BaseParam {

	public String client_ruleid;// 客户端规则唯一标识（主键）
	
	public Long pi_id;// 停车场主键ID
	
	public String area_code;// 停车场地址区域编码

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

	public String getClient_ruleid() {
		return client_ruleid;
	}

	public void setClient_ruleid(String client_ruleid) {
		this.client_ruleid = client_ruleid;
	}

}
