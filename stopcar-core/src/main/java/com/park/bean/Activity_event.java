package com.park.bean;

import java.io.*;
import java.util.*;

//activity_event
@SuppressWarnings({"serial"})
public class Activity_event implements Cloneable , Serializable{

    //public static String[] carrays ={"id","type","ui_id","ui_nd","pi_id","pi_name","area_code","car_code","order_type","orderid","ai_id","money","ctime","state","utime","ai_money","note","md5","send_unit","act_type","method_name","pay_source"};

    public long id;//bigint(20)    主键ID
    public int type;//int(11)    减免类型(1：返券2：随机减免)
    public long ui_id;//bigint(20)    用户ID
    public String ui_nd="";//varchar(100)    用户ND
    public long pi_id;//bigint(20)    停车场主键ID
    public String pi_name="";//varchar(100)    停车场名称
    public String area_code="";//varchar(100)    停车场地址区域编码
    public String car_code="";//varchar(100)    车牌号
    public int order_type;//int(11)    订单类型(0:临停1：租赁)
    public String orderid="";//varchar(100)    订单ID
    public long ai_id;//bigint(20)    活动表主键ID
    public int money;//int(11)    随机立免金额（单位分）
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public int state;//int(11)    处理状态0：没有处理1：处理成功2：处理失败
    public java.util.Date utime=new java.util.Date();//datetime    处理时间
    public int ai_money;//int(11)    处理后当前活动剩余金额
    public String note="";//varchar(100)    备注
    public String md5="";//varchar(100)    参数MD5（type+ai_id+ui_id+ui_nd+orderid+order_type+pi_id+car_code）
    public int send_unit;//int(11)    赠送单位(0:吾泊平台1：龙支付)
    public int act_type;//int(11)    事件行为（1：充值2：支付）
    public String method_name="";//varchar(100)    执行方法名称
    public int pay_source;//int(11)    支付类型1:支付宝2：微信3：银联4：钱包5:龙支付



    public long getId(){
        return id;
    }

    public void setId(long value){
        this.id= value;
    }

    public int getType(){
        return type;
    }

