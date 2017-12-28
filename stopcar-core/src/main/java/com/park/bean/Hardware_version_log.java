package com.park.bean;

import java.io.*;
import java.util.*;

//hardware_version_log
@SuppressWarnings({"serial"})
public class Hardware_version_log implements Cloneable , Serializable{

    //public static String[] carrays ={"id","device_id","mac","pda_sn","device_name","device_type","pi_id","area_code","pi_name","ctime","utime","vnum","note"};

    public long id;//bigint(20)    主键ID
    public long device_id;//bigint(20)    设备基本信息表主键ID
    public String mac="";//varchar(100)    PDA设备MAC
    public String pda_sn="";//varchar(100)    PDA设备串号
    public String device_name="";//varchar(100)    设备名称(例如：普通占到停车PDA，地磁android板)
    public int device_type;//int(11)    设备类型(0:未指定1：PDA2：地磁android板)
    public long pi_id;//bigint(20)    关联的停车场ID
    public String area_code="";//varchar(60)    关联的停车场地址编码
    public String pi_name="";//varchar(100)    停车场名称
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public int vnum;//int(11)    当前内部版本号
    public String note="";//varchar(100)    备注



    public long getId(){
        return id;
    }

    public void setId(long value){
        this.id= value;
    }

    public long getDevice_id(){
        return device_id;
    }

    public void setDevice_id(long value){
        this.device_id= value;
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

    public String getPda_sn(){
        return pda_sn;
    }

    public void setPda_sn(String value){
    	if(value == null){
           value = "";
        }
        this.pda_sn= value;
    }

    public String getDevice_name(){
        return device_name;
    }

    public void setDevice_name(String value){
    	if(value == null){
           value = "";
        }
        this.device_name= value;
    }

    public int getDevice_type(){
        return device_type;
    }

    public void setDevice_type(int value){
        this.device_type= value;
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

    public int getVnum(){
        return vnum;
    }

    public void setVnum(int value){
        this.vnum= value;
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



    public static Hardware_version_log newHardware_version_log(long id, long device_id, String mac, String pda_sn, String device_name, int device_type, long pi_id, String area_code, String pi_name, java.util.Date ctime, java.util.Date utime, int vnum, String note) {
        Hardware_version_log ret = new Hardware_version_log();
        ret.setId(id);
        ret.setDevice_id(device_id);
        ret.setMac(mac);
        ret.setPda_sn(pda_sn);
        ret.setDevice_name(device_name);
        ret.setDevice_type(device_type);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setPi_name(pi_name);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setVnum(vnum);
        ret.setNote(note);
        return ret;    
    }

    public void assignment(Hardware_version_log hardware_version_log) {
        long id = hardware_version_log.getId();
        long device_id = hardware_version_log.getDevice_id();
        String mac = hardware_version_log.getMac();
        String pda_sn = hardware_version_log.getPda_sn();
        String device_name = hardware_version_log.getDevice_name();
        int device_type = hardware_version_log.getDevice_type();
        long pi_id = hardware_version_log.getPi_id();
        String area_code = hardware_version_log.getArea_code();
        String pi_name = hardware_version_log.getPi_name();
        java.util.Date ctime = hardware_version_log.getCtime();
        java.util.Date utime = hardware_version_log.getUtime();
        int vnum = hardware_version_log.getVnum();
        String note = hardware_version_log.getNote();

        this.setId(id);
        this.setDevice_id(device_id);
        this.setMac(mac);
        this.setPda_sn(pda_sn);
        this.setDevice_name(device_name);
        this.setDevice_type(device_type);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setPi_name(pi_name);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setVnum(vnum);
        this.setNote(note);

    }

    @SuppressWarnings("unused")
    public static void getHardware_version_log(Hardware_version_log hardware_version_log ){
        long id = hardware_version_log.getId();
        long device_id = hardware_version_log.getDevice_id();
        String mac = hardware_version_log.getMac();
        String pda_sn = hardware_version_log.getPda_sn();
        String device_name = hardware_version_log.getDevice_name();
        int device_type = hardware_version_log.getDevice_type();
        long pi_id = hardware_version_log.getPi_id();
        String area_code = hardware_version_log.getArea_code();
        String pi_name = hardware_version_log.getPi_name();
        java.util.Date ctime = hardware_version_log.getCtime();
        java.util.Date utime = hardware_version_log.getUtime();
        int vnum = hardware_version_log.getVnum();
        String note = hardware_version_log.getNote();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Hardware_version_log hardware_version_log){
        long id = hardware_version_log.getId();
        long device_id = hardware_version_log.getDevice_id();
        String mac = hardware_version_log.getMac();
        String pda_sn = hardware_version_log.getPda_sn();
        String device_name = hardware_version_log.getDevice_name();
        int device_type = hardware_version_log.getDevice_type();
        long pi_id = hardware_version_log.getPi_id();
        String area_code = hardware_version_log.getArea_code();
        String pi_name = hardware_version_log.getPi_name();
        java.util.Date ctime = hardware_version_log.getCtime();
        java.util.Date utime = hardware_version_log.getUtime();
        int vnum = hardware_version_log.getVnum();
        String note = hardware_version_log.getNote();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("device_id",device_id);
        _ret.put("mac",mac);
        _ret.put("pda_sn",pda_sn);
        _ret.put("device_name",device_name);
        _ret.put("device_type",device_type);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("pi_name",pi_name);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("vnum",vnum);
        _ret.put("note",note);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Hardware_version_log clone2(){
        try{
            return (Hardware_version_log) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
