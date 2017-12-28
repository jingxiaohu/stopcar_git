package com.park.pda.action.v1.fingerprintvein.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 指纹系统--查询用户已经支付成功的订单流水
 * @author zyy
 */
public class Param_fingerprint_veno_userOrderList extends BaseParam {

    public String ui_tel;  //用户手机号码

    public String getUi_tel() {
        return ui_tel;
    }

    public void setUi_tel(String ui_tel) {
        this.ui_tel = ui_tel;
    }

}

