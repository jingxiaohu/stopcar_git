package com.park.bean;

import java.io.*;
import java.util.*;

//user_info
@SuppressWarnings({"serial"})
public class User_info implements Cloneable , Serializable{

    //public static String[] carrays ={"ui_id","uuid","ui_tel","ui_password","ui_nickname","ui_avatar","ui_sex","driving_licence","ui_name","ui_address","bind_tel","ui_vc","ui_rmb","coupon_num","ui_state","ui_autopay","pay_source","ui_level","ui_score","ui_mood","ui_intro","ui_age","ui_token","ui_email","ui_high","ui_degree","ctime","utime","ui_flag","ui_online","ui_integrity","note","lock_money","lock_expect_money","lock_rent_money","eu_id","eu_nd","etc_autopay","bank_no","ui_logo","ui_face_imgs","ui_face_feature","ui_finger_imgs","ui_finger_feature","ui_face_state","ui_finger_state"};

    public long ui_id;//bigint(20)    
    public String uuid="";//varchar(65)    用户uuid
    public String ui_tel="";//varchar(11)    手机号码/用户账号
    public String ui_password="";//varchar(80)    用户密码(MD5散列)
    public String ui_nickname="";//varchar(30)    用户昵称
    public String ui_avatar="";//varchar(200)    用户头像
    public String ui_sex="";//varchar(30)    用户性别:male男women女no未知
    public String driving_licence="";//varchar(255)    驾驶证号码
    public String ui_name="";//varchar(60)    姓名
    public String ui_address="";//varchar(100)    住址
    public String bind_tel="";//varchar(20)    绑定手机号码
    public long ui_vc;//bigint(20)    爱泊币（100爱泊=1元）
    public long ui_rmb;//bigint(20)    爱泊币（100爱泊=1元）
    public long coupon_num;//bigint(20)    优费卷数量
    public int ui_state;//int(11)    用户状态:0：正常1：禁用
    public int ui_autopay;//int(11)    是否自动支付：0：不是1：是
    public int pay_source;//int(11)    默认支付类型1:支付宝2：微信3：银联4：钱包
    public int ui_level;//int(11)    用户等级
    public long ui_score;//bigint(20)    用户积分
    public String ui_mood="";//varchar(100)    用户心情
    public String ui_intro="";//varchar(255)    个人简介
    public int ui_age;//int(11)    用户年龄
    public String ui_token="";//varchar(60)    用户授权码
    public String ui_email="";//varchar(60)    用户邮箱
    public int ui_high;//int(11)    用户身高
    public String ui_degree="";//varchar(60)    
    public java.util.Date ctime=new java.util.Date();//datetime    
    public java.util.Date utime=new java.util.Date();//datetime    
    public int ui_flag;//int(11)    注册来源0:android1:ios2:web
    public long ui_online;//bigint(20)    l累计在线时长(分钟)
    public int ui_integrity;//int(11)    用户诚信度按100来计算
    public String note="";//varchar(100)    备注
    public int lock_money;//int(11)    锁定金额
    public int lock_expect_money;//int(11)    锁定预约金额
    public int lock_rent_money;//int(11)    锁定租赁金额
    public long eu_id;//bigint(20)    ETC基本信息表主键ID
    public String eu_nd="";//varchar(70)    ETC唯一标识符
    public int etc_autopay;//int(11)    是否开启ETC支付0:没有开启1：开启
    public String bank_no="";//varchar(60)    选择的默认银行卡卡号
    public int ui_logo;//int(11)    吾泊加V标志(0:没有1：显示W)
    public String ui_face_imgs="";//varchar(255)    用户人脸图片地址集合(逗号分割)
    public String ui_face_feature="";//varchar(255)    用户人脸图片特征数据集合
    public String ui_finger_imgs="";//varchar(255)    用户指纹图片地址集合(逗号分割)
    public String ui_finger_feature="";//varchar(255)    用户指纹特征数据集合
    public int ui_face_state;//int(11)    人脸识别状态（0:未采集到人脸1：已经采集到人脸且关闭人脸支付2：开启人脸支付）
    public int ui_finger_state;//int(11)    指纹识别状态（0:未采集到指纹1：已经采集到指纹且关闭指纹支付2：开启指纹支付）



    public long getUi_id(){
        return ui_id;
    }

