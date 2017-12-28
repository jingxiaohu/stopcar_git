package com.park.service.etc.etcapi;

/**
 * 签约请求实体 所有字段必填
 *
 * @author zzy , Peter Wu
 */
public class ToRegisterRequest extends BaseRequest {

  /**
   * 功能号 标识该请求为签约还是解约，0为签约，1为解约
   * 长度 1
   */
  private String func;
  /**
   * 用户号  在系统存在的用户的唯一标识
   *
   * 长度 32
   */
  private String userNo;
  /**
   * 用户姓名
   *
   * 长度 20
   */
  private String userName;
  /**
   * 身份证号
   *
   * 长度 18
   */
  private String idNo;
  /**
   * 银行卡卡号
   *
   * 长度 19
   */
  private String bankCard;

  public String getFunc() {
    return func;
  }

  public void setFunc(String func) {
    this.func = func;
  }

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

  public String getBankCard() {
    return bankCard;
  }

  public void setBankCard(String bankCard) {
    this.bankCard = bankCard;
  }


}
