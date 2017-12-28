package com.park.gate.service;

import java.util.Date;
import com.park.exception.QzException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.Client_gate_rule;
import com.park.bean.Park_info;
import com.park.bean.ReturnDataNew;
import com.park.dao.Client_gate_ruleDao;
import com.park.gate.action.v1.park.param.Param_client_gate_rule;
import com.park.gate.action.v1.park.param.Param_client_gate_ruleT;
import com.park.mvc.service.BaseBiz;
import com.park.util.RequestUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户端--道闸--规则记录 管理
 * @author zyy
 */
@Service
public class ClientGateRuleBiz extends BaseBiz {
	@Autowired
	protected Client_gate_ruleDao client_gate_ruleDao;

	/**
	 * 添加客户端道闸规则记录
	 * @param returnData
	 * @param param
	 */
	public void add_client_gate_rule(ReturnDataNew returnData, Param_client_gate_rule param)  {
	    try {
	    	//校验停车场是否存在
	        Park_info parkInfo = returnParkInfo(param.getPi_id(),param.getArea_code());
	        if(parkInfo==null){
	        	returnData.setReturnData(errorcode_data, "该停车场信息不存在", "");
	            return;
	        }
	    	//校验客户端道闸规则信息是否存在
	    	String sql="select * from client_gate_rule where client_ruleid=? and pi_id=? and area_code=? and is_del=0";
	    	Client_gate_rule client_gate_rule = client_gate_ruleDao.queryUniqueT(sql, Client_gate_rule.class,param.getClient_ruleid(),param.getPi_id(),param.getArea_code());
	    	if(client_gate_rule != null){
	    		 returnData.setReturnData(errorcode_data, "该停车场客户端道闸规则信息已存在", null);
	    	     return;
	    	}
	    	
	    	client_gate_rule = new Client_gate_rule();
	    	Date date = new Date();
	    	//添加客户端道闸规则记录
	    	client_gate_rule.setClient_ruleid(param.getClient_ruleid());
	    	client_gate_rule.setPi_id(param.getPi_id());
	    	client_gate_rule.setArea_code(param.getArea_code());
	    	client_gate_rule.setGroup_id(param.getGroup_id());
	    	client_gate_rule.setType(param.getType());
	    	client_gate_rule.setMoney(param.getMoney());
	    	client_gate_rule.setState(param.getState());
	    	client_gate_rule.setStr_json(param.getStr_json());
	    	client_gate_rule.setIntro(param.getIntro());
	    	client_gate_rule.setPermit_time(param.getPermit_time());
	    	client_gate_rule.setCtime(date);
	    	client_gate_rule.setUtime(date);
	    	client_gate_rule.setClient_loginname(param.getClient_loginname());
	        
	        int cgr_id = client_gate_ruleDao.insert(client_gate_rule);
	        if (cgr_id < 1) {
	          returnData.setReturnData(errorcode_data, "客户端道闸规则记录添加失败", null);
	          return;
	        }
			client_gate_rule.setCgr_id(cgr_id);
	        //返回数据
	        returnData.setReturnData(errorcode_success, "客户端道闸规则记录添加成功", client_gate_rule);
	        return;
	    } catch (Exception e) {
	        log.error("ClientGateRuleBiz add_client_gate_rule is error", e);
	        returnData.setReturnData(errorcode_systerm, "system is error", null);
	        return;
	    }
   }

	/**
	 * 修改客户端道闸规则记录
	 * @param returnData
	 * @param param
	 */
	@Transactional(rollbackFor = QzException.class)
	public void update_client_gate_rule(ReturnDataNew returnData, Param_client_gate_rule param)  throws QzException{
		try {
	    	//校验停车场是否存在
	        Park_info parkInfo = returnParkInfo(param.getPi_id(),param.getArea_code());
	        if(parkInfo==null){
	        	returnData.setReturnData(errorcode_data, "该停车场信息不存在", "");
	            return;
	        }
	    	//校验停车场规则信息是否存在
	    	Client_gate_rule client_gate_rule = getClientGateRule(returnData,param);
	    	if(client_gate_rule == null){
	    		 returnData.setReturnData(errorcode_data, "该停车场客户端道闸规则信息不存在", "");
	    	     return;
	    	}

	    	//修改客户端道闸规则记录
	    	if(!RequestUtil.checkObjectBlank(param.getGroup_id())){
	    		client_gate_rule.setGroup_id(param.getGroup_id());
	    	}
	    	if(!RequestUtil.checkObjectBlank(param.getType())){
	    		client_gate_rule.setType(param.getType());
	    	}
	    	if(!RequestUtil.checkObjectBlank(param.getMoney())){
	    		client_gate_rule.setMoney(param.getMoney());
	    	}
	    	if(!RequestUtil.checkObjectBlank(param.getState())){
	    		client_gate_rule.setState(param.getState());
	    	}
	    	if(!RequestUtil.checkObjectBlank(param.getStr_json())){
	    		client_gate_rule.setStr_json(param.getStr_json());
	    	}
	    	if(!RequestUtil.checkObjectBlank(param.getIntro())){
	    		client_gate_rule.setIntro(param.getIntro());
	    	}
	    	if(!RequestUtil.checkObjectBlank(param.getPermit_time())){
	    		client_gate_rule.setPermit_time(param.getPermit_time());
	    	}
	    	if(!RequestUtil.checkObjectBlank(param.getClient_loginname())){
	    		client_gate_rule.setClient_loginname(param.getClient_loginname());
	    	}
	    	client_gate_rule.setUtime(new Date());

	    	//修改客户端道闸规则记录
			int id = client_gate_ruleDao.updateByKey(client_gate_rule);
			if(id < 1){
				returnData.setReturnData(errorcode_data, "客户端道闸规则记录修改失败", null);
				return;
			}

	        //返回数据
	        returnData.setReturnData(errorcode_success, "客户端道闸规则记录修改成功", client_gate_rule);
	        return;
	    } catch (Exception e) {
	        log.error("ClientGateRuleBiz update_client_gate_rule is error", e);
	        returnData.setReturnData(errorcode_systerm, "system is error", null);
	        return;
	    }
	}

