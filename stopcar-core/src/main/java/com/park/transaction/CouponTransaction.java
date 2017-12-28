/**  
* @Title: TT.java
* @Package com.intimes.biz
* @Description: TODO(用一句话描述该文件做什么)
* @author 敬小虎  
* @date 2015年3月20日 下午1:32:43
* @version V1.0  
*/ 
package com.park.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.park.exception.QzException;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.ActivityPB;
import com.park.mvc.service.common.CarPB;
import com.park.mvc.service.common.ParkCouponPB;
import com.park.mvc.service.common.ParkInfoPB;
import com.park.mvc.service.common.PayParkPB;
import com.park.mvc.service.common.UserPB;

/**
 * 车辆相关事务类
 * @author jingxiaohu
 *
 */
@Transactional(rollbackFor=QzException.class)
@Service
public class CouponTransaction extends  BaseBiz{
	@Autowired
	private PayParkPB payParkPB;
	@Autowired
	private UserPB userPB;
	@Autowired
	private ParkInfoPB parkInfoPB;
	@Autowired
	protected ActivityPB activityPB;
	@Autowired
	protected ParkCouponPB parkCouponPB;
	@Autowired
	protected CarPB carPB;
	
	

}
