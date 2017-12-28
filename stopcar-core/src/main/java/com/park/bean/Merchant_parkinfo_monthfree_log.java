package com.park.bean;

import java.io.*;
import java.util.*;

//merchant_parkinfo_monthfree_log
@SuppressWarnings({"serial"})
public class Merchant_parkinfo_monthfree_log implements Cloneable , Serializable{

    //public static String[] carrays ={"id","pu_id","pi_id","area_code","car_code","money","car_type","car_code_color","car_color","start_time_str","end_time_str","type","ctime","utime","note"};

    public long id;//bigint(20)    主键ID
    public long pu_id;//bigint(20)    商户ID
    public long pi_id;//bigint(20)    停车场ID
    public String area_code="";//varchar(100)    地址编号
    public String car_code="";//varchar(100)    车牌号
    public long money;//bigint(20)    该次缴纳费用(单位分)
    public int car_type;//int(11)    车牌类型0：未知车牌:、1：蓝牌小汽车、2：:黑牌小汽车、3：单排黄牌、4：双排黄牌、5：警车车牌、6：武警车牌、7：个性化车牌、8：单排军车牌、9：双排军车牌、10：使馆车牌、11：香港进出中国大陆车牌、12：农用车牌、13：教练车牌、14：澳门进出中国大陆车牌、15：双层武警车牌、16：武警总队车牌、17：双层武警总队车牌
    public int car_code_color;//int(11)    车牌颜色0：未知、1：蓝色、2：黄色、3：白色、4：黑色、5：绿色
    public int car_color;//int(11)    车辆颜色BLUE("蓝色",1),YELLOW("黄色",2),WHITE("白色",3),BLACK("黑色",4),GREEN("绿色",5)
    public String start_time_str="";//varchar(60)    开始包月时间(2017-03-22:10:00:00)
    public String end_time_str="";//varchar(60)    包月到期时间(2017-03-22:10:00:00)
    public int type;//int(11)    类型(0:包月车辆1:免费车辆)
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String note="";//varchar(255)    备注



    public long getId(){
        return id;
    }

    public void setId(long value){
        this.id= value;
    }

    public long getPu_id(){
        return pu_id;
    }

