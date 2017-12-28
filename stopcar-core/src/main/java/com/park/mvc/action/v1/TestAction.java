package com.park.mvc.action.v1;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.bean.ReturnDataNew;

/**
 * @author jingxiaohu
 *
 */
@Controller
@RequestMapping(value = "/v1")
public class TestAction extends BaseV1Controller {
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = -3599663972160625262L;

	@RequestMapping(value = "/test")
	@ResponseBody
	public String test(HttpSession session, HttpServletRequest request, HttpServletResponse response){
	
	ReturnDataNew returnData = new ReturnDataNew();
	 try{
		//testBiz.doTest();
		 returnData.setReturnData(errorcode_success, "成功", "","0");
		sendResp(returnData,response);
		return null;
		} catch (Exception e) {
			log.error("TestAction is error (JAVA)- P",e);
			returnData.setReturnData(errorcode_systerm, "system is error", ""); 
		}
		sendResp(returnData,response);
		return null; 
	}
	
	

	
	
	/********************接收参数区*************************/
	public int type;// 1:Android 2：ios 3:设备
	/************************get set 方法区****************************/

	public int getType() {
		return type;
	}





	public void setType(int type) {
		this.type = type;
	}


	

}
