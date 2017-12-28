package com.park.bean;

import java.io.*;
import java.util.*;

//sms_running
@SuppressWarnings({"serial"})
public class Sms_running implements Cloneable , Serializable{

    //public static String[] carrays ={"Id","sms_tel","sms_content","sms_stat","sms_type","sms_user","sms_user_name","server_state","sms_date","note"};

    public int Id;//int(11)    
    public String sms_tel="";//varchar(255)    
    public String sms_content="";//text    
    public int sms_stat;//int(11)    失败还是成功1-成功，0失败
    public int sms_type;//int(11)    1-自定义短信0-程序员自定义短信
    public String sms_user="";//varchar(20)    发送用户
    public String sms_user_name="";//varchar(40)    发送用户
    public String server_state="";//varchar(255)    服务端的状态码
    public String sms_date="";//varchar(40)    
    public String note="";//varchar(255)    



    public int getId(){
        return Id;
    }

    public void setId(int value){
        this.Id= value;
    }

    public String getSms_tel(){
        return sms_tel;
    }

    public void setSms_tel(String value){
    	if(value == null){
           value = "";
        }
        this.sms_tel= value;
    }

    public String getSms_content(){
        return sms_content;
    }

    public void setSms_content(String value){
    	if(value == null){
           value = "";
        }
        this.sms_content= value;
    }

    public int getSms_stat(){
        return sms_stat;
    }

    public void setSms_stat(int value){
        this.sms_stat= value;
    }

    public int getSms_type(){
        return sms_type;
    }

    public void setSms_type(int value){
        this.sms_type= value;
    }

    public String getSms_user(){
        return sms_user;
    }

    public void setSms_user(String value){
    	if(value == null){
           value = "";
        }
        this.sms_user= value;
    }

    public String getSms_user_name(){
        return sms_user_name;
    }

    public void setSms_user_name(String value){
    	if(value == null){
           value = "";
        }
        this.sms_user_name= value;
    }

    public String getServer_state(){
        return server_state;
    }

    public void setServer_state(String value){
    	if(value == null){
           value = "";
        }
        this.server_state= value;
    }

    public String getSms_date(){
        return sms_date;
    }

    public void setSms_date(String value){
    	if(value == null){
           value = "";
        }
        this.sms_date= value;
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



    public static Sms_running newSms_running(int Id, String sms_tel, String sms_content, int sms_stat, int sms_type, String sms_user, String sms_user_name, String server_state, String sms_date, String note) {
        Sms_running ret = new Sms_running();
        ret.setId(Id);
        ret.setSms_tel(sms_tel);
        ret.setSms_content(sms_content);
        ret.setSms_stat(sms_stat);
        ret.setSms_type(sms_type);
        ret.setSms_user(sms_user);
        ret.setSms_user_name(sms_user_name);
        ret.setServer_state(server_state);
        ret.setSms_date(sms_date);
        ret.setNote(note);
        return ret;    
    }

    public void assignment(Sms_running sms_running) {
        int Id = sms_running.getId();
        String sms_tel = sms_running.getSms_tel();
        String sms_content = sms_running.getSms_content();
        int sms_stat = sms_running.getSms_stat();
        int sms_type = sms_running.getSms_type();
        String sms_user = sms_running.getSms_user();
        String sms_user_name = sms_running.getSms_user_name();
        String server_state = sms_running.getServer_state();
        String sms_date = sms_running.getSms_date();
        String note = sms_running.getNote();

        this.setId(Id);
        this.setSms_tel(sms_tel);
        this.setSms_content(sms_content);
        this.setSms_stat(sms_stat);
        this.setSms_type(sms_type);
        this.setSms_user(sms_user);
        this.setSms_user_name(sms_user_name);
        this.setServer_state(server_state);
        this.setSms_date(sms_date);
        this.setNote(note);

    }

    @SuppressWarnings("unused")
    public static void getSms_running(Sms_running sms_running ){
        int Id = sms_running.getId();
        String sms_tel = sms_running.getSms_tel();
        String sms_content = sms_running.getSms_content();
        int sms_stat = sms_running.getSms_stat();
        int sms_type = sms_running.getSms_type();
        String sms_user = sms_running.getSms_user();
        String sms_user_name = sms_running.getSms_user_name();
        String server_state = sms_running.getServer_state();
        String sms_date = sms_running.getSms_date();
        String note = sms_running.getNote();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Sms_running sms_running){
        int Id = sms_running.getId();
        String sms_tel = sms_running.getSms_tel();
        String sms_content = sms_running.getSms_content();
        int sms_stat = sms_running.getSms_stat();
        int sms_type = sms_running.getSms_type();
        String sms_user = sms_running.getSms_user();
        String sms_user_name = sms_running.getSms_user_name();
        String server_state = sms_running.getServer_state();
        String sms_date = sms_running.getSms_date();
        String note = sms_running.getNote();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("Id",Id);
        _ret.put("sms_tel",sms_tel);
        _ret.put("sms_content",sms_content);
        _ret.put("sms_stat",sms_stat);
        _ret.put("sms_type",sms_type);
        _ret.put("sms_user",sms_user);
        _ret.put("sms_user_name",sms_user_name);
        _ret.put("server_state",server_state);
        _ret.put("sms_date",sms_date);
        _ret.put("note",note);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Sms_running clone2(){
        try{
            return (Sms_running) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