	/**
	 * 逻辑删除客户端道闸规则记录
	 * @param returnData
	 * @param
	 */
	public void delete_client_gate_rule(ReturnDataNew returnData,Param_client_gate_ruleT param) {
		try {
	    	//校验停车场规则信息是否存在
	    	String sql="select * from client_gate_rule where client_ruleid=? and pi_id=? and area_code=? and is_del=0";
	    	Client_gate_rule client_gate_rule = client_gate_ruleDao.queryUniqueT(sql, Client_gate_rule.class,param.getClient_ruleid(),param.getPi_id(),param.getArea_code());
	    	if(client_gate_rule == null){
	    		 returnData.setReturnData(errorcode_data, "该停车场客户端道闸规则信息不存在或已删除", "");
	    	     return;
	    	}
	    	
	    	Date date = new Date();
	    	//逻辑删除客户端道闸规则记录
	    	client_gate_rule.setIs_del(1);
	    	client_gate_rule.setUtime(date);
	        
	        int ret = client_gate_ruleDao.updateByKey(client_gate_rule);
	        if (ret < 1) {
	          returnData.setReturnData(errorcode_data, "客户端道闸规则记录删除失败", null);
	          return;
	        }
	        
	        //返回数据
	        returnData.setReturnData(errorcode_success, "客户端道闸规则记录删除成功", null);
	        return;
	    } catch (Exception e) {
	        log.error("ClientGateRuleBiz delete_client_gate_rule is error", e);
	        returnData.setReturnData(errorcode_systerm, "system is error", null);
	        return;
	    }
	}
	
	/**
	 * 根据ID查询客户端道闸规则记录
	 * @param returnData
	 * @param
	 */
	@TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
	public void get_client_gate_rule_byId(ReturnDataNew returnData,Param_client_gate_ruleT param) {
		try {
			//校验停车场规则信息是否存在
			String sql="select * from client_gate_rule where client_ruleid=? and pi_id=? and area_code=? and is_del=0";
			Client_gate_rule client_gate_rule = client_gate_ruleDao.queryUniqueT(sql, Client_gate_rule.class,param.getClient_ruleid(),param.getPi_id(),param.getArea_code());
			if(client_gate_rule == null){
				returnData.setReturnData(errorcode_data, "该停车场客户端道闸规则信息不存在或已删除", "");
				return;
			}
			
			//返回数据
			returnData.setReturnData(errorcode_success, "客户端道闸规则记录查询成功", client_gate_rule);
			return;
		} catch (Exception e) {
			log.error("ClientGateRuleBiz get_client_gate_rule_byId is error", e);
			returnData.setReturnData(errorcode_systerm, "system is error", null);
			return;
		}
	}

	/**
	 * 根据ID查询客户端道闸规则记录
	 * @param returnData
	 * @param
	 */
	public Client_gate_rule getClientGateRule(ReturnDataNew returnData,Param_client_gate_rule param) {
		try {
			String sql="select * from client_gate_rule where client_ruleid=? and pi_id=? and area_code=?";
			Client_gate_rule client_gate_rule = client_gate_ruleDao.queryUniqueT(sql, Client_gate_rule.class,param.getClient_ruleid(),param.getPi_id(),param.getArea_code());
			return client_gate_rule;
		} catch (Exception e) {
			log.error("ClientGateRuleBiz getClientGateRule is error", e);
			returnData.setReturnData(errorcode_systerm, "system is error", null);
			return null;
		}
	}

}
