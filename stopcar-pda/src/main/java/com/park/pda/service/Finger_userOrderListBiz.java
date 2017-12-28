package com.park.pda.service;

import com.alibaba.fastjson.JSONObject;
import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.Finger_userinfo;
import com.park.bean.Pay_park;
import com.park.bean.ReturnDataNew;
import com.park.dao.Finger_userinfoDao;
import com.park.dao.Pay_parkDao;
import com.park.mvc.service.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 指纹系统--查询用户已经支付成功的订单流水
 * @author zyy
 */
@Service
public class Finger_userOrderListBiz extends BaseBiz {

  @Autowired
  protected Finger_userinfoDao finger_userinfoDao;
  @Autowired
  protected Pay_parkDao pay_parkDao;

  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void getUserOrderList(ReturnDataNew returnData, String ui_tel,int page,int size) throws Exception {
    try {
      if (page < 1) {
        page = 1;
      }
      int start = (page - 1) * size;

      //根据手机号码查询用户信息列表（一个手机号可以有多个车牌号）
      String sql = "select * from finger_userinfo where ui_tel=? ";
      List<Finger_userinfo> userinfoList = finger_userinfoDao.queryListT(sql, Finger_userinfo.class, ui_tel);
      if(userinfoList == null || userinfoList.size() == 0){
        returnData.setReturnData(errorcode_data, "用户手机号码不存在", null);
        return;
      }

      List<Pay_park> userOrderList = new ArrayList<Pay_park>();
      List orderList = new ArrayList();
      JSONObject userOrderMap = null;
      for(Finger_userinfo finger_userinfo : userinfoList){
        //用户车牌
        String car_code = finger_userinfo.getCar_code();
        if(car_code == null){
          returnData.setReturnData(errorcode_data, "用户未绑定车牌号", null);
          return;
        }
        //分页查询用户已经支付成功的订单流水
        String orderSql = "select * from pay_park where pay_source=8 and car_code=? and is_del=0 and pp_state=1 " +
                "order by utime desc limit ?,? ";
        userOrderList = pay_parkDao.queryListT(orderSql,Pay_park.class,car_code,start,size);
        if(userOrderList.size()>0) {
          userOrderMap = new JSONObject();
          userOrderMap.put("car_code",car_code);
          userOrderMap.put("userOrderList",userOrderList);
        }
        if(userOrderMap != null){
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
}
