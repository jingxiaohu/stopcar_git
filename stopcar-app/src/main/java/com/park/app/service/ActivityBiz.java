package com.park.app.service;

import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.Activity_info;
import com.park.bean.ReturnDataNew;
import com.park.mvc.service.BaseBiz;
import org.springframework.stereotype.Service;

@Service
public class ActivityBiz extends BaseBiz {

  /**
   * 获取优先的活动
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void weight_activity(ReturnDataNew returnData, int dtype, long ui_id) {
    // TODO Auto-generated method stub
    try {
      /*if(page < 1){
        page = 1;
			}
        	int start = (page-1)*size;*/
      //首先验证用户是否存在
      String sql = "select * from activity_info where state=0 and weight>0  order by weight desc limit 1";
      Activity_info activity_info = getMySelfService().queryUniqueT(sql, Activity_info.class);
      returnData.setReturnData(errorcode_success, "获取成功", activity_info);
      return;

    } catch (Exception e) {
      log.error("ActivityBiz weight_activity is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


}
