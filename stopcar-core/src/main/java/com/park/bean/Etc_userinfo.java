package com.park.bean;

import java.io.*;
import java.util.*;

//etc_userinfo
@SuppressWarnings({"serial"})
public class Etc_userinfo implements Cloneable , Serializable{

    //public static String[] carrays ={"eu_id","eu_nd","ui_id","ui_nd","name","sfz_number","sfz_img_url","bank_card_number","bank_type","is_sign","sign_ip","ctime","utime","signtime","discard_time","note","ui_tel","is_default","orderid","verify_sign","is_del"};

    public long eu_id;//bigint(20)    主键ID
    public String eu_nd="";//varchar(100)    etc用户唯一标识符
    public long ui_id;//bigint(20)    平台用户ID
    public String ui_nd="";//varchar(100)    平台用户唯一标识符
    public String name="";//varchar(30)    ETC用户真实姓名
    public String sfz_number="";//varchar(30)    ETC用户真实身份证号码
    public String sfz_img_url="";//varchar(150)    ETC用户身份证图片
    public String bank_card_number="";//varchar(30)    ETC用户银行卡卡号
    public int bank_type;//int(11)    银行类型（0：建行银行）
    public int is_sign;//int(11)    是否签约成功（0：没有签约1：签约成功2：签约失败3：解除签约）
    public String sign_ip="";//varchar(100)    签约地IP
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    修改时间
    public java.util.Date signtime=new java.util.Date();//datetime    签约时间
    public java.util.Date discard_time=new java.util.Date();//datetime    解除签约时间
    public String note="";//varchar(100)    备注
    public String ui_tel="";//varchar(30)    用户手机号码
    public int is_default;//int(11)    是否是默认ETC支付卡0:不是1：是
    public String orderid="";//varchar(70)    签约验证支付订单号(user_pay表订单号)
    public int verify_sign;//int(11)    是否签约支付验证成功0：未验证1：成功2：失败
    public int is_del;//int(11)    是否删除0:没有1：删除



    public long getEu_id(){
        return eu_id;
    }

    public void setEu_id(long value){
        this.eu_id= value;
    }

    public String getEu_nd(){
        return eu_nd;
    }

