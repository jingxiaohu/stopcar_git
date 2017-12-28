package com.park.pda.service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.*;
import com.park.dao.Finger_userinfoDao;
import com.park.dao.Finger_userinfo_relationDao;
import com.park.exception.QzException;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.mvc.service.BaseBiz;
import com.park.pda.action.v1.fingerprintcollection.param.*;
import com.park.service.ETCHelper;
import com.park.service.LZFHelper;
import com.park.service.etc.etcapi.ETCResponse;
import com.park.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 车牌号作为主键处理
 * <p>
 * Created by zzy on 2017/7/20.
 */
@Service("fingerprintreqBiz")
public class FingerprintCollectionBiz extends BaseBiz {

    @Autowired
    private Finger_userinfoDao fingerUserinfoDao;

    @Autowired
    private LZFHelper lZFHelper;   //

    @Autowired
    private ETCHelper etcHelper;

    @Autowired
    private Finger_userinfo_relationDao fingerUserinfoRelationDao;
    /**
     * 短信超时时间  分钟
     */
    private int timeout_minutes = 30;
    /**
     * 已经支付一钱签约成功
     */
    private static String Resp_4001 = "4001";

    /**
     * @param req
     * @param returnDataNew
     */
    @Transactional(rollbackFor = QzException.class)
    public void saveFingerUserinfo(FingerprintCollectionReq req, ReturnDataNew returnDataNew) throws QzException {
        try {
            //验证短信
            if (!smsCodeVerify(req.getUi_tel(), req.getVerify_list(), req.getVclass(), req.getVerify_code(), returnDataNew)) {
                return;
            }

            User_pay user_pay = null;

            String sql = "select * from finger_userinfo t where  t.car_code = ? ";// +
            //"and t.verify_sign =1 and t.is_sign = 1 and t.is_del = 0";
            Finger_userinfo userInfo = getMySelfService().queryUniqueT(sql, Finger_userinfo.class, req.getCar_code());
            if (null != userInfo) {
                //判断是否签约
                if (1 == userInfo.getIs_sign()) {//已签约
                    //判断是否有修改手机号
                    if (req.getUi_tel().equals(userInfo.getUi_tel())) {//没有修改手机号
                        String name = req.getName();
                        String idNo = req.getSfz_number();
                        String bankNo = req.getBank_card_number();
                        //判断用户修改姓名、身份证、银行卡信息是否有修改
                        if (userInfo.getName().equalsIgnoreCase(name) &&
                                userInfo.getSfz_number().equalsIgnoreCase(idNo) &&
                                userInfo.getBank_card_number().equalsIgnoreCase(bankNo)) {
                            log.info("用户已经签约成功，无需再次签约");
                            JSONObject returnobj = new JSONObject();
                            returnobj.put("finger_userinfo", userInfo);
                            returnDataNew.setReturnData(errorcode_success, "用户已经签约成功，无需再次签约", returnobj);
                            returnDataNew.setErrorcode(Resp_4001);
                            return;
                            //有修改
                        } else {
                            //用户已经签约成功，再次采集指纹时，用户修改姓名、身份证、银行卡信息，这里需要先解约银行卡,再更新信息
                            log.info("用户已经签约成功，但是还没有录入指纹，再次采集指纹时，用户修改姓名、身份证、银行卡信息，这里需要先解约银行卡，并" +
                                    "重新验证一分钱和签约");
                            //解约
                            String serialNo = return16UUID();
                            ETCResponse etcResponse = etcHelper.toregister(
                                    serialNo,
                                    1,
                                    userInfo.getFu_nd(),
                                    userInfo.getName(),
                                    userInfo.getSfz_number(),
                                    userInfo.getBank_card_number());
                            if (null == etcResponse || !etcResponse.isSucceed()) {
                                log.info("解约失败响应描述：" + etcResponse.getRespMess());
                            }

                            //龙支付web下单
                            user_pay = LZFplaceAnOrder(req);

                            userInfo = makeFingerUserInfo(userInfo, req, user_pay);
                            int uid = fingerUserinfoDao.updateByKey(userInfo);
                            if (uid < 1) {
                                //插入失败
                                throw new QzException("修改信息失败");
                            }
                        }
                    } else {
                        log.info(userInfo.getCar_code() + " 车牌号已被绑定");
                        returnDataNew.setReturnData(errorcode_data, "亲，您的车牌号已被绑定，请更换其他车牌号！", null);
                        return;
                    }
                } else {//未签约，更新信息
                    log.info("验证一分钱并签约");
                    //龙支付web下单
                    user_pay = LZFplaceAnOrder(req);

                    userInfo = makeFingerUserInfo(userInfo, req, user_pay);
                    int uid = fingerUserinfoDao.updateByKey(userInfo);
                    if (uid < 1) {
                        //插入失败
                        throw new QzException("修改信息失败");
                    }
                }
            } else {
                log.info("验证一分钱并且签约");
                //龙支付web下单
                user_pay = LZFplaceAnOrder(req);

                userInfo = makeFingerUserInfo(userInfo, req, user_pay);
                int uid = fingerUserinfoDao.insert(userInfo);
                if (uid < 1) {
                    //插入失败
                    throw new QzException("新增信息失败");
                }
                userInfo.setFu_id(uid);
            }
            JSONObject returnobj = new JSONObject();
            returnobj.put("lzf_info", user_pay);
            returnobj.put("finger_userinfo", userInfo);
            returnDataNew.setReturnData(errorcode_success, "成功", returnobj);
        } catch (Exception e) {
            log.error("用户新增或者下单失败", e);
            throw new QzException("用户新增或者下单失败");
        }

    }


