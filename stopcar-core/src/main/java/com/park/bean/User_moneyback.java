package com.park.bean;

import java.io.*;
import java.util.*;

//user_moneyback
@SuppressWarnings({"serial"})
public class User_moneyback implements Cloneable , Serializable{

    //public static String[] carrays ={"um_id","ui_id","order_id","pi_id","ctime","utime","um_money","car_code","run_url","um_state","check_state","admin_userid","note","area_code","type","content","is_rent","check_content","pi_name"};

    public long um_id;//bigint(20)    主键ID
    public long ui_id;//bigint(20)    用户ID
    public String order_id="";//varchar(70)    停车下订单ID
    public long pi_id;//bigint(20)    场地表主键ID
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public int um_money;//int(11)    退款金额(单位分)
    public String car_code="";//varchar(60)    退款车牌号
    public String run_url="";//varchar(150)    用户行驶证图片
    public int um_state;//int(11)    退款状态0：未退款1：已退款
    public int check_state;//int(11)    审核状态0：未审核1：审核未通过2:审核通过
    public long admin_userid;//bigint(20)    审核人后台管理表主键ID
    public String note="";//varchar(100)    
    public String area_code="";//varchar(30)    区域地址编码
    public int type;//int(11)    申诉类型0：临停扣款问题1：预约超时扣费问题2：其它
    public String content="";//varchar(255)    申诉原因
    public int is_rent;//int(11)    是否是租赁订单0：临停订单1：预约临停订单2：租赁包月订单3：租赁续租
    public String check_content="";//text    审核结果
    public String pi_name="";//varchar(100)    停车场名称



    public long getUm_id(){
        return um_id;
    }

    public void setUm_id(long value){
        this.um_id= value;
    }

    public long getUi_id(){
        return ui_id;
    }

    public void setUi_id(long value){
        this.ui_id= value;
    }

    public String getOrder_id(){
        return order_id;
    }

    public void setOrder_id(String value){
    	if(value == null){
           value = "";
        }
        this.order_id= value;
    }

    public long getPi_id(){
        return pi_id;
    }

