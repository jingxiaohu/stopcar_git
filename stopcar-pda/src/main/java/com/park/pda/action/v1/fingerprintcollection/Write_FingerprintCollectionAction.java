package com.park.pda.action.v1.fingerprintcollection;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.fingerprintcollection.param.FingerPrintCheckSignReq;
import com.park.pda.action.v1.fingerprintcollection.param.FingerRemoveSignedReq;
import com.park.pda.action.v1.fingerprintcollection.param.FingerUserInfoUpReq;
import com.park.pda.action.v1.fingerprintcollection.param.FingerprintCollectionReq;
import com.park.pda.service.FingerprintCollectionBiz;
import com.park.util.IDCardValidator;
import com.park.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息、指纹采集控制器
 *
 * Created by zzy on 2017/7/20.
 */
@Controller
@RequestMapping("/v1")
public class Write_FingerprintCollectionAction extends BaseV1Controller {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FingerprintCollectionBiz fingerprintCollectionBiz;

    /**
     * 指纹系统--用户资料新增
     * 业务流程：
     *
     *
     * @param response
     * @return
     */
    @RequestMapping("/finger_userinfo_add")
    public String saveFingerUserinfo(HttpServletRequest request,FingerprintCollectionReq fingerprintCollection, HttpServletResponse response) {
        ReturnDataNew returnData = new ReturnDataNew();
        try {
            //参数检查
            if (!checkFingerprintCollectionReq(fingerprintCollection, returnData)) {
                sendResp(returnData, response);
                return null;
            }

            fingerprintCollection.setSign_ip(getIpAddr(request));
            fingerprintCollection.setGather_id(0L);

            fingerprintCollectionBiz.saveFingerUserinfo(fingerprintCollection, returnData);
        } catch (Exception e) {
            log.error("指纹系统--用户资料新增失败", e);
            returnData.setReturnData(errorcode_systerm,"指纹系统--用户资料新增失败",null);
        }
        sendResp(returnData, response);
        return null;
    }

    /**
     * 支付验证银行卡是否为本人操作，参考etc，验证成功后，签约成功
     *
     * @param response
     * @return
     */
    @RequestMapping("/finger_userinfo_check_sign")
    public String fingerCheckSign(FingerPrintCheckSignReq signReq, HttpServletResponse response) {
        ReturnDataNew returnData = new ReturnDataNew();
        try {
            if (null == signReq) {
                returnData.setReturnData(errorcode_param, "参数传递错误", null);
                sendResp(returnData, response);
                return null;
            }
            if (!signReq.checkRequest()) {
                returnData.setReturnData(errorcode_param, "没有进行参数签名认证", null);
                sendResp(returnData, response);
                return null;
            }
            if (isNull(signReq.getFu_id())) {
                returnData.setReturnData(errorcode_param, "fu_id is null", null);
                sendResp(returnData, response);
                return null;
            }
            if(isNull(signReq.getFu_nd())){
                returnData.setReturnData(errorcode_param, "fu_nd is null", null);
                sendResp(returnData, response);
                return null;
            }

            String signStr = getSignature(Constants.SYSTEM_KEY, signReq.getDtype(), signReq.getFu_id());
            if (!signReq.getSign().equalsIgnoreCase(signStr)) {
                returnData.setReturnData(errorcode_param, "验证签名失败", "");
                sendResp(returnData, response);
                return null;
            }

            fingerprintCollectionBiz.fingerCheckSign(signReq, returnData);
        } catch (Exception e) {
            log.error("支付验证一分钱失败", e);
            returnData.setReturnData(errorcode_systerm,"支付验证一分钱失败",null);
        }
        sendResp(returnData, response);
        return null;
    }

    /**
     * 指纹系统---修改用户资料，主要是修改指纹特征信息，
     *
     * @param response
     * @return
     */
    @RequestMapping("/finger_userinfo_update")
    public String updateFingerUserInfo(FingerUserInfoUpReq userInfoUpReq, HttpServletResponse response) {
        ReturnDataNew returnData = new ReturnDataNew();
        try {
            if (!checkFingerUserInfoUpReq(userInfoUpReq, returnData)) {
                sendResp(returnData, response);
                return null;
            }

            fingerprintCollectionBiz.updateFingerUserInfo(userInfoUpReq, returnData);
        } catch (Exception e) {
            log.error("指纹系统--修改用户资料失败", e);
            returnData.setReturnData(errorcode_systerm,"指纹系统--修改用户资料失败",null);
        }
        sendResp(returnData, response);
        return null;
    }


