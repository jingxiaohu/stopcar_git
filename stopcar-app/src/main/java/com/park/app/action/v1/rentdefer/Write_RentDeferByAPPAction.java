package com.park.app.action.v1.rentdefer;

import com.park.app.action.v1.order.param.CarcodeParkRentDel;
import com.park.app.action.v1.rentdefer.param.RentDeferByAPP;
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
 * 道闸租赁续约 by APP
 *
 * 流程：
 * 1.用户在手机APP上查询租赁记录，点击能够进行续约的记录。
 * 2.展示续约信息，确定，并跳转支付页面，输入金额，选择支付方式。
 * 3.请求后端进行下单，生成第三方支付流水,并返回给APP端流水信息。
 * 4.手机APP调用第三方支付接口完成支付，
 * 5.第三方支付系统调用本系统支付结果通知结果接口，支付成功后，记录事件信息记录表。
 * 6.定时任务轮询事件信息表，根据支付结果更新rent_defer表的payState（支付状态），并进行mq推送，同时修改mq推送状态。
 * 7.道闸系统接收mq推送的消息，进行租赁续约处理，处理成功后调用接口续约确认接口，修改rent_defer表的defer_state，确认续约成功，当确认续约成功之后，
 * 将续约成功的信息使用极光推送给用户app。
 * 8.定时任务处理rent_defer表中的长时间（从创建开始算多长时间后）处于续约中并且支付成功的订单，具体的时间需要确认，将支付的金额返回到用户钱包。
 * Created by zzy on 2017/7/3.
 *
 */
@Controller
@RequestMapping("/v1")
public class Write_RentDeferByAPPAction extends BaseV1Controller{

    private static final long serialVersionUID = 8716376642112691306L;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RentDeferByAPPBiz rentDeferByAPPBiz;

    /**
     * 租赁续租
     * @param rentDeferByAPP
     * @param response
     * @return
     */
    @RequestMapping("/rentOrderDefer")
    public String rentOrderDefer(RentDeferByAPP rentDeferByAPP,HttpServletResponse response){
        ReturnDataNew returnDataNew = new ReturnDataNew();
        try{
            if(!checkParam(rentDeferByAPP,returnDataNew)){
                sendResp(returnDataNew,response);
                return null;
            }

            rentDeferByAPPBiz.rentOrderDefer(rentDeferByAPP,returnDataNew);
        }catch (Exception e){
            log.error("系统异常",e);
            returnDataNew.setReturnData(errorcode_systerm,"system is error",null);
        }
        sendResp(returnDataNew,response);
        return null;
    }

    /**
     * 道闸租赁续租删除
     * @return
     */
    @RequestMapping("/rentdeferdel")
    public String rentDeferDel(CarcodeParkRentDel rentDeferDel,HttpServletResponse response){
        ReturnDataNew returnDataNew = new ReturnDataNew();
        try{
            //参数校验
            if(!checkDelParam(rentDeferDel,returnDataNew)){
                sendResp(returnDataNew, response);
                return null;
            }
            rentDeferByAPPBiz.rentDeferDel(rentDeferDel,returnDataNew);

        }catch (Exception e){
            log.error("数据库异常-->",e);
            returnDataNew.setReturnData(errorcode_systerm,"system is error",null);
        }
        sendResp(returnDataNew,response);
        return null;
    }

    /**
     * 删除数据参数校验
     * @param param
     * @return
     */
    private boolean checkDelParam(CarcodeParkRentDel param, ReturnDataNew returnDataNew){
        if(null == param){
            returnDataNew.setReturnData(errorcode_param,"参数传递错误",null);
            return false;
        }
        if(!param.checkRequest()){
            returnDataNew.setReturnData(errorcode_param,"没有进行签名认证",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getUi_id())){
            returnDataNew.setReturnData(errorcode_param,"ui_id is null",null);
            return false;
        }
//        if(RequestUtil.checkObjectBlank(param.getUi_nd())){
//            returnDataNew.setReturnData(errorcode_param,"ui_nd is null",null);
//            return false;
//        }
        if(RequestUtil.checkObjectBlank(param.getCpr_id())){
            returnDataNew.setReturnData(errorcode_param,"rd_id is null",null);
            return false;
        }

        String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.getCpr_id(),param.getUi_id());
        if (!param.getSign().equalsIgnoreCase(sign_str)) {
            log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
            returnDataNew.setReturnData(errorcode_param, " 验证签名失败", null);
            return false;
        }
        return true;
    }

    /**
     * 参数合法性校验
     * @return
     */
    private boolean checkParam(RentDeferByAPP param,ReturnDataNew returnDataNew){
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
//        if(RequestUtil.checkObjectBlank(param.getUi_nd())){
//            returnDataNew.setReturnData(errorcode_param,"ui_nd is null",null);
//            return false;
//        }
        if(RequestUtil.checkObjectBlank(param.getCpr_id())){
            returnDataNew.setReturnData(errorcode_param,"cpr_id is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getMonth_num())){
            returnDataNew.setReturnData(errorcode_param,"month_num is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getRent_type())){
            returnDataNew.setReturnData(errorcode_param,"rent_type is null",null);
            return false;
        }
        if(RequestUtil.checkObjectBlank(param.getPay_source())){
            returnDataNew.setReturnData(errorcode_param,"pay_source is null",null);
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
