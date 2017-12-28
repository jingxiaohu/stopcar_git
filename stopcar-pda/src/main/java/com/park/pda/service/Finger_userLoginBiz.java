package com.park.pda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.park.bean.Pda_userinfo;
import com.park.bean.ReturnDataNew;
import com.park.dao.Pda_userinfoDao;
import com.park.mvc.service.BaseBiz;
import com.park.pda.action.v1.finger.param.Param_finger_userLogin;

/**
 * 指纹采集--用户登录
 * @author zyy
 *
 */
@Service
public class Finger_userLoginBiz extends BaseBiz {
	@Autowired
	protected Pda_userinfoDao pda_userinfoDao;
	
	/**
	 * 指纹采集--用户登录
	 * @param returnData
	 * @param param
	 */
	public void pda_user_login(ReturnDataNew returnData,Param_finger_userLogin param) {
		try {
			//进行验证码的验证
		    String sql = "select * from pda_userinfo where loginname=? and password=? limit 1";
		    Pda_userinfo pda_userinfo = pda_userinfoDao.queryUniqueT(sql, Pda_userinfo.class, param.getLoginname(),param.getPassword());
		    if(pda_userinfo == null){
		    	returnData.setReturnData(errorcode_data, "登录帐号或密码不正确", null);
		    	return;
		    }
		    if(pda_userinfo.getState() == 1){
		    	returnData.setReturnData(errorcode_data, "该登录帐号已锁定", null);
		    	return;
		    }
		    returnData.setReturnData(errorcode_success, "登录成功", pda_userinfo);
		    return;
		} catch (Exception e) {
			log.error("Finger_userLoginBiz pda_user_login is error", e);
		    returnData.setReturnData(errorcode_systerm, "system is error", null);
		    return;
		}
	}

}
