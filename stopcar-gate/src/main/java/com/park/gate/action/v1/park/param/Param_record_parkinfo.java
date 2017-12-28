package com.park.gate.action.v1.park.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 记录停车场信息
 * @author jingxiaohu
 *
 */
public class Param_record_parkinfo extends BaseParam {
	/********************接收参数区*************************/
	public String name;//场地名称
	public String address_name;//停车场地理街道名称
	public Double lng; //地理经度
	public Double lat;//地理纬度
	public String linkman_name;//场地联系人姓名
	public String linkman_tel;//联系人电话
	public String copy_linkman_name;//备用联系人姓名
	public String copy_linkman_tel;//备用联系人手机
	public String pi_phone;//场地座机号码
	public String department;//负责单位 
	public Integer enter_num;//场地入口数量
	public Integer exit_num;//场地出口数量
	public Integer hlc_enter_num;//场地入口道闸数量
	public Integer hlc_exit_num;//场地出口道闸数量
	public Integer enter_camera_num;//场地入口摄像头数量
	public Integer exit_camera_num;//场地出口摄像头数量
	public String camera_info;//场地摄像头信息
	public String park_type;//停车场类型 0：道闸停车场 1：占道车场 2：露天立体车库停车场
	public String area_code;//省市县编号 140107  
	
	public Integer is_expect;//是否开启预约 0:不开启 1：开启
	public Integer  expect_money;//每小时预约费用（单位分）   默认值 就是 起步价
	public Integer  allow_revoke_time;//允许预约撤单时间(单位分钟)----*暂时不开启
	public Integer  allow_expect_time;//允许预约时间最大时长(单位分钟)----*暂时不开启 
	
	public Integer carport_yet;//INT    场地已停车位
	public Integer carport_space;//INT    场地空车位个数
	public Integer carport_total;//INT    场地总车位个数
	public Integer moth_car_num;//INT    租赁离线包月车位总个数
	public Integer moth_car_num_space;//INT    租赁离线剩余车位数
	public Integer time_car_num;//INT    时间段包月车位总数
	public Integer time_car_num_space;//INT    时间段包月车位剩余个数
	public Integer expect_car_num;//INT    已预约车辆数
	public Integer roadside_type;//占道停车是否按次数收费 0:按时间收费 1：按次数收费
	
	//更新场地的信息
	public long pi_id;//场地主键ID
	public Integer upload_source;//上传数据来源 0:离线道闸上传 1：中心管理后台录入  2：Android市场人员跑动录入
	public Integer admin_id;//上传数据后台管理账户ID 
	
	
	//by jxh 2017-5-27 新增道闸停车场专线IP 上传服务器
	public String special_ip;
	/************************get set 方法区****************************/

	public String getName() {
		return name;
	}


	public String getSpecial_ip() {
		return special_ip;
	}


	public void setSpecial_ip(String special_ip) {
		this.special_ip = special_ip;
	}


	public Integer getRoadside_type() {
		return roadside_type;
	}


	public void setRoadside_type(Integer roadside_type) {
		this.roadside_type = roadside_type;
	}


	public Integer getUpload_source() {
		return upload_source;
	}


	public void setUpload_source(Integer upload_source) {
		this.upload_source = upload_source;
	}


	public Integer getAdmin_id() {
		return admin_id;
	}


	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress_name() {
		return address_name;
	}


	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}


	public Double getLng() {
		return lng;
	}


	public void setLng(Double lng) {
		this.lng = lng;
	}


	public Double getLat() {
		return lat;
	}


	public void setLat(Double lat) {
		this.lat = lat;
	}


	public String getLinkman_name() {
		return linkman_name;
	}


	public void setLinkman_name(String linkman_name) {
		this.linkman_name = linkman_name;
	}


	public String getLinkman_tel() {
		return linkman_tel;
	}


	public void setLinkman_tel(String linkman_tel) {
		this.linkman_tel = linkman_tel;
	}


	public String getCopy_linkman_name() {
		return copy_linkman_name;
	}


	public void setCopy_linkman_name(String copy_linkman_name) {
		this.copy_linkman_name = copy_linkman_name;
	}


	public String getCopy_linkman_tel() {
		return copy_linkman_tel;
	}


	public void setCopy_linkman_tel(String copy_linkman_tel) {
		this.copy_linkman_tel = copy_linkman_tel;
	}


	public String getPi_phone() {
		return pi_phone;
	}


	public void setPi_phone(String pi_phone) {
		this.pi_phone = pi_phone;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public Integer getEnter_num() {
		return enter_num;
	}


	public void setEnter_num(Integer enter_num) {
		this.enter_num = enter_num;
	}


	public Integer getExit_num() {
		return exit_num;
	}


	public void setExit_num(Integer exit_num) {
		this.exit_num = exit_num;
	}


	public Integer getHlc_enter_num() {
		return hlc_enter_num;
	}


	public void setHlc_enter_num(Integer hlc_enter_num) {
		this.hlc_enter_num = hlc_enter_num;
	}


	public Integer getHlc_exit_num() {
		return hlc_exit_num;
	}


	public void setHlc_exit_num(Integer hlc_exit_num) {
		this.hlc_exit_num = hlc_exit_num;
	}


	public Integer getEnter_camera_num() {
		return enter_camera_num;
	}


	public void setEnter_camera_num(Integer enter_camera_num) {
		this.enter_camera_num = enter_camera_num;
	}


	public Integer getExit_camera_num() {
		return exit_camera_num;
	}


	public void setExit_camera_num(Integer exit_camera_num) {
		this.exit_camera_num = exit_camera_num;
	}


	public String getCamera_info() {
		return camera_info;
	}


	public void setCamera_info(String camera_info) {
		this.camera_info = camera_info;
	}


	public String getPark_type() {
		return park_type;
	}


	public void setPark_type(String park_type) {
		this.park_type = park_type;
	}


	public String getArea_code() {
		return area_code;
	}


	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}


	public Integer getIs_expect() {
		return is_expect;
	}


	public void setIs_expect(Integer is_expect) {
		this.is_expect = is_expect;
	}


	public Integer getExpect_money() {
		return expect_money;
	}


	public void setExpect_money(Integer expect_money) {
		this.expect_money = expect_money;
	}


	public Integer getAllow_revoke_time() {
		return allow_revoke_time;
	}


	public void setAllow_revoke_time(Integer allow_revoke_time) {
		this.allow_revoke_time = allow_revoke_time;
	}


	public Integer getAllow_expect_time() {
		return allow_expect_time;
	}


	public void setAllow_expect_time(Integer allow_expect_time) {
		this.allow_expect_time = allow_expect_time;
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


	public long getPi_id() {
		return pi_id;
	}


	public void setPi_id(long pi_id) {
		this.pi_id = pi_id;
	}
	
}
