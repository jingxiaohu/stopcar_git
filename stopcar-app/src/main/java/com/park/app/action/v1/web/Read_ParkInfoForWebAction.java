package com.park.app.action.v1.web;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.park.app.action.v1.web.param.Param_park_info_web;
import com.park.app.service.ParkInfoForWebBiz;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;

/**
 * web停车场基本信息管理
 * @author zyy
 */
@RestController
@RequestMapping(value = "/v1")
public class Read_ParkInfoForWebAction extends BaseV1Controller{

	private static final long serialVersionUID = -309709296188066094L;
	
	@Autowired 
	private ParkInfoForWebBiz parkInfoForWebBiz;

	/**
	 * web停车场基本信息查询,返回jsonp格式数据 
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/get_parkinfo_forweb")
	public void getParkInfo(HttpServletRequest request,HttpServletResponse response,Param_park_info_web param){
		List<Map<String, Object>> park_infoList = new ArrayList<Map<String, Object>>();
		ReturnDataNew returnData = new ReturnDataNew();
		try{
			//参数检查
			if(!checkParam(param,returnData)){
				sendResp(returnData, response);
		        return;
			}
			
			//查询相应经纬度距离范围内停车场基本信息
			park_infoList = parkInfoForWebBiz.getParkInfo(param.lng,param.lat,param.distance,param.area_code);
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			
			PrintWriter out = response.getWriter();
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			
			//根据停车场名称按第一个字符为中文/英文/数字，依次放入jsonArray
			//停车场名称按第一个字符为中文
			for (int k = 0; k < park_infoList.size(); k++) {
				Map<String, Object> map = park_infoList.get(k);
				Object object = map.get("pi_name");
				//判断park_infoList是否有停车场信息数据
				if(object == null){
					returnData.setReturnData(errorcode_systerm, park_infoList.get(0).toString(), null);
					sendResp(returnData, response);
					return;
				}
				char words[] = object.toString().toCharArray();
				String s = words[0]+"";
				int asciiCode = s.toString().codePointAt(0);
				if(asciiCode > 255){
					jsonArray.add(map);
				}
			}
			//停车场名称按第一个字符为英文
			for (int k = 0; k < park_infoList.size(); k++) {
				Map<String, Object> map = park_infoList.get(k);
				char words[] = map.get("pi_name").toString().toCharArray();
				String s = words[0]+"";
				int asciiCode = s.toString().codePointAt(0);
				if ((asciiCode >= 65 && asciiCode <= 90) || (asciiCode >= 97 && asciiCode <= 122)){
					jsonArray.add(map);
				}
			}
			//停车场名称按第一个字符为数字
			for (int k = 0; k < park_infoList.size(); k++) {
				Map<String, Object> map = park_infoList.get(k);
				char words[] = map.get("pi_name").toString().toCharArray();
				String s = words[0]+"";
				int asciiCode = s.toString().codePointAt(0);
				if(asciiCode>=48 && asciiCode<=57){
					jsonArray.add(map);
				}
			}
			//停车场名称按第一个字符为其他字符
			for (int k = 0; k < park_infoList.size(); k++) {
				Map<String, Object> map = park_infoList.get(k);
				char words[] = map.get("pi_name").toString().toCharArray();
				String s = words[0]+"";
				int asciiCode = s.toString().codePointAt(0);
				if((asciiCode>=0 && asciiCode<48) || (asciiCode>57 && asciiCode<65) || (asciiCode>90 && asciiCode<97) || (asciiCode>122 && asciiCode<=255)){
					jsonArray.add(map);
				}
			}
			
			//遍历返回的停车场基本信息List,并添加到jsonArray
			/*for (Map<String, Object> map : park_infoList) {
				jsonArray.add(map);
			}*/
			
			jsonObj.put("result", jsonArray);
			
			//客户端请求参数
			String jsonpCallback = request.getParameter("jsonpCallback");
			//返回jsonp格式数据
			out.println(jsonpCallback+"("+jsonObj.toString()+")");
			out.flush();
			out.close();
		}catch (Exception e) {
			log.error("Read_ParkInfoForWebAction getParkInfo is error(DEVICE-JAVA)- P", e);
		    returnData.setReturnData(errorcode_systerm, "system is error", "");
		}
		sendResp(returnData, response);
		return;
	}
	
	/**
	 * 参数检查
	 * @param param
	 * @param returnData
	 * @return
	 */
	public boolean checkParam(Param_park_info_web param,ReturnDataNew returnData) throws Exception{
		//参数检查
		if(param == null){
			returnData.setReturnData(errorcode_param, "参数传递错误","");
			return false;
		}
		//对封装的参数对象中的属性进行 非空等规则验证 
		if (param.lat == null) {
		      //场地纬度
		      returnData.setReturnData(errorcode_param, " 场地纬度 lat is null", null);
		      return false;
		}
		if (param.lng == null){
			//场地经度
			returnData.setReturnData(errorcode_param, "场地经度 lng is null", null);
			return false;
		}
        if (!RequestUtil.checkObjectBlank(param.getArea_code())) {
	        //地址区域编码
	        param.setArea_code(URLDecoder.decode(param.getArea_code(), Constants.SYSTEM_CHARACTER));
	    }else{
	    	//20170711  修改  如果没传area_code默认设置为510101
	    	param.setArea_code("510101");
//	    	returnData.setReturnData(errorcode_param, "area_code is null", null);
//	    	return false;
	    }
		
		return true;
	}

}
