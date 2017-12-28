package com.park.pda.service;

import com.alibaba.fastjson.JSONObject;
import com.park.bean.*;
import com.park.constants.Constants;
import com.park.dao.Finger_userinfo_bankDao;
import com.park.dao.Finger_userinfo_carcodeDao;
import com.park.dao.Finger_userinfo_newDao;
import com.park.dao.Finger_userinfo_relationDao;
import com.park.exception.QzException;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.mvc.service.BaseBiz;
import com.park.pda.action.v1.fingerprintvein.param.*;
import com.park.service.ETCHelper;
import com.park.service.LZFHelper;
import com.park.service.etc.etcapi.ETCResponse;
import com.park.util.FileUtil;
import com.park.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by zzy on 2017/8/29.
 */
@Service("fingerPrintVeinCollectBiz")
public class FingerPrintVeinCollectBiz extends BaseBiz {
    /**
     * 短信超时时间  分钟
     */
    private int timeout_minutes = 30;

    /**
     * 已经支付一钱签约成功
     */
    private static String Resp_4001 = "4001";

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Finger_userinfo_newDao fingerUserinfoNewDao;

    @Autowired
    private Finger_userinfo_bankDao fingerUserinfoBankDao;

    @Autowired
    private Finger_userinfo_relationDao fingerUserinfoRelationDao;

    @Autowired
    private Finger_userinfo_carcodeDao fingerUserinfoCarcodeDao;

    @Autowired
    private LZFHelper lZFHelper;

    @Autowired
    private ETCHelper etcHelper;

    /**
     * 短信验证  成功：true  失败:false
     *
     * @param returnDataNew
     * @return
     */
    public boolean smsCodeVerify(String tel, String verifyList, String vclass, String verifyCode, ReturnDataNew returnDataNew) throws Exception {
        //验证手机号是否合法
        if (!BaseV1Controller.isMobileNO(tel)) {
            returnDataNew.setReturnData(errorcode_param, "请输入正确的手机号", null);
            return false;
        }

        String sql = "SELECT v_code,v_time FROM sms_validate WHERE v_tel = ? AND v_list = ? AND v_class = ? AND v_code=? LIMIT 1";
        Sms_validate bsv = getMySelfService()
                .queryUniqueT(sql, Sms_validate.class, tel, verifyList, vclass, verifyCode);
        if (bsv == null) {
            returnDataNew.setReturnData(errorcode_data, "请输入正确的验证码", "");
            return false;
        }
        //检查时间是否过期 30分钟
        long time = bsv.getV_time();
        if (System.currentTimeMillis() - time > timeout_minutes * 60 * 1000) {
            returnDataNew.setReturnData(errorcode_data, "验证码已过期", "");
            return false;
        }
        return true;
    }


