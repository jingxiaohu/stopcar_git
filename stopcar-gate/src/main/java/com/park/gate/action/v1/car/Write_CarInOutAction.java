package com.park.gate.action.v1.car;


import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.gate.action.v1.car.param.Param_CarInOut;
import com.park.gate.service.CarInOutBiz;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * 车辆入库出库记录
 * 应用场景：
 * PDA和道闸停车场在车辆出入库时使用。
 * 业务流程：
 * 1.车辆入库
 *  1.1 PDA系统流程：PDA系统-->中心服务（/pda_check_car_owe 接口）-->PDA系统-->中心服务（/carinout 接口）
 *  1.2 道闸系统流程： 道闸系统-->中心服务（/pda_check_car_owe 接口）-->道闸系统-->中心服务（/carinout 接口）
 * 2.车辆出库
 *  2.1 PDA系统流程：
 *  a.PDA扫描车牌号码或者是手动输入车牌号码；
 *  b.PDA调用中心服务接口（/pda_check_car_owe），判断是否有欠费记录。如果有欠费记录，PDA页面展示只缴本次金额和缴所有金额按钮；
 *
 *  c.选择缴费本次金额，PDA页面调转到选择支付方式页面；
 *  ---未完待续
 *
 *
 * ======================================================================================================
 * 1.订单号由客户端上传，按照改造后订单号规则（32位）。
 * 2.根据客户端上传的客户端订单类型（我定义的是交易类型tradeType），映射为服务端的订单类型，去掉某些判断逻辑。
 * ----------------------------------------------------------------------------------------------
 * 3.对于预约订单，客户端和服务端检查超时不一致情况目前暂不处理,即不生成新的临停订单。
 * 4.对于租赁订单逾期，客户端和服务端检查超时不一致情况目前暂不处理。
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_CarInOutAction extends BaseV1Controller {

  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;

  @Autowired
  private CarInOutBiz carInOutBiz;

  /**
   * 记录车辆入库出库
   * 订单号必传
   */
  @RequestMapping(value = "/carinout")
  @ResponseBody
  public String recordCarInOut(HttpServletRequest request, HttpServletResponse response,
      Param_CarInOut param) {

    ReturnDataNew returnData = new ReturnDataNew();
    try {
      if(!checkParamCarInOut(param,returnData)){
        sendResp(returnData, response);
        return null;
      }

      carInOutBiz.recordCarInOut(param,returnData);

    } catch (Exception e) {
      log.error("Write_carInOutAction.recordCarInOut is error (JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }

    sendResp(returnData, response);
    return null;
  }

  /**
   * 参数检查 ,检查通过返回true
   *
   * @param param
   * @param returnData
   * @return
   */
  private boolean checkParamCarInOut(Param_CarInOut param, ReturnDataNew returnData) throws Exception{
    //参数检查
    if (param == null) {
      //参数传递错误
      returnData.setReturnData(errorcode_param, "参数传递错误", "");
      return false;
    }
    //检查是否进行了参数签名认证
    if (!param.checkRequest()) {
      returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
      return false;
    }
    //对封装的参数对象中的属性进行 非空等规则验证
    if (RequestUtil.checkObjectBlank(param.getPi_id())) {
      //场地主键ID
      returnData.setReturnData(errorcode_param, " pi_id is null", "");
      return false;
    }
    if (RequestUtil.checkObjectBlank(param.getIn_out())) {
      returnData.setReturnData(errorcode_param, " in_out is null", "");
      return false;
    }
    if (RequestUtil.checkObjectBlank(param.getCar_code())) {
      //car_code;//车牌号
      returnData.setReturnData(errorcode_param, "car_code is null", null);
      return false;
    } else {
      param.setCar_code(URLDecoder.decode(param.getCar_code(), Constants.SYSTEM_CHARACTER));
    }
    if (RequestUtil.checkObjectBlank(param.getIs_enter())) {
      //is_enter;//入库或者出库 ：0：   入库 1：出库
      returnData.setReturnData(errorcode_param, "is_enter is null", null);
      return false;
    }
    if(RequestUtil.checkObjectBlank(param.getOrder_id())){
      returnData.setReturnData(errorcode_param, "订单号不能为空", null);
      return false;
    }
    if(RequestUtil.checkObjectBlank(param.getCreate_time()) || param.getCreate_time() < 0 || (param.getCreate_time()+"").length() < 13){
      returnData.setReturnData(errorcode_param,"创建时间为空或参数错误",null);
      return false;
    }
    if(RequestUtil.checkObjectBlank(param.getTarde_type())){
      returnData.setReturnData(errorcode_param,"交易类型不能为空",null);
      return false;
    }

    if (!RequestUtil.checkObjectBlank(param.getArea_code())) {
      //省市县编号 140107
      //避免汉子的问题
      param.setArea_code(URLDecoder.decode(param.getArea_code(), Constants.SYSTEM_CHARACTER));
    }else {
      returnData.setReturnData(errorcode_param,"area_code is null",null);
      return false;
    }
    String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.getPi_id(), param.getIn_out(),param.getOrder_id());
    if (!param.getSign().equalsIgnoreCase(sign_str)) {
      log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
      returnData.setReturnData(errorcode_param, " 验证签名失败", null);
      return false;
    }
    return true;
  }
}
