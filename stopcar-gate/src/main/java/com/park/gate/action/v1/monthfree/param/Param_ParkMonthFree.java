package com.park.gate.action.v1.monthfree.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * Created by zzy on 2017/6/16.
 */
public class Param_ParkMonthFree extends BaseParam{
    //private String pu_id;               //商户ID
    private Long pi_id;               //停车场ID
    private String area_code;           //地址编号
    private String car_code;            //车牌号
    private Long money;               //该次缴纳费用(单位 分)
    private Integer car_type;            //车牌类型 0：未知车牌:、1：蓝牌小汽车、2：: 黑牌小汽车、3：单排黄牌、4：双排黄牌、 5： 警车车牌、6：武警车牌、7：个性化车牌、8：单 排军车牌、9：双排军车牌、10：使馆车牌、11： 香港进出中国大陆车牌、12：农用车牌、13：教 练车牌、14：澳门进出中国大陆车牌、15：双层 武警车牌、16：武警总队车牌、17：双层武警总 队车牌
    private Integer car_code_color;      //车牌颜色 0：未知、1：蓝色、2：黄色、3：白色、 4：黑色、5：绿色
    private Integer car_color;           //车辆颜色 BLUE("蓝色", 1), YELLOW("黄色", 2), WHITE("白色", 3), BLACK("黑色", 4), GREEN("绿色", 5)
    private String start_time_str;      //开始包月时间(2017-03-22 10:00:00)
    private String end_time_str;        //包月到期时间(2017-03-22 10:00:00)
    private Integer type;                //类型(0:包月车辆 1:免费车辆)
    private Long ctime;               //创建时间
    private Long utime;               //更新时间
    //private String note;                //备注
    private String car_tel;             //车主电话
    private String car_name;            //车主姓名
    //private String stime;               //服务器端创建时间
    private String local_loginname;     //本地管理人员的帐号
    private String client_id;          //道闸本地记录的主键ID

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

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
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

    public Integer getCar_color() {
        return car_color;
    }

    public void setCar_color(Integer car_color) {
        this.car_color = car_color;
    }

    public String getStart_time_str() {
        return start_time_str;
    }

    public void setStart_time_str(String start_time_str) {
        this.start_time_str = start_time_str;
    }

    public String getEnd_time_str() {
        return end_time_str;
    }

    public void setEnd_time_str(String end_time_str) {
        this.end_time_str = end_time_str;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getUtime() {
        return utime;
    }

    public void setUtime(Long utime) {
        this.utime = utime;
    }

    public String getCar_tel() {
        return car_tel;
    }

    public void setCar_tel(String car_tel) {
        this.car_tel = car_tel;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getLocal_loginname() {
        return local_loginname;
    }

    public void setLocal_loginname(String local_loginname) {
        this.local_loginname = local_loginname;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
}
