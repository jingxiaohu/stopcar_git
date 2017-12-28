package com.park.gate.action.v1.order;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.gate.action.v1.order.param.rentDefer.RentDeferAdd;
import com.park.gate.action.v1.order.param.rentDefer.RentDeferExpireState;
import com.park.gate.action.v1.order.param.rentDefer.RentDeferSure;
import com.park.gate.service.RentDeferByGateBiz;
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
 * 道闸租赁续约
 * 应用场景：
 * 道闸系统创建成功租赁需要订单成功后，将续约订单的信息上传至服务端进行存储。并对订单信息的续约状态，删除状态进行修改
 *
 * 2017-07-12 未实现
 * 增加逻辑：
 * 1.接口参数增加客户端的规则ID，增加客户端的租赁订单号。
 * 2.根据pi_id,area_code,car_code去carcode_park_rent表进行查询，是否有记录，如果有更新到期时间，如果没有则新增一条记录，当用户和车牌没有
 * 绑定时，也需要执行操作，只是对应的用户id为0就行了。
 *
 *
 * 设置续租来源，当clientFatherOrderId为空时，表示第一次租赁,否则为道闸线下续租。 by 2017-09-27
 * Created by zzy on 2017/6/30.
 */
@Controller
@RequestMapping("/v1")
public class Write_rentDeferAction extends BaseV1Controller {

    private static final long serialVersionUID = -2574525489006123083L;
    
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RentDeferByGateBiz rentDeferByGateBiz;

    /**
     * 道闸租赁第一次新增和道闸续租
     * @return
     */
    @RequestMapping("/rentdeferadd")
    public String rentDeferAdd(RentDeferAdd rentDeferAdd, HttpServletResponse response){
        ReturnDataNew returnDataNew = new ReturnDataNew();
        try{
            //参数校验
            if(!checkAddParam(rentDeferAdd,returnDataNew)){
                sendResp(returnDataNew, response);
                return null;
            }
            rentDeferByGateBiz.rentDeferAdd(rentDeferAdd,returnDataNew);

        }catch (Exception e){
            log.error("操作异常-->",e);
            returnDataNew.setReturnData(errorcode_systerm,"system is error",null);
        }

        sendResp(returnDataNew,response);
        return null;
    }



    /**
     * 道闸租赁续约是否到期状态修改
     * @param expireState
     * @param response
     * @return
     */
    @RequestMapping("/updateisexpire")
    public String updateIsExpireState(RentDeferExpireState expireState, HttpServletResponse response){
        ReturnDataNew returnDataNew = new ReturnDataNew();
        try{
            //参数校验
            if(!checkUpdateParam(expireState,returnDataNew)){
                sendResp(returnDataNew, response);
                return null;
            }
            rentDeferByGateBiz.updateIsExpireState(expireState,returnDataNew);
        }catch (Exception e){
            log.error("数据库异常-->",e);
            returnDataNew.setReturnData(errorcode_systerm,"system is error",null);
        }
        sendResp(returnDataNew,response);
        return null;
    }

    /**
     * APP续约并支付成功后，道闸调用该接口进行续约状态确认，如果不确认会有超时处理，将支付金额返还到用户钱包
     * 续约订单续约成功确认接口
     * rentDeferSuccessSure
     * @param response
     * @return
     */
    @RequestMapping("/rentdefersure")
    public String rentDeferSuccessSure(RentDeferSure rentDeferSure,HttpServletResponse response){
        ReturnDataNew returnDataNew = new ReturnDataNew();
        try{
            if(null == rentDeferSure){
                returnDataNew.setReturnData(errorcode_param,"参数传递错误",null);
                sendResp(returnDataNew, response);
                return null;
            }
            if(!rentDeferSure.checkRequest()){
                returnDataNew.setReturnData(errorcode_param,"没有进行签名认证",null);
                sendResp(returnDataNew, response);
                return null;
            }
            if(RequestUtil.checkObjectBlank(rentDeferSure.getPi_id())){
                returnDataNew.setReturnData(errorcode_param,"pi_id is null",null);
                sendResp(returnDataNew, response);
                return null;
            }
            if(RequestUtil.checkObjectBlank(rentDeferSure.getArea_code())){
                returnDataNew.setReturnData(errorcode_param,"area_code is null",null);
                sendResp(returnDataNew, response);
                return null;
            }
            if(RequestUtil.checkObjectBlank(rentDeferSure.getCar_code())){
                returnDataNew.setReturnData(errorcode_param,"carcode is null",null);
                sendResp(returnDataNew, response);
                return null;
            }
            if(RequestUtil.checkObjectBlank(rentDeferSure.getRent_order_id())){
                returnDataNew.setReturnData(errorcode_param,"rent_order_id is null",null);
                sendResp(returnDataNew, response);
                return null;
            }
//            if(RequestUtil.checkObjectBlank(rentDeferSure.getClient_order_id())){
//                returnDataNew.setReturnData(errorcode_param,"client_order_id is null",null);
//                sendResp(returnDataNew, response);
//                return null;
//            }
            if(RequestUtil.checkObjectBlank(rentDeferSure.getEndtime())){
                returnDataNew.setReturnData(errorcode_param,"enttime is null",null);
                sendResp(returnDataNew, response);
                return null;
            }
            if(RequestUtil.checkObjectBlank(rentDeferSure.getDefer_state())){
                returnDataNew.setReturnData(errorcode_param,"defer_state is null",null);
                sendResp(returnDataNew, response);
                return null;
            }

            String signStr = getSignature(Constants.SYSTEM_KEY,rentDeferSure.getDtype(),rentDeferSure.getPi_id(),rentDeferSure.getArea_code());
            if(!rentDeferSure.getSign().equalsIgnoreCase(signStr)){
                returnDataNew.setReturnData(errorcode_param,"验证签名失败",null);
                sendResp(returnDataNew,response);
                return null;
            }

            int deferState = rentDeferSure.getDefer_state();
            //道闸续租成功
            if(2 == deferState){
                rentDeferByGateBiz.rentDeferSure(rentDeferSure,returnDataNew);
            //道闸续租失败
            }else if(4 == deferState){
                rentDeferByGateBiz.rentDeferFail(rentDeferSure,returnDataNew);
            }else{
                log.info("续租状态不正确");
                returnDataNew.setReturnData(errorcode_param,"续租状态不正确",null);
            }

        }catch (Exception e){
            log.error("异常-->",e);
            returnDataNew.setReturnData(errorcode_systerm,"system is error",null);
        }
        sendResp(returnDataNew,response);
        return null;
    }

