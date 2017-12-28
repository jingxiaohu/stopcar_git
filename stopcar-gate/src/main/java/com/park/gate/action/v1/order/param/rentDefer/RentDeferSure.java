package com.park.gate.action.v1.order.param.rentDefer;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * Created by zzy on 2017/7/6.
 */
public class RentDeferSure extends BaseParam{
    private String rent_order_id;   //服务端生成的租赁订单号
    private String client_order_id;
    private String car_code;
    private String area_code;
    private Long pi_id;
    private Long endtime;//租赁的到期时间
    private Integer defer_state;   //2：续约成功  4：续约失败-退款钱包
    private String note;   //备注（如果续租失败，上传失败原因）
    
    
    public Long getEndtime() {
		return endtime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	public String getRent_order_id() {
        return rent_order_id;
    }

    public void setRent_order_id(String rent_order_id) {
        this.rent_order_id = rent_order_id;
    }

    public String getClient_order_id() {
        return client_order_id;
    }

    public void setClient_order_id(String client_order_id) {
        this.client_order_id = client_order_id;
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

    public Integer getDefer_state() {
        return defer_state;
    }

    public void setDefer_state(Integer defer_state) {
        this.defer_state = defer_state;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
