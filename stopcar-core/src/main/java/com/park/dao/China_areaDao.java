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

//china_area

@Repository("china_areaDao")
public class China_areaDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(China_areaDao.class);



    private  String TABLE = "china_area";

    private  String TABLENAME = "china_area";

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


    private  String[] carrays ={"id","area_code","area_name","province_code","province_name","city_code","city_name","father"};
    private  String coulmns ="id,area_code,area_name,province_code,province_name,city_code,city_name,father";
    private  String coulmns2 ="area_code,area_name,province_code,province_name,city_code,city_name,father";

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
    public int insert(China_area bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(China_area bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (area_code,area_name,province_code,province_name,city_code,city_name,father) VALUES (:area_code,:area_name,:province_code,:province_name,:city_code,:city_name,:father)";
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
    public int insert_primarykey(China_area bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(China_area bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (id,area_code,area_name,province_code,province_name,city_code,city_name,father) VALUES (:id,:area_code,:area_name,:province_code,:province_name,:city_code,:city_name,:father)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<China_area> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<China_area> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (area_code,area_name,province_code,province_name,city_code,city_name,father) VALUES (?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    China_area bean = beans.get(i);
                    ps.setString(1, bean.area_code);
                    ps.setString(2, bean.area_name);
                    ps.setString(3, bean.province_code);
                    ps.setString(4, bean.province_name);
                    ps.setString(5, bean.city_code);
                    ps.setString(6, bean.city_name);
                    ps.setString(7, bean.father);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<China_area> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<China_area> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,area_code,area_name,province_code,province_name,city_code,city_name,father FROM "+TABLENAME2+" ORDER BY id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<China_area>(China_area.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<China_area>();
        }
    }

    //查询最新数据
    public List<China_area> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<China_area> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,area_code,area_name,province_code,province_name,city_code,city_name,father FROM "+TABLENAME2+" ORDER BY id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<China_area>(China_area.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<China_area>();
        }
    }

    //根据主键查询
    public List<China_area> selectGtKey(int id) {
        return selectGtKey(id, TABLENAME);
    }

    //根据主键查询
    public List<China_area> selectGtKey(int id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,area_code,area_name,province_code,province_name,city_code,city_name,father FROM "+TABLENAME2+" WHERE id>:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            return _np.query(sql, param, new BeanPropertyRowMapper<China_area>(China_area.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<China_area>();
        }
    }

    //根据主键查询
    public China_area selectByKey(int id) {
        return selectByKey(id, TABLENAME);
    }

    //根据主键查询
    public China_area selectByKey(int id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,area_code,area_name,province_code,province_name,city_code,city_name,father FROM "+TABLENAME2+" WHERE id=:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            List<China_area> list =  _np.query(sql, param, new BeanPropertyRowMapper<China_area>(China_area.class));
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
    public List<China_area> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<China_area> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT id,area_code,area_name,province_code,province_name,city_code,city_name,father FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<China_area>(China_area.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<China_area>();
        }
    }

    //修改数据
    public int updateByKey(China_area bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(China_area bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET area_code=:area_code,area_name=:area_name,province_code=:province_code,province_name=:province_name,city_code=:city_code,city_name=:city_name,father=:father WHERE id=:id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<China_area> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<China_area> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET area_code=?,area_name=?,province_code=?,province_name=?,city_code=?,city_name=?,father=? WHERE id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    China_area bean = beans.get(i);
                    ps.setString(1, bean.area_code);
                    ps.setString(2, bean.area_name);
                    ps.setString(3, bean.province_code);
                    ps.setString(4, bean.province_name);
                    ps.setString(5, bean.city_code);
                    ps.setString(6, bean.city_name);
                    ps.setString(7, bean.father);
                    ps.setInt(8, bean.id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(int id) throws SQLException{
        return deleteByKey(id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(int id, String TABLENAME2) throws SQLException{
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
    public int[] deleteByKey(int[] keys) throws SQLException{
        return deleteByKey(keys, TABLENAME);
    }

    //批量删除数据
    public int[] deleteByKey(final int[] keys, String TABLENAME2) throws SQLException{
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
                    ps.setInt(1 , keys[i]);
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
                 "	`id`  INT(11) NOT NULL AUTO_INCREMENT COMMENT '//int(11)    '," +
                 "	`area_code`  VARCHAR(50) COMMENT '//varchar(50)    区/县编号'," +
                 "	`area_name`  VARCHAR(60) COMMENT '//varchar(60)    区县名称'," +
                 "	`province_code`  VARCHAR(50) COMMENT '//varchar(50)    省编号'," +
                 "	`province_name`  VARCHAR(60) COMMENT '//varchar(60)    省或者直辖市名称'," +
                 "	`city_code`  VARCHAR(50) COMMENT '//varchar(50)    市代码'," +
                 "	`city_name`  VARCHAR(60) COMMENT '//varchar(60)    市名称'," +
                 "	`father`  VARCHAR(6) COMMENT '//varchar(6)    市编号'," +
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
