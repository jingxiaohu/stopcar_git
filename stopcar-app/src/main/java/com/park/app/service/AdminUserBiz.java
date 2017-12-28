package com.park.app.service;

import com.park.bean.Organization_user;
import com.park.bean.ReturnDataNew;
import com.park.mvc.service.BaseBiz;
import org.springframework.stereotype.Service;

/**
 * @author jingxiaohu
 */
@Service
public class AdminUserBiz extends BaseBiz {


  public void ReturnAdminlogin(ReturnDataNew returnData, int dtype,
      String loginname, String password) {
    // TODO Auto-generated method stub dtype,tel,password,tel_version,ip
    try {
      String sql = "SELECT * FROM organization_user WHERE user_id=? AND password=? AND status=1";

      Organization_user adminuser = getMySelfService()
          .queryUniqueT(sql, Organization_user.class, loginname, password);
      if (adminuser == null) {
        returnData.setReturnData(errorcode_data, "您还没有注册!", null);
        return;
      }
      if (!password.equalsIgnoreCase(adminuser.getPassword())) {
        //密码不正确
        returnData.setReturnData(errorcode_data, "密码错误", null);
        return;
      }
      //返回数据
      adminuser.setPassword("");
      returnData.setReturnData(errorcode_success, "登录成功", adminuser);
      return;

    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("AdminUserBiz ReturnAdminlogin is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }

  }

  /********************************************下面是返回当前版本是否是全镜像还是增量版本*****************************************************/
}