    /**
     * 采集用户信息，
     *
     * @param req
     * @param returnData
     */
    @Transactional(rollbackFor = QzException.class)
    public void collectUserInfo(UserInfoReq req, ReturnDataNew returnData) {
        try {
            User_pay userPay = null;
            Finger_userinfo_bank bank = null;

            //这里手机号是唯一的
            String sql = "SELECT * FROM finger_userinfo_new t WHERE t.ui_tel = ? ";
            Finger_userinfo_new userinfoNew = getMySelfService().queryUniqueT(sql, Finger_userinfo_new.class, req.getUi_tel());
            if (null == userinfoNew) {
                //龙支付web下单
                userPay = LZFPlaceAnOrder(req);
                userinfoNew = makeFingerUserInfo(userinfoNew, req, userPay);
                int fuId = fingerUserinfoNewDao.insert(userinfoNew);
                if (fuId < 1) {
                    throw new QzException("指纹静脉-用户信息新增失败");
                }
                userinfoNew.setFu_id(fuId);
                bank = makeFingerUserInfoBank(bank, userinfoNew, req, userPay);
                int fubId = fingerUserinfoBankDao.insert(bank);
                if (fubId < 1) {
                    throw new QzException("指纹静脉采集-用户银行卡管理新增失败");
                }
                bank.setFub_id(fubId);

            } else {
                if (1 == userinfoNew.getIs_del()) {  //已删除
                    userPay = LZFPlaceAnOrder(req);
                    userinfoNew = makeFingerUserInfo(userinfoNew, req, userPay);
                    int fuId = fingerUserinfoNewDao.updateByKey(userinfoNew);
                    if (fuId < 1) {
                        throw new QzException("新增用户信息失败");
                    }

                    Finger_userinfo_bank userinfoBank = getFingerUserInfoBankByFuId(userinfoNew.getFu_id());

                    if(null != userinfoBank){
                        //签约成功
                        if(userinfoBank.getIs_sign() == 1){   //是否签约成功（0：没有签约 1：签约成功 2：签约失败 3：解除签约）
                            String serialNo = return16UUID();
                            log.info("ETC 解约");
                            //解约
                            ETCResponse etcResponse = etcHelper.toregister(
                                    serialNo,
                                    1,
                                    userinfoBank.getFu_nd(),
                                    userinfoBank.getName(),
                                    userinfoBank.getSfz_number(),
                                    userinfoBank.getBank_card_number());
                            if (null == etcResponse || !etcResponse.isSucceed()) {
                                log.info("解约失败响应描述：" + etcResponse.getRespMess());
                                return;
                            }
                            bank = makeFingerUserInfoBank(userinfoBank, userinfoNew, req, userPay);

                            int fubId = fingerUserinfoBankDao.updateByKey(bank);
                            if (fubId < 1) {
                                throw new QzException("指纹静脉采集-用户银行卡管理更新失败");
                            }

                            //未签约成功
                        }else{
                            bank = makeFingerUserInfoBank(userinfoBank, userinfoNew, req, userPay);
                            int fubId = fingerUserinfoBankDao.updateByKey(bank);
                            if (fubId < 1) {
                                throw new QzException("指纹静脉采集-用户银行卡管理更新失败");
                            }
                        }
                    }

                    //已录入用户信息，未删除
                } else {
                    Finger_userinfo_bank userinfoBank = getFingerUserInfoBankByFuId(userinfoNew.getFu_id());
                    userPay = LZFPlaceAnOrder(req);
                    if(null != userinfoBank){
                        //未签约成功
                        if(userinfoBank.getIs_sign() != 1){

                            log.info("用户未删除，并且签约不成功时，更新uiserinfo");
                            //用户未删除，并且签约不成功时，更新uiserinfo
                            userinfoNew = makeFingerUserInfo(userinfoNew, req, userPay);
                            int fuId = fingerUserinfoNewDao.updateByKey(userinfoNew);
                            if (fuId < 1) {
                                throw new QzException("新增用户信息失败");
                            }

                            bank = makeFingerUserInfoBank(userinfoBank, userinfoNew, req, userPay);

                            int fubId = fingerUserinfoBankDao.updateByKey(bank);
                            if (fubId < 1) {
                                throw new QzException("指纹静脉采集-用户银行卡管理更新失败");
                            }
                        }else{
                            log.info("用户已经采集信息，不能重复采集[{}]", req.getUi_tel());
                            returnData.setReturnData(errorcode_data, "用户已经采集信息，不能重复采集", null);
                            return;
                        }
                    }else{
                        bank = makeFingerUserInfoBank(bank, userinfoNew, req, userPay);
                        int fubId = fingerUserinfoBankDao.insert(bank);
                        if (fubId < 1) {
                            throw new QzException("指纹静脉采集-用户银行卡管理新增失败");
                        }
                        bank.setFub_id(fubId);
                    }
                }
            }

            JSONObject returnobj = new JSONObject();
            returnobj.put("lzf_info", userPay);
            //returnobj.put("finger_userinfo", userinfoNew);
            returnobj.put("finger_userbank", bank);
            returnData.setReturnData(errorcode_success, "交易成功", returnobj);
        } catch (Exception e) {
            log.error("用户指纹静脉信息采集异常-->", e);
            returnData.setReturnData(errorcode_systerm, "system is error", null);
        }
    }

