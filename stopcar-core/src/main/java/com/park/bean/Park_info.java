package com.park.bean;

import java.io.*;
import java.util.*;

//park_info
@SuppressWarnings({"serial"})
public class Park_info implements Cloneable , Serializable{

    //public static String[] carrays ={"pi_id","area_code","pi_name","address_name","lng","lat","linkman_name","linkman_tel","copy_linkman_name","copy_linkman_tel","pi_phone","department","enter_num","exit_num","hlc_enter_num","hlc_exit_num","enter_camera_num","exit_camera_num","camera_info","ctime","utime","park_type","is_expect","expect_money","allow_revoke_time","allow_expect_time","timeout_info","rent_info","month_price","is_rent","money","loginname","password","mac","is_fault","pi_state","roadside_type","pu_id","pu_nd","note","carport_yet","carport_space","carport_total","moth_car_num","moth_car_num_space","time_car_num","time_car_num_space","expect_car_num","upload_source","admin_id","pda_permit_time","special_ip","token","hardware_type","relet_state","ack_time"};

    public long pi_id;//bigint(20)    主键ID
    public String area_code="";//varchar(60)    省市县编号
    public String pi_name="";//varchar(150)    场地名称
    public String address_name="";//varchar(150)    场地地理位置名称
    public double lng;//double    场地经度
    public double lat;//double    场地纬度
    public String linkman_name="";//varchar(30)    场地联系人姓名
    public String linkman_tel="";//varchar(11)    场地联系人手机
    public String copy_linkman_name="";//varchar(30)    备用联系人姓名
    public String copy_linkman_tel="";//varchar(11)    备用联系人手机
    public String pi_phone="";//varchar(20)    场地座机号
    public String department="";//varchar(150)    负责单位
    public int enter_num;//int(11)    场地口入数量
    public int exit_num;//int(11)    场地出口数量
    public int hlc_enter_num;//int(11)    场地入口道闸数量
    public int hlc_exit_num;//int(11)    场地出口道闸数
    public int enter_camera_num;//int(11)    场地入口摄像头数
    public int exit_camera_num;//int(11)    场地出口摄像头数
    public String camera_info="";//varchar(150)    场地摄像头型号
    public java.util.Date ctime=new java.util.Date();//datetime    
    public java.util.Date utime=new java.util.Date();//datetime    
    public int park_type;//int(11)    停车场类型0：道闸停车场1：占道车场2：露天立体车库停车场
    public int is_expect;//int(11)    是否开启预约0:不开启1：开启
    public int expect_money;//int(11)    每小时预约费用（单位分）
    public int allow_revoke_time;//int(11)    允许预约撤单时间(单位分钟)
    public int allow_expect_time;//int(11)    允许预约时间最大时长(单位分钟)
    public String timeout_info="";//varchar(150)    计费信息首停2小时5元，之后每小时2元
    public String rent_info="";//varchar(150)    租赁计费信息准入时段18:00-08:00，300元/月
    public int month_price;//int(11)    租赁包月价格
    public int is_rent;//int(11)    是否开启租赁0:未开启1：已经开启
    public int money;//int(11)    蓝牌小汽车起步价
    public String loginname="";//varchar(60)    露天停车场帐号
    public String password="";//varchar(60)    露天停车场密码
    public String mac="";//varchar(255)    露天停车场PDAMAC（逗号分割）
    public int is_fault;//int(11)    停车场故障0:无故障1：发生故障
    public int pi_state;//int(11)    停车场状态（1开启0关闭）
    public int roadside_type;//int(11)    占道停车是否按次数收费0:按时间收费1：按次数收费
    public long pu_id;//bigint(20)    关联的商户主键ID
    public String pu_nd="";//varchar(100)    商户ND（商户编号）
    public String note="";//varchar(100)    备注
    public int carport_yet;//int(11)    临停已停车位
    public int carport_space;//int(11)    临停空车位个数
    public int carport_total;//int(11)    临停总车位个数
    public int moth_car_num;//int(11)    租赁离线包月车位总个数
    public int moth_car_num_space;//int(11)    租赁离线剩余车位数
    public int time_car_num;//int(11)    时间段包月车位总数
    public int time_car_num_space;//int(11)    时间段包月车位剩余个数
    public int expect_car_num;//int(11)    已预约车位数
    public int upload_source;//int(11)    上传数据来源0:离线道闸上传1：中心管理后台录入2：Android市场人员跑动录入
    public long admin_id;//bigint(20)    上传数据后台管理账户ID
    public String pda_permit_time="";//varchar(100)    PDA停车场上班时间-下班时间(8:00-21:00)
    public String special_ip="";//varchar(60)    道闸专线静态IP
    public String token="";//varchar(80)    授权token
    public int hardware_type;//int(11)    具有哪些硬件类型（0：未知1：地磁2：地磁+蓝牙车位）
    public int relet_state;//int(11)    线上续租（0：关闭1：开启）
    public String ack_time="";//varchar(30)    停车场正式开启时间(例如：年月日时分秒2017-09-1918:56:00)



