package com.park.app.action.v1.car.param;

import org.springframework.web.multipart.MultipartFile;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 用户添加或者删除绑定车牌号
 * @author jingxiaohu
 *
 */
public class Param_bindcar extends BaseParam {
	public  String car_code;//车牌号
	public int act;// 1:添加车牌 2：删除车牌
	
	
	//用户更新车辆信息
	public long uc_id;//表主键ID
	public MultipartFile lience;//行驶证照片
	public String lienceFileName;//行驶证照片 文件名称
    //提交过来的file的MIME类型
	public String lienceContentType;
	public String car_brand;//车辆品牌
	public Integer uc_color;//车辆颜色:0 未定  1：黑色 2：白色 3：银色 4：金色 5：红色 6 ：黄色 7：绿色 8：蓝色 9：橙色 10：灰色
	public String run_code;//行驶证号码
	
	
	
	
	
	public String getRun_code() {
		return run_code;
	}
	public void setRun_code(String run_code) {
		this.run_code = run_code;
	}
	public String getCar_code() {
		return car_code;
	}
	public void setCar_code(String car_code) {
		this.car_code = car_code;
	}
	public int getAct() {
		return act;
	}
	public void setAct(int act) {
		this.act = act;
	}
	public long getUc_id() {
		return uc_id;
	}
	public void setUc_id(long uc_id) {
		this.uc_id = uc_id;
	}
	public String getLienceFileName() {
		return lienceFileName;
	}
	public void setLienceFileName(String lienceFileName) {
		this.lienceFileName = lienceFileName;
	}
	public String getLienceContentType() {
		return lienceContentType;
	}
	public void setLienceContentType(String lienceContentType) {
		this.lienceContentType = lienceContentType;
	}
	public String getCar_brand() {
		return car_brand;
	}
	public MultipartFile getLience() {
		return lience;
	}
	public void setLience(MultipartFile lience) {
		this.lience = lience;
	}
	public void setCar_brand(String car_brand) {
		this.car_brand = car_brand;
	}
	public Integer getUc_color() {
		return uc_color;
	}
	public void setUc_color(Integer uc_color) {
		this.uc_color = uc_color;
	}
	
}
