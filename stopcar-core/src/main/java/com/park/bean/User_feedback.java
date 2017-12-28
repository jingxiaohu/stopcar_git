package com.park.bean;

import java.io.*;
import java.util.*;

//user_feedback
@SuppressWarnings({"serial"})
public class User_feedback implements Cloneable , Serializable{

    //public static String[] carrays ={"id","ui_id","content","ctime","utime","note","type","pi_id","area_code","pi_name","pda_id"};

    public long id;//bigint(20)    主键ID
    public long ui_id;//bigint(20)    用户主键ID
    public String content="";//varchar(255)    反馈信息
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String note="";//varchar(100)    备注
    public int type;//int(11)    反馈来源（0：app用户反馈1：PDA管理人反馈2：道闸管理人反馈）
    public long pi_id;//bigint(20)    停车场主键ID
    public String area_code="";//varchar(30)    地址区域编码
    public String pi_name="";//varchar(100)    停车场名称
    public long pda_id;//bigint(20)    PDA设备表的主键ID



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

    public String getContent(){
        return content;
    }

    public void setContent(String value){
    	if(value == null){
           value = "";
        }
        this.content= value;
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

    public int getType(){
        return type;
    }

    public void setType(int value){
        this.type= value;
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

    public long getPda_id(){
        return pda_id;
    }

    public void setPda_id(long value){
        this.pda_id= value;
    }



    public static User_feedback newUser_feedback(long id, long ui_id, String content, java.util.Date ctime, java.util.Date utime, String note, int type, long pi_id, String area_code, String pi_name, long pda_id) {
        User_feedback ret = new User_feedback();
        ret.setId(id);
        ret.setUi_id(ui_id);
        ret.setContent(content);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setNote(note);
        ret.setType(type);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setPi_name(pi_name);
        ret.setPda_id(pda_id);
        return ret;    
    }

    public void assignment(User_feedback user_feedback) {
        long id = user_feedback.getId();
        long ui_id = user_feedback.getUi_id();
        String content = user_feedback.getContent();
        java.util.Date ctime = user_feedback.getCtime();
        java.util.Date utime = user_feedback.getUtime();
        String note = user_feedback.getNote();
        int type = user_feedback.getType();
        long pi_id = user_feedback.getPi_id();
        String area_code = user_feedback.getArea_code();
        String pi_name = user_feedback.getPi_name();
        long pda_id = user_feedback.getPda_id();

        this.setId(id);
        this.setUi_id(ui_id);
        this.setContent(content);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setNote(note);
        this.setType(type);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setPi_name(pi_name);
        this.setPda_id(pda_id);

    }

    @SuppressWarnings("unused")
    public static void getUser_feedback(User_feedback user_feedback ){
        long id = user_feedback.getId();
        long ui_id = user_feedback.getUi_id();
        String content = user_feedback.getContent();
        java.util.Date ctime = user_feedback.getCtime();
        java.util.Date utime = user_feedback.getUtime();
        String note = user_feedback.getNote();
        int type = user_feedback.getType();
        long pi_id = user_feedback.getPi_id();
        String area_code = user_feedback.getArea_code();
        String pi_name = user_feedback.getPi_name();
        long pda_id = user_feedback.getPda_id();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(User_feedback user_feedback){
        long id = user_feedback.getId();
        long ui_id = user_feedback.getUi_id();
        String content = user_feedback.getContent();
        java.util.Date ctime = user_feedback.getCtime();
        java.util.Date utime = user_feedback.getUtime();
        String note = user_feedback.getNote();
        int type = user_feedback.getType();
        long pi_id = user_feedback.getPi_id();
        String area_code = user_feedback.getArea_code();
        String pi_name = user_feedback.getPi_name();
        long pda_id = user_feedback.getPda_id();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("ui_id",ui_id);
        _ret.put("content",content);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("note",note);
        _ret.put("type",type);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("pi_name",pi_name);
        _ret.put("pda_id",pda_id);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public User_feedback clone2(){
        try{
            return (User_feedback) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
