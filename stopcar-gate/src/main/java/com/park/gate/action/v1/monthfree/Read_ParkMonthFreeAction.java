package com.park.gate.action.v1.monthfree;

import com.park.bean.Parkinfo_monthfree;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.gate.action.v1.monthfree.param.Param_ParkMonthFreeQuery;
import com.park.gate.service.ParkMonthFreeActionBiz;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 查询停车场包月车和免费车记录
 * Created by zzy on 2017/6/16.
 */
@Controller
@RequestMapping(value = "/v1")
public class Read_ParkMonthFreeAction extends BaseV1Controller {

    private static final long serialVersionUID = -2253782441591288102L;

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ParkMonthFreeActionBiz parkMonthFreeActionBiz;

    @RequestMapping(value = "/query_parkmonthfree")
    public String queryParkMonthFreeInfo(Param_ParkMonthFreeQuery param , HttpServletResponse response){
        ReturnDataNew returnData = new ReturnDataNew();
        try{
            if(!checkParam(param,returnData)){
                sendResp(returnData,response);
                return null;
            }

            Parkinfo_monthfree parkinfo_monthfree =  parkMonthFreeActionBiz.queryParkInfoMonthFreeInfo(param);
            if(null == parkinfo_monthfree){
                returnData.setReturnData(errorcode_data,"没有查询到信息",null);
            }else{
                returnData.setReturnData(errorcode_success,"查询成功",parkinfo_monthfree);
            }

        }catch (Exception e){
            log.error("查询失败-->",e);
            returnData.setReturnData(errorcode_data,"查询失败",null);

        }
        sendResp(returnData,response);
        return null;
    }

    /**
     *
     * @param param
     * @param returnData
     * @return
     */
    private boolean checkParam(Param_ParkMonthFreeQuery param, ReturnDataNew returnData){
        if(null == param){
            returnData.setReturnData(errorcode_param,"参数传递错误",null);
            return false;
        }

        if (RequestUtil.checkObjectBlank(param.getPi_id())) {
            returnData.setReturnData(errorcode_param, "pi_id不能为空", null);
            return false;
        }
        String area_code = param.getArea_code();
        if (!StringUtils.hasText(area_code)) {
            returnData.setReturnData(errorcode_param, "area_code不能为空", null);
            return false;
        }
        String carCode = param.getCar_code();
        if(RequestUtil.checkObjectBlank(carCode)){
            returnData.setReturnData(errorcode_param,"车牌号不能为空",null);
            return false;
        }

        String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.getPi_id(),area_code);
        if (!param.sign.equalsIgnoreCase(sign_str)) {
            log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
            returnData.setReturnData(errorcode_param, " 签名验证不通过", null);
            return false;
        }
        return true;
    }
}