    /**
     * 支付一分钱验证,验证成功后建行卡绑定
     *
     * @param checkSignReq
     * @param returnDataNew
     */
    public void fingerCheckSign(FingerPrintVeionCheckSignReq checkSignReq, ReturnDataNew returnDataNew) {
        try {

            Finger_userinfo_new fingerUserinfoNew = getFingerUserInfoNewByKey(checkSignReq.getFu_id());
            if (null == fingerUserinfoNew) {
                returnDataNew.setReturnData(errorcode_data, "用户信息不存在", null);
                return;
            }

            long fubId = checkSignReq.getFub_id();
            Finger_userinfo_bank bank = fingerUserinfoBankDao.selectByKey(fubId);
            if (null == bank) {
                returnDataNew.setReturnData(errorcode_data, "指纹采集-用户银行卡信息不存在", null);
                return;
            }
            if (!checkSignReq.getFu_nd().equals(bank.getFu_nd())) {
                returnDataNew.setReturnData(errorcode_data, "亲，不能绑定他人的信息", null);
                return;
            }

            //这里需要 调用ETC银企直连接口 进行查询处理
            JSONObject returnobj = new JSONObject();
            returnobj.put("state", 0);//0：未签约  1：签约成功

            String orderId = bank.getOrderid();
            String bankNo = bank.getBank_card_number();
            boolean flag;//"接口返回查询成功"
            flag = lZFHelper.accountCheck(orderId, bankNo);
            //flag = true;
            if (flag) {
                bank.setVerify_sign(1);  //是否签约支付验证成功 0：未验证  1：成功 2：失败
                String serialNo = return16UUID();
                if (bank.getIs_sign() != 1) {
                    ETCResponse eTCResponse = etcHelper.toregister(
                            serialNo
                            , 0
                            , bank.getFu_nd()
                            , bank.getName()
                            , bank.getSfz_number(),
                            bank.getBank_card_number());
                    if (eTCResponse == null || !eTCResponse.isSucceed()) {
                        log.error("建行ETC签约失败：" + eTCResponse);
                        //签约失败
                        bank.setIs_sign(2);
                        bank.setNote("建行ETC签约失败");
                    } else {
                        bank.setIs_sign(1);
                        bank.setSigntime(new Date());
                        bank.setNote("建行ETC签约成功");
                    }
                    int count = fingerUserinfoBankDao.updateByKey(bank);
                    if (count >= 1 && eTCResponse.isSucceed()) {
                        returnDataNew.setReturnData(errorcode_success, "支付一分钱验证并签约成功", null);
                    } else {
                        returnDataNew.setReturnData(errorcode_data, "支付一分钱验证失败", null);
                    }
                }else{
                    returnDataNew.setReturnData(errorcode_success, "已经签约成功", null);
                }

            } else {
                log.info("龙支付支付结果未知或失败");
                returnDataNew.setReturnData(errorcode_data, "龙支付支付结果未知或失败", null);
            }
        } catch (Exception e) {
            log.error("支付一分钱验证异常-->", e);
        }
    }

    /**
     * 手指信息采集
     *
     * @param req
     * @param returnData
     */
    @Transactional(rollbackFor = QzException.class)
    public void fingerCollect(FingerCollectReq req, ReturnDataNew returnData) {
        try {

            long fuId = req.getFu_id();
            Finger_userinfo_new fingerUserinfoNew = getFingerUserInfoNewByKey(fuId);
            if (null == fingerUserinfoNew) {
                returnData.setReturnData(errorcode_data, "用户信息不存在", null);
                return;
            }
            String fuNd = fingerUserinfoNew.getFu_nd();
            Finger_userinfo_relation userinfoRelation = new Finger_userinfo_relation();
            userinfoRelation.setFu_id(fuId);
            userinfoRelation.setFingerprint(req.getFingerprint());
            userinfoRelation.setFinger_veno(req.getVein());
            userinfoRelation.setFingerprint_hash(req.getFingerprint_hash());
            userinfoRelation.setFinger_veno_hash(req.getVein_hash());
            Date date = new Date();
            userinfoRelation.setCtime(date);
            userinfoRelation.setUtime(date);
            userinfoRelation.setNote("用户指纹静脉信息");
            userinfoRelation.setFu_nd(fuNd);
            int furId = fingerUserinfoRelationDao.insert(userinfoRelation);
            if (furId < 1) {
                returnData.setReturnData(errorcode_systerm, "手指信息采集失败", null);
                return;
            }
            userinfoRelation.setFur_id(furId);

            //上传文件
            String fingerImgName = req.getFingerprint_img_name();
            String veinImgName = req.getFinger_veno_img_name();

            String fingerImgUrl = FileUtil.uploadScaleAvatarImage(req.getFingerprint_img(), fingerImgName, Constants.FINGER_IMGPATH,
                    0, 0, fingerUserinfoNew.getUi_tel());
            if (fingerImgUrl == null) {
                returnData.setReturnData(errorcode_data, "指纹图片上传错误", null);
                return;
            }

            //上传文件
            String veinImgUrl = FileUtil.uploadScaleAvatarImage(req.getFinger_veno_img(), veinImgName, Constants.FINGER_IMGPATH,
                    0, 0, fingerUserinfoNew.getUi_tel());
            if (veinImgUrl == null) {
                returnData.setReturnData(errorcode_data, "静脉图片上传错误", null);
                return;
            }

            userinfoRelation.setFingerprint_img(fingerImgUrl);
            userinfoRelation.setFinger_veno_img(veinImgUrl);

            int result = fingerUserinfoRelationDao.updateByKey(userinfoRelation);
            if (result < 1) {
//                returnData.setReturnData(errorcode_systerm, "指纹静脉图片上传数据库更新失败", null);
//                return;
                throw new QzException("指纹静脉图片上传数据库更新失败");
            }

            fingerUserinfoNew.setFingerprint_state(1);  //指纹完成度（0：未完成 1：已经完成）
            fingerUserinfoNew.setFinger_veno_state(1);  //指静脉完成度(0:未完成 1：已经完成)
            int fi = fingerUserinfoNewDao.updateByKey(fingerUserinfoNew);
            if (fi < 1) {
                //returnData.setReturnData(errorcode_systerm, "用户信息更新指纹静脉完成度失败", null);
                //return;
                throw new QzException("用户信息更新指纹静脉完成度失败");
            }

            returnData.setReturnData(errorcode_success, "手指信息采集成功", userinfoRelation);

        } catch (Exception e) {
            log.error("手指信息采集异常-->", e);
        }
    }

