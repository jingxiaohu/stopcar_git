package com.park.bean;

import java.io.*;
import java.util.*;

//lock_money_log
@SuppressWarnings({"serial"})
public class Lock_money_log implements Cloneable , Serializable{

    //public static String[] carrays ={"id","ui_id","ui_nd","type","state","money","orderid","oder_type","pi_id","area_code","ctime","note"};

    public long id;//bigint(20)    主键ID
    public long ui_id;//bigint(20)    用户主键ID
    public String ui_nd="";//varchar(100)    用户UUID
    public int type;//int(11)    类型0：预约1：取消预约2：租赁
    public int state;//int(11)    处理结果状态0:成功1：失败
    public int money;//int(11)    金额(单位分)
    public String orderid="";//varchar(100)    订单号
    public int oder_type;//int(11)    订单类型0:预约1：租赁
    public long pi_id;//bigint(20)    停车场ID
    public String area_code="";//varchar(60)    区域代码
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public String note="";//varchar(100)    备注



    public long getId(){
        return id;
    }

    public void setId(long value){
        this.id= value;
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

    public int getType(){
        return type;
    }

    public void setType(int value){
        this.type= value;
    }

    public int getState(){
        return state;
    }

    public void setState(int value){
        this.state= value;
    }

    public int getMoney(){
        return money;
    }

    public void setMoney(int value){
        this.money= value;
    }

    public String getOrderid(){
        return orderid;
    }

    public void setOrderid(String value){
    	if(value == null){
           value = "";
        }
        this.orderid= value;
    }

    public int getOder_type(){
        return oder_type;
    }

    public void setOder_type(int value){
        this.oder_type= value;
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



    public static Lock_money_log newLock_money_log(long id, long ui_id, String ui_nd, int type, int state, int money, String orderid, int oder_type, long pi_id, String area_code, java.util.Date ctime, String note) {
        Lock_money_log ret = new Lock_money_log();
        ret.setId(id);
        ret.setUi_id(ui_id);
        ret.setUi_nd(ui_nd);
        ret.setType(type);
        ret.setState(state);
        ret.setMoney(money);
        ret.setOrderid(orderid);
        ret.setOder_type(oder_type);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setCtime(ctime);
        ret.setNote(note);
        return ret;    
    }

    public void assignment(Lock_money_log lock_money_log) {
        long id = lock_money_log.getId();
        long ui_id = lock_money_log.getUi_id();
        String ui_nd = lock_money_log.getUi_nd();
        int type = lock_money_log.getType();
        int state = lock_money_log.getState();
        int money = lock_money_log.getMoney();
        String orderid = lock_money_log.getOrderid();
        int oder_type = lock_money_log.getOder_type();
        long pi_id = lock_money_log.getPi_id();
        String area_code = lock_money_log.getArea_code();
        java.util.Date ctime = lock_money_log.getCtime();
        String note = lock_money_log.getNote();

        this.setId(id);
        this.setUi_id(ui_id);
        this.setUi_nd(ui_nd);
        this.setType(type);
        this.setState(state);
        this.setMoney(money);
        this.setOrderid(orderid);
        this.setOder_type(oder_type);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setCtime(ctime);
        this.setNote(note);

    }

    @SuppressWarnings("unused")
    public static void getLock_money_log(Lock_money_log lock_money_log ){
        long id = lock_money_log.getId();
        long ui_id = lock_money_log.getUi_id();
        String ui_nd = lock_money_log.getUi_nd();
        int type = lock_money_log.getType();
        int state = lock_money_log.getState();
        int money = lock_money_log.getMoney();
        String orderid = lock_money_log.getOrderid();
        int oder_type = lock_money_log.getOder_type();
        long pi_id = lock_money_log.getPi_id();
        String area_code = lock_money_log.getArea_code();
        java.util.Date ctime = lock_money_log.getCtime();
        String note = lock_money_log.getNote();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Lock_money_log lock_money_log){
        long id = lock_money_log.getId();
        long ui_id = lock_money_log.getUi_id();
        String ui_nd = lock_money_log.getUi_nd();
        int type = lock_money_log.getType();
        int state = lock_money_log.getState();
        int money = lock_money_log.getMoney();
        String orderid = lock_money_log.getOrderid();
        int oder_type = lock_money_log.getOder_type();
        long pi_id = lock_money_log.getPi_id();
        String area_code = lock_money_log.getArea_code();
        java.util.Date ctime = lock_money_log.getCtime();
        String note = lock_money_log.getNote();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("ui_id",ui_id);
        _ret.put("ui_nd",ui_nd);
        _ret.put("type",type);
        _ret.put("state",state);
        _ret.put("money",money);
        _ret.put("orderid",orderid);
        _ret.put("oder_type",oder_type);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("ctime",ctime);
        _ret.put("note",note);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Lock_money_log clone2(){
        try{
            return (Lock_money_log) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
