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

//rabbitmq_push_fail

@Repository("rabbitmq_push_failDao")
public class Rabbitmq_push_failDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Rabbitmq_push_failDao.class);



    private  String TABLE = "rabbitmq_push_fail";

    private  String TABLENAME = "rabbitmq_push_fail";

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


    private  String[] carrays ={"rpf_id","rpf_nd","server_code","type","content_json","persistent_state","ctime","utime","fail_count","state","name","note","exchange_name","routing_key","host_name","content_md5"};
    private  String coulmns ="rpf_id,rpf_nd,server_code,type,content_json,persistent_state,ctime,utime,fail_count,state,name,note,exchange_name,routing_key,host_name,content_md5";
    private  String coulmns2 ="rpf_nd,server_code,type,content_json,persistent_state,ctime,utime,fail_count,state,name,note,exchange_name,routing_key,host_name,content_md5";

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
    public int insert(Rabbitmq_push_fail bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Rabbitmq_push_fail bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (rpf_nd,server_code,type,content_json,persistent_state,ctime,utime,fail_count,state,name,note,exchange_name,routing_key,host_name,content_md5) VALUES (:rpf_nd,:server_code,:type,:content_json,:persistent_state,:ctime,:utime,:fail_count,:state,:name,:note,:exchange_name,:routing_key,:host_name,:content_md5)";
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
    public int insert_primarykey(Rabbitmq_push_fail bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Rabbitmq_push_fail bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (rpf_id,rpf_nd,server_code,type,content_json,persistent_state,ctime,utime,fail_count,state,name,note,exchange_name,routing_key,host_name,content_md5) VALUES (:rpf_id,:rpf_nd,:server_code,:type,:content_json,:persistent_state,:ctime,:utime,:fail_count,:state,:name,:note,:exchange_name,:routing_key,:host_name,:content_md5)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Rabbitmq_push_fail> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Rabbitmq_push_fail> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (rpf_nd,server_code,type,content_json,persistent_state,ctime,utime,fail_count,state,name,note,exchange_name,routing_key,host_name,content_md5) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Rabbitmq_push_fail bean = beans.get(i);
                    ps.setString(1, bean.rpf_nd);
                    ps.setInt(2, bean.server_code);
                    ps.setInt(3, bean.type);
                    ps.setString(4, bean.content_json);
                    ps.setInt(5, bean.persistent_state);
                    ps.setTimestamp(6, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(7, new Timestamp(bean.utime.getTime()));
                    ps.setInt(8, bean.fail_count);
                    ps.setInt(9, bean.state);
                    ps.setString(10, bean.name);
                    ps.setString(11, bean.note);
                    ps.setString(12, bean.exchange_name);
                    ps.setString(13, bean.routing_key);
                    ps.setString(14, bean.host_name);
                    ps.setString(15, bean.content_md5);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Rabbitmq_push_fail> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Rabbitmq_push_fail> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT rpf_id,rpf_nd,server_code,type,content_json,persistent_state,ctime,utime,fail_count,state,name,note,exchange_name,routing_key,host_name,content_md5 FROM "+TABLENAME2+" ORDER BY rpf_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Rabbitmq_push_fail>(Rabbitmq_push_fail.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Rabbitmq_push_fail>();
        }
    }

    //查询最新数据
    public List<Rabbitmq_push_fail> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Rabbitmq_push_fail> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT rpf_id,rpf_nd,server_code,type,content_json,persistent_state,ctime,utime,fail_count,state,name,note,exchange_name,routing_key,host_name,content_md5 FROM "+TABLENAME2+" ORDER BY rpf_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Rabbitmq_push_fail>(Rabbitmq_push_fail.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Rabbitmq_push_fail>();
        }
    }

    //根据主键查询
    public List<Rabbitmq_push_fail> selectGtKey(long rpf_id) {
        return selectGtKey(rpf_id, TABLENAME);
    }

    //根据主键查询
    public List<Rabbitmq_push_fail> selectGtKey(long rpf_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT rpf_id,rpf_nd,server_code,type,content_json,persistent_state,ctime,utime,fail_count,state,name,note,exchange_name,routing_key,host_name,content_md5 FROM "+TABLENAME2+" WHERE rpf_id>:rpf_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("rpf_id", rpf_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Rabbitmq_push_fail>(Rabbitmq_push_fail.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Rabbitmq_push_fail>();
        }
    }

    //根据主键查询
    public Rabbitmq_push_fail selectByKey(long rpf_id) {
        return selectByKey(rpf_id, TABLENAME);
    }

    //根据主键查询
    public Rabbitmq_push_fail selectByKey(long rpf_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT rpf_id,rpf_nd,server_code,type,content_json,persistent_state,ctime,utime,fail_count,state,name,note,exchange_name,routing_key,host_name,content_md5 FROM "+TABLENAME2+" WHERE rpf_id=:rpf_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("rpf_id", rpf_id);
            List<Rabbitmq_push_fail> list =  _np.query(sql, param, new BeanPropertyRowMapper<Rabbitmq_push_fail>(Rabbitmq_push_fail.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey rpf_id="+rpf_id,e);
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
    public List<Rabbitmq_push_fail> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Rabbitmq_push_fail> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT rpf_id,rpf_nd,server_code,type,content_json,persistent_state,ctime,utime,fail_count,state,name,note,exchange_name,routing_key,host_name,content_md5 FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Rabbitmq_push_fail>(Rabbitmq_push_fail.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Rabbitmq_push_fail>();
        }
    }

    //修改数据
    public int updateByKey(Rabbitmq_push_fail bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Rabbitmq_push_fail bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET rpf_nd=:rpf_nd,server_code=:server_code,type=:type,content_json=:content_json,persistent_state=:persistent_state,ctime=:ctime,utime=:utime,fail_count=:fail_count,state=:state,name=:name,note=:note,exchange_name=:exchange_name,routing_key=:routing_key,host_name=:host_name,content_md5=:content_md5 WHERE rpf_id=:rpf_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Rabbitmq_push_fail> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Rabbitmq_push_fail> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET rpf_nd=?,server_code=?,type=?,content_json=?,persistent_state=?,ctime=?,utime=?,fail_count=?,state=?,name=?,note=?,exchange_name=?,routing_key=?,host_name=?,content_md5=? WHERE rpf_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Rabbitmq_push_fail bean = beans.get(i);
                    ps.setString(1, bean.rpf_nd);
                    ps.setInt(2, bean.server_code);
                    ps.setInt(3, bean.type);
                    ps.setString(4, bean.content_json);
                    ps.setInt(5, bean.persistent_state);
                    ps.setTimestamp(6, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(7, new Timestamp(bean.utime.getTime()));
                    ps.setInt(8, bean.fail_count);
                    ps.setInt(9, bean.state);
                    ps.setString(10, bean.name);
                    ps.setString(11, bean.note);
                    ps.setString(12, bean.exchange_name);
                    ps.setString(13, bean.routing_key);
                    ps.setString(14, bean.host_name);
                    ps.setString(15, bean.content_md5);
                    ps.setLong(16, bean.rpf_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long rpf_id) throws SQLException{
        return deleteByKey(rpf_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long rpf_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE rpf_id=:rpf_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("rpf_id", rpf_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE rpf_id=?";
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
                 "	`rpf_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`rpf_nd`  VARCHAR(80) COMMENT '//varchar(80)    唯一标识ND'," +
                 "	`server_code`  INT(11) COMMENT '//int(11)    mq服务器（0：泸州服务器1：贵阳服务器2：重庆服务器）'," +
                 "	`type`  INT(11) COMMENT '//int(11)    业务分类（0：停车场推送）'," +
                 "	`content_json`  TEXT COMMENT '//text    JSON内容'," +
                 "	`persistent_state`  INT(11) COMMENT '//int(11)    是否持久化(0：不持久化1：持久化)'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`fail_count`  INT(11) COMMENT '//int(11)    失败次数'," +
                 "	`state`  INT(11) COMMENT '//int(11)    是否关闭（0：不关闭1：关闭（不再进行再次调用或者其它任何处理）2:推送成功）'," +
                 "	`name`  TINYTEXT COMMENT '//varchar(255)    接口名称或者事件名称（调用的哪个接口引入的MQ）'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`exchange_name`  VARCHAR(100) COMMENT '//varchar(100)    交换机名称'," +
                 "	`routing_key`  VARCHAR(100) COMMENT '//varchar(100)    路由key'," +
                 "	`host_name`  TINYTEXT COMMENT '//varchar(255)    虚拟主机host'," +
                 "	`content_md5`  VARCHAR(100) COMMENT '//varchar(100)    内容MD5'," +
                 "	PRIMARY KEY (`rpf_id`)" +
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