    public void setUi_id(long value){
        this.ui_id= value;
    }

    public String getUuid(){
        return uuid;
    }

    public void setUuid(String value){
    	if(value == null){
           value = "";
        }
        this.uuid= value;
    }

    public String getUi_tel(){
        return ui_tel;
    }

    public void setUi_tel(String value){
    	if(value == null){
           value = "";
        }
        this.ui_tel= value;
    }

    public String getUi_password(){
        return ui_password;
    }

    public void setUi_password(String value){
    	if(value == null){
           value = "";
        }
        this.ui_password= value;
    }

    public String getUi_nickname(){
        return ui_nickname;
    }

    public void setUi_nickname(String value){
    	if(value == null){
           value = "";
        }
        this.ui_nickname= value;
    }

    public String getUi_avatar(){
        return ui_avatar;
    }

    public void setUi_avatar(String value){
    	if(value == null){
           value = "";
        }
        this.ui_avatar= value;
    }

    public String getUi_sex(){
        return ui_sex;
    }

    public void setUi_sex(String value){
    	if(value == null){
           value = "";
        }
        this.ui_sex= value;
    }

    public String getDriving_licence(){
        return driving_licence;
    }

    public void setDriving_licence(String value){
    	if(value == null){
           value = "";
        }
        this.driving_licence= value;
    }

    public String getUi_name(){
        return ui_name;
    }

    public void setUi_name(String value){
    	if(value == null){
           value = "";
        }
        this.ui_name= value;
    }

    public String getUi_address(){
        return ui_address;
    }

    public void setUi_address(String value){
    	if(value == null){
           value = "";
        }
        this.ui_address= value;
    }

    public String getBind_tel(){
        return bind_tel;
    }

    public void setBind_tel(String value){
    	if(value == null){
           value = "";
        }
        this.bind_tel= value;
    }

    public long getUi_vc(){
        return ui_vc;
    }

    public void setUi_vc(long value){
        this.ui_vc= value;
    }

    public long getUi_rmb(){
        return ui_rmb;
    }

    public void setUi_rmb(long value){
        this.ui_rmb= value;
    }

    public long getCoupon_num(){
        return coupon_num;
    }

    public void setCoupon_num(long value){
        this.coupon_num= value;
    }

    public int getUi_state(){
        return ui_state;
    }

    public void setUi_state(int value){
        this.ui_state= value;
    }

    public int getUi_autopay(){
        return ui_autopay;
    }

    public void setUi_autopay(int value){
        this.ui_autopay= value;
    }

    public int getPay_source(){
        return pay_source;
    }

    public void setPay_source(int value){
        this.pay_source= value;
    }

    public int getUi_level(){
        return ui_level;
    }

    public void setUi_level(int value){
        this.ui_level= value;
    }

    public long getUi_score(){
        return ui_score;
    }

    public void setUi_score(long value){
        this.ui_score= value;
    }

    public String getUi_mood(){
        return ui_mood;
    }

    public void setUi_mood(String value){
    	if(value == null){
           value = "";
        }
        this.ui_mood= value;
    }

    public String getUi_intro(){
        return ui_intro;
    }

    public void setUi_intro(String value){
    	if(value == null){
           value = "";
        }
        this.ui_intro= value;
    }

    public int getUi_age(){
        return ui_age;
    }

    public void setUi_age(int value){
        this.ui_age= value;
    }

    public String getUi_token(){
        return ui_token;
    }

    public void setUi_token(String value){
    	if(value == null){
           value = "";
        }
        this.ui_token= value;
    }

    public String getUi_email(){
        return ui_email;
    }

    public void setUi_email(String value){
    	if(value == null){
           value = "";
        }
        this.ui_email= value;
    }

    public int getUi_high(){
        return ui_high;
    }

    public void setUi_high(int value){
        this.ui_high= value;
    }

    public String getUi_degree(){
        return ui_degree;
    }

    public void setUi_degree(String value){
    	if(value == null){
           value = "";
        }
        this.ui_degree= value;
    }

    public java.util.Date getCtime(){
        return ctime;
    }

