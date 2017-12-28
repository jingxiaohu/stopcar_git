package com.park.dao;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import java.util.*;

import java.sql.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.*;
import com.park.bean.*;
import org.springframework.stereotype.Repository;
import com.highbeauty.text.EasyTemplate;

//pay_month_park

@Repository("pay_month_parkDao")
public class Pay_month_parkDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Pay_month_parkDao.class);



    private  String TABLE = "pay_month_park";

    private  String TABLENAME = "pay_month_park";

    public  String getTABLE(){
        return  TABLE;
    }

    public  String getTABLENAME(){
        return  TABLENAME;
    }

    public  String TABLEMM(){
        return TABLE + sdfMm.format(new java.util.Date());
    }

    public  String TABLEDD(){
        return TABLE + sdfDd.format(new java.util.Date());
    }


    private  String[] carrays ={"id","pi_id","ui_id","car_code","money","pp_state","ctime","utime","month_num","pay_source","my_order","other_order","pay_type","pp_versioncode","phone_type","order_type","start_time","end_time","month_info","allege_state","area_code","is_del","upc_id","discount_money","note","discount_name","discount_type","final_time","address_name","base_rate","outtime_rate","outtime_money","outtime_time","outtime_minute","outtime_start_price","outtime_start_minute","outtime_charge_money","outtime_charge_minute","outtime_other_order","outtime_paytime","outtime_paystate","permit_time","is_24hours","is_open","open_time","is_cash","park_type","scan_time","arrive_time","temp_money","pi_name","is_over","cancel_state","pu_id","pu_nd","lng","lat","is_expire","rent_state","ui_nd","ai_id","ai_money","ai_effect","ui_tel","magnetic_state","gov_num","stime","up_orderid"};
    private  String coulmns ="id,pi_id,ui_id,car_code,money,pp_state,ctime,utime,month_num,pay_source,my_order,other_order,pay_type,pp_versioncode,phone_type,order_type,start_time,end_time,month_info,allege_state,area_code,is_del,upc_id,discount_money,note,discount_name,discount_type,final_time,address_name,base_rate,outtime_rate,outtime_money,outtime_time,outtime_minute,outtime_start_price,outtime_start_minute,outtime_charge_money,outtime_charge_minute,outtime_other_order,outtime_paytime,outtime_paystate,permit_time,is_24hours,is_open,open_time,is_cash,park_type,scan_time,arrive_time,temp_money,pi_name,is_over,cancel_state,pu_id,pu_nd,lng,lat,is_expire,rent_state,ui_nd,ai_id,ai_money,ai_effect,ui_tel,magnetic_state,gov_num,stime,up_orderid";
    private  String coulmns2 ="pi_id,ui_id,car_code,money,pp_state,ctime,utime,month_num,pay_source,my_order,other_order,pay_type,pp_versioncode,phone_type,order_type,start_time,end_time,month_info,allege_state,area_code,is_del,upc_id,discount_money,note,discount_name,discount_type,final_time,address_name,base_rate,outtime_rate,outtime_money,outtime_time,outtime_minute,outtime_start_price,outtime_start_minute,outtime_charge_money,outtime_charge_minute,outtime_other_order,outtime_paytime,outtime_paystate,permit_time,is_24hours,is_open,open_time,is_cash,park_type,scan_time,arrive_time,temp_money,pi_name,is_over,cancel_state,pu_id,pu_nd,lng,lat,is_expire,rent_state,ui_nd,ai_id,ai_money,ai_effect,ui_tel,magnetic_state,gov_num,stime,up_orderid";

    public  String[] getCarrays(){
        return  carrays;
    }

    public  String getCoulmns(){
        return  coulmns;
    }

    public  String getCoulmns2(){
        return  coulmns2;
    }

    //添加数据
    public int insert(Pay_month_park bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Pay_month_park bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,ui_id,car_code,money,pp_state,ctime,utime,month_num,pay_source,my_order,other_order,pay_type,pp_versioncode,phone_type,order_type,start_time,end_time,month_info,allege_state,area_code,is_del,upc_id,discount_money,note,discount_name,discount_type,final_time,address_name,base_rate,outtime_rate,outtime_money,outtime_time,outtime_minute,outtime_start_price,outtime_start_minute,outtime_charge_money,outtime_charge_minute,outtime_other_order,outtime_paytime,outtime_paystate,permit_time,is_24hours,is_open,open_time,is_cash,park_type,scan_time,arrive_time,temp_money,pi_name,is_over,cancel_state,pu_id,pu_nd,lng,lat,is_expire,rent_state,ui_nd,ai_id,ai_money,ai_effect,ui_tel,magnetic_state,gov_num,stime,up_orderid) VALUES (:pi_id,:ui_id,:car_code,:money,:pp_state,:ctime,:utime,:month_num,:pay_source,:my_order,:other_order,:pay_type,:pp_versioncode,:phone_type,:order_type,:start_time,:end_time,:month_info,:allege_state,:area_code,:is_del,:upc_id,:discount_money,:note,:discount_name,:discount_type,:final_time,:address_name,:base_rate,:outtime_rate,:outtime_money,:outtime_time,:outtime_minute,:outtime_start_price,:outtime_start_minute,:outtime_charge_money,:outtime_charge_minute,:outtime_other_order,:outtime_paytime,:outtime_paystate,:permit_time,:is_24hours,:is_open,:open_time,:is_cash,:park_type,:scan_time,:arrive_time,:temp_money,:pi_name,:is_over,:cancel_state,:pu_id,:pu_nd,:lng,:lat,:is_expire,:rent_state,:ui_nd,:ai_id,:ai_money,:ai_effect,:ui_tel,:magnetic_state,:gov_num,:stime,:up_orderid)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            KeyHolder keyholder = new GeneratedKeyHolder();
            _np.update(sql, ps, keyholder);
            return keyholder.getKey().intValue();
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //添加数据
    public int insert_primarykey(Pay_month_park bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Pay_month_park bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (id,pi_id,ui_id,car_code,money,pp_state,ctime,utime,month_num,pay_source,my_order,other_order,pay_type,pp_versioncode,phone_type,order_type,start_time,end_time,month_info,allege_state,area_code,is_del,upc_id,discount_money,note,discount_name,discount_type,final_time,address_name,base_rate,outtime_rate,outtime_money,outtime_time,outtime_minute,outtime_start_price,outtime_start_minute,outtime_charge_money,outtime_charge_minute,outtime_other_order,outtime_paytime,outtime_paystate,permit_time,is_24hours,is_open,open_time,is_cash,park_type,scan_time,arrive_time,temp_money,pi_name,is_over,cancel_state,pu_id,pu_nd,lng,lat,is_expire,rent_state,ui_nd,ai_id,ai_money,ai_effect,ui_tel,magnetic_state,gov_num,stime,up_orderid) VALUES (:id,:pi_id,:ui_id,:car_code,:money,:pp_state,:ctime,:utime,:month_num,:pay_source,:my_order,:other_order,:pay_type,:pp_versioncode,:phone_type,:order_type,:start_time,:end_time,:month_info,:allege_state,:area_code,:is_del,:upc_id,:discount_money,:note,:discount_name,:discount_type,:final_time,:address_name,:base_rate,:outtime_rate,:outtime_money,:outtime_time,:outtime_minute,:outtime_start_price,:outtime_start_minute,:outtime_charge_money,:outtime_charge_minute,:outtime_other_order,:outtime_paytime,:outtime_paystate,:permit_time,:is_24hours,:is_open,:open_time,:is_cash,:park_type,:scan_time,:arrive_time,:temp_money,:pi_name,:is_over,:cancel_state,:pu_id,:pu_nd,:lng,:lat,:is_expire,:rent_state,:ui_nd,:ai_id,:ai_money,:ai_effect,:ui_tel,:magnetic_state,:gov_num,:stime,:up_orderid)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Pay_month_park> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Pay_month_park> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,ui_id,car_code,money,pp_state,ctime,utime,month_num,pay_source,my_order,other_order,pay_type,pp_versioncode,phone_type,order_type,start_time,end_time,month_info,allege_state,area_code,is_del,upc_id,discount_money,note,discount_name,discount_type,final_time,address_name,base_rate,outtime_rate,outtime_money,outtime_time,outtime_minute,outtime_start_price,outtime_start_minute,outtime_charge_money,outtime_charge_minute,outtime_other_order,outtime_paytime,outtime_paystate,permit_time,is_24hours,is_open,open_time,is_cash,park_type,scan_time,arrive_time,temp_money,pi_name,is_over,cancel_state,pu_id,pu_nd,lng,lat,is_expire,rent_state,ui_nd,ai_id,ai_money,ai_effect,ui_tel,magnetic_state,gov_num,stime,up_orderid) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Pay_month_park bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setLong(2, bean.ui_id);
                    ps.setString(3, bean.car_code);
                    ps.setInt(4, bean.money);
                    ps.setInt(5, bean.pp_state);
                    ps.setTimestamp(6, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(7, new Timestamp(bean.utime.getTime()));
                    ps.setInt(8, bean.month_num);
                    ps.setInt(9, bean.pay_source);
                    ps.setString(10, bean.my_order);
                    ps.setString(11, bean.other_order);
                    ps.setInt(12, bean.pay_type);
                    ps.setString(13, bean.pp_versioncode);
                    ps.setInt(14, bean.phone_type);
                    ps.setInt(15, bean.order_type);
                    ps.setTimestamp(16, new Timestamp(bean.start_time.getTime()));
                    ps.setTimestamp(17, new Timestamp(bean.end_time.getTime()));
                    ps.setString(18, bean.month_info);
                    ps.setInt(19, bean.allege_state);
                    ps.setString(20, bean.area_code);
                    ps.setInt(21, bean.is_del);
                    ps.setLong(22, bean.upc_id);
                    ps.setLong(23, bean.discount_money);
                    ps.setString(24, bean.note);
                    ps.setString(25, bean.discount_name);
                    ps.setInt(26, bean.discount_type);
                    ps.setInt(27, bean.final_time);
                    ps.setString(28, bean.address_name);
                    ps.setString(29, bean.base_rate);
                    ps.setString(30, bean.outtime_rate);
                    ps.setInt(31, bean.outtime_money);
                    ps.setTimestamp(32, new Timestamp(bean.outtime_time.getTime()));
                    ps.setInt(33, bean.outtime_minute);
                    ps.setInt(34, bean.outtime_start_price);
                    ps.setInt(35, bean.outtime_start_minute);
                    ps.setInt(36, bean.outtime_charge_money);
                    ps.setInt(37, bean.outtime_charge_minute);
                    ps.setString(38, bean.outtime_other_order);
                    ps.setTimestamp(39, new Timestamp(bean.outtime_paytime.getTime()));
                    ps.setInt(40, bean.outtime_paystate);
                    ps.setString(41, bean.permit_time);
                    ps.setInt(42, bean.is_24hours);
                    ps.setInt(43, bean.is_open);
                    ps.setTimestamp(44, new Timestamp(bean.open_time.getTime()));
                    ps.setInt(45, bean.is_cash);
                    ps.setInt(46, bean.park_type);
                    ps.setTimestamp(47, new Timestamp(bean.scan_time.getTime()));
                    ps.setTimestamp(48, new Timestamp(bean.arrive_time.getTime()));
                    ps.setLong(49, bean.temp_money);
                    ps.setString(50, bean.pi_name);
                    ps.setInt(51, bean.is_over);
                    ps.setInt(52, bean.cancel_state);
                    ps.setLong(53, bean.pu_id);
                    ps.setString(54, bean.pu_nd);
                    ps.setDouble(55, bean.lng);
                    ps.setDouble(56, bean.lat);
                    ps.setInt(57, bean.is_expire);
                    ps.setInt(58, bean.rent_state);
                    ps.setString(59, bean.ui_nd);
                    ps.setLong(60, bean.ai_id);
                    ps.setInt(61, bean.ai_money);
                    ps.setInt(62, bean.ai_effect);
                    ps.setString(63, bean.ui_tel);
                    ps.setInt(64, bean.magnetic_state);
                    ps.setString(65, bean.gov_num);
                    ps.setTimestamp(66, new Timestamp(bean.stime.getTime()));
                    ps.setString(67, bean.up_orderid);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Pay_month_park> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Pay_month_park> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,pi_id,ui_id,car_code,money,pp_state,ctime,utime,month_num,pay_source,my_order,other_order,pay_type,pp_versioncode,phone_type,order_type,start_time,end_time,month_info,allege_state,area_code,is_del,upc_id,discount_money,note,discount_name,discount_type,final_time,address_name,base_rate,outtime_rate,outtime_money,outtime_time,outtime_minute,outtime_start_price,outtime_start_minute,outtime_charge_money,outtime_charge_minute,outtime_other_order,outtime_paytime,outtime_paystate,permit_time,is_24hours,is_open,open_time,is_cash,park_type,scan_time,arrive_time,temp_money,pi_name,is_over,cancel_state,pu_id,pu_nd,lng,lat,is_expire,rent_state,ui_nd,ai_id,ai_money,ai_effect,ui_tel,magnetic_state,gov_num,stime,up_orderid FROM "+TABLENAME2+" ORDER BY id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Pay_month_park>(Pay_month_park.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Pay_month_park>();
        }
    }

    //查询最新数据
    public List<Pay_month_park> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Pay_month_park> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,pi_id,ui_id,car_code,money,pp_state,ctime,utime,month_num,pay_source,my_order,other_order,pay_type,pp_versioncode,phone_type,order_type,start_time,end_time,month_info,allege_state,area_code,is_del,upc_id,discount_money,note,discount_name,discount_type,final_time,address_name,base_rate,outtime_rate,outtime_money,outtime_time,outtime_minute,outtime_start_price,outtime_start_minute,outtime_charge_money,outtime_charge_minute,outtime_other_order,outtime_paytime,outtime_paystate,permit_time,is_24hours,is_open,open_time,is_cash,park_type,scan_time,arrive_time,temp_money,pi_name,is_over,cancel_state,pu_id,pu_nd,lng,lat,is_expire,rent_state,ui_nd,ai_id,ai_money,ai_effect,ui_tel,magnetic_state,gov_num,stime,up_orderid FROM "+TABLENAME2+" ORDER BY id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Pay_month_park>(Pay_month_park.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Pay_month_park>();
        }
    }

    //根据主键查询
    public List<Pay_month_park> selectGtKey(long id) {
        return selectGtKey(id, TABLENAME);
    }

    //根据主键查询
    public List<Pay_month_park> selectGtKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,pi_id,ui_id,car_code,money,pp_state,ctime,utime,month_num,pay_source,my_order,other_order,pay_type,pp_versioncode,phone_type,order_type,start_time,end_time,month_info,allege_state,area_code,is_del,upc_id,discount_money,note,discount_name,discount_type,final_time,address_name,base_rate,outtime_rate,outtime_money,outtime_time,outtime_minute,outtime_start_price,outtime_start_minute,outtime_charge_money,outtime_charge_minute,outtime_other_order,outtime_paytime,outtime_paystate,permit_time,is_24hours,is_open,open_time,is_cash,park_type,scan_time,arrive_time,temp_money,pi_name,is_over,cancel_state,pu_id,pu_nd,lng,lat,is_expire,rent_state,ui_nd,ai_id,ai_money,ai_effect,ui_tel,magnetic_state,gov_num,stime,up_orderid FROM "+TABLENAME2+" WHERE id>:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Pay_month_park>(Pay_month_park.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Pay_month_park>();
        }
    }

    //根据主键查询
    public Pay_month_park selectByKey(long id) {
        return selectByKey(id, TABLENAME);
    }

    //根据主键查询
    public Pay_month_park selectByKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,pi_id,ui_id,car_code,money,pp_state,ctime,utime,month_num,pay_source,my_order,other_order,pay_type,pp_versioncode,phone_type,order_type,start_time,end_time,month_info,allege_state,area_code,is_del,upc_id,discount_money,note,discount_name,discount_type,final_time,address_name,base_rate,outtime_rate,outtime_money,outtime_time,outtime_minute,outtime_start_price,outtime_start_minute,outtime_charge_money,outtime_charge_minute,outtime_other_order,outtime_paytime,outtime_paystate,permit_time,is_24hours,is_open,open_time,is_cash,park_type,scan_time,arrive_time,temp_money,pi_name,is_over,cancel_state,pu_id,pu_nd,lng,lat,is_expire,rent_state,ui_nd,ai_id,ai_money,ai_effect,ui_tel,magnetic_state,gov_num,stime,up_orderid FROM "+TABLENAME2+" WHERE id=:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            List<Pay_month_park> list =  _np.query(sql, param, new BeanPropertyRowMapper<Pay_month_park>(Pay_month_park.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey id="+id,e);
            return null;
        }
    }

    //所有数据总数
    public int count() {
        return count(TABLENAME);
    }

    //所有数据总数
    public int count(String TABLENAME2) {
        String sql;
        try{
            sql="SELECT COUNT(*) FROM "+TABLENAME2+"";
            return _np.getJdbcOperations().queryForObject(sql,Integer.class);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("count",e);
            return 0;
        }
    }

    //分页查询
    public List<Pay_month_park> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Pay_month_park> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT id,pi_id,ui_id,car_code,money,pp_state,ctime,utime,month_num,pay_source,my_order,other_order,pay_type,pp_versioncode,phone_type,order_type,start_time,end_time,month_info,allege_state,area_code,is_del,upc_id,discount_money,note,discount_name,discount_type,final_time,address_name,base_rate,outtime_rate,outtime_money,outtime_time,outtime_minute,outtime_start_price,outtime_start_minute,outtime_charge_money,outtime_charge_minute,outtime_other_order,outtime_paytime,outtime_paystate,permit_time,is_24hours,is_open,open_time,is_cash,park_type,scan_time,arrive_time,temp_money,pi_name,is_over,cancel_state,pu_id,pu_nd,lng,lat,is_expire,rent_state,ui_nd,ai_id,ai_money,ai_effect,ui_tel,magnetic_state,gov_num,stime,up_orderid FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Pay_month_park>(Pay_month_park.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Pay_month_park>();
        }
    }

    //修改数据
    public int updateByKey(Pay_month_park bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Pay_month_park bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=:pi_id,ui_id=:ui_id,car_code=:car_code,money=:money,pp_state=:pp_state,ctime=:ctime,utime=:utime,month_num=:month_num,pay_source=:pay_source,my_order=:my_order,other_order=:other_order,pay_type=:pay_type,pp_versioncode=:pp_versioncode,phone_type=:phone_type,order_type=:order_type,start_time=:start_time,end_time=:end_time,month_info=:month_info,allege_state=:allege_state,area_code=:area_code,is_del=:is_del,upc_id=:upc_id,discount_money=:discount_money,note=:note,discount_name=:discount_name,discount_type=:discount_type,final_time=:final_time,address_name=:address_name,base_rate=:base_rate,outtime_rate=:outtime_rate,outtime_money=:outtime_money,outtime_time=:outtime_time,outtime_minute=:outtime_minute,outtime_start_price=:outtime_start_price,outtime_start_minute=:outtime_start_minute,outtime_charge_money=:outtime_charge_money,outtime_charge_minute=:outtime_charge_minute,outtime_other_order=:outtime_other_order,outtime_paytime=:outtime_paytime,outtime_paystate=:outtime_paystate,permit_time=:permit_time,is_24hours=:is_24hours,is_open=:is_open,open_time=:open_time,is_cash=:is_cash,park_type=:park_type,scan_time=:scan_time,arrive_time=:arrive_time,temp_money=:temp_money,pi_name=:pi_name,is_over=:is_over,cancel_state=:cancel_state,pu_id=:pu_id,pu_nd=:pu_nd,lng=:lng,lat=:lat,is_expire=:is_expire,rent_state=:rent_state,ui_nd=:ui_nd,ai_id=:ai_id,ai_money=:ai_money,ai_effect=:ai_effect,ui_tel=:ui_tel,magnetic_state=:magnetic_state,gov_num=:gov_num,stime=:stime,up_orderid=:up_orderid WHERE id=:id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Pay_month_park> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Pay_month_park> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=?,ui_id=?,car_code=?,money=?,pp_state=?,ctime=?,utime=?,month_num=?,pay_source=?,my_order=?,other_order=?,pay_type=?,pp_versioncode=?,phone_type=?,order_type=?,start_time=?,end_time=?,month_info=?,allege_state=?,area_code=?,is_del=?,upc_id=?,discount_money=?,note=?,discount_name=?,discount_type=?,final_time=?,address_name=?,base_rate=?,outtime_rate=?,outtime_money=?,outtime_time=?,outtime_minute=?,outtime_start_price=?,outtime_start_minute=?,outtime_charge_money=?,outtime_charge_minute=?,outtime_other_order=?,outtime_paytime=?,outtime_paystate=?,permit_time=?,is_24hours=?,is_open=?,open_time=?,is_cash=?,park_type=?,scan_time=?,arrive_time=?,temp_money=?,pi_name=?,is_over=?,cancel_state=?,pu_id=?,pu_nd=?,lng=?,lat=?,is_expire=?,rent_state=?,ui_nd=?,ai_id=?,ai_money=?,ai_effect=?,ui_tel=?,magnetic_state=?,gov_num=?,stime=?,up_orderid=? WHERE id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Pay_month_park bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setLong(2, bean.ui_id);
                    ps.setString(3, bean.car_code);
                    ps.setInt(4, bean.money);
                    ps.setInt(5, bean.pp_state);
                    ps.setTimestamp(6, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(7, new Timestamp(bean.utime.getTime()));
                    ps.setInt(8, bean.month_num);
                    ps.setInt(9, bean.pay_source);
                    ps.setString(10, bean.my_order);
                    ps.setString(11, bean.other_order);
                    ps.setInt(12, bean.pay_type);
                    ps.setString(13, bean.pp_versioncode);
                    ps.setInt(14, bean.phone_type);
                    ps.setInt(15, bean.order_type);
                    ps.setTimestamp(16, new Timestamp(bean.start_time.getTime()));
                    ps.setTimestamp(17, new Timestamp(bean.end_time.getTime()));
                    ps.setString(18, bean.month_info);
                    ps.setInt(19, bean.allege_state);
                    ps.setString(20, bean.area_code);
                    ps.setInt(21, bean.is_del);
                    ps.setLong(22, bean.upc_id);
                    ps.setLong(23, bean.discount_money);
                    ps.setString(24, bean.note);
                    ps.setString(25, bean.discount_name);
                    ps.setInt(26, bean.discount_type);
                    ps.setInt(27, bean.final_time);
                    ps.setString(28, bean.address_name);
                    ps.setString(29, bean.base_rate);
                    ps.setString(30, bean.outtime_rate);
                    ps.setInt(31, bean.outtime_money);
                    ps.setTimestamp(32, new Timestamp(bean.outtime_time.getTime()));
                    ps.setInt(33, bean.outtime_minute);
                    ps.setInt(34, bean.outtime_start_price);
                    ps.setInt(35, bean.outtime_start_minute);
                    ps.setInt(36, bean.outtime_charge_money);
                    ps.setInt(37, bean.outtime_charge_minute);
                    ps.setString(38, bean.outtime_other_order);
                    ps.setTimestamp(39, new Timestamp(bean.outtime_paytime.getTime()));
                    ps.setInt(40, bean.outtime_paystate);
                    ps.setString(41, bean.permit_time);
                    ps.setInt(42, bean.is_24hours);
                    ps.setInt(43, bean.is_open);
                    ps.setTimestamp(44, new Timestamp(bean.open_time.getTime()));
                    ps.setInt(45, bean.is_cash);
                    ps.setInt(46, bean.park_type);
                    ps.setTimestamp(47, new Timestamp(bean.scan_time.getTime()));
                    ps.setTimestamp(48, new Timestamp(bean.arrive_time.getTime()));
                    ps.setLong(49, bean.temp_money);
                    ps.setString(50, bean.pi_name);
                    ps.setInt(51, bean.is_over);
                    ps.setInt(52, bean.cancel_state);
                    ps.setLong(53, bean.pu_id);
                    ps.setString(54, bean.pu_nd);
                    ps.setDouble(55, bean.lng);
                    ps.setDouble(56, bean.lat);
                    ps.setInt(57, bean.is_expire);
                    ps.setInt(58, bean.rent_state);
                    ps.setString(59, bean.ui_nd);
                    ps.setLong(60, bean.ai_id);
                    ps.setInt(61, bean.ai_money);
                    ps.setInt(62, bean.ai_effect);
                    ps.setString(63, bean.ui_tel);
                    ps.setInt(64, bean.magnetic_state);
                    ps.setString(65, bean.gov_num);
                    ps.setTimestamp(66, new Timestamp(bean.stime.getTime()));
                    ps.setString(67, bean.up_orderid);
                    ps.setLong(68, bean.id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long id) throws SQLException{
        return deleteByKey(id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE id=:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            return _np.update(sql, param);
        }catch(Exception e){
            log.error("deleteByKey", e);
            throw new SQLException("deleteByKey is error", e);
        }
    }

    //批量删除数据
    public int[] deleteByKey(long[] keys) throws SQLException{
        return deleteByKey(keys, TABLENAME);
    }

    //批量删除数据
    public int[] deleteByKey(final long[] keys, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return keys.length;
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setLong(1 , keys[i]);
                }
            });
        }catch(Exception e){
            log.error("int[] deleteByKey", e);
            throw new SQLException("deleteByKey is error", e);
        }
    }

    //创建表
    public void createTable(String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "CREATE TABLE IF NOT EXISTS `${TABLENAME}` (" +
                 "	`id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    支付停车场主键ID'," +
                 "	`ui_id`  BIGINT(20) COMMENT '//bigint(20)    用户ID'," +
                 "	`car_code`  VARCHAR(60) COMMENT '//varchar(60)    用户支付车牌号'," +
                 "	`money`  INT(11) COMMENT '//int(11)    支付金额（单位分）'," +
                 "	`pp_state`  INT(11) COMMENT '//int(11)    支付状态0:未支付1：已经支付'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    下单时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新支付状态时间'," +
                 "	`month_num`  INT(11) COMMENT '//int(11)    包月租凭月数'," +
                 "	`pay_source`  INT(11) COMMENT '//int(11)    支付类型1:支付宝2：微信3：银联4：钱包5:龙支付6:ETC快捷支付'," +
                 "	`my_order`  VARCHAR(80) COMMENT '//varchar(80)    我们的订单号'," +
                 "	`other_order`  VARCHAR(80) COMMENT '//varchar(80)    第三方的支付单号'," +
                 "	`pay_type`  INT(11) COMMENT '//int(11)    支付类型0:手动输入密码支付1：快捷支付（服务器可以请求第三方直接扣款）'," +
                 "	`pp_versioncode`  VARCHAR(30) COMMENT '//varchar(30)    当前APPSDK版本号（内部升级版本代号）'," +
                 "	`phone_type`  INT(11) COMMENT '//int(11)    手机类型0:android1：IOS'," +
                 "	`order_type`  INT(11) COMMENT '//int(11)    下单类型0:普通下单1：预约下单2：租赁包月订单'," +
                 "	`start_time`  DATETIME COMMENT '//datetime    启始时间'," +
                 "	`end_time`  DATETIME COMMENT '//datetime    到期时间'," +
                 "	`month_info`  TINYTEXT COMMENT '//varchar(255)    包月提示信息'," +
                 "	`allege_state`  INT(11) COMMENT '//int(11)    申述状态0:未申述1：申述中2：申述失败3：申述成功'," +
                 "	`area_code`  VARCHAR(60) COMMENT '//varchar(60)    省市县编号'," +
                 "	`is_del`  INT(11) COMMENT '//int(11)    删除状态0:正常1：假删除'," +
                 "	`upc_id`  BIGINT(20) COMMENT '//bigint(20)    用户优惠券表主键ID'," +
                 "	`discount_money`  BIGINT(20) COMMENT '//bigint(20)    抵扣优惠金额（单位分）'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`discount_name`  VARCHAR(150) COMMENT '//varchar(150)    抵扣优惠券名称'," +
                 "	`discount_type`  INT(11) COMMENT '//int(11)    优惠券类型0:金额券1：折扣券'," +
                 "	`final_time`  INT(11) COMMENT '//int(11)    结算时计费时长（单位分钟）'," +
                 "	`address_name`  VARCHAR(150) COMMENT '//varchar(150)    停车场地点名称'," +
                 "	`base_rate`  VARCHAR(60) COMMENT '//varchar(60)    基础费率（300元/月）'," +
                 "	`outtime_rate`  VARCHAR(100) COMMENT '//varchar(100)    超时费率(首停2小时3元，之后1元/小时)'," +
                 "	`outtime_money`  INT(11) COMMENT '//int(11)    超时金额'," +
                 "	`outtime_time`  DATETIME COMMENT '//datetime    超时结算时间（2016-08-1012：00：00）'," +
                 "	`outtime_minute`  INT(11) COMMENT '//int(11)    超时时长（单位分钟）'," +
                 "	`outtime_start_price`  INT(11) COMMENT '//int(11)    超时起步价'," +
                 "	`outtime_start_minute`  INT(11) COMMENT '//int(11)    超时起步量纲时长（单位分钟）'," +
                 "	`outtime_charge_money`  INT(11) COMMENT '//int(11)    超时计费价格（单位分）'," +
                 "	`outtime_charge_minute`  INT(11) COMMENT '//int(11)    超时计费量纲时长（单位分钟）'," +
                 "	`outtime_other_order`  VARCHAR(80) COMMENT '//varchar(80)    超时第三方支付订单号'," +
                 "	`outtime_paytime`  DATETIME COMMENT '//datetime    超时缴费时间'," +
                 "	`outtime_paystate`  INT(11) COMMENT '//int(11)    超时缴费状态0:未缴费1：已经缴费'," +
                 "	`permit_time`  VARCHAR(60) COMMENT '//varchar(60)    准入时间段（17:00-08:30）'," +
                 "	`is_24hours`  INT(11) COMMENT '//int(11)    是否是24小时包月0:不是24小时包月1：是24小时包月'," +
                 "	`is_open`  INT(11) COMMENT '//int(11)    是否开闸0:未开闸1：已开闸'," +
                 "	`open_time`  DATETIME COMMENT '//datetime    开闸时间'," +
                 "	`is_cash`  INT(11) COMMENT '//int(11)    是否现金支付0：在线支付1：现金支付'," +
                 "	`park_type`  INT(11) COMMENT '//int(11)    停车场类型0：道闸停车场1：占道车场2：露天立体车库停车场'," +
                 "	`scan_time`  DATETIME COMMENT '//datetime    是否可以停车缴费时间（摄像头是否扫描到了：仅限道闸停车使用）'," +
                 "	`arrive_time`  DATETIME COMMENT '//datetime    该次入库的到达时间'," +
                 "	`temp_money`  BIGINT(20) COMMENT '//bigint(20)    临停费用累计'," +
                 "	`pi_name`  VARCHAR(100) COMMENT '//varchar(100)    停车场名称'," +
                 "	`is_over`  INT(11) COMMENT '//int(11)    订单是否完成或者逃逸(0:没有完成1：完成2：车辆逃逸3：未交费)'," +
                 "	`cancel_state`  INT(11) COMMENT '//int(11)    订单关闭状态0:没有关闭1：已经关闭2：订单异常'," +
                 "	`pu_id`  BIGINT(20) COMMENT '//bigint(20)    商户主键ID'," +
                 "	`pu_nd`  VARCHAR(100) COMMENT '//varchar(100)    商户编号'," +
                 "	`lng`  DOUBLE COMMENT '//double    场地经度'," +
                 "	`lat`  DOUBLE COMMENT '//double    场地纬度'," +
                 "	`is_expire`  INT(11) COMMENT '//int(11)    是否已到期（0：没有到期1：已经到期）'," +
                 "	`rent_state`  INT(11) COMMENT '//int(11)    租赁状态0:待支付1：租赁中2：租赁成功3：租赁失败--解绑租赁金额'," +
                 "	`ui_nd`  VARCHAR(100) COMMENT '//varchar(100)    用户唯一标识uuid'," +
                 "	`ai_id`  BIGINT(20) COMMENT '//bigint(20)    活动ID'," +
                 "	`ai_money`  INT(11) COMMENT '//int(11)    活动优惠金额（单位分）'," +
                 "	`ai_effect`  INT(11) COMMENT '//int(11)    活动优惠是否生效（0：没有1：生效）'," +
                 "	`ui_tel`  VARCHAR(100) COMMENT '//varchar(100)    用户电话号码'," +
                 "	`magnetic_state`  INT(11) NOT NULL COMMENT '//int(11)    智能磁场入库出库状态（1：已入库2：已出库3：车辆逃逸4：其它故障）'," +
                 "	`gov_num`  VARCHAR(60) NOT NULL COMMENT '//varchar(60)    智能磁场车位编号（政府部门统一分配）'," +
                 "	`stime`  DATETIME COMMENT '//datetime    服务器端接收数据创建时间'," +
                 "	`up_orderid`  VARCHAR(80) COMMENT '//varchar(80)    第三方支付user_pay中的流水单号(订单支付、欠费订单补交)'," +
                 "	PRIMARY KEY (`id`)" +
                 ") ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;";
            Map<String,String> params = new HashMap<String,String>();
            params.put("TABLENAME", TABLENAME2);
            sql  = EasyTemplate.make(sql, params);
            _np.getJdbcOperations().execute(sql);
        }catch(Exception e){
            log.error("createTable",e);
            throw new SQLException("createTable is error", e);
        }
    }

    //清空表
    public void truncate() throws SQLException{
        truncate(TABLENAME);
    }

    //清空表
    public void truncate(String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql="TRUNCATE TABLE "+TABLENAME2+"";
            _np.getJdbcOperations().execute(sql);
        }catch(Exception e){
            log.error("truncate",e);
            throw new SQLException("truncate is error", e);
        }
    }

    //修复表
    public void repair(){
        repair(TABLENAME);
    }

    //修复表
    public void repair(String TABLENAME2){
        try{
            String sql;
            sql="REPAIR TABLE "+TABLENAME2+"";
            _np.getJdbcOperations().execute(sql);
        }catch(Exception e){
            log.error("repair",e);
        }
    }

    //优化表
    public void optimize(){
        optimize(TABLENAME);
    }

    //优化表
    public void optimize(String TABLENAME2){
        try{
            String sql;
            sql="OPTIMIZE TABLE "+TABLENAME2+"";
            _np.getJdbcOperations().execute(sql);
        }catch(Exception e){
            log.error("optimize",e);
        }
    }

    //执行sql
    public void execute(String sql) throws SQLException{
        try{
            _np.getJdbcOperations().execute(sql);
        }catch(Exception e){
            log.error("execute",e);
            throw new SQLException("execute is error", e);
        }
    }

}
