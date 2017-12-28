package com.park.bean;

import java.io.*;
import java.util.*;

//car_in_out
@SuppressWarnings({"serial"})
public class Car_in_out implements Cloneable , Serializable{

    //public static String[] carrays ={"cio_id","pi_id","pd_id","ui_id","car_code","is_enter","in_out","in_out_code","ctime","utime","area_code","note","car_type","car_code_color","order_id","out_type","is_rent","rent_remain_time","is_local_month","ui_nd","ui_tel","is_sync","gov_num","stime"};

    public long cio_id;//bigint(20)    主键ID
    public long pi_id;//bigint(20)    场地车库主键ID
    public long pd_id;//bigint(20)    出入口设备主键ID
    public long ui_id;//bigint(20)    用户ID
    public String car_code="";//varchar(60)    车牌号
    public int is_enter;//int(11)    入库或者出库：0：入库1：出库
    public String in_out="";//varchar(60)    出口或者入口入口：enter出口：exit
    public String in_out_code="";//varchar(60)    出入口编号A/B/C
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public String area_code="";//varchar(60)    省市县编号
    public String note="";//varchar(100)    备注
    public int car_type;//int(11)    车牌类型:车牌类型0：未知车牌:、1：蓝牌小汽车、2：:黑牌小汽车、3：单排黄牌、4：双排黄牌、5：警车车牌、6：武警车牌、7：个性化车牌、8：单排军车牌、9：双排军车牌、10：使馆车牌、11：香港进出中国大陆车牌、12：农用车牌、13：教练车牌、14：澳门进出中国大陆车牌、15：双层武警车牌、16：武警总队车牌、17：双层武警总队车牌
    public int car_code_color;//int(11)    车牌颜色0：未知、1：蓝色、2：黄色、3：白色、4：黑色、5：绿色
    public String order_id="";//varchar(80)    订单编号
    public int out_type;//int(11)    入库/出库类型:(0:正常出入库1：道闸本地临停出入库2：道闸本地包月出入库3：异常出入库)4:异步数据上传出入库（网络异常）
    public int is_rent;//int(11)    是否是租赁车辆0:不是1：是
    public long rent_remain_time;//bigint(20)    租赁车辆距离结束时间还有多少时长（单位毫秒）
    public int is_local_month;//int(11)    是否是道闸本地包月车辆0:不是1：是
    public String ui_nd="";//varchar(100)    用户唯一标识uuid
    public String ui_tel="";//varchar(100)    用户电话号码
    public int is_sync;//int(11)    是否是网络异常导致的数据上传：0不是1：是
    public String gov_num="";//varchar(60)    地磁停车场车位编号（政府部门统一分配）
    public java.util.Date stime=new java.util.Date();//datetime    服务器端接收数据创建时间



    public long getCio_id(){
        return cio_id;
    }

    public void setCio_id(long value){
        this.cio_id= value;
    }

    public long getPi_id(){
        return pi_id;
    }

    public void setPi_id(long value){
        this.pi_id= value;
    }

    public long getPd_id(){
        return pd_id;
    }

