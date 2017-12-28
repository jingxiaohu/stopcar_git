package com.park.bean;

import java.io.*;
import java.util.*;

//finger_userinfo
@SuppressWarnings({"serial"})
public class Finger_userinfo implements Cloneable , Serializable{

    //public static String[] carrays ={"fu_id","fu_nd","ui_tel","ui_id","ui_nd","name","sfz_number","sfz_img_url","fingerprint","bank_card_number","bank_type","orderid","verify_sign","state","is_del","ctime","utime","mac","gather_id","is_sign","sign_ip","signtime","discard_time","is_default","note","car_code","finger_veno","fingerprint_hash","finger_veno_hash"};

    public long fu_id;//bigint(20)    主键ID
    public String fu_nd="";//varchar(100)    唯一标识符
    public String ui_tel="";//varchar(30)    用户手机号码
    public long ui_id;//bigint(20)    平台用户ID
    public String ui_nd="";//varchar(100)    平台用户唯一标识符
    public String name="";//varchar(30)    用户真实姓名
    public String sfz_number="";//varchar(30)    用户真实身份证号码
    public String sfz_img_url="";//varchar(150)    用户身份证图片
    public String fingerprint="";//text    用户指纹特征信息
    public String bank_card_number="";//varchar(30)    用户银行卡卡号
    public int bank_type;//int(11)    银行类型（0：建行银行）
    public String orderid="";//varchar(70)    签约验证支付订单号(user_pay表订单号)
    public int verify_sign;//int(11)    是否签约支付验证成功0：未验证1：成功2：失败
    public int state;//int(11)    有效状态(0：无效1：有效)
    public int is_del;//int(11)    是否删除0:没有1：删除
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    修改时间
    public String mac="";//varchar(100)    采集数据提交设备MAC
    public long gather_id;//bigint(20)    采集数据提交设备基本信息表主键ID
    public int is_sign;//int(11)    是否签约成功（0：没有签约1：签约成功2：签约失败3：解除签约）
    public String sign_ip="";//varchar(100)    签约地IP
    public java.util.Date signtime=new java.util.Date();//datetime    签约时间
    public java.util.Date discard_time=new java.util.Date();//datetime    解除签约时间
    public int is_default;//int(11)    是否是默认ETC支付卡0:不是1：是
    public String note="";//varchar(100)    备注
    public String car_code="";//varchar(60)    车牌号
    public String finger_veno="";//text    用户指静脉特征信息
    public String fingerprint_hash="";//varchar(100)    用户指纹图片hashcode
    public String finger_veno_hash="";//varchar(100)    用户指静脉图片hashcode



    public long getFu_id(){
        return fu_id;
    }

    public void setFu_id(long value){
        this.fu_id= value;
    }

    public String getFu_nd(){
        return fu_nd;
    }

    public void setFu_nd(String value){
    	if(value == null){
           value = "";
        }
        this.fu_nd= value;
    }

    public String getUi_tel(){
        return ui_tel;
    }

    public void setUi_tel(String value){
    	if(value == null){
           value = "";
        }
        this.ui_tel= value;
    }

    public long getUi_id(){
        return ui_id;
    }

    public void setUi_id(long value){
        this.ui_id= value;
    }

    public String getUi_nd(){
        return ui_nd;
    }

    public void setUi_nd(String value){
    	if(value == null){
           value = "";
        }
        this.ui_nd= value;
    }

    public String getName(){
        return name;
    }

    public void setName(String value){
    	if(value == null){
           value = "";
        }
        this.name= value;
    }

    public String getSfz_number(){
        return sfz_number;
    }

    public void setSfz_number(String value){
    	if(value == null){
           value = "";
        }
        this.sfz_number= value;
    }

    public String getSfz_img_url(){
        return sfz_img_url;
    }

    public void setSfz_img_url(String value){
    	if(value == null){
           value = "";
        }
        this.sfz_img_url= value;
    }

    public String getFingerprint(){
        return fingerprint;
    }

    public void setFingerprint(String value){
    	if(value == null){
           value = "";
        }
        this.fingerprint= value;
    }

