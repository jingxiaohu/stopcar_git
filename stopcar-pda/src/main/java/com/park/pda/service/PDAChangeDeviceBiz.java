package com.park.pda.service;

import com.park.bean.Magnetic_device;
import com.park.bean.Magnetic_device_log;
import com.park.bean.Park_info;
import com.park.bean.Pay_month_park;
import com.park.bean.Pay_park;
import com.park.bean.ReturnDataNew;
import com.park.dao.Magnetic_device_logDao;
import com.park.dao.Park_carport_logDao;
import com.park.dao.Pay_month_parkDao;
import com.park.dao.Pay_parkDao;
import com.park.mvc.service.BaseBiz;
import com.park.pda.action.v1.magnetic.param.Param_change_device_state;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 修改设备状态
 *
 * @ClassName: ChangeDeviceBiz
 * @Description:TODO
 * @author: xxy
 * @date: 2017年5月18日 下午4:28:41
 */
@Service
public class PDAChangeDeviceBiz extends BaseBiz {

  @Autowired
  protected Magnetic_device_logDao magnetic_device_logDao;
  @Autowired
  protected Pay_month_parkDao pay_month_parkDao;
  @Autowired
  protected Pay_parkDao pay_parkDao;
  @Autowired
  protected Park_carport_logDao park_carport_logDao;


