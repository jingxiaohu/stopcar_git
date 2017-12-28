package com.park.service.etc.etcapi;

/**
 * 基础响应
 * <pre>
 *   参数	参数名称	类型长度	描述	是否必填	样例
 * transDate	交易日期	String(8)	交易日期，格式yyyyMMdd固定长度8位	是	20170425
 * transTime	交易时间	String(40)	HHmmss固定长度6位	是	174744
 * txChannel	发起方标示(渠道号)	String(4)	发起方标示(渠道号)	是	2001
 * bankSerial	银行流水号	String(16)	银行流水号（银行发起交易的请求报文和第三方发起交易的响应报文）	否，响应码为成功时必填
 * serial	第三方流水	String(16)	第三方流水号（第三方发起交易的请求报文和银行发起交易的响应报文），只允许数字	是	2017383508631266
 * respCode	响应码	String(5)	响应码，具体参见响应码对照表 是
 * respMess	响应信息	String(64)	响应描述信息	是	交易成功
 *
 * </pre>
 *
 * 响应码说明：
 * <pre>
 * 响应码	响应码说明	备注
 * 00000	成功	交易成功
 * E0001	无该学校信息	可能发送了错误的学校代码
 * E0002	帐户未签约或已撤销	校园卡签约状态不正常
 * E0003	转账失败	银行卡扣款失败
 * E0004	已签退不能交易	学校签约状态为签退
 * E0005	无此笔交易	输入了错误的冲正流水号
 * E0006	无符合条件数据	获取批量签约结果失败，可能输入错误的批次号。
 * E0007	错误的银行卡号	银行卡号不符合规范
 * E0008	证件号码不符	户名或证件号码不符
 * E0009	帐户余额不足
 * E0010	帐户状态异常
 * E0011	连接银行失败
 * E0090	系统错误，数据库错误
 * E0099	系统错误，其他错误
 *
 *
 * </pre>
 *
 * @author zzy , Peter Wu
 */
public class ETCResponse {

  /**
   * 交易日期，格式yyyyMMdd固定长度8位	是	20170425
   */
  private String transDate;
  /**
   * 交易时间	 HHmmss固定长度6位	是	174744
   */
  private String transTime;
  /**
   * 发起方标示(渠道号)	是	2001
   */
  private String txChannel;
  /**
   * 银行流水号（银行发起交易的请求报文和第三方发起交易的响应报文）	否，响应码为成功时必填
   */
  private String bankSerial;
  /**
   * 第三方流水号（第三方发起交易的请求报文和银行发起交易的响应报文），只允许数字	是	2017383508631266
   */
  private String serial;
  /**
   * 响应码，具体参见响应码对照表
   */
  private String respCode;
  /**
   * 响应描述信息
   */
  private String respMess;

  /**
   * @return 交易日期，格式yyyyMMdd固定长度8位	是	20170425
   */
  public String getTransDate() {
    return transDate;
  }

  public void setTransDate(String transDate) {
    this.transDate = transDate;
  }

  /**
   * @return 交易时间   HHmmss固定长度6位	是	174744
   */
  public String getTransTime() {
    return transTime;
  }

  public void setTransTime(String transTime) {
    this.transTime = transTime;
  }

  /**
   * @return 发起方标示(渠道号)  是	2001
   */
  public String getTxChannel() {
    return txChannel;
  }

  public void setTxChannel(String txChannel) {
    this.txChannel = txChannel;
  }

  /**
   * @return 银行流水号（银行发起交易的请求报文和第三方发起交易的响应报文）	否，响应码为成功时必填
   */
  public String getBankSerial() {
    return bankSerial;
  }

  public void setBankSerial(String bankSerial) {
    this.bankSerial = bankSerial;
  }

  /**
   * @return 第三方流水号（第三方发起交易的请求报文和银行发起交易的响应报文），只允许数字	是	2017383508631266
   */
  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  /**
   * @return 响应码，具体参见响应码对照表
   */
  public String getRespCode() {
    return respCode;
  }

  public void setRespCode(String respCode) {
    this.respCode = respCode;
  }

  /**
   * @return 响应描述信息
   */
  public String getRespMess() {
    return respMess;
  }

  public void setRespMess(String respMess) {
    this.respMess = respMess;
  }

  @Override
  public String toString() {
    return "{" +
        "transDate:'" + transDate + '\'' +
        ", transTime:'" + transTime + '\'' +
        ", txChannel:'" + txChannel + '\'' +
        ", bankSerial:'" + bankSerial + '\'' +
        ", serial:'" + serial + '\'' +
        ", respCode:'" + respCode + '\'' +
        ", respMess:'" + respMess + '\'' +
        '}';
  }

  //--------------------------------------------

  /**
   * @return 交易是否成功
   */
  public boolean isSucceed() {
    return "00000".equals(respCode);
  }

}
