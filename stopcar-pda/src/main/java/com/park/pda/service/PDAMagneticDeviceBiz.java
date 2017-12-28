
package com.park.pda.service;

import com.park.bean.Magnetic_device;
import com.park.bean.Park_info;
import com.park.bean.ReturnDataNew;
import com.park.mvc.service.BaseBiz;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 设备编号管理
 *
 * @author zyy
 */
@Service
public class PDAMagneticDeviceBiz extends BaseBiz {


  /**
   * 设备编号绑定
   */
  public void bindMagneticDevice(ReturnDataNew returnData, long pi_id, String area_code,
      String gov_num,
      String car_dev_num, String android_dev_num) {
    // TODO Auto-generated method stub
    try {
      //判断停车场是否存在
      Park_info parkInfo = returnParkInfo(pi_id, area_code);
      if(parkInfo == null ){
	  	  returnData.setReturnData(errorcode_param, "没有找到对应的停车场信息", "");
	  	  return;
	  }

	  Magnetic_device magnetic_device = null;
        //判断政府编码是否存在  by zyy 20170801 暂时不使用pi_id，使用地址编码前四位进行查询
      String gov_num_sql = "SELECT * FROM magnetic_device WHERE gov_num=? AND substring(area_code,1,4)=? and is_del=0";
      magnetic_device = getMySelfService().queryUniqueT(gov_num_sql, Magnetic_device.class,gov_num,area_code.substring(0,4));
      if (magnetic_device != null) {
          returnData.setReturnData(errorcode_data, "该政府编码已存在", "");
          return;
      }
      //判断设备编号是否存在
      String car_dev_num_sql = "SELECT * FROM magnetic_device WHERE car_dev_num=? AND pi_id=? AND area_code=? and is_del=0";
      magnetic_device = getMySelfService().queryUniqueT(car_dev_num_sql, Magnetic_device.class,car_dev_num, pi_id, area_code);
      if (magnetic_device != null) {
            returnData.setReturnData(errorcode_data, "该设备编号已存在", "");
            return;
      }

      magnetic_device = new Magnetic_device();
      Date date = new Date();

      magnetic_device.setPi_id(pi_id);
      magnetic_device.setArea_code(area_code);
      magnetic_device.setGov_num(gov_num);
      magnetic_device.setCar_dev_num(car_dev_num);
      magnetic_device.setAndroid_dev_num(android_dev_num);
      magnetic_device.setState(0);
      magnetic_device.setCtime(date);
      magnetic_device.setUtime(date);
      magnetic_device.setPtime(date);
      magnetic_device.setNote("");
      magnetic_device.setFault_count(0);

      int id = magnetic_deviceDao.insert(magnetic_device);
      if (id < 1) {
        returnData.setReturnData(errorcode_data, "设备编号绑定失败", null);
        return;
      }

      //返回数据
      returnData.setReturnData(errorcode_success, "设备编号绑定成功", magnetic_device);
      return;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("MagneticDeviceBiz bindMagneticDevice is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }
  }

  /**
   * 更新设备编号
   */
  public void update_magneticDevice(ReturnDataNew returnData, long pi_id, String area_code,
      String gov_num, String car_dev_num) {
    // TODO Auto-generated method stub
    try {
    	//判断停车场是否存在
        Park_info parkInfo = returnParkInfo(pi_id, area_code);
        if(parkInfo == null ){
			returnData.setReturnData(errorcode_param, "没有找到对应的停车场信息", "");
			return;
		}
        //判断设备是否存在
        String sql = "SELECT * FROM magnetic_device WHERE pi_id=? AND area_code=? AND gov_num=? and is_del=0";
        Magnetic_device magnetic_device = getMySelfService()
            .queryUniqueT(sql, Magnetic_device.class, pi_id, area_code, gov_num);
        if (magnetic_device == null) {
          returnData.setReturnData(errorcode_data, "设备不存在", "");
          return;
        }
        
        Date date = new Date();
        //修改设备编码
        magnetic_device.setCar_dev_num(car_dev_num);
        magnetic_device.setUtime(date);
        magnetic_device.setFault_count(0);
        
        int result = magnetic_deviceDao.updateByKey(magnetic_device);
        if (result < 1) {
          returnData.setReturnData(errorcode_data, "设备编号修改失败", null);
          return;
        }
        
        //返回数据
        returnData.setReturnData(errorcode_success, "设备编号修改成功", null);
        return;
    } catch (Exception e) {
        // TODO Auto-generated catch block
        log.error("MagneticDeviceBiz update_magneticDevice is error", e);
        returnData.setReturnData(errorcode_systerm, "system is error", null);
        return;
    }
  }

  /**
   * 读取绑定的设备列表
   */
  public void read_bindMagneticDevice(ReturnDataNew returnData, long pi_id, String area_code) {
    // TODO Auto-generated method stub
    try {
    	//判断停车场是否存在
        Park_info parkInfo = returnParkInfo(pi_id, area_code);
        if(parkInfo == null ){
			returnData.setReturnData(errorcode_param, "没有找到对应的停车场信息", "");
			return;
		}
        
        String sql = "SELECT * FROM magnetic_device WHERE pi_id=? and area_code=? and is_del=0";
        List<Magnetic_device> list = magnetic_deviceDao
            .executeQuery(sql, Magnetic_device.class, pi_id, area_code);
        if (null == list || list.size() == 0) {
            returnData.setReturnData(errorcode_success, "未获取到数据", list);
            return;
        }
        //返回数据
        returnData.setReturnData(errorcode_success, "读取绑定的设备编号成功", list);
        return;
    } catch (Exception e) {
        // TODO Auto-generated catch block
        log.error("MagneticDeviceBiz read_bindMagneticDevice is error", e);
        returnData.setReturnData(errorcode_systerm, "system is error", null);
        return;
    }
  }

  /**
	 * 绑定的设备编号逻辑删除
	 * @return
	 */
  public void delete_magneticDevice(ReturnDataNew returnData, Long pi_id, String area_code, String gov_num) {
  	// TODO Auto-generated method stub
  	try {
  		//判断停车场是否存在
        Park_info parkInfo = returnParkInfo(pi_id, area_code);
        if(parkInfo == null ){
			returnData.setReturnData(errorcode_param, "没有找到对应的停车场信息", "");
			return;
		}
        //判断设备是否存在
        String sql = "SELECT * FROM magnetic_device WHERE pi_id=? AND area_code=? AND gov_num=? and is_del=0";
        Magnetic_device magnetic_device = getMySelfService()
            .queryUniqueT(sql, Magnetic_device.class, pi_id, area_code, gov_num);
        if (magnetic_device == null) {
          returnData.setReturnData(errorcode_data, "设备不存在", "");
          return;
        }
        
        Date date = new Date();
        //修改设备编码
        magnetic_device.setIs_del(1);
        magnetic_device.setUtime(date);
        int result = magnetic_deviceDao.updateByKey(magnetic_device);
        if (result < 1) {
          returnData.setReturnData(errorcode_data, "设备编号删除失败", null);
          return;
        }
        
        //返回数据
        returnData.setReturnData(errorcode_success, "设备删除成功", null);
        return;
    } catch (Exception e) {
        // TODO Auto-generated catch block
        log.error("MagneticDeviceBiz delete_magneticDevice is error", e);
        returnData.setReturnData(errorcode_systerm, "system is error", null);
        return;
    }

  }
}
