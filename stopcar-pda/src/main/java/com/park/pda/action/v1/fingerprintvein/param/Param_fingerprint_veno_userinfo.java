package com.park.pda.action.v1.fingerprintvein.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 查询用户采集的信息
 *
 * @author zyy
 */
public class Param_fingerprint_veno_userinfo extends BaseParam {

  private String ui_tel;    //手机号码

  public String getUi_tel() {
    return ui_tel;
  }

  public void setUi_tel(String ui_tel) {
    this.ui_tel = ui_tel;
  }

}
