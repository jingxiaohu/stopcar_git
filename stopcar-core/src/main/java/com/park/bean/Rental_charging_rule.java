package com.park.bean;

import java.io.*;
import java.util.*;

//rental_charging_rule
@SuppressWarnings({"serial"})
public class Rental_charging_rule implements Cloneable , Serializable{

    //public static String[] carrays ={"rcr_id","pi_id","start_price","start_time","charging","charging_time","month_price","month_time","permit_time","timeout_info","rcr_type","rcr_state","rcr_discount","car_displacement","car_type","car_code_color","is_time_bucket","time_bucket","ctime","utime","rcr_md5","is_default","area_code","note","free_minute","is_free_minute","roadside_type"};

    public long rcr_id;//bigint(20)    主键ID
    public long pi_id;//bigint(20)    停车场主键ID（外键）
    public int start_price;//int(11)    起步价(RMB单位分)
    public int start_time;//int(11)    起步价(RMB单位分)
    public int charging;//int(11)    计费价(RMB单位分)
    public int charging_time;//int(11)    计费时长(分钟)
    public int month_price;//int(11)    包月价格(单位分)
    public int month_time;//int(11)    包月时长(天)
    public String permit_time="";//varchar(100)    准入时间段（17:00-08:30）
    public String timeout_info="";//varchar(150)    计费信息(首停2小时3元，之后1元/小时)
    public int rcr_type;//int(11)    停车收费类型0：普通车位停车1：时间段包月停车
    public int rcr_state;//int(11)    是否有效0：有效1：无效
    public int rcr_discount;//int(11)    是否可以使用优费券:0：可以使用1：无法使用
    public String car_displacement="";//varchar(60)    车辆排量
    public int car_type;//int(11)    车牌类型0：未知车牌:、1：蓝牌小汽车、2：:黑牌小汽车、3：单排黄牌、4：双排黄牌、5：警车车牌、6：武警车牌、7：个性化车牌、8：单排军车牌、9：双排军车牌、10：使馆车牌、11：香港进出中国大陆车牌、12：农用车牌、13：教练车牌、14：澳门进出中国大陆车牌、15：双层武警车牌、16：武警总队车牌、17：双层武警总队车牌
    public int car_code_color;//int(11)    车牌颜色0：未知、1：蓝色、2：黄色、3：白色、4：黑色、5：绿色
    public int is_time_bucket;//int(11)    是否按时间段收费0:不按时间段收费1：按时间段收费
    public String time_bucket="";//varchar(100)    收费时间段文字说明例如：白天9：00-12：00每小时2元
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String rcr_md5="";//varchar(80)    规则MD5校验pi_id+start_price+start_time+charging+charging_time+rcr_type+rcr_discount+car_type+car_sign_type+is_time_bucket
    public int is_default;//int(11)    是否是默认规则0:不是1：是
    public String area_code="";//varchar(20)    省市区代码
    public String note="";//varchar(100)    备注
    public int free_minute;//int(11)    多少分钟之内进出免费
    public int is_free_minute;//int(11)    多少分钟之内进出免费是否开启0:不开启1：开启
    public int roadside_type;//int(11)    占道停车是否按次数收费0:按时间收费1：按次数收费



    public long getRcr_id(){
        return rcr_id;
    }

    public void setRcr_id(long value){
        this.rcr_id= value;
    }

    public long getPi_id(){
        return pi_id;
    }

    public void setPi_id(long value){
        this.pi_id= value;
    }

    public int getStart_price(){
        return start_price;
    }

    public void setStart_price(int value){
        this.start_price= value;
    }

    public int getStart_time(){
        return start_time;
    }

    public void setStart_time(int value){
        this.start_time= value;
    }

    public int getCharging(){
        return charging;
    }

    public void setCharging(int value){
        this.charging= value;
    }

    public int getCharging_time(){
        return charging_time;
    }

    public void setCharging_time(int value){
        this.charging_time= value;
    }

    public int getMonth_price(){
        return month_price;
    }

    public void setMonth_price(int value){
        this.month_price= value;
    }

    public int getMonth_time(){
        return month_time;
    }

    public void setMonth_time(int value){
        this.month_time= value;
    }

    public String getPermit_time(){
        return permit_time;
    }

    public void setPermit_time(String value){
    	if(value == null){
           value = "";
        }
        this.permit_time= value;
    }

    public String getTimeout_info(){
        return timeout_info;
    }

    public void setTimeout_info(String value){
    	if(value == null){
           value = "";
        }
        this.timeout_info= value;
    }

    public int getRcr_type(){
        return rcr_type;
    }

    public void setRcr_type(int value){
        this.rcr_type= value;
    }

    public int getRcr_state(){
        return rcr_state;
    }

    public void setRcr_state(int value){
        this.rcr_state= value;
    }

    public int getRcr_discount(){
        return rcr_discount;
    }

    public void setRcr_discount(int value){
        this.rcr_discount= value;
    }

    public String getCar_displacement(){
        return car_displacement;
    }

