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

//parkinfo_partner

@Repository("parkinfo_partnerDao")
public class Parkinfo_partnerDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Parkinfo_partnerDao.class);



    private  String TABLE = "parkinfo_partner";

    private  String TABLENAME = "parkinfo_partner";

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


    private  String[] carrays ={"id","pi_id","pu_id","pu_nd","ctime","utime","is_effect","area_code","note","clearing_type","clearing_percentage","clearing_base"};
    private  String coulmns ="id,pi_id,pu_id,pu_nd,ctime,utime,is_effect,area_code,note,clearing_type,clearing_percentage,clearing_base";
    private  String coulmns2 ="pi_id,pu_id,pu_nd,ctime,utime,is_effect,area_code,note,clearing_type,clearing_percentage,clearing_base";

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
    public int insert(Parkinfo_partner bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Parkinfo_partner bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,pu_id,pu_nd,ctime,utime,is_effect,area_code,note,clearing_type,clearing_percentage,clearing_base) VALUES (:pi_id,:pu_id,:pu_nd,:ctime,:utime,:is_effect,:area_code,:note,:clearing_type,:clearing_percentage,:clearing_base)";
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
    public int insert_primarykey(Parkinfo_partner bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Parkinfo_partner bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (id,pi_id,pu_id,pu_nd,ctime,utime,is_effect,area_code,note,clearing_type,clearing_percentage,clearing_base) VALUES (:id,:pi_id,:pu_id,:pu_nd,:ctime,:utime,:is_effect,:area_code,:note,:clearing_type,:clearing_percentage,:clearing_base)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Parkinfo_partner> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Parkinfo_partner> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,pu_id,pu_nd,ctime,utime,is_effect,area_code,note,clearing_type,clearing_percentage,clearing_base) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Parkinfo_partner bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setLong(2, bean.pu_id);
                    ps.setString(3, bean.pu_nd);
                    ps.setTimestamp(4, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(5, new Timestamp(bean.utime.getTime()));
                    ps.setInt(6, bean.is_effect);
                    ps.setString(7, bean.area_code);
                    ps.setString(8, bean.note);
                    ps.setInt(9, bean.clearing_type);
                    ps.setDouble(10, bean.clearing_percentage);
                    ps.setInt(11, bean.clearing_base);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Parkinfo_partner> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Parkinfo_partner> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,pi_id,pu_id,pu_nd,ctime,utime,is_effect,area_code,note,clearing_type,clearing_percentage,clearing_base FROM "+TABLENAME2+" ORDER BY id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Parkinfo_partner>(Parkinfo_partner.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Parkinfo_partner>();
        }
    }

    //查询最新数据
    public List<Parkinfo_partner> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Parkinfo_partner> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,pi_id,pu_id,pu_nd,ctime,utime,is_effect,area_code,note,clearing_type,clearing_percentage,clearing_base FROM "+TABLENAME2+" ORDER BY id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Parkinfo_partner>(Parkinfo_partner.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Parkinfo_partner>();
        }
    }

    //根据主键查询
    public List<Parkinfo_partner> selectGtKey(long id) {
        return selectGtKey(id, TABLENAME);
    }

    //根据主键查询
    public List<Parkinfo_partner> selectGtKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,pi_id,pu_id,pu_nd,ctime,utime,is_effect,area_code,note,clearing_type,clearing_percentage,clearing_base FROM "+TABLENAME2+" WHERE id>:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Parkinfo_partner>(Parkinfo_partner.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Parkinfo_partner>();
        }
    }

    //根据主键查询
    public Parkinfo_partner selectByKey(long id) {
        return selectByKey(id, TABLENAME);
    }

    //根据主键查询
    public Parkinfo_partner selectByKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,pi_id,pu_id,pu_nd,ctime,utime,is_effect,area_code,note,clearing_type,clearing_percentage,clearing_base FROM "+TABLENAME2+" WHERE id=:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            List<Parkinfo_partner> list =  _np.query(sql, param, new BeanPropertyRowMapper<Parkinfo_partner>(Parkinfo_partner.class));
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
    public List<Parkinfo_partner> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Parkinfo_partner> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT id,pi_id,pu_id,pu_nd,ctime,utime,is_effect,area_code,note,clearing_type,clearing_percentage,clearing_base FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Parkinfo_partner>(Parkinfo_partner.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Parkinfo_partner>();
        }
    }

    //修改数据
    public int updateByKey(Parkinfo_partner bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Parkinfo_partner bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=:pi_id,pu_id=:pu_id,pu_nd=:pu_nd,ctime=:ctime,utime=:utime,is_effect=:is_effect,area_code=:area_code,note=:note,clearing_type=:clearing_type,clearing_percentage=:clearing_percentage,clearing_base=:clearing_base WHERE id=:id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Parkinfo_partner> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Parkinfo_partner> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=?,pu_id=?,pu_nd=?,ctime=?,utime=?,is_effect=?,area_code=?,note=?,clearing_type=?,clearing_percentage=?,clearing_base=? WHERE id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Parkinfo_partner bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setLong(2, bean.pu_id);
                    ps.setString(3, bean.pu_nd);
                    ps.setTimestamp(4, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(5, new Timestamp(bean.utime.getTime()));
                    ps.setInt(6, bean.is_effect);
                    ps.setString(7, bean.area_code);
                    ps.setString(8, bean.note);
                    ps.setInt(9, bean.clearing_type);
                    ps.setDouble(10, bean.clearing_percentage);
                    ps.setInt(11, bean.clearing_base);
                    ps.setLong(12, bean.id);
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
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场主键IDp_id'," +
                 "	`pu_id`  BIGINT(20) COMMENT '//bigint(20)    商户基本信息表主键IDpu_id'," +
                 "	`pu_nd`  VARCHAR(100) COMMENT '//varchar(100)    商户编号'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`is_effect`  INT(11) COMMENT '//int(11)    是否启用（0：启用1：弃用）'," +
                 "	`area_code`  VARCHAR(60) COMMENT '//varchar(60)    地区区域编码'," +
                 "	`note`  TINYTEXT COMMENT '//varchar(255)    备注'," +
                 "	`clearing_type`  INT(11) COMMENT '//int(11)    结算方案(0：不结算1：全额结算2：按基数结算)'," +
                 "	`clearing_percentage`  DOUBLE COMMENT '//double    结算比例'," +
                 "	`clearing_base`  INT(11) COMMENT '//int(11)    结算基数(整数单位分)'," +
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
