
package com.park.mvc.service;

import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.Rental_charging_rule;
import com.park.bean.ReturnDataNew;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 处理停车场信息的业务逻辑管理类
 *
 * @author jingxiaohu
 */
@Service
public class ParkinfoBiz extends BaseBiz {


  /**
   * 读取停车场计费规则信息
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void read_charging_rule(ReturnDataNew returnData, long pi_id,
      String area_code) {
    // TODO Auto-generated method stub
    try {
      //首先获取该停车场规则
      List<Rental_charging_rule> list = queryChargeRule(pi_id, area_code);
      if (list == null || list.size() == 0) {
        returnData.setReturnData(errorcode_data, "该停车场还没有制定规则", "");
        return;
      }

      returnData.setReturnData(errorcode_success, "读取停车场计费规则信息成功", list);
      return;
    } catch (Exception e) {
      log.error("ParkinfoBiz read_charging_rule is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 查询场地规则数据
   */
  public List<Rental_charging_rule> queryChargeRule(long pi_id, String area_code) {
    try {
      //select *  from  rental_charging_rule where pi_id=111 and area_code='510502' ORDER BY is_default desc ,ctime asc
      String sql = "select *  from  rental_charging_rule where pi_id=? and area_code=? ORDER BY is_default desc ,ctime asc";
      List<Rental_charging_rule> cr = getMySelfService()
          .queryListT(sql, Rental_charging_rule.class, pi_id, area_code);
      return cr;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("queryRentChargeRuleByPiId is error " + e.getMessage(), e);
    }
    return null;
  }

}
