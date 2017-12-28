package com.park.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.park.dao.Park_infoDao;

/**
 * 处理停车场的属性
 * @author jingxiaohu
 *
 */
public class ParkUtil {

	static Logger log = LoggerFactory.getLogger(ParkUtil.class);
	
/*	*//**
	 * 加减停车场空位数
	 * @param park_info
	 * @param type 0:增加  1：减少
	 * @param count  加减数字
	 *//*
	public static boolean addOrReduce(Park_infoDao park_infoDao,Park_info park_info,int type,int count ,String area_code ) throws QzException{
		try {
			if(type == 0){
				//增加
				park_info.setCarport_space(park_info.getCarport_space() + count); 
				if(park_info.getCarport_yet()-count >= 0){
					park_info.setCarport_yet(park_info.getCarport_yet()-count);
				}else{
					park_info.setCarport_yet(0);
				}
				count  = park_infoDao.updateByKey(park_info,ReturnParkTableName(park_infoDao,area_code));	
				if(count < 1){
					return false;
				}
			}else{
				//减少
				if(park_info.getCarport_space() - count >= 0){
					park_info.setCarport_space(park_info.getCarport_space() - count); 
				}else{
					park_info.setCarport_space(0); 
				}
				park_info.setCarport_yet(park_info.getCarport_yet()+count);
				count  = park_infoDao.updateByKey(park_info,ReturnParkTableName(park_infoDao,area_code));	
				if(count < 1){
					return false;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("ParkUtil.addOrReduce is error", e); 
			throw new QzException("ParkUtil.addOrReduce is error",e);
		}
		return true;
	}*/
	
	/**
	 * 区域代码转化成 省级市  area_code "510112";//省市区区域代码  四川省 成都市 龙泉驿区
	 */
	public static String ReturnParkTableName(Park_infoDao park_infoDao,String area_code){
		if(area_code == null)
			return null; 
		return  park_infoDao.getTABLENAME()+area_code.substring(0, 2)+"0000";
	}
	
}
