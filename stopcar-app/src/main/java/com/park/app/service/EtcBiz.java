package com.park.app.service;

import com.alibaba.fastjson.JSONObject;
import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.app.action.v1.pay.LZF_PayAction;
import com.park.bean.Etc_userinfo;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_info;
import com.park.constants.Constants;
import com.park.exception.QzException;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.EtcPB;
import com.park.mvc.service.common.JpushPB;
import com.park.service.ETCHelper;
import com.park.service.etc.etcapi.ETCResponse;
import com.park.util.FileUtil;
import com.park.util.RequestUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * ETC的业务逻辑管理类
 *
 * @author jingxiaohu
 */
@Service
public class EtcBiz extends BaseBiz {

  @Autowired
  protected AppUserPayBiz appUserPayBiz;
  @Autowired
  protected EtcPB etcPB;

  @Autowired
  private JpushPB jpushPB;

  /**
   * 用户 ETC注册
   */
  public void ReturnETCUserRegister(ReturnDataNew returnData, int dtype,
      String tel, String name, String bank_card_number, int bank_type,
      String sfz_number, long ui_id, MultipartFile cardimg,
      String cardimgFileName, String cardimgContentType,
      int pay_type,
      int money, int version, int system_type,
      String subject, String ip, String token, String pub, int type) {
    // TODO Auto-generated method stub

    try {
      Etc_userinfo existEtc = getMySelfService().queryUniqueT("SELECT * FROM etc_userinfo WHERE ui_id=? AND is_del=0 AND is_sign=1",Etc_userinfo.class,ui_id);
      if (existEtc != null) {
        returnData.setReturnData(errorcode_data, "请先解绑已签约银行卡!", "");
        return;
      }

      JSONObject returnobj = new JSONObject();
      JSONObject lzfobj = null;
      //充值
      appUserPayBiz.lzf_charge(returnData, pay_type, ui_id, money, version, system_type,
          subject, ip, token, pub, type, null, 0, "", LZF_PayAction.getLZFNotify_url());
      if (!"0".equalsIgnoreCase(returnData.getErrorno())) {
        returnData.setReturnData(errorcode_data, "处理下单失败!", "");
        return;
      } else {
        if (!RequestUtil.checkObjectBlank(returnData.getData())) {
          lzfobj = JSONObject.parseObject(returnData.getData().toString());
        }
      }
      //验证是否成功进行下单
      if (lzfobj == null) {
        returnData.setReturnData(errorcode_data, "处理下单失败!", "");
        return;
      }

      //检查该用户是否存在
      User_info userinfo = user_infoDao.selectByKey(ui_id);
      if (userinfo == null) {
        returnData.setReturnData(errorcode_data, "用户不存在!", null);
        return;
      }
      /*String uuid = lzfobj.getString("ui_nd");
      tel = lzfobj.getString("tel");*/
      String order_id = lzfobj.getString("order_id");
      //首先验证该银行卡 该用户是否已经绑定过了
      Date date = new Date();
      Etc_userinfo etc_userinfo = etcPB.Query_ETC_UserByBankNo(ui_id, bank_card_number);
      if (etc_userinfo != null) {

        //by zzy 2017-06-23 如果用户已经签约，提示用户已经签约，无需再次签约
        if(1 == etc_userinfo.getIs_sign()){   //是否签约成功（0：没有签约 1：签约成功 2：签约失败 3：解除签约）
          returnData.setReturnData(errorcode_data, "银行卡已经被绑定", null);
          return;
        }

        etc_userinfo.setIs_del(0);
        etc_userinfo.setVerify_sign(0);
        etc_userinfo.setOrderid(order_id);
        etc_userinfo.setUtime(date);

        //by zzy 2017-06-26 当用户重新签约ETC时，修改用户姓名和身份证号
        etc_userinfo.setName(name);
        etc_userinfo.setSfz_number(sfz_number);

        int count = etc_userinfoDao.updateByKey(etc_userinfo);
        if (count != 1) {
          returnData.setReturnData(errorcode_data, "记录ETC用户基本信息失败", null);
          return;
        }
      } else {
        etc_userinfo = new Etc_userinfo();
        etc_userinfo.setEu_nd(returnUUID());
        etc_userinfo.setUi_id(ui_id);
        etc_userinfo.setUi_nd(userinfo.getUuid());
        etc_userinfo.setDiscard_time(date);
        etc_userinfo.setCtime(date);
        etc_userinfo.setUtime(date);
        etc_userinfo.setSigntime(date);

        etc_userinfo.setOrderid(order_id);

        if (!RequestUtil.checkObjectBlank(name)) {
          //开户行真实姓名
          etc_userinfo.setName(name);
        }
        if (!RequestUtil.checkObjectBlank(bank_type)) {
          //银行类型
          etc_userinfo.setBank_type(bank_type);
        }
        if (!RequestUtil.checkObjectBlank(bank_card_number)) {
          //银行卡卡号
          etc_userinfo.setBank_card_number(bank_card_number);
        }
        if (!RequestUtil.checkObjectBlank(userinfo.getUi_tel())) {
          //电话号码
          etc_userinfo.setUi_tel(userinfo.getUi_tel());
        }
        if (!RequestUtil.checkObjectBlank(sfz_number)) {
          //用户身份证号码
          etc_userinfo.setSfz_number(sfz_number);
        }

        if (!RequestUtil.checkObjectBlank(cardimg) && !RequestUtil
            .checkObjectBlank(cardimgFileName)) {
          //处理用户身份证问题
          //上传文件
          String url = FileUtil
              .uploadScaleAvatarImage(cardimg, cardimgFileName, Constants.USER_CARD,
                  Constants.CARD_WIDTH, Constants.CARD_HIGHT, tel);
          if (url == null) {
            returnData.setReturnData(errorcode_data, "上传用户身份证错误", null);
            return;
          }
          etc_userinfo.setSfz_img_url(url);
        }
        //新增记录ETC用户身份信息
        int id = etc_userinfoDao.insert(etc_userinfo);
        if (id < 1) {
          returnData.setReturnData(errorcode_data, "记录ETC用户基本信息失败", null);
          return;
        }
        etc_userinfo.setEu_id(id);
      }

      returnobj.put("lzf_info", lzfobj);
      returnobj.put("etc_userinfo", etc_userinfo);

      returnData.setReturnData(errorcode_success, "记录ETC用户基本信息成功", returnobj);
      return;

    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("EtcBiz ReturnETCUserRegister is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }
  }

  /**
   * 修改ETC用户信息
   */
  public void ReturnETCUserUpdate(ReturnDataNew returnData, int dtype, Long eu_id,
      String tel, String name, String bank_card_number, Integer bank_type,
      String sfz_number, long ui_id, MultipartFile cardimg,
      String cardimgFileName, String cardimgContentType) {
    // TODO Auto-generated method stub
    try {

      //获取ETC用户基本信息
      String sql = "SELECT * FROM etc_userinfo WHERE eu_id=? LIMIT 1";
      Etc_userinfo etc_userinfo = getMySelfService().queryUniqueT(sql, Etc_userinfo.class, eu_id);
      if (etc_userinfo == null) {
        returnData.setReturnData(errorcode_data, "该ETC用户不存在!", "");
        return;
      }
      if (!RequestUtil.checkObjectBlank(bank_type)) {
        //银行类型
        etc_userinfo.setBank_type(bank_type);
      }
      if (!RequestUtil.checkObjectBlank(name)) {
        //开户行真实姓名
        etc_userinfo.setName(name);
      }
      if (!RequestUtil.checkObjectBlank(bank_card_number)) {
        //银行卡卡号
        etc_userinfo.setBank_card_number(bank_card_number);
      }
      if (!RequestUtil.checkObjectBlank(tel)) {
        //电话号码
        etc_userinfo.setUi_tel(tel);
      }
      if (!RequestUtil.checkObjectBlank(sfz_number)) {
        //用户身份证号码
        etc_userinfo.setSfz_number(sfz_number);
      }

      if (!RequestUtil.checkObjectBlank(cardimg) && !RequestUtil
          .checkObjectBlank(cardimgFileName)) {
        //处理用户身份证问题
        //上传文件
        String url = FileUtil.uploadScaleAvatarImage(cardimg, cardimgFileName, Constants.USER_CARD,
            Constants.CARD_WIDTH, Constants.CARD_HIGHT, etc_userinfo.getUi_tel());
        if (url == null) {
          returnData.setReturnData(errorcode_data, "上传用户身份证错误", null);
          return;
        }
        etc_userinfo.setSfz_img_url(url);
      }

      //新增记录ETC用户身份信息
      int count = etc_userinfoDao.updateByKey(etc_userinfo);
      if (count < 1) {
        returnData.setReturnData(errorcode_data, "记录ETC用户基本信息失败", null);
        return;
      }
      returnData.setReturnData(errorcode_success, "记录ETC用户基本信息成功", etc_userinfo);
      return;

    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("EtcBiz ReturnETCUserUpdate is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }
  }

  @Autowired
  private ETCHelper etcHelper;

  /**
   * 用户退签ETC
   */
  public void ReturnETCUserDel(ReturnDataNew returnData, int dtype,
      Long eu_id, long ui_id) {
    // TODO Auto-generated method stub
    try {
      //检查该用户是否存在
      User_info userinfo = user_infoDao.selectByKey(ui_id);
      if (userinfo == null) {
        returnData.setReturnData(errorcode_data, "用户不存在!", null);
        return;
      }
      //获取ETC用户基本信息
      String sql = "SELECT * FROM etc_userinfo WHERE eu_id=? LIMIT 1";
      Etc_userinfo etc_userinfo = getMySelfService().queryUniqueT(sql, Etc_userinfo.class, eu_id);
      if (etc_userinfo == null) {
        returnData.setReturnData(errorcode_data, "该条数据不存在!", "");
        return;
      }
      if (etc_userinfo.getUi_id() != ui_id) {
        returnData.setReturnData(errorcode_data, "亲，您不能删除他人绑定的银行卡信息!", null);
        return;
      }
      try {
        ETCResponse etcResponse = etcHelper.toregister(return16UUID()
            , 1
            , userinfo.getUuid()
            , etc_userinfo.getName()
            , etc_userinfo.getSfz_number(),
            etc_userinfo.getBank_card_number());
        if (etcResponse == null || !etcResponse.isSucceed()) {
          //签约失败
          throw new QzException("解约失败");
        }
        etcPB.ReturnETCUserDel(returnData, userinfo, etc_userinfo);
        returnData.setReturnData(errorcode_success, "删除银行卡签约成功", etc_userinfo);
      } catch (QzException e) {
        log.error(e.getMessage(), e);
        returnData.setReturnData(errorcode_data, "删除银行卡签约失败", null);
      }

      return;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("EtcBiz ReturnETCUserDel is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }
  }


  /**
   * 获取用户绑定的ETC银行卡
   */
  @TargetDataSource(DynamicDataSourceHolder.SLAVE)
  public void read_etc_user(ReturnDataNew returnData, int dtype, long ui_id) {
    try {
      String sql = "select * from etc_userinfo where ui_id=? and is_sign=1 and is_del=0 order by is_default desc,signtime desc";
      List<Etc_userinfo> etc_userinfos = getMySelfService()
          .queryListT(sql, Etc_userinfo.class, ui_id);
      returnData.setReturnData(errorcode_success, "获取用户绑定的ETC银行卡成功", etc_userinfos);
    } catch (Exception e) {
      log.error("com.park.mvc.v1.biz.EtcBiz.read_etc_user is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


  /**
   * ETC设置默认银行卡
   */
  public void etc_user_set_default(ReturnDataNew returnData, int dtype, long ui_id, Long eu_id) {
    try {
      //检查该用户是否存在
      User_info userinfo = user_infoDao.selectByKey(ui_id);
      if (userinfo == null) {
        returnData.setReturnData(errorcode_data, "用户不存在!", null);
        return;
      }
      String sql = "SELECT * FROM etc_userinfo WHERE eu_id=? LIMIT 1";
      Etc_userinfo etc_userinfo = getMySelfService()
          .queryUniqueT(sql, Etc_userinfo.class, eu_id);
      if (etc_userinfo == null) {
        returnData.setReturnData(errorcode_data, "银行卡不存在!", "");
        return;
      }
      if (etc_userinfo.getUi_id() != ui_id) {
        returnData.setReturnData(errorcode_data, "亲，您不能设置他人绑定的银行卡信息!", null);
        return;
      }
      if (etc_userinfo.getIs_sign() != 1) {
        returnData.setReturnData(errorcode_data, "亲，您的银行卡需要重新签约", null);
        return;
      }
      try {
        if (etc_userinfo.is_default != 1) {
          etcPB.etc_user_set_default(ui_id, eu_id, userinfo, etc_userinfo);
        }
        returnData.setReturnData(errorcode_success, "ETC设置默认银行卡成功", null);
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        returnData.setReturnData(errorcode_systerm, "ETC设置默认银行卡失败", null);
      }
    } catch (Exception e) {
      log.error("com.park.mvc.v1.biz.EtcBiz.etc_user_set_default is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }


  /**
   * 检查ETC用户 是否签约成功
   */
  public void etc_checksign(ReturnDataNew returnData, int dtype, long eu_id, long ui_id) {
    // TODO Auto-generated method stub
    try {
      JSONObject returnobj = new JSONObject();
      returnobj.put("state", 0);//0：未签约  1：签约成功

      String sql = "SELECT * FROM etc_userinfo WHERE eu_id=? LIMIT 1";
      Etc_userinfo etc_userinfo = getMySelfService().queryUniqueT(sql, Etc_userinfo.class, eu_id);
      if (etc_userinfo == null) {
        returnData.setReturnData(errorcode_data, "银行卡不存在!", "");
        return;
      }
      if (etc_userinfo.getUi_id() != ui_id) {
        returnData.setReturnData(errorcode_data, "亲，您不能设置他人绑定的银行卡信息!", null);
        return;
      }
      //这里需要 调用ETC银企直连接口 进行查询处理----吴永平出
      try {
        etcPB.Query_ETC_BankSign(returnData, etc_userinfo);
      } catch (Exception e) {
        // TODO Auto-generated catch block
      }

      //state 0：未签约  1：签约成功
      if (etc_userinfo.is_sign == 1) {
        returnobj.put("state", 1);//1：签约成功
        //推送签约消息
        pushEtcSignResult(etc_userinfo);

      } else {
        returnobj.put("state", 0);//0：未签约
      }
      returnData.setReturnData(errorcode_success, "查询成功", returnobj);

    } catch (Exception e) {
      log.error("com.park.mvc.v1.biz.EtcBiz.etc_checksign is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
    }
  }

  /**
   * 建行ETC签约信息推送
   * @param userinfo
   */
  private void pushEtcSignResult(Etc_userinfo userinfo){
    String title = "系统消息";
    String uiNd = userinfo.getUi_nd();
    String message = "恭喜您，银行卡号 "+hideBankCard(userinfo.getBank_card_number())+" 签约成功！";
    jpushPB.pushSystem(uiNd,message,title);
    log.info("签约成功，推送用户编号："+uiNd);
  }

  /**
   * 银行卡号只显示前两位和后四位,其余*代替
   * @param str
   * @return
   */
  public String hideBankCard(String str) {
    if (null == str || "".equals(str)) {
      return null;
    }
    String regx = "(?<=\\d{4})\\d(?=\\d{4})";
    return str.replaceAll(regx, "*");
  }

}