    public void setCar_displacement(String value){
    	if(value == null){
           value = "";
        }
        this.car_displacement= value;
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

    public int getIs_time_bucket(){
        return is_time_bucket;
    }

    public void setIs_time_bucket(int value){
        this.is_time_bucket= value;
    }

    public String getTime_bucket(){
        return time_bucket;
    }

    public void setTime_bucket(String value){
    	if(value == null){
           value = "";
        }
        this.time_bucket= value;
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

    public String getRcr_md5(){
        return rcr_md5;
    }

    public void setRcr_md5(String value){
    	if(value == null){
           value = "";
        }
        this.rcr_md5= value;
    }

    public int getIs_default(){
        return is_default;
    }

    public void setIs_default(int value){
        this.is_default= value;
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

    public String getNote(){
        return note;
    }

    public void setNote(String value){
    	if(value == null){
           value = "";
        }
        this.note= value;
    }

    public int getFree_minute(){
        return free_minute;
    }

    public void setFree_minute(int value){
        this.free_minute= value;
    }

    public int getIs_free_minute(){
        return is_free_minute;
    }

    public void setIs_free_minute(int value){
        this.is_free_minute= value;
    }

    public int getRoadside_type(){
        return roadside_type;
    }

    public void setRoadside_type(int value){
        this.roadside_type= value;
    }



    public static Rental_charging_rule newRental_charging_rule(long rcr_id, long pi_id, int start_price, int start_time, int charging, int charging_time, int month_price, int month_time, String permit_time, String timeout_info, int rcr_type, int rcr_state, int rcr_discount, String car_displacement, int car_type, int car_code_color, int is_time_bucket, String time_bucket, java.util.Date ctime, java.util.Date utime, String rcr_md5, int is_default, String area_code, String note, int free_minute, int is_free_minute, int roadside_type) {
        Rental_charging_rule ret = new Rental_charging_rule();
        ret.setRcr_id(rcr_id);
        ret.setPi_id(pi_id);
        ret.setStart_price(start_price);
        ret.setStart_time(start_time);
        ret.setCharging(charging);
        ret.setCharging_time(charging_time);
        ret.setMonth_price(month_price);
        ret.setMonth_time(month_time);
        ret.setPermit_time(permit_time);
        ret.setTimeout_info(timeout_info);
        ret.setRcr_type(rcr_type);
        ret.setRcr_state(rcr_state);
        ret.setRcr_discount(rcr_discount);
        ret.setCar_displacement(car_displacement);
        ret.setCar_type(car_type);
        ret.setCar_code_color(car_code_color);
        ret.setIs_time_bucket(is_time_bucket);
        ret.setTime_bucket(time_bucket);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setRcr_md5(rcr_md5);
        ret.setIs_default(is_default);
        ret.setArea_code(area_code);
        ret.setNote(note);
        ret.setFree_minute(free_minute);
        ret.setIs_free_minute(is_free_minute);
        ret.setRoadside_type(roadside_type);
        return ret;    
    }

    public void assignment(Rental_charging_rule rental_charging_rule) {
        long rcr_id = rental_charging_rule.getRcr_id();
        long pi_id = rental_charging_rule.getPi_id();
        int start_price = rental_charging_rule.getStart_price();
        int start_time = rental_charging_rule.getStart_time();
        int charging = rental_charging_rule.getCharging();
        int charging_time = rental_charging_rule.getCharging_time();
        int month_price = rental_charging_rule.getMonth_price();
        int month_time = rental_charging_rule.getMonth_time();
        String permit_time = rental_charging_rule.getPermit_time();
        String timeout_info = rental_charging_rule.getTimeout_info();
        int rcr_type = rental_charging_rule.getRcr_type();
        int rcr_state = rental_charging_rule.getRcr_state();
        int rcr_discount = rental_charging_rule.getRcr_discount();
        String car_displacement = rental_charging_rule.getCar_displacement();
        int car_type = rental_charging_rule.getCar_type();
        int car_code_color = rental_charging_rule.getCar_code_color();
        int is_time_bucket = rental_charging_rule.getIs_time_bucket();
        String time_bucket = rental_charging_rule.getTime_bucket();
        java.util.Date ctime = rental_charging_rule.getCtime();
        java.util.Date utime = rental_charging_rule.getUtime();
        String rcr_md5 = rental_charging_rule.getRcr_md5();
        int is_default = rental_charging_rule.getIs_default();
        String area_code = rental_charging_rule.getArea_code();
        String note = rental_charging_rule.getNote();
        int free_minute = rental_charging_rule.getFree_minute();
        int is_free_minute = rental_charging_rule.getIs_free_minute();
        int roadside_type = rental_charging_rule.getRoadside_type();

        this.setRcr_id(rcr_id);
        this.setPi_id(pi_id);
        this.setStart_price(start_price);
        this.setStart_time(start_time);
        this.setCharging(charging);
        this.setCharging_time(charging_time);
        this.setMonth_price(month_price);
        this.setMonth_time(month_time);
        this.setPermit_time(permit_time);
        this.setTimeout_info(timeout_info);
        this.setRcr_type(rcr_type);
        this.setRcr_state(rcr_state);
        this.setRcr_discount(rcr_discount);
        this.setCar_displacement(car_displacement);
        this.setCar_type(car_type);
        this.setCar_code_color(car_code_color);
        this.setIs_time_bucket(is_time_bucket);
        this.setTime_bucket(time_bucket);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setRcr_md5(rcr_md5);
        this.setIs_default(is_default);
        this.setArea_code(area_code);
        this.setNote(note);
        this.setFree_minute(free_minute);
        this.setIs_free_minute(is_free_minute);
        this.setRoadside_type(roadside_type);

    }

    @SuppressWarnings("unused")
    public static void getRental_charging_rule(Rental_charging_rule rental_charging_rule ){
        long rcr_id = rental_charging_rule.getRcr_id();
        long pi_id = rental_charging_rule.getPi_id();
        int start_price = rental_charging_rule.getStart_price();
        int start_time = rental_charging_rule.getStart_time();
        int charging = rental_charging_rule.getCharging();
        int charging_time = rental_charging_rule.getCharging_time();
        int month_price = rental_charging_rule.getMonth_price();
        int month_time = rental_charging_rule.getMonth_time();
        String permit_time = rental_charging_rule.getPermit_time();
        String timeout_info = rental_charging_rule.getTimeout_info();
        int rcr_type = rental_charging_rule.getRcr_type();
        int rcr_state = rental_charging_rule.getRcr_state();
        int rcr_discount = rental_charging_rule.getRcr_discount();
        String car_displacement = rental_charging_rule.getCar_displacement();
        int car_type = rental_charging_rule.getCar_type();
        int car_code_color = rental_charging_rule.getCar_code_color();
        int is_time_bucket = rental_charging_rule.getIs_time_bucket();
        String time_bucket = rental_charging_rule.getTime_bucket();
        java.util.Date ctime = rental_charging_rule.getCtime();
        java.util.Date utime = rental_charging_rule.getUtime();
        String rcr_md5 = rental_charging_rule.getRcr_md5();
        int is_default = rental_charging_rule.getIs_default();
        String area_code = rental_charging_rule.getArea_code();
        String note = rental_charging_rule.getNote();
        int free_minute = rental_charging_rule.getFree_minute();
        int is_free_minute = rental_charging_rule.getIs_free_minute();
        int roadside_type = rental_charging_rule.getRoadside_type();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Rental_charging_rule rental_charging_rule){
        long rcr_id = rental_charging_rule.getRcr_id();
        long pi_id = rental_charging_rule.getPi_id();
        int start_price = rental_charging_rule.getStart_price();
        int start_time = rental_charging_rule.getStart_time();
        int charging = rental_charging_rule.getCharging();
        int charging_time = rental_charging_rule.getCharging_time();
        int month_price = rental_charging_rule.getMonth_price();
        int month_time = rental_charging_rule.getMonth_time();
        String permit_time = rental_charging_rule.getPermit_time();
        String timeout_info = rental_charging_rule.getTimeout_info();
        int rcr_type = rental_charging_rule.getRcr_type();
        int rcr_state = rental_charging_rule.getRcr_state();
        int rcr_discount = rental_charging_rule.getRcr_discount();
        String car_displacement = rental_charging_rule.getCar_displacement();
        int car_type = rental_charging_rule.getCar_type();
        int car_code_color = rental_charging_rule.getCar_code_color();
        int is_time_bucket = rental_charging_rule.getIs_time_bucket();
        String time_bucket = rental_charging_rule.getTime_bucket();
        java.util.Date ctime = rental_charging_rule.getCtime();
        java.util.Date utime = rental_charging_rule.getUtime();
        String rcr_md5 = rental_charging_rule.getRcr_md5();
        int is_default = rental_charging_rule.getIs_default();
        String area_code = rental_charging_rule.getArea_code();
        String note = rental_charging_rule.getNote();
        int free_minute = rental_charging_rule.getFree_minute();
        int is_free_minute = rental_charging_rule.getIs_free_minute();
        int roadside_type = rental_charging_rule.getRoadside_type();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("rcr_id",rcr_id);
        _ret.put("pi_id",pi_id);
        _ret.put("start_price",start_price);
        _ret.put("start_time",start_time);
        _ret.put("charging",charging);
        _ret.put("charging_time",charging_time);
        _ret.put("month_price",month_price);
        _ret.put("month_time",month_time);
        _ret.put("permit_time",permit_time);
        _ret.put("timeout_info",timeout_info);
        _ret.put("rcr_type",rcr_type);
        _ret.put("rcr_state",rcr_state);
        _ret.put("rcr_discount",rcr_discount);
        _ret.put("car_displacement",car_displacement);
        _ret.put("car_type",car_type);
        _ret.put("car_code_color",car_code_color);
        _ret.put("is_time_bucket",is_time_bucket);
        _ret.put("time_bucket",time_bucket);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("rcr_md5",rcr_md5);
        _ret.put("is_default",is_default);
        _ret.put("area_code",area_code);
        _ret.put("note",note);
        _ret.put("free_minute",free_minute);
        _ret.put("is_free_minute",is_free_minute);
        _ret.put("roadside_type",roadside_type);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Rental_charging_rule clone2(){
        try{
            return (Rental_charging_rule) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
