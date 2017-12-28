package com.park.pda.action.v1.finger.param;

import com.park.mvc.action.v1.param.BaseParam;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author Peter Wu
 */
public class Param_finger_userinfo extends BaseParam {

  /**
   * 车牌号
   */
  @NotBlank
  public String car_code;

  @NotNull
  public Integer finger_flag = 1; //用户指纹/指静脉标记 0:指静脉 1:指纹

  public String getCar_code() {
    return car_code;
  }

  public void setCar_code(String car_code) {
    this.car_code = car_code;
  }

  public Integer getFinger_flag() {
    return finger_flag;
  }

  public void setFinger_flag(Integer finger_flag) {
    this.finger_flag = finger_flag;
  }
}
