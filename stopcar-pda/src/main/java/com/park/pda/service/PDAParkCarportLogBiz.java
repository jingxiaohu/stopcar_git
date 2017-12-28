
package com.park.pda.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.bean.Park_carport_log;
import com.park.bean.ReturnDataNew;
import com.park.dao.Park_carport_logDao;
import com.park.mvc.service.BaseBiz;

/**
 * 停车场车位总数、已停车位数、空余车位数 流水记录
 * @author zyy
 */
@Service
public class PDAParkCarportLogBiz extends BaseBiz {
	 @Autowired
	 protected Park_carport_logDao park_carport_logDao;

	/**
	 * 添加  停车场车位总数、已停车位数、空余车位数
	 */
	public void insert_park_carport_log(ReturnDataNew returnData, long pi_id, String area_code, int carport_total, int carport_yet,
			int carport_space, int park_type, int data_flag, String ctime) {
    // TODO Auto-generated method stub
    try {
    	long longDate = Long.parseLong(ctime);
    	Date newCtime = new Date(longDate);
    	Date date = new Date();
    	Park_carport_log park_carport_log = new Park_carport_log();
        
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