    public String getBank_card_number(){
        return bank_card_number;
    }

    public void setBank_card_number(String value){
    	if(value == null){
           value = "";
        }
        this.bank_card_number= value;
    }

    public int getBank_type(){
        return bank_type;
    }

    public void setBank_type(int value){
        this.bank_type= value;
    }

    public String getOrderid(){
        return orderid;
    }

    public void setOrderid(String value){
    	if(value == null){
           value = "";
        }
        this.orderid= value;
    }

    public int getVerify_sign(){
        return verify_sign;
    }

    public void setVerify_sign(int value){
        this.verify_sign= value;
    }

    public int getState(){
        return state;
    }

    public void setState(int value){
        this.state= value;
    }

    public int getIs_del(){
        return is_del;
    }

    public void setIs_del(int value){
        this.is_del= value;
    }

    public java.util.Date getCtime(){
        return ctime;
    }

    public void setCtime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.ctime= value;
    }

    public java.util.Date getUtime(){
        return utime;
    }

    public void setUtime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.utime= value;
    }

    public String getMac(){
        return mac;
    }

    public void setMac(String value){
    	if(value == null){
           value = "";
        }
        this.mac= value;
    }

    public long getGather_id(){
        return gather_id;
    }

    public void setGather_id(long value){
        this.gather_id= value;
    }

    public int getIs_sign(){
        return is_sign;
    }

    public void setIs_sign(int value){
        this.is_sign= value;
    }

    public String getSign_ip(){
        return sign_ip;
    }

    public void setSign_ip(String value){
    	if(value == null){
           value = "";
        }
        this.sign_ip= value;
    }

    public java.util.Date getSigntime(){
        return signtime;
    }

    public void setSigntime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.signtime= value;
    }

    public java.util.Date getDiscard_time(){
        return discard_time;
    }

    public void setDiscard_time(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.discard_time= value;
    }

    public int getIs_default(){
        return is_default;
    }

    public void setIs_default(int value){
        this.is_default= value;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String value){
    	if(value == null){
           value = "";
        }
        this.note= value;
    }

    public String getCar_code(){
        return car_code;
    }

    public void setCar_code(String value){
    	if(value == null){
           value = "";
        }
        this.car_code= value;
    }

    public String getFinger_veno(){
        return finger_veno;
    }

    public void setFinger_veno(String value){
    	if(value == null){
           value = "";
        }
        this.finger_veno= value;
    }

    public String getFingerprint_hash(){
        return fingerprint_hash;
    }

    public void setFingerprint_hash(String value){
    	if(value == null){
           value = "";
        }
        this.fingerprint_hash= value;
    }

    public String getFinger_veno_hash(){
        return finger_veno_hash;
    }

    public void setFinger_veno_hash(String value){
    	if(value == null){
           value = "";
        }
        this.finger_veno_hash= value;
    }



    public static Finger_userinfo newFinger_userinfo(long fu_id, String fu_nd, String ui_tel, long ui_id, String ui_nd, String name, String sfz_number, String sfz_img_url, String fingerprint, String bank_card_number, int bank_type, String orderid, int verify_sign, int state, int is_del, java.util.Date ctime, java.util.Date utime, String mac, long gather_id, int is_sign, String sign_ip, java.util.Date signtime, java.util.Date discard_time, int is_default, String note, String car_code, String finger_veno, String fingerprint_hash, String finger_veno_hash) {
        Finger_userinfo ret = new Finger_userinfo();
        ret.setFu_id(fu_id);
        ret.setFu_nd(fu_nd);
        ret.setUi_tel(ui_tel);
        ret.setUi_id(ui_id);
        ret.setUi_nd(ui_nd);
        ret.setName(name);
        ret.setSfz_number(sfz_number);
        ret.setSfz_img_url(sfz_img_url);
        ret.setFingerprint(fingerprint);
        ret.setBank_card_number(bank_card_number);
        ret.setBank_type(bank_type);
        ret.setOrderid(orderid);
        ret.setVerify_sign(verify_sign);
        ret.setState(state);
        ret.setIs_del(is_del);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setMac(mac);
        ret.setGather_id(gather_id);
        ret.setIs_sign(is_sign);
        ret.setSign_ip(sign_ip);
        ret.setSigntime(signtime);
        ret.setDiscard_time(discard_time);
        ret.setIs_default(is_default);
        ret.setNote(note);
        ret.setCar_code(car_code);
        ret.setFinger_veno(finger_veno);
        ret.setFingerprint_hash(fingerprint_hash);
        ret.setFinger_veno_hash(finger_veno_hash);
        return ret;    
    }

    public void assignment(Finger_userinfo finger_userinfo) {
        long fu_id = finger_userinfo.getFu_id();
        String fu_nd = finger_userinfo.getFu_nd();
        String ui_tel = finger_userinfo.getUi_tel();
        long ui_id = finger_userinfo.getUi_id();
        String ui_nd = finger_userinfo.getUi_nd();
        String name = finger_userinfo.getName();
        String sfz_number = finger_userinfo.getSfz_number();
        String sfz_img_url = finger_userinfo.getSfz_img_url();
        String fingerprint = finger_userinfo.getFingerprint();
        String bank_card_number = finger_userinfo.getBank_card_number();
        int bank_type = finger_userinfo.getBank_type();
        String orderid = finger_userinfo.getOrderid();
        int verify_sign = finger_userinfo.getVerify_sign();
        int state = finger_userinfo.getState();
        int is_del = finger_userinfo.getIs_del();
        java.util.Date ctime = finger_userinfo.getCtime();
        java.util.Date utime = finger_userinfo.getUtime();
        String mac = finger_userinfo.getMac();
        long gather_id = finger_userinfo.getGather_id();
        int is_sign = finger_userinfo.getIs_sign();
        String sign_ip = finger_userinfo.getSign_ip();
        java.util.Date signtime = finger_userinfo.getSigntime();
        java.util.Date discard_time = finger_userinfo.getDiscard_time();
        int is_default = finger_userinfo.getIs_default();
        String note = finger_userinfo.getNote();
        String car_code = finger_userinfo.getCar_code();
        String finger_veno = finger_userinfo.getFinger_veno();
        String fingerprint_hash = finger_userinfo.getFingerprint_hash();
        String finger_veno_hash = finger_userinfo.getFinger_veno_hash();

        this.setFu_id(fu_id);
        this.setFu_nd(fu_nd);
        this.setUi_tel(ui_tel);
        this.setUi_id(ui_id);
        this.setUi_nd(ui_nd);
        this.setName(name);
        this.setSfz_number(sfz_number);
        this.setSfz_img_url(sfz_img_url);
        this.setFingerprint(fingerprint);
        this.setBank_card_number(bank_card_number);
        this.setBank_type(bank_type);
        this.setOrderid(orderid);
        this.setVerify_sign(verify_sign);
        this.setState(state);
        this.setIs_del(is_del);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setMac(mac);
        this.setGather_id(gather_id);
        this.setIs_sign(is_sign);
        this.setSign_ip(sign_ip);
        this.setSigntime(signtime);
        this.setDiscard_time(discard_time);
        this.setIs_default(is_default);
        this.setNote(note);
        this.setCar_code(car_code);
        this.setFinger_veno(finger_veno);
        this.setFingerprint_hash(fingerprint_hash);
        this.setFinger_veno_hash(finger_veno_hash);

    }

    @SuppressWarnings("unused")
    public static void getFinger_userinfo(Finger_userinfo finger_userinfo ){
        long fu_id = finger_userinfo.getFu_id();
        String fu_nd = finger_userinfo.getFu_nd();
        String ui_tel = finger_userinfo.getUi_tel();
        long ui_id = finger_userinfo.getUi_id();
        String ui_nd = finger_userinfo.getUi_nd();
        String name = finger_userinfo.getName();
        String sfz_number = finger_userinfo.getSfz_number();
        String sfz_img_url = finger_userinfo.getSfz_img_url();
        String fingerprint = finger_userinfo.getFingerprint();
        String bank_card_number = finger_userinfo.getBank_card_number();
        int bank_type = finger_userinfo.getBank_type();
        String orderid = finger_userinfo.getOrderid();
        int verify_sign = finger_userinfo.getVerify_sign();
        int state = finger_userinfo.getState();
        int is_del = finger_userinfo.getIs_del();
        java.util.Date ctime = finger_userinfo.getCtime();
        java.util.Date utime = finger_userinfo.getUtime();
        String mac = finger_userinfo.getMac();
        long gather_id = finger_userinfo.getGather_id();
        int is_sign = finger_userinfo.getIs_sign();
        String sign_ip = finger_userinfo.getSign_ip();
        java.util.Date signtime = finger_userinfo.getSigntime();
        java.util.Date discard_time = finger_userinfo.getDiscard_time();
        int is_default = finger_userinfo.getIs_default();
        String note = finger_userinfo.getNote();
        String car_code = finger_userinfo.getCar_code();
        String finger_veno = finger_userinfo.getFinger_veno();
        String fingerprint_hash = finger_userinfo.getFingerprint_hash();
        String finger_veno_hash = finger_userinfo.getFinger_veno_hash();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Finger_userinfo finger_userinfo){
        long fu_id = finger_userinfo.getFu_id();
        String fu_nd = finger_userinfo.getFu_nd();
        String ui_tel = finger_userinfo.getUi_tel();
        long ui_id = finger_userinfo.getUi_id();
        String ui_nd = finger_userinfo.getUi_nd();
        String name = finger_userinfo.getName();
        String sfz_number = finger_userinfo.getSfz_number();
        String sfz_img_url = finger_userinfo.getSfz_img_url();
        String fingerprint = finger_userinfo.getFingerprint();
        String bank_card_number = finger_userinfo.getBank_card_number();
        int bank_type = finger_userinfo.getBank_type();
        String orderid = finger_userinfo.getOrderid();
        int verify_sign = finger_userinfo.getVerify_sign();
        int state = finger_userinfo.getState();
        int is_del = finger_userinfo.getIs_del();
        java.util.Date ctime = finger_userinfo.getCtime();
        java.util.Date utime = finger_userinfo.getUtime();
        String mac = finger_userinfo.getMac();
        long gather_id = finger_userinfo.getGather_id();
        int is_sign = finger_userinfo.getIs_sign();
        String sign_ip = finger_userinfo.getSign_ip();
        java.util.Date signtime = finger_userinfo.getSigntime();
        java.util.Date discard_time = finger_userinfo.getDiscard_time();
        int is_default = finger_userinfo.getIs_default();
        String note = finger_userinfo.getNote();
        String car_code = finger_userinfo.getCar_code();
        String finger_veno = finger_userinfo.getFinger_veno();
        String fingerprint_hash = finger_userinfo.getFingerprint_hash();
        String finger_veno_hash = finger_userinfo.getFinger_veno_hash();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("fu_id",fu_id);
        _ret.put("fu_nd",fu_nd);
        _ret.put("ui_tel",ui_tel);
        _ret.put("ui_id",ui_id);
        _ret.put("ui_nd",ui_nd);
        _ret.put("name",name);
        _ret.put("sfz_number",sfz_number);
        _ret.put("sfz_img_url",sfz_img_url);
        _ret.put("fingerprint",fingerprint);
        _ret.put("bank_card_number",bank_card_number);
        _ret.put("bank_type",bank_type);
        _ret.put("orderid",orderid);
        _ret.put("verify_sign",verify_sign);
        _ret.put("state",state);
        _ret.put("is_del",is_del);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("mac",mac);
        _ret.put("gather_id",gather_id);
        _ret.put("is_sign",is_sign);
        _ret.put("sign_ip",sign_ip);
        _ret.put("signtime",signtime);
        _ret.put("discard_time",discard_time);
        _ret.put("is_default",is_default);
        _ret.put("note",note);
        _ret.put("car_code",car_code);
        _ret.put("finger_veno",finger_veno);
        _ret.put("fingerprint_hash",fingerprint_hash);
        _ret.put("finger_veno_hash",finger_veno_hash);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Finger_userinfo clone2(){
        try{
            return (Finger_userinfo) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