    public void setType(int value){
        this.type= value;
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

    public String getPi_name(){
        return pi_name;
    }

    public void setPi_name(String value){
    	if(value == null){
           value = "";
        }
        this.pi_name= value;
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

    public String getCar_code(){
        return car_code;
    }

    public void setCar_code(String value){
    	if(value == null){
           value = "";
        }
        this.car_code= value;
    }

    public int getOrder_type(){
        return order_type;
    }

    public void setOrder_type(int value){
        this.order_type= value;
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

    public long getAi_id(){
        return ai_id;
    }

    public void setAi_id(long value){
        this.ai_id= value;
    }

    public int getMoney(){
        return money;
    }

    public void setMoney(int value){
        this.money= value;
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

    public int getState(){
        return state;
    }

    public void setState(int value){
        this.state= value;
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

    public int getAi_money(){
        return ai_money;
    }

    public void setAi_money(int value){
        this.ai_money= value;
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

    public String getMd5(){
        return md5;
    }

    public void setMd5(String value){
    	if(value == null){
           value = "";
        }
        this.md5= value;
    }

    public int getSend_unit(){
        return send_unit;
    }

    public void setSend_unit(int value){
        this.send_unit= value;
    }

    public int getAct_type(){
        return act_type;
    }

    public void setAct_type(int value){
        this.act_type= value;
    }

    public String getMethod_name(){
        return method_name;
    }

    public void setMethod_name(String value){
    	if(value == null){
           value = "";
        }
        this.method_name= value;
    }

    public int getPay_source(){
        return pay_source;
    }

    public void setPay_source(int value){
        this.pay_source= value;
    }



    public static Activity_event newActivity_event(long id, int type, long ui_id, String ui_nd, long pi_id, String pi_name, String area_code, String car_code, int order_type, String orderid, long ai_id, int money, java.util.Date ctime, int state, java.util.Date utime, int ai_money, String note, String md5, int send_unit, int act_type, String method_name, int pay_source) {
        Activity_event ret = new Activity_event();
        ret.setId(id);
        ret.setType(type);
        ret.setUi_id(ui_id);
        ret.setUi_nd(ui_nd);
        ret.setPi_id(pi_id);
        ret.setPi_name(pi_name);
        ret.setArea_code(area_code);
        ret.setCar_code(car_code);
        ret.setOrder_type(order_type);
        ret.setOrderid(orderid);
        ret.setAi_id(ai_id);
        ret.setMoney(money);
        ret.setCtime(ctime);
        ret.setState(state);
        ret.setUtime(utime);
        ret.setAi_money(ai_money);
        ret.setNote(note);
        ret.setMd5(md5);
        ret.setSend_unit(send_unit);
        ret.setAct_type(act_type);
        ret.setMethod_name(method_name);
        ret.setPay_source(pay_source);
        return ret;    
    }

    public void assignment(Activity_event activity_event) {
        long id = activity_event.getId();
        int type = activity_event.getType();
        long ui_id = activity_event.getUi_id();
        String ui_nd = activity_event.getUi_nd();
        long pi_id = activity_event.getPi_id();
        String pi_name = activity_event.getPi_name();
        String area_code = activity_event.getArea_code();
        String car_code = activity_event.getCar_code();
        int order_type = activity_event.getOrder_type();
        String orderid = activity_event.getOrderid();
        long ai_id = activity_event.getAi_id();
        int money = activity_event.getMoney();
        java.util.Date ctime = activity_event.getCtime();
        int state = activity_event.getState();
        java.util.Date utime = activity_event.getUtime();
        int ai_money = activity_event.getAi_money();
        String note = activity_event.getNote();
        String md5 = activity_event.getMd5();
        int send_unit = activity_event.getSend_unit();
        int act_type = activity_event.getAct_type();
        String method_name = activity_event.getMethod_name();
        int pay_source = activity_event.getPay_source();

        this.setId(id);
        this.setType(type);
        this.setUi_id(ui_id);
        this.setUi_nd(ui_nd);
        this.setPi_id(pi_id);
        this.setPi_name(pi_name);
        this.setArea_code(area_code);
        this.setCar_code(car_code);
        this.setOrder_type(order_type);
        this.setOrderid(orderid);
        this.setAi_id(ai_id);
        this.setMoney(money);
        this.setCtime(ctime);
        this.setState(state);
        this.setUtime(utime);
        this.setAi_money(ai_money);
        this.setNote(note);
        this.setMd5(md5);
        this.setSend_unit(send_unit);
        this.setAct_type(act_type);
        this.setMethod_name(method_name);
        this.setPay_source(pay_source);

    }

    @SuppressWarnings("unused")
    public static void getActivity_event(Activity_event activity_event ){
        long id = activity_event.getId();
        int type = activity_event.getType();
        long ui_id = activity_event.getUi_id();
        String ui_nd = activity_event.getUi_nd();
        long pi_id = activity_event.getPi_id();
        String pi_name = activity_event.getPi_name();
        String area_code = activity_event.getArea_code();
        String car_code = activity_event.getCar_code();
        int order_type = activity_event.getOrder_type();
        String orderid = activity_event.getOrderid();
        long ai_id = activity_event.getAi_id();
        int money = activity_event.getMoney();
        java.util.Date ctime = activity_event.getCtime();
        int state = activity_event.getState();
        java.util.Date utime = activity_event.getUtime();
        int ai_money = activity_event.getAi_money();
        String note = activity_event.getNote();
        String md5 = activity_event.getMd5();
        int send_unit = activity_event.getSend_unit();
        int act_type = activity_event.getAct_type();
        String method_name = activity_event.getMethod_name();
        int pay_source = activity_event.getPay_source();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Activity_event activity_event){
        long id = activity_event.getId();
        int type = activity_event.getType();
        long ui_id = activity_event.getUi_id();
        String ui_nd = activity_event.getUi_nd();
        long pi_id = activity_event.getPi_id();
        String pi_name = activity_event.getPi_name();
        String area_code = activity_event.getArea_code();
        String car_code = activity_event.getCar_code();
        int order_type = activity_event.getOrder_type();
        String orderid = activity_event.getOrderid();
        long ai_id = activity_event.getAi_id();
        int money = activity_event.getMoney();
        java.util.Date ctime = activity_event.getCtime();
        int state = activity_event.getState();
        java.util.Date utime = activity_event.getUtime();
        int ai_money = activity_event.getAi_money();
        String note = activity_event.getNote();
        String md5 = activity_event.getMd5();
        int send_unit = activity_event.getSend_unit();
        int act_type = activity_event.getAct_type();
        String method_name = activity_event.getMethod_name();
        int pay_source = activity_event.getPay_source();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("type",type);
        _ret.put("ui_id",ui_id);
        _ret.put("ui_nd",ui_nd);
        _ret.put("pi_id",pi_id);
        _ret.put("pi_name",pi_name);
        _ret.put("area_code",area_code);
        _ret.put("car_code",car_code);
        _ret.put("order_type",order_type);
        _ret.put("orderid",orderid);
        _ret.put("ai_id",ai_id);
        _ret.put("money",money);
        _ret.put("ctime",ctime);
        _ret.put("state",state);
        _ret.put("utime",utime);
        _ret.put("ai_money",ai_money);
        _ret.put("note",note);
        _ret.put("md5",md5);
        _ret.put("send_unit",send_unit);
        _ret.put("act_type",act_type);
        _ret.put("method_name",method_name);
        _ret.put("pay_source",pay_source);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Activity_event clone2(){
        try{
            return (Activity_event) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
