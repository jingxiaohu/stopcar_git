
package com.park.pda.action.v1.pda;

import java.net.URLDecoder;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.pda.param.Param_pda_feedback;
import com.park.pda.service.PDAFeedbackBiz;
import com.park.util.RequestUtil;

/**
 * 用户反馈
 *
 * @author zyy
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_PDA_FeedbackAction extends BaseV1Controller {


  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;

  @Autowired
  private PDAFeedbackBiz userBiz;

/**
 * 用户反馈
 * PDA管理员提交一些反馈信息到服务器（占道停车场）
 * @param response
 * @param param
 * @return
 */
@RequestMapping(value = "/pda_feedback")
  @ResponseBody
  public String pda_feedback(HttpServletResponse response, Param_pda_feedback param) {
    ReturnDataNew returnData = new ReturnDataNew();
    try {
    	//参数检查和签名验证
		if(!checkParam(param,returnData)){
	        sendResp(returnData, response);
	        return null;
	    }
    	
        userBiz.pda_feedback(returnData, param.content,param.pi_id,param.area_code,param.pi_name,param.pda_id);

    } catch (Exception e) {
      log.error("Write_PDA_FeedbackAction pda_feedback  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }

  /**
	 * 参数检查和签名验证
	 * @param param
	 * @param returnData
	 * @return
	 * @throws Exception
	 */
	private boolean checkParam(Param_pda_feedback param, ReturnDataNew returnData) throws Exception{
		//参数检查
	    if (param == null) {
	      //参数传递错误
	      returnData.setReturnData(errorcode_param, "参数传递错误", "");
	      return false;
	    }
	    //检查是否进行了参数签名认证
	    if (!param.checkRequest()) {
	      returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
	      return false;
	    }
	    //对封装的参数对象中的属性进行 非空等规则验证
	    if (RequestUtil.checkObjectBlank(param.getContent())) {
	        //反馈信息content
	    	returnData.setReturnData(errorcode_param, " content is null", null);
	    	return false;
        } else {
            param.content = URLDecoder.decode(param.content, Constants.SYSTEM_CHARACTER);
        }
	    if (RequestUtil.checkObjectBlank(param.getPi_id())) {
	      //场地主键ID
	      returnData.setReturnData(errorcode_param, " pi_id is null", null);
	      return false;
	    }
	    if (!RequestUtil.checkObjectBlank(param.getArea_code())) {
	        //地址区域编码
	        param.setArea_code(URLDecoder.decode(param.getArea_code(), Constants.SYSTEM_CHARACTER));
	    }else{
	    	returnData.setReturnData(errorcode_param, "area_code is null", null);
	    	return false;
	    }
	    if (!RequestUtil.checkObjectBlank(param.getPi_name())) {
	    	//停车场名称
	        param.setPi_name(URLDecoder.decode(param.getPi_name(), Constants.SYSTEM_CHARACTER));
	    } else {
	    	returnData.setReturnData(errorcode_param, "pi_name is null", null);
		    return false;
	    }
	    if (!RequestUtil.checkObjectBlank(param.getPda_id())) {
	        //PDA设备表的主键ID
	        returnData.setReturnData(errorcode_param, " PDA_ID is null", null);
	        return false;
	    }
	    String sign_str = getSignature(Constants.SYSTEM_KEY,param.dtype,param.pi_id,param.area_code);
	    if (!param.getSign().equalsIgnoreCase(sign_str)) {
	      log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
	      returnData.setReturnData(errorcode_param, " 验证签名失败", null);
	      return false;
	    }
	    return true;
	}
  
}
