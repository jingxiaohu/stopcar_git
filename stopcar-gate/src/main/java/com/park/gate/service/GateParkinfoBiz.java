package com.park.gate.service;

import com.park.bean.Car_in_out;
import com.park.bean.Park_device;
import com.park.bean.Park_info;
import com.park.bean.Pay_month_park;
import com.park.bean.Pay_park;
import com.park.bean.Rental_charging_rule;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_carcode;
import com.park.gate.transaction.GateCarTransaction;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.ParkInfoPB;
import com.park.mvc.service.common.PayParkPB;
import com.park.util.RequestUtil;
import java.util.Date;
import javax.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 处理停车场信息的业务逻辑管理类
 *
 * @author jingxiaohu
 */
@Service
public class GateParkinfoBiz extends BaseBiz {

  @Resource
  protected GateCarTransaction gateCarTransaction;
  @Autowired
  protected GateCarBiz gateCarBiz;
  @Autowired
  protected ParkInfoPB parkInfoPB;
  @Autowired
  protected PayParkPB payParkPB;

//	String str_filed = "pi_id,area_code,pi_name,address_name,lng,lat,linkman_name,linkman_tel,copy_linkman_name,copy_linkman_tel,pi_phone,carport_space,carport_total,department,carport_yet,enter_num,exit_num,hlc_enter_num,hlc_exit_num,enter_camera_num,exit_camera_num,camera_info,ctime,utime,park_type,time_car_num,moth_car_num,expect_money,allow_revoke_time,allow_expect_time,timeout_info,rent_info,note";

