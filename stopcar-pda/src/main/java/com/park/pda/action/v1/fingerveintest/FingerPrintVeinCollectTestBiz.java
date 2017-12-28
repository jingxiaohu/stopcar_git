package com.park.pda.action.v1.fingerveintest;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.park.bean.*;
import com.park.constants.Constants;
import com.park.dao.*;
import com.park.exception.QzException;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.mvc.service.BaseBiz;
import com.park.pda.action.v1.fingerprintcollection.param.UserFingerEvnoInfo;
import com.park.pda.action.v1.fingerprintvein.param.*;
import com.park.pda.action.v1.fingerveintest.param.Param_pdaSureTest;
import com.park.pda.action.v1.fingerveintest.param.UserInfoTestReq;
import com.park.service.ETCHelper;
import com.park.service.LZFHelper;
import com.park.service.etc.etcapi.ETCResponse;
import com.park.util.FileUtil;
import com.park.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import zk.jni.Zkfp;
import zk.jni.Zkfv;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zzy on 2017/8/29.
 */
@Service("fingerPrintVeinCollectTestBiz")
public class FingerPrintVeinCollectTestBiz extends BaseBiz {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Finger_userinfo_new2Dao fingerUserinfoNew2Dao;

    @Autowired
    private Finger_userinfo_relation2Dao fingerUserinfoRelation2Dao;

    @Autowired
    private Finger_userinfo_carcode2Dao fingerUserinfoCarcode2Dao;

    private long uiId = 100000000L;

    private String uiNd = "abcdefj1233445";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 采集用户指纹静脉信息，
     *
     * @param req
     * @param returnData
     */
    @Transactional(rollbackFor = QzException.class)
    public void collectUserInfo(UserInfoTestReq req, ReturnDataNew returnData) throws QzException{
        try {
            String tel = req.getUi_tel();
            Finger_userinfo_new2 fingerUserinfoNew2 = null;
            fingerUserinfoNew2 = getMySelfService().queryUniqueT(
                    "SELECT * FROM finger_userinfo_new2 t where t.ui_tel = ? and t.is_del = 0",
                    Finger_userinfo_new2.class,
                    tel);
            if(null != fingerUserinfoNew2){
                log.info("用户指纹信息已经录入");
                returnData.setReturnData(errorcode_data,"用户指纹信息已经录入","");
                return;
            }

            fingerUserinfoNew2 = makeFingerUserInfo(fingerUserinfoNew2,req);

            int fuId = fingerUserinfoNew2Dao.insert(fingerUserinfoNew2);
            if(fuId < 1){
                returnData.setReturnData(errorcode_systerm,"用户指纹信息添加失败",null);
                return;
            }
            fingerUserinfoNew2.setFu_id(fuId);

            List<UserFingerEvnoInfo> list = parseFingerprintVeinStr(req.getFingerprint_vein_str());
            for (UserFingerEvnoInfo evnoInfo : list) {
                Finger_userinfo_relation2 userinfoRelation = new Finger_userinfo_relation2();
                userinfoRelation.setFu_id(fuId);
                userinfoRelation.setFingerprint(evnoInfo.getFingerprint());
                userinfoRelation.setFinger_veno(evnoInfo.getVein());
                userinfoRelation.setFingerprint_hash(evnoInfo.getFingerprint_hash());
                userinfoRelation.setFinger_veno_hash(evnoInfo.getVein_hash());
                Date date = new Date();
                userinfoRelation.setCtime(date);
                userinfoRelation.setUtime(date);
                userinfoRelation.setNote("用户指纹静脉信息");
                int result = fingerUserinfoRelation2Dao.insert(userinfoRelation);
                if(result < 1){
                    log.info("用户指纹静脉信息采集异常");
                    throw new QzException("用户指纹静脉信息采集异常");
                }
            }

            returnData.setReturnData(errorcode_success, "添加指纹用户信息成功", null);

        } catch (Exception e) {
            log.error("用户指纹静脉信息采集异常-->", e);
            //returnData.setReturnData(errorcode_systerm, "system is error", null);
            throw new QzException("用户指纹静脉信息采集异常");
        }
    }


