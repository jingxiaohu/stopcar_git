package com.park.bean;

import java.io.*;
import java.util.*;

//park_heartbeat
@SuppressWarnings({"serial"})
public class Park_heartbeat implements Cloneable , Serializable{

    //public static String[] carrays ={"id","pi_id","area_code","num","total","ctime","ctime_num","is_rent","note","carport_yet","carport_space","carport_total","moth_car_num","moth_car_num_space","time_car_num","time_car_num_space","expect_car_num","state"};

    public long id;//bigint(20)    
    public long pi_id;//bigint(20)    停车场主键ID
    public String area_code="";//varchar(30)    省市区编号
    public int num;//int(11)    库存车辆数
    public int total;//int(11)    停车位总数
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public long ctime_num;//bigint(20)    创建时间长整型
    public int is_rent;//int(11)    是否有租赁包月业务0：没有1：有
    public String note="";//varchar(60)    备注
    public int carport_yet;//int(11)    临停已停车位
    public int carport_space;//int(11)    临停空车位个数
    public int carport_total;//int(11)    临停总车位个数
    public int moth_car_num;//int(11)    租赁离线包月车位总个数
    public int moth_car_num_space;//int(11)    租赁离线剩余车位数
    public int time_car_num;//int(11)    时间段包月车位总数.
    public int time_car_num_space;//int(11)    时间段包月车位剩余个数
    public int expect_car_num;//int(11)    已预约车位数
    public int state;//int(11)    是否被处理（下次心跳过来更新状态）0:未处理1：被下次心跳处理



    public long getId(){
        return id;
    }

