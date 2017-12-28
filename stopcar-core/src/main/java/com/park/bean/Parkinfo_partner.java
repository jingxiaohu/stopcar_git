package com.park.bean;

import java.io.*;
import java.util.*;

//parkinfo_partner
@SuppressWarnings({"serial"})
public class Parkinfo_partner implements Cloneable , Serializable{

    //public static String[] carrays ={"id","pi_id","pu_id","pu_nd","ctime","utime","is_effect","area_code","note","clearing_type","clearing_percentage","clearing_base"};

    public long id;//bigint(20)    
    public long pi_id;//bigint(20)    停车场主键IDp_id
    public long pu_id;//bigint(20)    商户基本信息表主键IDpu_id
    public String pu_nd="";//varchar(100)    商户编号
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public int is_effect;//int(11)    是否启用（0：启用1：弃用）
    public String area_code="";//varchar(60)    地区区域编码
    public String note="";//varchar(255)    备注
    public int clearing_type;//int(11)    结算方案(0：不结算1：全额结算2：按基数结算)
    public double clearing_percentage;//double    结算比例
    public int clearing_base;//int(11)    结算基数(整数单位分)



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

    public int getIs_effect(){
        return is_effect;
    }

    public void setIs_effect(int value){
        this.is_effect= value;
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

    public int getClearing_type(){
        return clearing_type;
    }

    public void setClearing_type(int value){
        this.clearing_type= value;
    }

    public double getClearing_percentage(){
        return clearing_percentage;
    }

    public void setClearing_percentage(double value){
        this.clearing_percentage= value;
    }

    public int getClearing_base(){
        return clearing_base;
    }

    public void setClearing_base(int value){
        this.clearing_base= value;
    }



    public static Parkinfo_partner newParkinfo_partner(long id, long pi_id, long pu_id, String pu_nd, java.util.Date ctime, java.util.Date utime, int is_effect, String area_code, String note, int clearing_type, double clearing_percentage, int clearing_base) {
        Parkinfo_partner ret = new Parkinfo_partner();
        ret.setId(id);
        ret.setPi_id(pi_id);
        ret.setPu_id(pu_id);
        ret.setPu_nd(pu_nd);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setIs_effect(is_effect);
        ret.setArea_code(area_code);
        ret.setNote(note);
        ret.setClearing_type(clearing_type);
        ret.setClearing_percentage(clearing_percentage);
        ret.setClearing_base(clearing_base);
        return ret;    
    }

    public void assignment(Parkinfo_partner parkinfo_partner) {
        long id = parkinfo_partner.getId();
        long pi_id = parkinfo_partner.getPi_id();
        long pu_id = parkinfo_partner.getPu_id();
        String pu_nd = parkinfo_partner.getPu_nd();
        java.util.Date ctime = parkinfo_partner.getCtime();
        java.util.Date utime = parkinfo_partner.getUtime();
        int is_effect = parkinfo_partner.getIs_effect();
        String area_code = parkinfo_partner.getArea_code();
        String note = parkinfo_partner.getNote();
        int clearing_type = parkinfo_partner.getClearing_type();
        double clearing_percentage = parkinfo_partner.getClearing_percentage();
        int clearing_base = parkinfo_partner.getClearing_base();

        this.setId(id);
        this.setPi_id(pi_id);
        this.setPu_id(pu_id);
        this.setPu_nd(pu_nd);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setIs_effect(is_effect);
        this.setArea_code(area_code);
        this.setNote(note);
        this.setClearing_type(clearing_type);
        this.setClearing_percentage(clearing_percentage);
        this.setClearing_base(clearing_base);

    }

    @SuppressWarnings("unused")
    public static void getParkinfo_partner(Parkinfo_partner parkinfo_partner ){
        long id = parkinfo_partner.getId();
        long pi_id = parkinfo_partner.getPi_id();
        long pu_id = parkinfo_partner.getPu_id();
        String pu_nd = parkinfo_partner.getPu_nd();
        java.util.Date ctime = parkinfo_partner.getCtime();
        java.util.Date utime = parkinfo_partner.getUtime();
        int is_effect = parkinfo_partner.getIs_effect();
        String area_code = parkinfo_partner.getArea_code();
        String note = parkinfo_partner.getNote();
        int clearing_type = parkinfo_partner.getClearing_type();
        double clearing_percentage = parkinfo_partner.getClearing_percentage();
        int clearing_base = parkinfo_partner.getClearing_base();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Parkinfo_partner parkinfo_partner){
        long id = parkinfo_partner.getId();
        long pi_id = parkinfo_partner.getPi_id();
        long pu_id = parkinfo_partner.getPu_id();
        String pu_nd = parkinfo_partner.getPu_nd();
        java.util.Date ctime = parkinfo_partner.getCtime();
        java.util.Date utime = parkinfo_partner.getUtime();
        int is_effect = parkinfo_partner.getIs_effect();
        String area_code = parkinfo_partner.getArea_code();
        String note = parkinfo_partner.getNote();
        int clearing_type = parkinfo_partner.getClearing_type();
        double clearing_percentage = parkinfo_partner.getClearing_percentage();
        int clearing_base = parkinfo_partner.getClearing_base();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("pi_id",pi_id);
        _ret.put("pu_id",pu_id);
        _ret.put("pu_nd",pu_nd);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("is_effect",is_effect);
        _ret.put("area_code",area_code);
        _ret.put("note",note);
        _ret.put("clearing_type",clearing_type);
        _ret.put("clearing_percentage",clearing_percentage);
        _ret.put("clearing_base",clearing_base);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Parkinfo_partner clone2(){
        try{
            return (Parkinfo_partner) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
