package com.park.bean;

import java.io.*;
import java.util.*;

//user_park_coupon_log
@SuppressWarnings({"serial"})
public class User_park_coupon_log implements Cloneable , Serializable{

    //public static String[] carrays ={"upcl_id","upc_id","ui_id","ui_nd","pc_id","money","discount","high_money","upc_type","end_time","upc_state","ctime","utime","send_unit","ai_id","state","note","type","rtime","accept_ui_id","accept_ui_nd"};

    public long upcl_id;//bigint(20)    主键ID
    public long upc_id;//bigint(20)    主键ID
    public long ui_id;//bigint(20)    主键ID
    public String ui_nd="";//varchar(100)    用户唯一标识符
    public long pc_id;//bigint(20)    停车优惠券表主键ID
    public int money;//int(11)    优惠券金额（单位分）
    public double discount;//double(2,2)    折扣券折数
    public int high_money;//int(11)    最高抵扣金额（单位分）
    public int upc_type;//int(11)    优惠券类型0:金额券1：折扣券
    public java.util.Date end_time=new java.util.Date();//datetime    有效期
    public int upc_state;//int(11)    使用状态0：未使用1：已使用
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public int send_unit;//int(11)    赠送单位(0:吾泊平台1：龙支付)
    public long ai_id;//bigint(20)    活动ID（活动表主键ID）
    public int state;//int(11)    是否进行过过期前提醒推送（0：没有推送1：已经推送过了）
    public String note="";//varchar(100)    备注
    public int type;//int(11)    记录类型（0：未指定1：用户停车使用2：用户扫码转赠3：系统赠送）
    public java.util.Date rtime=new java.util.Date();//datetime    变更记录时间(该次变更的入库时间)
    public long accept_ui_id;//bigint(20)    转赠的代金券接受人用户ID
    public String accept_ui_nd="";//varchar(80)    转赠的代金券接受人用户ND



    public long getUpcl_id(){
        return upcl_id;
    }

    public void setUpcl_id(long value){
        this.upcl_id= value;
    }

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

    public String getUi_nd(){
        return ui_nd;
    }

    public void setUi_nd(String value){
    	if(value == null){
           value = "";
        }
        this.ui_nd= value;
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

    public String getNote(){
        return note;
    }

    public void setNote(String value){
    	if(value == null){
           value = "";
        }
        this.note= value;
    }

    public int getType(){
        return type;
    }

    public void setType(int value){
        this.type= value;
    }

    public java.util.Date getRtime(){
        return rtime;
    }

    public void setRtime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.rtime= value;
    }

    public long getAccept_ui_id(){
        return accept_ui_id;
    }

    public void setAccept_ui_id(long value){
        this.accept_ui_id= value;
    }

    public String getAccept_ui_nd(){
        return accept_ui_nd;
    }

    public void setAccept_ui_nd(String value){
    	if(value == null){
           value = "";
        }
        this.accept_ui_nd= value;
    }



    public static User_park_coupon_log newUser_park_coupon_log(long upcl_id, long upc_id, long ui_id, String ui_nd, long pc_id, int money, double discount, int high_money, int upc_type, java.util.Date end_time, int upc_state, java.util.Date ctime, java.util.Date utime, int send_unit, long ai_id, int state, String note, int type, java.util.Date rtime, long accept_ui_id, String accept_ui_nd) {
        User_park_coupon_log ret = new User_park_coupon_log();
        ret.setUpcl_id(upcl_id);
        ret.setUpc_id(upc_id);
        ret.setUi_id(ui_id);
        ret.setUi_nd(ui_nd);
        ret.setPc_id(pc_id);
        ret.setMoney(money);
        ret.setDiscount(discount);
        ret.setHigh_money(high_money);
        ret.setUpc_type(upc_type);
        ret.setEnd_time(end_time);
        ret.setUpc_state(upc_state);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setSend_unit(send_unit);
        ret.setAi_id(ai_id);
        ret.setState(state);
        ret.setNote(note);
        ret.setType(type);
        ret.setRtime(rtime);
        ret.setAccept_ui_id(accept_ui_id);
        ret.setAccept_ui_nd(accept_ui_nd);
        return ret;    
    }

