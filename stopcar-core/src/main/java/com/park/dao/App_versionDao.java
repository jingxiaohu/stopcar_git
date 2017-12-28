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

//app_version

@Repository("app_versionDao")
public class App_versionDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(App_versionDao.class);



    private  String TABLE = "app_version";

    private  String TABLENAME = "app_version";

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


    private  String[] carrays ={"id","cav_version","cav_version_external","cav_version_code","ctime","content","cav_md5","android_url","ios_url","is_forced","note","type"};
    private  String coulmns ="id,cav_version,cav_version_external,cav_version_code,ctime,content,cav_md5,android_url,ios_url,is_forced,note,type";
    private  String coulmns2 ="cav_version,cav_version_external,cav_version_code,ctime,content,cav_md5,android_url,ios_url,is_forced,note,type";

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
    public int insert(App_version bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(App_version bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (cav_version,cav_version_external,cav_version_code,ctime,content,cav_md5,android_url,ios_url,is_forced,note,type) VALUES (:cav_version,:cav_version_external,:cav_version_code,:ctime,:content,:cav_md5,:android_url,:ios_url,:is_forced,:note,:type)";
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
    public int insert_primarykey(App_version bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(App_version bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (id,cav_version,cav_version_external,cav_version_code,ctime,content,cav_md5,android_url,ios_url,is_forced,note,type) VALUES (:id,:cav_version,:cav_version_external,:cav_version_code,:ctime,:content,:cav_md5,:android_url,:ios_url,:is_forced,:note,:type)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<App_version> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<App_version> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (cav_version,cav_version_external,cav_version_code,ctime,content,cav_md5,android_url,ios_url,is_forced,note,type) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    App_version bean = beans.get(i);
                    ps.setString(1, bean.cav_version);
                    ps.setString(2, bean.cav_version_external);
                    ps.setInt(3, bean.cav_version_code);
                    ps.setTimestamp(4, new Timestamp(bean.ctime.getTime()));
                    ps.setString(5, bean.content);
                    ps.setString(6, bean.cav_md5);
                    ps.setString(7, bean.android_url);
                    ps.setString(8, bean.ios_url);
                    ps.setInt(9, bean.is_forced);
                    ps.setString(10, bean.note);
                    ps.setInt(11, bean.type);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<App_version> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<App_version> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,cav_version,cav_version_external,cav_version_code,ctime,content,cav_md5,android_url,ios_url,is_forced,note,type FROM "+TABLENAME2+" ORDER BY id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<App_version>(App_version.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<App_version>();
        }
    }

    //查询最新数据
    public List<App_version> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<App_version> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,cav_version,cav_version_external,cav_version_code,ctime,content,cav_md5,android_url,ios_url,is_forced,note,type FROM "+TABLENAME2+" ORDER BY id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<App_version>(App_version.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<App_version>();
        }
    }

    //根据主键查询
    public List<App_version> selectGtKey(long id) {
        return selectGtKey(id, TABLENAME);
    }

    //根据主键查询
    public List<App_version> selectGtKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,cav_version,cav_version_external,cav_version_code,ctime,content,cav_md5,android_url,ios_url,is_forced,note,type FROM "+TABLENAME2+" WHERE id>:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            return _np.query(sql, param, new BeanPropertyRowMapper<App_version>(App_version.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<App_version>();
        }
    }

    //根据主键查询
    public App_version selectByKey(long id) {
        return selectByKey(id, TABLENAME);
    }

    //根据主键查询
    public App_version selectByKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,cav_version,cav_version_external,cav_version_code,ctime,content,cav_md5,android_url,ios_url,is_forced,note,type FROM "+TABLENAME2+" WHERE id=:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            List<App_version> list =  _np.query(sql, param, new BeanPropertyRowMapper<App_version>(App_version.class));
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
    public List<App_version> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<App_version> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT id,cav_version,cav_version_external,cav_version_code,ctime,content,cav_md5,android_url,ios_url,is_forced,note,type FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<App_version>(App_version.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<App_version>();
        }
    }

    //修改数据
    public int updateByKey(App_version bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(App_version bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET cav_version=:cav_version,cav_version_external=:cav_version_external,cav_version_code=:cav_version_code,ctime=:ctime,content=:content,cav_md5=:cav_md5,android_url=:android_url,ios_url=:ios_url,is_forced=:is_forced,note=:note,type=:type WHERE id=:id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<App_version> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<App_version> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET cav_version=?,cav_version_external=?,cav_version_code=?,ctime=?,content=?,cav_md5=?,android_url=?,ios_url=?,is_forced=?,note=?,type=? WHERE id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    App_version bean = beans.get(i);
                    ps.setString(1, bean.cav_version);
                    ps.setString(2, bean.cav_version_external);
                    ps.setInt(3, bean.cav_version_code);
                    ps.setTimestamp(4, new Timestamp(bean.ctime.getTime()));
                    ps.setString(5, bean.content);
                    ps.setString(6, bean.cav_md5);
                    ps.setString(7, bean.android_url);
                    ps.setString(8, bean.ios_url);
                    ps.setInt(9, bean.is_forced);
                    ps.setString(10, bean.note);
                    ps.setInt(11, bean.type);
                    ps.setLong(12, bean.id);
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
                 "	`cav_version`  VARCHAR(100) COMMENT '//varchar(100)    渠道版本'," +
                 "	`cav_version_external`  VARCHAR(100) COMMENT '//varchar(100)    渠道外部版本'," +
                 "	`cav_version_code`  INT(11) COMMENT '//int(11)    渠道版本内部编号'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`content`  TEXT COMMENT '//text    版本更新内容'," +
                 "	`cav_md5`  VARCHAR(32) COMMENT '//varchar(32)    版本MD5文件效验码'," +
                 "	`android_url`  TINYTEXT COMMENT '//varchar(500)    Android_app版本升级包URL地址'," +
                 "	`ios_url`  TINYTEXT COMMENT '//varchar(500)    Ios_app版本升级包URL地址'," +
                 "	`is_forced`  INT(11) COMMENT '//int(11)    是否强制更新0：不强制更新1：强制更新'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`type`  INT(11) COMMENT '//int(11)    类型0：Android手机APP1：ios手机APP2：PDA'," +
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