  /**
   * 更新设备状态，同时写入日志表 更新停车场车位数
   *
   * @Title: updateDevice
   * @Description: TODO
   * @return: void
   */
  public void updateDevice(ReturnDataNew returnData, Param_change_device_state param)
      throws Exception {
    Magnetic_device magnetic_device = null;
    Magnetic_device_log deviceLog = null;
    String android_dev_num = null;
    Pay_month_park pay_month_park = null;
    Pay_park pay_park = null;
    try {
      String parkInfoTableName = ReturnParkTableName(param.area_code);
      Park_info park_info = park_infoDao.selectByKey(param.getPi_id(), parkInfoTableName);
      if (park_info == null) {
        returnData.setReturnData(errorcode_param, "没有找到对应的停车场信息", "");
        return;
      }

      //根据设备根据没有结束的订单和设备
      magnetic_device = findDeviceByParam(param.getPi_id(), param.getArea_code(),
          param.getCar_dev_num());

      if (magnetic_device == null) {
        returnData.setReturnData(errorcode_param, "没有找到对应的设备信息", "");
        return;
      }

      //当设备中有车时，修改订单状态
      int state = magnetic_device.getState();
      String gav_num = magnetic_device.getGov_num();
      if (state == 1) {
        pay_park = findPay_park(param.getPi_id(), param.getArea_code(), gav_num);
        pay_month_park = findPay_month_park(param.getPi_id(), param.getArea_code(), gav_num);

      }
      if (pay_park != null && pay_park.magnetic_state == 1) {
        if (pay_park.getPp_state() == 1) {
          pay_park.setMagnetic_state(2);
          pay_park.setIs_over(1);
        } else {
          pay_park.setIs_over(3);
          pay_park.setMagnetic_state(3);
        }
        pay_park.setLeave_time(new Date());
        pay_parkDao.updateByKey(pay_park);
      }
      if (pay_month_park != null && pay_month_park.magnetic_state == 1) {
        if (pay_month_park.getPp_state() == 1) {
          pay_month_park.setMagnetic_state(2);
          pay_month_park.setIs_over(1);
        } else {
          pay_month_park.setMagnetic_state(3);
          pay_month_park.setIs_over(3);
        }
        pay_month_parkDao.updateByKey(pay_month_park);
      }

      android_dev_num = magnetic_device.getAndroid_dev_num();
      if (android_dev_num == null || "".equals(android_dev_num)) {
        magnetic_device.setAndroid_dev_num(param.getAndroid_dev_num());
      }
      magnetic_device.setState(param.getState());
      magnetic_device.setUtime(new Date());

      deviceLog = Magnetic_device_log
          .newMagnetic_device_log(0, magnetic_device.getPi_id(), magnetic_device.getArea_code(),
              magnetic_device.getGov_num(), magnetic_device.getCar_dev_num(),
              magnetic_device.getAndroid_dev_num(),
              magnetic_device.getState(), new Date(), "", param.getCar_port_total(),
              param.getCar_port_yet(), param.getCar_port_space());

      int UpdatedevicelogResult = magnetic_device_logDao.insert(deviceLog);
      if (UpdatedevicelogResult <= 0) {
        returnData.setReturnData(errorcode_data, "写入日志失败", null);
        return;
      }
      int updateDeviceResult = magnetic_deviceDao.updateByKey(magnetic_device);
      if (updateDeviceResult <= 0) {
        returnData.setReturnData(errorcode_data, "更新设备状态失败", null);
        return;
      }

      /**
       * 停车场车位数量流水记录 park_carport_log
       */
      //by jxh 2017-7-3   不记录地磁android板子收集的车位变化到 Park_carport_log 表中去
      /*Park_carport_log park_carport_log = new Park_carport_log();

			park_carport_log.setPi_id(param.pi_id);
	        park_carport_log.setArea_code(param.area_code);
	        park_carport_log.setCarport_total(param.getCar_port_total());
	        park_carport_log.setCarport_yet(param.getCar_port_yet());
	        park_carport_log.setCarport_space(param.getCar_port_space());
	        park_carport_log.setPark_type(park_info.getPark_type());
	        park_carport_log.setData_flag(2);
	        park_carport_log.setCtime(new Date(param.getCtime()));
	        park_carport_log.setStime(new Date());
	        park_carport_log.setNote("");

	        int ret = park_carport_logDao.insert(park_carport_log);
	        if(ret <= 0){
				returnData.setReturnData(errorcode_data, "停车场车位数量流水记录失败", null);
			    return;
			}*/

      returnData.setReturnData(errorcode_success, "修改成功", "");
    } catch (Exception e) {
      log.error("ChangeDeviceBiz ReturnreadMycoupon is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }


  }

  /**************************分离出来的方法*****************************/
  /**
   * 根据参数查询设备
   *
   * @param piId 停车场id
   * @param areaCode 省市县编号
   * @param car_dev_num 车位设备编码
   * @Title: findDeviceByParam
   * @Description: TODO
   * @return: Magnetic_device
   */
  public Magnetic_device findDeviceByParam(int piId, String areaCode, String car_dev_num) {
    String sql = "SELECT * FROM magnetic_device  WHERE pi_id =?  AND area_code =? AND car_dev_num =?  ORDER BY id DESC";

    try {
      Magnetic_device magnetic_device = getMySelfService()
          .queryUniqueT(sql, Magnetic_device.class, piId, areaCode, car_dev_num);
      return magnetic_device;
    } catch (Exception e) {
      //createTable(TABLENAME2);
      log.error("", e);
    }
    return null;
  }

  public Pay_park findPay_park(int piId, String areaCode, String gav_num) {
    String sql = "SELECT * FROM pay_park  WHERE pi_id = ?  AND area_code = ? AND gov_num = ?  ORDER BY id DESC LIMIT 1";

    try {
      Pay_park pay_park = getMySelfService()
          .queryUniqueT(sql, Pay_park.class, piId, areaCode, gav_num);
      return pay_park;
    } catch (Exception e) {
      //createTable(TABLENAME2);
      log.error("", e);
    }
    return null;
  }

  public Pay_month_park findPay_month_park(int piId, String areaCode, String gav_num) {
    String sql = "SELECT * FROM pay_month_park  WHERE pi_id = ?  AND area_code =  ? AND gov_num = ?  ORDER BY id DESC LIMIT 1";

    try {
      Pay_month_park pay_month_park = getMySelfService()
          .queryUniqueT(sql, Pay_month_park.class, piId, areaCode, gav_num);
      return pay_month_park;
    } catch (Exception e) {
      //createTable(TABLENAME2);
      log.error("", e);
    }
    return null;
  }


}
