package com.park.app.service;

import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_info;
import com.park.bean.User_park_coupon;
import com.park.bean.User_park_coupon_log;
import com.park.dao.User_park_coupon_logDao;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.JpushPB;
import com.park.mvc.service.common.PayParkPB;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 优惠券的业务逻辑管理类
 *
 * @author jingxiaohu
 */
@Service
public class CouponBiz extends BaseBiz {

  @Autowired
  protected PayParkPB payParkPB;
  @Autowired
  protected JpushPB jpushPB;
  @Autowired
  private User_park_coupon_logDao user_park_coupon_logDao;


  /**
   * 读取我的优惠券列表
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void ReturnreadMycoupon(ReturnDataNew returnData, int dtype,
      long ui_id, int page, int size) {
    // TODO Auto-generated method stub
    try {
      if (page < 1) {
        page = 1;
      }
      int start = (page - 1) * size;
      size = 1000;
      //首先验证用户是否存在
      String sql =
          "select *,end_time > now() as  is_effect from user_park_coupon where ui_id =:ui_id order by money asc,end_time  asc,send_unit asc limit "
              + start + "," + size;
      Map<String, Object> paramMap = new HashMap<String, Object>();
      paramMap.put("ui_id", ui_id + "");
      List<Map<String, Object>> list = getMySelfService().queryForList(sql, paramMap);
      returnData.setReturnData("0", "获取成功", list);
      return;

    } catch (Exception e) {
      log.error("CouponBiz ReturnreadMycoupon is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 扫码赠送优惠券
   */
  public void give_coupon(ReturnDataNew returnData, long ui_id, Long upc_id,
      Long from_ui_id) {
    try {
      if (ui_id == from_ui_id.longValue()) {
        returnData.setReturnData(errorcode_data, "自己不能送给自己代金券", "");
        return;
      }

      //首先检查用户是否存在
      User_info user_info_to = user_infoDao.selectByKey(ui_id);
      if (user_info_to == null) {
        returnData.setReturnData(errorcode_data, "用户不存在", "");
        return;
      }
      User_info user_info_from = user_infoDao.selectByKey(from_ui_id);
      if (user_info_from == null) {
        returnData.setReturnData(errorcode_data, "用户不存在", "");
        return;
      }
      //检查优惠券是否在 赠送者账户上面 是否有效
      Date date = new Date();
      User_park_coupon user_park_coupon = user_park_couponDao.selectByKey(upc_id);
      if (user_park_coupon == null) {
        returnData.setReturnData(errorcode_data, "代金券不存在", "");
        return;
      }
      User_park_coupon_log user_park_coupon_log = new User_park_coupon_log();
      BeanUtils.copyProperties(user_park_coupon,user_park_coupon_log);
      if (!from_ui_id.equals(user_park_coupon.getUi_id())
          || user_park_coupon.getUpc_state() == 1
          || user_park_coupon.getEnd_time().getTime() - date.getTime() < 0) {
        returnData.setReturnData(errorcode_data, "代金券不可用", "");
        return;
      }
      //by jxh 2017-3-14 扈总新增该需求，以此为证
         /*if(user_park_coupon.getSend_unit() == 1){
           returnData.setReturnData(errorcode_data, "龙支付代金券不可转赠","");
					return;
			 	}*/
      //进行赠送处理
      user_park_coupon.setUi_id(ui_id);
      int count = user_park_couponDao.updateByKey(user_park_coupon);
      if (count != 1) {
        //更新失败
        returnData.setReturnData(errorcode_data, "获取失败", "");
        return;
      } else {
        user_park_coupon_log.setType(2);//记录类型（0：未指定1：用户停车使用2：用户扫码转赠3：系统赠送）
        user_park_coupon_log.setAccept_ui_id(ui_id);
        user_park_coupon_log.setAccept_ui_nd(user_info_to.uuid);
        user_park_coupon_logDao.insert(user_park_coupon_log);
        //获取成功 则推送消息给 赠送者 通知他的代金券减少了
        /**
         * 这里处理推送---现金支付
         */
        String title = "系统消息";
        String message =
            "亲，你已成功赠送" + user_park_coupon.getMoney() / 100 + "元代金券,给手机号码为【" + user_info_to
                .getUi_tel() + "】的用户";
        jpushPB.pushSystem(user_info_from.getUuid(), message, title);
      }

      returnData.setReturnData(errorcode_success, "获取成功", "");
      return;

    } catch (Exception e) {
      log.error("CouponBiz give_coupon is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
  }

  /**************************分离出来的方法*****************************/


}
