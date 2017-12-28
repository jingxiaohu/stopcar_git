package com.park.service.etc.etcapi;

/**
 * 交易参数
 *
 * <pre>
 *   参数	名称	类型长度	描述	是否必填	样例
 * userNo	用户号	String(32)	在系统存在的用户的唯一标识	是
 * userName	用户姓名	String(20)	用户姓名	是	苏二五
 * idNo	身份证号	String(18)	用户身份证号	是	44090119750219837X
 * ropAmt	圈存额度	String(20)	圈存金额，单位元	是
 * bankCard	银行卡卡号	String(19)	建设银行卡卡号	是	621700034000056xxxx
 *
 * </pre>
 * 全部必填
 *
 * @author zzy , Peter Wu
 */
public class ToRopeRequest extends BaseRequest {

  /**
   * 在系统存在的用户的唯一标识
   */
  private String userNo;
  /**
   * 用户姓名
   */
  private String userName;
  /**
   * 用户身份证号
   */
  private String idNo;
  /**
   * 圈存金额 单位元
   */
  private String ropAmt;
  /**
   * 建设银行卡卡号
   */
  private String bankCard;

  public String getUserNo() {
    return userNo;
  }

  public void setUserNo(String userNo) {
    this.userNo = userNo;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getIdNo() {
    return idNo;
  }

  public void setIdNo(String idNo) {
    this.idNo = idNo;
  }

  public String getRopAmt() {
    return ropAmt;
  }

  public void setRopAmt(String ropAmt) {
    this.ropAmt = ropAmt;
  }

  public String getBankCard() {
    return bankCard;
  }

  public void setBankCard(String bankCard) {
    this.bankCard = bankCard;
  }

}
