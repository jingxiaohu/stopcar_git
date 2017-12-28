package com.park.app.service;

import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.Fault_record;
import com.park.bean.Park_info;
import com.park.bean.Rental_charging_rule;
import com.park.bean.ReturnDataNew;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.ParkInfoPB;
import com.park.mvc.service.common.PayParkPB;
import com.park.transaction.CarTransaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理停车场信息的业务逻辑管理类
 *
 * @author jingxiaohu
 */
@Service
public class AppParkinfoBiz extends BaseBiz {

  @Autowired
  private CarTransaction carTransaction;
  @Autowired
  protected CarBiz carBiz;
  @Autowired
  protected ParkInfoPB parkInfoPB;
  @Autowired
  protected PayParkPB payParkPB;

//	String str_filed = "pi_id,area_code,pi_name,address_name,lng,lat,linkman_name,linkman_tel,copy_linkman_name,copy_linkman_tel,pi_phone,carport_space,carport_total,department,carport_yet,enter_num,exit_num,hlc_enter_num,hlc_exit_num,enter_camera_num,exit_camera_num,camera_info,ctime,utime,park_type,time_car_num,moth_car_num,expect_money,allow_revoke_time,allow_expect_time,timeout_info,rent_info,note";


  /**
   * 故障上报记录
   */

