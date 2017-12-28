package com.park.bean;

import java.io.*;
import java.util.*;

//wubo_hardware
@SuppressWarnings({"serial"})
public class Wubo_hardware implements Cloneable , Serializable{

    //public static String[] carrays ={"wh_id","type","mac","imei","plant_type","state","ctime","utime","loginname","name","note","batch_date","channel_id","version_code"};

    public long wh_id;//bigint(20)    主键ID
    public int type;//int(11)    硬件产品类型(0：未知1：地磁小车位设备2：地磁android板收集器)
    public String mac="";//varchar(12)    硬件MAC地址（12位）
    public String imei="";//varchar(20)    硬件串号(目前暂不使用20位8位日期+12位随机数字)
    public int plant_type;//int(11)    工厂状态（0：分配成功1：进入生产2：成品入库3：外放投入使用）
    public int state;//int(11)    硬件状态(0:正常1：故障)
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String loginname="";//varchar(30)    操作人员帐号
    public String name="";//varchar(30)    操作人姓名
    public String note="";//varchar(255)    备注
    public String batch_date="";//varchar(30)    生产批次（例如：20170804）
    public long channel_id;//bigint(20)    逻辑渠道表主键ID(外键)
    public int version_code;//int(11)    当前内部升级版本号



    public long getWh_id(){
        return wh_id;
    }

    public void setWh_id(long value){
        this.wh_id= value;
    }

    public int getType(){
        return type;
    }

    public void setType(int value){
        this.type= value;
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

    public String getImei(){
        return imei;
    }

    public void setImei(String value){
    	if(value == null){
           value = "";
        }
        this.imei= value;
    }

    public int getPlant_type(){
        return plant_type;
    }

    public void setPlant_type(int value){
        this.plant_type= value;
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

    public String getLoginname(){
        return loginname;
    }

    public void setLoginname(String value){
    	if(value == null){
           value = "";
        }
        this.loginname= value;
    }

    public String getName(){
        return name;
    }

    public void setName(String value){
    	if(value == null){
           value = "";
        }
        this.name= value;
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

    public String getBatch_date(){
        return batch_date;
    }

    public void setBatch_date(String value){
    	if(value == null){
           value = "";
        }
        this.batch_date= value;
    }

    public long getChannel_id(){
        return channel_id;
    }

    public void setChannel_id(long value){
        this.channel_id= value;
    }

    public int getVersion_code(){
        return version_code;
    }

    public void setVersion_code(int value){
        this.version_code= value;
    }



    public static Wubo_hardware newWubo_hardware(long wh_id, int type, String mac, String imei, int plant_type, int state, java.util.Date ctime, java.util.Date utime, String loginname, String name, String note, String batch_date, long channel_id, int version_code) {
        Wubo_hardware ret = new Wubo_hardware();
        ret.setWh_id(wh_id);
        ret.setType(type);
        ret.setMac(mac);
        ret.setImei(imei);
        ret.setPlant_type(plant_type);
        ret.setState(state);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setLoginname(loginname);
        ret.setName(name);
        ret.setNote(note);
        ret.setBatch_date(batch_date);
        ret.setChannel_id(channel_id);
        ret.setVersion_code(version_code);
        return ret;    
    }

    public void assignment(Wubo_hardware wubo_hardware) {
        long wh_id = wubo_hardware.getWh_id();
        int type = wubo_hardware.getType();
        String mac = wubo_hardware.getMac();
        String imei = wubo_hardware.getImei();
        int plant_type = wubo_hardware.getPlant_type();
        int state = wubo_hardware.getState();
        java.util.Date ctime = wubo_hardware.getCtime();
        java.util.Date utime = wubo_hardware.getUtime();
        String loginname = wubo_hardware.getLoginname();
        String name = wubo_hardware.getName();
        String note = wubo_hardware.getNote();
        String batch_date = wubo_hardware.getBatch_date();
        long channel_id = wubo_hardware.getChannel_id();
        int version_code = wubo_hardware.getVersion_code();

        this.setWh_id(wh_id);
        this.setType(type);
        this.setMac(mac);
        this.setImei(imei);
        this.setPlant_type(plant_type);
        this.setState(state);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setLoginname(loginname);
        this.setName(name);
        this.setNote(note);
        this.setBatch_date(batch_date);
        this.setChannel_id(channel_id);
        this.setVersion_code(version_code);

    }

    @SuppressWarnings("unused")
    public static void getWubo_hardware(Wubo_hardware wubo_hardware ){
        long wh_id = wubo_hardware.getWh_id();
        int type = wubo_hardware.getType();
        String mac = wubo_hardware.getMac();
        String imei = wubo_hardware.getImei();
        int plant_type = wubo_hardware.getPlant_type();
        int state = wubo_hardware.getState();
        java.util.Date ctime = wubo_hardware.getCtime();
        java.util.Date utime = wubo_hardware.getUtime();
        String loginname = wubo_hardware.getLoginname();
        String name = wubo_hardware.getName();
        String note = wubo_hardware.getNote();
        String batch_date = wubo_hardware.getBatch_date();
        long channel_id = wubo_hardware.getChannel_id();
        int version_code = wubo_hardware.getVersion_code();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Wubo_hardware wubo_hardware){
        long wh_id = wubo_hardware.getWh_id();
        int type = wubo_hardware.getType();
        String mac = wubo_hardware.getMac();
        String imei = wubo_hardware.getImei();
        int plant_type = wubo_hardware.getPlant_type();
        int state = wubo_hardware.getState();
        java.util.Date ctime = wubo_hardware.getCtime();
        java.util.Date utime = wubo_hardware.getUtime();
        String loginname = wubo_hardware.getLoginname();
        String name = wubo_hardware.getName();
        String note = wubo_hardware.getNote();
        String batch_date = wubo_hardware.getBatch_date();
        long channel_id = wubo_hardware.getChannel_id();
        int version_code = wubo_hardware.getVersion_code();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("wh_id",wh_id);
        _ret.put("type",type);
        _ret.put("mac",mac);
        _ret.put("imei",imei);
        _ret.put("plant_type",plant_type);
        _ret.put("state",state);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("loginname",loginname);
        _ret.put("name",name);
        _ret.put("note",note);
        _ret.put("batch_date",batch_date);
        _ret.put("channel_id",channel_id);
        _ret.put("version_code",version_code);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Wubo_hardware clone2(){
        try{
            return (Wubo_hardware) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
