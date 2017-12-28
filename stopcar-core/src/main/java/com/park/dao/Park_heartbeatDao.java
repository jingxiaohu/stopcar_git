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

//park_heartbeat

@Repository("park_heartbeatDao")
public class Park_heartbeatDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Park_heartbeatDao.class);



    private  String TABLE = "park_heartbeat";

    private  String TABLENAME = "park_heartbeat";

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


    private  String[] carrays ={"id","pi_id","area_code","num","total","ctime","ctime_num","is_rent","note","carport_yet","carport_space","carport_total","moth_car_num","moth_car_num_space","time_car_num","time_car_num_space","expect_car_num","state"};
    private  String coulmns ="id,pi_id,area_code,num,total,ctime,ctime_num,is_rent,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,state";
    private  String coulmns2 ="pi_id,area_code,num,total,ctime,ctime_num,is_rent,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,state";

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
    public int insert(Park_heartbeat bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Park_heartbeat bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,area_code,num,total,ctime,ctime_num,is_rent,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,state) VALUES (:pi_id,:area_code,:num,:total,:ctime,:ctime_num,:is_rent,:note,:carport_yet,:carport_space,:carport_total,:moth_car_num,:moth_car_num_space,:time_car_num,:time_car_num_space,:expect_car_num,:state)";
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
    public int insert_primarykey(Park_heartbeat bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Park_heartbeat bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (id,pi_id,area_code,num,total,ctime,ctime_num,is_rent,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,state) VALUES (:id,:pi_id,:area_code,:num,:total,:ctime,:ctime_num,:is_rent,:note,:carport_yet,:carport_space,:carport_total,:moth_car_num,:moth_car_num_space,:time_car_num,:time_car_num_space,:expect_car_num,:state)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Park_heartbeat> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Park_heartbeat> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,area_code,num,total,ctime,ctime_num,is_rent,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,state) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_heartbeat bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setString(2, bean.area_code);
                    ps.setInt(3, bean.num);
                    ps.setInt(4, bean.total);
                    ps.setTimestamp(5, new Timestamp(bean.ctime.getTime()));
                    ps.setLong(6, bean.ctime_num);
                    ps.setInt(7, bean.is_rent);
                    ps.setString(8, bean.note);
                    ps.setInt(9, bean.carport_yet);
                    ps.setInt(10, bean.carport_space);
                    ps.setInt(11, bean.carport_total);
                    ps.setInt(12, bean.moth_car_num);
                    ps.setInt(13, bean.moth_car_num_space);
                    ps.setInt(14, bean.time_car_num);
                    ps.setInt(15, bean.time_car_num_space);
                    ps.setInt(16, bean.expect_car_num);
                    ps.setInt(17, bean.state);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Park_heartbeat> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Park_heartbeat> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,pi_id,area_code,num,total,ctime,ctime_num,is_rent,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,state FROM "+TABLENAME2+" ORDER BY id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_heartbeat>(Park_heartbeat.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Park_heartbeat>();
        }
    }

    //查询最新数据
    public List<Park_heartbeat> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Park_heartbeat> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,pi_id,area_code,num,total,ctime,ctime_num,is_rent,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,state FROM "+TABLENAME2+" ORDER BY id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_heartbeat>(Park_heartbeat.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Park_heartbeat>();
        }
    }

    //根据主键查询
    public List<Park_heartbeat> selectGtKey(long id) {
        return selectGtKey(id, TABLENAME);
    }

    //根据主键查询
    public List<Park_heartbeat> selectGtKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,pi_id,area_code,num,total,ctime,ctime_num,is_rent,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,state FROM "+TABLENAME2+" WHERE id>:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Park_heartbeat>(Park_heartbeat.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Park_heartbeat>();
        }
    }

    //根据主键查询
    public Park_heartbeat selectByKey(long id) {
        return selectByKey(id, TABLENAME);
    }

    //根据主键查询
    public Park_heartbeat selectByKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,pi_id,area_code,num,total,ctime,ctime_num,is_rent,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,state FROM "+TABLENAME2+" WHERE id=:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            List<Park_heartbeat> list =  _np.query(sql, param, new BeanPropertyRowMapper<Park_heartbeat>(Park_heartbeat.class));
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
    public List<Park_heartbeat> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Park_heartbeat> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT id,pi_id,area_code,num,total,ctime,ctime_num,is_rent,note,carport_yet,carport_space,carport_total,moth_car_num,moth_car_num_space,time_car_num,time_car_num_space,expect_car_num,state FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Park_heartbeat>(Park_heartbeat.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Park_heartbeat>();
        }
    }

    //修改数据
    public int updateByKey(Park_heartbeat bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Park_heartbeat bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=:pi_id,area_code=:area_code,num=:num,total=:total,ctime=:ctime,ctime_num=:ctime_num,is_rent=:is_rent,note=:note,carport_yet=:carport_yet,carport_space=:carport_space,carport_total=:carport_total,moth_car_num=:moth_car_num,moth_car_num_space=:moth_car_num_space,time_car_num=:time_car_num,time_car_num_space=:time_car_num_space,expect_car_num=:expect_car_num,state=:state WHERE id=:id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_heartbeat> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_heartbeat> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=?,area_code=?,num=?,total=?,ctime=?,ctime_num=?,is_rent=?,note=?,carport_yet=?,carport_space=?,carport_total=?,moth_car_num=?,moth_car_num_space=?,time_car_num=?,time_car_num_space=?,expect_car_num=?,state=? WHERE id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_heartbeat bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setString(2, bean.area_code);
                    ps.setInt(3, bean.num);
                    ps.setInt(4, bean.total);
                    ps.setTimestamp(5, new Timestamp(bean.ctime.getTime()));
                    ps.setLong(6, bean.ctime_num);
                    ps.setInt(7, bean.is_rent);
                    ps.setString(8, bean.note);
                    ps.setInt(9, bean.carport_yet);
                    ps.setInt(10, bean.carport_space);
                    ps.setInt(11, bean.carport_total);
                    ps.setInt(12, bean.moth_car_num);
                    ps.setInt(13, bean.moth_car_num_space);
                    ps.setInt(14, bean.time_car_num);
                    ps.setInt(15, bean.time_car_num_space);
                    ps.setInt(16, bean.expect_car_num);
                    ps.setInt(17, bean.state);
                    ps.setLong(18, bean.id);
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
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场主键ID'," +
                 "	`area_code`  VARCHAR(30) COMMENT '//varchar(30)    省市区编号'," +
                 "	`num`  INT(11) COMMENT '//int(11)    库存车辆数'," +
                 "	`total`  INT(11) COMMENT '//int(11)    停车位总数'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`ctime_num`  BIGINT(20) COMMENT '//bigint(20)    创建时间长整型'," +
                 "	`is_rent`  INT(11) COMMENT '//int(11)    是否有租赁包月业务0：没有1：有'," +
                 "	`note`  VARCHAR(60) COMMENT '//varchar(60)    备注'," +
                 "	`carport_yet`  INT(11) COMMENT '//int(11)    临停已停车位'," +
                 "	`carport_space`  INT(11) COMMENT '//int(11)    临停空车位个数'," +
                 "	`carport_total`  INT(11) COMMENT '//int(11)    临停总车位个数'," +
                 "	`moth_car_num`  INT(11) COMMENT '//int(11)    租赁离线包月车位总个数'," +
                 "	`moth_car_num_space`  INT(11) COMMENT '//int(11)    租赁离线剩余车位数'," +
                 "	`time_car_num`  INT(11) COMMENT '//int(11)    时间段包月车位总数.'," +
                 "	`time_car_num_space`  INT(11) COMMENT '//int(11)    时间段包月车位剩余个数'," +
                 "	`expect_car_num`  INT(11) COMMENT '//int(11)    已预约车位数'," +
                 "	`state`  INT(11) COMMENT '//int(11)    是否被处理（下次心跳过来更新状态）0:未处理1：被下次心跳处理'," +
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
