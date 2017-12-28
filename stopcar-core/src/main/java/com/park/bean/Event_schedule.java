package com.park.bean;

import java.io.*;
import java.util.*;

//event_schedule
@SuppressWarnings({"serial"})
public class Event_schedule implements Cloneable , Serializable{

    //public static String[] carrays ={"es_id","event_name","event_type","up_orderid","order_id","order_type","ui_id","ui_nd","pi_id","area_code","ctime","utime","state","note"};

    public long es_id;//bigint(20)    主键ID
    public String event_name="";//varchar(100)    事件名称（中文）
    public int event_type;//int(11)    事件类型（0：未指定1：续约租赁订单）
    public String up_orderid="";//varchar(80)    平台支付流水订单号
    public String order_id="";//varchar(80)    业务订单号（例如普通临停订单预约订单租赁订单包月订单）
    public int order_type;//int(11)    业务订单类型（0:未指定1：普通临停订单2：预约订单3：租赁订单4：包月订单）
    public long ui_id;//bigint(20)    用户ID
    public String ui_nd="";//varchar(80)    用户ND
    public long pi_id;//bigint(20)    停车场主键ID
    public String area_code="";//varchar(30)    地址区域编码
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public int state;//int(11)    处理状态（0：未处理1：处理成功2：处理失败）
    public String note="";//varchar(100)    备注



    public long getEs_id(){
        return es_id;
    }

    public void setEs_id(long value){
        this.es_id= value;
    }

    public String getEvent_name(){
        return event_name;
    }

    public void setEvent_name(String value){
    	if(value == null){
           value = "";
        }
        this.event_name= value;
    }

    public int getEvent_type(){
        return event_type;
    }

    public void setEvent_type(int value){
        this.event_type= value;
    }

    public String getUp_orderid(){
        return up_orderid;
    }

    public void setUp_orderid(String value){
    	if(value == null){
           value = "";
        }
        this.up_orderid= value;
    }

    public String getOrder_id(){
        return order_id;
    }

    public void setOrder_id(String value){
    	if(value == null){
           value = "";
        }
        this.order_id= value;
    }

    public int getOrder_type(){
        return order_type;
    }

    public void setOrder_type(int value){
        this.order_type= value;
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

    public java.util.Date getUtime(){
        return utime;
    }

    public void setUtime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.utime= value;
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



    public static Event_schedule newEvent_schedule(long es_id, String event_name, int event_type, String up_orderid, String order_id, int order_type, long ui_id, String ui_nd, long pi_id, String area_code, java.util.Date ctime, java.util.Date utime, int state, String note) {
        Event_schedule ret = new Event_schedule();
        ret.setEs_id(es_id);
        ret.setEvent_name(event_name);
        ret.setEvent_type(event_type);
        ret.setUp_orderid(up_orderid);
        ret.setOrder_id(order_id);
        ret.setOrder_type(order_type);
        ret.setUi_id(ui_id);
        ret.setUi_nd(ui_nd);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setState(state);
        ret.setNote(note);
        return ret;    
    }

    public void assignment(Event_schedule event_schedule) {
        long es_id = event_schedule.getEs_id();
        String event_name = event_schedule.getEvent_name();
        int event_type = event_schedule.getEvent_type();
        String up_orderid = event_schedule.getUp_orderid();
        String order_id = event_schedule.getOrder_id();
        int order_type = event_schedule.getOrder_type();
        long ui_id = event_schedule.getUi_id();
        String ui_nd = event_schedule.getUi_nd();
        long pi_id = event_schedule.getPi_id();
        String area_code = event_schedule.getArea_code();
        java.util.Date ctime = event_schedule.getCtime();
        java.util.Date utime = event_schedule.getUtime();
        int state = event_schedule.getState();
        String note = event_schedule.getNote();

        this.setEs_id(es_id);
        this.setEvent_name(event_name);
        this.setEvent_type(event_type);
        this.setUp_orderid(up_orderid);
        this.setOrder_id(order_id);
        this.setOrder_type(order_type);
        this.setUi_id(ui_id);
        this.setUi_nd(ui_nd);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setState(state);
        this.setNote(note);

    }

    @SuppressWarnings("unused")
    public static void getEvent_schedule(Event_schedule event_schedule ){
        long es_id = event_schedule.getEs_id();
        String event_name = event_schedule.getEvent_name();
        int event_type = event_schedule.getEvent_type();
        String up_orderid = event_schedule.getUp_orderid();
        String order_id = event_schedule.getOrder_id();
        int order_type = event_schedule.getOrder_type();
        long ui_id = event_schedule.getUi_id();
        String ui_nd = event_schedule.getUi_nd();
        long pi_id = event_schedule.getPi_id();
        String area_code = event_schedule.getArea_code();
        java.util.Date ctime = event_schedule.getCtime();
        java.util.Date utime = event_schedule.getUtime();
        int state = event_schedule.getState();
        String note = event_schedule.getNote();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Event_schedule event_schedule){
        long es_id = event_schedule.getEs_id();
        String event_name = event_schedule.getEvent_name();
        int event_type = event_schedule.getEvent_type();
        String up_orderid = event_schedule.getUp_orderid();
        String order_id = event_schedule.getOrder_id();
        int order_type = event_schedule.getOrder_type();
        long ui_id = event_schedule.getUi_id();
        String ui_nd = event_schedule.getUi_nd();
        long pi_id = event_schedule.getPi_id();
        String area_code = event_schedule.getArea_code();
        java.util.Date ctime = event_schedule.getCtime();
        java.util.Date utime = event_schedule.getUtime();
        int state = event_schedule.getState();
        String note = event_schedule.getNote();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("es_id",es_id);
        _ret.put("event_name",event_name);
        _ret.put("event_type",event_type);
        _ret.put("up_orderid",up_orderid);
        _ret.put("order_id",order_id);
        _ret.put("order_type",order_type);
        _ret.put("ui_id",ui_id);
        _ret.put("ui_nd",ui_nd);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("state",state);
        _ret.put("note",note);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Event_schedule clone2(){
        try{
            return (Event_schedule) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
