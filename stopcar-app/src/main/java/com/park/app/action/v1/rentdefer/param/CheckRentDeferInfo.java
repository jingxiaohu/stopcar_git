package com.park.app.action.v1.rentdefer.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * Created by zzy on 2017/8/28.
 */
public class CheckRentDeferInfo extends BaseParam {
    private Long cpr_id;    //用户车牌--停车场租赁id
    private Long pi_id;   //停车场主键id
    private String area_code;   //区域代码
    private String car_code;    //车牌号
    private String client_ruleid;  //客户端规则id
    private Integer unit_price;   //租赁续租单价

    public Long getCpr_id() {
        return cpr_id;
    }

    public void setCpr_id(Long cpr_id) {
        this.cpr_id = cpr_id;
    }

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

    public String getCar_code() {
        return car_code;
    }

    public void setCar_code(String car_code) {
        this.car_code = car_code;
    }

    public String getClient_ruleid() {
        return client_ruleid;
    }

    public void setClient_ruleid(String client_ruleid) {
        this.client_ruleid = client_ruleid;
    }

    public Integer getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Integer unit_price) {
        this.unit_price = unit_price;
    }
}
