package com.park.pda.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.park.bean.Park_hardware;
import com.park.bean.Park_info;
import com.park.bean.ReturnDataNew;
import com.park.dao.Park_hardwareDao;
import com.park.mvc.service.BaseBiz;
import com.park.util.MD5;
import com.park.util.RequestUtil;

/**
 * 停车场硬件设备校验登录MAC地址，并返回停车场信息。第一次登录时初始化 MAC
 * @author zyy
 */
@Service
public class PDAParkHardwareBiz extends BaseBiz {

	@Autowired
	protected Park_hardwareDao park_hardwareDao;

	 /**
	   * 停车场硬件设备校验登录MAC地址，并返回停车场信息
	   */
	public void getparkinfo_by_hardware_mac(ReturnDataNew returnData,String ph_mac,String ph_loginname,String ph_password) {
	    // TODO Auto-generated method stub
	    try {
	    	String sql = "select * from park_hardware where ph_loginname=? and ph_password=? limit 1 ";
	        Park_hardware park_hardware = getMySelfService().queryUniqueT(sql, Park_hardware.class, ph_loginname, ph_password);
	        if (park_hardware == null) {
	        	returnData.setReturnData(errorcode_data, "登录帐号或密码不正确", null);
        		return;
	        }
	        //检查硬件设备MAC地址(ph_mac)是否存在  by zyy 20170801
				if(park_hardware.getPh_mac() != null  && StringUtils.trim(park_hardware.getPh_mac()).length()>0){
					if(!park_hardware.getPh_mac().equals(ph_mac)){
						returnData.setReturnData(errorcode_data, "硬件设备MAC地址(ph_mac)不一致", null);
						return;
					}
				}else{
					park_hardware.setPh_mac(ph_mac);
				}
	        //更新登录token
	        park_hardware.setToken(RequestUtil.getUUID());

	        int ret = park_hardwareDao.updateByKey(park_hardware);
	        if (ret < 1) {
	            returnData.setReturnData(errorcode_data, "硬件设备MAC地址初始化失败", "");
	            return;
	        }

	        //获取停车场信息
	        Park_info parkInfo = returnParkInfo(park_hardware.getPi_id(),park_hardware.getArea_code());
	        if(parkInfo==null){
	        	returnData.setReturnData(errorcode_data, "停车场信息不存在", "");
	            return;
	        }

	        //返回数据组装
	        JSONObject returnObj = new JSONObject();
	        park_hardware.setPh_password("");
	        returnObj.put("park_info", parkInfo);
	        returnObj.put("park_hardware_info", park_hardware);


	        returnData.setReturnData(errorcode_success, "根据硬件设备mac地址获取停车场信息成功", returnObj);
	        return;
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        log.error("PDAParkHardwareBiz getparkinfo_by_hardware_mac is error", e);
	        returnData.setReturnData(errorcode_systerm, "system is error", null);
	        return;
	    }
	  }

}
