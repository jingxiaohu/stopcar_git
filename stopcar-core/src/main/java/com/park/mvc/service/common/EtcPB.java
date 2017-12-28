package com.park.mvc.service.common;

import com.park.bean.*;
import com.park.exception.QzException;
import com.park.mvc.service.BaseBiz;
import com.park.service.ETCHelper;
import com.park.service.LZFHelper;
import com.park.service.etc.etcapi.ETCResponse;
import com.park.transaction.PayTransaction;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 车辆相关公用方法
 *
 * @author jingxiaohu
 */
@Service
public class EtcPB extends BaseBiz {

  @Autowired
  protected LZFHelper lZFHelper;
  @Autowired
  private ETCHelper eTCHelper;
  @Autowired
  protected PayTransaction payTransaction;


  /**
   * 调用ETC银企直连接口 进行查询处理 用户绑定银行卡是否有效
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = QzException.class)
  public void Query_ETC_BankSign(ReturnDataNew returnData, Etc_userinfo etc_userinfo)
      throws QzException {
    //入库之前先检查该用户对该车辆进行该停车场预约下单没有   如果已经进行了预约下单则直接更新对应得数据
    try {
      //这里需要 调用ETC银企直连接口 进行查询处理----吴永平出
      boolean flag;//"接口返回查询成功"
      flag = lZFHelper.accountCheck(etc_userinfo.getOrderid(), etc_userinfo.getBank_card_number());
      if (flag) {
        Date date = new Date();
        //更新平台用户信息表中的默认签约银行卡表主键ID
        User_info userinfo = user_infoDao.selectByKey(etc_userinfo.getUi_id());
        if (userinfo == null) {
          returnData.setReturnData(errorcode_data, "用户不存在!", null);
          throw new QzException("用户不存在!");
        }
        //"接口返回查询成功"
        etc_userinfo.setVerify_sign(1);
        //调用ETC用户签约接口---待开发
        String pay_orderid = return16UUID();
        if (etc_userinfo.is_sign != 1) {
          ETCResponse eTCResponse = eTCHelper.toregister(
              pay_orderid
              , 0
              , userinfo.getUuid()
              , etc_userinfo.getName()
              , etc_userinfo.getSfz_number(),
              etc_userinfo.getBank_card_number());
          if (eTCResponse == null || !eTCResponse.isSucceed()) {
            log.error("ETC签约失败：" + eTCResponse);
            //签约失败
            etc_userinfo.setIs_sign(2);
          } else {
            //更新ETC用户绑定的银行卡等信息
            etc_userinfo.setUtime(date);
            //返还1分钱到钱包
            userinfo.setUi_vc(userinfo.ui_vc + 1);
            etc_userinfo.setIs_sign(1);//是否签约成功（0：没有签约 1：签约成功 2：签约失败 3：解除签约）
            etc_userinfo.setSigntime(date);
            if (userinfo.getEu_id() == 0) {
              //更新平台用户默认的ETC主键信息
              userinfo.setEu_id(etc_userinfo.getEu_id());
              userinfo.setEu_nd(etc_userinfo.getEu_nd());
              userinfo.setBank_no(etc_userinfo.getBank_card_number());
              //如果是没有设置默认卡的 就设定默认卡
              etc_userinfo.setIs_default(1);
            }
            int count = user_infoDao.updateByKey(userinfo);
            if (count != 1) {
              throw new QzException("更新平台用户默认的ETC主键信息失败");
            }
          }
          int count = etc_userinfoDao.updateByKey(etc_userinfo);
          if (count != 1) {
            throw new QzException("更新用户ETC验证银行账户有效失败");
          }

        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("Query_ETC_BankSign  is error" + e.getMessage(), e);
      throw new QzException("调用ETC银企直连接口 进行查询处理 用户绑定银行卡是否有效Query_ETC_BankSign失败");
    }
  }

  /**
   * 通过银行卡号和用户ID查询是否已经绑定过了该银行卡
   */
  public Etc_userinfo Query_ETC_UserByBankNo(long ui_id, String bank_no) {
    //入库之前先检查该用户对该车辆进行该停车场预约下单没有   如果已经进行了预约下单则直接更新对应得数据
    try {
      String sql = "SELECT * FROM etc_userinfo WHERE ui_id=? AND bank_card_number=?";
      return getMySelfService().queryUniqueT(sql, Etc_userinfo.class, ui_id, bank_no);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("Query_ETC_BankSign  is error" + e.getMessage(), e);
    }
    return null;
  }


