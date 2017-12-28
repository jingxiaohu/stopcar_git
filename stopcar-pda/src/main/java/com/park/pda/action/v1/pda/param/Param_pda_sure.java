package com.park.pda.action.v1.pda.param;

import com.park.mvc.action.v1.param.BaseParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 露天停车场的PDA更新用户自动支付
 *
 * @author jingxiaohu
 */
public class Param_pda_sure extends BaseParam {

  /********************接收参数区*************************/
  public long pi_id;//预约停车场主键ID
  public int pay_type;//支付类型 0:自动扣款（钱包）  1：现金支付 2：微信  3：银联  4：钱包 5:龙支付 6:支付宝  7:扫脸支付 8：指纹支付  9：指静脉支付
  public String car_code;//车牌号
  public int money;//金额（ 单位分）
  public String area_code;//省市区区域代码
  public String orderid;//我们的订单号  字符串
  public String escape_orderids;//其它订单号 （逃逸订单或者未交费订单）多个订单号 以逗号分割

  /*********************2017-4-13为了处理客户端离线数据的异步上传问题新增参数*******/
  public Long sync_time;//离线端入库时间或者出库时间  13位毫秒数
  public Integer is_sync;// 如果是异步上传  is_sync=1
  /*****************************************************************/
  //by jxh 2017-4-14 新增请求字段
  public int type;//处理类型  0:常规类型  1：免费分钟类型 2:免费车类型 3：包月车类型 4：租赁车类型

  //by jxh 2017-7-6 新增扫脸支付图片字段
  //用户人脸图片
  public MultipartFile face;
  //提交过来的file的名字
  public String faceFileName;
  //提交过来的file的MIME类型
  public String faceContentType;

  /**
   * 指纹
   */
  public String fingerprint;
  /**
   * 用户指静脉
   */
  public String finger_veno;

  /************************get set 方法区****************************/

  public String getFingerprint() {
    return fingerprint;
  }

  public void setFingerprint(String fingerprint) {
    this.fingerprint = fingerprint;
  }

  public String getFinger_veno() {
    return finger_veno;
  }

  public void setFinger_veno(String finger_veno) {
    this.finger_veno = finger_veno;
  }


  public String getCar_code() {
    return car_code;
  }


  public MultipartFile getFace() {
    return face;
  }


  public void setFace(MultipartFile face) {
    this.face = face;
  }


  public String getFaceFileName() {
    return faceFileName;
  }


  public void setFaceFileName(String faceFileName) {
    this.faceFileName = faceFileName;
  }


  public String getFaceContentType() {
    return faceContentType;
  }


  public void setFaceContentType(String faceContentType) {
    this.faceContentType = faceContentType;
  }


  public int getType() {
    return type;
  }


  public void setType(int type) {
    this.type = type;
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


  public String getEscape_orderids() {
    return escape_orderids;
  }


  public void setEscape_orderids(String escape_orderids) {
    this.escape_orderids = escape_orderids;
  }


  public String getOrderid() {
    return orderid;
  }


  public void setOrderid(String orderid) {
    this.orderid = orderid;
  }

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


  public int getPay_type() {
    return pay_type;
  }


  public void setPay_type(int pay_type) {
    this.pay_type = pay_type;
  }

  public void setCar_code(String car_code) {
    this.car_code = car_code;
  }


  public int getMoney() {
    return money;
  }


  public void setMoney(int money) {
    this.money = money;
  }


}
