package com.park.bean;

import java.io.*;
import java.util.*;

//pda_info
@SuppressWarnings({"serial"})
public class Pda_info implements Cloneable , Serializable{

    //public static String[] carrays ={"pda_id","mac","pda_sn","plate_license","loginname","password","pi_id","area_code","pu_id","pu_nd","ctime","utime","note","is_initialize","lng","lat","pi_name","pda_c_id","vnum","token"};

    public long pda_id;//bigint(20)    主键ID
    public String mac="";//varchar(100)    PDA设备MAC
    public String pda_sn="";//varchar(100)    PDA设备串号
    public String plate_license="";//varchar(150)    惠通扫描授权串号(序列号)
    public String loginname="";//varchar(60)    PDA登录帐号
    public String password="";//varchar(100)    PDA登录密码
    public long pi_id;//bigint(20)    PDA关联的停车场ID
    public String area_code="";//varchar(60)    PDA关联的停车场地址编码
    public long pu_id;//bigint(20)    PDA关联的商户ID
    public String pu_nd="";//varchar(80)    PDA关联的商户的ND
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String note="";//varchar(100)    备注
    public int is_initialize;//int(11)    是否初始化成功0:没有初始化1：初始化成功
    public double lng;//double    初始化经度
    public double lat;//double    初始化纬度
    public String pi_name="";//varchar(100)    停车场名称
    public long pda_c_id;//bigint(20)    逻辑渠道表主键ID（外键）
    public int vnum;//int(11)    当前内部版本号
    public String token="";//varchar(80)    授权token



    public long getPda_id(){
        return pda_id;
    }

