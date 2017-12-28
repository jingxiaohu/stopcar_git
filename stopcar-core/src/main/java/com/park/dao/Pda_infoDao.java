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

//pda_info

@Repository("pda_infoDao")
public class Pda_infoDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Pda_infoDao.class);



    private  String TABLE = "pda_info";

    private  String TABLENAME = "pda_info";

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


    private  String[] carrays ={"pda_id","mac","pda_sn","plate_license","loginname","password","pi_id","area_code","pu_id","pu_nd","ctime","utime","note","is_initialize","lng","lat","pi_name","pda_c_id","vnum","token"};
    private  String coulmns ="pda_id,mac,pda_sn,plate_license,loginname,password,pi_id,area_code,pu_id,pu_nd,ctime,utime,note,is_initialize,lng,lat,pi_name,pda_c_id,vnum,token";
    private  String coulmns2 ="mac,pda_sn,plate_license,loginname,password,pi_id,area_code,pu_id,pu_nd,ctime,utime,note,is_initialize,lng,lat,pi_name,pda_c_id,vnum,token";

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
    public int insert(Pda_info bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Pda_info bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (mac,pda_sn,plate_license,loginname,password,pi_id,area_code,pu_id,pu_nd,ctime,utime,note,is_initialize,lng,lat,pi_name,pda_c_id,vnum,token) VALUES (:mac,:pda_sn,:plate_license,:loginname,:password,:pi_id,:area_code,:pu_id,:pu_nd,:ctime,:utime,:note,:is_initialize,:lng,:lat,:pi_name,:pda_c_id,:vnum,:token)";
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
    public int insert_primarykey(Pda_info bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Pda_info bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pda_id,mac,pda_sn,plate_license,loginname,password,pi_id,area_code,pu_id,pu_nd,ctime,utime,note,is_initialize,lng,lat,pi_name,pda_c_id,vnum,token) VALUES (:pda_id,:mac,:pda_sn,:plate_license,:loginname,:password,:pi_id,:area_code,:pu_id,:pu_nd,:ctime,:utime,:note,:is_initialize,:lng,:lat,:pi_name,:pda_c_id,:vnum,:token)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Pda_info> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Pda_info> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (mac,pda_sn,plate_license,loginname,password,pi_id,area_code,pu_id,pu_nd,ctime,utime,note,is_initialize,lng,lat,pi_name,pda_c_id,vnum,token) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Pda_info bean = beans.get(i);
                    ps.setString(1, bean.mac);
                    ps.setString(2, bean.pda_sn);
                    ps.setString(3, bean.plate_license);
                    ps.setString(4, bean.loginname);
                    ps.setString(5, bean.password);
                    ps.setLong(6, bean.pi_id);
                    ps.setString(7, bean.area_code);
                    ps.setLong(8, bean.pu_id);
                    ps.setString(9, bean.pu_nd);
                    ps.setTimestamp(10, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(11, new Timestamp(bean.utime.getTime()));
                    ps.setString(12, bean.note);
                    ps.setInt(13, bean.is_initialize);
                    ps.setDouble(14, bean.lng);
                    ps.setDouble(15, bean.lat);
                    ps.setString(16, bean.pi_name);
                    ps.setLong(17, bean.pda_c_id);
                    ps.setInt(18, bean.vnum);
                    ps.setString(19, bean.token);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Pda_info> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Pda_info> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pda_id,mac,pda_sn,plate_license,loginname,password,pi_id,area_code,pu_id,pu_nd,ctime,utime,note,is_initialize,lng,lat,pi_name,pda_c_id,vnum,token FROM "+TABLENAME2+" ORDER BY pda_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Pda_info>(Pda_info.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Pda_info>();
        }
    }

    //查询最新数据
    public List<Pda_info> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Pda_info> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pda_id,mac,pda_sn,plate_license,loginname,password,pi_id,area_code,pu_id,pu_nd,ctime,utime,note,is_initialize,lng,lat,pi_name,pda_c_id,vnum,token FROM "+TABLENAME2+" ORDER BY pda_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Pda_info>(Pda_info.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Pda_info>();
        }
    }

    //根据主键查询
    public List<Pda_info> selectGtKey(long pda_id) {
        return selectGtKey(pda_id, TABLENAME);
    }

    //根据主键查询
    public List<Pda_info> selectGtKey(long pda_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pda_id,mac,pda_sn,plate_license,loginname,password,pi_id,area_code,pu_id,pu_nd,ctime,utime,note,is_initialize,lng,lat,pi_name,pda_c_id,vnum,token FROM "+TABLENAME2+" WHERE pda_id>:pda_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pda_id", pda_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Pda_info>(Pda_info.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Pda_info>();
        }
    }

    //根据主键查询
    public Pda_info selectByKey(long pda_id) {
        return selectByKey(pda_id, TABLENAME);
    }

    //根据主键查询
    public Pda_info selectByKey(long pda_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pda_id,mac,pda_sn,plate_license,loginname,password,pi_id,area_code,pu_id,pu_nd,ctime,utime,note,is_initialize,lng,lat,pi_name,pda_c_id,vnum,token FROM "+TABLENAME2+" WHERE pda_id=:pda_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pda_id", pda_id);
            List<Pda_info> list =  _np.query(sql, param, new BeanPropertyRowMapper<Pda_info>(Pda_info.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey pda_id="+pda_id,e);
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
    public List<Pda_info> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Pda_info> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT pda_id,mac,pda_sn,plate_license,loginname,password,pi_id,area_code,pu_id,pu_nd,ctime,utime,note,is_initialize,lng,lat,pi_name,pda_c_id,vnum,token FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Pda_info>(Pda_info.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Pda_info>();
        }
    }

    //修改数据
    public int updateByKey(Pda_info bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Pda_info bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET mac=:mac,pda_sn=:pda_sn,plate_license=:plate_license,loginname=:loginname,password=:password,pi_id=:pi_id,area_code=:area_code,pu_id=:pu_id,pu_nd=:pu_nd,ctime=:ctime,utime=:utime,note=:note,is_initialize=:is_initialize,lng=:lng,lat=:lat,pi_name=:pi_name,pda_c_id=:pda_c_id,vnum=:vnum,token=:token WHERE pda_id=:pda_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Pda_info> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Pda_info> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET mac=?,pda_sn=?,plate_license=?,loginname=?,password=?,pi_id=?,area_code=?,pu_id=?,pu_nd=?,ctime=?,utime=?,note=?,is_initialize=?,lng=?,lat=?,pi_name=?,pda_c_id=?,vnum=?,token=? WHERE pda_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Pda_info bean = beans.get(i);
                    ps.setString(1, bean.mac);
                    ps.setString(2, bean.pda_sn);
                    ps.setString(3, bean.plate_license);
                    ps.setString(4, bean.loginname);
                    ps.setString(5, bean.password);
                    ps.setLong(6, bean.pi_id);
                    ps.setString(7, bean.area_code);
                    ps.setLong(8, bean.pu_id);
                    ps.setString(9, bean.pu_nd);
                    ps.setTimestamp(10, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(11, new Timestamp(bean.utime.getTime()));
                    ps.setString(12, bean.note);
                    ps.setInt(13, bean.is_initialize);
                    ps.setDouble(14, bean.lng);
                    ps.setDouble(15, bean.lat);
                    ps.setString(16, bean.pi_name);
                    ps.setLong(17, bean.pda_c_id);
                    ps.setInt(18, bean.vnum);
                    ps.setString(19, bean.token);
                    ps.setLong(20, bean.pda_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long pda_id) throws SQLException{
        return deleteByKey(pda_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long pda_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE pda_id=:pda_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pda_id", pda_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE pda_id=?";
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
                 "	`pda_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`mac`  VARCHAR(100) COMMENT '//varchar(100)    PDA设备MAC'," +
                 "	`pda_sn`  VARCHAR(100) COMMENT '//varchar(100)    PDA设备串号'," +
                 "	`plate_license`  VARCHAR(150) COMMENT '//varchar(150)    惠通扫描授权串号(序列号)'," +
                 "	`loginname`  VARCHAR(60) COMMENT '//varchar(60)    PDA登录帐号'," +
                 "	`password`  VARCHAR(100) COMMENT '//varchar(100)    PDA登录密码'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    PDA关联的停车场ID'," +
                 "	`area_code`  VARCHAR(60) COMMENT '//varchar(60)    PDA关联的停车场地址编码'," +
                 "	`pu_id`  BIGINT(20) COMMENT '//bigint(20)    PDA关联的商户ID'," +
                 "	`pu_nd`  VARCHAR(80) COMMENT '//varchar(80)    PDA关联的商户的ND'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`is_initialize`  INT(11) COMMENT '//int(11)    是否初始化成功0:没有初始化1：初始化成功'," +
                 "	`lng`  DOUBLE COMMENT '//double    初始化经度'," +
                 "	`lat`  DOUBLE COMMENT '//double    初始化纬度'," +
                 "	`pi_name`  VARCHAR(100) COMMENT '//varchar(100)    停车场名称'," +
                 "	`pda_c_id`  BIGINT(20) COMMENT '//bigint(20)    逻辑渠道表主键ID（外键）'," +
                 "	`vnum`  INT(11) COMMENT '//int(11)    当前内部版本号'," +
                 "	`token`  VARCHAR(80) COMMENT '//varchar(80)    授权token'," +
                 "	PRIMARY KEY (`pda_id`)" +
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
