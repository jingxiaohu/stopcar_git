package com.park.dao;


import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

//DAO Factory

@Repository("daoFactory")
public class DaoFactory{

@Autowired
protected User_infoDao user_infoDao;
@Autowired
protected Car_in_outDao car_in_outDao;
@Autowired
protected Fault_recordDao fault_recordDao;
@Autowired
protected Park_couponDao park_couponDao;
@Autowired
protected Park_deviceDao park_deviceDao;
@Autowired
protected Park_infoDao park_infoDao;
@Autowired
protected Pay_parkDao pay_parkDao;
@Autowired
protected Rental_charging_ruleDao rental_charging_ruleDao;
@Autowired
protected User_moneybackDao user_moneybackDao;
@Autowired
protected User_park_couponDao user_park_couponDao;
@Autowired
protected User_login_logDao user_login_logDao;
@Autowired
protected Sms_runningDao sms_runningDao;
@Autowired
protected Sms_validateDao sms_validateDao;
@Autowired
protected User_feedbackDao user_feedbackDao;
@Autowired
protected User_carcodeDao user_carcodeDao;
@Autowired
protected Pay_month_parkDao pay_month_parkDao;
@Autowired
protected China_areaDao china_areaDao;
@Autowired
protected Park_heartbeatDao park_heartbeatDao;
@Autowired
protected User_vc_actDao user_vc_actDao;
@Autowired
protected Park_userinfoDao park_userinfoDao;
@Autowired
protected Parkinfo_partnerDao parkinfo_partnerDao;
@Autowired
protected User_cash_applyDao user_cash_applyDao;
@Autowired
protected User_payDao user_payDao;
@Autowired
protected App_versionDao app_versionDao;
@Autowired
protected Organization_userDao organization_userDao;
@Autowired
protected Lock_money_logDao lock_money_logDao;
@Autowired
protected Random_coupon_logDao random_coupon_logDao;
@Autowired
protected Activity_infoDao activity_infoDao;
@Autowired
protected Activity_eventDao activity_eventDao;
@Autowired
protected Pda_infoDao pda_infoDao;
@Autowired
protected Pda_channelDao pda_channelDao;
@Autowired
protected Channel_versionDao channel_versionDao;
@Autowired
protected Pda_userinfoDao pda_userinfoDao;
@Autowired
protected Pda_punch_cardDao pda_punch_cardDao;
@Autowired
protected Request_params_logDao request_params_logDao;
@Autowired
protected Merchant_parkinfo_monthfree_logDao merchant_parkinfo_monthfree_logDao;
@Autowired
protected Pda_owe_order_coverDao pda_owe_order_coverDao;
@Autowired
protected Etc_userpay_recordDao etc_userpay_recordDao;
@Autowired
protected Etc_userinfoDao etc_userinfoDao;
@Autowired
protected Magnetic_deviceDao magnetic_deviceDao;
@Autowired
protected Magnetic_device_logDao magnetic_device_logDao;
@Autowired
protected Park_carport_logDao park_carport_logDao;
@Autowired
protected Park_hardwareDao park_hardwareDao;
@Autowired
protected Parkinfo_monthfreeDao parkinfo_monthfreeDao;
@Autowired
protected Park_ruleDao park_ruleDao;
@Autowired
protected User_park_coupon_logDao user_park_coupon_logDao;
@Autowired
protected Rent_deferDao rent_deferDao;
@Autowired
protected Event_scheduleDao event_scheduleDao;
@Autowired
protected Rabbitmq_push_failDao rabbitmq_push_failDao;
@Autowired
protected Carcode_park_rentDao carcode_park_rentDao;
@Autowired
protected Client_gate_ruleDao client_gate_ruleDao;
@Autowired
protected Hardware_version_logDao hardware_version_logDao;
@Autowired
protected Finger_userinfoDao finger_userinfoDao;
@Autowired
protected Wubo_hardwareDao wubo_hardwareDao;
@Autowired
protected Park_authorizeDao park_authorizeDao;
@Autowired
protected Finger_userinfo_relationDao finger_userinfo_relationDao;
@Autowired
protected Finger_userinfo_carcodeDao finger_userinfo_carcodeDao;
@Autowired
protected Finger_userinfo_bankDao finger_userinfo_bankDao;
@Autowired
protected Finger_userinfo_newDao finger_userinfo_newDao;
@Autowired
protected Finger_userinfo_carcode2Dao finger_userinfo_carcode2Dao;
@Autowired
protected Finger_userinfo_new2Dao finger_userinfo_new2Dao;
@Autowired
protected Finger_userinfo_relation2Dao finger_userinfo_relation2Dao;
@Autowired
protected Order_abnormal_logDao order_abnormal_logDao;

/*******************************下面是GET方法**************************************/
public User_infoDao getUser_infoDao() {
	return user_infoDao;
}
public Car_in_outDao getCar_in_outDao() {
	return car_in_outDao;
}
public Fault_recordDao getFault_recordDao() {
	return fault_recordDao;
}
public Park_couponDao getPark_couponDao() {
	return park_couponDao;
}
public Park_deviceDao getPark_deviceDao() {
	return park_deviceDao;
}
public Park_infoDao getPark_infoDao() {
	return park_infoDao;
}
public Pay_parkDao getPay_parkDao() {
	return pay_parkDao;
}
public Rental_charging_ruleDao getRental_charging_ruleDao() {
	return rental_charging_ruleDao;
}
public User_moneybackDao getUser_moneybackDao() {
	return user_moneybackDao;
}
public User_park_couponDao getUser_park_couponDao() {
	return user_park_couponDao;
}
public User_login_logDao getUser_login_logDao() {
	return user_login_logDao;
}
public Sms_runningDao getSms_runningDao() {
	return sms_runningDao;
}
public Sms_validateDao getSms_validateDao() {
	return sms_validateDao;
}
public User_feedbackDao getUser_feedbackDao() {
	return user_feedbackDao;
}
public User_carcodeDao getUser_carcodeDao() {
	return user_carcodeDao;
}
public Pay_month_parkDao getPay_month_parkDao() {
	return pay_month_parkDao;
}
public China_areaDao getChina_areaDao() {
	return china_areaDao;
}
public Park_heartbeatDao getPark_heartbeatDao() {
	return park_heartbeatDao;
}
public User_vc_actDao getUser_vc_actDao() {
	return user_vc_actDao;
}
public Park_userinfoDao getPark_userinfoDao() {
	return park_userinfoDao;
}
public Parkinfo_partnerDao getParkinfo_partnerDao() {
	return parkinfo_partnerDao;
}
public User_cash_applyDao getUser_cash_applyDao() {
	return user_cash_applyDao;
}
public User_payDao getUser_payDao() {
	return user_payDao;
}
public App_versionDao getApp_versionDao() {
	return app_versionDao;
}
public Organization_userDao getOrganization_userDao() {
	return organization_userDao;
}
public Lock_money_logDao getLock_money_logDao() {
	return lock_money_logDao;
}
public Random_coupon_logDao getRandom_coupon_logDao() {
	return random_coupon_logDao;
}
public Activity_infoDao getActivity_infoDao() {
	return activity_infoDao;
}
public Activity_eventDao getActivity_eventDao() {
	return activity_eventDao;
}
public Pda_infoDao getPda_infoDao() {
	return pda_infoDao;
}
public Pda_channelDao getPda_channelDao() {
	return pda_channelDao;
}
public Channel_versionDao getChannel_versionDao() {
	return channel_versionDao;
}
public Pda_userinfoDao getPda_userinfoDao() {
	return pda_userinfoDao;
}
public Pda_punch_cardDao getPda_punch_cardDao() {
	return pda_punch_cardDao;
}
public Request_params_logDao getRequest_params_logDao() {
	return request_params_logDao;
}
public Merchant_parkinfo_monthfree_logDao getMerchant_parkinfo_monthfree_logDao() {
	return merchant_parkinfo_monthfree_logDao;
}
public Pda_owe_order_coverDao getPda_owe_order_coverDao() {
	return pda_owe_order_coverDao;
}
public Etc_userpay_recordDao getEtc_userpay_recordDao() {
	return etc_userpay_recordDao;
}
public Etc_userinfoDao getEtc_userinfoDao() {
	return etc_userinfoDao;
}
public Magnetic_deviceDao getMagnetic_deviceDao() {
	return magnetic_deviceDao;
}
public Magnetic_device_logDao getMagnetic_device_logDao() {
	return magnetic_device_logDao;
}
public Park_carport_logDao getPark_carport_logDao() {
	return park_carport_logDao;
}
public Park_hardwareDao getPark_hardwareDao() {
	return park_hardwareDao;
}
public Parkinfo_monthfreeDao getParkinfo_monthfreeDao() {
	return parkinfo_monthfreeDao;
}
public Park_ruleDao getPark_ruleDao() {
	return park_ruleDao;
}
public User_park_coupon_logDao getUser_park_coupon_logDao() {
	return user_park_coupon_logDao;
}
public Rent_deferDao getRent_deferDao() {
	return rent_deferDao;
}
public Event_scheduleDao getEvent_scheduleDao() {
	return event_scheduleDao;
}
public Rabbitmq_push_failDao getRabbitmq_push_failDao() {
	return rabbitmq_push_failDao;
}
public Carcode_park_rentDao getCarcode_park_rentDao() {
	return carcode_park_rentDao;
}
public Client_gate_ruleDao getClient_gate_ruleDao() {
	return client_gate_ruleDao;
}
public Hardware_version_logDao getHardware_version_logDao() {
	return hardware_version_logDao;
}
public Finger_userinfoDao getFinger_userinfoDao() {
	return finger_userinfoDao;
}
public Wubo_hardwareDao getWubo_hardwareDao() {
	return wubo_hardwareDao;
}
public Park_authorizeDao getPark_authorizeDao() {
	return park_authorizeDao;
}
public Finger_userinfo_relationDao getFinger_userinfo_relationDao() {
	return finger_userinfo_relationDao;
}
public Finger_userinfo_carcodeDao getFinger_userinfo_carcodeDao() {
	return finger_userinfo_carcodeDao;
}
public Finger_userinfo_bankDao getFinger_userinfo_bankDao() {
	return finger_userinfo_bankDao;
}
public Finger_userinfo_newDao getFinger_userinfo_newDao() {
	return finger_userinfo_newDao;
}
public Finger_userinfo_carcode2Dao getFinger_userinfo_carcode2Dao() {
	return finger_userinfo_carcode2Dao;
}
public Finger_userinfo_new2Dao getFinger_userinfo_new2Dao() {
	return finger_userinfo_new2Dao;
}
public Finger_userinfo_relation2Dao getFinger_userinfo_relation2Dao() {
	return finger_userinfo_relation2Dao;
}
public Order_abnormal_logDao getOrder_abnormal_logDao() {
	return order_abnormal_logDao;
}


}
