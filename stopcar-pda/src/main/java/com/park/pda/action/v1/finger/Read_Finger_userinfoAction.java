package com.park.pda.action.v1.finger;

import com.park.bean.ReturnDataNew;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.finger.param.Param_finger_userinfo;
import com.park.pda.service.Finger_userinfoBiz;
import com.park.sign.ApiDoc;
import com.park.sign.ApiSign;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 读取用户指纹/指静脉信息
 *
 * @author Peter Wu
 */
@Controller
@RequestMapping(value = "v1", name = "指纹支付")
public class Read_Finger_userinfoAction extends BaseV1Controller {

  private static final long serialVersionUID = -6603509476719362652L;

  @Autowired
  protected Finger_userinfoBiz finger_userinfoBiz;

  /**
   * 根据车牌读取用户指纹/指静脉信息
   * finger_flag用户指纹/指静脉标记 0:指静脉 1:指纹
   */
  @RequestMapping(value = "/read_finger_userinfo", name = "根据车牌读取用户指纹/指静脉信息")
  @ApiSign({"dtype", "car_code", "finger_flag"})
  //@ApiDoc(tableNames = {"finger_userinfo"}, requires = {"dtype", "car_code", "finger_flag"})
  public void read_finger_userinfo(HttpServletResponse response,
      @Validated Param_finger_userinfo param)
      throws Exception {
    ReturnDataNew returnData = new ReturnDataNew();
    finger_userinfoBiz.read_finger_userinfo(returnData, param.car_code, param.finger_flag);
    sendResp(returnData, response);
  }
}