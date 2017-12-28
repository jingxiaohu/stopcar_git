package com.park.app.service;

import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.Carcode_park_rent;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_info;
import com.park.dao.Carcode_park_rentDao;
import com.park.mvc.service.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * app根据用户车牌查询租赁信息
 * @author zyy
 */
@Service
public class CarcodeParkRentBiz extends BaseBiz{
	
	@Autowired
	private Carcode_park_rentDao carcode_park_rentDao;

	/**
	 * 用户根据车牌号查询租赁信息
	 * @param returnData
	 * @param ui_id 用户id
	 * @param car_code 用户车牌号
	 * @param page 当前页号
	 * @param size 每页条数
	 */
	@TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
	public void getParkRentInfoByCarcode(ReturnDataNew returnData, long ui_id, String car_code, int page, int size) {
		try {
			//判断用户是否存在
	        User_info user_info = user_infoDao.selectByKey(ui_id);
	        if (user_info == null) {
	            returnData.setReturnData(errorcode_data, "用户信息不存在", "");
	            return;
	        }
	        if (page < 1) {
	            page = 1;
	        }
	        int start = (page - 1) * size;
	        String sql = "SELECT t1.cpr_id,t1.pi_id,t1.area_code,t1.pi_name,t2.money as unit_price,t1.starttime,t1.endtime,t1.stime,t1.utime,t1.ui_id,t1.ui_nd,t1.car_code,t2.permit_time,t1.rent_type,t1.is_del,t1.is_expire,t1.note,t1.client_rule_id,t1.address_name,t1.rd_id " +
					" FROM carcode_park_rent t1 INNER JOIN client_gate_rule t2 " +
					" ON t1.client_rule_id = t2.client_ruleid AND t1.pi_id=t2.pi_id AND t1.area_code=t2.area_code " +
					" where t1.ui_id=? and t1.car_code=? and t1.is_del=0 order by t1.utime desc limit ?,?";
	        List<Carcode_park_rent> returnList = carcode_park_rentDao.executeQuery(sql, Carcode_park_rent.class, ui_id,car_code,start,size);
	        if (returnList == null || returnList.size() == 0) {
	            returnData.setReturnData(errorcode_success, "未获取到数据", returnList);
	            return;
	        }

	        //by zyy 20170808 租赁类型 rent_type 为3时，允许时间段 permit_time 设置为全天
			//租赁类型 rent_type 为2时，允许时间段 permit_time 设置为"当日-次日"格式
			List<Carcode_park_rent> parkRentInfoList = new ArrayList<Carcode_park_rent>();
			for (Carcode_park_rent carcode_park_rent: returnList) {
				if(carcode_park_rent.getRent_type() == 3){
					carcode_park_rent.setPermit_time("全天");
				}else if(carcode_park_rent.getRent_type() == 2){
					String permit_time = carcode_park_rent.getPermit_time();
					StringBuilder stringBuilder = null;
					if(permit_time != null && permit_time.trim().length() > 0){
						stringBuilder = new StringBuilder(permit_time);
						stringBuilder.insert(0,"当日");
						stringBuilder.insert(permit_time.indexOf("-")+3,"次日");
						carcode_park_rent.setPermit_time(stringBuilder.toString());
					}
				}
				parkRentInfoList.add(carcode_park_rent);
			}
			//返回数据
			returnData.setReturnData(errorcode_success, "查询租赁信息成功", parkRentInfoList);
			return;
		} catch (Exception e) {
			log.error("CarcodeParkRentBiz getParkRentInfoByCarcode is error", e);
	        returnData.setReturnData(errorcode_systerm, "system is error", null);
	        return;
		}

	}

}
