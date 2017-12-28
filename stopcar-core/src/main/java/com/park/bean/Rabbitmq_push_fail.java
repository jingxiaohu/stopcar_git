package com.park.bean;

import java.io.*;
import java.util.*;

//rabbitmq_push_fail
@SuppressWarnings({"serial"})
public class Rabbitmq_push_fail implements Cloneable , Serializable{

    //public static String[] carrays ={"rpf_id","rpf_nd","server_code","type","content_json","persistent_state","ctime","utime","fail_count","state","name","note","exchange_name","routing_key","host_name","content_md5"};

    public long rpf_id;//bigint(20)    主键ID
    public String rpf_nd="";//varchar(80)    唯一标识ND
    public int server_code;//int(11)    mq服务器（0：泸州服务器1：贵阳服务器2：重庆服务器）
    public int type;//int(11)    业务分类（0：停车场推送）
    public String content_json="";//text    JSON内容
    public int persistent_state;//int(11)    是否持久化(0：不持久化1：持久化)
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public int fail_count;//int(11)    失败次数
    public int state;//int(11)    是否关闭（0：不关闭1：关闭（不再进行再次调用或者其它任何处理）2:推送成功）
    public String name="";//varchar(255)    接口名称或者事件名称（调用的哪个接口引入的MQ）
    public String note="";//varchar(100)    备注
    public String exchange_name="";//varchar(100)    交换机名称
    public String routing_key="";//varchar(100)    路由key
    public String host_name="";//varchar(255)    虚拟主机host
    public String content_md5="";//varchar(100)    内容MD5



    public long getRpf_id(){
        return rpf_id;
    }

    public void setRpf_id(long value){
        this.rpf_id= value;
    }

    public String getRpf_nd(){
        return rpf_nd;
    }

    public void setRpf_nd(String value){
    	if(value == null){
           value = "";
        }
        this.rpf_nd= value;
    }

    public int getServer_code(){
        return server_code;
    }

    public void setServer_code(int value){
        this.server_code= value;
    }

    public int getType(){
        return type;
    }

    public void setType(int value){
        this.type= value;
    }

    public String getContent_json(){
        return content_json;
    }

    public void setContent_json(String value){
    	if(value == null){
           value = "";
        }
        this.content_json= value;
    }

    public int getPersistent_state(){
        return persistent_state;
    }

