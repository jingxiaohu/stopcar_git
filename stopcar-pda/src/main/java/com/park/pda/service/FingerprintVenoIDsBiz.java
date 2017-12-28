package com.park.pda.service;

import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.ReturnDataNew;
import com.park.dao.Finger_userinfo_relationDao;
import com.park.mvc.service.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 指纹系统-通过车牌查询用户指纹、指静脉信息
 *
 * @author zyy
 */
@Service
public class FingerprintVenoIDsBiz extends BaseBiz {

    /**
     * 指纹系统-通过用户ID查询指纹、指静脉ID列表
     */
    @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
    public void queryFingerVenofurIds(Long fu_id, ReturnDataNew returnData) {
        try {
            //查询指纹、指静脉ID列表
            String sql = "SELECT fur_id FROM finger_userinfo_relation where fu_id=:fu_id AND is_del=0 ";
            Map<String,Long> paramMap = new HashMap<String,Long>();
            paramMap.put("fu_id",fu_id);
            List furIdList = getMySelfService().queryForList(sql,paramMap);

            //返回数据
            returnData.setReturnData(errorcode_success, "查询成功", furIdList);
        } catch (Exception e) {
            log.error("指纹系统-通过用户ID查询指纹、指静脉ID列表-查询失败", e);
            returnData.setReturnData(errorcode_systerm, "指纹系统-通过用户ID查询指纹、指静脉ID列表查询失败", null);
        }

    }
}
