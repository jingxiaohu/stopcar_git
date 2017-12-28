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

//park_authorize

@Repository("park_authorizeDao")
public class Park_authorizeDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Park_authorizeDao.class);



    private  String TABLE = "park_authorize";

    private  String TABLENAME = "park_authorize";

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


    private  String[] carrays ={"pa_id","pi_id","area_code","pi_name","secret_key","state","duration","last_time","encrypt_type","is_del","ctime","utime","note"};
    private  String coulmns ="pa_id,pi_id,area_code,pi_name,secret_key,state,duration,last_time,encrypt_type,is_del,ctime,utime,note";
    private  String coulmns2 ="pi_id,area_code,pi_name,secret_key,state,duration,last_time,encrypt_type,is_del,ctime,utime,note";

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
    public int insert(Park_authorize bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Park_authorize bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,area_code,pi_name,secret_key,state,duration,last_time,encrypt_type,is_del,ctime,utime,note) VALUES (:pi_id,:area_code,:pi_name,:secret_key,:state,:duration,:last_time,:encrypt_type,:is_del,:ctime,:utime,:note)";
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
    public int insert_primarykey(Park_authorize bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Park_authorize bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pa_id,pi_id,area_code,pi_name,secret_key,state,duration,last_time,encrypt_type,is_del,ctime,utime,note) VALUES (:pa_id,:pi_id,:area_code,:pi_name,:secret_key,:state,:duration,:last_time,:encrypt_type,:is_del,:ctime,:utime,:note)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Park_authorize> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Park_authorize> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,area_code,pi_name,secret_key,state,duration,last_time,encrypt_type,is_del,ctime,utime,note) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_authorize bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setString(2, bean.area_code);
                    ps.setString(3, bean.pi_name);
                    ps.setString(4, bean.secret_key);
                    ps.setInt(5, bean.state);
                    ps.setInt(6, bean.duration);
                    ps.setTimestamp(7, new Timestamp(bean.last_time.getTime()));
                    ps.setInt(8, bean.encrypt_type);
                    ps.setInt(9, bean.is_del);
                    ps.setTimestamp(10, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(11, new Timestamp(bean.utime.getTime()));
                    ps.setString(12, bean.note);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Park_authorize> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Park_authorize> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pa_id,pi_id,area_code,pi_name,secret_key,state,duration,last_time,encrypt_type,is_del,ctime,utime,note FROM "+TABLENAME2+" ORDER BY pa_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_authorize>(Park_authorize.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Park_authorize>();
        }
    }

    //查询最新数据
    public List<Park_authorize> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Park_authorize> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pa_id,pi_id,area_code,pi_name,secret_key,state,duration,last_time,encrypt_type,is_del,ctime,utime,note FROM "+TABLENAME2+" ORDER BY pa_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_authorize>(Park_authorize.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Park_authorize>();
        }
    }

    //根据主键查询
    public List<Park_authorize> selectGtKey(long pa_id) {
        return selectGtKey(pa_id, TABLENAME);
    }

    //根据主键查询
    public List<Park_authorize> selectGtKey(long pa_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pa_id,pi_id,area_code,pi_name,secret_key,state,duration,last_time,encrypt_type,is_del,ctime,utime,note FROM "+TABLENAME2+" WHERE pa_id>:pa_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pa_id", pa_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Park_authorize>(Park_authorize.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Park_authorize>();
        }
    }

    //根据主键查询
    public Park_authorize selectByKey(long pa_id) {
        return selectByKey(pa_id, TABLENAME);
    }

    //根据主键查询
    public Park_authorize selectByKey(long pa_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pa_id,pi_id,area_code,pi_name,secret_key,state,duration,last_time,encrypt_type,is_del,ctime,utime,note FROM "+TABLENAME2+" WHERE pa_id=:pa_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pa_id", pa_id);
            List<Park_authorize> list =  _np.query(sql, param, new BeanPropertyRowMapper<Park_authorize>(Park_authorize.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey pa_id="+pa_id,e);
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
    public List<Park_authorize> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Park_authorize> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT pa_id,pi_id,area_code,pi_name,secret_key,state,duration,last_time,encrypt_type,is_del,ctime,utime,note FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Park_authorize>(Park_authorize.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Park_authorize>();
        }
    }

    //修改数据
    public int updateByKey(Park_authorize bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Park_authorize bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=:pi_id,area_code=:area_code,pi_name=:pi_name,secret_key=:secret_key,state=:state,duration=:duration,last_time=:last_time,encrypt_type=:encrypt_type,is_del=:is_del,ctime=:ctime,utime=:utime,note=:note WHERE pa_id=:pa_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_authorize> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_authorize> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=?,area_code=?,pi_name=?,secret_key=?,state=?,duration=?,last_time=?,encrypt_type=?,is_del=?,ctime=?,utime=?,note=? WHERE pa_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_authorize bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setString(2, bean.area_code);
                    ps.setString(3, bean.pi_name);
                    ps.setString(4, bean.secret_key);
                    ps.setInt(5, bean.state);
                    ps.setInt(6, bean.duration);
                    ps.setTimestamp(7, new Timestamp(bean.last_time.getTime()));
                    ps.setInt(8, bean.encrypt_type);
                    ps.setInt(9, bean.is_del);
                    ps.setTimestamp(10, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(11, new Timestamp(bean.utime.getTime()));
                    ps.setString(12, bean.note);
                    ps.setLong(13, bean.pa_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long pa_id) throws SQLException{
        return deleteByKey(pa_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long pa_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE pa_id=:pa_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pa_id", pa_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE pa_id=?";
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
                 "	`pa_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场主键ID'," +
                 "	`area_code`  VARCHAR(100) COMMENT '//varchar(100)    区域代码'," +
                 "	`pi_name`  VARCHAR(200) COMMENT '//varchar(200)    停车场名称'," +
                 "	`secret_key`  TINYTEXT COMMENT '//varchar(255)    授权加密key'," +
                 "	`state`  INT(11) COMMENT '//int(11)    授权状态(0:未知1：成功授权2：授权失败)'," +
                 "	`duration`  INT(11) COMMENT '//int(11)    授权使用时长（单位小时）'," +
                 "	`last_time`  DATETIME COMMENT '//datetime    上次授权成功时间'," +
                 "	`encrypt_type`  INT(11) COMMENT '//int(11)    加密方式（1RSA2DES3MD5）'," +
                 "	`is_del`  INT(11) COMMENT '//int(11)    是否开启或者关闭（0：正常1：关闭）'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	PRIMARY KEY (`pa_id`)" +
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