    public long getPi_id(){
        return pi_id;
    }

    public void setPi_id(long value){
        this.pi_id= value;
    }

    public String getArea_code(){
        return area_code;
    }

    public void setArea_code(String value){
    	if(value == null){
           value = "";
        }
        this.area_code= value;
    }

    public String getPi_name(){
        return pi_name;
    }

    public void setPi_name(String value){
    	if(value == null){
           value = "";
        }
        this.pi_name= value;
    }

    public String getAddress_name(){
        return address_name;
    }

    public void setAddress_name(String value){
    	if(value == null){
           value = "";
        }
        this.address_name= value;
    }

    public double getLng(){
        return lng;
    }

    public void setLng(double value){
        this.lng= value;
    }

    public double getLat(){
        return lat;
    }

    public void setLat(double value){
        this.lat= value;
    }

    public String getLinkman_name(){
        return linkman_name;
    }

    public void setLinkman_name(String value){
    	if(value == null){
           value = "";
        }
        this.linkman_name= value;
    }

    public String getLinkman_tel(){
        return linkman_tel;
    }

    public void setLinkman_tel(String value){
    	if(value == null){
           value = "";
        }
        this.linkman_tel= value;
    }

    public String getCopy_linkman_name(){
        return copy_linkman_name;
    }

    public void setCopy_linkman_name(String value){
    	if(value == null){
           value = "";
        }
        this.copy_linkman_name= value;
    }

    public String getCopy_linkman_tel(){
        return copy_linkman_tel;
    }

    public void setCopy_linkman_tel(String value){
    	if(value == null){
           value = "";
        }
        this.copy_linkman_tel= value;
    }

    public String getPi_phone(){
        return pi_phone;
    }

