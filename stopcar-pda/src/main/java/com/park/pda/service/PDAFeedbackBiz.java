package com.park.pda.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_feedback;
import com.park.dao.User_feedbackDao;
import com.park.mvc.service.BaseBiz;

/**
 * pda用户信息管理
 *
 * @author zyy
 */
@Service
public class PDAFeedbackBiz extends BaseBiz {

	  @Autowired
	  protected User_feedbackDao user_feedbackDao;
	
	 /**
	   * 用户反馈
	   */
	  public void pda_feedback(ReturnDataNew returnData, String content,long pi_id,String area_code,String pi_name,long pda_id) {
	    // TODO Auto-generated method stub
	    try {
	      //记录反馈信息
	      Date date = new Date();
	      User_feedback user_feedback = new User_feedback();
	      user_feedback.setContent(content);
	      user_feedback.setCtime(date);
	      user_feedback.setUtime(date);
	      user_feedback.setNote("");
	      user_feedback.setType(1);
	      user_feedback.setPi_id(pi_id);
	      user_feedback.setArea_code(area_code);
	      user_feedback.setPi_name(pi_name);
	      user_feedback.setPda_id(pda_id);
	      
	      int ret = user_feedbackDao.insert(user_feedback);
	      if (ret < 1) {
	          returnData.setReturnData(errorcode_data, "pda用户反馈失败", "");
	          return;
	      }
	      returnData.setReturnData(errorcode_success, "pda用户反馈成功", "");
	      return;

	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      log.error("PDAFeedbackBiz pda_feedback is error", e);
	      returnData.setReturnData(errorcode_systerm, "system is error", null);
	      return;
	    }
	  }

}
