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

//user_carcode

@Repository("user_carcodeDao")
public class User_carcodeDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(User_carcodeDao.class);



    private  String TABLE = "user_carcode";

    private  String TABLENAME = "user_carcode";

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


    private  String[] carrays ={"uc_id","ui_id","car_code","ctime","utime","run_url","is_default","car_brand","uc_color","note","ui_nd","ui_tel","run_code"};
    private  String coulmns ="uc_id,ui_id,car_code,ctime,utime,run_url,is_default,car_brand,uc_color,note,ui_nd,ui_tel,run_code";
    private  String coulmns2 ="ui_id,car_code,ctime,utime,run_url,is_default,car_brand,uc_color,note,ui_nd,ui_tel,run_code";

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
    public int insert(User_carcode bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(User_carcode bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (ui_id,car_code,ctime,utime,run_url,is_default,car_brand,uc_color,note,ui_nd,ui_tel,run_code) VALUES (:ui_id,:car_code,:ctime,:utime,:run_url,:is_default,:car_brand,:uc_color,:note,:ui_nd,:ui_tel,:run_code)";
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
    public int insert_primarykey(User_carcode bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(User_carcode bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (uc_id,ui_id,car_code,ctime,utime,run_url,is_default,car_brand,uc_color,note,ui_nd,ui_tel,run_code) VALUES (:uc_id,:ui_id,:car_code,:ctime,:utime,:run_url,:is_default,:car_brand,:uc_color,:note,:ui_nd,:ui_tel,:run_code)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<User_carcode> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<User_carcode> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (ui_id,car_code,ctime,utime,run_url,is_default,car_brand,uc_color,note,ui_nd,ui_tel,run_code) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    User_carcode bean = beans.get(i);
                    ps.setLong(1, bean.ui_id);
                    ps.setString(2, bean.car_code);
                    ps.setTimestamp(3, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(4, new Timestamp(bean.utime.getTime()));
                    ps.setString(5, bean.run_url);
                    ps.setInt(6, bean.is_default);
                    ps.setString(7, bean.car_brand);
                    ps.setInt(8, bean.uc_color);
                    ps.setString(9, bean.note);
                    ps.setString(10, bean.ui_nd);
                    ps.setString(11, bean.ui_tel);
                    ps.setString(12, bean.run_code);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<User_carcode> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<User_carcode> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT uc_id,ui_id,car_code,ctime,utime,run_url,is_default,car_brand,uc_color,note,ui_nd,ui_tel,run_code FROM "+TABLENAME2+" ORDER BY uc_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<User_carcode>(User_carcode.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<User_carcode>();
        }
    }

    //查询最新数据
    public List<User_carcode> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<User_carcode> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT uc_id,ui_id,car_code,ctime,utime,run_url,is_default,car_brand,uc_color,note,ui_nd,ui_tel,run_code FROM "+TABLENAME2+" ORDER BY uc_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<User_carcode>(User_carcode.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<User_carcode>();
        }
    }

    //根据主键查询
    public List<User_carcode> selectGtKey(long uc_id) {
        return selectGtKey(uc_id, TABLENAME);
    }

    //根据主键查询
    public List<User_carcode> selectGtKey(long uc_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT uc_id,ui_id,car_code,ctime,utime,run_url,is_default,car_brand,uc_color,note,ui_nd,ui_tel,run_code FROM "+TABLENAME2+" WHERE uc_id>:uc_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("uc_id", uc_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<User_carcode>(User_carcode.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<User_carcode>();
        }
    }

    //根据主键查询
    public User_carcode selectByKey(long uc_id) {
        return selectByKey(uc_id, TABLENAME);
    }

    //根据主键查询
    public User_carcode selectByKey(long uc_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT uc_id,ui_id,car_code,ctime,utime,run_url,is_default,car_brand,uc_color,note,ui_nd,ui_tel,run_code FROM "+TABLENAME2+" WHERE uc_id=:uc_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("uc_id", uc_id);
            List<User_carcode> list =  _np.query(sql, param, new BeanPropertyRowMapper<User_carcode>(User_carcode.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey uc_id="+uc_id,e);
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
    public List<User_carcode> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<User_carcode> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT uc_id,ui_id,car_code,ctime,utime,run_url,is_default,car_brand,uc_color,note,ui_nd,ui_tel,run_code FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<User_carcode>(User_carcode.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<User_carcode>();
        }
    }

    //修改数据
    public int updateByKey(User_carcode bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(User_carcode bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET ui_id=:ui_id,car_code=:car_code,ctime=:ctime,utime=:utime,run_url=:run_url,is_default=:is_default,car_brand=:car_brand,uc_color=:uc_color,note=:note,ui_nd=:ui_nd,ui_tel=:ui_tel,run_code=:run_code WHERE uc_id=:uc_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<User_carcode> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<User_carcode> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET ui_id=?,car_code=?,ctime=?,utime=?,run_url=?,is_default=?,car_brand=?,uc_color=?,note=?,ui_nd=?,ui_tel=?,run_code=? WHERE uc_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    User_carcode bean = beans.get(i);
                    ps.setLong(1, bean.ui_id);
                    ps.setString(2, bean.car_code);
                    ps.setTimestamp(3, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(4, new Timestamp(bean.utime.getTime()));
                    ps.setString(5, bean.run_url);
                    ps.setInt(6, bean.is_default);
                    ps.setString(7, bean.car_brand);
                    ps.setInt(8, bean.uc_color);
                    ps.setString(9, bean.note);
                    ps.setString(10, bean.ui_nd);
                    ps.setString(11, bean.ui_tel);
                    ps.setString(12, bean.run_code);
                    ps.setLong(13, bean.uc_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long uc_id) throws SQLException{
        return deleteByKey(uc_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long uc_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE uc_id=:uc_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("uc_id", uc_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE uc_id=?";
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
                 "	`uc_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    '," +
                 "	`ui_id`  BIGINT(20) COMMENT '//bigint(20)    用户ID'," +
                 "	`car_code`  VARCHAR(80) COMMENT '//varchar(80)    车牌号码'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`run_url`  VARCHAR(200) COMMENT '//varchar(200)    行驶证照片'," +
                 "	`is_default`  INT(11) COMMENT '//int(11)    是否是默认车牌号选定:0：不是1：是默认车牌'," +
                 "	`car_brand`  VARCHAR(100) COMMENT '//varchar(100)    车辆品牌'," +
                 "	`uc_color`  INT(11) COMMENT '//int(11)    车辆颜色:0未定1：黑色2：白色3：银色4：金色5：红色6：黄色7：绿色8：蓝色9：橙色10：灰色'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`ui_nd`  VARCHAR(100) COMMENT '//varchar(100)    用户唯一标识uuid'," +
                 "	`ui_tel`  VARCHAR(100) COMMENT '//varchar(100)    用户电话号码'," +
                 "	`run_code`  VARCHAR(60) COMMENT '//varchar(60)    行驶证号码'," +
                 "	PRIMARY KEY (`uc_id`)" +
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
