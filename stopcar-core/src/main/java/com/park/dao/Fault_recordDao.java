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

//fault_record

@Repository("fault_recordDao")
public class Fault_recordDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Fault_recordDao.class);



    private  String TABLE = "fault_record";

    private  String TABLENAME = "fault_record";

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


    private  String[] carrays ={"fr_id","pi_id","pd_id","fr_type","fr_desc","ctime","utime","area_code","note"};
    private  String coulmns ="fr_id,pi_id,pd_id,fr_type,fr_desc,ctime,utime,area_code,note";
    private  String coulmns2 ="pi_id,pd_id,fr_type,fr_desc,ctime,utime,area_code,note";

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
    public int insert(Fault_record bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Fault_record bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,pd_id,fr_type,fr_desc,ctime,utime,area_code,note) VALUES (:pi_id,:pd_id,:fr_type,:fr_desc,:ctime,:utime,:area_code,:note)";
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
    public int insert_primarykey(Fault_record bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Fault_record bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (fr_id,pi_id,pd_id,fr_type,fr_desc,ctime,utime,area_code,note) VALUES (:fr_id,:pi_id,:pd_id,:fr_type,:fr_desc,:ctime,:utime,:area_code,:note)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Fault_record> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Fault_record> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,pd_id,fr_type,fr_desc,ctime,utime,area_code,note) VALUES (?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Fault_record bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setLong(2, bean.pd_id);
                    ps.setInt(3, bean.fr_type);
                    ps.setString(4, bean.fr_desc);
                    ps.setTimestamp(5, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(6, new Timestamp(bean.utime.getTime()));
                    ps.setString(7, bean.area_code);
                    ps.setString(8, bean.note);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Fault_record> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Fault_record> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT fr_id,pi_id,pd_id,fr_type,fr_desc,ctime,utime,area_code,note FROM "+TABLENAME2+" ORDER BY fr_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Fault_record>(Fault_record.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Fault_record>();
        }
    }

    //查询最新数据
    public List<Fault_record> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Fault_record> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT fr_id,pi_id,pd_id,fr_type,fr_desc,ctime,utime,area_code,note FROM "+TABLENAME2+" ORDER BY fr_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Fault_record>(Fault_record.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Fault_record>();
        }
    }

    //根据主键查询
    public List<Fault_record> selectGtKey(long fr_id) {
        return selectGtKey(fr_id, TABLENAME);
    }

    //根据主键查询
    public List<Fault_record> selectGtKey(long fr_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT fr_id,pi_id,pd_id,fr_type,fr_desc,ctime,utime,area_code,note FROM "+TABLENAME2+" WHERE fr_id>:fr_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("fr_id", fr_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Fault_record>(Fault_record.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Fault_record>();
        }
    }

    //根据主键查询
    public Fault_record selectByKey(long fr_id) {
        return selectByKey(fr_id, TABLENAME);
    }

    //根据主键查询
    public Fault_record selectByKey(long fr_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT fr_id,pi_id,pd_id,fr_type,fr_desc,ctime,utime,area_code,note FROM "+TABLENAME2+" WHERE fr_id=:fr_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("fr_id", fr_id);
            List<Fault_record> list =  _np.query(sql, param, new BeanPropertyRowMapper<Fault_record>(Fault_record.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey fr_id="+fr_id,e);
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
    public List<Fault_record> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Fault_record> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT fr_id,pi_id,pd_id,fr_type,fr_desc,ctime,utime,area_code,note FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Fault_record>(Fault_record.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Fault_record>();
        }
    }

    //修改数据
    public int updateByKey(Fault_record bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Fault_record bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=:pi_id,pd_id=:pd_id,fr_type=:fr_type,fr_desc=:fr_desc,ctime=:ctime,utime=:utime,area_code=:area_code,note=:note WHERE fr_id=:fr_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Fault_record> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Fault_record> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=?,pd_id=?,fr_type=?,fr_desc=?,ctime=?,utime=?,area_code=?,note=? WHERE fr_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Fault_record bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setLong(2, bean.pd_id);
                    ps.setInt(3, bean.fr_type);
                    ps.setString(4, bean.fr_desc);
                    ps.setTimestamp(5, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(6, new Timestamp(bean.utime.getTime()));
                    ps.setString(7, bean.area_code);
                    ps.setString(8, bean.note);
                    ps.setLong(9, bean.fr_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long fr_id) throws SQLException{
        return deleteByKey(fr_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long fr_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE fr_id=:fr_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("fr_id", fr_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE fr_id=?";
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
                 "	`fr_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    场地主键ID'," +
                 "	`pd_id`  BIGINT(20) COMMENT '//bigint(20)    出入口设备主键ID'," +
                 "	`fr_type`  INT(11) COMMENT '//int(11)    故障类型0:摄像头故障1：开闸设备故障'," +
                 "	`fr_desc`  TINYTEXT COMMENT '//varchar(255)    故障简述'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    '," +
                 "	`utime`  DATETIME COMMENT '//datetime    '," +
                 "	`area_code`  VARCHAR(20) COMMENT '//varchar(20)    省市区编号'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    '," +
                 "	PRIMARY KEY (`fr_id`)" +
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
