package com.park.bean;

import java.io.*;
import java.util.*;

//activity_info
@SuppressWarnings({"serial"})
public class Activity_info implements Cloneable , Serializable{

    //public static String[] carrays ={"ai_id","title","intro","money","type","starttime","endtime","ctime","utime","admin_loginname","admin_id","partner","people_num","note","state","pi_id","area_code","pc_id","weight","pi_name","img","img_jump_url","coupon_endtime"};

    public long ai_id;//bigint(20)    主键ID
    public String title="";//varchar(150)    活动标题
    public String intro="";//text    活动简介
    public long money;//bigint(20)    活动金额(单位分)
    public int type;//int(11)    活动类型（例如1：返券2：减免）
    public java.util.Date starttime=new java.util.Date();//datetime    活动开始时间
    public java.util.Date endtime=new java.util.Date();//datetime    活动结束时间
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String admin_loginname="";//varchar(100)    创建者账号
    public long admin_id;//bigint(20)    创建者主键IDadmin_id
    public String partner="";//varchar(150)    活动合作伙伴
    public int people_num;//int(11)    活动参与人数
    public String note="";//varchar(100)    备注
    public int state;//int(11)    是否关闭0:正常1：关闭
    public long pi_id;//bigint(20)    停车场主键ID
    public String area_code="";//varchar(100)    停车场区域地址编码
    public long pc_id;//bigint(20)    优惠券基本信息表主键ID（外键优惠券基本信息表主键ID）
    public int weight;//int(11)    权重比(排序)
    public String pi_name="";//varchar(100)    停车场名称
    public String img="";//varchar(255)    活动图片地址
    public String img_jump_url="";//varchar(255)    活动图片跳转地址
    public java.util.Date coupon_endtime=new java.util.Date();//datetime    送券到期时间



    public long getAi_id(){
        return ai_id;
    }

    public void setAi_id(long value){
        this.ai_id= value;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String value){
    	if(value == null){
           value = "";
        }
        this.title= value;
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

    public long getMoney(){
        return money;
    }

    public void setMoney(long value){
        this.money= value;
    }

    public int getType(){
        return type;
    }

    public void setType(int value){
        this.type= value;
    }

    public java.util.Date getStarttime(){
        return starttime;
    }

    public void setStarttime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.starttime= value;
    }

    public java.util.Date getEndtime(){
        return endtime;
    }

    public void setEndtime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.endtime= value;
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

    public String getAdmin_loginname(){
        return admin_loginname;
    }

    public void setAdmin_loginname(String value){
    	if(value == null){
           value = "";
        }
        this.admin_loginname= value;
    }

    public long getAdmin_id(){
        return admin_id;
    }

    public void setAdmin_id(long value){
        this.admin_id= value;
    }

    public String getPartner(){
        return partner;
    }

    public void setPartner(String value){
    	if(value == null){
           value = "";
        }
        this.partner= value;
    }

    public int getPeople_num(){
        return people_num;
    }

