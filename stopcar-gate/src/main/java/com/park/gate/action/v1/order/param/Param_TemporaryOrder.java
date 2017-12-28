package com.park.gate.action.v1.order.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 租赁订单产生了临时费用  直接生成一个临停订单
 *
 * @author jingxiaohu
 */
public class Param_TemporaryOrder extends BaseParam {
    /********************接收参数区*************************/
    private Integer pi_id;//预约停车场主键ID
    private String car_code;//车牌号
    private String area_code;//省市区区域代码
    private Integer final_time;//结算时计费时长（单位分钟）-- 当前时间-创建时间=起步时长+计费时长
    private Integer car_type;//车牌类型:车牌类型 0：未知车牌:、1：蓝牌小汽车、2：: 黑牌小汽车、3：单排黄牌、4：双排黄牌、 5： 警车车牌、6：武警车牌、7：个性化车牌、8：单 排军车牌、9：双排军车牌、10：使馆车牌、11： 香港进出中国大陆车牌、12：农用车牌、13：教 练车牌、14：澳门进出中国大陆车牌、15：双层 武警车牌、16：武警总队车牌、17：双层武警总 队车牌
    private Integer car_code_color;//车牌颜色 0：未知、1：蓝色、2：黄色、3：白色、 4：黑色、5：绿色
    private Integer money;//缴费金额
    private long create_time;//创建时间   13位毫秒数
    private String order_type;// 下单类型 0: 普通下单  1：预约下单 2：租赁临停订单   3:免费转临停   4：包月转临停
    private String order_id;

    /************************get set 方法区****************************/


    public String getCar_code() {
        return car_code;
    }

    public Integer getCar_type() {
        return car_type;
    }

    public void setCar_type(Integer car_type) {
        this.car_type = car_type;
    }

    public Integer getCar_code_color() {
        return car_code_color;
    }

    public void setCar_code_color(Integer car_code_color) {
        this.car_code_color = car_code_color;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public void setCar_code(String car_code) {
        this.car_code = car_code;
    }

    public Integer getFinal_time() {
        return final_time;
    }

    public void setFinal_time(Integer final_time) {
        this.final_time = final_time;
    }

    public Integer getPi_id() {
        return pi_id;
    }

    public void setPi_id(Integer pi_id) {
        this.pi_id = pi_id;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
