package com.park.bean;

import java.io.*;
import java.util.*;

//carcode_park_rent
@SuppressWarnings({"serial"})
public class Carcode_park_rent implements Cloneable , Serializable{

    //public static String[] carrays ={"cpr_id","pi_id","area_code","pi_name","unit_price","starttime","endtime","stime","utime","ui_id","ui_nd","car_code","permit_time","rent_type","is_del","is_expire","note","client_rule_id","address_name","rd_id","client_father_orderid"};

    public long cpr_id;//bigint(20)    主键ID
    public long pi_id;//bigint(20)    停车场主键ID
    public String area_code="";//varchar(30)    停车场地址编码
    public String pi_name="";//varchar(80)    停车场名称
    public int unit_price;//int(11)    租赁每个月单价上次成功续租或者租赁金额（单位分)
    public java.util.Date starttime=new java.util.Date();//datetime    开始时间
    public java.util.Date endtime=new java.util.Date();//datetime    到期时间
    public java.util.Date stime=new java.util.Date();//datetime    服务器端接收时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public long ui_id;//bigint(20)    用户主键ID
    public String ui_nd="";//varchar(80)    用户ND
    public String car_code="";//varchar(80)    用户车牌号
    public String permit_time="";//varchar(80)    允许时间段（8：00-23：00）上次成功续租或者租赁允许时间段
    public int rent_type;//int(11)    租赁类型（0：未指定1：普通分时间段租赁2：垮天分时间段租赁3：全天）
    public int is_del;//int(11)    是否逻辑删除(0:正常1：删除)
    public int is_expire;//int(11)    是否已经到期（0：未到期1：已经到期）
    public String note="";//varchar(100)    备注
    public String client_rule_id="";//varchar(80)    客户端的规则ID
    public String address_name="";//varchar(255)    停车场地址
    public long rd_id;//bigint(20)    rent_defer表主键ID（最后一次修改carcode_park_rent表对应的ID）
    public String client_father_orderid="";//varchar(80)    客户端根节点订单号



    public long getCpr_id(){
        return cpr_id;
    }