    public void setPda_id(long value){
        this.pda_id= value;
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

    public String getPlate_license(){
        return plate_license;
    }

    public void setPlate_license(String value){
    	if(value == null){
           value = "";
        }
        this.plate_license= value;
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

    public String getPassword(){
        return password;
    }

    public void setPassword(String value){
    	if(value == null){
           value = "";
        }
        this.password= value;
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

    public long getPu_id(){
        return pu_id;
    }

    public void setPu_id(long value){
        this.pu_id= value;
    }

    public String getPu_nd(){
        return pu_nd;
    }

    public void setPu_nd(String value){
    	if(value == null){
           value = "";
        }
        this.pu_nd= value;
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

    public int getIs_initialize(){
        return is_initialize;
    }

    public void setIs_initialize(int value){
        this.is_initialize= value;
    }

    public double getLng(){
        return lng;
    }

    public void setLng(double value){
        this.lng= value;
    }

    public double getLat(){
        return lat;
    }

    public void setLat(double value){
        this.lat= value;
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

    public long getPda_c_id(){
        return pda_c_id;
    }

    public void setPda_c_id(long value){
        this.pda_c_id= value;
    }

    public int getVnum(){
        return vnum;
    }

    public void setVnum(int value){
        this.vnum= value;
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



    public static Pda_info newPda_info(long pda_id, String mac, String pda_sn, String plate_license, String loginname, String password, long pi_id, String area_code, long pu_id, String pu_nd, java.util.Date ctime, java.util.Date utime, String note, int is_initialize, double lng, double lat, String pi_name, long pda_c_id, int vnum, String token) {
        Pda_info ret = new Pda_info();
        ret.setPda_id(pda_id);
        ret.setMac(mac);
        ret.setPda_sn(pda_sn);
        ret.setPlate_license(plate_license);
        ret.setLoginname(loginname);
        ret.setPassword(password);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setPu_id(pu_id);
        ret.setPu_nd(pu_nd);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setNote(note);
        ret.setIs_initialize(is_initialize);
        ret.setLng(lng);
        ret.setLat(lat);
        ret.setPi_name(pi_name);
        ret.setPda_c_id(pda_c_id);
        ret.setVnum(vnum);
        ret.setToken(token);
        return ret;    
    }

    public void assignment(Pda_info pda_info) {
        long pda_id = pda_info.getPda_id();
        String mac = pda_info.getMac();
        String pda_sn = pda_info.getPda_sn();
        String plate_license = pda_info.getPlate_license();
        String loginname = pda_info.getLoginname();
        String password = pda_info.getPassword();
        long pi_id = pda_info.getPi_id();
        String area_code = pda_info.getArea_code();
        long pu_id = pda_info.getPu_id();
        String pu_nd = pda_info.getPu_nd();
        java.util.Date ctime = pda_info.getCtime();
        java.util.Date utime = pda_info.getUtime();
        String note = pda_info.getNote();
        int is_initialize = pda_info.getIs_initialize();
        double lng = pda_info.getLng();
        double lat = pda_info.getLat();
        String pi_name = pda_info.getPi_name();
        long pda_c_id = pda_info.getPda_c_id();
        int vnum = pda_info.getVnum();
        String token = pda_info.getToken();

        this.setPda_id(pda_id);
        this.setMac(mac);
        this.setPda_sn(pda_sn);
        this.setPlate_license(plate_license);
        this.setLoginname(loginname);
        this.setPassword(password);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setPu_id(pu_id);
        this.setPu_nd(pu_nd);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setNote(note);
        this.setIs_initialize(is_initialize);
        this.setLng(lng);
        this.setLat(lat);
        this.setPi_name(pi_name);
        this.setPda_c_id(pda_c_id);
        this.setVnum(vnum);
        this.setToken(token);

    }

    @SuppressWarnings("unused")
    public static void getPda_info(Pda_info pda_info ){
        long pda_id = pda_info.getPda_id();
        String mac = pda_info.getMac();
        String pda_sn = pda_info.getPda_sn();
        String plate_license = pda_info.getPlate_license();
        String loginname = pda_info.getLoginname();
        String password = pda_info.getPassword();
        long pi_id = pda_info.getPi_id();
        String area_code = pda_info.getArea_code();
        long pu_id = pda_info.getPu_id();
        String pu_nd = pda_info.getPu_nd();
        java.util.Date ctime = pda_info.getCtime();
        java.util.Date utime = pda_info.getUtime();
        String note = pda_info.getNote();
        int is_initialize = pda_info.getIs_initialize();
        double lng = pda_info.getLng();
        double lat = pda_info.getLat();
        String pi_name = pda_info.getPi_name();
        long pda_c_id = pda_info.getPda_c_id();
        int vnum = pda_info.getVnum();
        String token = pda_info.getToken();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Pda_info pda_info){
        long pda_id = pda_info.getPda_id();
        String mac = pda_info.getMac();
        String pda_sn = pda_info.getPda_sn();
        String plate_license = pda_info.getPlate_license();
        String loginname = pda_info.getLoginname();
        String password = pda_info.getPassword();
        long pi_id = pda_info.getPi_id();
        String area_code = pda_info.getArea_code();
        long pu_id = pda_info.getPu_id();
        String pu_nd = pda_info.getPu_nd();
        java.util.Date ctime = pda_info.getCtime();
        java.util.Date utime = pda_info.getUtime();
        String note = pda_info.getNote();
        int is_initialize = pda_info.getIs_initialize();
        double lng = pda_info.getLng();
        double lat = pda_info.getLat();
        String pi_name = pda_info.getPi_name();
        long pda_c_id = pda_info.getPda_c_id();
        int vnum = pda_info.getVnum();
        String token = pda_info.getToken();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("pda_id",pda_id);
        _ret.put("mac",mac);
        _ret.put("pda_sn",pda_sn);
        _ret.put("plate_license",plate_license);
        _ret.put("loginname",loginname);
        _ret.put("password",password);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("pu_id",pu_id);
        _ret.put("pu_nd",pu_nd);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("note",note);
        _ret.put("is_initialize",is_initialize);
        _ret.put("lng",lng);
        _ret.put("lat",lat);
        _ret.put("pi_name",pi_name);
        _ret.put("pda_c_id",pda_c_id);
        _ret.put("vnum",vnum);
        _ret.put("token",token);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Pda_info clone2(){
        try{
            return (Pda_info) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
