package com.park.pda.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.park.bean.Hardware_version_log;
import com.park.bean.Park_hardware;
import com.park.bean.Park_info;
import com.park.bean.Pda_info;
import com.park.bean.Pda_punch_card;
import com.park.bean.ReturnDataNew;
import com.park.bean.Sms_validate;
import com.park.dao.Hardware_version_logDao;
import com.park.dao.Park_hardwareDao;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.ParkInfoPB;
import com.park.mvc.service.common.PayParkPB;
import com.park.transaction.CarTransaction;
import com.park.util.RequestUtil;

/**
 * 处理停车场信息的业务逻辑管理类
 *
 * @author jingxiaohu
 */
@Service
public class PDAParkinfoBiz extends BaseBiz {

  @Autowired
  private CarTransaction carTransaction;
  @Autowired
  protected PDACarBiz carBiz;
  @Autowired
  protected ParkInfoPB parkInfoPB;
  @Autowired
  protected PayParkPB payParkPB;
  @Autowired
  protected Hardware_version_logDao hardware_version_logDao;
  @Autowired
  protected Park_hardwareDao park_hardwareDao;

  /**
   * 通过露天停车场的帐号和密码 区域编码获取该停车场信息
   */
  public Park_info QueryByLoginLoginName(String area_code, String loginname, int park_type) {
    try {
      //精确查询
      String sql = "select *  from " + ReturnParkTableName(area_code)
          + " where park_type=? and loginname=?  limit 1";
      return getMySelfService().queryUniqueT(sql, Park_info.class, park_type, loginname);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("queryByNameT is error" + e.getMessage(), e);
    }
    return null;
  }

