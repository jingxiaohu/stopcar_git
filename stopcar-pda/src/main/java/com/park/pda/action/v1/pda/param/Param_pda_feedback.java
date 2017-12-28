package com.park.pda.action.v1.pda.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 用户反馈
 * @author zyy
 *
 */
public class Param_pda_feedback extends BaseParam {
	/********************接收参数区*************************/
	public String content;//反馈信息
	public Long pi_id;//停车场主键ID
	public String area_code;//地址区域编码
	public String pi_name;//停车场名称
	public Long pda_id;//PDA设备表的主键ID
	/************************get set 方法区****************************/


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

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

	public String getPi_name() {
		return pi_name;
	}

	public void setPi_name(String pi_name) {
		this.pi_name = pi_name;
	}

	public Long getPda_id() {
		return pda_id;
	}

	public void setPda_id(Long pda_id) {
		this.pda_id = pda_id;
	}

}