    /**
     * 公共创建finger_userinfo方法
     *
     * @param req
     * @param user_pay
     * @return
     */
    private Finger_userinfo makeFingerUserInfo(Finger_userinfo userInfo, FingerprintCollectionReq req, User_pay user_pay) throws Exception {
        if (null == userInfo) {
            userInfo = new Finger_userinfo();
        }

        Date dateS = new Date();
        userInfo.setFu_nd(returnUserRegisterUUID());//唯一标识符
        userInfo.setUi_tel(req.getUi_tel());//用户手机号码
        userInfo.setCar_code(req.getCar_code());
        userInfo.setUi_id(user_pay.getUi_id());//平台用户ID
        userInfo.setUi_nd(user_pay.getUi_nd());//平台用户唯一标识符
        userInfo.setName(req.getName());//用户真实姓名
        userInfo.setSfz_number(req.getSfz_number());//用户真实身份证号码
        if (!RequestUtil.checkObjectBlank(req.getSfz_img_url())) {
            userInfo.setSfz_img_url(req.getSfz_img_url());//用户身份证图片
        }
        userInfo.setBank_card_number(req.getBank_card_number());//用户银行卡卡号
        userInfo.setBank_type(0);//银行类型（0：建行银行）
        userInfo.setOrderid(user_pay.getOrder_id());//签约验证支付订单号(user_pay表订单号)
        userInfo.setVerify_sign(0);//是否签约支付验证成功0：未验证 1：成功	2：失败
        userInfo.setState(1);//有效状态(0：无	效	1：有效)
        userInfo.setIs_del(0);//是否删除 0:没有	1：删除
        userInfo.setCtime(dateS);//创建时间
        userInfo.setUtime(dateS);//修改时间
        userInfo.setMac(req.getMac());//采集数据提交设备MAC
        userInfo.setGather_id(req.getGather_id());//采集数据提交设备基本信息表主键ID
        userInfo.setIs_sign(0);//是否签约成功（0：没有签约 1：签约成功	2：签约失败	3：解除签约）
        userInfo.setSign_ip(req.getSign_ip());//签约地IP
        userInfo.setIs_default(0);//是否是默认ETC支付卡 0:不是	1：是
        userInfo.setNote("初始化数据");//备注
        userInfo.setFingerprint("");  // by zzy 2017-08-01 修改指纹为空
        return userInfo;
    }

