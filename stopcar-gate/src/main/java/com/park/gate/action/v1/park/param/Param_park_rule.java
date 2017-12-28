package com.park.gate.action.v1.park.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 停车场-特殊规则信息映射表
 * 
 * @author zyy
 *
 */
public class Param_park_rule extends BaseParam {

	public Long pi_id;// 停车场主键ID
	public String area_code;// 停车场地址区域编码
	public String json_array;// 规则JSONArray数组

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

	public String getJson_array() {
		return json_array;
	}

	public void setJson_array(String json_array) {
		this.json_array = json_array;
	}

}
