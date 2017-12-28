package com.park.bean;

import java.io.*;
import java.util.*;

//magnetic_device
@SuppressWarnings({"serial"})
public class Magnetic_device implements Cloneable , Serializable{

    //public static String[] carrays ={"id","pi_id","area_code","gov_num","car_dev_num","android_dev_num","state","ctime","utime","ptime","note","fault_count","is_del"};

    public long id;//bigint(20)    
    public long pi_id;//bigint(20)    停车场主键ID
    public String area_code="";//varchar(60)    地址编码
    public String gov_num="";//varchar(60)    政府拟定的车位编码（例如：ASD123）
    public String car_dev_num="";//varchar(60)    车位设备编码
    public String android_dev_num="";//varchar(60)    android板子设备编码
    public int state;//int(11)    车位设备状态（0：无车1：有车2：故障）
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    修改时间
    public java.util.Date ptime=new java.util.Date();//datetime    上次推送时间
    public String note="";//varchar(100)    备注
    public int fault_count;//int(11)    设备故障计数器（当设备恢复正常后需要进行清零）
    public int is_del;//int(11)    是否删除（0：正常1：删除（逻辑删除））



    public long getId(){
        return id;
    }

    public void setId(long value){
        this.id= value;
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

    public String getGov_num(){
        return gov_num;
    }

    public void setGov_num(String value){
    	if(value == null){
           value = "";
        }
        this.gov_num= value;
    }

    public String getCar_dev_num(){
        return car_dev_num;
    }

    public void setCar_dev_num(String value){
    	if(value == null){
           value = "";
        }
        this.car_dev_num= value;
    }

    public String getAndroid_dev_num(){
        return android_dev_num;
    }

    public void setAndroid_dev_num(String value){
    	if(value == null){
           value = "";
        }
        this.android_dev_num= value;
    }

    public int getState(){
        return state;
    }

    public void setState(int value){
        this.state= value;
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

    public java.util.Date getPtime(){
        return ptime;
    }

    public void setPtime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.ptime= value;
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

    public int getFault_count(){
        return fault_count;
    }

    public void setFault_count(int value){
        this.fault_count= value;
    }

    public int getIs_del(){
        return is_del;
    }

    public void setIs_del(int value){
        this.is_del= value;
    }



    public static Magnetic_device newMagnetic_device(long id, long pi_id, String area_code, String gov_num, String car_dev_num, String android_dev_num, int state, java.util.Date ctime, java.util.Date utime, java.util.Date ptime, String note, int fault_count, int is_del) {
        Magnetic_device ret = new Magnetic_device();
        ret.setId(id);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setGov_num(gov_num);
        ret.setCar_dev_num(car_dev_num);
        ret.setAndroid_dev_num(android_dev_num);
        ret.setState(state);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setPtime(ptime);
        ret.setNote(note);
        ret.setFault_count(fault_count);
        ret.setIs_del(is_del);
        return ret;    
    }

    public void assignment(Magnetic_device magnetic_device) {
        long id = magnetic_device.getId();
        long pi_id = magnetic_device.getPi_id();
        String area_code = magnetic_device.getArea_code();
        String gov_num = magnetic_device.getGov_num();
        String car_dev_num = magnetic_device.getCar_dev_num();
        String android_dev_num = magnetic_device.getAndroid_dev_num();
        int state = magnetic_device.getState();
        java.util.Date ctime = magnetic_device.getCtime();
        java.util.Date utime = magnetic_device.getUtime();
        java.util.Date ptime = magnetic_device.getPtime();
        String note = magnetic_device.getNote();
        int fault_count = magnetic_device.getFault_count();
        int is_del = magnetic_device.getIs_del();

        this.setId(id);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setGov_num(gov_num);
        this.setCar_dev_num(car_dev_num);
        this.setAndroid_dev_num(android_dev_num);
        this.setState(state);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setPtime(ptime);
        this.setNote(note);
        this.setFault_count(fault_count);
        this.setIs_del(is_del);

    }

    @SuppressWarnings("unused")
    public static void getMagnetic_device(Magnetic_device magnetic_device ){
        long id = magnetic_device.getId();
        long pi_id = magnetic_device.getPi_id();
        String area_code = magnetic_device.getArea_code();
        String gov_num = magnetic_device.getGov_num();
        String car_dev_num = magnetic_device.getCar_dev_num();
        String android_dev_num = magnetic_device.getAndroid_dev_num();
        int state = magnetic_device.getState();
        java.util.Date ctime = magnetic_device.getCtime();
        java.util.Date utime = magnetic_device.getUtime();
        java.util.Date ptime = magnetic_device.getPtime();
        String note = magnetic_device.getNote();
        int fault_count = magnetic_device.getFault_count();
        int is_del = magnetic_device.getIs_del();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Magnetic_device magnetic_device){
        long id = magnetic_device.getId();
        long pi_id = magnetic_device.getPi_id();
        String area_code = magnetic_device.getArea_code();
        String gov_num = magnetic_device.getGov_num();
        String car_dev_num = magnetic_device.getCar_dev_num();
        String android_dev_num = magnetic_device.getAndroid_dev_num();
        int state = magnetic_device.getState();
        java.util.Date ctime = magnetic_device.getCtime();
        java.util.Date utime = magnetic_device.getUtime();
        java.util.Date ptime = magnetic_device.getPtime();
        String note = magnetic_device.getNote();
        int fault_count = magnetic_device.getFault_count();
        int is_del = magnetic_device.getIs_del();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("gov_num",gov_num);
        _ret.put("car_dev_num",car_dev_num);
        _ret.put("android_dev_num",android_dev_num);
        _ret.put("state",state);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("ptime",ptime);
        _ret.put("note",note);
        _ret.put("fault_count",fault_count);
        _ret.put("is_del",is_del);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Magnetic_device clone2(){
        try{
            return (Magnetic_device) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
