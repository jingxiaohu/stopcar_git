package com.park.gate.action.v1.monthfree;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.gate.action.v1.monthfree.param.Param_ParkMonthFree;
import com.park.gate.action.v1.monthfree.param.Param_ParkMonthFreeDel;
import com.park.gate.action.v1.monthfree.param.Param_ParkMonthFreeState;
import com.park.gate.service.ParkMonthFreeActionBiz;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 停车场本地包月车和本地免费车记录
 *
 * 每个接口方法中的对象的所有参数都使用
 * Created by zzy on 2017/6/16.
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_ParkMonthFreeAction extends BaseV1Controller {

    private static final long serialVersionUID = -3759913908231596368L;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ParkMonthFreeActionBiz parkMonthFreeActionBiz;
    /**
     * 添加
     * @param response
     * @param param
     * @return
     */
    @RequestMapping(value = "/record_parkmonthfree")
    public String recordParkMonthFree(HttpServletResponse response, Param_ParkMonthFree param){
        ReturnDataNew returnData = new ReturnDataNew();
        try{
            if(!checkParam(param,returnData)){
                sendResp(returnData,response);
                return null;
            }
            parkMonthFreeActionBiz.recordParkMonthFree(param,returnData);

        }catch (Exception e){
            log.error("异常-->",e);
            returnData.setReturnData(errorcode_systerm,"系统错误",null);
        }

        sendResp(returnData,response);
        return null;
    }

    /**
     * 修改信息
     * @param response
     * @param param
     * @return
     */
    @RequestMapping(value = "/update_parkmonthfree")
    public String updateParkMonthFree(HttpServletResponse response,Param_ParkMonthFree param){
        ReturnDataNew returnData = new ReturnDataNew();
        try{
            if(!checkParam(param,returnData)){
                sendResp(returnData,response);
                return null;
            }

            parkMonthFreeActionBiz.updateParkMonthFree(param,returnData);

        }catch (Exception e){
            log.error("异常-->",e);
            returnData.setReturnData(errorcode_systerm,"系统错误",null);
        }

        sendResp(returnData,response);
        return null;
    }

    /**
     * 修改状态 无效
     * @param response
     * @param param
     * @return
     */
    @RequestMapping(value = "/update_state_parkmonthfree")
    public String updateParkMonthFreeState(HttpServletResponse response,Param_ParkMonthFreeState param){
        ReturnDataNew returnData = new ReturnDataNew();
        try{
            if(!checkStateParam(param,returnData)){
                sendResp(returnData,response);
                return null;
            }
            parkMonthFreeActionBiz.updateParkMonthFreeState(param,returnData);
            returnData.setReturnData(errorcode_success,"修改状态成功",null);
        }catch (Exception e){
            log.error("修改状态失败-->",e);
        }

        sendResp(returnData,response);
        return null;
    }
    /**
     * 删除
     * @param response
     * @param param
     * @return
     */
    @RequestMapping(value = "/del_parkmonthfree")
    public String deleteParkMontFree(HttpServletResponse response,Param_ParkMonthFreeDel param){

        ReturnDataNew returnData = new ReturnDataNew();
        try{
            if(!checkDelParam(param,returnData)){
                sendResp(returnData,response);
                return null;
            }

            parkMonthFreeActionBiz.delParkMonthFree(param,returnData);

        }catch (Exception e){
            log.error("异常-->",e);
            returnData.setReturnData(errorcode_systerm,"系统错误",null);
        }

        sendResp(returnData,response);
        return null;
    }
    /**
     * 添加和修改信息接口参数有效性检查
     * @param param
     * @return
     */
    private boolean checkParam(Param_ParkMonthFree param, ReturnDataNew returnData){
        if(!check(param,param.getPi_id(),param.getArea_code(),param.getCar_code(),returnData)){
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getClient_id())){
            returnData.setReturnData(errorcode_param,"道闸本地记录的主键ID不能为空",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getType())){
            returnData.setReturnData(errorcode_param,"类型不能为空",null);
            return false;
        }
        String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.getPi_id(),param.getArea_code());
        if (!param.sign.equalsIgnoreCase(sign_str)) {
            log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
            returnData.setReturnData(errorcode_param, " 签名验证不通过", null);
            return false;
        }
        return true;
    }

    /**
     * 状态接口参数检查
     * @param param
     * @param returnData
     * @return
     */
    private boolean checkStateParam(Param_ParkMonthFreeState param, ReturnDataNew returnData){
        if(!check(param,param.getPi_id(),param.getArea_code(),param.getCar_code(),returnData)){
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getClient_id())){
            returnData.setReturnData(errorcode_param,"道闸本地记录的主键ID不能为空",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getState())){
            returnData.setReturnData(errorcode_param,"状态不能为空",null);
            return false;
        }
        String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.getPi_id(),param.getArea_code());
        if (!param.sign.equalsIgnoreCase(sign_str)) {
            log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
            returnData.setReturnData(errorcode_param, " 签名验证不通过", null);
            return false;
        }
        return true;
    }

    /**
     * 删除接口参数检查
     * @param param
     * @param returnData
     * @return
     */
    public boolean checkDelParam(Param_ParkMonthFreeDel param,ReturnDataNew returnData){

        if(!check(param,param.getPi_id(),param.getArea_code(),param.getCar_code(),returnData)){
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getClient_id())){
            returnData.setReturnData(errorcode_param,"道闸本地记录的主键ID不能为空",null);
            return false;
        }
        String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.getPi_id(),param.getArea_code());
        if (!param.sign.equalsIgnoreCase(sign_str)) {
            log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
            returnData.setReturnData(errorcode_param, " 签名验证不通过", null);
            return false;
        }
        return true;
    }

    /**
     * 基础检查
     * @param param
     * @param pi_id
     * @param area_code
     * @param car_code
     * @param returnData
     * @return
     */
    private boolean check(Object param,Long pi_id,String area_code,String car_code,ReturnDataNew returnData){
        if(null == param){
            returnData.setReturnData(errorcode_param,"参数传递错误",null);
            return false;
        }
        if (RequestUtil.checkObjectBlank(pi_id)) {
            returnData.setReturnData(errorcode_param, "pi_id不能为空", null);
            return false;
        }
        if (RequestUtil.checkObjectBlank(area_code)) {
            returnData.setReturnData(errorcode_param, "area_code不能为空", null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(car_code)){
            returnData.setReturnData(errorcode_param,"车牌号不能为空",null);
            return false;
        }
        return true;
    }
}