    public void setPersistent_state(int value){
        this.persistent_state= value;
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

    public int getFail_count(){
        return fail_count;
    }

    public void setFail_count(int value){
        this.fail_count= value;
    }

    public int getState(){
        return state;
    }

    public void setState(int value){
        this.state= value;
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

    public String getNote(){
        return note;
    }

    public void setNote(String value){
    	if(value == null){
           value = "";
        }
        this.note= value;
    }

    public String getExchange_name(){
        return exchange_name;
    }

    public void setExchange_name(String value){
    	if(value == null){
           value = "";
        }
        this.exchange_name= value;
    }

    public String getRouting_key(){
        return routing_key;
    }

    public void setRouting_key(String value){
    	if(value == null){
           value = "";
        }
        this.routing_key= value;
    }

    public String getHost_name(){
        return host_name;
    }

    public void setHost_name(String value){
    	if(value == null){
           value = "";
        }
        this.host_name= value;
    }

    public String getContent_md5(){
        return content_md5;
    }

    public void setContent_md5(String value){
    	if(value == null){
           value = "";
        }
        this.content_md5= value;
    }



    public static Rabbitmq_push_fail newRabbitmq_push_fail(long rpf_id, String rpf_nd, int server_code, int type, String content_json, int persistent_state, java.util.Date ctime, java.util.Date utime, int fail_count, int state, String name, String note, String exchange_name, String routing_key, String host_name, String content_md5) {
        Rabbitmq_push_fail ret = new Rabbitmq_push_fail();
        ret.setRpf_id(rpf_id);
        ret.setRpf_nd(rpf_nd);
        ret.setServer_code(server_code);
        ret.setType(type);
        ret.setContent_json(content_json);
        ret.setPersistent_state(persistent_state);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setFail_count(fail_count);
        ret.setState(state);
        ret.setName(name);
        ret.setNote(note);
        ret.setExchange_name(exchange_name);
        ret.setRouting_key(routing_key);
        ret.setHost_name(host_name);
        ret.setContent_md5(content_md5);
        return ret;    
    }

    public void assignment(Rabbitmq_push_fail rabbitmq_push_fail) {
        long rpf_id = rabbitmq_push_fail.getRpf_id();
        String rpf_nd = rabbitmq_push_fail.getRpf_nd();
        int server_code = rabbitmq_push_fail.getServer_code();
        int type = rabbitmq_push_fail.getType();
        String content_json = rabbitmq_push_fail.getContent_json();
        int persistent_state = rabbitmq_push_fail.getPersistent_state();
        java.util.Date ctime = rabbitmq_push_fail.getCtime();
        java.util.Date utime = rabbitmq_push_fail.getUtime();
        int fail_count = rabbitmq_push_fail.getFail_count();
        int state = rabbitmq_push_fail.getState();
        String name = rabbitmq_push_fail.getName();
        String note = rabbitmq_push_fail.getNote();
        String exchange_name = rabbitmq_push_fail.getExchange_name();
        String routing_key = rabbitmq_push_fail.getRouting_key();
        String host_name = rabbitmq_push_fail.getHost_name();
        String content_md5 = rabbitmq_push_fail.getContent_md5();

        this.setRpf_id(rpf_id);
        this.setRpf_nd(rpf_nd);
        this.setServer_code(server_code);
        this.setType(type);
        this.setContent_json(content_json);
        this.setPersistent_state(persistent_state);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setFail_count(fail_count);
        this.setState(state);
        this.setName(name);
        this.setNote(note);
        this.setExchange_name(exchange_name);
        this.setRouting_key(routing_key);
        this.setHost_name(host_name);
        this.setContent_md5(content_md5);

    }

    @SuppressWarnings("unused")
    public static void getRabbitmq_push_fail(Rabbitmq_push_fail rabbitmq_push_fail ){
        long rpf_id = rabbitmq_push_fail.getRpf_id();
        String rpf_nd = rabbitmq_push_fail.getRpf_nd();
        int server_code = rabbitmq_push_fail.getServer_code();
        int type = rabbitmq_push_fail.getType();
        String content_json = rabbitmq_push_fail.getContent_json();
        int persistent_state = rabbitmq_push_fail.getPersistent_state();
        java.util.Date ctime = rabbitmq_push_fail.getCtime();
        java.util.Date utime = rabbitmq_push_fail.getUtime();
        int fail_count = rabbitmq_push_fail.getFail_count();
        int state = rabbitmq_push_fail.getState();
        String name = rabbitmq_push_fail.getName();
        String note = rabbitmq_push_fail.getNote();
        String exchange_name = rabbitmq_push_fail.getExchange_name();
        String routing_key = rabbitmq_push_fail.getRouting_key();
        String host_name = rabbitmq_push_fail.getHost_name();
        String content_md5 = rabbitmq_push_fail.getContent_md5();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Rabbitmq_push_fail rabbitmq_push_fail){
        long rpf_id = rabbitmq_push_fail.getRpf_id();
        String rpf_nd = rabbitmq_push_fail.getRpf_nd();
        int server_code = rabbitmq_push_fail.getServer_code();
        int type = rabbitmq_push_fail.getType();
        String content_json = rabbitmq_push_fail.getContent_json();
        int persistent_state = rabbitmq_push_fail.getPersistent_state();
        java.util.Date ctime = rabbitmq_push_fail.getCtime();
        java.util.Date utime = rabbitmq_push_fail.getUtime();
        int fail_count = rabbitmq_push_fail.getFail_count();
        int state = rabbitmq_push_fail.getState();
        String name = rabbitmq_push_fail.getName();
        String note = rabbitmq_push_fail.getNote();
        String exchange_name = rabbitmq_push_fail.getExchange_name();
        String routing_key = rabbitmq_push_fail.getRouting_key();
        String host_name = rabbitmq_push_fail.getHost_name();
        String content_md5 = rabbitmq_push_fail.getContent_md5();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("rpf_id",rpf_id);
        _ret.put("rpf_nd",rpf_nd);
        _ret.put("server_code",server_code);
        _ret.put("type",type);
        _ret.put("content_json",content_json);
        _ret.put("persistent_state",persistent_state);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("fail_count",fail_count);
        _ret.put("state",state);
        _ret.put("name",name);
        _ret.put("note",note);
        _ret.put("exchange_name",exchange_name);
        _ret.put("routing_key",routing_key);
        _ret.put("host_name",host_name);
        _ret.put("content_md5",content_md5);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Rabbitmq_push_fail clone2(){
        try{
            return (Rabbitmq_push_fail) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
