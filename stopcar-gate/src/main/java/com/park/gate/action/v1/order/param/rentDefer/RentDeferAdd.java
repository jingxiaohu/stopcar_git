package com.park.gate.action.v1.order.param.rentDefer;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * Created by zzy on 2017/6/30.
 */
public class RentDeferAdd extends BaseParam{
    //private String rent_order_id;  //租赁订单号
    private Long pi_id;   //停车场主键id
    private String area_code;   //区域代码
    private Integer money;   //租赁续期金额（单位 分）
    private Integer unit_price;   //租赁每个月单价（单位分）
    private Integer month_num;   //续约的月份数
    private Long starttime;   //开始时间
    private Long endtime;    //结束时间
    private String car_code;    //车牌号
    private String permit_time;   //允许时间段（8：00-23：00）
    private Integer pay_source;    //支付类型 0：现金 1:支付宝  2：微信  3：银联  4：钱包 5:龙支付  6:ETC快捷支付
    private Integer rent_type;    //租赁类型（0：普通时间段  1：早半天  2：晚半天  3：全天）
    private Long ctime;      //客户端创建时间
    private String client_order_id;   //客户端的租赁订单号(只有一个)
    private String client_rule_id;    //客户端规则id
    private String client_father_orderid;  //客户端的续约租赁的父订单号

//    public String getRent_order_id() {
//        return rent_order_id;
//    }
//
//    public void setRent_order_id(String rent_order_id) {
//        this.rent_order_id = rent_order_id;
//    }

    public Long getPi_id() {
        return pi_id;
    }

    public void setPi_id(Long pi_id) {
        this.pi_id = pi_id;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Integer unit_price) {
        this.unit_price = unit_price;
    }

    public Integer getMonth_num() {
        return month_num;
    }

    public void setMonth_num(Integer month_num) {
        this.month_num = month_num;
    }

    public Long getStarttime() {
        return starttime;
    }

    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    public Long getEndtime() {
        return endtime;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    public String getCar_code() {
        return car_code;
    }

    public void setCar_code(String car_code) {
        this.car_code = car_code;
    }

    public String getPermit_time() {
        return permit_time;
    }

    public void setPermit_time(String permit_time) {
        this.permit_time = permit_time;
    }

    public Integer getPay_source() {
        return pay_source;
    }

    public void setPay_source(Integer pay_source) {
        this.pay_source = pay_source;
    }

    public Integer getRent_type() {
        return rent_type;
    }

    public void setRent_type(Integer rent_type) {
        this.rent_type = rent_type;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public String getClient_order_id() {
        return client_order_id;
    }

    public void setClient_order_id(String client_order_id) {
        this.client_order_id = client_order_id;
    }

    public String getClient_rule_id() {
        return client_rule_id;
    }

    public void setClient_rule_id(String client_rule_id) {
        this.client_rule_id = client_rule_id;
    }

    public String getClient_father_orderid() {
        return client_father_orderid;
    }

    public void setClient_father_orderid(String client_father_orderid) {
        this.client_father_orderid = client_father_orderid;
    }
}
