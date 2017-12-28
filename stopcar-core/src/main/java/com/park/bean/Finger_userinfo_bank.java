package com.park.bean;

import java.io.*;
import java.util.*;

//finger_userinfo_bank
@SuppressWarnings({"serial"})
public class Finger_userinfo_bank implements Cloneable , Serializable{

    //public static String[] carrays ={"fub_id","fu_id","fu_nd","sfz_number","bank_card_number","bank_type","orderid","verify_sign","state","is_del","ctime","utime","is_sign","sign_ip","signtime","discard_time","is_default","note","name"};

    public long fub_id;//bigint(20)    主键ID
    public long fu_id;//bigint(20)    用户指纹采集和绑卡表外键（finger_userinfo）ID
    public String fu_nd="";//varchar(100)    唯一标识符
    public String sfz_number="";//varchar(30)    用户真实身份证号码
    public String bank_card_number="";//varchar(30)    用户银行卡卡号
    public int bank_type;//int(11)    银行类型（0：建行银行）
    public String orderid="";//varchar(70)    签约验证支付订单号(user_pay表订单号)
    public int verify_sign;//int(11)    是否签约支付验证成功0：未验证1：成功2：失败
    public int state;//int(11)    有效状态(0：无效1：有效)
    public int is_del;//int(11)    是否删除0:没有1：删除
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    修改时间
    public int is_sign;//int(11)    是否签约成功（0：没有签约1：签约成功2：签约失败3：解除签约）
    public String sign_ip="";//varchar(100)    签约地IP
    public java.util.Date signtime=new java.util.Date();//datetime    签约时间
    public java.util.Date discard_time=new java.util.Date();//datetime    解除签约时间
    public int is_default;//int(11)    是否是默认ETC支付卡0:不是1：是
    public String note="";//varchar(100)    备注
    public String name="";//varchar(60)    用户真实姓名



    public long getFub_id(){
        return fub_id;
    }

    public void setFub_id(long value){
        this.fub_id= value;
    }

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

    public String getSfz_number(){
        return sfz_number;
    }

