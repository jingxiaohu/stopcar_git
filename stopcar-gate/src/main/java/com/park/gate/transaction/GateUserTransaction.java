/**
 * @Title: TT.java
 * @Package com.intimes.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 敬小虎
 * @date 2015年3月20日 下午1:32:43
 * @version V1.0
 */
package com.park.gate.transaction;

import com.park.bean.*;
import com.park.exception.QzException;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 敬小虎
 * @ClassName: AppSdkTransaction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2015年3月20日 下午2:11:51
 */
@Transactional(rollbackFor = QzException.class)
@Service
public class GateUserTransaction extends BaseBiz {

    @Autowired
    private UserPB userPB;

    /**
     * 处理用户被锁定的金额----情况1：锁定金额  情况2：解除锁定金额 3:解除锁定金额和返还金额
     *
     * @param type       : 0：预约   1：取消预约  2：租赁
     * @param order_type 0:预约  1：租赁
     * @param state      处理结果状态 0:成功 1：失败
     */
    public void doUnLockMoney(int type, int order_type, Integer state, int money, User_info user_info,
                              String orderid, long pi_id, String area_code, Pay_month_park pay_month_park,
                              Pay_park pay_park, ReturnDataNew returnData) throws QzException {
        userPB.doUnLockMoney(type, order_type, state, money, user_info, orderid, pi_id, area_code,
                pay_month_park, pay_park, returnData);
    }

}
