package com.park.bean;

import java.io.*;
import java.util.*;

//park_device
@SuppressWarnings({"serial"})
public class Park_device implements Cloneable , Serializable{

    //public static String[] carrays ={"pd_id","pi_id","in_out","in_out_code","camera","camera_mac","signo_name","solid_garage_mac","solid_garage_sn","ctime","utime","pd_md5","note","area_code"};

    public long pd_id;//bigint(20)    
    public long pi_id;//bigint(20)    
    public String in_out="";//varchar(30)    出口或者入口入口：enter出口：exit
    public String in_out_code="";//varchar(60)    出入口编号例如（A出口B入口）
    public String camera="";//varchar(100)    摄像头
    public String camera_mac="";//varchar(100)    摄像头MAC
    public String signo_name="";//varchar(100)    道闸名称
    public String solid_garage_mac="";//varchar(80)    立体车库设备MAC
    public String solid_garage_sn="";//varchar(60)    立体车库设备编号
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String pd_md5="";//varchar(80)    校验MD5(pi_id+in_out+in_out_code+camera_mac+signo_name+solid_garage_mac)
    public String note="";//varchar(100)    备注
    public String area_code="";//varchar(20)    省市区代码



    public long getPd_id(){
        return pd_id;
    }

    public void setPd_id(long value){
        this.pd_id= value;
    }

    public long getPi_id(){
        return pi_id;
    }

    public void setPi_id(long value){
        this.pi_id= value;
    }

    public String getIn_out(){
        return in_out;
    }

    public void setIn_out(String value){
    	if(value == null){
           value = "";
        }
        this.in_out= value;
    }

    public String getIn_out_code(){
        return in_out_code;
    }

    public void setIn_out_code(String value){
    	if(value == null){
           value = "";
        }
        this.in_out_code= value;
    }

    public String getCamera(){
        return camera;
    }

    public void setCamera(String value){
    	if(value == null){
           value = "";
        }
        this.camera= value;
    }

    public String getCamera_mac(){
        return camera_mac;
    }

    public void setCamera_mac(String value){
    	if(value == null){
           value = "";
        }
        this.camera_mac= value;
    }

    public String getSigno_name(){
        return signo_name;
    }

    public void setSigno_name(String value){
    	if(value == null){
           value = "";
        }
        this.signo_name= value;
    }

    public String getSolid_garage_mac(){
        return solid_garage_mac;
    }

    public void setSolid_garage_mac(String value){
    	if(value == null){
           value = "";
        }
        this.solid_garage_mac= value;
    }

    public String getSolid_garage_sn(){
        return solid_garage_sn;
    }

    public void setSolid_garage_sn(String value){
    	if(value == null){
           value = "";
        }
        this.solid_garage_sn= value;
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

    public String getPd_md5(){
        return pd_md5;
    }

    public void setPd_md5(String value){
    	if(value == null){
           value = "";
        }
        this.pd_md5= value;
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

    public String getArea_code(){
        return area_code;
    }

    public void setArea_code(String value){
    	if(value == null){
           value = "";
        }
        this.area_code= value;
    }



    public static Park_device newPark_device(long pd_id, long pi_id, String in_out, String in_out_code, String camera, String camera_mac, String signo_name, String solid_garage_mac, String solid_garage_sn, java.util.Date ctime, java.util.Date utime, String pd_md5, String note, String area_code) {
        Park_device ret = new Park_device();
        ret.setPd_id(pd_id);
        ret.setPi_id(pi_id);
        ret.setIn_out(in_out);
        ret.setIn_out_code(in_out_code);
        ret.setCamera(camera);
        ret.setCamera_mac(camera_mac);
        ret.setSigno_name(signo_name);
        ret.setSolid_garage_mac(solid_garage_mac);
        ret.setSolid_garage_sn(solid_garage_sn);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setPd_md5(pd_md5);
        ret.setNote(note);
        ret.setArea_code(area_code);
        return ret;    
    }

    public void assignment(Park_device park_device) {
        long pd_id = park_device.getPd_id();
        long pi_id = park_device.getPi_id();
        String in_out = park_device.getIn_out();
        String in_out_code = park_device.getIn_out_code();
        String camera = park_device.getCamera();
        String camera_mac = park_device.getCamera_mac();
        String signo_name = park_device.getSigno_name();
        String solid_garage_mac = park_device.getSolid_garage_mac();
        String solid_garage_sn = park_device.getSolid_garage_sn();
        java.util.Date ctime = park_device.getCtime();
        java.util.Date utime = park_device.getUtime();
        String pd_md5 = park_device.getPd_md5();
        String note = park_device.getNote();
        String area_code = park_device.getArea_code();

        this.setPd_id(pd_id);
        this.setPi_id(pi_id);
        this.setIn_out(in_out);
        this.setIn_out_code(in_out_code);
        this.setCamera(camera);
        this.setCamera_mac(camera_mac);
        this.setSigno_name(signo_name);
        this.setSolid_garage_mac(solid_garage_mac);
        this.setSolid_garage_sn(solid_garage_sn);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setPd_md5(pd_md5);
        this.setNote(note);
        this.setArea_code(area_code);

    }

    @SuppressWarnings("unused")
    public static void getPark_device(Park_device park_device ){
        long pd_id = park_device.getPd_id();
        long pi_id = park_device.getPi_id();
        String in_out = park_device.getIn_out();
        String in_out_code = park_device.getIn_out_code();
        String camera = park_device.getCamera();
        String camera_mac = park_device.getCamera_mac();
        String signo_name = park_device.getSigno_name();
        String solid_garage_mac = park_device.getSolid_garage_mac();
        String solid_garage_sn = park_device.getSolid_garage_sn();
        java.util.Date ctime = park_device.getCtime();
        java.util.Date utime = park_device.getUtime();
        String pd_md5 = park_device.getPd_md5();
        String note = park_device.getNote();
        String area_code = park_device.getArea_code();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Park_device park_device){
        long pd_id = park_device.getPd_id();
        long pi_id = park_device.getPi_id();
        String in_out = park_device.getIn_out();
        String in_out_code = park_device.getIn_out_code();
        String camera = park_device.getCamera();
        String camera_mac = park_device.getCamera_mac();
        String signo_name = park_device.getSigno_name();
        String solid_garage_mac = park_device.getSolid_garage_mac();
        String solid_garage_sn = park_device.getSolid_garage_sn();
        java.util.Date ctime = park_device.getCtime();
        java.util.Date utime = park_device.getUtime();
        String pd_md5 = park_device.getPd_md5();
        String note = park_device.getNote();
        String area_code = park_device.getArea_code();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("pd_id",pd_id);
        _ret.put("pi_id",pi_id);
        _ret.put("in_out",in_out);
        _ret.put("in_out_code",in_out_code);
        _ret.put("camera",camera);
        _ret.put("camera_mac",camera_mac);
        _ret.put("signo_name",signo_name);
        _ret.put("solid_garage_mac",solid_garage_mac);
        _ret.put("solid_garage_sn",solid_garage_sn);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("pd_md5",pd_md5);
        _ret.put("note",note);
        _ret.put("area_code",area_code);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Park_device clone2(){
        try{
            return (Park_device) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
