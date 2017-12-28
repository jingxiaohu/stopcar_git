package com.park.bean;

import java.io.*;
import java.util.*;

//park_coupon
@SuppressWarnings({"serial"})
public class Park_coupon implements Cloneable , Serializable{

    //public static String[] carrays ={"id","name","money","discount","high_money","end_time","use_range","pc_type","ctime","utime","note","ai_id"};

    public long id;//bigint(20)    主键ID
    public String name="";//varchar(100)    
    public int money;//int(11)    优惠券金额(单位分)
    public double discount;//double(2,2)    折扣券折数
    public int high_money;//int(11)    最高抵扣金额
    public java.util.Date end_time=new java.util.Date();//datetime    有效期到期时间
    public int use_range;//int(11)    使用范围0：爱泊车场通用1：其它车场使用
    public int pc_type;//int(11)    优惠券类型0:金额券1：折扣券
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String note="";//varchar(100)    备注
    public long ai_id;//bigint(20)    活动基本信息表主键ID（外键）



    public long getId(){
        return id;
    }

    public void setId(long value){
        this.id= value;
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

    public int getMoney(){
        return money;
    }

    public void setMoney(int value){
        this.money= value;
    }

    public double getDiscount(){
        return discount;
    }

    public void setDiscount(double value){
        this.discount= value;
    }

    public int getHigh_money(){
        return high_money;
    }

    public void setHigh_money(int value){
        this.high_money= value;
    }

    public java.util.Date getEnd_time(){
        return end_time;
    }

    public void setEnd_time(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.end_time= value;
    }

    public int getUse_range(){
        return use_range;
    }

    public void setUse_range(int value){
        this.use_range= value;
    }

    public int getPc_type(){
        return pc_type;
    }

    public void setPc_type(int value){
        this.pc_type= value;
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

    public long getAi_id(){
        return ai_id;
    }

    public void setAi_id(long value){
        this.ai_id= value;
    }



    public static Park_coupon newPark_coupon(long id, String name, int money, double discount, int high_money, java.util.Date end_time, int use_range, int pc_type, java.util.Date ctime, java.util.Date utime, String note, long ai_id) {
        Park_coupon ret = new Park_coupon();
        ret.setId(id);
        ret.setName(name);
        ret.setMoney(money);
        ret.setDiscount(discount);
        ret.setHigh_money(high_money);
        ret.setEnd_time(end_time);
        ret.setUse_range(use_range);
        ret.setPc_type(pc_type);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setNote(note);
        ret.setAi_id(ai_id);
        return ret;    
    }

    public void assignment(Park_coupon park_coupon) {
        long id = park_coupon.getId();
        String name = park_coupon.getName();
        int money = park_coupon.getMoney();
        double discount = park_coupon.getDiscount();
        int high_money = park_coupon.getHigh_money();
        java.util.Date end_time = park_coupon.getEnd_time();
        int use_range = park_coupon.getUse_range();
        int pc_type = park_coupon.getPc_type();
        java.util.Date ctime = park_coupon.getCtime();
        java.util.Date utime = park_coupon.getUtime();
        String note = park_coupon.getNote();
        long ai_id = park_coupon.getAi_id();

        this.setId(id);
        this.setName(name);
        this.setMoney(money);
        this.setDiscount(discount);
        this.setHigh_money(high_money);
        this.setEnd_time(end_time);
        this.setUse_range(use_range);
        this.setPc_type(pc_type);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setNote(note);
        this.setAi_id(ai_id);

    }

    @SuppressWarnings("unused")
    public static void getPark_coupon(Park_coupon park_coupon ){
        long id = park_coupon.getId();
        String name = park_coupon.getName();
        int money = park_coupon.getMoney();
        double discount = park_coupon.getDiscount();
        int high_money = park_coupon.getHigh_money();
        java.util.Date end_time = park_coupon.getEnd_time();
        int use_range = park_coupon.getUse_range();
        int pc_type = park_coupon.getPc_type();
        java.util.Date ctime = park_coupon.getCtime();
        java.util.Date utime = park_coupon.getUtime();
        String note = park_coupon.getNote();
        long ai_id = park_coupon.getAi_id();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Park_coupon park_coupon){
        long id = park_coupon.getId();
        String name = park_coupon.getName();
        int money = park_coupon.getMoney();
        double discount = park_coupon.getDiscount();
        int high_money = park_coupon.getHigh_money();
        java.util.Date end_time = park_coupon.getEnd_time();
        int use_range = park_coupon.getUse_range();
        int pc_type = park_coupon.getPc_type();
        java.util.Date ctime = park_coupon.getCtime();
        java.util.Date utime = park_coupon.getUtime();
        String note = park_coupon.getNote();
        long ai_id = park_coupon.getAi_id();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("name",name);
        _ret.put("money",money);
        _ret.put("discount",discount);
        _ret.put("high_money",high_money);
        _ret.put("end_time",end_time);
        _ret.put("use_range",use_range);
        _ret.put("pc_type",pc_type);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("note",note);
        _ret.put("ai_id",ai_id);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Park_coupon clone2(){
        try{
            return (Park_coupon) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
