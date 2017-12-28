package com.park.mvc.service.common;

import com.park.bean.Park_userinfo;
import com.park.bean.Pay_month_park;
import com.park.bean.Pay_park;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_info;
import com.park.bean.User_vc_act;
import com.park.exception.QzException;
import com.park.mvc.service.BaseBiz;
import com.park.util.RequestUtil;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户公用方法
 * @author jingxiaohu
 *
 */
@Service
public class UserPB extends BaseBiz{
	@Autowired
	protected PayParkPB payParkPB;
	@Autowired
	protected PayMonthParkPB payMonthParkPB;
	/**
	 * 变更用户钱包金额
	 * @param ui_id
	 * @param ui_nd
	 * @param type 0:新增 1:减少
	 * @param money
	 * @return
	 */
	public User_info updateUserMoney(long ui_id,String ui_nd,int type,long money){
		try {
			User_info user_info = user_infoDao.selectByKey(ui_id);
			if(user_info == null || money < 1){
				return null;
			}
			if(type == 0){
				//增加
				user_info.setUi_vc(user_info.getUi_vc() + money);
			}else{
				//减少
				if(user_info.getUi_vc() - money >= 0){
					user_info.setUi_vc(user_info.getUi_vc() - money);
				}else{
					return null;
				}
			}
			int count = user_infoDao.updateByKey(user_info);
			if(count < 1){
				return null;
			}
			return user_info;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("UserPB.updateUserMoney 变更用户钱包金额", e);
		}
		return null;
	}
	
