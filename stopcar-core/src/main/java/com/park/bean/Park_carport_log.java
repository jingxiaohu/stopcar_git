package com.park.bean;

import java.io.*;
import java.util.*;

//park_carport_log
@SuppressWarnings({"serial"})
public class Park_carport_log implements Cloneable , Serializable{

    //public static String[] carrays ={"pcl_id","pi_id","area_code","carport_total","carport_yet","carport_space","park_type","data_flag","ctime","stime","note"};

    public long pcl_id;//bigint(20)    主键ID
    public long pi_id;//bigint(20)    停车场主键ID
    public String area_code="";//varchar(30)    停车场地址区域编码
    public int carport_total;//int(11)    车位总数
    public int carport_yet;//int(11)    已停车位数
    public int carport_space;//int(11)    空余车位数
    public int park_type;//int(11)    停车场类型(停车场类型0：道闸停车场1：占道车场2：露天立体车库停车场)
    public int data_flag;//int(11)    上传来源1：普通占道停车场2：地磁占道停车场3：道闸停车场
    public java.util.Date ctime=new java.util.Date();//datetime    停车场本地时间
    public java.util.Date stime=new java.util.Date();//datetime    接收数据时间
    public String note="";//varchar(100)    备注



    public long getPcl_id(){
        return pcl_id;
    }

    public void setPcl_id(long value){
        this.pcl_id= value;
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

    public int getPark_type(){
        return park_type;
    }

    public void setPark_type(int value){
        this.park_type= value;
    }

    public int getData_flag(){
        return data_flag;
    }

    public void setData_flag(int value){
        this.data_flag= value;
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

    public java.util.Date getStime(){
        return stime;
    }

    public void setStime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.stime= value;
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



    public static Park_carport_log newPark_carport_log(long pcl_id, long pi_id, String area_code, int carport_total, int carport_yet, int carport_space, int park_type, int data_flag, java.util.Date ctime, java.util.Date stime, String note) {
        Park_carport_log ret = new Park_carport_log();
        ret.setPcl_id(pcl_id);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setCarport_total(carport_total);
        ret.setCarport_yet(carport_yet);
        ret.setCarport_space(carport_space);
        ret.setPark_type(park_type);
        ret.setData_flag(data_flag);
        ret.setCtime(ctime);
        ret.setStime(stime);
        ret.setNote(note);
        return ret;    
    }

    public void assignment(Park_carport_log park_carport_log) {
        long pcl_id = park_carport_log.getPcl_id();
        long pi_id = park_carport_log.getPi_id();
        String area_code = park_carport_log.getArea_code();
        int carport_total = park_carport_log.getCarport_total();
        int carport_yet = park_carport_log.getCarport_yet();
        int carport_space = park_carport_log.getCarport_space();
        int park_type = park_carport_log.getPark_type();
        int data_flag = park_carport_log.getData_flag();
        java.util.Date ctime = park_carport_log.getCtime();
        java.util.Date stime = park_carport_log.getStime();
        String note = park_carport_log.getNote();

        this.setPcl_id(pcl_id);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setCarport_total(carport_total);
        this.setCarport_yet(carport_yet);
        this.setCarport_space(carport_space);
        this.setPark_type(park_type);
        this.setData_flag(data_flag);
        this.setCtime(ctime);
        this.setStime(stime);
        this.setNote(note);

    }

    @SuppressWarnings("unused")
    public static void getPark_carport_log(Park_carport_log park_carport_log ){
        long pcl_id = park_carport_log.getPcl_id();
        long pi_id = park_carport_log.getPi_id();
        String area_code = park_carport_log.getArea_code();
        int carport_total = park_carport_log.getCarport_total();
        int carport_yet = park_carport_log.getCarport_yet();
        int carport_space = park_carport_log.getCarport_space();
        int park_type = park_carport_log.getPark_type();
        int data_flag = park_carport_log.getData_flag();
        java.util.Date ctime = park_carport_log.getCtime();
        java.util.Date stime = park_carport_log.getStime();
        String note = park_carport_log.getNote();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Park_carport_log park_carport_log){
        long pcl_id = park_carport_log.getPcl_id();
        long pi_id = park_carport_log.getPi_id();
        String area_code = park_carport_log.getArea_code();
        int carport_total = park_carport_log.getCarport_total();
        int carport_yet = park_carport_log.getCarport_yet();
        int carport_space = park_carport_log.getCarport_space();
        int park_type = park_carport_log.getPark_type();
        int data_flag = park_carport_log.getData_flag();
        java.util.Date ctime = park_carport_log.getCtime();
        java.util.Date stime = park_carport_log.getStime();
        String note = park_carport_log.getNote();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("pcl_id",pcl_id);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("carport_total",carport_total);
        _ret.put("carport_yet",carport_yet);
        _ret.put("carport_space",carport_space);
        _ret.put("park_type",park_type);
        _ret.put("data_flag",data_flag);
        _ret.put("ctime",ctime);
        _ret.put("stime",stime);
        _ret.put("note",note);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Park_carport_log clone2(){
        try{
            return (Park_carport_log) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
