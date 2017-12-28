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

//park_rule

@Repository("park_ruleDao")
public class Park_ruleDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Park_ruleDao.class);



    private  String TABLE = "park_rule";

    private  String TABLENAME = "park_rule";

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


    private  String[] carrays ={"pr_id","pr_nd","pi_id","area_code","state","json_array","ctime","utime","note"};
    private  String coulmns ="pr_id,pr_nd,pi_id,area_code,state,json_array,ctime,utime,note";
    private  String coulmns2 ="pr_nd,pi_id,area_code,state,json_array,ctime,utime,note";

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
    public int insert(Park_rule bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Park_rule bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pr_nd,pi_id,area_code,state,json_array,ctime,utime,note) VALUES (:pr_nd,:pi_id,:area_code,:state,:json_array,:ctime,:utime,:note)";
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
    public int insert_primarykey(Park_rule bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Park_rule bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pr_id,pr_nd,pi_id,area_code,state,json_array,ctime,utime,note) VALUES (:pr_id,:pr_nd,:pi_id,:area_code,:state,:json_array,:ctime,:utime,:note)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Park_rule> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Park_rule> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pr_nd,pi_id,area_code,state,json_array,ctime,utime,note) VALUES (?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_rule bean = beans.get(i);
                    ps.setString(1, bean.pr_nd);
                    ps.setLong(2, bean.pi_id);
                    ps.setString(3, bean.area_code);
                    ps.setInt(4, bean.state);
                    ps.setString(5, bean.json_array);
                    ps.setTimestamp(6, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(7, new Timestamp(bean.utime.getTime()));
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
    public List<Park_rule> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Park_rule> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pr_id,pr_nd,pi_id,area_code,state,json_array,ctime,utime,note FROM "+TABLENAME2+" ORDER BY pr_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_rule>(Park_rule.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Park_rule>();
        }
    }

    //查询最新数据
    public List<Park_rule> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Park_rule> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pr_id,pr_nd,pi_id,area_code,state,json_array,ctime,utime,note FROM "+TABLENAME2+" ORDER BY pr_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_rule>(Park_rule.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Park_rule>();
        }
    }

    //根据主键查询
    public List<Park_rule> selectGtKey(long pr_id) {
        return selectGtKey(pr_id, TABLENAME);
    }

    //根据主键查询
    public List<Park_rule> selectGtKey(long pr_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pr_id,pr_nd,pi_id,area_code,state,json_array,ctime,utime,note FROM "+TABLENAME2+" WHERE pr_id>:pr_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pr_id", pr_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Park_rule>(Park_rule.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Park_rule>();
        }
    }

    //根据主键查询
    public Park_rule selectByKey(long pr_id) {
        return selectByKey(pr_id, TABLENAME);
    }

    //根据主键查询
    public Park_rule selectByKey(long pr_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pr_id,pr_nd,pi_id,area_code,state,json_array,ctime,utime,note FROM "+TABLENAME2+" WHERE pr_id=:pr_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pr_id", pr_id);
            List<Park_rule> list =  _np.query(sql, param, new BeanPropertyRowMapper<Park_rule>(Park_rule.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey pr_id="+pr_id,e);
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
    public List<Park_rule> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Park_rule> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT pr_id,pr_nd,pi_id,area_code,state,json_array,ctime,utime,note FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Park_rule>(Park_rule.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Park_rule>();
        }
    }

    //修改数据
    public int updateByKey(Park_rule bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Park_rule bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pr_nd=:pr_nd,pi_id=:pi_id,area_code=:area_code,state=:state,json_array=:json_array,ctime=:ctime,utime=:utime,note=:note WHERE pr_id=:pr_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_rule> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_rule> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pr_nd=?,pi_id=?,area_code=?,state=?,json_array=?,ctime=?,utime=?,note=? WHERE pr_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_rule bean = beans.get(i);
                    ps.setString(1, bean.pr_nd);
                    ps.setLong(2, bean.pi_id);
                    ps.setString(3, bean.area_code);
                    ps.setInt(4, bean.state);
                    ps.setString(5, bean.json_array);
                    ps.setTimestamp(6, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(7, new Timestamp(bean.utime.getTime()));
                    ps.setString(8, bean.note);
                    ps.setLong(9, bean.pr_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long pr_id) throws SQLException{
        return deleteByKey(pr_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long pr_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE pr_id=:pr_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pr_id", pr_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE pr_id=?";
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
                 "	`pr_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`pr_nd`  VARCHAR(80) COMMENT '//varchar(80)    唯一标识符ND'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场主键ID'," +
                 "	`area_code`  VARCHAR(30) COMMENT '//varchar(30)    停车场地址区域编码'," +
                 "	`state`  INT(11) COMMENT '//int(11)    是否有效（0：无效1：有效）'," +
                 "	`json_array`  TEXT COMMENT '//text    规则JSONArray数组'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    服务器接受该数据时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    规则修改时间'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	PRIMARY KEY (`pr_id`)" +
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
