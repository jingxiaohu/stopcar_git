
package com.park.gate.action.v1.park;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import com.park.gate.action.v1.park.param.Param_record_parkinfo;
import com.park.gate.service.GateParkinfoBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;

/**
 * 记录停车场信息
 * @author jingxiaohu
 *
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_parkinfoAction extends BaseV1Controller {


	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = -3599663972160625262L;

	@Autowired
	private GateParkinfoBiz gateParkinfoBiz;

	/**
	 * 记录停车场信息
	 * @return
	 */
	@RequestMapping(value = "/record_parkinfo")
	@ResponseBody
	public String record_parkinfo(HttpServletResponse response,Param_record_parkinfo param){
		ReturnDataNew returnData = new ReturnDataNew();
		 try{
			
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
			if(RequestUtil.checkObjectBlank(param.name)){
				//场地名称
				
				returnData.setReturnData(errorcode_param, " name="+param.name+"  name is null", null);
				sendResp(returnData,response);
				return null;
			}else{
				param.name = returnUrlDecode(param.name);
			}
			
			if(RequestUtil.checkObjectBlank(param.address_name)){
				//address_name;//停车场地理街道名称
				
				returnData.setReturnData(errorcode_param, " address_name="+param.address_name+"  address_name is null", null);
				sendResp(returnData,response);
				return null;
			}else{
				param.address_name = returnUrlDecode(param.address_name);
			}
			/*if(lng == 0){
				//lng; //地理经度
				returnData.setReturnData(errorcode_param, " lng="+lng+"  lng is zero", null);
				sendResp(returnData,response);
				return null;
			}
			if(lat == 0){
				//lat;//地理纬度
				returnData.setReturnData(errorcode_param, " lat="+lat+"  lat is zero", null);
				sendResp(returnData,response);
				return null;
			}*/
			if(RequestUtil.checkObjectBlank(param.linkman_name)){
				//场地联系人姓名
				
//				returnData.setReturnData(errorcode_param, " linkman_name="+linkman_name+"  linkman_name is null", null);
//				sendResp(returnData,response);
//				return null;
			}else{
				param.linkman_name = returnUrlDecode(param.linkman_name);
			}
			
			if(RequestUtil.checkObjectBlank(param.linkman_tel)){
				//linkman_tel;//联系人电话
//				returnData.setReturnData(errorcode_param, " linkman_tel="+linkman_tel+"  linkman_tel is null", null);
//				sendResp(returnData,response);
//				return null;
			}else{
				if(!isMobileNO(param.linkman_tel)){
					//手机号码验证 
					returnData.setReturnData(errorcode_param, " linkman_tel="+param.linkman_tel+"  linkman_tel is not right telephone", null);
					sendResp(returnData,response);
					return null;
				}
			}
			
			
			if(RequestUtil.checkObjectBlank(param.copy_linkman_name)){
				//copy_linkman_name;//备用联系人姓名
				
//				returnData.setReturnData(errorcode_param, " copy_linkman_name="+copy_linkman_name+"  copy_linkman_name is null", null);
//				sendResp(returnData,response);
//				return null;
			}else{
				param.copy_linkman_name = returnUrlDecode(param.copy_linkman_name);
			}
			if(RequestUtil.checkObjectBlank(param.copy_linkman_tel)){
//				//copy_linkman_tel;//备用联系人手机
//				returnData.setReturnData(errorcode_param, " copy_linkman_tel="+copy_linkman_tel+"  copy_linkman_tel is null", null);
//				sendResp(returnData,response);
//				return null;
			}else{
				if(!isMobileNO(param.copy_linkman_tel)){
					//手机号码验证 
					returnData.setReturnData(errorcode_param, " copy_linkman_tel="+param.copy_linkman_tel+"  copy_linkman_tel is not right telephone", null);
					sendResp(returnData,response);
					return null;
				}
			}
			if(RequestUtil.checkObjectBlank(param.pi_phone)){
				//pi_phone;//场地座机号码
//				returnData.setReturnData(errorcode_param, " pi_phone="+pi_phone+"  pi_phone is null", null);
//				sendResp(returnData,response);
//				return null;
			}
			if(RequestUtil.checkObjectBlank(param.department)){
				//department;//负责单位
				
//				returnData.setReturnData(errorcode_param, " department="+department+"  department is null", null);
//				sendResp(returnData,response);
//				return null;
			}else{
				param.department = returnUrlDecode(param.department);
			}
			
//			if(carport_total < 1){
//				//carport_total;//场地车位总数
//				returnData.setReturnData(errorcode_param, " carport_total="+carport_total+"  carport_total is smaller zero", null);
//				sendResp(returnData,response);
//				return null;
//			}
//			if(enter_num < 1){
//				//enter_num;//场地入口数量
//				returnData.setReturnData(errorcode_param, " enter_num="+enter_num+"  enter_num is smaller zero", null);
//				sendResp(returnData,response);
//				return null;
//			}
//			if(exit_num < 1){
//				//exit_num;//场地出口数量
//				returnData.setReturnData(errorcode_param, " exit_num="+exit_num+"  exit_num is smaller zero", null);
//				sendResp(returnData,response);
//				return null;
//			}
//			if(hlc_enter_num < 1){
//				//hlc_enter_num;//场地入口道闸数量
//				returnData.setReturnData(errorcode_param, " hlc_enter_num="+hlc_enter_num+"  hlc_enter_num is smaller zero", null);
//				sendResp(returnData,response);
//				return null;
//			}
//			if(hlc_exit_num < 1){
//				//hlc_exit_num;//场地出口道闸数量
//				returnData.setReturnData(errorcode_param, " hlc_exit_num="+hlc_exit_num+"  hlc_exit_num is smaller zero", null);
//				sendResp(returnData,response);
//				return null;
//			}
//			
//			
//			
//			if(enter_camera_num < 1){
//				//enter_camera_num;//场地入口摄像头数量
//				returnData.setReturnData(errorcode_param, " enter_camera_num="+enter_camera_num+"  enter_camera_num is smaller zero", null);
//				sendResp(returnData,response);
//				return null;
//			}
//			if(exit_camera_num < 1){
//				//exit_camera_num;//场地出口摄像头数量
//				returnData.setReturnData(errorcode_param, " exit_camera_num="+exit_camera_num+"  exit_camera_num is smaller zero", null);
//				sendResp(returnData,response);
//				return null;
//			}
			
			if(RequestUtil.checkObjectBlank(param.camera_info)){
				//camera_info;//场地摄像头信息
				
//				returnData.setReturnData(errorcode_param, " camera_info="+camera_info+"  camera_info is null", null);
//				sendResp(returnData,response);
//				return null;
			}else{
				param.camera_info = URLDecoder.decode(param.camera_info, Constants.SYSTEM_CHARACTER);
			}
			
			if(RequestUtil.checkObjectBlank(param.area_code)){
				//省市县编号 140107
				//避免汉子的问题
				
				returnData.setReturnData(errorcode_param, " area_code="+param.area_code+"  area_code is null", null);
				sendResp(returnData,response);
				return null;
			}
			if(RequestUtil.checkObjectBlank(param.park_type)){
				//park_type;//停车场类型 0：地下室停车场 1：露天停车场 2：露天立体车库停车场
				returnData.setReturnData(errorcode_param, " park_type="+param.park_type+"  park_type is null", null);
				sendResp(returnData,response);
				return null;
			}
			
			//道闸专线静态IP不为空时，校验ip规则
//			if(!RequestUtil.checkObjectBlank(param.special_ip)){
//				if(!isIpAddress(param.special_ip)){
//					returnData.setReturnData(errorcode_param, " special_ip="+param.special_ip+" special_ip is error", null);
//					sendResp(returnData,response);
//					return null;
//				}
//			}
			
			String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype,param.park_type);
			if(!param.sign.equalsIgnoreCase(sign_str)){
				log.warn("sign="+param.sign+"  sign_str="+sign_str);
				returnData.setReturnData(errorcode_param, " sign is not right", null);
				sendResp(returnData,response);
				return null;
			}

			gateParkinfoBiz.record_parkinfo(returnData,param.name,param.address_name,param.lng,param.lat,
					param.linkman_name,param.linkman_tel,param.copy_linkman_name
					,param.copy_linkman_tel,param.pi_phone,param.department,param.enter_num,
					param.exit_num,param.hlc_enter_num,param.hlc_exit_num,param.enter_camera_num,
					param.exit_camera_num
					,param.camera_info,param.park_type,param.area_code,param.expect_money,
					param.allow_revoke_time,param.allow_expect_time,param.is_expect,
					param.carport_yet,param.carport_space,param.carport_total,
					param.moth_car_num,param.moth_car_num_space,param.time_car_num,
					param.time_car_num_space,param.expect_car_num,param.admin_id,
					param.upload_source,param.roadside_type,param.special_ip);
			
			sendResp(returnData,response);
			return null;
			
			} catch (Exception e) {
				log.error("Write_parkinfoAction record_parkinfo  is error(DEVICE-JAVA)- P",e);
				returnData.setReturnData(errorcode_systerm, "system is error", ""); 
			}
			sendResp(returnData,response);
			return null; 
	}
	
	
	/**
	 * 记录停车场信息
	 * @return
	 */
	@RequestMapping(value = "/update_parkinfo")
	@ResponseBody
	public String update_parkinfo(HttpServletResponse response,Param_record_parkinfo param){
		ReturnDataNew returnData = new ReturnDataNew();
		 try{
			
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
			
			if(param.pi_id < 1){
				//pi_id;//场地主键ID
				returnData.setReturnData(errorcode_param, " pi_id="+param.pi_id+"  pi_id is smaller zero", null);
				sendResp(returnData,response);
				return null;
			}
			
			if(!RequestUtil.checkObjectBlank(param.name)){
				param.name = returnUrlDecode(param.name);
			}
			
			if(!RequestUtil.checkObjectBlank(param.address_name)){
				param.address_name = returnUrlDecode(param.address_name);
			}
			if(!RequestUtil.checkObjectBlank(param.linkman_name)){
				param.linkman_name = returnUrlDecode(param.linkman_name);
			}
			
			if(!RequestUtil.checkObjectBlank(param.linkman_tel)){
				if(!isMobileNO(param.linkman_tel)){
					//手机号码验证 
					returnData.setReturnData(errorcode_param, " linkman_tel="+param.linkman_tel+"  linkman_tel is not right telephone", null);
					sendResp(returnData,response);
					return null;
				}
			}
			if(!RequestUtil.checkObjectBlank(param.copy_linkman_name)){
				param.copy_linkman_name = returnUrlDecode(param.copy_linkman_name);
			}
			if(!RequestUtil.checkObjectBlank(param.copy_linkman_tel)){
				if(!isMobileNO(param.copy_linkman_tel)){
					//手机号码验证 
					returnData.setReturnData(errorcode_param, " copy_linkman_tel="+param.copy_linkman_tel+"  copy_linkman_tel is not right telephone", null);
					sendResp(returnData,response);
					return null;
				}
			}
			if(!RequestUtil.checkObjectBlank(param.department)){
				param.department = returnUrlDecode(param.department);
			}
			if(!RequestUtil.checkObjectBlank(param.camera_info)){
				param.camera_info = URLDecoder.decode(param.camera_info, Constants.SYSTEM_CHARACTER);
			}
			
			if(!RequestUtil.checkObjectBlank(param.area_code)){
				//省市县编号 140107
				//避免汉子的问题
				param.area_code = URLDecoder.decode(param.area_code, Constants.SYSTEM_CHARACTER);
			}
			
			String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype,param.pi_id);
			if(!param.sign.equalsIgnoreCase(sign_str)){
				log.warn("sign="+param.sign+"  sign_str="+sign_str);
				returnData.setReturnData(errorcode_param, " sign is not right", null);
				sendResp(returnData,response);
				return null;
			}
			gateParkinfoBiz.update_parkinfo(returnData,param.pi_id,param.name,param.address_name,
					param.lng,param.lat,param.linkman_name,param.linkman_tel,param.copy_linkman_name
					,param.copy_linkman_tel,param.pi_phone,param.department,
					param.enter_num,param.exit_num,param.hlc_enter_num,param.hlc_exit_num,
					param.enter_camera_num,param.exit_camera_num
					,param.camera_info,param.park_type,param.area_code,param.expect_money,
					param.allow_revoke_time,param.allow_expect_time,
					param.is_expect,param.carport_yet,param.carport_space,param.carport_total,
					param.moth_car_num,param.moth_car_num_space,param.time_car_num,
					param.time_car_num_space,param.expect_car_num,param.admin_id,
					param.upload_source,param.roadside_type,param.special_ip);
			
			sendResp(returnData,response);
			return null;
			
			} catch (Exception e) {
				log.error("Write_parkinfoAction update_parkinfo  is error(DEVICE-JAVA)- P",e);
				returnData.setReturnData(errorcode_systerm, "system is error", ""); 
			}
			sendResp(returnData,response);
			return null; 
	}
	

	
}
