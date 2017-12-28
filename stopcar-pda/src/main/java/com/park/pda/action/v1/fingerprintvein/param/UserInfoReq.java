package com.park.pda.action.v1.fingerprintvein.param;

import apidoc.jxh.cn.TargetComment;
import com.park.mvc.action.v1.param.BaseParam;

/**
 * 用户指纹静脉信息
 * Created by zzy on 2017/7/19.
 */
public class UserInfoReq extends BaseParam {
    @TargetComment(value = "用户手机号码",isnull = "否")
    private String ui_tel;  // 用户手机号码   N   唯一

    @TargetComment(value = "用户真实姓名",isnull = "否")
    private String name;  //  用户真实姓名    N

    @TargetComment(value = "用户真实姓名",isnull = "否")
    private String sfz_number;  //  用户真实身份证号码  N

    @TargetComment(value = "身份证图片地址",isnull = "是")
    private String sfz_img_url; //身份证图片地址  Y

    @TargetComment(value = "用户银行卡卡号",isnull = "否")
    private String bank_card_number;  // 用户银行卡卡号 N

    @TargetComment(value = "银行类型",isnull = "否")
    private Integer bank_type;  //银行类型（0：建行银行）N

    @TargetComment(value = "采集数据提交设备MAC",isnull = "是")
    private String mac; // 采集数据提交设备MAC N

    @TargetComment(value = "采集数据提交设备基本信息表主键ID",isnull = "是")
    private Long gather_id;// 采集数据提交设备基本信息表主键ID Y

    @TargetComment(value = "签约地IP",isnull = "是")
    private String sign_ip;  //签约地IP Y

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


    public String getUi_tel() {
        return ui_tel;
    }

    public void setUi_tel(String ui_tel) {
        this.ui_tel = ui_tel;
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
