package com.park.gate.action.v1.monthfree.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * Created by zzy on 2017/6/16.
 */
public class Param_ParkMonthFreeState extends BaseParam{
    private Long pi_id;               //停车场ID
    private String area_code;           //地址编号
    private String car_code;            //车牌号
    private String client_id;          //道闸本地记录的主键ID
    private String state;              //状态   0：有效   1：无效

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

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
