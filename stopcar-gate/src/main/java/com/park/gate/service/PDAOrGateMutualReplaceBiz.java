package com.park.gate.service;

import com.park.bean.Park_info;
import com.park.bean.Pay_park;
import com.park.bean.ReturnDataNew;
import com.park.gate.action.v1.order.param.Param_PDAOrGateMutualReplace;
import com.park.mvc.service.BaseBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * PDA或者道闸出库时，相互代替，获取最新的一条未支付的订单
 * Created by zzy on 2017/6/19.
 */
@Service("pdaOrGateMutualReplaceBiz")
public class PDAOrGateMutualReplaceBiz extends BaseBiz{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取最新的一条未支付的订单 pay_park表 ctime字段倒序
     * @param param
     * @param returnData
     */
    public void getLatestUnPayOrder(Param_PDAOrGateMutualReplace param, ReturnDataNew returnData){
        try{
            long pi_id = Long.parseLong(param.getPi_id());
            Park_info park_info = returnParkInfo(pi_id, param.getArea_code());
            if (park_info == null) {
                returnData.setReturnData(errorcode_data, "该停车场不存在", "1");
                return;
            }

            String sql = "select * from pay_park t where t.pp_state = 0 and t.pi_id = ? and t.area_code = ? and t.car_code = ? order by ctime desc limit 1";

            Pay_park pay_park = getMySelfService().queryUniqueT(sql,Pay_park.class,pi_id,param.getArea_code(),param.getCar_code());

            if(null == pay_park){
                returnData.setReturnData(errorcode_success, "没有未支付的订单", "");
            }else{
                returnData.setReturnData(errorcode_success, "操作成功", pay_park);
            }

        } catch (Exception e){
            returnData.setReturnData(errorcode_systerm, "system is error", "");
        }

    }

}