    /**
     * 用户手指信息删除
     *
     * @param req
     * @param returnData
     */
    @Transactional(rollbackFor = QzException.class)
    public void fingerDelete(FingerDelReq req, ReturnDataNew returnData) throws Exception{
        try {
            long furId = req.getFur_id();
            long fuId = req.getFu_id();

            List<Finger_userinfo_relation> listR = getMySelfService().queryListT(
                    "SELECT * FROM finger_userinfo_relation t WHERE t.fu_id = ?",
                    Finger_userinfo_relation.class,
                    fuId);
            if(null != listR && listR.size() <= 1){
                returnData.setReturnData(errorcode_data, "必须保留一个指纹", null);
                return;
            }

            Finger_userinfo_relation relation = getMySelfService().queryUniqueT(
                    "SELECT * FROM finger_userinfo_relation t WHERE t.fur_id = ? LIMIT 1",
                    Finger_userinfo_relation.class,
                    furId);
            if (null == relation) {
                returnData.setReturnData(errorcode_data, "用户手指信息不存在", null);
                return;
            }

            int result = fingerUserinfoRelationDao.deleteByKey(furId);
            if (result < 1) {
                returnData.setReturnData(errorcode_data, "用户手指信息删除失败", null);
                return;
            }

            List<Finger_userinfo_relation> list = getMySelfService().queryListT(
                    "SELECT * FROM finger_userinfo_relation t WHERE t.fu_id = ?",
                    Finger_userinfo_relation.class,
                    fuId);
            if (null == list || list.size() == 0) {
                Finger_userinfo_new fingerUserinfoNew = getFingerUserInfoNewByKey(fuId);
                if (null != fingerUserinfoNew) {
                    fingerUserinfoNew.setFingerprint_state(0);  //指纹完成度（0：未完成 1：已经完成）
                    fingerUserinfoNew.setFinger_veno_state(0);  //指静脉完成度(0:未完成 1：已经完成)
                    int fi = fingerUserinfoNewDao.updateByKey(fingerUserinfoNew);
                    if (fi < 1) {
                        //returnData.setReturnData(errorcode_systerm, "用户信息更新指纹静脉完成度失败", null);
                        //return;
                        throw new QzException("用户信息更新指纹静脉完成度失败");
                    }
                }

            }
            returnData.setReturnData(errorcode_success, "用户手指信息删除成功", null);
        } catch (Exception e) {
            log.error("用户手指信息删除异常-->", e);
            throw new QzException("用户手指信息删除异常");
        }
    }

