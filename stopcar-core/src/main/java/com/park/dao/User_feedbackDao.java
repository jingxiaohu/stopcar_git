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

//user_feedback

@Repository("user_feedbackDao")
public class User_feedbackDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(User_feedbackDao.class);



    private  String TABLE = "user_feedback";

    private  String TABLENAME = "user_feedback";

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


    private  String[] carrays ={"id","ui_id","content","ctime","utime","note","type","pi_id","area_code","pi_name","pda_id"};
    private  String coulmns ="id,ui_id,content,ctime,utime,note,type,pi_id,area_code,pi_name,pda_id";
    private  String coulmns2 ="ui_id,content,ctime,utime,note,type,pi_id,area_code,pi_name,pda_id";

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
    public int insert(User_feedback bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(User_feedback bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (ui_id,content,ctime,utime,note,type,pi_id,area_code,pi_name,pda_id) VALUES (:ui_id,:content,:ctime,:utime,:note,:type,:pi_id,:area_code,:pi_name,:pda_id)";
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
    public int insert_primarykey(User_feedback bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(User_feedback bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (id,ui_id,content,ctime,utime,note,type,pi_id,area_code,pi_name,pda_id) VALUES (:id,:ui_id,:content,:ctime,:utime,:note,:type,:pi_id,:area_code,:pi_name,:pda_id)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<User_feedback> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<User_feedback> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (ui_id,content,ctime,utime,note,type,pi_id,area_code,pi_name,pda_id) VALUES (?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    User_feedback bean = beans.get(i);
                    ps.setLong(1, bean.ui_id);
                    ps.setString(2, bean.content);
                    ps.setTimestamp(3, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(4, new Timestamp(bean.utime.getTime()));
                    ps.setString(5, bean.note);
                    ps.setInt(6, bean.type);
                    ps.setLong(7, bean.pi_id);
                    ps.setString(8, bean.area_code);
                    ps.setString(9, bean.pi_name);
                    ps.setLong(10, bean.pda_id);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<User_feedback> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<User_feedback> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,ui_id,content,ctime,utime,note,type,pi_id,area_code,pi_name,pda_id FROM "+TABLENAME2+" ORDER BY id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<User_feedback>(User_feedback.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<User_feedback>();
        }
    }

    //查询最新数据
    public List<User_feedback> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<User_feedback> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,ui_id,content,ctime,utime,note,type,pi_id,area_code,pi_name,pda_id FROM "+TABLENAME2+" ORDER BY id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<User_feedback>(User_feedback.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<User_feedback>();
        }
    }

    //根据主键查询
    public List<User_feedback> selectGtKey(long id) {
        return selectGtKey(id, TABLENAME);
    }

    //根据主键查询
    public List<User_feedback> selectGtKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,ui_id,content,ctime,utime,note,type,pi_id,area_code,pi_name,pda_id FROM "+TABLENAME2+" WHERE id>:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            return _np.query(sql, param, new BeanPropertyRowMapper<User_feedback>(User_feedback.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<User_feedback>();
        }
    }

    //根据主键查询
    public User_feedback selectByKey(long id) {
        return selectByKey(id, TABLENAME);
    }

    //根据主键查询
    public User_feedback selectByKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,ui_id,content,ctime,utime,note,type,pi_id,area_code,pi_name,pda_id FROM "+TABLENAME2+" WHERE id=:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            List<User_feedback> list =  _np.query(sql, param, new BeanPropertyRowMapper<User_feedback>(User_feedback.class));
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
    public List<User_feedback> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<User_feedback> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT id,ui_id,content,ctime,utime,note,type,pi_id,area_code,pi_name,pda_id FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<User_feedback>(User_feedback.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<User_feedback>();
        }
    }

    //修改数据
    public int updateByKey(User_feedback bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(User_feedback bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET ui_id=:ui_id,content=:content,ctime=:ctime,utime=:utime,note=:note,type=:type,pi_id=:pi_id,area_code=:area_code,pi_name=:pi_name,pda_id=:pda_id WHERE id=:id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<User_feedback> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<User_feedback> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET ui_id=?,content=?,ctime=?,utime=?,note=?,type=?,pi_id=?,area_code=?,pi_name=?,pda_id=? WHERE id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    User_feedback bean = beans.get(i);
                    ps.setLong(1, bean.ui_id);
                    ps.setString(2, bean.content);
                    ps.setTimestamp(3, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(4, new Timestamp(bean.utime.getTime()));
                    ps.setString(5, bean.note);
                    ps.setInt(6, bean.type);
                    ps.setLong(7, bean.pi_id);
                    ps.setString(8, bean.area_code);
                    ps.setString(9, bean.pi_name);
                    ps.setLong(10, bean.pda_id);
                    ps.setLong(11, bean.id);
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
                 "	`ui_id`  BIGINT(20) COMMENT '//bigint(20)    用户主键ID'," +
                 "	`content`  TINYTEXT COMMENT '//varchar(255)    反馈信息'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`type`  INT(11) COMMENT '//int(11)    反馈来源（0：app用户反馈1：PDA管理人反馈2：道闸管理人反馈）'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场主键ID'," +
                 "	`area_code`  VARCHAR(30) COMMENT '//varchar(30)    地址区域编码'," +
                 "	`pi_name`  VARCHAR(100) COMMENT '//varchar(100)    停车场名称'," +
                 "	`pda_id`  BIGINT(20) COMMENT '//bigint(20)    PDA设备表的主键ID'," +
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
