package com.park.bean;

import java.io.*;
import java.util.*;

//user_cash_apply
@SuppressWarnings({"serial"})
public class User_cash_apply implements Cloneable , Serializable{

    //public static String[] carrays ={"id","pu_id","money","ctime","utime","note","state","bank_no","bank_name","bank_sub_name","name","tel"};

    public long id;//bigint(20)    
    public long pu_id;//bigint(20)    商户主键ID
    public int money;//int(11)    提现金额
    public java.util.Date ctime=new java.util.Date();//datetime    提现申请时间
    public java.util.Date utime=new java.util.Date();//datetime    打款时间
    public String note="";//varchar(255)    备注
    public int state;//int(11)    提现状态0：未打款1：已打款2：打款失败
    public String bank_no="";//varchar(80)    商户银行卡号
    public String bank_name="";//varchar(80)    开户行名称
    public String bank_sub_name="";//varchar(200)    开户行支行详细名称
    public String name="";//varchar(100)    商户姓名
    public String tel="";//varchar(11)    商户手机号码



    public long getId(){
        return id;
    }

    public void setId(long value){
        this.id= value;
    }

    public long getPu_id(){
        return pu_id;
    }

    public void setPu_id(long value){
        this.pu_id= value;
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

    public int getState(){
        return state;
    }

    public void setState(int value){
        this.state= value;
    }

    public String getBank_no(){
        return bank_no;
    }

    public void setBank_no(String value){
    	if(value == null){
           value = "";
        }
        this.bank_no= value;
    }

    public String getBank_name(){
        return bank_name;
    }

    public void setBank_name(String value){
    	if(value == null){
           value = "";
        }
        this.bank_name= value;
    }

    public String getBank_sub_name(){
        return bank_sub_name;
    }

    public void setBank_sub_name(String value){
    	if(value == null){
           value = "";
        }
        this.bank_sub_name= value;
    }

    public String getName(){
        return name;
    }

    public void setName(String value){
    	if(value == null){
           value = "";
        }
        this.name= value;
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



    public static User_cash_apply newUser_cash_apply(long id, long pu_id, int money, java.util.Date ctime, java.util.Date utime, String note, int state, String bank_no, String bank_name, String bank_sub_name, String name, String tel) {
        User_cash_apply ret = new User_cash_apply();
        ret.setId(id);
        ret.setPu_id(pu_id);
        ret.setMoney(money);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setNote(note);
        ret.setState(state);
        ret.setBank_no(bank_no);
        ret.setBank_name(bank_name);
        ret.setBank_sub_name(bank_sub_name);
        ret.setName(name);
        ret.setTel(tel);
        return ret;    
    }

    public void assignment(User_cash_apply user_cash_apply) {
        long id = user_cash_apply.getId();
        long pu_id = user_cash_apply.getPu_id();
        int money = user_cash_apply.getMoney();
        java.util.Date ctime = user_cash_apply.getCtime();
        java.util.Date utime = user_cash_apply.getUtime();
        String note = user_cash_apply.getNote();
        int state = user_cash_apply.getState();
        String bank_no = user_cash_apply.getBank_no();
        String bank_name = user_cash_apply.getBank_name();
        String bank_sub_name = user_cash_apply.getBank_sub_name();
        String name = user_cash_apply.getName();
        String tel = user_cash_apply.getTel();

        this.setId(id);
        this.setPu_id(pu_id);
        this.setMoney(money);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setNote(note);
        this.setState(state);
        this.setBank_no(bank_no);
        this.setBank_name(bank_name);
        this.setBank_sub_name(bank_sub_name);
        this.setName(name);
        this.setTel(tel);

    }

    @SuppressWarnings("unused")
    public static void getUser_cash_apply(User_cash_apply user_cash_apply ){
        long id = user_cash_apply.getId();
        long pu_id = user_cash_apply.getPu_id();
        int money = user_cash_apply.getMoney();
        java.util.Date ctime = user_cash_apply.getCtime();
        java.util.Date utime = user_cash_apply.getUtime();
        String note = user_cash_apply.getNote();
        int state = user_cash_apply.getState();
        String bank_no = user_cash_apply.getBank_no();
        String bank_name = user_cash_apply.getBank_name();
        String bank_sub_name = user_cash_apply.getBank_sub_name();
        String name = user_cash_apply.getName();
        String tel = user_cash_apply.getTel();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(User_cash_apply user_cash_apply){
        long id = user_cash_apply.getId();
        long pu_id = user_cash_apply.getPu_id();
        int money = user_cash_apply.getMoney();
        java.util.Date ctime = user_cash_apply.getCtime();
        java.util.Date utime = user_cash_apply.getUtime();
        String note = user_cash_apply.getNote();
        int state = user_cash_apply.getState();
        String bank_no = user_cash_apply.getBank_no();
        String bank_name = user_cash_apply.getBank_name();
        String bank_sub_name = user_cash_apply.getBank_sub_name();
        String name = user_cash_apply.getName();
        String tel = user_cash_apply.getTel();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("pu_id",pu_id);
        _ret.put("money",money);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("note",note);
        _ret.put("state",state);
        _ret.put("bank_no",bank_no);
        _ret.put("bank_name",bank_name);
        _ret.put("bank_sub_name",bank_sub_name);
        _ret.put("name",name);
        _ret.put("tel",tel);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public User_cash_apply clone2(){
        try{
            return (User_cash_apply) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
