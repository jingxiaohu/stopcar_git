package com.park.bean;

import java.io.*;
import java.util.*;

//park_userinfo
@SuppressWarnings({"serial"})
public class Park_userinfo implements Cloneable , Serializable{

    //public static String[] carrays ={"pu_id","nd","name","tel","bank_no","bank_name","bank_sub_name","pda_num","money","ctime","utime","loginname","password","note","money_online","money_offline","kickback","privilege_id","error_count","error_date","clearing_type","clearing_percentage","clearing_base"};

    public long pu_id;//bigint(20)    
    public String nd="";//varchar(100)    商户编号
    public String name="";//varchar(100)    PDA老板姓名
    public String tel="";//varchar(11)    PDA老板手机号码
    public String bank_no="";//varchar(60)    PDA老板银行卡卡号
    public String bank_name="";//varchar(150)    PDA老板开户行名称
    public String bank_sub_name="";//varchar(255)    PDA老板开户行支行名称
    public int pda_num;//int(11)    PDA老板拥有的PDA个数
    public int money;//int(11)    商户平台总金额（单位分）
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String loginname="";//varchar(60)    账号
    public String password="";//varchar(60)    密码
    public String note="";//varchar(255)    备注
    public long money_online;//bigint(20)    线上累计金额
    public long money_offline;//bigint(20)    线下累计金额
    public double kickback;//double    打款百分比
    public int privilege_id;//int(11)    商户系统菜单权限映射ID
    public int error_count;//int(11)    登录错误次数
    public String error_date="";//varchar(60)    登录错误发生的日期例如2017-08-07
    public int clearing_type;//int(11)    结算方案(0：不结算1：全额结算2：按基数结算)
    public double clearing_percentage;//double    结算比例
    public int clearing_base;//int(11)    结算基数(整数单位分)



    public long getPu_id(){
        return pu_id;
    }

