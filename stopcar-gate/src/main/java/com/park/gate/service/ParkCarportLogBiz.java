
package com.park.gate.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.bean.Park_carport_log;
import com.park.bean.Park_info;
import com.park.bean.ReturnDataNew;
import com.park.dao.Park_carport_logDao;
import com.park.mvc.service.BaseBiz;

/**
 * 停车场车位总数、已停车位数、空余车位数
 * @author zyy
 */
@Service
public class ParkCarportLogBiz extends BaseBiz {
	@Autowired
	 protected Park_carport_logDao park_carport_logDao;

	/**
	 * 添加  停车场车位总数、已停车位数、空余车位数
	 */
	public void gate_insert_park_carport_log(ReturnDataNew returnData, long pi_id, String area_code, int carport_total, int carport_yet,
			int carport_space, int park_type, int data_flag, String ctime) {
    // TODO Auto-generated method stub
    try {
    	long longDate = Long.parseLong(ctime);
    	Date newCtime = new Date(longDate);
    	Date date = new Date();
    	
    	//判断停车场是否存在
        Park_info parkInfo = returnParkInfo(pi_id, area_code);
    	
        String sql = "SELECT * FROM park_carport_log WHERE pi_id=? AND area_code=? ";
        Park_carport_log park_carport_log = getMySelfService()
            .queryUniqueT(sql, Park_carport_log.class, pi_id, area_code);
        if (park_carport_log != null) {
          returnData.setReturnData(errorcode_data, "该停车场车位数据已存在", "");
          return;
        }
        
        park_carport_log = new Park_carport_log();
        park_carport_log.setPi_id(pi_id);
        park_carport_log.setArea_code(area_code);
        park_carport_log.setCarport_total(carport_total);
        park_carport_log.setCarport_yet(carport_yet);
        park_carport_log.setCarport_space(carport_space);
        park_carport_log.setPark_type(park_type);
        park_carport_log.setData_flag(data_flag);
        park_carport_log.setCtime(newCtime);
        park_carport_log.setStime(date);
        park_carport_log.setNote("");
        
        int ret = park_carport_logDao.insert(park_carport_log);
        if (ret < 1) {
          returnData.setReturnData(errorcode_data, "添加失败", null);
          return;
        }
        
        parkInfo.setCarport_space(carport_space);
        parkInfo.setCarport_total(carport_total);
        parkInfo.setCarport_yet(carport_yet);
        
        park_infoDao.updateByKey(parkInfo);
        
        //返回数据
        returnData.setReturnData(errorcode_success, "添加成功", park_carport_log);
        return;
    } catch (Exception e) {
        // TODO Auto-generated catch block
        log.error("PDAParkCarportLogBiz insert_park_carport_log is error", e);
        returnData.setReturnData(errorcode_systerm, "system is error", null);
        return;
    }
  }

}
