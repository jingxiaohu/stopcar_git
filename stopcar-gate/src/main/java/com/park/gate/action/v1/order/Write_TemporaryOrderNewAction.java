
package com.park.gate.action.v1.order;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.gate.action.v1.order.param.Param_TemporaryOrder;
import com.park.gate.service.GateOrderBiz;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * 租赁、本地包月、本地免费订单产生了临时费用  直接生成一个临停订单
 * 包括 本地包月转临停--4、本地免费转临停--3、租赁转临停--2
 * 下单类型由客户端提供
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_TemporaryOrderNewAction extends BaseV1Controller {


    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -3599663972160625262L;

    @Autowired
    private GateOrderBiz gateOrderBiz;

    /**
     * 租赁订单产生了临时费用  直接生成一个临停订单
     *
     * @return
     */
    @RequestMapping(value = "/temporaryorder")
    @ResponseBody
    public String temporaryOrder(HttpServletResponse response, Param_TemporaryOrder param) {
        ReturnDataNew returnData = new ReturnDataNew();
        try {

            if (!checkParam(param, returnData)) {
                sendResp(returnData, response);
                return null;
            }
            gateOrderBiz.temporaryOrder(returnData,param);

        } catch (Exception e) {
            log.error("Write_TemporaryOrderAction temporaryOrder is error-->", e);
            returnData.setReturnData(errorcode_systerm, "system is error", "");
        }
        sendResp(returnData, response);
        return null;
    }

    /**
     * 参数检查
     *
     * @param param
     * @param returnData
     * @return
     * @throws Exception
     */
    private boolean checkParam(Param_TemporaryOrder param, ReturnDataNew returnData) throws Exception {
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

        if (RequestUtil.checkObjectBlank(param.getCar_code())) {
            //car_code 车牌号
            returnData.setReturnData(errorcode_param, " car_code=" + param.getCar_code() + "  car_code is null", null);
            return false;
        } else {
            param.setCar_code(URLDecoder.decode(param.getCar_code(), Constants.SYSTEM_CHARACTER));
        }
        if (RequestUtil.checkObjectBlank(param.getPi_id())) {
            returnData.setReturnData(errorcode_param, " pi_id is null", "");
            return false;
        }
        if (RequestUtil.checkObjectBlank(param.getFinal_time())) {
            returnData.setReturnData(errorcode_param, " final_time is null", "");
            return false;
        }
        if (RequestUtil.checkObjectBlank(param.getMoney())) {
            returnData.setReturnData(errorcode_param, " money is null", "");
            return false;
        }
        if (RequestUtil.checkObjectBlank(param.getArea_code())) {
            //area_code;省市区区域代码
            returnData.setReturnData(errorcode_param, " area_code=" + param.getArea_code() + "  area_code is null", null);
            return false;
        }

        if (param.getCreate_time() == 0) {
            returnData.setReturnData(errorcode_param, "创建时间不能为空", null);
            return false;
        }

        if (RequestUtil.checkObjectBlank(param.getOrder_type())) {
            returnData.setReturnData(errorcode_param, "下单类型不能为空", null);
            return false;
        }

        if(RequestUtil.checkObjectBlank(param.getOrder_id())){
            returnData.setReturnData(errorcode_param,"订单号不能为空",null);
            return false;
        }
        String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.getPi_id(), param.getFinal_time(), param.getMoney(),param.getOrder_id());
        if (!param.sign.equalsIgnoreCase(sign_str)) {
            log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
            returnData.setReturnData(errorcode_param, " 验证签名失败", null);
            return false;
        }
        return true;
    }
}
