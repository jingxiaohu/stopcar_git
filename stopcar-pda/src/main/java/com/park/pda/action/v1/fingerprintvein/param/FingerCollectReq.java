package com.park.pda.action.v1.fingerprintvein.param;

import apidoc.jxh.cn.TargetComment;
import com.park.mvc.action.v1.param.BaseParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * Created by zzy on 2017/8/30.
 */
public class FingerCollectReq extends BaseParam {
    @TargetComment(value = "指纹用户基本信息表主键ID（外键）",isnull = "否")
    @NotNull
    private Long fu_id; //指纹用户基本信息表主键ID（外键）

    @TargetComment(value = "指纹图片",isnull = "否")
    @NotNull
    private MultipartFile fingerprint_img;   //指纹图片    最大100k

    @TargetComment(value = "指纹图片文件名",isnull = "是")
    private String fingerprint_img_name;   //指纹图片文件名

    @TargetComment(value = "静脉图片",isnull = "否")
    @NotNull
    private MultipartFile finger_veno_img;    //静脉图片   最大100k

    @TargetComment(value = "静脉图片文件名",isnull = "是")
    private String finger_veno_img_name;    //静脉图片文件名

    @TargetComment(value = "静脉信息",isnull = "否")
    @NotBlank
    private String vein;   //静脉信息

    @TargetComment(value = "指纹信息",isnull = "否")
    @NotBlank
    private String fingerprint;   //指纹信息

    @TargetComment(value = "静脉hashcode",isnull = "否")
    @NotBlank
    private String vein_hash; //  静脉hashcode

    @TargetComment(value = "指纹hashcode",isnull = "否")
    @NotBlank
    private String fingerprint_hash;  //指纹hashcode

    public Long getFu_id() {
        return fu_id;
    }

    public void setFu_id(Long fu_id) {
        this.fu_id = fu_id;
    }

    public MultipartFile getFingerprint_img() {
        return fingerprint_img;
    }

    public void setFingerprint_img(MultipartFile fingerprint_img) {
        this.fingerprint_img = fingerprint_img;
    }

    public String getFingerprint_img_name() {
        return fingerprint_img_name;
    }

    public void setFingerprint_img_name(String fingerprint_img_name) {
        this.fingerprint_img_name = fingerprint_img_name;
    }

    public MultipartFile getFinger_veno_img() {
        return finger_veno_img;
    }

    public void setFinger_veno_img(MultipartFile finger_veno_img) {
        this.finger_veno_img = finger_veno_img;
    }

    public String getFinger_veno_img_name() {
        return finger_veno_img_name;
    }

    public void setFinger_veno_img_name(String finger_veno_img_name) {
        this.finger_veno_img_name = finger_veno_img_name;
    }

    public String getVein() {
        return vein;
    }

    public void setVein(String vein) {
        this.vein = vein;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getVein_hash() {
        return vein_hash;
    }

    public void setVein_hash(String vein_hash) {
        this.vein_hash = vein_hash;
    }

    public String getFingerprint_hash() {
        return fingerprint_hash;
    }

    public void setFingerprint_hash(String fingerprint_hash) {
        this.fingerprint_hash = fingerprint_hash;
    }
}
