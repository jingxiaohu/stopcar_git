package com.park.bean;

import java.io.*;
import java.util.*;

//random_coupon_log
@SuppressWarnings({"serial"})
public class Random_coupon_log implements Cloneable , Serializable{

    //public static String[] carrays ={"rcl_id","nd","car_code","money","upc_id","type","pi_id","pi_name","area_code","orderid","order_type","partner","ctime","note","ai_id","ui_id","ui_nd","ae_id","send_unit","act_type","pay_source"};

    public long rcl_id;//bigint(20)    主键ID
    public String nd="";//varchar(100)    唯一标识符ND
    public String car_code="";//varchar(60)    车牌号
    public int money;//int(11)    减免金额
    public long upc_id;//bigint(20)    返券主键ID
    public int type;//int(11)    减免类型(1：返券2：随机减免)
    public long pi_id;//bigint(20)    停车场主键ID
    public String pi_name="";//varchar(100)    停车场名称
    public String area_code="";//varchar(60)    停车场地区区域编码
    public String orderid="";//varchar(100)    对应的订单ID
    public int order_type;//int(11)    订单类型(0:临停1：租赁)
    public String partner="";//varchar(100)    合作商家(例如：建行龙支付)
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public String note="";//varchar(100)    备注
    public long ai_id;//bigint(20)    活动主键ID(外键-活动基本信息表主键ID)
    public long ui_id;//bigint(20)    用户ID
    public String ui_nd="";//varchar(100)    用户唯一标识
    public long ae_id;//bigint(20)    事件表主键ID
    public int send_unit;//int(11)    赠送单位(0:吾泊平台1：龙支付)
    public int act_type;//int(11)    事件行为（1：充值2：支付）
    public int pay_source;//int(11)    支付类型1:支付宝2：微信3：银联4：钱包5:龙支付



    public long getRcl_id(){
        return rcl_id;
    }

    public void setRcl_id(long value){
        this.rcl_id= value;
    }

    public String getNd(){
        return nd;
    }

