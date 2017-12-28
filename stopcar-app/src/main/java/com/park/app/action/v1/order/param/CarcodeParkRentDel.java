package com.park.app.action.v1.order.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * Created by zzy on 2017/6/30.
 */
public class CarcodeParkRentDel extends BaseParam {
    private Long cpr_id;   //用户车牌--停车场租赁映射表 主键id
    private String car_code;
    private String area_code;
    private Long pi_id;

    public Long getCpr_id() {
        return cpr_id;
    }

    public void setCpr_id(Long cpr_id) {
        this.cpr_id = cpr_id;
    }

    public String getCar_code() {
        return car_code;
    }

    public void setCar_code(String car_code) {
        this.car_code = car_code;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public Long getPi_id() {
        return pi_id;
    }

    public void setPi_id(Long pi_id) {
        this.pi_id = pi_id;
    }
}
