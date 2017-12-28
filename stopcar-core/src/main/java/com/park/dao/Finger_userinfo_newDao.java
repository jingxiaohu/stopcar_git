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

//finger_userinfo_new

@Repository("finger_userinfo_newDao")
public class Finger_userinfo_newDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Finger_userinfo_newDao.class);



    private  String TABLE = "finger_userinfo_new";

    private  String TABLENAME = "finger_userinfo_new";

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


    private  String[] carrays ={"fu_id","fu_nd","ui_tel","ui_id","ui_nd","name","sfz_number","sfz_img_url","sfz_img_back_url","state","is_del","ctime","utime","mac","gather_id","note","fingerprint_state","finger_veno_state"};
    private  String coulmns ="fu_id,fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,sfz_img_back_url,state,is_del,ctime,utime,mac,gather_id,note,fingerprint_state,finger_veno_state";
    private  String coulmns2 ="fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,sfz_img_back_url,state,is_del,ctime,utime,mac,gather_id,note,fingerprint_state,finger_veno_state";

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
    public int insert(Finger_userinfo_new bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Finger_userinfo_new bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,sfz_img_back_url,state,is_del,ctime,utime,mac,gather_id,note,fingerprint_state,finger_veno_state) VALUES (:fu_nd,:ui_tel,:ui_id,:ui_nd,:name,:sfz_number,:sfz_img_url,:sfz_img_back_url,:state,:is_del,:ctime,:utime,:mac,:gather_id,:note,:fingerprint_state,:finger_veno_state)";
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
    public int insert_primarykey(Finger_userinfo_new bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Finger_userinfo_new bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (fu_id,fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,sfz_img_back_url,state,is_del,ctime,utime,mac,gather_id,note,fingerprint_state,finger_veno_state) VALUES (:fu_id,:fu_nd,:ui_tel,:ui_id,:ui_nd,:name,:sfz_number,:sfz_img_url,:sfz_img_back_url,:state,:is_del,:ctime,:utime,:mac,:gather_id,:note,:fingerprint_state,:finger_veno_state)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Finger_userinfo_new> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Finger_userinfo_new> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,sfz_img_back_url,state,is_del,ctime,utime,mac,gather_id,note,fingerprint_state,finger_veno_state) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Finger_userinfo_new bean = beans.get(i);
                    ps.setString(1, bean.fu_nd);
                    ps.setString(2, bean.ui_tel);
                    ps.setLong(3, bean.ui_id);
                    ps.setString(4, bean.ui_nd);
                    ps.setString(5, bean.name);
                    ps.setString(6, bean.sfz_number);
                    ps.setString(7, bean.sfz_img_url);
                    ps.setString(8, bean.sfz_img_back_url);
                    ps.setInt(9, bean.state);
                    ps.setInt(10, bean.is_del);
                    ps.setTimestamp(11, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(12, new Timestamp(bean.utime.getTime()));
                    ps.setString(13, bean.mac);
                    ps.setLong(14, bean.gather_id);
                    ps.setString(15, bean.note);
                    ps.setInt(16, bean.fingerprint_state);
                    ps.setInt(17, bean.finger_veno_state);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Finger_userinfo_new> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Finger_userinfo_new> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT fu_id,fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,sfz_img_back_url,state,is_del,ctime,utime,mac,gather_id,note,fingerprint_state,finger_veno_state FROM "+TABLENAME2+" ORDER BY fu_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Finger_userinfo_new>(Finger_userinfo_new.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Finger_userinfo_new>();
        }
    }

    //查询最新数据
    public List<Finger_userinfo_new> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Finger_userinfo_new> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT fu_id,fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,sfz_img_back_url,state,is_del,ctime,utime,mac,gather_id,note,fingerprint_state,finger_veno_state FROM "+TABLENAME2+" ORDER BY fu_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Finger_userinfo_new>(Finger_userinfo_new.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Finger_userinfo_new>();
        }
    }

    //根据主键查询
    public List<Finger_userinfo_new> selectGtKey(long fu_id) {
        return selectGtKey(fu_id, TABLENAME);
    }

    //根据主键查询
    public List<Finger_userinfo_new> selectGtKey(long fu_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT fu_id,fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,sfz_img_back_url,state,is_del,ctime,utime,mac,gather_id,note,fingerprint_state,finger_veno_state FROM "+TABLENAME2+" WHERE fu_id>:fu_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("fu_id", fu_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Finger_userinfo_new>(Finger_userinfo_new.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Finger_userinfo_new>();
        }
    }

    //根据主键查询
    public Finger_userinfo_new selectByKey(long fu_id) {
        return selectByKey(fu_id, TABLENAME);
    }

    //根据主键查询
    public Finger_userinfo_new selectByKey(long fu_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT fu_id,fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,sfz_img_back_url,state,is_del,ctime,utime,mac,gather_id,note,fingerprint_state,finger_veno_state FROM "+TABLENAME2+" WHERE fu_id=:fu_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("fu_id", fu_id);
            List<Finger_userinfo_new> list =  _np.query(sql, param, new BeanPropertyRowMapper<Finger_userinfo_new>(Finger_userinfo_new.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey fu_id="+fu_id,e);
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
    public List<Finger_userinfo_new> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Finger_userinfo_new> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT fu_id,fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,sfz_img_back_url,state,is_del,ctime,utime,mac,gather_id,note,fingerprint_state,finger_veno_state FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Finger_userinfo_new>(Finger_userinfo_new.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Finger_userinfo_new>();
        }
    }

    //修改数据
    public int updateByKey(Finger_userinfo_new bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Finger_userinfo_new bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET fu_nd=:fu_nd,ui_tel=:ui_tel,ui_id=:ui_id,ui_nd=:ui_nd,name=:name,sfz_number=:sfz_number,sfz_img_url=:sfz_img_url,sfz_img_back_url=:sfz_img_back_url,state=:state,is_del=:is_del,ctime=:ctime,utime=:utime,mac=:mac,gather_id=:gather_id,note=:note,fingerprint_state=:fingerprint_state,finger_veno_state=:finger_veno_state WHERE fu_id=:fu_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Finger_userinfo_new> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Finger_userinfo_new> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET fu_nd=?,ui_tel=?,ui_id=?,ui_nd=?,name=?,sfz_number=?,sfz_img_url=?,sfz_img_back_url=?,state=?,is_del=?,ctime=?,utime=?,mac=?,gather_id=?,note=?,fingerprint_state=?,finger_veno_state=? WHERE fu_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Finger_userinfo_new bean = beans.get(i);
                    ps.setString(1, bean.fu_nd);
                    ps.setString(2, bean.ui_tel);
                    ps.setLong(3, bean.ui_id);
                    ps.setString(4, bean.ui_nd);
                    ps.setString(5, bean.name);
                    ps.setString(6, bean.sfz_number);
                    ps.setString(7, bean.sfz_img_url);
                    ps.setString(8, bean.sfz_img_back_url);
                    ps.setInt(9, bean.state);
                    ps.setInt(10, bean.is_del);
                    ps.setTimestamp(11, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(12, new Timestamp(bean.utime.getTime()));
                    ps.setString(13, bean.mac);
                    ps.setLong(14, bean.gather_id);
                    ps.setString(15, bean.note);
                    ps.setInt(16, bean.fingerprint_state);
                    ps.setInt(17, bean.finger_veno_state);
                    ps.setLong(18, bean.fu_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long fu_id) throws SQLException{
        return deleteByKey(fu_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long fu_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE fu_id=:fu_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("fu_id", fu_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE fu_id=?";
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
                 "	`fu_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`fu_nd`  VARCHAR(100) COMMENT '//varchar(100)    唯一标识符'," +
                 "	`ui_tel`  VARCHAR(30) COMMENT '//varchar(30)    用户手机号码'," +
                 "	`ui_id`  BIGINT(20) COMMENT '//bigint(20)    平台用户ID'," +
                 "	`ui_nd`  VARCHAR(100) COMMENT '//varchar(100)    平台用户唯一标识符'," +
                 "	`name`  VARCHAR(30) COMMENT '//varchar(30)    用户真实姓名'," +
                 "	`sfz_number`  VARCHAR(30) COMMENT '//varchar(30)    用户真实身份证号码'," +
                 "	`sfz_img_url`  VARCHAR(150) COMMENT '//varchar(150)    用户身份证图片'," +
                 "	`sfz_img_back_url`  VARCHAR(150) COMMENT '//varchar(150)    用户身份证背面图片'," +
                 "	`state`  INT(11) COMMENT '//int(11)    有效状态(0：无效1：有效)'," +
                 "	`is_del`  INT(11) COMMENT '//int(11)    是否删除0:没有1：删除'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    修改时间'," +
                 "	`mac`  VARCHAR(100) COMMENT '//varchar(100)    采集数据提交设备MAC'," +
                 "	`gather_id`  BIGINT(20) COMMENT '//bigint(20)    采集数据提交设备基本信息表主键ID'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`fingerprint_state`  INT(11) COMMENT '//int(11)    指纹完成度（0：未完成1：已经完成）'," +
                 "	`finger_veno_state`  INT(11) COMMENT '//int(11)    指静脉完成度(0:未完成1：已经完成)'," +
                 "	PRIMARY KEY (`fu_id`)" +
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