    /**
     *
     * @param req
     * @param returnData
     */
    public void fingerVeinVerify(Param_pdaSureTest req,ReturnDataNew returnData) throws Exception{

        String result = "";
        String fingerprint = req.getFingerprint();
        String finger_veno = req.getFinger_veno();

        JSONObject returnobj = new JSONObject();

        Finger_userinfo_new2 finger_userinfo = getMySelfService().queryUniqueT(
                "SELECT * FROM finger_userinfo_new2 t WHERE t.ui_tel = ? AND t.is_del = 0",
                Finger_userinfo_new2.class,
                req.getTel());
        if(null == finger_userinfo){
            returnData.setReturnData(errorcode_data,"用户指纹静脉信息不存在",null);
            return;
        }
        //服务端验证指纹
        List<String> fingerprints = jdbcTemplate.queryForList(
                "SELECT fingerprint FROM finger_userinfo_relation2 WHERE fu_id=? AND is_del=0",
                String.class, finger_userinfo.fu_id);
        boolean fingerprintPass = false;
        for (String fp : fingerprints) {
            if (log.isInfoEnabled()) {
                log.info("zkfp验证结果：{}", Zkfp.SingleMatch(Base64Utils.decode(fp.getBytes()),
                        Base64Utils.decode(fingerprint.getBytes())));
            }
            if (Zkfp.SingleMatch(Base64Utils.decode(fp.getBytes()),
                    Base64Utils.decode(fingerprint.getBytes())) > 60) {
                fingerprintPass = true;
                break;
            }
        }

        if(fingerprintPass){
            returnobj.put("fingerprintPass", "指纹验证成功");
            result = "指纹验证成功";
        }else{
            returnobj.put("fingerprintpass", "指纹验证失败");
            result = "指纹验证失败";
        }


        //服务端验证指静脉
        List<String> finger_venos = jdbcTemplate.queryForList(
                "SELECT finger_veno FROM finger_userinfo_relation2 WHERE fu_id=? AND is_del=0",
                String.class, finger_userinfo.fu_id);
        boolean finger_venoPass = false;
        out:
        for (String fv : finger_venos) {
            for (String fvu : fv.split(",")) {
                if (log.isInfoEnabled()) {
                    log.info("zkfv验证结果：{}",
                            Zkfv.SingleMatch(Base64Utils.decode(finger_veno.getBytes()),
                                    Base64Utils.decode(fvu.getBytes())));
                }
                if (Zkfv.SingleMatch(Base64Utils.decode(finger_veno.getBytes()),
                        Base64Utils.decode(fvu.getBytes())) > 60) {
                    finger_venoPass = true;
                    break out;
                }
            }
        }

        if(finger_venoPass){
            returnobj.put("finger_venopass", "指静脉验证成功");
            result = result + ";"+"指静脉验证成功";
        }else{
            returnobj.put("finger_venopass", "指静脉验证失败");
            result = result + ";"+"指静脉验证失败";
        }

        returnData.setReturnData(errorcode_success,result,returnobj);

    }

    /**
     * 公共创建Finger_userinfo_new2方法
     *
     * @param req
     * @return
     */
    private Finger_userinfo_new2 makeFingerUserInfo(Finger_userinfo_new2 userInfo, UserInfoTestReq req) throws Exception {
        if (null == userInfo) {
            userInfo = new Finger_userinfo_new2();
        }

        Date dateS = new Date();
        userInfo.setFu_nd(returnUserRegisterUUID());//唯一标识符
        userInfo.setUi_tel(req.getUi_tel());//用户手机号码
        userInfo.setUi_id(uiId);//平台用户ID
        userInfo.setUi_nd(uiNd);//平台用户唯一标识符
        userInfo.setName(req.getName());//用户真实姓名
        userInfo.setSfz_number("");//用户真实身份证号码

        userInfo.setState(1);//有效状态(0：无	效	1：有效)
        userInfo.setIs_del(0);//是否删除 0:没有	1：删除
        userInfo.setCtime(dateS);//创建时间
        userInfo.setUtime(dateS);//修改时间
        userInfo.setMac(req.getMac());//采集数据提交设备MAC
        if(!RequestUtil.checkObjectBlank(req.getGather_id())){
            userInfo.setGather_id(req.getGather_id());//采集数据提交设备基本信息表主键ID
        }
        userInfo.setFinger_veno_state(1);  //指静脉完成度(0:未完成 1：已经完成)
        userInfo.setFingerprint_state(1);  //指纹完成度（0：未完成 1：已经完成）
        userInfo.setNote("初始化数据");//备注
        return userInfo;
    }

    /**
     * 解析
     *
     * @param input
     * @return
     */
    private List<UserFingerEvnoInfo> parseFingerprintVeinStr(String input) throws Exception {

        List<UserFingerEvnoInfo> list = new ArrayList<UserFingerEvnoInfo>();
        Gson gson = new Gson();
        //创建一个JsonParser
        JsonParser parser = new JsonParser();
        //通过JsonParser对象可以把 json 格式的字符串解析成一个JsonElement对象
        JsonElement el = parser.parse(input);

        //把JsonElement对象转换成JsonArray
        JsonArray jsonArray = null;
        if (el.isJsonArray()) {
            jsonArray = el.getAsJsonArray();
        }

        //遍历JsonArray对象
        UserFingerEvnoInfo userFingerEvnoInfo = null;
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
            //JsonElement转换为JavaBean对象
            userFingerEvnoInfo = gson.fromJson(e, UserFingerEvnoInfo.class);
            list.add(userFingerEvnoInfo);
//                System.out.println("fingerprint:" + userFingerEvnoInfo.getFingerprint());
//                System.out.println("vein:" + userFingerEvnoInfo.getVein());
//                System.out.println("====================================");
        }
        return list;
    }

}
