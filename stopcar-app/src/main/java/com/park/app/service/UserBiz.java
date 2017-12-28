package com.park.app.service;

import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.ReturnDataNew;
import com.park.bean.Sms_validate;
import com.park.bean.User_feedback;
import com.park.bean.User_info;
import com.park.constants.Constants;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.ActivityPB;
import com.park.mvc.service.common.asyn.UserAsyn;
import com.park.util.FileUtil;
import com.park.util.RequestUtil;
import java.net.URLDecoder;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户信息管理
 *
 * @author jingxiaohu
 */
@Service
public class UserBiz extends BaseBiz {

  @Autowired
  protected ActivityPB activityPB;
  @Autowired
  protected UserAsyn userAsyn;


  /**
   * 2.1.4. 用户注册
   *
   * @param verify_list :跟随验证码一起生产的 MD5验证串
   * @param repassword return { "errorno": "0", "errormsg": "注册成功", "data": { "username":
   * "15882345448", "uid": "118", "password": "14e1b600b1fd579f47433b88e8d85291", "niname":
   * "xdfzgdmx118", "toppic": "" }
   */
  public void ReturnUserRegister(ReturnDataNew returnData, int dtype, String telephone,
      String verify_code, String verify_list, String vclass, String password,
      String repassword) {
    // TODO Auto-generated method stub
    try {
      //从什么设备发出的请求  1:android 2:ios 3:web
      //进行验证码的验证
      String sql = "SELECT v_code,v_time FROM sms_validate WHERE v_tel = ? AND v_list = ? AND v_class = ? AND v_code=? LIMIT 1";
      Sms_validate bsv = getMySelfService()
          .queryUniqueT(sql, Sms_validate.class, telephone, verify_list, vclass, verify_code);
      if (bsv == null) {
        returnData.setReturnData(errorcode_data, "验证码错误", "");
        return;
      }
      //检查时间是否过期 6分钟
      long time = bsv.getV_time();
      if (System.currentTimeMillis() - time > 6 * 60 * 1000) {
        returnData.setReturnData(errorcode_data, "验证码过期", "");
        return;
      }
      //检查该手机号码是否已经注册
      User_info userinfo = gainUserInfoByTelePhone(telephone);
      if (userinfo != null) {
        returnData.setReturnData(errorcode_data, "您已经注册过了", "");
        return;
      }

      //写入用户信息到用户基本信息表中
      userinfo = new User_info();
      userinfo.setUuid(returnUserRegisterUUID());
      Date date = new Date();
      userinfo.setUi_tel(telephone);
      //绑定手机号（注册的时候默认是注册手机号码）
      userinfo.setBind_tel(telephone);
      userinfo.setUi_password(password);
      userinfo.setCtime(date);
      //注册来源 0:android 1:ios 2: web 3:PC
      userinfo.setUi_flag(dtype);
      //未知
      userinfo.setUi_sex("no");

//			userinfo.setUi_nickname(RandomStringUtils.random(8, true, true));//用户昵称
      userinfo.setUi_nickname("");//用户昵称
      //电话号码+密码 生成的MD5码 （授权码）
      userinfo.setUi_token(DigestUtils.md5Hex(telephone + password));
      int userid = user_infoDao.insert(userinfo);
      if (userid < 1) {
        returnData.setReturnData(errorcode_data, "注册失败", null);
        return;
      }
      userinfo.setUi_id(userid);
      /**
       * 注册成功则查看赠券活动是否结束 如果没有 则进行赠券活动奖励发放
       */
      asyncOrderTask.recordEvent_coupon_register(
          null,
          1,
          0,
          null,
          null,
          1,
          0,
          null,
          userinfo.getUi_id(),
          userinfo.getUuid(),
          getMySelfService(), activityPB
      );
      /**
       * 返回数据
       */
      returnData.setReturnData(errorcode_success, "注册成功", userinfo);
      return;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("UserBiz ReturnUserRegister is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }
  }

  /**
   * 用户登录
   */
  public void ReturnUserLogin(ReturnDataNew returnData, int dtype,
      String telephone, String password, String ip) {
    // TODO Auto-generated method stub dtype,tel,password,tel_version,ip
    try {
      User_info userinfo = null;
      //普通登陆
      //检查该用户是否已经注册过 如果没有则不能进行修改密码
      userinfo = gainUserInfoByTelePhone(telephone);
      if (userinfo == null) {
        returnData.setReturnData(errorcode_data, "您还没有注册!", null);
        return;
      }
      if (1 == userinfo.getUi_state()) {
        //禁用
        returnData.setReturnData(errorcode_data, "用户已禁用", null);
        return;
      }
      if (!password.equalsIgnoreCase(userinfo.getUi_password())) {
        //密码不正确
        returnData.setReturnData(errorcode_data, "密码错误", null);
        return;
      }

      //这里调用异步 用户登录日志记录
      userAsyn.Log_recordUserLogin(userinfo, dtype, ip, returnData);

      //返回数据
      returnData.setReturnData(errorcode_success, "登录成功", userinfo);
      return;

    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("UserBiz ReturnUserLogin is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }

  }

  /**
   * 用户修改密码、重置密码
   */
  public void ReturnUserModifyPassword(ReturnDataNew returnData,
      int dtype, String telephone, String verify_code, String verify_list, String vclass,
      String password, String repassword) {
    // TODO Auto-generated method stub
    //dtype,tel,verify_code,verify_list,password,repassword,imei,tel_version
    try {
      //检查该手机号码是否已经注册
      User_info userinfo = gainUserInfoByTelePhone(telephone);
      if (userinfo == null) {
        returnData.setReturnData(errorcode_data, "您还没有注册!", null);
        return;
      }
      //从什么设备发出的请求1-收音机2-手机APP 3-WEB
      //进行验证码的验证
      String sql = "SELECT v_code,v_time FROM sms_validate WHERE v_tel = ? AND v_list = ? AND v_class = ? AND v_code=?  LIMIT 1";
      Sms_validate bsv = getMySelfService()
          .queryUniqueT(sql, Sms_validate.class, telephone, verify_list, vclass, verify_code);
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
      Date date = new Date();
      //更新用户密码
      userinfo.setUi_password(password);
      //电话号码+密码 生成的MD5码 （授权码）
      userinfo.setUi_token(DigestUtils.md5Hex(telephone + password));
      userinfo.setUtime(date);
      int count = user_infoDao.updateByKey(userinfo);
      if (count < 1) {
        returnData.setReturnData(errorcode_data, "更改密码失败", null);
        return;
      }

      returnData.setReturnData(errorcode_success, "更改密码成功", userinfo);
      return;

    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("UserBiz ReturnUserModifyPassword is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }
  }

  /**
   * 修改用户基本信息
   */
  public void change_userinfo(ReturnDataNew returnData, int dtype,
      long ui_id, MultipartFile avatar, String avatarFileName, String nickname,
      String sex, String name, String driving_licence, String email, String ui_autopay,
      int pay_source, Integer etc_autopay) {
    // TODO Auto-generated method stub ui_autopay;//

    try {
      //检查该手机号码是否已经注册
      User_info userinfo = user_infoDao.selectByKey(ui_id);
      if (userinfo == null) {
        returnData.setReturnData(errorcode_data, "用户不存在!", null);
        return;
      }
      if (!RequestUtil.checkObjectBlank(nickname)) {
        //昵称
        userinfo.setUi_nickname(nickname);
      }
      if (!RequestUtil.checkObjectBlank(name)) {
        //姓名
        name = URLDecoder.decode(name, Constants.SYSTEM_CHARACTER);
        userinfo.setUi_name(name);
      }
      if (!RequestUtil.checkObjectBlank(driving_licence)) {
        //驾驶证
        userinfo.setDriving_licence(driving_licence);
      }
      if (!RequestUtil.checkObjectBlank(sex)) {
        //性别
        userinfo.setUi_sex(sex);
      }
      if (!RequestUtil.checkObjectBlank(email)) {
        //邮箱
        userinfo.setUi_email(email);
      }

      if (!RequestUtil.checkObjectBlank(ui_autopay)) {
        //是否自动支付 是否自动支付 ：0 ：不是 1：是
        userinfo.setUi_autopay(Integer.parseInt(ui_autopay));
      }
      if (pay_source > 0) {
        //设置用户默认支付方式
        userinfo.setPay_source(pay_source);
      }

      if (!RequestUtil.checkObjectBlank(avatar) && !RequestUtil.checkObjectBlank(avatarFileName)) {
        //处理用户头像问题
        //上传文件
        String url = FileUtil.uploadScaleAvatarImage(avatar, avatarFileName, Constants.USER_AVATAR,
            Constants.AVATAR_WIDTH, Constants.AVATAR_HIGHT, userinfo.getUi_tel());
        if (url == null) {
          returnData.setReturnData(errorcode_data, "上传头像错误", null);
          return;
        }
        userinfo.setUi_avatar(url);
      }
      if (!RequestUtil.checkObjectBlank(etc_autopay)) {
        //是否开启ETC自动支付  ：0 ：不开启  1：开启
        userinfo.setEtc_autopay(etc_autopay);
      }
      //更新用户的昵称
      int count = user_infoDao.updateByKey(userinfo);
      if (count < 1) {
        returnData.setReturnData(errorcode_data, "更改用户基本信息失败", null);
        return;
      }
      returnData.setReturnData(errorcode_success, "更改用户基本信息成功", userinfo);
      return;

    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("UserBiz ReturnUserUpdateUserInfo is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }
  }

  /**
   * 修改用户绑定手机
   */
  public void ReturnUserUpdateTel(ReturnDataNew returnData, int dtype, String tel, String password,
      long ui_id) {
    // TODO Auto-generated method stub
    try {
      //检查该用户是否已经注册过了的
      String sql = "SELECT * FROM user_info WHERE ui_id=? AND ui_password=? LIMIT 1";
      User_info userinfo = getMySelfService().queryUniqueT(sql, User_info.class, ui_id, password);
      if (userinfo == null) {
        //用户密码错误
        returnData.setReturnData(errorcode_data, "用户密码错误", null);
        return;
      }
      //检查该手机号码是否已经注册 或者 被绑定过
      User_info userinfo2 = gainUserInfoByTel_bind(tel);
      if (userinfo2 != null && ui_id != userinfo2.getUi_id()) {
        returnData.setReturnData(errorcode_data, "该手机号码已经被注册!", null);
        return;
      }
      userinfo.setBind_tel(tel);
      //更新用户的绑定手机号码
      int count = user_infoDao.updateByKey(userinfo);
      if (count < 1) {
        returnData.setReturnData(errorcode_data, "绑定手机号码失败", null);
        return;
      }

      returnData.setReturnData(errorcode_success, "绑定手机号码成功", userinfo);
      return;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("UserBiz ReturnUserUpdateTel is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }
  }

  /**
   * 获取我自己的基本信息
   */
  @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
  public void ReturnReadMyInfo(ReturnDataNew returnData, int dtype, long ui_id) {
    // TODO Auto-generated method stub
    try {
      //检查该手机号码是否已经注册
      User_info userinfo = user_infoDao.selectByKey(ui_id);
      if (userinfo == null) {
        returnData.setReturnData(errorcode_data, "用户不存在!", null);
        return;
      }
      returnData.setReturnData(errorcode_success, "读取我的信息成功", userinfo);
      return;

    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("UserBiz ReturnReadMyInfo is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }
  }

  /**
   * 用户反馈
   */
  public void feedback(ReturnDataNew returnData, long ui_id, String content) {
    // TODO Auto-generated method stub
    try {
      //检查用户是否存在
      User_info userinfo = user_infoDao.selectByKey(ui_id);
      if (userinfo == null) {
        returnData.setReturnData(errorcode_data, "用户不存在!", null);
        return;
      }
      //记录反馈信息
      Date date = new Date();
      User_feedback user_feedback = new User_feedback();
      user_feedback.setContent(content);
      user_feedback.setUi_id(ui_id);
      user_feedback.setCtime(date);
      user_feedback.setUtime(date);
      int id = user_feedbackDao.insert(user_feedback);
      if (id < 1) {
        returnData.setReturnData(errorcode_data, "用户反馈失败", "");
        return;
      }
      returnData.setReturnData(errorcode_success, "用户反馈成功", "");
      return;

    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("UserBiz feedback is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }
  }

  /**********************下面是分离的方法************************/
  /**
   * 检查该手机号码是否已经被其他人注册过了
   */
  public User_info gainUserInfoByTelePhone(String telephone) throws Exception {
    try {
      String sql = "SELECT * FROM user_info WHERE ui_tel=? LIMIT 1";
      return getMySelfService().queryUniqueT(sql, User_info.class, telephone);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("gainUserInfoByTelePhone(String telephone)  telephone=" + telephone + "  is error",
          e);
      throw new Exception("gainUserInfoByTelePhone 检查该手机号码是否已经被其他人注册过了 失败", e);
    }
  }

  /**
   * 检查该手机号码是否已经被其他人注册过了 或者  绑定过了
   */
  public User_info gainUserInfoByTel_bind(String telephone) throws Exception {
    try {
      String sql = "SELECT * FROM user_info WHERE ui_tel=? AND bind_tel=? LIMIT 1";
      return getMySelfService().queryUniqueT(sql, User_info.class, telephone);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("gainUserInfoByTelePhone(String telephone)  telephone=" + telephone + "  is error",
          e);
      throw new Exception("gainUserInfoByTelePhone 检查该手机号码是否已经被其他人注册过了 失败", e);
    }
  }

	/**
	 * 人脸识别图片提取
	 * @param avatar 人脸识别图片
	 * @param avatarFileName 人脸识别图片文件名
	 */
	public void recodeUserFaceImg(ReturnDataNew returnData,long ui_id,MultipartFile avatar, String avatarFileName) {
		try {
			//检查用户是否存
			User_info userinfo = user_infoDao.selectByKey(ui_id);
	        if (userinfo == null) {
		        returnData.setReturnData(errorcode_data, "用户不存在!", null);
		        return;
	        }
			
			if (!RequestUtil.checkObjectBlank(avatar) && !RequestUtil.checkObjectBlank(avatarFileName)) {
		        //上传文件
		        String imgUrl = FileUtil.uploadScaleAvatarImage(avatar, avatarFileName, Constants.USER_FACE,
		            0, 0, userinfo.getUi_tel());
		        if (imgUrl == null) {
		            returnData.setReturnData(errorcode_data, "上传人脸识别图片错误", null);
		            return;
		        }
		        userinfo.setUi_face_imgs(imgUrl);
		        userinfo.setUi_face_state(1);
		    }
			
			//人脸识别图片提取
		    int ret = user_infoDao.updateByKey(userinfo);
		    if (ret < 1) {
		        returnData.setReturnData(errorcode_data, "人脸识别图片提取失败", null);
		        return;
		    }
		    //返回数据
		    returnData.setReturnData(errorcode_success, "人脸识别图片提取成功", userinfo);
		    return;
		} catch (Exception e) {
		    log.error("UserBiz recodeUserFaceImg is error", e);
		    returnData.setReturnData(errorcode_systerm, "system is error", null);
		    return;
	    }
	}

	/**
	 * 修改用户 人脸识别开关状态
	 * @param returnData
	 * @param ui_id
	 */
	public void updateUserFaceState(ReturnDataNew returnData, long ui_id,Integer ui_face_state) {
		try {
			//检查用户是否存
			User_info userinfo = user_infoDao.selectByKey(ui_id);
	        if (userinfo == null) {
		        returnData.setReturnData(errorcode_data, "用户不存在!", null);
		        return;
	        }
	        
	        if(ui_face_state > 2){
	        	returnData.setReturnData(errorcode_data, "状态值不正确", null);
		        return;
	        }
	        
	        userinfo.setUi_face_state(ui_face_state);
			
			//人脸识别开关状态修改
		    int ret = user_infoDao.updateByKey(userinfo);
		    if (ret < 1) {
		        returnData.setReturnData(errorcode_data, "人脸识别开关状态修改失败", null);
		        return;
		    }
		    //返回数据
		    returnData.setReturnData(errorcode_success, "人脸识别开关状态修改成功", userinfo);
		    return;
		} catch (Exception e) {
		    log.error("UserBiz updateUserFaceState is error", e);
		    returnData.setReturnData(errorcode_systerm, "system is error", null);
		    return;
	    }
	}

}
