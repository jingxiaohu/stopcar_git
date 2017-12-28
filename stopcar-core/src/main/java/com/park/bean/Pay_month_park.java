package com.park.bean;

import java.io.*;
import java.util.*;

//pay_month_park
@SuppressWarnings({"serial"})
public class Pay_month_park implements Cloneable , Serializable{

    //public static String[] carrays ={"id","pi_id","ui_id","car_code","money","pp_state","ctime","utime","month_num","pay_source","my_order","other_order","pay_type","pp_versioncode","phone_type","order_type","start_time","end_time","month_info","allege_state","area_code","is_del","upc_id","discount_money","note","discount_name","discount_type","final_time","address_name","base_rate","outtime_rate","outtime_money","outtime_time","outtime_minute","outtime_start_price","outtime_start_minute","outtime_charge_money","outtime_charge_minute","outtime_other_order","outtime_paytime","outtime_paystate","permit_time","is_24hours","is_open","open_time","is_cash","park_type","scan_time","arrive_time","temp_money","pi_name","is_over","cancel_state","pu_id","pu_nd","lng","lat","is_expire","rent_state","ui_nd","ai_id","ai_money","ai_effect","ui_tel","magnetic_state","gov_num","stime","up_orderid"};

    public long id;//bigint(20)    主键ID
    public long pi_id;//bigint(20)    支付停车场主键ID
    public long ui_id;//bigint(20)    用户ID
    public String car_code="";//varchar(60)    用户支付车牌号
    public int money;//int(11)    支付金额（单位分）
    public int pp_state;//int(11)    支付状态0:未支付1：已经支付
    public java.util.Date ctime=new java.util.Date();//datetime    下单时间
    public java.util.Date utime=new java.util.Date();//datetime    更新支付状态时间
    public int month_num;//int(11)    包月租凭月数
    public int pay_source;//int(11)    支付类型1:支付宝2：微信3：银联4：钱包5:龙支付6:ETC快捷支付
    public String my_order="";//varchar(80)    我们的订单号
    public String other_order="";//varchar(80)    第三方的支付单号
    public int pay_type;//int(11)    支付类型0:手动输入密码支付1：快捷支付（服务器可以请求第三方直接扣款）
    public String pp_versioncode="";//varchar(30)    当前APPSDK版本号（内部升级版本代号）
    public int phone_type;//int(11)    手机类型0:android1：IOS
    public int order_type;//int(11)    下单类型0:普通下单1：预约下单2：租赁包月订单
    public java.util.Date start_time=new java.util.Date();//datetime    启始时间
    public java.util.Date end_time=new java.util.Date();//datetime    到期时间
    public String month_info="";//varchar(255)    包月提示信息
    public int allege_state;//int(11)    申述状态0:未申述1：申述中2：申述失败3：申述成功
    public String area_code="";//varchar(60)    省市县编号
    public int is_del;//int(11)    删除状态0:正常1：假删除
    public long upc_id;//bigint(20)    用户优惠券表主键ID
    public long discount_money;//bigint(20)    抵扣优惠金额（单位分）
    public String note="";//varchar(100)    备注
    public String discount_name="";//varchar(150)    抵扣优惠券名称
    public int discount_type;//int(11)    优惠券类型0:金额券1：折扣券
    public int final_time;//int(11)    结算时计费时长（单位分钟）
    public String address_name="";//varchar(150)    停车场地点名称
    public String base_rate="";//varchar(60)    基础费率（300元/月）
    public String outtime_rate="";//varchar(100)    超时费率(首停2小时3元，之后1元/小时)
    public int outtime_money;//int(11)    超时金额
    public java.util.Date outtime_time=new java.util.Date();//datetime    超时结算时间（2016-08-1012：00：00）
    public int outtime_minute;//int(11)    超时时长（单位分钟）
    public int outtime_start_price;//int(11)    超时起步价
    public int outtime_start_minute;//int(11)    超时起步量纲时长（单位分钟）
    public int outtime_charge_money;//int(11)    超时计费价格（单位分）
    public int outtime_charge_minute;//int(11)    超时计费量纲时长（单位分钟）
    public String outtime_other_order="";//varchar(80)    超时第三方支付订单号
    public java.util.Date outtime_paytime=new java.util.Date();//datetime    超时缴费时间
    public int outtime_paystate;//int(11)    超时缴费状态0:未缴费1：已经缴费
    public String permit_time="";//varchar(60)    准入时间段（17:00-08:30）
    public int is_24hours;//int(11)    是否是24小时包月0:不是24小时包月1：是24小时包月
    public int is_open;//int(11)    是否开闸0:未开闸1：已开闸
    public java.util.Date open_time=new java.util.Date();//datetime    开闸时间
    public int is_cash;//int(11)    是否现金支付0：在线支付1：现金支付
    public int park_type;//int(11)    停车场类型0：道闸停车场1：占道车场2：露天立体车库停车场
    public java.util.Date scan_time=new java.util.Date();//datetime    是否可以停车缴费时间（摄像头是否扫描到了：仅限道闸停车使用）
    public java.util.Date arrive_time=new java.util.Date();//datetime    该次入库的到达时间
    public long temp_money;//bigint(20)    临停费用累计
    public String pi_name="";//varchar(100)    停车场名称
    public int is_over;//int(11)    订单是否完成或者逃逸(0:没有完成1：完成2：车辆逃逸3：未交费)
    public int cancel_state;//int(11)    订单关闭状态0:没有关闭1：已经关闭2：订单异常
    public long pu_id;//bigint(20)    商户主键ID
    public String pu_nd="";//varchar(100)    商户编号
    public double lng;//double    场地经度
    public double lat;//double    场地纬度
    public int is_expire;//int(11)    是否已到期（0：没有到期1：已经到期）
    public int rent_state;//int(11)    租赁状态0:待支付1：租赁中2：租赁成功3：租赁失败--解绑租赁金额
    public String ui_nd="";//varchar(100)    用户唯一标识uuid
    public long ai_id;//bigint(20)    活动ID
    public int ai_money;//int(11)    活动优惠金额（单位分）
    public int ai_effect;//int(11)    活动优惠是否生效（0：没有1：生效）
    public String ui_tel="";//varchar(100)    用户电话号码
    public int magnetic_state;//int(11)    智能磁场入库出库状态（1：已入库2：已出库3：车辆逃逸4：其它故障）
    public String gov_num="";//varchar(60)    智能磁场车位编号（政府部门统一分配）
    public java.util.Date stime=new java.util.Date();//datetime    服务器端接收数据创建时间
    public String up_orderid="";//varchar(80)    第三方支付user_pay中的流水单号(订单支付、欠费订单补交)



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