    public void setPeople_num(int value){
        this.people_num= value;
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

    public int getState(){
        return state;
    }

    public void setState(int value){
        this.state= value;
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

    public long getPc_id(){
        return pc_id;
    }

    public void setPc_id(long value){
        this.pc_id= value;
    }

    public int getWeight(){
        return weight;
    }

    public void setWeight(int value){
        this.weight= value;
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

    public String getImg(){
        return img;
    }

    public void setImg(String value){
    	if(value == null){
           value = "";
        }
        this.img= value;
    }

    public String getImg_jump_url(){
        return img_jump_url;
    }

    public void setImg_jump_url(String value){
    	if(value == null){
           value = "";
        }
        this.img_jump_url= value;
    }

    public java.util.Date getCoupon_endtime(){
        return coupon_endtime;
    }

    public void setCoupon_endtime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.coupon_endtime= value;
    }



    public static Activity_info newActivity_info(long ai_id, String title, String intro, long money, int type, java.util.Date starttime, java.util.Date endtime, java.util.Date ctime, java.util.Date utime, String admin_loginname, long admin_id, String partner, int people_num, String note, int state, long pi_id, String area_code, long pc_id, int weight, String pi_name, String img, String img_jump_url, java.util.Date coupon_endtime) {
        Activity_info ret = new Activity_info();
        ret.setAi_id(ai_id);
        ret.setTitle(title);
        ret.setIntro(intro);
        ret.setMoney(money);
        ret.setType(type);
        ret.setStarttime(starttime);
        ret.setEndtime(endtime);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setAdmin_loginname(admin_loginname);
        ret.setAdmin_id(admin_id);
        ret.setPartner(partner);
        ret.setPeople_num(people_num);
        ret.setNote(note);
        ret.setState(state);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setPc_id(pc_id);
        ret.setWeight(weight);
        ret.setPi_name(pi_name);
        ret.setImg(img);
        ret.setImg_jump_url(img_jump_url);
        ret.setCoupon_endtime(coupon_endtime);
        return ret;    
    }

    public void assignment(Activity_info activity_info) {
        long ai_id = activity_info.getAi_id();
        String title = activity_info.getTitle();
        String intro = activity_info.getIntro();
        long money = activity_info.getMoney();
        int type = activity_info.getType();
        java.util.Date starttime = activity_info.getStarttime();
        java.util.Date endtime = activity_info.getEndtime();
        java.util.Date ctime = activity_info.getCtime();
        java.util.Date utime = activity_info.getUtime();
        String admin_loginname = activity_info.getAdmin_loginname();
        long admin_id = activity_info.getAdmin_id();
        String partner = activity_info.getPartner();
        int people_num = activity_info.getPeople_num();
        String note = activity_info.getNote();
        int state = activity_info.getState();
        long pi_id = activity_info.getPi_id();
        String area_code = activity_info.getArea_code();
        long pc_id = activity_info.getPc_id();
        int weight = activity_info.getWeight();
        String pi_name = activity_info.getPi_name();
        String img = activity_info.getImg();
        String img_jump_url = activity_info.getImg_jump_url();
        java.util.Date coupon_endtime = activity_info.getCoupon_endtime();

        this.setAi_id(ai_id);
        this.setTitle(title);
        this.setIntro(intro);
        this.setMoney(money);
        this.setType(type);
        this.setStarttime(starttime);
        this.setEndtime(endtime);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setAdmin_loginname(admin_loginname);
        this.setAdmin_id(admin_id);
        this.setPartner(partner);
        this.setPeople_num(people_num);
        this.setNote(note);
        this.setState(state);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setPc_id(pc_id);
        this.setWeight(weight);
        this.setPi_name(pi_name);
        this.setImg(img);
        this.setImg_jump_url(img_jump_url);
        this.setCoupon_endtime(coupon_endtime);

    }

    @SuppressWarnings("unused")
    public static void getActivity_info(Activity_info activity_info ){
        long ai_id = activity_info.getAi_id();
        String title = activity_info.getTitle();
        String intro = activity_info.getIntro();
        long money = activity_info.getMoney();
        int type = activity_info.getType();
        java.util.Date starttime = activity_info.getStarttime();
        java.util.Date endtime = activity_info.getEndtime();
        java.util.Date ctime = activity_info.getCtime();
        java.util.Date utime = activity_info.getUtime();
        String admin_loginname = activity_info.getAdmin_loginname();
        long admin_id = activity_info.getAdmin_id();
        String partner = activity_info.getPartner();
        int people_num = activity_info.getPeople_num();
        String note = activity_info.getNote();
        int state = activity_info.getState();
        long pi_id = activity_info.getPi_id();
        String area_code = activity_info.getArea_code();
        long pc_id = activity_info.getPc_id();
        int weight = activity_info.getWeight();
        String pi_name = activity_info.getPi_name();
        String img = activity_info.getImg();
        String img_jump_url = activity_info.getImg_jump_url();
        java.util.Date coupon_endtime = activity_info.getCoupon_endtime();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Activity_info activity_info){
        long ai_id = activity_info.getAi_id();
        String title = activity_info.getTitle();
        String intro = activity_info.getIntro();
        long money = activity_info.getMoney();
        int type = activity_info.getType();
        java.util.Date starttime = activity_info.getStarttime();
        java.util.Date endtime = activity_info.getEndtime();
        java.util.Date ctime = activity_info.getCtime();
        java.util.Date utime = activity_info.getUtime();
        String admin_loginname = activity_info.getAdmin_loginname();
        long admin_id = activity_info.getAdmin_id();
        String partner = activity_info.getPartner();
        int people_num = activity_info.getPeople_num();
        String note = activity_info.getNote();
        int state = activity_info.getState();
        long pi_id = activity_info.getPi_id();
        String area_code = activity_info.getArea_code();
        long pc_id = activity_info.getPc_id();
        int weight = activity_info.getWeight();
        String pi_name = activity_info.getPi_name();
        String img = activity_info.getImg();
        String img_jump_url = activity_info.getImg_jump_url();
        java.util.Date coupon_endtime = activity_info.getCoupon_endtime();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("ai_id",ai_id);
        _ret.put("title",title);
        _ret.put("intro",intro);
        _ret.put("money",money);
        _ret.put("type",type);
        _ret.put("starttime",starttime);
        _ret.put("endtime",endtime);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("admin_loginname",admin_loginname);
        _ret.put("admin_id",admin_id);
        _ret.put("partner",partner);
        _ret.put("people_num",people_num);
        _ret.put("note",note);
        _ret.put("state",state);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("pc_id",pc_id);
        _ret.put("weight",weight);
        _ret.put("pi_name",pi_name);
        _ret.put("img",img);
        _ret.put("img_jump_url",img_jump_url);
        _ret.put("coupon_endtime",coupon_endtime);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Activity_info clone2(){
        try{
            return (Activity_info) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
