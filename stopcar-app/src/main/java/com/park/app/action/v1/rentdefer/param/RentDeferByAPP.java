package com.park.app.action.v1.rentdefer.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * Created by zzy on 2017/7/3.
 */
public class RentDeferByAPP extends BaseParam{
    private Long pi_id;   //停车场主键id
    private String area_code;   //区域代码
    private Integer month_num;   //续约的月份数
    private String client_ruleid; //客户端规则id
    private String car_code;    //车牌号
    private Integer pay_source;    //支付类型 0：现金 1:支付宝  2：微信  3：银联  4：钱包 5:龙支付  6:ETC快捷支付
    private Integer rent_type;    //租赁类型（0：普通时间段  1：早半天  2：晚半天  3：全天）   //必传
    private Long cpr_id;    //用户车牌--停车场租赁id
    private Integer unit_price;   //租赁续租单价


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

    public Integer getMonth_num() {
        return month_num;
    }

    public void setMonth_num(Integer month_num) {
        this.month_num = month_num;
    }

    public String getClient_ruleid() {
        return client_ruleid;
    }

    public void setClient_ruleid(String client_ruleid) {
        this.client_ruleid = client_ruleid;
    }

    public String getCar_code() {
        return car_code;
    }

    public void setCar_code(String car_code) {
        this.car_code = car_code;
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

    public Long getCpr_id() {
        return cpr_id;
    }

    public void setCpr_id(Long cpr_id) {
        this.cpr_id = cpr_id;
    }

    public Integer getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Integer unit_price) {
        this.unit_price = unit_price;
    }
}
