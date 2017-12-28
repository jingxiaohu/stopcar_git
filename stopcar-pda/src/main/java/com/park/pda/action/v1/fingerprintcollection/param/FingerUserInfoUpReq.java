package com.park.pda.action.v1.fingerprintcollection.param;

import com.park.mvc.action.v1.param.BaseParam;

import java.util.Date;

/**
 * 指纹静脉信息修改请求参数
 * Created by zzy on 2017/7/20.
 */
public class FingerUserInfoUpReq extends BaseParam{
    private long fu_id;//bigint(20)    主键ID
    private String ui_tel;//varchar(30)    用户手机号码  //不能修改
    private String car_code;  //车牌号码
    private String name;//varchar(30)    用户真实姓名
    private String sfz_number;//varchar(30)    用户真实身份证号码
    private String sfz_img_url;//varchar(150)    用户身份证图片
    private String fingerprint;//text    用户指纹特征信息
    private int bank_type;//int(11)    银行类型（0：建行银行）
    private String bank_card_number;  //银行卡号
    private String mac;//varchar(100)    采集数据提交设备MAC
    private long gather_id;//bigint(20)    采集数据提交设备基本信息表主键ID
    private String sign_ip;//varchar(100)    签约地IP
    //private Long discard_time;//datetime    解除签约时间
    private String finger_veno;  //用户指静脉信息
    private String fingerprint_hash; //用户指纹图片hashcode
    private String finger_veno_hash; //用户指静脉图片hashcode
    private String fingerprint_vein_str; //用户指纹静脉信息json数组字符串

    public long getFu_id() {
        return fu_id;
    }

    public void setFu_id(long fu_id) {
        this.fu_id = fu_id;
    }

    public String getUi_tel() {
        return ui_tel;
    }

    public void setUi_tel(String ui_tel) {
        this.ui_tel = ui_tel;
    }

    public String getCar_code() {
        return car_code;
    }

    public void setCar_code(String car_code) {
        this.car_code = car_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSfz_number() {
        return sfz_number;
    }

    public void setSfz_number(String sfz_number) {
        this.sfz_number = sfz_number;
    }

    public String getSfz_img_url() {
        return sfz_img_url;
    }

    public void setSfz_img_url(String sfz_img_url) {
        this.sfz_img_url = sfz_img_url;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public int getBank_type() {
        return bank_type;
    }

    public void setBank_type(int bank_type) {
        this.bank_type = bank_type;
    }

    public String getBank_card_number() {
        return bank_card_number;
    }

    public void setBank_card_number(String bank_card_number) {
        this.bank_card_number = bank_card_number;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public long getGather_id() {
        return gather_id;
    }

    public void setGather_id(long gather_id) {
        this.gather_id = gather_id;
    }

    public String getSign_ip() {
        return sign_ip;
    }

    public void setSign_ip(String sign_ip) {
        this.sign_ip = sign_ip;
    }

    public String getFinger_veno() {
        return finger_veno;
    }

    public void setFinger_veno(String finger_veno) {
        this.finger_veno = finger_veno;
    }

    public String getFingerprint_hash() {
        return fingerprint_hash;
    }

    public void setFingerprint_hash(String fingerprint_hash) {
        this.fingerprint_hash = fingerprint_hash;
    }

    public String getFinger_veno_hash() {
        return finger_veno_hash;
    }

    public void setFinger_veno_hash(String finger_veno_hash) {
        this.finger_veno_hash = finger_veno_hash;
    }

    public String getFingerprint_vein_str() {
        return fingerprint_vein_str;
    }

    public void setFingerprint_vein_str(String fingerprint_vein_str) {
        this.fingerprint_vein_str = fingerprint_vein_str;
    }

    //    public Long getDiscard_time() {
//        return discard_time;
//    }
//
//    public void setDiscard_time(Long discard_time) {
//        this.discard_time = discard_time;
//    }
}
