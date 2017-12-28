package com.park.bean;

import java.io.*;
import java.util.*;

//user_vc_act
@SuppressWarnings({"serial"})
public class User_vc_act implements Cloneable , Serializable{

    //public static String[] carrays ={"id","ui_id","order_id","order_type","act_type","money","ctime","state","is_add","note","act_name","tel","ui_nd","pay_source","discount_money","discount_type","upc_id"};

    public long id;//bigint(20)    主键ID
    public long ui_id;//bigint(20)    用户ID
    public String order_id="";//varchar(65)    订单ID
    public int order_type;//int(11)    下单类型0:普通下单1：预约下单2：租赁包月订单3:租赁续租订单
    public int act_type;//int(11)    用户行为0：订单支付1：充值2:系统返还
    public int money;//int(11)    交易金额（单位分）
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public int state;//int(11)    处理状态0：未处理1：已处理
    public int is_add;//int(11)    增加还是减少0：减少1：增加
    public String note="";//varchar(100)    备注
    public String act_name="";//varchar(100)    事件名称
    public String tel="";//varchar(100)    用户电话号码
    public String ui_nd="";//varchar(100)    用户唯一标识符
    public int pay_source;//int(11)    支付类型0：现金1:支付宝2：微信3：银联4：钱包5:龙支付6:ETC快捷支付
    public int discount_money;//int(11)    抵扣优惠金额（单位分）
    public int discount_type;//int(11)    优惠券类型0:金额券1：折扣券
    public long upc_id;//bigint(20)    用户优惠券表主键ID



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

    public int getAct_type(){
        return act_type;
    }

    public void setAct_type(int value){
        this.act_type= value;
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

    public int getIs_add(){
        return is_add;
    }

    public void setIs_add(int value){
        this.is_add= value;
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

    public String getAct_name(){
        return act_name;
    }

    public void setAct_name(String value){
    	if(value == null){
           value = "";
        }
        this.act_name= value;
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

    public String getUi_nd(){
        return ui_nd;
    }

    public void setUi_nd(String value){
    	if(value == null){
           value = "";
        }
        this.ui_nd= value;
    }

    public int getPay_source(){
        return pay_source;
    }

    public void setPay_source(int value){
        this.pay_source= value;
    }

    public int getDiscount_money(){
        return discount_money;
    }

    public void setDiscount_money(int value){
        this.discount_money= value;
    }

    public int getDiscount_type(){
        return discount_type;
    }

    public void setDiscount_type(int value){
        this.discount_type= value;
    }

    public long getUpc_id(){
        return upc_id;
    }

    public void setUpc_id(long value){
        this.upc_id= value;
    }



    public static User_vc_act newUser_vc_act(long id, long ui_id, String order_id, int order_type, int act_type, int money, java.util.Date ctime, int state, int is_add, String note, String act_name, String tel, String ui_nd, int pay_source, int discount_money, int discount_type, long upc_id) {
        User_vc_act ret = new User_vc_act();
        ret.setId(id);
        ret.setUi_id(ui_id);
        ret.setOrder_id(order_id);
        ret.setOrder_type(order_type);
        ret.setAct_type(act_type);
        ret.setMoney(money);
        ret.setCtime(ctime);
        ret.setState(state);
        ret.setIs_add(is_add);
        ret.setNote(note);
        ret.setAct_name(act_name);
        ret.setTel(tel);
        ret.setUi_nd(ui_nd);
        ret.setPay_source(pay_source);
        ret.setDiscount_money(discount_money);
        ret.setDiscount_type(discount_type);
        ret.setUpc_id(upc_id);
        return ret;    
    }

    public void assignment(User_vc_act user_vc_act) {
        long id = user_vc_act.getId();
        long ui_id = user_vc_act.getUi_id();
        String order_id = user_vc_act.getOrder_id();
        int order_type = user_vc_act.getOrder_type();
        int act_type = user_vc_act.getAct_type();
        int money = user_vc_act.getMoney();
        java.util.Date ctime = user_vc_act.getCtime();
        int state = user_vc_act.getState();
        int is_add = user_vc_act.getIs_add();
        String note = user_vc_act.getNote();
        String act_name = user_vc_act.getAct_name();
        String tel = user_vc_act.getTel();
        String ui_nd = user_vc_act.getUi_nd();
        int pay_source = user_vc_act.getPay_source();
        int discount_money = user_vc_act.getDiscount_money();
        int discount_type = user_vc_act.getDiscount_type();
        long upc_id = user_vc_act.getUpc_id();

        this.setId(id);
        this.setUi_id(ui_id);
        this.setOrder_id(order_id);
        this.setOrder_type(order_type);
        this.setAct_type(act_type);
        this.setMoney(money);
        this.setCtime(ctime);
        this.setState(state);
        this.setIs_add(is_add);
        this.setNote(note);
        this.setAct_name(act_name);
        this.setTel(tel);
        this.setUi_nd(ui_nd);
        this.setPay_source(pay_source);
        this.setDiscount_money(discount_money);
        this.setDiscount_type(discount_type);
        this.setUpc_id(upc_id);

    }

    @SuppressWarnings("unused")
    public static void getUser_vc_act(User_vc_act user_vc_act ){
        long id = user_vc_act.getId();
        long ui_id = user_vc_act.getUi_id();
        String order_id = user_vc_act.getOrder_id();
        int order_type = user_vc_act.getOrder_type();
        int act_type = user_vc_act.getAct_type();
        int money = user_vc_act.getMoney();
        java.util.Date ctime = user_vc_act.getCtime();
        int state = user_vc_act.getState();
        int is_add = user_vc_act.getIs_add();
        String note = user_vc_act.getNote();
        String act_name = user_vc_act.getAct_name();
        String tel = user_vc_act.getTel();
        String ui_nd = user_vc_act.getUi_nd();
        int pay_source = user_vc_act.getPay_source();
        int discount_money = user_vc_act.getDiscount_money();
        int discount_type = user_vc_act.getDiscount_type();
        long upc_id = user_vc_act.getUpc_id();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(User_vc_act user_vc_act){
        long id = user_vc_act.getId();
        long ui_id = user_vc_act.getUi_id();
        String order_id = user_vc_act.getOrder_id();
        int order_type = user_vc_act.getOrder_type();
        int act_type = user_vc_act.getAct_type();
        int money = user_vc_act.getMoney();
        java.util.Date ctime = user_vc_act.getCtime();
        int state = user_vc_act.getState();
        int is_add = user_vc_act.getIs_add();
        String note = user_vc_act.getNote();
        String act_name = user_vc_act.getAct_name();
        String tel = user_vc_act.getTel();
        String ui_nd = user_vc_act.getUi_nd();
        int pay_source = user_vc_act.getPay_source();
        int discount_money = user_vc_act.getDiscount_money();
        int discount_type = user_vc_act.getDiscount_type();
        long upc_id = user_vc_act.getUpc_id();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("ui_id",ui_id);
        _ret.put("order_id",order_id);
        _ret.put("order_type",order_type);
        _ret.put("act_type",act_type);
        _ret.put("money",money);
        _ret.put("ctime",ctime);
        _ret.put("state",state);
        _ret.put("is_add",is_add);
        _ret.put("note",note);
        _ret.put("act_name",act_name);
        _ret.put("tel",tel);
        _ret.put("ui_nd",ui_nd);
        _ret.put("pay_source",pay_source);
        _ret.put("discount_money",discount_money);
        _ret.put("discount_type",discount_type);
        _ret.put("upc_id",upc_id);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public User_vc_act clone2(){
        try{
            return (User_vc_act) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
