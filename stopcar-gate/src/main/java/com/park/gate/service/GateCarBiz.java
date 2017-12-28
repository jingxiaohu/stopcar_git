package com.park.gate.service;

import com.park.bean.User_carcode;
import com.park.mvc.service.BaseBiz;
import org.springframework.stereotype.Service;

/**
 * 处理车辆信息的业务逻辑管理类
 *
 * @author jingxiaohu
 */
@Service
public class GateCarBiz extends BaseBiz {

    /**
     * 查询用户和车牌号绑定关系
     */

    public User_carcode queryUserCarBycode(String car_code) {
        try {
            //首先判断是否已经绑定了该量车
            String sql = "SELECT *  FROM user_carcode WHERE car_code=? LIMIT 1";
            User_carcode user_carcode = getMySelfService()
                    .queryUniqueT(sql, User_carcode.class, car_code);
            return user_carcode;
        } catch (Exception e) {
            log.error("queryUserCarBycode is error" + e.getMessage(), e);
        }
        return null;
    }


}
