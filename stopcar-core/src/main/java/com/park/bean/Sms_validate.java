package com.park.bean;

import java.io.*;
import java.util.*;

//sms_validate
@SuppressWarnings({"serial"})
public class Sms_validate implements Cloneable , Serializable{

    //public static String[] carrays ={"id","v_tel","v_code","v_list","v_class","v_time","v_time_str","note"};

    public long id;//bigint(20)    主键ID
    public String v_tel="";//varchar(40)    手机号
    public String v_code="";//varchar(10)    验证码6位数字
    public String v_list="";//varchar(32)    随机码v_code的MD5
    public String v_class="";//varchar(40)    什么类型的短信验证码1：注册2：重置密码3:修改手机
    public long v_time;//bigint(20)    验证时间
    public String v_time_str="";//varchar(40)    时间字符串
    public String note="";//varchar(255)    



    public long getId(){
        return id;
    }

    public void setId(long value){
        this.id= value;
    }

    public String getV_tel(){
        return v_tel;
    }

    public void setV_tel(String value){
    	if(value == null){
           value = "";
        }
        this.v_tel= value;
    }

    public String getV_code(){
        return v_code;
    }

    public void setV_code(String value){
    	if(value == null){
           value = "";
        }
        this.v_code= value;
    }

    public String getV_list(){
        return v_list;
    }

    public void setV_list(String value){
    	if(value == null){
           value = "";
        }
        this.v_list= value;
    }

    public String getV_class(){
        return v_class;
    }

    public void setV_class(String value){
    	if(value == null){
           value = "";
        }
        this.v_class= value;
    }

    public long getV_time(){
        return v_time;
    }

    public void setV_time(long value){
        this.v_time= value;
    }

    public String getV_time_str(){
        return v_time_str;
    }

    public void setV_time_str(String value){
    	if(value == null){
           value = "";
        }
        this.v_time_str= value;
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



    public static Sms_validate newSms_validate(long id, String v_tel, String v_code, String v_list, String v_class, long v_time, String v_time_str, String note) {
        Sms_validate ret = new Sms_validate();
        ret.setId(id);
        ret.setV_tel(v_tel);
        ret.setV_code(v_code);
        ret.setV_list(v_list);
        ret.setV_class(v_class);
        ret.setV_time(v_time);
        ret.setV_time_str(v_time_str);
        ret.setNote(note);
        return ret;    
    }

    public void assignment(Sms_validate sms_validate) {
        long id = sms_validate.getId();
        String v_tel = sms_validate.getV_tel();
        String v_code = sms_validate.getV_code();
        String v_list = sms_validate.getV_list();
        String v_class = sms_validate.getV_class();
        long v_time = sms_validate.getV_time();
        String v_time_str = sms_validate.getV_time_str();
        String note = sms_validate.getNote();

        this.setId(id);
        this.setV_tel(v_tel);
        this.setV_code(v_code);
        this.setV_list(v_list);
        this.setV_class(v_class);
        this.setV_time(v_time);
        this.setV_time_str(v_time_str);
        this.setNote(note);

    }

    @SuppressWarnings("unused")
    public static void getSms_validate(Sms_validate sms_validate ){
        long id = sms_validate.getId();
        String v_tel = sms_validate.getV_tel();
        String v_code = sms_validate.getV_code();
        String v_list = sms_validate.getV_list();
        String v_class = sms_validate.getV_class();
        long v_time = sms_validate.getV_time();
        String v_time_str = sms_validate.getV_time_str();
        String note = sms_validate.getNote();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Sms_validate sms_validate){
        long id = sms_validate.getId();
        String v_tel = sms_validate.getV_tel();
        String v_code = sms_validate.getV_code();
        String v_list = sms_validate.getV_list();
        String v_class = sms_validate.getV_class();
        long v_time = sms_validate.getV_time();
        String v_time_str = sms_validate.getV_time_str();
        String note = sms_validate.getNote();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("v_tel",v_tel);
        _ret.put("v_code",v_code);
        _ret.put("v_list",v_list);
        _ret.put("v_class",v_class);
        _ret.put("v_time",v_time);
        _ret.put("v_time_str",v_time_str);
        _ret.put("note",note);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Sms_validate clone2(){
        try{
            return (Sms_validate) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