    public void setNd(String value){
    	if(value == null){
           value = "";
        }
        this.nd= value;
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

    public int getMoney(){
        return money;
    }

    public void setMoney(int value){
        this.money= value;
    }

    public long getUpc_id(){
        return upc_id;
    }

    public void setUpc_id(long value){
        this.upc_id= value;
    }

    public int getType(){
        return type;
    }

    public void setType(int value){
        this.type= value;
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

    public String getOrderid(){
        return orderid;
    }

    public void setOrderid(String value){
    	if(value == null){
           value = "";
        }
        this.orderid= value;
    }

    public int getOrder_type(){
        return order_type;
    }

    public void setOrder_type(int value){
        this.order_type= value;
    }

    public String getPartner(){
        return partner;
    }

    public void setPartner(String value){
    	if(value == null){
           value = "";
        }
        this.partner= value;
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

    public long getAi_id(){
        return ai_id;
    }

    public void setAi_id(long value){
        this.ai_id= value;
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

    public long getAe_id(){
        return ae_id;
    }

    public void setAe_id(long value){
        this.ae_id= value;
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

    public int getPay_source(){
        return pay_source;
    }

    public void setPay_source(int value){
        this.pay_source= value;
    }



    public static Random_coupon_log newRandom_coupon_log(long rcl_id, String nd, String car_code, int money, long upc_id, int type, long pi_id, String pi_name, String area_code, String orderid, int order_type, String partner, java.util.Date ctime, String note, long ai_id, long ui_id, String ui_nd, long ae_id, int send_unit, int act_type, int pay_source) {
        Random_coupon_log ret = new Random_coupon_log();
        ret.setRcl_id(rcl_id);
        ret.setNd(nd);
        ret.setCar_code(car_code);
        ret.setMoney(money);
        ret.setUpc_id(upc_id);
        ret.setType(type);
        ret.setPi_id(pi_id);
        ret.setPi_name(pi_name);
        ret.setArea_code(area_code);
        ret.setOrderid(orderid);
        ret.setOrder_type(order_type);
        ret.setPartner(partner);
        ret.setCtime(ctime);
        ret.setNote(note);
        ret.setAi_id(ai_id);
        ret.setUi_id(ui_id);
        ret.setUi_nd(ui_nd);
        ret.setAe_id(ae_id);
        ret.setSend_unit(send_unit);
        ret.setAct_type(act_type);
        ret.setPay_source(pay_source);
        return ret;    
    }

    public void assignment(Random_coupon_log random_coupon_log) {
        long rcl_id = random_coupon_log.getRcl_id();
        String nd = random_coupon_log.getNd();
        String car_code = random_coupon_log.getCar_code();
        int money = random_coupon_log.getMoney();
        long upc_id = random_coupon_log.getUpc_id();
        int type = random_coupon_log.getType();
        long pi_id = random_coupon_log.getPi_id();
        String pi_name = random_coupon_log.getPi_name();
        String area_code = random_coupon_log.getArea_code();
        String orderid = random_coupon_log.getOrderid();
        int order_type = random_coupon_log.getOrder_type();
        String partner = random_coupon_log.getPartner();
        java.util.Date ctime = random_coupon_log.getCtime();
        String note = random_coupon_log.getNote();
        long ai_id = random_coupon_log.getAi_id();
        long ui_id = random_coupon_log.getUi_id();
        String ui_nd = random_coupon_log.getUi_nd();
        long ae_id = random_coupon_log.getAe_id();
        int send_unit = random_coupon_log.getSend_unit();
        int act_type = random_coupon_log.getAct_type();
        int pay_source = random_coupon_log.getPay_source();

        this.setRcl_id(rcl_id);
        this.setNd(nd);
        this.setCar_code(car_code);
        this.setMoney(money);
        this.setUpc_id(upc_id);
        this.setType(type);
        this.setPi_id(pi_id);
        this.setPi_name(pi_name);
        this.setArea_code(area_code);
        this.setOrderid(orderid);
        this.setOrder_type(order_type);
        this.setPartner(partner);
        this.setCtime(ctime);
        this.setNote(note);
        this.setAi_id(ai_id);
        this.setUi_id(ui_id);
        this.setUi_nd(ui_nd);
        this.setAe_id(ae_id);
        this.setSend_unit(send_unit);
        this.setAct_type(act_type);
        this.setPay_source(pay_source);

    }

    @SuppressWarnings("unused")
    public static void getRandom_coupon_log(Random_coupon_log random_coupon_log ){
        long rcl_id = random_coupon_log.getRcl_id();
        String nd = random_coupon_log.getNd();
        String car_code = random_coupon_log.getCar_code();
        int money = random_coupon_log.getMoney();
        long upc_id = random_coupon_log.getUpc_id();
        int type = random_coupon_log.getType();
        long pi_id = random_coupon_log.getPi_id();
        String pi_name = random_coupon_log.getPi_name();
        String area_code = random_coupon_log.getArea_code();
        String orderid = random_coupon_log.getOrderid();
        int order_type = random_coupon_log.getOrder_type();
        String partner = random_coupon_log.getPartner();
        java.util.Date ctime = random_coupon_log.getCtime();
        String note = random_coupon_log.getNote();
        long ai_id = random_coupon_log.getAi_id();
        long ui_id = random_coupon_log.getUi_id();
        String ui_nd = random_coupon_log.getUi_nd();
        long ae_id = random_coupon_log.getAe_id();
        int send_unit = random_coupon_log.getSend_unit();
        int act_type = random_coupon_log.getAct_type();
        int pay_source = random_coupon_log.getPay_source();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Random_coupon_log random_coupon_log){
        long rcl_id = random_coupon_log.getRcl_id();
        String nd = random_coupon_log.getNd();
        String car_code = random_coupon_log.getCar_code();
        int money = random_coupon_log.getMoney();
        long upc_id = random_coupon_log.getUpc_id();
        int type = random_coupon_log.getType();
        long pi_id = random_coupon_log.getPi_id();
        String pi_name = random_coupon_log.getPi_name();
        String area_code = random_coupon_log.getArea_code();
        String orderid = random_coupon_log.getOrderid();
        int order_type = random_coupon_log.getOrder_type();
        String partner = random_coupon_log.getPartner();
        java.util.Date ctime = random_coupon_log.getCtime();
        String note = random_coupon_log.getNote();
        long ai_id = random_coupon_log.getAi_id();
        long ui_id = random_coupon_log.getUi_id();
        String ui_nd = random_coupon_log.getUi_nd();
        long ae_id = random_coupon_log.getAe_id();
        int send_unit = random_coupon_log.getSend_unit();
        int act_type = random_coupon_log.getAct_type();
        int pay_source = random_coupon_log.getPay_source();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("rcl_id",rcl_id);
        _ret.put("nd",nd);
        _ret.put("car_code",car_code);
        _ret.put("money",money);
        _ret.put("upc_id",upc_id);
        _ret.put("type",type);
        _ret.put("pi_id",pi_id);
        _ret.put("pi_name",pi_name);
        _ret.put("area_code",area_code);
        _ret.put("orderid",orderid);
        _ret.put("order_type",order_type);
        _ret.put("partner",partner);
        _ret.put("ctime",ctime);
        _ret.put("note",note);
        _ret.put("ai_id",ai_id);
        _ret.put("ui_id",ui_id);
        _ret.put("ui_nd",ui_nd);
        _ret.put("ae_id",ae_id);
        _ret.put("send_unit",send_unit);
        _ret.put("act_type",act_type);
        _ret.put("pay_source",pay_source);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Random_coupon_log clone2(){
        try{
            return (Random_coupon_log) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
