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

//pda_channel

@Repository("pda_channelDao")
public class Pda_channelDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Pda_channelDao.class);



    private  String TABLE = "pda_channel";

    private  String TABLENAME = "pda_channel";

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


    private  String[] carrays ={"pda_c_id","name","intro","is_open","father_id","is_delete","ctime","utime","note"};
    private  String coulmns ="pda_c_id,name,intro,is_open,father_id,is_delete,ctime,utime,note";
    private  String coulmns2 ="name,intro,is_open,father_id,is_delete,ctime,utime,note";

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
    public int insert(Pda_channel bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Pda_channel bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (name,intro,is_open,father_id,is_delete,ctime,utime,note) VALUES (:name,:intro,:is_open,:father_id,:is_delete,:ctime,:utime,:note)";
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
    public int insert_primarykey(Pda_channel bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Pda_channel bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pda_c_id,name,intro,is_open,father_id,is_delete,ctime,utime,note) VALUES (:pda_c_id,:name,:intro,:is_open,:father_id,:is_delete,:ctime,:utime,:note)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Pda_channel> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Pda_channel> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (name,intro,is_open,father_id,is_delete,ctime,utime,note) VALUES (?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Pda_channel bean = beans.get(i);
                    ps.setString(1, bean.name);
                    ps.setString(2, bean.intro);
                    ps.setInt(3, bean.is_open);
                    ps.setLong(4, bean.father_id);
                    ps.setInt(5, bean.is_delete);
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
    public List<Pda_channel> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Pda_channel> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pda_c_id,name,intro,is_open,father_id,is_delete,ctime,utime,note FROM "+TABLENAME2+" ORDER BY pda_c_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Pda_channel>(Pda_channel.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Pda_channel>();
        }
    }

    //查询最新数据
    public List<Pda_channel> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Pda_channel> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pda_c_id,name,intro,is_open,father_id,is_delete,ctime,utime,note FROM "+TABLENAME2+" ORDER BY pda_c_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Pda_channel>(Pda_channel.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Pda_channel>();
        }
    }

    //根据主键查询
    public List<Pda_channel> selectGtKey(long pda_c_id) {
        return selectGtKey(pda_c_id, TABLENAME);
    }

    //根据主键查询
    public List<Pda_channel> selectGtKey(long pda_c_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pda_c_id,name,intro,is_open,father_id,is_delete,ctime,utime,note FROM "+TABLENAME2+" WHERE pda_c_id>:pda_c_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pda_c_id", pda_c_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Pda_channel>(Pda_channel.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Pda_channel>();
        }
    }

    //根据主键查询
    public Pda_channel selectByKey(long pda_c_id) {
        return selectByKey(pda_c_id, TABLENAME);
    }

    //根据主键查询
    public Pda_channel selectByKey(long pda_c_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pda_c_id,name,intro,is_open,father_id,is_delete,ctime,utime,note FROM "+TABLENAME2+" WHERE pda_c_id=:pda_c_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pda_c_id", pda_c_id);
            List<Pda_channel> list =  _np.query(sql, param, new BeanPropertyRowMapper<Pda_channel>(Pda_channel.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey pda_c_id="+pda_c_id,e);
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
    public List<Pda_channel> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Pda_channel> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT pda_c_id,name,intro,is_open,father_id,is_delete,ctime,utime,note FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Pda_channel>(Pda_channel.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Pda_channel>();
        }
    }

    //修改数据
    public int updateByKey(Pda_channel bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Pda_channel bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET name=:name,intro=:intro,is_open=:is_open,father_id=:father_id,is_delete=:is_delete,ctime=:ctime,utime=:utime,note=:note WHERE pda_c_id=:pda_c_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Pda_channel> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Pda_channel> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET name=?,intro=?,is_open=?,father_id=?,is_delete=?,ctime=?,utime=?,note=? WHERE pda_c_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Pda_channel bean = beans.get(i);
                    ps.setString(1, bean.name);
                    ps.setString(2, bean.intro);
                    ps.setInt(3, bean.is_open);
                    ps.setLong(4, bean.father_id);
                    ps.setInt(5, bean.is_delete);
                    ps.setTimestamp(6, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(7, new Timestamp(bean.utime.getTime()));
                    ps.setString(8, bean.note);
                    ps.setLong(9, bean.pda_c_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long pda_c_id) throws SQLException{
        return deleteByKey(pda_c_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long pda_c_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE pda_c_id=:pda_c_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pda_c_id", pda_c_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE pda_c_id=?";
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
                 "	`pda_c_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`name`  VARCHAR(60) COMMENT '//varchar(60)    渠道名称'," +
                 "	`intro`  TEXT COMMENT '//text    渠道简介'," +
                 "	`is_open`  INT(11) COMMENT '//int(11)    是否开启0:不开启1：开启'," +
                 "	`father_id`  BIGINT(20) COMMENT '//bigint(20)    父渠道ID'," +
                 "	`is_delete`  INT(11) COMMENT '//int(11)    是否删除0:不删除1：删除'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	PRIMARY KEY (`pda_c_id`)" +
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
