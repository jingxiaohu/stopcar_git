package com.park.bean;

import java.io.*;
import java.util.*;

//pda_owe_order_cover
@SuppressWarnings({"serial"})
public class Pda_owe_order_cover implements Cloneable , Serializable{

    //public static String[] carrays ={"pooc_id","pooc_nd","car_code","order_id","old_pi_id","old_area_code","old_pu_id","old_pu_nd","old_mac","now_pi_id","now_area_code","now_pu_id","now_pu_nd","now_mac","money","ctime","utime","state","old_ui_id","old_uuid","now_ui_id","now_uuid","note","pay_source"};

    public long pooc_id;//bigint(20)    主键ID
    public String pooc_nd="";//varchar(70)    ND
    public String car_code="";//varchar(30)    车牌号
    public String order_id="";//varchar(70)    欠费订单ID
    public long old_pi_id;//bigint(20)    原欠费订单停车场ID
    public String old_area_code="";//varchar(30)    原欠费订单停车场区域编码
    public long old_pu_id;//bigint(20)    原欠费订单所属商户ID
    public String old_pu_nd="";//varchar(70)    原欠费订单所属商户ND
    public String old_mac="";//varchar(70)    原PDA欠费设备唯一标识符MAC
    public long now_pi_id;//bigint(20)    PDA补交费用所在停车场ID
    public String now_area_code="";//varchar(30)    PDA补交费用停车场区域编码
    public long now_pu_id;//bigint(20)    PDA补交费用所属商户ID
    public String now_pu_nd="";//varchar(70)    PDA补交费用所属商户ND(商户编号)
    public String now_mac="";//varchar(70)    PDA补交设备唯一标识MAC
    public long money;//bigint(20)    PDA补交费用(单位分)
    public java.util.Date ctime=new java.util.Date();//datetime    创建时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public int state;//int(11)    补交是否完成（0：未完成1：完成）
    public long old_ui_id;//bigint(20)    原欠费订单所属用户主键ID
    public String old_uuid="";//varchar(70)    原欠费订单所属用户ND
    public long now_ui_id;//bigint(20)    当前补交欠费用户主键ID
    public String now_uuid="";//varchar(70)    当前补交欠费用户ND
    public String note="";//varchar(100)    备注
    public int pay_source;//int(11)    支付类型0：现金支付1:支付宝2：微信3：银联4：钱包5:龙支付6:ETC快捷支付7：扫脸支付8：指纹支付9：指静脉支付



    public long getPooc_id(){
        return pooc_id;
    }

    public void setPooc_id(long value){
        this.pooc_id= value;
    }

    public String getPooc_nd(){
        return pooc_nd;
    }

