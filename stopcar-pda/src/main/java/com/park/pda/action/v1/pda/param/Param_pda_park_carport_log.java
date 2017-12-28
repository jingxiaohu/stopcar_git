package com.park.pda.action.v1.pda.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 停车场车位总数、已停车位数、空余车位数 流水记录
 * @author zyy
 *
 */
public class Param_pda_park_carport_log extends BaseParam {

	public Long pi_id;	//停车场主键ID
	
	public String area_code;	//停车场地址区域编码
	
	public Integer carport_total;	//车位总数
	
	public Integer carport_yet;	//已停车位数
	
	public Integer carport_space;	//空余车位数
	
	public Integer park_type;	//停车场类型( 停车场类型0：道闸停车场1：占道车场2：露天立体车库停车场)
	
	public Integer data_flag;	//上传来源 1：普通占道停车场 2：地磁占道停车场 3：道闸停车场
	
	public String ctime;	//停车场本地时间
	
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

	public Integer getCarport_total() {
		return carport_total;
	}

	public void setCarport_total(Integer carport_total) {
		this.carport_total = carport_total;
	}

	public Integer getCarport_yet() {
		return carport_yet;
	}

	public void setCarport_yet(Integer carport_yet) {
		this.carport_yet = carport_yet;
	}

	public Integer getCarport_space() {
		return carport_space;
	}

	public void setCarport_space(Integer carport_space) {
		this.carport_space = carport_space;
	}

	public Integer getPark_type() {
		return park_type;
	}

	public void setPark_type(Integer park_type) {
		this.park_type = park_type;
	}

	public Integer getData_flag() {
		return data_flag;
	}

	public void setData_flag(Integer data_flag) {
		this.data_flag = data_flag;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	
}
