package com.park.service.etc.etcapi;

/**
 * 请求基础
 * 所有字段必填
 * <pre>
 *   参数	参数名称	类型长度	描述	是否必填	样例
 * transDate	交易日期	String(8)	yyyyMMdd固定长度8位	是	20170425
 * transTime	交易时间	String(6)	HHmmss固定长度6位	是	174744
 * serial	第三方流水	String(16)	第三方流水号（第三方发起交易的请求报文和银行发起交易的响应报文），只允许数字	是	2017383508631266
 * </pre>
 *
 * @author zzy , Peter Wu
 */
public class BaseRequest {

  /**
   * 交易日期	String(8)	yyyyMMdd固定长度8位	是	20170425
   */
  private String transDate;
  /**
   * 交易时间	String(6)	HHmmss固定长度6位	是	174744
   */
  private String transTime;
  /**
   * 第三方流水	String(16)	第三方流水号（第三方发起交易的请求报文和银行发起交易的响应报文），只允许数字	是	2017383508631266
   */
  private String serial;

  public String getTransDate() {
    return transDate;
  }

  public void setTransDate(String transDate) {
    this.transDate = transDate;
  }

  public String getTransTime() {
    return transTime;
  }

  public void setTransTime(String transTime) {
    this.transTime = transTime;
  }

  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }


}
