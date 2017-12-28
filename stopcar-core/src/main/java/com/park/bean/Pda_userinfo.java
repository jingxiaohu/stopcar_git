package com.park.bean;

import java.io.*;
import java.util.*;

//pda_userinfo
@SuppressWarnings({"serial"})
public class Pda_userinfo implements Cloneable , Serializable{

    //public static String[] carrays ={"pui_id","name","age","sex","address","tel","link_tel","pi_id","area_code","pda_id","mac","ctime","utime","note","loginname","password","state"};

    public long pui_id;//bigint(20)    主键ID
    public String name="";//varchar(100)    姓名
    public int age;//int(11)    年龄
    public int sex;//int(11)    性别0:保密1：男2：女
    public String address="";//varchar(100)    住址
    public String tel="";//varchar(20)    手机号码
    public String link_tel="";//varchar(20)    紧急联系人手机号码
    public long pi_id;//bigint(20)    管理的停车场主键ID
    public String area_code="";//varchar(60)    停车场地址区域编码
    public long pda_id;//bigint(20)    管理的PDA基本信息表主键ID
    public String mac="";//varchar(60)    管理的PDA物理地址
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String note="";//varchar(100)    备注
    public String loginname="";//varchar(100)    登录帐号
    public String password="";//varchar(100)    密码
    public int state;//int(11)    是否锁定(0:正常1：锁定关闭登录)



    public long getPui_id(){
        return pui_id;
    }

    public void setPui_id(long value){
        this.pui_id= value;
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

    public int getAge(){
        return age;
    }

    public void setAge(int value){
        this.age= value;
    }

    public int getSex(){
        return sex;
    }

    public void setSex(int value){
        this.sex= value;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String value){
    	if(value == null){
           value = "";
        }
        this.address= value;
    }

    public String getTel(){
        return tel;
    }

    public void setTel(String value){
    	if(value == null){
           value = "";
        }
        this.tel= value;
    }

    public String getLink_tel(){
        return link_tel;
    }

    public void setLink_tel(String value){
    	if(value == null){
           value = "";
        }
        this.link_tel= value;
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

    public long getPda_id(){
        return pda_id;
    }

    public void setPda_id(long value){
        this.pda_id= value;
    }

    public String getMac(){
        return mac;
    }

    public void setMac(String value){
    	if(value == null){
           value = "";
        }
        this.mac= value;
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

    public String getLoginname(){
        return loginname;
    }

    public void setLoginname(String value){
    	if(value == null){
           value = "";
        }
        this.loginname= value;
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

    public int getState(){
        return state;
    }

    public void setState(int value){
        this.state= value;
    }



    public static Pda_userinfo newPda_userinfo(long pui_id, String name, int age, int sex, String address, String tel, String link_tel, long pi_id, String area_code, long pda_id, String mac, java.util.Date ctime, java.util.Date utime, String note, String loginname, String password, int state) {
        Pda_userinfo ret = new Pda_userinfo();
        ret.setPui_id(pui_id);
        ret.setName(name);
        ret.setAge(age);
        ret.setSex(sex);
        ret.setAddress(address);
        ret.setTel(tel);
        ret.setLink_tel(link_tel);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setPda_id(pda_id);
        ret.setMac(mac);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setNote(note);
        ret.setLoginname(loginname);
        ret.setPassword(password);
        ret.setState(state);
        return ret;    
    }

    public void assignment(Pda_userinfo pda_userinfo) {
        long pui_id = pda_userinfo.getPui_id();
        String name = pda_userinfo.getName();
        int age = pda_userinfo.getAge();
        int sex = pda_userinfo.getSex();
        String address = pda_userinfo.getAddress();
        String tel = pda_userinfo.getTel();
        String link_tel = pda_userinfo.getLink_tel();
        long pi_id = pda_userinfo.getPi_id();
        String area_code = pda_userinfo.getArea_code();
        long pda_id = pda_userinfo.getPda_id();
        String mac = pda_userinfo.getMac();
        java.util.Date ctime = pda_userinfo.getCtime();
        java.util.Date utime = pda_userinfo.getUtime();
        String note = pda_userinfo.getNote();
        String loginname = pda_userinfo.getLoginname();
        String password = pda_userinfo.getPassword();
        int state = pda_userinfo.getState();

        this.setPui_id(pui_id);
        this.setName(name);
        this.setAge(age);
        this.setSex(sex);
        this.setAddress(address);
        this.setTel(tel);
        this.setLink_tel(link_tel);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setPda_id(pda_id);
        this.setMac(mac);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setNote(note);
        this.setLoginname(loginname);
        this.setPassword(password);
        this.setState(state);

    }

    @SuppressWarnings("unused")
    public static void getPda_userinfo(Pda_userinfo pda_userinfo ){
        long pui_id = pda_userinfo.getPui_id();
        String name = pda_userinfo.getName();
        int age = pda_userinfo.getAge();
        int sex = pda_userinfo.getSex();
        String address = pda_userinfo.getAddress();
        String tel = pda_userinfo.getTel();
        String link_tel = pda_userinfo.getLink_tel();
        long pi_id = pda_userinfo.getPi_id();
        String area_code = pda_userinfo.getArea_code();
        long pda_id = pda_userinfo.getPda_id();
        String mac = pda_userinfo.getMac();
        java.util.Date ctime = pda_userinfo.getCtime();
        java.util.Date utime = pda_userinfo.getUtime();
        String note = pda_userinfo.getNote();
        String loginname = pda_userinfo.getLoginname();
        String password = pda_userinfo.getPassword();
        int state = pda_userinfo.getState();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Pda_userinfo pda_userinfo){
        long pui_id = pda_userinfo.getPui_id();
        String name = pda_userinfo.getName();
        int age = pda_userinfo.getAge();
        int sex = pda_userinfo.getSex();
        String address = pda_userinfo.getAddress();
        String tel = pda_userinfo.getTel();
        String link_tel = pda_userinfo.getLink_tel();
        long pi_id = pda_userinfo.getPi_id();
        String area_code = pda_userinfo.getArea_code();
        long pda_id = pda_userinfo.getPda_id();
        String mac = pda_userinfo.getMac();
        java.util.Date ctime = pda_userinfo.getCtime();
        java.util.Date utime = pda_userinfo.getUtime();
        String note = pda_userinfo.getNote();
        String loginname = pda_userinfo.getLoginname();
        String password = pda_userinfo.getPassword();
        int state = pda_userinfo.getState();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("pui_id",pui_id);
        _ret.put("name",name);
        _ret.put("age",age);
        _ret.put("sex",sex);
        _ret.put("address",address);
        _ret.put("tel",tel);
        _ret.put("link_tel",link_tel);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("pda_id",pda_id);
        _ret.put("mac",mac);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("note",note);
        _ret.put("loginname",loginname);
        _ret.put("password",password);
        _ret.put("state",state);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Pda_userinfo clone2(){
        try{
            return (Pda_userinfo) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