    public void assignment(User_park_coupon_log user_park_coupon_log) {
        long upcl_id = user_park_coupon_log.getUpcl_id();
        long upc_id = user_park_coupon_log.getUpc_id();
        long ui_id = user_park_coupon_log.getUi_id();
        String ui_nd = user_park_coupon_log.getUi_nd();
        long pc_id = user_park_coupon_log.getPc_id();
        int money = user_park_coupon_log.getMoney();
        double discount = user_park_coupon_log.getDiscount();
        int high_money = user_park_coupon_log.getHigh_money();
        int upc_type = user_park_coupon_log.getUpc_type();
        java.util.Date end_time = user_park_coupon_log.getEnd_time();
        int upc_state = user_park_coupon_log.getUpc_state();
        java.util.Date ctime = user_park_coupon_log.getCtime();
        java.util.Date utime = user_park_coupon_log.getUtime();
        int send_unit = user_park_coupon_log.getSend_unit();
        long ai_id = user_park_coupon_log.getAi_id();
        int state = user_park_coupon_log.getState();
        String note = user_park_coupon_log.getNote();
        int type = user_park_coupon_log.getType();
        java.util.Date rtime = user_park_coupon_log.getRtime();
        long accept_ui_id = user_park_coupon_log.getAccept_ui_id();
        String accept_ui_nd = user_park_coupon_log.getAccept_ui_nd();

        this.setUpcl_id(upcl_id);
        this.setUpc_id(upc_id);
        this.setUi_id(ui_id);
        this.setUi_nd(ui_nd);
        this.setPc_id(pc_id);
        this.setMoney(money);
        this.setDiscount(discount);
        this.setHigh_money(high_money);
        this.setUpc_type(upc_type);
        this.setEnd_time(end_time);
        this.setUpc_state(upc_state);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setSend_unit(send_unit);
        this.setAi_id(ai_id);
        this.setState(state);
        this.setNote(note);
        this.setType(type);
        this.setRtime(rtime);
        this.setAccept_ui_id(accept_ui_id);
        this.setAccept_ui_nd(accept_ui_nd);

    }

    @SuppressWarnings("unused")
    public static void getUser_park_coupon_log(User_park_coupon_log user_park_coupon_log ){
        long upcl_id = user_park_coupon_log.getUpcl_id();
        long upc_id = user_park_coupon_log.getUpc_id();
        long ui_id = user_park_coupon_log.getUi_id();
        String ui_nd = user_park_coupon_log.getUi_nd();
        long pc_id = user_park_coupon_log.getPc_id();
        int money = user_park_coupon_log.getMoney();
        double discount = user_park_coupon_log.getDiscount();
        int high_money = user_park_coupon_log.getHigh_money();
        int upc_type = user_park_coupon_log.getUpc_type();
        java.util.Date end_time = user_park_coupon_log.getEnd_time();
        int upc_state = user_park_coupon_log.getUpc_state();
        java.util.Date ctime = user_park_coupon_log.getCtime();
        java.util.Date utime = user_park_coupon_log.getUtime();
        int send_unit = user_park_coupon_log.getSend_unit();
        long ai_id = user_park_coupon_log.getAi_id();
        int state = user_park_coupon_log.getState();
        String note = user_park_coupon_log.getNote();
        int type = user_park_coupon_log.getType();
        java.util.Date rtime = user_park_coupon_log.getRtime();
        long accept_ui_id = user_park_coupon_log.getAccept_ui_id();
        String accept_ui_nd = user_park_coupon_log.getAccept_ui_nd();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(User_park_coupon_log user_park_coupon_log){
        long upcl_id = user_park_coupon_log.getUpcl_id();
        long upc_id = user_park_coupon_log.getUpc_id();
        long ui_id = user_park_coupon_log.getUi_id();
        String ui_nd = user_park_coupon_log.getUi_nd();
        long pc_id = user_park_coupon_log.getPc_id();
        int money = user_park_coupon_log.getMoney();
        double discount = user_park_coupon_log.getDiscount();
        int high_money = user_park_coupon_log.getHigh_money();
        int upc_type = user_park_coupon_log.getUpc_type();
        java.util.Date end_time = user_park_coupon_log.getEnd_time();
        int upc_state = user_park_coupon_log.getUpc_state();
        java.util.Date ctime = user_park_coupon_log.getCtime();
        java.util.Date utime = user_park_coupon_log.getUtime();
        int send_unit = user_park_coupon_log.getSend_unit();
        long ai_id = user_park_coupon_log.getAi_id();
        int state = user_park_coupon_log.getState();
        String note = user_park_coupon_log.getNote();
        int type = user_park_coupon_log.getType();
        java.util.Date rtime = user_park_coupon_log.getRtime();
        long accept_ui_id = user_park_coupon_log.getAccept_ui_id();
        String accept_ui_nd = user_park_coupon_log.getAccept_ui_nd();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("upcl_id",upcl_id);
        _ret.put("upc_id",upc_id);
        _ret.put("ui_id",ui_id);
        _ret.put("ui_nd",ui_nd);
        _ret.put("pc_id",pc_id);
        _ret.put("money",money);
        _ret.put("discount",discount);
        _ret.put("high_money",high_money);
        _ret.put("upc_type",upc_type);
        _ret.put("end_time",end_time);
        _ret.put("upc_state",upc_state);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("send_unit",send_unit);
        _ret.put("ai_id",ai_id);
        _ret.put("state",state);
        _ret.put("note",note);
        _ret.put("type",type);
        _ret.put("rtime",rtime);
        _ret.put("accept_ui_id",accept_ui_id);
        _ret.put("accept_ui_nd",accept_ui_nd);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public User_park_coupon_log clone2(){
        try{
            return (User_park_coupon_log) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