  /**
   * 记录停车场信息
   */
  public void record_parkinfo(ReturnDataNew returnData, String name,
      String address_name, Double lng, Double lat, String linkman_name,
      String linkman_tel, String copy_linkman_name,
      String copy_linkman_tel, String pi_phone, String department,
      Integer enter_num, Integer exit_num, Integer hlc_enter_num,
      Integer hlc_exit_num, Integer enter_camera_num, Integer exit_camera_num,
      String camera_info, String park_type, String area_code, Integer expect_money,
      Integer allow_revoke_time, Integer allow_expect_time, Integer is_expect
      , Integer carport_yet, Integer carport_space, Integer carport_total, Integer moth_car_num,
      Integer moth_car_num_space, Integer time_car_num, Integer time_car_num_space,
      Integer expect_car_num, Integer admin_id, Integer upload_source, Integer roadside_type,
      String special_ip) {
    // TODO Auto-generated method stub
    try {
      String tablename = ReturnParkTableName(area_code);
      if (!isExistParkInfo(area_code, tablename)) {
        //不存在该表
        returnData.setReturnData(errorcode_data, "该地区还未开通", "");
        return;
      }
      //首先判断是否已经进行了录入  如果数据库中没有该停车场的数据则进行插入  否则不予处理
      Park_info park_info = queryByNameT(name, address_name, area_code);
      if (park_info != null) {
        //该停车场已经录入过了
        returnData.setReturnData(errorcode_data, "该停车场已经录入过了", park_info, "1");
        return;
      }
      //入库操作
      park_info = new Park_info();
      Date date = new Date();
      if (admin_id != null) {
        park_info.setAdmin_id(admin_id.intValue());
      }
      if (upload_source != null) {
        park_info.setUpload_source(upload_source.intValue());
      }
      if (name != null) {
        park_info.setPi_name(name);
      }
      if (address_name != null) {
        park_info.setAddress_name(address_name);
      }
      if (camera_info != null) {
        park_info.setCamera_info(camera_info);
      }
      if (copy_linkman_name != null) {
        park_info.setCopy_linkman_name(copy_linkman_name);
      }
      if (copy_linkman_tel != null) {
        park_info.setCopy_linkman_tel(copy_linkman_tel);
      }

      park_info.setCtime(date);
      if (department != null) {
        park_info.setDepartment(department);
      }

      if (enter_camera_num != null) {
        park_info.setEnter_camera_num(enter_camera_num.intValue());
      }
      if (enter_num != null) {
        park_info.setEnter_num(enter_num.intValue());
      }
      if (exit_camera_num != null) {
        park_info.setExit_camera_num(exit_camera_num.intValue());
      }
      if (exit_num != null) {
        park_info.setExit_num(exit_num.intValue());
      }
      if (hlc_enter_num != null) {
        park_info.setHlc_enter_num(hlc_enter_num.intValue());
      }
      if (hlc_exit_num != null) {
        park_info.setHlc_exit_num(hlc_exit_num.intValue());
      }
      if (lat != null) {
        park_info.setLat(lat.doubleValue());
      }
      if (lng != null) {
        park_info.setLng(lng.doubleValue());
      }

      if (linkman_name != null) {
        park_info.setLinkman_name(linkman_name);
      }
      if (linkman_tel != null) {
        park_info.setLinkman_tel(linkman_tel);
      }

      if (park_type != null) {
        park_info.setPark_type(Integer.parseInt(park_type));
      }
      if (pi_phone != null) {
        park_info.setPi_phone(pi_phone);
      }
      park_info.setUtime(date);
      if (area_code != null) {
        park_info.setArea_code(area_code);
      }

      if (allow_expect_time != null) {
        park_info.setAllow_expect_time(allow_expect_time.intValue());
      }
      if (allow_revoke_time != null) {
        park_info.setAllow_revoke_time(allow_revoke_time.intValue());
      }
      if (expect_money != null) {
        park_info.setExpect_money(expect_money);
      }
      if (moth_car_num != null) {
        park_info.setMoth_car_num(moth_car_num);
      }
      if (is_expect != null) {
        park_info.setIs_expect(is_expect);
      }
      if (carport_total != null && carport_total.intValue() > 0) {
        park_info.setCarport_total(carport_total.intValue());
      }
      if (carport_space != null && carport_space.intValue() > 0) {
        park_info.setCarport_space(carport_space.intValue());
      }
      if (carport_yet != null && carport_yet.intValue() > 0) {
        park_info.setCarport_yet(carport_yet.intValue());
      }
      if (moth_car_num != null && moth_car_num.intValue() > 0) {
        park_info.setMoth_car_num(moth_car_num.intValue());
      }
      if (moth_car_num_space != null && moth_car_num_space.intValue() > 0) {
        park_info.setMoth_car_num_space(moth_car_num_space.intValue());
      }
      if (time_car_num != null && time_car_num.intValue() > 0) {
        park_info.setTime_car_num(time_car_num.intValue());
      }

      if (time_car_num_space != null && time_car_num_space.intValue() > 0) {
        park_info.setTime_car_num_space(time_car_num_space.intValue());
      }

      //by jxh 2017-2-24 添加是否按次收费
      if (roadside_type != null) {
        park_info.setRoadside_type(roadside_type);
      }

      //道闸专线静态IP不为空时 ，存入数据库
      if (null != special_ip) {
        park_info.setSpecial_ip(special_ip);
      }

      int id = park_infoDao.insert(park_info, ReturnParkTableName(area_code));
      park_info.setPi_id(id);
      returnData.setReturnData(errorcode_success, null, park_info);
      return;

    } catch (Exception e) {
      log.error("ParkinfoBiz record_parkinfo is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 更新场地信息
   */
  public void update_parkinfo(ReturnDataNew returnData, long pi_id, String name,
      String address_name, Double lng, Double lat, String linkman_name,
      String linkman_tel, String copy_linkman_name, String copy_linkman_tel,
      String pi_phone, String department, Integer enter_num,
      Integer exit_num, Integer hlc_enter_num, Integer hlc_exit_num,
      Integer enter_camera_num, Integer exit_camera_num, String camera_info,
      String park_type, String area_code, Integer expect_money, Integer allow_revoke_time,
      Integer allow_expect_time, Integer is_expect
      , Integer carport_yet, Integer carport_space, Integer carport_total,
      Integer moth_car_num, Integer moth_car_num_space, Integer time_car_num,
      Integer time_car_num_space, Integer expect_car_num, Integer admin_id, Integer upload_source,
      Integer roadside_type, String special_ip) {
    // TODO Auto-generated method stub
    try {
      //获取该场地的信息
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        returnData.setReturnData(errorcode_data, "该停车场不存在", "");
        return;
      }
      //入库操作
      Date date = new Date();
      if (admin_id != null) {
        park_info.setAdmin_id(admin_id.intValue());
      }
      if (upload_source != null) {
        park_info.setUpload_source(upload_source.intValue());
      }
      if (name != null) {
        park_info.setPi_name(name);
      }
      if (address_name != null) {
        park_info.setAddress_name(address_name);
      }

      if (camera_info != null) {
        park_info.setCamera_info(camera_info);
      }

      if (copy_linkman_name != null) {
        park_info.setCopy_linkman_name(copy_linkman_name);
      }

      if (copy_linkman_tel != null) {
        park_info.setCopy_linkman_tel(copy_linkman_tel);
      }

      if (department != null) {
        park_info.setDepartment(department);
      }

      if (enter_camera_num != null) {
        park_info.setEnter_camera_num(enter_camera_num.intValue());
      }
      if (enter_num != null) {
        park_info.setEnter_num(enter_num.intValue());
      }
      if (exit_camera_num != null) {
        park_info.setExit_camera_num(exit_camera_num.intValue());
      }
      if (exit_num != null) {
        park_info.setExit_num(exit_num.intValue());
      }
      if (hlc_enter_num != null) {
        park_info.setHlc_enter_num(hlc_enter_num.intValue());
      }
      if (hlc_exit_num != null) {
        park_info.setHlc_exit_num(hlc_exit_num.intValue());
      }
      if (lat != null) {
        park_info.setLat(lat.doubleValue());
      }
      if (lng != null) {
        park_info.setLng(lng.doubleValue());
      }

      if (linkman_name != null) {
        park_info.setLinkman_name(linkman_name);
      }
      if (linkman_tel != null) {
        park_info.setLinkman_tel(linkman_tel);
      }

      if (park_type != null) {
        park_info.setPark_type(Integer.parseInt(park_type));
      }
      if (pi_phone != null) {
        park_info.setPi_phone(pi_phone);
      }
      park_info.setUtime(date);

      if (area_code != null) {
        park_info.setArea_code(area_code);
      }

      if (allow_expect_time != null) {
        park_info.setAllow_expect_time(allow_expect_time.intValue());
      }
      if (allow_revoke_time != null) {
        park_info.setAllow_revoke_time(allow_revoke_time.intValue());
      }
      if (expect_money != null) {
        park_info.setExpect_money(expect_money);
      }
      if (moth_car_num != null) {
        park_info.setMoth_car_num(moth_car_num);
      }
      if (is_expect != null) {
        park_info.setIs_expect(is_expect);
      }
      if (carport_total != null && carport_total.intValue() > 0) {
        park_info.setCarport_total(carport_total.intValue());
      }
      if (carport_space != null && carport_space.intValue() > 0) {
        park_info.setCarport_space(carport_space.intValue());
      }
      if (carport_yet != null && carport_yet.intValue() > 0) {
        park_info.setCarport_yet(carport_yet.intValue());
      }
      if (moth_car_num != null && moth_car_num.intValue() > 0) {
        park_info.setMoth_car_num(moth_car_num.intValue());
      }
      if (moth_car_num_space != null && moth_car_num_space.intValue() > 0) {
        park_info.setMoth_car_num_space(moth_car_num_space.intValue());
      }
      if (time_car_num != null && time_car_num.intValue() > 0) {
        park_info.setTime_car_num(time_car_num.intValue());
      }

      if (time_car_num_space != null && time_car_num_space.intValue() > 0) {
        park_info.setTime_car_num_space(time_car_num_space.intValue());
      }

      //by jxh 2017-2-24 添加是否按次收费
      if (roadside_type != null) {
        park_info.setRoadside_type(roadside_type);
      }

      //道闸专线静态IP不为空时 ，存入数据库
      if (null != special_ip) {
        park_info.setSpecial_ip(special_ip);
      }

      int count = park_infoDao.updateByKey(park_info, ReturnParkTableName(area_code));
      if (count > 0) {
        returnData.setReturnData(errorcode_success, "更新成功", park_info);
        return;
      } else {
        //更新失败
        returnData.setReturnData(errorcode_data, "更新失败", "");
        return;
      }

    } catch (Exception e) {
      log.error("ParkinfoBiz record_parkinfo is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


  /**
   * 记录停车场计费规则信息
   */
  public void record_charging_rule(ReturnDataNew returnData, Long pi_id,
      Integer start_price, Integer start_time, Integer charging, Integer charging_time,
      Integer month_price, Integer month_time, String permit_time,
      String timeout_info, Integer rcr_type, Integer rcr_state, Integer rcr_discount,
      String car_displacement, Integer car_type, Integer car_code_color,
      Integer is_time_bucket, String time_bucket, String area_code, Integer roadside_type) {
    // TODO Auto-generated method stub rental_charging_rule
    try {
      //首先判断是否已经进行了录入  如果数据库中没有该停车场的数据则不进行插入  否则不予处理
      //获取该场地的信息
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        returnData.setReturnData(errorcode_data, "该停车场不存在", "");
        return;
      }
      //检查该项规则是否之前已经录入过
      Rental_charging_rule charging_rule = queryChargeRuleByMd5(pi_id, rcr_type, car_type,
          car_code_color, area_code);
      if (charging_rule != null) {
        returnData.setReturnData(errorcode_data, "该停车场的该规则已经存在", charging_rule);
        return;
      }

      if (park_info.park_type != 1 && charging != null) {
        if (charging < 1) {
          returnData.setReturnData(errorcode_param, "charging必须大于0", null);
          return;
        }
      }
      //入库操作
      Date date = new Date();
      charging_rule = new Rental_charging_rule();
      if (car_displacement != null) {
        charging_rule.setCar_displacement(car_displacement);
      }
      if (car_code_color != null) {
        charging_rule.setCar_code_color(car_code_color);
      }
      if (car_type != null) {
        charging_rule.setCar_type(car_type);
      }
      if (charging != null) {
        charging_rule.setCharging(charging);
      }
      if (charging_time != null) {
        charging_rule.setCharging_time(charging_time);
      }

      if (is_time_bucket != null) {
        charging_rule.setIs_time_bucket(is_time_bucket);
      }
      if (month_price != null) {
        charging_rule.setMonth_price(month_price);
      }
      if (month_time != null) {
        charging_rule.setMonth_time(month_time);
      }

      if (permit_time != null) {
        charging_rule.setPermit_time(permit_time);
      }

      if (pi_id != null) {
        charging_rule.setPi_id(pi_id);
      }

      if (rcr_discount != null) {
        charging_rule.setRcr_discount(rcr_discount);
      }

      if (rcr_state != null) {
        charging_rule.setRcr_state(rcr_state);
      }
      if (rcr_type != null) {
        charging_rule.setRcr_type(rcr_type);
      }
      if (start_price != null) {
        charging_rule.setStart_price(start_price);
      }
      if (start_time != null) {
        charging_rule.setStart_time(start_time);
      }
      if (time_bucket != null) {
        charging_rule.setTime_bucket(time_bucket);
      }
      if (timeout_info != null) {
        charging_rule.setTimeout_info(timeout_info);
      }
      if (area_code != null) {
        charging_rule.setArea_code(area_code);
      }
      String md5str = MD5RentalChargingRrule(pi_id, rcr_type, car_type, car_code_color, area_code);
      charging_rule.setRcr_md5(md5str);
      charging_rule.setUtime(date);
      charging_rule.setCtime(date);

      //by jxh 2017-2-24 添加是否按次收费
      if (roadside_type != null) {
        charging_rule.setRoadside_type(roadside_type);
      }
      //by jxh 2017-3-17 添加设置默认规则
      if (isDefault(car_type, car_code_color)) {
        charging_rule.setIs_default(1);
      }
      int id = rental_charging_ruleDao.insert(charging_rule);
      if (id < 1) {
        returnData.setReturnData(errorcode_data, "插入场地计费规则失败", "");
        return;
      } else {
        if (isDefault(car_type, car_code_color)) {
          //普通规则  租赁规则  rcr_type 停车类型 0：普通车位停车 1：时间段包月停车
          if (rcr_type == 0) {

            //by jxh 2017-2-24 添加是否按次收费
            if (roadside_type != null) {
              park_info.setRoadside_type(roadside_type);
              //每次插入计费规则成功都去检查下 停车场数据表中 是否有 计费规则初始信息 如果没有则添加 如果有则不处理
              if (RequestUtil.checkObjectBlank(park_info.getTimeout_info())) {
                if (roadside_type == 1) {
                  //按次收费
                  String str = "单次%s元";
                  str = String.format(str, formatDouble((charging * 1.0) / 100));
                  park_info.setTimeout_info(str);
                } else {
                  //为空 则添加 首停2小时5元，之后每小时2元
                  String str = "首停%s小时%s元，之后每小时%s元";
                  str = String.format(str, formatDouble((start_time * 1.0) / 60),
                      formatDouble((start_price * 1.0) / 100),
                      formatDouble((charging * 1.0) / 100));
                  park_info.setTimeout_info(str);
                }
                //更新停车场首基本停车规则信息
                park_infoDao.updateByKey(park_info, ReturnParkTableName(area_code));
              } else {
                //不为空
                //更新停车场首基本停车规则信息
                park_infoDao.updateByKey(park_info, ReturnParkTableName(area_code));
              }
            } else {
              //每次插入计费规则成功都去检查下 停车场数据表中 是否有 计费规则初始信息 如果没有则添加 如果有则不处理
              if (RequestUtil.checkObjectBlank(park_info.getTimeout_info())) {
                //为空 则添加 首停2小时5元，之后每小时2元
                String str = "首停%s小时%s元，之后每小时%s元";
                str = String.format(str, formatDouble((start_time * 1.0) / 60),
                    formatDouble((start_price * 1.0) / 100), formatDouble((charging * 1.0) / 100));
                park_info.setTimeout_info(str);
                //更新停车场首基本停车规则信息
                park_infoDao.updateByKey(park_info, ReturnParkTableName(area_code));
              }
            }

            //补全停车场蓝牌小汽车的起步价
            if (park_info.getMoney() < 1 && car_code_color == 1) {
              park_info.setMoney(start_price);
              //更新停车场蓝牌小汽车起步价
              park_infoDao.updateByKey(park_info, ReturnParkTableName(area_code));
            }


          } else {
            if (RequestUtil.checkObjectBlank(park_info.getRent_info())) {
              //时间段包月停车 准入时段 18:00-08:00，300元/月
              String str = "准入时段 %s，%s元/月";
              str = String.format(str, permit_time, formatDouble((month_price * 1.0) / 100));
              park_info.setRent_info(str);
              park_info.setMonth_price(month_price);
              park_info.setIs_rent(1);

              //更新停车场首基本停车规则信息
              park_infoDao.updateByKey(park_info, ReturnParkTableName(area_code));
            }
          }
        }

      }
      charging_rule.setRcr_id(id);
      returnData.setReturnData(errorcode_success, null, charging_rule);
      return;

    } catch (Exception e) {
      log.error("ParkinfoBiz record_charging_rule is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 更新停车场规则
   */
  public void update_charging_rule(ReturnDataNew returnData, long rcr_id, Long pi_id,
      Integer start_price, Integer start_time, Integer charging, Integer charging_time,
      Integer month_price, Integer month_time, String permit_time,
      String timeout_info, Integer rcr_type, Integer rcr_state, Integer rcr_discount,
      String car_displacement, Integer car_type, Integer car_code_color,
      Integer is_time_bucket, String time_bucket, String area_code, Integer roadside_type) {
    // TODO Auto-generated method stub

    try {
      //首先获取该条规则
      Rental_charging_rule charging_rule = rental_charging_ruleDao.selectByKey(rcr_id);
      if (charging_rule == null) {
        returnData.setReturnData(errorcode_data, "该停车场的该条规则不存在", "");
        return;
      }
      //更新操作
      Date date = new Date();
      if (car_displacement != null) {
        charging_rule.setCar_displacement(car_displacement);
      }
      if (car_code_color != null) {
        charging_rule.setCar_code_color(car_code_color);
      }
      if (car_type != null) {
        charging_rule.setCar_type(car_type);
      }
      if (charging != null && charging >= 0) {
        charging_rule.setCharging(charging);
      }
      if (charging_time != null) {
        charging_rule.setCharging_time(charging_time);
      }

      if (is_time_bucket != null) {
        charging_rule.setIs_time_bucket(is_time_bucket);
      }
      if (month_price != null) {
        charging_rule.setMonth_price(month_price);
      }
      if (month_time != null) {
        charging_rule.setMonth_time(month_time);
      }

      if (permit_time != null) {
        charging_rule.setPermit_time(permit_time);
      }

      if (pi_id != null) {
        charging_rule.setPi_id(pi_id);
      }

      if (rcr_discount != null) {
        charging_rule.setRcr_discount(rcr_discount);
      }

      if (rcr_state != null) {
        charging_rule.setRcr_state(rcr_state);
      }
      if (rcr_type != null) {
        charging_rule.setRcr_type(rcr_type);
      }
      if (start_price != null && start_price >= 0) {
        charging_rule.setStart_price(start_price);
      }
      if (start_time != null) {
        charging_rule.setStart_time(start_time);
      }
      if (time_bucket != null) {
        charging_rule.setTime_bucket(time_bucket);
      }
      if (timeout_info != null) {
        charging_rule.setTimeout_info(timeout_info);
      }
      if (area_code != null) {
        charging_rule.setArea_code(area_code);
      }
      charging_rule.setUtime(date);
      if (pi_id != null && rcr_type != null && car_type != null
          && car_code_color != null && area_code != null) {
        String md5str = MD5RentalChargingRrule(pi_id, rcr_type, car_type, car_code_color,
            area_code);
        charging_rule.setRcr_md5(md5str);
      }

      //by jxh 2017-2-24 添加是否按次收费
      if (roadside_type != null) {
        charging_rule.setRoadside_type(roadside_type);
      }
      //by jxh 2017-3-17 添加设置默认规则
      if (isDefault(car_type, car_code_color) && charging_rule.getIs_default() == 0) {
        //如果想更新成为默认规则 那么需要去数据库查询是否已经存在该类型（例如：临停规则 或者 租赁规则） 的类型的默认规则 如果已经存在则修改失败
        Rental_charging_rule charging_rule2 = queryDefaultChargeRule(pi_id, rcr_type, car_type,
            area_code);
        if (charging_rule2 == null) {
          charging_rule.setIs_default(1);
        }
        /*else {
          //已经有该种车辆对于类型的默认规则了
          returnData.setReturnData(errorcode_data, "已经有该种车辆对于类型的默认规则了", "");
          return;
        }*/
      }
      
      
      //by jxh 2017-7-6 修改规则的时候  检查该项规则是否与数据库中之前已经录入过的规则相同
      // 取消验证 2017-12-01
//      Rental_charging_rule charging_rule2 = queryChargeRuleByMd5(
//    		  charging_rule.getPi_id(), charging_rule.getRcr_type(),
//    		  charging_rule.getCar_type(),charging_rule.getCar_code_color(),
//    		  charging_rule.getArea_code()
//    		  );
//      if (charging_rule2 != null && rcr_id != charging_rule2.getRcr_id()) {
//        returnData.setReturnData(errorcode_data, "该停车场的该规则已经存在,修改失败", charging_rule);
//        return;
//      }
      
      
      
      int count = rental_charging_ruleDao.updateByKey(charging_rule);
      if (count > 0) {
        Park_info park_info = null;
        //更新场地规则默认信息
        if (isDefault(charging_rule.getCar_type(), charging_rule.getCar_code_color())) {
          park_info = returnParkInfo(pi_id, area_code);

          if (park_info == null) {
            returnData.setReturnData(errorcode_data, "未找到对应停车场", "");
            return;
          }

          if (rcr_type != null) {
            //普通规则  租赁规则  rcr_type 停车类型 0：普通车位停车 1：时间段包月停车
            if (rcr_type == 0) {
              //普通车位停车

              //by jxh 2017-2-24 添加是否按次收费
              if (roadside_type != null) {
                //更新停车场首基本停车规则信息
                park_info.setRoadside_type(roadside_type);
                if (roadside_type == 1) {
                  //按次收费
                  String str = "单次%s元";
                  str = String.format(str, formatDouble((charging * 1.0) / 100));
                  park_info.setTimeout_info(str);
                } else {
                  //为空 则添加 首停2小时5元，之后每小时2元
                  String str = "首停%s小时%s元，之后每小时%s元";
                  str = String.format(str, formatDouble((start_time * 1.0) / 60),
                      formatDouble((start_price * 1.0) / 100),
                      formatDouble((charging * 1.0) / 100));
                  park_info.setTimeout_info(str);
                }
              } else {
                //为空 则添加 首停2小时5元，之后每小时2元
                String str = "首停%s小时%s元，之后每小时%s元";
                str = String.format(str, formatDouble((start_time * 1.0) / 60),
                    formatDouble((start_price * 1.0) / 100), formatDouble((charging * 1.0) / 100));
                park_info.setTimeout_info(str);
              }
              if (park_info.getMoney() < 1 && car_code_color == 1) {
                park_info.setMoney(start_price);
              }
              //更新停车场首基本停车规则信息
              park_infoDao.updateByKey(park_info, ReturnParkTableName(area_code));
            } else {
              //时间段包月停车 准入时段 18:00-08:00，300元/月
              String str = "准入时段 %s，%s元/月";
              str = String.format(str, permit_time, formatDouble((month_price * 1.0) / 100));
              park_info.setRent_info(str);
              //更新停车场首基本停车规则信息
              park_infoDao.updateByKey(park_info, ReturnParkTableName(area_code));
            }
          }

        }
        returnData.setReturnData(errorcode_success, "更新成功", charging_rule);
        return;
      } else {
        //更新失败
        returnData.setReturnData(errorcode_data, "更新失败", "");
        return;
      }


    } catch (Exception e) {
      log.error("ParkinfoBiz record_charging_rule is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


  /**
   * 记录场地出入口设备对应关系信息
   */
  public void record_park_device(ReturnDataNew returnData, long pi_id,
      String in_out, String in_out_code, String camera,
      String camera_mac, String signo_name, String solid_garage_mac,
      String solid_garage_sn, String area_code) {
    // TODO Auto-generated method stub
    try {
      //首先判断是否已经进行了录入  如果数据库中没有该停车场的数据则不进行插入  否则不予处理
      //获取该场地的信息
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        returnData.setReturnData(errorcode_data, "该停车场不存在", "");
        return;
      }
      //检查该项规则是否之前已经录入过
      Park_device park_device = queryPark_deviceByMd5(pi_id, in_out, in_out_code, camera_mac,
          signo_name, solid_garage_mac, area_code);
      if (park_device != null) {
        returnData.setReturnData(errorcode_data, "该停车场的该出入口设备记录已经存在", "");
        return;
      }

      //入库操作
      Date date = new Date();
      park_device = new Park_device();
      park_device.setCamera(camera);
      park_device.setCamera_mac(camera_mac);
      park_device.setCtime(date);
      park_device.setIn_out(in_out);
      park_device.setIn_out_code(in_out_code);
      String md5str = DigestUtils.md5Hex(
          pi_id + in_out + in_out_code + camera_mac + signo_name + solid_garage_mac + area_code);
      park_device.setPd_md5(md5str);
      park_device.setPi_id(pi_id);
      park_device.setSigno_name(signo_name);
      park_device.setSolid_garage_mac(solid_garage_mac);
      park_device.setSolid_garage_sn(solid_garage_sn);
      park_device.setUtime(date);
      park_device.setArea_code(area_code);

      int id = park_deviceDao.insert(park_device);
      if (id < 1) {
        returnData.setReturnData(errorcode_data, "插入场地出入口设备关系失败", "");
        return;
      }
      park_device.setPd_id(id);
      returnData.setReturnData(errorcode_success, null, park_device);
      return;

    } catch (Exception e) {
      log.error("ParkinfoBiz record_park_device is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }

  }


  /**
   * 更新场地出入口设备对应关系信息
   */
  public void update_park_device(ReturnDataNew returnData, long pd_id, long pi_id,
      String in_out, String in_out_code, String camera,
      String camera_mac, String signo_name, String solid_garage_mac,
      String solid_garage_sn, String area_code) {
    // TODO Auto-generated method stub
    try {
      //首先判断是否已经进行了录入  如果数据库中没有该停车场的数据则不进行插入  否则不予处理
      //获取该场地的信息
      Park_device park_device = park_deviceDao.selectByKey(pd_id);
      if (park_device == null) {
        returnData.setReturnData(errorcode_data, "该停车场该出入口的设备不存在", "");
        return;
      }
      //更新操作
      Date date = new Date();
      park_device.setCamera(camera);
      park_device.setCamera_mac(camera_mac);
      //park_device.setCtime(date);   // by zzy 2017-07-17 更新时不需要修改创建时间
      park_device.setIn_out(in_out);
      park_device.setIn_out_code(in_out_code);
      String md5str = DigestUtils.md5Hex(
          pi_id + in_out + in_out_code + camera_mac + signo_name + solid_garage_mac + area_code);
      park_device.setPd_md5(md5str);
      park_device.setPi_id(pi_id);
      park_device.setSigno_name(signo_name);
      park_device.setSolid_garage_mac(solid_garage_mac);
      park_device.setSolid_garage_sn(solid_garage_sn);
      park_device.setUtime(date);
      park_device.setArea_code(area_code);

      int count = park_deviceDao.updateByKey(park_device);
      if (count < 1) {
        returnData.setReturnData(errorcode_data, "更新场地出入口设备关系失败", "");
        return;
      }
      returnData.setReturnData(errorcode_success, null, park_device);
      return;

    } catch (Exception e) {
      log.error("ParkinfoBiz update_park_device is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


  /**
   * 车辆入库出库记录
   */
  public void record_car_in_out(ReturnDataNew returnData, int dtype, long pi_id, long pd_id,
      String car_code,
      int is_enter, String in_out,
      String in_out_code, int car_type, int car_code_color, String area_code, int out_type,
      int is_local_month, String order_id, boolean is_sync, Long sync_time,
      String gov_num, boolean magnetic_force) {
    try {
      //首先判断是否已经进行了录入  如果数据库中没有该停车场的数据则不进行插入  否则不予处理
      //获取该场地的信息
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        returnData.setReturnData(errorcode_data, "该停车场不存在", "1");
        return;
      }
      //停车场状态（1开启0关闭）
// 2017/04/18 by 吴永平  pda 造数据时 关闭的停车场无法入库，临时关闭停车场状态检查
//      if (park_info.getPi_state() == 0 && is_enter == 0) {
//        returnData.setReturnData(errorcode_data, "该停车场暂时处于关闭状态", "2");
//        return;
//      }

      //用户ID 通过车牌号获取用户ID
      long ui_id = 0;
      String ui_nd = null;
      String ui_tel = null;
      User_carcode user_carcode = gateCarBiz.queryUserCarBycode(car_code);
      if (user_carcode != null) {
        ui_id = user_carcode.getUi_id();
        ui_nd = user_carcode.getUi_nd();
        ui_tel = user_carcode.getUi_tel();
      }
      //入库或者出库操作
      Date date;
      if (is_sync) {//异步上传依客户端时间
        date = new Date(sync_time);
      } else {
        date = new Date();
      }
      int type = 1;//type 1:临停 2：租赁
      Pay_month_park pay_month_park = null;
      if (is_local_month == 0 || out_type == 0) {
        //不是本地包月道闸停车
        /**
         * 检查是否是该停车场的租赁车辆
         */
        if (StringUtils.hasText(order_id)) {
          pay_month_park = payParkPB.selectOnePayMonthPark(order_id);
        } else {
          pay_month_park = payParkPB
              .queryRentOrder(pi_id, car_code, area_code, ui_id, date);
        }

        if (pay_month_park != null) {
          type = 2;
        }
      }

      //入库时 有绑定地磁编号时，地磁信号检查
//      if (StringUtils.hasText(gov_num) && is_enter == 0 && !is_sync) {
//        //查看对应地磁设备状态
//        Magnetic_device magnetic_device = getMySelfService()
//            .queryUniqueT(
//                "SELECT * FROM magnetic_device WHERE gov_num=? AND pi_id=? AND area_code=? LIMIT 1",
//                Magnetic_device.class, gov_num, pi_id, area_code);
//
//        if (magnetic_device == null) {
//          returnData.setReturnData(errorcode_data, "地磁设备编号" + gov_num + "错误，未找到相关车位", "4");
//          return;
//        }
//
//        if (magnetic_device.state != 2) {//如果地磁设备故障 不检查车位状态直接入库
//          if (magnetic_device.state != 1) {//车位没有车
//            if (magnetic_force) {//强制入库 设备错误数+1
//              int update = getMySelfService()
//                  .update("UPDATE magnetic_device SET state=1,fault_count=fault_count+1 WHERE id=?",
//                      magnetic_device.id);
//              if (update != 1) {
//                log.error("更新地磁设备错误数失败");
//              }
//            } else {
//              returnData.setReturnData(errorcode_data, "地磁设备未检测到车辆", "4");
//              return;
//            }
//          } else {//车位有车
////            if (checkOldOrder(returnData, pi_id, car_code, type, gov_num, magnetic_force,
////                magnetic_device)) {
////              return;
////            }
//          }
//        }
//      }

      Car_in_out car_in_out = new Car_in_out();
      if (is_sync) {//异步上传依客户端时间
        car_in_out.setIs_sync(1);
      }

      car_in_out.setCtime(date);
      car_in_out.setIn_out(in_out);
      car_in_out.setIn_out_code(in_out_code);
      car_in_out.setPi_id(pi_id);
      car_in_out.setUtime(date);
      car_in_out.setCar_code(car_code);
      car_in_out.setIs_enter(is_enter);
      //获取是该停车场的 出入口设备信息表的主键ID
      car_in_out.setPd_id(pd_id);
      car_in_out.setUi_id(ui_id);
      if (ui_nd != null) {
        car_in_out.setUi_nd(ui_nd);
      }
      if (ui_tel != null) {
        car_in_out.setUi_tel(ui_tel);
      }
      if (StringUtils.hasText(order_id)) {
        car_in_out.setOrder_id(order_id);
      }

      car_in_out.setUtime(date);
      car_in_out.setCar_type(car_type);
      car_in_out.setCar_code_color(car_code_color);
      //属于哪个省市区代码
      car_in_out.setArea_code(park_info.getArea_code());
      //入库/出库类型: (0:正常出入库 1：道闸本地临停出入库 2：道闸本地包月出入库   3：异常出入库)
      if (is_sync) {
        car_in_out.setOut_type(4);
      } else {
        car_in_out.setOut_type(out_type);
      }
      //是否是道闸本地包月车辆 0:不是 1：是
      car_in_out.setIs_local_month(is_local_month);

      //这里更新车辆出入库的数量变化
      if ((is_local_month == 0 || out_type == 0) && park_info.getPark_type() == 1) {
        parkInfoPB.upCarNum(park_info, is_enter, type);
      }

      //地磁停车场车位编号（政府部门统一分配）不为空时存入数据库
      if (null != gov_num) {
        car_in_out.setGov_num(gov_num);
      }

      int id = car_in_outDao.insert(car_in_out);
      if (id < 1) {
        returnData.setReturnData(errorcode_data, "车辆入库出库记录失败", "3");
        return;
      }
      //设置主键ID
      car_in_out.setCio_id(id);

      if (pay_month_park != null) {
        car_in_out.setOrder_id(pay_month_park.getMy_order());
        car_in_out.setIs_rent(1);
        car_in_out.setRent_remain_time(pay_month_park.getEnd_time().getTime() - date.getTime());
        if (is_enter == 0) {
          //入库
          pay_month_park.setGov_num(gov_num);
          pay_month_park.setMagnetic_state(1);
          pay_month_park.setArrive_time(date);
          int count = pay_month_parkDao.updateByKey(pay_month_park);
          if (count != 1) {
            //更新失败
            returnData.setReturnData(errorcode_data, "更新租赁分时间段包月当天入库时间失败", "2");
            return;
          }
        }
      }
////入库/出库类型: (0:正常出入库 1：道闸本地临停出入库  2：道闸本地包月出入库
// 3：异常出入库   4：道闸本地免费车出入库   5:预约车辆出入库  6:租赁车辆出入库)
      if ((is_local_month == 0 && (out_type == 0 || out_type == 5)) && type != 2) {
        //不是   道闸停车本地包月
        try {
          //事物处理  该车辆出入的细节
          gateCarTransaction
              .doCar_In_Out(dtype, order_id, car_in_out, park_info, returnData, gov_num);
          //更新出库记录---把订单号更新
          car_in_outDao.updateByKey(car_in_out);
        } catch (Exception e) {
          log.warn("空闲车位数量变更 错误", e);
          returnData.setReturnData(errorcode_systerm, "空闲车位数量变更 错误", "");
          return;
        }
      }
      //返回结果
      returnData.setReturnData(errorcode_success, null, car_in_out);
      return;

    } catch (Exception e) {
      log.error("ParkinfoBiz record_car_in_out is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

    /**
     *记录车辆出入库
     */
    public void recordCarInOut(ReturnDataNew returnData, int dtype, long pi_id, long pd_id,
                             String car_code,
                             int is_enter, String in_out,
                             String in_out_code, int car_type, int car_code_color, String area_code, int out_type,
                             int is_local_month, String order_id, boolean is_sync, long createTime,
                             String gov_num){
        try {
            //首先判断是否已经进行了录入  如果数据库中没有该停车场的数据则不进行插入  否则不予处理
            //获取该场地的信息
            Park_info park_info = returnParkInfo(pi_id, area_code);
            if (park_info == null) {
                returnData.setReturnData(errorcode_data, "该停车场不存在", "1");
                return;
            }

            //用户ID 通过车牌号获取用户ID
            long ui_id = 0;
            String ui_nd = null;
            String ui_tel = null;
            User_carcode user_carcode = gateCarBiz.queryUserCarBycode(car_code);
            if (user_carcode != null) {
                ui_id = user_carcode.getUi_id();
                ui_nd = user_carcode.getUi_nd();
                ui_tel = user_carcode.getUi_tel();
            }
            //入库或者出库操作
            Date date = new Date(createTime);

            int type = 1;//type 1:临停 2：租赁
            Pay_month_park pay_month_park = null;
            if (is_local_month == 0 || out_type == 0) {
                //不是本地包月道闸停车
                /**
                 * 检查是否是该停车场的租赁车辆
                 */
                if (StringUtils.hasText(order_id)) {
                    pay_month_park = payParkPB.selectOnePayMonthPark(order_id);
                } else {
                    pay_month_park = payParkPB.queryRentOrder(pi_id, car_code, area_code, ui_id, date);
                }

                if (pay_month_park != null) {
                    type = 2;
                }
            }

            Car_in_out car_in_out = new Car_in_out();
            if (is_sync) {//异步上传
                car_in_out.setIs_sync(1);
            }
            car_in_out.setCtime(date);
            car_in_out.setIn_out(in_out);
            car_in_out.setIn_out_code(in_out_code);
            car_in_out.setPi_id(pi_id);
            car_in_out.setUtime(date);
            car_in_out.setCar_code(car_code);
            car_in_out.setIs_enter(is_enter);
            //获取是该停车场的 出入口设备信息表的主键ID
            car_in_out.setPd_id(pd_id);
            car_in_out.setUi_id(ui_id);
            if (ui_nd != null) {
                car_in_out.setUi_nd(ui_nd);
            }
            if (ui_tel != null) {
                car_in_out.setUi_tel(ui_tel);
            }

            car_in_out.setUtime(date);
            car_in_out.setCar_type(car_type);
            car_in_out.setCar_code_color(car_code_color);
            //属于哪个省市区代码
            car_in_out.setArea_code(park_info.getArea_code());
            //入库/出库类型: (0:正常出入库 1：道闸本地临停出入库 2：道闸本地包月出入库   3：异常出入库)
            if (is_sync) {
                car_in_out.setOut_type(4);
            } else {
                car_in_out.setOut_type(out_type);
            }
            //是否是道闸本地包月车辆 0:不是 1：是
            car_in_out.setIs_local_month(is_local_month);

            //这里更新车辆出入库的数量变化
            if ((is_local_month == 0 || out_type == 0) && park_info.getPark_type() == 1) {
                parkInfoPB.upCarNum(park_info, is_enter, type);
            }

            //地磁停车场车位编号（政府部门统一分配）不为空时存入数据库
            if (null != gov_num) {
                car_in_out.setGov_num(gov_num);
            }

            int id = car_in_outDao.insert(car_in_out);
            if (id < 1) {
                returnData.setReturnData(errorcode_data, "车辆入库出库记录失败", "3");
                return;
            }
            //设置主键ID
            car_in_out.setCio_id(id);

            if (null !=pay_month_park) {
                car_in_out.setOrder_id(pay_month_park.getMy_order());
                car_in_out.setIs_rent(1);
                car_in_out.setRent_remain_time(pay_month_park.getEnd_time().getTime() - date.getTime());
                if (is_enter == 0) {
                    //入库
                    pay_month_park.setGov_num(gov_num);
                    pay_month_park.setMagnetic_state(1);
                    pay_month_park.setArrive_time(date);
                    int count = pay_month_parkDao.updateByKey(pay_month_park);
                    if (count != 1) {
                        //更新失败
                        returnData.setReturnData(errorcode_data, "更新租赁分时间段包月当天入库时间失败", "2");
                        return;
                    }
                }
            }

            if ((is_local_month == 0 && out_type == 0) && type != 2) {
                //不是   道闸停车本地包月
                try {
                    //事物处理  该车辆出入的细节
                    gateCarTransaction.doCar_In_Out(dtype, order_id, car_in_out, park_info, returnData, gov_num);
                    //更新出库记录---把订单号更新
                    car_in_outDao.updateByKey(car_in_out);
                } catch (Exception e) {
//                    log.error("空闲车位数量变更 错误", e);
                    returnData.setReturnData(errorcode_systerm, "空闲车位数量变更 错误", "");
                    return;
                }
            }
            //返回结果
            returnData.setReturnData(errorcode_success, null, car_in_out);
            return;

        } catch (Exception e) {
            log.error("ParkinfoBiz record_car_in_out is error", e);
            returnData.setReturnData(errorcode_systerm, "system is error", null);
        }
    }

  /**
   * 开闸
   */
  public void open_signo(ReturnDataNew returnData, Integer pi_id, String area_code,
      Integer is_cash, String order_id, boolean is_sync, Long sync_time,Integer is_open) {
    try {
      //首先判断是否已经进行了录入  如果数据库中没有该停车场的数据则不进行插入  否则不予处理
      //获取该场地的信息
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        returnData.setReturnData(errorcode_data, "该停车场不存在", "");
        return;
      }
      String sql = "SELECT *  FROM pay_park WHERE my_order = ? AND is_del=0 AND cancel_state != 1";
      Pay_park pay_park = getMySelfService().queryUniqueT(sql, Pay_park.class, order_id);
      if (pay_park == null) {
        returnData.setReturnData(errorcode_data, "没有该车辆的订单信息", "", "1");
        return;
      }

      Date date;
      if (is_sync) {//异步上传依客户端时间
        date = new Date(sync_time);
      } else {
        date = new Date();
      }

      if (pay_park.getPp_state() == 1) {
        //已经在线支付
        //更新开闸时间和开闸状态
        pay_park.setOpen_time(date);
        if (is_open == null){
          pay_park.setIs_open(1);//是否开闸 0:未开闸 1：已正常放行开闸 2: 道闸手动放行
        }else{
          pay_park.setIs_open(is_open);//是否开闸 0:未开闸 1：已正常放行开闸 2: 道闸手动放行
        }
        //pay_park.setIs_cash(is_cash);
        pay_park.setIs_over(1);//订单是否完成(0:没有完成1：完成)
      } else {
        //更新开闸时间和开闸状态
        pay_park.setOpen_time(date);
        if (is_open == null){
          pay_park.setIs_open(1);//是否开闸 0:未开闸 1：已正常放行开闸 2: 道闸手动放行
        }else{
          pay_park.setIs_open(is_open);//是否开闸 0:未开闸 1：已正常放行开闸 2: 道闸手动放行
        }
        pay_park.setIs_over(1);//订单是否完成(0:没有完成1：完成)
        pay_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
        //pay_park.setIs_cash(is_cash);//是否现金支付 0：在线支付  1：现金支付
      }
      int count = pay_parkDao.updateByKey(pay_park);
      if (count < 1) {
        //更新失败
        returnData.setReturnData(errorcode_data, "更新该车辆该次订单的开闸记录信息失败", "", "2");
        return;
      }

      //返回结果
      returnData.setReturnData(errorcode_success, "更新该车辆该次订单的开闸记录信息成功", "");
      return;

    } catch (Exception e) {
      log.error("ParkinfoBiz open_signo is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }

  }

  /**
   * 通过停车场名字查询出停车场信息
   */
  public Park_info queryByNameT(String name, String address_name, String area_code) {
    try {
      //精确查询
      String sql = "select *  from " + ReturnParkTableName(area_code)
          + " where pi_name=? and  address_name=?";
      return getMySelfService().queryUniqueT(sql, Park_info.class, name, address_name);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("queryByNameT is error" + e.getMessage(), e);
    }
    return null;
  }

  /**
   * 通过参数校验 检查该规则是否已经录入过
   */
  public Rental_charging_rule queryChargeRuleByMd5(long pi_id, int rcr_type, int car_type,
      int car_code_color, String area_code) {
    try {
      String md5str = MD5RentalChargingRrule(pi_id, rcr_type, car_type, car_code_color, area_code);
      String sql = "SELECT *  FROM  rental_charging_rule WHERE pi_id=? AND area_code=? AND  rcr_md5=?";
      Rental_charging_rule cr = getMySelfService()
          .queryUniqueT(sql, Rental_charging_rule.class, pi_id, area_code, md5str);
      return cr;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("queryChargeRuleByMd5 is error " + e.getMessage(), e);
    }
    return null;
  }

  public Park_device queryPark_deviceByMd5(long pi_id, String in_out, String in_out_code,
      String camera_mac, String signo_name, String solid_garage_mac, String area_code) {
    try {
      String md5str = DigestUtils.md5Hex(
          pi_id + in_out + in_out_code + camera_mac + signo_name + solid_garage_mac + area_code);
      String sql = "SELECT *  FROM  park_device WHERE pi_id=? AND area_code=? AND  pd_md5=?";
      Park_device cr = getMySelfService()
          .queryUniqueT(sql, Park_device.class, pi_id, area_code, md5str);
      return cr;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("queryPark_deviceByMd5 is error " + e.getMessage(), e);
    }
    return null;
  }


  /**
   * MD5规则限制
   */
  public String MD5RentalChargingRrule(long pi_id, int rcr_type, int car_type, int car_code_color,
      String area_code) {
    String md5str = DigestUtils.md5Hex(String.valueOf(pi_id)
        + String.valueOf(rcr_type) + String.valueOf(car_type) + String.valueOf(car_code_color));
    return md5str;
  }

  /**
   * 判断是否是默认规则
   */
  public boolean isDefault(Integer car_type, Integer car_code_color) {
    if (car_type == null || car_code_color == null) {
      return false;
    }
    return car_type == 1 && car_code_color == 1;
  }

  /**
   * 查询场地对应默认s规则数据
   *
   * @param rcr_type ：停车类型 0：普通车位停车 1：时间段包月停车
   * @param car_type 车牌类型 0：未知车牌:、1：蓝牌小汽车、2：: 黑牌小汽车、3：单排黄牌、4：双排黄牌、 5： 警车车牌、6：武警车牌、7：个性化车牌、8：单
   * 排军车牌、9：双排军车牌、10：使馆车牌、11： 香港进出中国大陆车牌、12：农用车牌、13：教 练车牌、14：澳门进出中国大陆车牌、15：双层
   * 武警车牌、16：武警总队车牌、17：双层武警总 队车牌
   */
  public Rental_charging_rule queryDefaultChargeRule(long pi_id, int rcr_type, int car_type,
      String area_code) {
    try {
      String sql = "SELECT *  FROM  rental_charging_rule WHERE pi_id=? AND area_code=? AND rcr_state=0 AND rcr_type=? AND car_code_color=1 AND car_type=? AND is_default=1 LIMIT 1";
      Rental_charging_rule cr = getMySelfService()
          .queryUniqueT(sql, Rental_charging_rule.class, pi_id, area_code, rcr_type, car_type);
      return cr;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("queryRentChargeRuleByPiId is error " + e.getMessage(), e);
    }
    return null;
  }

}
