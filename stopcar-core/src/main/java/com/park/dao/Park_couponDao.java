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

//park_coupon

@Repository("park_couponDao")
public class Park_couponDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Park_couponDao.class);



    private  String TABLE = "park_coupon";

    private  String TABLENAME = "park_coupon";

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


    private  String[] carrays ={"id","name","money","discount","high_money","end_time","use_range","pc_type","ctime","utime","note","ai_id"};
    private  String coulmns ="id,name,money,discount,high_money,end_time,use_range,pc_type,ctime,utime,note,ai_id";
    private  String coulmns2 ="name,money,discount,high_money,end_time,use_range,pc_type,ctime,utime,note,ai_id";

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
    public int insert(Park_coupon bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Park_coupon bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (name,money,discount,high_money,end_time,use_range,pc_type,ctime,utime,note,ai_id) VALUES (:name,:money,:discount,:high_money,:end_time,:use_range,:pc_type,:ctime,:utime,:note,:ai_id)";
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
    public int insert_primarykey(Park_coupon bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Park_coupon bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (id,name,money,discount,high_money,end_time,use_range,pc_type,ctime,utime,note,ai_id) VALUES (:id,:name,:money,:discount,:high_money,:end_time,:use_range,:pc_type,:ctime,:utime,:note,:ai_id)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Park_coupon> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Park_coupon> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (name,money,discount,high_money,end_time,use_range,pc_type,ctime,utime,note,ai_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_coupon bean = beans.get(i);
                    ps.setString(1, bean.name);
                    ps.setInt(2, bean.money);
                    ps.setDouble(3, bean.discount);
                    ps.setInt(4, bean.high_money);
                    ps.setTimestamp(5, new Timestamp(bean.end_time.getTime()));
                    ps.setInt(6, bean.use_range);
                    ps.setInt(7, bean.pc_type);
                    ps.setTimestamp(8, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(9, new Timestamp(bean.utime.getTime()));
                    ps.setString(10, bean.note);
                    ps.setLong(11, bean.ai_id);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Park_coupon> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Park_coupon> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,name,money,discount,high_money,end_time,use_range,pc_type,ctime,utime,note,ai_id FROM "+TABLENAME2+" ORDER BY id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_coupon>(Park_coupon.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Park_coupon>();
        }
    }

    //查询最新数据
    public List<Park_coupon> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Park_coupon> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,name,money,discount,high_money,end_time,use_range,pc_type,ctime,utime,note,ai_id FROM "+TABLENAME2+" ORDER BY id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_coupon>(Park_coupon.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Park_coupon>();
        }
    }

    //根据主键查询
    public List<Park_coupon> selectGtKey(long id) {
        return selectGtKey(id, TABLENAME);
    }

    //根据主键查询
    public List<Park_coupon> selectGtKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,name,money,discount,high_money,end_time,use_range,pc_type,ctime,utime,note,ai_id FROM "+TABLENAME2+" WHERE id>:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Park_coupon>(Park_coupon.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Park_coupon>();
        }
    }

    //根据主键查询
    public Park_coupon selectByKey(long id) {
        return selectByKey(id, TABLENAME);
    }

    //根据主键查询
    public Park_coupon selectByKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,name,money,discount,high_money,end_time,use_range,pc_type,ctime,utime,note,ai_id FROM "+TABLENAME2+" WHERE id=:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            List<Park_coupon> list =  _np.query(sql, param, new BeanPropertyRowMapper<Park_coupon>(Park_coupon.class));
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
    public List<Park_coupon> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Park_coupon> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT id,name,money,discount,high_money,end_time,use_range,pc_type,ctime,utime,note,ai_id FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Park_coupon>(Park_coupon.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Park_coupon>();
        }
    }

    //修改数据
    public int updateByKey(Park_coupon bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Park_coupon bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET name=:name,money=:money,discount=:discount,high_money=:high_money,end_time=:end_time,use_range=:use_range,pc_type=:pc_type,ctime=:ctime,utime=:utime,note=:note,ai_id=:ai_id WHERE id=:id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_coupon> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_coupon> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET name=?,money=?,discount=?,high_money=?,end_time=?,use_range=?,pc_type=?,ctime=?,utime=?,note=?,ai_id=? WHERE id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_coupon bean = beans.get(i);
                    ps.setString(1, bean.name);
                    ps.setInt(2, bean.money);
                    ps.setDouble(3, bean.discount);
                    ps.setInt(4, bean.high_money);
                    ps.setTimestamp(5, new Timestamp(bean.end_time.getTime()));
                    ps.setInt(6, bean.use_range);
                    ps.setInt(7, bean.pc_type);
                    ps.setTimestamp(8, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(9, new Timestamp(bean.utime.getTime()));
                    ps.setString(10, bean.note);
                    ps.setLong(11, bean.ai_id);
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
                 "	`id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`name`  VARCHAR(100) COMMENT '//varchar(100)    '," +
                 "	`money`  INT(11) COMMENT '//int(11)    优惠券金额(单位分)'," +
                 "	`discount`  DOUBLE COMMENT '//double(2,2)    折扣券折数'," +
                 "	`high_money`  INT(11) COMMENT '//int(11)    最高抵扣金额'," +
                 "	`end_time`  DATETIME COMMENT '//datetime    有效期到期时间'," +
                 "	`use_range`  INT(11) COMMENT '//int(11)    使用范围0：爱泊车场通用1：其它车场使用'," +
                 "	`pc_type`  INT(11) COMMENT '//int(11)    优惠券类型0:金额券1：折扣券'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`ai_id`  BIGINT(20) COMMENT '//bigint(20)    活动基本信息表主键ID（外键）'," +
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
