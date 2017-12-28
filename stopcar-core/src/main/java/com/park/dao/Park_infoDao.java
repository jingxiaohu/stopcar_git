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

//park_info

@Repository("park_infoDao")
public class Park_infoDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Park_infoDao.class);



    private  String TABLE = "park_info";

    private  String TABLENAME = "park_info";

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


    private  String[] carrays ={"pi_id","area_code","pi_name","address_name","lng","lat","linkman_name","linkman_tel","copy_linkman_name","copy_linkman_tel","pi_phone","department","enter_num","exit_num","hlc_enter_num","hlc_exit_num","enter_camera_num","exit_camera_num","camera_info","ctime","utime","park_type","is_expect","expect_money","allow_revoke_time","allow_expect_time","timeout_info","rent_info","month_price","is_rent","money","loginname","password","mac","is_fault","pi_state","roadside_type","pu_id","pu_nd","note","carport_yet","carport_space","carport_total","moth_car_num","moth_car_num_space","time_car_num","time_car_num_space","expect_car_num","upload_source","admin_id","pda_permit_time","special_ip","token","hardware_type","relet_state","ack_time"};
    private  String coulmns ="pi_id,area_code,pi_name,address_name,lng,lat,linkman_name,linkman_tel,copy_linkman_name,copy_linkman_tel,pi_phone,department,enter_num,exit_num,hlc_enter_num,hlc_exit_num,enter_camera_num,exit_camera_num,camera_info,ctime,utime,park_type,is_expect,expect_money,allow_revoke_time,allow_expect_time,timeout_info,rent_info,month_price,is_rent,money,loginname,password,mac,is_fault,pi_state,roadside_type,pu_id,pu_nd,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,upload_source,admin_id,pda_permit_time,special_ip,token,hardware_type,relet_state,ack_time";
    private  String coulmns2 ="area_code,pi_name,address_name,lng,lat,linkman_name,linkman_tel,copy_linkman_name,copy_linkman_tel,pi_phone,department,enter_num,exit_num,hlc_enter_num,hlc_exit_num,enter_camera_num,exit_camera_num,camera_info,ctime,utime,park_type,is_expect,expect_money,allow_revoke_time,allow_expect_time,timeout_info,rent_info,month_price,is_rent,money,loginname,password,mac,is_fault,pi_state,roadside_type,pu_id,pu_nd,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,upload_source,admin_id,pda_permit_time,special_ip,token,hardware_type,relet_state,ack_time";

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
    public int insert(Park_info bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Park_info bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (area_code,pi_name,address_name,lng,lat,linkman_name,linkman_tel,copy_linkman_name,copy_linkman_tel,pi_phone,department,enter_num,exit_num,hlc_enter_num,hlc_exit_num,enter_camera_num,exit_camera_num,camera_info,ctime,utime,park_type,is_expect,expect_money,allow_revoke_time,allow_expect_time,timeout_info,rent_info,month_price,is_rent,money,loginname,password,mac,is_fault,pi_state,roadside_type,pu_id,pu_nd,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,upload_source,admin_id,pda_permit_time,special_ip,token,hardware_type,relet_state,ack_time) VALUES (:area_code,:pi_name,:address_name,:lng,:lat,:linkman_name,:linkman_tel,:copy_linkman_name,:copy_linkman_tel,:pi_phone,:department,:enter_num,:exit_num,:hlc_enter_num,:hlc_exit_num,:enter_camera_num,:exit_camera_num,:camera_info,:ctime,:utime,:park_type,:is_expect,:expect_money,:allow_revoke_time,:allow_expect_time,:timeout_info,:rent_info,:month_price,:is_rent,:money,:loginname,:password,:mac,:is_fault,:pi_state,:roadside_type,:pu_id,:pu_nd,:note,:carport_yet,:carport_space,:carport_total,:moth_car_num,:moth_car_num_space,:time_car_num,:time_car_num_space,:expect_car_num,:upload_source,:admin_id,:pda_permit_time,:special_ip,:token,:hardware_type,:relet_state,:ack_time)";
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
    public int insert_primarykey(Park_info bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Park_info bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,area_code,pi_name,address_name,lng,lat,linkman_name,linkman_tel,copy_linkman_name,copy_linkman_tel,pi_phone,department,enter_num,exit_num,hlc_enter_num,hlc_exit_num,enter_camera_num,exit_camera_num,camera_info,ctime,utime,park_type,is_expect,expect_money,allow_revoke_time,allow_expect_time,timeout_info,rent_info,month_price,is_rent,money,loginname,password,mac,is_fault,pi_state,roadside_type,pu_id,pu_nd,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,upload_source,admin_id,pda_permit_time,special_ip,token,hardware_type,relet_state,ack_time) VALUES (:pi_id,:area_code,:pi_name,:address_name,:lng,:lat,:linkman_name,:linkman_tel,:copy_linkman_name,:copy_linkman_tel,:pi_phone,:department,:enter_num,:exit_num,:hlc_enter_num,:hlc_exit_num,:enter_camera_num,:exit_camera_num,:camera_info,:ctime,:utime,:park_type,:is_expect,:expect_money,:allow_revoke_time,:allow_expect_time,:timeout_info,:rent_info,:month_price,:is_rent,:money,:loginname,:password,:mac,:is_fault,:pi_state,:roadside_type,:pu_id,:pu_nd,:note,:carport_yet,:carport_space,:carport_total,:moth_car_num,:moth_car_num_space,:time_car_num,:time_car_num_space,:expect_car_num,:upload_source,:admin_id,:pda_permit_time,:special_ip,:token,:hardware_type,:relet_state,:ack_time)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Park_info> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Park_info> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (area_code,pi_name,address_name,lng,lat,linkman_name,linkman_tel,copy_linkman_name,copy_linkman_tel,pi_phone,department,enter_num,exit_num,hlc_enter_num,hlc_exit_num,enter_camera_num,exit_camera_num,camera_info,ctime,utime,park_type,is_expect,expect_money,allow_revoke_time,allow_expect_time,timeout_info,rent_info,month_price,is_rent,money,loginname,password,mac,is_fault,pi_state,roadside_type,pu_id,pu_nd,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,upload_source,admin_id,pda_permit_time,special_ip,token,hardware_type,relet_state,ack_time) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_info bean = beans.get(i);
                    ps.setString(1, bean.area_code);
                    ps.setString(2, bean.pi_name);
                    ps.setString(3, bean.address_name);
                    ps.setDouble(4, bean.lng);
                    ps.setDouble(5, bean.lat);
                    ps.setString(6, bean.linkman_name);
                    ps.setString(7, bean.linkman_tel);
                    ps.setString(8, bean.copy_linkman_name);
                    ps.setString(9, bean.copy_linkman_tel);
                    ps.setString(10, bean.pi_phone);
                    ps.setString(11, bean.department);
                    ps.setInt(12, bean.enter_num);
                    ps.setInt(13, bean.exit_num);
                    ps.setInt(14, bean.hlc_enter_num);
                    ps.setInt(15, bean.hlc_exit_num);
                    ps.setInt(16, bean.enter_camera_num);
                    ps.setInt(17, bean.exit_camera_num);
                    ps.setString(18, bean.camera_info);
                    ps.setTimestamp(19, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(20, new Timestamp(bean.utime.getTime()));
                    ps.setInt(21, bean.park_type);
                    ps.setInt(22, bean.is_expect);
                    ps.setInt(23, bean.expect_money);
                    ps.setInt(24, bean.allow_revoke_time);
                    ps.setInt(25, bean.allow_expect_time);
                    ps.setString(26, bean.timeout_info);
                    ps.setString(27, bean.rent_info);
                    ps.setInt(28, bean.month_price);
                    ps.setInt(29, bean.is_rent);
                    ps.setInt(30, bean.money);
                    ps.setString(31, bean.loginname);
                    ps.setString(32, bean.password);
                    ps.setString(33, bean.mac);
                    ps.setInt(34, bean.is_fault);
                    ps.setInt(35, bean.pi_state);
                    ps.setInt(36, bean.roadside_type);
                    ps.setLong(37, bean.pu_id);
                    ps.setString(38, bean.pu_nd);
                    ps.setString(39, bean.note);
                    ps.setInt(40, bean.carport_yet);
                    ps.setInt(41, bean.carport_space);
                    ps.setInt(42, bean.carport_total);
                    ps.setInt(43, bean.moth_car_num);
                    ps.setInt(44, bean.moth_car_num_space);
                    ps.setInt(45, bean.time_car_num);
                    ps.setInt(46, bean.time_car_num_space);
                    ps.setInt(47, bean.expect_car_num);
                    ps.setInt(48, bean.upload_source);
                    ps.setLong(49, bean.admin_id);
                    ps.setString(50, bean.pda_permit_time);
                    ps.setString(51, bean.special_ip);
                    ps.setString(52, bean.token);
                    ps.setInt(53, bean.hardware_type);
                    ps.setInt(54, bean.relet_state);
                    ps.setString(55, bean.ack_time);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Park_info> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Park_info> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pi_id,area_code,pi_name,address_name,lng,lat,linkman_name,linkman_tel,copy_linkman_name,copy_linkman_tel,pi_phone,department,enter_num,exit_num,hlc_enter_num,hlc_exit_num,enter_camera_num,exit_camera_num,camera_info,ctime,utime,park_type,is_expect,expect_money,allow_revoke_time,allow_expect_time,timeout_info,rent_info,month_price,is_rent,money,loginname,password,mac,is_fault,pi_state,roadside_type,pu_id,pu_nd,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,upload_source,admin_id,pda_permit_time,special_ip,token,hardware_type,relet_state,ack_time FROM "+TABLENAME2+" ORDER BY pi_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_info>(Park_info.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Park_info>();
        }
    }

    //查询最新数据
    public List<Park_info> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Park_info> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pi_id,area_code,pi_name,address_name,lng,lat,linkman_name,linkman_tel,copy_linkman_name,copy_linkman_tel,pi_phone,department,enter_num,exit_num,hlc_enter_num,hlc_exit_num,enter_camera_num,exit_camera_num,camera_info,ctime,utime,park_type,is_expect,expect_money,allow_revoke_time,allow_expect_time,timeout_info,rent_info,month_price,is_rent,money,loginname,password,mac,is_fault,pi_state,roadside_type,pu_id,pu_nd,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,upload_source,admin_id,pda_permit_time,special_ip,token,hardware_type,relet_state,ack_time FROM "+TABLENAME2+" ORDER BY pi_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_info>(Park_info.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Park_info>();
        }
    }

    //根据主键查询
    public List<Park_info> selectGtKey(long pi_id) {
        return selectGtKey(pi_id, TABLENAME);
    }

    //根据主键查询
    public List<Park_info> selectGtKey(long pi_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pi_id,area_code,pi_name,address_name,lng,lat,linkman_name,linkman_tel,copy_linkman_name,copy_linkman_tel,pi_phone,department,enter_num,exit_num,hlc_enter_num,hlc_exit_num,enter_camera_num,exit_camera_num,camera_info,ctime,utime,park_type,is_expect,expect_money,allow_revoke_time,allow_expect_time,timeout_info,rent_info,month_price,is_rent,money,loginname,password,mac,is_fault,pi_state,roadside_type,pu_id,pu_nd,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,upload_source,admin_id,pda_permit_time,special_ip,token,hardware_type,relet_state,ack_time FROM "+TABLENAME2+" WHERE pi_id>:pi_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pi_id", pi_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Park_info>(Park_info.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Park_info>();
        }
    }

    //根据主键查询
    public Park_info selectByKey(long pi_id) {
        return selectByKey(pi_id, TABLENAME);
    }

    //根据主键查询
    public Park_info selectByKey(long pi_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pi_id,area_code,pi_name,address_name,lng,lat,linkman_name,linkman_tel,copy_linkman_name,copy_linkman_tel,pi_phone,department,enter_num,exit_num,hlc_enter_num,hlc_exit_num,enter_camera_num,exit_camera_num,camera_info,ctime,utime,park_type,is_expect,expect_money,allow_revoke_time,allow_expect_time,timeout_info,rent_info,month_price,is_rent,money,loginname,password,mac,is_fault,pi_state,roadside_type,pu_id,pu_nd,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,upload_source,admin_id,pda_permit_time,special_ip,token,hardware_type,relet_state,ack_time FROM "+TABLENAME2+" WHERE pi_id=:pi_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pi_id", pi_id);
            List<Park_info> list =  _np.query(sql, param, new BeanPropertyRowMapper<Park_info>(Park_info.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey pi_id="+pi_id,e);
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
    public List<Park_info> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Park_info> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT pi_id,area_code,pi_name,address_name,lng,lat,linkman_name,linkman_tel,copy_linkman_name,copy_linkman_tel,pi_phone,department,enter_num,exit_num,hlc_enter_num,hlc_exit_num,enter_camera_num,exit_camera_num,camera_info,ctime,utime,park_type,is_expect,expect_money,allow_revoke_time,allow_expect_time,timeout_info,rent_info,month_price,is_rent,money,loginname,password,mac,is_fault,pi_state,roadside_type,pu_id,pu_nd,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,upload_source,admin_id,pda_permit_time,special_ip,token,hardware_type,relet_state,ack_time FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Park_info>(Park_info.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Park_info>();
        }
    }

    //修改数据
    public int updateByKey(Park_info bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Park_info bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET area_code=:area_code,pi_name=:pi_name,address_name=:address_name,lng=:lng,lat=:lat,linkman_name=:linkman_name,linkman_tel=:linkman_tel,copy_linkman_name=:copy_linkman_name,copy_linkman_tel=:copy_linkman_tel,pi_phone=:pi_phone,department=:department,enter_num=:enter_num,exit_num=:exit_num,hlc_enter_num=:hlc_enter_num,hlc_exit_num=:hlc_exit_num,enter_camera_num=:enter_camera_num,exit_camera_num=:exit_camera_num,camera_info=:camera_info,ctime=:ctime,utime=:utime,park_type=:park_type,is_expect=:is_expect,expect_money=:expect_money,allow_revoke_time=:allow_revoke_time,allow_expect_time=:allow_expect_time,timeout_info=:timeout_info,rent_info=:rent_info,month_price=:month_price,is_rent=:is_rent,money=:money,loginname=:loginname,password=:password,mac=:mac,is_fault=:is_fault,pi_state=:pi_state,roadside_type=:roadside_type,pu_id=:pu_id,pu_nd=:pu_nd,note=:note,carport_yet=:carport_yet,carport_space=:carport_space,carport_total=:carport_total,moth_car_num=:moth_car_num,moth_car_num_space=:moth_car_num_space,time_car_num=:time_car_num,time_car_num_space=:time_car_num_space,expect_car_num=:expect_car_num,upload_source=:upload_source,admin_id=:admin_id,pda_permit_time=:pda_permit_time,special_ip=:special_ip,token=:token,hardware_type=:hardware_type,relet_state=:relet_state,ack_time=:ack_time WHERE pi_id=:pi_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_info> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_info> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET area_code=?,pi_name=?,address_name=?,lng=?,lat=?,linkman_name=?,linkman_tel=?,copy_linkman_name=?,copy_linkman_tel=?,pi_phone=?,department=?,enter_num=?,exit_num=?,hlc_enter_num=?,hlc_exit_num=?,enter_camera_num=?,exit_camera_num=?,camera_info=?,ctime=?,utime=?,park_type=?,is_expect=?,expect_money=?,allow_revoke_time=?,allow_expect_time=?,timeout_info=?,rent_info=?,month_price=?,is_rent=?,money=?,loginname=?,password=?,mac=?,is_fault=?,pi_state=?,roadside_type=?,pu_id=?,pu_nd=?,note=?,carport_yet=?,carport_space=?,carport_total=?,moth_car_num=?,moth_car_num_space=?,time_car_num=?,time_car_num_space=?,expect_car_num=?,upload_source=?,admin_id=?,pda_permit_time=?,special_ip=?,token=?,hardware_type=?,relet_state=?,ack_time=? WHERE pi_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_info bean = beans.get(i);
                    ps.setString(1, bean.area_code);
                    ps.setString(2, bean.pi_name);
                    ps.setString(3, bean.address_name);
                    ps.setDouble(4, bean.lng);
                    ps.setDouble(5, bean.lat);
                    ps.setString(6, bean.linkman_name);
                    ps.setString(7, bean.linkman_tel);
                    ps.setString(8, bean.copy_linkman_name);
                    ps.setString(9, bean.copy_linkman_tel);
                    ps.setString(10, bean.pi_phone);
                    ps.setString(11, bean.department);
                    ps.setInt(12, bean.enter_num);
                    ps.setInt(13, bean.exit_num);
                    ps.setInt(14, bean.hlc_enter_num);
                    ps.setInt(15, bean.hlc_exit_num);
                    ps.setInt(16, bean.enter_camera_num);
                    ps.setInt(17, bean.exit_camera_num);
                    ps.setString(18, bean.camera_info);
                    ps.setTimestamp(19, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(20, new Timestamp(bean.utime.getTime()));
                    ps.setInt(21, bean.park_type);
                    ps.setInt(22, bean.is_expect);
                    ps.setInt(23, bean.expect_money);
                    ps.setInt(24, bean.allow_revoke_time);
                    ps.setInt(25, bean.allow_expect_time);
                    ps.setString(26, bean.timeout_info);
                    ps.setString(27, bean.rent_info);
                    ps.setInt(28, bean.month_price);
                    ps.setInt(29, bean.is_rent);
                    ps.setInt(30, bean.money);
                    ps.setString(31, bean.loginname);
                    ps.setString(32, bean.password);
                    ps.setString(33, bean.mac);
                    ps.setInt(34, bean.is_fault);
                    ps.setInt(35, bean.pi_state);
                    ps.setInt(36, bean.roadside_type);
                    ps.setLong(37, bean.pu_id);
                    ps.setString(38, bean.pu_nd);
                    ps.setString(39, bean.note);
                    ps.setInt(40, bean.carport_yet);
                    ps.setInt(41, bean.carport_space);
                    ps.setInt(42, bean.carport_total);
                    ps.setInt(43, bean.moth_car_num);
                    ps.setInt(44, bean.moth_car_num_space);
                    ps.setInt(45, bean.time_car_num);
                    ps.setInt(46, bean.time_car_num_space);
                    ps.setInt(47, bean.expect_car_num);
                    ps.setInt(48, bean.upload_source);
                    ps.setLong(49, bean.admin_id);
                    ps.setString(50, bean.pda_permit_time);
                    ps.setString(51, bean.special_ip);
                    ps.setString(52, bean.token);
                    ps.setInt(53, bean.hardware_type);
                    ps.setInt(54, bean.relet_state);
                    ps.setString(55, bean.ack_time);
                    ps.setLong(56, bean.pi_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long pi_id) throws SQLException{
        return deleteByKey(pi_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long pi_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE pi_id=:pi_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pi_id", pi_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE pi_id=?";
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
                 "	`pi_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`area_code`  VARCHAR(60) COMMENT '//varchar(60)    省市县编号'," +
                 "	`pi_name`  VARCHAR(150) COMMENT '//varchar(150)    场地名称'," +
                 "	`address_name`  VARCHAR(150) COMMENT '//varchar(150)    场地地理位置名称'," +
                 "	`lng`  DOUBLE COMMENT '//double    场地经度'," +
                 "	`lat`  DOUBLE COMMENT '//double    场地纬度'," +
                 "	`linkman_name`  VARCHAR(30) COMMENT '//varchar(30)    场地联系人姓名'," +
                 "	`linkman_tel`  VARCHAR(11) COMMENT '//varchar(11)    场地联系人手机'," +
                 "	`copy_linkman_name`  VARCHAR(30) COMMENT '//varchar(30)    备用联系人姓名'," +
                 "	`copy_linkman_tel`  VARCHAR(11) COMMENT '//varchar(11)    备用联系人手机'," +
                 "	`pi_phone`  VARCHAR(20) COMMENT '//varchar(20)    场地座机号'," +
                 "	`department`  VARCHAR(150) COMMENT '//varchar(150)    负责单位'," +
                 "	`enter_num`  INT(11) COMMENT '//int(11)    场地口入数量'," +
                 "	`exit_num`  INT(11) COMMENT '//int(11)    场地出口数量'," +
                 "	`hlc_enter_num`  INT(11) COMMENT '//int(11)    场地入口道闸数量'," +
                 "	`hlc_exit_num`  INT(11) COMMENT '//int(11)    场地出口道闸数'," +
                 "	`enter_camera_num`  INT(11) COMMENT '//int(11)    场地入口摄像头数'," +
                 "	`exit_camera_num`  INT(11) COMMENT '//int(11)    场地出口摄像头数'," +
                 "	`camera_info`  VARCHAR(150) COMMENT '//varchar(150)    场地摄像头型号'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    '," +
                 "	`utime`  DATETIME COMMENT '//datetime    '," +
                 "	`park_type`  INT(11) COMMENT '//int(11)    停车场类型0：道闸停车场1：占道车场2：露天立体车库停车场'," +
                 "	`is_expect`  INT(11) COMMENT '//int(11)    是否开启预约0:不开启1：开启'," +
                 "	`expect_money`  INT(11) COMMENT '//int(11)    每小时预约费用（单位分）'," +
                 "	`allow_revoke_time`  INT(11) COMMENT '//int(11)    允许预约撤单时间(单位分钟)'," +
                 "	`allow_expect_time`  INT(11) COMMENT '//int(11)    允许预约时间最大时长(单位分钟)'," +
                 "	`timeout_info`  VARCHAR(150) COMMENT '//varchar(150)    计费信息首停2小时5元，之后每小时2元'," +
                 "	`rent_info`  VARCHAR(150) COMMENT '//varchar(150)    租赁计费信息准入时段18:00-08:00，300元/月'," +
                 "	`month_price`  INT(11) COMMENT '//int(11)    租赁包月价格'," +
                 "	`is_rent`  INT(11) COMMENT '//int(11)    是否开启租赁0:未开启1：已经开启'," +
                 "	`money`  INT(11) COMMENT '//int(11)    蓝牌小汽车起步价'," +
                 "	`loginname`  VARCHAR(60) COMMENT '//varchar(60)    露天停车场帐号'," +
                 "	`password`  VARCHAR(60) COMMENT '//varchar(60)    露天停车场密码'," +
                 "	`mac`  TINYTEXT COMMENT '//varchar(255)    露天停车场PDAMAC（逗号分割）'," +
                 "	`is_fault`  INT(11) COMMENT '//int(11)    停车场故障0:无故障1：发生故障'," +
                 "	`pi_state`  INT(11) COMMENT '//int(11)    停车场状态（1开启0关闭）'," +
                 "	`roadside_type`  INT(11) COMMENT '//int(11)    占道停车是否按次数收费0:按时间收费1：按次数收费'," +
                 "	`pu_id`  BIGINT(20) COMMENT '//bigint(20)    关联的商户主键ID'," +
                 "	`pu_nd`  VARCHAR(100) COMMENT '//varchar(100)    商户ND（商户编号）'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`carport_yet`  INT(11) COMMENT '//int(11)    临停已停车位'," +
                 "	`carport_space`  INT(11) COMMENT '//int(11)    临停空车位个数'," +
                 "	`carport_total`  INT(11) COMMENT '//int(11)    临停总车位个数'," +
                 "	`moth_car_num`  INT(11) COMMENT '//int(11)    租赁离线包月车位总个数'," +
                 "	`moth_car_num_space`  INT(11) COMMENT '//int(11)    租赁离线剩余车位数'," +
                 "	`time_car_num`  INT(11) COMMENT '//int(11)    时间段包月车位总数'," +
                 "	`time_car_num_space`  INT(11) COMMENT '//int(11)    时间段包月车位剩余个数'," +
                 "	`expect_car_num`  INT(11) COMMENT '//int(11)    已预约车位数'," +
                 "	`upload_source`  INT(11) COMMENT '//int(11)    上传数据来源0:离线道闸上传1：中心管理后台录入2：Android市场人员跑动录入'," +
                 "	`admin_id`  BIGINT(20) COMMENT '//bigint(20)    上传数据后台管理账户ID'," +
                 "	`pda_permit_time`  VARCHAR(100) COMMENT '//varchar(100)    PDA停车场上班时间-下班时间(8:00-21:00)'," +
                 "	`special_ip`  VARCHAR(60) COMMENT '//varchar(60)    道闸专线静态IP'," +
                 "	`token`  VARCHAR(80) COMMENT '//varchar(80)    授权token'," +
                 "	`hardware_type`  INT(11) COMMENT '//int(11)    具有哪些硬件类型（0：未知1：地磁2：地磁+蓝牙车位）'," +
                 "	`relet_state`  INT(11) COMMENT '//int(11)    线上续租（0：关闭1：开启）'," +
                 "	`ack_time`  VARCHAR(30) COMMENT '//varchar(30)    停车场正式开启时间(例如：年月日时分秒2017-09-1918:56:00)'," +
                 "	PRIMARY KEY (`pi_id`)" +
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
