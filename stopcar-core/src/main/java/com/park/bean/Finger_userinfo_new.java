package com.park.bean;

import java.io.*;
import java.util.*;

//finger_userinfo_new
@SuppressWarnings({"serial"})
public class Finger_userinfo_new implements Cloneable , Serializable{

    //public static String[] carrays ={"fu_id","fu_nd","ui_tel","ui_id","ui_nd","name","sfz_number","sfz_img_url","sfz_img_back_url","state","is_del","ctime","utime","mac","gather_id","note","fingerprint_state","finger_veno_state"};

    public long fu_id;//bigint(20)    主键ID
    public String fu_nd="";//varchar(100)    唯一标识符
    public String ui_tel="";//varchar(30)    用户手机号码
    public long ui_id;//bigint(20)    平台用户ID
    public String ui_nd="";//varchar(100)    平台用户唯一标识符
    public String name="";//varchar(30)    用户真实姓名
    public String sfz_number="";//varchar(30)    用户真实身份证号码
    public String sfz_img_url="";//varchar(150)    用户身份证图片
    public String sfz_img_back_url="";//varchar(150)    用户身份证背面图片
    public int state;//int(11)    有效状态(0：无效1：有效)
    public int is_del;//int(11)    是否删除0:没有1：删除
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    修改时间
    public String mac="";//varchar(100)    采集数据提交设备MAC
    public long gather_id;//bigint(20)    采集数据提交设备基本信息表主键ID
    public String note="";//varchar(100)    备注
    public int fingerprint_state;//int(11)    指纹完成度（0：未完成1：已经完成）
    public int finger_veno_state;//int(11)    指静脉完成度(0:未完成1：已经完成)



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

    public String getSfz_img_back_url(){
        return sfz_img_back_url;
    }

