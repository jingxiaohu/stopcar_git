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

//finger_userinfo_relation2

@Repository("finger_userinfo_relation2Dao")
public class Finger_userinfo_relation2Dao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Finger_userinfo_relation2Dao.class);



    private  String TABLE = "finger_userinfo_relation2";

    private  String TABLENAME = "finger_userinfo_relation2";

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


    private  String[] carrays ={"fur_id","fu_id","fu_nd","fingerprint","finger_veno","fingerprint_hash","finger_veno_hash","is_del","ctime","utime","note","fingerprint_img","finger_veno_img"};
    private  String coulmns ="fur_id,fu_id,fu_nd,fingerprint,finger_veno,fingerprint_hash,finger_veno_hash,is_del,ctime,utime,note,fingerprint_img,finger_veno_img";
    private  String coulmns2 ="fu_id,fu_nd,fingerprint,finger_veno,fingerprint_hash,finger_veno_hash,is_del,ctime,utime,note,fingerprint_img,finger_veno_img";

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
    public int insert(Finger_userinfo_relation2 bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Finger_userinfo_relation2 bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (fu_id,fu_nd,fingerprint,finger_veno,fingerprint_hash,finger_veno_hash,is_del,ctime,utime,note,fingerprint_img,finger_veno_img) VALUES (:fu_id,:fu_nd,:fingerprint,:finger_veno,:fingerprint_hash,:finger_veno_hash,:is_del,:ctime,:utime,:note,:fingerprint_img,:finger_veno_img)";
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
    public int insert_primarykey(Finger_userinfo_relation2 bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Finger_userinfo_relation2 bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (fur_id,fu_id,fu_nd,fingerprint,finger_veno,fingerprint_hash,finger_veno_hash,is_del,ctime,utime,note,fingerprint_img,finger_veno_img) VALUES (:fur_id,:fu_id,:fu_nd,:fingerprint,:finger_veno,:fingerprint_hash,:finger_veno_hash,:is_del,:ctime,:utime,:note,:fingerprint_img,:finger_veno_img)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Finger_userinfo_relation2> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Finger_userinfo_relation2> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (fu_id,fu_nd,fingerprint,finger_veno,fingerprint_hash,finger_veno_hash,is_del,ctime,utime,note,fingerprint_img,finger_veno_img) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Finger_userinfo_relation2 bean = beans.get(i);
                    ps.setLong(1, bean.fu_id);
                    ps.setString(2, bean.fu_nd);
                    ps.setString(3, bean.fingerprint);
                    ps.setString(4, bean.finger_veno);
                    ps.setString(5, bean.fingerprint_hash);
                    ps.setString(6, bean.finger_veno_hash);
                    ps.setInt(7, bean.is_del);
                    ps.setTimestamp(8, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(9, new Timestamp(bean.utime.getTime()));
                    ps.setString(10, bean.note);
                    ps.setString(11, bean.fingerprint_img);
                    ps.setString(12, bean.finger_veno_img);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Finger_userinfo_relation2> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Finger_userinfo_relation2> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT fur_id,fu_id,fu_nd,fingerprint,finger_veno,fingerprint_hash,finger_veno_hash,is_del,ctime,utime,note,fingerprint_img,finger_veno_img FROM "+TABLENAME2+" ORDER BY fur_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Finger_userinfo_relation2>(Finger_userinfo_relation2.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Finger_userinfo_relation2>();
        }
    }

    //查询最新数据
    public List<Finger_userinfo_relation2> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Finger_userinfo_relation2> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT fur_id,fu_id,fu_nd,fingerprint,finger_veno,fingerprint_hash,finger_veno_hash,is_del,ctime,utime,note,fingerprint_img,finger_veno_img FROM "+TABLENAME2+" ORDER BY fur_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Finger_userinfo_relation2>(Finger_userinfo_relation2.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Finger_userinfo_relation2>();
        }
    }

    //根据主键查询
    public List<Finger_userinfo_relation2> selectGtKey(long fur_id) {
        return selectGtKey(fur_id, TABLENAME);
    }

    //根据主键查询
    public List<Finger_userinfo_relation2> selectGtKey(long fur_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT fur_id,fu_id,fu_nd,fingerprint,finger_veno,fingerprint_hash,finger_veno_hash,is_del,ctime,utime,note,fingerprint_img,finger_veno_img FROM "+TABLENAME2+" WHERE fur_id>:fur_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("fur_id", fur_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Finger_userinfo_relation2>(Finger_userinfo_relation2.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Finger_userinfo_relation2>();
        }
    }

    //根据主键查询
    public Finger_userinfo_relation2 selectByKey(long fur_id) {
        return selectByKey(fur_id, TABLENAME);
    }

    //根据主键查询
    public Finger_userinfo_relation2 selectByKey(long fur_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT fur_id,fu_id,fu_nd,fingerprint,finger_veno,fingerprint_hash,finger_veno_hash,is_del,ctime,utime,note,fingerprint_img,finger_veno_img FROM "+TABLENAME2+" WHERE fur_id=:fur_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("fur_id", fur_id);
            List<Finger_userinfo_relation2> list =  _np.query(sql, param, new BeanPropertyRowMapper<Finger_userinfo_relation2>(Finger_userinfo_relation2.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey fur_id="+fur_id,e);
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
    public List<Finger_userinfo_relation2> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Finger_userinfo_relation2> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT fur_id,fu_id,fu_nd,fingerprint,finger_veno,fingerprint_hash,finger_veno_hash,is_del,ctime,utime,note,fingerprint_img,finger_veno_img FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Finger_userinfo_relation2>(Finger_userinfo_relation2.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Finger_userinfo_relation2>();
        }
    }

    //修改数据
    public int updateByKey(Finger_userinfo_relation2 bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Finger_userinfo_relation2 bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET fu_id=:fu_id,fu_nd=:fu_nd,fingerprint=:fingerprint,finger_veno=:finger_veno,fingerprint_hash=:fingerprint_hash,finger_veno_hash=:finger_veno_hash,is_del=:is_del,ctime=:ctime,utime=:utime,note=:note,fingerprint_img=:fingerprint_img,finger_veno_img=:finger_veno_img WHERE fur_id=:fur_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Finger_userinfo_relation2> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Finger_userinfo_relation2> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET fu_id=?,fu_nd=?,fingerprint=?,finger_veno=?,fingerprint_hash=?,finger_veno_hash=?,is_del=?,ctime=?,utime=?,note=?,fingerprint_img=?,finger_veno_img=? WHERE fur_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Finger_userinfo_relation2 bean = beans.get(i);
                    ps.setLong(1, bean.fu_id);
                    ps.setString(2, bean.fu_nd);
                    ps.setString(3, bean.fingerprint);
                    ps.setString(4, bean.finger_veno);
                    ps.setString(5, bean.fingerprint_hash);
                    ps.setString(6, bean.finger_veno_hash);
                    ps.setInt(7, bean.is_del);
                    ps.setTimestamp(8, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(9, new Timestamp(bean.utime.getTime()));
                    ps.setString(10, bean.note);
                    ps.setString(11, bean.fingerprint_img);
                    ps.setString(12, bean.finger_veno_img);
                    ps.setLong(13, bean.fur_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long fur_id) throws SQLException{
        return deleteByKey(fur_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long fur_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE fur_id=:fur_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("fur_id", fur_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE fur_id=?";
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
                 "	`fur_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`fu_id`  BIGINT(20) COMMENT '//bigint(20)    指纹用户基本信息表主键ID（外键）'," +
                 "	`fu_nd`  VARCHAR(80) COMMENT '//varchar(80)    指纹用户基本信息表(finger_userinfo)ND'," +
                 "	`fingerprint`  TEXT COMMENT '//text    用户指纹'," +
                 "	`finger_veno`  TEXT COMMENT '//text    用户指静脉'," +
                 "	`fingerprint_hash`  TINYTEXT COMMENT '//varchar(255)    用户指纹图片hash'," +
                 "	`finger_veno_hash`  TINYTEXT COMMENT '//varchar(255)    用户指静脉图片hash'," +
                 "	`is_del`  INT(11) COMMENT '//int(11)    是否删除(0:正常1：删除)'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    修改时间'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`fingerprint_img`  TINYTEXT COMMENT '//varchar(255)    用户指纹图片URL'," +
                 "	`finger_veno_img`  TINYTEXT COMMENT '//varchar(255)    用户指静脉图片URL'," +
                 "	PRIMARY KEY (`fur_id`)" +
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