    public void setPi_id(long value){
        this.pi_id= value;
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

    public int getUm_money(){
        return um_money;
    }

    public void setUm_money(int value){
        this.um_money= value;
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

    public String getRun_url(){
        return run_url;
    }

    public void setRun_url(String value){
    	if(value == null){
           value = "";
        }
        this.run_url= value;
    }

    public int getUm_state(){
        return um_state;
    }

    public void setUm_state(int value){
        this.um_state= value;
    }

    public int getCheck_state(){
        return check_state;
    }

    public void setCheck_state(int value){
        this.check_state= value;
    }

    public long getAdmin_userid(){
        return admin_userid;
    }

    public void setAdmin_userid(long value){
        this.admin_userid= value;
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

    public String getArea_code(){
        return area_code;
    }

    public void setArea_code(String value){
    	if(value == null){
           value = "";
        }
        this.area_code= value;
    }

    public int getType(){
        return type;
    }

    public void setType(int value){
        this.type= value;
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

    public int getIs_rent(){
        return is_rent;
    }

    public void setIs_rent(int value){
        this.is_rent= value;
    }

    public String getCheck_content(){
        return check_content;
    }

    public void setCheck_content(String value){
    	if(value == null){
           value = "";
        }
        this.check_content= value;
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



    public static User_moneyback newUser_moneyback(long um_id, long ui_id, String order_id, long pi_id, java.util.Date ctime, java.util.Date utime, int um_money, String car_code, String run_url, int um_state, int check_state, long admin_userid, String note, String area_code, int type, String content, int is_rent, String check_content, String pi_name) {
        User_moneyback ret = new User_moneyback();
        ret.setUm_id(um_id);
        ret.setUi_id(ui_id);
        ret.setOrder_id(order_id);
        ret.setPi_id(pi_id);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setUm_money(um_money);
        ret.setCar_code(car_code);
        ret.setRun_url(run_url);
        ret.setUm_state(um_state);
        ret.setCheck_state(check_state);
        ret.setAdmin_userid(admin_userid);
        ret.setNote(note);
        ret.setArea_code(area_code);
        ret.setType(type);
        ret.setContent(content);
        ret.setIs_rent(is_rent);
        ret.setCheck_content(check_content);
        ret.setPi_name(pi_name);
        return ret;    
    }

    public void assignment(User_moneyback user_moneyback) {
        long um_id = user_moneyback.getUm_id();
        long ui_id = user_moneyback.getUi_id();
        String order_id = user_moneyback.getOrder_id();
        long pi_id = user_moneyback.getPi_id();
        java.util.Date ctime = user_moneyback.getCtime();
        java.util.Date utime = user_moneyback.getUtime();
        int um_money = user_moneyback.getUm_money();
        String car_code = user_moneyback.getCar_code();
        String run_url = user_moneyback.getRun_url();
        int um_state = user_moneyback.getUm_state();
        int check_state = user_moneyback.getCheck_state();
        long admin_userid = user_moneyback.getAdmin_userid();
        String note = user_moneyback.getNote();
        String area_code = user_moneyback.getArea_code();
        int type = user_moneyback.getType();
        String content = user_moneyback.getContent();
        int is_rent = user_moneyback.getIs_rent();
        String check_content = user_moneyback.getCheck_content();
        String pi_name = user_moneyback.getPi_name();

        this.setUm_id(um_id);
        this.setUi_id(ui_id);
        this.setOrder_id(order_id);
        this.setPi_id(pi_id);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setUm_money(um_money);
        this.setCar_code(car_code);
        this.setRun_url(run_url);
        this.setUm_state(um_state);
        this.setCheck_state(check_state);
        this.setAdmin_userid(admin_userid);
        this.setNote(note);
        this.setArea_code(area_code);
        this.setType(type);
        this.setContent(content);
        this.setIs_rent(is_rent);
        this.setCheck_content(check_content);
        this.setPi_name(pi_name);

    }

    @SuppressWarnings("unused")
    public static void getUser_moneyback(User_moneyback user_moneyback ){
        long um_id = user_moneyback.getUm_id();
        long ui_id = user_moneyback.getUi_id();
        String order_id = user_moneyback.getOrder_id();
        long pi_id = user_moneyback.getPi_id();
        java.util.Date ctime = user_moneyback.getCtime();
        java.util.Date utime = user_moneyback.getUtime();
        int um_money = user_moneyback.getUm_money();
        String car_code = user_moneyback.getCar_code();
        String run_url = user_moneyback.getRun_url();
        int um_state = user_moneyback.getUm_state();
        int check_state = user_moneyback.getCheck_state();
        long admin_userid = user_moneyback.getAdmin_userid();
        String note = user_moneyback.getNote();
        String area_code = user_moneyback.getArea_code();
        int type = user_moneyback.getType();
        String content = user_moneyback.getContent();
        int is_rent = user_moneyback.getIs_rent();
        String check_content = user_moneyback.getCheck_content();
        String pi_name = user_moneyback.getPi_name();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(User_moneyback user_moneyback){
        long um_id = user_moneyback.getUm_id();
        long ui_id = user_moneyback.getUi_id();
        String order_id = user_moneyback.getOrder_id();
        long pi_id = user_moneyback.getPi_id();
        java.util.Date ctime = user_moneyback.getCtime();
        java.util.Date utime = user_moneyback.getUtime();
        int um_money = user_moneyback.getUm_money();
        String car_code = user_moneyback.getCar_code();
        String run_url = user_moneyback.getRun_url();
        int um_state = user_moneyback.getUm_state();
        int check_state = user_moneyback.getCheck_state();
        long admin_userid = user_moneyback.getAdmin_userid();
        String note = user_moneyback.getNote();
        String area_code = user_moneyback.getArea_code();
        int type = user_moneyback.getType();
        String content = user_moneyback.getContent();
        int is_rent = user_moneyback.getIs_rent();
        String check_content = user_moneyback.getCheck_content();
        String pi_name = user_moneyback.getPi_name();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("um_id",um_id);
        _ret.put("ui_id",ui_id);
        _ret.put("order_id",order_id);
        _ret.put("pi_id",pi_id);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("um_money",um_money);
        _ret.put("car_code",car_code);
        _ret.put("run_url",run_url);
        _ret.put("um_state",um_state);
        _ret.put("check_state",check_state);
        _ret.put("admin_userid",admin_userid);
        _ret.put("note",note);
        _ret.put("area_code",area_code);
        _ret.put("type",type);
        _ret.put("content",content);
        _ret.put("is_rent",is_rent);
        _ret.put("check_content",check_content);
        _ret.put("pi_name",pi_name);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public User_moneyback clone2(){
        try{
            return (User_moneyback) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
