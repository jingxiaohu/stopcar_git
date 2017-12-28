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

//channel_version

@Repository("channel_versionDao")
public class Channel_versionDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Channel_versionDao.class);



    private  String TABLE = "channel_version";

    private  String TABLENAME = "channel_version";

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


    private  String[] carrays ={"cv_id","pda_c_id","version_out_show","version_code","is_trunk","is_open","apk_url","md5","intro","name","ctime","utime","is_force","note"};
    private  String coulmns ="cv_id,pda_c_id,version_out_show,version_code,is_trunk,is_open,apk_url,md5,intro,name,ctime,utime,is_force,note";
    private  String coulmns2 ="pda_c_id,version_out_show,version_code,is_trunk,is_open,apk_url,md5,intro,name,ctime,utime,is_force,note";

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
    public int insert(Channel_version bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Channel_version bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pda_c_id,version_out_show,version_code,is_trunk,is_open,apk_url,md5,intro,name,ctime,utime,is_force,note) VALUES (:pda_c_id,:version_out_show,:version_code,:is_trunk,:is_open,:apk_url,:md5,:intro,:name,:ctime,:utime,:is_force,:note)";
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
    public int insert_primarykey(Channel_version bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Channel_version bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (cv_id,pda_c_id,version_out_show,version_code,is_trunk,is_open,apk_url,md5,intro,name,ctime,utime,is_force,note) VALUES (:cv_id,:pda_c_id,:version_out_show,:version_code,:is_trunk,:is_open,:apk_url,:md5,:intro,:name,:ctime,:utime,:is_force,:note)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Channel_version> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Channel_version> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pda_c_id,version_out_show,version_code,is_trunk,is_open,apk_url,md5,intro,name,ctime,utime,is_force,note) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Channel_version bean = beans.get(i);
                    ps.setLong(1, bean.pda_c_id);
                    ps.setString(2, bean.version_out_show);
                    ps.setInt(3, bean.version_code);
                    ps.setInt(4, bean.is_trunk);
                    ps.setInt(5, bean.is_open);
                    ps.setString(6, bean.apk_url);
                    ps.setString(7, bean.md5);
                    ps.setString(8, bean.intro);
                    ps.setString(9, bean.name);
                    ps.setTimestamp(10, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(11, new Timestamp(bean.utime.getTime()));
                    ps.setInt(12, bean.is_force);
                    ps.setString(13, bean.note);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Channel_version> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Channel_version> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT cv_id,pda_c_id,version_out_show,version_code,is_trunk,is_open,apk_url,md5,intro,name,ctime,utime,is_force,note FROM "+TABLENAME2+" ORDER BY cv_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Channel_version>(Channel_version.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Channel_version>();
        }
    }

    //查询最新数据
    public List<Channel_version> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Channel_version> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT cv_id,pda_c_id,version_out_show,version_code,is_trunk,is_open,apk_url,md5,intro,name,ctime,utime,is_force,note FROM "+TABLENAME2+" ORDER BY cv_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Channel_version>(Channel_version.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Channel_version>();
        }
    }

    //根据主键查询
    public List<Channel_version> selectGtKey(long cv_id) {
        return selectGtKey(cv_id, TABLENAME);
    }

    //根据主键查询
    public List<Channel_version> selectGtKey(long cv_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT cv_id,pda_c_id,version_out_show,version_code,is_trunk,is_open,apk_url,md5,intro,name,ctime,utime,is_force,note FROM "+TABLENAME2+" WHERE cv_id>:cv_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("cv_id", cv_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Channel_version>(Channel_version.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Channel_version>();
        }
    }

    //根据主键查询
    public Channel_version selectByKey(long cv_id) {
        return selectByKey(cv_id, TABLENAME);
    }

    //根据主键查询
    public Channel_version selectByKey(long cv_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT cv_id,pda_c_id,version_out_show,version_code,is_trunk,is_open,apk_url,md5,intro,name,ctime,utime,is_force,note FROM "+TABLENAME2+" WHERE cv_id=:cv_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("cv_id", cv_id);
            List<Channel_version> list =  _np.query(sql, param, new BeanPropertyRowMapper<Channel_version>(Channel_version.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey cv_id="+cv_id,e);
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
    public List<Channel_version> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Channel_version> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT cv_id,pda_c_id,version_out_show,version_code,is_trunk,is_open,apk_url,md5,intro,name,ctime,utime,is_force,note FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Channel_version>(Channel_version.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Channel_version>();
        }
    }

    //修改数据
    public int updateByKey(Channel_version bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Channel_version bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pda_c_id=:pda_c_id,version_out_show=:version_out_show,version_code=:version_code,is_trunk=:is_trunk,is_open=:is_open,apk_url=:apk_url,md5=:md5,intro=:intro,name=:name,ctime=:ctime,utime=:utime,is_force=:is_force,note=:note WHERE cv_id=:cv_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Channel_version> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Channel_version> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pda_c_id=?,version_out_show=?,version_code=?,is_trunk=?,is_open=?,apk_url=?,md5=?,intro=?,name=?,ctime=?,utime=?,is_force=?,note=? WHERE cv_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Channel_version bean = beans.get(i);
                    ps.setLong(1, bean.pda_c_id);
                    ps.setString(2, bean.version_out_show);
                    ps.setInt(3, bean.version_code);
                    ps.setInt(4, bean.is_trunk);
                    ps.setInt(5, bean.is_open);
                    ps.setString(6, bean.apk_url);
                    ps.setString(7, bean.md5);
                    ps.setString(8, bean.intro);
                    ps.setString(9, bean.name);
                    ps.setTimestamp(10, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(11, new Timestamp(bean.utime.getTime()));
                    ps.setInt(12, bean.is_force);
                    ps.setString(13, bean.note);
                    ps.setLong(14, bean.cv_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long cv_id) throws SQLException{
        return deleteByKey(cv_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long cv_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE cv_id=:cv_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("cv_id", cv_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE cv_id=?";
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
                 "	`cv_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`pda_c_id`  BIGINT(20) COMMENT '//bigint(20)    渠道ID'," +
                 "	`version_out_show`  VARCHAR(60) COMMENT '//varchar(60)    版本外部编号'," +
                 "	`version_code`  INT(11) COMMENT '//int(11)    版本内部编号'," +
                 "	`is_trunk`  INT(11) COMMENT '//int(11)    是否是主干渠道0:不是1：是'," +
                 "	`is_open`  INT(11) COMMENT '//int(11)    是否启用0：不启用1：启用'," +
                 "	`apk_url`  TINYTEXT COMMENT '//varchar(255)    APK文件下载地址'," +
                 "	`md5`  VARCHAR(100) COMMENT '//varchar(100)    APK文件MD5'," +
                 "	`intro`  TEXT COMMENT '//text    版本介绍'," +
                 "	`name`  VARCHAR(100) COMMENT '//varchar(100)    版本打包人姓名'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`is_force`  INT(11) COMMENT '//int(11)    是否强制更新0：不强制1:强制'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	PRIMARY KEY (`cv_id`)" +
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
