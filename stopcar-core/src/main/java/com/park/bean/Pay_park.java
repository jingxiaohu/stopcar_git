package com.park.bean;

import java.io.*;
import java.util.*;

//pay_park
@SuppressWarnings({"serial"})
public class Pay_park implements Cloneable , Serializable{

    //public static String[] carrays ={"id","pi_id","ui_id","car_code","money","pp_state","ctime","utime","pay_source","my_order","other_order","pay_type","pp_versioncode","phone_type","order_type","allege_state","expect_time","arrive_time","leave_time","expect_money","expect_info","area_code","is_expect_outtime","is_expect_deduct","start_price","start_time","charging","charging_time","is_del","upc_id","discount_money","note","discount_name","discount_type","final_time","address_name","cancel_state","is_open","open_time","is_cash","park_type","scan_time","pi_name","is_over","free_minute","is_free_minute","pu_id","pu_nd","lng","lat","expect_state","ui_nd","ai_id","ai_money","ai_effect","ui_tel","magnetic_state","gov_num","stime","up_orderid","close_time","sure_type"};

    public long id;//bigint(20)    主键ID
    public long pi_id;//bigint(20)    支付停车场主键ID
    public long ui_id;//bigint(20)    用户ID
    public String car_code="";//varchar(60)    用户支付车牌号
    public int money;//int(11)    支付金额（单位分）
    public int pp_state;//int(11)    支付状态0:未支付1：已经支付
    public java.util.Date ctime=new java.util.Date();//datetime    下单时间
    public java.util.Date utime=new java.util.Date();//datetime    更新支付状态时间
    public int pay_source;//int(11)    支付类型0：现金支付1:支付宝2：微信3：银联4：钱包5:龙支付6:ETC快捷支付7：扫脸支付8：指纹支付9：指静脉支付
    public String my_order="";//varchar(80)    我们的订单号
    public String other_order="";//varchar(80)    第三方的支付单号
    public int pay_type;//int(11)    支付类型0:手动输入密码支付1：快捷支付（服务器可以请求第三方直接扣款）
    public String pp_versioncode="";//varchar(30)    当前APPSDK版本号（内部升级版本代号）
    public int phone_type;//int(11)    手机类型0:android1：IOS
    public int order_type;//int(11)    下单类型0:普通下单1：预约下单2：租赁临停订单3:包月临停4：免费临停
    public int allege_state;//int(11)    申述状态0:未申述1：申述中2：申述失败3：申述成功
    public int expect_time;//int(11)    预约时长(单位分钟
    public java.util.Date arrive_time=new java.util.Date();//datetime    到场时间
    public java.util.Date leave_time=new java.util.Date();//datetime    离场时间
    public int expect_money;//int(11)    预定价格
    public String expect_info="";//varchar(255)    预定提示信息
    public String area_code="";//varchar(60)    省市县编号
    public int is_expect_outtime;//int(11)    是否预约已经超时0：未超时1：已经超时
    public int is_expect_deduct;//int(11)    是否已经扣除预约超时钱0：已锁定扣款金额1：已经扣款成功2：已解绑锁定金额
    public int start_price;//int(11)    起步价（RMB单位分）
    public int start_time;//int(11)    起步时长(分钟)
    public int charging;//int(11)    计费价(RMB单位分)每小时2元
    public int charging_time;//int(11)    计费单位时长(分钟)例如：每小时2元那么就是1小时
    public int is_del;//int(11)    删除状态0:正常1：假删除
    public long upc_id;//bigint(20)    用户优惠券表主键ID
    public long discount_money;//bigint(20)    抵扣优惠金额（单位分）
    public String note="";//varchar(100)    备注
    public String discount_name="";//varchar(100)    抵扣优惠券名称
    public int discount_type;//int(11)    优惠券类型0:金额券1：折扣券
    public int final_time;//int(11)    结算时计费时长（单位分钟）
    public String address_name="";//varchar(150)    停车场地点名称
    public int cancel_state;//int(11)    订单关闭状态0:没有关闭1：已经关闭2：订单异常（停车场心跳时间段内断开或者道闸手动开闸）3：异步数据上传（网络异常）
    public int is_open;//int(11)    是否开闸0:未开闸1：已正常放行开闸2:道闸手动放行
    public java.util.Date open_time=new java.util.Date();//datetime    开闸时间
    public int is_cash;//int(11)    是否现金支付0：在线支付1：现金支付2：免费支付3:免费车类型4：包月车类型5：租赁车类型
    public int park_type;//int(11)    停车场类型0：道闸停车场1：占道车场2：露天立体车库停车场
    public java.util.Date scan_time=new java.util.Date();//datetime    是否可以停车缴费时间（摄像头是否扫描到了：仅限道闸停车使用）
    public String pi_name="";//varchar(100)    停车场名称
    public int is_over;//int(11)    订单是否完成或者逃逸(0:没有完成1：完成2：车辆逃逸3：未交费4：PDA补交欠费已完成5：PDA补交逃逸已完成)
    public int free_minute;//int(11)    多少分钟之内进出免费
    public int is_free_minute;//int(11)    多少分钟之内进出免费是否开启0:不开启1：开启
    public long pu_id;//bigint(20)    商户主键ID
    public String pu_nd="";//varchar(100)    商户编号
    public double lng;//double    场地经度
    public double lat;//double    场地纬度
    public int expect_state;//int(11)    预约状态0:未预约1：预约中2：预约成功3：预约失败4：取消预约中5：取消预约成功6：取消预约失败
    public String ui_nd="";//varchar(100)    用户唯一标识uuid
    public long ai_id;//bigint(20)    活动ID
    public int ai_money;//int(11)    活动优惠金额（单位分）
    public int ai_effect;//int(11)    活动优惠是否生效（0：没有1：生效）
    public String ui_tel="";//varchar(100)    用户电话号码
    public int magnetic_state;//int(11)    智能磁场入库出库状态（1：已入库2：已出库3：车辆逃逸4：其它故障）
    public String gov_num="";//varchar(60)    智能磁场车位编号（政府部门统一分配）
    public java.util.Date stime=new java.util.Date();//datetime    服务器端接收数据创建时间
    public String up_orderid="";//varchar(80)    第三方支付user_pay中的流水单号(订单支付、欠费订单补交)
    public long close_time;//bigint(20)    结算时间（异步传输的采用客户端时间同步传送的用服务器时间）
    public int sure_type;//int(11)    订单结算确认状态（0：未结算确认1：客户端已支付确认）



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