    public void setCpr_id(long value){
        this.cpr_id= value;
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

    public String getPi_name(){
        return pi_name;
    }

    public void setPi_name(String value){
    	if(value == null){
           value = "";
        }
        this.pi_name= value;
    }

    public int getUnit_price(){
        return unit_price;
    }

    public void setUnit_price(int value){
        this.unit_price= value;
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

    public java.util.Date getStime(){
        return stime;
    }

    public void setStime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.stime= value;
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

    public long getUi_id(){
        return ui_id;
    }

    public void setUi_id(long value){
        this.ui_id= value;
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

    public String getCar_code(){
        return car_code;
    }

    public void setCar_code(String value){
    	if(value == null){
           value = "";
        }
        this.car_code= value;
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

    public int getRent_type(){
        return rent_type;
    }

    public void setRent_type(int value){
        this.rent_type= value;
    }

    public int getIs_del(){
        return is_del;
    }

    public void setIs_del(int value){
        this.is_del= value;
    }

    public int getIs_expire(){
        return is_expire;
    }

    public void setIs_expire(int value){
        this.is_expire= value;
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

    public String getClient_rule_id(){
        return client_rule_id;
    }

    public void setClient_rule_id(String value){
    	if(value == null){
           value = "";
        }
        this.client_rule_id= value;
    }

    public String getAddress_name(){
        return address_name;
    }

    public void setAddress_name(String value){
    	if(value == null){
           value = "";
        }
        this.address_name= value;
    }

    public long getRd_id(){
        return rd_id;
    }

    public void setRd_id(long value){
        this.rd_id= value;
    }

    public String getClient_father_orderid(){
        return client_father_orderid;
    }

    public void setClient_father_orderid(String value){
    	if(value == null){
           value = "";
        }
        this.client_father_orderid= value;
    }



    public static Carcode_park_rent newCarcode_park_rent(long cpr_id, long pi_id, String area_code, String pi_name, int unit_price, java.util.Date starttime, java.util.Date endtime, java.util.Date stime, java.util.Date utime, long ui_id, String ui_nd, String car_code, String permit_time, int rent_type, int is_del, int is_expire, String note, String client_rule_id, String address_name, long rd_id, String client_father_orderid) {
        Carcode_park_rent ret = new Carcode_park_rent();
        ret.setCpr_id(cpr_id);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setPi_name(pi_name);
        ret.setUnit_price(unit_price);
        ret.setStarttime(starttime);
        ret.setEndtime(endtime);
        ret.setStime(stime);
        ret.setUtime(utime);
        ret.setUi_id(ui_id);
        ret.setUi_nd(ui_nd);
        ret.setCar_code(car_code);
        ret.setPermit_time(permit_time);
        ret.setRent_type(rent_type);
        ret.setIs_del(is_del);
        ret.setIs_expire(is_expire);
        ret.setNote(note);
        ret.setClient_rule_id(client_rule_id);
        ret.setAddress_name(address_name);
        ret.setRd_id(rd_id);
        ret.setClient_father_orderid(client_father_orderid);
        return ret;    
    }

    public void assignment(Carcode_park_rent carcode_park_rent) {
        long cpr_id = carcode_park_rent.getCpr_id();
        long pi_id = carcode_park_rent.getPi_id();
        String area_code = carcode_park_rent.getArea_code();
        String pi_name = carcode_park_rent.getPi_name();
        int unit_price = carcode_park_rent.getUnit_price();
        java.util.Date starttime = carcode_park_rent.getStarttime();
        java.util.Date endtime = carcode_park_rent.getEndtime();
        java.util.Date stime = carcode_park_rent.getStime();
        java.util.Date utime = carcode_park_rent.getUtime();
        long ui_id = carcode_park_rent.getUi_id();
        String ui_nd = carcode_park_rent.getUi_nd();
        String car_code = carcode_park_rent.getCar_code();
        String permit_time = carcode_park_rent.getPermit_time();
        int rent_type = carcode_park_rent.getRent_type();
        int is_del = carcode_park_rent.getIs_del();
        int is_expire = carcode_park_rent.getIs_expire();
        String note = carcode_park_rent.getNote();
        String client_rule_id = carcode_park_rent.getClient_rule_id();
        String address_name = carcode_park_rent.getAddress_name();
        long rd_id = carcode_park_rent.getRd_id();
        String client_father_orderid = carcode_park_rent.getClient_father_orderid();

        this.setCpr_id(cpr_id);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setPi_name(pi_name);
        this.setUnit_price(unit_price);
        this.setStarttime(starttime);
        this.setEndtime(endtime);
        this.setStime(stime);
        this.setUtime(utime);
        this.setUi_id(ui_id);
        this.setUi_nd(ui_nd);
        this.setCar_code(car_code);
        this.setPermit_time(permit_time);
        this.setRent_type(rent_type);
        this.setIs_del(is_del);
        this.setIs_expire(is_expire);
        this.setNote(note);
        this.setClient_rule_id(client_rule_id);
        this.setAddress_name(address_name);
        this.setRd_id(rd_id);
        this.setClient_father_orderid(client_father_orderid);

    }

    @SuppressWarnings("unused")
    public static void getCarcode_park_rent(Carcode_park_rent carcode_park_rent ){
        long cpr_id = carcode_park_rent.getCpr_id();
        long pi_id = carcode_park_rent.getPi_id();
        String area_code = carcode_park_rent.getArea_code();
        String pi_name = carcode_park_rent.getPi_name();
        int unit_price = carcode_park_rent.getUnit_price();
        java.util.Date starttime = carcode_park_rent.getStarttime();
        java.util.Date endtime = carcode_park_rent.getEndtime();
        java.util.Date stime = carcode_park_rent.getStime();
        java.util.Date utime = carcode_park_rent.getUtime();
        long ui_id = carcode_park_rent.getUi_id();
        String ui_nd = carcode_park_rent.getUi_nd();
        String car_code = carcode_park_rent.getCar_code();
        String permit_time = carcode_park_rent.getPermit_time();
        int rent_type = carcode_park_rent.getRent_type();
        int is_del = carcode_park_rent.getIs_del();
        int is_expire = carcode_park_rent.getIs_expire();
        String note = carcode_park_rent.getNote();
        String client_rule_id = carcode_park_rent.getClient_rule_id();
        String address_name = carcode_park_rent.getAddress_name();
        long rd_id = carcode_park_rent.getRd_id();
        String client_father_orderid = carcode_park_rent.getClient_father_orderid();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Carcode_park_rent carcode_park_rent){
        long cpr_id = carcode_park_rent.getCpr_id();
        long pi_id = carcode_park_rent.getPi_id();
        String area_code = carcode_park_rent.getArea_code();
        String pi_name = carcode_park_rent.getPi_name();
        int unit_price = carcode_park_rent.getUnit_price();
        java.util.Date starttime = carcode_park_rent.getStarttime();
        java.util.Date endtime = carcode_park_rent.getEndtime();
        java.util.Date stime = carcode_park_rent.getStime();
        java.util.Date utime = carcode_park_rent.getUtime();
        long ui_id = carcode_park_rent.getUi_id();
        String ui_nd = carcode_park_rent.getUi_nd();
        String car_code = carcode_park_rent.getCar_code();
        String permit_time = carcode_park_rent.getPermit_time();
        int rent_type = carcode_park_rent.getRent_type();
        int is_del = carcode_park_rent.getIs_del();
        int is_expire = carcode_park_rent.getIs_expire();
        String note = carcode_park_rent.getNote();
        String client_rule_id = carcode_park_rent.getClient_rule_id();
        String address_name = carcode_park_rent.getAddress_name();
        long rd_id = carcode_park_rent.getRd_id();
        String client_father_orderid = carcode_park_rent.getClient_father_orderid();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("cpr_id",cpr_id);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("pi_name",pi_name);
        _ret.put("unit_price",unit_price);
        _ret.put("starttime",starttime);
        _ret.put("endtime",endtime);
        _ret.put("stime",stime);
        _ret.put("utime",utime);
        _ret.put("ui_id",ui_id);
        _ret.put("ui_nd",ui_nd);
        _ret.put("car_code",car_code);
        _ret.put("permit_time",permit_time);
        _ret.put("rent_type",rent_type);
        _ret.put("is_del",is_del);
        _ret.put("is_expire",is_expire);
        _ret.put("note",note);
        _ret.put("client_rule_id",client_rule_id);
        _ret.put("address_name",address_name);
        _ret.put("rd_id",rd_id);
        _ret.put("client_father_orderid",client_father_orderid);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Carcode_park_rent clone2(){
        try{
            return (Carcode_park_rent) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