    public void setCtime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.ctime= value;
    }

    public java.util.Date getUtime(){
        return utime;
    }

    public void setUtime(java.util.Date value){
    	if(value == null){
           value = new java.util.Date();
        }
        this.utime= value;
    }

    public int getUi_flag(){
        return ui_flag;
    }

    public void setUi_flag(int value){
        this.ui_flag= value;
    }

    public long getUi_online(){
        return ui_online;
    }

    public void setUi_online(long value){
        this.ui_online= value;
    }

    public int getUi_integrity(){
        return ui_integrity;
    }

    public void setUi_integrity(int value){
        this.ui_integrity= value;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String value){
    	if(value == null){
           value = "";
        }
        this.note= value;
    }

    public int getLock_money(){
        return lock_money;
    }

    public void setLock_money(int value){
        this.lock_money= value;
    }

    public int getLock_expect_money(){
        return lock_expect_money;
    }

    public void setLock_expect_money(int value){
        this.lock_expect_money= value;
    }

    public int getLock_rent_money(){
        return lock_rent_money;
    }

    public void setLock_rent_money(int value){
        this.lock_rent_money= value;
    }

    public long getEu_id(){
        return eu_id;
    }

    public void setEu_id(long value){
        this.eu_id= value;
    }

    public String getEu_nd(){
        return eu_nd;
    }

    public void setEu_nd(String value){
    	if(value == null){
           value = "";
        }
        this.eu_nd= value;
    }

    public int getEtc_autopay(){
        return etc_autopay;
    }

    public void setEtc_autopay(int value){
        this.etc_autopay= value;
    }

    public String getBank_no(){
        return bank_no;
    }

    public void setBank_no(String value){
    	if(value == null){
           value = "";
        }
        this.bank_no= value;
    }

    public int getUi_logo(){
        return ui_logo;
    }

    public void setUi_logo(int value){
        this.ui_logo= value;
    }

    public String getUi_face_imgs(){
        return ui_face_imgs;
    }

    public void setUi_face_imgs(String value){
    	if(value == null){
           value = "";
        }
        this.ui_face_imgs= value;
    }

    public String getUi_face_feature(){
        return ui_face_feature;
    }

    public void setUi_face_feature(String value){
    	if(value == null){
           value = "";
        }
        this.ui_face_feature= value;
    }

    public String getUi_finger_imgs(){
        return ui_finger_imgs;
    }

    public void setUi_finger_imgs(String value){
    	if(value == null){
           value = "";
        }
        this.ui_finger_imgs= value;
    }

    public String getUi_finger_feature(){
        return ui_finger_feature;
    }

    public void setUi_finger_feature(String value){
    	if(value == null){
           value = "";
        }
        this.ui_finger_feature= value;
    }

    public int getUi_face_state(){
        return ui_face_state;
    }

    public void setUi_face_state(int value){
        this.ui_face_state= value;
    }

    public int getUi_finger_state(){
        return ui_finger_state;
    }

    public void setUi_finger_state(int value){
        this.ui_finger_state= value;
    }



    public static User_info newUser_info(long ui_id, String uuid, String ui_tel, String ui_password, String ui_nickname, String ui_avatar, String ui_sex, String driving_licence, String ui_name, String ui_address, String bind_tel, long ui_vc, long ui_rmb, long coupon_num, int ui_state, int ui_autopay, int pay_source, int ui_level, long ui_score, String ui_mood, String ui_intro, int ui_age, String ui_token, String ui_email, int ui_high, String ui_degree, java.util.Date ctime, java.util.Date utime, int ui_flag, long ui_online, int ui_integrity, String note, int lock_money, int lock_expect_money, int lock_rent_money, long eu_id, String eu_nd, int etc_autopay, String bank_no, int ui_logo, String ui_face_imgs, String ui_face_feature, String ui_finger_imgs, String ui_finger_feature, int ui_face_state, int ui_finger_state) {
        User_info ret = new User_info();
        ret.setUi_id(ui_id);
        ret.setUuid(uuid);
        ret.setUi_tel(ui_tel);
        ret.setUi_password(ui_password);
        ret.setUi_nickname(ui_nickname);
        ret.setUi_avatar(ui_avatar);
        ret.setUi_sex(ui_sex);
        ret.setDriving_licence(driving_licence);
        ret.setUi_name(ui_name);
        ret.setUi_address(ui_address);
        ret.setBind_tel(bind_tel);
        ret.setUi_vc(ui_vc);
        ret.setUi_rmb(ui_rmb);
        ret.setCoupon_num(coupon_num);
        ret.setUi_state(ui_state);
        ret.setUi_autopay(ui_autopay);
        ret.setPay_source(pay_source);
        ret.setUi_level(ui_level);
        ret.setUi_score(ui_score);
        ret.setUi_mood(ui_mood);
        ret.setUi_intro(ui_intro);
        ret.setUi_age(ui_age);
        ret.setUi_token(ui_token);
        ret.setUi_email(ui_email);
        ret.setUi_high(ui_high);
        ret.setUi_degree(ui_degree);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setUi_flag(ui_flag);
        ret.setUi_online(ui_online);
        ret.setUi_integrity(ui_integrity);
        ret.setNote(note);
        ret.setLock_money(lock_money);
        ret.setLock_expect_money(lock_expect_money);
        ret.setLock_rent_money(lock_rent_money);
        ret.setEu_id(eu_id);
        ret.setEu_nd(eu_nd);
        ret.setEtc_autopay(etc_autopay);
        ret.setBank_no(bank_no);
        ret.setUi_logo(ui_logo);
        ret.setUi_face_imgs(ui_face_imgs);
        ret.setUi_face_feature(ui_face_feature);
        ret.setUi_finger_imgs(ui_finger_imgs);
        ret.setUi_finger_feature(ui_finger_feature);
        ret.setUi_face_state(ui_face_state);
        ret.setUi_finger_state(ui_finger_state);
        return ret;    
    }

    public void assignment(User_info user_info) {
        long ui_id = user_info.getUi_id();
        String uuid = user_info.getUuid();
        String ui_tel = user_info.getUi_tel();
        String ui_password = user_info.getUi_password();
        String ui_nickname = user_info.getUi_nickname();
        String ui_avatar = user_info.getUi_avatar();
        String ui_sex = user_info.getUi_sex();
        String driving_licence = user_info.getDriving_licence();
        String ui_name = user_info.getUi_name();
        String ui_address = user_info.getUi_address();
        String bind_tel = user_info.getBind_tel();
        long ui_vc = user_info.getUi_vc();
        long ui_rmb = user_info.getUi_rmb();
        long coupon_num = user_info.getCoupon_num();
        int ui_state = user_info.getUi_state();
        int ui_autopay = user_info.getUi_autopay();
        int pay_source = user_info.getPay_source();
        int ui_level = user_info.getUi_level();
        long ui_score = user_info.getUi_score();
        String ui_mood = user_info.getUi_mood();
        String ui_intro = user_info.getUi_intro();
        int ui_age = user_info.getUi_age();
        String ui_token = user_info.getUi_token();
        String ui_email = user_info.getUi_email();
        int ui_high = user_info.getUi_high();
        String ui_degree = user_info.getUi_degree();
        java.util.Date ctime = user_info.getCtime();
        java.util.Date utime = user_info.getUtime();
        int ui_flag = user_info.getUi_flag();
        long ui_online = user_info.getUi_online();
        int ui_integrity = user_info.getUi_integrity();
        String note = user_info.getNote();
        int lock_money = user_info.getLock_money();
        int lock_expect_money = user_info.getLock_expect_money();
        int lock_rent_money = user_info.getLock_rent_money();
        long eu_id = user_info.getEu_id();
        String eu_nd = user_info.getEu_nd();
        int etc_autopay = user_info.getEtc_autopay();
        String bank_no = user_info.getBank_no();
        int ui_logo = user_info.getUi_logo();
        String ui_face_imgs = user_info.getUi_face_imgs();
        String ui_face_feature = user_info.getUi_face_feature();
        String ui_finger_imgs = user_info.getUi_finger_imgs();
        String ui_finger_feature = user_info.getUi_finger_feature();
        int ui_face_state = user_info.getUi_face_state();
        int ui_finger_state = user_info.getUi_finger_state();

        this.setUi_id(ui_id);
        this.setUuid(uuid);
        this.setUi_tel(ui_tel);
        this.setUi_password(ui_password);
        this.setUi_nickname(ui_nickname);
        this.setUi_avatar(ui_avatar);
        this.setUi_sex(ui_sex);
        this.setDriving_licence(driving_licence);
        this.setUi_name(ui_name);
        this.setUi_address(ui_address);
        this.setBind_tel(bind_tel);
        this.setUi_vc(ui_vc);
        this.setUi_rmb(ui_rmb);
        this.setCoupon_num(coupon_num);
        this.setUi_state(ui_state);
        this.setUi_autopay(ui_autopay);
        this.setPay_source(pay_source);
        this.setUi_level(ui_level);
        this.setUi_score(ui_score);
        this.setUi_mood(ui_mood);
        this.setUi_intro(ui_intro);
        this.setUi_age(ui_age);
        this.setUi_token(ui_token);
        this.setUi_email(ui_email);
        this.setUi_high(ui_high);
        this.setUi_degree(ui_degree);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setUi_flag(ui_flag);
        this.setUi_online(ui_online);
        this.setUi_integrity(ui_integrity);
        this.setNote(note);
        this.setLock_money(lock_money);
        this.setLock_expect_money(lock_expect_money);
        this.setLock_rent_money(lock_rent_money);
        this.setEu_id(eu_id);
        this.setEu_nd(eu_nd);
        this.setEtc_autopay(etc_autopay);
        this.setBank_no(bank_no);
        this.setUi_logo(ui_logo);
        this.setUi_face_imgs(ui_face_imgs);
        this.setUi_face_feature(ui_face_feature);
        this.setUi_finger_imgs(ui_finger_imgs);
        this.setUi_finger_feature(ui_finger_feature);
        this.setUi_face_state(ui_face_state);
        this.setUi_finger_state(ui_finger_state);

    }

    @SuppressWarnings("unused")
    public static void getUser_info(User_info user_info ){
        long ui_id = user_info.getUi_id();
        String uuid = user_info.getUuid();
        String ui_tel = user_info.getUi_tel();
        String ui_password = user_info.getUi_password();
        String ui_nickname = user_info.getUi_nickname();
        String ui_avatar = user_info.getUi_avatar();
        String ui_sex = user_info.getUi_sex();
        String driving_licence = user_info.getDriving_licence();
        String ui_name = user_info.getUi_name();
        String ui_address = user_info.getUi_address();
        String bind_tel = user_info.getBind_tel();
        long ui_vc = user_info.getUi_vc();
        long ui_rmb = user_info.getUi_rmb();
        long coupon_num = user_info.getCoupon_num();
        int ui_state = user_info.getUi_state();
        int ui_autopay = user_info.getUi_autopay();
        int pay_source = user_info.getPay_source();
        int ui_level = user_info.getUi_level();
        long ui_score = user_info.getUi_score();
        String ui_mood = user_info.getUi_mood();
        String ui_intro = user_info.getUi_intro();
        int ui_age = user_info.getUi_age();
        String ui_token = user_info.getUi_token();
        String ui_email = user_info.getUi_email();
        int ui_high = user_info.getUi_high();
        String ui_degree = user_info.getUi_degree();
        java.util.Date ctime = user_info.getCtime();
        java.util.Date utime = user_info.getUtime();
        int ui_flag = user_info.getUi_flag();
        long ui_online = user_info.getUi_online();
        int ui_integrity = user_info.getUi_integrity();
        String note = user_info.getNote();
        int lock_money = user_info.getLock_money();
        int lock_expect_money = user_info.getLock_expect_money();
        int lock_rent_money = user_info.getLock_rent_money();
        long eu_id = user_info.getEu_id();
        String eu_nd = user_info.getEu_nd();
        int etc_autopay = user_info.getEtc_autopay();
        String bank_no = user_info.getBank_no();
        int ui_logo = user_info.getUi_logo();
        String ui_face_imgs = user_info.getUi_face_imgs();
        String ui_face_feature = user_info.getUi_face_feature();
        String ui_finger_imgs = user_info.getUi_finger_imgs();
        String ui_finger_feature = user_info.getUi_finger_feature();
        int ui_face_state = user_info.getUi_face_state();
        int ui_finger_state = user_info.getUi_finger_state();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(User_info user_info){
        long ui_id = user_info.getUi_id();
        String uuid = user_info.getUuid();
        String ui_tel = user_info.getUi_tel();
        String ui_password = user_info.getUi_password();
        String ui_nickname = user_info.getUi_nickname();
        String ui_avatar = user_info.getUi_avatar();
        String ui_sex = user_info.getUi_sex();
        String driving_licence = user_info.getDriving_licence();
        String ui_name = user_info.getUi_name();
        String ui_address = user_info.getUi_address();
        String bind_tel = user_info.getBind_tel();
        long ui_vc = user_info.getUi_vc();
        long ui_rmb = user_info.getUi_rmb();
        long coupon_num = user_info.getCoupon_num();
        int ui_state = user_info.getUi_state();
        int ui_autopay = user_info.getUi_autopay();
        int pay_source = user_info.getPay_source();
        int ui_level = user_info.getUi_level();
        long ui_score = user_info.getUi_score();
        String ui_mood = user_info.getUi_mood();
        String ui_intro = user_info.getUi_intro();
        int ui_age = user_info.getUi_age();
        String ui_token = user_info.getUi_token();
        String ui_email = user_info.getUi_email();
        int ui_high = user_info.getUi_high();
        String ui_degree = user_info.getUi_degree();
        java.util.Date ctime = user_info.getCtime();
        java.util.Date utime = user_info.getUtime();
        int ui_flag = user_info.getUi_flag();
        long ui_online = user_info.getUi_online();
        int ui_integrity = user_info.getUi_integrity();
        String note = user_info.getNote();
        int lock_money = user_info.getLock_money();
        int lock_expect_money = user_info.getLock_expect_money();
        int lock_rent_money = user_info.getLock_rent_money();
        long eu_id = user_info.getEu_id();
        String eu_nd = user_info.getEu_nd();
        int etc_autopay = user_info.getEtc_autopay();
        String bank_no = user_info.getBank_no();
        int ui_logo = user_info.getUi_logo();
        String ui_face_imgs = user_info.getUi_face_imgs();
        String ui_face_feature = user_info.getUi_face_feature();
        String ui_finger_imgs = user_info.getUi_finger_imgs();
        String ui_finger_feature = user_info.getUi_finger_feature();
        int ui_face_state = user_info.getUi_face_state();
        int ui_finger_state = user_info.getUi_finger_state();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("ui_id",ui_id);
        _ret.put("uuid",uuid);
        _ret.put("ui_tel",ui_tel);
        _ret.put("ui_password",ui_password);
        _ret.put("ui_nickname",ui_nickname);
        _ret.put("ui_avatar",ui_avatar);
        _ret.put("ui_sex",ui_sex);
        _ret.put("driving_licence",driving_licence);
        _ret.put("ui_name",ui_name);
        _ret.put("ui_address",ui_address);
        _ret.put("bind_tel",bind_tel);
        _ret.put("ui_vc",ui_vc);
        _ret.put("ui_rmb",ui_rmb);
        _ret.put("coupon_num",coupon_num);
        _ret.put("ui_state",ui_state);
        _ret.put("ui_autopay",ui_autopay);
        _ret.put("pay_source",pay_source);
        _ret.put("ui_level",ui_level);
        _ret.put("ui_score",ui_score);
        _ret.put("ui_mood",ui_mood);
        _ret.put("ui_intro",ui_intro);
        _ret.put("ui_age",ui_age);
        _ret.put("ui_token",ui_token);
        _ret.put("ui_email",ui_email);
        _ret.put("ui_high",ui_high);
        _ret.put("ui_degree",ui_degree);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("ui_flag",ui_flag);
        _ret.put("ui_online",ui_online);
        _ret.put("ui_integrity",ui_integrity);
        _ret.put("note",note);
        _ret.put("lock_money",lock_money);
        _ret.put("lock_expect_money",lock_expect_money);
        _ret.put("lock_rent_money",lock_rent_money);
        _ret.put("eu_id",eu_id);
        _ret.put("eu_nd",eu_nd);
        _ret.put("etc_autopay",etc_autopay);
        _ret.put("bank_no",bank_no);
        _ret.put("ui_logo",ui_logo);
        _ret.put("ui_face_imgs",ui_face_imgs);
        _ret.put("ui_face_feature",ui_face_feature);
        _ret.put("ui_finger_imgs",ui_finger_imgs);
        _ret.put("ui_finger_feature",ui_finger_feature);
        _ret.put("ui_face_state",ui_face_state);
        _ret.put("ui_finger_state",ui_finger_state);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public User_info clone2(){
        try{
            return (User_info) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
