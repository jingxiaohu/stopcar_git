package com.park.pda.action.v1.fingerprintvein.param;

import apidoc.jxh.cn.TargetComment;
import com.park.mvc.action.v1.param.BaseParam;

/**
 * Created by zzy on 2017/8/30.
 */
public class FingerPrintVeionCheckSignReq extends BaseParam {
    @TargetComment(value = "指纹系统--用户资料管理主键",isnull = "否")
    private Long fu_id;     //指纹系统--用户资料管理主键

    @TargetComment(value = "用户唯一标识符",isnull = "否")
    private String fu_nd;    //唯一标识符

    @TargetComment(value = "指纹采集--用户银行卡管理主键",isnull = "否")
    private Long fub_id;    //指纹采集-------用户银行卡管理主键

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

    public Long getFub_id() {
        return fub_id;
    }

    public void setFub_id(Long fub_id) {
        this.fub_id = fub_id;
    }
}
