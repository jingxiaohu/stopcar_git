package com.park.bean;

import java.io.*;
import java.util.*;

//user_park_coupon
@SuppressWarnings({"serial"})
public class User_park_coupon implements Cloneable , Serializable{

    //public static String[] carrays ={"upc_id","ui_id","pc_id","money","discount","high_money","upc_type","end_time","upc_state","ctime","utime","note","send_unit","ai_id","state"};

    public long upc_id;//bigint(20)    主键ID
    public long ui_id;//bigint(20)    主键ID
    public long pc_id;//bigint(20)    停车优惠券表主键ID
    public int money;//int(11)    优惠券金额
    public double discount;//double(2,2)    折扣券折数
    public int high_money;//int(11)    最高抵扣金额
    public int upc_type;//int(11)    优惠券类型0:金额券1：折扣券
    public java.util.Date end_time=new java.util.Date();//datetime    有效期
    public int upc_state;//int(11)    使用状态0：未使用1：已使用
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String note="";//varchar(100)    备注
    public int send_unit;//int(11)    赠送单位(0:吾泊平台1：龙支付)
    public long ai_id;//bigint(20)    活动ID（活动表主键ID）
    public int state;//int(11)    是否进行过过期前提醒推送（0：没有推送1：已经推送过了）



    public long getUpc_id(){
        return upc_id;
    }

    public void setUpc_id(long value){
        this.upc_id= value;
    }

    public long getUi_id(){
        return ui_id;
    }

    public void setUi_id(long value){
        this.ui_id= value;
    }

    public long getPc_id(){
        return pc_id;
    }

    public void setPc_id(long value){
        this.pc_id= value;
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

    public int getUpc_type(){
        return upc_type;
    }

    public void setUpc_type(int value){
        this.upc_type= value;
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

    public int getUpc_state(){
        return upc_state;
    }

    public void setUpc_state(int value){
        this.upc_state= value;
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

    public int getSend_unit(){
        return send_unit;
    }

    public void setSend_unit(int value){
        this.send_unit= value;
    }

    public long getAi_id(){
        return ai_id;
    }

    public void setAi_id(long value){
        this.ai_id= value;
    }

    public int getState(){
        return state;
    }

    public void setState(int value){
        this.state= value;
    }



    public static User_park_coupon newUser_park_coupon(long upc_id, long ui_id, long pc_id, int money, double discount, int high_money, int upc_type, java.util.Date end_time, int upc_state, java.util.Date ctime, java.util.Date utime, String note, int send_unit, long ai_id, int state) {
        User_park_coupon ret = new User_park_coupon();
        ret.setUpc_id(upc_id);
        ret.setUi_id(ui_id);
        ret.setPc_id(pc_id);
        ret.setMoney(money);
        ret.setDiscount(discount);
        ret.setHigh_money(high_money);
        ret.setUpc_type(upc_type);
        ret.setEnd_time(end_time);
        ret.setUpc_state(upc_state);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setNote(note);
        ret.setSend_unit(send_unit);
        ret.setAi_id(ai_id);
        ret.setState(state);
        return ret;    
    }

    public void assignment(User_park_coupon user_park_coupon) {
        long upc_id = user_park_coupon.getUpc_id();
        long ui_id = user_park_coupon.getUi_id();
        long pc_id = user_park_coupon.getPc_id();
        int money = user_park_coupon.getMoney();
        double discount = user_park_coupon.getDiscount();
        int high_money = user_park_coupon.getHigh_money();
        int upc_type = user_park_coupon.getUpc_type();
        java.util.Date end_time = user_park_coupon.getEnd_time();
        int upc_state = user_park_coupon.getUpc_state();
        java.util.Date ctime = user_park_coupon.getCtime();
        java.util.Date utime = user_park_coupon.getUtime();
        String note = user_park_coupon.getNote();
        int send_unit = user_park_coupon.getSend_unit();
        long ai_id = user_park_coupon.getAi_id();
        int state = user_park_coupon.getState();

        this.setUpc_id(upc_id);
        this.setUi_id(ui_id);
        this.setPc_id(pc_id);
        this.setMoney(money);
        this.setDiscount(discount);
        this.setHigh_money(high_money);
        this.setUpc_type(upc_type);
        this.setEnd_time(end_time);
        this.setUpc_state(upc_state);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setNote(note);
        this.setSend_unit(send_unit);
        this.setAi_id(ai_id);
        this.setState(state);

    }

    @SuppressWarnings("unused")
    public static void getUser_park_coupon(User_park_coupon user_park_coupon ){
        long upc_id = user_park_coupon.getUpc_id();
        long ui_id = user_park_coupon.getUi_id();
        long pc_id = user_park_coupon.getPc_id();
        int money = user_park_coupon.getMoney();
        double discount = user_park_coupon.getDiscount();
        int high_money = user_park_coupon.getHigh_money();
        int upc_type = user_park_coupon.getUpc_type();
        java.util.Date end_time = user_park_coupon.getEnd_time();
        int upc_state = user_park_coupon.getUpc_state();
        java.util.Date ctime = user_park_coupon.getCtime();
        java.util.Date utime = user_park_coupon.getUtime();
        String note = user_park_coupon.getNote();
        int send_unit = user_park_coupon.getSend_unit();
        long ai_id = user_park_coupon.getAi_id();
        int state = user_park_coupon.getState();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(User_park_coupon user_park_coupon){
        long upc_id = user_park_coupon.getUpc_id();
        long ui_id = user_park_coupon.getUi_id();
        long pc_id = user_park_coupon.getPc_id();
        int money = user_park_coupon.getMoney();
        double discount = user_park_coupon.getDiscount();
        int high_money = user_park_coupon.getHigh_money();
        int upc_type = user_park_coupon.getUpc_type();
        java.util.Date end_time = user_park_coupon.getEnd_time();
        int upc_state = user_park_coupon.getUpc_state();
        java.util.Date ctime = user_park_coupon.getCtime();
        java.util.Date utime = user_park_coupon.getUtime();
        String note = user_park_coupon.getNote();
        int send_unit = user_park_coupon.getSend_unit();
        long ai_id = user_park_coupon.getAi_id();
        int state = user_park_coupon.getState();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("upc_id",upc_id);
        _ret.put("ui_id",ui_id);
        _ret.put("pc_id",pc_id);
        _ret.put("money",money);
        _ret.put("discount",discount);
        _ret.put("high_money",high_money);
        _ret.put("upc_type",upc_type);
        _ret.put("end_time",end_time);
        _ret.put("upc_state",upc_state);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("note",note);
        _ret.put("send_unit",send_unit);
        _ret.put("ai_id",ai_id);
        _ret.put("state",state);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public User_park_coupon clone2(){
        try{
            return (User_park_coupon) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
