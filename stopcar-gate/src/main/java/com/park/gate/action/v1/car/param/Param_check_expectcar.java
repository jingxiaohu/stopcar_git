package com.park.gate.action.v1.car.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 检查该车牌号是否是预约车或者租赁车
 * @author jingxiaohu
 *
 */
public class Param_check_expectcar extends BaseParam {
	/********************接收参数区*************************/
	public String area_code;//省市县编号 140107  
	public long pi_id;//场地主键ID
	public String car_code;//车牌号 (第一期：车牌号---对应一个人)
	public int car_type;//车牌类型:车牌类型 0：未知车牌:、1：蓝牌小汽车、2：: 黑牌小汽车、3：单排黄牌、4：双排黄牌、 5： 警车车牌、6：武警车牌、7：个性化车牌、8：单 排军车牌、9：双排军车牌、10：使馆车牌、11： 香港进出中国大陆车牌、12：农用车牌、13：教 练车牌、14：澳门进出中国大陆车牌、15：双层 武警车牌、16：武警总队车牌、17：双层武警总 队车牌
	public int car_code_color;//车牌颜色 0：未知、1：蓝色、2：黄色、3：白色、 4：黑色、5：绿色
	/************************get set 方法区****************************/


	public String getArea_code() {
		return area_code;
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
	public String getCar_code() {
		return car_code;
	}
	public void setCar_code(String car_code) {
		this.car_code = car_code;
	}
	public int getCar_type() {
		return car_type;
	}
	public void setCar_type(int car_type) {
		this.car_type = car_type;
	}
	public int getCar_code_color() {
		return car_code_color;
	}
	public void setCar_code_color(int car_code_color) {
		this.car_code_color = car_code_color;
	}
	



	
	
	
}
