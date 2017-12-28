package com.park.gate.action.v1.park.param;

import apidoc.jxh.cn.TargetComment;
import com.park.DataSource.TargetDataSource;
import com.park.mvc.action.v1.param.BaseParam;

/**
 * 停车场-获取授权信息
 */
public class Param_park_auth extends BaseParam {
    @TargetComment(value = "停车场主键ID", isnull = "否")
    public Long pi_id;// 停车场主键ID
    @TargetComment(value = "停车场地址区域编码", isnull = "否")
    public String area_code;// 停车场地址区域编码

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


}
