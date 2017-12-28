package com.park.app.service;

import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.Carcode_park_rent;
import com.park.bean.Pay_month_park;
import com.park.bean.Pay_park;
import com.park.bean.Rent_defer;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_carcode;
import com.park.bean.User_info;
import com.park.bean.User_moneyback;
import com.park.constants.Constants;
import com.park.dao.Rent_deferDao;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.CarPB;
import com.park.mvc.service.common.PayMonthParkPB;
import com.park.mvc.service.common.PayParkPB;
import com.park.transaction.CarTransaction;
import com.park.util.FileUtil;
import com.park.util.RequestUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 处理车辆信息的业务逻辑管理类
 *
 * @author jingxiaohu
 */
@Service
public class CarBiz extends BaseBiz {

  @Autowired
  private CarTransaction carTransaction;
  @Autowired
  private PayParkPB payParkPB;
  @Autowired
  private PayMonthParkPB payMonthParkPB;
  @Autowired
  protected CarPB carPB;
  @Autowired
  protected Rent_deferDao rent_deferDao;

  /**
   * 用户添加绑定车牌号
   */
  public void bindcar(ReturnDataNew returnData, long ui_id, String car_code, int act) {
    // TODO Auto-generated method stub
    try {
      User_info user_info = user_infoDao.selectByKey(ui_id);
      if (user_info == null) {
        //用户不存在
        returnData.setReturnData(errorcode_data, "用户不存在", "");
        return;
      }
      //首先判断该量车是否已经被绑定了
      User_carcode user_carcode = queryUserCarBycode(car_code);

      //act 1:添加车牌 2：删除车牌
      if (act == 2) {
        //删除该车牌绑定
        if (user_carcode != null) {
          //车牌在订单中使用可以删除该使用的车牌号（出库需扫车牌号，所以不能删除）
          List<Pay_park> list1 = payParkPB.selectAllPayParkBYcar_code(ui_id, car_code);
          if (list1 != null && list1.size() > 0) {
            returnData.setReturnData(errorcode_data, "还有未支付的临停订单，不允许删除该车牌绑定,亲!", "");
            return;
          }
          List<Pay_park> list2 = payMonthParkPB.selectAllpayMonthParkBYcar_code(ui_id, car_code);
          if (list2 != null && list2.size() > 0) {
            returnData.setReturnData(errorcode_data, "还有未支付的租赁订单，不允许删除该车牌绑定,亲!", "");
            return;
          }
          List<Carcode_park_rent> carcode_park_rents = getMySelfService().queryListT(
              "SELECT * FROM carcode_park_rent o WHERE o.car_code=? AND o.endtime>? AND o.is_del=0",
              Carcode_park_rent.class, car_code, new Date());
          if (carcode_park_rents != null && carcode_park_rents.size() > 0) {
            returnData.setReturnData(errorcode_data, "还有未到期的租赁，不允许删除该车牌绑定,亲!", "");
            return;
          }

          int count = user_carcodeDao.deleteByKey(user_carcode.getUc_id());
          if (count != 1) {
            returnData.setReturnData(errorcode_data, "删除该车牌绑定失败", "");
            return;
          }
        }
        returnData.setReturnData(errorcode_success, "删除该车牌绑定成功", "");
        return;
      }

      if (user_carcode != null) {
        if (user_carcode.getUi_id() == ui_id) {
          returnData.setReturnData(errorcode_data, "您已经绑定了该车牌号了", "");
          return;
        }

        returnData.setReturnData(errorcode_data, "该车牌号已经被绑定了,如果您的车牌被他（她）绑定，可以去申述", "");
        return;
      }
      //每人绑定车牌不能超过10个
      String sql = "select count(uc_id) from user_carcode where ui_id=?";
      int user_carcodecount = user_carcodeDao.getJdbc().getJdbcOperations()
          .queryForObject(sql, Integer.class, ui_id);
      if (user_carcodecount >= 3) {
        returnData.setReturnData(errorcode_data, "绑定的车牌数不能超过3个", "");
        return;
      }

      //入库操作
      Date date = new Date();
      user_carcode = new User_carcode();
      user_carcode.setCtime(date);
      user_carcode.setUtime(date);
      user_carcode.setUi_id(ui_id);
      user_carcode.setUi_nd(user_info.getUuid());
      user_carcode.setUi_tel(user_info.getUi_tel());
      user_carcode.setCar_code(car_code);
          /*int id = user_carcodeDao.insert(user_carcode);
          if(id < 1){
        		returnData.setReturnData(errorcode_data, "绑定失败", ""); 
    			return;
        	}
        	user_carcode.setUc_id(id);
			returnData.setReturnData(errorcode_success, "绑定成功", ""); 
			return;*/
      carTransaction.bindCarCode(user_carcode, returnData);

    } catch (Exception e) {
      log.error("CarBiz bindcar is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 用户申诉退费
   */
  public void usermoneyback(ReturnDataNew returnData, long ui_id, String order_id,
      long pi_id, int um_money, String car_code, int um_state,
      int check_state, long admin_userid, int is_rent, String area_code, int type, String content) {
    // TODO Auto-generated method stub
    try {
      Date date = new Date();
      int money = 0;
      String pi_name = "";
      //首先判断用户是否存在
      User_info user_info = user_infoDao.selectByKey(ui_id);
      if (user_info == null) {
        returnData.setReturnData(errorcode_data, "用户不存在", "");
        return;
      }
      if (is_rent == 2) {
        //租赁订单
        Pay_month_park pay_month_park = payMonthParkPB.selectOnePayMonthPark(order_id);
        if (pay_month_park == null) {
          //该订单不存在
          returnData.setReturnData(errorcode_data, "该订单不存在", "");
          return;
        }
        pi_name = pay_month_park.getPi_name();
        //超过24小时不能进行申诉
        if (date.getTime() - pay_month_park.getUtime().getTime() > 24 * 60 * 60 * 1000) {
          //超过24小时不能进行申诉
          returnData.setReturnData(errorcode_data, "超过24小时不能进行申诉", "");
          return;
        }

        money = pay_month_park.getMoney();

        if (pay_month_park.getPp_state() == 0) {
          //必须是已经付款了的订单才能进行申述
          returnData.setReturnData(errorcode_data, "该订单还没有进行扣款，申述无效", "");
          return;
        }
        if (pay_month_park.getAllege_state() == 1) {
          //已经申述
          returnData.setReturnData(errorcode_data, "该订单你已申述", "");
          return;
        }
        //申述状态0:未申述1：申述中2：申述失败3：申述成功
        pay_month_park.setAllege_state(1);
        int count = pay_month_parkDao.updateByKey(pay_month_park);
        if (count < 1) {
          returnData.setReturnData(errorcode_data, "申述失败", "");
          return;
        }
      } else if (is_rent == 3) {
          //租赁续租
    	  String sql = "select * from rent_defer where rent_order_id=?";
    	  Rent_defer rent_defer = rent_deferDao.queryUniqueT(sql, Rent_defer.class, order_id);
          if (rent_defer == null) {
            //该订单不存在
            returnData.setReturnData(errorcode_data, "该订单不存在", "");
            return;
          }
          pi_name = rent_defer.getPi_name();
          //超过24小时不能进行申诉
          if (date.getTime() - rent_defer.getUtime().getTime() > 24 * 60 * 60 * 1000) {
            //超过24小时不能进行申诉
            returnData.setReturnData(errorcode_data, "超过24小时不能进行申诉", "");
            return;
          }

          money = rent_defer.getMoney();
          //pay_state:支付状态（0：未支付 1：支付成功  2：支付失败）
          if (rent_defer.getPay_state() != 1) {
            //必须是已经付款了的订单才能进行申述
            returnData.setReturnData(errorcode_data, "该订单还没有进行扣款，申述无效", "");
            return;
          }
          //defer_state:续约状态（0：未续约  1：续约中  2：续约成功 3：续约失败   4：续约超时失败-退款钱包）
          if (rent_defer.getDefer_state()==0 || rent_defer.getDefer_state()==3 || rent_defer.getDefer_state()==4) {
        	  //未续约成功
        	  returnData.setReturnData(errorcode_data, "该订单未续约成功，申诉无效", "");
        	  return;
          }
          //申述状态0:未申述1：申述中2：申述失败3：申述成功
          if (rent_defer.getAllege_state() == 1) {
        	  //已经申述
        	  returnData.setReturnData(errorcode_data, "该订单你已申述", "");
        	  return;
          }
          rent_defer.setAllege_state(1);
          int count = rent_deferDao.updateByKey(rent_defer);
          if (count < 1) {
            returnData.setReturnData(errorcode_data, "申述失败", "");
            return;
          }
      }else {
        //临停订单
        //检查该订单是否已经被该用户申述过了
        Pay_park pay_park = payParkPB.selectOnePayPark(order_id);
        if (pay_park == null) {
          //该订单不存在
          returnData.setReturnData(errorcode_data, "该订单不存在", "");
          return;
        }
        pi_name = pay_park.getPi_name();
        //超过24小时不能进行申诉
        if (date.getTime() - pay_park.getUtime().getTime() > 24 * 60 * 60 * 1000) {
          //超过24小时不能进行申诉
          returnData.setReturnData(errorcode_data, "超过24小时不能进行申诉", "");
          return;
        }

        if (pay_park.getPp_state() == 0) {
          //必须是已经付款了的订单才能进行申述
          returnData.setReturnData(errorcode_data, "该订单还没有进行扣款，申述无效", "");
          return;
        }
        if (pay_park.getAllege_state() == 1) {
          //已经申述
          returnData.setReturnData(errorcode_data, "该订单你已申述", "");
          return;
        }

        money = pay_park.getMoney();
        if (pay_park.getDiscount_money() > 0 && pay_park.getUpc_id() > 0) {
          money = (int) (money - pay_park.getDiscount_money()) >= 0 ? (int) (money - pay_park
              .getDiscount_money()) : 0;
        }
        //申述状态0:未申述1：申述中2：申述失败3：申述成功
        pay_park.setAllege_state(1);
        int count = pay_parkDao.updateByKey(pay_park);
        if (count < 1) {
          returnData.setReturnData(errorcode_data, "申述失败", "");
          return;
        }
      }

      //入库操作
      User_moneyback user_moneyback = new User_moneyback();
      user_moneyback.setAdmin_userid(admin_userid);
      user_moneyback.setCar_code(car_code);
      user_moneyback.setCheck_state(check_state);
      user_moneyback.setPi_id(pi_id);
      user_moneyback.setOrder_id(order_id);
      user_moneyback.setUi_id(ui_id);
      user_moneyback.setUm_money(money);
      user_moneyback.setUm_state(um_state);
      user_moneyback.setCtime(date);
      user_moneyback.setUtime(date);
      user_moneyback.setArea_code(area_code);
      user_moneyback.setType(type);
      user_moneyback.setContent(content);
      user_moneyback.setIs_rent(is_rent);
      user_moneyback.setPi_name(pi_name);
      int id = user_moneybackDao.insert(user_moneyback);
      if (id < 1) {
        returnData.setReturnData(errorcode_data, "用户申述失败", "");
        return;
      }
      user_moneyback.setUm_id(id);
      returnData.setReturnData(errorcode_success, "用户申述成功", "");
      return;

    } catch (Exception e) {
      log.error("CarBiz usermoneyback is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


  /**
   * 读取用户绑定的车牌号
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void read_bindcar(ReturnDataNew returnData, long ui_id) {
    // TODO Auto-generated method stub
    try {
      //首先判断用户是否存在
      User_info user_info = user_infoDao.selectByKey(ui_id);
      if (user_info == null) {
        returnData.setReturnData(errorcode_data, "用户不存在", "");
        return;
      }
      //查询该用户所绑定的车牌号码
      List<User_carcode> list = QueryUserCarCode(ui_id);

      returnData.setReturnData(errorcode_success, "读取用户绑定的车牌号成功", list);
      return;

    } catch (Exception e) {
      log.error("CarBiz read_bindcar is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }

  }

  /**
   * 更新用户车辆信息
   */
  public void update_car(ReturnDataNew returnData, long ui_id,
      String car_code, long uc_id, MultipartFile lience,
      String lienceFileName, String lienceContentType, String car_brand, Integer uc_color,
      String run_code) {
    // TODO Auto-generated method stub
    try {
      //首先判断是否已经绑定了该量车
      User_carcode user_carcode = user_carcodeDao.selectByKey(uc_id);
      if (user_carcode == null) {
        //您还没有已绑定的车牌
        returnData.setReturnData(errorcode_data, "您还没有已绑定的车牌", "");
        return;
      }
      if (!RequestUtil.checkObjectBlank(car_code)) {
        //检查该车牌是否是自己的车牌
        if (!car_code.equalsIgnoreCase(user_carcode.getCar_code())) {
          //不是自己的车牌号 则检查是否已经被其他人绑定
          //首先判断该量车是否已经被绑定了
          User_carcode user_carcode2 = queryUserCarBycode(car_code);
          if (user_carcode2 != null) {
            returnData.setReturnData(errorcode_data, "该车牌号已经被绑定了,如果您的车牌被他（她）绑定，可以去申述", "");
            return;
          } else {
            //车牌在订单中使用可以删除该使用的车牌号（出库需扫车牌号，所以不能删除）
            List<Pay_park> list1 = payParkPB
                .selectAllPayParkBYcar_code(ui_id, user_carcode.getCar_code());
            if (list1 != null && list1.size() > 0) {
              returnData.setReturnData(errorcode_data, "还有未支付的临停订单，不允许修改车牌", "");
              return;
            }
            List<Pay_park> list2 = payMonthParkPB
                .selectAllpayMonthParkBYcar_code(ui_id, user_carcode.getCar_code());
            if (list2 != null && list2.size() > 0) {
              returnData.setReturnData(errorcode_data, "还有未支付的租赁订单，不允许修改车牌", "");
              return;
            }
            //车牌
            user_carcode.setCar_code(car_code);
          }
        }
      }

      if (!RequestUtil.checkObjectBlank(car_brand)) {
        //品牌
        user_carcode.setCar_brand(car_brand);
      }
      if (!RequestUtil.checkObjectBlank(uc_color)) {
        //颜色
        user_carcode.setUc_color(uc_color);
      }
      if (!RequestUtil.checkObjectBlank(lience) && !RequestUtil.checkObjectBlank(lienceFileName)) {
        //行驶证
        //上传文件
        String url = FileUtil.uploadScaleAvatarImage(lience, lienceFileName, Constants.USER_LIENCE,
            Constants.LIENCE_WIDTH, Constants.LIENCE_HIGHT, null);
        if (url == null) {
          returnData.setReturnData(errorcode_data, "上传行驶证失败", null);
          return;
        }
        user_carcode.setRun_url(url);
      }
      if (!RequestUtil.checkObjectBlank(run_code)) {
        user_carcode.setRun_code(run_code);
      }
      carTransaction.update_car(user_carcode, returnData);
    } catch (Exception e) {
      log.error("CarBiz update_car is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


  /**
   * 获取用户申诉退费审核结果
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void read_usermoneyback(ReturnDataNew returnData, long ui_id,
      String order_id, int is_rent) {
    // TODO Auto-generated method stub
    try {
      //首先判断用户是否存在
      String sql = "SELECT *  FROM user_moneyback WHERE order_id=? AND ui_id=? AND is_rent=?";

      User_moneyback user_moneyback = getMySelfService()
          .queryUniqueT(sql, User_moneyback.class, order_id, ui_id, is_rent);
      if (user_moneyback == null) {
        //无该申述记录
        returnData.setReturnData(errorcode_data, "无该申述记录", "", "1");
        return;
      }
      returnData.setReturnData(errorcode_success, "获取成功", user_moneyback);
      return;

    } catch (Exception e) {
      log.error("CarBiz read_usermoneyback is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  //查询用户和车牌号绑定关系
  public User_carcode queryUserCarBycode(String car_code) {
    try {
      //首先判断是否已经绑定了该量车
      String sql = "SELECT *  FROM user_carcode WHERE car_code=? LIMIT 1";
      return getMySelfService()
          .queryUniqueT(sql, User_carcode.class, car_code);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("queryUserCarBycode is error" + e.getMessage(), e);
    }
    return null;
  }

  /**
   * 查询用户的车牌号码
   */
  public List<User_carcode> QueryUserCarCode(long ui_id) {
    //查询该用户所绑定的车牌号码
    try {
      //首先判断是否已经绑定了该量车
      String sql = "select *  from user_carcode where ui_id=?";
      return getMySelfService().queryListT(sql, User_carcode.class, ui_id);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("QueryUserCarCode is error" + e.getMessage(), e);
    }
    return null;
  }


}