    public void setSfz_img_back_url(String value){
    	if(value == null){
           value = "";
        }
        this.sfz_img_back_url= value;
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

    public String getNote(){
        return note;
    }

    public void setNote(String value){
    	if(value == null){
           value = "";
        }
        this.note= value;
    }

    public int getFingerprint_state(){
        return fingerprint_state;
    }

    public void setFingerprint_state(int value){
        this.fingerprint_state= value;
    }

    public int getFinger_veno_state(){
        return finger_veno_state;
    }

    public void setFinger_veno_state(int value){
        this.finger_veno_state= value;
    }



    public static Finger_userinfo_new newFinger_userinfo_new(long fu_id, String fu_nd, String ui_tel, long ui_id, String ui_nd, String name, String sfz_number, String sfz_img_url, String sfz_img_back_url, int state, int is_del, java.util.Date ctime, java.util.Date utime, String mac, long gather_id, String note, int fingerprint_state, int finger_veno_state) {
        Finger_userinfo_new ret = new Finger_userinfo_new();
        ret.setFu_id(fu_id);
        ret.setFu_nd(fu_nd);
        ret.setUi_tel(ui_tel);
        ret.setUi_id(ui_id);
        ret.setUi_nd(ui_nd);
        ret.setName(name);
        ret.setSfz_number(sfz_number);
        ret.setSfz_img_url(sfz_img_url);
        ret.setSfz_img_back_url(sfz_img_back_url);
        ret.setState(state);
        ret.setIs_del(is_del);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setMac(mac);
        ret.setGather_id(gather_id);
        ret.setNote(note);
        ret.setFingerprint_state(fingerprint_state);
        ret.setFinger_veno_state(finger_veno_state);
        return ret;    
    }

    public void assignment(Finger_userinfo_new finger_userinfo_new) {
        long fu_id = finger_userinfo_new.getFu_id();
        String fu_nd = finger_userinfo_new.getFu_nd();
        String ui_tel = finger_userinfo_new.getUi_tel();
        long ui_id = finger_userinfo_new.getUi_id();
        String ui_nd = finger_userinfo_new.getUi_nd();
        String name = finger_userinfo_new.getName();
        String sfz_number = finger_userinfo_new.getSfz_number();
        String sfz_img_url = finger_userinfo_new.getSfz_img_url();
        String sfz_img_back_url = finger_userinfo_new.getSfz_img_back_url();
        int state = finger_userinfo_new.getState();
        int is_del = finger_userinfo_new.getIs_del();
        java.util.Date ctime = finger_userinfo_new.getCtime();
        java.util.Date utime = finger_userinfo_new.getUtime();
        String mac = finger_userinfo_new.getMac();
        long gather_id = finger_userinfo_new.getGather_id();
        String note = finger_userinfo_new.getNote();
        int fingerprint_state = finger_userinfo_new.getFingerprint_state();
        int finger_veno_state = finger_userinfo_new.getFinger_veno_state();

        this.setFu_id(fu_id);
        this.setFu_nd(fu_nd);
        this.setUi_tel(ui_tel);
        this.setUi_id(ui_id);
        this.setUi_nd(ui_nd);
        this.setName(name);
        this.setSfz_number(sfz_number);
        this.setSfz_img_url(sfz_img_url);
        this.setSfz_img_back_url(sfz_img_back_url);
        this.setState(state);
        this.setIs_del(is_del);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setMac(mac);
        this.setGather_id(gather_id);
        this.setNote(note);
        this.setFingerprint_state(fingerprint_state);
        this.setFinger_veno_state(finger_veno_state);

    }

    @SuppressWarnings("unused")
    public static void getFinger_userinfo_new(Finger_userinfo_new finger_userinfo_new ){
        long fu_id = finger_userinfo_new.getFu_id();
        String fu_nd = finger_userinfo_new.getFu_nd();
        String ui_tel = finger_userinfo_new.getUi_tel();
        long ui_id = finger_userinfo_new.getUi_id();
        String ui_nd = finger_userinfo_new.getUi_nd();
        String name = finger_userinfo_new.getName();
        String sfz_number = finger_userinfo_new.getSfz_number();
        String sfz_img_url = finger_userinfo_new.getSfz_img_url();
        String sfz_img_back_url = finger_userinfo_new.getSfz_img_back_url();
        int state = finger_userinfo_new.getState();
        int is_del = finger_userinfo_new.getIs_del();
        java.util.Date ctime = finger_userinfo_new.getCtime();
        java.util.Date utime = finger_userinfo_new.getUtime();
        String mac = finger_userinfo_new.getMac();
        long gather_id = finger_userinfo_new.getGather_id();
        String note = finger_userinfo_new.getNote();
        int fingerprint_state = finger_userinfo_new.getFingerprint_state();
        int finger_veno_state = finger_userinfo_new.getFinger_veno_state();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Finger_userinfo_new finger_userinfo_new){
        long fu_id = finger_userinfo_new.getFu_id();
        String fu_nd = finger_userinfo_new.getFu_nd();
        String ui_tel = finger_userinfo_new.getUi_tel();
        long ui_id = finger_userinfo_new.getUi_id();
        String ui_nd = finger_userinfo_new.getUi_nd();
        String name = finger_userinfo_new.getName();
        String sfz_number = finger_userinfo_new.getSfz_number();
        String sfz_img_url = finger_userinfo_new.getSfz_img_url();
        String sfz_img_back_url = finger_userinfo_new.getSfz_img_back_url();
        int state = finger_userinfo_new.getState();
        int is_del = finger_userinfo_new.getIs_del();
        java.util.Date ctime = finger_userinfo_new.getCtime();
        java.util.Date utime = finger_userinfo_new.getUtime();
        String mac = finger_userinfo_new.getMac();
        long gather_id = finger_userinfo_new.getGather_id();
        String note = finger_userinfo_new.getNote();
        int fingerprint_state = finger_userinfo_new.getFingerprint_state();
        int finger_veno_state = finger_userinfo_new.getFinger_veno_state();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("fu_id",fu_id);
        _ret.put("fu_nd",fu_nd);
        _ret.put("ui_tel",ui_tel);
        _ret.put("ui_id",ui_id);
        _ret.put("ui_nd",ui_nd);
        _ret.put("name",name);
        _ret.put("sfz_number",sfz_number);
        _ret.put("sfz_img_url",sfz_img_url);
        _ret.put("sfz_img_back_url",sfz_img_back_url);
        _ret.put("state",state);
        _ret.put("is_del",is_del);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("mac",mac);
        _ret.put("gather_id",gather_id);
        _ret.put("note",note);
        _ret.put("fingerprint_state",fingerprint_state);
        _ret.put("finger_veno_state",finger_veno_state);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Finger_userinfo_new clone2(){
        try{
            return (Finger_userinfo_new) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
