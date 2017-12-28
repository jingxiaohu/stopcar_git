package com.park.bean;

import java.io.*;
import java.util.*;

//rent_defer
@SuppressWarnings({"serial"})
public class Rent_defer implements Cloneable , Serializable{

    //public static String[] carrays ={"rd_id","rent_order_id","pi_id","area_code","pi_name","money","unit_price","starttime","endtime","month_num","ctime","stime","utime","ui_id","ui_nd","car_code","pu_id","pu_nd","permit_time","pay_state","defer_state","up_orderid","flag","pay_source","mq_state","rent_type","is_del","note","father_order_id","son_order_id","is_expire","client_order_id","client_rule_id","allege_state","cpr_id"};

    public long rd_id;//bigint(20)    主键ID
    public String rent_order_id="";//varchar(80)    租赁订单号
    public long pi_id;//bigint(20)    停车场主键ID
    public String area_code="";//varchar(30)    停车场地址编码
    public String pi_name="";//varchar(80)    停车场名称
    public int money;//int(11)    租赁续期金额（单位分）
    public int unit_price;//int(11)    租赁每个月单价（单位分）
    public java.util.Date starttime=new java.util.Date();//datetime    开始时间
    public java.util.Date endtime=new java.util.Date();//datetime    到期时间
    public int month_num;//int(11)    续约月份个数
    public java.util.Date ctime=new java.util.Date();//datetime    客户端创建时间
    public java.util.Date stime=new java.util.Date();//datetime    服务器端接收时间
    public java.util.Date utime=new java.util.Date();//datetime    更新时间
    public long ui_id;//bigint(20)    用户主键ID
    public String ui_nd="";//varchar(80)    用户ND
    public String car_code="";//varchar(80)    用户车牌号
    public long pu_id;//bigint(20)    商户主键ID
    public String pu_nd="";//varchar(80)    商户ND
    public String permit_time="";//varchar(80)    允许时间段（8：00-23：00）
    public int pay_state;//int(11)    支付状态（0：未支付1：支付成功2：支付失败）
    public int defer_state;//int(11)    续约状态（0：未续约1：续约中2：续约成功3：续约失败4：续约失败-退款钱包）
    public String up_orderid="";//varchar(80)    平台支付流水单号
    public int flag;//int(11)    续约来源（0：未知1：线下道闸租赁2：线上3：线下道闸续租）
    public int pay_source;//int(11)    支付类型0：现金支付1:支付宝2：微信3：银联4：钱包5:龙支付6:ETC快捷支付7：扫脸支付8：指纹支付9：指静脉支付
    public int mq_state;//int(11)    是否MQ推送（0：没有1：推送成功2：推送失败）
    public int rent_type;//int(11)    租赁类型（0：未指定1：普通分时间段租赁2：垮天分时间段租赁3：全天）
    public int is_del;//int(11)    是否逻辑删除(0:正常1：删除)
    public String note="";//varchar(100)    备注
    public String father_order_id="";//varchar(80)    父亲订单（父订单）ID
    public String son_order_id="";//varchar(80)    子续约订单ID（目前废弃）
    public int is_expire;//int(11)    是否已经到期（0：未到期1：已经到期）
    public String client_order_id="";//varchar(80)    客户端的租赁订单号
    public String client_rule_id="";//varchar(80)    客户端的规则ID
    public int allege_state;//int(11)    申述状态0:未申述1：申述中2：申述失败3：申述成功
    public long cpr_id;//bigint(20)    外键carcode_park_rent表主键ID



    public long getRd_id(){
        return rd_id;
    }

    public void setRd_id(long value){
        this.rd_id= value;
    }

    public String getRent_order_id(){
        return rent_order_id;
    }