  /**
   * 记录ETC扣款调用数据
   */
  public void recordETC_PayLog(Etc_userinfo etc_userinfo, String pay_orderid,
      Pay_park pay_park, int is_over, int state, int total_money, Date date) {
    try {
      Etc_userpay_record etc_pay = new Etc_userpay_record();
      etc_pay.setCtime(date);
      etc_pay.setPi_id(pay_park.getPi_id());
      etc_pay.setArea_code(pay_park.getArea_code());
      etc_pay.setPi_name(pay_park.getPi_name());
      etc_pay.setPu_id(pay_park.getPu_id());
      etc_pay.setPu_nd(pay_park.getPu_nd());
      etc_pay.setOrder_id(pay_park.getMy_order());
      etc_pay.setOrder_type(0);
      etc_pay.setMoney(total_money);
      etc_pay.setEu_id(etc_userinfo.getEu_id());
      etc_pay.setEu_nd(etc_userinfo.getEu_nd());
      etc_pay.setEur_nd(returnUUID());
      etc_pay.setIs_over(is_over);
      etc_pay.setPay_orderid(pay_orderid);
      etc_pay.setRefund_time(date);
      etc_pay.setState(state);
      etc_pay.setTrade_time(date);
      etc_pay.setUi_id(pay_park.getUi_id());
      etc_pay.setUi_nd(pay_park.getUi_nd());
      int id = etc_userpay_recordDao.insert(etc_pay);
      if (id < 1) {
        throw new QzException("扣款失败");
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("recordETC_PayLog is error", e);
    }
  }

  /**
   * 记录ETC扣款调用数据
   */
  public void recordETC_PayLog(Finger_userinfo_new finger_userinfo, String pay_orderid,
                               Pay_park pay_park, int is_over, int state, int total_money, Date date) {
    try {
      Etc_userpay_record etc_pay = new Etc_userpay_record();
      etc_pay.setCtime(date);
      etc_pay.setPi_id(pay_park.getPi_id());
      etc_pay.setArea_code(pay_park.getArea_code());
      etc_pay.setPi_name(pay_park.getPi_name());
      etc_pay.setPu_id(pay_park.getPu_id());
      etc_pay.setPu_nd(pay_park.getPu_nd());
      etc_pay.setOrder_id(pay_park.getMy_order());
      etc_pay.setOrder_type(0);
      etc_pay.setMoney(total_money);
      etc_pay.setEu_id(finger_userinfo.getFu_id());
      etc_pay.setEu_nd(finger_userinfo.getFu_nd());
      etc_pay.setNote("指纹ETC支付");
      etc_pay.setEur_nd(returnUUID());
      etc_pay.setIs_over(is_over);
      etc_pay.setPay_orderid(pay_orderid);
      etc_pay.setRefund_time(date);
      etc_pay.setState(state);
      etc_pay.setTrade_time(date);
      etc_pay.setUi_id(pay_park.getUi_id());
      etc_pay.setUi_nd(pay_park.getUi_nd());
      int id = etc_userpay_recordDao.insert(etc_pay);
      if (id < 1) {
        throw new QzException("扣款失败");
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("recordETC_PayLog is error", e);
    }
  }

  /**
   * ETC设置默认银行卡
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = QzException.class)
  public void etc_user_set_default(long ui_id, Long eu_id, User_info userinfo,
      Etc_userinfo etc_userinfo) throws Exception {
    try {
      getMySelfService()
          .update("update etc_userinfo set is_default=0 WHERE ui_id=? AND is_default=1", ui_id);
      int update1 = getMySelfService()
          .update("update etc_userinfo set is_default=1 WHERE eu_id=?", eu_id);
      if (update1 < 1) {
        throw new QzException("etc_user_set_default 失败");
      }
      //更新平台用户默认的ETC主键信息
      userinfo.setEu_id(eu_id);
      userinfo.setEu_nd(etc_userinfo.eu_nd);
      userinfo.setBank_no(etc_userinfo.getBank_card_number());
      int updateByKey = user_infoDao.updateByKey(userinfo);
      if (updateByKey < 1) {
        throw new QzException("etc_user_set_default 失败");
      }
    } catch (Exception e) {
      throw new QzException("etc_user_set_default 失败", e);
    }
  }

  /**
   * 用户退签ETC
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = QzException.class)
  public void ReturnETCUserDel(ReturnDataNew returnData, User_info userinfo,
      Etc_userinfo etc_userinfo) throws QzException {

    if (etc_userinfo.is_default == 1) {
      //更新平台用户默认的ETC主键信息
      userinfo.setEu_id(0);
      userinfo.setEu_nd("");
      userinfo.setBank_no("");
      if (user_infoDao.updateByKey(userinfo) < 1) {
        throw new QzException("解约失败");
      }
    }
    etc_userinfo.setIs_sign(3);
    // 删除该张银行卡签约
    etc_userinfo.setIs_default(0);//退签设置为非默认卡
    etc_userinfo.setIs_del(1);
    etc_userinfo.setUtime(new Date());
    int count = etc_userinfoDao.updateByKey(etc_userinfo);
    if (count < 1) {
      throw new QzException("解约失败");
    }


  }
/****************************下面是任务调度器处理的工具方法**************************************/
}
