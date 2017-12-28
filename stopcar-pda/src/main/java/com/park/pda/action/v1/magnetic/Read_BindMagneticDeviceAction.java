package com.park.pda.action.v1.magnetic;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.magnetic.param.Param_magnetic_device;
import com.park.pda.service.PDAMagneticDeviceBiz;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 设备编号查询
 * @author zyy
 *
 */
@Controller
@RequestMapping(value = "v1")
public class Read_BindMagneticDeviceAction extends BaseV1Controller{

	private static final long serialVersionUID = -6603509476719362652L;
	
	@Autowired
	protected PDAMagneticDeviceBiz magneticDeviceBiz;
	
	/**
	 * 根据pi_id和area_code获取地磁停车场内绑定的地磁设备列表信息
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/read_bind_magnetic_device")
	public String read_bindMagneticDevice (HttpServletResponse response,Param_magnetic_device param){
		ReturnDataNew returnData = new ReturnDataNew();
		
		try{
			//参数检查
			if (param == null) {
				//参数传递错误
				returnData.setReturnData(errorcode_param, "参数传递错误", "");
				sendResp(returnData, response);
				return null;
			}
			//检查是否进行了参数签名认证
			if (!param.checkRequest()) {
				returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
				sendResp(returnData, response);
				return null;
			}
			//对封装的参数对象中的属性进行 非空等规则验证
			long pi_id = param.getPi_id();
			if (pi_id <= 0) {
				returnData.setReturnData(errorcode_param, "pi_id不能为空", null);
				sendResp(returnData, response);
				return null;
			}
			String area_code = param.getArea_code();
			if (!StringUtils.hasText(area_code)) {
				returnData.setReturnData(errorcode_param, "area_code不能为空", null);
				sendResp(returnData, response);
				return null;
			}
			
			//字符串签名
			String sign_str = getSignature(Constants.SYSTEM_KEY,param.pi_id,param.area_code);
			if (!param.sign.equalsIgnoreCase(sign_str)) {
				log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
				returnData.setReturnData(errorcode_param, " sign is not right", null);
				sendResp(returnData, response);
				return null;
			}
			
			magneticDeviceBiz.read_bindMagneticDevice(returnData,param.pi_id,param.area_code);
			
			sendResp(returnData,response);
			return null;
		}catch (Exception e) {
			log.error("Read_bindMagneticDeviceAction read_bindMagneticDevice  is error(DEVICE-JAVA)- P", e);
			returnData.setReturnData(errorcode_systerm, "system is error", "");
		}
		sendResp(returnData, response);
		return null;
	}
}
