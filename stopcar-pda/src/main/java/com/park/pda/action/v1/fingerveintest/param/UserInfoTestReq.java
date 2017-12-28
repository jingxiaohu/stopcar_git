package com.park.pda.action.v1.fingerveintest.param;

import apidoc.jxh.cn.TargetComment;
import com.park.mvc.action.v1.param.BaseParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * 用户指纹静脉信息
 * Created by zzy on 2017/7/19.
 */
public class UserInfoTestReq extends BaseParam {
    @TargetComment(value = "用户手机号码",isnull = "否")
    private String ui_tel;  // 用户手机号码   N   唯一

    @TargetComment(value = "用户真实姓名",isnull = "否")
    private String name;  //  用户真实姓名    N

    @TargetComment(value = "采集数据提交设备MAC",isnull = "否")
    private String mac; // 采集数据提交设备MAC N

    @TargetComment(value = "采集数据提交设备基本信息表主键ID",isnull = "是")
    private Long gather_id;// 采集数据提交设备基本信息表主键ID Y

    @TargetComment(value = "签约地IP",isnull = "是")
    private String sign_ip;  //签约地IP Y

    @TargetComment(value = "用户指纹静脉信息json数组字符串",isnull = "否")
    private String fingerprint_vein_str; //用户指纹静脉信息json数组字符串

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

    public String getFingerprint_vein_str() {
        return fingerprint_vein_str;
    }

    public void setFingerprint_vein_str(String fingerprint_vein_str) {
        this.fingerprint_vein_str = fingerprint_vein_str;
    }
}
