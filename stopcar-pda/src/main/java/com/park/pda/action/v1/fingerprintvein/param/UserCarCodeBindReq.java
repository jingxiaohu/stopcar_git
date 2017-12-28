package com.park.pda.action.v1.fingerprintvein.param;

import apidoc.jxh.cn.TargetComment;
import com.park.mvc.action.v1.param.BaseParam;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by zzy on 2017/8/30.
 */
public class UserCarCodeBindReq extends BaseParam {
    @TargetComment(value = "用户指纹采集和绑卡表外键（finger_userinfo）ID",isnull = "否")
    @NotNull
    private Long fu_id;  //用户指纹采集和绑卡表外键（finger_userinfo）ID

    @TargetComment(value = "车牌号码",isnull = "否")
    @NotBlank
    private String car_code;   //车牌号码

    @TargetComment(value = "身份证号码",isnull = "是")
    private String sfz_number;   //身份证号码

    public Long getFu_id() {
        return fu_id;
    }

    public void setFu_id(Long fu_id) {
        this.fu_id = fu_id;
    }

    public String getCar_code() {
        return car_code;
    }

    public void setCar_code(String car_code) {
        this.car_code = car_code;
    }

    public String getSfz_number() {
        return sfz_number;
    }

    public void setSfz_number(String sfz_number) {
        this.sfz_number = sfz_number;
    }
}
