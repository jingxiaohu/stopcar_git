package com.park.pda.action.v1.fingerprintcollection.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * pda指纹采集请求参数
 * Created by zzy on 2017/7/19.
 */
public class FingerprintCollectionReq extends BaseParam {
    private String verify_code;   //手机验证码    N
    private String verify_list;//由发送验证码接口或者重新发送验证码接口返回的verify_list参数的值   N
    private String vclass;//固定参数：1：注册 2：重置密码 3:重置绑定电话号码  4：绑定银行卡  5:获取指纹   N
    private String ui_tel;  // 用户手机号码   N
    private String car_code;  //用户车牌   N
    private String name;  //  用户真实姓名    N
    private String sfz_number;  //  用户真实身份证号码  N
    private String sfz_img_url; //身份证图片地址  Y
    private String bank_card_number;  // 用户银行卡卡号 N
    private Integer bank_type;  //银行类型（0：建行银行）Y
    private Long ctime; //  创建时间    N
    private String mac; // 采集数据提交设备MAC N
    private Long gather_id;// 采集数据提交设备基本信息表主键ID N
    private String sign_ip;  //签约地IP N

    /* 龙支付相关参数**/
//    public Integer pay_type=5;//支付类型 1:支付宝  2：微信  3：建行银联  4：钱包 5:龙支付
//    public Integer pay_price=1;//充值金额 单位 分
//    public Integer version=1;//当前版本编号
//    public String subject="建行签约";//商品名称
//    public int system_type=1;//操作系统类型（IOS Android web） 1:android 2:IOS 3:web   4:PDA
//    public long t;//时间戳ms
//    public String token;//令牌
//    //收银台页面上，商品展示的超链接，必填
//    public String show_url ="";
//    // 页面跳转同步通知页面路径
//    public String return_url = "";
//    public int type=1;//是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付


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

    public String getBank_card_number() {
        return bank_card_number;
    }

    public void setBank_card_number(String bank_card_number) {
        this.bank_card_number = bank_card_number;
    }

    public Integer getBank_type() {
        return bank_type;
    }

    public void setBank_type(Integer bank_type) {
        this.bank_type = bank_type;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Long getGather_id() {
        return gather_id;
    }

    public void setGather_id(Long gather_id) {
        this.gather_id = gather_id;
    }

    public String getSign_ip() {
        return sign_ip;
    }

    public void setSign_ip(String sign_ip) {
        this.sign_ip = sign_ip;
    }
}
