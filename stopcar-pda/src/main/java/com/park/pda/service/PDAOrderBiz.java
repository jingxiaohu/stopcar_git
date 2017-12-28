package com.park.pda.service;

import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.Park_info;
import com.park.bean.Pay_month_park;
import com.park.bean.Pay_park;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_info;
import com.park.constants.Constants;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.ActivityPB;
import com.park.mvc.service.common.ParkCouponPB;
import com.park.mvc.service.common.ParkInfoPB;
import com.park.mvc.service.common.PayMonthParkPB;
import com.park.mvc.service.common.PayParkPB;
import com.park.transaction.CarTransaction;
import com.park.util.FileUtil;
import com.park.util.RequestUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 处理用户订单管理
 *
 * @author jingxiaohu
 */
@Service
public class PDAOrderBiz extends BaseBiz {

  @Autowired
  private CarTransaction carTransaction;
  @Autowired
  protected PDACarBiz carBiz;
  @Autowired
  protected PayParkPB payParkPB;
  @Autowired
  protected PayMonthParkPB payMonthParkPB;

  @Autowired
  protected ParkInfoPB parkInfoPB;
  @Autowired
  protected ActivityPB activityPB;
  @Autowired
  protected ParkCouponPB parkCouponPB;

  /**
   * PDA查询订单信息
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void pda_read_order(ReturnDataNew returnData, int dtype, Integer type, String orderid) {
    try {
      if (type == 0) {
        //0:普通停车订单
        String sql = "SELECT *  FROM  pay_park  WHERE my_order=? LIMIT 1";
        Pay_park pay_park = getMySelfService().queryUniqueT(sql, Pay_park.class, orderid);
        if (pay_park == null) {
          returnData.setReturnData(errorcode_data, "订单不存在", "");
          return;
        }
        //返回数据
        returnData.setReturnData(errorcode_success, "普通停车订单", pay_park);
      } else {
        //1：租赁停车订单
        String sql = "SELECT *  FROM  pay_month_park  WHERE  my_order=? LIMIT 1";
        Pay_month_park pay_month_park = getMySelfService()
            .queryUniqueT(sql, Pay_month_park.class, orderid);
        if (pay_month_park == null) {
          returnData.setReturnData(errorcode_data, "订单不存在", "");
          return;
        }
        //返回数据
        returnData.setReturnData(errorcode_success, "租赁停车订单", pay_month_park);
      }
    } catch (Exception e) {

      log.error("orderBiz.pda_read_order is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 获取PDA的对应的车是否是逃逸车辆或者未交费车辆
   */
  @Deprecated
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void pda_check_escape_car(ReturnDataNew returnData, Long pi_id, String area_code,
      Integer type,
      String car_code) {

    try {
      //is_over int default 0 comment '订单是否完成或者逃逸(0:没有完成 1：完成 2：车辆逃逸   3：未交费)',
      //通过车牌号查询 是否存在逃逸或者未缴费车辆
      String name = "";
      int is_over = 2;
      if (type == 0) {
        is_over = 2;
        name = "逃逸";
      } else {
        is_over = 3;
        name = "未交费";
      }

      //获取商户ID
      Park_info park_info = returnParkInfo(pi_id, area_code);
      if (park_info == null) {
        //该停车场不存在
        returnData.setReturnData(errorcode_data, "该停车场不存在", "");
        return;
      }
      //商户ID
      long pu_id = park_info.getPu_id();
      if (pu_id == 0) {
        //不是逃逸或者未缴费车辆
        returnData.setReturnData(errorcode_success, "不是" + name + "车辆", "");
        return;
      }

      String sql = "SELECT * FROM pay_park WHERE pu_id=?  AND car_code=? AND is_over=? AND pp_state=0 AND is_del=0";
      List<Pay_park> pay_park_list = getMySelfService()
          .queryListT(sql, Pay_park.class, pu_id, car_code, is_over);
      if (pay_park_list == null || pay_park_list.size() == 0) {
        //不是逃逸或者未缴费车辆
        returnData.setReturnData(errorcode_success, "不是" + name + "车辆", "");
        return;
      } else {
        //检查该车辆是否
        returnData.setReturnData(errorcode_success, "是" + name + "车辆", pay_park_list);
        return;
      }
    } catch (Exception e) {

      log.error("orderBiz.pda_check_escape_car is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 获取PDA的预约且未付款和未取消的订单
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void pda_expect_order(ReturnDataNew returnData, int dtype,
      String area_code, long pi_id, int page, int size) {

    try {
      if (page < 1) {
        page = 1;
      }
      int start = (page - 1) * size;
      //0:普通停车订单
      String sql =
          "select *  from  pay_park  where pi_id=? and area_code=? and is_del=0 and cancel_state=0 and order_type=1 and pp_state=0 order by ctime desc limit "
              + start + "," + size;
      List<Pay_park> list = getMySelfService().queryListT(sql, Pay_park.class, pi_id, area_code);
      //返回数据
      returnData.setReturnData(errorcode_success, "获取PDA的预约且未付款和未取消的订单", list);
      return;
    } catch (Exception e) {

      log.error("orderBiz.pda_expect_order is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_data, "处理失败", "", "-1");
    }
  }

  /**
   * 露天停车场的PDA更新用户自动支付
   *
   * @param pay_type 支付类型 0:自动扣款  1：现金支付
   * @param type //处理类型  0:常规类型  1：免费分钟类型 2:免费车类型 3：包月车类型 4：租赁车类型
   */
  public void pda_sure(ReturnDataNew returnData, int dtype, String orderid,
      int pay_type, long pi_id, String area_code, int money, String escape_orderids,
      boolean is_sync, Long sync_time, int type, MultipartFile face, String faceFileName,
      String fingerprint, String finger_veno) {

    try {

      //首先判断是否已经进行了录入  如果数据库中没有该停车场的数据则不进行插入  否则不予处理
      Pay_park pay_park = payParkPB.selectOnePayPark(orderid);// QueryByOrderId(orderid);
      if (pay_park == null) {
        returnData.setReturnData(errorcode_data, "该订单不存在", "");
        return;
      }
      if (pay_park.getPp_state() == 1) {
        //返回结果
        returnData.setReturnData(errorcode_data, "该订单已经支付过了", "");
        return;
      }

      pay_park.setMoney(money);

      try {
        //扫脸识别
        String face_url = null;
        /**
         * 兼容之前老版本且在线运行的PDA的BUG  验证如果是钱包支付 则提前验证用户是否存在
         */
        //支付类型 0:自动扣款（钱包）  1：现金支付 2：微信  3：银联  4：钱包 5:龙支付 6:支付宝 7:扫脸支付
        if (pay_type == 0 || pay_type == 4 || pay_type == 7) {//钱包支付

          if (pay_park.getUi_id() == 0) {
            returnData.setReturnData(errorcode_data, "该车牌未绑定钱包", "", "");
            return;
          } else {
            User_info userinfo = user_infoDao.selectByKey(pay_park.getUi_id());
            if (userinfo == null) {
              returnData.setReturnData(errorcode_data, "用户不存在", "", "");
              return;
            } else {
              //验证是否是扫脸支付 如果是则 进行人脸图片处理
              if (pay_type == 7) {
                //by jxh 2017-7-6 新增扫脸支付
                if (!RequestUtil.checkObjectBlank(face) && !RequestUtil
                    .checkObjectBlank(faceFileName)) {
                  //处理用户头像问题
                  //上传文件
                  face_url = FileUtil.uploadScaleAvatarImage(face, faceFileName,
                      Constants.USER_FACE,
                      0, 0, userinfo.getUi_tel());

                }

                if (face_url == null) {
                  returnData.setReturnData(errorcode_data, "上传人脸图片错误", null);
                  return;
                }
              }


            }
            //检查是否开启了自动扣款
            if (userinfo.getUi_autopay() == 0) {
              //没有开启自动扣款
              returnData.setReturnData(errorcode_data, "没有开启自动扣款", "", "");
              return;
            }
          }
        }

        carTransaction.upEscapseOrderState(returnData,
            pay_park, pay_type, pi_id, area_code, money, escape_orderids, is_sync, sync_time, type,
            face_url, fingerprint, finger_veno);

      } catch (Exception e) {

        log.warn("露天停车场的PDA更新用户自动支付 失败", e);
        //返回结果
        returnData.setReturnData(errorcode_data, e.getLocalizedMessage(), "", "2");
        return;
      }
    } catch (Exception e) {
      log.error("ParkinfoBiz pda_sure is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 获取PDA的对应的车的欠费记录 -- 跨商户使用--NEW
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void pda_check_car_Owe(ReturnDataNew returnData, Long pi_id, String area_code,
      Integer type,
      String car_code) {

    try {
      //is_over int default 0 comment '订单是否完成或者逃逸(0:没有完成 1：完成 2：车辆逃逸   3：未交费)',
      //通过车牌号查询 是否存在逃逸或者未缴费车辆
      String name = "";
      int is_over = 3;//"未交费";
      if (type == 0) {
        name = "同商户欠费订单";
        //获取商户ID
        Park_info park_info = returnParkInfo(pi_id, area_code);
        if (park_info == null) {
          //该停车场不存在
          returnData.setReturnData(errorcode_data, "该停车场不存在", "");
          return;
        }
        //商户ID
        long pu_id = park_info.getPu_id();
        if (pu_id == 0) {
          //不是逃逸或者未缴费车辆
          returnData.setReturnData(errorcode_success, "不是欠费车辆", "");
          return;
        }
        String sql = "SELECT * FROM pay_park WHERE pu_id=?  AND car_code=? AND is_over=? AND pp_state=0 AND is_del=0";
        List<Pay_park> pay_park_list = getMySelfService()
            .queryListT(sql, Pay_park.class, pu_id, car_code, is_over);
        if (pay_park_list == null || pay_park_list.size() == 0) {
          //不是逃逸或者未缴费车辆
          returnData.setReturnData(errorcode_success, "不是欠费车辆", "");
          return;
        } else {
          //检查该车辆是否
          returnData.setReturnData(errorcode_success, "是欠费车辆", pay_park_list);
          return;
        }
      } else {
        name = "跨商户欠费订单";
        String sql = "SELECT * FROM pay_park WHERE car_code=? AND is_over=? AND pp_state=0 AND is_del=0 AND LEFT(area_code,2)=?";
        List<Pay_park> pay_park_list = getMySelfService()
            .queryListT(sql, Pay_park.class, car_code, is_over, area_code.substring(0, 2));
        if (pay_park_list == null || pay_park_list.size() == 0) {
          //不是逃逸或者未缴费车辆
          returnData.setReturnData(errorcode_success, "不是欠费车辆", "");
          return;
        } else {
          //检查该车辆是否
          returnData.setReturnData(errorcode_success, "是欠费车辆", pay_park_list);
          return;
        }
      }
    } catch (Exception e) {
      log.error("orderBiz.pda_check_car_Owe is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 更新订单的 逃逸 状态
   */
  public void upate_order_escape(ReturnDataNew returnData, int dtype,
      String orderid, Integer type, Integer money, boolean is_sync, Long sync_time) {

    try {
      Pay_park pay_park = payParkPB.selectOnePayPark(orderid);
      if (pay_park == null) {
        //订单不存在
        returnData.setReturnData(errorcode_data, "订单不存在", "" + orderid, "1");
        return;
      } else {
        Date date;
        if (is_sync) {//异步上传依客户端时间
          date = new Date(sync_time);
          pay_park.setCancel_state(3);
        } else {
          date = new Date();
        }
        switch (type) {
          case 0:
            break;
          case 1: {
            // 标记逃逸
            //Is_over 订单是否完成或者逃逸(0:没有完成1：完成2：车辆逃逸)
            if (pay_park.getIs_over() == 2) {
              returnData.setReturnData(errorcode_data, "该条订单已经标记为逃逸过了", "", "2");
              return;
            } else {

              pay_park.setIs_over(2);
              pay_park.setMoney(money < 0 ? 0 : money);
              pay_park.setUtime(date);
              pay_park.setLeave_time(date);
              int count = pay_parkDao.updateByKey(pay_park);
              if (count != 1) {
                //更新失败
                returnData.setReturnData(errorcode_data, "处理失败", "" + orderid, "3");
                return;
              } else {
                returnData.setReturnData(errorcode_success, "更新成功", "", "");
                return;
              }
            }
          }
          case 2: {
            // 标记未交费
            //Is_over 订单是否完成或者逃逸(0:没有完成 1：完成 2：车辆逃逸 3：未交费)
            pay_park.setIs_over(3);
            pay_park.setMoney(money < 0 ? 0 : money);
            pay_park.setUtime(date);
            pay_park.setLeave_time(date);
            int count = pay_parkDao.updateByKey(pay_park);
            if (count != 1) {
              //更新失败
              returnData.setReturnData(errorcode_data, "处理失败", "" + orderid, "3");
              return;
            } else {
              returnData.setReturnData(errorcode_success, "更新成功", "", "");
              return;
            }
          }
          case 3:
            break;
          default:
            break;
        }
      }

      returnData.setReturnData(errorcode_data, "更新类型出现错误--该逻辑还未写", "", "");
      return;

    } catch (Exception e) {

      log.error("orderBiz.upate_order_escape is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 获取PDA停车场的所有未删除订单
   */
  public void pda_all_order(ReturnDataNew returnData, String area_code, long pi_id, int page,
      int size, long start_time, long end_time) {
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date stime = dateFormat.parse(dateFormat.format(start_time));
      Date etime = dateFormat.parse(dateFormat.format(end_time));
      if (page < 1) {
        page = 1;
      }
      int start = (page - 1) * size;
      //0:普通停车订单
      String sql =
          "select * from pay_park where park_type=1 and pi_id=? and area_code=? and is_del=0 and arrive_time>? and arrive_time<? order by arrive_time desc limit "
              + start + "," + size;
      List<Pay_park> list = getMySelfService()
          .queryListT(sql, Pay_park.class, pi_id, area_code, stime, etime);
      //返回数据
      returnData.setReturnData(errorcode_success, "获取PDA停车场的所有未删除订单成功", list);
      return;
    } catch (Exception e) {

      log.error("orderBiz.pda_all_order is error" + e.getMessage(), e);
      returnData.setReturnData(errorcode_data, "处理失败", "", "-1");
    }
  }

}