    /**
     * 指纹系统--用户资料解除签约操作
     * @param removeSignedReq
     * @param response
     * @return
     */
    @RequestMapping("/finger_removesigned")
    public String fingerRemoveSigned(FingerRemoveSignedReq removeSignedReq,HttpServletResponse response) {
        ReturnDataNew returnData = new ReturnDataNew();
        try {
            if (null == removeSignedReq) {
                returnData.setReturnData(errorcode_param, "参数传递错误", null);
                sendResp(returnData, response);
                return null;
            }
            if (!removeSignedReq.checkRequest()) {
                returnData.setReturnData(errorcode_param, "没有进行参数签名认证", null);
                sendResp(returnData, response);
                return null;
            }
            if (isNull(removeSignedReq.getFu_id())) {
                returnData.setReturnData(errorcode_param, "fu_id is null", null);
                sendResp(returnData, response);
                return null;
            }

            String signStr = getSignature(Constants.SYSTEM_KEY, removeSignedReq.getDtype(), removeSignedReq.getFu_id());
            if (!removeSignedReq.getSign().equalsIgnoreCase(signStr)) {
                returnData.setReturnData(errorcode_param, "验证签名失败", "");
                sendResp(returnData, response);
                return null;
            }

            fingerprintCollectionBiz.fingerRemoveSigned(removeSignedReq,returnData);

        } catch (Exception e) {
            log.error("解除签约操作异常",e);
            returnData.setReturnData(errorcode_systerm,"解除签约操作失败",null);
        }
        sendResp(returnData,response);
        return null;
    }

    /**
     * 检查参数有效性
     * @param param
     * @param returnData
     * @return
     */
    private boolean checkFingerprintCollectionReq(FingerprintCollectionReq param, ReturnDataNew returnData) {
        if (null == param) {
            returnData.setReturnData(errorcode_param, "参数传递错误", null);
            return false;
        }
        if (!param.checkRequest()) {
            returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
            return false;
        }

        if (isNull(param.getVerify_code())) {
            setReData(returnData, "verify_code");
            return false;
        }
        if (isNull(param.getVerify_list())) {
            setReData(returnData, "verify_list");
            return false;
        }
        if (isNull(param.getVclass())) {
            setReData(returnData, "vclass");
            return false;
        }
        if (isNull(param.getUi_tel())) {
            setReData(returnData, "ui_tel");
            return false;
        }
        if (isNull(param.getCar_code())) {
            setReData(returnData, "car_code");
            return false;
        }
        if (isNull(param.getName())) {
            setReData(returnData, "name");
            return false;
        }
        if (isNull(param.getSfz_number())) {
            setReData(returnData, "sfz_number");
            return false;
        }else if(!IDCardValidator.validate(param.getSfz_number())){
            returnData.setReturnData(errorcode_param,"请输入正确的身份证号码",null);
            return false;
        }
        if (isNull(param.getBank_card_number())) {
            setReData(returnData, "bank_card_number");
            return false;
        }
        if (isNull(param.getMac())) {
            setReData(returnData, "mac");
            return false;
        }
//        if (isNull(param.getSign_ip())) {
//            setReData(returnData, "sign_ip");
//            return false;
//        }
//        if (isNull(param.getGather_id())) {
//            setReData(returnData, "gather_id");
//            return false;
//        }
//        if (isNull(param.getCtime())) {
//            setReData(returnData, "ctime");
//            return false;
//        }

        String signStr = getSignature(Constants.SYSTEM_KEY, param.dtype, param.getUi_tel(), param.getSfz_number(), param.getBank_card_number());
        if (!param.getSign().equalsIgnoreCase(signStr)) {
            returnData.setReturnData(errorcode_param, "验证签名不正确", null);
            return false;
        }

        return true;
    }

    private boolean checkFingerUserInfoUpReq(FingerUserInfoUpReq param, ReturnDataNew returnData) {
        if (null == param) {
            returnData.setReturnData(errorcode_param, "参数传递错误", null);
            return false;
        }
        if (!param.checkRequest()) {
            returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
            return false;
        }

        if(isNull(param.getFu_id())){
            setReData(returnData,"fu_id");
            return false;
        }
        String signStr = getSignature(Constants.SYSTEM_KEY, param.getDtype(), param.getFu_id());
        if(!param.getSign().equalsIgnoreCase(signStr)){
            returnData.setReturnData(errorcode_param,"签名验证不正确 ",null);
            return false;
        }
        return true;
    }

    public boolean isNull(Object obj) {
        return RequestUtil.checkObjectBlank(obj);
    }

    private void setReData(ReturnDataNew reData, String filed) {
        reData.setReturnData(errorcode_param, filed + " is null", null);
    }
}
