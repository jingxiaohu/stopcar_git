package com.park.app.service;

import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.Rent_defer;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_info;
import com.park.dao.Rent_deferDao;
import com.park.mvc.service.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * app续租--根据续租订单编号获取续租订单
 * @author zyy
 */
@Service
public class RentDeferOneOrderBiz extends BaseBiz{
	
	@Autowired
	private Rent_deferDao rent_deferDao;

	/**
	 * app续租--根据续租订单编号获取续租订单
	 * @param
	 */
	@TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
	public void get_rentDeferOneOrder(ReturnDataNew returnData, long ui_id, String rent_order_id) {
		try {
	        //判断用户是否存在
	        User_info user_info = user_infoDao.selectByKey(ui_id);
	        if (user_info == null) {
	            returnData.setReturnData(errorcode_data, "用户不存在", null);
	            return;
	        }
	        String sql = "select * from rent_defer where ui_id=? and rent_order_id=? limit 1";
			Rent_defer rent_defer = rent_deferDao.queryUniqueT(sql,Rent_defer.class,ui_id,rent_order_id);
			if (rent_defer == null ) {
				returnData.setReturnData(errorcode_data, "获取续租订单失败，请检查ui_id或rent_order_id是否存在", null);
				return;
			}
			//by zyy 20170808 租赁类型 rent_type 为3时，允许时间段 permit_time 设置为全天
			//租赁类型 rent_type 为2时，允许时间段 permit_time 设置为"当日-次日"格式
			if(rent_defer.getRent_type() == 3){
				rent_defer.setPermit_time("全天");
			}else if(rent_defer.getRent_type() == 2){
				String permit_time = rent_defer.getPermit_time();
				StringBuilder stringBuilder = null;
				if(permit_time != null && permit_time.trim().length() > 0){
					stringBuilder = new StringBuilder(permit_time);
					stringBuilder.insert(0,"当日");
					stringBuilder.insert(permit_time.indexOf("-")+3,"次日");
					rent_defer.setPermit_time(stringBuilder.toString());
				}
			}
			//返回数据
			returnData.setReturnData(errorcode_success, "获取续租订单成功", rent_defer);
			return;
		} catch (Exception e) {
			log.error("RentDeferOneOrderBiz get_rentDeferOneOrder is error", e);
	        returnData.setReturnData(errorcode_systerm, "system is error", null);
	        return;
		}
	}

}
