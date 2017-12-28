package com.park.mvc.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.bean.Park_heartbeat;
import com.park.bean.Park_info;
import com.park.mvc.service.BaseBiz;
import com.park.util.RequestUtil;

/**
 * 停车场公用方法
 * @author jingxiaohu
 *
 */
@Service
public class ParkInfoPB extends BaseBiz{
	@Autowired
	private PayParkPB payParkPB;
	
	/**
	 * 停车场车辆个数变更
	 * @param park_info 停车场基本信息
	 * @param is_enter 0：   入库   1：出库
	 * @param type 1:临停 2：租赁  
	 * @return
	 */
	public boolean upCarNum(Park_info park_info,int is_enter,int type){ 
		try {
			if(is_enter == 0){
				/**
				 * 首先判断是    租赁  还是   临停   还是预约  取消预约
				 */
				switch(type){
				
				case 1:{
					//临停
					//入库  停车场空余车位数 减少
					park_info.setCarport_space(park_info.getCarport_space()-1>=0 ?park_info.getCarport_space()-1 : 0);//空闲车位数
					park_info.setCarport_yet(park_info.getCarport_yet() + 1  <= park_info.getCarport_total() ? park_info.getCarport_yet() + 1 :  park_info.getCarport_total());//已停车位数
					int count = park_infoDao.updateByKey(park_info,ReturnParkTableName(park_info.getArea_code()));
					if(count == 1){
						//更新成功
						return true;
					}
					break;
				}
				case 2:{
					//租赁
					//入库  停车场租赁空余车位数 减少
					park_info.setTime_car_num_space(park_info.getTime_car_num_space()-1 >= 0 ? park_info.getTime_car_num_space()-1 : 0);//空闲车位数
					int count = park_infoDao.updateByKey(park_info,ReturnParkTableName(park_info.getArea_code()));
					if(count == 1){
						//更新成功
						return true;
					}
					break;
				}
				default: return false ;
			}
		}
			
		if(is_enter == 1){
				
				/**
				 * 首先判断是    租赁  还是   临停  1:临停 2：租赁
				 */
				switch(type){
				
				case 1:{
					//临停
					//出库  停车场空余车位数 增加
					park_info.setCarport_space(park_info.getCarport_space() + 1 <= park_info.getCarport_total() ? park_info.getCarport_space() + 1 : park_info.getCarport_total());//空闲车位数
					park_info.setCarport_yet(park_info.getCarport_yet() - 1  >= 0 ? park_info.getCarport_yet() - 1 :  0);//已停车位数
					int count  = park_infoDao.updateByKey(park_info,ReturnParkTableName(park_info.getArea_code()));
					if(count == 1){
						//更新失败
						return true;
					}
					break;
				}
				case 2:{
					//租赁
					//出库  停车场空余车位数 增加
					park_info.setTime_car_num_space(park_info.getTime_car_num_space() + 1 <= park_info.getTime_car_num() ? park_info.getTime_car_num_space() + 1 : park_info.getTime_car_num());//空闲车位数
					int count = park_infoDao.updateByKey(park_info,ReturnParkTableName(park_info.getArea_code()));
					if(count == 1){
						//更新成功
						return true;
					}
					break;
				}
				default: return false ;
			}

			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("ParkInfoPB.upCarNum is error", e); 
		}
		
		
		return false;
	}
	
	
	
	/**
	 * 停车场车辆个数变更
	 * @param park_info 停车场基本信息
	 * @param type 1: 车辆预约   2：车辆取消预约   3：预约超时  4：预约未超时入库
	 * @return
	 */
	public boolean upCarNumExpect(Park_info park_info,int type){ 
		/*try {
				switch(type){
				
				case 1:{
					//车辆预约  停车场空余车位数减少  已预约车辆数增加
					park_info.setCarport_space(park_info.getCarport_space()-1>=0 ?park_info.getCarport_space()-1 : 0);//空闲车位数
					park_info.setExpect_car_num(park_info.getExpect_car_num()+1 <= park_info.getCarport_total() ? park_info.getExpect_car_num()+1 : park_info.getCarport_total() );//已预约车位数
					int count = park_infoDao.updateByKey(park_info,ReturnParkTableName(park_info.getArea_code()));
					if(count == 1){
						//更新成功
						return true;
					}
					break;
				}
				case 2:{
					//车辆取消预约 停车场空余车位数增加   已预约车位数减少
					park_info.setCarport_space(park_info.getCarport_space() + 1 <= park_info.getCarport_total() ?park_info.getCarport_space() + 1 : park_info.getCarport_total());//空闲车位数
					park_info.setExpect_car_num(park_info.getExpect_car_num()-1 >= 0 ? park_info.getExpect_car_num()-1 : 0 );//已预约车位数
					int count = park_infoDao.updateByKey(park_info,ReturnParkTableName(park_info.getArea_code()));
					if(count == 1){
						//更新成功
						return true;
					}
					break;
				}
				case 3:{
					//预约超时 停车场空余车位数增加  已预约车位数减少
					park_info.setCarport_space(park_info.getCarport_space() + 1 <= park_info.getCarport_total() ?park_info.getCarport_space() + 1 : park_info.getCarport_total());//空闲车位数
					park_info.setExpect_car_num(park_info.getExpect_car_num()-1 >= 0 ? park_info.getExpect_car_num()-1 : 0 );//已预约车位数
					int count = park_infoDao.updateByKey(park_info,ReturnParkTableName(park_info.getArea_code()));
					if(count == 1){
						//更新成功
						return true;
					}
					break;
				}
				case 4:{
					//预约未超时入库   停车场空余车位数不变   已预约车位数减少
					park_info.setExpect_car_num(park_info.getExpect_car_num() - 1 >= 0 ? park_info.getExpect_car_num()-1 : 0 );//已预约车位数
					int count = park_infoDao.updateByKey(park_info,ReturnParkTableName(park_info.getArea_code()));
					if(count == 1){
						//更新成功
						return true;
					}
					break;
				}
				default: return false ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("ParkInfoPB.upCarNumExpect is error", e); 
		}
		return false;*/
		return true;
	}


	/**
	 * 处理停车场异常  is_fault 停车场故障 0:无故障 1：发生故障
	 */
	public void upFaultParkInfo() {
		// TODO Auto-generated method stub
		try {
			//10分钟都没有心跳 则做为异常处理
			String sql = "select * from park_heartbeat where state = 0 GROUP BY pi_id  HAVING unix_timestamp() - UNIX_TIMESTAMP(ctime) > 600";
			List<Park_heartbeat> list = getMySelfService().queryListT(sql, Park_heartbeat.class);
			if(list != null && list.size() > 0){
				for (Park_heartbeat park_heartbeat : list) {
					if(park_heartbeat.getPi_id() != 0 && !RequestUtil.checkObjectBlank(park_heartbeat.getArea_code())){
						try {
						 //首先检查停车场是否存在如果不存在则返回空集合
					      String tablename = ReturnParkTableName(park_heartbeat.getArea_code());
					      if (!isExistParkInfo(park_heartbeat.getArea_code(), tablename)) {
					        //不存在该表
					       continue;
					      }
						    Park_info park_info = park_infoDao.selectByKey(park_heartbeat.getPi_id(),ReturnParkTableName(park_heartbeat.getArea_code()));
							if(park_info != null ){
								park_info.setIs_fault(1);//停车场故障 0:无故障 1：发生故障
								int count = park_infoDao.updateByKey(park_info,ReturnParkTableName(park_info.getArea_code()));
								if(count != 1){
									//更新失败
									log.error("pi_id="+park_heartbeat.getPi_id()+"area_code="+park_heartbeat.getArea_code()+"  更新停车场异常状态失败");
								}else{
									//更新该次心跳记录标记为已经处理
									park_heartbeat.setState(1);
									count = park_heartbeatDao.updateByKey(park_heartbeat);
									if(count != 1){
										//更新失败
										log.error("park_heartbeatDao.updateByKey(park_heartbeat) is error");
									}else{
										if(park_info.getPark_type() != 1){
											//不是占道停车场 才处理这些未交费的订单为 异常订单
											//标记该停车场部分订单异常
//											payParkPB.upOrderFault(park_info);
										}
									}
								}
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							log.error("pi_id="+park_heartbeat.getPi_id()+"area_code="+park_heartbeat.getArea_code()+"  更新停车场异常状态失败",e);
						}
					}
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("调度处理租赁订单 ---还处于租赁中--10分钟-租赁车超时 upRentOrderOutTime10() is error"+e.getMessage(), e);
		}
	}

}
