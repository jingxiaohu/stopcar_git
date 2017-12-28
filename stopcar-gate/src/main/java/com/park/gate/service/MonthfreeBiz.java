package com.park.gate.service;

import com.park.bean.Park_info;
import com.park.bean.ReturnDataNew;
import com.park.exception.QzException;
import com.park.gate.transaction.GateMonthfreeLogTransactioin;
import com.park.mvc.service.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理包月/免费车记录相关逻辑
 *
 * @author Peter Wu
 */
@Service
public class MonthfreeBiz extends BaseBiz {

  @Autowired
  protected GateMonthfreeLogTransactioin gateMonthfreeLogTransactioin;

  /**
   * 处理包月/免费车记录相关逻辑
   */
  public void record_monthfree_log(ReturnDataNew returnData, long pi_id, String area_code,
      String month_log, String free_log) throws QzException {
    try {
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        returnData.setReturnData(errorcode_param,
            "不存在area_code：" + area_code + "和pi_id:" + pi_id + "对应停车场", null);
        return;
      }
      if (park_info.getLat() == 0 || park_info.getLng() == 0 || park_info.pi_state == 0) {
        returnData.setReturnData(errorcode_param, "停车场未开启", null);
        return;
      }
      long pu_id = park_info.getPu_id();
      if (pu_id <= 0) {
        returnData.setReturnData(errorcode_param, "停车场未绑定商户", null);
        return;
      }

      gateMonthfreeLogTransactioin
          .record_monthfree_log(pu_id, pi_id, area_code, month_log, free_log);
      returnData.setReturnData(errorcode_success,
          "更新停车场包月/免费车记录成功", null);
    } catch (Exception e) {
      log.error("MonthfreeBiz record_monthfree_log is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


}
