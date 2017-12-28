package com.park.pda.action.v1.fingerveintest.param;

import apidoc.jxh.cn.TargetComment;
import com.park.mvc.action.v1.param.BaseParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 *
 * @author jingxiaohu
 */
public class Param_pdaSureTest extends BaseParam {

  @TargetComment(value = "用户手机号码",isnull = "否")
  private String tel;

  /**
   * 指纹
   */
  @TargetComment(value = "指纹信息",isnull = "否")
  private String fingerprint;
  /**
   * 用户指静脉
   */
  @TargetComment(value = "用户指静脉信息",isnull = "否")
  private String finger_veno;

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

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
}
