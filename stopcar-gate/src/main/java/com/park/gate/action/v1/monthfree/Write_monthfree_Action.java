package com.park.gate.action.v1.monthfree;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.gate.action.v1.monthfree.param.MonthfreeParams;
import com.park.gate.service.MonthfreeBiz;
import com.park.mvc.action.v1.BaseV1Controller;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 包月/免费车记录
 *
 * @author Peter Wu
 */
@RestController
@RequestMapping(value = "/v1")
public class Write_monthfree_Action extends BaseV1Controller {


  private static final long serialVersionUID = -7623426715226093753L;

  @Autowired
  private MonthfreeBiz monthfreeBiz;

  /**
   * 记录/更新服务器包月/免费车记录，
   * 每次调用接口，根据停车场ID与地址编号查询对应商户ID并设置到相关记录
   * 服务器删除对应停车场所有记录再记录新的数据。
   */
  @RequestMapping(value = "/record_monthfree_log")
  public void record_monthfree_log(HttpServletResponse response, MonthfreeParams param) {

    ReturnDataNew returnData = new ReturnDataNew();
    try {
      long pi_id = param.getPi_id();
      if (pi_id <= 0) {
        returnData.setReturnData(errorcode_param, "pi_id不能为空", null);
        sendResp(returnData, response);
        return;
      }
      String area_code = param.getArea_code();
      if (!StringUtils.hasText(area_code)) {
        returnData.setReturnData(errorcode_param, "area_code不能为空", null);
        sendResp(returnData, response);
        return;
      }
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, pi_id,
          area_code);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return;
      }
//处理业务
      monthfreeBiz.record_monthfree_log(returnData, pi_id, area_code, param.getMonth_log(),
          param.getFree_log());
      sendResp(returnData, response);
    } catch (Exception e) {
      log.error("Write_monthfree_Action.record_monthfree_log is error (JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
      sendResp(returnData, response);
    }
  }

}