    public int getMoney(){
        return money;
    }

    public void setMoney(int value){
        this.money= value;
    }

    public int getPp_state(){
        return pp_state;
    }

    public void setPp_state(int value){
        this.pp_state= value;
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

    public int getMonth_num(){
        return month_num;
    }

    public void setMonth_num(int value){
        this.month_num= value;
    }

    public int getPay_source(){
        return pay_source;
    }

    public void setPay_source(int value){
        this.pay_source= value;
    }

    public String getMy_order(){
        return my_order;
    }

    public void setMy_order(String value){
    	if(value == null){
           value = "";
        }
        this.my_order= value;
    }

    public String getOther_order(){
        return other_order;
    }

    public void setOther_order(String value){
    	if(value == null){
           value = "";
        }
        this.other_order= value;
    }

    public int getPay_type(){
        return pay_type;
    }

    public void setPay_type(int value){
        this.pay_type= value;
    }

    public String getPp_versioncode(){
        return pp_versioncode;
    }

    public void setPp_versioncode(String value){
    	if(value == null){
           value = "";
        }
        this.pp_versioncode= value;
    }

    public int getPhone_type(){
        return phone_type;
    }

    public void setPhone_type(int value){
        this.phone_type= value;
    }

    public int getOrder_type(){
        return order_type;
    }

    public void setOrder_type(int value){
        this.order_type= value;
    }

    public java.util.Date getStart_time(){
        return start_time;
    }

    public void setStart_time(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.start_time= value;
    }

    public java.util.Date getEnd_time(){
        return end_time;
    }

    public void setEnd_time(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.end_time= value;
    }

    public String getMonth_info(){
        return month_info;
    }

    public void setMonth_info(String value){
    	if(value == null){
           value = "";
        }
        this.month_info= value;
    }

    public int getAllege_state(){
        return allege_state;
    }

    public void setAllege_state(int value){
        this.allege_state= value;
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

    public int getIs_del(){
        return is_del;
    }

    public void setIs_del(int value){
        this.is_del= value;
    }

    public long getUpc_id(){
        return upc_id;
    }

    public void setUpc_id(long value){
        this.upc_id= value;
    }

    public long getDiscount_money(){
        return discount_money;
    }

    public void setDiscount_money(long value){
        this.discount_money= value;
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

    public String getDiscount_name(){
        return discount_name;
    }

    public void setDiscount_name(String value){
    	if(value == null){
           value = "";
        }
        this.discount_name= value;
    }

    public int getDiscount_type(){
        return discount_type;
    }

    public void setDiscount_type(int value){
        this.discount_type= value;
    }

    public int getFinal_time(){
        return final_time;
    }

    public void setFinal_time(int value){
        this.final_time= value;
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

    public String getBase_rate(){
        return base_rate;
    }

    public void setBase_rate(String value){
    	if(value == null){
           value = "";
        }
        this.base_rate= value;
    }

    public String getOuttime_rate(){
        return outtime_rate;
    }

    public void setOuttime_rate(String value){
    	if(value == null){
           value = "";
        }
        this.outtime_rate= value;
    }

    public int getOuttime_money(){
        return outtime_money;
    }

    public void setOuttime_money(int value){
        this.outtime_money= value;
    }

    public java.util.Date getOuttime_time(){
        return outtime_time;
    }

    public void setOuttime_time(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.outtime_time= value;
    }

    public int getOuttime_minute(){
        return outtime_minute;
    }

    public void setOuttime_minute(int value){
        this.outtime_minute= value;
    }

    public int getOuttime_start_price(){
        return outtime_start_price;
    }

    public void setOuttime_start_price(int value){
        this.outtime_start_price= value;
    }

    public int getOuttime_start_minute(){
        return outtime_start_minute;
    }

    public void setOuttime_start_minute(int value){
        this.outtime_start_minute= value;
    }

    public int getOuttime_charge_money(){
        return outtime_charge_money;
    }

    public void setOuttime_charge_money(int value){
        this.outtime_charge_money= value;
    }

    public int getOuttime_charge_minute(){
        return outtime_charge_minute;
    }

    public void setOuttime_charge_minute(int value){
        this.outtime_charge_minute= value;
    }

    public String getOuttime_other_order(){
        return outtime_other_order;
    }

    public void setOuttime_other_order(String value){
    	if(value == null){
           value = "";
        }
        this.outtime_other_order= value;
    }

    public java.util.Date getOuttime_paytime(){
        return outtime_paytime;
    }

    public void setOuttime_paytime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.outtime_paytime= value;
    }

    public int getOuttime_paystate(){
        return outtime_paystate;
    }

    public void setOuttime_paystate(int value){
        this.outtime_paystate= value;
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

    public int getIs_24hours(){
        return is_24hours;
    }

    public void setIs_24hours(int value){
        this.is_24hours= value;
    }

    public int getIs_open(){
        return is_open;
    }

    public void setIs_open(int value){
        this.is_open= value;
    }

    public java.util.Date getOpen_time(){
        return open_time;
    }

    public void setOpen_time(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.open_time= value;
    }

    public int getIs_cash(){
        return is_cash;
    }

    public void setIs_cash(int value){
        this.is_cash= value;
    }

    public int getPark_type(){
        return park_type;
    }

    public void setPark_type(int value){
        this.park_type= value;
    }

    public java.util.Date getScan_time(){
        return scan_time;
    }

    public void setScan_time(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.scan_time= value;
    }

    public java.util.Date getArrive_time(){
        return arrive_time;
    }

    public void setArrive_time(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.arrive_time= value;
    }

    public long getTemp_money(){
        return temp_money;
    }

    public void setTemp_money(long value){
        this.temp_money= value;
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

    public int getIs_over(){
        return is_over;
    }

    public void setIs_over(int value){
        this.is_over= value;
    }

    public int getCancel_state(){
        return cancel_state;
    }

    public void setCancel_state(int value){
        this.cancel_state= value;
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

    public double getLng(){
        return lng;
    }

    public void setLng(double value){
        this.lng= value;
    }

    public double getLat(){
        return lat;
    }

    public void setLat(double value){
        this.lat= value;
    }

    public int getIs_expire(){
        return is_expire;
    }

    public void setIs_expire(int value){
        this.is_expire= value;
    }

    public int getRent_state(){
        return rent_state;
    }

    public void setRent_state(int value){
        this.rent_state= value;
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

    public long getAi_id(){
        return ai_id;
    }

    public void setAi_id(long value){
        this.ai_id= value;
    }

    public int getAi_money(){
        return ai_money;
    }

    public void setAi_money(int value){
        this.ai_money= value;
    }

    public int getAi_effect(){
        return ai_effect;
    }

    public void setAi_effect(int value){
        this.ai_effect= value;
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

    public int getMagnetic_state(){
        return magnetic_state;
    }

    public void setMagnetic_state(int value){
        this.magnetic_state= value;
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

    public String getUp_orderid(){
        return up_orderid;
    }

    public void setUp_orderid(String value){
    	if(value == null){
           value = "";
        }
        this.up_orderid= value;
    }



    public static Pay_month_park newPay_month_park(long id, long pi_id, long ui_id, String car_code, int money, int pp_state, java.util.Date ctime, java.util.Date utime, int month_num, int pay_source, String my_order, String other_order, int pay_type, String pp_versioncode, int phone_type, int order_type, java.util.Date start_time, java.util.Date end_time, String month_info, int allege_state, String area_code, int is_del, long upc_id, long discount_money, String note, String discount_name, int discount_type, int final_time, String address_name, String base_rate, String outtime_rate, int outtime_money, java.util.Date outtime_time, int outtime_minute, int outtime_start_price, int outtime_start_minute, int outtime_charge_money, int outtime_charge_minute, String outtime_other_order, java.util.Date outtime_paytime, int outtime_paystate, String permit_time, int is_24hours, int is_open, java.util.Date open_time, int is_cash, int park_type, java.util.Date scan_time, java.util.Date arrive_time, long temp_money, String pi_name, int is_over, int cancel_state, long pu_id, String pu_nd, double lng, double lat, int is_expire, int rent_state, String ui_nd, long ai_id, int ai_money, int ai_effect, String ui_tel, int magnetic_state, String gov_num, java.util.Date stime, String up_orderid) {
        Pay_month_park ret = new Pay_month_park();
        ret.setId(id);
        ret.setPi_id(pi_id);
        ret.setUi_id(ui_id);
        ret.setCar_code(car_code);
        ret.setMoney(money);
        ret.setPp_state(pp_state);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setMonth_num(month_num);
        ret.setPay_source(pay_source);
        ret.setMy_order(my_order);
        ret.setOther_order(other_order);
        ret.setPay_type(pay_type);
        ret.setPp_versioncode(pp_versioncode);
        ret.setPhone_type(phone_type);
        ret.setOrder_type(order_type);
        ret.setStart_time(start_time);
        ret.setEnd_time(end_time);
        ret.setMonth_info(month_info);
        ret.setAllege_state(allege_state);
        ret.setArea_code(area_code);
        ret.setIs_del(is_del);
        ret.setUpc_id(upc_id);
        ret.setDiscount_money(discount_money);
        ret.setNote(note);
        ret.setDiscount_name(discount_name);
        ret.setDiscount_type(discount_type);
        ret.setFinal_time(final_time);
        ret.setAddress_name(address_name);
        ret.setBase_rate(base_rate);
        ret.setOuttime_rate(outtime_rate);
        ret.setOuttime_money(outtime_money);
        ret.setOuttime_time(outtime_time);
        ret.setOuttime_minute(outtime_minute);
        ret.setOuttime_start_price(outtime_start_price);
        ret.setOuttime_start_minute(outtime_start_minute);
        ret.setOuttime_charge_money(outtime_charge_money);
        ret.setOuttime_charge_minute(outtime_charge_minute);
        ret.setOuttime_other_order(outtime_other_order);
        ret.setOuttime_paytime(outtime_paytime);
        ret.setOuttime_paystate(outtime_paystate);
        ret.setPermit_time(permit_time);
        ret.setIs_24hours(is_24hours);
        ret.setIs_open(is_open);
        ret.setOpen_time(open_time);
        ret.setIs_cash(is_cash);
        ret.setPark_type(park_type);
        ret.setScan_time(scan_time);
        ret.setArrive_time(arrive_time);
        ret.setTemp_money(temp_money);
        ret.setPi_name(pi_name);
        ret.setIs_over(is_over);
        ret.setCancel_state(cancel_state);
        ret.setPu_id(pu_id);
        ret.setPu_nd(pu_nd);
        ret.setLng(lng);
        ret.setLat(lat);
        ret.setIs_expire(is_expire);
        ret.setRent_state(rent_state);
        ret.setUi_nd(ui_nd);
        ret.setAi_id(ai_id);
        ret.setAi_money(ai_money);
        ret.setAi_effect(ai_effect);
        ret.setUi_tel(ui_tel);
        ret.setMagnetic_state(magnetic_state);
        ret.setGov_num(gov_num);
        ret.setStime(stime);
        ret.setUp_orderid(up_orderid);
        return ret;    
    }

    public void assignment(Pay_month_park pay_month_park) {
        long id = pay_month_park.getId();
        long pi_id = pay_month_park.getPi_id();
        long ui_id = pay_month_park.getUi_id();
        String car_code = pay_month_park.getCar_code();
        int money = pay_month_park.getMoney();
        int pp_state = pay_month_park.getPp_state();
        java.util.Date ctime = pay_month_park.getCtime();
        java.util.Date utime = pay_month_park.getUtime();
        int month_num = pay_month_park.getMonth_num();
        int pay_source = pay_month_park.getPay_source();
        String my_order = pay_month_park.getMy_order();
        String other_order = pay_month_park.getOther_order();
        int pay_type = pay_month_park.getPay_type();
        String pp_versioncode = pay_month_park.getPp_versioncode();
        int phone_type = pay_month_park.getPhone_type();
        int order_type = pay_month_park.getOrder_type();
        java.util.Date start_time = pay_month_park.getStart_time();
        java.util.Date end_time = pay_month_park.getEnd_time();
        String month_info = pay_month_park.getMonth_info();
        int allege_state = pay_month_park.getAllege_state();
        String area_code = pay_month_park.getArea_code();
        int is_del = pay_month_park.getIs_del();
        long upc_id = pay_month_park.getUpc_id();
        long discount_money = pay_month_park.getDiscount_money();
        String note = pay_month_park.getNote();
        String discount_name = pay_month_park.getDiscount_name();
        int discount_type = pay_month_park.getDiscount_type();
        int final_time = pay_month_park.getFinal_time();
        String address_name = pay_month_park.getAddress_name();
        String base_rate = pay_month_park.getBase_rate();
        String outtime_rate = pay_month_park.getOuttime_rate();
        int outtime_money = pay_month_park.getOuttime_money();
        java.util.Date outtime_time = pay_month_park.getOuttime_time();
        int outtime_minute = pay_month_park.getOuttime_minute();
        int outtime_start_price = pay_month_park.getOuttime_start_price();
        int outtime_start_minute = pay_month_park.getOuttime_start_minute();
        int outtime_charge_money = pay_month_park.getOuttime_charge_money();
        int outtime_charge_minute = pay_month_park.getOuttime_charge_minute();
        String outtime_other_order = pay_month_park.getOuttime_other_order();
        java.util.Date outtime_paytime = pay_month_park.getOuttime_paytime();
        int outtime_paystate = pay_month_park.getOuttime_paystate();
        String permit_time = pay_month_park.getPermit_time();
        int is_24hours = pay_month_park.getIs_24hours();
        int is_open = pay_month_park.getIs_open();
        java.util.Date open_time = pay_month_park.getOpen_time();
        int is_cash = pay_month_park.getIs_cash();
        int park_type = pay_month_park.getPark_type();
        java.util.Date scan_time = pay_month_park.getScan_time();
        java.util.Date arrive_time = pay_month_park.getArrive_time();
        long temp_money = pay_month_park.getTemp_money();
        String pi_name = pay_month_park.getPi_name();
        int is_over = pay_month_park.getIs_over();
        int cancel_state = pay_month_park.getCancel_state();
        long pu_id = pay_month_park.getPu_id();
        String pu_nd = pay_month_park.getPu_nd();
        double lng = pay_month_park.getLng();
        double lat = pay_month_park.getLat();
        int is_expire = pay_month_park.getIs_expire();
        int rent_state = pay_month_park.getRent_state();
        String ui_nd = pay_month_park.getUi_nd();
        long ai_id = pay_month_park.getAi_id();
        int ai_money = pay_month_park.getAi_money();
        int ai_effect = pay_month_park.getAi_effect();
        String ui_tel = pay_month_park.getUi_tel();
        int magnetic_state = pay_month_park.getMagnetic_state();
        String gov_num = pay_month_park.getGov_num();
        java.util.Date stime = pay_month_park.getStime();
        String up_orderid = pay_month_park.getUp_orderid();

        this.setId(id);
        this.setPi_id(pi_id);
        this.setUi_id(ui_id);
        this.setCar_code(car_code);
        this.setMoney(money);
        this.setPp_state(pp_state);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setMonth_num(month_num);
        this.setPay_source(pay_source);
        this.setMy_order(my_order);
        this.setOther_order(other_order);
        this.setPay_type(pay_type);
        this.setPp_versioncode(pp_versioncode);
        this.setPhone_type(phone_type);
        this.setOrder_type(order_type);
        this.setStart_time(start_time);
        this.setEnd_time(end_time);
        this.setMonth_info(month_info);
        this.setAllege_state(allege_state);
        this.setArea_code(area_code);
        this.setIs_del(is_del);
        this.setUpc_id(upc_id);
        this.setDiscount_money(discount_money);
        this.setNote(note);
        this.setDiscount_name(discount_name);
        this.setDiscount_type(discount_type);
        this.setFinal_time(final_time);
        this.setAddress_name(address_name);
        this.setBase_rate(base_rate);
        this.setOuttime_rate(outtime_rate);
        this.setOuttime_money(outtime_money);
        this.setOuttime_time(outtime_time);
        this.setOuttime_minute(outtime_minute);
        this.setOuttime_start_price(outtime_start_price);
        this.setOuttime_start_minute(outtime_start_minute);
        this.setOuttime_charge_money(outtime_charge_money);
        this.setOuttime_charge_minute(outtime_charge_minute);
        this.setOuttime_other_order(outtime_other_order);
        this.setOuttime_paytime(outtime_paytime);
        this.setOuttime_paystate(outtime_paystate);
        this.setPermit_time(permit_time);
        this.setIs_24hours(is_24hours);
        this.setIs_open(is_open);
        this.setOpen_time(open_time);
        this.setIs_cash(is_cash);
        this.setPark_type(park_type);
        this.setScan_time(scan_time);
        this.setArrive_time(arrive_time);
        this.setTemp_money(temp_money);
        this.setPi_name(pi_name);
        this.setIs_over(is_over);
        this.setCancel_state(cancel_state);
        this.setPu_id(pu_id);
        this.setPu_nd(pu_nd);
        this.setLng(lng);
        this.setLat(lat);
        this.setIs_expire(is_expire);
        this.setRent_state(rent_state);
        this.setUi_nd(ui_nd);
        this.setAi_id(ai_id);
        this.setAi_money(ai_money);
        this.setAi_effect(ai_effect);
        this.setUi_tel(ui_tel);
        this.setMagnetic_state(magnetic_state);
        this.setGov_num(gov_num);
        this.setStime(stime);
        this.setUp_orderid(up_orderid);

    }

    @SuppressWarnings("unused")
    public static void getPay_month_park(Pay_month_park pay_month_park ){
        long id = pay_month_park.getId();
        long pi_id = pay_month_park.getPi_id();
        long ui_id = pay_month_park.getUi_id();
        String car_code = pay_month_park.getCar_code();
        int money = pay_month_park.getMoney();
        int pp_state = pay_month_park.getPp_state();
        java.util.Date ctime = pay_month_park.getCtime();
        java.util.Date utime = pay_month_park.getUtime();
        int month_num = pay_month_park.getMonth_num();
        int pay_source = pay_month_park.getPay_source();
        String my_order = pay_month_park.getMy_order();
        String other_order = pay_month_park.getOther_order();
        int pay_type = pay_month_park.getPay_type();
        String pp_versioncode = pay_month_park.getPp_versioncode();
        int phone_type = pay_month_park.getPhone_type();
        int order_type = pay_month_park.getOrder_type();
        java.util.Date start_time = pay_month_park.getStart_time();
        java.util.Date end_time = pay_month_park.getEnd_time();
        String month_info = pay_month_park.getMonth_info();
        int allege_state = pay_month_park.getAllege_state();
        String area_code = pay_month_park.getArea_code();
        int is_del = pay_month_park.getIs_del();
        long upc_id = pay_month_park.getUpc_id();
        long discount_money = pay_month_park.getDiscount_money();
        String note = pay_month_park.getNote();
        String discount_name = pay_month_park.getDiscount_name();
        int discount_type = pay_month_park.getDiscount_type();
        int final_time = pay_month_park.getFinal_time();
        String address_name = pay_month_park.getAddress_name();
        String base_rate = pay_month_park.getBase_rate();
        String outtime_rate = pay_month_park.getOuttime_rate();
        int outtime_money = pay_month_park.getOuttime_money();
        java.util.Date outtime_time = pay_month_park.getOuttime_time();
        int outtime_minute = pay_month_park.getOuttime_minute();
        int outtime_start_price = pay_month_park.getOuttime_start_price();
        int outtime_start_minute = pay_month_park.getOuttime_start_minute();
        int outtime_charge_money = pay_month_park.getOuttime_charge_money();
        int outtime_charge_minute = pay_month_park.getOuttime_charge_minute();
        String outtime_other_order = pay_month_park.getOuttime_other_order();
        java.util.Date outtime_paytime = pay_month_park.getOuttime_paytime();
        int outtime_paystate = pay_month_park.getOuttime_paystate();
        String permit_time = pay_month_park.getPermit_time();
        int is_24hours = pay_month_park.getIs_24hours();
        int is_open = pay_month_park.getIs_open();
        java.util.Date open_time = pay_month_park.getOpen_time();
        int is_cash = pay_month_park.getIs_cash();
        int park_type = pay_month_park.getPark_type();
        java.util.Date scan_time = pay_month_park.getScan_time();
        java.util.Date arrive_time = pay_month_park.getArrive_time();
        long temp_money = pay_month_park.getTemp_money();
        String pi_name = pay_month_park.getPi_name();
        int is_over = pay_month_park.getIs_over();
        int cancel_state = pay_month_park.getCancel_state();
        long pu_id = pay_month_park.getPu_id();
        String pu_nd = pay_month_park.getPu_nd();
        double lng = pay_month_park.getLng();
        double lat = pay_month_park.getLat();
        int is_expire = pay_month_park.getIs_expire();
        int rent_state = pay_month_park.getRent_state();
        String ui_nd = pay_month_park.getUi_nd();
        long ai_id = pay_month_park.getAi_id();
        int ai_money = pay_month_park.getAi_money();
        int ai_effect = pay_month_park.getAi_effect();
        String ui_tel = pay_month_park.getUi_tel();
        int magnetic_state = pay_month_park.getMagnetic_state();
        String gov_num = pay_month_park.getGov_num();
        java.util.Date stime = pay_month_park.getStime();
        String up_orderid = pay_month_park.getUp_orderid();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Pay_month_park pay_month_park){
        long id = pay_month_park.getId();
        long pi_id = pay_month_park.getPi_id();
        long ui_id = pay_month_park.getUi_id();
        String car_code = pay_month_park.getCar_code();
        int money = pay_month_park.getMoney();
        int pp_state = pay_month_park.getPp_state();
        java.util.Date ctime = pay_month_park.getCtime();
        java.util.Date utime = pay_month_park.getUtime();
        int month_num = pay_month_park.getMonth_num();
        int pay_source = pay_month_park.getPay_source();
        String my_order = pay_month_park.getMy_order();
        String other_order = pay_month_park.getOther_order();
        int pay_type = pay_month_park.getPay_type();
        String pp_versioncode = pay_month_park.getPp_versioncode();
        int phone_type = pay_month_park.getPhone_type();
        int order_type = pay_month_park.getOrder_type();
        java.util.Date start_time = pay_month_park.getStart_time();
        java.util.Date end_time = pay_month_park.getEnd_time();
        String month_info = pay_month_park.getMonth_info();
        int allege_state = pay_month_park.getAllege_state();
        String area_code = pay_month_park.getArea_code();
        int is_del = pay_month_park.getIs_del();
        long upc_id = pay_month_park.getUpc_id();
        long discount_money = pay_month_park.getDiscount_money();
        String note = pay_month_park.getNote();
        String discount_name = pay_month_park.getDiscount_name();
        int discount_type = pay_month_park.getDiscount_type();
        int final_time = pay_month_park.getFinal_time();
        String address_name = pay_month_park.getAddress_name();
        String base_rate = pay_month_park.getBase_rate();
        String outtime_rate = pay_month_park.getOuttime_rate();
        int outtime_money = pay_month_park.getOuttime_money();
        java.util.Date outtime_time = pay_month_park.getOuttime_time();
        int outtime_minute = pay_month_park.getOuttime_minute();
        int outtime_start_price = pay_month_park.getOuttime_start_price();
        int outtime_start_minute = pay_month_park.getOuttime_start_minute();
        int outtime_charge_money = pay_month_park.getOuttime_charge_money();
        int outtime_charge_minute = pay_month_park.getOuttime_charge_minute();
        String outtime_other_order = pay_month_park.getOuttime_other_order();
        java.util.Date outtime_paytime = pay_month_park.getOuttime_paytime();
        int outtime_paystate = pay_month_park.getOuttime_paystate();
        String permit_time = pay_month_park.getPermit_time();
        int is_24hours = pay_month_park.getIs_24hours();
        int is_open = pay_month_park.getIs_open();
        java.util.Date open_time = pay_month_park.getOpen_time();
        int is_cash = pay_month_park.getIs_cash();
        int park_type = pay_month_park.getPark_type();
        java.util.Date scan_time = pay_month_park.getScan_time();
        java.util.Date arrive_time = pay_month_park.getArrive_time();
        long temp_money = pay_month_park.getTemp_money();
        String pi_name = pay_month_park.getPi_name();
        int is_over = pay_month_park.getIs_over();
        int cancel_state = pay_month_park.getCancel_state();
        long pu_id = pay_month_park.getPu_id();
        String pu_nd = pay_month_park.getPu_nd();
        double lng = pay_month_park.getLng();
        double lat = pay_month_park.getLat();
        int is_expire = pay_month_park.getIs_expire();
        int rent_state = pay_month_park.getRent_state();
        String ui_nd = pay_month_park.getUi_nd();
        long ai_id = pay_month_park.getAi_id();
        int ai_money = pay_month_park.getAi_money();
        int ai_effect = pay_month_park.getAi_effect();
        String ui_tel = pay_month_park.getUi_tel();
        int magnetic_state = pay_month_park.getMagnetic_state();
        String gov_num = pay_month_park.getGov_num();
        java.util.Date stime = pay_month_park.getStime();
        String up_orderid = pay_month_park.getUp_orderid();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("pi_id",pi_id);
        _ret.put("ui_id",ui_id);
        _ret.put("car_code",car_code);
        _ret.put("money",money);
        _ret.put("pp_state",pp_state);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("month_num",month_num);
        _ret.put("pay_source",pay_source);
        _ret.put("my_order",my_order);
        _ret.put("other_order",other_order);
        _ret.put("pay_type",pay_type);
        _ret.put("pp_versioncode",pp_versioncode);
        _ret.put("phone_type",phone_type);
        _ret.put("order_type",order_type);
        _ret.put("start_time",start_time);
        _ret.put("end_time",end_time);
        _ret.put("month_info",month_info);
        _ret.put("allege_state",allege_state);
        _ret.put("area_code",area_code);
        _ret.put("is_del",is_del);
        _ret.put("upc_id",upc_id);
        _ret.put("discount_money",discount_money);
        _ret.put("note",note);
        _ret.put("discount_name",discount_name);
        _ret.put("discount_type",discount_type);
        _ret.put("final_time",final_time);
        _ret.put("address_name",address_name);
        _ret.put("base_rate",base_rate);
        _ret.put("outtime_rate",outtime_rate);
        _ret.put("outtime_money",outtime_money);
        _ret.put("outtime_time",outtime_time);
        _ret.put("outtime_minute",outtime_minute);
        _ret.put("outtime_start_price",outtime_start_price);
        _ret.put("outtime_start_minute",outtime_start_minute);
        _ret.put("outtime_charge_money",outtime_charge_money);
        _ret.put("outtime_charge_minute",outtime_charge_minute);
        _ret.put("outtime_other_order",outtime_other_order);
        _ret.put("outtime_paytime",outtime_paytime);
        _ret.put("outtime_paystate",outtime_paystate);
        _ret.put("permit_time",permit_time);
        _ret.put("is_24hours",is_24hours);
        _ret.put("is_open",is_open);
        _ret.put("open_time",open_time);
        _ret.put("is_cash",is_cash);
        _ret.put("park_type",park_type);
        _ret.put("scan_time",scan_time);
        _ret.put("arrive_time",arrive_time);
        _ret.put("temp_money",temp_money);
        _ret.put("pi_name",pi_name);
        _ret.put("is_over",is_over);
        _ret.put("cancel_state",cancel_state);
        _ret.put("pu_id",pu_id);
        _ret.put("pu_nd",pu_nd);
        _ret.put("lng",lng);
        _ret.put("lat",lat);
        _ret.put("is_expire",is_expire);
        _ret.put("rent_state",rent_state);
        _ret.put("ui_nd",ui_nd);
        _ret.put("ai_id",ai_id);
        _ret.put("ai_money",ai_money);
        _ret.put("ai_effect",ai_effect);
        _ret.put("ui_tel",ui_tel);
        _ret.put("magnetic_state",magnetic_state);
        _ret.put("gov_num",gov_num);
        _ret.put("stime",stime);
        _ret.put("up_orderid",up_orderid);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Pay_month_park clone2(){
        try{
            return (Pay_month_park) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