    /**
     * 用户指纹静脉车牌绑定
     * @param req
     * @param returnData
     */
    public void bindCarCode(UserCarCodeBindReq req,ReturnDataNew returnData){
        try{
            long fuId = req.getFu_id();
            Finger_userinfo_new fingerUserinfoNew = getFingerUserInfoNewByKey(fuId);
            if(null == fingerUserinfoNew){
                returnData.setReturnData(errorcode_data, "用户信息不存在", null);
                return;
            }

            Finger_userinfo_carcode car = getMySelfService().queryUniqueT(
                    "SELECT * FROM finger_userinfo_carcode t where t.car_code = ?",
                    Finger_userinfo_carcode.class, req.getCar_code());
            if(null != car){
               returnData.setReturnData(errorcode_data,"车牌已经被绑定,请更换车牌",null);
               return;
            }

            Finger_userinfo_carcode carcode = new Finger_userinfo_carcode();
            carcode.setFu_id(fuId);
            carcode.setSfz_number(fingerUserinfoNew.getSfz_number());
            carcode.setCar_code(req.getCar_code());
            carcode.setIs_run(0);   //是否启用(0:启用 1：关闭)
            carcode.setIsi_del(0);   //是否删除(0:正常 1：逻辑删除)
            Date date = new Date();
            carcode.setCtime(date);
            carcode.setUtime(date);
            carcode.setNote("绑定车牌");
            carcode.setFu_nd(fingerUserinfoNew.getFu_nd());

            int fucId = fingerUserinfoCarcodeDao.insert(carcode);
            if(fucId < 1){
                returnData.setReturnData(errorcode_systerm,"用户指纹静脉车牌绑定失败",null);
                return;
            }
            carcode.setFuc_id(fucId);

            returnData.setReturnData(errorcode_success,"用户指纹静脉车牌绑定成功",carcode);

        }catch (Exception e){
            log.error("用户指纹静脉车牌绑定异常",e);
        }
    }

    /**
     * 用户指纹静脉车牌解绑
     * @param req
     * @param returnData
     */
    public void unBindCarCode(UserCarCodeUnBindReq req,ReturnDataNew returnData){
        try{
            long fucId = req.getFuc_id();
            Finger_userinfo_carcode carcode = getMySelfService().queryUniqueT(
                    "SELECT * FROM finger_userinfo_carcode t WHERE t.fuc_id = ? LIMIT 1",
                    Finger_userinfo_carcode.class,
                    fucId);
            if(null == carcode){
                returnData.setReturnData(errorcode_param,"用户指纹静脉车牌信息不存在",null);
                return;
            }
            int re = fingerUserinfoCarcodeDao.deleteByKey(fucId);
            if(re < 1){
                returnData.setReturnData(errorcode_param,"用户指纹静脉车牌信息解绑失败",null);
                return;
            }
            returnData.setReturnData(errorcode_success,"用户指纹静脉车牌信息解绑成功",null);

        }catch (Exception e){
            log.error("用户指纹静脉车牌解绑异常",e);
            returnData.setReturnData(errorcode_systerm,"system is error",null);
        }
    }

    /**
     * 龙支付下单
     *
     * @param req
     * @return
     * @throws Exception
     */
    public User_pay LZFPlaceAnOrder(UserInfoReq req) throws Exception {
        int money = 1;   //支付金额
        String callbackurl = "";
        int system_type = 4;  //操作系统类型（IOS Android web） 1:android 2:IOS 3:web   4:PDA
        int pay_type = 5;   //支付类型 1:支付宝  2：微信  3：建行银联  4：钱包 5:龙支付
        long ui_id = 0L;
        String ui_nd = "";
        int version = 1;
        String carOrderId = "";
        String subject = "";  //商品名称

        User_info user = getUserInfoByPhone(req.getUi_tel());
        if (null != user) {
            ui_id = user.getUi_id();
            ui_nd = user.getUuid();
        }
        User_pay user_pay = new User_pay();
        //type  是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付
        //生成订单
        Date date = new Date();
        user_pay.setAct_type(1);//INT    行为类型（1：用户充值）
        user_pay.setCtime(date);
        user_pay.setEtime(date);
        user_pay.setUtime(date);
        user_pay.setMoney(money);
        user_pay.setOrder_id(returnUUID());
        user_pay.setReturn_url(callbackurl);
        user_pay.setState(0);//交易状态(0:未支付1：已支付2：支付失败)
        user_pay.setSystem_type(system_type);
        user_pay.setTransaction_id("");
        user_pay.setType(pay_type);//支付类型1:支付宝 2：微信 3：银联
        user_pay.setUi_id(ui_id);
        user_pay.setUi_nd(ui_nd);
        user_pay.setVersion_code(version);
        user_pay.setCar_order_id(carOrderId);
        if (RequestUtil.checkObjectBlank(subject)) {
            subject = "吾泊充值";
        }
        user_pay.setSubject(subject);
        user_pay.setIp(req.getSign_ip());
        user_pay.setTel(req.getUi_tel());
        //user_pay.setEscape_orderids("");

        int id = user_payDao.insert(user_pay);
        if (id < 1) {
            //插入失败
            throw new QzException("用户指纹静脉信息采集龙支付下订单失败");
        }
        user_pay.setId(id);
        return user_pay;
    }