    public void setId(long value){
        this.id= value;
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

    public int getNum(){
        return num;
    }

    public void setNum(int value){
        this.num= value;
    }

    public int getTotal(){
        return total;
    }

    public void setTotal(int value){
        this.total= value;
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

    public long getCtime_num(){
        return ctime_num;
    }

    public void setCtime_num(long value){
        this.ctime_num= value;
    }

    public int getIs_rent(){
        return is_rent;
    }

    public void setIs_rent(int value){
        this.is_rent= value;
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

    public int getCarport_yet(){
        return carport_yet;
    }

    public void setCarport_yet(int value){
        this.carport_yet= value;
    }

    public int getCarport_space(){
        return carport_space;
    }

    public void setCarport_space(int value){
        this.carport_space= value;
    }

    public int getCarport_total(){
        return carport_total;
    }

    public void setCarport_total(int value){
        this.carport_total= value;
    }

    public int getMoth_car_num(){
        return moth_car_num;
    }

    public void setMoth_car_num(int value){
        this.moth_car_num= value;
    }

    public int getMoth_car_num_space(){
        return moth_car_num_space;
    }

    public void setMoth_car_num_space(int value){
        this.moth_car_num_space= value;
    }

    public int getTime_car_num(){
        return time_car_num;
    }

    public void setTime_car_num(int value){
        this.time_car_num= value;
    }

    public int getTime_car_num_space(){
        return time_car_num_space;
    }

    public void setTime_car_num_space(int value){
        this.time_car_num_space= value;
    }

    public int getExpect_car_num(){
        return expect_car_num;
    }

    public void setExpect_car_num(int value){
        this.expect_car_num= value;
    }

    public int getState(){
        return state;
    }

    public void setState(int value){
        this.state= value;
    }



    public static Park_heartbeat newPark_heartbeat(long id, long pi_id, String area_code, int num, int total, java.util.Date ctime, long ctime_num, int is_rent, String note, int carport_yet, int carport_space, int carport_total, int moth_car_num, int moth_car_num_space, int time_car_num, int time_car_num_space, int expect_car_num, int state) {
        Park_heartbeat ret = new Park_heartbeat();
        ret.setId(id);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setNum(num);
        ret.setTotal(total);
        ret.setCtime(ctime);
        ret.setCtime_num(ctime_num);
        ret.setIs_rent(is_rent);
        ret.setNote(note);
        ret.setCarport_yet(carport_yet);
        ret.setCarport_space(carport_space);
        ret.setCarport_total(carport_total);
        ret.setMoth_car_num(moth_car_num);
        ret.setMoth_car_num_space(moth_car_num_space);
        ret.setTime_car_num(time_car_num);
        ret.setTime_car_num_space(time_car_num_space);
        ret.setExpect_car_num(expect_car_num);
        ret.setState(state);
        return ret;    
    }

    public void assignment(Park_heartbeat park_heartbeat) {
        long id = park_heartbeat.getId();
        long pi_id = park_heartbeat.getPi_id();
        String area_code = park_heartbeat.getArea_code();
        int num = park_heartbeat.getNum();
        int total = park_heartbeat.getTotal();
        java.util.Date ctime = park_heartbeat.getCtime();
        long ctime_num = park_heartbeat.getCtime_num();
        int is_rent = park_heartbeat.getIs_rent();
        String note = park_heartbeat.getNote();
        int carport_yet = park_heartbeat.getCarport_yet();
        int carport_space = park_heartbeat.getCarport_space();
        int carport_total = park_heartbeat.getCarport_total();
        int moth_car_num = park_heartbeat.getMoth_car_num();
        int moth_car_num_space = park_heartbeat.getMoth_car_num_space();
        int time_car_num = park_heartbeat.getTime_car_num();
        int time_car_num_space = park_heartbeat.getTime_car_num_space();
        int expect_car_num = park_heartbeat.getExpect_car_num();
        int state = park_heartbeat.getState();

        this.setId(id);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setNum(num);
        this.setTotal(total);
        this.setCtime(ctime);
        this.setCtime_num(ctime_num);
        this.setIs_rent(is_rent);
        this.setNote(note);
        this.setCarport_yet(carport_yet);
        this.setCarport_space(carport_space);
        this.setCarport_total(carport_total);
        this.setMoth_car_num(moth_car_num);
        this.setMoth_car_num_space(moth_car_num_space);
        this.setTime_car_num(time_car_num);
        this.setTime_car_num_space(time_car_num_space);
        this.setExpect_car_num(expect_car_num);
        this.setState(state);

    }

    @SuppressWarnings("unused")
    public static void getPark_heartbeat(Park_heartbeat park_heartbeat ){
        long id = park_heartbeat.getId();
        long pi_id = park_heartbeat.getPi_id();
        String area_code = park_heartbeat.getArea_code();
        int num = park_heartbeat.getNum();
        int total = park_heartbeat.getTotal();
        java.util.Date ctime = park_heartbeat.getCtime();
        long ctime_num = park_heartbeat.getCtime_num();
        int is_rent = park_heartbeat.getIs_rent();
        String note = park_heartbeat.getNote();
        int carport_yet = park_heartbeat.getCarport_yet();
        int carport_space = park_heartbeat.getCarport_space();
        int carport_total = park_heartbeat.getCarport_total();
        int moth_car_num = park_heartbeat.getMoth_car_num();
        int moth_car_num_space = park_heartbeat.getMoth_car_num_space();
        int time_car_num = park_heartbeat.getTime_car_num();
        int time_car_num_space = park_heartbeat.getTime_car_num_space();
        int expect_car_num = park_heartbeat.getExpect_car_num();
        int state = park_heartbeat.getState();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Park_heartbeat park_heartbeat){
        long id = park_heartbeat.getId();
        long pi_id = park_heartbeat.getPi_id();
        String area_code = park_heartbeat.getArea_code();
        int num = park_heartbeat.getNum();
        int total = park_heartbeat.getTotal();
        java.util.Date ctime = park_heartbeat.getCtime();
        long ctime_num = park_heartbeat.getCtime_num();
        int is_rent = park_heartbeat.getIs_rent();
        String note = park_heartbeat.getNote();
        int carport_yet = park_heartbeat.getCarport_yet();
        int carport_space = park_heartbeat.getCarport_space();
        int carport_total = park_heartbeat.getCarport_total();
        int moth_car_num = park_heartbeat.getMoth_car_num();
        int moth_car_num_space = park_heartbeat.getMoth_car_num_space();
        int time_car_num = park_heartbeat.getTime_car_num();
        int time_car_num_space = park_heartbeat.getTime_car_num_space();
        int expect_car_num = park_heartbeat.getExpect_car_num();
        int state = park_heartbeat.getState();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("num",num);
        _ret.put("total",total);
        _ret.put("ctime",ctime);
        _ret.put("ctime_num",ctime_num);
        _ret.put("is_rent",is_rent);
        _ret.put("note",note);
        _ret.put("carport_yet",carport_yet);
        _ret.put("carport_space",carport_space);
        _ret.put("carport_total",carport_total);
        _ret.put("moth_car_num",moth_car_num);
        _ret.put("moth_car_num_space",moth_car_num_space);
        _ret.put("time_car_num",time_car_num);
        _ret.put("time_car_num_space",time_car_num_space);
        _ret.put("expect_car_num",expect_car_num);
        _ret.put("state",state);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Park_heartbeat clone2(){
        try{
            return (Park_heartbeat) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
