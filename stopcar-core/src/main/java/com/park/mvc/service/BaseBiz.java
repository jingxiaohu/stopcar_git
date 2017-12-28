package com.park.mvc.service;

import com.park.bean.Park_info;
import com.park.bean.User_info;
import com.park.dao.*;
import com.park.mq.RabbitPublisher;
import com.park.service.MySelfService;
import com.park.service.UserRedisService;
import com.park.task.AsyncJpushTask;
import com.park.task.AsyncOrderTask;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Title:action
 * Description:处理memcached中的一些数据的读取
 * Copyright: Copyright (c) 2014
 * Company: rumtel Technology Chengdu Co. Ltd.
 *
 * @author 敬小虎
 * @version 1.0 2014-4-14
 * @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly=true) :只读事务
 * @Transactional(propagation = Propagation.NOT_SUPPORTED) : 不进行事务执行
 */
@Service
public class BaseBiz {

  //@Resource(name="CacheManager")
  //private CacheManager cacheManager;
  @Resource(name = "MySelfService")
  private MySelfService mySelfService;
  @Resource(name = "userRedisService")
  private UserRedisService userRedisService;

  //激光推送
  @Autowired
  protected AsyncJpushTask asyncJpushTask;
  @Autowired
  protected AsyncOrderTask asyncOrderTask;
  //MQ推送
  @Autowired
  protected RabbitPublisher rabbitPublisher;

  public Map<String, String> map_source = null;


  public Map<String, String> getMap_source() {
    if (map_source == null) {
      map_source = new HashMap<String, String>();
      map_source.put("qingting", "1");
      map_source.put("kuke", "2");
      map_source.put("ifeng", "3");
    }
    return map_source;
  }

  protected Logger log = LoggerFactory.getLogger(getClass());
  //系统错误代码
  public String errorcode_success = "0";
  public String errorcode_systerm = "1000";
  public String errorcode_param = "1001";
  public String errorcode_data = "1002";

  protected SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  protected SimpleDateFormat sf_yyyy_mm_dd = new SimpleDateFormat("yyyy-MM-dd");
  protected SimpleDateFormat sf_yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
  /******************DAO定义************************/
  @Autowired
  protected  DaoFactory daoFactory;

  @Autowired
  protected User_infoDao user_infoDao;
  @Autowired
  protected User_login_logDao user_login_logDao;
  @Autowired
  protected Sms_validateDao sms_validateDao;
  @Autowired
  protected Sms_runningDao sms_runningDao;
  @Autowired
  protected Park_infoDao park_infoDao;
  @Autowired
  protected Rental_charging_ruleDao rental_charging_ruleDao;
  @Autowired
  protected Park_deviceDao park_deviceDao;
  @Autowired
  protected Car_in_outDao car_in_outDao;
  @Autowired
  protected Fault_recordDao fault_recordDao;
  @Autowired
  protected User_carcodeDao user_carcodeDao;
  @Autowired
  protected User_feedbackDao user_feedbackDao;
  @Autowired
  protected User_moneybackDao user_moneybackDao;
  @Autowired
  protected Pay_parkDao pay_parkDao;
  @Autowired
  protected Pay_month_parkDao pay_month_parkDao;
  @Autowired
  protected Park_heartbeatDao park_heartbeatDao;
  @Autowired
  protected User_vc_actDao user_vc_actDao;
  @Autowired
  protected User_park_couponDao user_park_couponDao;
  @Autowired
  protected User_payDao user_payDao;
  @Autowired
  protected Park_userinfoDao park_userinfoDao;

  @Autowired
  protected Lock_money_logDao lock_money_logDao;
  @Autowired
  protected Pda_punch_cardDao pda_punch_cardDao;
  @Autowired
  protected Random_coupon_logDao random_coupon_logDao;
  @Autowired
  protected Activity_infoDao activity_infoDao;
  @Autowired
  protected Activity_eventDao activity_eventDao;
  @Autowired
  protected Pda_infoDao pda_infoDao;
  @Autowired
  protected Etc_userinfoDao etc_userinfoDao;
  @Autowired
  protected Etc_userpay_recordDao etc_userpay_recordDao;

  @Autowired
  protected Magnetic_deviceDao magnetic_deviceDao;

  @Autowired
  protected Order_abnormal_logDao order_abnormal_logDao;
  /*****************************************/
  public String ERROR_RESP = "HTTP_GET_ERROR";

  public MySelfService getMySelfService() {
    return mySelfService;
  }
  /*public CacheManager getCacheManager() {
    return cacheManager;
	}*/


