package com.park.gate.action.v1.monthfree.param;


import com.park.mvc.action.v1.param.BaseParam;

/**
 * @author Peter Wu
 */
public class MonthfreeParams extends BaseParam {
  //地址编码	
  private String area_code;
  //停车场主键ID
  private long pi_id;
  //道闸包月车辆记录JSON数组
  private String month_log;
  //道闸免费车辆记录JSON数组
  private String free_log;


  public String getArea_code() {
    return area_code;
  }

  public void setArea_code(String area_code) {
    this.area_code = area_code;
  }

  public long getPi_id() {
    return pi_id;
  }

  public void setPi_id(long pi_id) {
    this.pi_id = pi_id;
  }

  public String getMonth_log() {
    return month_log;
  }

  public void setMonth_log(String month_log) {
    this.month_log = month_log;
  }

  public String getFree_log() {
    return free_log;
  }

  public void setFree_log(String free_log) {
    this.free_log = free_log;
  }
}