    public void setSfz_number(String value){
    	if(value == null){
           value = "";
        }
        this.sfz_number= value;
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

    public String getName(){
        return name;
    }

    public void setName(String value){
    	if(value == null){
           value = "";
        }
        this.name= value;
    }



    public static Finger_userinfo_bank newFinger_userinfo_bank(long fub_id, long fu_id, String fu_nd, String sfz_number, String bank_card_number, int bank_type, String orderid, int verify_sign, int state, int is_del, java.util.Date ctime, java.util.Date utime, int is_sign, String sign_ip, java.util.Date signtime, java.util.Date discard_time, int is_default, String note, String name) {
        Finger_userinfo_bank ret = new Finger_userinfo_bank();
        ret.setFub_id(fub_id);
        ret.setFu_id(fu_id);
        ret.setFu_nd(fu_nd);
        ret.setSfz_number(sfz_number);
        ret.setBank_card_number(bank_card_number);
        ret.setBank_type(bank_type);
        ret.setOrderid(orderid);
        ret.setVerify_sign(verify_sign);
        ret.setState(state);
        ret.setIs_del(is_del);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setIs_sign(is_sign);
        ret.setSign_ip(sign_ip);
        ret.setSigntime(signtime);
        ret.setDiscard_time(discard_time);
        ret.setIs_default(is_default);
        ret.setNote(note);
        ret.setName(name);
        return ret;    
    }

    public void assignment(Finger_userinfo_bank finger_userinfo_bank) {
        long fub_id = finger_userinfo_bank.getFub_id();
        long fu_id = finger_userinfo_bank.getFu_id();
        String fu_nd = finger_userinfo_bank.getFu_nd();
        String sfz_number = finger_userinfo_bank.getSfz_number();
        String bank_card_number = finger_userinfo_bank.getBank_card_number();
        int bank_type = finger_userinfo_bank.getBank_type();
        String orderid = finger_userinfo_bank.getOrderid();
        int verify_sign = finger_userinfo_bank.getVerify_sign();
        int state = finger_userinfo_bank.getState();
        int is_del = finger_userinfo_bank.getIs_del();
        java.util.Date ctime = finger_userinfo_bank.getCtime();
        java.util.Date utime = finger_userinfo_bank.getUtime();
        int is_sign = finger_userinfo_bank.getIs_sign();
        String sign_ip = finger_userinfo_bank.getSign_ip();
        java.util.Date signtime = finger_userinfo_bank.getSigntime();
        java.util.Date discard_time = finger_userinfo_bank.getDiscard_time();
        int is_default = finger_userinfo_bank.getIs_default();
        String note = finger_userinfo_bank.getNote();
        String name = finger_userinfo_bank.getName();

        this.setFub_id(fub_id);
        this.setFu_id(fu_id);
        this.setFu_nd(fu_nd);
        this.setSfz_number(sfz_number);
        this.setBank_card_number(bank_card_number);
        this.setBank_type(bank_type);
        this.setOrderid(orderid);
        this.setVerify_sign(verify_sign);
        this.setState(state);
        this.setIs_del(is_del);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setIs_sign(is_sign);
        this.setSign_ip(sign_ip);
        this.setSigntime(signtime);
        this.setDiscard_time(discard_time);
        this.setIs_default(is_default);
        this.setNote(note);
        this.setName(name);

    }

    @SuppressWarnings("unused")
    public static void getFinger_userinfo_bank(Finger_userinfo_bank finger_userinfo_bank ){
        long fub_id = finger_userinfo_bank.getFub_id();
        long fu_id = finger_userinfo_bank.getFu_id();
        String fu_nd = finger_userinfo_bank.getFu_nd();
        String sfz_number = finger_userinfo_bank.getSfz_number();
        String bank_card_number = finger_userinfo_bank.getBank_card_number();
        int bank_type = finger_userinfo_bank.getBank_type();
        String orderid = finger_userinfo_bank.getOrderid();
        int verify_sign = finger_userinfo_bank.getVerify_sign();
        int state = finger_userinfo_bank.getState();
        int is_del = finger_userinfo_bank.getIs_del();
        java.util.Date ctime = finger_userinfo_bank.getCtime();
        java.util.Date utime = finger_userinfo_bank.getUtime();
        int is_sign = finger_userinfo_bank.getIs_sign();
        String sign_ip = finger_userinfo_bank.getSign_ip();
        java.util.Date signtime = finger_userinfo_bank.getSigntime();
        java.util.Date discard_time = finger_userinfo_bank.getDiscard_time();
        int is_default = finger_userinfo_bank.getIs_default();
        String note = finger_userinfo_bank.getNote();
        String name = finger_userinfo_bank.getName();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Finger_userinfo_bank finger_userinfo_bank){
        long fub_id = finger_userinfo_bank.getFub_id();
        long fu_id = finger_userinfo_bank.getFu_id();
        String fu_nd = finger_userinfo_bank.getFu_nd();
        String sfz_number = finger_userinfo_bank.getSfz_number();
        String bank_card_number = finger_userinfo_bank.getBank_card_number();
        int bank_type = finger_userinfo_bank.getBank_type();
        String orderid = finger_userinfo_bank.getOrderid();
        int verify_sign = finger_userinfo_bank.getVerify_sign();
        int state = finger_userinfo_bank.getState();
        int is_del = finger_userinfo_bank.getIs_del();
        java.util.Date ctime = finger_userinfo_bank.getCtime();
        java.util.Date utime = finger_userinfo_bank.getUtime();
        int is_sign = finger_userinfo_bank.getIs_sign();
        String sign_ip = finger_userinfo_bank.getSign_ip();
        java.util.Date signtime = finger_userinfo_bank.getSigntime();
        java.util.Date discard_time = finger_userinfo_bank.getDiscard_time();
        int is_default = finger_userinfo_bank.getIs_default();
        String note = finger_userinfo_bank.getNote();
        String name = finger_userinfo_bank.getName();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("fub_id",fub_id);
        _ret.put("fu_id",fu_id);
        _ret.put("fu_nd",fu_nd);
        _ret.put("sfz_number",sfz_number);
        _ret.put("bank_card_number",bank_card_number);
        _ret.put("bank_type",bank_type);
        _ret.put("orderid",orderid);
        _ret.put("verify_sign",verify_sign);
        _ret.put("state",state);
        _ret.put("is_del",is_del);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("is_sign",is_sign);
        _ret.put("sign_ip",sign_ip);
        _ret.put("signtime",signtime);
        _ret.put("discard_time",discard_time);
        _ret.put("is_default",is_default);
        _ret.put("note",note);
        _ret.put("name",name);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Finger_userinfo_bank clone2(){
        try{
            return (Finger_userinfo_bank) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