  /**
   * @return the userRedisService
   */
  public UserRedisService getUserRedisService() {
    return userRedisService;
  }


  /***
   * 处理获取单个类
   * @param <T>
   * @param <T>
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public <T> T returnListOne(String sql, Class classaa) throws Exception {
    List<T> list = getMySelfService().executeQuery(sql, classaa);
    if (list != null && list.size() > 0) {
      return list.get(0);
    }
    return null;
  }
  /*************************处理公用的业务方法***********************************/
  /**
   * 记录手机设备信息
   * @Title: insertClientDevice
   * @Description: TODO(这里用一句话描述这个方法的作用)
   * @param @param client_device
   * @param @return    设定文件
   * @return long    返回类型
   * @throws
   */
/*	protected long insertClientDevice(Client_device client_device){
    if(client_device == null){
			return 0;
		}
		long xx = 0;
		try {
			String sql = "select * from client_device where imei=? limit 1";
			Client_device client_device2 = getMySelfService().queryUniqueT(sql, Client_device.class, client_device.getImei());
			if(client_device2 == null){
				xx = clientDeviceDao.insert(client_device);
			}else{
				xx = client_device2.getCd_id();
			}
			
		} catch (Exception e) {
			log.error("xx = clientDeviceDao.insert(client_device) is error", e); 
		}
		
		return xx < 1 ?0:xx;
	}*/

  /******************定义 添、删、查、改***公用方法*********************/


  /**
   * 生成19位UUID
   */
  public String returnUUID() {
//		return RequestUtil.getUUID().substring(13)+System.currentTimeMillis();
    return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + RandomStringUtils
        .random(5, false, true);
  }

  /**
   * 生成32位UUID
   */
  public String returnUserRegisterUUID() {
    return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + RandomStringUtils
        .random(18, true, true);
  }
  
  
  /**
   * 生成订单订单
   * 生成规则如下：
   * 吾泊订单长度定为32位
   * 订单构成：区域地址编码6位+产品标号1位+停车场主键ID 8位（其中不足8位的前面填充零）+订单类型 1位+年月日时分秒14位+2位随机数（数字+24位字母都可以）
   * 其中产品标号：0 道闸 1 PDA  2 地磁PDA  3 mepos道闸
   * 订单类型：0 临停订单 1 本地包月订单 2 本地免费订单 3  预约订单 4 租赁订单 （其中预约订单和租赁订单生成都由服务器端生成）
   * 订单例子：510116000000001020170531105720hk
   *
   * @param area_code 区域地址编码6位
   * @param product_type 产品标号1位：0 道闸 1 PDA  2 地磁PDA  3 mepos道闸
   * @param park_info_id 停车场主键ID 8位（其中不足8位的前面填充零）
   * @param order_type 订单类型：0 临停订单 1 本地包月订单 2 本地免费订单 3  预约订单 4 租赁订单 （其中预约订单和租赁订单生成都由服务器端生成）
   * @return 订单号
   */
  public String returnNewOrderId(String area_code, int product_type, long park_info_id,
      int order_type) {
    StringBuilder order_id = new StringBuilder();
    order_id.append(area_code).append(product_type);
    String pi_id = String.valueOf(park_info_id);
    if (pi_id.length() > 8) {
      pi_id = pi_id.substring(0, 8);
    } else {
      char[] chars = new char[8 - pi_id.length()];
      Arrays.fill(chars, '0');
      pi_id = new String(chars) + pi_id;
    }
    order_id.append(pi_id).append(order_type)
        .append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
        .append(RandomStringUtils
            .random(2, "abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789"))
    ;
    return order_id.toString();
//    return returnUUID();
  }

  private final SimpleDateFormat uuid16dateFormat = new SimpleDateFormat("yyyy");

  /**
   * @return 生成16位UUID
   */
  public String return16UUID() {
    return uuid16dateFormat.format(new Date()) + RandomStringUtils.randomNumeric(12);
  }

