package com.park.pda.action.v1.fingerprintcollection.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 验证一分钱是否支付成功
 * Created by zzy on 2017/7/20.
 */
public class FingerPrintCheckSignReq extends BaseParam {
    private Long fu_id;     //指纹系统--用户资料管理主键
    private String fu_nd;    //唯一标识符

    public Long getFu_id() {
        return fu_id;
    }

    public void setFu_id(Long fu_id) {
        this.fu_id = fu_id;
    }

    public String getFu_nd() {
        return fu_nd;
    }

    public void setFu_nd(String fu_nd) {
        this.fu_nd = fu_nd;
    }
}
