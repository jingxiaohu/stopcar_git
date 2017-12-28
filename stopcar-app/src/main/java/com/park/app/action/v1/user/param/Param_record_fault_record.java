package com.park.app.action.v1.user.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 
 * @author jingxiaohu
 *
 */
public class Param_record_fault_record extends BaseParam {
	/********************接收参数区*************************/
	public long pi_id;//场地主键ID
	public long pd_id;//出入口设备主键ID
	public int  fr_type;//故障类型 0:摄像头故障 1：开闸设备故障
	public String fr_desc;//故障简述
	public String area_code;//省市县编号 140107
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



	public long getPd_id() {
		return pd_id;
	}



	public void setPd_id(long pd_id) {
		this.pd_id = pd_id;
	}



	public int getFr_type() {
		return fr_type;
	}



	public void setFr_type(int fr_type) {
		this.fr_type = fr_type;
	}



	public String getFr_desc() {
		return fr_desc;
	}



	public void setFr_desc(String fr_desc) {
		this.fr_desc = fr_desc;
	}


}
