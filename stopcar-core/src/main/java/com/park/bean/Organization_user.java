package com.park.bean;

import java.io.*;
import java.util.*;

//organization_user
@SuppressWarnings({"serial"})
public class Organization_user implements Cloneable , Serializable{

    //public static String[] carrays ={"id","user_id","name","password","privilege_id","status","gender","create_time","update_time","user_phone","user_email","error_count","error_date"};

    public int id;//int(11)    
    public String user_id="";//varchar(20)    4-10位的数字和字母(不区分大小写)，创建时用户自己填写，需做唯一性校验，用作登陆用户名
    public String name="";//varchar(40)    用户的昵称
    public String password="";//varchar(100)    
    public int privilege_id;//int(11)    指向权限表
    public byte status;//tinyint(4)    用户的状态，如可用，不可用
    public byte gender;//tinyint(4)    用户的性别
    public long create_time;//bigint(20)    此用户被创建的时间
    public long update_time;//bigint(20)    
    public String user_phone="";//varchar(20)    电话号码
    public String user_email="";//varchar(50)    邮件
    public int error_count;//int(11)    登录错误次数
    public String error_date="";//varchar(60)    登录错误发生的日期例如2017-08-07



    public int getId(){
        return id;
    }

    public void setId(int value){
        this.id= value;
    }

    public String getUser_id(){
        return user_id;
    }

    public void setUser_id(String value){
    	if(value == null){
           value = "";
        }
        this.user_id= value;
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

    public String getPassword(){
        return password;
    }

    public void setPassword(String value){
    	if(value == null){
           value = "";
        }
        this.password= value;
    }

    public int getPrivilege_id(){
        return privilege_id;
    }

    public void setPrivilege_id(int value){
        this.privilege_id= value;
    }

    public byte getStatus(){
        return status;
    }

    public void setStatus(byte value){
        this.status= value;
    }

    public byte getGender(){
        return gender;
    }

    public void setGender(byte value){
        this.gender= value;
    }

    public long getCreate_time(){
        return create_time;
    }

    public void setCreate_time(long value){
        this.create_time= value;
    }

    public long getUpdate_time(){
        return update_time;
    }

    public void setUpdate_time(long value){
        this.update_time= value;
    }

    public String getUser_phone(){
        return user_phone;
    }

    public void setUser_phone(String value){
    	if(value == null){
           value = "";
        }
        this.user_phone= value;
    }

    public String getUser_email(){
        return user_email;
    }

    public void setUser_email(String value){
    	if(value == null){
           value = "";
        }
        this.user_email= value;
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



    public static Organization_user newOrganization_user(int id, String user_id, String name, String password, int privilege_id, byte status, byte gender, long create_time, long update_time, String user_phone, String user_email, int error_count, String error_date) {
        Organization_user ret = new Organization_user();
        ret.setId(id);
        ret.setUser_id(user_id);
        ret.setName(name);
        ret.setPassword(password);
        ret.setPrivilege_id(privilege_id);
        ret.setStatus(status);
        ret.setGender(gender);
        ret.setCreate_time(create_time);
        ret.setUpdate_time(update_time);
        ret.setUser_phone(user_phone);
        ret.setUser_email(user_email);
        ret.setError_count(error_count);
        ret.setError_date(error_date);
        return ret;    
    }

    public void assignment(Organization_user organization_user) {
        int id = organization_user.getId();
        String user_id = organization_user.getUser_id();
        String name = organization_user.getName();
        String password = organization_user.getPassword();
        int privilege_id = organization_user.getPrivilege_id();
        byte status = organization_user.getStatus();
        byte gender = organization_user.getGender();
        long create_time = organization_user.getCreate_time();
        long update_time = organization_user.getUpdate_time();
        String user_phone = organization_user.getUser_phone();
        String user_email = organization_user.getUser_email();
        int error_count = organization_user.getError_count();
        String error_date = organization_user.getError_date();

        this.setId(id);
        this.setUser_id(user_id);
        this.setName(name);
        this.setPassword(password);
        this.setPrivilege_id(privilege_id);
        this.setStatus(status);
        this.setGender(gender);
        this.setCreate_time(create_time);
        this.setUpdate_time(update_time);
        this.setUser_phone(user_phone);
        this.setUser_email(user_email);
        this.setError_count(error_count);
        this.setError_date(error_date);

    }

    @SuppressWarnings("unused")
    public static void getOrganization_user(Organization_user organization_user ){
        int id = organization_user.getId();
        String user_id = organization_user.getUser_id();
        String name = organization_user.getName();
        String password = organization_user.getPassword();
        int privilege_id = organization_user.getPrivilege_id();
        byte status = organization_user.getStatus();
        byte gender = organization_user.getGender();
        long create_time = organization_user.getCreate_time();
        long update_time = organization_user.getUpdate_time();
        String user_phone = organization_user.getUser_phone();
        String user_email = organization_user.getUser_email();
        int error_count = organization_user.getError_count();
        String error_date = organization_user.getError_date();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Organization_user organization_user){
        int id = organization_user.getId();
        String user_id = organization_user.getUser_id();
        String name = organization_user.getName();
        String password = organization_user.getPassword();
        int privilege_id = organization_user.getPrivilege_id();
        byte status = organization_user.getStatus();
        byte gender = organization_user.getGender();
        long create_time = organization_user.getCreate_time();
        long update_time = organization_user.getUpdate_time();
        String user_phone = organization_user.getUser_phone();
        String user_email = organization_user.getUser_email();
        int error_count = organization_user.getError_count();
        String error_date = organization_user.getError_date();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("user_id",user_id);
        _ret.put("name",name);
        _ret.put("password",password);
        _ret.put("privilege_id",privilege_id);
        _ret.put("status",status);
        _ret.put("gender",gender);
        _ret.put("create_time",create_time);
        _ret.put("update_time",update_time);
        _ret.put("user_phone",user_phone);
        _ret.put("user_email",user_email);
        _ret.put("error_count",error_count);
        _ret.put("error_date",error_date);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Organization_user clone2(){
        try{
            return (Organization_user) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
