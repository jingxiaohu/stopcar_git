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

//sms_running

@Repository("sms_runningDao")
public class Sms_runningDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Sms_runningDao.class);



    private  String TABLE = "sms_running";

    private  String TABLENAME = "sms_running";

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


    private  String[] carrays ={"Id","sms_tel","sms_content","sms_stat","sms_type","sms_user","sms_user_name","server_state","sms_date","note"};
    private  String coulmns ="Id,sms_tel,sms_content,sms_stat,sms_type,sms_user,sms_user_name,server_state,sms_date,note";
    private  String coulmns2 ="sms_tel,sms_content,sms_stat,sms_type,sms_user,sms_user_name,server_state,sms_date,note";

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
    public int insert(Sms_running bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Sms_running bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (sms_tel,sms_content,sms_stat,sms_type,sms_user,sms_user_name,server_state,sms_date,note) VALUES (:sms_tel,:sms_content,:sms_stat,:sms_type,:sms_user,:sms_user_name,:server_state,:sms_date,:note)";
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
    public int insert_primarykey(Sms_running bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Sms_running bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (Id,sms_tel,sms_content,sms_stat,sms_type,sms_user,sms_user_name,server_state,sms_date,note) VALUES (:Id,:sms_tel,:sms_content,:sms_stat,:sms_type,:sms_user,:sms_user_name,:server_state,:sms_date,:note)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Sms_running> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Sms_running> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (sms_tel,sms_content,sms_stat,sms_type,sms_user,sms_user_name,server_state,sms_date,note) VALUES (?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Sms_running bean = beans.get(i);
                    ps.setString(1, bean.sms_tel);
                    ps.setString(2, bean.sms_content);
                    ps.setInt(3, bean.sms_stat);
                    ps.setInt(4, bean.sms_type);
                    ps.setString(5, bean.sms_user);
                    ps.setString(6, bean.sms_user_name);
                    ps.setString(7, bean.server_state);
                    ps.setString(8, bean.sms_date);
                    ps.setString(9, bean.note);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Sms_running> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Sms_running> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT Id,sms_tel,sms_content,sms_stat,sms_type,sms_user,sms_user_name,server_state,sms_date,note FROM "+TABLENAME2+" ORDER BY Id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Sms_running>(Sms_running.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Sms_running>();
        }
    }

    //查询最新数据
    public List<Sms_running> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Sms_running> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT Id,sms_tel,sms_content,sms_stat,sms_type,sms_user,sms_user_name,server_state,sms_date,note FROM "+TABLENAME2+" ORDER BY Id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Sms_running>(Sms_running.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Sms_running>();
        }
    }

    //根据主键查询
    public List<Sms_running> selectGtKey(int Id) {
        return selectGtKey(Id, TABLENAME);
    }

    //根据主键查询
    public List<Sms_running> selectGtKey(int Id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT Id,sms_tel,sms_content,sms_stat,sms_type,sms_user,sms_user_name,server_state,sms_date,note FROM "+TABLENAME2+" WHERE Id>:Id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("Id", Id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Sms_running>(Sms_running.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Sms_running>();
        }
    }

    //根据主键查询
    public Sms_running selectByKey(int Id) {
        return selectByKey(Id, TABLENAME);
    }

    //根据主键查询
    public Sms_running selectByKey(int Id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT Id,sms_tel,sms_content,sms_stat,sms_type,sms_user,sms_user_name,server_state,sms_date,note FROM "+TABLENAME2+" WHERE Id=:Id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("Id", Id);
            List<Sms_running> list =  _np.query(sql, param, new BeanPropertyRowMapper<Sms_running>(Sms_running.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey Id="+Id,e);
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
    public List<Sms_running> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Sms_running> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT Id,sms_tel,sms_content,sms_stat,sms_type,sms_user,sms_user_name,server_state,sms_date,note FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Sms_running>(Sms_running.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Sms_running>();
        }
    }

    //修改数据
    public int updateByKey(Sms_running bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Sms_running bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET sms_tel=:sms_tel,sms_content=:sms_content,sms_stat=:sms_stat,sms_type=:sms_type,sms_user=:sms_user,sms_user_name=:sms_user_name,server_state=:server_state,sms_date=:sms_date,note=:note WHERE Id=:Id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Sms_running> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Sms_running> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET sms_tel=?,sms_content=?,sms_stat=?,sms_type=?,sms_user=?,sms_user_name=?,server_state=?,sms_date=?,note=? WHERE Id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Sms_running bean = beans.get(i);
                    ps.setString(1, bean.sms_tel);
                    ps.setString(2, bean.sms_content);
                    ps.setInt(3, bean.sms_stat);
                    ps.setInt(4, bean.sms_type);
                    ps.setString(5, bean.sms_user);
                    ps.setString(6, bean.sms_user_name);
                    ps.setString(7, bean.server_state);
                    ps.setString(8, bean.sms_date);
                    ps.setString(9, bean.note);
                    ps.setInt(10, bean.Id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(int Id) throws SQLException{
        return deleteByKey(Id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(int Id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE Id=:Id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("Id", Id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE Id=?";
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
                 "	`Id`  INT(11) NOT NULL AUTO_INCREMENT COMMENT '//int(11)    '," +
                 "	`sms_tel`  TINYTEXT COMMENT '//varchar(255)    '," +
                 "	`sms_content`  TEXT COMMENT '//text    '," +
                 "	`sms_stat`  INT(11) COMMENT '//int(11)    失败还是成功1-成功，0失败'," +
                 "	`sms_type`  INT(11) COMMENT '//int(11)    1-自定义短信0-程序员自定义短信'," +
                 "	`sms_user`  VARCHAR(20) COMMENT '//varchar(20)    发送用户'," +
                 "	`sms_user_name`  VARCHAR(40) COMMENT '//varchar(40)    发送用户'," +
                 "	`server_state`  TINYTEXT COMMENT '//varchar(255)    服务端的状态码'," +
                 "	`sms_date`  VARCHAR(40) COMMENT '//varchar(40)    '," +
                 "	`note`  TINYTEXT COMMENT '//varchar(255)    '," +
                 "	PRIMARY KEY (`Id`)" +
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