  /**
   * 请求头集合
   */
  @SuppressWarnings("deprecation")
  public String doGet(String url, Map<String, String> header, NameValuePair[] params) {
    HttpClient hc = new HttpClient();
    GetMethod get = null;
    try {
      hc.setConnectionTimeout(20 * 1000);
      hc.setTimeout(20 * 1000);
      get = new GetMethod(url);
      if (params != null) {
        get.setQueryString(params);
      }
      get.setRequestHeader("Connection", "close");
      get.addRequestHeader("Content-Type", "application/json;charset=utf-8");
      get.addRequestHeader("Accept",
          "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
      get.addRequestHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
      if (header != null) {
        for (String h : header.keySet()) {
          get.addRequestHeader(h, header.get(h));
        }
      }
      hc.executeMethod(get);
      if (get.getStatusCode() == 200) {
        InputStream resStream = get.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(resStream));
        StringBuffer resBuffer = new StringBuffer();
        String resTemp = "";
        while ((resTemp = br.readLine()) != null) {
          resBuffer.append(resTemp);
        }
        String response = resBuffer.toString();
        //return get.getResponseBodyAsString();
        return response;
      } else {
        log.error(url + " req error StatusCode:" + get.getStatusCode());
      }
    } catch (Exception e) {
      log.error("doGet error", e);
      return ERROR_RESP;
    } finally {
      if (get != null) {
        get.releaseConnection();
        //释放链接
        hc.getHttpConnectionManager().closeIdleConnections(0);
      }
    }
    return ERROR_RESP;
  }

  /**
   * 区域代码转化成 省级市  area_code "510112";//省市区区域代码  四川省 成都市 龙泉驿区
   */
  public String ReturnParkTableName(String area_code) {
    if (area_code == null) {
      return null;
    }
    return park_infoDao.getTABLENAME() + area_code.substring(0, 2) + "0000";
  }