    public int getAllege_state(){
        return allege_state;
    }

    public void setAllege_state(int value){
        this.allege_state= value;
    }

    public int getExpect_time(){
        return expect_time;
    }

    public void setExpect_time(int value){
        this.expect_time= value;
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

    public java.util.Date getLeave_time(){
        return leave_time;
    }

    public void setLeave_time(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.leave_time= value;
    }

    public int getExpect_money(){
        return expect_money;
    }

    public void setExpect_money(int value){
        this.expect_money= value;
    }

    public String getExpect_info(){
        return expect_info;
    }

    public void setExpect_info(String value){
    	if(value == null){
           value = "";
        }
        this.expect_info= value;
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

    public int getIs_expect_outtime(){
        return is_expect_outtime;
    }

    public void setIs_expect_outtime(int value){
        this.is_expect_outtime= value;
    }

    public int getIs_expect_deduct(){
        return is_expect_deduct;
    }

    public void setIs_expect_deduct(int value){
        this.is_expect_deduct= value;
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

    public int getCancel_state(){
        return cancel_state;
    }

    public void setCancel_state(int value){
        this.cancel_state= value;
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

    public int getExpect_state(){
        return expect_state;
    }

    public void setExpect_state(int value){
        this.expect_state= value;
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

    public long getClose_time(){
        return close_time;
    }

    public void setClose_time(long value){
        this.close_time= value;
    }

    public int getSure_type(){
        return sure_type;
    }

    public void setSure_type(int value){
        this.sure_type= value;
    }



    public static Pay_park newPay_park(long id, long pi_id, long ui_id, String car_code, int money, int pp_state, java.util.Date ctime, java.util.Date utime, int pay_source, String my_order, String other_order, int pay_type, String pp_versioncode, int phone_type, int order_type, int allege_state, int expect_time, java.util.Date arrive_time, java.util.Date leave_time, int expect_money, String expect_info, String area_code, int is_expect_outtime, int is_expect_deduct, int start_price, int start_time, int charging, int charging_time, int is_del, long upc_id, long discount_money, String note, String discount_name, int discount_type, int final_time, String address_name, int cancel_state, int is_open, java.util.Date open_time, int is_cash, int park_type, java.util.Date scan_time, String pi_name, int is_over, int free_minute, int is_free_minute, long pu_id, String pu_nd, double lng, double lat, int expect_state, String ui_nd, long ai_id, int ai_money, int ai_effect, String ui_tel, int magnetic_state, String gov_num, java.util.Date stime, String up_orderid, long close_time, int sure_type) {
        Pay_park ret = new Pay_park();
        ret.setId(id);
        ret.setPi_id(pi_id);
        ret.setUi_id(ui_id);
        ret.setCar_code(car_code);
        ret.setMoney(money);
        ret.setPp_state(pp_state);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setPay_source(pay_source);
        ret.setMy_order(my_order);
        ret.setOther_order(other_order);
        ret.setPay_type(pay_type);
        ret.setPp_versioncode(pp_versioncode);
        ret.setPhone_type(phone_type);
        ret.setOrder_type(order_type);
        ret.setAllege_state(allege_state);
        ret.setExpect_time(expect_time);
        ret.setArrive_time(arrive_time);
        ret.setLeave_time(leave_time);
        ret.setExpect_money(expect_money);
        ret.setExpect_info(expect_info);
        ret.setArea_code(area_code);
        ret.setIs_expect_outtime(is_expect_outtime);
        ret.setIs_expect_deduct(is_expect_deduct);
        ret.setStart_price(start_price);
        ret.setStart_time(start_time);
        ret.setCharging(charging);
        ret.setCharging_time(charging_time);
        ret.setIs_del(is_del);
        ret.setUpc_id(upc_id);
        ret.setDiscount_money(discount_money);
        ret.setNote(note);
        ret.setDiscount_name(discount_name);
        ret.setDiscount_type(discount_type);
        ret.setFinal_time(final_time);
        ret.setAddress_name(address_name);
        ret.setCancel_state(cancel_state);
        ret.setIs_open(is_open);
        ret.setOpen_time(open_time);
        ret.setIs_cash(is_cash);
        ret.setPark_type(park_type);
        ret.setScan_time(scan_time);
        ret.setPi_name(pi_name);
        ret.setIs_over(is_over);
        ret.setFree_minute(free_minute);
        ret.setIs_free_minute(is_free_minute);
        ret.setPu_id(pu_id);
        ret.setPu_nd(pu_nd);
        ret.setLng(lng);
        ret.setLat(lat);
        ret.setExpect_state(expect_state);
        ret.setUi_nd(ui_nd);
        ret.setAi_id(ai_id);
        ret.setAi_money(ai_money);
        ret.setAi_effect(ai_effect);
        ret.setUi_tel(ui_tel);
        ret.setMagnetic_state(magnetic_state);
        ret.setGov_num(gov_num);
        ret.setStime(stime);
        ret.setUp_orderid(up_orderid);
        ret.setClose_time(close_time);
        ret.setSure_type(sure_type);
        return ret;    
    }

    public void assignment(Pay_park pay_park) {
        long id = pay_park.getId();
        long pi_id = pay_park.getPi_id();
        long ui_id = pay_park.getUi_id();
        String car_code = pay_park.getCar_code();
        int money = pay_park.getMoney();
        int pp_state = pay_park.getPp_state();
        java.util.Date ctime = pay_park.getCtime();
        java.util.Date utime = pay_park.getUtime();
        int pay_source = pay_park.getPay_source();
        String my_order = pay_park.getMy_order();
        String other_order = pay_park.getOther_order();
        int pay_type = pay_park.getPay_type();
        String pp_versioncode = pay_park.getPp_versioncode();
        int phone_type = pay_park.getPhone_type();
        int order_type = pay_park.getOrder_type();
        int allege_state = pay_park.getAllege_state();
        int expect_time = pay_park.getExpect_time();
        java.util.Date arrive_time = pay_park.getArrive_time();
        java.util.Date leave_time = pay_park.getLeave_time();
        int expect_money = pay_park.getExpect_money();
        String expect_info = pay_park.getExpect_info();
        String area_code = pay_park.getArea_code();
        int is_expect_outtime = pay_park.getIs_expect_outtime();
        int is_expect_deduct = pay_park.getIs_expect_deduct();
        int start_price = pay_park.getStart_price();
        int start_time = pay_park.getStart_time();
        int charging = pay_park.getCharging();
        int charging_time = pay_park.getCharging_time();
        int is_del = pay_park.getIs_del();
        long upc_id = pay_park.getUpc_id();
        long discount_money = pay_park.getDiscount_money();
        String note = pay_park.getNote();
        String discount_name = pay_park.getDiscount_name();
        int discount_type = pay_park.getDiscount_type();
        int final_time = pay_park.getFinal_time();
        String address_name = pay_park.getAddress_name();
        int cancel_state = pay_park.getCancel_state();
        int is_open = pay_park.getIs_open();
        java.util.Date open_time = pay_park.getOpen_time();
        int is_cash = pay_park.getIs_cash();
        int park_type = pay_park.getPark_type();
        java.util.Date scan_time = pay_park.getScan_time();
        String pi_name = pay_park.getPi_name();
        int is_over = pay_park.getIs_over();
        int free_minute = pay_park.getFree_minute();
        int is_free_minute = pay_park.getIs_free_minute();
        long pu_id = pay_park.getPu_id();
        String pu_nd = pay_park.getPu_nd();
        double lng = pay_park.getLng();
        double lat = pay_park.getLat();
        int expect_state = pay_park.getExpect_state();
        String ui_nd = pay_park.getUi_nd();
        long ai_id = pay_park.getAi_id();
        int ai_money = pay_park.getAi_money();
        int ai_effect = pay_park.getAi_effect();
        String ui_tel = pay_park.getUi_tel();
        int magnetic_state = pay_park.getMagnetic_state();
        String gov_num = pay_park.getGov_num();
        java.util.Date stime = pay_park.getStime();
        String up_orderid = pay_park.getUp_orderid();
        long close_time = pay_park.getClose_time();
        int sure_type = pay_park.getSure_type();

        this.setId(id);
        this.setPi_id(pi_id);
        this.setUi_id(ui_id);
        this.setCar_code(car_code);
        this.setMoney(money);
        this.setPp_state(pp_state);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setPay_source(pay_source);
        this.setMy_order(my_order);
        this.setOther_order(other_order);
        this.setPay_type(pay_type);
        this.setPp_versioncode(pp_versioncode);
        this.setPhone_type(phone_type);
        this.setOrder_type(order_type);
        this.setAllege_state(allege_state);
        this.setExpect_time(expect_time);
        this.setArrive_time(arrive_time);
        this.setLeave_time(leave_time);
        this.setExpect_money(expect_money);
        this.setExpect_info(expect_info);
        this.setArea_code(area_code);
        this.setIs_expect_outtime(is_expect_outtime);
        this.setIs_expect_deduct(is_expect_deduct);
        this.setStart_price(start_price);
        this.setStart_time(start_time);
        this.setCharging(charging);
        this.setCharging_time(charging_time);
        this.setIs_del(is_del);
        this.setUpc_id(upc_id);
        this.setDiscount_money(discount_money);
        this.setNote(note);
        this.setDiscount_name(discount_name);
        this.setDiscount_type(discount_type);
        this.setFinal_time(final_time);
        this.setAddress_name(address_name);
        this.setCancel_state(cancel_state);
        this.setIs_open(is_open);
        this.setOpen_time(open_time);
        this.setIs_cash(is_cash);
        this.setPark_type(park_type);
        this.setScan_time(scan_time);
        this.setPi_name(pi_name);
        this.setIs_over(is_over);
        this.setFree_minute(free_minute);
        this.setIs_free_minute(is_free_minute);
        this.setPu_id(pu_id);
        this.setPu_nd(pu_nd);
        this.setLng(lng);
        this.setLat(lat);
        this.setExpect_state(expect_state);
        this.setUi_nd(ui_nd);
        this.setAi_id(ai_id);
        this.setAi_money(ai_money);
        this.setAi_effect(ai_effect);
        this.setUi_tel(ui_tel);
        this.setMagnetic_state(magnetic_state);
        this.setGov_num(gov_num);
        this.setStime(stime);
        this.setUp_orderid(up_orderid);
        this.setClose_time(close_time);
        this.setSure_type(sure_type);

    }

    @SuppressWarnings("unused")
    public static void getPay_park(Pay_park pay_park ){
        long id = pay_park.getId();
        long pi_id = pay_park.getPi_id();
        long ui_id = pay_park.getUi_id();
        String car_code = pay_park.getCar_code();
        int money = pay_park.getMoney();
        int pp_state = pay_park.getPp_state();
        java.util.Date ctime = pay_park.getCtime();
        java.util.Date utime = pay_park.getUtime();
        int pay_source = pay_park.getPay_source();
        String my_order = pay_park.getMy_order();
        String other_order = pay_park.getOther_order();
        int pay_type = pay_park.getPay_type();
        String pp_versioncode = pay_park.getPp_versioncode();
        int phone_type = pay_park.getPhone_type();
        int order_type = pay_park.getOrder_type();
        int allege_state = pay_park.getAllege_state();
        int expect_time = pay_park.getExpect_time();
        java.util.Date arrive_time = pay_park.getArrive_time();
        java.util.Date leave_time = pay_park.getLeave_time();
        int expect_money = pay_park.getExpect_money();
        String expect_info = pay_park.getExpect_info();
        String area_code = pay_park.getArea_code();
        int is_expect_outtime = pay_park.getIs_expect_outtime();
        int is_expect_deduct = pay_park.getIs_expect_deduct();
        int start_price = pay_park.getStart_price();
        int start_time = pay_park.getStart_time();
        int charging = pay_park.getCharging();
        int charging_time = pay_park.getCharging_time();
        int is_del = pay_park.getIs_del();
        long upc_id = pay_park.getUpc_id();
        long discount_money = pay_park.getDiscount_money();
        String note = pay_park.getNote();
        String discount_name = pay_park.getDiscount_name();
        int discount_type = pay_park.getDiscount_type();
        int final_time = pay_park.getFinal_time();
        String address_name = pay_park.getAddress_name();
        int cancel_state = pay_park.getCancel_state();
        int is_open = pay_park.getIs_open();
        java.util.Date open_time = pay_park.getOpen_time();
        int is_cash = pay_park.getIs_cash();
        int park_type = pay_park.getPark_type();
        java.util.Date scan_time = pay_park.getScan_time();
        String pi_name = pay_park.getPi_name();
        int is_over = pay_park.getIs_over();
        int free_minute = pay_park.getFree_minute();
        int is_free_minute = pay_park.getIs_free_minute();
        long pu_id = pay_park.getPu_id();
        String pu_nd = pay_park.getPu_nd();
        double lng = pay_park.getLng();
        double lat = pay_park.getLat();
        int expect_state = pay_park.getExpect_state();
        String ui_nd = pay_park.getUi_nd();
        long ai_id = pay_park.getAi_id();
        int ai_money = pay_park.getAi_money();
        int ai_effect = pay_park.getAi_effect();
        String ui_tel = pay_park.getUi_tel();
        int magnetic_state = pay_park.getMagnetic_state();
        String gov_num = pay_park.getGov_num();
        java.util.Date stime = pay_park.getStime();
        String up_orderid = pay_park.getUp_orderid();
        long close_time = pay_park.getClose_time();
        int sure_type = pay_park.getSure_type();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Pay_park pay_park){
        long id = pay_park.getId();
        long pi_id = pay_park.getPi_id();
        long ui_id = pay_park.getUi_id();
        String car_code = pay_park.getCar_code();
        int money = pay_park.getMoney();
        int pp_state = pay_park.getPp_state();
        java.util.Date ctime = pay_park.getCtime();
        java.util.Date utime = pay_park.getUtime();
        int pay_source = pay_park.getPay_source();
        String my_order = pay_park.getMy_order();
        String other_order = pay_park.getOther_order();
        int pay_type = pay_park.getPay_type();
        String pp_versioncode = pay_park.getPp_versioncode();
        int phone_type = pay_park.getPhone_type();
        int order_type = pay_park.getOrder_type();
        int allege_state = pay_park.getAllege_state();
        int expect_time = pay_park.getExpect_time();
        java.util.Date arrive_time = pay_park.getArrive_time();
        java.util.Date leave_time = pay_park.getLeave_time();
        int expect_money = pay_park.getExpect_money();
        String expect_info = pay_park.getExpect_info();
        String area_code = pay_park.getArea_code();
        int is_expect_outtime = pay_park.getIs_expect_outtime();
        int is_expect_deduct = pay_park.getIs_expect_deduct();
        int start_price = pay_park.getStart_price();
        int start_time = pay_park.getStart_time();
        int charging = pay_park.getCharging();
        int charging_time = pay_park.getCharging_time();
        int is_del = pay_park.getIs_del();
        long upc_id = pay_park.getUpc_id();
        long discount_money = pay_park.getDiscount_money();
        String note = pay_park.getNote();
        String discount_name = pay_park.getDiscount_name();
        int discount_type = pay_park.getDiscount_type();
        int final_time = pay_park.getFinal_time();
        String address_name = pay_park.getAddress_name();
        int cancel_state = pay_park.getCancel_state();
        int is_open = pay_park.getIs_open();
        java.util.Date open_time = pay_park.getOpen_time();
        int is_cash = pay_park.getIs_cash();
        int park_type = pay_park.getPark_type();
        java.util.Date scan_time = pay_park.getScan_time();
        String pi_name = pay_park.getPi_name();
        int is_over = pay_park.getIs_over();
        int free_minute = pay_park.getFree_minute();
        int is_free_minute = pay_park.getIs_free_minute();
        long pu_id = pay_park.getPu_id();
        String pu_nd = pay_park.getPu_nd();
        double lng = pay_park.getLng();
        double lat = pay_park.getLat();
        int expect_state = pay_park.getExpect_state();
        String ui_nd = pay_park.getUi_nd();
        long ai_id = pay_park.getAi_id();
        int ai_money = pay_park.getAi_money();
        int ai_effect = pay_park.getAi_effect();
        String ui_tel = pay_park.getUi_tel();
        int magnetic_state = pay_park.getMagnetic_state();
        String gov_num = pay_park.getGov_num();
        java.util.Date stime = pay_park.getStime();
        String up_orderid = pay_park.getUp_orderid();
        long close_time = pay_park.getClose_time();
        int sure_type = pay_park.getSure_type();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("id",id);
        _ret.put("pi_id",pi_id);
        _ret.put("ui_id",ui_id);
        _ret.put("car_code",car_code);
        _ret.put("money",money);
        _ret.put("pp_state",pp_state);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("pay_source",pay_source);
        _ret.put("my_order",my_order);
        _ret.put("other_order",other_order);
        _ret.put("pay_type",pay_type);
        _ret.put("pp_versioncode",pp_versioncode);
        _ret.put("phone_type",phone_type);
        _ret.put("order_type",order_type);
        _ret.put("allege_state",allege_state);
        _ret.put("expect_time",expect_time);
        _ret.put("arrive_time",arrive_time);
        _ret.put("leave_time",leave_time);
        _ret.put("expect_money",expect_money);
        _ret.put("expect_info",expect_info);
        _ret.put("area_code",area_code);
        _ret.put("is_expect_outtime",is_expect_outtime);
        _ret.put("is_expect_deduct",is_expect_deduct);
        _ret.put("start_price",start_price);
        _ret.put("start_time",start_time);
        _ret.put("charging",charging);
        _ret.put("charging_time",charging_time);
        _ret.put("is_del",is_del);
        _ret.put("upc_id",upc_id);
        _ret.put("discount_money",discount_money);
        _ret.put("note",note);
        _ret.put("discount_name",discount_name);
        _ret.put("discount_type",discount_type);
        _ret.put("final_time",final_time);
        _ret.put("address_name",address_name);
        _ret.put("cancel_state",cancel_state);
        _ret.put("is_open",is_open);
        _ret.put("open_time",open_time);
        _ret.put("is_cash",is_cash);
        _ret.put("park_type",park_type);
        _ret.put("scan_time",scan_time);
        _ret.put("pi_name",pi_name);
        _ret.put("is_over",is_over);
        _ret.put("free_minute",free_minute);
        _ret.put("is_free_minute",is_free_minute);
        _ret.put("pu_id",pu_id);
        _ret.put("pu_nd",pu_nd);
        _ret.put("lng",lng);
        _ret.put("lat",lat);
        _ret.put("expect_state",expect_state);
        _ret.put("ui_nd",ui_nd);
        _ret.put("ai_id",ai_id);
        _ret.put("ai_money",ai_money);
        _ret.put("ai_effect",ai_effect);
        _ret.put("ui_tel",ui_tel);
        _ret.put("magnetic_state",magnetic_state);
        _ret.put("gov_num",gov_num);
        _ret.put("stime",stime);
        _ret.put("up_orderid",up_orderid);
        _ret.put("close_time",close_time);
        _ret.put("sure_type",sure_type);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Pay_park clone2(){
        try{
            return (Pay_park) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
