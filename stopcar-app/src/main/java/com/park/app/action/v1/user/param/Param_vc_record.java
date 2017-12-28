package com.park.app.action.v1.user.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 获取我的账户出入记录
 * @author jingxiaohu
 *
 */
public class Param_vc_record extends BaseParam {
	/********************接收参数区*************************/
	public int type;// 获取虚拟币行为类型  0:消耗和充值  1：消耗  2：充值
	public int page  = 1;
	public int size = 20;
	/************************get set 方法区****************************/



	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}



    
}