    /**
     * 公共创建Finger_userinfo_new方法
     *
     * @param req
     * @param user_pay
     * @return
     */
    private Finger_userinfo_new makeFingerUserInfo(Finger_userinfo_new userInfo, UserInfoReq req, User_pay user_pay) throws Exception {
        if (null == userInfo) {
            userInfo = new Finger_userinfo_new();
        }

        Date dateS = new Date();
        userInfo.setFu_nd(returnUserRegisterUUID());//唯一标识符
        userInfo.setUi_tel(req.getUi_tel());//用户手机号码
        userInfo.setUi_id(user_pay.getUi_id());//平台用户ID
        userInfo.setUi_nd(user_pay.getUi_nd());//平台用户唯一标识符
        userInfo.setName(req.getName());//用户真实姓名
        userInfo.setSfz_number(req.getSfz_number());//用户真实身份证号码
        if (!RequestUtil.checkObjectBlank(req.getSfz_img_url())) {
            userInfo.setSfz_img_url(req.getSfz_img_url());//用户身份证图片
        }
        userInfo.setState(1);//有效状态(0：无	效	1：有效)
        userInfo.setIs_del(0);//是否删除 0:没有	1：删除
        userInfo.setCtime(dateS);//创建时间
        userInfo.setUtime(dateS);//修改时间
        userInfo.setMac(req.getMac());//采集数据提交设备MAC
        if(!RequestUtil.checkObjectBlank(req.getGather_id())){
            userInfo.setGather_id(req.getGather_id());//采集数据提交设备基本信息表主键ID
        }
        userInfo.setFinger_veno_state(0);  //指静脉完成度(0:未完成 1：已经完成)
        userInfo.setFingerprint_state(0);  //指纹完成度（0：未完成 1：已经完成）
        userInfo.setNote("初始化数据");//备注
        return userInfo;
    }

    /**
     * 初始化Finger_userinfo_bank
     *
     * @param bank
     * @param req
     * @return
     */
    private Finger_userinfo_bank makeFingerUserInfoBank(Finger_userinfo_bank bank, Finger_userinfo_new userinfoNew, UserInfoReq req, User_pay userPay) {
        if (null == bank) {
            bank = new Finger_userinfo_bank();
        }
        bank.setFu_id(userinfoNew.getFu_id());
        bank.setFu_nd(userinfoNew.getFu_nd());
        bank.setSfz_number(req.getSfz_number());
        bank.setBank_card_number(req.getBank_card_number());
        bank.setBank_type(0);
        bank.setOrderid(userPay.getOrder_id());
        bank.setVerify_sign(0);  //是否签约支付验证成功 0：未验证  1：成功 2：失败
        bank.setState(1);   //有效状态(  0：无 效  1：有效)
        bank.setIs_del(0);   //是否删除 0:没有 1：删除
        Date date = new Date();
        bank.setCtime(date);
        bank.setUtime(date);
        bank.setIs_sign(0);  //是否签约成功（0：没有签约 1：签约成功 2：签约失败 3：解除签约）
        bank.setSign_ip(req.getSign_ip());
        bank.setSigntime(null);
        bank.setDiscard_time(null);
        bank.setIs_default(1);
        bank.setName(userinfoNew.getName());
        bank.setNote("初始化数据");
        return bank;
    }

    /**
     * 检查该手机号码是否已经被其他人注册过了
     */
    public User_info getUserInfoByPhone(String telephone) throws QzException {
        try {
            return getMySelfService().queryUniqueT(
                    "SELECT * FROM user_info WHERE ui_tel=? LIMIT 1",
                    User_info.class,
                    telephone);
        } catch (Exception e) {
            log.error("异常-->", e);
            throw new QzException("检查该手机号码是否已经被其他人注册过了失败", e);
        }
    }

    /**
     * 根据主键获取用户信息
     *
     * @param fuId
     * @return
     * @throws Exception
     */
    public Finger_userinfo_new getFingerUserInfoNewByKey(long fuId) throws Exception {
        return getMySelfService().queryUniqueT(
                "SELECT * FROM finger_userinfo_new t WHERE t.fu_id = ? LIMIT 1",
                Finger_userinfo_new.class,
                fuId);
    }

    public Finger_userinfo_bank getFingerUserInfoBankByFuId(long fu_id) throws Exception{
        return getMySelfService().queryUniqueT(
                "SELECT * FROM finger_userinfo_bank t WHERE t.fu_id = ? LIMIT 1",
                Finger_userinfo_bank.class,
                fu_id);
    }

}
