package com.park.bean;

import java.io.*;
import java.util.*;

//app_version
@SuppressWarnings({"serial"})
public class App_version implements Cloneable , Serializable{

    //public static String[] carrays ={"id","cav_version","cav_version_external","cav_version_code","ctime","content","cav_md5","android_url","ios_url","is_forced","note","type"};

    public long id;//bigint(20)    主键ID
    public String cav_version="";//varchar(100)    渠道版本
    public String cav_version_external="";//varchar(100)    渠道外部版本
    public int cav_version_code;//int(11)    渠道版本内部编号
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public String content="";//text    版本更新内容
    public String cav_md5="";//varchar(32)    版本MD5文件效验码
    public String android_url="";//varchar(500)    Android_app版本升级包URL地址
    public String ios_url="";//varchar(500)    Ios_app版本升级包URL地址
    public int is_forced;//int(11)    是否强制更新0：不强制更新1：强制更新
    public String note="";//varchar(100)    备注
    public int type;//int(11)    类型0：Android手机APP1：ios手机APP2：PDA



    public long getId(){
        return id;
    }

    public void setId(long value){
        this.id= value;
    }

    public String getCav_version(){
        return cav_version;
    }

    public void setCav_version(String value){
    	if(value == null){
           value = "";
        }
        this.cav_version= value;
    }

    public String getCav_version_external(){
        return cav_version_external;
    }

    public void setCav_version_external(String value){
    	if(value == null){
           value = "";
        }
        this.cav_version_external= value;
    }

    public int getCav_version_code(){
        return cav_version_code;
    }

    public void setCav_version_code(int value){
        this.cav_version_code= value;
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

    public String getContent(){
        return content;
    }

    public void setContent(String value){
    	if(value == null){
           value = "";
        }
        this.content= value;
    }

    public String getCav_md5(){
        return cav_md5;
    }

    public void setCav_md5(String value){
    	if(value == null){
           value = "";
        }
        this.cav_md5= value;
    }

    public String getAndroid_url(){
        return android_url;
    }

    public void setAndroid_url(String value){
    	if(value == null){
           value = "";
        }
        this.android_url= value;
    }

    public String getIos_url(){
        return ios_url;
    }

    public void setIos_url(String value){
    	if(value == null){
           value = "";
        }
        this.ios_url= value;
    }

    public int getIs_forced(){
        return is_forced;
    }

    public void setIs_forced(int value){
        this.is_forced= value;
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



    public static App_version newApp_version(long id, String cav_version, String cav_version_external, int cav_version_code, java.util.Date ctime, String content, String cav_md5, String android_url, String ios_url, int is_forced, String note, int type) {
        App_version ret = new App_version();
        ret.setId(id);
        ret.setCav_version(cav_version);
        ret.setCav_version_external(cav_version_external);
        ret.setCav_version_code(cav_version_code);
        ret.setCtime(ctime);
        ret.setContent(content);
        ret.setCav_md5(cav_md5);
        ret.setAndroid_url(android_url);
        ret.setIos_url(ios_url);
        ret.setIs_forced(is_forced);
        ret.setNote(note);
        ret.setType(type);
        return ret;    
    }

    public void assignment(App_version app_version) {
        long id = app_version.getId();
        String cav_version = app_version.getCav_version();
        String cav_version_external = app_version.getCav_version_external();
        int cav_version_code = app_version.getCav_version_code();
        java.util.Date ctime = app_version.getCtime();
        String content = app_version.getContent();
        String cav_md5 = app_version.getCav_md5();
        String android_url = app_version.getAndroid_url();
        String ios_url = app_version.getIos_url();
        int is_forced = app_version.getIs_forced();
        String note = app_version.getNote();
        int type = app_version.getType();

        this.setId(id);
        this.setCav_version(cav_version);
        this.setCav_version_external(cav_version_external);
        this.setCav_version_code(cav_version_code);
        this.setCtime(ctime);
        this.setContent(content);
        this.setCav_md5(cav_md5);
        this.setAndroid_url(android_url);
        this.setIos_url(ios_url);
        this.setIs_forced(is_forced);
        this.setNote(note);
        this.setType(type);

    }

    @SuppressWarnings("unused")
    public static void getApp_version(App_version app_version ){
        long id = app_version.getId();
        String cav_version = app_version.getCav_version();
        String cav_version_external = app_version.getCav_version_external();
        int cav_version_code = app_version.getCav_version_code();
        java.util.Date ctime = app_version.getCtime();
        String content = app_version.getContent();
        String cav_md5 = app_version.getCav_md5();
        String android_url = app_version.getAndroid_url();
        String ios_url = app_version.getIos_url();
        int is_forced = app_version.getIs_forced();
        String note = app_version.getNote();
        int type = app_version.getType();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(App_version app_version){
        long id = app_version.getId();
        String cav_version = app_version.getCav_version();
        String cav_version_external = app_version.getCav_version_external();
        int cav_version_code = app_version.getCav_version_code();
        java.util.Date ctime = app_version.getCtime();
        String content = app_version.getContent();
        String cav_md5 = app_version.getCav_md5();
        String android_url = app_version.getAndroid_url();
        String ios_url = app_version.getIos_url();
        int is_forced = app_version.getIs_forced();
        String note = app_version.getNote();
        int type = app_version.getType();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("cav_version",cav_version);
        _ret.put("cav_version_external",cav_version_external);
        _ret.put("cav_version_code",cav_version_code);
        _ret.put("ctime",ctime);
        _ret.put("content",content);
        _ret.put("cav_md5",cav_md5);
        _ret.put("android_url",android_url);
        _ret.put("ios_url",ios_url);
        _ret.put("is_forced",is_forced);
        _ret.put("note",note);
        _ret.put("type",type);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public App_version clone2(){
        try{
            return (App_version) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