    public void setPu_id(long value){
        this.pu_id= value;
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

    public String getCar_code(){
        return car_code;
    }

    public void setCar_code(String value){
    	if(value == null){
           value = "";
        }
        this.car_code= value;
    }

    public long getMoney(){
        return money;
    }

    public void setMoney(long value){
        this.money= value;
    }

    public int getCar_type(){
        return car_type;
    }

    public void setCar_type(int value){
        this.car_type= value;
    }

    public int getCar_code_color(){
        return car_code_color;
    }

    public void setCar_code_color(int value){
        this.car_code_color= value;
    }

    public int getCar_color(){
        return car_color;
    }

    public void setCar_color(int value){
        this.car_color= value;
    }

    public String getStart_time_str(){
        return start_time_str;
    }

    public void setStart_time_str(String value){
    	if(value == null){
           value = "";
        }
        this.start_time_str= value;
    }

    public String getEnd_time_str(){
        return end_time_str;
    }

    public void setEnd_time_str(String value){
    	if(value == null){
           value = "";
        }
        this.end_time_str= value;
    }

    public int getType(){
        return type;
    }

    public void setType(int value){
        this.type= value;
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



    public static Merchant_parkinfo_monthfree_log newMerchant_parkinfo_monthfree_log(long id, long pu_id, long pi_id, String area_code, String car_code, long money, int car_type, int car_code_color, int car_color, String start_time_str, String end_time_str, int type, java.util.Date ctime, java.util.Date utime, String note) {
        Merchant_parkinfo_monthfree_log ret = new Merchant_parkinfo_monthfree_log();
        ret.setId(id);
        ret.setPu_id(pu_id);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setCar_code(car_code);
        ret.setMoney(money);
        ret.setCar_type(car_type);
        ret.setCar_code_color(car_code_color);
        ret.setCar_color(car_color);
        ret.setStart_time_str(start_time_str);
        ret.setEnd_time_str(end_time_str);
        ret.setType(type);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setNote(note);
        return ret;    
    }

    public void assignment(Merchant_parkinfo_monthfree_log merchant_parkinfo_monthfree_log) {
        long id = merchant_parkinfo_monthfree_log.getId();
        long pu_id = merchant_parkinfo_monthfree_log.getPu_id();
        long pi_id = merchant_parkinfo_monthfree_log.getPi_id();
        String area_code = merchant_parkinfo_monthfree_log.getArea_code();
        String car_code = merchant_parkinfo_monthfree_log.getCar_code();
        long money = merchant_parkinfo_monthfree_log.getMoney();
        int car_type = merchant_parkinfo_monthfree_log.getCar_type();
        int car_code_color = merchant_parkinfo_monthfree_log.getCar_code_color();
        int car_color = merchant_parkinfo_monthfree_log.getCar_color();
        String start_time_str = merchant_parkinfo_monthfree_log.getStart_time_str();
        String end_time_str = merchant_parkinfo_monthfree_log.getEnd_time_str();
        int type = merchant_parkinfo_monthfree_log.getType();
        java.util.Date ctime = merchant_parkinfo_monthfree_log.getCtime();
        java.util.Date utime = merchant_parkinfo_monthfree_log.getUtime();
        String note = merchant_parkinfo_monthfree_log.getNote();

        this.setId(id);
        this.setPu_id(pu_id);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setCar_code(car_code);
        this.setMoney(money);
        this.setCar_type(car_type);
        this.setCar_code_color(car_code_color);
        this.setCar_color(car_color);
        this.setStart_time_str(start_time_str);
        this.setEnd_time_str(end_time_str);
        this.setType(type);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setNote(note);

    }

    @SuppressWarnings("unused")
    public static void getMerchant_parkinfo_monthfree_log(Merchant_parkinfo_monthfree_log merchant_parkinfo_monthfree_log ){
        long id = merchant_parkinfo_monthfree_log.getId();
        long pu_id = merchant_parkinfo_monthfree_log.getPu_id();
        long pi_id = merchant_parkinfo_monthfree_log.getPi_id();
        String area_code = merchant_parkinfo_monthfree_log.getArea_code();
        String car_code = merchant_parkinfo_monthfree_log.getCar_code();
        long money = merchant_parkinfo_monthfree_log.getMoney();
        int car_type = merchant_parkinfo_monthfree_log.getCar_type();
        int car_code_color = merchant_parkinfo_monthfree_log.getCar_code_color();
        int car_color = merchant_parkinfo_monthfree_log.getCar_color();
        String start_time_str = merchant_parkinfo_monthfree_log.getStart_time_str();
        String end_time_str = merchant_parkinfo_monthfree_log.getEnd_time_str();
        int type = merchant_parkinfo_monthfree_log.getType();
        java.util.Date ctime = merchant_parkinfo_monthfree_log.getCtime();
        java.util.Date utime = merchant_parkinfo_monthfree_log.getUtime();
        String note = merchant_parkinfo_monthfree_log.getNote();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Merchant_parkinfo_monthfree_log merchant_parkinfo_monthfree_log){
        long id = merchant_parkinfo_monthfree_log.getId();
        long pu_id = merchant_parkinfo_monthfree_log.getPu_id();
        long pi_id = merchant_parkinfo_monthfree_log.getPi_id();
        String area_code = merchant_parkinfo_monthfree_log.getArea_code();
        String car_code = merchant_parkinfo_monthfree_log.getCar_code();
        long money = merchant_parkinfo_monthfree_log.getMoney();
        int car_type = merchant_parkinfo_monthfree_log.getCar_type();
        int car_code_color = merchant_parkinfo_monthfree_log.getCar_code_color();
        int car_color = merchant_parkinfo_monthfree_log.getCar_color();
        String start_time_str = merchant_parkinfo_monthfree_log.getStart_time_str();
        String end_time_str = merchant_parkinfo_monthfree_log.getEnd_time_str();
        int type = merchant_parkinfo_monthfree_log.getType();
        java.util.Date ctime = merchant_parkinfo_monthfree_log.getCtime();
        java.util.Date utime = merchant_parkinfo_monthfree_log.getUtime();
        String note = merchant_parkinfo_monthfree_log.getNote();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("pu_id",pu_id);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("car_code",car_code);
        _ret.put("money",money);
        _ret.put("car_type",car_type);
        _ret.put("car_code_color",car_code_color);
        _ret.put("car_color",car_color);
        _ret.put("start_time_str",start_time_str);
        _ret.put("end_time_str",end_time_str);
        _ret.put("type",type);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("note",note);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Merchant_parkinfo_monthfree_log clone2(){
        try{
            return (Merchant_parkinfo_monthfree_log) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
