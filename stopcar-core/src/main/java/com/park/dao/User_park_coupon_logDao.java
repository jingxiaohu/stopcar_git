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

//user_park_coupon_log

@Repository("user_park_coupon_logDao")
public class User_park_coupon_logDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(User_park_coupon_logDao.class);



    private  String TABLE = "user_park_coupon_log";

    private  String TABLENAME = "user_park_coupon_log";

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


    private  String[] carrays ={"upcl_id","upc_id","ui_id","ui_nd","pc_id","money","discount","high_money","upc_type","end_time","upc_state","ctime","utime","send_unit","ai_id","state","note","type","rtime","accept_ui_id","accept_ui_nd"};
    private  String coulmns ="upcl_id,upc_id,ui_id,ui_nd,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,send_unit,ai_id,state,note,type,rtime,accept_ui_id,accept_ui_nd";
    private  String coulmns2 ="upc_id,ui_id,ui_nd,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,send_unit,ai_id,state,note,type,rtime,accept_ui_id,accept_ui_nd";

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
    public int insert(User_park_coupon_log bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(User_park_coupon_log bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (upc_id,ui_id,ui_nd,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,send_unit,ai_id,state,note,type,rtime,accept_ui_id,accept_ui_nd) VALUES (:upc_id,:ui_id,:ui_nd,:pc_id,:money,:discount,:high_money,:upc_type,:end_time,:upc_state,:ctime,:utime,:send_unit,:ai_id,:state,:note,:type,:rtime,:accept_ui_id,:accept_ui_nd)";
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
    public int insert_primarykey(User_park_coupon_log bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(User_park_coupon_log bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (upcl_id,upc_id,ui_id,ui_nd,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,send_unit,ai_id,state,note,type,rtime,accept_ui_id,accept_ui_nd) VALUES (:upcl_id,:upc_id,:ui_id,:ui_nd,:pc_id,:money,:discount,:high_money,:upc_type,:end_time,:upc_state,:ctime,:utime,:send_unit,:ai_id,:state,:note,:type,:rtime,:accept_ui_id,:accept_ui_nd)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<User_park_coupon_log> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<User_park_coupon_log> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (upc_id,ui_id,ui_nd,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,send_unit,ai_id,state,note,type,rtime,accept_ui_id,accept_ui_nd) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    User_park_coupon_log bean = beans.get(i);
                    ps.setLong(1, bean.upc_id);
                    ps.setLong(2, bean.ui_id);
                    ps.setString(3, bean.ui_nd);
                    ps.setLong(4, bean.pc_id);
                    ps.setInt(5, bean.money);
                    ps.setDouble(6, bean.discount);
                    ps.setInt(7, bean.high_money);
                    ps.setInt(8, bean.upc_type);
                    ps.setTimestamp(9, new Timestamp(bean.end_time.getTime()));
                    ps.setInt(10, bean.upc_state);
                    ps.setTimestamp(11, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(12, new Timestamp(bean.utime.getTime()));
                    ps.setInt(13, bean.send_unit);
                    ps.setLong(14, bean.ai_id);
                    ps.setInt(15, bean.state);
                    ps.setString(16, bean.note);
                    ps.setInt(17, bean.type);
                    ps.setTimestamp(18, new Timestamp(bean.rtime.getTime()));
                    ps.setLong(19, bean.accept_ui_id);
                    ps.setString(20, bean.accept_ui_nd);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<User_park_coupon_log> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<User_park_coupon_log> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT upcl_id,upc_id,ui_id,ui_nd,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,send_unit,ai_id,state,note,type,rtime,accept_ui_id,accept_ui_nd FROM "+TABLENAME2+" ORDER BY upcl_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<User_park_coupon_log>(User_park_coupon_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<User_park_coupon_log>();
        }
    }

    //查询最新数据
    public List<User_park_coupon_log> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<User_park_coupon_log> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT upcl_id,upc_id,ui_id,ui_nd,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,send_unit,ai_id,state,note,type,rtime,accept_ui_id,accept_ui_nd FROM "+TABLENAME2+" ORDER BY upcl_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<User_park_coupon_log>(User_park_coupon_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<User_park_coupon_log>();
        }
    }

    //根据主键查询
    public List<User_park_coupon_log> selectGtKey(long upcl_id) {
        return selectGtKey(upcl_id, TABLENAME);
    }

    //根据主键查询
    public List<User_park_coupon_log> selectGtKey(long upcl_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT upcl_id,upc_id,ui_id,ui_nd,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,send_unit,ai_id,state,note,type,rtime,accept_ui_id,accept_ui_nd FROM "+TABLENAME2+" WHERE upcl_id>:upcl_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("upcl_id", upcl_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<User_park_coupon_log>(User_park_coupon_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<User_park_coupon_log>();
        }
    }

    //根据主键查询
    public User_park_coupon_log selectByKey(long upcl_id) {
        return selectByKey(upcl_id, TABLENAME);
    }

    //根据主键查询
    public User_park_coupon_log selectByKey(long upcl_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT upcl_id,upc_id,ui_id,ui_nd,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,send_unit,ai_id,state,note,type,rtime,accept_ui_id,accept_ui_nd FROM "+TABLENAME2+" WHERE upcl_id=:upcl_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("upcl_id", upcl_id);
            List<User_park_coupon_log> list =  _np.query(sql, param, new BeanPropertyRowMapper<User_park_coupon_log>(User_park_coupon_log.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey upcl_id="+upcl_id,e);
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
    public List<User_park_coupon_log> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<User_park_coupon_log> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT upcl_id,upc_id,ui_id,ui_nd,pc_id,money,discount,high_money,upc_type,end_time,upc_state,ctime,utime,send_unit,ai_id,state,note,type,rtime,accept_ui_id,accept_ui_nd FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<User_park_coupon_log>(User_park_coupon_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<User_park_coupon_log>();
        }
    }

    //修改数据
    public int updateByKey(User_park_coupon_log bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(User_park_coupon_log bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET upc_id=:upc_id,ui_id=:ui_id,ui_nd=:ui_nd,pc_id=:pc_id,money=:money,discount=:discount,high_money=:high_money,upc_type=:upc_type,end_time=:end_time,upc_state=:upc_state,ctime=:ctime,utime=:utime,send_unit=:send_unit,ai_id=:ai_id,state=:state,note=:note,type=:type,rtime=:rtime,accept_ui_id=:accept_ui_id,accept_ui_nd=:accept_ui_nd WHERE upcl_id=:upcl_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<User_park_coupon_log> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<User_park_coupon_log> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET upc_id=?,ui_id=?,ui_nd=?,pc_id=?,money=?,discount=?,high_money=?,upc_type=?,end_time=?,upc_state=?,ctime=?,utime=?,send_unit=?,ai_id=?,state=?,note=?,type=?,rtime=?,accept_ui_id=?,accept_ui_nd=? WHERE upcl_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    User_park_coupon_log bean = beans.get(i);
                    ps.setLong(1, bean.upc_id);
                    ps.setLong(2, bean.ui_id);
                    ps.setString(3, bean.ui_nd);
                    ps.setLong(4, bean.pc_id);
                    ps.setInt(5, bean.money);
                    ps.setDouble(6, bean.discount);
                    ps.setInt(7, bean.high_money);
                    ps.setInt(8, bean.upc_type);
                    ps.setTimestamp(9, new Timestamp(bean.end_time.getTime()));
                    ps.setInt(10, bean.upc_state);
                    ps.setTimestamp(11, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(12, new Timestamp(bean.utime.getTime()));
                    ps.setInt(13, bean.send_unit);
                    ps.setLong(14, bean.ai_id);
                    ps.setInt(15, bean.state);
                    ps.setString(16, bean.note);
                    ps.setInt(17, bean.type);
                    ps.setTimestamp(18, new Timestamp(bean.rtime.getTime()));
                    ps.setLong(19, bean.accept_ui_id);
                    ps.setString(20, bean.accept_ui_nd);
                    ps.setLong(21, bean.upcl_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long upcl_id) throws SQLException{
        return deleteByKey(upcl_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long upcl_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE upcl_id=:upcl_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("upcl_id", upcl_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE upcl_id=?";
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
                 "	`upcl_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`upc_id`  BIGINT(20) COMMENT '//bigint(20)    主键ID'," +
                 "	`ui_id`  BIGINT(20) COMMENT '//bigint(20)    主键ID'," +
                 "	`ui_nd`  VARCHAR(100) COMMENT '//varchar(100)    用户唯一标识符'," +
                 "	`pc_id`  BIGINT(20) COMMENT '//bigint(20)    停车优惠券表主键ID'," +
                 "	`money`  INT(11) COMMENT '//int(11)    优惠券金额（单位分）'," +
                 "	`discount`  DOUBLE COMMENT '//double(2,2)    折扣券折数'," +
                 "	`high_money`  INT(11) COMMENT '//int(11)    最高抵扣金额（单位分）'," +
                 "	`upc_type`  INT(11) COMMENT '//int(11)    优惠券类型0:金额券1：折扣券'," +
                 "	`end_time`  DATETIME COMMENT '//datetime    有效期'," +
                 "	`upc_state`  INT(11) COMMENT '//int(11)    使用状态0：未使用1：已使用'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`send_unit`  INT(11) COMMENT '//int(11)    赠送单位(0:吾泊平台1：龙支付)'," +
                 "	`ai_id`  BIGINT(20) COMMENT '//bigint(20)    活动ID（活动表主键ID）'," +
                 "	`state`  INT(11) COMMENT '//int(11)    是否进行过过期前提醒推送（0：没有推送1：已经推送过了）'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`type`  INT(11) COMMENT '//int(11)    记录类型（0：未指定1：用户停车使用2：用户扫码转赠3：系统赠送）'," +
                 "	`rtime`  DATETIME COMMENT '//datetime    变更记录时间(该次变更的入库时间)'," +
                 "	`accept_ui_id`  BIGINT(20) COMMENT '//bigint(20)    转赠的代金券接受人用户ID'," +
                 "	`accept_ui_nd`  VARCHAR(80) COMMENT '//varchar(80)    转赠的代金券接受人用户ND'," +
                 "	PRIMARY KEY (`upcl_id`)" +
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
