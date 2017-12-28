package com.park.gate.service;

import com.park.bean.Pay_month_park;
import com.park.dao.Pay_month_parkDao;
import com.park.dao.Pay_parkDao;
import com.park.gate.action.v1.order.param.Param_parkConfirmCarOut;
import com.park.mvc.service.BaseBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.bean.Pay_park;
import com.park.bean.ReturnDataNew;

import java.util.Date;

/**
 * 道闸确认车辆已出库服务层
 * @author zzy
 *
 */
@Service("parkConfirmCarOutBiz")
public class ParkConfirmCarOutBiz extends BaseBiz {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 用户支付停车下单表
	 */
	private static String orderType_1 = "1";
	/**
	 * 用户支付租赁停车下单表
	 */
	private static String orderType_2 = "2";

	/**
	 * 更新智能磁场入库出库状态
	 * @param returnData
	 * @param param
	 */
	public void updateMagneticState(ReturnDataNew returnData,Param_parkConfirmCarOut param){
		try{
			
			String orderType = param.getOrder_type();
			
			if(orderType_1.equals(orderType)){//非包月车辆订单
				excutePayPark(param,returnData);
			}else if(orderType_2.equals(orderType)){//包月车辆订单
				excutePayMonthPark(param,returnData);
			}else{
				returnData.setReturnData(errorcode_systerm, "订单类型不正确", null);
			}
			
		}catch(Exception e){
			log.error("异常-->",e);
			 returnData.setReturnData(errorcode_systerm, "system is error", null);
		}
	}
	
	/**
	 * 更新 智能磁场入库出库状态（1:已入库  2:已出库  3:车辆逃逸  4:其它故障）
	 * @param param
	 * @param returnData
	 * @throws Exception
	 */
	public void excutePayPark(Param_parkConfirmCarOut param,ReturnDataNew returnData) throws Exception{
		//检查订单号是否存在
		String sql = "select * from pay_park where my_order = ?";
		Pay_park park = getMySelfService().queryUniqueT(sql, Pay_park.class, param.getMy_order());
		if(null == park){
			returnData.setReturnData(errorcode_systerm, "该临停订单不存在", null);
			return ;
		}

		park.setMagnetic_state(2);
		//park.setUtime(new Date());
		int result = pay_parkDao.updateByKey(park);
		if(result >= 1){
			returnData.setReturnData(errorcode_success, "操作成功", null);
		}else{
			returnData.setReturnData(errorcode_systerm, "操作失败", null);
		}
	}

	/**
	 * 更新 智能磁场入库出库状态（1:已入库  2:已出库  3:车辆逃逸  4:其它故障）
	 * @param param
	 * @param returnData
	 * @throws Exception
	 */
	public void excutePayMonthPark(Param_parkConfirmCarOut param,ReturnDataNew returnData) throws Exception{
		//检查订单号是否存在
		String sql = "select * from pay_month_park where my_order = ?";
		Pay_month_park park = getMySelfService().queryUniqueT(sql, Pay_month_park.class, param.getMy_order());
		if(null == park){
			returnData.setReturnData(errorcode_systerm, "该租赁订单不存在", null);
			return ;
		}

		park.setMagnetic_state(2);
		//park.setUtime(new Date());
		int result = pay_month_parkDao.updateByKey(park);
		if(result >= 1){
			returnData.setReturnData(errorcode_success, "操作成功", null);
		}else{
			returnData.setReturnData(errorcode_systerm, "操作失败", null);
		}
	}
}
