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

//event_schedule

@Repository("event_scheduleDao")
public class Event_scheduleDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Event_scheduleDao.class);



    private  String TABLE = "event_schedule";

    private  String TABLENAME = "event_schedule";

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


    private  String[] carrays ={"es_id","event_name","event_type","up_orderid","order_id","order_type","ui_id","ui_nd","pi_id","area_code","ctime","utime","state","note"};
    private  String coulmns ="es_id,event_name,event_type,up_orderid,order_id,order_type,ui_id,ui_nd,pi_id,area_code,ctime,utime,state,note";
    private  String coulmns2 ="event_name,event_type,up_orderid,order_id,order_type,ui_id,ui_nd,pi_id,area_code,ctime,utime,state,note";

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
    public int insert(Event_schedule bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Event_schedule bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (event_name,event_type,up_orderid,order_id,order_type,ui_id,ui_nd,pi_id,area_code,ctime,utime,state,note) VALUES (:event_name,:event_type,:up_orderid,:order_id,:order_type,:ui_id,:ui_nd,:pi_id,:area_code,:ctime,:utime,:state,:note)";
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
    public int insert_primarykey(Event_schedule bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Event_schedule bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (es_id,event_name,event_type,up_orderid,order_id,order_type,ui_id,ui_nd,pi_id,area_code,ctime,utime,state,note) VALUES (:es_id,:event_name,:event_type,:up_orderid,:order_id,:order_type,:ui_id,:ui_nd,:pi_id,:area_code,:ctime,:utime,:state,:note)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Event_schedule> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Event_schedule> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (event_name,event_type,up_orderid,order_id,order_type,ui_id,ui_nd,pi_id,area_code,ctime,utime,state,note) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Event_schedule bean = beans.get(i);
                    ps.setString(1, bean.event_name);
                    ps.setInt(2, bean.event_type);
                    ps.setString(3, bean.up_orderid);
                    ps.setString(4, bean.order_id);
                    ps.setInt(5, bean.order_type);
                    ps.setLong(6, bean.ui_id);
                    ps.setString(7, bean.ui_nd);
                    ps.setLong(8, bean.pi_id);
                    ps.setString(9, bean.area_code);
                    ps.setTimestamp(10, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(11, new Timestamp(bean.utime.getTime()));
                    ps.setInt(12, bean.state);
                    ps.setString(13, bean.note);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Event_schedule> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Event_schedule> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT es_id,event_name,event_type,up_orderid,order_id,order_type,ui_id,ui_nd,pi_id,area_code,ctime,utime,state,note FROM "+TABLENAME2+" ORDER BY es_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Event_schedule>(Event_schedule.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Event_schedule>();
        }
    }

    //查询最新数据
    public List<Event_schedule> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Event_schedule> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT es_id,event_name,event_type,up_orderid,order_id,order_type,ui_id,ui_nd,pi_id,area_code,ctime,utime,state,note FROM "+TABLENAME2+" ORDER BY es_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Event_schedule>(Event_schedule.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Event_schedule>();
        }
    }

    //根据主键查询
    public List<Event_schedule> selectGtKey(long es_id) {
        return selectGtKey(es_id, TABLENAME);
    }

    //根据主键查询
    public List<Event_schedule> selectGtKey(long es_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT es_id,event_name,event_type,up_orderid,order_id,order_type,ui_id,ui_nd,pi_id,area_code,ctime,utime,state,note FROM "+TABLENAME2+" WHERE es_id>:es_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("es_id", es_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Event_schedule>(Event_schedule.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Event_schedule>();
        }
    }

    //根据主键查询
    public Event_schedule selectByKey(long es_id) {
        return selectByKey(es_id, TABLENAME);
    }

    //根据主键查询
    public Event_schedule selectByKey(long es_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT es_id,event_name,event_type,up_orderid,order_id,order_type,ui_id,ui_nd,pi_id,area_code,ctime,utime,state,note FROM "+TABLENAME2+" WHERE es_id=:es_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("es_id", es_id);
            List<Event_schedule> list =  _np.query(sql, param, new BeanPropertyRowMapper<Event_schedule>(Event_schedule.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey es_id="+es_id,e);
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
    public List<Event_schedule> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Event_schedule> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT es_id,event_name,event_type,up_orderid,order_id,order_type,ui_id,ui_nd,pi_id,area_code,ctime,utime,state,note FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Event_schedule>(Event_schedule.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Event_schedule>();
        }
    }

    //修改数据
    public int updateByKey(Event_schedule bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Event_schedule bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET event_name=:event_name,event_type=:event_type,up_orderid=:up_orderid,order_id=:order_id,order_type=:order_type,ui_id=:ui_id,ui_nd=:ui_nd,pi_id=:pi_id,area_code=:area_code,ctime=:ctime,utime=:utime,state=:state,note=:note WHERE es_id=:es_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Event_schedule> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Event_schedule> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET event_name=?,event_type=?,up_orderid=?,order_id=?,order_type=?,ui_id=?,ui_nd=?,pi_id=?,area_code=?,ctime=?,utime=?,state=?,note=? WHERE es_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Event_schedule bean = beans.get(i);
                    ps.setString(1, bean.event_name);
                    ps.setInt(2, bean.event_type);
                    ps.setString(3, bean.up_orderid);
                    ps.setString(4, bean.order_id);
                    ps.setInt(5, bean.order_type);
                    ps.setLong(6, bean.ui_id);
                    ps.setString(7, bean.ui_nd);
                    ps.setLong(8, bean.pi_id);
                    ps.setString(9, bean.area_code);
                    ps.setTimestamp(10, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(11, new Timestamp(bean.utime.getTime()));
                    ps.setInt(12, bean.state);
                    ps.setString(13, bean.note);
                    ps.setLong(14, bean.es_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long es_id) throws SQLException{
        return deleteByKey(es_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long es_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE es_id=:es_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("es_id", es_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE es_id=?";
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
                 "	`es_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`event_name`  VARCHAR(100) COMMENT '//varchar(100)    事件名称（中文）'," +
                 "	`event_type`  INT(11) COMMENT '//int(11)    事件类型（0：未指定1：续约租赁订单）'," +
                 "	`up_orderid`  VARCHAR(80) COMMENT '//varchar(80)    平台支付流水订单号'," +
                 "	`order_id`  VARCHAR(80) COMMENT '//varchar(80)    业务订单号（例如普通临停订单预约订单租赁订单包月订单）'," +
                 "	`order_type`  INT(11) COMMENT '//int(11)    业务订单类型（0:未指定1：普通临停订单2：预约订单3：租赁订单4：包月订单）'," +
                 "	`ui_id`  BIGINT(20) COMMENT '//bigint(20)    用户ID'," +
                 "	`ui_nd`  VARCHAR(80) COMMENT '//varchar(80)    用户ND'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场主键ID'," +
                 "	`area_code`  VARCHAR(30) COMMENT '//varchar(30)    地址区域编码'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`state`  INT(11) COMMENT '//int(11)    处理状态（0：未处理1：处理成功2：处理失败）'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	PRIMARY KEY (`es_id`)" +
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
