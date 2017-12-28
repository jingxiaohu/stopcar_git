package com.park.gate.action.v1.order.param.rentDefer;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * Created by zzy on 2017/6/30.
 */
public class RentDeferExpireState extends BaseParam{
    private Long pi_id;
    private String area_code;
    private String car_code;
    private Integer expire_state;  //是否已经到期（0：未到期 1：已经到期）

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

    public Integer getExpire_state() {
        return expire_state;
    }

    public void setExpire_state(Integer expire_state) {
        this.expire_state = expire_state;
    }
}
