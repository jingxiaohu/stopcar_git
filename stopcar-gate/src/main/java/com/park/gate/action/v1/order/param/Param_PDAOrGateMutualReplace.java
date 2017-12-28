package com.park.gate.action.v1.order.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * PDA 或道闸相互替换查询实体类
 * Created by zzy on 2017/6/19.
 */
public class Param_PDAOrGateMutualReplace extends BaseParam{

    private String pi_id;   //停车场主键ID
    private String area_code;   //区域号
    private String car_code;     //车牌号

    public String getPi_id() {
        return pi_id;
    }

    public void setPi_id(String pi_id) {
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
