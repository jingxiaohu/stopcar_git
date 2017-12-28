package com.park.bean;

import java.io.*;
import java.util.*;

//finger_userinfo_relation
@SuppressWarnings({"serial"})
public class Finger_userinfo_relation implements Cloneable , Serializable{

    //public static String[] carrays ={"fur_id","fu_id","fingerprint","finger_veno","fingerprint_hash","finger_veno_hash","is_del","ctime","utime","note","fingerprint_img","finger_veno_img","fu_nd"};

    public long fur_id;//bigint(20)    主键ID
    public long fu_id;//bigint(20)    指纹用户基本信息表主键ID（外键）
    public String fingerprint="";//text    用户指纹
    public String finger_veno="";//text    用户指静脉
    public String fingerprint_hash="";//varchar(255)    用户指纹图片hash
    public String finger_veno_hash="";//varchar(255)    用户指静脉图片hash
    public int is_del;//int(11)    是否删除(0:正常1：删除)
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    修改时间
    public String note="";//varchar(100)    备注
    public String fingerprint_img="";//varchar(255)    用户指纹图片URL
    public String finger_veno_img="";//varchar(255)    用户指静脉图片URL
    public String fu_nd="";//varchar(80)    指纹用户基本信息表(finger_userinfo)ND



    public long getFur_id(){
        return fur_id;
    }

    public void setFur_id(long value){
        this.fur_id= value;
    }

    public long getFu_id(){
        return fu_id;
    }

    public void setFu_id(long value){
        this.fu_id= value;
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

    public String getNote(){
        return note;
    }

    public void setNote(String value){
    	if(value == null){
           value = "";
        }
        this.note= value;
    }

    public String getFingerprint_img(){
        return fingerprint_img;
    }

    public void setFingerprint_img(String value){
    	if(value == null){
           value = "";
        }
        this.fingerprint_img= value;
    }

    public String getFinger_veno_img(){
        return finger_veno_img;
    }

    public void setFinger_veno_img(String value){
    	if(value == null){
           value = "";
        }
        this.finger_veno_img= value;
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



    public static Finger_userinfo_relation newFinger_userinfo_relation(long fur_id, long fu_id, String fingerprint, String finger_veno, String fingerprint_hash, String finger_veno_hash, int is_del, java.util.Date ctime, java.util.Date utime, String note, String fingerprint_img, String finger_veno_img, String fu_nd) {
        Finger_userinfo_relation ret = new Finger_userinfo_relation();
        ret.setFur_id(fur_id);
        ret.setFu_id(fu_id);
        ret.setFingerprint(fingerprint);
        ret.setFinger_veno(finger_veno);
        ret.setFingerprint_hash(fingerprint_hash);
        ret.setFinger_veno_hash(finger_veno_hash);
        ret.setIs_del(is_del);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setNote(note);
        ret.setFingerprint_img(fingerprint_img);
        ret.setFinger_veno_img(finger_veno_img);
        ret.setFu_nd(fu_nd);
        return ret;    
    }

    public void assignment(Finger_userinfo_relation finger_userinfo_relation) {
        long fur_id = finger_userinfo_relation.getFur_id();
        long fu_id = finger_userinfo_relation.getFu_id();
        String fingerprint = finger_userinfo_relation.getFingerprint();
        String finger_veno = finger_userinfo_relation.getFinger_veno();
        String fingerprint_hash = finger_userinfo_relation.getFingerprint_hash();
        String finger_veno_hash = finger_userinfo_relation.getFinger_veno_hash();
        int is_del = finger_userinfo_relation.getIs_del();
        java.util.Date ctime = finger_userinfo_relation.getCtime();
        java.util.Date utime = finger_userinfo_relation.getUtime();
        String note = finger_userinfo_relation.getNote();
        String fingerprint_img = finger_userinfo_relation.getFingerprint_img();
        String finger_veno_img = finger_userinfo_relation.getFinger_veno_img();
        String fu_nd = finger_userinfo_relation.getFu_nd();

        this.setFur_id(fur_id);
        this.setFu_id(fu_id);
        this.setFingerprint(fingerprint);
        this.setFinger_veno(finger_veno);
        this.setFingerprint_hash(fingerprint_hash);
        this.setFinger_veno_hash(finger_veno_hash);
        this.setIs_del(is_del);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setNote(note);
        this.setFingerprint_img(fingerprint_img);
        this.setFinger_veno_img(finger_veno_img);
        this.setFu_nd(fu_nd);

    }

    @SuppressWarnings("unused")
    public static void getFinger_userinfo_relation(Finger_userinfo_relation finger_userinfo_relation ){
        long fur_id = finger_userinfo_relation.getFur_id();
        long fu_id = finger_userinfo_relation.getFu_id();
        String fingerprint = finger_userinfo_relation.getFingerprint();
        String finger_veno = finger_userinfo_relation.getFinger_veno();
        String fingerprint_hash = finger_userinfo_relation.getFingerprint_hash();
        String finger_veno_hash = finger_userinfo_relation.getFinger_veno_hash();
        int is_del = finger_userinfo_relation.getIs_del();
        java.util.Date ctime = finger_userinfo_relation.getCtime();
        java.util.Date utime = finger_userinfo_relation.getUtime();
        String note = finger_userinfo_relation.getNote();
        String fingerprint_img = finger_userinfo_relation.getFingerprint_img();
        String finger_veno_img = finger_userinfo_relation.getFinger_veno_img();
        String fu_nd = finger_userinfo_relation.getFu_nd();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Finger_userinfo_relation finger_userinfo_relation){
        long fur_id = finger_userinfo_relation.getFur_id();
        long fu_id = finger_userinfo_relation.getFu_id();
        String fingerprint = finger_userinfo_relation.getFingerprint();
        String finger_veno = finger_userinfo_relation.getFinger_veno();
        String fingerprint_hash = finger_userinfo_relation.getFingerprint_hash();
        String finger_veno_hash = finger_userinfo_relation.getFinger_veno_hash();
        int is_del = finger_userinfo_relation.getIs_del();
        java.util.Date ctime = finger_userinfo_relation.getCtime();
        java.util.Date utime = finger_userinfo_relation.getUtime();
        String note = finger_userinfo_relation.getNote();
        String fingerprint_img = finger_userinfo_relation.getFingerprint_img();
        String finger_veno_img = finger_userinfo_relation.getFinger_veno_img();
        String fu_nd = finger_userinfo_relation.getFu_nd();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("fur_id",fur_id);
        _ret.put("fu_id",fu_id);
        _ret.put("fingerprint",fingerprint);
        _ret.put("finger_veno",finger_veno);
        _ret.put("fingerprint_hash",fingerprint_hash);
        _ret.put("finger_veno_hash",finger_veno_hash);
        _ret.put("is_del",is_del);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("note",note);
        _ret.put("fingerprint_img",fingerprint_img);
        _ret.put("finger_veno_img",finger_veno_img);
        _ret.put("fu_nd",fu_nd);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Finger_userinfo_relation clone2(){
        try{
            return (Finger_userinfo_relation) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
