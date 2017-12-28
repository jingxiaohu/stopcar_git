package com.park.bean;

import java.io.*;
import java.util.*;

//channel_version
@SuppressWarnings({"serial"})
public class Channel_version implements Cloneable , Serializable{

    //public static String[] carrays ={"cv_id","pda_c_id","version_out_show","version_code","is_trunk","is_open","apk_url","md5","intro","name","ctime","utime","is_force","note"};

    public long cv_id;//bigint(20)    主键ID
    public long pda_c_id;//bigint(20)    渠道ID
    public String version_out_show="";//varchar(60)    版本外部编号
    public int version_code;//int(11)    版本内部编号
    public int is_trunk;//int(11)    是否是主干渠道0:不是1：是
    public int is_open;//int(11)    是否启用0：不启用1：启用
    public String apk_url="";//varchar(255)    APK文件下载地址
    public String md5="";//varchar(100)    APK文件MD5
    public String intro="";//text    版本介绍
    public String name="";//varchar(100)    版本打包人姓名
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public int is_force;//int(11)    是否强制更新0：不强制1:强制
    public String note="";//varchar(100)    备注



    public long getCv_id(){
        return cv_id;
    }

    public void setCv_id(long value){
        this.cv_id= value;
    }

    public long getPda_c_id(){
        return pda_c_id;
    }

    public void setPda_c_id(long value){
        this.pda_c_id= value;
    }

    public String getVersion_out_show(){
        return version_out_show;
    }

    public void setVersion_out_show(String value){
    	if(value == null){
           value = "";
        }
        this.version_out_show= value;
    }

    public int getVersion_code(){
        return version_code;
    }

    public void setVersion_code(int value){
        this.version_code= value;
    }

    public int getIs_trunk(){
        return is_trunk;
    }

    public void setIs_trunk(int value){
        this.is_trunk= value;
    }

    public int getIs_open(){
        return is_open;
    }

    public void setIs_open(int value){
        this.is_open= value;
    }

    public String getApk_url(){
        return apk_url;
    }

    public void setApk_url(String value){
    	if(value == null){
           value = "";
        }
        this.apk_url= value;
    }

    public String getMd5(){
        return md5;
    }

    public void setMd5(String value){
    	if(value == null){
           value = "";
        }
        this.md5= value;
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

    public String getName(){
        return name;
    }

    public void setName(String value){
    	if(value == null){
           value = "";
        }
        this.name= value;
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

    public int getIs_force(){
        return is_force;
    }

    public void setIs_force(int value){
        this.is_force= value;
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



    public static Channel_version newChannel_version(long cv_id, long pda_c_id, String version_out_show, int version_code, int is_trunk, int is_open, String apk_url, String md5, String intro, String name, java.util.Date ctime, java.util.Date utime, int is_force, String note) {
        Channel_version ret = new Channel_version();
        ret.setCv_id(cv_id);
        ret.setPda_c_id(pda_c_id);
        ret.setVersion_out_show(version_out_show);
        ret.setVersion_code(version_code);
        ret.setIs_trunk(is_trunk);
        ret.setIs_open(is_open);
        ret.setApk_url(apk_url);
        ret.setMd5(md5);
        ret.setIntro(intro);
        ret.setName(name);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setIs_force(is_force);
        ret.setNote(note);
        return ret;    
    }

    public void assignment(Channel_version channel_version) {
        long cv_id = channel_version.getCv_id();
        long pda_c_id = channel_version.getPda_c_id();
        String version_out_show = channel_version.getVersion_out_show();
        int version_code = channel_version.getVersion_code();
        int is_trunk = channel_version.getIs_trunk();
        int is_open = channel_version.getIs_open();
        String apk_url = channel_version.getApk_url();
        String md5 = channel_version.getMd5();
        String intro = channel_version.getIntro();
        String name = channel_version.getName();
        java.util.Date ctime = channel_version.getCtime();
        java.util.Date utime = channel_version.getUtime();
        int is_force = channel_version.getIs_force();
        String note = channel_version.getNote();

        this.setCv_id(cv_id);
        this.setPda_c_id(pda_c_id);
        this.setVersion_out_show(version_out_show);
        this.setVersion_code(version_code);
        this.setIs_trunk(is_trunk);
        this.setIs_open(is_open);
        this.setApk_url(apk_url);
        this.setMd5(md5);
        this.setIntro(intro);
        this.setName(name);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setIs_force(is_force);
        this.setNote(note);

    }

    @SuppressWarnings("unused")
    public static void getChannel_version(Channel_version channel_version ){
        long cv_id = channel_version.getCv_id();
        long pda_c_id = channel_version.getPda_c_id();
        String version_out_show = channel_version.getVersion_out_show();
        int version_code = channel_version.getVersion_code();
        int is_trunk = channel_version.getIs_trunk();
        int is_open = channel_version.getIs_open();
        String apk_url = channel_version.getApk_url();
        String md5 = channel_version.getMd5();
        String intro = channel_version.getIntro();
        String name = channel_version.getName();
        java.util.Date ctime = channel_version.getCtime();
        java.util.Date utime = channel_version.getUtime();
        int is_force = channel_version.getIs_force();
        String note = channel_version.getNote();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Channel_version channel_version){
        long cv_id = channel_version.getCv_id();
        long pda_c_id = channel_version.getPda_c_id();
        String version_out_show = channel_version.getVersion_out_show();
        int version_code = channel_version.getVersion_code();
        int is_trunk = channel_version.getIs_trunk();
        int is_open = channel_version.getIs_open();
        String apk_url = channel_version.getApk_url();
        String md5 = channel_version.getMd5();
        String intro = channel_version.getIntro();
        String name = channel_version.getName();
        java.util.Date ctime = channel_version.getCtime();
        java.util.Date utime = channel_version.getUtime();
        int is_force = channel_version.getIs_force();
        String note = channel_version.getNote();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("cv_id",cv_id);
        _ret.put("pda_c_id",pda_c_id);
        _ret.put("version_out_show",version_out_show);
        _ret.put("version_code",version_code);
        _ret.put("is_trunk",is_trunk);
        _ret.put("is_open",is_open);
        _ret.put("apk_url",apk_url);
        _ret.put("md5",md5);
        _ret.put("intro",intro);
        _ret.put("name",name);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("is_force",is_force);
        _ret.put("note",note);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Channel_version clone2(){
        try{
            return (Channel_version) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
