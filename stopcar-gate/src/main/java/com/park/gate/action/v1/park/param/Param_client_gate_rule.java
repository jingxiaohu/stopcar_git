package com.park.gate.action.v1.park.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 道闸客户端规则记录管理
 * 添加和修改共用 参数类
 * @author zyy
 *
 */
public class Param_client_gate_rule extends BaseParam {

	public String client_ruleid;// 客户端规则唯一标识（主键）
	
	public Long pi_id;// 停车场主键ID
	
	public String area_code;// 停车场地址区域编码
	
	public String group_id;// 客户端所属分组ID
	
	public Integer type;// 类型（0：未指定 1：临停  2：租赁）
	
	public Integer money;// 默认通用型单价(单位分)
	
	public Integer state;// 是否开启（0：关闭 1：开启）
	
	public String str_json;// 其它属性JSON
	
	public String intro;// APP端显示的 描述
	
	public String permit_time;// 租赁允许时间段（08：00-19：00）
	
	public String client_loginname;// 客户端修改人登录帐号

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

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getStr_json() {
		return str_json;
	}

	public void setStr_json(String str_json) {
		this.str_json = str_json;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getPermit_time() {
		return permit_time;
	}

	public void setPermit_time(String permit_time) {
		this.permit_time = permit_time;
	}

	public String getClient_loginname() {
		return client_loginname;
	}

	public void setClient_loginname(String client_loginname) {
		this.client_loginname = client_loginname;
	}

}
