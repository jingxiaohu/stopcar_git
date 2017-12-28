package com.park.bean;

import java.io.*;
import java.util.*;

//request_params_log
@SuppressWarnings({"serial"})
public class Request_params_log implements Cloneable , Serializable{

    //public static String[] carrays ={"id","method_name","duration","params_json","ctime","note","reponse_json"};

    public long id;//bigint(20)    主键ID
    public String method_name="";//varchar(100)    请求方法名称
    public long duration;//bigint(20)    请求消耗时长
    public String params_json="";//text    请求参数JSON字符串
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public String note="";//varchar(100)    备注
    public String reponse_json="";//text    返回数据JSON字符串



    public long getId(){
        return id;
    }

    public void setId(long value){
        this.id= value;
    }

    public String getMethod_name(){
        return method_name;
    }

    public void setMethod_name(String value){
    	if(value == null){
           value = "";
        }
        this.method_name= value;
    }

    public long getDuration(){
        return duration;
    }

    public void setDuration(long value){
        this.duration= value;
    }

    public String getParams_json(){
        return params_json;
    }

    public void setParams_json(String value){
    	if(value == null){
           value = "";
        }
        this.params_json= value;
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

    public String getNote(){
        return note;
    }

    public void setNote(String value){
    	if(value == null){
           value = "";
        }
        this.note= value;
    }

    public String getReponse_json(){
        return reponse_json;
    }

    public void setReponse_json(String value){
    	if(value == null){
           value = "";
        }
        this.reponse_json= value;
    }



    public static Request_params_log newRequest_params_log(long id, String method_name, long duration, String params_json, java.util.Date ctime, String note, String reponse_json) {
        Request_params_log ret = new Request_params_log();
        ret.setId(id);
        ret.setMethod_name(method_name);
        ret.setDuration(duration);
        ret.setParams_json(params_json);
        ret.setCtime(ctime);
        ret.setNote(note);
        ret.setReponse_json(reponse_json);
        return ret;    
    }

    public void assignment(Request_params_log request_params_log) {
        long id = request_params_log.getId();
        String method_name = request_params_log.getMethod_name();
        long duration = request_params_log.getDuration();
        String params_json = request_params_log.getParams_json();
        java.util.Date ctime = request_params_log.getCtime();
        String note = request_params_log.getNote();
        String reponse_json = request_params_log.getReponse_json();

        this.setId(id);
        this.setMethod_name(method_name);
        this.setDuration(duration);
        this.setParams_json(params_json);
        this.setCtime(ctime);
        this.setNote(note);
        this.setReponse_json(reponse_json);

    }

    @SuppressWarnings("unused")
    public static void getRequest_params_log(Request_params_log request_params_log ){
        long id = request_params_log.getId();
        String method_name = request_params_log.getMethod_name();
        long duration = request_params_log.getDuration();
        String params_json = request_params_log.getParams_json();
        java.util.Date ctime = request_params_log.getCtime();
        String note = request_params_log.getNote();
        String reponse_json = request_params_log.getReponse_json();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Request_params_log request_params_log){
        long id = request_params_log.getId();
        String method_name = request_params_log.getMethod_name();
        long duration = request_params_log.getDuration();
        String params_json = request_params_log.getParams_json();
        java.util.Date ctime = request_params_log.getCtime();
        String note = request_params_log.getNote();
        String reponse_json = request_params_log.getReponse_json();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("method_name",method_name);
        _ret.put("duration",duration);
        _ret.put("params_json",params_json);
        _ret.put("ctime",ctime);
        _ret.put("note",note);
        _ret.put("reponse_json",reponse_json);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Request_params_log clone2(){
        try{
            return (Request_params_log) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
