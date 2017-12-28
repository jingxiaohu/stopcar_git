
package com.park.mvc.service;

import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.Park_info;
import com.park.bean.ReturnDataNew;
import org.springframework.stereotype.Service;

/**
 * 处理用户订单管理
 *
 * @author jingxiaohu
 */
@Service
public class OrderBiz extends BaseBiz {

  /**
   * 读取停车场详情
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void read_park_info(ReturnDataNew returnData, int dtype, long pi_id,
      String area_code) {

    try {
//	        	Park_info park_info = park_infoDao.selectByKey(pi_id,ReturnParkTableName(area_code));
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        //该停车场不存在
        returnData.setReturnData(errorcode_data, "该停车场不存在", "");
        return;
      }
      //返回结果
      returnData.setReturnData(errorcode_success, "获取停车场详情成功", park_info);
      return;
    } catch (Exception e) {
      log.error("orderBiz read_park_info is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }
}
