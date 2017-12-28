package com.park.mvc.action.v1.park.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 读取停车场详情
 * @author jingxiaohu
 *
 */
public class Param_park_info extends BaseParam {
	/********************接收参数区*************************/
	public long pi_id;//停车场主键ID
	public String area_code;//省市区区域代码  四川省 成都市 龙泉驿区  510112

	
	/************************get set 方法区****************************/

	public long getPi_id() {
		return pi_id;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public void setPi_id(long pi_id) {
		this.pi_id = pi_id;
	}


	
	

	
	
	

}