    public void setEu_nd(String value){
    	if(value == null){
           value = "";
        }
        this.eu_nd= value;
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

    public String getNote(){
        return note;
    }

    public void setNote(String value){
    	if(value == null){
           value = "";
        }
        this.note= value;
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

    public int getIs_default(){
        return is_default;
    }

    public void setIs_default(int value){
        this.is_default= value;
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

    public int getIs_del(){
        return is_del;
    }

    public void setIs_del(int value){
        this.is_del= value;
    }



    public static Etc_userinfo newEtc_userinfo(long eu_id, String eu_nd, long ui_id, String ui_nd, String name, String sfz_number, String sfz_img_url, String bank_card_number, int bank_type, int is_sign, String sign_ip, java.util.Date ctime, java.util.Date utime, java.util.Date signtime, java.util.Date discard_time, String note, String ui_tel, int is_default, String orderid, int verify_sign, int is_del) {
        Etc_userinfo ret = new Etc_userinfo();
        ret.setEu_id(eu_id);
        ret.setEu_nd(eu_nd);
        ret.setUi_id(ui_id);
        ret.setUi_nd(ui_nd);
        ret.setName(name);
        ret.setSfz_number(sfz_number);
        ret.setSfz_img_url(sfz_img_url);
        ret.setBank_card_number(bank_card_number);
        ret.setBank_type(bank_type);
        ret.setIs_sign(is_sign);
        ret.setSign_ip(sign_ip);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setSigntime(signtime);
        ret.setDiscard_time(discard_time);
        ret.setNote(note);
        ret.setUi_tel(ui_tel);
        ret.setIs_default(is_default);
        ret.setOrderid(orderid);
        ret.setVerify_sign(verify_sign);
        ret.setIs_del(is_del);
        return ret;    
    }

    public void assignment(Etc_userinfo etc_userinfo) {
        long eu_id = etc_userinfo.getEu_id();
        String eu_nd = etc_userinfo.getEu_nd();
        long ui_id = etc_userinfo.getUi_id();
        String ui_nd = etc_userinfo.getUi_nd();
        String name = etc_userinfo.getName();
        String sfz_number = etc_userinfo.getSfz_number();
        String sfz_img_url = etc_userinfo.getSfz_img_url();
        String bank_card_number = etc_userinfo.getBank_card_number();
        int bank_type = etc_userinfo.getBank_type();
        int is_sign = etc_userinfo.getIs_sign();
        String sign_ip = etc_userinfo.getSign_ip();
        java.util.Date ctime = etc_userinfo.getCtime();
        java.util.Date utime = etc_userinfo.getUtime();
        java.util.Date signtime = etc_userinfo.getSigntime();
        java.util.Date discard_time = etc_userinfo.getDiscard_time();
        String note = etc_userinfo.getNote();
        String ui_tel = etc_userinfo.getUi_tel();
        int is_default = etc_userinfo.getIs_default();
        String orderid = etc_userinfo.getOrderid();
        int verify_sign = etc_userinfo.getVerify_sign();
        int is_del = etc_userinfo.getIs_del();

        this.setEu_id(eu_id);
        this.setEu_nd(eu_nd);
        this.setUi_id(ui_id);
        this.setUi_nd(ui_nd);
        this.setName(name);
        this.setSfz_number(sfz_number);
        this.setSfz_img_url(sfz_img_url);
        this.setBank_card_number(bank_card_number);
        this.setBank_type(bank_type);
        this.setIs_sign(is_sign);
        this.setSign_ip(sign_ip);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setSigntime(signtime);
        this.setDiscard_time(discard_time);
        this.setNote(note);
        this.setUi_tel(ui_tel);
        this.setIs_default(is_default);
        this.setOrderid(orderid);
        this.setVerify_sign(verify_sign);
        this.setIs_del(is_del);

    }

    @SuppressWarnings("unused")
    public static void getEtc_userinfo(Etc_userinfo etc_userinfo ){
        long eu_id = etc_userinfo.getEu_id();
        String eu_nd = etc_userinfo.getEu_nd();
        long ui_id = etc_userinfo.getUi_id();
        String ui_nd = etc_userinfo.getUi_nd();
        String name = etc_userinfo.getName();
        String sfz_number = etc_userinfo.getSfz_number();
        String sfz_img_url = etc_userinfo.getSfz_img_url();
        String bank_card_number = etc_userinfo.getBank_card_number();
        int bank_type = etc_userinfo.getBank_type();
        int is_sign = etc_userinfo.getIs_sign();
        String sign_ip = etc_userinfo.getSign_ip();
        java.util.Date ctime = etc_userinfo.getCtime();
        java.util.Date utime = etc_userinfo.getUtime();
        java.util.Date signtime = etc_userinfo.getSigntime();
        java.util.Date discard_time = etc_userinfo.getDiscard_time();
        String note = etc_userinfo.getNote();
        String ui_tel = etc_userinfo.getUi_tel();
        int is_default = etc_userinfo.getIs_default();
        String orderid = etc_userinfo.getOrderid();
        int verify_sign = etc_userinfo.getVerify_sign();
        int is_del = etc_userinfo.getIs_del();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Etc_userinfo etc_userinfo){
        long eu_id = etc_userinfo.getEu_id();
        String eu_nd = etc_userinfo.getEu_nd();
        long ui_id = etc_userinfo.getUi_id();
        String ui_nd = etc_userinfo.getUi_nd();
        String name = etc_userinfo.getName();
        String sfz_number = etc_userinfo.getSfz_number();
        String sfz_img_url = etc_userinfo.getSfz_img_url();
        String bank_card_number = etc_userinfo.getBank_card_number();
        int bank_type = etc_userinfo.getBank_type();
        int is_sign = etc_userinfo.getIs_sign();
        String sign_ip = etc_userinfo.getSign_ip();
        java.util.Date ctime = etc_userinfo.getCtime();
        java.util.Date utime = etc_userinfo.getUtime();
        java.util.Date signtime = etc_userinfo.getSigntime();
        java.util.Date discard_time = etc_userinfo.getDiscard_time();
        String note = etc_userinfo.getNote();
        String ui_tel = etc_userinfo.getUi_tel();
        int is_default = etc_userinfo.getIs_default();
        String orderid = etc_userinfo.getOrderid();
        int verify_sign = etc_userinfo.getVerify_sign();
        int is_del = etc_userinfo.getIs_del();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("eu_id",eu_id);
        _ret.put("eu_nd",eu_nd);
        _ret.put("ui_id",ui_id);
        _ret.put("ui_nd",ui_nd);
        _ret.put("name",name);
        _ret.put("sfz_number",sfz_number);
        _ret.put("sfz_img_url",sfz_img_url);
        _ret.put("bank_card_number",bank_card_number);
        _ret.put("bank_type",bank_type);
        _ret.put("is_sign",is_sign);
        _ret.put("sign_ip",sign_ip);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("signtime",signtime);
        _ret.put("discard_time",discard_time);
        _ret.put("note",note);
        _ret.put("ui_tel",ui_tel);
        _ret.put("is_default",is_default);
        _ret.put("orderid",orderid);
        _ret.put("verify_sign",verify_sign);
        _ret.put("is_del",is_del);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Etc_userinfo clone2(){
        try{
            return (Etc_userinfo) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
