package com.park.pda.service;

import com.alibaba.fastjson.JSONObject;
import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.Finger_userinfo_new;
import com.park.bean.Finger_userinfo_relation;
import com.park.bean.ReturnDataNew;
import com.park.mvc.service.BaseBiz;
import com.park.pda.action.v1.fingerprintvein.param.Param_fingerprint_veno_info;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 指纹系统-通过车牌查询用户指纹、指静脉信息
 *
 * @author zyy
 */
@Service
public class FingerprintVenoInfoBiz extends BaseBiz {



    /**
     * 指纹系统-通过车牌查询用户指纹、指静脉信息
     */
    @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
    public void queryFingerVenoInfo(Param_fingerprint_veno_info param, ReturnDataNew returnData) {
        try {
            String car_code = param.getCar_code();  //车牌号
            JSONObject jsonObject = new JSONObject(); //返回结果数据
            int fingerPrintFlag = 0;    //指纹返回结果 1：有值，0：为空
            int fingerVenoFlag = 0;     //指静脉返回结果 1：有值，0：为空
            //根据用户车牌查询绑定的用户信息
            Finger_userinfo_new finger_userinfo_new = getFingerUserinfo(car_code);
            if(finger_userinfo_new != null) {
                //根据用户fu_id获取采集的指纹/指静脉信息列表
                List<Finger_userinfo_relation> relationList = getFingerUserRelationList(finger_userinfo_new.getFu_id());
                if (relationList != null && relationList.size() > 0) {
                    //遍历指纹/指静脉信息列表，添加到返回数据列表中
                    for (Finger_userinfo_relation relation : relationList) {
                        if (relation.getFingerprint() != null && !relation.getFingerprint().equals("")) {
                            fingerPrintFlag = 1;
                        }
                        if (relation.getFinger_veno() != null && !relation.getFinger_veno().equals("")) {
                            fingerVenoFlag = 1;
                        }
                    }
                }
            }

            jsonObject.put("car_code",car_code);
            jsonObject.put("fingerPrintFlag",fingerPrintFlag);
            jsonObject.put("fingerVenoFlag",fingerVenoFlag);

            //返回数据
            returnData.setReturnData(errorcode_success, "查询成功", jsonObject);
        } catch (Exception e) {
            log.error("指纹系统-通过车牌查询用户指纹、指静脉信息-查询失败", e);
            returnData.setReturnData(errorcode_systerm, "指纹系统-通过车牌查询用户指纹、指静脉信息查询失败", null);
        }

    }

    /**
     * 根据用户车牌查询绑定的用户信息
     */
    public Finger_userinfo_new getFingerUserinfo(String car_code){
        try{
            String sql = "SELECT t.* FROM finger_userinfo_new t " +
                    " RIGHT JOIN finger_userinfo_carcode t1 ON t.fu_id = t1.fu_id " +
                    " WHERE t1.car_code=? AND t.state = 1 AND t.is_del = 0";
            return getMySelfService().queryUniqueT(sql,Finger_userinfo_new.class,car_code);
        }catch(Exception e){
            log.error("getFingerUserinfo is error", e);
        }
        return null;
    }

    /**
     * 查询用户指纹、指静脉信息
     */
    public List<Finger_userinfo_relation> getFingerUserRelationList(long fu_id){
        try{
            String sql = "SELECT t.* FROM finger_userinfo_relation t " +
                    " LEFT JOIN finger_userinfo_new t1 ON t.fu_id = t1.fu_id " +
                    " WHERE t1.fu_id =? AND t.is_del=0 ";

            return getMySelfService().queryListT(sql,Finger_userinfo_relation.class,fu_id);
        }catch(Exception e){
            log.error("getFingerUserRelationList is error", e);
        }
        return null;
    }
}
