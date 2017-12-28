package com.park.gate.action.v1.monthfree.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * Created by zzy on 2017/6/16.
 */
public class Param_ParkMonthFreeQuery extends BaseParam{
    private Long pi_id;               //停车场ID
    private String area_code;           //地址编号
    private String car_code;            //车牌号

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
}
