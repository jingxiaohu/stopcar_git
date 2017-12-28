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

//user_park_coupon

@Repository("user_park_couponDao")
public class User_park_couponDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(User_park_couponDao.class);



    private  String TABLE = "user_park_coupon";

    private  String TABLENAME = "user_park_coupon";

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


    private  String[] carrays ={"upc_id","ui_id","pc_id","money","discount","high_money","upc_type","end_time","upc_state","ctime","utime","note","send_unit","ai_id","state"};
    private  String coulmns ="upc_id,ui_id,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,note,send_unit,ai_id,state";
    private  String coulmns2 ="ui_id,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,note,send_unit,ai_id,state";

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
    public int insert(User_park_coupon bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(User_park_coupon bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (ui_id,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,note,send_unit,ai_id,state) VALUES (:ui_id,:pc_id,:money,:discount,:high_money,:upc_type,:end_time,:upc_state,:ctime,:utime,:note,:send_unit,:ai_id,:state)";
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
    public int insert_primarykey(User_park_coupon bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(User_park_coupon bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (upc_id,ui_id,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,note,send_unit,ai_id,state) VALUES (:upc_id,:ui_id,:pc_id,:money,:discount,:high_money,:upc_type,:end_time,:upc_state,:ctime,:utime,:note,:send_unit,:ai_id,:state)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<User_park_coupon> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<User_park_coupon> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (ui_id,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,note,send_unit,ai_id,state) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    User_park_coupon bean = beans.get(i);
                    ps.setLong(1, bean.ui_id);
                    ps.setLong(2, bean.pc_id);
                    ps.setInt(3, bean.money);
                    ps.setDouble(4, bean.discount);
                    ps.setInt(5, bean.high_money);
                    ps.setInt(6, bean.upc_type);
                    ps.setTimestamp(7, new Timestamp(bean.end_time.getTime()));
                    ps.setInt(8, bean.upc_state);
                    ps.setTimestamp(9, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(10, new Timestamp(bean.utime.getTime()));
                    ps.setString(11, bean.note);
                    ps.setInt(12, bean.send_unit);
                    ps.setLong(13, bean.ai_id);
                    ps.setInt(14, bean.state);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<User_park_coupon> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<User_park_coupon> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT upc_id,ui_id,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,note,send_unit,ai_id,state FROM "+TABLENAME2+" ORDER BY upc_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<User_park_coupon>(User_park_coupon.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<User_park_coupon>();
        }
    }

    //查询最新数据
    public List<User_park_coupon> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<User_park_coupon> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT upc_id,ui_id,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,note,send_unit,ai_id,state FROM "+TABLENAME2+" ORDER BY upc_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<User_park_coupon>(User_park_coupon.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<User_park_coupon>();
        }
    }

    //根据主键查询
    public List<User_park_coupon> selectGtKey(long upc_id) {
        return selectGtKey(upc_id, TABLENAME);
    }

    //根据主键查询
    public List<User_park_coupon> selectGtKey(long upc_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT upc_id,ui_id,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,note,send_unit,ai_id,state FROM "+TABLENAME2+" WHERE upc_id>:upc_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("upc_id", upc_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<User_park_coupon>(User_park_coupon.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<User_park_coupon>();
        }
    }

    //根据主键查询
    public User_park_coupon selectByKey(long upc_id) {
        return selectByKey(upc_id, TABLENAME);
    }

    //根据主键查询
    public User_park_coupon selectByKey(long upc_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT upc_id,ui_id,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,note,send_unit,ai_id,state FROM "+TABLENAME2+" WHERE upc_id=:upc_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("upc_id", upc_id);
            List<User_park_coupon> list =  _np.query(sql, param, new BeanPropertyRowMapper<User_park_coupon>(User_park_coupon.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey upc_id="+upc_id,e);
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
    public List<User_park_coupon> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<User_park_coupon> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT upc_id,ui_id,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,note,send_unit,ai_id,state FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<User_park_coupon>(User_park_coupon.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<User_park_coupon>();
        }
    }

    //修改数据
    public int updateByKey(User_park_coupon bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(User_park_coupon bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET ui_id=:ui_id,pc_id=:pc_id,money=:money,discount=:discount,high_money=:high_money,upc_type=:upc_type,end_time=:end_time,upc_state=:upc_state,ctime=:ctime,utime=:utime,note=:note,send_unit=:send_unit,ai_id=:ai_id,state=:state WHERE upc_id=:upc_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<User_park_coupon> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<User_park_coupon> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET ui_id=?,pc_id=?,money=?,discount=?,high_money=?,upc_type=?,end_time=?,upc_state=?,ctime=?,utime=?,note=?,send_unit=?,ai_id=?,state=? WHERE upc_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    User_park_coupon bean = beans.get(i);
                    ps.setLong(1, bean.ui_id);
                    ps.setLong(2, bean.pc_id);
                    ps.setInt(3, bean.money);
                    ps.setDouble(4, bean.discount);
                    ps.setInt(5, bean.high_money);
                    ps.setInt(6, bean.upc_type);
                    ps.setTimestamp(7, new Timestamp(bean.end_time.getTime()));
                    ps.setInt(8, bean.upc_state);
                    ps.setTimestamp(9, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(10, new Timestamp(bean.utime.getTime()));
                    ps.setString(11, bean.note);
                    ps.setInt(12, bean.send_unit);
                    ps.setLong(13, bean.ai_id);
                    ps.setInt(14, bean.state);
                    ps.setLong(15, bean.upc_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long upc_id) throws SQLException{
        return deleteByKey(upc_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long upc_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE upc_id=:upc_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("upc_id", upc_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE upc_id=?";
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
                 "	`upc_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`ui_id`  BIGINT(20) COMMENT '//bigint(20)    主键ID'," +
                 "	`pc_id`  BIGINT(20) COMMENT '//bigint(20)    停车优惠券表主键ID'," +
                 "	`money`  INT(11) COMMENT '//int(11)    优惠券金额'," +
                 "	`discount`  DOUBLE COMMENT '//double(2,2)    折扣券折数'," +
                 "	`high_money`  INT(11) COMMENT '//int(11)    最高抵扣金额'," +
                 "	`upc_type`  INT(11) COMMENT '//int(11)    优惠券类型0:金额券1：折扣券'," +
                 "	`end_time`  DATETIME COMMENT '//datetime    有效期'," +
                 "	`upc_state`  INT(11) COMMENT '//int(11)    使用状态0：未使用1：已使用'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`send_unit`  INT(11) COMMENT '//int(11)    赠送单位(0:吾泊平台1：龙支付)'," +
                 "	`ai_id`  BIGINT(20) COMMENT '//bigint(20)    活动ID（活动表主键ID）'," +
                 "	`state`  INT(11) COMMENT '//int(11)    是否进行过过期前提醒推送（0：没有推送1：已经推送过了）'," +
                 "	PRIMARY KEY (`upc_id`)" +
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
