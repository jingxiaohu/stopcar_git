package com.park.pda.service;

import com.park.bean.Finger_userinfo;
import com.park.bean.Finger_userinfo_relation;
import com.park.bean.ReturnDataNew;
import com.park.mvc.service.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Peter Wu
 */
@Service
public class Finger_userinfoBiz extends BaseBiz {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * 根据车牌读取用户指纹/指静脉信息
   * @param returnData
   * @param car_code
   * @param finger_flag
   * @throws Exception
   */
  public void read_finger_userinfo(ReturnDataNew returnData, String car_code, Integer finger_flag) throws Exception {
    List<String> fingerList = null;
    String msg = "未获取到用户对应指纹/指静脉信息";
    try {
      //读取指纹、指静脉用户信息
      Finger_userinfo fingerUserinfo = getMySelfService().queryUniqueT(
              "SELECT * FROM finger_userinfo WHERE car_code=? AND is_del=0 AND state=1 LIMIT 1",
              Finger_userinfo.class, car_code);
      if(null == fingerUserinfo){
        returnData.setReturnData(errorcode_data, "未获取到指纹/指静脉用户信息", null);
        return;
      }

      //读取用户指纹、指静脉信息对象
      List<Finger_userinfo_relation> fURelationList = getMySelfService().queryListT(
          "SELECT t1.* FROM finger_userinfo t "
                  +" INNER JOIN finger_userinfo_relation t1 ON t1.fu_id=t.fu_id "
                  +" WHERE t.car_code=? AND t.is_del=0 AND t.state=1",
              Finger_userinfo_relation.class, car_code);

      if(fURelationList != null && fURelationList.size()>0){
        //用户指纹/指静脉标记 finger_flag 0:指静脉 1:指纹
        if (fingerUserinfo.getIs_sign() == 1 && fingerUserinfo.getVerify_sign() == 1) {
          fingerList = new ArrayList<>();

          for (Finger_userinfo_relation fURelation : fURelationList) {
            if (finger_flag == 1) {
                if(fURelation.getFingerprint() != null && fURelation.getFingerprint() != ""){
                  fingerList.add(fURelation.getFingerprint());
                  msg = "读取用户指纹信息成功";
                }else {
                  msg = "未找到用户指纹信息";
                }
            }else if (finger_flag == 0) {
              if(fURelation.getFinger_veno() != null && fURelation.getFinger_veno() != ""){
                String finger_vero = fURelation.getFinger_veno();
                fingerList.add(finger_vero);
                msg = "读取用户指静脉信息成功";
              }else {
                msg = "未找到用户指静脉信息";
              }
            }else{
              msg = "finger_flag参数值不正确";
            }
          }
        } else {
          msg = "指纹/指静脉支付签约失败";
        }
      }
    } catch (EmptyResultDataAccessException ignored) {
      //ignored 未找到用户对应指纹/指静脉
    }
    returnData.setReturnData(errorcode_success, msg, fingerList);
  }
}
