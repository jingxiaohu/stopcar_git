package com.park.pda.service;

import com.alibaba.fastjson.JSONObject;
import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.Finger_userinfo_bank;
import com.park.bean.Finger_userinfo_carcode;
import com.park.bean.Finger_userinfo_new;
import com.park.bean.ReturnDataNew;
import com.park.mvc.service.BaseBiz;
import com.park.pda.action.v1.fingerprintvein.param.Param_fingerprint_veno_userinfo;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 指纹系统-通过手机号码查询用户采集的信息
 *
 * @author zyy
 */
@Service
public class FingerprintVenoUserinfoBiz extends BaseBiz {

    /**
     * 短信超时时间  分钟
     */
    private int timeout_minutes = 30;

    /**
     * 指纹系统-通过手机号码查询用户采集的信息
     */
    @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
    public void queryFingerVenoUserinfo(Param_fingerprint_veno_userinfo param, ReturnDataNew returnData) {
        try {
            JSONObject jsonObject = new JSONObject();
            String ui_tel = param.getUi_tel();

            //验证手机号对应的指纹用户信息是否存在
            Finger_userinfo_new ringer_userinfo_new = getFingerUserInfoByUiTel(ui_tel);
            /*if(null == ringer_userinfo_new){
                returnData.setReturnData(errorcode_success, "指纹系统-该手机号不存在", null);
                return;
            }*/

            Finger_userinfo_bank finger_userinfo_bank = null;
            List<Finger_userinfo_carcode> carCodeList = null;
            if(null != ringer_userinfo_new){
                //查询用户银行卡信息
                finger_userinfo_bank = getBankInfoByFuId(ringer_userinfo_new.fu_id);
                //指纹系统-用户车牌跟用户绑定信息查询
                carCodeList = getCarcodeByFuId(ringer_userinfo_new.getFu_id());
            }
            //封装返回结果数据
            jsonObject.put("finger_userinfo_new",ringer_userinfo_new);
            jsonObject.put("finger_userinfo_bank",finger_userinfo_bank);
            jsonObject.put("carCodeList",carCodeList);

            //返回数据
            returnData.setReturnData(errorcode_success, "查询成功", jsonObject);
        } catch (Exception e) {
            log.error("指纹系统-通过手机号码查询用户采集的信息-查询失败", e);
            returnData.setReturnData(errorcode_systerm, "指纹系统-通过手机号码查询用户采集的信息-查询失败", null);
        }

    }

    /**
     * 根据手机号查询指纹系统用户信息
     */
    public Finger_userinfo_new getFingerUserInfoByUiTel(String ui_tel){
        try{
            String sql = "SELECT * FROM finger_userinfo_new WHERE ui_tel = ?  AND state = 1 AND is_del = 0 limit 1";
            return getMySelfService().queryUniqueT(sql,Finger_userinfo_new.class,ui_tel);
        }catch(Exception e){
            log.error("getFingerUserInfoByUiTel is error", e);
        }
        return null;
    }

    /**
     * 用户银行卡信息查询
     */
    public Finger_userinfo_bank getBankInfoByFuId(long fu_id){
        try{
            String sql = "SELECT * FROM finger_userinfo_bank t " +
                    " LEFT JOIN finger_userinfo_new t1 ON t1.fu_id = t.fu_id " +
                    " WHERE t1.fu_id =? AND t.state = 1 AND t.is_del = 0 limit 1 ";
            return getMySelfService().queryUniqueT(sql,Finger_userinfo_bank.class,fu_id);
        }catch(Exception e){
            log.error("getBankInfoByFuId is error", e);
        }
        return null;
    }

    /**
     * 用户车牌跟用户绑定信息查询
     */
    public List<Finger_userinfo_carcode> getCarcodeByFuId(long fu_id){
        try{
            String sql = "SELECT t.* FROM finger_userinfo_carcode t " +
                    " LEFT JOIN finger_userinfo_new t1 ON t1.fu_id = t.fu_id " +
                    " WHERE t1.fu_id = ? AND t.isi_del = 0";
            return getMySelfService().queryListT(sql,Finger_userinfo_carcode.class,fu_id);
        }catch(Exception e){
            log.error("getCarcodeByFuId is error", e);
        }
        return null;
    }
}
