package com.park.gate.service;

import com.park.bean.Park_info;
import com.park.bean.Parkinfo_monthfree;
import com.park.bean.ReturnDataNew;
import com.park.dao.DaoFactory;
import com.park.gate.action.v1.monthfree.param.Param_ParkMonthFree;
import com.park.gate.action.v1.monthfree.param.Param_ParkMonthFreeDel;
import com.park.gate.action.v1.monthfree.param.Param_ParkMonthFreeQuery;
import com.park.gate.action.v1.monthfree.param.Param_ParkMonthFreeState;
import com.park.mvc.service.BaseBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 停车场本地包月车和本地免费车服务类
 * Created by zzy on 2017/6/16.
 */
@Service("packMonthFreeActionBiz")
public class ParkMonthFreeActionBiz extends BaseBiz {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 有效
     */
    private static final int STATE_VALID = 0;

    /**
     * 无效
     */
    private static final int STATE_INVALID = 1;

    @Autowired
    private DaoFactory daoFactory;
    /**
     * 添加
     * @param param
     * @param returnData
     */
    public void recordParkMonthFree(Param_ParkMonthFree param, ReturnDataNew returnData){

        try{
            long pi_id = param.getPi_id();
            String area_code = param.getArea_code();

            Park_info park_info = returnParkInfo(pi_id,area_code);
            if (park_info == null) {
                returnData.setReturnData(errorcode_param,
                        "不存在area_code：" + area_code + "和pi_id:" + pi_id + "对应停车场", null);
                return;
            }
            if (park_info.getLat() == 0 || park_info.getLng() == 0 || park_info.pi_state == 0) {
                returnData.setReturnData(errorcode_param, "停车场未开启", null);
                return;
            }
            long pu_id = park_info.getPu_id();
            if (pu_id <= 0) {
                returnData.setReturnData(errorcode_param, "停车场未绑定商户", null);
                return;
            }

            Parkinfo_monthfree monthfree = queryParkinfo_monthfreeUnique(param);
            if(null != monthfree){
                returnData.setReturnData(errorcode_param,
                        "该区域(area_code：" + area_code + ")下停车场(pi_id:" + pi_id + ") 已存在车辆信息:"+param.getCar_code(),null);
                return;
            }

            Parkinfo_monthfree parkinfo_monthfree = new Parkinfo_monthfree();
            parkinfo_monthfree.setPu_id(pu_id);
            parkinfo_monthfree.setPi_id(pi_id);
            parkinfo_monthfree.setArea_code(area_code);
            parkinfo_monthfree.setCar_code(param.getCar_code());
            parkinfo_monthfree.setMoney(param.getMoney());
            parkinfo_monthfree.setCar_type(param.getCar_type());
            parkinfo_monthfree.setCar_code_color(param.getCar_code_color());
            parkinfo_monthfree.setCar_color(param.getCar_color());
            parkinfo_monthfree.setStart_time_str(param.getStart_time_str());
            parkinfo_monthfree.setEnd_time_str(param.getEnd_time_str());
            parkinfo_monthfree.setType(param.getType());
            parkinfo_monthfree.setCtime(new Date(param.getCtime()));
            parkinfo_monthfree.setUtime(new Date(param.getUtime()));
            parkinfo_monthfree.setNote("");
            parkinfo_monthfree.setState(STATE_VALID); // 0:有效  1:无效
            parkinfo_monthfree.setCar_tel(param.getCar_tel());
            parkinfo_monthfree.setCar_name(param.getCar_name());
            parkinfo_monthfree.setLocal_loginname(param.getLocal_loginname());
            parkinfo_monthfree.setClient_id(param.getClient_id());

            int count = daoFactory.getParkinfo_monthfreeDao().insert(parkinfo_monthfree);
            if(count >= 1){
                returnData.setReturnData(errorcode_success,"插入数据成功",parkinfo_monthfree);
                return ;
            }
            returnData.setReturnData(errorcode_data,"插入数据失败",null);

        }catch (Exception e){
            log.error("异常-->",e);
            returnData.setReturnData(errorcode_systerm,"系统异常",null);
        }

    }

