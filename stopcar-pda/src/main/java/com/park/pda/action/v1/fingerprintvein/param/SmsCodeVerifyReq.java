package com.park.pda.action.v1.fingerprintvein.param;

import apidoc.jxh.cn.TargetComment;
import com.park.mvc.action.v1.param.BaseParam;

/**
 * Created by zzy on 2017/8/29.
 */
public class SmsCodeVerifyReq extends BaseParam {
    @TargetComment(value = "手机验证码", isnull = "否")
    private String verify_code;   //手机验证码    N

    @TargetComment(value = "手机号码ui_tel+随机码v_code的MD5", isnull = "否")
    private String verify_list;//由发送验证码接口或者重新发送验证码接口返回的verify_list参数的值   N

    @TargetComment(value = "固定参数：1：注册 2：重置密码 3:重置绑定电话号码  4:指纹静脉短信验证", isnull = "否")
    private String vclass;//固定参数：1：注册 2：重置密码 3:重置绑定电话号码  4：绑定银行卡  5:获取指纹   N

    @TargetComment(value = "用户手机号码", isnull = "否")
    private String ui_tel;  // 用户手机号码   N

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

    public String getUi_tel() {
        return ui_tel;
    }

    public void setUi_tel(String ui_tel) {
        this.ui_tel = ui_tel;
    }

}
