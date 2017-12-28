package com.park.pda.action.v1.fingerprintvein.param;

import apidoc.jxh.cn.TargetComment;
import com.park.mvc.action.v1.param.BaseParam;

import javax.validation.constraints.NotNull;

/**
 * Created by zzy on 2017/8/30.
 */
public class UserCarCodeUnBindReq extends BaseParam {
    @TargetComment(value = "用户指纹采集和绑卡表外键（finger_userinfo）ID",isnull = "否")
    @NotNull
    private Long fuc_id;  //用户指纹采集和绑卡表外键（finger_userinfo）ID

    public Long getFuc_id() {
        return fuc_id;
    }

    public void setFuc_id(Long fuc_id) {
        this.fuc_id = fuc_id;
    }
}