    public void setPd_id(long value){
        this.pd_id= value;
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

    public int getIs_enter(){
        return is_enter;
    }

    public void setIs_enter(int value){
        this.is_enter= value;
    }

    public String getIn_out(){
        return in_out;
    }

    public void setIn_out(String value){
    	if(value == null){
           value = "";
        }
        this.in_out= value;
    }

    public String getIn_out_code(){
        return in_out_code;
    }

    public void setIn_out_code(String value){
    	if(value == null){
           value = "";
        }
        this.in_out_code= value;
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

    public String getOrder_id(){
        return order_id;
    }

    public void setOrder_id(String value){
    	if(value == null){
           value = "";
        }
        this.order_id= value;
    }

    public int getOut_type(){
        return out_type;
    }

    public void setOut_type(int value){
        this.out_type= value;
    }

    public int getIs_rent(){
        return is_rent;
    }

    public void setIs_rent(int value){
        this.is_rent= value;
    }

    public long getRent_remain_time(){
        return rent_remain_time;
    }

    public void setRent_remain_time(long value){
        this.rent_remain_time= value;
    }

    public int getIs_local_month(){
        return is_local_month;
    }

    public void setIs_local_month(int value){
        this.is_local_month= value;
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

    public int getIs_sync(){
        return is_sync;
    }

    public void setIs_sync(int value){
        this.is_sync= value;
    }

    public String getGov_num(){
        return gov_num;
    }

    public void setGov_num(String value){
    	if(value == null){
           value = "";
        }
        this.gov_num= value;
    }

    public java.util.Date getStime(){
        return stime;
    }

    public void setStime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.stime= value;
    }



    public static Car_in_out newCar_in_out(long cio_id, long pi_id, long pd_id, long ui_id, String car_code, int is_enter, String in_out, String in_out_code, java.util.Date ctime, java.util.Date utime, String area_code, String note, int car_type, int car_code_color, String order_id, int out_type, int is_rent, long rent_remain_time, int is_local_month, String ui_nd, String ui_tel, int is_sync, String gov_num, java.util.Date stime) {
        Car_in_out ret = new Car_in_out();
        ret.setCio_id(cio_id);
        ret.setPi_id(pi_id);
        ret.setPd_id(pd_id);
        ret.setUi_id(ui_id);
        ret.setCar_code(car_code);
        ret.setIs_enter(is_enter);
        ret.setIn_out(in_out);
        ret.setIn_out_code(in_out_code);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setArea_code(area_code);
        ret.setNote(note);
        ret.setCar_type(car_type);
        ret.setCar_code_color(car_code_color);
        ret.setOrder_id(order_id);
        ret.setOut_type(out_type);
        ret.setIs_rent(is_rent);
        ret.setRent_remain_time(rent_remain_time);
        ret.setIs_local_month(is_local_month);
        ret.setUi_nd(ui_nd);
        ret.setUi_tel(ui_tel);
        ret.setIs_sync(is_sync);
        ret.setGov_num(gov_num);
        ret.setStime(stime);
        return ret;    
    }

    public void assignment(Car_in_out car_in_out) {
        long cio_id = car_in_out.getCio_id();
        long pi_id = car_in_out.getPi_id();
        long pd_id = car_in_out.getPd_id();
        long ui_id = car_in_out.getUi_id();
        String car_code = car_in_out.getCar_code();
        int is_enter = car_in_out.getIs_enter();
        String in_out = car_in_out.getIn_out();
        String in_out_code = car_in_out.getIn_out_code();
        java.util.Date ctime = car_in_out.getCtime();
        java.util.Date utime = car_in_out.getUtime();
        String area_code = car_in_out.getArea_code();
        String note = car_in_out.getNote();
        int car_type = car_in_out.getCar_type();
        int car_code_color = car_in_out.getCar_code_color();
        String order_id = car_in_out.getOrder_id();
        int out_type = car_in_out.getOut_type();
        int is_rent = car_in_out.getIs_rent();
        long rent_remain_time = car_in_out.getRent_remain_time();
        int is_local_month = car_in_out.getIs_local_month();
        String ui_nd = car_in_out.getUi_nd();
        String ui_tel = car_in_out.getUi_tel();
        int is_sync = car_in_out.getIs_sync();
        String gov_num = car_in_out.getGov_num();
        java.util.Date stime = car_in_out.getStime();

        this.setCio_id(cio_id);
        this.setPi_id(pi_id);
        this.setPd_id(pd_id);
        this.setUi_id(ui_id);
        this.setCar_code(car_code);
        this.setIs_enter(is_enter);
        this.setIn_out(in_out);
        this.setIn_out_code(in_out_code);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setArea_code(area_code);
        this.setNote(note);
        this.setCar_type(car_type);
        this.setCar_code_color(car_code_color);
        this.setOrder_id(order_id);
        this.setOut_type(out_type);
        this.setIs_rent(is_rent);
        this.setRent_remain_time(rent_remain_time);
        this.setIs_local_month(is_local_month);
        this.setUi_nd(ui_nd);
        this.setUi_tel(ui_tel);
        this.setIs_sync(is_sync);
        this.setGov_num(gov_num);
        this.setStime(stime);

    }

    @SuppressWarnings("unused")
    public static void getCar_in_out(Car_in_out car_in_out ){
        long cio_id = car_in_out.getCio_id();
        long pi_id = car_in_out.getPi_id();
        long pd_id = car_in_out.getPd_id();
        long ui_id = car_in_out.getUi_id();
        String car_code = car_in_out.getCar_code();
        int is_enter = car_in_out.getIs_enter();
        String in_out = car_in_out.getIn_out();
        String in_out_code = car_in_out.getIn_out_code();
        java.util.Date ctime = car_in_out.getCtime();
        java.util.Date utime = car_in_out.getUtime();
        String area_code = car_in_out.getArea_code();
        String note = car_in_out.getNote();
        int car_type = car_in_out.getCar_type();
        int car_code_color = car_in_out.getCar_code_color();
        String order_id = car_in_out.getOrder_id();
        int out_type = car_in_out.getOut_type();
        int is_rent = car_in_out.getIs_rent();
        long rent_remain_time = car_in_out.getRent_remain_time();
        int is_local_month = car_in_out.getIs_local_month();
        String ui_nd = car_in_out.getUi_nd();
        String ui_tel = car_in_out.getUi_tel();
        int is_sync = car_in_out.getIs_sync();
        String gov_num = car_in_out.getGov_num();
        java.util.Date stime = car_in_out.getStime();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Car_in_out car_in_out){
        long cio_id = car_in_out.getCio_id();
        long pi_id = car_in_out.getPi_id();
        long pd_id = car_in_out.getPd_id();
        long ui_id = car_in_out.getUi_id();
        String car_code = car_in_out.getCar_code();
        int is_enter = car_in_out.getIs_enter();
        String in_out = car_in_out.getIn_out();
        String in_out_code = car_in_out.getIn_out_code();
        java.util.Date ctime = car_in_out.getCtime();
        java.util.Date utime = car_in_out.getUtime();
        String area_code = car_in_out.getArea_code();
        String note = car_in_out.getNote();
        int car_type = car_in_out.getCar_type();
        int car_code_color = car_in_out.getCar_code_color();
        String order_id = car_in_out.getOrder_id();
        int out_type = car_in_out.getOut_type();
        int is_rent = car_in_out.getIs_rent();
        long rent_remain_time = car_in_out.getRent_remain_time();
        int is_local_month = car_in_out.getIs_local_month();
        String ui_nd = car_in_out.getUi_nd();
        String ui_tel = car_in_out.getUi_tel();
        int is_sync = car_in_out.getIs_sync();
        String gov_num = car_in_out.getGov_num();
        java.util.Date stime = car_in_out.getStime();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("cio_id",cio_id);
        _ret.put("pi_id",pi_id);
        _ret.put("pd_id",pd_id);
        _ret.put("ui_id",ui_id);
        _ret.put("car_code",car_code);
        _ret.put("is_enter",is_enter);
        _ret.put("in_out",in_out);
        _ret.put("in_out_code",in_out_code);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("area_code",area_code);
        _ret.put("note",note);
        _ret.put("car_type",car_type);
        _ret.put("car_code_color",car_code_color);
        _ret.put("order_id",order_id);
        _ret.put("out_type",out_type);
        _ret.put("is_rent",is_rent);
        _ret.put("rent_remain_time",rent_remain_time);
        _ret.put("is_local_month",is_local_month);
        _ret.put("ui_nd",ui_nd);
        _ret.put("ui_tel",ui_tel);
        _ret.put("is_sync",is_sync);
        _ret.put("gov_num",gov_num);
        _ret.put("stime",stime);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Car_in_out clone2(){
        try{
            return (Car_in_out) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
