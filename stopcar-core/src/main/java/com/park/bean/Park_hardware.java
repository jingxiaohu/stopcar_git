package com.park.bean;

import java.io.*;
import java.util.*;

//park_hardware
@SuppressWarnings({"serial"})
public class Park_hardware implements Cloneable , Serializable{

    //public static String[] carrays ={"ph_id","pi_id","area_code","type","ph_mac","ph_licence","park_type","ctime","utime","ph_loginname","ph_password","ph_state","note","token","versioncode"};

    public long ph_id;//bigint(20)    主键ID
    public long pi_id;//bigint(20)    停车场主键ID
    public String area_code="";//varchar(30)    地址区域编码
    public int type;//int(11)    硬件设备类型(0:未知1：PDA设备2：地磁设备（抽象android板子）3：蓝牙车位锁)
    public String ph_mac="";//varchar(80)    硬件设备MAC地址
    public String ph_licence="";//varchar(80)    硬件设备串号
    public int park_type;//int(11)    停车场类型（0：道闸1：占道2：立体车库）
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String ph_loginname="";//varchar(100)    硬件设备登录帐号
    public String ph_password="";//varchar(100)    硬件设备登录密码
    public int ph_state;//int(11)    硬件设备状态（0：正常1：异常）
    public String note="";//varchar(100)    备注
    public String token="";//varchar(80)    授权tokon
    public int versioncode;//int(11)    当前内部版本号(例如:1,2,3,4)



    public long getPh_id(){
        return ph_id;
    }

    public void setPh_id(long value){
        this.ph_id= value;
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

    public int getType(){
        return type;
    }

    public void setType(int value){
        this.type= value;
    }

    public String getPh_mac(){
        return ph_mac;
    }

    public void setPh_mac(String value){
    	if(value == null){
           value = "";
        }
        this.ph_mac= value;
    }

    public String getPh_licence(){
        return ph_licence;
    }

    public void setPh_licence(String value){
    	if(value == null){
           value = "";
        }
        this.ph_licence= value;
    }

    public int getPark_type(){
        return park_type;
    }

    public void setPark_type(int value){
        this.park_type= value;
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

    public String getPh_loginname(){
        return ph_loginname;
    }

    public void setPh_loginname(String value){
    	if(value == null){
           value = "";
        }
        this.ph_loginname= value;
    }

    public String getPh_password(){
        return ph_password;
    }

    public void setPh_password(String value){
    	if(value == null){
           value = "";
        }
        this.ph_password= value;
    }

    public int getPh_state(){
        return ph_state;
    }

    public void setPh_state(int value){
        this.ph_state= value;
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

    public String getToken(){
        return token;
    }

    public void setToken(String value){
    	if(value == null){
           value = "";
        }
        this.token= value;
    }

    public int getVersioncode(){
        return versioncode;
    }

    public void setVersioncode(int value){
        this.versioncode= value;
    }



    public static Park_hardware newPark_hardware(long ph_id, long pi_id, String area_code, int type, String ph_mac, String ph_licence, int park_type, java.util.Date ctime, java.util.Date utime, String ph_loginname, String ph_password, int ph_state, String note, String token, int versioncode) {
        Park_hardware ret = new Park_hardware();
        ret.setPh_id(ph_id);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setType(type);
        ret.setPh_mac(ph_mac);
        ret.setPh_licence(ph_licence);
        ret.setPark_type(park_type);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setPh_loginname(ph_loginname);
        ret.setPh_password(ph_password);
        ret.setPh_state(ph_state);
        ret.setNote(note);
        ret.setToken(token);
        ret.setVersioncode(versioncode);
        return ret;    
    }

    public void assignment(Park_hardware park_hardware) {
        long ph_id = park_hardware.getPh_id();
        long pi_id = park_hardware.getPi_id();
        String area_code = park_hardware.getArea_code();
        int type = park_hardware.getType();
        String ph_mac = park_hardware.getPh_mac();
        String ph_licence = park_hardware.getPh_licence();
        int park_type = park_hardware.getPark_type();
        java.util.Date ctime = park_hardware.getCtime();
        java.util.Date utime = park_hardware.getUtime();
        String ph_loginname = park_hardware.getPh_loginname();
        String ph_password = park_hardware.getPh_password();
        int ph_state = park_hardware.getPh_state();
        String note = park_hardware.getNote();
        String token = park_hardware.getToken();
        int versioncode = park_hardware.getVersioncode();

        this.setPh_id(ph_id);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setType(type);
        this.setPh_mac(ph_mac);
        this.setPh_licence(ph_licence);
        this.setPark_type(park_type);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setPh_loginname(ph_loginname);
        this.setPh_password(ph_password);
        this.setPh_state(ph_state);
        this.setNote(note);
        this.setToken(token);
        this.setVersioncode(versioncode);

    }

    @SuppressWarnings("unused")
    public static void getPark_hardware(Park_hardware park_hardware ){
        long ph_id = park_hardware.getPh_id();
        long pi_id = park_hardware.getPi_id();
        String area_code = park_hardware.getArea_code();
        int type = park_hardware.getType();
        String ph_mac = park_hardware.getPh_mac();
        String ph_licence = park_hardware.getPh_licence();
        int park_type = park_hardware.getPark_type();
        java.util.Date ctime = park_hardware.getCtime();
        java.util.Date utime = park_hardware.getUtime();
        String ph_loginname = park_hardware.getPh_loginname();
        String ph_password = park_hardware.getPh_password();
        int ph_state = park_hardware.getPh_state();
        String note = park_hardware.getNote();
        String token = park_hardware.getToken();
        int versioncode = park_hardware.getVersioncode();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Park_hardware park_hardware){
        long ph_id = park_hardware.getPh_id();
        long pi_id = park_hardware.getPi_id();
        String area_code = park_hardware.getArea_code();
        int type = park_hardware.getType();
        String ph_mac = park_hardware.getPh_mac();
        String ph_licence = park_hardware.getPh_licence();
        int park_type = park_hardware.getPark_type();
        java.util.Date ctime = park_hardware.getCtime();
        java.util.Date utime = park_hardware.getUtime();
        String ph_loginname = park_hardware.getPh_loginname();
        String ph_password = park_hardware.getPh_password();
        int ph_state = park_hardware.getPh_state();
        String note = park_hardware.getNote();
        String token = park_hardware.getToken();
        int versioncode = park_hardware.getVersioncode();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("ph_id",ph_id);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("type",type);
        _ret.put("ph_mac",ph_mac);
        _ret.put("ph_licence",ph_licence);
        _ret.put("park_type",park_type);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("ph_loginname",ph_loginname);
        _ret.put("ph_password",ph_password);
        _ret.put("ph_state",ph_state);
        _ret.put("note",note);
        _ret.put("token",token);
        _ret.put("versioncode",versioncode);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Park_hardware clone2(){
        try{
            return (Park_hardware) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
