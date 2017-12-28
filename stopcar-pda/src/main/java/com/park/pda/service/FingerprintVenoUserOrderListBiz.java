package com.park.pda.service;

import com.alibaba.fastjson.JSONObject;
import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.Finger_userinfo_carcode;
import com.park.bean.Finger_userinfo_new;
import com.park.bean.Pay_park;
import com.park.bean.ReturnDataNew;
import com.park.dao.Finger_userinfo_newDao;
import com.park.dao.Pay_parkDao;
import com.park.mvc.service.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 指纹系统--查询用户已经支付成功的订单流水(指纹/指静脉)
 * @author zyy
 */
@Service
public class FingerprintVenoUserOrderListBiz extends BaseBiz {

  @Autowired
  protected Finger_userinfo_newDao finger_userinfo_newDao;
  @Autowired
  protected Pay_parkDao pay_parkDao;

  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void getUserOrderList(ReturnDataNew returnData, String ui_tel,int page,int size) throws Exception {
    try {
      if (page < 1) {
        page = 1;
      }
      int start = (page - 1) * size;

      //根据手机号码查询用户信息
      String sql = "select * from finger_userinfo_new where ui_tel=? limit 1";
      Finger_userinfo_new fingerUserInfo = finger_userinfo_newDao.queryUniqueT(sql, Finger_userinfo_new.class, ui_tel);
      if(fingerUserInfo == null){
        returnData.setReturnData(errorcode_success, "指纹系统--该手机号码未绑定用户", null);
        return;
      }

      List<Pay_park> userOrderList = new ArrayList<Pay_park>();
      List<Map> orderList = new ArrayList();
      JSONObject userOrderMap = null;
      //用户车牌列表（一个手机号可以有多个车牌号）
      List<Finger_userinfo_carcode> carCodeList = getCarcodeByFuId(fingerUserInfo.getFu_id());
      if(carCodeList == null || carCodeList.size() == 0){
        returnData.setReturnData(errorcode_success, "用户未绑定车牌号", null);
        return;
      }
      for (Finger_userinfo_carcode finger_userinfo_carcode : carCodeList) {
        String car_code = finger_userinfo_carcode.getCar_code();
        //分页查询用户已经支付成功的订单流水
        String orderSql = "SELECT * FROM pay_park WHERE car_code=? AND pay_source in(8,9) AND is_del=0 AND pp_state=1 " +
                " ORDER BY utime DESC LIMIT ?,? ";
        userOrderList = pay_parkDao.queryListT(orderSql,Pay_park.class,car_code,start,size);
        if(userOrderList.size()>0) {
          userOrderMap = new JSONObject();
          userOrderMap.put("car_code",car_code);
          userOrderMap.put("userOrderList",userOrderList);
          orderList.add(userOrderMap);
        }
      }

      //返回数据
      returnData.setReturnData(errorcode_success, "获取用户已经支付成功的订单流水成功", orderList);
      return;
    } catch (Exception e) {
      log.error("Finger_userOrderListBiz getUserOrderList is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }
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
      log.error("FingerprintVenoUserOrderListBiz getCarcodeByFuId is error", e);
    }
    return null;
  }
}
