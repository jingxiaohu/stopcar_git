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

//wubo_hardware

@Repository("wubo_hardwareDao")
public class Wubo_hardwareDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Wubo_hardwareDao.class);



    private  String TABLE = "wubo_hardware";

    private  String TABLENAME = "wubo_hardware";

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


    private  String[] carrays ={"wh_id","type","mac","imei","plant_type","state","ctime","utime","loginname","name","note","batch_date","channel_id","version_code"};
    private  String coulmns ="wh_id,type,mac,imei,plant_type,state,ctime,utime,loginname,name,note,batch_date,channel_id,version_code";
    private  String coulmns2 ="type,mac,imei,plant_type,state,ctime,utime,loginname,name,note,batch_date,channel_id,version_code";

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
    public int insert(Wubo_hardware bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Wubo_hardware bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (type,mac,imei,plant_type,state,ctime,utime,loginname,name,note,batch_date,channel_id,version_code) VALUES (:type,:mac,:imei,:plant_type,:state,:ctime,:utime,:loginname,:name,:note,:batch_date,:channel_id,:version_code)";
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
    public int insert_primarykey(Wubo_hardware bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Wubo_hardware bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (wh_id,type,mac,imei,plant_type,state,ctime,utime,loginname,name,note,batch_date,channel_id,version_code) VALUES (:wh_id,:type,:mac,:imei,:plant_type,:state,:ctime,:utime,:loginname,:name,:note,:batch_date,:channel_id,:version_code)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Wubo_hardware> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Wubo_hardware> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (type,mac,imei,plant_type,state,ctime,utime,loginname,name,note,batch_date,channel_id,version_code) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Wubo_hardware bean = beans.get(i);
                    ps.setInt(1, bean.type);
                    ps.setString(2, bean.mac);
                    ps.setString(3, bean.imei);
                    ps.setInt(4, bean.plant_type);
                    ps.setInt(5, bean.state);
                    ps.setTimestamp(6, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(7, new Timestamp(bean.utime.getTime()));
                    ps.setString(8, bean.loginname);
                    ps.setString(9, bean.name);
                    ps.setString(10, bean.note);
                    ps.setString(11, bean.batch_date);
                    ps.setLong(12, bean.channel_id);
                    ps.setInt(13, bean.version_code);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Wubo_hardware> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Wubo_hardware> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT wh_id,type,mac,imei,plant_type,state,ctime,utime,loginname,name,note,batch_date,channel_id,version_code FROM "+TABLENAME2+" ORDER BY wh_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Wubo_hardware>(Wubo_hardware.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Wubo_hardware>();
        }
    }

    //查询最新数据
    public List<Wubo_hardware> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Wubo_hardware> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT wh_id,type,mac,imei,plant_type,state,ctime,utime,loginname,name,note,batch_date,channel_id,version_code FROM "+TABLENAME2+" ORDER BY wh_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Wubo_hardware>(Wubo_hardware.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Wubo_hardware>();
        }
    }

    //根据主键查询
    public List<Wubo_hardware> selectGtKey(long wh_id) {
        return selectGtKey(wh_id, TABLENAME);
    }

    //根据主键查询
    public List<Wubo_hardware> selectGtKey(long wh_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT wh_id,type,mac,imei,plant_type,state,ctime,utime,loginname,name,note,batch_date,channel_id,version_code FROM "+TABLENAME2+" WHERE wh_id>:wh_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("wh_id", wh_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Wubo_hardware>(Wubo_hardware.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Wubo_hardware>();
        }
    }

    //根据主键查询
    public Wubo_hardware selectByKey(long wh_id) {
        return selectByKey(wh_id, TABLENAME);
    }

    //根据主键查询
    public Wubo_hardware selectByKey(long wh_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT wh_id,type,mac,imei,plant_type,state,ctime,utime,loginname,name,note,batch_date,channel_id,version_code FROM "+TABLENAME2+" WHERE wh_id=:wh_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("wh_id", wh_id);
            List<Wubo_hardware> list =  _np.query(sql, param, new BeanPropertyRowMapper<Wubo_hardware>(Wubo_hardware.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey wh_id="+wh_id,e);
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
    public List<Wubo_hardware> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Wubo_hardware> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT wh_id,type,mac,imei,plant_type,state,ctime,utime,loginname,name,note,batch_date,channel_id,version_code FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Wubo_hardware>(Wubo_hardware.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Wubo_hardware>();
        }
    }

    //修改数据
    public int updateByKey(Wubo_hardware bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Wubo_hardware bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET type=:type,mac=:mac,imei=:imei,plant_type=:plant_type,state=:state,ctime=:ctime,utime=:utime,loginname=:loginname,name=:name,note=:note,batch_date=:batch_date,channel_id=:channel_id,version_code=:version_code WHERE wh_id=:wh_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Wubo_hardware> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Wubo_hardware> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET type=?,mac=?,imei=?,plant_type=?,state=?,ctime=?,utime=?,loginname=?,name=?,note=?,batch_date=?,channel_id=?,version_code=? WHERE wh_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Wubo_hardware bean = beans.get(i);
                    ps.setInt(1, bean.type);
                    ps.setString(2, bean.mac);
                    ps.setString(3, bean.imei);
                    ps.setInt(4, bean.plant_type);
                    ps.setInt(5, bean.state);
                    ps.setTimestamp(6, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(7, new Timestamp(bean.utime.getTime()));
                    ps.setString(8, bean.loginname);
                    ps.setString(9, bean.name);
                    ps.setString(10, bean.note);
                    ps.setString(11, bean.batch_date);
                    ps.setLong(12, bean.channel_id);
                    ps.setInt(13, bean.version_code);
                    ps.setLong(14, bean.wh_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long wh_id) throws SQLException{
        return deleteByKey(wh_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long wh_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE wh_id=:wh_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("wh_id", wh_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE wh_id=?";
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
                 "	`wh_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`type`  INT(11) COMMENT '//int(11)    硬件产品类型(0：未知1：地磁小车位设备2：地磁android板收集器)'," +
                 "	`mac`  VARCHAR(12) COMMENT '//varchar(12)    硬件MAC地址（12位）'," +
                 "	`imei`  VARCHAR(20) COMMENT '//varchar(20)    硬件串号(目前暂不使用20位8位日期+12位随机数字)'," +
                 "	`plant_type`  INT(11) COMMENT '//int(11)    工厂状态（0：分配成功1：进入生产2：成品入库3：外放投入使用）'," +
                 "	`state`  INT(11) COMMENT '//int(11)    硬件状态(0:正常1：故障)'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`loginname`  VARCHAR(30) COMMENT '//varchar(30)    操作人员帐号'," +
                 "	`name`  VARCHAR(30) COMMENT '//varchar(30)    操作人姓名'," +
                 "	`note`  TINYTEXT COMMENT '//varchar(255)    备注'," +
                 "	`batch_date`  VARCHAR(30) COMMENT '//varchar(30)    生产批次（例如：20170804）'," +
                 "	`channel_id`  BIGINT(20) COMMENT '//bigint(20)    逻辑渠道表主键ID(外键)'," +
                 "	`version_code`  INT(11) COMMENT '//int(11)    当前内部升级版本号'," +
                 "	PRIMARY KEY (`wh_id`)" +
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
