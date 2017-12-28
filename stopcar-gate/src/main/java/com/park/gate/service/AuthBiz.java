package com.park.gate.service;

import com.alibaba.fastjson.JSONObject;
import com.park.bean.Park_authorize;
import com.park.bean.Park_info;
import com.park.bean.ReturnDataNew;
import com.park.dao.DaoFactory;
import com.park.exception.QzException;
import com.park.gate.transaction.GateMonthfreeLogTransactioin;
import com.park.mvc.service.BaseBiz;
import com.park.util.QEncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 处理停车场授权逻辑
 *
 */
@Service
public class AuthBiz extends BaseBiz {
  /**
   * 处理停车场授权逻辑
   */
  public void read_park_auth(ReturnDataNew returnData, long pi_id, String area_code) throws QzException {
    try {
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        returnData.setReturnData(errorcode_param, "不存在area_code：" + area_code + "和pi_id:" + pi_id + "对应停车场", "");
        return;
      }
      //查询授权信息
      String sql = "select * from park_authorize where pi_id=? and area_code=? limit 1";
      Park_authorize park_authorize = getMySelfService().queryUniqueT(sql,Park_authorize.class,pi_id,area_code);
      if(park_authorize == null){
           //第一次进行初始化
        Date date = new Date();
        park_authorize  = new Park_authorize();
        park_authorize.setArea_code(area_code);
        park_authorize.setPi_id(pi_id);
        park_authorize.setPi_name(park_info.getPi_name());
        park_authorize.setCtime(date);
        park_authorize.setDuration(24*3);//单位小时
        park_authorize.setEncrypt_type(2);//AES
        park_authorize.setIs_del(0);
        park_authorize.setLast_time(date);
//        park_authorize.setSecret_key("AFfsfhMFAFAS$#@%^&*&^%$!as79123fnDa");
        park_authorize.setUtime(date);
        park_authorize.setState(1);//授权状态(0:未知  1：成功授权  2：授权失败)
        int id = daoFactory.getPark_authorizeDao().insert(park_authorize);
        if(id < 1){
           //授权失败
          returnData.setReturnData(errorcode_data, "授权失败", "");
          return;
        }

        //授权成功进行 加密
        String encrypt = QEncodeUtil.aesEncrypt(JSONObject.toJSONString(park_authorize), "AFfsfhMFAFAS$#@%^&*&^%$!as79123fnDa");
        JSONObject obj = new JSONObject();
        obj.put("park_authorize",encrypt);
        returnData.setReturnData(errorcode_success, "授权成功", obj);
        return;
      }else{
        //检查
        //授权成功进行 加密
        String encrypt = QEncodeUtil.aesEncrypt(JSONObject.toJSONString(park_authorize), "AFfsfhMFAFAS$#@%^&*&^%$!as79123fnDa");
        JSONObject obj = new JSONObject();
        obj.put("park_authorize",encrypt);
        returnData.setReturnData(errorcode_success, "授权成功", obj);
        return;
      }
    } catch (Exception e) {
      log.error("AuthBiz read_park_auth is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


}
