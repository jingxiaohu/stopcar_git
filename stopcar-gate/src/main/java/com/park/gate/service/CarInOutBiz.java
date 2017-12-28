package com.park.gate.service;

import com.park.bean.*;
import com.park.exception.QzException;
import com.park.gate.action.v1.car.param.Param_CarInOut;
import com.park.gate.transaction.CarInOutTransaction;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.ActivityPB;
import com.park.mvc.service.common.PayParkPB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 车辆出入库服务类
 * Created by zzy on 2017/6/12.
 */
@Service("CarInOutBiz")
public class CarInOutBiz extends BaseBiz {
    @Autowired
    private GateCarBiz gateCarBiz;

    @Autowired
    private CarInOutTransaction carInOutTransaction;

    /**
     * 车辆出入库
     * @param carInOut
     * @param returnData
     */
    public void recordCarInOut(Param_CarInOut carInOut, ReturnDataNew returnData) {
        try {
            int isEnter = carInOut.getIs_enter();
            //首先判断是否已经进行了录入  如果数据库中没有该停车场的数据则不进行插入
            //获取该场地的信息
            Park_info park_info = returnParkInfo(carInOut.getPi_id(), carInOut.getArea_code());
            if (park_info == null) {
                returnData.setReturnData(errorcode_data, "该停车场不存在", "1");
                return;
            }
            Car_in_out car_in_out = insertCarInOut(park_info, carInOut, returnData);
            if (null == car_in_out) {
                log.info("车辆出入库失败");
                return;
            }

            //入库操作
            if (isEnter == 0) {
                //doCarIn(carInOut, car_in_out, park_info, returnData);
                carInOutTransaction.doCarIn(carInOut, car_in_out, park_info, returnData);
            //出库
            } else {
                //doCarOut(carInOut.getOrder_id(), car_in_out, park_info, returnData);
                carInOutTransaction.doCarOut(carInOut.getOrder_id(), car_in_out);
            }
            if(null == returnData.getErrormsg() || "".equals(returnData.getErrormsg())){
                returnData.setReturnData(errorcode_success, "车辆出入库成功", car_in_out);
            }

        } catch (Exception e) {
            log.error("ParkinfoBiz record_car_in_out is error", e);
            returnData.setReturnData(errorcode_systerm, "system is error ", null);
        }
    }


    /**
     * 生成停车场出入记录
     *
     * @param carInOut
     * @param returnData
     * @return
     * @throws Exception
     */
    public Car_in_out insertCarInOut(Park_info park_info, Param_CarInOut carInOut, ReturnDataNew returnData) throws Exception {
        //用户ID 通过车牌号获取用户ID
        long ui_id = 0;
        String ui_nd = null;  //用户唯一标识
        String ui_tel = null;
        User_carcode userCarcode = gateCarBiz.queryUserCarBycode(carInOut.getCar_code());
        if (null != userCarcode) {
            ui_id = userCarcode.getUi_id();
            ui_nd = userCarcode.getUi_nd();
            ui_tel = userCarcode.getUi_tel();
        }

        Date date = new Date(carInOut.getCreate_time());

        Car_in_out car_in_out = new Car_in_out();
        if (carInOut.getIs_sync() != null && carInOut.getIs_sync() == 1) {//异步上传
            car_in_out.setIs_sync(1);
        }
        car_in_out.setCtime(date);
        car_in_out.setIn_out(carInOut.getIn_out());
        car_in_out.setIn_out_code(carInOut.getIn_out_code());
        car_in_out.setPi_id(carInOut.getPi_id());
        car_in_out.setCar_code(carInOut.getCar_code());
        car_in_out.setIs_enter(carInOut.getIs_enter());
        //获取是该停车场的 出入口设备信息表的主键ID
        car_in_out.setPd_id(carInOut.getPd_id());
        car_in_out.setUi_id(ui_id);
        if (ui_nd != null) {
            car_in_out.setUi_nd(ui_nd);
        }
        if (ui_tel != null) {
            car_in_out.setUi_tel(ui_tel);
        }
        car_in_out.setOrder_id(carInOut.getOrder_id());
        car_in_out.setUtime(date);
        car_in_out.setCar_type(carInOut.getCar_type());
        car_in_out.setCar_code_color(carInOut.getCar_code_color());
        //属于哪个省市区代码
        car_in_out.setArea_code(park_info.getArea_code());
        //入库/出库类型: (0:正常出入库 1：道闸本地临停出入库 2：道闸本地包月出入库   3：异常出入库)
        if (carInOut.getIs_sync() != null && carInOut.getIs_sync() == 1) {
            car_in_out.setOut_type(4);
        } else {
            car_in_out.setOut_type(carInOut.getOut_type());
        }
        //是否是道闸本地包月车辆 0:不是 1：是
        int is_local_month = 0;
        if("5".equals(carInOut.getTarde_type())){
            is_local_month = 1;
        }
        car_in_out.setIs_local_month(is_local_month);

        //地磁停车场车位编号（政府部门统一分配）不为空时存入数据库
        if (null != carInOut.getGov_num()) {
            car_in_out.setGov_num(carInOut.getGov_num());
        }

        //生成车辆出入停车场记录
        int id = car_in_outDao.insert(car_in_out);
        if (id < 1) {
            returnData.setReturnData(errorcode_data, "车辆入库出库记录失败", "3");
            return null;
        }
        car_in_out.setCio_id(id);
        return car_in_out;
    }


    /**
     * 判断预约订单是否超时，超时true，未超时false
     *
     * @param pay_park
     * @return
     */
    private boolean isTimeOut(Date date,Pay_park pay_park) {
        if (date.getTime() - pay_park.getCtime().getTime() <= pay_park.getExpect_time() * 60 * 1000) {
            return false;
        } else {
            return true;
        }
    }


}
