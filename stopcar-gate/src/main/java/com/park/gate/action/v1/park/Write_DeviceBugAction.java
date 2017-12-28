
package com.park.gate.action.v1.park;

import com.park.bean.ReturnDataNew;
import com.park.gate.action.v1.park.param.Param_gpspark;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 记录设备BUG
 * @author jingxiaohu
 *
 */
@Controller
@RequestMapping(value = "/v1")
@Deprecated
public class Write_DeviceBugAction extends BaseV1Controller {


	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = -3599663972160625262L;


	/**
	 * 记录设备BUG信息
	 * @return
	 */
	@RequestMapping(value = "/devicebug")
	@ResponseBody
	public String DeviceBug(HttpServletResponse response,Param_gpspark param){
		ReturnDataNew returnData = new ReturnDataNew();
		 try{
			
			//检查是否是合法请求
				//参数检查
			 if(param == null){ 
				 //参数传递错误 
				 returnData.setReturnData(errorcode_param, "参数传递错误", "");
				 sendResp(returnData,response);
				 return null; 
			 }
			 //检查是否进行了参数签名认证
			 if(!param.checkRequest()){
				 returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
				 sendResp(returnData,response);
				 return null; 
			 }
			 //对封装的参数对象中的属性进行 非空等规则验证
			if(RequestUtil.checkObjectBlank(info)){
				returnData.setReturnData(errorcode_param, " info="+info+"  info is null", null);
				sendResp(returnData,response);
				return null;
			}
			if(type < 1){
				returnData.setReturnData(errorcode_param, " type="+type+"  type is not right", null);
				sendResp(returnData,response);
				return null;
			}
			if(devicetime < 1){
				returnData.setReturnData(errorcode_param, " devicetime="+devicetime+"  devicetime is not right", null);
				sendResp(returnData,response);
				return null;
			}
			//deviceRecordBiz.ReturnDeviceBugRecord(returnData,mac,devicemac,info,classname,methodname,type,devicetime);
			sendResp(returnData,response);
			return null;
			
			} catch (Exception e) {
				log.error("Write_DeviceBugAction DeviceBug  is error(DEVICE-JAVA)- P",e);
				returnData.setReturnData(errorcode_systerm, "system is error", ""); 
			}
			sendResp(returnData,response);
			return null; 
	}
	
	
	/********************接收参数区*************************/
	public String mac;//设备MAC
	public String info;//来源
	public String classname;//异常错误类
	public String methodname ;//具体的某个方法体爆出的BUG
	public int type;//1：WIFI 2：蓝牙 3： FM 4:launcher
	public long devicetime;//设备当前报错的时间

	/************************get set 方法区****************************/
	
	public String getMac() {
		return mac;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getDevicetime() {
		return devicetime;
	}
	public void setDevicetime(long devicetime) {
		this.devicetime = devicetime;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getMethodname() {
		return methodname;
	}
	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}

	
	
	
}
