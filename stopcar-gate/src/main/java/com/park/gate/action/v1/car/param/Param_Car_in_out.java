package com.park.gate.action.v1.car.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 车辆入库出库记录
 *
 * @author jingxiaohu
 * @category record_car_in_out
 */
public class Param_Car_in_out extends BaseParam {

  public String area_code;//省市县编号 140107

  public Integer pi_id;//场地主键ID
  public long pd_id;//出入口设备主键ID
  public String car_code;//车牌号 (第一期：车牌号---对应一个人)
  public Integer is_enter;//入库或者出库 ：0：   入库   1：出库
  public String in_out;//出口或者入口 入口：enter  出口：exit
  public String in_out_code;//出入口编号 A /B/C
  public Integer car_type;//车牌类型:车牌类型 0：未知车牌:、1：蓝牌小汽车、2：: 黑牌小汽车、3：单排黄牌、4：双排黄牌、 5： 警车车牌、6：武警车牌、7：个性化车牌、8：单 排军车牌、9：双排军车牌、10：使馆车牌、11： 香港进出中国大陆车牌、12：农用车牌、13：教 练车牌、14：澳门进出中国大陆车牌、15：双层 武警车牌、16：武警总队车牌、17：双层武警总 队车牌
  public Integer car_code_color;//车牌颜色 0：未知、1：蓝色、2：黄色、3：白色、 4：黑色、5：绿色
  public int out_type;//入库/出库类型: (0:正常出入库 1：道闸本地临停出入库  2：道闸本地包月出入库   3：异常出入库   4：道闸本地免费车出入库   5:预约车辆出入库  6:租赁车辆出入库)
  public int is_local_month;//是否在服务器创建订单  0:创建   1：不创建
  //开闸
  public Integer is_cash;//是否现金支付 0：在线支付  1：现金支付

  public String order_id;//订单ID
  /*********************2017-4-13为了处理客户端离线数据的异步上传问题新增参数*******/
  public Long sync_time;//离线端入库时间或者出库时间  13位毫秒数
  public Integer is_sync;// 如果是异步上传  is_sync=1 
/*****************************************************************/

  /**
   * varchar(60)    智能磁场车位编号（政府部门统一分配）
   */
  public String gov_num;
  /**
   * 地磁异常强制入库
   */
  public boolean magnetic_force;

//--------------------------------------------

  public String getGov_num() {
    return gov_num;
  }

  public void setGov_num(String gov_num) {
    this.gov_num = gov_num;
  }

  public boolean isMagnetic_force() {
    return magnetic_force;
  }

  public void setMagnetic_force(boolean magnetic_force) {
    this.magnetic_force = magnetic_force;
  }

  public String getOrder_id() {
    return order_id;
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

  public void setOrder_id(String order_id) {
    this.order_id = order_id;
  }

  public Integer getIs_cash() {
    return is_cash;
  }

  public void setIs_cash(Integer is_cash) {
    this.is_cash = is_cash;
  }

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

  public long getPd_id() {
    return pd_id;
  }

  public void setPd_id(long pd_id) {
    this.pd_id = pd_id;
  }

  public String getCar_code() {
    return car_code;
  }

  public void setCar_code(String car_code) {
    this.car_code = car_code;
  }

  public Integer getIs_enter() {
    return is_enter;
  }

  public void setIs_enter(Integer is_enter) {
    this.is_enter = is_enter;
  }

  public String getIn_out() {
    return in_out;
  }

  public void setIn_out(String in_out) {
    this.in_out = in_out;
  }

  public String getIn_out_code() {
    return in_out_code;
  }

  public void setIn_out_code(String in_out_code) {
    this.in_out_code = in_out_code;
  }

  public Integer getCar_type() {
    return car_type;
  }

  public void setCar_type(Integer car_type) {
    this.car_type = car_type;
  }

  public Integer getCar_code_color() {
    return car_code_color;
  }

  public void setCar_code_color(Integer car_code_color) {
    this.car_code_color = car_code_color;
  }

  public int getOut_type() {
    return out_type;
  }

  public void setOut_type(int out_type) {
    this.out_type = out_type;
  }

  public int getIs_local_month() {
    return is_local_month;
  }

  public void setIs_local_month(int is_local_month) {
    this.is_local_month = is_local_month;
  }

}
