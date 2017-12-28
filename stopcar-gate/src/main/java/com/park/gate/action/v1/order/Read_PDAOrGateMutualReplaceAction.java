package com.park.gate.action.v1.order;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.gate.action.v1.order.param.Param_PDAOrGateMutualReplace;
import com.park.gate.service.PDAOrGateMutualReplaceBiz;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * PDA或者道闸出库时，相互代替，获取最新的一条未支付的订单
 * 使用场景：
 * 当车辆已经入库，道闸停车场停电时，需要使用PDA代替道闸进行出库，pda需要获取道闸生成的最新的一条未支付的
 * 订单。
 * Created by zzy on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/v1")
public class Read_PDAOrGateMutualReplaceAction extends BaseV1Controller{

    private static final long serialVersionUID = -9075423752991486172L;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PDAOrGateMutualReplaceBiz pdaOrGateMutualReplaceBiz;

    @RequestMapping(value="/getLatestUnPayOrder")
    public String getLatestUnPayOrder(HttpServletResponse response, Param_PDAOrGateMutualReplace param) {
        ReturnDataNew returnData = new ReturnDataNew();
        try{
            if(!checkParam(param,returnData)){
                sendResp(returnData,response);
                return null;
            }

            pdaOrGateMutualReplaceBiz.getLatestUnPayOrder(param,returnData);

        }catch (Exception e){
            returnData.setReturnData(errorcode_systerm, "system is error", "");
            log.error("异常-->",e);
        }
        sendResp(returnData,response);
        return null;
    }

    /**
     * 参数合法性校验 校验成功返回true
     * @param param
     * @param returnData
     * @return
     * @throws Exception
     */
    private boolean checkParam(Param_PDAOrGateMutualReplace param, ReturnDataNew returnData) throws Exception{
        if(null == param){
            returnData.setReturnData(errorcode_param,"参数传递错误","");
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getPi_id())){
            returnData.setReturnData(errorcode_param,"停车场id不能为空","");
            return false;
        }
        if (RequestUtil.checkObjectBlank(param.getCar_code())) {
            //car_code;//车牌号
            returnData.setReturnData(errorcode_param, "车牌号不能为空", null);
            return false;
        } else {
            param.setCar_code(URLDecoder.decode(param.getCar_code(), Constants.SYSTEM_CHARACTER));
        }
        if(RequestUtil.checkObjectBlank(param.getArea_code())){
            returnData.setReturnData(errorcode_param,"区域号不能为空","");
            return false;
        }
        String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.getPi_id(), param.getArea_code());
        if (!param.getSign().equalsIgnoreCase(sign_str)) {
            log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
            returnData.setReturnData(errorcode_param, " 验证签名失败", null);
            return false;
        }

        return true;
    }
}
