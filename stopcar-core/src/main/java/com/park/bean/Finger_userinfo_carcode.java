package com.park.bean;

import java.io.*;
import java.util.*;

//finger_userinfo_carcode
@SuppressWarnings({"serial"})
public class Finger_userinfo_carcode implements Cloneable , Serializable{

    //public static String[] carrays ={"fuc_id","fu_id","sfz_number","car_code","is_run","isi_del","ctime","utime","note","fu_nd"};

    public long fuc_id;//bigint(20)    主键ID
    public long fu_id;//bigint(20)    用户指纹采集和绑卡表外键（finger_userinfo）ID
    public String sfz_number="";//varchar(60)    用户身份证号码
    public String car_code="";//varchar(60)    用户车牌
    public int is_run;//int(11)    是否启用(0:启用1：关闭)
    public int isi_del;//int(11)    是否删除(0:正常1：逻辑删除)
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    修改时间
    public String note="";//varchar(100)    备注
    public String fu_nd="";//varchar(80)    指纹用户基本信息表(finger_userinfo)ND



    public long getFuc_id(){
        return fuc_id;
    }

    public void setFuc_id(long value){
        this.fuc_id= value;
    }

    public long getFu_id(){
        return fu_id;
    }

    public void setFu_id(long value){
        this.fu_id= value;
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

    public String getCar_code(){
        return car_code;
    }

    public void setCar_code(String value){
    	if(value == null){
           value = "";
        }
        this.car_code= value;
    }

    public int getIs_run(){
        return is_run;
    }

    public void setIs_run(int value){
        this.is_run= value;
    }

    public int getIsi_del(){
        return isi_del;
    }

    public void setIsi_del(int value){
        this.isi_del= value;
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

    public String getFu_nd(){
        return fu_nd;
    }

    public void setFu_nd(String value){
    	if(value == null){
           value = "";
        }
        this.fu_nd= value;
    }



    public static Finger_userinfo_carcode newFinger_userinfo_carcode(long fuc_id, long fu_id, String sfz_number, String car_code, int is_run, int isi_del, java.util.Date ctime, java.util.Date utime, String note, String fu_nd) {
        Finger_userinfo_carcode ret = new Finger_userinfo_carcode();
        ret.setFuc_id(fuc_id);
        ret.setFu_id(fu_id);
        ret.setSfz_number(sfz_number);
        ret.setCar_code(car_code);
        ret.setIs_run(is_run);
        ret.setIsi_del(isi_del);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setNote(note);
        ret.setFu_nd(fu_nd);
        return ret;    
    }

    public void assignment(Finger_userinfo_carcode finger_userinfo_carcode) {
        long fuc_id = finger_userinfo_carcode.getFuc_id();
        long fu_id = finger_userinfo_carcode.getFu_id();
        String sfz_number = finger_userinfo_carcode.getSfz_number();
        String car_code = finger_userinfo_carcode.getCar_code();
        int is_run = finger_userinfo_carcode.getIs_run();
        int isi_del = finger_userinfo_carcode.getIsi_del();
        java.util.Date ctime = finger_userinfo_carcode.getCtime();
        java.util.Date utime = finger_userinfo_carcode.getUtime();
        String note = finger_userinfo_carcode.getNote();
        String fu_nd = finger_userinfo_carcode.getFu_nd();

        this.setFuc_id(fuc_id);
        this.setFu_id(fu_id);
        this.setSfz_number(sfz_number);
        this.setCar_code(car_code);
        this.setIs_run(is_run);
        this.setIsi_del(isi_del);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setNote(note);
        this.setFu_nd(fu_nd);

    }

    @SuppressWarnings("unused")
    public static void getFinger_userinfo_carcode(Finger_userinfo_carcode finger_userinfo_carcode ){
        long fuc_id = finger_userinfo_carcode.getFuc_id();
        long fu_id = finger_userinfo_carcode.getFu_id();
        String sfz_number = finger_userinfo_carcode.getSfz_number();
        String car_code = finger_userinfo_carcode.getCar_code();
        int is_run = finger_userinfo_carcode.getIs_run();
        int isi_del = finger_userinfo_carcode.getIsi_del();
        java.util.Date ctime = finger_userinfo_carcode.getCtime();
        java.util.Date utime = finger_userinfo_carcode.getUtime();
        String note = finger_userinfo_carcode.getNote();
        String fu_nd = finger_userinfo_carcode.getFu_nd();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Finger_userinfo_carcode finger_userinfo_carcode){
        long fuc_id = finger_userinfo_carcode.getFuc_id();
        long fu_id = finger_userinfo_carcode.getFu_id();
        String sfz_number = finger_userinfo_carcode.getSfz_number();
        String car_code = finger_userinfo_carcode.getCar_code();
        int is_run = finger_userinfo_carcode.getIs_run();
        int isi_del = finger_userinfo_carcode.getIsi_del();
        java.util.Date ctime = finger_userinfo_carcode.getCtime();
        java.util.Date utime = finger_userinfo_carcode.getUtime();
        String note = finger_userinfo_carcode.getNote();
        String fu_nd = finger_userinfo_carcode.getFu_nd();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("fuc_id",fuc_id);
        _ret.put("fu_id",fu_id);
        _ret.put("sfz_number",sfz_number);
        _ret.put("car_code",car_code);
        _ret.put("is_run",is_run);
        _ret.put("isi_del",isi_del);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("note",note);
        _ret.put("fu_nd",fu_nd);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Finger_userinfo_carcode clone2(){
        try{
            return (Finger_userinfo_carcode) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
