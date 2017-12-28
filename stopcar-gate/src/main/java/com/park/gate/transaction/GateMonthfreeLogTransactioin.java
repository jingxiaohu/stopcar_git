package com.park.gate.transaction;

import com.alibaba.fastjson.JSONArray;
import com.park.bean.Merchant_parkinfo_monthfree_log;
import com.park.dao.Merchant_parkinfo_monthfree_logDao;
import com.park.exception.QzException;
import com.park.mvc.service.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * 处理包月/免费车记录相关事务类
 *
 * @author Peter Wu
 */
@Transactional(rollbackFor = QzException.class)
@Service
public class GateMonthfreeLogTransactioin extends BaseBiz {

  @Autowired
  private Merchant_parkinfo_monthfree_logDao monthfree_logDao;

  /**
   * 处理包月/免费车记录相关逻辑
   */
  public void record_monthfree_log(long pu_id, long pi_id, String area_code,
      String month_log, String free_log) throws QzException {
    try {
      recordLog(pu_id, pi_id, area_code, month_log, 0);//处理包月
      recordLog(pu_id, pi_id, area_code, free_log, 1);//处理免费
    } catch (Exception e) {
      log.error("数据库更新包月/免费车记录出错", e);
      throw new QzException("数据库更新包月/免费车记录出错");
    }
  }

  /**
   * 记录数据
   */
  private void recordLog(long pu_id, long pi_id, String area_code,
      String monthfree_log, int type)
      throws SQLException {
    if (StringUtils.hasText(monthfree_log)) {
      List<Merchant_parkinfo_monthfree_log> monthfree_logs = JSONArray
          .parseArray(monthfree_log, Merchant_parkinfo_monthfree_log.class);
      for (Merchant_parkinfo_monthfree_log month_log : monthfree_logs) {
        month_log.setPi_id(pi_id);
        month_log.setArea_code(area_code);
        month_log.setPu_id(pu_id);
        month_log.setType(type);
      }
      //删除停车场相关记录
      HashMap<String, Object> map = new HashMap<>();
      map.put("pi_id", pi_id);
      map.put("area_code", area_code);
      map.put("type", type);
      monthfree_logDao.getJdbc().update(
          "DELETE FROM merchant_parkinfo_monthfree_log WHERE pi_id=:pi_id AND area_code=:area_code AND type=:type",
          map);
      //记录新的记录
      monthfree_logDao.insert(monthfree_logs);
    }
  }

}
