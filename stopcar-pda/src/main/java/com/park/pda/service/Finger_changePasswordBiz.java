package com.park.pda.service;

import com.park.bean.Pda_userinfo;
import com.park.bean.ReturnDataNew;
import com.park.dao.Pda_userinfoDao;
import com.park.mvc.service.BaseBiz;
import com.park.pda.action.v1.finger.param.Param_finger_changePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 指纹采集--用户修改密码
 * @author zyy
 */
@Service
public class Finger_changePasswordBiz extends BaseBiz {

  @Autowired
  protected Pda_userinfoDao pda_userinfoDao;

  public void changePassword(ReturnDataNew returnData, Param_finger_changePassword param) throws Exception {
    String finger_userinfo = null;
    try {
      String sql = "select * from pda_userinfo where loginname=? and password=? limit 1";
      Pda_userinfo pda_userinfo = pda_userinfoDao.queryUniqueT(sql, Pda_userinfo.class, param.getLoginName(),param.getPassword());
      if(pda_userinfo == null){
        returnData.setReturnData(errorcode_data, "登录帐号或旧密码不正确", null);
        return;
      }
      /*if(pda_userinfo.getState() == 1){
        returnData.setReturnData(errorcode_data, "该登录帐号已锁定", null);
        return;
      }*/

      pda_userinfo.setPassword(param.getRePassword());
      int ret = pda_userinfoDao.updateByKey(pda_userinfo);
      if(ret < 1){
        returnData.setReturnData(errorcode_data, "密码修改失败", null);
        return;
      }

      returnData.setReturnData(errorcode_success, "密码修改成功", pda_userinfo);
      return;

    } catch (Exception e) {
      log.error("Finger_changePasswordBiz changePassword is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }
  }
}
