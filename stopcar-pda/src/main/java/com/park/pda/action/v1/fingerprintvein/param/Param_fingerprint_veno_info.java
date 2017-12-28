package com.park.pda.action.v1.fingerprintvein.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 查询用户采集的信息
 *
 * @author zyy
 */
public class Param_fingerprint_veno_info extends BaseParam {

  private String car_code;    //车牌号

  public String getCar_code() {
    return car_code;
  }

  public void setCar_code(String car_code) {
    this.car_code = car_code;
  }
}
