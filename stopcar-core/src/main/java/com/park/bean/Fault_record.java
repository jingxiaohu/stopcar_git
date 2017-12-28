package com.park.bean;

import java.io.*;
import java.util.*;

//fault_record
@SuppressWarnings({"serial"})
public class Fault_record implements Cloneable , Serializable{

    //public static String[] carrays ={"fr_id","pi_id","pd_id","fr_type","fr_desc","ctime","utime","area_code","note"};

    public long fr_id;//bigint(20)    主键ID
    public long pi_id;//bigint(20)    场地主键ID
    public long pd_id;//bigint(20)    出入口设备主键ID
    public int fr_type;//int(11)    故障类型0:摄像头故障1：开闸设备故障
    public String fr_desc="";//varchar(255)    故障简述
    public java.util.Date ctime=new java.util.Date();//datetime    
    public java.util.Date utime=new java.util.Date();//datetime    
    public String area_code="";//varchar(20)    省市区编号
    public String note="";//varchar(100)    



    public long getFr_id(){
        return fr_id;
    }

    public void setFr_id(long value){
        this.fr_id= value;
    }

    public long getPi_id(){
        return pi_id;
    }

    public void setPi_id(long value){
        this.pi_id= value;
    }

    public long getPd_id(){
        return pd_id;
    }

    public void setPd_id(long value){
        this.pd_id= value;
    }

    public int getFr_type(){
        return fr_type;
    }

    public void setFr_type(int value){
        this.fr_type= value;
    }

    public String getFr_desc(){
        return fr_desc;
    }

    public void setFr_desc(String value){
    	if(value == null){
           value = "";
        }
        this.fr_desc= value;
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

    public String getArea_code(){
        return area_code;
    }

    public void setArea_code(String value){
    	if(value == null){
           value = "";
        }
        this.area_code= value;
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



    public static Fault_record newFault_record(long fr_id, long pi_id, long pd_id, int fr_type, String fr_desc, java.util.Date ctime, java.util.Date utime, String area_code, String note) {
        Fault_record ret = new Fault_record();
        ret.setFr_id(fr_id);
        ret.setPi_id(pi_id);
        ret.setPd_id(pd_id);
        ret.setFr_type(fr_type);
        ret.setFr_desc(fr_desc);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setArea_code(area_code);
        ret.setNote(note);
        return ret;    
    }

    public void assignment(Fault_record fault_record) {
        long fr_id = fault_record.getFr_id();
        long pi_id = fault_record.getPi_id();
        long pd_id = fault_record.getPd_id();
        int fr_type = fault_record.getFr_type();
        String fr_desc = fault_record.getFr_desc();
        java.util.Date ctime = fault_record.getCtime();
        java.util.Date utime = fault_record.getUtime();
        String area_code = fault_record.getArea_code();
        String note = fault_record.getNote();

        this.setFr_id(fr_id);
        this.setPi_id(pi_id);
        this.setPd_id(pd_id);
        this.setFr_type(fr_type);
        this.setFr_desc(fr_desc);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setArea_code(area_code);
        this.setNote(note);

    }

    @SuppressWarnings("unused")
    public static void getFault_record(Fault_record fault_record ){
        long fr_id = fault_record.getFr_id();
        long pi_id = fault_record.getPi_id();
        long pd_id = fault_record.getPd_id();
        int fr_type = fault_record.getFr_type();
        String fr_desc = fault_record.getFr_desc();
        java.util.Date ctime = fault_record.getCtime();
        java.util.Date utime = fault_record.getUtime();
        String area_code = fault_record.getArea_code();
        String note = fault_record.getNote();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Fault_record fault_record){
        long fr_id = fault_record.getFr_id();
        long pi_id = fault_record.getPi_id();
        long pd_id = fault_record.getPd_id();
        int fr_type = fault_record.getFr_type();
        String fr_desc = fault_record.getFr_desc();
        java.util.Date ctime = fault_record.getCtime();
        java.util.Date utime = fault_record.getUtime();
        String area_code = fault_record.getArea_code();
        String note = fault_record.getNote();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("fr_id",fr_id);
        _ret.put("pi_id",pi_id);
        _ret.put("pd_id",pd_id);
        _ret.put("fr_type",fr_type);
        _ret.put("fr_desc",fr_desc);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("area_code",area_code);
        _ret.put("note",note);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Fault_record clone2(){
        try{
            return (Fault_record) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
