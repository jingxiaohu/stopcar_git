package com.park.pda.action.v1.fingerveintest;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.fingerveintest.param.Param_pdaSureTest;
import com.park.pda.action.v1.fingerveintest.param.UserInfoTestReq;
import com.park.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 指纹静脉信息采集
 * Created by zzy on 2017/8/29.
 */
@Controller
@RequestMapping("/v1")
public class Write_FingerPrintVeinCollectTestAction extends BaseV1Controller {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FingerPrintVeinCollectTestBiz fingerPrintVeinCollectTestBizBiz;

    /**
     * 采集用户身份信息
     *
     * @param response
     * @return
     */
    @RequestMapping("/collect_userinfo_fingervein_test")
    public void collectionUserInfo(UserInfoTestReq req, HttpServletResponse response) {
        ReturnDataNew returnData = new ReturnDataNew();
        try {
            if (!checkUserInfo(req, returnData)) {
                sendResp(returnData, response);
                return ;
            }

            fingerPrintVeinCollectTestBizBiz.collectUserInfo(req, returnData);

        } catch (Exception e) {
            log.error("采集用户身份信息异常-->", e);
        }
        sendResp(returnData, response);
    }

    /**
     * 指纹静脉验证
     * @param response
     * @return
     */
    @RequestMapping("/fingerVeinVerify_test")
    public String fingerVeinVerify(HttpServletResponse response, Param_pdaSureTest req){
        ReturnDataNew returnData = new ReturnDataNew();
        try{
            if (null == req) {
                returnData.setReturnData(errorcode_param, "参数传递错误", null);
                sendResp(returnData,response);
                return null;
            }
            if (!req.checkRequest()) {
                returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
                sendResp(returnData,response);
                return null;
            }
            if(RequestUtil.checkObjectBlank(req.getFinger_veno())){
                returnData.setReturnData(errorcode_param, "finger_veno is null", "");
                sendResp(returnData,response);
                return null;
            }
            if(RequestUtil.checkObjectBlank(req.getFingerprint())){
                returnData.setReturnData(errorcode_param, "fingerprint is null", "");
                sendResp(returnData,response);
                return null;
            }
            String signStr = getSignature(Constants.SYSTEM_KEY, req.getDtype(), req.getTel());
            if (!req.getSign().equalsIgnoreCase(signStr)) {
                returnData.setReturnData(errorcode_param, "验证签名失败", "");
                sendResp(returnData,response);
                return null;
            }

            fingerPrintVeinCollectTestBizBiz.fingerVeinVerify(req,returnData);

        }catch (Exception e){
            log.error("指纹静脉验证异常-->",e);
            returnData.setReturnData(errorcode_systerm,"指纹静脉验证异常",null);
        }
        sendResp(returnData,response);
        return null;
    }

    public boolean checkUserInfo(UserInfoTestReq param, ReturnDataNew returnData) {
        if (null == param) {
            returnData.setReturnData(errorcode_param, "参数传递错误", null);
            return false;
        }
        if (!param.checkRequest()) {
            returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
            return false;
        }

        if (isNull(param.getUi_tel())) {
            setReData(returnData, "ui_tel");
            return false;
        }else {
            if(!isMobileNO(param.getUi_tel())){
                returnData.setReturnData(errorcode_param,"请输入正确的手机号",null);
                return false;
            }

        }
        if (isNull(param.getName())) {
            setReData(returnData, "name");
            return false;
        }
        if(isNull(param.getFingerprint_vein_str())){
            setReData(returnData,"fingerprint_vein_str()");
            return false;
        }
        if (isNull(param.getMac())) {
            setReData(returnData, "mac");
            return false;
        }
        String signStr = getSignature(Constants.SYSTEM_KEY, param.getDtype(), param.getUi_tel());
        if (!param.getSign().equalsIgnoreCase(signStr)) {
            returnData.setReturnData(errorcode_param, "验证签名失败", "");
            return false;
        }
        return true;
    }

    private boolean isNull(Object obj) {
        return RequestUtil.checkObjectBlank(obj);
    }

    private void setReData(ReturnDataNew reData, String filed) {
        reData.setReturnData(errorcode_param, filed + " is null", null);
    }

}
