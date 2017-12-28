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

//client_gate_rule

@Repository("client_gate_ruleDao")
public class Client_gate_ruleDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Client_gate_ruleDao.class);



    private  String TABLE = "client_gate_rule";

    private  String TABLENAME = "client_gate_rule";

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


    private  String[] carrays ={"cgr_id","client_ruleid","pi_id","area_code","group_id","type","money","state","str_json","intro","permit_time","ctime","utime","client_loginname","note","is_del"};
    private  String coulmns ="cgr_id,client_ruleid,pi_id,area_code,group_id,type,money,state,str_json,intro,permit_time,ctime,utime,client_loginname,note,is_del";
    private  String coulmns2 ="client_ruleid,pi_id,area_code,group_id,type,money,state,str_json,intro,permit_time,ctime,utime,client_loginname,note,is_del";

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
    public int insert(Client_gate_rule bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Client_gate_rule bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (client_ruleid,pi_id,area_code,group_id,type,money,state,str_json,intro,permit_time,ctime,utime,client_loginname,note,is_del) VALUES (:client_ruleid,:pi_id,:area_code,:group_id,:type,:money,:state,:str_json,:intro,:permit_time,:ctime,:utime,:client_loginname,:note,:is_del)";
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
    public int insert_primarykey(Client_gate_rule bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Client_gate_rule bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (cgr_id,client_ruleid,pi_id,area_code,group_id,type,money,state,str_json,intro,permit_time,ctime,utime,client_loginname,note,is_del) VALUES (:cgr_id,:client_ruleid,:pi_id,:area_code,:group_id,:type,:money,:state,:str_json,:intro,:permit_time,:ctime,:utime,:client_loginname,:note,:is_del)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Client_gate_rule> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Client_gate_rule> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (client_ruleid,pi_id,area_code,group_id,type,money,state,str_json,intro,permit_time,ctime,utime,client_loginname,note,is_del) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Client_gate_rule bean = beans.get(i);
                    ps.setString(1, bean.client_ruleid);
                    ps.setLong(2, bean.pi_id);
                    ps.setString(3, bean.area_code);
                    ps.setString(4, bean.group_id);
                    ps.setInt(5, bean.type);
                    ps.setInt(6, bean.money);
                    ps.setInt(7, bean.state);
                    ps.setString(8, bean.str_json);
                    ps.setString(9, bean.intro);
                    ps.setString(10, bean.permit_time);
                    ps.setTimestamp(11, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(12, new Timestamp(bean.utime.getTime()));
                    ps.setString(13, bean.client_loginname);
                    ps.setString(14, bean.note);
                    ps.setInt(15, bean.is_del);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Client_gate_rule> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Client_gate_rule> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT cgr_id,client_ruleid,pi_id,area_code,group_id,type,money,state,str_json,intro,permit_time,ctime,utime,client_loginname,note,is_del FROM "+TABLENAME2+" ORDER BY cgr_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Client_gate_rule>(Client_gate_rule.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Client_gate_rule>();
        }
    }

    //查询最新数据
    public List<Client_gate_rule> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Client_gate_rule> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT cgr_id,client_ruleid,pi_id,area_code,group_id,type,money,state,str_json,intro,permit_time,ctime,utime,client_loginname,note,is_del FROM "+TABLENAME2+" ORDER BY cgr_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Client_gate_rule>(Client_gate_rule.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Client_gate_rule>();
        }
    }

    //根据主键查询
    public List<Client_gate_rule> selectGtKey(long cgr_id) {
        return selectGtKey(cgr_id, TABLENAME);
    }

    //根据主键查询
    public List<Client_gate_rule> selectGtKey(long cgr_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT cgr_id,client_ruleid,pi_id,area_code,group_id,type,money,state,str_json,intro,permit_time,ctime,utime,client_loginname,note,is_del FROM "+TABLENAME2+" WHERE cgr_id>:cgr_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("cgr_id", cgr_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Client_gate_rule>(Client_gate_rule.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Client_gate_rule>();
        }
    }

    //根据主键查询
    public Client_gate_rule selectByKey(long cgr_id) {
        return selectByKey(cgr_id, TABLENAME);
    }

    //根据主键查询
    public Client_gate_rule selectByKey(long cgr_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT cgr_id,client_ruleid,pi_id,area_code,group_id,type,money,state,str_json,intro,permit_time,ctime,utime,client_loginname,note,is_del FROM "+TABLENAME2+" WHERE cgr_id=:cgr_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("cgr_id", cgr_id);
            List<Client_gate_rule> list =  _np.query(sql, param, new BeanPropertyRowMapper<Client_gate_rule>(Client_gate_rule.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey cgr_id="+cgr_id,e);
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
    public List<Client_gate_rule> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Client_gate_rule> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT cgr_id,client_ruleid,pi_id,area_code,group_id,type,money,state,str_json,intro,permit_time,ctime,utime,client_loginname,note,is_del FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Client_gate_rule>(Client_gate_rule.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Client_gate_rule>();
        }
    }

    //修改数据
    public int updateByKey(Client_gate_rule bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Client_gate_rule bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET client_ruleid=:client_ruleid,pi_id=:pi_id,area_code=:area_code,group_id=:group_id,type=:type,money=:money,state=:state,str_json=:str_json,intro=:intro,permit_time=:permit_time,ctime=:ctime,utime=:utime,client_loginname=:client_loginname,note=:note,is_del=:is_del WHERE cgr_id=:cgr_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Client_gate_rule> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Client_gate_rule> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET client_ruleid=?,pi_id=?,area_code=?,group_id=?,type=?,money=?,state=?,str_json=?,intro=?,permit_time=?,ctime=?,utime=?,client_loginname=?,note=?,is_del=? WHERE cgr_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Client_gate_rule bean = beans.get(i);
                    ps.setString(1, bean.client_ruleid);
                    ps.setLong(2, bean.pi_id);
                    ps.setString(3, bean.area_code);
                    ps.setString(4, bean.group_id);
                    ps.setInt(5, bean.type);
                    ps.setInt(6, bean.money);
                    ps.setInt(7, bean.state);
                    ps.setString(8, bean.str_json);
                    ps.setString(9, bean.intro);
                    ps.setString(10, bean.permit_time);
                    ps.setTimestamp(11, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(12, new Timestamp(bean.utime.getTime()));
                    ps.setString(13, bean.client_loginname);
                    ps.setString(14, bean.note);
                    ps.setInt(15, bean.is_del);
                    ps.setLong(16, bean.cgr_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long cgr_id) throws SQLException{
        return deleteByKey(cgr_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long cgr_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE cgr_id=:cgr_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("cgr_id", cgr_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE cgr_id=?";
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
                 "	`cgr_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`client_ruleid`  VARCHAR(100) COMMENT '//varchar(100)    客户端规则唯一标识（主键）'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场主键ID'," +
                 "	`area_code`  VARCHAR(30) COMMENT '//varchar(30)    停车场区域编码'," +
                 "	`group_id`  VARCHAR(100) COMMENT '//varchar(100)    客户端所属分组ID'," +
                 "	`type`  INT(11) COMMENT '//int(11)    类型（0：未指定1：临停2：租赁）'," +
                 "	`money`  INT(11) COMMENT '//int(11)    默认通用型单价(单位分)'," +
                 "	`state`  INT(11) COMMENT '//int(11)    是否开启（0：关闭1：开启）'," +
                 "	`str_json`  TEXT COMMENT '//text    其它属性JSON'," +
                 "	`intro`  TEXT COMMENT '//text    APP端显示的描述'," +
                 "	`permit_time`  VARCHAR(60) COMMENT '//varchar(60)    租赁允许时间段（08：00-19：00）'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`client_loginname`  VARCHAR(100) COMMENT '//varchar(100)    客户端修改人登录帐号'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`is_del`  INT(11) COMMENT '//int(11)    逻辑删除（0：正常1：删除）'," +
                 "	PRIMARY KEY (`cgr_id`)" +
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
