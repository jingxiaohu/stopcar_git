package com.park.bean;

import java.io.*;
import java.util.*;

//etc_userpay_record
@SuppressWarnings({"serial"})
public class Etc_userpay_record implements Cloneable , Serializable{

    //public static String[] carrays ={"eur_id","eur_nd","eu_id","eu_nd","ui_id","ui_nd","pu_id","pu_nd","pi_id","area_code","order_id","order_type","money","state","trade_time","refund_state","refund_time","ctime","note","pi_name","is_over","pay_orderid"};

    public long eur_id;//bigint(20)    主键ID
    public String eur_nd="";//varchar(100)    唯一标识符nd
    public long eu_id;//bigint(20)    ETC用户资料表主键ID
    public String eu_nd="";//varchar(100)    ETC用户资料表唯一标识ND
    public long ui_id;//bigint(20)    平台用户ID
    public String ui_nd="";//varchar(100)    平台用户唯一标识ND
    public long pu_id;//bigint(20)    商户主键ID
    public String pu_nd="";//varchar(100)    商户唯一标识ND
    public long pi_id;//bigint(20)    停车场ID
    public String area_code="";//varchar(30)    地址编码
    public String order_id="";//varchar(100)    车辆订单号
    public int order_type;//int(11)    订单类型（0：临停订单1：租赁订单）
    public long money;//bigint(20)    ETC支付金额单位分
    public int state;//int(11)    是否支付成功(0:未支付1：支付成功2：支付失败)
    public java.util.Date trade_time=new java.util.Date();//datetime    支付交易时间
    public int refund_state;//int(11)    是否进行了退款（0：没有退款1：退款成功2：退款失败）
    public java.util.Date refund_time=new java.util.Date();//datetime    退款行为发生时间
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public String note="";//varchar(100)    备注
    public String pi_name="";//varchar(80)    停车场名称
    public int is_over;//int(11)    订单事件是否完成ETC支付（0：未完成1：完成）
    public String pay_orderid="";//varchar(60)    支付订单号（本地事物单号）



    public long getEur_id(){
        return eur_id;
    }

    public void setEur_id(long value){
        this.eur_id= value;
    }

    public String getEur_nd(){
        return eur_nd;
    }

    public void setEur_nd(String value){
    	if(value == null){
           value = "";
        }
        this.eur_nd= value;
    }

    public long getEu_id(){
        return eu_id;
    }

    public void setEu_id(long value){
        this.eu_id= value;
    }

    public String getEu_nd(){
        return eu_nd;
    }

