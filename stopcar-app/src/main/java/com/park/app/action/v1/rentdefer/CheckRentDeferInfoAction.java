package com.park.app.action.v1.rentdefer;

import com.park.app.action.v1.rentdefer.param.CheckRentDeferInfo;
import com.park.app.service.RentDeferByAPPBiz;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 租赁订单续租信息校验
 * Created by zzy on 2017/8/28.
 */
@Controller
@RequestMapping("/v1")
public class CheckRentDeferInfoAction extends BaseV1Controller {

    @Autowired
    private RentDeferByAPPBiz rentDeferByAPPBiz;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/rentOrderInfoCheck")
    public String checkRentDeferInfo(CheckRentDeferInfo checkRentDeferInfo, HttpServletResponse response){
        ReturnDataNew returnData = new ReturnDataNew();
        try{
            if(!checkParam(checkRentDeferInfo,returnData)){
                sendResp(returnData,response);
                return null;
            }

            if(!rentDeferByAPPBiz.isRentDefer(checkRentDeferInfo,returnData)){
                sendResp(returnData,response);
                return null;
            }

            returnData.setReturnData(errorcode_success,"成功","");

        } catch (Exception e){
            log.error("异常-->",e);
            returnData.setReturnData(errorcode_systerm,"system is error","");
        }
        sendResp(returnData,response);
        return null;
    }

    /**
     * 参数合法性校验
     * @return
     */
    private boolean checkParam(CheckRentDeferInfo param, ReturnDataNew returnDataNew){
        if(null == param){
            returnDataNew.setReturnData(errorcode_param,"参数传递错误",null);
            return false;
        }
        //检查是否进行了参数签名认证
        if (!param.checkRequest()) {
            returnDataNew.setReturnData(errorcode_param, "没有进行参数签名认证", "");
            return false;
        }

        if(RequestUtil.checkObjectBlank(param.getUi_id())){
            returnDataNew.setReturnData(errorcode_param,"ui_id is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getCpr_id())){
            returnDataNew.setReturnData(errorcode_param,"cpr_id is null",null);
            return false;
        }

        if(RequestUtil.checkObjectBlank(param.getCar_code())){
            returnDataNew.setReturnData(errorcode_param,"car_code is null",null);
            return false;
        }

        if(RequestUtil.checkObjectBlank(param.getUnit_price())){
            returnDataNew.setReturnData(errorcode_param,"unit_price is null",null);
            return false;
        }
        String signStr = getSignature(Constants.SYSTEM_KEY, param.getDtype(), param.getCpr_id(),param.getUi_id());
        if(!signStr.equalsIgnoreCase(param.getSign())){
            returnDataNew.setReturnData(errorcode_param,"验证签名失败",null);
            return false;
        }
        return true;
    }
}
