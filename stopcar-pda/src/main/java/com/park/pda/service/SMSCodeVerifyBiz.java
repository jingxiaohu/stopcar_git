package com.park.pda.service;

import com.park.bean.ReturnDataNew;
import com.park.bean.Sms_validate;
import com.park.bean.User_info;
import com.park.mvc.service.BaseBiz;
import org.springframework.stereotype.Service;

/**
 * Created by zzy on 2017/7/19.
 */
@Service("sMSCodeVerifyBiz")
public class SMSCodeVerifyBiz extends BaseBiz{

    /**
     * 超时时间 分钟
     */
    int timeout_minutes = 60;

    public void smsCodeVerify(ReturnDataNew returnDataNew){
        try{
            String telephone = "";
            String verify_list = "";
            String vclass = "";
            String verify_code = "";
            String sql = "SELECT v_code,v_time FROM sms_validate WHERE v_tel = ? AND v_list = ? AND v_class = ? AND v_code=? LIMIT 1";
            Sms_validate bsv = getMySelfService()
                    .queryUniqueT(sql, Sms_validate.class, telephone, verify_list, vclass, verify_code);
            if (bsv == null) {
                returnDataNew.setReturnData(errorcode_data, "验证码错误", "");
                return;
            }
            //检查时间是否过期 6分钟
            long time = bsv.getV_time();
            if (System.currentTimeMillis() - time > timeout_minutes * 60 * 1000) {
                returnDataNew.setReturnData(errorcode_data, "验证码过期", "");
                return;
            }
        }catch (Exception e){
            log.error("异常",e);
        }
    }


}
