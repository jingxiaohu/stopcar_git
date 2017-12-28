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

//finger_userinfo_carcode

@Repository("finger_userinfo_carcodeDao")
public class Finger_userinfo_carcodeDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Finger_userinfo_carcodeDao.class);



    private  String TABLE = "finger_userinfo_carcode";

    private  String TABLENAME = "finger_userinfo_carcode";

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


    private  String[] carrays ={"fuc_id","fu_id","sfz_number","car_code","is_run","isi_del","ctime","utime","note","fu_nd"};
    private  String coulmns ="fuc_id,fu_id,sfz_number,car_code,is_run,isi_del,ctime,utime,note,fu_nd";
    private  String coulmns2 ="fu_id,sfz_number,car_code,is_run,isi_del,ctime,utime,note,fu_nd";

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
    public int insert(Finger_userinfo_carcode bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Finger_userinfo_carcode bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (fu_id,sfz_number,car_code,is_run,isi_del,ctime,utime,note,fu_nd) VALUES (:fu_id,:sfz_number,:car_code,:is_run,:isi_del,:ctime,:utime,:note,:fu_nd)";
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
    public int insert_primarykey(Finger_userinfo_carcode bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Finger_userinfo_carcode bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (fuc_id,fu_id,sfz_number,car_code,is_run,isi_del,ctime,utime,note,fu_nd) VALUES (:fuc_id,:fu_id,:sfz_number,:car_code,:is_run,:isi_del,:ctime,:utime,:note,:fu_nd)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Finger_userinfo_carcode> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Finger_userinfo_carcode> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (fu_id,sfz_number,car_code,is_run,isi_del,ctime,utime,note,fu_nd) VALUES (?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Finger_userinfo_carcode bean = beans.get(i);
                    ps.setLong(1, bean.fu_id);
                    ps.setString(2, bean.sfz_number);
                    ps.setString(3, bean.car_code);
                    ps.setInt(4, bean.is_run);
                    ps.setInt(5, bean.isi_del);
                    ps.setTimestamp(6, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(7, new Timestamp(bean.utime.getTime()));
                    ps.setString(8, bean.note);
                    ps.setString(9, bean.fu_nd);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Finger_userinfo_carcode> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Finger_userinfo_carcode> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT fuc_id,fu_id,sfz_number,car_code,is_run,isi_del,ctime,utime,note,fu_nd FROM "+TABLENAME2+" ORDER BY fuc_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Finger_userinfo_carcode>(Finger_userinfo_carcode.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Finger_userinfo_carcode>();
        }
    }

    //查询最新数据
    public List<Finger_userinfo_carcode> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Finger_userinfo_carcode> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT fuc_id,fu_id,sfz_number,car_code,is_run,isi_del,ctime,utime,note,fu_nd FROM "+TABLENAME2+" ORDER BY fuc_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Finger_userinfo_carcode>(Finger_userinfo_carcode.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Finger_userinfo_carcode>();
        }
    }

    //根据主键查询
    public List<Finger_userinfo_carcode> selectGtKey(long fuc_id) {
        return selectGtKey(fuc_id, TABLENAME);
    }

    //根据主键查询
    public List<Finger_userinfo_carcode> selectGtKey(long fuc_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT fuc_id,fu_id,sfz_number,car_code,is_run,isi_del,ctime,utime,note,fu_nd FROM "+TABLENAME2+" WHERE fuc_id>:fuc_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("fuc_id", fuc_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Finger_userinfo_carcode>(Finger_userinfo_carcode.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Finger_userinfo_carcode>();
        }
    }

    //根据主键查询
    public Finger_userinfo_carcode selectByKey(long fuc_id) {
        return selectByKey(fuc_id, TABLENAME);
    }

    //根据主键查询
    public Finger_userinfo_carcode selectByKey(long fuc_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT fuc_id,fu_id,sfz_number,car_code,is_run,isi_del,ctime,utime,note,fu_nd FROM "+TABLENAME2+" WHERE fuc_id=:fuc_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("fuc_id", fuc_id);
            List<Finger_userinfo_carcode> list =  _np.query(sql, param, new BeanPropertyRowMapper<Finger_userinfo_carcode>(Finger_userinfo_carcode.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey fuc_id="+fuc_id,e);
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
    public List<Finger_userinfo_carcode> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Finger_userinfo_carcode> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT fuc_id,fu_id,sfz_number,car_code,is_run,isi_del,ctime,utime,note,fu_nd FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Finger_userinfo_carcode>(Finger_userinfo_carcode.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Finger_userinfo_carcode>();
        }
    }

    //修改数据
    public int updateByKey(Finger_userinfo_carcode bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Finger_userinfo_carcode bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET fu_id=:fu_id,sfz_number=:sfz_number,car_code=:car_code,is_run=:is_run,isi_del=:isi_del,ctime=:ctime,utime=:utime,note=:note,fu_nd=:fu_nd WHERE fuc_id=:fuc_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Finger_userinfo_carcode> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Finger_userinfo_carcode> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET fu_id=?,sfz_number=?,car_code=?,is_run=?,isi_del=?,ctime=?,utime=?,note=?,fu_nd=? WHERE fuc_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Finger_userinfo_carcode bean = beans.get(i);
                    ps.setLong(1, bean.fu_id);
                    ps.setString(2, bean.sfz_number);
                    ps.setString(3, bean.car_code);
                    ps.setInt(4, bean.is_run);
                    ps.setInt(5, bean.isi_del);
                    ps.setTimestamp(6, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(7, new Timestamp(bean.utime.getTime()));
                    ps.setString(8, bean.note);
                    ps.setString(9, bean.fu_nd);
                    ps.setLong(10, bean.fuc_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long fuc_id) throws SQLException{
        return deleteByKey(fuc_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long fuc_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE fuc_id=:fuc_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("fuc_id", fuc_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE fuc_id=?";
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
                 "	`fuc_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`fu_id`  BIGINT(20) COMMENT '//bigint(20)    用户指纹采集和绑卡表外键（finger_userinfo）ID'," +
                 "	`sfz_number`  VARCHAR(60) COMMENT '//varchar(60)    用户身份证号码'," +
                 "	`car_code`  VARCHAR(60) COMMENT '//varchar(60)    用户车牌'," +
                 "	`is_run`  INT(11) COMMENT '//int(11)    是否启用(0:启用1：关闭)'," +
                 "	`isi_del`  INT(11) COMMENT '//int(11)    是否删除(0:正常1：逻辑删除)'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    修改时间'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`fu_nd`  VARCHAR(80) COMMENT '//varchar(80)    指纹用户基本信息表(finger_userinfo)ND'," +
                 "	PRIMARY KEY (`fuc_id`)" +
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
