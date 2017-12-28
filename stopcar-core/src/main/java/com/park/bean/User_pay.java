package com.park.bean;

import java.io.*;
import java.util.*;

//user_pay
@SuppressWarnings({"serial"})
public class User_pay implements Cloneable , Serializable{

    //public static String[] carrays ={"id","order_id","transaction_id","type","version_code","system_type","return_url","ui_id","ui_nd","money","act_type","ctime","utime","etime","state","ip","referer","subject","note","car_order_id","tel","escape_orderids"};

    public long id;//bigint(20)    主键ID
    public String order_id="";//varchar(100)    我的订单
    public String transaction_id="";//varchar(100)    第三方事物单号
    public int type;//int(11)    支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
    public int version_code;//int(11)    当前手机APP版本号
    public int system_type;//int(11)    操作系统类型（IOSAndroidweb）1:android2:IOS3:web4:PDA
    public String return_url="";//varchar(255)    前置页面跳转URL
    public long ui_id;//bigint(20)    
    public String ui_nd="";//varchar(100)    
    public long money;//bigint(20)    
    public int act_type;//int(11)    行为类型1：充值2：普通订单支付3：租赁订单支付4：租赁订单续约
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    第三方回调时间
    public java.util.Date etime=new java.util.Date();//datetime    订单失效时间
    public int state;//int(11)    交易状态(0:未支付1：已支付2：支付失败)
    public String ip="";//varchar(100)    IP地址
    public String referer="";//varchar(255)    第三方回调referer
    public String subject="";//varchar(200)    商品名称
    public String note="";//varchar(100)    备注
    public String car_order_id="";//varchar(200)    停车订单号
    public String tel="";//varchar(20)    用户电话号码
    public String escape_orderids="";//varchar(2000)    补交欠费订单集合(订单之间逗号分隔a1,a2,a3)



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

    public String getTransaction_id(){
        return transaction_id;
    }

    public void setTransaction_id(String value){
    	if(value == null){
           value = "";
        }
        this.transaction_id= value;
    }

    public int getType(){
        return type;
    }

    public void setType(int value){
        this.type= value;
    }

    public int getVersion_code(){
        return version_code;
    }

    public void setVersion_code(int value){
        this.version_code= value;
    }

    public int getSystem_type(){
        return system_type;
    }

    public void setSystem_type(int value){
        this.system_type= value;
    }

    public String getReturn_url(){
        return return_url;
    }

