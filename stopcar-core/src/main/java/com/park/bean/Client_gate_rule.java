package com.park.bean;

import java.io.*;
import java.util.*;

//client_gate_rule
@SuppressWarnings({"serial"})
public class Client_gate_rule implements Cloneable , Serializable{

    //public static String[] carrays ={"cgr_id","client_ruleid","pi_id","area_code","group_id","type","money","state","str_json","intro","permit_time","ctime","utime","client_loginname","note","is_del"};

    public long cgr_id;//bigint(20)    主键ID
    public String client_ruleid="";//varchar(100)    客户端规则唯一标识（主键）
    public long pi_id;//bigint(20)    停车场主键ID
    public String area_code="";//varchar(30)    停车场区域编码
    public String group_id="";//varchar(100)    客户端所属分组ID
    public int type;//int(11)    类型（0：未指定1：临停2：租赁）
    public int money;//int(11)    默认通用型单价(单位分)
    public int state;//int(11)    是否开启（0：关闭1：开启）
    public String str_json="";//text    其它属性JSON
    public String intro="";//text    APP端显示的描述
    public String permit_time="";//varchar(60)    租赁允许时间段（08：00-19：00）
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String client_loginname="";//varchar(100)    客户端修改人登录帐号
    public String note="";//varchar(100)    备注
    public int is_del;//int(11)    逻辑删除（0：正常1：删除）



    public long getCgr_id(){
        return cgr_id;
    }

    public void setCgr_id(long value){
        this.cgr_id= value;
    }

    public String getClient_ruleid(){
        return client_ruleid;
    }

    public void setClient_ruleid(String value){
    	if(value == null){
           value = "";
        }
        this.client_ruleid= value;
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

    public String getGroup_id(){
        return group_id;
    }

    public void setGroup_id(String value){
    	if(value == null){
           value = "";
        }
        this.group_id= value;
    }

    public int getType(){
        return type;
    }

    public void setType(int value){
        this.type= value;
    }

    public int getMoney(){
        return money;
    }

    public void setMoney(int value){
        this.money= value;
    }

    public int getState(){
        return state;
    }

    public void setState(int value){
        this.state= value;
    }

    public String getStr_json(){
        return str_json;
    }

    public void setStr_json(String value){
    	if(value == null){
           value = "";
        }
        this.str_json= value;
    }

    public String getIntro(){
        return intro;
    }

    public void setIntro(String value){
    	if(value == null){
           value = "";
        }
        this.intro= value;
    }

    public String getPermit_time(){
        return permit_time;
    }

    public void setPermit_time(String value){
    	if(value == null){
           value = "";
        }
        this.permit_time= value;
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

    public String getClient_loginname(){
        return client_loginname;
    }

    public void setClient_loginname(String value){
    	if(value == null){
           value = "";
        }
        this.client_loginname= value;
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

    public int getIs_del(){
        return is_del;
    }

    public void setIs_del(int value){
        this.is_del= value;
    }



    public static Client_gate_rule newClient_gate_rule(long cgr_id, String client_ruleid, long pi_id, String area_code, String group_id, int type, int money, int state, String str_json, String intro, String permit_time, java.util.Date ctime, java.util.Date utime, String client_loginname, String note, int is_del) {
        Client_gate_rule ret = new Client_gate_rule();
        ret.setCgr_id(cgr_id);
        ret.setClient_ruleid(client_ruleid);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setGroup_id(group_id);
        ret.setType(type);
        ret.setMoney(money);
        ret.setState(state);
        ret.setStr_json(str_json);
        ret.setIntro(intro);
        ret.setPermit_time(permit_time);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setClient_loginname(client_loginname);
        ret.setNote(note);
        ret.setIs_del(is_del);
        return ret;    
    }

    public void assignment(Client_gate_rule client_gate_rule) {
        long cgr_id = client_gate_rule.getCgr_id();
        String client_ruleid = client_gate_rule.getClient_ruleid();
        long pi_id = client_gate_rule.getPi_id();
        String area_code = client_gate_rule.getArea_code();
        String group_id = client_gate_rule.getGroup_id();
        int type = client_gate_rule.getType();
        int money = client_gate_rule.getMoney();
        int state = client_gate_rule.getState();
        String str_json = client_gate_rule.getStr_json();
        String intro = client_gate_rule.getIntro();
        String permit_time = client_gate_rule.getPermit_time();
        java.util.Date ctime = client_gate_rule.getCtime();
        java.util.Date utime = client_gate_rule.getUtime();
        String client_loginname = client_gate_rule.getClient_loginname();
        String note = client_gate_rule.getNote();
        int is_del = client_gate_rule.getIs_del();

        this.setCgr_id(cgr_id);
        this.setClient_ruleid(client_ruleid);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setGroup_id(group_id);
        this.setType(type);
        this.setMoney(money);
        this.setState(state);
        this.setStr_json(str_json);
        this.setIntro(intro);
        this.setPermit_time(permit_time);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setClient_loginname(client_loginname);
        this.setNote(note);
        this.setIs_del(is_del);

    }

    @SuppressWarnings("unused")
    public static void getClient_gate_rule(Client_gate_rule client_gate_rule ){
        long cgr_id = client_gate_rule.getCgr_id();
        String client_ruleid = client_gate_rule.getClient_ruleid();
        long pi_id = client_gate_rule.getPi_id();
        String area_code = client_gate_rule.getArea_code();
        String group_id = client_gate_rule.getGroup_id();
        int type = client_gate_rule.getType();
        int money = client_gate_rule.getMoney();
        int state = client_gate_rule.getState();
        String str_json = client_gate_rule.getStr_json();
        String intro = client_gate_rule.getIntro();
        String permit_time = client_gate_rule.getPermit_time();
        java.util.Date ctime = client_gate_rule.getCtime();
        java.util.Date utime = client_gate_rule.getUtime();
        String client_loginname = client_gate_rule.getClient_loginname();
        String note = client_gate_rule.getNote();
        int is_del = client_gate_rule.getIs_del();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Client_gate_rule client_gate_rule){
        long cgr_id = client_gate_rule.getCgr_id();
        String client_ruleid = client_gate_rule.getClient_ruleid();
        long pi_id = client_gate_rule.getPi_id();
        String area_code = client_gate_rule.getArea_code();
        String group_id = client_gate_rule.getGroup_id();
        int type = client_gate_rule.getType();
        int money = client_gate_rule.getMoney();
        int state = client_gate_rule.getState();
        String str_json = client_gate_rule.getStr_json();
        String intro = client_gate_rule.getIntro();
        String permit_time = client_gate_rule.getPermit_time();
        java.util.Date ctime = client_gate_rule.getCtime();
        java.util.Date utime = client_gate_rule.getUtime();
        String client_loginname = client_gate_rule.getClient_loginname();
        String note = client_gate_rule.getNote();
        int is_del = client_gate_rule.getIs_del();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("cgr_id",cgr_id);
        _ret.put("client_ruleid",client_ruleid);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("group_id",group_id);
        _ret.put("type",type);
        _ret.put("money",money);
        _ret.put("state",state);
        _ret.put("str_json",str_json);
        _ret.put("intro",intro);
        _ret.put("permit_time",permit_time);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("client_loginname",client_loginname);
        _ret.put("note",note);
        _ret.put("is_del",is_del);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Client_gate_rule clone2(){
        try{
            return (Client_gate_rule) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
