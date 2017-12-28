package com.park.gate.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.bean.Park_info;
import com.park.bean.Park_rule;
import com.park.bean.ReturnDataNew;
import com.park.dao.Park_ruleDao;
import com.park.gate.action.v1.park.param.Param_park_rule;
import com.park.mvc.service.BaseBiz;
import com.park.util.RequestUtil;

/**
 * 停车场-特殊规则信息映射
 * @author zyy
 */
@Service
@Deprecated
// by jxh 2017-7-17 该功能后续 也许会终止
public class GateParkruleBiz extends BaseBiz {
	@Autowired
	protected Park_ruleDao park_ruleDao;

	/**
	 * 添加停车场-特殊规则信息
	 * @param returnData
	 * @param param(pi_id,area_code,json_array:规则JSONArray数组)
	 */
	public void add_parkrule(ReturnDataNew returnData, Param_park_rule param)  {
    // TODO Auto-generated method stub
    try {
    	//校验停车场是否存在
        Park_info parkInfo = returnParkInfo(param.getPi_id(),param.getArea_code());
        if(parkInfo==null){
        	returnData.setReturnData(errorcode_data, "该停车场信息不存在", "");
            return;
        }
    	//校验停车场规则信息是否存在
    	String ruleSql="select * from park_rule where pi_id=? and area_code=? and json_array=? limit 1";
    	Park_rule park_rule = getMySelfService().queryUniqueT(ruleSql, Park_rule.class, param.getPi_id(),param.getArea_code(),param.getJson_array());
    	if(park_rule != null){
    		 returnData.setReturnData(errorcode_data, "该停车场规则信息已存在", "");
    	     return;
    	}
    	
    	park_rule = new Park_rule();
    	String pr_nd = RequestUtil.getUUID();
    	Date date = new Date();

    	park_rule.setPr_nd(pr_nd);
    	park_rule.setPi_id(param.getPi_id());
    	park_rule.setArea_code(param.getArea_code());
    	park_rule.setState(1);
    	park_rule.setJson_array(param.getJson_array());
    	park_rule.setCtime(date);
    	park_rule.setUtime(date);
    	park_rule.setNote("");
        
        int ret = park_ruleDao.insert(park_rule);
        if (ret < 1) {
          returnData.setReturnData(errorcode_data, "停车场规则信息添加失败", null);
          return;
        }
        
        //返回数据
        returnData.setReturnData(errorcode_success, "停车场规则信息添加成功", park_rule);
        return;
    } catch (Exception e) {
        // TODO Auto-generated catch block
        log.error("GateParkruleBiz add_parkrule is error", e);
        returnData.setReturnData(errorcode_systerm, "system is error", null);
        return;
    }
  }

	/**
	 * 修改停车场-特殊规则信息
	 * @param returnData
	 * @param param(pi_id,area_code,json_array:规则JSONArray数组)
	 */
	public void update_parkrule(ReturnDataNew returnData, Param_park_rule param) {
		// TODO Auto-generated method stub
		try {
			//校验停车场是否存在
	        Park_info parkInfo = returnParkInfo(param.getPi_id(),param.getArea_code());
	        if(parkInfo==null){
	        	returnData.setReturnData(errorcode_data, "该停车场信息不存在", "");
	            return;
	        }
			
			//校验停车场规则信息是否存在
	    	String ruleSql="select * from park_rule where pi_id=? and area_code=?  ";
	    	Park_rule park_rule = getMySelfService().queryUniqueT(ruleSql, Park_rule.class, param.pi_id,param.area_code);
	    	if(park_rule == null){
	    		returnData.setReturnData(errorcode_data, "该停车场规则信息不存在", "");
	    		return;
	    	}
	    	
	    	Date date = new Date();

	    	park_rule.setJson_array(param.getJson_array());
	    	park_rule.setUtime(date);
	        
	        int ret = park_ruleDao.updateByKey(park_rule);
	        if (ret < 1) {
	            returnData.setReturnData(errorcode_data, "停车场规则信息修改失败", null);
	            return;
	        }
	        
	        //返回数据
	        returnData.setReturnData(errorcode_success, "停车场规则信息修改成功", park_rule);
	        return;
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        log.error("GateParkruleBiz update_parkrule is error", e);
	        returnData.setReturnData(errorcode_systerm, "system is error", null);
	        return;
	    }
	}

	/**
	 * 查询停车场-特殊规则信息
	 * @param returnData
	 * @param pi_id
	 * @param area_code
	 */
	public void get_parkrule(ReturnDataNew returnData, Long pi_id, String area_code) {
		// TODO Auto-generated method stub
		try {
			//校验停车场是否存在
	        Park_info parkInfo = returnParkInfo(pi_id,area_code);
	        if(parkInfo==null){
	        	returnData.setReturnData(errorcode_data, "该停车场信息不存在", "");
	            return;
	        }
	    	
	    	String sql = "select * from park_rule where pi_id=? and area_code=? ";
	    	Park_rule park_rule = getMySelfService().queryUniqueT(sql, Park_rule.class,pi_id,area_code);
	    	if(park_rule==null){
	    		returnData.setReturnData(errorcode_success, "未获取到数据", park_rule);
	            return;
	    	}
	        
	        //返回数据
	        returnData.setReturnData(errorcode_success, "读取停车场规则信息成功", park_rule);
	        return;
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        log.error("GateParkruleBiz get_parkrule is error", e);
	        returnData.setReturnData(errorcode_systerm, "system is error", null);
	        return;
	    }
	}


}
