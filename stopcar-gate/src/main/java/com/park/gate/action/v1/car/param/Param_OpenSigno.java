package com.park.gate.action.v1.car.param;

import apidoc.jxh.cn.TargetComment;
import com.park.mvc.action.v1.param.BaseParam;

/**
 * 车辆出库开闸记录
 *
 * @author jingxiaohu
 */
public class Param_OpenSigno extends BaseParam {
  @TargetComment(value = "省市县编号 140107",isnull = "否",lenth = "6字符")
  public String area_code;//省市县编号 140107
  @TargetComment(value = "场地主键ID",isnull = "否")
  public Integer pi_id;//场地主键ID
  //开闸
  @TargetComment(value = "是否现金支付 0：在线支付  1：现金支付",isnull = "否")
  public Integer is_cash;//是否现金支付 0：在线支付  1：现金支付
  @TargetComment(value = "订单ID",isnull = "否")
  public String order_id;//订单ID
  /*********************2017-4-13为了处理客户端离线数据的异步上传问题新增参数*******/
  @TargetComment(value = "离线端入库时间或者出库时间  13位毫秒数",isnull = "否")
  public Long sync_time;//离线端入库时间或者出库时间  13位毫秒数
  @TargetComment(value = "如果是异步上传  is_sync=1",isnull = "否")
  public Integer is_sync;// 如果是异步上传  is_sync=1 
  /*********************************2017-8-9新增开闸类型参数*******************************************/
  @TargetComment(value = "是否开闸 0:未开闸 1：已正常放行开闸 2: 道闸手动放行",isnull = "否")
  public Integer is_open;


  public String getArea_code() {
    return area_code;
  }

  public void setArea_code(String area_code) {
    this.area_code = area_code;
  }

  public Integer getPi_id() {
    return pi_id;
  }

  public void setPi_id(Integer pi_id) {
    this.pi_id = pi_id;
  }


  public Integer getIs_cash() {
    return is_cash;
  }

  public void setIs_cash(Integer is_cash) {
    this.is_cash = is_cash;
  }

  public String getOrder_id() {
    return order_id;
  }

  public void setOrder_id(String order_id) {
    this.order_id = order_id;
  }

  public Long getSync_time() {
    return sync_time;
  }

  public void setSync_time(Long sync_time) {
    this.sync_time = sync_time;
  }

  public Integer getIs_sync() {
    return is_sync;
  }

  public void setIs_sync(Integer is_sync) {
    this.is_sync = is_sync;
  }

  public Integer getIs_open() {
    return is_open;
  }

  public void setIs_open(Integer is_open) {
    this.is_open = is_open;
  }
}
