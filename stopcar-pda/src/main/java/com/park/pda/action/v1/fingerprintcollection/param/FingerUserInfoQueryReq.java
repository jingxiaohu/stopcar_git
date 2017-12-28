package com.park.pda.action.v1.fingerprintcollection.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 信息查询请求参数
 * Created by zzy on 2017/7/20.
 */
public class FingerUserInfoQueryReq extends BaseParam{
    private String ui_tel;    //手机号
    private String verify_code;  //手机验证码
    private String verify_list;   //MD5
    private String vclass ;//

    public String getUi_tel() {
        return ui_tel;
    }

    public void setUi_tel(String ui_tel) {
        this.ui_tel = ui_tel;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }

    public String getVerify_list() {
        return verify_list;
    }

    public void setVerify_list(String verify_list) {
        this.verify_list = verify_list;
    }

    public String getVclass() {
        return vclass;
    }

    public void setVclass(String vclass) {
        this.vclass = vclass;
    }
}
