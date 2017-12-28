package com.park.app.service;

import com.alibaba.fastjson.JSONObject;
import com.park.bean.ReturnDataNew;
import com.park.bean.Sms_running;
import com.park.bean.Sms_validate;
import com.park.bean.User_info;
import com.park.mvc.service.BaseBiz;
import com.park.util.SMSUtil;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

/**
 * 用户信息管理
 *
 * @author jingxiaohu
 */
@Service
public class SMSBiz extends BaseBiz {

//	@Autowired
//	protected MailBiz mailBiz;

  /**
   * 发送验证码/重发验证码
   */
  public void ReturnSendSMg(ReturnDataNew returnData, int dtype,
      String telephone, String vclass) {

    //验证码 6位数字
    String verify_code = RandomStringUtils.random(6, false, true);
    //验证码的MAD5
    String verify_list = DigestUtils.md5Hex(telephone + verify_code);
    try {
      //1：注册 2：重置密码 3:重置绑定电话号码  4：绑定银行卡  5：指纹数据采集验证
      if ("1".equalsIgnoreCase(vclass)) {
        //用户注册
        //检查该手机号码是否已经注册
        User_info userinfo = gainUserInfoByTelePhone(telephone);
        if (userinfo != null) {
          returnData.setReturnData(errorcode_data, "您已经注册过了", null);
          return;
        }
      } else if ("2".equalsIgnoreCase(vclass)) {
        //修改密码
        //检查该用户是否已经注册过 如果没有则不能进行修改密码
        User_info userinfo = gainUserInfoByTelePhone(telephone);
        if (userinfo == null) {
          returnData.setReturnData(errorcode_data, "您还没有注册!", null);
          return;
        }
      } else if ("3".equalsIgnoreCase(vclass)) {
        //修改手机
        //检查该用户是否已经注册过 如果没有则不能进行修改手机
        User_info userinfo = gainUserInfoByTelePhone(telephone);
        if (userinfo == null) {
          returnData.setReturnData(errorcode_data, "您还没有注册!", null);
          return;
        }
      } else if("4".equals(vclass) || "5".equals(vclass)){
        // 4：绑定银行卡  5：指纹数据采集验证

      }else {
        returnData.setReturnData(errorcode_data, "请选择发送类别!", null);
        return;
      }

      //向by_sms_validate表中插入一条数据
      Date date = new Date();
      Sms_validate bsv = new Sms_validate();
      bsv.setV_code(verify_code);
      bsv.setV_class(vclass);
      bsv.setV_list(verify_list);
      bsv.setV_tel(telephone);
      bsv.setV_time(date.getTime());
      bsv.setV_time_str(sf.format(date));
      int id = sms_validateDao.insert(bsv);
      if (id > 0) {
        //插入成功 可以进行短信验证码发送
        //短信验证码发送成功 则把这条数据写入数据库中的by_sms_running
        Sms_running bsr = SMSUtil.sendMsg(verify_code, telephone, vclass);//调用发送API
        if (bsr != null) {
          //调用短信第三方API成功
          int xx = sms_runningDao.insert(bsr);
          if (xx > 0) {
            JSONObject obj = new JSONObject();
            obj.put("tel", telephone);
            obj.put("verify_list", verify_list);
            obj.put("resend_time", "120");//重发时间
            returnData.setReturnData(errorcode_success, "发送成功!", obj);
            return;
          }
        }
      }
      returnData.setReturnData(errorcode_data, "验证码发送失败", null);
      return;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("SMSBiz ReturnSendSMg is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }


  }

  /**
   * 重新发送验证码接口
   */
  public void ReturnReSendSMg(ReturnDataNew returnData, int dtype,
      String telephone, String vclass, String verify_list) {
    try {
      //1：注册 2：重置密码 3:重置绑定电话号码  4：绑定银行卡  5：指纹数据采集验证
      if ("1".equalsIgnoreCase(vclass)) {
        //用户注册
        //检查该手机号码是否已经注册
        User_info userinfo = gainUserInfoByTelePhone(telephone);
        if (userinfo != null) {
          returnData.setReturnData(errorcode_data, "您已经注册过了", null);
          return;
        }
      } else if ("2".equalsIgnoreCase(vclass)) {
        //修改密码
        //检查该用户是否已经注册过 如果没有则不能进行修改密码
        User_info userinfo = gainUserInfoByTelePhone(telephone);
        if (userinfo == null) {
          returnData.setReturnData(errorcode_data, "您还没有注册!", null);
          return;
        }
      } else if ("3".equalsIgnoreCase(vclass)) {
        //修改手机
        //检查该用户是否已经注册过,如果没有则不能进行修改手机
        User_info userinfo = gainUserInfoByTelePhone(telephone);
        if (userinfo == null) {
          returnData.setReturnData(errorcode_data, "您还没有注册!", null);
          return;
        }
      } else if("4".equals(vclass) || "5".equals(vclass)){
        // 4：绑定银行卡 5：指纹数据采集验证

      }else {
        returnData.setReturnData(errorcode_data, "请选择发送类别!", null);
        return;
      }

      //检查时间是否过期
      String verify_code = null;
      String sql = "SELECT * FROM sms_validate WHERE v_tel = ? AND v_list = ? AND v_class = ? LIMIT 1";
      Sms_validate bsv = getMySelfService()
          .queryUniqueT(sql, Sms_validate.class, telephone, verify_list, vclass);
      if (bsv == null) {
        returnData.setReturnData(errorcode_data, "验证发送失败", null);
        log.error("if(bsv == null) 数据库中没有查询到该手机上一次发送的验证码");
        return;
      }

      long time = bsv.getV_time();
      if (System.currentTimeMillis() - time < 2 * 60 * 1000) {
        returnData.setReturnData(errorcode_data, "请间隔120秒后发送验证码!", null);
        return;
      } else {
        Date date = new Date();
        if (System.currentTimeMillis() - time > 15 * 60 * 1000) {
          //超过15分钟进行重新生成验证码
          //验证码 6位数字
          verify_code = RandomStringUtils.random(6, false, true);
          //验证码的MAD5
          verify_list = DigestUtils.md5Hex(verify_code);

          //向by_sms_validate表中插入一条数据
          bsv = new Sms_validate();
          bsv.setV_code(verify_code);
          bsv.setV_class(vclass);
          bsv.setV_list(verify_list);
          bsv.setV_tel(telephone);
          bsv.setV_time(date.getTime());
          bsv.setV_time_str(sf.format(date));

          int id = sms_validateDao.insert(bsv);
          if (id < 1) {
            //插入by_sms_validate 失败
            returnData.setReturnData(errorcode_data, "验证码发送失败", null);
            log.error("int id = sms_validateDao.insert(bsv)失败");
            return;
          }
        } else {
          //没超过15分钟 则进行更新之前的数据就可以
          verify_code = bsv.getV_code();
          //更新数据库中 该手机这条验证码发送的时间
          bsv.setV_time(date.getTime());
          int count = sms_validateDao.updateByKey(bsv);
          if (count != 1) {
            //更新时间失败
            returnData.setReturnData(errorcode_data, "验证码发送失败", null);
            log.error("by_sms_validateDao.updateByKey(bsv)更新时间失败");
            return;
          }
        }
      }

      //发送验证码
      //短信验证码发送成功 则把这条数据写入数据库中的by_sms_running
      Sms_running bsr = SMSUtil.sendMsg(verify_code, telephone, vclass);//调用发送API
      if (bsr != null) {
        //调用短信第三方API成功
        int xx = sms_runningDao.insert(bsr);
        if (xx > 0) {
          JSONObject obj = new JSONObject();
          obj.put("tel", telephone);
          obj.put("verify_list", verify_list);
          obj.put("resend_time", "120");
          returnData.setReturnData(errorcode_success, "发送成功!", obj);
          return;
        }
      }
      returnData.setReturnData(errorcode_data, "验证码发送失败", null);
      return;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("SMSBiz ReturnReSendSMg is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }
  }
  /**
   * 通过邮箱找回密码
   * @param returnData
   * @param dtype
   * @param email
   */
//	public void ReturnFindPassWord_mail(ReturnDataNew returnData, int dtype,
//			String email) {
//		// TODO Auto-generated method stub
//		try {
//			User_info userinfo  = gainUserInfoByEmail(email);
//			if(userinfo == null){
//				returnData.setReturnData(errorcode_success, "发送成功", ""); 
//				return ;
//			}
//			Map<String, Object>  map = new HashMap<String, Object>();
//			map.put("password", userinfo.getUi_password_show());
//			String mailTo[] = { email };
//			// 发送邮件
//			if (mailBiz.sendEmail(map, "股掌","find_password.vm", mailTo, null)) {
//				// 发送成功
//				returnData.setReturnData(errorcode_success, "发送成功", "");
//				return ;
//			} else {
//				// 发送失败
//				returnData.setReturnData(errorcode_param, "发送失败","");
//				return ;
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			log.error("SMSBiz ReturnFindPassWord_mail is error", e);
//			returnData.setReturnData(errorcode_systerm, "system is error", null); 
//			return ;
//		}
//	}


  /**
   * 获取用户信息
   */
  public User_info gainUserInfoByTelePhone(String telephone) throws Exception {
    //获取该串号的物料码对应的SD卡是否存在
    try {
      String sql = "SELECT * FROM user_info WHERE ui_tel=? LIMIT 1";
      return getMySelfService().queryUniqueT(sql, User_info.class, telephone);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("gainUserInfoByTelePhone(String telephone)  telephone=" + telephone + "  is error",
          e);
      throw new Exception("gainUserInfoByTelePhone 获取用户信息失败", e);
    }
  }


}