    /**
     * 支付一分钱验证
     *
     * @param checkSign
     * @param returnDataNew
     */
    public void fingerCheckSign(FingerPrintCheckSignReq checkSign, ReturnDataNew returnDataNew) {
        try {
            //这里需要 调用ETC银企直连接口 进行查询处理
            JSONObject returnobj = new JSONObject();
            returnobj.put("state", 0);//0：未签约  1：签约成功
            //returnobj.put("errormsg","未知");

            Long fu_id = checkSign.getFu_id();
            Finger_userinfo userinfo = fingerUserinfoDao.selectByKey(fu_id);
            if (null == userinfo) {
                returnDataNew.setReturnData(errorcode_data, "指纹系统--用户信息不存在", null);
                return;
            }
            if (!checkSign.getFu_nd().equals(userinfo.getFu_nd())) {
                returnDataNew.setReturnData(errorcode_data, "亲，不能绑定他人的信息", null);
                return;
            }

            String orderId = userinfo.getOrderid();
            String bankNo = userinfo.getBank_card_number();
            boolean flag;//"接口返回查询成功"
            flag = lZFHelper.accountCheck(orderId, bankNo);
            //flag = true;
            if (flag) {
                userinfo.setVerify_sign(1);
                String serialNo = return16UUID();
                if (userinfo.getIs_sign() != 1) {
                    ETCResponse eTCResponse = etcHelper.toregister(
                            serialNo
                            , 0
                            , userinfo.getFu_nd()
                            , userinfo.getName()
                            , userinfo.getSfz_number(),
                            userinfo.getBank_card_number());
                    if (eTCResponse == null || !eTCResponse.isSucceed()) {
                        log.error("ETC签约失败：" + eTCResponse);
                        //签约失败
                        userinfo.setIs_sign(2);
                        userinfo.setNote("ETC签约失败");
                    } else {
                        userinfo.setIs_sign(1);
                        userinfo.setSigntime(new Date());
                        userinfo.setNote("ETC签约成功");
                    }
                    int count = fingerUserinfoDao.updateByKey(userinfo);
                    if (count >= 1 && eTCResponse.isSucceed()) {
                        //returnobj.put("state", 1);//0：未签约  1：签约成功
                        //returnobj.put("errormsg","签约成功");
                        returnDataNew.setReturnData(errorcode_success, "查询成功", null);
                    } else {
                        returnDataNew.setReturnData(errorcode_data, "查询失败", null);
                    }
                }

            } else {
                log.info("查询失败--龙支付结果未知或失败");
                returnDataNew.setReturnData(errorcode_data, "查询失败--龙支付结果未知或失败", returnobj);
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }


    /**
     * 更新用户信息、主要更新用户指纹特征信息
     *
     * @param req
     * @param returnDataNew
     */
    @Transactional(rollbackFor = QzException.class)
    public void updateFingerUserInfo(FingerUserInfoUpReq req, ReturnDataNew returnDataNew) throws QzException {
        try {

            returnDataNew.setReturnData(errorcode_systerm, "修改信息失败", null);
            long fuId = req.getFu_id();
            Finger_userinfo userinfo = fingerUserinfoDao.selectByKey(fuId);
            if (null == userinfo) {
                returnDataNew.setReturnData(errorcode_data, "指纹系统--用户信息不存在", null);
                return;
            }

            //已经签约成功的信息不能修改用户姓名、身份证、银行卡类型、银行卡号
            if (userinfo.getIs_sign() != 1) {
                if (!RequestUtil.checkObjectBlank(req.getName())) {
                    userinfo.setCar_code(req.getName());
                }
                if (!RequestUtil.checkObjectBlank(req.getSfz_number())) {
                    userinfo.setSfz_number(req.getSfz_number());
                }
                if (!RequestUtil.checkObjectBlank(req.getSfz_img_url())) {
                    userinfo.setSfz_img_url(req.getSfz_img_url());
                }
                if (!RequestUtil.checkObjectBlank(req.getBank_type())) {
                    userinfo.setBank_type(req.getBank_type());
                }
                if (!RequestUtil.checkObjectBlank(req.getBank_card_number())) {
                    userinfo.setBank_card_number(req.getBank_card_number());
                }
            }

            if (!RequestUtil.checkObjectBlank(req.getCar_code())) {
                userinfo.setCar_code(req.getCar_code());
            }

//            if (!RequestUtil.checkObjectBlank(req.getFingerprint())) {
//                userinfo.setFingerprint(req.getFingerprint());
//            }
//            if (!RequestUtil.checkObjectBlank(req.getFinger_veno())) {
//                userinfo.setFinger_veno(req.getFinger_veno());
//            }
//            if (!RequestUtil.checkObjectBlank(req.getFingerprint_hash())) {
//                userinfo.setFingerprint_hash(req.getFingerprint_hash());
//            }
//            if (!RequestUtil.checkObjectBlank(req.getFinger_veno_hash())) {
//                userinfo.setFinger_veno_hash(req.getFinger_veno_hash());
//            }
            if (!RequestUtil.checkObjectBlank(req.getMac())) {
                userinfo.setMac(req.getMac());
            }
            if (!RequestUtil.checkObjectBlank(req.getGather_id())) {
                userinfo.setGather_id(req.getGather_id());
            }
            if (!RequestUtil.checkObjectBlank(req.getSign_ip())) {
                userinfo.setSign_ip(req.getSign_ip());
            }

            userinfo.setUtime(new Date());
            userinfo.setNote("修改用户指纹或者静脉信息");
            int count = fingerUserinfoDao.updateByKey(userinfo);
            if (count >= 1) {
                if (!RequestUtil.checkObjectBlank(req.getFingerprint_vein_str())) {
                    //这里判断用户已经采集过指纹、静脉信息，如果有删除
                    String sql = "select * from finger_userinfo_relation t where t.is_del = 0 and t.fu_id = ?";
                    List<Finger_userinfo_relation> relations = getMySelfService().queryListT(sql, Finger_userinfo_relation.class, fuId);
                    if (null != relations || relations.size() == 0) {
                        log.info("用户已经采集过指纹、静脉信息，删除信息");
                        for (Finger_userinfo_relation relation : relations) {
                            fingerUserinfoRelationDao.deleteByKey(relation.getFur_id());
                        }
                    }

                    List<UserFingerEvnoInfo> list = parseFingerprintVeinStr(req.getFingerprint_vein_str());
                    for (UserFingerEvnoInfo evnoInfo : list) {
                        Finger_userinfo_relation userinfoRelation = new Finger_userinfo_relation();
                        userinfoRelation.setFu_id(fuId);
                        userinfoRelation.setFingerprint(evnoInfo.getFingerprint());
                        userinfoRelation.setFinger_veno(evnoInfo.getVein());
                        userinfoRelation.setFingerprint_hash(evnoInfo.getFingerprint_hash());
                        userinfoRelation.setFinger_veno_hash(evnoInfo.getVein_hash());
                        Date date = new Date();
                        userinfoRelation.setCtime(date);
                        userinfoRelation.setUtime(date);
                        userinfoRelation.setNote("用户指纹静脉信息");
                        fingerUserinfoRelationDao.insert(userinfoRelation);
                    }
                }
                returnDataNew.setReturnData(errorcode_success, "修改信息成功", null);
            }

        } catch (Exception e) {
            log.error("更新用户指纹特征信息失败", e);
            throw new QzException("更新用户指纹特征信息失败事务回滚", e);
        }

    }


    /**
     * 用户解除签约操作
     *
     * @param removeSignedReq
     * @param returnData
     */
    public void fingerRemoveSigned(FingerRemoveSignedReq removeSignedReq, ReturnDataNew returnData) throws QzException {
        try {
            returnData.setReturnData(errorcode_systerm, "解约失败", null);

            Finger_userinfo userinfo = fingerUserinfoDao.selectByKey(removeSignedReq.getFu_id());
            if (null == userinfo) {
                returnData.setReturnData(errorcode_data, "指纹系统--用户信息不存在", null);
                return;
            }
            if (userinfo.getIs_sign() != 1) {
                returnData.setReturnData(errorcode_data, "指纹系统--用户没有签约，无需解约", null);
                return;
            }
            String serialNo = return16UUID();
            ETCResponse etcResponse = etcHelper.toregister(
                    serialNo,
                    1,
                    userinfo.getFu_nd(),
                    userinfo.getName(),
                    userinfo.getSfz_number(),
                    userinfo.getBank_card_number());
            if (null == etcResponse || !etcResponse.isSucceed()) {
                log.info("解约失败响应描述：" + etcResponse.getRespMess());
                return;
            }

            userinfo.setIs_sign(3);   //解约
            userinfo.setDiscard_time(new Date());
            userinfo.setNote("解约");
            int count = fingerUserinfoDao.updateByKey(userinfo);
            if (count >= 1) {
                returnData.setReturnData(errorcode_success, "解约成功", null);
            }

        } catch (Exception e) {
            log.error("用户解除签约操作异常", e);
        }
    }

    /**
     * 查询信息
     *  20170825 by zyy 查询用户指纹/指静脉信息暂时删除签约条件is_sign=1、verify_sign=1
     * @return
     */
    @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
    public void queryFingerUserinfo(FingerUserInfoQueryReq queryReq, ReturnDataNew returnData) {
        try {
            //验证短信
            if (!smsCodeVerify(queryReq.getUi_tel(), queryReq.getVerify_list(), queryReq.getVclass(), queryReq.getVerify_code(), returnData)) {
                return;
            }

            String ui_tel = queryReq.getUi_tel();
            String sql = "SELECT * FROM finger_userinfo " +
                    " WHERE ui_tel = ?  AND state = 1 AND is_del = 0 ";

            //返回结果数据的list
            List<Map> resultList = new ArrayList<>();
            //根据电话号码获取指纹系统用户信息列表
            List<Finger_userinfo> fingerUserInfoList =
                    getMySelfService().queryListT(sql, Finger_userinfo.class, ui_tel);
            if(fingerUserInfoList == null || fingerUserInfoList.size() == 0){
                returnData.setReturnData(errorcode_data, "用户信息不存在", null);
                return;
            }

            //关联信息表中用户指纹/指静脉信息
            List<Finger_userinfo_relation> relationList = new ArrayList<>();
            JSONObject fingerUserInfoMap = null;
            List<String> fingerprintList = new ArrayList<>();
            List<String> finger_venoList = new ArrayList<>();

            //遍历用户信息，根据finger_userinfo表的fu_id查询关联的用户指纹/指静脉信息
            //再遍历用户指纹/指静脉信息，把信息添加到相应指纹/指静脉信息列表
            for (Finger_userinfo finger_userinfo : fingerUserInfoList) {
                String infoSql = "SELECT t.* from finger_userinfo_relation t " +
                        " LEFT JOIN finger_userinfo t1 ON t1.fu_id = t.fu_id" +
                        " WHERE t.fu_id=? ";
                relationList = getMySelfService().queryListT(infoSql,Finger_userinfo_relation.class,finger_userinfo.getFu_id());
                if(relationList != null && relationList.size() > 0){
                    for (Finger_userinfo_relation relation : relationList) {
                        if (relation.getFingerprint() != null && relation.getFingerprint() != "") {
                            fingerprintList.add(relation.getFingerprint());
                        }
                        if (relation.getFinger_veno() != null && relation.getFinger_veno() != "") {
                            finger_venoList.add(relation.getFinger_veno());
                        }
                    }
                    fingerUserInfoMap = new JSONObject();
                    fingerUserInfoMap.put("finger_userinfo",finger_userinfo);
                    fingerUserInfoMap.put("finger_printList",fingerprintList);
                    fingerUserInfoMap.put("finger_venoList",finger_venoList);
                    resultList.add(fingerUserInfoMap);
                }
            }

            //返回数据
            returnData.setReturnData(errorcode_success, "查询成功", resultList);
        } catch (Exception e) {
            log.error("查询失败", e);
            returnData.setReturnData(errorcode_systerm, "查询失败", null);
        }

    }

    /**
     * 短信验证  成功：true  失败:false
     *
     * @param returnDataNew
     * @return
     */
    public boolean smsCodeVerify(String tel, String verifyList, String vclass, String verifyCode, ReturnDataNew returnDataNew) {
        try {
            //手机号正确性验证
            if(!BaseV1Controller.isMobileNO(tel)){
                returnDataNew.setReturnData(errorcode_param, "亲，请输入正确的手机号", null);
                return false;
            }

            String sql = "SELECT v_code,v_time FROM sms_validate WHERE v_tel = ? AND v_list = ? AND v_class = ? AND v_code=? LIMIT 1";
            Sms_validate bsv = getMySelfService()
                    .queryUniqueT(sql, Sms_validate.class, tel, verifyList, vclass, verifyCode);
            if (bsv == null) {
                returnDataNew.setReturnData(errorcode_data, "验证码错误", "");
                return false;
            }
            //检查时间是否过期 30分钟
            long time = bsv.getV_time();
            if (System.currentTimeMillis() - time > timeout_minutes * 60 * 1000) {
                returnDataNew.setReturnData(errorcode_data, "验证码过期", "");
                return false;
            }
        } catch (Exception e) {
            log.error("异常", e);
            return false;
        }
        return true;
    }

    /**
     * 检查该手机号码是否已经被其他人注册过了
     */
    public User_info getUserInfoByPhone(String telephone) throws QzException {
        try {
            String sql = "SELECT * FROM user_info WHERE ui_tel=? LIMIT 1";
            return getMySelfService().queryUniqueT(sql, User_info.class, telephone);
        } catch (Exception e) {
            log.error("异常", e);
            throw new QzException("检查该手机号码是否已经被其他人注册过了失败", e);
        }
    }

    /**
     * 龙支付下单
     *
     * @param req
     * @return
     * @throws Exception
     */
    public User_pay LZFplaceAnOrder(FingerprintCollectionReq req) throws Exception {
        int money = 1;   //支付金额
        String callbackurl = "";
        int system_type = 4;  //操作系统类型（IOS Android web） 1:android 2:IOS 3:web   4:PDA
        int pay_type = 5;   //支付类型 1:支付宝  2：微信  3：建行银联  4：钱包 5:龙支付
        long ui_id = 0L;
        String ui_nd = "";
        int version = 1;
        String orderid = "";
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
        user_pay.setCar_order_id(orderid);
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
            throw new QzException("用户指纹采集龙支付下订单失败");
        }
        user_pay.setId(id);
        return user_pay;
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
        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
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
