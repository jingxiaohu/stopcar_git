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

//sms_validate

@Repository("sms_validateDao")
public class Sms_validateDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Sms_validateDao.class);



    private  String TABLE = "sms_validate";

    private  String TABLENAME = "sms_validate";

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


    private  String[] carrays ={"id","v_tel","v_code","v_list","v_class","v_time","v_time_str","note"};
    private  String coulmns ="id,v_tel,v_code,v_list,v_class,v_time,v_time_str,note";
    private  String coulmns2 ="v_tel,v_code,v_list,v_class,v_time,v_time_str,note";

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
    public int insert(Sms_validate bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Sms_validate bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (v_tel,v_code,v_list,v_class,v_time,v_time_str,note) VALUES (:v_tel,:v_code,:v_list,:v_class,:v_time,:v_time_str,:note)";
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
    public int insert_primarykey(Sms_validate bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Sms_validate bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (id,v_tel,v_code,v_list,v_class,v_time,v_time_str,note) VALUES (:id,:v_tel,:v_code,:v_list,:v_class,:v_time,:v_time_str,:note)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Sms_validate> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Sms_validate> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (v_tel,v_code,v_list,v_class,v_time,v_time_str,note) VALUES (?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Sms_validate bean = beans.get(i);
                    ps.setString(1, bean.v_tel);
                    ps.setString(2, bean.v_code);
                    ps.setString(3, bean.v_list);
                    ps.setString(4, bean.v_class);
                    ps.setLong(5, bean.v_time);
                    ps.setString(6, bean.v_time_str);
                    ps.setString(7, bean.note);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Sms_validate> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Sms_validate> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,v_tel,v_code,v_list,v_class,v_time,v_time_str,note FROM "+TABLENAME2+" ORDER BY id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Sms_validate>(Sms_validate.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Sms_validate>();
        }
    }

    //查询最新数据
    public List<Sms_validate> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Sms_validate> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,v_tel,v_code,v_list,v_class,v_time,v_time_str,note FROM "+TABLENAME2+" ORDER BY id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Sms_validate>(Sms_validate.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Sms_validate>();
        }
    }

    //根据主键查询
    public List<Sms_validate> selectGtKey(long id) {
        return selectGtKey(id, TABLENAME);
    }

    //根据主键查询
    public List<Sms_validate> selectGtKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,v_tel,v_code,v_list,v_class,v_time,v_time_str,note FROM "+TABLENAME2+" WHERE id>:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Sms_validate>(Sms_validate.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Sms_validate>();
        }
    }

    //根据主键查询
    public Sms_validate selectByKey(long id) {
        return selectByKey(id, TABLENAME);
    }

    //根据主键查询
    public Sms_validate selectByKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,v_tel,v_code,v_list,v_class,v_time,v_time_str,note FROM "+TABLENAME2+" WHERE id=:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            List<Sms_validate> list =  _np.query(sql, param, new BeanPropertyRowMapper<Sms_validate>(Sms_validate.class));
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
    public List<Sms_validate> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Sms_validate> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT id,v_tel,v_code,v_list,v_class,v_time,v_time_str,note FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Sms_validate>(Sms_validate.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Sms_validate>();
        }
    }

    //修改数据
    public int updateByKey(Sms_validate bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Sms_validate bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET v_tel=:v_tel,v_code=:v_code,v_list=:v_list,v_class=:v_class,v_time=:v_time,v_time_str=:v_time_str,note=:note WHERE id=:id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Sms_validate> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Sms_validate> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET v_tel=?,v_code=?,v_list=?,v_class=?,v_time=?,v_time_str=?,note=? WHERE id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Sms_validate bean = beans.get(i);
                    ps.setString(1, bean.v_tel);
                    ps.setString(2, bean.v_code);
                    ps.setString(3, bean.v_list);
                    ps.setString(4, bean.v_class);
                    ps.setLong(5, bean.v_time);
                    ps.setString(6, bean.v_time_str);
                    ps.setString(7, bean.note);
                    ps.setLong(8, bean.id);
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
                 "	`v_tel`  VARCHAR(40) COMMENT '//varchar(40)    手机号'," +
                 "	`v_code`  VARCHAR(10) COMMENT '//varchar(10)    验证码6位数字'," +
                 "	`v_list`  VARCHAR(32) COMMENT '//varchar(32)    随机码v_code的MD5'," +
                 "	`v_class`  VARCHAR(40) COMMENT '//varchar(40)    什么类型的短信验证码1：注册2：重置密码3:修改手机'," +
                 "	`v_time`  BIGINT(20) COMMENT '//bigint(20)    验证时间'," +
                 "	`v_time_str`  VARCHAR(40) COMMENT '//varchar(40)    时间字符串'," +
                 "	`note`  TINYTEXT COMMENT '//varchar(255)    '," +
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