    /**
     * 修改内容
     * @param param
     * @param returnData
     */
    public void updateParkMonthFree(Param_ParkMonthFree param, ReturnDataNew returnData){

        try{
            Parkinfo_monthfree parkinfo_monthfree = queryParkinfo_monthfreeUnique(param);

            if(null == parkinfo_monthfree){
                returnData.setReturnData(errorcode_param,"没有查询到信息",null);
                return;
            }

            //parkinfo_monthfree.setCar_code(param.getCar_code());
            parkinfo_monthfree.setMoney(param.getMoney());
            parkinfo_monthfree.setCar_type(param.getCar_type());
            parkinfo_monthfree.setCar_code_color(param.getCar_code_color());
            parkinfo_monthfree.setCar_color(param.getCar_color());
            parkinfo_monthfree.setStart_time_str(param.getStart_time_str());
            parkinfo_monthfree.setEnd_time_str(param.getEnd_time_str());
            parkinfo_monthfree.setType(param.getType());
            parkinfo_monthfree.setCtime(new Date(param.getCtime()));
            parkinfo_monthfree.setUtime(new Date(param.getUtime()));
            parkinfo_monthfree.setNote("");
            parkinfo_monthfree.setCar_tel(param.getCar_tel());
            parkinfo_monthfree.setCar_name(param.getCar_name());
            parkinfo_monthfree.setLocal_loginname(param.getLocal_loginname());

            daoFactory.getParkinfo_monthfreeDao().updateByKey(parkinfo_monthfree);
            returnData.setReturnData(errorcode_success,"修改成功",parkinfo_monthfree);
        }catch (Exception e){
            log.error("修改信息异常-->",e);
            returnData.setReturnData(errorcode_systerm,"数据库异常",null);
        }
    }

    /**
     * 修改状态
     * @param param
     * @param returnData
     */
    public void updateParkMonthFreeState(Param_ParkMonthFreeState param, ReturnDataNew returnData){
        try{
            Param_ParkMonthFree free = new Param_ParkMonthFree();
            free.setPi_id(param.getPi_id());
            free.setArea_code(param.getArea_code());
            free.setCar_code(param.getCar_code());
            free.setClient_id(param.getClient_id());

            Parkinfo_monthfree parkinfo_monthfree = queryParkinfo_monthfreeUnique(free);

            if(null == parkinfo_monthfree){
                returnData.setReturnData(errorcode_param,"没有查询到信息",null);
                return;
            }

            String sql = "update parkinfo_monthfree t set t.state = ? where t.id = ? ";

            daoFactory.getParkinfo_monthfreeDao().update(sql,param.getState(),parkinfo_monthfree.getId());
            returnData.setReturnData(errorcode_success,"修改状态成功",null);
        }catch (Exception e){
            log.error("修改状态失败-->",e);
            returnData.setReturnData(errorcode_systerm,"修改状态失败",null);
        }
    }


    /**
     * 删除信息
     * @param param
     * @param returnData
     */
    public void delParkMonthFree(Param_ParkMonthFreeDel param, ReturnDataNew returnData){
        try{
            Param_ParkMonthFree free = new Param_ParkMonthFree();
            free.setPi_id(param.getPi_id());
            free.setArea_code(param.getArea_code());
            free.setCar_code(param.getCar_code());
            free.setClient_id(param.getClient_id());
            Parkinfo_monthfree parkinfo_monthfree = queryParkinfo_monthfreeUnique(free);

            if(null == parkinfo_monthfree){
                returnData.setReturnData(errorcode_param,"没有查询到信息",null);
                return;
            }
            int count = daoFactory.getParkinfo_monthfreeDao().deleteByKey(parkinfo_monthfree.getId());
            if(count >=1){
                returnData.setReturnData(errorcode_success,"删除成功",null);
            }

        }catch (Exception e){
            log.error("删除信息异常-->",e);
            returnData.setReturnData(errorcode_systerm,"数据库异常",null);
        }
    }

    /**
     * 根据停车场id、区域号、车牌号、道闸本地记录的主键ID 查询信息
     * @param param
     * @return
     */
    public Parkinfo_monthfree queryParkinfo_monthfreeUnique(Param_ParkMonthFree param){
        Parkinfo_monthfree parkinfo_monthfree = null;
        try{

            String sql = "select * from parkinfo_monthfree t where t.pi_id=? and t.area_code=? and t.car_code=? and client_id = ?";

            parkinfo_monthfree = getMySelfService().getBaseDao().queryUniqueT(sql.toString(),
                    Parkinfo_monthfree.class, param.getPi_id(), param.getArea_code(), param.getCar_code(),param.getClient_id());
            if(null == parkinfo_monthfree){
                return null;
            }
        }catch (Exception e){
            log.error("查询失败-->",e);
        }
        return parkinfo_monthfree;
    }

    /**
     * 根据停车场id、区域号、车牌号 查询信息
     * @param param
     * @return
     */
    public Parkinfo_monthfree queryParkInfoMonthFreeInfo(Param_ParkMonthFreeQuery param){
        Parkinfo_monthfree parkinfo_monthfree = null;

        try{
            String sql = "select * from parkinfo_monthfree t where t.pi_id=? and t.area_code=? and t.car_code=? and state = 0 ORDER BY t.ctime asc limit 1";
            parkinfo_monthfree = getMySelfService().getBaseDao().queryUniqueT(sql.toString(),
                    Parkinfo_monthfree.class, param.getPi_id(), param.getArea_code(), param.getCar_code());
            if(null == parkinfo_monthfree){
                return null;
            }
        }catch (Exception e){
            log.error("查询失败-->",e);
        }
        return parkinfo_monthfree;
    }
}
