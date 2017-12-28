package com.park.pda.action.v1.fingerprintvein;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.fingerprintvein.param.*;
import com.park.pda.service.FingerPrintVeinCollectBiz;
import com.park.sign.ApiSign;
import com.park.util.BankNoValidUtil;
import com.park.util.IDCardValidator;
import com.park.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 指纹静脉信息采集
 * Created by zzy on 2017/8/29.
 */
@Controller
@RequestMapping("/v1")
public class Write_FingerPrintVeinCollectAction extends BaseV1Controller {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FingerPrintVeinCollectBiz fingerPrintVeinCollectBiz;

    /**
     * 手机短信验证
     *
     * @param req
     * @param response
     * @return
     */
    @RequestMapping("/smscodeverify")
    public void smsCodeVerify(SmsCodeVerifyReq req, HttpServletResponse response) {
        ReturnDataNew returnData = new ReturnDataNew();
        try {
            if (!checkSmsCodeParam(req, returnData)) {
                sendResp(returnData, response);
                return ;
            }

            boolean result = fingerPrintVeinCollectBiz.smsCodeVerify(req.getUi_tel(), req.getVerify_list(), req.getVclass(), req.getVerify_code(), returnData);
            if (result) {
                returnData.setReturnData(errorcode_success, "短信验证成功", null);
            }

        } catch (Exception e) {
            log.info("短信验证异常-->", e);
            returnData.setReturnData(errorcode_systerm, "system is error", null);
        }
        sendResp(returnData, response);
    }

    /**
     * 采集用户身份信息
     *
     * @param response
     * @return
     */
    @RequestMapping("/collect_userinfo")
    public void collectionUserInfo(UserInfoReq req, HttpServletResponse response) {
        ReturnDataNew returnData = new ReturnDataNew();
        try {
            if (!checkUserInfo(req, returnData)) {
                sendResp(returnData, response);
                return ;
            }

            fingerPrintVeinCollectBiz.collectUserInfo(req, returnData);

        } catch (Exception e) {
            log.error("采集用户身份信息异常-->", e);
        }
        sendResp(returnData, response);
    }

    /**
     * 支付一分钱验证银行卡是否为本人操作，参考etc，验证成功后，去进行绑卡操作
     *
     * @param response
     * @return
     */
    @RequestMapping("/fingerprintveion_checksign")
    public void fingerCheckSign(FingerPrintVeionCheckSignReq signReq, HttpServletResponse response) {
        ReturnDataNew returnData = new ReturnDataNew();
        try {
            if (null == signReq) {
                returnData.setReturnData(errorcode_param, "参数传递错误", null);
                sendResp(returnData, response);
                return ;
            }
            if (!signReq.checkRequest()) {
                returnData.setReturnData(errorcode_param, "没有进行参数签名认证", null);
                sendResp(returnData, response);
                return ;
            }
            if (isNull(signReq.getFu_id())) {
                returnData.setReturnData(errorcode_param, "fu_id is null", null);
                sendResp(returnData, response);
                return ;
            }
            if (isNull(signReq.getFu_nd())) {
                returnData.setReturnData(errorcode_param, "fu_nd is null", null);
                sendResp(returnData, response);
                return ;
            }
            if (isNull(signReq.getFub_id())) {
                returnData.setReturnData(errorcode_param, "fub_id is null", null);
                sendResp(returnData, response);
                return ;
            }

            String signStr = getSignature(Constants.SYSTEM_KEY, signReq.getDtype(), signReq.getFu_id(), signReq.getFub_id());
            if (!signReq.getSign().equalsIgnoreCase(signStr)) {
                returnData.setReturnData(errorcode_param, "验证签名失败", "");
                sendResp(returnData, response);
                return ;
            }

            fingerPrintVeinCollectBiz.fingerCheckSign(signReq, returnData);
        } catch (Exception e) {
            log.error("支付验证一分钱失败", e);
            returnData.setReturnData(errorcode_systerm, "支付验证一分钱失败", null);
        }
        sendResp(returnData, response);
    }