    public void setPooc_nd(String value){
    	if(value == null){
           value = "";
        }
        this.pooc_nd= value;
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

    public String getOrder_id(){
        return order_id;
    }

    public void setOrder_id(String value){
    	if(value == null){
           value = "";
        }
        this.order_id= value;
    }

    public long getOld_pi_id(){
        return old_pi_id;
    }

    public void setOld_pi_id(long value){
        this.old_pi_id= value;
    }

    public String getOld_area_code(){
        return old_area_code;
    }

    public void setOld_area_code(String value){
    	if(value == null){
           value = "";
        }
        this.old_area_code= value;
    }

    public long getOld_pu_id(){
        return old_pu_id;
    }

    public void setOld_pu_id(long value){
        this.old_pu_id= value;
    }

    public String getOld_pu_nd(){
        return old_pu_nd;
    }

    public void setOld_pu_nd(String value){
    	if(value == null){
           value = "";
        }
        this.old_pu_nd= value;
    }

    public String getOld_mac(){
        return old_mac;
    }

    public void setOld_mac(String value){
    	if(value == null){
           value = "";
        }
        this.old_mac= value;
    }

    public long getNow_pi_id(){
        return now_pi_id;
    }

    public void setNow_pi_id(long value){
        this.now_pi_id= value;
    }

    public String getNow_area_code(){
        return now_area_code;
    }

    public void setNow_area_code(String value){
    	if(value == null){
           value = "";
        }
        this.now_area_code= value;
    }

    public long getNow_pu_id(){
        return now_pu_id;
    }

    public void setNow_pu_id(long value){
        this.now_pu_id= value;
    }

    public String getNow_pu_nd(){
        return now_pu_nd;
    }

    public void setNow_pu_nd(String value){
    	if(value == null){
           value = "";
        }
        this.now_pu_nd= value;
    }

    public String getNow_mac(){
        return now_mac;
    }

    public void setNow_mac(String value){
    	if(value == null){
           value = "";
        }
        this.now_mac= value;
    }

    public long getMoney(){
        return money;
    }

    public void setMoney(long value){
        this.money= value;
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

    public int getState(){
        return state;
    }

    public void setState(int value){
        this.state= value;
    }

    public long getOld_ui_id(){
        return old_ui_id;
    }

    public void setOld_ui_id(long value){
        this.old_ui_id= value;
    }

    public String getOld_uuid(){
        return old_uuid;
    }

    public void setOld_uuid(String value){
    	if(value == null){
           value = "";
        }
        this.old_uuid= value;
    }

    public long getNow_ui_id(){
        return now_ui_id;
    }

    public void setNow_ui_id(long value){
        this.now_ui_id= value;
    }

    public String getNow_uuid(){
        return now_uuid;
    }

    public void setNow_uuid(String value){
    	if(value == null){
           value = "";
        }
        this.now_uuid= value;
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

    public int getPay_source(){
        return pay_source;
    }

    public void setPay_source(int value){
        this.pay_source= value;
    }



    public static Pda_owe_order_cover newPda_owe_order_cover(long pooc_id, String pooc_nd, String car_code, String order_id, long old_pi_id, String old_area_code, long old_pu_id, String old_pu_nd, String old_mac, long now_pi_id, String now_area_code, long now_pu_id, String now_pu_nd, String now_mac, long money, java.util.Date ctime, java.util.Date utime, int state, long old_ui_id, String old_uuid, long now_ui_id, String now_uuid, String note, int pay_source) {
        Pda_owe_order_cover ret = new Pda_owe_order_cover();
        ret.setPooc_id(pooc_id);
        ret.setPooc_nd(pooc_nd);
        ret.setCar_code(car_code);
        ret.setOrder_id(order_id);
        ret.setOld_pi_id(old_pi_id);
        ret.setOld_area_code(old_area_code);
        ret.setOld_pu_id(old_pu_id);
        ret.setOld_pu_nd(old_pu_nd);
        ret.setOld_mac(old_mac);
        ret.setNow_pi_id(now_pi_id);
        ret.setNow_area_code(now_area_code);
        ret.setNow_pu_id(now_pu_id);
        ret.setNow_pu_nd(now_pu_nd);
        ret.setNow_mac(now_mac);
        ret.setMoney(money);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setState(state);
        ret.setOld_ui_id(old_ui_id);
        ret.setOld_uuid(old_uuid);
        ret.setNow_ui_id(now_ui_id);
        ret.setNow_uuid(now_uuid);
        ret.setNote(note);
        ret.setPay_source(pay_source);
        return ret;    
    }

    public void assignment(Pda_owe_order_cover pda_owe_order_cover) {
        long pooc_id = pda_owe_order_cover.getPooc_id();
        String pooc_nd = pda_owe_order_cover.getPooc_nd();
        String car_code = pda_owe_order_cover.getCar_code();
        String order_id = pda_owe_order_cover.getOrder_id();
        long old_pi_id = pda_owe_order_cover.getOld_pi_id();
        String old_area_code = pda_owe_order_cover.getOld_area_code();
        long old_pu_id = pda_owe_order_cover.getOld_pu_id();
        String old_pu_nd = pda_owe_order_cover.getOld_pu_nd();
        String old_mac = pda_owe_order_cover.getOld_mac();
        long now_pi_id = pda_owe_order_cover.getNow_pi_id();
        String now_area_code = pda_owe_order_cover.getNow_area_code();
        long now_pu_id = pda_owe_order_cover.getNow_pu_id();
        String now_pu_nd = pda_owe_order_cover.getNow_pu_nd();
        String now_mac = pda_owe_order_cover.getNow_mac();
        long money = pda_owe_order_cover.getMoney();
        java.util.Date ctime = pda_owe_order_cover.getCtime();
        java.util.Date utime = pda_owe_order_cover.getUtime();
        int state = pda_owe_order_cover.getState();
        long old_ui_id = pda_owe_order_cover.getOld_ui_id();
        String old_uuid = pda_owe_order_cover.getOld_uuid();
        long now_ui_id = pda_owe_order_cover.getNow_ui_id();
        String now_uuid = pda_owe_order_cover.getNow_uuid();
        String note = pda_owe_order_cover.getNote();
        int pay_source = pda_owe_order_cover.getPay_source();

        this.setPooc_id(pooc_id);
        this.setPooc_nd(pooc_nd);
        this.setCar_code(car_code);
        this.setOrder_id(order_id);
        this.setOld_pi_id(old_pi_id);
        this.setOld_area_code(old_area_code);
        this.setOld_pu_id(old_pu_id);
        this.setOld_pu_nd(old_pu_nd);
        this.setOld_mac(old_mac);
        this.setNow_pi_id(now_pi_id);
        this.setNow_area_code(now_area_code);
        this.setNow_pu_id(now_pu_id);
        this.setNow_pu_nd(now_pu_nd);
        this.setNow_mac(now_mac);
        this.setMoney(money);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setState(state);
        this.setOld_ui_id(old_ui_id);
        this.setOld_uuid(old_uuid);
        this.setNow_ui_id(now_ui_id);
        this.setNow_uuid(now_uuid);
        this.setNote(note);
        this.setPay_source(pay_source);

    }

    @SuppressWarnings("unused")
    public static void getPda_owe_order_cover(Pda_owe_order_cover pda_owe_order_cover ){
        long pooc_id = pda_owe_order_cover.getPooc_id();
        String pooc_nd = pda_owe_order_cover.getPooc_nd();
        String car_code = pda_owe_order_cover.getCar_code();
        String order_id = pda_owe_order_cover.getOrder_id();
        long old_pi_id = pda_owe_order_cover.getOld_pi_id();
        String old_area_code = pda_owe_order_cover.getOld_area_code();
        long old_pu_id = pda_owe_order_cover.getOld_pu_id();
        String old_pu_nd = pda_owe_order_cover.getOld_pu_nd();
        String old_mac = pda_owe_order_cover.getOld_mac();
        long now_pi_id = pda_owe_order_cover.getNow_pi_id();
        String now_area_code = pda_owe_order_cover.getNow_area_code();
        long now_pu_id = pda_owe_order_cover.getNow_pu_id();
        String now_pu_nd = pda_owe_order_cover.getNow_pu_nd();
        String now_mac = pda_owe_order_cover.getNow_mac();
        long money = pda_owe_order_cover.getMoney();
        java.util.Date ctime = pda_owe_order_cover.getCtime();
        java.util.Date utime = pda_owe_order_cover.getUtime();
        int state = pda_owe_order_cover.getState();
        long old_ui_id = pda_owe_order_cover.getOld_ui_id();
        String old_uuid = pda_owe_order_cover.getOld_uuid();
        long now_ui_id = pda_owe_order_cover.getNow_ui_id();
        String now_uuid = pda_owe_order_cover.getNow_uuid();
        String note = pda_owe_order_cover.getNote();
        int pay_source = pda_owe_order_cover.getPay_source();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Pda_owe_order_cover pda_owe_order_cover){
        long pooc_id = pda_owe_order_cover.getPooc_id();
        String pooc_nd = pda_owe_order_cover.getPooc_nd();
        String car_code = pda_owe_order_cover.getCar_code();
        String order_id = pda_owe_order_cover.getOrder_id();
        long old_pi_id = pda_owe_order_cover.getOld_pi_id();
        String old_area_code = pda_owe_order_cover.getOld_area_code();
        long old_pu_id = pda_owe_order_cover.getOld_pu_id();
        String old_pu_nd = pda_owe_order_cover.getOld_pu_nd();
        String old_mac = pda_owe_order_cover.getOld_mac();
        long now_pi_id = pda_owe_order_cover.getNow_pi_id();
        String now_area_code = pda_owe_order_cover.getNow_area_code();
        long now_pu_id = pda_owe_order_cover.getNow_pu_id();
        String now_pu_nd = pda_owe_order_cover.getNow_pu_nd();
        String now_mac = pda_owe_order_cover.getNow_mac();
        long money = pda_owe_order_cover.getMoney();
        java.util.Date ctime = pda_owe_order_cover.getCtime();
        java.util.Date utime = pda_owe_order_cover.getUtime();
        int state = pda_owe_order_cover.getState();
        long old_ui_id = pda_owe_order_cover.getOld_ui_id();
        String old_uuid = pda_owe_order_cover.getOld_uuid();
        long now_ui_id = pda_owe_order_cover.getNow_ui_id();
        String now_uuid = pda_owe_order_cover.getNow_uuid();
        String note = pda_owe_order_cover.getNote();
        int pay_source = pda_owe_order_cover.getPay_source();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("pooc_id",pooc_id);
        _ret.put("pooc_nd",pooc_nd);
        _ret.put("car_code",car_code);
        _ret.put("order_id",order_id);
        _ret.put("old_pi_id",old_pi_id);
        _ret.put("old_area_code",old_area_code);
        _ret.put("old_pu_id",old_pu_id);
        _ret.put("old_pu_nd",old_pu_nd);
        _ret.put("old_mac",old_mac);
        _ret.put("now_pi_id",now_pi_id);
        _ret.put("now_area_code",now_area_code);
        _ret.put("now_pu_id",now_pu_id);
        _ret.put("now_pu_nd",now_pu_nd);
        _ret.put("now_mac",now_mac);
        _ret.put("money",money);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("state",state);
        _ret.put("old_ui_id",old_ui_id);
        _ret.put("old_uuid",old_uuid);
        _ret.put("now_ui_id",now_ui_id);
        _ret.put("now_uuid",now_uuid);
        _ret.put("note",note);
        _ret.put("pay_source",pay_source);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Pda_owe_order_cover clone2(){
        try{
            return (Pda_owe_order_cover) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