    public void setEu_nd(String value){
    	if(value == null){
           value = "";
        }
        this.eu_nd= value;
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

    public long getMoney(){
        return money;
    }

    public void setMoney(long value){
        this.money= value;
    }

    public int getState(){
        return state;
    }

    public void setState(int value){
        this.state= value;
    }

    public java.util.Date getTrade_time(){
        return trade_time;
    }

    public void setTrade_time(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.trade_time= value;
    }

    public int getRefund_state(){
        return refund_state;
    }

    public void setRefund_state(int value){
        this.refund_state= value;
    }

    public java.util.Date getRefund_time(){
        return refund_time;
    }

    public void setRefund_time(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.refund_time= value;
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

    public String getPi_name(){
        return pi_name;
    }

    public void setPi_name(String value){
    	if(value == null){
           value = "";
        }
        this.pi_name= value;
    }

    public int getIs_over(){
        return is_over;
    }

    public void setIs_over(int value){
        this.is_over= value;
    }

    public String getPay_orderid(){
        return pay_orderid;
    }

    public void setPay_orderid(String value){
    	if(value == null){
           value = "";
        }
        this.pay_orderid= value;
    }



    public static Etc_userpay_record newEtc_userpay_record(long eur_id, String eur_nd, long eu_id, String eu_nd, long ui_id, String ui_nd, long pu_id, String pu_nd, long pi_id, String area_code, String order_id, int order_type, long money, int state, java.util.Date trade_time, int refund_state, java.util.Date refund_time, java.util.Date ctime, String note, String pi_name, int is_over, String pay_orderid) {
        Etc_userpay_record ret = new Etc_userpay_record();
        ret.setEur_id(eur_id);
        ret.setEur_nd(eur_nd);
        ret.setEu_id(eu_id);
        ret.setEu_nd(eu_nd);
        ret.setUi_id(ui_id);
        ret.setUi_nd(ui_nd);
        ret.setPu_id(pu_id);
        ret.setPu_nd(pu_nd);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setOrder_id(order_id);
        ret.setOrder_type(order_type);
        ret.setMoney(money);
        ret.setState(state);
        ret.setTrade_time(trade_time);
        ret.setRefund_state(refund_state);
        ret.setRefund_time(refund_time);
        ret.setCtime(ctime);
        ret.setNote(note);
        ret.setPi_name(pi_name);
        ret.setIs_over(is_over);
        ret.setPay_orderid(pay_orderid);
        return ret;    
    }

    public void assignment(Etc_userpay_record etc_userpay_record) {
        long eur_id = etc_userpay_record.getEur_id();
        String eur_nd = etc_userpay_record.getEur_nd();
        long eu_id = etc_userpay_record.getEu_id();
        String eu_nd = etc_userpay_record.getEu_nd();
        long ui_id = etc_userpay_record.getUi_id();
        String ui_nd = etc_userpay_record.getUi_nd();
        long pu_id = etc_userpay_record.getPu_id();
        String pu_nd = etc_userpay_record.getPu_nd();
        long pi_id = etc_userpay_record.getPi_id();
        String area_code = etc_userpay_record.getArea_code();
        String order_id = etc_userpay_record.getOrder_id();
        int order_type = etc_userpay_record.getOrder_type();
        long money = etc_userpay_record.getMoney();
        int state = etc_userpay_record.getState();
        java.util.Date trade_time = etc_userpay_record.getTrade_time();
        int refund_state = etc_userpay_record.getRefund_state();
        java.util.Date refund_time = etc_userpay_record.getRefund_time();
        java.util.Date ctime = etc_userpay_record.getCtime();
        String note = etc_userpay_record.getNote();
        String pi_name = etc_userpay_record.getPi_name();
        int is_over = etc_userpay_record.getIs_over();
        String pay_orderid = etc_userpay_record.getPay_orderid();

        this.setEur_id(eur_id);
        this.setEur_nd(eur_nd);
        this.setEu_id(eu_id);
        this.setEu_nd(eu_nd);
        this.setUi_id(ui_id);
        this.setUi_nd(ui_nd);
        this.setPu_id(pu_id);
        this.setPu_nd(pu_nd);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setOrder_id(order_id);
        this.setOrder_type(order_type);
        this.setMoney(money);
        this.setState(state);
        this.setTrade_time(trade_time);
        this.setRefund_state(refund_state);
        this.setRefund_time(refund_time);
        this.setCtime(ctime);
        this.setNote(note);
        this.setPi_name(pi_name);
        this.setIs_over(is_over);
        this.setPay_orderid(pay_orderid);

    }

    @SuppressWarnings("unused")
    public static void getEtc_userpay_record(Etc_userpay_record etc_userpay_record ){
        long eur_id = etc_userpay_record.getEur_id();
        String eur_nd = etc_userpay_record.getEur_nd();
        long eu_id = etc_userpay_record.getEu_id();
        String eu_nd = etc_userpay_record.getEu_nd();
        long ui_id = etc_userpay_record.getUi_id();
        String ui_nd = etc_userpay_record.getUi_nd();
        long pu_id = etc_userpay_record.getPu_id();
        String pu_nd = etc_userpay_record.getPu_nd();
        long pi_id = etc_userpay_record.getPi_id();
        String area_code = etc_userpay_record.getArea_code();
        String order_id = etc_userpay_record.getOrder_id();
        int order_type = etc_userpay_record.getOrder_type();
        long money = etc_userpay_record.getMoney();
        int state = etc_userpay_record.getState();
        java.util.Date trade_time = etc_userpay_record.getTrade_time();
        int refund_state = etc_userpay_record.getRefund_state();
        java.util.Date refund_time = etc_userpay_record.getRefund_time();
        java.util.Date ctime = etc_userpay_record.getCtime();
        String note = etc_userpay_record.getNote();
        String pi_name = etc_userpay_record.getPi_name();
        int is_over = etc_userpay_record.getIs_over();
        String pay_orderid = etc_userpay_record.getPay_orderid();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Etc_userpay_record etc_userpay_record){
        long eur_id = etc_userpay_record.getEur_id();
        String eur_nd = etc_userpay_record.getEur_nd();
        long eu_id = etc_userpay_record.getEu_id();
        String eu_nd = etc_userpay_record.getEu_nd();
        long ui_id = etc_userpay_record.getUi_id();
        String ui_nd = etc_userpay_record.getUi_nd();
        long pu_id = etc_userpay_record.getPu_id();
        String pu_nd = etc_userpay_record.getPu_nd();
        long pi_id = etc_userpay_record.getPi_id();
        String area_code = etc_userpay_record.getArea_code();
        String order_id = etc_userpay_record.getOrder_id();
        int order_type = etc_userpay_record.getOrder_type();
        long money = etc_userpay_record.getMoney();
        int state = etc_userpay_record.getState();
        java.util.Date trade_time = etc_userpay_record.getTrade_time();
        int refund_state = etc_userpay_record.getRefund_state();
        java.util.Date refund_time = etc_userpay_record.getRefund_time();
        java.util.Date ctime = etc_userpay_record.getCtime();
        String note = etc_userpay_record.getNote();
        String pi_name = etc_userpay_record.getPi_name();
        int is_over = etc_userpay_record.getIs_over();
        String pay_orderid = etc_userpay_record.getPay_orderid();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("eur_id",eur_id);
        _ret.put("eur_nd",eur_nd);
        _ret.put("eu_id",eu_id);
        _ret.put("eu_nd",eu_nd);
        _ret.put("ui_id",ui_id);
        _ret.put("ui_nd",ui_nd);
        _ret.put("pu_id",pu_id);
        _ret.put("pu_nd",pu_nd);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("order_id",order_id);
        _ret.put("order_type",order_type);
        _ret.put("money",money);
        _ret.put("state",state);
        _ret.put("trade_time",trade_time);
        _ret.put("refund_state",refund_state);
        _ret.put("refund_time",refund_time);
        _ret.put("ctime",ctime);
        _ret.put("note",note);
        _ret.put("pi_name",pi_name);
        _ret.put("is_over",is_over);
        _ret.put("pay_orderid",pay_orderid);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Etc_userpay_record clone2(){
        try{
            return (Etc_userpay_record) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
