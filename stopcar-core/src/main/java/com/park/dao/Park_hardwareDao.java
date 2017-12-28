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

//park_hardware

@Repository("park_hardwareDao")
public class Park_hardwareDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Park_hardwareDao.class);



    private  String TABLE = "park_hardware";

    private  String TABLENAME = "park_hardware";

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


    private  String[] carrays ={"ph_id","pi_id","area_code","type","ph_mac","ph_licence","park_type","ctime","utime","ph_loginname","ph_password","ph_state","note","token","versioncode"};
    private  String coulmns ="ph_id,pi_id,area_code,type,ph_mac,ph_licence,park_type,ctime,utime,ph_loginname,ph_password,ph_state,note,token,versioncode";
    private  String coulmns2 ="pi_id,area_code,type,ph_mac,ph_licence,park_type,ctime,utime,ph_loginname,ph_password,ph_state,note,token,versioncode";

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
    public int insert(Park_hardware bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Park_hardware bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,area_code,type,ph_mac,ph_licence,park_type,ctime,utime,ph_loginname,ph_password,ph_state,note,token,versioncode) VALUES (:pi_id,:area_code,:type,:ph_mac,:ph_licence,:park_type,:ctime,:utime,:ph_loginname,:ph_password,:ph_state,:note,:token,:versioncode)";
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
    public int insert_primarykey(Park_hardware bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Park_hardware bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (ph_id,pi_id,area_code,type,ph_mac,ph_licence,park_type,ctime,utime,ph_loginname,ph_password,ph_state,note,token,versioncode) VALUES (:ph_id,:pi_id,:area_code,:type,:ph_mac,:ph_licence,:park_type,:ctime,:utime,:ph_loginname,:ph_password,:ph_state,:note,:token,:versioncode)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Park_hardware> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Park_hardware> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,area_code,type,ph_mac,ph_licence,park_type,ctime,utime,ph_loginname,ph_password,ph_state,note,token,versioncode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_hardware bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setString(2, bean.area_code);
                    ps.setInt(3, bean.type);
                    ps.setString(4, bean.ph_mac);
                    ps.setString(5, bean.ph_licence);
                    ps.setInt(6, bean.park_type);
                    ps.setTimestamp(7, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(8, new Timestamp(bean.utime.getTime()));
                    ps.setString(9, bean.ph_loginname);
                    ps.setString(10, bean.ph_password);
                    ps.setInt(11, bean.ph_state);
                    ps.setString(12, bean.note);
                    ps.setString(13, bean.token);
                    ps.setInt(14, bean.versioncode);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Park_hardware> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Park_hardware> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT ph_id,pi_id,area_code,type,ph_mac,ph_licence,park_type,ctime,utime,ph_loginname,ph_password,ph_state,note,token,versioncode FROM "+TABLENAME2+" ORDER BY ph_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_hardware>(Park_hardware.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Park_hardware>();
        }
    }

    //查询最新数据
    public List<Park_hardware> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Park_hardware> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT ph_id,pi_id,area_code,type,ph_mac,ph_licence,park_type,ctime,utime,ph_loginname,ph_password,ph_state,note,token,versioncode FROM "+TABLENAME2+" ORDER BY ph_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_hardware>(Park_hardware.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Park_hardware>();
        }
    }

    //根据主键查询
    public List<Park_hardware> selectGtKey(long ph_id) {
        return selectGtKey(ph_id, TABLENAME);
    }

    //根据主键查询
    public List<Park_hardware> selectGtKey(long ph_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT ph_id,pi_id,area_code,type,ph_mac,ph_licence,park_type,ctime,utime,ph_loginname,ph_password,ph_state,note,token,versioncode FROM "+TABLENAME2+" WHERE ph_id>:ph_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("ph_id", ph_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Park_hardware>(Park_hardware.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Park_hardware>();
        }
    }

    //根据主键查询
    public Park_hardware selectByKey(long ph_id) {
        return selectByKey(ph_id, TABLENAME);
    }

    //根据主键查询
    public Park_hardware selectByKey(long ph_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT ph_id,pi_id,area_code,type,ph_mac,ph_licence,park_type,ctime,utime,ph_loginname,ph_password,ph_state,note,token,versioncode FROM "+TABLENAME2+" WHERE ph_id=:ph_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("ph_id", ph_id);
            List<Park_hardware> list =  _np.query(sql, param, new BeanPropertyRowMapper<Park_hardware>(Park_hardware.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey ph_id="+ph_id,e);
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
    public List<Park_hardware> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Park_hardware> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT ph_id,pi_id,area_code,type,ph_mac,ph_licence,park_type,ctime,utime,ph_loginname,ph_password,ph_state,note,token,versioncode FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Park_hardware>(Park_hardware.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Park_hardware>();
        }
    }

    //修改数据
    public int updateByKey(Park_hardware bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Park_hardware bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=:pi_id,area_code=:area_code,type=:type,ph_mac=:ph_mac,ph_licence=:ph_licence,park_type=:park_type,ctime=:ctime,utime=:utime,ph_loginname=:ph_loginname,ph_password=:ph_password,ph_state=:ph_state,note=:note,token=:token,versioncode=:versioncode WHERE ph_id=:ph_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_hardware> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_hardware> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=?,area_code=?,type=?,ph_mac=?,ph_licence=?,park_type=?,ctime=?,utime=?,ph_loginname=?,ph_password=?,ph_state=?,note=?,token=?,versioncode=? WHERE ph_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_hardware bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setString(2, bean.area_code);
                    ps.setInt(3, bean.type);
                    ps.setString(4, bean.ph_mac);
                    ps.setString(5, bean.ph_licence);
                    ps.setInt(6, bean.park_type);
                    ps.setTimestamp(7, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(8, new Timestamp(bean.utime.getTime()));
                    ps.setString(9, bean.ph_loginname);
                    ps.setString(10, bean.ph_password);
                    ps.setInt(11, bean.ph_state);
                    ps.setString(12, bean.note);
                    ps.setString(13, bean.token);
                    ps.setInt(14, bean.versioncode);
                    ps.setLong(15, bean.ph_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long ph_id) throws SQLException{
        return deleteByKey(ph_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long ph_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE ph_id=:ph_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("ph_id", ph_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE ph_id=?";
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
                 "	`ph_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场主键ID'," +
                 "	`area_code`  VARCHAR(30) COMMENT '//varchar(30)    地址区域编码'," +
                 "	`type`  INT(11) COMMENT '//int(11)    硬件设备类型(0:未知1：PDA设备2：地磁设备（抽象android板子）3：蓝牙车位锁)'," +
                 "	`ph_mac`  VARCHAR(80) COMMENT '//varchar(80)    硬件设备MAC地址'," +
                 "	`ph_licence`  VARCHAR(80) COMMENT '//varchar(80)    硬件设备串号'," +
                 "	`park_type`  INT(11) COMMENT '//int(11)    停车场类型（0：道闸1：占道2：立体车库）'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`ph_loginname`  VARCHAR(100) COMMENT '//varchar(100)    硬件设备登录帐号'," +
                 "	`ph_password`  VARCHAR(100) COMMENT '//varchar(100)    硬件设备登录密码'," +
                 "	`ph_state`  INT(11) COMMENT '//int(11)    硬件设备状态（0：正常1：异常）'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`token`  VARCHAR(80) COMMENT '//varchar(80)    授权tokon'," +
                 "	`versioncode`  INT(11) COMMENT '//int(11)    当前内部版本号(例如:1,2,3,4)'," +
                 "	PRIMARY KEY (`ph_id`)" +
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
