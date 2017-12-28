package com.park.bean;

import java.io.*;
import java.util.*;

//park_authorize
@SuppressWarnings({"serial"})
public class Park_authorize implements Cloneable , Serializable{

    //public static String[] carrays ={"pa_id","pi_id","area_code","pi_name","secret_key","state","duration","last_time","encrypt_type","is_del","ctime","utime","note"};

    public long pa_id;//bigint(20)    主键ID
    public long pi_id;//bigint(20)    停车场主键ID
    public String area_code="";//varchar(100)    区域代码
    public String pi_name="";//varchar(200)    停车场名称
    public String secret_key="";//varchar(255)    授权加密key
    public int state;//int(11)    授权状态(0:未知1：成功授权2：授权失败)
    public int duration;//int(11)    授权使用时长（单位小时）
    public java.util.Date last_time=new java.util.Date();//datetime    上次授权成功时间
    public int encrypt_type;//int(11)    加密方式（1RSA2DES3MD5）
    public int is_del;//int(11)    是否开启或者关闭（0：正常1：关闭）
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String note="";//varchar(100)    备注



    public long getPa_id(){
        return pa_id;
    }

    public void setPa_id(long value){
        this.pa_id= value;
    }

    public long getPi_id(){
        return pi_id;
    }

    public void setPi_id(long value){
        this.pi_id= value;
    }

    public String getArea_code(){
        return area_code;
    }

    public void setArea_code(String value){
    	if(value == null){
           value = "";
        }
        this.area_code= value;
    }

    public String getPi_name(){
        return pi_name;
    }

    public void setPi_name(String value){
    	if(value == null){
           value = "";
        }
        this.pi_name= value;
    }

    public String getSecret_key(){
        return secret_key;
    }

    public void setSecret_key(String value){
    	if(value == null){
           value = "";
        }
        this.secret_key= value;
    }

    public int getState(){
        return state;
    }

    public void setState(int value){
        this.state= value;
    }

    public int getDuration(){
        return duration;
    }

    public void setDuration(int value){
        this.duration= value;
    }

    public java.util.Date getLast_time(){
        return last_time;
    }

    public void setLast_time(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.last_time= value;
    }

    public int getEncrypt_type(){
        return encrypt_type;
    }

    public void setEncrypt_type(int value){
        this.encrypt_type= value;
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



    public static Park_authorize newPark_authorize(long pa_id, long pi_id, String area_code, String pi_name, String secret_key, int state, int duration, java.util.Date last_time, int encrypt_type, int is_del, java.util.Date ctime, java.util.Date utime, String note) {
        Park_authorize ret = new Park_authorize();
        ret.setPa_id(pa_id);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setPi_name(pi_name);
        ret.setSecret_key(secret_key);
        ret.setState(state);
        ret.setDuration(duration);
        ret.setLast_time(last_time);
        ret.setEncrypt_type(encrypt_type);
        ret.setIs_del(is_del);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setNote(note);
        return ret;    
    }

    public void assignment(Park_authorize park_authorize) {
        long pa_id = park_authorize.getPa_id();
        long pi_id = park_authorize.getPi_id();
        String area_code = park_authorize.getArea_code();
        String pi_name = park_authorize.getPi_name();
        String secret_key = park_authorize.getSecret_key();
        int state = park_authorize.getState();
        int duration = park_authorize.getDuration();
        java.util.Date last_time = park_authorize.getLast_time();
        int encrypt_type = park_authorize.getEncrypt_type();
        int is_del = park_authorize.getIs_del();
        java.util.Date ctime = park_authorize.getCtime();
        java.util.Date utime = park_authorize.getUtime();
        String note = park_authorize.getNote();

        this.setPa_id(pa_id);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setPi_name(pi_name);
        this.setSecret_key(secret_key);
        this.setState(state);
        this.setDuration(duration);
        this.setLast_time(last_time);
        this.setEncrypt_type(encrypt_type);
        this.setIs_del(is_del);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setNote(note);

    }

    @SuppressWarnings("unused")
    public static void getPark_authorize(Park_authorize park_authorize ){
        long pa_id = park_authorize.getPa_id();
        long pi_id = park_authorize.getPi_id();
        String area_code = park_authorize.getArea_code();
        String pi_name = park_authorize.getPi_name();
        String secret_key = park_authorize.getSecret_key();
        int state = park_authorize.getState();
        int duration = park_authorize.getDuration();
        java.util.Date last_time = park_authorize.getLast_time();
        int encrypt_type = park_authorize.getEncrypt_type();
        int is_del = park_authorize.getIs_del();
        java.util.Date ctime = park_authorize.getCtime();
        java.util.Date utime = park_authorize.getUtime();
        String note = park_authorize.getNote();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Park_authorize park_authorize){
        long pa_id = park_authorize.getPa_id();
        long pi_id = park_authorize.getPi_id();
        String area_code = park_authorize.getArea_code();
        String pi_name = park_authorize.getPi_name();
        String secret_key = park_authorize.getSecret_key();
        int state = park_authorize.getState();
        int duration = park_authorize.getDuration();
        java.util.Date last_time = park_authorize.getLast_time();
        int encrypt_type = park_authorize.getEncrypt_type();
        int is_del = park_authorize.getIs_del();
        java.util.Date ctime = park_authorize.getCtime();
        java.util.Date utime = park_authorize.getUtime();
        String note = park_authorize.getNote();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("pa_id",pa_id);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("pi_name",pi_name);
        _ret.put("secret_key",secret_key);
        _ret.put("state",state);
        _ret.put("duration",duration);
        _ret.put("last_time",last_time);
        _ret.put("encrypt_type",encrypt_type);
        _ret.put("is_del",is_del);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("note",note);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Park_authorize clone2(){
        try{
            return (Park_authorize) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