	/**
	 * 
	 * @param act_type 用户行为0：订单支付1：充值2:系统返还
	 * @param money
	 * @param order_id
	 * @param order_type
	 * @param ui_id
	 * @param returnData
	 * @param pay_source 支付类型  1:支付宝 2：微信  3：银联  4：钱包   5:龙支付
	 * @param date
	 * @throws QzException
	 */
	public void recordVC(int act_type, int money, String order_id,
			int order_type, long ui_id,
			ReturnDataNew returnData, String act_name, String ui_nd, String tel, int pay_source,
			long upc_id, int discount_type, int discount_money, Date date) throws QzException{
    	try {
			//记录该次用户虚拟币更改记录
			User_vc_act  va = new User_vc_act();
			va.setUpc_id(upc_id);
			va.setDiscount_type(discount_type);
			va.setDiscount_money(discount_money);
			va.setAct_type(act_type);//用户行为  0：订单支付   1：充值  2:系统返还
			if(act_type == 0){
				va.setIs_add(0);//增加还是减少  0：减少  1：增加
			}else{
				va.setIs_add(1);//增加还是减少  0：减少  1：增加
			}
			
			va.setCtime(date);
			va.setMoney(money);//交易金额（单位 分）
			va.setOrder_id(order_id);//订单ID
			va.setOrder_type(order_type);//下单类型 0: 普通下单  1：预约下单 2：租赁包月订单  3:租赁续租订单
			va.setUi_id(ui_id);
			va.setState(1);//处理状态 0：未处理 1：已处理
			va.setAct_name(act_name);
			va.setTel(tel);//电话号码
			va.setUi_nd(ui_nd);//用户唯一标识符
			va.setPay_source(pay_source);//支付类型  1:支付宝 2：微信  3：银联  4：钱包   5:龙支付
			int count = user_vc_actDao.insert(va);
			if(count < 1){
				//更新失败
				returnData.setReturnData(errorcode_data, "缴费失败", "");
				throw new QzException("record_user_vc_act 缴费失败");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			returnData.setReturnData(errorcode_data, "缴费失败", "");
			throw new QzException("record_user_vc_act 缴费失败",e);
		}
	}
	
	
	/**
	 * 更新商户账户金额
	 * @param type 0:现金  1：线上
	 * @throws QzException
	 */
	@Deprecated
	public void upManchentMoney(long pu_id,String pu_nd,int money,int type) throws QzException{
    	try {
    		Date date = new Date();
    		Park_userinfo park_userinfo = null;
			try {
				park_userinfo = park_userinfoDao.selectByKey(pu_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("商户不存在");
			}
    		if(park_userinfo == null || money < 1){
    			return;
    		}
    		if(type == 0){
    			//现金
    			park_userinfo.setMoney_offline(park_userinfo.getMoney_offline()+money); 
    		}else{
    			//线上
//        		park_userinfo.setMoney(park_userinfo.getMoney()+money);
        		park_userinfo.setMoney_online(park_userinfo.getMoney()+money);
    		}
    		park_userinfo.setUtime(date);
			int count = park_userinfoDao.updateByKey(park_userinfo);
			if(count < 1){
				//更新失败
//				throw new QzException("upManchentMoney 更新商户账户金额失败");
				log.error("更新商户账户金额 失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new QzException("upManchentMoney 更新商户账户金额失败",e);
		}
	}
	
	/**
	 * 处理用户被锁定的金额----情况1：锁定金额  情况2：解除锁定金额 3:解除锁定金额和返还金额
	 * @param type : 0：预约   1：取消预约  2：租赁
	 * @param order_type 0:预约  1：租赁
	 * @param state 处理结果状态 0:成功 1：失败
	 * @param money
	 * @param user_info
	 * @param returnData
	 * @throws QzException
	 */
	public void doUnLockMoney(int type,int order_type,Integer state,int money,User_info user_info,String orderid,long pi_id,String area_code,Pay_month_park pay_month_park,Pay_park pay_park,ReturnDataNew returnData) throws QzException{
    	try {
    		String message =null;
			String title ="";
    		//order_type : 0:代表预约  1：代表租赁
    		if(order_type == 0){
    			
    			if(state == null){
					//表示道闸停车场没有调用更新接口处理 而是中心服务器本地的调用处理
        			if(type == 0){
        				//预约
        				user_info.setLock_expect_money(user_info.getLock_expect_money()+money);
        				pay_park.setExpect_state(1);
        			}
				}else{
					if(pay_park == null){
						log.error("doUnLockMoney 处理用户被锁定的金额失败 订单不存在 orderid="+orderid);
						returnData.setReturnData(errorcode_data, "订单不存在", "", "1"); 
						throw new QzException("doUnLockMoney 处理用户被锁定的金额失败 订单不存在 orderid="+orderid);
					}
	    			//预约
	    			if(type == 0){
	    				if(pay_park.getExpect_state() != 1){
	    					throw new QzException("doUnLockMoney 已经处理过了-预约-不能重复处理  orderid="+orderid);
						}
	    				//预约
	    				//处理结果状态state  0:成功 1：失败
	    				if(state == 0){
	    					/**
	    					 * 预约 且  成功
	    					 */
		    				pay_park.setExpect_state(2);//预约状态  1：预约中  2：预约成功  3：预约失败 4：取消预约中 5：取消预约成功 6：取消预约失败
		    				pay_park.setIs_expect_deduct(0);//是否已经扣除预约超时钱0：已锁定扣款金额1：已经扣款成功2：已解绑锁定金额
		    				//----预约成功推送
		    				message = "亲!您预约【"+pay_park.getPi_name()+"】停车场成功.";
		    				title = "系统消息";

	    				}else{
	    					/**
	    					 * 预约 且  失败
	    					 */
	    					//解除锁定
		    				user_info.setLock_expect_money(user_info.getLock_expect_money() - money >= 0 ? user_info.getLock_expect_money() - money : 0);
		    				pay_park.setExpect_state(3);//预约状态  1：预约中  2：预约成功  3：预约失败 4：取消预约中 5：取消预约成功 6：取消预约失败
		    				pay_park.setIs_expect_deduct(2);//是否已经扣除预约超时钱0：已锁定扣款金额1：已经扣款成功2：已解绑锁定金额
		    				//更改订单为关闭
    						pay_park.setCancel_state(1);//用户取消下单状态0:没有关闭 1：关闭 2：异常
		    				//----预约失败推送
		    				message = "亲!您预约【"+pay_park.getPi_name()+"】停车场失败.";
		    				title = "系统消息";
	    				}
	    			}
	    			if(type == 1){
	    				if(pay_park.getExpect_state() != 4){
	    					throw new QzException("doUnLockMoney 已经处理过了-取消预约-不能重复处理  orderid="+orderid);
						}
	    				//取消预约
	    				//处理结果状态state  0:成功 1：失败
	    				if(state == 0){
	    					/**
	    					 * 取消预约 且  成功
	    					 */
	    					//解除锁定且关闭该订单
	    					user_info.setLock_expect_money(user_info.getLock_expect_money() - money >= 0 ? user_info.getLock_expect_money() - money : 0);
    						//更改订单为关闭
    						pay_park.setCancel_state(1);//用户取消下单状态0:没有取消1：取消 2：异常
    						pay_park.setExpect_state(5);//预约状态  1：预约中  2：预约成功  3：预约失败 4：取消预约中 5：取消预约成功 6：取消预约失败
    						pay_park.setIs_expect_deduct(2);//是否已经扣除预约超时钱0：已锁定扣款金额1：已经扣款成功2：已解绑锁定金额
    						//----取消预约成功推送
		    				message = "亲!您取消预约【"+pay_park.getPi_name()+"】停车场成功.";
		    				title = "系统消息";
	    				}else{
	    					/**
	    					 * 取消预约 且  失败
	    					 */
	    					//解除锁定金额且关闭该订单
    						//更改订单为关闭
    						pay_park.setCancel_state(0);//用户取消下单状态0:没有取消1：取消
    						pay_park.setExpect_state(6);//预约状态  1：预约中  2：预约成功  3：预约失败 4：取消预约中 5：取消预约成功 6：取消预约失败
    						pay_park.setIs_expect_deduct(0);//是否已经扣除预约超时钱0：已锁定扣款金额1：已经扣款成功2：已解绑锁定金额
    						//----取消预约失败推送
		    				message = "亲!您取消预约【"+pay_park.getPi_name()+"】停车场失败.";
		    				title = "系统消息";
	    				}
	    			}
	    			
	    			
    			}
				//-------
				//更新预约订单状态
				pay_park.setUtime(new Date());//设置订单更新时间
				int count = pay_parkDao.updateByKey(pay_park);
				if(count != 1){
					//处理失败
					log.error("doUnLockMoney 处理用户被锁定的金额失败pay_parkDao.updateByKey orderid="+orderid);
					returnData.setReturnData(errorcode_data, "处理失败", "","2"); 
					throw new QzException("doUnLockMoney 处理用户被锁定的金额失败 pay_parkDao.updateByKey orderid="+orderid);
				}
    		}else{
    			//租赁订单
	        	if(pay_month_park == null){
	        		//订单不存在
	        		log.error("doUnLockMoney 处理用户被锁定的金额失败 租赁订单不存在 orderid="+orderid);
					returnData.setReturnData(errorcode_data, "租赁订单不存在", "", "1"); 
					throw new QzException("doUnLockMoney 处理用户被锁定的金额失败 租赁订单不存在 orderid="+orderid);
	        	}
    			if(type == 2){
    				//租赁处理
    				if(state == null){
    					//表示道闸停车场没有调用更新接口处理 而是中心服务器本地的调用处理
            			//租赁
            			user_info.setLock_rent_money(user_info.getLock_rent_money() + money);
            			pay_month_park.setRent_state(1);
    				}else{
    					//处理结果状态state  0:成功 1：失败
    					if(pay_month_park.getRent_state() != 1){
	    					throw new QzException("doUnLockMoney 已经处理过了-pay_month_park-不能重复处理  orderid="+orderid);
						}
    					if(state == 0){
    						//离线道闸租赁成功
        					//解除锁定---返还金额给用户  并且进行推送通知 @@@@
            				user_info.setLock_rent_money(user_info.getLock_rent_money() - money >= 0 ? user_info.getLock_rent_money() - money : 0);
            				pay_month_park.setRent_state(2);//租赁状态 1：租赁中 2：租赁成功 3：租赁失败--返还金额
            				//这里更新商户账户金额
            				upManchentMoney(pay_month_park.getPu_id(),pay_month_park.getPu_nd(),money, 1);
            				
            				//这里添加JPUSH  租赁成功
		    				//----租赁推送
		    				message = "亲!您租赁【"+pay_month_park.getPi_name()+"】停车场成功.";
		    				title = "系统消息";
    					}else{
    						//离线道闸租赁失败
        					//解除锁定---返还金额给用户  并且进行推送通知 @@@@
            				user_info.setLock_rent_money(user_info.getLock_rent_money() - money >= 0 ? user_info.getLock_rent_money() - money : 0);
            				user_info.setUi_vc(user_info.getUi_vc()+money); 
            				pay_month_park.setRent_state(3);//租赁状态 1：租赁中 2：租赁成功 3：租赁失败--返还金额
            				pay_month_park.setCancel_state(1);//关闭该订单
            				pay_month_park.setPp_state(0);//设置成未支付
            				//记录该次用户虚拟币更改记录 order_type 订单类型 0:普通订单 1：租赁订单
            				String act_name="租赁失败返钱";
            	        	recordVC( 2, money, orderid, order_type, user_info.getUi_id(), returnData,act_name,user_info.getUuid(),user_info.getUi_tel(),pay_month_park.getPay_source()
            	        			,pay_month_park.getUpc_id(),pay_month_park.getDiscount_type(),(int)pay_month_park.getDiscount_money(),
														new Date());
            				//这里添加JPUSH 租赁失败 系统返还租赁金额XX元人民币到钱包
		    				//----租赁失败推送
		    				message = "亲!您租赁【"+pay_month_park.getPi_name()+"】停车场失败,订单已自动关闭.";
		    				title = "系统消息";
    						
    					}
    				}
					//-------
					//更新预约订单状态
					pay_month_park.setUtime(new Date());//设置订单更新时间
					int count = pay_month_parkDao.updateByKey(pay_month_park);
					if(count != 1){
						//处理失败
						log.error("doUnLockMoney 处理用户被锁定的金额失败 pay_month_parkDao.updateByKey orderid="+orderid);
						returnData.setReturnData(errorcode_data, "处理失败", "","2"); 
						throw new QzException("doUnLockMoney 处理用户被锁定的金额失败 pay_month_parkDao.updateByKey orderid="+orderid);
					}
    			}
    		}

			int count = user_infoDao.updateByKey(user_info);
			if(count < 1){
				//更新失败
				returnData.setReturnData(errorcode_data, "处理失败", "");
				throw new QzException("doUnLockMoney setLock_money 失败");
			}
			
			/**
			 * 这里处理推送
			 */
			if(state != null){
				payParkPB.pushOrderSate( 0, user_info , message, title,pay_park,pay_month_park);
			}

			//处理成功
			returnData.setReturnData(errorcode_success, "处理成功", ""); 
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnData.setReturnData(errorcode_data, "处理失败", "");
			throw new QzException("doUnLockMoney 处理失败",e);
		}
	}
	
	/**
	 * 验证是否能够进行ETC扣款调用
	 * @param user_info
	 * @return true:可以进行ETC扣款调用 false：不能进行扣款调用
	 */
	public boolean isETC_Pay(User_info user_info){
		if(!RequestUtil.checkObjectBlank(user_info.getBank_no())){
			return true;
		}
		return false;
	}
}
