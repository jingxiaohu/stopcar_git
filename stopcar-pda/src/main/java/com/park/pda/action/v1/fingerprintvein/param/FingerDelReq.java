package com.park.pda.action.v1.fingerprintvein.param;

import apidoc.jxh.cn.TargetComment;
import com.park.mvc.action.v1.param.BaseParam;

import javax.validation.constraints.NotNull;

/**
 * Created by zzy on 2017/8/30.
 */
public class FingerDelReq extends BaseParam {
    @TargetComment(value = "指纹系统-用户指纹图片hash-用户资料关系表主键",isnull = "否")
    @NotNull
    private Long fur_id; //指纹系统--------用户指纹图片hash----用户资料关系表主键

    @TargetComment(value = "指纹系统--(新)用户资料管理表主键",isnull = "否")
    @NotNull
    private Long fu_id;  //指纹系统-------(新)用户资料管理表主键

    public Long getFur_id() {
        return fur_id;
    }

    public void setFur_id(Long fur_id) {
        this.fur_id = fur_id;
    }

    public Long getFu_id() {
        return fu_id;
    }

    public void setFu_id(Long fu_id) {
        this.fu_id = fu_id;
    }
}
