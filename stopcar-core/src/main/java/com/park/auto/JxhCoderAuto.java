package com.park.auto;

import com.highbeauty.sql.spring.builder.ABuilder;
/**
 *
* @ClassName: JxhCoderAuto
* @Description: TODO(数据库表字段自动生成bean、dao)
* @author 敬小虎
* @date 2015年3月5日 下午2:24:48
*
 */
public class JxhCoderAuto {
	public static void main(String[] args) throws Throwable {
		boolean src = true;
		boolean is_maven = true;
		String moduleName="stopcar-core";
		String pkg = "com.park.";
		String[] tablenames = {"user_info","car_in_out","fault_record","park_coupon","park_device","park_info","pay_park","rental_charging_rule","user_moneyback","user_park_coupon","user_login_log",
				"sms_running","sms_validate","user_feedback","user_carcode","pay_month_park",
				"china_area","park_heartbeat",
				"user_vc_act","park_userinfo",
				"parkinfo_partner","user_cash_apply","user_pay",
				"app_version","organization_user","lock_money_log"
				,"random_coupon_log","activity_info","activity_event","pda_info","pda_channel","channel_version","pda_userinfo","pda_punch_card","request_params_log"
				,"merchant_parkinfo_monthfree_log","pda_owe_order_cover"
				,"etc_userpay_record","etc_userinfo","magnetic_device",
				"magnetic_device_log","park_carport_log","park_hardware",
				"parkinfo_monthfree","park_rule","user_park_coupon_log","rent_defer","event_schedule","rabbitmq_push_fail","carcode_park_rent","client_gate_rule"
				,"hardware_version_log","finger_userinfo","wubo_hardware","park_authorize"
				,"finger_userinfo_relation","finger_userinfo_carcode","finger_userinfo_bank","finger_userinfo_new"
				,"finger_userinfo_carcode2","finger_userinfo_new2","finger_userinfo_relation2","order_abnormal_log"};
		String ip = "127.0.0.1";
		int port = 3306;
		String user = "root";
		String password = "root";
		String databaseName = "stopcar";
		ABuilder.AutoCoder(is_maven,src,moduleName, pkg, tablenames, ip, port, user, password, databaseName);
	}

}
