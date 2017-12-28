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

//organization_user

@Repository("organization_userDao")
public class Organization_userDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Organization_userDao.class);



    private  String TABLE = "organization_user";

    private  String TABLENAME = "organization_user";

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


    private  String[] carrays ={"id","user_id","name","password","privilege_id","status","gender","create_time","update_time","user_phone","user_email","error_count","error_date"};
    private  String coulmns ="id,user_id,name,password,privilege_id,status,gender,create_time,update_time,user_phone,user_email,error_count,error_date";
    private  String coulmns2 ="user_id,name,password,privilege_id,status,gender,create_time,update_time,user_phone,user_email,error_count,error_date";

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
    public int insert(Organization_user bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Organization_user bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (user_id,name,password,privilege_id,status,gender,create_time,update_time,user_phone,user_email,error_count,error_date) VALUES (:user_id,:name,:password,:privilege_id,:status,:gender,:create_time,:update_time,:user_phone,:user_email,:error_count,:error_date)";
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
    public int insert_primarykey(Organization_user bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Organization_user bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (id,user_id,name,password,privilege_id,status,gender,create_time,update_time,user_phone,user_email,error_count,error_date) VALUES (:id,:user_id,:name,:password,:privilege_id,:status,:gender,:create_time,:update_time,:user_phone,:user_email,:error_count,:error_date)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Organization_user> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Organization_user> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (user_id,name,password,privilege_id,status,gender,create_time,update_time,user_phone,user_email,error_count,error_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Organization_user bean = beans.get(i);
                    ps.setString(1, bean.user_id);
                    ps.setString(2, bean.name);
                    ps.setString(3, bean.password);
                    ps.setInt(4, bean.privilege_id);
                    ps.setByte(5, bean.status);
                    ps.setByte(6, bean.gender);
                    ps.setLong(7, bean.create_time);
                    ps.setLong(8, bean.update_time);
                    ps.setString(9, bean.user_phone);
                    ps.setString(10, bean.user_email);
                    ps.setInt(11, bean.error_count);
                    ps.setString(12, bean.error_date);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Organization_user> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Organization_user> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,user_id,name,password,privilege_id,status,gender,create_time,update_time,user_phone,user_email,error_count,error_date FROM "+TABLENAME2+" ORDER BY id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Organization_user>(Organization_user.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Organization_user>();
        }
    }

    //查询最新数据
    public List<Organization_user> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Organization_user> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,user_id,name,password,privilege_id,status,gender,create_time,update_time,user_phone,user_email,error_count,error_date FROM "+TABLENAME2+" ORDER BY id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Organization_user>(Organization_user.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Organization_user>();
        }
    }

    //根据主键查询
    public List<Organization_user> selectGtKey(int id) {
        return selectGtKey(id, TABLENAME);
    }

    //根据主键查询
    public List<Organization_user> selectGtKey(int id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,user_id,name,password,privilege_id,status,gender,create_time,update_time,user_phone,user_email,error_count,error_date FROM "+TABLENAME2+" WHERE id>:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Organization_user>(Organization_user.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Organization_user>();
        }
    }

    //根据主键查询
    public Organization_user selectByKey(int id) {
        return selectByKey(id, TABLENAME);
    }

    //根据主键查询
    public Organization_user selectByKey(int id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,user_id,name,password,privilege_id,status,gender,create_time,update_time,user_phone,user_email,error_count,error_date FROM "+TABLENAME2+" WHERE id=:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            List<Organization_user> list =  _np.query(sql, param, new BeanPropertyRowMapper<Organization_user>(Organization_user.class));
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
    public List<Organization_user> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Organization_user> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT id,user_id,name,password,privilege_id,status,gender,create_time,update_time,user_phone,user_email,error_count,error_date FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Organization_user>(Organization_user.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Organization_user>();
        }
    }

    //修改数据
    public int updateByKey(Organization_user bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Organization_user bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET user_id=:user_id,name=:name,password=:password,privilege_id=:privilege_id,status=:status,gender=:gender,create_time=:create_time,update_time=:update_time,user_phone=:user_phone,user_email=:user_email,error_count=:error_count,error_date=:error_date WHERE id=:id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Organization_user> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Organization_user> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET user_id=?,name=?,password=?,privilege_id=?,status=?,gender=?,create_time=?,update_time=?,user_phone=?,user_email=?,error_count=?,error_date=? WHERE id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Organization_user bean = beans.get(i);
                    ps.setString(1, bean.user_id);
                    ps.setString(2, bean.name);
                    ps.setString(3, bean.password);
                    ps.setInt(4, bean.privilege_id);
                    ps.setByte(5, bean.status);
                    ps.setByte(6, bean.gender);
                    ps.setLong(7, bean.create_time);
                    ps.setLong(8, bean.update_time);
                    ps.setString(9, bean.user_phone);
                    ps.setString(10, bean.user_email);
                    ps.setInt(11, bean.error_count);
                    ps.setString(12, bean.error_date);
                    ps.setInt(13, bean.id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(int id) throws SQLException{
        return deleteByKey(id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(int id, String TABLENAME2) throws SQLException{
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
    public int[] deleteByKey(int[] keys) throws SQLException{
        return deleteByKey(keys, TABLENAME);
    }

    //批量删除数据
    public int[] deleteByKey(final int[] keys, String TABLENAME2) throws SQLException{
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
                    ps.setInt(1 , keys[i]);
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
                 "	`id`  INT(11) NOT NULL AUTO_INCREMENT COMMENT '//int(11)    '," +
                 "	`user_id`  VARCHAR(20) NOT NULL COMMENT '//varchar(20)    4-10位的数字和字母(不区分大小写)，创建时用户自己填写，需做唯一性校验，用作登陆用户名'," +
                 "	`name`  VARCHAR(40) NOT NULL COMMENT '//varchar(40)    用户的昵称'," +
                 "	`password`  VARCHAR(100) NOT NULL COMMENT '//varchar(100)    '," +
                 "	`privilege_id`  INT(11) NOT NULL COMMENT '//int(11)    指向权限表'," +
                 "	`status`  TINYINT(4) NOT NULL COMMENT '//tinyint(4)    用户的状态，如可用，不可用'," +
                 "	`gender`  TINYINT(4) NOT NULL COMMENT '//tinyint(4)    用户的性别'," +
                 "	`create_time`  BIGINT(20) NOT NULL COMMENT '//bigint(20)    此用户被创建的时间'," +
                 "	`update_time`  BIGINT(20) NOT NULL COMMENT '//bigint(20)    '," +
                 "	`user_phone`  VARCHAR(20) COMMENT '//varchar(20)    电话号码'," +
                 "	`user_email`  VARCHAR(50) COMMENT '//varchar(50)    邮件'," +
                 "	`error_count`  INT(11) COMMENT '//int(11)    登录错误次数'," +
                 "	`error_date`  VARCHAR(60) COMMENT '//varchar(60)    登录错误发生的日期例如2017-08-07'," +
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