    /**
     * 手指信息采集
     *
     * @param req
     * @param response
     * @return
     */
    @RequestMapping("/finger_collect")
    @ApiSign({"dtype", "fu_id"})
    public void fingerCollect(@Validated FingerCollectReq req, HttpServletResponse response) throws Exception {

        String JPG = ".jpg";

        ReturnDataNew returnData = new ReturnDataNew();
        //获取文件名
        MultipartFile fingerImg = req.getFingerprint_img();
        MultipartFile veinImg = req.getFinger_veno_img();

        long fileSize = 100 * 1024 * 1024;
        if(fingerImg.getBytes().length > fileSize || veinImg.getBytes().length > fileSize){
            returnData.setReturnData(errorcode_param,"只能上传小于100k的文件",null);
            sendResp(returnData,response);
            return;
        }

        String fingerImgName = null;
        if (fingerImg != null) {
            int idf = fingerImg.getOriginalFilename().lastIndexOf(".");
            if(idf > 0){
                fingerImgName = fingerImg.getOriginalFilename().substring(0, idf)+JPG;
            }else{
                fingerImgName = fingerImg.getOriginalFilename()+JPG;
            }

            req.setFingerprint_img_name(fingerImgName);
        }

        String veinImgName = null;
        if (null != veinImg) {

            int idv = fingerImg.getOriginalFilename().lastIndexOf(".");
            if(idv > 0){
                veinImgName = veinImg.getOriginalFilename().substring(0, idv)+JPG;
            }else{
                veinImgName = veinImg.getOriginalFilename()+JPG;
            }

            req.setFinger_veno_img_name(veinImgName);
        }
        fingerPrintVeinCollectBiz.fingerCollect(req, returnData);

        sendResp(returnData, response);
    }

    /**
     * 用户手指信息删除
     *
     * @param req
     * @param response
     * @return
     */
    @RequestMapping("/fingerdel")
    @ApiSign({"dtype", "fu_id", "fur_id"})
    public void fingerDelete(@Validated FingerDelReq req, HttpServletResponse response) throws Exception{
        ReturnDataNew returnData = new ReturnDataNew();
        fingerPrintVeinCollectBiz.fingerDelete(req, returnData);
        sendResp(returnData, response);
    }


    /**
     * 用户指纹静脉车牌绑定
     *
     * @param req
     * @param response
     * @return
     */
    @RequestMapping("/finger_carcode_bind")
    @ApiSign({"dtype","fu_id"})
    public void bindCarCode(@Validated UserCarCodeBindReq req, HttpServletResponse response) {
        ReturnDataNew returnData = new ReturnDataNew();
        fingerPrintVeinCollectBiz.bindCarCode(req, returnData);
        sendResp(returnData, response);
    }


    /**
     * 用户指纹静脉车牌解绑
     *
     * @param req
     * @param response
     * @return
     */
    @RequestMapping("/finger_carcode_unbind")
    @ApiSign({"dtype","fuc_id"})
    public void unBindCarCode(@Validated UserCarCodeUnBindReq req, HttpServletResponse response) throws Exception{
        ReturnDataNew returnData = new ReturnDataNew();
        fingerPrintVeinCollectBiz.unBindCarCode(req, returnData);
        sendResp(returnData, response);
    }


    private boolean checkSmsCodeParam(SmsCodeVerifyReq param, ReturnDataNew returnData) {
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
        }else if (!isMobileNO(param.getUi_tel())) {
            returnData.setReturnData(errorcode_param, "请输入正确的手机号", null);
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

        String signStr = getSignature(Constants.SYSTEM_KEY, param.getDtype(), param.getUi_tel(), param.getVerify_code());
        if (!param.getSign().equalsIgnoreCase(signStr)) {
            returnData.setReturnData(errorcode_param, "验证签名失败", "");
            return false;
        }


        return true;
    }


    public boolean checkUserInfo(UserInfoReq param, ReturnDataNew returnData) {
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
        }
        if (isNull(param.getName())) {
            setReData(returnData, "name");
            return false;
        }
        if (isNull(param.getSfz_number())) {
            setReData(returnData, "sfz_number");
            return false;
        } else if (!IDCardValidator.validate(param.getSfz_number())) {
            returnData.setReturnData(errorcode_param, "请输入正确的身份证号码", null);
            return false;
        }
        if (isNull(param.getBank_type())) {
            setReData(returnData, "bank_type");
            return false;
        }
        if (isNull(param.getBank_card_number())) {
            setReData(returnData, "bank_card_number");
            return false;
        } else if (!BankNoValidUtil.bankNoIsValid(param.getBank_card_number())) {
            returnData.setReturnData(errorcode_param,"请输入正确的银行卡号",null);
            return false;
        }
        if (isNull(param.getMac())) {
            setReData(returnData, "mac");
            return false;
        }
        String signStr = getSignature(Constants.SYSTEM_KEY, param.getDtype(), param.getUi_tel(), param.getSfz_number(), param.getBank_card_number());
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
