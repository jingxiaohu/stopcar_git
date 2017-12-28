package com.park.bean;

import java.io.*;
import java.util.*;

//park_rule
@SuppressWarnings({"serial"})
public class Park_rule implements Cloneable , Serializable{

    //public static String[] carrays ={"pr_id","pr_nd","pi_id","area_code","state","json_array","ctime","utime","note"};

    public long pr_id;//bigint(20)    主键ID
    public String pr_nd="";//varchar(80)    唯一标识符ND
    public long pi_id;//bigint(20)    停车场主键ID
    public String area_code="";//varchar(30)    停车场地址区域编码
    public int state;//int(11)    是否有效（0：无效1：有效）
    public String json_array="";//text    规则JSONArray数组
    public java.util.Date ctime=new java.util.Date();//datetime    服务器接受该数据时间
    public java.util.Date utime=new java.util.Date();//datetime    规则修改时间
    public String note="";//varchar(100)    备注



    public long getPr_id(){
        return pr_id;
    }

    public void setPr_id(long value){
        this.pr_id= value;
    }

    public String getPr_nd(){
        return pr_nd;
    }

    public void setPr_nd(String value){
    	if(value == null){
           value = "";
        }
        this.pr_nd= value;
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

    public int getState(){
        return state;
    }

    public void setState(int value){
        this.state= value;
    }

    public String getJson_array(){
        return json_array;
    }

    public void setJson_array(String value){
    	if(value == null){
           value = "";
        }
        this.json_array= value;
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



    public static Park_rule newPark_rule(long pr_id, String pr_nd, long pi_id, String area_code, int state, String json_array, java.util.Date ctime, java.util.Date utime, String note) {
        Park_rule ret = new Park_rule();
        ret.setPr_id(pr_id);
        ret.setPr_nd(pr_nd);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setState(state);
        ret.setJson_array(json_array);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setNote(note);
        return ret;    
    }

    public void assignment(Park_rule park_rule) {
        long pr_id = park_rule.getPr_id();
        String pr_nd = park_rule.getPr_nd();
        long pi_id = park_rule.getPi_id();
        String area_code = park_rule.getArea_code();
        int state = park_rule.getState();
        String json_array = park_rule.getJson_array();
        java.util.Date ctime = park_rule.getCtime();
        java.util.Date utime = park_rule.getUtime();
        String note = park_rule.getNote();

        this.setPr_id(pr_id);
        this.setPr_nd(pr_nd);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setState(state);
        this.setJson_array(json_array);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setNote(note);

    }

    @SuppressWarnings("unused")
    public static void getPark_rule(Park_rule park_rule ){
        long pr_id = park_rule.getPr_id();
        String pr_nd = park_rule.getPr_nd();
        long pi_id = park_rule.getPi_id();
        String area_code = park_rule.getArea_code();
        int state = park_rule.getState();
        String json_array = park_rule.getJson_array();
        java.util.Date ctime = park_rule.getCtime();
        java.util.Date utime = park_rule.getUtime();
        String note = park_rule.getNote();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Park_rule park_rule){
        long pr_id = park_rule.getPr_id();
        String pr_nd = park_rule.getPr_nd();
        long pi_id = park_rule.getPi_id();
        String area_code = park_rule.getArea_code();
        int state = park_rule.getState();
        String json_array = park_rule.getJson_array();
        java.util.Date ctime = park_rule.getCtime();
        java.util.Date utime = park_rule.getUtime();
        String note = park_rule.getNote();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("pr_id",pr_id);
        _ret.put("pr_nd",pr_nd);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("state",state);
        _ret.put("json_array",json_array);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("note",note);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Park_rule clone2(){
        try{
            return (Park_rule) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