    public void setReturn_url(String value){
    	if(value == null){
           value = "";
        }
        this.return_url= value;
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

    public long getMoney(){
        return money;
    }

    public void setMoney(long value){
        this.money= value;
    }

    public int getAct_type(){
        return act_type;
    }

    public void setAct_type(int value){
        this.act_type= value;
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

    public java.util.Date getEtime(){
        return etime;
    }

    public void setEtime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.etime= value;
    }

    public int getState(){
        return state;
    }

    public void setState(int value){
        this.state= value;
    }

    public String getIp(){
        return ip;
    }

    public void setIp(String value){
    	if(value == null){
           value = "";
        }
        this.ip= value;
    }

    public String getReferer(){
        return referer;
    }

    public void setReferer(String value){
    	if(value == null){
           value = "";
        }
        this.referer= value;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String value){
    	if(value == null){
           value = "";
        }
        this.subject= value;
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

    public String getCar_order_id(){
        return car_order_id;
    }

    public void setCar_order_id(String value){
    	if(value == null){
           value = "";
        }
        this.car_order_id= value;
    }

    public String getTel(){
        return tel;
    }

    public void setTel(String value){
    	if(value == null){
           value = "";
        }
        this.tel= value;
    }

    public String getEscape_orderids(){
        return escape_orderids;
    }

    public void setEscape_orderids(String value){
    	if(value == null){
           value = "";
        }
        this.escape_orderids= value;
    }



    public static User_pay newUser_pay(long id, String order_id, String transaction_id, int type, int version_code, int system_type, String return_url, long ui_id, String ui_nd, long money, int act_type, java.util.Date ctime, java.util.Date utime, java.util.Date etime, int state, String ip, String referer, String subject, String note, String car_order_id, String tel, String escape_orderids) {
        User_pay ret = new User_pay();
        ret.setId(id);
        ret.setOrder_id(order_id);
        ret.setTransaction_id(transaction_id);
        ret.setType(type);
        ret.setVersion_code(version_code);
        ret.setSystem_type(system_type);
        ret.setReturn_url(return_url);
        ret.setUi_id(ui_id);
        ret.setUi_nd(ui_nd);
        ret.setMoney(money);
        ret.setAct_type(act_type);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setEtime(etime);
        ret.setState(state);
        ret.setIp(ip);
        ret.setReferer(referer);
        ret.setSubject(subject);
        ret.setNote(note);
        ret.setCar_order_id(car_order_id);
        ret.setTel(tel);
        ret.setEscape_orderids(escape_orderids);
        return ret;    
    }

    public void assignment(User_pay user_pay) {
        long id = user_pay.getId();
        String order_id = user_pay.getOrder_id();
        String transaction_id = user_pay.getTransaction_id();
        int type = user_pay.getType();
        int version_code = user_pay.getVersion_code();
        int system_type = user_pay.getSystem_type();
        String return_url = user_pay.getReturn_url();
        long ui_id = user_pay.getUi_id();
        String ui_nd = user_pay.getUi_nd();
        long money = user_pay.getMoney();
        int act_type = user_pay.getAct_type();
        java.util.Date ctime = user_pay.getCtime();
        java.util.Date utime = user_pay.getUtime();
        java.util.Date etime = user_pay.getEtime();
        int state = user_pay.getState();
        String ip = user_pay.getIp();
        String referer = user_pay.getReferer();
        String subject = user_pay.getSubject();
        String note = user_pay.getNote();
        String car_order_id = user_pay.getCar_order_id();
        String tel = user_pay.getTel();
        String escape_orderids = user_pay.getEscape_orderids();

        this.setId(id);
        this.setOrder_id(order_id);
        this.setTransaction_id(transaction_id);
        this.setType(type);
        this.setVersion_code(version_code);
        this.setSystem_type(system_type);
        this.setReturn_url(return_url);
        this.setUi_id(ui_id);
        this.setUi_nd(ui_nd);
        this.setMoney(money);
        this.setAct_type(act_type);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setEtime(etime);
        this.setState(state);
        this.setIp(ip);
        this.setReferer(referer);
        this.setSubject(subject);
        this.setNote(note);
        this.setCar_order_id(car_order_id);
        this.setTel(tel);
        this.setEscape_orderids(escape_orderids);

    }

    @SuppressWarnings("unused")
    public static void getUser_pay(User_pay user_pay ){
        long id = user_pay.getId();
        String order_id = user_pay.getOrder_id();
        String transaction_id = user_pay.getTransaction_id();
        int type = user_pay.getType();
        int version_code = user_pay.getVersion_code();
        int system_type = user_pay.getSystem_type();
        String return_url = user_pay.getReturn_url();
        long ui_id = user_pay.getUi_id();
        String ui_nd = user_pay.getUi_nd();
        long money = user_pay.getMoney();
        int act_type = user_pay.getAct_type();
        java.util.Date ctime = user_pay.getCtime();
        java.util.Date utime = user_pay.getUtime();
        java.util.Date etime = user_pay.getEtime();
        int state = user_pay.getState();
        String ip = user_pay.getIp();
        String referer = user_pay.getReferer();
        String subject = user_pay.getSubject();
        String note = user_pay.getNote();
        String car_order_id = user_pay.getCar_order_id();
        String tel = user_pay.getTel();
        String escape_orderids = user_pay.getEscape_orderids();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(User_pay user_pay){
        long id = user_pay.getId();
        String order_id = user_pay.getOrder_id();
        String transaction_id = user_pay.getTransaction_id();
        int type = user_pay.getType();
        int version_code = user_pay.getVersion_code();
        int system_type = user_pay.getSystem_type();
        String return_url = user_pay.getReturn_url();
        long ui_id = user_pay.getUi_id();
        String ui_nd = user_pay.getUi_nd();
        long money = user_pay.getMoney();
        int act_type = user_pay.getAct_type();
        java.util.Date ctime = user_pay.getCtime();
        java.util.Date utime = user_pay.getUtime();
        java.util.Date etime = user_pay.getEtime();
        int state = user_pay.getState();
        String ip = user_pay.getIp();
        String referer = user_pay.getReferer();
        String subject = user_pay.getSubject();
        String note = user_pay.getNote();
        String car_order_id = user_pay.getCar_order_id();
        String tel = user_pay.getTel();
        String escape_orderids = user_pay.getEscape_orderids();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("order_id",order_id);
        _ret.put("transaction_id",transaction_id);
        _ret.put("type",type);
        _ret.put("version_code",version_code);
        _ret.put("system_type",system_type);
        _ret.put("return_url",return_url);
        _ret.put("ui_id",ui_id);
        _ret.put("ui_nd",ui_nd);
        _ret.put("money",money);
        _ret.put("act_type",act_type);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("etime",etime);
        _ret.put("state",state);
        _ret.put("ip",ip);
        _ret.put("referer",referer);
        _ret.put("subject",subject);
        _ret.put("note",note);
        _ret.put("car_order_id",car_order_id);
        _ret.put("tel",tel);
        _ret.put("escape_orderids",escape_orderids);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public User_pay clone2(){
        try{
            return (User_pay) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
