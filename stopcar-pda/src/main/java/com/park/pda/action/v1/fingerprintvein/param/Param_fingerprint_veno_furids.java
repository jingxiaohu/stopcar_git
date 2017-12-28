package com.park.pda.action.v1.fingerprintvein.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 查询用户采集的信息
 *
 * @author zyy
 */
public class Param_fingerprint_veno_furids extends BaseParam {

  private Long fu_id;    //指纹用户基本信息ID

  public Long getFu_id() {
    return fu_id;
  }

  public void setFu_id(Long fu_id) {
    this.fu_id = fu_id;
  }
}
