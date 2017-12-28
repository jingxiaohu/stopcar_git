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

//activity_event

@Repository("activity_eventDao")
public class Activity_eventDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Activity_eventDao.class);



    private  String TABLE = "activity_event";

    private  String TABLENAME = "activity_event";

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


    private  String[] carrays ={"id","type","ui_id","ui_nd","pi_id","pi_name","area_code","car_code","order_type","orderid","ai_id","money","ctime","state","utime","ai_money","note","md5","send_unit","act_type","method_name","pay_source"};
    private  String coulmns ="id,type,ui_id,ui_nd,pi_id,pi_name,area_code,car_code,order_type,orderid,ai_id,money,ctime,state,utime,ai_money,note,md5,send_unit,act_type,method_name,pay_source";
    private  String coulmns2 ="type,ui_id,ui_nd,pi_id,pi_name,area_code,car_code,order_type,orderid,ai_id,money,ctime,state,utime,ai_money,note,md5,send_unit,act_type,method_name,pay_source";

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
    public int insert(Activity_event bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Activity_event bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (type,ui_id,ui_nd,pi_id,pi_name,area_code,car_code,order_type,orderid,ai_id,money,ctime,state,utime,ai_money,note,md5,send_unit,act_type,method_name,pay_source) VALUES (:type,:ui_id,:ui_nd,:pi_id,:pi_name,:area_code,:car_code,:order_type,:orderid,:ai_id,:money,:ctime,:state,:utime,:ai_money,:note,:md5,:send_unit,:act_type,:method_name,:pay_source)";
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
    public int insert_primarykey(Activity_event bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Activity_event bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (id,type,ui_id,ui_nd,pi_id,pi_name,area_code,car_code,order_type,orderid,ai_id,money,ctime,state,utime,ai_money,note,md5,send_unit,act_type,method_name,pay_source) VALUES (:id,:type,:ui_id,:ui_nd,:pi_id,:pi_name,:area_code,:car_code,:order_type,:orderid,:ai_id,:money,:ctime,:state,:utime,:ai_money,:note,:md5,:send_unit,:act_type,:method_name,:pay_source)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Activity_event> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Activity_event> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (type,ui_id,ui_nd,pi_id,pi_name,area_code,car_code,order_type,orderid,ai_id,money,ctime,state,utime,ai_money,note,md5,send_unit,act_type,method_name,pay_source) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Activity_event bean = beans.get(i);
                    ps.setInt(1, bean.type);
                    ps.setLong(2, bean.ui_id);
                    ps.setString(3, bean.ui_nd);
                    ps.setLong(4, bean.pi_id);
                    ps.setString(5, bean.pi_name);
                    ps.setString(6, bean.area_code);
                    ps.setString(7, bean.car_code);
                    ps.setInt(8, bean.order_type);
                    ps.setString(9, bean.orderid);
                    ps.setLong(10, bean.ai_id);
                    ps.setInt(11, bean.money);
                    ps.setTimestamp(12, new Timestamp(bean.ctime.getTime()));
                    ps.setInt(13, bean.state);
                    ps.setTimestamp(14, new Timestamp(bean.utime.getTime()));
                    ps.setInt(15, bean.ai_money);
                    ps.setString(16, bean.note);
                    ps.setString(17, bean.md5);
                    ps.setInt(18, bean.send_unit);
                    ps.setInt(19, bean.act_type);
                    ps.setString(20, bean.method_name);
                    ps.setInt(21, bean.pay_source);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Activity_event> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Activity_event> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,type,ui_id,ui_nd,pi_id,pi_name,area_code,car_code,order_type,orderid,ai_id,money,ctime,state,utime,ai_money,note,md5,send_unit,act_type,method_name,pay_source FROM "+TABLENAME2+" ORDER BY id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Activity_event>(Activity_event.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Activity_event>();
        }
    }

    //查询最新数据
    public List<Activity_event> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Activity_event> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,type,ui_id,ui_nd,pi_id,pi_name,area_code,car_code,order_type,orderid,ai_id,money,ctime,state,utime,ai_money,note,md5,send_unit,act_type,method_name,pay_source FROM "+TABLENAME2+" ORDER BY id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Activity_event>(Activity_event.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Activity_event>();
        }
    }

    //根据主键查询
    public List<Activity_event> selectGtKey(long id) {
        return selectGtKey(id, TABLENAME);
    }

    //根据主键查询
    public List<Activity_event> selectGtKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,type,ui_id,ui_nd,pi_id,pi_name,area_code,car_code,order_type,orderid,ai_id,money,ctime,state,utime,ai_money,note,md5,send_unit,act_type,method_name,pay_source FROM "+TABLENAME2+" WHERE id>:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Activity_event>(Activity_event.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Activity_event>();
        }
    }

    //根据主键查询
    public Activity_event selectByKey(long id) {
        return selectByKey(id, TABLENAME);
    }

    //根据主键查询
    public Activity_event selectByKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,type,ui_id,ui_nd,pi_id,pi_name,area_code,car_code,order_type,orderid,ai_id,money,ctime,state,utime,ai_money,note,md5,send_unit,act_type,method_name,pay_source FROM "+TABLENAME2+" WHERE id=:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            List<Activity_event> list =  _np.query(sql, param, new BeanPropertyRowMapper<Activity_event>(Activity_event.class));
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
    public List<Activity_event> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Activity_event> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT id,type,ui_id,ui_nd,pi_id,pi_name,area_code,car_code,order_type,orderid,ai_id,money,ctime,state,utime,ai_money,note,md5,send_unit,act_type,method_name,pay_source FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Activity_event>(Activity_event.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Activity_event>();
        }
    }

    //修改数据
    public int updateByKey(Activity_event bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Activity_event bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET type=:type,ui_id=:ui_id,ui_nd=:ui_nd,pi_id=:pi_id,pi_name=:pi_name,area_code=:area_code,car_code=:car_code,order_type=:order_type,orderid=:orderid,ai_id=:ai_id,money=:money,ctime=:ctime,state=:state,utime=:utime,ai_money=:ai_money,note=:note,md5=:md5,send_unit=:send_unit,act_type=:act_type,method_name=:method_name,pay_source=:pay_source WHERE id=:id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Activity_event> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Activity_event> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET type=?,ui_id=?,ui_nd=?,pi_id=?,pi_name=?,area_code=?,car_code=?,order_type=?,orderid=?,ai_id=?,money=?,ctime=?,state=?,utime=?,ai_money=?,note=?,md5=?,send_unit=?,act_type=?,method_name=?,pay_source=? WHERE id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Activity_event bean = beans.get(i);
                    ps.setInt(1, bean.type);
                    ps.setLong(2, bean.ui_id);
                    ps.setString(3, bean.ui_nd);
                    ps.setLong(4, bean.pi_id);
                    ps.setString(5, bean.pi_name);
                    ps.setString(6, bean.area_code);
                    ps.setString(7, bean.car_code);
                    ps.setInt(8, bean.order_type);
                    ps.setString(9, bean.orderid);
                    ps.setLong(10, bean.ai_id);
                    ps.setInt(11, bean.money);
                    ps.setTimestamp(12, new Timestamp(bean.ctime.getTime()));
                    ps.setInt(13, bean.state);
                    ps.setTimestamp(14, new Timestamp(bean.utime.getTime()));
                    ps.setInt(15, bean.ai_money);
                    ps.setString(16, bean.note);
                    ps.setString(17, bean.md5);
                    ps.setInt(18, bean.send_unit);
                    ps.setInt(19, bean.act_type);
                    ps.setString(20, bean.method_name);
                    ps.setInt(21, bean.pay_source);
                    ps.setLong(22, bean.id);
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
                 "	`id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`type`  INT(11) COMMENT '//int(11)    减免类型(1：返券2：随机减免)'," +
                 "	`ui_id`  BIGINT(20) COMMENT '//bigint(20)    用户ID'," +
                 "	`ui_nd`  VARCHAR(100) COMMENT '//varchar(100)    用户ND'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场主键ID'," +
                 "	`pi_name`  VARCHAR(100) COMMENT '//varchar(100)    停车场名称'," +
                 "	`area_code`  VARCHAR(100) COMMENT '//varchar(100)    停车场地址区域编码'," +
                 "	`car_code`  VARCHAR(100) COMMENT '//varchar(100)    车牌号'," +
                 "	`order_type`  INT(11) COMMENT '//int(11)    订单类型(0:临停1：租赁)'," +
                 "	`orderid`  VARCHAR(100) COMMENT '//varchar(100)    订单ID'," +
                 "	`ai_id`  BIGINT(20) COMMENT '//bigint(20)    活动表主键ID'," +
                 "	`money`  INT(11) COMMENT '//int(11)    随机立免金额（单位分）'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`state`  INT(11) COMMENT '//int(11)    处理状态0：没有处理1：处理成功2：处理失败'," +
                 "	`utime`  DATETIME COMMENT '//datetime    处理时间'," +
                 "	`ai_money`  INT(11) COMMENT '//int(11)    处理后当前活动剩余金额'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`md5`  VARCHAR(100) COMMENT '//varchar(100)    参数MD5（type+ai_id+ui_id+ui_nd+orderid+order_type+pi_id+car_code）'," +
                 "	`send_unit`  INT(11) COMMENT '//int(11)    赠送单位(0:吾泊平台1：龙支付)'," +
                 "	`act_type`  INT(11) COMMENT '//int(11)    事件行为（1：充值2：支付）'," +
                 "	`method_name`  VARCHAR(100) COMMENT '//varchar(100)    执行方法名称'," +
                 "	`pay_source`  INT(11) COMMENT '//int(11)    支付类型1:支付宝2：微信3：银联4：钱包5:龙支付'," +
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