    public void setRent_order_id(String value){
    	if(value == null){
           value = "";
        }
        this.rent_order_id= value;
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

    public int getMoney(){
        return money;
    }

    public void setMoney(int value){
        this.money= value;
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

    public int getMonth_num(){
        return month_num;
    }

    public void setMonth_num(int value){
        this.month_num= value;
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

    public long getPu_id(){
        return pu_id;
    }

    public void setPu_id(long value){
        this.pu_id= value;
    }

    public String getPu_nd(){
        return pu_nd;
    }

    public void setPu_nd(String value){
    	if(value == null){
           value = "";
        }
        this.pu_nd= value;
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

    public int getPay_state(){
        return pay_state;
    }

    public void setPay_state(int value){
        this.pay_state= value;
    }

    public int getDefer_state(){
        return defer_state;
    }

    public void setDefer_state(int value){
        this.defer_state= value;
    }

    public String getUp_orderid(){
        return up_orderid;
    }

    public void setUp_orderid(String value){
    	if(value == null){
           value = "";
        }
        this.up_orderid= value;
    }

    public int getFlag(){
        return flag;
    }

    public void setFlag(int value){
        this.flag= value;
    }

    public int getPay_source(){
        return pay_source;
    }

    public void setPay_source(int value){
        this.pay_source= value;
    }

    public int getMq_state(){
        return mq_state;
    }

    public void setMq_state(int value){
        this.mq_state= value;
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

    public String getNote(){
        return note;
    }

    public void setNote(String value){
    	if(value == null){
           value = "";
        }
        this.note= value;
    }

    public String getFather_order_id(){
        return father_order_id;
    }

    public void setFather_order_id(String value){
    	if(value == null){
           value = "";
        }
        this.father_order_id= value;
    }

    public String getSon_order_id(){
        return son_order_id;
    }

    public void setSon_order_id(String value){
    	if(value == null){
           value = "";
        }
        this.son_order_id= value;
    }

    public int getIs_expire(){
        return is_expire;
    }

    public void setIs_expire(int value){
        this.is_expire= value;
    }

    public String getClient_order_id(){
        return client_order_id;
    }

    public void setClient_order_id(String value){
    	if(value == null){
           value = "";
        }
        this.client_order_id= value;
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

    public int getAllege_state(){
        return allege_state;
    }

    public void setAllege_state(int value){
        this.allege_state= value;
    }

    public long getCpr_id(){
        return cpr_id;
    }

    public void setCpr_id(long value){
        this.cpr_id= value;
    }



    public static Rent_defer newRent_defer(long rd_id, String rent_order_id, long pi_id, String area_code, String pi_name, int money, int unit_price, java.util.Date starttime, java.util.Date endtime, int month_num, java.util.Date ctime, java.util.Date stime, java.util.Date utime, long ui_id, String ui_nd, String car_code, long pu_id, String pu_nd, String permit_time, int pay_state, int defer_state, String up_orderid, int flag, int pay_source, int mq_state, int rent_type, int is_del, String note, String father_order_id, String son_order_id, int is_expire, String client_order_id, String client_rule_id, int allege_state, long cpr_id) {
        Rent_defer ret = new Rent_defer();
        ret.setRd_id(rd_id);
        ret.setRent_order_id(rent_order_id);
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setPi_name(pi_name);
        ret.setMoney(money);
        ret.setUnit_price(unit_price);
        ret.setStarttime(starttime);
        ret.setEndtime(endtime);
        ret.setMonth_num(month_num);
        ret.setCtime(ctime);
        ret.setStime(stime);
        ret.setUtime(utime);
        ret.setUi_id(ui_id);
        ret.setUi_nd(ui_nd);
        ret.setCar_code(car_code);
        ret.setPu_id(pu_id);
        ret.setPu_nd(pu_nd);
        ret.setPermit_time(permit_time);
        ret.setPay_state(pay_state);
        ret.setDefer_state(defer_state);
        ret.setUp_orderid(up_orderid);
        ret.setFlag(flag);
        ret.setPay_source(pay_source);
        ret.setMq_state(mq_state);
        ret.setRent_type(rent_type);
        ret.setIs_del(is_del);
        ret.setNote(note);
        ret.setFather_order_id(father_order_id);
        ret.setSon_order_id(son_order_id);
        ret.setIs_expire(is_expire);
        ret.setClient_order_id(client_order_id);
        ret.setClient_rule_id(client_rule_id);
        ret.setAllege_state(allege_state);
        ret.setCpr_id(cpr_id);
        return ret;    
    }

    public void assignment(Rent_defer rent_defer) {
        long rd_id = rent_defer.getRd_id();
        String rent_order_id = rent_defer.getRent_order_id();
        long pi_id = rent_defer.getPi_id();
        String area_code = rent_defer.getArea_code();
        String pi_name = rent_defer.getPi_name();
        int money = rent_defer.getMoney();
        int unit_price = rent_defer.getUnit_price();
        java.util.Date starttime = rent_defer.getStarttime();
        java.util.Date endtime = rent_defer.getEndtime();
        int month_num = rent_defer.getMonth_num();
        java.util.Date ctime = rent_defer.getCtime();
        java.util.Date stime = rent_defer.getStime();
        java.util.Date utime = rent_defer.getUtime();
        long ui_id = rent_defer.getUi_id();
        String ui_nd = rent_defer.getUi_nd();
        String car_code = rent_defer.getCar_code();
        long pu_id = rent_defer.getPu_id();
        String pu_nd = rent_defer.getPu_nd();
        String permit_time = rent_defer.getPermit_time();
        int pay_state = rent_defer.getPay_state();
        int defer_state = rent_defer.getDefer_state();
        String up_orderid = rent_defer.getUp_orderid();
        int flag = rent_defer.getFlag();
        int pay_source = rent_defer.getPay_source();
        int mq_state = rent_defer.getMq_state();
        int rent_type = rent_defer.getRent_type();
        int is_del = rent_defer.getIs_del();
        String note = rent_defer.getNote();
        String father_order_id = rent_defer.getFather_order_id();
        String son_order_id = rent_defer.getSon_order_id();
        int is_expire = rent_defer.getIs_expire();
        String client_order_id = rent_defer.getClient_order_id();
        String client_rule_id = rent_defer.getClient_rule_id();
        int allege_state = rent_defer.getAllege_state();
        long cpr_id = rent_defer.getCpr_id();

        this.setRd_id(rd_id);
        this.setRent_order_id(rent_order_id);
        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setPi_name(pi_name);
        this.setMoney(money);
        this.setUnit_price(unit_price);
        this.setStarttime(starttime);
        this.setEndtime(endtime);
        this.setMonth_num(month_num);
        this.setCtime(ctime);
        this.setStime(stime);
        this.setUtime(utime);
        this.setUi_id(ui_id);
        this.setUi_nd(ui_nd);
        this.setCar_code(car_code);
        this.setPu_id(pu_id);
        this.setPu_nd(pu_nd);
        this.setPermit_time(permit_time);
        this.setPay_state(pay_state);
        this.setDefer_state(defer_state);
        this.setUp_orderid(up_orderid);
        this.setFlag(flag);
        this.setPay_source(pay_source);
        this.setMq_state(mq_state);
        this.setRent_type(rent_type);
        this.setIs_del(is_del);
        this.setNote(note);
        this.setFather_order_id(father_order_id);
        this.setSon_order_id(son_order_id);
        this.setIs_expire(is_expire);
        this.setClient_order_id(client_order_id);
        this.setClient_rule_id(client_rule_id);
        this.setAllege_state(allege_state);
        this.setCpr_id(cpr_id);

    }

    @SuppressWarnings("unused")
    public static void getRent_defer(Rent_defer rent_defer ){
        long rd_id = rent_defer.getRd_id();
        String rent_order_id = rent_defer.getRent_order_id();
        long pi_id = rent_defer.getPi_id();
        String area_code = rent_defer.getArea_code();
        String pi_name = rent_defer.getPi_name();
        int money = rent_defer.getMoney();
        int unit_price = rent_defer.getUnit_price();
        java.util.Date starttime = rent_defer.getStarttime();
        java.util.Date endtime = rent_defer.getEndtime();
        int month_num = rent_defer.getMonth_num();
        java.util.Date ctime = rent_defer.getCtime();
        java.util.Date stime = rent_defer.getStime();
        java.util.Date utime = rent_defer.getUtime();
        long ui_id = rent_defer.getUi_id();
        String ui_nd = rent_defer.getUi_nd();
        String car_code = rent_defer.getCar_code();
        long pu_id = rent_defer.getPu_id();
        String pu_nd = rent_defer.getPu_nd();
        String permit_time = rent_defer.getPermit_time();
        int pay_state = rent_defer.getPay_state();
        int defer_state = rent_defer.getDefer_state();
        String up_orderid = rent_defer.getUp_orderid();
        int flag = rent_defer.getFlag();
        int pay_source = rent_defer.getPay_source();
        int mq_state = rent_defer.getMq_state();
        int rent_type = rent_defer.getRent_type();
        int is_del = rent_defer.getIs_del();
        String note = rent_defer.getNote();
        String father_order_id = rent_defer.getFather_order_id();
        String son_order_id = rent_defer.getSon_order_id();
        int is_expire = rent_defer.getIs_expire();
        String client_order_id = rent_defer.getClient_order_id();
        String client_rule_id = rent_defer.getClient_rule_id();
        int allege_state = rent_defer.getAllege_state();
        long cpr_id = rent_defer.getCpr_id();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Rent_defer rent_defer){
        long rd_id = rent_defer.getRd_id();
        String rent_order_id = rent_defer.getRent_order_id();
        long pi_id = rent_defer.getPi_id();
        String area_code = rent_defer.getArea_code();
        String pi_name = rent_defer.getPi_name();
        int money = rent_defer.getMoney();
        int unit_price = rent_defer.getUnit_price();
        java.util.Date starttime = rent_defer.getStarttime();
        java.util.Date endtime = rent_defer.getEndtime();
        int month_num = rent_defer.getMonth_num();
        java.util.Date ctime = rent_defer.getCtime();
        java.util.Date stime = rent_defer.getStime();
        java.util.Date utime = rent_defer.getUtime();
        long ui_id = rent_defer.getUi_id();
        String ui_nd = rent_defer.getUi_nd();
        String car_code = rent_defer.getCar_code();
        long pu_id = rent_defer.getPu_id();
        String pu_nd = rent_defer.getPu_nd();
        String permit_time = rent_defer.getPermit_time();
        int pay_state = rent_defer.getPay_state();
        int defer_state = rent_defer.getDefer_state();
        String up_orderid = rent_defer.getUp_orderid();
        int flag = rent_defer.getFlag();
        int pay_source = rent_defer.getPay_source();
        int mq_state = rent_defer.getMq_state();
        int rent_type = rent_defer.getRent_type();
        int is_del = rent_defer.getIs_del();
        String note = rent_defer.getNote();
        String father_order_id = rent_defer.getFather_order_id();
        String son_order_id = rent_defer.getSon_order_id();
        int is_expire = rent_defer.getIs_expire();
        String client_order_id = rent_defer.getClient_order_id();
        String client_rule_id = rent_defer.getClient_rule_id();
        int allege_state = rent_defer.getAllege_state();
        long cpr_id = rent_defer.getCpr_id();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("rd_id",rd_id);
        _ret.put("rent_order_id",rent_order_id);
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("pi_name",pi_name);
        _ret.put("money",money);
        _ret.put("unit_price",unit_price);
        _ret.put("starttime",starttime);
        _ret.put("endtime",endtime);
        _ret.put("month_num",month_num);
        _ret.put("ctime",ctime);
        _ret.put("stime",stime);
        _ret.put("utime",utime);
        _ret.put("ui_id",ui_id);
        _ret.put("ui_nd",ui_nd);
        _ret.put("car_code",car_code);
        _ret.put("pu_id",pu_id);
        _ret.put("pu_nd",pu_nd);
        _ret.put("permit_time",permit_time);
        _ret.put("pay_state",pay_state);
        _ret.put("defer_state",defer_state);
        _ret.put("up_orderid",up_orderid);
        _ret.put("flag",flag);
        _ret.put("pay_source",pay_source);
        _ret.put("mq_state",mq_state);
        _ret.put("rent_type",rent_type);
        _ret.put("is_del",is_del);
        _ret.put("note",note);
        _ret.put("father_order_id",father_order_id);
        _ret.put("son_order_id",son_order_id);
        _ret.put("is_expire",is_expire);
        _ret.put("client_order_id",client_order_id);
        _ret.put("client_rule_id",client_rule_id);
        _ret.put("allege_state",allege_state);
        _ret.put("cpr_id",cpr_id);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Rent_defer clone2(){
        try{
            return (Rent_defer) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
