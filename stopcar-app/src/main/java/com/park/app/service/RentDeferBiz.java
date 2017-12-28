package com.park.app.service;

import com.park.bean.Rent_defer;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_info;
import com.park.dao.Rent_deferDao;
import com.park.mvc.service.BaseBiz;
import com.park.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * app续约租赁订单管理
 * @author zyy
 */
@Service
public class RentDeferBiz extends BaseBiz{

	@Autowired
	private Rent_deferDao rent_deferDao;

	/**
	 * app获取续约租赁订单
	 * @param
	 */
	public void get_rentDeferInfo(ReturnDataNew returnData, long ui_id, Long pi_id, String area_code,int page,int size) {
		try {
	        //判断用户是否存在
	        User_info user_info = user_infoDao.selectByKey(ui_id);
	        if (user_info == null) {
	            returnData.setReturnData(errorcode_data, "用户不存在", null);
	            return;
	        }

			if (page < 1) {
				page = 1;
			}
			int start = (page - 1) * size;

			Object[] params = null;
			StringBuilder sql = new StringBuilder("select * from rent_defer where ui_id=? ");
	        if(!RequestUtil.checkObjectBlank(pi_id) && !RequestUtil.checkObjectBlank(area_code)){
	        	sql.append(" and pi_id=? and area_code=? ");
				params = new Object[]{ui_id,pi_id,area_code};
	        }else{
				params = new Object[]{ui_id};
			}
			sql.append(" order by utime desc limit "+start+","+size);
			List<Rent_defer> returnList = rent_deferDao.executeQuery(sql.toString(), Rent_defer.class, params);
			if (returnList == null || returnList.size() == 0) {
				returnData.setReturnData(errorcode_success, "未获取到数据", returnList);
				return;
			}
			//by zyy 20170808 租赁类型 rent_type 为3时，允许时间段 permit_time 设置为全天
			//租赁类型 rent_type 为2时，允许时间段 permit_time 设置为"当日-次日"格式
			List<Rent_defer> rent_defer_list = new ArrayList<Rent_defer>();
			for (Rent_defer rent_defer: returnList) {
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

				rent_defer_list.add(rent_defer);
			}

			//返回数据
			returnData.setReturnData(errorcode_success, "获取续约租赁订单成功", rent_defer_list);
			return;
		} catch (Exception e) {
			log.error("RentDeferBiz get_rentDeferInfo is error", e);
	        returnData.setReturnData(errorcode_systerm, "system is error", null);
	        return;
		}
	}

}
