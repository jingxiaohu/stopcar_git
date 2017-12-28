package com.park.pda.action.v1.finger.param;

import com.park.mvc.action.v1.param.BaseParam;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 指纹采集--用户修改密码
 * @author zyy
 */
public class Param_finger_changePassword extends BaseParam {

  public String loginName;  //登录帐号

  public String password;   //旧密码

  public String rePassword;   //新密码

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRePassword() {
    return rePassword;
  }

  public void setRePassword(String rePassword) {
    this.rePassword = rePassword;
  }
}
