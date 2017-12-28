package com.park.gate.action.v1.park.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 新增/更新停车场规则
 * @author jingxiaohu
 *
 */
public class Param_charging_rule extends BaseParam {
	/********************接收参数区*************************/
	public String area_code;//省市县编号 140107
	
	public Long pi_id;//场地主键ID
	public Integer start_price;//起步价（RMB 单位 分）
	public Integer start_time;//起步时长（单位 分钟）
	public Integer charging;//计费价(RMB  单位分)
	public Integer charging_time;//计费时长（单位 分钟）
	public Integer month_price;//包月价格(单位分)
	public Integer month_time;//包月时长(天)
	public String permit_time;//准入时间段17:00-08:30
	public String timeout_info;//超时费率(首停2小时3元，之后1元/小时)
	public Integer rcr_type;//停车类型 0：普通车位停车 1：时间段包月停车
	public Integer rcr_state;// 是否有效  0：有效 1：无效
	public Integer rcr_discount;//是否可以使用优费券 : 0： 可以使用 1：无法使用
	public String car_displacement;//车辆排量
	public Integer car_type;//车牌类型:车牌类型 0：未知车牌:、1：蓝牌小汽车、2：: 黑牌小汽车、3：单排黄牌、4：双排黄牌、 5： 警车车牌、6：武警车牌、7：个性化车牌、8：单 排军车牌、9：双排军车牌、10：使馆车牌、11： 香港进出中国大陆车牌、12：农用车牌、13：教 练车牌、14：澳门进出中国大陆车牌、15：双层 武警车牌、16：武警总队车牌、17：双层武警总 队车牌
	public Integer car_code_color;//车牌颜色 0：未知、1：蓝色、2：黄色、3：白色、 4：黑色、5：绿色
	public Integer is_time_bucket;//是否按时间段收费 0:不按时间段收费 1：按时间段收费
	public String time_bucket;//时间段收费  例如：白天 9：00-12：00 每小时2元 
	public Integer roadside_type;//占道停车是否按次数收费 0:按时间收费 1：按次数收费
	//更新规则的时候使用
	 public long rcr_id;//规则表主键ID

	 /************************get set 方法区****************************/
	public String getArea_code() {
		return area_code;
	}


	public Integer getRoadside_type() {
		return roadside_type;
	}


	public void setRoadside_type(Integer roadside_type) {
		this.roadside_type = roadside_type;
	}


	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}


	public Long getPi_id() {
		return pi_id;
	}


	public void setPi_id(Long pi_id) {
		this.pi_id = pi_id;
	}


	public Integer getStart_price() {
		return start_price;
	}


	public void setStart_price(Integer start_price) {
		this.start_price = start_price;
	}


	public Integer getStart_time() {
		return start_time;
	}


	public void setStart_time(Integer start_time) {
		this.start_time = start_time;
	}


	public Integer getCharging() {
		return charging;
	}


	public void setCharging(Integer charging) {
		this.charging = charging;
	}


	public Integer getCharging_time() {
		return charging_time;
	}


	public void setCharging_time(Integer charging_time) {
		this.charging_time = charging_time;
	}


	public Integer getMonth_price() {
		return month_price;
	}


	public void setMonth_price(Integer month_price) {
		this.month_price = month_price;
	}


	public Integer getMonth_time() {
		return month_time;
	}


	public void setMonth_time(Integer month_time) {
		this.month_time = month_time;
	}


	public String getPermit_time() {
		return permit_time;
	}


	public void setPermit_time(String permit_time) {
		this.permit_time = permit_time;
	}


	public String getTimeout_info() {
		return timeout_info;
	}


	public void setTimeout_info(String timeout_info) {
		this.timeout_info = timeout_info;
	}


	public Integer getRcr_type() {
		return rcr_type;
	}


	public void setRcr_type(Integer rcr_type) {
		this.rcr_type = rcr_type;
	}


	public Integer getRcr_state() {
		return rcr_state;
	}


	public void setRcr_state(Integer rcr_state) {
		this.rcr_state = rcr_state;
	}


	public Integer getRcr_discount() {
		return rcr_discount;
	}


	public void setRcr_discount(Integer rcr_discount) {
		this.rcr_discount = rcr_discount;
	}


	public String getCar_displacement() {
		return car_displacement;
	}


	public void setCar_displacement(String car_displacement) {
		this.car_displacement = car_displacement;
	}


	public Integer getCar_type() {
		return car_type;
	}


	public void setCar_type(Integer car_type) {
		this.car_type = car_type;
	}


	public Integer getCar_code_color() {
		return car_code_color;
	}


	public void setCar_code_color(Integer car_code_color) {
		this.car_code_color = car_code_color;
	}


	public Integer getIs_time_bucket() {
		return is_time_bucket;
	}


	public void setIs_time_bucket(Integer is_time_bucket) {
		this.is_time_bucket = is_time_bucket;
	}


	public String getTime_bucket() {
		return time_bucket;
	}


	public void setTime_bucket(String time_bucket) {
		this.time_bucket = time_bucket;
	}


	public long getRcr_id() {
		return rcr_id;
	}


	public void setRcr_id(long rcr_id) {
		this.rcr_id = rcr_id;
	}


	
}