  /**
   * 用户重置PDA密码
   */
  public void change_pass_pda(ReturnDataNew returnData, int dtype, String tel,
      String verify_code, String verify_list, String vclass, String password,
      String repassword, String area_code, int park_type, String loginname) {
    // TODO Auto-generated method stub
    try {
      //获取该场地的信息
      Park_info park_info = QueryByLoginLoginName(area_code, loginname, park_type);
      if (park_info == null) {
        returnData.setReturnData(errorcode_data, "该停车场不存在", "");
        return;
      }

      //从什么设备发出的请求1-收音机2-手机APP 3-WEB
      //进行验证码的验证
      String sql = "SELECT v_code,v_time FROM sms_validate WHERE v_tel = ? AND v_list = ? AND v_class = ?  LIMIT 1";
      Sms_validate bsv = getMySelfService()
          .queryUniqueT(sql, Sms_validate.class, park_info.getLinkman_tel(), verify_list, vclass);
      if (bsv == null) {
        returnData.setReturnData(errorcode_data, "验证码错误", null);
        return;
      }
      //检查时间是否过期
      long time = Long.parseLong(bsv.getV_time() + "000");
      if (System.currentTimeMillis() - time > 6 * 60 * 1000) {
        returnData.setReturnData(errorcode_data, "验证码过期", null);
        return;
      }
      //初始化操作
      Date date = new Date();
      park_info.setUtime(date);
      park_info.setPassword(password);
      int count = park_infoDao.updateByKey(park_info, ReturnParkTableName(area_code));
      if (count > 0) {
        returnData.setReturnData(errorcode_success, "重置密码成功", park_info);
        return;
      } else {
        //更新失败
        returnData.setReturnData(errorcode_data, "重置密码失败", "");
        return;
      }

    } catch (Exception e) {
      log.error("ParkinfoBiz init_pda is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 露天停车场PDA 更新打卡记录对应的  清算状态
   */
  public void pda_clock_update(ReturnDataNew returnData, Double lng,
      Double lat, String loginname, Integer pi_id, String mac,
      Integer park_type, String area_code, Integer type, Integer ppc_id,
      String ppc_nd) {
    // TODO Auto-generated method stub
    try {
      Pda_punch_card pda_punch_card = pda_punch_cardDao.selectByKey(ppc_id);
      if (pda_punch_card == null) {
        returnData.setReturnData(errorcode_data, "该 打卡记录不存在", "");
        return;
      }
      Date date = new Date();
      pda_punch_card.setIs_clearing(1);//已清算
      pda_punch_card.setUtime(date);
      int count = pda_punch_cardDao.updateByKey(pda_punch_card);
      if (count != 1) {
        //更新失败
        returnData.setReturnData(errorcode_data, "更新清算失败", "");
        return;
      }

      returnData.setReturnData(errorcode_success, "更新清算成功", pda_punch_card);
      return;
    } catch (Exception e) {
      log.error("ParkinfoBiz pda_clock_update is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 露天停车场PDA 停车场员工上班下班打卡
   */
  public void pda_clock(ReturnDataNew returnData, Double lng, Double lat,
      String loginname, Integer pi_id, String mac, Integer park_type,
      String area_code, Integer type) {
    // TODO Auto-generated method stub
    try {
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        returnData.setReturnData(errorcode_data, "该停车场不存在", "");
        return;
      }
      Pda_punch_card pda_punch_card = new Pda_punch_card();
      Date date = new Date();
      pda_punch_card.setArea_code(area_code);
      pda_punch_card.setCtime(date);
      pda_punch_card.setIs_clearing(0);
      pda_punch_card.setLoginname(loginname);
      pda_punch_card.setMac(mac);
      pda_punch_card.setNd(returnUUID());
      String pda_permit_time = park_info.getPda_permit_time();
      if (!RequestUtil.checkObjectBlank(pda_permit_time)) {
        String[] pda_permit_times = pda_permit_time.split("-");
        pda_punch_card.setOntime(pda_permit_times[0]);
        pda_punch_card.setOfftime(pda_permit_times[1]);
      }
      pda_punch_card.setPi_id(pi_id);
      pda_punch_card.setPu_id(park_info.getPu_id());
      pda_punch_card.setPu_nd(park_info.getPu_nd());
      pda_punch_card.setType(type);//打卡类型0：上班打卡1：下班打卡
      pda_punch_card.setUtime(date);
      if (type == 0) {
        pda_punch_card.setNote("上班打卡");
      } else {
        pda_punch_card.setNote("下班打卡");
      }
      int id = pda_punch_cardDao.insert(pda_punch_card);
      if (id < 1) {
        //插入失败
        returnData.setReturnData(errorcode_data, "打卡失败", "");
        return;
      }

      returnData.setReturnData(errorcode_success, "打卡成功", pda_punch_card);
      return;
    } catch (Exception e) {
      log.error("ParkinfoBiz pda_clock is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }
//	String str_filed = "pi_id,area_code,pi_name,address_name,lng,lat,linkman_name,linkman_tel,copy_linkman_name,copy_linkman_tel,pi_phone,carport_space,carport_total,department,carport_yet,enter_num,exit_num,hlc_enter_num,hlc_exit_num,enter_camera_num,exit_camera_num,camera_info,ctime,utime,park_type,time_car_num,moth_car_num,expect_money,allow_revoke_time,allow_expect_time,timeout_info,rent_info,note";

  /**
   * 露天停车场PDA登录
   */
  public void pda_login(ReturnDataNew returnData, double lng, double lat,
      String loginname, String password, String mac, int vnum) {
    // TODO Auto-generated method stub
    try {
        //获取设备基本信息表
        /*String sql = "SELECT *  FROM pda_info WHERE loginname=? AND  password=? AND mac=? LIMIT 1";
        Pda_info pda_info = getMySelfService()
	          .queryUniqueT(sql, Pda_info.class, loginname, password, mac);*/
        Pda_info pda_info = get_pda_info(loginname, password, mac);
      
        if (pda_info == null) {
            returnData.setReturnData(errorcode_data, "用户名或者密码不正确", "");
            return;
        }
        if (pda_info.getPi_id() == 0 || !StringUtils.hasText(pda_info.getArea_code())) {
            returnData.setReturnData(errorcode_data, "还没有绑定停车场", "");
            return;
        }
        Park_info park_info = returnParkInfo(pda_info.getPi_id(), pda_info.getArea_code());
        if (park_info == null) {
            returnData.setReturnData(errorcode_data, "还没有绑定停车场", "");
            return;
        }
        
        //设置PDA设备的当前版本号
        if (vnum > pda_info.getVnum()) {
            pda_info.setVnum(vnum);
            //更新版本号时刷新修改时间
            pda_info.setUtime(new Date());
            int updateByKey = pda_infoDao.updateByKey(pda_info);
            if(updateByKey < 1){
            	log.error("PDA登录-设置PDA设备的当前版本号 失败");
            }
        }
      
      //记录硬件设备版本升级日志（mac、pi_id、area_code、版本号须不一致）
      int hardware_version_log_ret = recode_hardware_version_log(loginname, password, mac,vnum);
      if(hardware_version_log_ret < 1){
		  log.info("PDA登录-记录硬件设备版本升级日志 失败");
	  }
      
      //记录停车场硬件设备信息
      int hardware_info_ret = recode_hardware_info(loginname, password, mac,vnum);
      if(hardware_info_ret < 1){
		  log.info("PDA登录-记录停车场硬件设备信息 失败");
	  }
      
      //这里需要对 该停车场是否绑定了 商户  和  该设备是否属于该停车场  要做验证

      Map<String, Object> map = new HashMap<String, Object>();
      map.put("pda_info", pda_info);
      map.put("park_info", park_info);

      returnData.setReturnData(errorcode_success, "登录成功", map);
      return;
    } catch (Throwable e) {
      log.error("ParkinfoBiz pda_login is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
  }

  /**
   * 占道停车场PDA MAC的初始化提交
   */
  public void init_pda_v2(ReturnDataNew returnData, String mac) {
    // TODO Auto-generated method stub
    try {
      //获取设备基本信息 -- 检查该PDA设备是否之前已经提交过MAC地址
      String sql = "SELECT *  FROM pda_info WHERE mac=? LIMIT 1";
      Pda_info pda_info = getMySelfService().queryUniqueT(sql, Pda_info.class, mac);
      if (pda_info != null) {
        returnData.setReturnData(errorcode_data, "已经初始化过了", "", "1");
        return;
      }
      /**
       * 没有提交过MAC地址 则进行分配帐号和密码 设备串号
       */
      Date date = new Date();
      pda_info = new Pda_info();
      pda_info.setMac(mac);
      pda_info.setIs_initialize(1);
      pda_info.setLoginname(sf_yyyyMMddHHmmss.format(date));
      pda_info.setPassword(RequestUtil.MD5("123456"));
      pda_info.setPda_sn(returnUUID());
      pda_info.setCtime(date);
      pda_info.setUtime(date);
      //20170703 zhuyangyong pda登录如果toket为空设置一个32位token
      if(pda_info.getToken()==null || pda_info.getToken()==""){
    	  pda_info.setToken(RequestUtil.getUUID());
      }

      int id = pda_infoDao.insert(pda_info);
      if (id > 0) {
        returnData.setReturnData(errorcode_success, "初始化成功", pda_info);
        return;
      } else {
        //入库失败
        returnData.setReturnData(errorcode_data, "初始化失败", "");
        return;
      }
    } catch (Exception e) {
      log.error("ParkinfoBiz init_pda_v2 is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 露天停车场PDA帐号信息和经纬度 MAC 的初始化
   */

  public void init_pda(ReturnDataNew returnData, double lng, double lat,
      String loginname, String password, String mac, int park_type,
      String area_code) {
    // TODO Auto-generated method stub
    try {
      String tablename = ReturnParkTableName(area_code);
      if (!isExistParkInfo(area_code, tablename)) {
        //不存在该表
        returnData.setReturnData(errorcode_data, "地址编码错误", "");
        return;
      }
      //获取该场地的信息
      Park_info park_info = QueryByLoginName(area_code, loginname, password, park_type);
      if (park_info == null) {
        returnData.setReturnData(errorcode_data, "该停车场不存在", "");
        return;
      }
      //初始化操作
      Date date = new Date();
      if (lat > 0) {
        if (park_info.getLat() > 0) {

        } else {
          park_info.setLat(lat);
        }

      }
      if (lng > 0) {
        if (park_info.getLng() > 0) {

        } else {
          park_info.setLng(lng);
        }

      }
      //设置MAC
      String macs = park_info.getMac();
      if (RequestUtil.checkObjectBlank(macs)) {
        //该停车场MAC为空  则进行第一次初始化
        //设置MAC绑定之前  先检查该MAC是否已经被其它停车场绑定了
        Park_info park_info_isexist = CheckMacBind(mac, area_code);
        if (park_info_isexist != null) {
          log.error(
              "该设备已经被其它停车场绑定过了tablename=" + tablename + " pi_id=" + park_info_isexist.getPi_id()
                  + " mac=" + mac);
          returnData.setReturnData(errorcode_data, "该设备已经被其它停车场绑定过了", "", "1");
          return;
        }
        park_info.setMac(mac);
      } else {
        if (!macs.equalsIgnoreCase(mac)) {
          returnData.setReturnData(errorcode_data, "PDA 物理地址不匹配", "", "2");
          return;
        }
      }
      park_info.setUtime(date);
      int count = park_infoDao.updateByKey(park_info, ReturnParkTableName(area_code));
      if (count > 0) {
        returnData.setReturnData(errorcode_success, "初始化成功", park_info);
        return;
      } else {
        //更新失败
        returnData.setReturnData(errorcode_data, "初始化失败", "");
        return;
      }

    } catch (Exception e) {
      log.error("ParkinfoBiz init_pda is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 通过露天停车场的帐号和密码 区域编码获取该停车场信息
   */
  public Park_info QueryByLoginName(String area_code, String loginname, String password,
      int park_type) {
    try {
      //精确查询
      String sql = "select *  from " + ReturnParkTableName(area_code)
          + " where park_type=? and loginname=? and  password=? limit 1";
      return getMySelfService().queryUniqueT(sql, Park_info.class, park_type, loginname, password);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("queryByNameT is error" + e.getMessage(), e);
    }
    return null;
  }

  /**
   * 检查MAC是否被其它停车场绑定
   */
  public Park_info CheckMacBind(String pda_mac, String area_code) {
    try {
      //精确查询
      String sql = "select *  from " + ReturnParkTableName(area_code)
          + " where park_type=1 and mac=? limit 1";
      return getMySelfService().queryUniqueT(sql, Park_info.class, pda_mac);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("CheckMacBind is error" + e.getMessage(), e);
    }
    return null;
  }

  /**
   * 根据参数查询pda_info
   * @param loginname 登录用户名
   * @param password 登录密码
   * @param mac 登录mac
   * @return
   */
  public Pda_info get_pda_info(String loginname, String password, String mac){
  	  String sql = "SELECT *  FROM pda_info WHERE loginname=? AND  password=? AND mac=? LIMIT 1";
      try {
  	  	  return getMySelfService().queryUniqueT(sql, Pda_info.class, loginname, password, mac);
  	  } catch (Exception e) {
            log.error("PDAParkinfoBiz get_pda_info is error", e);
  	  }
  	  return null;
  }
  
  /**
   * 记录硬件设备版本升级日志
   * @param loginname 登录用户名
   * @param password 登录密码
   * @param mac 登录mac
   * @param vnum 版本号
   * @return
   */
  public int recode_hardware_version_log(String loginname, String password,String mac, int vnum){
	  Pda_info pda_info = get_pda_info(loginname, password, mac);
	  String hardwareLogSql = "select * from hardware_version_log where mac=? and pi_id=? and area_code=? and vnum=? limit 1";
	  Date date = new Date();
	  try {
		  //记录硬件设备版本升级日志（mac、pi_id、area_code、版本号须不一致）
	      Hardware_version_log hardware_version_log = hardware_version_logDao.queryUniqueT(hardwareLogSql, Hardware_version_log.class, mac,pda_info.getPi_id(),pda_info.getArea_code(),vnum);
	      String device_name = "普通占到停车PDA";
	      if(hardware_version_log == null){
	    	  hardware_version_log = new Hardware_version_log();
	    	  hardware_version_log.setDevice_id(pda_info.getPda_id());
	    	  hardware_version_log.setMac(mac);
	    	  hardware_version_log.setPda_sn(pda_info.getPda_sn());
	    	  hardware_version_log.setDevice_name(device_name);
	    	  hardware_version_log.setDevice_type(1);	//设备类型( 0:未指定 1：PDA  2：地磁android板)
	    	  hardware_version_log.setPi_id(pda_info.getPi_id());
	    	  hardware_version_log.setArea_code(pda_info.getArea_code());
	    	  hardware_version_log.setPi_name(pda_info.getPi_name());
	    	  hardware_version_log.setCtime(date);
	    	  hardware_version_log.setUtime(date);
	    	  hardware_version_log.setVnum(vnum);
	    	  
	    	  return hardware_version_logDao.insert(hardware_version_log);
	      }
	  } catch (Exception e) {
		  log.error("PDAParkinfoBiz recode_hardware_version_log is error", e);
	  }
      return 0;	  
   }
  
  /**
   * 记录停车场硬件设备信息
   * @param loginname 登录用户名
   * @param password 登录密码
   * @param mac 登录mac
   * @param vnum 版本号
   * @return
   */
  public int recode_hardware_info(String loginname, String password,String mac, int vnum){
	  Pda_info pda_info = get_pda_info(loginname, password, mac);
	  String hardwareSql = "select * from park_hardware where pi_id=? and area_code=? and ph_mac=? limit 1";
	  Date date = new Date();
	  try {
		  //记录停车场硬件设备信息
	      Park_hardware park_hardware = park_hardwareDao.queryUniqueT(hardwareSql, Park_hardware.class, pda_info.getPi_id(),pda_info.getArea_code(),mac);
	      if(park_hardware == null){
	    	  park_hardware = new Park_hardware();
	    	  park_hardware.setPi_id(pda_info.getPi_id());
	    	  park_hardware.setArea_code(pda_info.getArea_code());
	    	  park_hardware.setType(1);	//硬件设备类型(0:未知 1：PDA设备 2：地磁设备)
	    	  park_hardware.setPh_mac(mac);
	    	  park_hardware.setPh_licence(pda_info.getPda_sn());
	    	  park_hardware.setPark_type(1); //停车场类型（0：道闸 1：占道  2：立体车库
	    	  park_hardware.setCtime(date);
	    	  park_hardware.setUtime(date);
	    	  park_hardware.setPh_loginname(loginname);
	    	  park_hardware.setPh_password(password);
	    	  park_hardware.setPh_state(0);	//硬件设备状态（0：正常 1：异常)
	    	  park_hardware.setVersioncode(vnum);
	    	  
	    	  return park_hardwareDao.insert(park_hardware);
	      }
	  } catch (Exception e) {
		  log.error("PDAParkinfoBiz recode_hardware_info is error", e);
	  }
	  return 0;	  
   }
}