    /**
     * 新增数据参数校验
     * @param param
     * @return
     */
    private boolean checkAddParam(RentDeferAdd param,ReturnDataNew returnDataNew) throws Exception{
        if(null == param){
            returnDataNew.setReturnData(errorcode_param,"参数传递错误",null);
            return false;
        }
        if(!param.checkRequest()){
            returnDataNew.setReturnData(errorcode_param,"没有进行签名认证",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getPi_id())){
            returnDataNew.setReturnData(errorcode_param,"pi_id is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getArea_code())){
            returnDataNew.setReturnData(errorcode_param,"area_code is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getMoney())){
            returnDataNew.setReturnData(errorcode_param,"money is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getUnit_price())){
            returnDataNew.setReturnData(errorcode_param,"unit_price is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getMonth_num())){
            returnDataNew.setReturnData(errorcode_param,"month_num is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getStarttime())){
            returnDataNew.setReturnData(errorcode_param,"starttime is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getEndtime())){
            returnDataNew.setReturnData(errorcode_param,"endtime is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getCar_code())){
            returnDataNew.setReturnData(errorcode_param,"car_code is null",null);
            return false;
        }else{
            param.setArea_code(URLDecoder.decode(param.getArea_code(), Constants.SYSTEM_CHARACTER));
        }
        if(RequestUtil.checkObjectBlank(param.getPermit_time())){
            returnDataNew.setReturnData(errorcode_param,"permit_time is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getPay_source())){
            returnDataNew.setReturnData(errorcode_param,"pay_source is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getRent_type())){
            returnDataNew.setReturnData(errorcode_param,"rent_type is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getClient_order_id())){
            returnDataNew.setReturnData(errorcode_param,"client_order_id is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getClient_rule_id())){
            returnDataNew.setReturnData(errorcode_param,"client_rule_id is null",null);
            return  false;
        }
        String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.getClient_order_id(),param.getPi_id(), param.getArea_code());
        if (!param.getSign().equalsIgnoreCase(sign_str)) {
            log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
            returnDataNew.setReturnData(errorcode_param, " 验证签名失败", null);
            return false;
        }
        return true;
    }

    /**
     * 修改状态数据校验
     * @param param
     * @return
     */
    private boolean checkUpdateParam(RentDeferExpireState param, ReturnDataNew returnDataNew){
        if(null == param){
            returnDataNew.setReturnData(errorcode_param,"参数传递错误",null);
            return false;
        }
        if(!param.checkRequest()){
            returnDataNew.setReturnData(errorcode_param,"没有进行签名认证",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getPi_id())){
            returnDataNew.setReturnData(errorcode_param,"pi_id is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getCar_code())){
            returnDataNew.setReturnData(errorcode_param,"car_code is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getArea_code())){
            returnDataNew.setReturnData(errorcode_param,"area_code is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getExpire_state())){
            returnDataNew.setReturnData(errorcode_param,"expire_state is null",null);
            return false;
        }
        String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.getPi_id(),param.getArea_code());
        if (!param.getSign().equalsIgnoreCase(sign_str)) {
            log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
            returnDataNew.setReturnData(errorcode_param, " 验证签名失败", null);
            return false;
        }
        return true;
    }
}
