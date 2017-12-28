package com.park.bean;

import java.io.*;
import java.util.*;

//user_carcode
@SuppressWarnings({"serial"})
public class User_carcode implements Cloneable , Serializable{

    //public static String[] carrays ={"uc_id","ui_id","car_code","ctime","utime","run_url","is_default","car_brand","uc_color","note","ui_nd","ui_tel","run_code"};

    public long uc_id;//bigint(20)    
    public long ui_id;//bigint(20)    用户ID
    public String car_code="";//varchar(80)    车牌号码
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String run_url="";//varchar(200)    行驶证照片
    public int is_default;//int(11)    是否是默认车牌号选定:0：不是1：是默认车牌
    public String car_brand="";//varchar(100)    车辆品牌
    public int uc_color;//int(11)    车辆颜色:0未定1：黑色2：白色3：银色4：金色5：红色6：黄色7：绿色8：蓝色9：橙色10：灰色
    public String note="";//varchar(100)    备注
    public String ui_nd="";//varchar(100)    用户唯一标识uuid
    public String ui_tel="";//varchar(100)    用户电话号码
    public String run_code="";//varchar(60)    行驶证号码



    public long getUc_id(){
        return uc_id;
    }

    public void setUc_id(long value){
        this.uc_id= value;
    }

    public long getUi_id(){
        return ui_id;
    }

    public void setUi_id(long value){
        this.ui_id= value;
    }

    public String getCar_code(){
        return car_code;
    }

    public void setCar_code(String value){
    	if(value == null){
           value = "";
        }
        this.car_code= value;
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

    public String getRun_url(){
        return run_url;
    }

    public void setRun_url(String value){
    	if(value == null){
           value = "";
        }
        this.run_url= value;
    }

    public int getIs_default(){
        return is_default;
    }

    public void setIs_default(int value){
        this.is_default= value;
    }

    public String getCar_brand(){
        return car_brand;
    }

    public void setCar_brand(String value){
    	if(value == null){
           value = "";
        }
        this.car_brand= value;
    }

    public int getUc_color(){
        return uc_color;
    }

    public void setUc_color(int value){
        this.uc_color= value;
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

    public String getUi_nd(){
        return ui_nd;
    }

    public void setUi_nd(String value){
    	if(value == null){
           value = "";
        }
        this.ui_nd= value;
    }

    public String getUi_tel(){
        return ui_tel;
    }

    public void setUi_tel(String value){
    	if(value == null){
           value = "";
        }
        this.ui_tel= value;
    }

    public String getRun_code(){
        return run_code;
    }

    public void setRun_code(String value){
    	if(value == null){
           value = "";
        }
        this.run_code= value;
    }



    public static User_carcode newUser_carcode(long uc_id, long ui_id, String car_code, java.util.Date ctime, java.util.Date utime, String run_url, int is_default, String car_brand, int uc_color, String note, String ui_nd, String ui_tel, String run_code) {
        User_carcode ret = new User_carcode();
        ret.setUc_id(uc_id);
        ret.setUi_id(ui_id);
        ret.setCar_code(car_code);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setRun_url(run_url);
        ret.setIs_default(is_default);
        ret.setCar_brand(car_brand);
        ret.setUc_color(uc_color);
        ret.setNote(note);
        ret.setUi_nd(ui_nd);
        ret.setUi_tel(ui_tel);
        ret.setRun_code(run_code);
        return ret;    
    }

    public void assignment(User_carcode user_carcode) {
        long uc_id = user_carcode.getUc_id();
        long ui_id = user_carcode.getUi_id();
        String car_code = user_carcode.getCar_code();
        java.util.Date ctime = user_carcode.getCtime();
        java.util.Date utime = user_carcode.getUtime();
        String run_url = user_carcode.getRun_url();
        int is_default = user_carcode.getIs_default();
        String car_brand = user_carcode.getCar_brand();
        int uc_color = user_carcode.getUc_color();
        String note = user_carcode.getNote();
        String ui_nd = user_carcode.getUi_nd();
        String ui_tel = user_carcode.getUi_tel();
        String run_code = user_carcode.getRun_code();

        this.setUc_id(uc_id);
        this.setUi_id(ui_id);
        this.setCar_code(car_code);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setRun_url(run_url);
        this.setIs_default(is_default);
        this.setCar_brand(car_brand);
        this.setUc_color(uc_color);
        this.setNote(note);
        this.setUi_nd(ui_nd);
        this.setUi_tel(ui_tel);
        this.setRun_code(run_code);

    }

    @SuppressWarnings("unused")
    public static void getUser_carcode(User_carcode user_carcode ){
        long uc_id = user_carcode.getUc_id();
        long ui_id = user_carcode.getUi_id();
        String car_code = user_carcode.getCar_code();
        java.util.Date ctime = user_carcode.getCtime();
        java.util.Date utime = user_carcode.getUtime();
        String run_url = user_carcode.getRun_url();
        int is_default = user_carcode.getIs_default();
        String car_brand = user_carcode.getCar_brand();
        int uc_color = user_carcode.getUc_color();
        String note = user_carcode.getNote();
        String ui_nd = user_carcode.getUi_nd();
        String ui_tel = user_carcode.getUi_tel();
        String run_code = user_carcode.getRun_code();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(User_carcode user_carcode){
        long uc_id = user_carcode.getUc_id();
        long ui_id = user_carcode.getUi_id();
        String car_code = user_carcode.getCar_code();
        java.util.Date ctime = user_carcode.getCtime();
        java.util.Date utime = user_carcode.getUtime();
        String run_url = user_carcode.getRun_url();
        int is_default = user_carcode.getIs_default();
        String car_brand = user_carcode.getCar_brand();
        int uc_color = user_carcode.getUc_color();
        String note = user_carcode.getNote();
        String ui_nd = user_carcode.getUi_nd();
        String ui_tel = user_carcode.getUi_tel();
        String run_code = user_carcode.getRun_code();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("uc_id",uc_id);
        _ret.put("ui_id",ui_id);
        _ret.put("car_code",car_code);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("run_url",run_url);
        _ret.put("is_default",is_default);
        _ret.put("car_brand",car_brand);
        _ret.put("uc_color",uc_color);
        _ret.put("note",note);
        _ret.put("ui_nd",ui_nd);
        _ret.put("ui_tel",ui_tel);
        _ret.put("run_code",run_code);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public User_carcode clone2(){
        try{
            return (User_carcode) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
