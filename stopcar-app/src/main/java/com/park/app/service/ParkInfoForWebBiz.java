package com.park.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.mvc.service.BaseBiz;

/**
 * web停车场基本信息管理
 * @author zyy
 */
@Service
public class ParkInfoForWebBiz extends BaseBiz{
	
	/**
	 * web 相应经纬度距离范围内停车场基本信息查询
	 */
	@TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
	public List<Map<String, Object>> getParkInfo( Double lng, Double lat, Integer distance,String area_code){
		List<Map<String, Object>> park_infoList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (distance == null) {
				distance = 500;//米
			}
			//首先检查停车场是否存在如果不存在则返回空集合
			String tablename = ReturnParkTableName(area_code);
			if (!isExistParkInfo(area_code, tablename)) {
				map.put(errorcode_param, "该区域代码表不存在");
				//不存在该表 -- 返回结果
				park_infoList.add(map);
				return park_infoList;
			}

			//查询停车场基本信息
			StringBuilder sql = new StringBuilder();
			sql.append("select t.pi_name,t.carport_total,t.carport_yet,t.carport_space,t.lng,t.lat,");
			sql.append(" CASE WHEN t.carport_space>=10 THEN 'free' ");
			sql.append(" WHEN (t.carport_space<10 and t.carport_space>0) THEN 'warning' ");
			sql.append(" WHEN t.carport_space=0 THEN 'full' END as park_state,t.pi_state,t.pu_id,t.is_fault,t.park_type,");
			sql.append(" ROUND(6378.138*2*ASIN(SQRT(POW(SIN((:lat*PI()/180-lat*PI()/180)/2),2)+COS(:lat*PI()/180)*COS(lat*PI()/180)*POW(SIN((:lng*PI()/180-lng*PI()/180)/2),2)))*1000) AS juli ");
			sql.append(" from "+ tablename +" t having juli <:distance and t.pi_state=1 and t.pu_id>0 ");
			sql.append(" ORDER BY convert(t.pi_name using GBK) ASC");
			
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("lat",lat);
			paramMap.put("lng",lng);
			paramMap.put("distance",distance);
			paramMap.put("area_code",area_code);
			
			park_infoList = getMySelfService().queryForList(sql.toString(), paramMap);
			if (park_infoList == null) {
				park_infoList = new ArrayList<Map<String, Object>>();
				map.put(errorcode_param, "停车场信息不存在");
				park_infoList.add(map);
				return park_infoList;
			}
			
			//返回数据
			return park_infoList;
		} catch (Exception e) {
			log.error("ParkInfoForWebBiz getParkInfo is error", e);
			map.put(errorcode_param, "system is error");
			park_infoList.add(map);
			return park_infoList;
		}
	}
	
}
