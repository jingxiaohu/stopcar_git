package com.park.gate.action.v1.park.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 停车场心跳表
 * @author jingxiaohu
 *
 */
public class Param_heartbeat extends BaseParam {
	/********************接收参数区*************************/
	public String area_code;//省市县编号 140107
	public long pi_id;//场地主键ID
	public int is_rent;//是否有租赁包月业务 0：没有 1：有
	public long time;//上次心跳时间  
	public int  park_type;//停车场类型 0：地下室停车场 1：露天停车场 2：露天立体车库停车场',
	
	public Integer carport_yet;//INT    场地已停车位
	public Integer carport_space;//INT    场地空车位个数
	public Integer carport_total;//INT    场地总车位个数
	public Integer moth_car_num;//INT    租赁离线包月车位总个数
	public Integer moth_car_num_space;//INT    租赁离线剩余车位数
	public Integer time_car_num;//INT    时间段包月车位总数
	public Integer time_car_num_space;//INT    时间段包月车位剩余个数
	public Integer expect_car_num;//INT    已预约车辆数
	/************************get set 方法区****************************/


	public String getArea_code() {
		return area_code;
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
	public Integer getCarport_total() {
		return carport_total;
	}
	public void setCarport_total(Integer carport_total) {
		this.carport_total = carport_total;
	}
	public Integer getMoth_car_num() {
		return moth_car_num;
	}
	public void setMoth_car_num(Integer moth_car_num) {
		this.moth_car_num = moth_car_num;
	}
	public Integer getMoth_car_num_space() {
		return moth_car_num_space;
	}
	public void setMoth_car_num_space(Integer moth_car_num_space) {
		this.moth_car_num_space = moth_car_num_space;
	}
	public Integer getTime_car_num() {
		return time_car_num;
	}
	public void setTime_car_num(Integer time_car_num) {
		this.time_car_num = time_car_num;
	}
	public Integer getTime_car_num_space() {
		return time_car_num_space;
	}
	public void setTime_car_num_space(Integer time_car_num_space) {
		this.time_car_num_space = time_car_num_space;
	}
	public Integer getExpect_car_num() {
		return expect_car_num;
	}
	public void setExpect_car_num(Integer expect_car_num) {
		this.expect_car_num = expect_car_num;
	}
	public int getPark_type() {
		return park_type;
	}
	public void setPark_type(int park_type) {
		this.park_type = park_type;
	}

	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public long getPi_id() {
		return pi_id;
	}
	public void setPi_id(long pi_id) {
		this.pi_id = pi_id;
	}
	public int getIs_rent() {
		return is_rent;
	}
	public void setIs_rent(int is_rent) {
		this.is_rent = is_rent;
	}




}
