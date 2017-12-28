package com.park.gate.service;

import com.park.bean.Pay_month_park;
import com.park.dao.Pay_month_parkDao;
import com.park.dao.Pay_parkDao;
import com.park.gate.action.v1.order.param.Param_order_fake_del;
import com.park.mvc.service.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.bean.Pay_park;
import com.park.bean.ReturnDataNew;

import java.util.Date;

@Service("orderFakeDelBiz")
public class OrderFakeDelBiz extends BaseBiz {

	/**
	 * 用户支付停车下单表
	 */
	private static String orderType_1 = "1";
	/**
	 * 用户支付租赁停车下单表
	 */
	private static String orderType_2 = "2";
	
	public void updateMagneticState(ReturnDataNew returnData,Param_order_fake_del param){
		try{
			
			String orderType = param.getOrder_type();
			
			if(orderType_1.equals(orderType)){//非包月车辆订单
				excutePayPark(param,returnData);
			}else if(orderType_2.equals(orderType)){//包月车辆订单
				excutePayMonthPark(param,returnData);
			}else{
				returnData.setReturnData(errorcode_param, "订单类型不正确", null);
			}
			
		}catch(Exception e){
			log.error("更新异常-->",e);
			returnData.setReturnData(errorcode_systerm, "system is error", null);
		}
	}
	
	/**
	 * 更新 删除状态0:正常1：假删除
	 * @param param
	 * @param returnData
	 * @throws Exception
	 */
	private void excutePayPark(Param_order_fake_del param,ReturnDataNew returnData) throws Exception{
		//检查订单号是否存在
		String sql = "select * from pay_park where my_order = ? and pi_id = ? and area_code = ?";
		Pay_park park = getMySelfService().queryUniqueT(sql, Pay_park.class, param.getMy_order(),param.getPi_id(),param.getArea_code());
		if(null == park){
			returnData.setReturnData(errorcode_data, "地区对应的停车场的临停订单不存在", null);
			return ;
		}
		
		park.setIs_del(1);
		//park.setUtime(new Date());
		//park.setNote("修改删除状态");
		int result = pay_parkDao.updateByKey(park);
		if(result >= 1){
			returnData.setReturnData(errorcode_success, "操作成功", null);
		}else{
			returnData.setReturnData(errorcode_systerm, "操作失败", null);
		}
	}

	/**
	 * 更新 删除状态0:正常1：假删除
	 * @param param
	 * @param returnData
	 * @throws Exception
	 */
	private void excutePayMonthPark(Param_order_fake_del param,ReturnDataNew returnData) throws Exception{
		//检查订单号是否存在
		String sql = "select * from pay_month_park where my_order = ? and pi_id = ? and area_code = ?";
		Pay_month_park park = getMySelfService().queryUniqueT(sql, Pay_month_park.class, param.getMy_order(),param.getPi_id(),param.getArea_code());
		if(null == park){
			returnData.setReturnData(errorcode_data, "地区对应的停车场的租赁订单不存在", null);
			return ;
		}

		park.setIs_del(1);
		//park.setUtime(new Date());
		//park.setNote("修改删除状态");
		int result = pay_month_parkDao.updateByKey(park);
		if(result >= 1){
			returnData.setReturnData(errorcode_success, "操作成功", null);
		}else{
			returnData.setReturnData(errorcode_systerm, "操作失败", null);
		}
	}
}
