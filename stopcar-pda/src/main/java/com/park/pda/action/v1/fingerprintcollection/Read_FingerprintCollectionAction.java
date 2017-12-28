package com.park.pda.action.v1.fingerprintcollection;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.park.bean.ReturnData;
import com.park.bean.ReturnDataBase;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.interceptor.SpringMVCInterceptor;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.fingerprintcollection.param.FingerUserInfoQueryReq;
import com.park.pda.service.FingerprintCollectionBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 查询指纹系统-------用户资料
 * Created by zzy on 2017/7/20.
 */
@Controller
@RequestMapping("/v1")
@Deprecated //by zyy 20170829 暂时不使用，使用新接口
public class Read_FingerprintCollectionAction extends BaseV1Controller{

    @Autowired
    private FingerprintCollectionBiz fingerprintCollectionBiz;

    @Autowired(required = false)
    private HttpServletRequest request;

    /**
     * 查询指纹用户信息
     * @param queryReq
     * @param response
     * @return
     */
    @RequestMapping("/finger_query")
    public String fingerUserInfoQuery(FingerUserInfoQueryReq queryReq, HttpServletResponse response){
        ReturnDataNew returnData = new ReturnDataNew();
        try{
            //参数检查
            if (queryReq == null) {
                //参数传递错误
                returnData.setReturnData(errorcode_param, "参数传递错误", "");
                sendResp(returnData, response);
                return null;
            }
            //检查是否进行了参数签名认证
            if (!queryReq.checkRequest()) {
                returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
                sendResp(returnData, response);
                return null;
            }

            String signStr = getSignature(Constants.SYSTEM_KEY,queryReq.getDtype(),queryReq.getUi_tel());
            if(!queryReq.getSign().equalsIgnoreCase(signStr)){
                returnData.setReturnData(errorcode_param,"验证签名失败",null);
                sendResp(returnData,response);
                return null;
            }
            fingerprintCollectionBiz.queryFingerUserinfo(queryReq,returnData);
        }catch (Exception e){
            log.error("",e);
            returnData.setReturnData(errorcode_systerm,"查询指纹用户信息失败",null);
        }
        sendResp(returnData,response);
        return null;
    }

    /**
     * 封装发送响应
     * BaseV1Controller 中有这个方法，这里JSONObject.toJSONString()的时候添加了SerializerFeature.DisableCircularReferenceDetect参数
     * DisableCircularReferenceDetect:消除循环引用
     * @param @param returnData    设定文件
     * @return void   返回类型
     * @Title: sendResp
     */
    protected void sendResp(ReturnDataBase returnData, HttpServletResponse response) {
        if (returnData == null) {
            returnData = new ReturnData();
        }
        response.setContentType("text/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            String data = JSONObject.toJSONString(returnData, SerializerFeature.DisableCircularReferenceDetect);
            request.setAttribute(SpringMVCInterceptor.RESPONSE_DATA, data);
            response.getWriter().write(data);
        } catch (Exception e) {
            log.error("BaseAction.sendResp is error", e);
        }
    }
}
