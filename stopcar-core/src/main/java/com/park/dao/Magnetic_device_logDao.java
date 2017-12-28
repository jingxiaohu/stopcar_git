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

//magnetic_device_log

@Repository("magnetic_device_logDao")
public class Magnetic_device_logDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Magnetic_device_logDao.class);



    private  String TABLE = "magnetic_device_log";

    private  String TABLENAME = "magnetic_device_log";

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


    private  String[] carrays ={"id","pi_id","area_code","gov_num","car_dev_num","android_dev_num","state","ctime","note","carport_total","carport_yet","carport_space"};
    private  String coulmns ="id,pi_id,area_code,gov_num,car_dev_num,android_dev_num,state,ctime,note,carport_total,carport_yet,carport_space";
    private  String coulmns2 ="pi_id,area_code,gov_num,car_dev_num,android_dev_num,state,ctime,note,carport_total,carport_yet,carport_space";

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
    public int insert(Magnetic_device_log bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Magnetic_device_log bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,area_code,gov_num,car_dev_num,android_dev_num,state,ctime,note,carport_total,carport_yet,carport_space) VALUES (:pi_id,:area_code,:gov_num,:car_dev_num,:android_dev_num,:state,:ctime,:note,:carport_total,:carport_yet,:carport_space)";
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
    public int insert_primarykey(Magnetic_device_log bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Magnetic_device_log bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (id,pi_id,area_code,gov_num,car_dev_num,android_dev_num,state,ctime,note,carport_total,carport_yet,carport_space) VALUES (:id,:pi_id,:area_code,:gov_num,:car_dev_num,:android_dev_num,:state,:ctime,:note,:carport_total,:carport_yet,:carport_space)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Magnetic_device_log> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Magnetic_device_log> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,area_code,gov_num,car_dev_num,android_dev_num,state,ctime,note,carport_total,carport_yet,carport_space) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Magnetic_device_log bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setString(2, bean.area_code);
                    ps.setString(3, bean.gov_num);
                    ps.setString(4, bean.car_dev_num);
                    ps.setString(5, bean.android_dev_num);
                    ps.setInt(6, bean.state);
                    ps.setTimestamp(7, new Timestamp(bean.ctime.getTime()));
                    ps.setString(8, bean.note);
                    ps.setInt(9, bean.carport_total);
                    ps.setInt(10, bean.carport_yet);
                    ps.setInt(11, bean.carport_space);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Magnetic_device_log> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Magnetic_device_log> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,pi_id,area_code,gov_num,car_dev_num,android_dev_num,state,ctime,note,carport_total,carport_yet,carport_space FROM "+TABLENAME2+" ORDER BY id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Magnetic_device_log>(Magnetic_device_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Magnetic_device_log>();
        }
    }

    //查询最新数据
    public List<Magnetic_device_log> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Magnetic_device_log> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,pi_id,area_code,gov_num,car_dev_num,android_dev_num,state,ctime,note,carport_total,carport_yet,carport_space FROM "+TABLENAME2+" ORDER BY id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Magnetic_device_log>(Magnetic_device_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Magnetic_device_log>();
        }
    }

    //根据主键查询
    public List<Magnetic_device_log> selectGtKey(long id) {
        return selectGtKey(id, TABLENAME);
    }

    //根据主键查询
    public List<Magnetic_device_log> selectGtKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,pi_id,area_code,gov_num,car_dev_num,android_dev_num,state,ctime,note,carport_total,carport_yet,carport_space FROM "+TABLENAME2+" WHERE id>:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Magnetic_device_log>(Magnetic_device_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Magnetic_device_log>();
        }
    }

    //根据主键查询
    public Magnetic_device_log selectByKey(long id) {
        return selectByKey(id, TABLENAME);
    }

    //根据主键查询
    public Magnetic_device_log selectByKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,pi_id,area_code,gov_num,car_dev_num,android_dev_num,state,ctime,note,carport_total,carport_yet,carport_space FROM "+TABLENAME2+" WHERE id=:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            List<Magnetic_device_log> list =  _np.query(sql, param, new BeanPropertyRowMapper<Magnetic_device_log>(Magnetic_device_log.class));
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
    public List<Magnetic_device_log> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Magnetic_device_log> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT id,pi_id,area_code,gov_num,car_dev_num,android_dev_num,state,ctime,note,carport_total,carport_yet,carport_space FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Magnetic_device_log>(Magnetic_device_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Magnetic_device_log>();
        }
    }

    //修改数据
    public int updateByKey(Magnetic_device_log bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Magnetic_device_log bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=:pi_id,area_code=:area_code,gov_num=:gov_num,car_dev_num=:car_dev_num,android_dev_num=:android_dev_num,state=:state,ctime=:ctime,note=:note,carport_total=:carport_total,carport_yet=:carport_yet,carport_space=:carport_space WHERE id=:id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Magnetic_device_log> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Magnetic_device_log> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=?,area_code=?,gov_num=?,car_dev_num=?,android_dev_num=?,state=?,ctime=?,note=?,carport_total=?,carport_yet=?,carport_space=? WHERE id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Magnetic_device_log bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setString(2, bean.area_code);
                    ps.setString(3, bean.gov_num);
                    ps.setString(4, bean.car_dev_num);
                    ps.setString(5, bean.android_dev_num);
                    ps.setInt(6, bean.state);
                    ps.setTimestamp(7, new Timestamp(bean.ctime.getTime()));
                    ps.setString(8, bean.note);
                    ps.setInt(9, bean.carport_total);
                    ps.setInt(10, bean.carport_yet);
                    ps.setInt(11, bean.carport_space);
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
                 "	`id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    '," +
                 "	`pi_id`  BIGINT(20) NOT NULL COMMENT '//bigint(20)    停车场主键ID'," +
                 "	`area_code`  VARCHAR(60) NOT NULL COMMENT '//varchar(60)    地址编码'," +
                 "	`gov_num`  VARCHAR(60) NOT NULL COMMENT '//varchar(60)    政府拟定的车位编码（例如：ASD123）'," +
                 "	`car_dev_num`  VARCHAR(60) NOT NULL COMMENT '//varchar(60)    车位设备编码'," +
                 "	`android_dev_num`  VARCHAR(60) NOT NULL COMMENT '//varchar(60)    android板子设备编码'," +
                 "	`state`  INT(11) NOT NULL COMMENT '//int(11)    车位设备状态（0：无车1：有车2：故障）'," +
                 "	`ctime`  DATETIME NOT NULL COMMENT '//datetime    创建时间'," +
                 "	`note`  VARCHAR(100) NOT NULL COMMENT '//varchar(100)    备注'," +
                 "	`carport_total`  INT(11) COMMENT '//int(11)    车位总数'," +
                 "	`carport_yet`  INT(11) COMMENT '//int(11)    已停车位数'," +
                 "	`carport_space`  INT(11) COMMENT '//int(11)    空余车位数'," +
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
