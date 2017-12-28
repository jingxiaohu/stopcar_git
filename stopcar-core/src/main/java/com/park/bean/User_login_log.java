package com.park.bean;

import java.io.*;
import java.util.*;

//user_login_log
@SuppressWarnings({"serial"})
public class User_login_log implements Cloneable , Serializable{

    //public static String[] carrays ={"id","ui_id","ctime","utime","lng","lat","flag","ull_ip","note"};

    public long id;//bigint(20)    主键ID
    public long ui_id;//bigint(20)    用户ID
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String lng="";//varchar(60)    经度
    public String lat="";//varchar(60)    纬度
    public int flag;//int(11)    登陆设备类型0:android1:IOS2:web3:pc
    public String ull_ip="";//varchar(60)    登陆IP
    public String note="";//varchar(100)    备注



    public long getId(){
        return id;
    }

    public void setId(long value){
        this.id= value;
    }

    public long getUi_id(){
        return ui_id;
    }

    public void setUi_id(long value){
        this.ui_id= value;
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

    public String getLng(){
        return lng;
    }

    public void setLng(String value){
    	if(value == null){
           value = "";
        }
        this.lng= value;
    }

    public String getLat(){
        return lat;
    }

    public void setLat(String value){
    	if(value == null){
           value = "";
        }
        this.lat= value;
    }

    public int getFlag(){
        return flag;
    }

    public void setFlag(int value){
        this.flag= value;
    }

    public String getUll_ip(){
        return ull_ip;
    }

    public void setUll_ip(String value){
    	if(value == null){
           value = "";
        }
        this.ull_ip= value;
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



    public static User_login_log newUser_login_log(long id, long ui_id, java.util.Date ctime, java.util.Date utime, String lng, String lat, int flag, String ull_ip, String note) {
        User_login_log ret = new User_login_log();
        ret.setId(id);
        ret.setUi_id(ui_id);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setLng(lng);
        ret.setLat(lat);
        ret.setFlag(flag);
        ret.setUll_ip(ull_ip);
        ret.setNote(note);
        return ret;    
    }

    public void assignment(User_login_log user_login_log) {
        long id = user_login_log.getId();
        long ui_id = user_login_log.getUi_id();
        java.util.Date ctime = user_login_log.getCtime();
        java.util.Date utime = user_login_log.getUtime();
        String lng = user_login_log.getLng();
        String lat = user_login_log.getLat();
        int flag = user_login_log.getFlag();
        String ull_ip = user_login_log.getUll_ip();
        String note = user_login_log.getNote();

        this.setId(id);
        this.setUi_id(ui_id);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setLng(lng);
        this.setLat(lat);
        this.setFlag(flag);
        this.setUll_ip(ull_ip);
        this.setNote(note);

    }

    @SuppressWarnings("unused")
    public static void getUser_login_log(User_login_log user_login_log ){
        long id = user_login_log.getId();
        long ui_id = user_login_log.getUi_id();
        java.util.Date ctime = user_login_log.getCtime();
        java.util.Date utime = user_login_log.getUtime();
        String lng = user_login_log.getLng();
        String lat = user_login_log.getLat();
        int flag = user_login_log.getFlag();
        String ull_ip = user_login_log.getUll_ip();
        String note = user_login_log.getNote();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(User_login_log user_login_log){
        long id = user_login_log.getId();
        long ui_id = user_login_log.getUi_id();
        java.util.Date ctime = user_login_log.getCtime();
        java.util.Date utime = user_login_log.getUtime();
        String lng = user_login_log.getLng();
        String lat = user_login_log.getLat();
        int flag = user_login_log.getFlag();
        String ull_ip = user_login_log.getUll_ip();
        String note = user_login_log.getNote();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("ui_id",ui_id);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("lng",lng);
        _ret.put("lat",lat);
        _ret.put("flag",flag);
        _ret.put("ull_ip",ull_ip);
        _ret.put("note",note);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public User_login_log clone2(){
        try{
            return (User_login_log) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
