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

//park_device

@Repository("park_deviceDao")
public class Park_deviceDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Park_deviceDao.class);



    private  String TABLE = "park_device";

    private  String TABLENAME = "park_device";

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


    private  String[] carrays ={"pd_id","pi_id","in_out","in_out_code","camera","camera_mac","signo_name","solid_garage_mac","solid_garage_sn","ctime","utime","pd_md5","note","area_code"};
    private  String coulmns ="pd_id,pi_id,in_out,in_out_code,camera,camera_mac,signo_name,solid_garage_mac,solid_garage_sn,ctime,utime,pd_md5,note,area_code";
    private  String coulmns2 ="pi_id,in_out,in_out_code,camera,camera_mac,signo_name,solid_garage_mac,solid_garage_sn,ctime,utime,pd_md5,note,area_code";

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
    public int insert(Park_device bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Park_device bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,in_out,in_out_code,camera,camera_mac,signo_name,solid_garage_mac,solid_garage_sn,ctime,utime,pd_md5,note,area_code) VALUES (:pi_id,:in_out,:in_out_code,:camera,:camera_mac,:signo_name,:solid_garage_mac,:solid_garage_sn,:ctime,:utime,:pd_md5,:note,:area_code)";
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
    public int insert_primarykey(Park_device bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Park_device bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pd_id,pi_id,in_out,in_out_code,camera,camera_mac,signo_name,solid_garage_mac,solid_garage_sn,ctime,utime,pd_md5,note,area_code) VALUES (:pd_id,:pi_id,:in_out,:in_out_code,:camera,:camera_mac,:signo_name,:solid_garage_mac,:solid_garage_sn,:ctime,:utime,:pd_md5,:note,:area_code)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Park_device> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Park_device> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,in_out,in_out_code,camera,camera_mac,signo_name,solid_garage_mac,solid_garage_sn,ctime,utime,pd_md5,note,area_code) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_device bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setString(2, bean.in_out);
                    ps.setString(3, bean.in_out_code);
                    ps.setString(4, bean.camera);
                    ps.setString(5, bean.camera_mac);
                    ps.setString(6, bean.signo_name);
                    ps.setString(7, bean.solid_garage_mac);
                    ps.setString(8, bean.solid_garage_sn);
                    ps.setTimestamp(9, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(10, new Timestamp(bean.utime.getTime()));
                    ps.setString(11, bean.pd_md5);
                    ps.setString(12, bean.note);
                    ps.setString(13, bean.area_code);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Park_device> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Park_device> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pd_id,pi_id,in_out,in_out_code,camera,camera_mac,signo_name,solid_garage_mac,solid_garage_sn,ctime,utime,pd_md5,note,area_code FROM "+TABLENAME2+" ORDER BY pd_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_device>(Park_device.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Park_device>();
        }
    }

    //查询最新数据
    public List<Park_device> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Park_device> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pd_id,pi_id,in_out,in_out_code,camera,camera_mac,signo_name,solid_garage_mac,solid_garage_sn,ctime,utime,pd_md5,note,area_code FROM "+TABLENAME2+" ORDER BY pd_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_device>(Park_device.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Park_device>();
        }
    }

    //根据主键查询
    public List<Park_device> selectGtKey(long pd_id) {
        return selectGtKey(pd_id, TABLENAME);
    }

    //根据主键查询
    public List<Park_device> selectGtKey(long pd_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pd_id,pi_id,in_out,in_out_code,camera,camera_mac,signo_name,solid_garage_mac,solid_garage_sn,ctime,utime,pd_md5,note,area_code FROM "+TABLENAME2+" WHERE pd_id>:pd_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pd_id", pd_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Park_device>(Park_device.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Park_device>();
        }
    }

    //根据主键查询
    public Park_device selectByKey(long pd_id) {
        return selectByKey(pd_id, TABLENAME);
    }

    //根据主键查询
    public Park_device selectByKey(long pd_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pd_id,pi_id,in_out,in_out_code,camera,camera_mac,signo_name,solid_garage_mac,solid_garage_sn,ctime,utime,pd_md5,note,area_code FROM "+TABLENAME2+" WHERE pd_id=:pd_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pd_id", pd_id);
            List<Park_device> list =  _np.query(sql, param, new BeanPropertyRowMapper<Park_device>(Park_device.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey pd_id="+pd_id,e);
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
    public List<Park_device> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Park_device> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT pd_id,pi_id,in_out,in_out_code,camera,camera_mac,signo_name,solid_garage_mac,solid_garage_sn,ctime,utime,pd_md5,note,area_code FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Park_device>(Park_device.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Park_device>();
        }
    }

    //修改数据
    public int updateByKey(Park_device bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Park_device bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=:pi_id,in_out=:in_out,in_out_code=:in_out_code,camera=:camera,camera_mac=:camera_mac,signo_name=:signo_name,solid_garage_mac=:solid_garage_mac,solid_garage_sn=:solid_garage_sn,ctime=:ctime,utime=:utime,pd_md5=:pd_md5,note=:note,area_code=:area_code WHERE pd_id=:pd_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_device> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_device> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=?,in_out=?,in_out_code=?,camera=?,camera_mac=?,signo_name=?,solid_garage_mac=?,solid_garage_sn=?,ctime=?,utime=?,pd_md5=?,note=?,area_code=? WHERE pd_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_device bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setString(2, bean.in_out);
                    ps.setString(3, bean.in_out_code);
                    ps.setString(4, bean.camera);
                    ps.setString(5, bean.camera_mac);
                    ps.setString(6, bean.signo_name);
                    ps.setString(7, bean.solid_garage_mac);
                    ps.setString(8, bean.solid_garage_sn);
                    ps.setTimestamp(9, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(10, new Timestamp(bean.utime.getTime()));
                    ps.setString(11, bean.pd_md5);
                    ps.setString(12, bean.note);
                    ps.setString(13, bean.area_code);
                    ps.setLong(14, bean.pd_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long pd_id) throws SQLException{
        return deleteByKey(pd_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long pd_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE pd_id=:pd_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pd_id", pd_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE pd_id=?";
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
                 "	`pd_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    '," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    '," +
                 "	`in_out`  VARCHAR(30) COMMENT '//varchar(30)    出口或者入口入口：enter出口：exit'," +
                 "	`in_out_code`  VARCHAR(60) COMMENT '//varchar(60)    出入口编号例如（A出口B入口）'," +
                 "	`camera`  VARCHAR(100) COMMENT '//varchar(100)    摄像头'," +
                 "	`camera_mac`  VARCHAR(100) COMMENT '//varchar(100)    摄像头MAC'," +
                 "	`signo_name`  VARCHAR(100) COMMENT '//varchar(100)    道闸名称'," +
                 "	`solid_garage_mac`  VARCHAR(80) COMMENT '//varchar(80)    立体车库设备MAC'," +
                 "	`solid_garage_sn`  VARCHAR(60) COMMENT '//varchar(60)    立体车库设备编号'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`pd_md5`  VARCHAR(80) COMMENT '//varchar(80)    校验MD5(pi_id+in_out+in_out_code+camera_mac+signo_name+solid_garage_mac)'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`area_code`  VARCHAR(20) COMMENT '//varchar(20)    省市区代码'," +
                 "	PRIMARY KEY (`pd_id`)" +
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
