package com.park.pda.action.v1.fingerprintcollection.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 解除签约请求参数
 * Created by zzy on 2017/7/21.
 */
public class FingerRemoveSignedReq extends BaseParam {
    private Long fu_id;   //指纹系统--用户资料管理主键
    //private String fu_nd;   //唯一标识符

    public Long getFu_id() {
        return fu_id;
    }

    public void setFu_id(Long fu_id) {
        this.fu_id = fu_id;
    }

}