  public void record_fault_record(ReturnDataNew returnData, long pi_id,
      long pd_id, int fr_type, String fr_desc, String area_code) {
    // TODO Auto-generated method stub
    try {
      //首先判断是否已经进行了录入  如果数据库中没有该停车场的数据则不进行插入  否则不予处理
      //获取该场地的信息
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        returnData.setReturnData(errorcode_data, "该停车场不存在", "");
        return;
      }
      //入库操作
      Date date = new Date();
      Fault_record fault_record = new Fault_record();
      fault_record.setCtime(date);
      fault_record.setPi_id(pi_id);
      //获取是该停车场的 出入口设备信息表的主键ID
      fault_record.setPd_id(pd_id);
      fault_record.setUtime(date);
      fault_record.setFr_desc(fr_desc);
      fault_record.setFr_type(fr_type);
      fault_record.setArea_code(area_code);
      int id = fault_recordDao.insert(fault_record);
      if (id < 1) {
        returnData.setReturnData(errorcode_data, "故障上报记录失败", "");
        return;
      }
      fault_record.setFr_id(id);
      returnData.setReturnData(errorcode_success, null, fault_record);
      return;

    } catch (Exception e) {
      log.error("ParkinfoBiz record_fault_record is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


  /**
   * 通过GPS导航获取 该经纬度范围内的停车场数据列表
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void ReturnRead_gpspark(ReturnDataNew returnData, int dtype,
      long ui_id, double lng, double lat, int distance, int park_type, int type, String area_code) {
    // TODO Auto-generated method stub Distance(double long1, double lat1, double long2,double lat2)
    try {
      if (distance == 0) {
        distance = 500;//米
      }
      //首先检查停车场是否存在如果不存在则返回空集合
      String tablename = ReturnParkTableName(area_code);
      /*if (!isExistParkInfo(area_code, tablename)) {
        //不存在该表
        //返回结果
        returnData.setReturnData(errorcode_success, "查询附近的停车场信息成功", new ArrayList<Park_info>());
        return;
      }*/
      //首先根据经纬度从数据库中筛选出  范围在 上下不超过0.1 的停车场数据
      String sql = "";
      if (type == 0) {
        sql =
            "SELECT * FROM " +
                    "(SELECT *,ROUND(6378.138*2*ASIN(SQRT(POW(SIN((?*PI()/180-lat*PI()/180)/2),2)+COS(?*PI()/180)*COS(lat*PI()/180)*POW(SIN((?*PI()/180-lng*PI()/180)/2),2)))*1000) AS juli "
                + "   FROM " + ReturnParkTableName(area_code) +"  where pi_state=1) cc "
                + "   where juli < ?   "
                + " ORDER BY juli ASC LIMIT 200";
      } else {
        sql =
            "SELECT *,ROUND(6378.138*2*ASIN(SQRT(POW(SIN((?*PI()/180-lat*PI()/180)/2),2)+COS(?*PI()/180)*COS(lat*PI()/180)*POW(SIN((?*PI()/180-lng*PI()/180)/2),2)))*1000) AS juli "
                //+ " FROM "+ReturnParkTableName(area_code)+" having juli < ? and park_type=? "
                    + "   FROM " + ReturnParkTableName(area_code) +"  where pi_state=1) cc "
                    + "   where juli < ?   "
                    + " ORDER BY month_price ASC LIMIT 200";
      }
//			List<Park_info> list = getMySelfService().queryListT(sql, Park_info.class, lat,lat,lng,distance,park_type);
      List<Park_info> list = getMySelfService()
          .queryListT(sql, Park_info.class, lat, lat, lng, distance);
      //返回结果
      returnData.setReturnData(errorcode_success, "查询附近的停车场信息成功", list);
      return;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("ParkinfoBiz.ReturnRead_gpspark is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 读取用户预约下单租赁包月车位(车位租赁)详情页
   */
  public void read_rent_order(ReturnDataNew returnData, int dtype,
      long ui_id, long pi_id, String area_code) {
    // TODO Auto-generated method stub
    try {
      //获取 该停车场规则详细
      int rcr_type = 1;//停车类型 0：普通车位停车 1：时间段包月停车
      Rental_charging_rule charging_rule = queryChargeRule(pi_id, rcr_type, 1, area_code);
      //返回结果
      returnData.setReturnData(errorcode_success, "查询停车场租赁规则信息成功", charging_rule);
      return;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("ParkinfoBiz.read_rent_order is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 读取用户预约下单普通车位 需要的订单准备数据(普通车位停车)详情页
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void read_expect_order(ReturnDataNew returnData, int dtype,
      long ui_id, long pi_id, String area_code) {
    // TODO Auto-generated method stub
    try {
      //获取 该停车场规则详细
      int rcr_type = 0;//停车类型 0：普通车位停车 1：时间段包月停车
      Rental_charging_rule charging_rule = queryChargeRule(pi_id, rcr_type, 1, area_code);
      if (charging_rule == null) {
        returnData.setReturnData(errorcode_data, "很抱歉亲！，该停车场已关闭预约功能。", "", "1");
        return;
      }
      //返回结果
      returnData.setReturnData(errorcode_success, "停车场收费规则信息成功", charging_rule);
      return;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("ParkinfoBiz.ReturnRead_gpspark is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


  /**
   * 通过GPS导航获取 该经纬度范围内的停车场数据列表 ---- 车位租赁
   *
   * @param type 0 :按距离 1：按价格
   */
  public void ReturnRead_gpspark_rent(ReturnDataNew returnData, int dtype,
      long ui_id, double lng, double lat, int distance, int type, int park_type, String area_code) {
    // TODO Auto-generated method stub
    try {
      if (distance == 0) {
        distance = 500;//米
      }
      //首先根据经纬度从数据库中筛选出  范围在 上下不超过0.1 的停车场数据
      String sql = "";
      if (type == 0) {
        sql =
            "SELECT *,ROUND(6378.138*2*ASIN(SQRT(POW(SIN((?*PI()/180-lat*PI()/180)/2),2)+COS(?*PI()/180)*COS(lat*PI()/180)*POW(SIN((?*PI()/180-lng*PI()/180)/2),2)))*1000) AS juli FROM "
                + ReturnParkTableName(area_code)
                + " having juli < ? and park_type=? and is_rent=1 and month_price>0 and pi_state=1 "
                + " ORDER BY juli ASC LIMIT 200";
      } else {
        sql =
            "SELECT *,ROUND(6378.138*2*ASIN(SQRT(POW(SIN((?*PI()/180-lat*PI()/180)/2),2)+COS(?*PI()/180)*COS(lat*PI()/180)*POW(SIN((?*PI()/180-lng*PI()/180)/2),2)))*1000) AS juli FROM "
                + ReturnParkTableName(area_code)
                + " having juli < ? and park_type=? and is_rent=1 and month_price>0 and pi_state=1  "
                + " ORDER BY month_price ASC LIMIT 200";
      }
      List<Park_info> list = getMySelfService()
          .queryListT(sql, Park_info.class, lat, lat, lng, distance, park_type);
      //返回结果
      returnData.setReturnData(errorcode_success, "查询附近的租赁停车场信息成功", list);
      return;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("ParkinfoBiz.ReturnRead_gpspark is error" + e.getMessage(), e);
    }
  }

  /**************************分离出来的方法*****************************/


  /**
   * 查询场地规则数据
   *
   * @param rcr_type ：停车类型 0：普通车位停车 1：时间段包月停车
   * @param car_type 车牌类型 0：未知车牌:、1：蓝牌小汽车、2：: 黑牌小汽车、3：单排黄牌、4：双排黄牌、 5： 警车车牌、6：武警车牌、7：个性化车牌、8：单
   * 排军车牌、9：双排军车牌、10：使馆车牌、11： 香港进出中国大陆车牌、12：农用车牌、13：教 练车牌、14：澳门进出中国大陆车牌、15：双层
   * 武警车牌、16：武警总队车牌、17：双层武警总 队车牌
   */
  public Rental_charging_rule queryChargeRule(long pi_id, int rcr_type, int car_type,
      String area_code) {
    try {
      String sql = "SELECT *  FROM  rental_charging_rule WHERE pi_id=? AND area_code=? AND rcr_state=0 AND rcr_type=? AND car_code_color=1 AND car_type=? LIMIT 1";
      return getMySelfService()
          .queryUniqueT(sql, Rental_charging_rule.class, pi_id, area_code, rcr_type, car_type);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("queryRentChargeRuleByPiId is error " + e.getMessage(), e);
    }
    return null;
  }


}
