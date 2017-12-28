package com.park.service.etc.etcapi;

/**
 * @author zzy
 */
public class StrikeBalRequest extends BaseRequest {

  /**
   * 原交易日期
   */
  private String oldTxdate;
  /**
   * 原交易第三方流水号
   */
  private String oldTransBankId;
  /**
   * 原圈存额度
   */
  private String oldRopAmt;

  public String getOldTxdate() {
    return oldTxdate;
  }

  public void setOldTxdate(String oldTxdate) {
    this.oldTxdate = oldTxdate;
  }

  public String getOldTransBankId() {
    return oldTransBankId;
  }

  public void setOldTransBankId(String oldTransBankId) {
    this.oldTransBankId = oldTransBankId;
  }

  public String getOldRopAmt() {
    return oldRopAmt;
  }

  public void setOldRopAmt(String oldRopAmt) {
    this.oldRopAmt = oldRopAmt;
  }

  @Override
  public String toString() {
    return "StrikeBalRequest [oldTxdate=" + oldTxdate + ", oldTransBankId=" + oldTransBankId
        + ", oldRopAmt="
        + oldRopAmt + "]";
  }


}