    public void setPu_id(long value){
        this.pu_id= value;
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

    public int getPda_num(){
        return pda_num;
    }

    public void setPda_num(int value){
        this.pda_num= value;
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

    public String getLoginname(){
        return loginname;
    }

    public void setLoginname(String value){
    	if(value == null){
           value = "";
        }
        this.loginname= value;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String value){
    	if(value == null){
           value = "";
        }
        this.password= value;
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

    public long getMoney_online(){
        return money_online;
    }

    public void setMoney_online(long value){
        this.money_online= value;
    }

    public long getMoney_offline(){
        return money_offline;
    }

    public void setMoney_offline(long value){
        this.money_offline= value;
    }

    public double getKickback(){
        return kickback;
    }

    public void setKickback(double value){
        this.kickback= value;
    }

    public int getPrivilege_id(){
        return privilege_id;
    }

    public void setPrivilege_id(int value){
        this.privilege_id= value;
    }

    public int getError_count(){
        return error_count;
    }

    public void setError_count(int value){
        this.error_count= value;
    }

    public String getError_date(){
        return error_date;
    }

    public void setError_date(String value){
    	if(value == null){
           value = "";
        }
        this.error_date= value;
    }

    public int getClearing_type(){
        return clearing_type;
    }

    public void setClearing_type(int value){
        this.clearing_type= value;
    }

    public double getClearing_percentage(){
        return clearing_percentage;
    }

    public void setClearing_percentage(double value){
        this.clearing_percentage= value;
    }

    public int getClearing_base(){
        return clearing_base;
    }

    public void setClearing_base(int value){
        this.clearing_base= value;
    }



    public static Park_userinfo newPark_userinfo(long pu_id, String nd, String name, String tel, String bank_no, String bank_name, String bank_sub_name, int pda_num, int money, java.util.Date ctime, java.util.Date utime, String loginname, String password, String note, long money_online, long money_offline, double kickback, int privilege_id, int error_count, String error_date, int clearing_type, double clearing_percentage, int clearing_base) {
        Park_userinfo ret = new Park_userinfo();
        ret.setPu_id(pu_id);
        ret.setNd(nd);
        ret.setName(name);
        ret.setTel(tel);
        ret.setBank_no(bank_no);
        ret.setBank_name(bank_name);
        ret.setBank_sub_name(bank_sub_name);
        ret.setPda_num(pda_num);
        ret.setMoney(money);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setLoginname(loginname);
        ret.setPassword(password);
        ret.setNote(note);
        ret.setMoney_online(money_online);
        ret.setMoney_offline(money_offline);
        ret.setKickback(kickback);
        ret.setPrivilege_id(privilege_id);
        ret.setError_count(error_count);
        ret.setError_date(error_date);
        ret.setClearing_type(clearing_type);
        ret.setClearing_percentage(clearing_percentage);
        ret.setClearing_base(clearing_base);
        return ret;    
    }

    public void assignment(Park_userinfo park_userinfo) {
        long pu_id = park_userinfo.getPu_id();
        String nd = park_userinfo.getNd();
        String name = park_userinfo.getName();
        String tel = park_userinfo.getTel();
        String bank_no = park_userinfo.getBank_no();
        String bank_name = park_userinfo.getBank_name();
        String bank_sub_name = park_userinfo.getBank_sub_name();
        int pda_num = park_userinfo.getPda_num();
        int money = park_userinfo.getMoney();
        java.util.Date ctime = park_userinfo.getCtime();
        java.util.Date utime = park_userinfo.getUtime();
        String loginname = park_userinfo.getLoginname();
        String password = park_userinfo.getPassword();
        String note = park_userinfo.getNote();
        long money_online = park_userinfo.getMoney_online();
        long money_offline = park_userinfo.getMoney_offline();
        double kickback = park_userinfo.getKickback();
        int privilege_id = park_userinfo.getPrivilege_id();
        int error_count = park_userinfo.getError_count();
        String error_date = park_userinfo.getError_date();
        int clearing_type = park_userinfo.getClearing_type();
        double clearing_percentage = park_userinfo.getClearing_percentage();
        int clearing_base = park_userinfo.getClearing_base();

        this.setPu_id(pu_id);
        this.setNd(nd);
        this.setName(name);
        this.setTel(tel);
        this.setBank_no(bank_no);
        this.setBank_name(bank_name);
        this.setBank_sub_name(bank_sub_name);
        this.setPda_num(pda_num);
        this.setMoney(money);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setLoginname(loginname);
        this.setPassword(password);
        this.setNote(note);
        this.setMoney_online(money_online);
        this.setMoney_offline(money_offline);
        this.setKickback(kickback);
        this.setPrivilege_id(privilege_id);
        this.setError_count(error_count);
        this.setError_date(error_date);
        this.setClearing_type(clearing_type);
        this.setClearing_percentage(clearing_percentage);
        this.setClearing_base(clearing_base);

    }

    @SuppressWarnings("unused")
    public static void getPark_userinfo(Park_userinfo park_userinfo ){
        long pu_id = park_userinfo.getPu_id();
        String nd = park_userinfo.getNd();
        String name = park_userinfo.getName();
        String tel = park_userinfo.getTel();
        String bank_no = park_userinfo.getBank_no();
        String bank_name = park_userinfo.getBank_name();
        String bank_sub_name = park_userinfo.getBank_sub_name();
        int pda_num = park_userinfo.getPda_num();
        int money = park_userinfo.getMoney();
        java.util.Date ctime = park_userinfo.getCtime();
        java.util.Date utime = park_userinfo.getUtime();
        String loginname = park_userinfo.getLoginname();
        String password = park_userinfo.getPassword();
        String note = park_userinfo.getNote();
        long money_online = park_userinfo.getMoney_online();
        long money_offline = park_userinfo.getMoney_offline();
        double kickback = park_userinfo.getKickback();
        int privilege_id = park_userinfo.getPrivilege_id();
        int error_count = park_userinfo.getError_count();
        String error_date = park_userinfo.getError_date();
        int clearing_type = park_userinfo.getClearing_type();
        double clearing_percentage = park_userinfo.getClearing_percentage();
        int clearing_base = park_userinfo.getClearing_base();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Park_userinfo park_userinfo){
        long pu_id = park_userinfo.getPu_id();
        String nd = park_userinfo.getNd();
        String name = park_userinfo.getName();
        String tel = park_userinfo.getTel();
        String bank_no = park_userinfo.getBank_no();
        String bank_name = park_userinfo.getBank_name();
        String bank_sub_name = park_userinfo.getBank_sub_name();
        int pda_num = park_userinfo.getPda_num();
        int money = park_userinfo.getMoney();
        java.util.Date ctime = park_userinfo.getCtime();
        java.util.Date utime = park_userinfo.getUtime();
        String loginname = park_userinfo.getLoginname();
        String password = park_userinfo.getPassword();
        String note = park_userinfo.getNote();
        long money_online = park_userinfo.getMoney_online();
        long money_offline = park_userinfo.getMoney_offline();
        double kickback = park_userinfo.getKickback();
        int privilege_id = park_userinfo.getPrivilege_id();
        int error_count = park_userinfo.getError_count();
        String error_date = park_userinfo.getError_date();
        int clearing_type = park_userinfo.getClearing_type();
        double clearing_percentage = park_userinfo.getClearing_percentage();
        int clearing_base = park_userinfo.getClearing_base();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("pu_id",pu_id);
        _ret.put("nd",nd);
        _ret.put("name",name);
        _ret.put("tel",tel);
        _ret.put("bank_no",bank_no);
        _ret.put("bank_name",bank_name);
        _ret.put("bank_sub_name",bank_sub_name);
        _ret.put("pda_num",pda_num);
        _ret.put("money",money);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("loginname",loginname);
        _ret.put("password",password);
        _ret.put("note",note);
        _ret.put("money_online",money_online);
        _ret.put("money_offline",money_offline);
        _ret.put("kickback",kickback);
        _ret.put("privilege_id",privilege_id);
        _ret.put("error_count",error_count);
        _ret.put("error_date",error_date);
        _ret.put("clearing_type",clearing_type);
        _ret.put("clearing_percentage",clearing_percentage);
        _ret.put("clearing_base",clearing_base);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Park_userinfo clone2(){
        try{
            return (Park_userinfo) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
