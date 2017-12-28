package com.park.bean;

import java.io.*;
import java.util.*;

//order_abnormal_log
@SuppressWarnings({"serial"})
public class Order_abnormal_log implements Cloneable , Serializable{

    //public static String[] carrays ={"id","order_id","server_type","client_type","money","ctime","pi_id","area_code","pi_name","is_asyn","note"};

    public long id;//bigint(20)    主键ID
    public String order_id="";//varchar(100)    订单号
    public int server_type;//int(11)    订单服务器端支付类型0：未指定1:支付宝2：微信3：银联4：钱包5:龙支付6:ETC快捷支付7：扫脸支付8：指纹支付9：指静脉支付
    public int client_type;//int(11)    订单接收到客户端支付类型0：未指定1：现金支付2：线上支付
    public long money;//bigint(20)    订单金额单位分
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public long pi_id;//bigint(20)    停车场主键ID
    public String area_code="";//varchar(60)    地址区域编码
    public String pi_name="";//varchar(100)    停车场名称
    public int is_asyn;//int(11)    是否是异步传输(0:同步1：异步)
    public String note="";//varchar(100)    备注



    public long getId(){
        return id;
    }

    public void setId(long value){
        this.id= value;
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

    public int getServer_type(){
        return server_type;
    }

    public void setServer_type(int value){
        this.server_type= value;
    }

    public int getClient_type(){
        return client_type;
    }

    public void setClient_type(int value){
        this.client_type= value;
    }

    public long getMoney(){
        return money;
    }

    public void setMoney(long value){
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

    public String getPi_name(){
        return pi_name;
    }

    public void setPi_name(String value){
    	if(value == null){
           value = "";
        }
        this.pi_name= value;
    }

    public int getIs_asyn(){
        return is_asyn;
    }

    public void setIs_asyn(int value){
        this.is_asyn= value;
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



    public static Order_abnormal_log newOrder_abnormal_log(long id, String order_id, int server_type, int client_type, long money, java.util.Date ctime, long pi_id, String area_code, String pi_name, int is_asyn, String note) {
        Order_abnormal_log ret = new Order_abnormal_log();
        ret.setId(id);
        ret.setOrder_id(order_id);
        ret.setServer_type(server_type);
        ret.setClient_type(client_type);
        ret.setMoney(money);
        ret.setCtime(ctime);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setPi_name(pi_name);
        ret.setIs_asyn(is_asyn);
        ret.setNote(note);
        return ret;    
    }

    public void assignment(Order_abnormal_log order_abnormal_log) {
        long id = order_abnormal_log.getId();
        String order_id = order_abnormal_log.getOrder_id();
        int server_type = order_abnormal_log.getServer_type();
        int client_type = order_abnormal_log.getClient_type();
        long money = order_abnormal_log.getMoney();
        java.util.Date ctime = order_abnormal_log.getCtime();
        long pi_id = order_abnormal_log.getPi_id();
        String area_code = order_abnormal_log.getArea_code();
        String pi_name = order_abnormal_log.getPi_name();
        int is_asyn = order_abnormal_log.getIs_asyn();
        String note = order_abnormal_log.getNote();

        this.setId(id);
        this.setOrder_id(order_id);
        this.setServer_type(server_type);
        this.setClient_type(client_type);
        this.setMoney(money);
        this.setCtime(ctime);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setPi_name(pi_name);
        this.setIs_asyn(is_asyn);
        this.setNote(note);

    }

    @SuppressWarnings("unused")
    public static void getOrder_abnormal_log(Order_abnormal_log order_abnormal_log ){
        long id = order_abnormal_log.getId();
        String order_id = order_abnormal_log.getOrder_id();
        int server_type = order_abnormal_log.getServer_type();
        int client_type = order_abnormal_log.getClient_type();
        long money = order_abnormal_log.getMoney();
        java.util.Date ctime = order_abnormal_log.getCtime();
        long pi_id = order_abnormal_log.getPi_id();
        String area_code = order_abnormal_log.getArea_code();
        String pi_name = order_abnormal_log.getPi_name();
        int is_asyn = order_abnormal_log.getIs_asyn();
        String note = order_abnormal_log.getNote();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Order_abnormal_log order_abnormal_log){
        long id = order_abnormal_log.getId();
        String order_id = order_abnormal_log.getOrder_id();
        int server_type = order_abnormal_log.getServer_type();
        int client_type = order_abnormal_log.getClient_type();
        long money = order_abnormal_log.getMoney();
        java.util.Date ctime = order_abnormal_log.getCtime();
        long pi_id = order_abnormal_log.getPi_id();
        String area_code = order_abnormal_log.getArea_code();
        String pi_name = order_abnormal_log.getPi_name();
        int is_asyn = order_abnormal_log.getIs_asyn();
        String note = order_abnormal_log.getNote();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("order_id",order_id);
        _ret.put("server_type",server_type);
        _ret.put("client_type",client_type);
        _ret.put("money",money);
        _ret.put("ctime",ctime);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("pi_name",pi_name);
        _ret.put("is_asyn",is_asyn);
        _ret.put("note",note);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Order_abnormal_log clone2(){
        try{
            return (Order_abnormal_log) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