    public void setPi_phone(String value){
    	if(value == null){
           value = "";
        }
        this.pi_phone= value;
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(String value){
    	if(value == null){
           value = "";
        }
        this.department= value;
    }

    public int getEnter_num(){
        return enter_num;
    }

    public void setEnter_num(int value){
        this.enter_num= value;
    }

    public int getExit_num(){
        return exit_num;
    }

    public void setExit_num(int value){
        this.exit_num= value;
    }

    public int getHlc_enter_num(){
        return hlc_enter_num;
    }

    public void setHlc_enter_num(int value){
        this.hlc_enter_num= value;
    }

    public int getHlc_exit_num(){
        return hlc_exit_num;
    }

    public void setHlc_exit_num(int value){
        this.hlc_exit_num= value;
    }

    public int getEnter_camera_num(){
        return enter_camera_num;
    }

    public void setEnter_camera_num(int value){
        this.enter_camera_num= value;
    }

    public int getExit_camera_num(){
        return exit_camera_num;
    }

    public void setExit_camera_num(int value){
        this.exit_camera_num= value;
    }

    public String getCamera_info(){
        return camera_info;
    }

    public void setCamera_info(String value){
    	if(value == null){
           value = "";
        }
        this.camera_info= value;
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

    public int getPark_type(){
        return park_type;
    }

    public void setPark_type(int value){
        this.park_type= value;
    }

    public int getIs_expect(){
        return is_expect;
    }

    public void setIs_expect(int value){
        this.is_expect= value;
    }

    public int getExpect_money(){
        return expect_money;
    }

    public void setExpect_money(int value){
        this.expect_money= value;
    }

    public int getAllow_revoke_time(){
        return allow_revoke_time;
    }

    public void setAllow_revoke_time(int value){
        this.allow_revoke_time= value;
    }

    public int getAllow_expect_time(){
        return allow_expect_time;
    }

    public void setAllow_expect_time(int value){
        this.allow_expect_time= value;
    }

    public String getTimeout_info(){
        return timeout_info;
    }

    public void setTimeout_info(String value){
    	if(value == null){
           value = "";
        }
        this.timeout_info= value;
    }

    public String getRent_info(){
        return rent_info;
    }

    public void setRent_info(String value){
    	if(value == null){
           value = "";
        }
        this.rent_info= value;
    }

    public int getMonth_price(){
        return month_price;
    }

    public void setMonth_price(int value){
        this.month_price= value;
    }

    public int getIs_rent(){
        return is_rent;
    }

    public void setIs_rent(int value){
        this.is_rent= value;
    }

    public int getMoney(){
        return money;
    }

    public void setMoney(int value){
        this.money= value;
    }

    public String getLoginname(){
        return loginname;
    }

    public void setLoginname(String value){
    	if(value == null){
           value = "";
        }
        this.loginname= value;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String value){
    	if(value == null){
           value = "";
        }
        this.password= value;
    }

    public String getMac(){
        return mac;
    }

    public void setMac(String value){
    	if(value == null){
           value = "";
        }
        this.mac= value;
    }

    public int getIs_fault(){
        return is_fault;
    }

    public void setIs_fault(int value){
        this.is_fault= value;
    }

    public int getPi_state(){
        return pi_state;
    }

    public void setPi_state(int value){
        this.pi_state= value;
    }

    public int getRoadside_type(){
        return roadside_type;
    }

    public void setRoadside_type(int value){
        this.roadside_type= value;
    }

    public long getPu_id(){
        return pu_id;
    }

    public void setPu_id(long value){
        this.pu_id= value;
    }

    public String getPu_nd(){
        return pu_nd;
    }

    public void setPu_nd(String value){
    	if(value == null){
           value = "";
        }
        this.pu_nd= value;
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

    public int getCarport_yet(){
        return carport_yet;
    }

    public void setCarport_yet(int value){
        this.carport_yet= value;
    }

    public int getCarport_space(){
        return carport_space;
    }

    public void setCarport_space(int value){
        this.carport_space= value;
    }

    public int getCarport_total(){
        return carport_total;
    }

    public void setCarport_total(int value){
        this.carport_total= value;
    }

    public int getMoth_car_num(){
        return moth_car_num;
    }

    public void setMoth_car_num(int value){
        this.moth_car_num= value;
    }

    public int getMoth_car_num_space(){
        return moth_car_num_space;
    }

    public void setMoth_car_num_space(int value){
        this.moth_car_num_space= value;
    }

    public int getTime_car_num(){
        return time_car_num;
    }

    public void setTime_car_num(int value){
        this.time_car_num= value;
    }

    public int getTime_car_num_space(){
        return time_car_num_space;
    }

    public void setTime_car_num_space(int value){
        this.time_car_num_space= value;
    }

    public int getExpect_car_num(){
        return expect_car_num;
    }

    public void setExpect_car_num(int value){
        this.expect_car_num= value;
    }

    public int getUpload_source(){
        return upload_source;
    }

    public void setUpload_source(int value){
        this.upload_source= value;
    }

    public long getAdmin_id(){
        return admin_id;
    }

    public void setAdmin_id(long value){
        this.admin_id= value;
    }

    public String getPda_permit_time(){
        return pda_permit_time;
    }

    public void setPda_permit_time(String value){
    	if(value == null){
           value = "";
        }
        this.pda_permit_time= value;
    }

    public String getSpecial_ip(){
        return special_ip;
    }

    public void setSpecial_ip(String value){
    	if(value == null){
           value = "";
        }
        this.special_ip= value;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String value){
    	if(value == null){
           value = "";
        }
        this.token= value;
    }

    public int getHardware_type(){
        return hardware_type;
    }

    public void setHardware_type(int value){
        this.hardware_type= value;
    }

    public int getRelet_state(){
        return relet_state;
    }

    public void setRelet_state(int value){
        this.relet_state= value;
    }

    public String getAck_time(){
        return ack_time;
    }

    public void setAck_time(String value){
    	if(value == null){
           value = "";
        }
        this.ack_time= value;
    }



    public static Park_info newPark_info(long pi_id, String area_code, String pi_name, String address_name, double lng, double lat, String linkman_name, String linkman_tel, String copy_linkman_name, String copy_linkman_tel, String pi_phone, String department, int enter_num, int exit_num, int hlc_enter_num, int hlc_exit_num, int enter_camera_num, int exit_camera_num, String camera_info, java.util.Date ctime, java.util.Date utime, int park_type, int is_expect, int expect_money, int allow_revoke_time, int allow_expect_time, String timeout_info, String rent_info, int month_price, int is_rent, int money, String loginname, String password, String mac, int is_fault, int pi_state, int roadside_type, long pu_id, String pu_nd, String note, int carport_yet, int carport_space, int carport_total, int moth_car_num, int moth_car_num_space, int time_car_num, int time_car_num_space, int expect_car_num, int upload_source, long admin_id, String pda_permit_time, String special_ip, String token, int hardware_type, int relet_state, String ack_time) {
        Park_info ret = new Park_info();
        ret.setPi_id(pi_id);
        ret.setArea_code(area_code);
        ret.setPi_name(pi_name);
        ret.setAddress_name(address_name);
        ret.setLng(lng);
        ret.setLat(lat);
        ret.setLinkman_name(linkman_name);
        ret.setLinkman_tel(linkman_tel);
        ret.setCopy_linkman_name(copy_linkman_name);
        ret.setCopy_linkman_tel(copy_linkman_tel);
        ret.setPi_phone(pi_phone);
        ret.setDepartment(department);
        ret.setEnter_num(enter_num);
        ret.setExit_num(exit_num);
        ret.setHlc_enter_num(hlc_enter_num);
        ret.setHlc_exit_num(hlc_exit_num);
        ret.setEnter_camera_num(enter_camera_num);
        ret.setExit_camera_num(exit_camera_num);
        ret.setCamera_info(camera_info);
        ret.setCtime(ctime);
        ret.setUtime(utime);
        ret.setPark_type(park_type);
        ret.setIs_expect(is_expect);
        ret.setExpect_money(expect_money);
        ret.setAllow_revoke_time(allow_revoke_time);
        ret.setAllow_expect_time(allow_expect_time);
        ret.setTimeout_info(timeout_info);
        ret.setRent_info(rent_info);
        ret.setMonth_price(month_price);
        ret.setIs_rent(is_rent);
        ret.setMoney(money);
        ret.setLoginname(loginname);
        ret.setPassword(password);
        ret.setMac(mac);
        ret.setIs_fault(is_fault);
        ret.setPi_state(pi_state);
        ret.setRoadside_type(roadside_type);
        ret.setPu_id(pu_id);
        ret.setPu_nd(pu_nd);
        ret.setNote(note);
        ret.setCarport_yet(carport_yet);
        ret.setCarport_space(carport_space);
        ret.setCarport_total(carport_total);
        ret.setMoth_car_num(moth_car_num);
        ret.setMoth_car_num_space(moth_car_num_space);
        ret.setTime_car_num(time_car_num);
        ret.setTime_car_num_space(time_car_num_space);
        ret.setExpect_car_num(expect_car_num);
        ret.setUpload_source(upload_source);
        ret.setAdmin_id(admin_id);
        ret.setPda_permit_time(pda_permit_time);
        ret.setSpecial_ip(special_ip);
        ret.setToken(token);
        ret.setHardware_type(hardware_type);
        ret.setRelet_state(relet_state);
        ret.setAck_time(ack_time);
        return ret;    
    }

    public void assignment(Park_info park_info) {
        long pi_id = park_info.getPi_id();
        String area_code = park_info.getArea_code();
        String pi_name = park_info.getPi_name();
        String address_name = park_info.getAddress_name();
        double lng = park_info.getLng();
        double lat = park_info.getLat();
        String linkman_name = park_info.getLinkman_name();
        String linkman_tel = park_info.getLinkman_tel();
        String copy_linkman_name = park_info.getCopy_linkman_name();
        String copy_linkman_tel = park_info.getCopy_linkman_tel();
        String pi_phone = park_info.getPi_phone();
        String department = park_info.getDepartment();
        int enter_num = park_info.getEnter_num();
        int exit_num = park_info.getExit_num();
        int hlc_enter_num = park_info.getHlc_enter_num();
        int hlc_exit_num = park_info.getHlc_exit_num();
        int enter_camera_num = park_info.getEnter_camera_num();
        int exit_camera_num = park_info.getExit_camera_num();
        String camera_info = park_info.getCamera_info();
        java.util.Date ctime = park_info.getCtime();
        java.util.Date utime = park_info.getUtime();
        int park_type = park_info.getPark_type();
        int is_expect = park_info.getIs_expect();
        int expect_money = park_info.getExpect_money();
        int allow_revoke_time = park_info.getAllow_revoke_time();
        int allow_expect_time = park_info.getAllow_expect_time();
        String timeout_info = park_info.getTimeout_info();
        String rent_info = park_info.getRent_info();
        int month_price = park_info.getMonth_price();
        int is_rent = park_info.getIs_rent();
        int money = park_info.getMoney();
        String loginname = park_info.getLoginname();
        String password = park_info.getPassword();
        String mac = park_info.getMac();
        int is_fault = park_info.getIs_fault();
        int pi_state = park_info.getPi_state();
        int roadside_type = park_info.getRoadside_type();
        long pu_id = park_info.getPu_id();
        String pu_nd = park_info.getPu_nd();
        String note = park_info.getNote();
        int carport_yet = park_info.getCarport_yet();
        int carport_space = park_info.getCarport_space();
        int carport_total = park_info.getCarport_total();
        int moth_car_num = park_info.getMoth_car_num();
        int moth_car_num_space = park_info.getMoth_car_num_space();
        int time_car_num = park_info.getTime_car_num();
        int time_car_num_space = park_info.getTime_car_num_space();
        int expect_car_num = park_info.getExpect_car_num();
        int upload_source = park_info.getUpload_source();
        long admin_id = park_info.getAdmin_id();
        String pda_permit_time = park_info.getPda_permit_time();
        String special_ip = park_info.getSpecial_ip();
        String token = park_info.getToken();
        int hardware_type = park_info.getHardware_type();
        int relet_state = park_info.getRelet_state();
        String ack_time = park_info.getAck_time();

        this.setPi_id(pi_id);
        this.setArea_code(area_code);
        this.setPi_name(pi_name);
        this.setAddress_name(address_name);
        this.setLng(lng);
        this.setLat(lat);
        this.setLinkman_name(linkman_name);
        this.setLinkman_tel(linkman_tel);
        this.setCopy_linkman_name(copy_linkman_name);
        this.setCopy_linkman_tel(copy_linkman_tel);
        this.setPi_phone(pi_phone);
        this.setDepartment(department);
        this.setEnter_num(enter_num);
        this.setExit_num(exit_num);
        this.setHlc_enter_num(hlc_enter_num);
        this.setHlc_exit_num(hlc_exit_num);
        this.setEnter_camera_num(enter_camera_num);
        this.setExit_camera_num(exit_camera_num);
        this.setCamera_info(camera_info);
        this.setCtime(ctime);
        this.setUtime(utime);
        this.setPark_type(park_type);
        this.setIs_expect(is_expect);
        this.setExpect_money(expect_money);
        this.setAllow_revoke_time(allow_revoke_time);
        this.setAllow_expect_time(allow_expect_time);
        this.setTimeout_info(timeout_info);
        this.setRent_info(rent_info);
        this.setMonth_price(month_price);
        this.setIs_rent(is_rent);
        this.setMoney(money);
        this.setLoginname(loginname);
        this.setPassword(password);
        this.setMac(mac);
        this.setIs_fault(is_fault);
        this.setPi_state(pi_state);
        this.setRoadside_type(roadside_type);
        this.setPu_id(pu_id);
        this.setPu_nd(pu_nd);
        this.setNote(note);
        this.setCarport_yet(carport_yet);
        this.setCarport_space(carport_space);
        this.setCarport_total(carport_total);
        this.setMoth_car_num(moth_car_num);
        this.setMoth_car_num_space(moth_car_num_space);
        this.setTime_car_num(time_car_num);
        this.setTime_car_num_space(time_car_num_space);
        this.setExpect_car_num(expect_car_num);
        this.setUpload_source(upload_source);
        this.setAdmin_id(admin_id);
        this.setPda_permit_time(pda_permit_time);
        this.setSpecial_ip(special_ip);
        this.setToken(token);
        this.setHardware_type(hardware_type);
        this.setRelet_state(relet_state);
        this.setAck_time(ack_time);

    }

    @SuppressWarnings("unused")
    public static void getPark_info(Park_info park_info ){
        long pi_id = park_info.getPi_id();
        String area_code = park_info.getArea_code();
        String pi_name = park_info.getPi_name();
        String address_name = park_info.getAddress_name();
        double lng = park_info.getLng();
        double lat = park_info.getLat();
        String linkman_name = park_info.getLinkman_name();
        String linkman_tel = park_info.getLinkman_tel();
        String copy_linkman_name = park_info.getCopy_linkman_name();
        String copy_linkman_tel = park_info.getCopy_linkman_tel();
        String pi_phone = park_info.getPi_phone();
        String department = park_info.getDepartment();
        int enter_num = park_info.getEnter_num();
        int exit_num = park_info.getExit_num();
        int hlc_enter_num = park_info.getHlc_enter_num();
        int hlc_exit_num = park_info.getHlc_exit_num();
        int enter_camera_num = park_info.getEnter_camera_num();
        int exit_camera_num = park_info.getExit_camera_num();
        String camera_info = park_info.getCamera_info();
        java.util.Date ctime = park_info.getCtime();
        java.util.Date utime = park_info.getUtime();
        int park_type = park_info.getPark_type();
        int is_expect = park_info.getIs_expect();
        int expect_money = park_info.getExpect_money();
        int allow_revoke_time = park_info.getAllow_revoke_time();
        int allow_expect_time = park_info.getAllow_expect_time();
        String timeout_info = park_info.getTimeout_info();
        String rent_info = park_info.getRent_info();
        int month_price = park_info.getMonth_price();
        int is_rent = park_info.getIs_rent();
        int money = park_info.getMoney();
        String loginname = park_info.getLoginname();
        String password = park_info.getPassword();
        String mac = park_info.getMac();
        int is_fault = park_info.getIs_fault();
        int pi_state = park_info.getPi_state();
        int roadside_type = park_info.getRoadside_type();
        long pu_id = park_info.getPu_id();
        String pu_nd = park_info.getPu_nd();
        String note = park_info.getNote();
        int carport_yet = park_info.getCarport_yet();
        int carport_space = park_info.getCarport_space();
        int carport_total = park_info.getCarport_total();
        int moth_car_num = park_info.getMoth_car_num();
        int moth_car_num_space = park_info.getMoth_car_num_space();
        int time_car_num = park_info.getTime_car_num();
        int time_car_num_space = park_info.getTime_car_num_space();
        int expect_car_num = park_info.getExpect_car_num();
        int upload_source = park_info.getUpload_source();
        long admin_id = park_info.getAdmin_id();
        String pda_permit_time = park_info.getPda_permit_time();
        String special_ip = park_info.getSpecial_ip();
        String token = park_info.getToken();
        int hardware_type = park_info.getHardware_type();
        int relet_state = park_info.getRelet_state();
        String ack_time = park_info.getAck_time();
    }

    public Map<String,Object> toMap(){
        return toEnMap(this);
    }

    public static Map<String,Object> toEnMap(Park_info park_info){
        long pi_id = park_info.getPi_id();
        String area_code = park_info.getArea_code();
        String pi_name = park_info.getPi_name();
        String address_name = park_info.getAddress_name();
        double lng = park_info.getLng();
        double lat = park_info.getLat();
        String linkman_name = park_info.getLinkman_name();
        String linkman_tel = park_info.getLinkman_tel();
        String copy_linkman_name = park_info.getCopy_linkman_name();
        String copy_linkman_tel = park_info.getCopy_linkman_tel();
        String pi_phone = park_info.getPi_phone();
        String department = park_info.getDepartment();
        int enter_num = park_info.getEnter_num();
        int exit_num = park_info.getExit_num();
        int hlc_enter_num = park_info.getHlc_enter_num();
        int hlc_exit_num = park_info.getHlc_exit_num();
        int enter_camera_num = park_info.getEnter_camera_num();
        int exit_camera_num = park_info.getExit_camera_num();
        String camera_info = park_info.getCamera_info();
        java.util.Date ctime = park_info.getCtime();
        java.util.Date utime = park_info.getUtime();
        int park_type = park_info.getPark_type();
        int is_expect = park_info.getIs_expect();
        int expect_money = park_info.getExpect_money();
        int allow_revoke_time = park_info.getAllow_revoke_time();
        int allow_expect_time = park_info.getAllow_expect_time();
        String timeout_info = park_info.getTimeout_info();
        String rent_info = park_info.getRent_info();
        int month_price = park_info.getMonth_price();
        int is_rent = park_info.getIs_rent();
        int money = park_info.getMoney();
        String loginname = park_info.getLoginname();
        String password = park_info.getPassword();
        String mac = park_info.getMac();
        int is_fault = park_info.getIs_fault();
        int pi_state = park_info.getPi_state();
        int roadside_type = park_info.getRoadside_type();
        long pu_id = park_info.getPu_id();
        String pu_nd = park_info.getPu_nd();
        String note = park_info.getNote();
        int carport_yet = park_info.getCarport_yet();
        int carport_space = park_info.getCarport_space();
        int carport_total = park_info.getCarport_total();
        int moth_car_num = park_info.getMoth_car_num();
        int moth_car_num_space = park_info.getMoth_car_num_space();
        int time_car_num = park_info.getTime_car_num();
        int time_car_num_space = park_info.getTime_car_num_space();
        int expect_car_num = park_info.getExpect_car_num();
        int upload_source = park_info.getUpload_source();
        long admin_id = park_info.getAdmin_id();
        String pda_permit_time = park_info.getPda_permit_time();
        String special_ip = park_info.getSpecial_ip();
        String token = park_info.getToken();
        int hardware_type = park_info.getHardware_type();
        int relet_state = park_info.getRelet_state();
        String ack_time = park_info.getAck_time();
    
        Map<String,Object>  _ret = new HashMap<String,Object>();
        _ret.put("pi_id",pi_id);
        _ret.put("area_code",area_code);
        _ret.put("pi_name",pi_name);
        _ret.put("address_name",address_name);
        _ret.put("lng",lng);
        _ret.put("lat",lat);
        _ret.put("linkman_name",linkman_name);
        _ret.put("linkman_tel",linkman_tel);
        _ret.put("copy_linkman_name",copy_linkman_name);
        _ret.put("copy_linkman_tel",copy_linkman_tel);
        _ret.put("pi_phone",pi_phone);
        _ret.put("department",department);
        _ret.put("enter_num",enter_num);
        _ret.put("exit_num",exit_num);
        _ret.put("hlc_enter_num",hlc_enter_num);
        _ret.put("hlc_exit_num",hlc_exit_num);
        _ret.put("enter_camera_num",enter_camera_num);
        _ret.put("exit_camera_num",exit_camera_num);
        _ret.put("camera_info",camera_info);
        _ret.put("ctime",ctime);
        _ret.put("utime",utime);
        _ret.put("park_type",park_type);
        _ret.put("is_expect",is_expect);
        _ret.put("expect_money",expect_money);
        _ret.put("allow_revoke_time",allow_revoke_time);
        _ret.put("allow_expect_time",allow_expect_time);
        _ret.put("timeout_info",timeout_info);
        _ret.put("rent_info",rent_info);
        _ret.put("month_price",month_price);
        _ret.put("is_rent",is_rent);
        _ret.put("money",money);
        _ret.put("loginname",loginname);
        _ret.put("password",password);
        _ret.put("mac",mac);
        _ret.put("is_fault",is_fault);
        _ret.put("pi_state",pi_state);
        _ret.put("roadside_type",roadside_type);
        _ret.put("pu_id",pu_id);
        _ret.put("pu_nd",pu_nd);
        _ret.put("note",note);
        _ret.put("carport_yet",carport_yet);
        _ret.put("carport_space",carport_space);
        _ret.put("carport_total",carport_total);
        _ret.put("moth_car_num",moth_car_num);
        _ret.put("moth_car_num_space",moth_car_num_space);
        _ret.put("time_car_num",time_car_num);
        _ret.put("time_car_num_space",time_car_num_space);
        _ret.put("expect_car_num",expect_car_num);
        _ret.put("upload_source",upload_source);
        _ret.put("admin_id",admin_id);
        _ret.put("pda_permit_time",pda_permit_time);
        _ret.put("special_ip",special_ip);
        _ret.put("token",token);
        _ret.put("hardware_type",hardware_type);
        _ret.put("relet_state",relet_state);
        _ret.put("ack_time",ack_time);
        return _ret;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Park_info clone2(){
        try{
            return (Park_info) this.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
