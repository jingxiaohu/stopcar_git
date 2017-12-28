package com.park.bean;

import java.io.*;
import java.util.*;

//pda_channel
@SuppressWarnings({"serial"})
public class Pda_channel implements Cloneable , Serializable{

    //public static String[] carrays ={"pda_c_id","name","intro","is_open","father_id","is_delete","ctime","utime","note"};

    public long pda_c_id;//bigint(20)    主键ID
    public String name="";//varchar(60)    渠道名称
    public String intro="";//text    渠道简介
    public int is_open;//int(11)    是否开启0:不开启1：开启
    public long father_id;//bigint(20)    父渠道ID
    public int is_delete;//int(11)    是否删除0:不删除1：删除
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String note="";//varchar(100)    备注



    public long getPda_c_id(){
        return pda_c_id;
    }

    public void setPda_c_id(long value){
        this.pda_c_id= value;
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

    public String getIntro(){
        return intro;
    }

    public void setIntro(String value){
    	if(value == null){
           value = "";
        }
        this.intro= value;
    }

    public int getIs_open(){
        return is_open;
    }

    public void setIs_open(int value){
        this.is_open= value;
    }

    public long getFather_id(){
        return father_id;
    }

    public void setFather_id(long value){
        this.father_id= value;
    }

    public int getIs_delete(){
        return is_delete;
    }

    public void setIs_delete(int value){
        this.is_delete= value;
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



    public static Pda_channel newPda_channel(long pda_c_id, String name, String intro, int is_open, long father_id, int is_delete, java.util.Date ctime, java.util.Date utime, String note) {
        Pda_channel ret = new Pda_channel();
        ret.setPda_c_id(pda_c_id);
        ret.setName(name);
        ret.setIntro(intro);
        ret.setIs_open(is_open);
        ret.setFather_id(father_id);
        ret.setIs_delete(is_delete);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setNote(note);
        return ret;    
    }

    public void assignment(Pda_channel pda_channel) {
        long pda_c_id = pda_channel.getPda_c_id();
        String name = pda_channel.getName();
        String intro = pda_channel.getIntro();
        int is_open = pda_channel.getIs_open();
        long father_id = pda_channel.getFather_id();
        int is_delete = pda_channel.getIs_delete();
        java.util.Date ctime = pda_channel.getCtime();
        java.util.Date utime = pda_channel.getUtime();
        String note = pda_channel.getNote();

        this.setPda_c_id(pda_c_id);
        this.setName(name);
        this.setIntro(intro);
        this.setIs_open(is_open);
        this.setFather_id(father_id);
        this.setIs_delete(is_delete);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setNote(note);

    }

    @SuppressWarnings("unused")
    public static void getPda_channel(Pda_channel pda_channel ){
        long pda_c_id = pda_channel.getPda_c_id();
        String name = pda_channel.getName();
        String intro = pda_channel.getIntro();
        int is_open = pda_channel.getIs_open();
        long father_id = pda_channel.getFather_id();
        int is_delete = pda_channel.getIs_delete();
        java.util.Date ctime = pda_channel.getCtime();
        java.util.Date utime = pda_channel.getUtime();
        String note = pda_channel.getNote();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Pda_channel pda_channel){
        long pda_c_id = pda_channel.getPda_c_id();
        String name = pda_channel.getName();
        String intro = pda_channel.getIntro();
        int is_open = pda_channel.getIs_open();
        long father_id = pda_channel.getFather_id();
        int is_delete = pda_channel.getIs_delete();
        java.util.Date ctime = pda_channel.getCtime();
        java.util.Date utime = pda_channel.getUtime();
        String note = pda_channel.getNote();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("pda_c_id",pda_c_id);
        _ret.put("name",name);
        _ret.put("intro",intro);
        _ret.put("is_open",is_open);
        _ret.put("father_id",father_id);
        _ret.put("is_delete",is_delete);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("note",note);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Pda_channel clone2(){
        try{
            return (Pda_channel) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