  /**
   * 检查停车场表是否存在
   *
   * @return true:存在 false：不存在
   */
  public boolean isExistParkInfo(String area_code, String tablename) {
    try {
      String sql = "SELECT * FROM information_schema.TABLES WHERE table_name =:tablename";
      Map<String, Object> paramMap = new HashMap<String, Object>();
      paramMap.put("tablename", tablename);
      List<Map<String, Object>> list = getMySelfService().queryForList(sql, paramMap);
      if (list != null && list.size() == 1) {
        return true;
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("isExistParkInfo is error", e);
    }
    return false;
  }

  /**
   * 获取停车场基本信息
   */
  public Park_info returnParkInfo(long pi_id, String area_code) {
    try {
     /* String tablename = ReturnParkTableName(area_code);
      if (!isExistParkInfo(area_code, tablename)) {
        //不存在该表
        return null;
      }*/
      String tablename = "park_info510000";
      /*if (!isExistParkInfo(area_code, tablename)) {
        //不存在该表
        return null;
      }*/
      String sql = "select * from " + tablename + "  where pi_id=? limit 1";
      return getMySelfService().queryUniqueT(sql, Park_info.class, pi_id);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("returnParkInfo is error", e);
    }
    return null;
  }

  /*****************上生活需要的工具方法**********************/
  /**
   * 分页处理
   */
  public int returnPage(int totalsize, int pagesize) {
    if (totalsize % pagesize == 0) {
      return totalsize / pagesize;
    } else {
      return totalsize / pagesize + 1;
    }
  }

  /**
   * 预约的时候 ：：用户余额不足判定
   *
   * @return true:余额不足  false:余额足够
   */
  public boolean isNotSureMoney(User_info user_info, long money) {
    if (user_info.getUi_vc() - user_info.getLock_expect_money() - money < 0) {
      return true;
    }
    return false;
  }

  /**
   * 租赁的时候 ：：用户余额不足判定
   *
   * @return true:余额不足  false:余额足够
   */
  public boolean isNotSureRentMoney(User_info user_info, long money) {
    if (user_info.getUi_vc() - user_info.getLock_rent_money() - money < 0) {
      return true;
    }
    return false;
  }



  /**
   * 分时间段包月计算法则
   *
   * @param permit_time 17:00-08:30 或者  08:00-17:00  准入时间段
   * @param starttime : 起始时间
   * @param endtime ： 到期时间
   * @param entertime : 入库时间
   * @param outtime ： 结算时间
   */
  public long calculateMonthPay(String permit_time, Date starttime, Date endtime, Date entertime,
      Date outtime) {
    //临停时间
    long temp_time = 0;
    //一天中 除开允许的 时间段之外的 临停时间 单位分钟
    long diff_minute = onedayPay(permit_time);
    // 17:00-08:30 或者  08:00-17:00  准入时间段
    long diff2 = outtime.getTime() - entertime.getTime();
    temp_time =
        (diff2 / (24 * 3600 * 1000)) * diff_minute + one_day(permit_time, entertime, outtime);

    //分以下几种情况
    //第一种：用户入库后  再开始的包月
    if (starttime.getTime() > entertime.getTime()) {
      temp_time += (starttime.getTime() - entertime.getTime()) / (60 * 1000);
    }
    //第二种：用户出库的时候 包月已经逾期
    if (endtime.getTime() < outtime.getTime()) {
      temp_time += (outtime.getTime() - endtime.getTime()) / (60 * 1000);
    }
    return temp_time * 60 * 1000;
  }


  /**
   * 分时间段包月计算  一天的临停时间
   *
   * @param permit_time 17:00-08:30 或者  08:00-17:00  准入时间段
   */
  public long onedayPay(String permit_time) {
    //一天中 除开允许的 时间段之外的 临停时间 单位分钟
    int diff_minute = 0;
    // 17:00-08:30 或者  08:00-17:00  准入时间段
    String[] time_array = permit_time.split("-");
    if (time_array != null && time_array.length == 2) {
      String[] start_array = time_array[0].split(":");
      if (start_array == null || start_array.length < 2) {
        return 0;
      }
      String[] end_array = time_array[1].split(":");
      if (end_array == null || end_array.length < 2) {
        return 0;
      }
      int start_hour = Integer.parseInt(start_array[0]);
      int start_minute = Integer.parseInt(start_array[1]);

      int end_hour = Integer.parseInt(end_array[0]);
      int end_minute = Integer.parseInt(end_array[1]);

      if (start_hour <= 24 && start_hour > end_hour) {
        int diff_hour = start_hour - end_hour;
        diff_minute = diff_hour * 60 + (start_minute - end_minute);
      } else {
        int diff_hour = (end_hour - start_hour);
        diff_minute = 24 * 60 - (diff_hour * 60 + (end_minute - start_minute));
      }
    }
    return diff_minute;
  }

  /**
   * 判断时间是否在包月区间里面
   *
   * @return ture:在扣款区间  false 不在扣款区间
   */
  public boolean is_between(String permit_time, Date time) {
    // 17:00-08:30 或者  08:00-17:00  准入时间段
    String[] time_array = permit_time.split("-");
    if (time_array != null && time_array.length == 2) {
      String[] start_array = time_array[0].split(":");
      if (start_array == null || start_array.length < 2) {
        return false;
      }
      String[] end_array = time_array[1].split(":");
      if (end_array == null || end_array.length < 2) {
        return false;
      }
      int start_hour = Integer.parseInt(start_array[0]);
      int start_minute = Integer.parseInt(start_array[1]);

      int end_hour = Integer.parseInt(end_array[0]);
      int end_minute = Integer.parseInt(end_array[1]);

      Calendar cl = Calendar.getInstance(Locale.CHINA);
      cl.setTime(time);
      int time_hour = cl.get(Calendar.HOUR_OF_DAY);
      int time_minute = cl.get(Calendar.MINUTE);

      if (start_hour > end_hour) {
        //17:00-08:30
        if (time_hour * 60 + time_minute >= start_hour * 60 + start_minute
            || time_hour * 60 + time_minute <= end_hour * 60 + end_minute) {
          return false;
        }
      } else {
        //08:00-17:00
        if (time_hour * 60 + time_minute >= start_hour * 60 + start_minute
            && time_hour * 60 + time_minute <= end_hour * 60 + end_minute) {
          return false;
        }
      }

    }

    return true;
  }

  /**
   * 计算一天里面的时间
   */
  public long one_day(String permit_time, Date entertime, Date outtime) {
    //临停时间
    long temp_time = 0;
    //一天中 除开允许的 时间段之外的 临停时间 单位分钟
    int diff_minute_enter = 0;
    int diff_minute_out = 0;
    // 17:00-08:30 或者  08:00-17:00  准入时间段
    String[] time_array = permit_time.split("-");
    if (time_array != null && time_array.length == 2) {
      String[] start_array = time_array[0].split(":");
      if (start_array == null || start_array.length < 2) {
        return 0;
      }
      String[] end_array = time_array[1].split(":");
      if (end_array == null || end_array.length < 2) {
        return 0;
      }
      int start_hour = Integer.parseInt(start_array[0]);
      int start_minute = Integer.parseInt(start_array[1]);

      int end_hour = Integer.parseInt(end_array[0]);
      int end_minute = Integer.parseInt(end_array[1]);

      Calendar cl = Calendar.getInstance(Locale.CHINA);
      cl.setTime(entertime);
      int enter_hour = cl.get(Calendar.HOUR_OF_DAY);
      int enter_minute = cl.get(Calendar.MINUTE);

      Calendar cl2 = Calendar.getInstance(Locale.CHINA);
      cl2.setTime(outtime);
      int out_hour = cl2.get(Calendar.HOUR_OF_DAY);
      int out_minute = cl2.get(Calendar.MINUTE);

      if (start_hour > end_hour) {
        //时间段 18:30-08:00
        int T = (start_hour - end_hour) * 60 + (start_minute - end_minute);
        if (is_between(permit_time, entertime)) {
          //入库时间落在 扣费时间段内部
          if (is_between(permit_time, outtime)) {
            if (enter_hour * 60 + enter_minute >= out_hour * 60 + out_minute) {
              int diff_hour = start_hour - enter_hour;
              diff_minute_enter = diff_hour * 60 + (start_minute - enter_minute);
              diff_hour = out_hour - end_hour;
              diff_minute_out = diff_hour * 60 + (out_minute - end_minute);
              temp_time = diff_minute_enter + diff_minute_out;
            } else {
              int diff_hour = out_hour - enter_hour;
              temp_time = diff_hour * 60 + (out_minute - enter_minute);
            }
          } else {
            int diff_hour = start_hour - enter_hour;
            temp_time = diff_hour * 60 + (start_minute - enter_minute);
          }
        } else {
          //入库时间落在 免费时间段内部

          if (is_between(permit_time, outtime)) {
            int diff_hour = out_hour - end_hour;
            temp_time = diff_hour * 60 + (out_minute - end_minute);
          } else {
            if (enter_hour * 60 + enter_minute >= out_hour * 60 + out_minute) {
              temp_time = T;
            } else {
              temp_time = 0;
            }
          }
        }
      } else {
        //时间段 8:00-18:30
        int T = (24 * 60 - (end_hour - start_hour) * 60 - (end_minute - start_minute));
        if (is_between(permit_time, entertime)) {
          //入库时间落在 扣费时间段内部

          if (is_between(permit_time, outtime)) {
            if (enter_hour * 60 + enter_minute >= out_hour * 60 + out_minute) {
              int diff_hour = enter_hour - out_hour;
              temp_time = diff_hour * 60 + (enter_minute - out_minute);
              temp_time = T - temp_time;
            } else {
              int diff_hour = out_hour - enter_hour;
              temp_time = diff_hour * 60 + (out_minute - enter_minute);
            }
          } else {
            if (enter_hour > 12) {
              int diff_hour = (24 - enter_hour) + start_hour;
              temp_time = diff_hour * 60 + (start_minute - enter_minute);
            } else {
              int diff_hour = start_hour - enter_hour;
              temp_time = diff_hour * 60 + (start_minute - enter_minute);
            }
          }
        } else {
          //入库时间落在 免费时间段内部
          if (is_between(permit_time, outtime)) {
            if (out_hour >= end_hour) {
              int diff_hour = out_hour - end_hour;
              temp_time = diff_hour * 60 + (out_minute - end_minute);
            } else {
              int diff_hour = out_hour - end_hour;
              temp_time = diff_hour * 60 + (out_minute - end_minute);
            }
          } else {
            if (enter_hour * 60 + enter_minute >= out_hour * 60 + out_minute) {
              //都落免费区间 且  入库时间小时 大于 出库时间小时
              temp_time = T;
            } else {
              temp_time = 0;
            }
          }
        }
      }
    }
    return temp_time;
  }

  /**
   * 保留2位小数
   */
  public String formatDouble(double price) {
    DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
    return decimalFormat.format(price);//format 返回的是字符串
  }

//  public static void main(String[] args) {
//	/*Calendar cl = Calendar.getInstance(Locale.CHINA);
//	cl.set(2016, 8, 3, 19, 30);
//
//	Calendar cl2 = Calendar.getInstance(Locale.CHINA);
//	cl2.set(2016, 8, 4, 16, 30);
//
//	Calendar cl3 = Calendar.getInstance(Locale.CHINA);
//	cl3.set(2016, 8, 3, 17, 30);
//
//	Calendar cl4 = Calendar.getInstance(Locale.CHINA);
//	cl4.set(2016, 8, 6, 15, 0);
//	long tt = calculateMonthPay("18:30-08:00",cl.getTime(),cl2.getTime(),cl3.getTime(),cl4.getTime());
//	*/
//    Calendar cl = Calendar.getInstance(Locale.CHINA);
//    cl.set(2016, 8, 5, 12, 30);
//
//    Calendar cl2 = Calendar.getInstance(Locale.CHINA);
//    cl2.set(2016, 8, 5, 16, 30);
//
//    System.out.println(new BaseBiz().one_day("24:00-23:00", cl.getTime(), cl2.getTime()));
//  }

}
