package com.park.bean;

import java.io.*;
import java.util.*;

//magnetic_device_log
@SuppressWarnings({"serial"})
public class Magnetic_device_log implements Cloneable , Serializable{

    //public static String[] carrays ={"id","pi_id","area_code","gov_num","car_dev_num","android_dev_num","state","ctime","note","carport_total","carport_yet","carport_space"};

    public long id;//bigint(20)    
    public long pi_id;//bigint(20)    停车场主键ID
    public String area_code="";//varchar(60)    地址编码
    public String gov_num="";//varchar(60)    政府拟定的车位编码（例如：ASD123）
    public String car_dev_num="";//varchar(60)    车位设备编码
    public String android_dev_num="";//varchar(60)    android板子设备编码
    public int state;//int(11)    车位设备状态（0：无车1：有车2：故障）
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public String note="";//varchar(100)    备注
    public int carport_total;//int(11)    车位总数
    public int carport_yet;//int(11)    已停车位数
    public int carport_space;//int(11)    空余车位数



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

    public String getNote(){
        return note;
    }

    public void setNote(String value){
    	if(value == null){
           value = "";
        }
        this.note= value;
    }

    public int getCarport_total(){
        return carport_total;
    }

    public void setCarport_total(int value){
        this.carport_total= value;
    }

    public int getCarport_yet(){
        return carport_yet;
    }

    public void setCarport_yet(int value){
        this.carport_yet= value;
    }

    public int getCarport_space(){
        return carport_space;
    }

    public void setCarport_space(int value){
        this.carport_space= value;
    }



    public static Magnetic_device_log newMagnetic_device_log(long id, long pi_id, String area_code, String gov_num, String car_dev_num, String android_dev_num, int state, java.util.Date ctime, String note, int carport_total, int carport_yet, int carport_space) {
        Magnetic_device_log ret = new Magnetic_device_log();
        ret.setId(id);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setGov_num(gov_num);
        ret.setCar_dev_num(car_dev_num);
        ret.setAndroid_dev_num(android_dev_num);
        ret.setState(state);
        ret.setCtime(ctime);
        ret.setNote(note);
        ret.setCarport_total(carport_total);
        ret.setCarport_yet(carport_yet);
        ret.setCarport_space(carport_space);
        return ret;    
    }

    public void assignment(Magnetic_device_log magnetic_device_log) {
        long id = magnetic_device_log.getId();
        long pi_id = magnetic_device_log.getPi_id();
        String area_code = magnetic_device_log.getArea_code();
        String gov_num = magnetic_device_log.getGov_num();
        String car_dev_num = magnetic_device_log.getCar_dev_num();
        String android_dev_num = magnetic_device_log.getAndroid_dev_num();
        int state = magnetic_device_log.getState();
        java.util.Date ctime = magnetic_device_log.getCtime();
        String note = magnetic_device_log.getNote();
        int carport_total = magnetic_device_log.getCarport_total();
        int carport_yet = magnetic_device_log.getCarport_yet();
        int carport_space = magnetic_device_log.getCarport_space();

        this.setId(id);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setGov_num(gov_num);
        this.setCar_dev_num(car_dev_num);
        this.setAndroid_dev_num(android_dev_num);
        this.setState(state);
        this.setCtime(ctime);
        this.setNote(note);
        this.setCarport_total(carport_total);
        this.setCarport_yet(carport_yet);
        this.setCarport_space(carport_space);

    }

    @SuppressWarnings("unused")
    public static void getMagnetic_device_log(Magnetic_device_log magnetic_device_log ){
        long id = magnetic_device_log.getId();
        long pi_id = magnetic_device_log.getPi_id();
        String area_code = magnetic_device_log.getArea_code();
        String gov_num = magnetic_device_log.getGov_num();
        String car_dev_num = magnetic_device_log.getCar_dev_num();
        String android_dev_num = magnetic_device_log.getAndroid_dev_num();
        int state = magnetic_device_log.getState();
        java.util.Date ctime = magnetic_device_log.getCtime();
        String note = magnetic_device_log.getNote();
        int carport_total = magnetic_device_log.getCarport_total();
        int carport_yet = magnetic_device_log.getCarport_yet();
        int carport_space = magnetic_device_log.getCarport_space();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Magnetic_device_log magnetic_device_log){
        long id = magnetic_device_log.getId();
        long pi_id = magnetic_device_log.getPi_id();
        String area_code = magnetic_device_log.getArea_code();
        String gov_num = magnetic_device_log.getGov_num();
        String car_dev_num = magnetic_device_log.getCar_dev_num();
        String android_dev_num = magnetic_device_log.getAndroid_dev_num();
        int state = magnetic_device_log.getState();
        java.util.Date ctime = magnetic_device_log.getCtime();
        String note = magnetic_device_log.getNote();
        int carport_total = magnetic_device_log.getCarport_total();
        int carport_yet = magnetic_device_log.getCarport_yet();
        int carport_space = magnetic_device_log.getCarport_space();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("gov_num",gov_num);
        _ret.put("car_dev_num",car_dev_num);
        _ret.put("android_dev_num",android_dev_num);
        _ret.put("state",state);
        _ret.put("ctime",ctime);
        _ret.put("note",note);
        _ret.put("carport_total",carport_total);
        _ret.put("carport_yet",carport_yet);
        _ret.put("carport_space",carport_space);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Magnetic_device_log clone2(){
        try{
            return (Magnetic_device_log) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
