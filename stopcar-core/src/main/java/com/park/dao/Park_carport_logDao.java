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

//park_carport_log

@Repository("park_carport_logDao")
public class Park_carport_logDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Park_carport_logDao.class);



    private  String TABLE = "park_carport_log";

    private  String TABLENAME = "park_carport_log";

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


    private  String[] carrays ={"pcl_id","pi_id","area_code","carport_total","carport_yet","carport_space","park_type","data_flag","ctime","stime","note"};
    private  String coulmns ="pcl_id,pi_id,area_code,carport_total,carport_yet,carport_space,park_type,data_flag,ctime,stime,note";
    private  String coulmns2 ="pi_id,area_code,carport_total,carport_yet,carport_space,park_type,data_flag,ctime,stime,note";

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
    public int insert(Park_carport_log bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Park_carport_log bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,area_code,carport_total,carport_yet,carport_space,park_type,data_flag,ctime,stime,note) VALUES (:pi_id,:area_code,:carport_total,:carport_yet,:carport_space,:park_type,:data_flag,:ctime,:stime,:note)";
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
    public int insert_primarykey(Park_carport_log bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Park_carport_log bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pcl_id,pi_id,area_code,carport_total,carport_yet,carport_space,park_type,data_flag,ctime,stime,note) VALUES (:pcl_id,:pi_id,:area_code,:carport_total,:carport_yet,:carport_space,:park_type,:data_flag,:ctime,:stime,:note)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Park_carport_log> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Park_carport_log> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,area_code,carport_total,carport_yet,carport_space,park_type,data_flag,ctime,stime,note) VALUES (?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_carport_log bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setString(2, bean.area_code);
                    ps.setInt(3, bean.carport_total);
                    ps.setInt(4, bean.carport_yet);
                    ps.setInt(5, bean.carport_space);
                    ps.setInt(6, bean.park_type);
                    ps.setInt(7, bean.data_flag);
                    ps.setTimestamp(8, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(9, new Timestamp(bean.stime.getTime()));
                    ps.setString(10, bean.note);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Park_carport_log> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Park_carport_log> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pcl_id,pi_id,area_code,carport_total,carport_yet,carport_space,park_type,data_flag,ctime,stime,note FROM "+TABLENAME2+" ORDER BY pcl_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_carport_log>(Park_carport_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Park_carport_log>();
        }
    }

    //查询最新数据
    public List<Park_carport_log> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Park_carport_log> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pcl_id,pi_id,area_code,carport_total,carport_yet,carport_space,park_type,data_flag,ctime,stime,note FROM "+TABLENAME2+" ORDER BY pcl_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_carport_log>(Park_carport_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Park_carport_log>();
        }
    }

    //根据主键查询
    public List<Park_carport_log> selectGtKey(long pcl_id) {
        return selectGtKey(pcl_id, TABLENAME);
    }

    //根据主键查询
    public List<Park_carport_log> selectGtKey(long pcl_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pcl_id,pi_id,area_code,carport_total,carport_yet,carport_space,park_type,data_flag,ctime,stime,note FROM "+TABLENAME2+" WHERE pcl_id>:pcl_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pcl_id", pcl_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Park_carport_log>(Park_carport_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Park_carport_log>();
        }
    }

    //根据主键查询
    public Park_carport_log selectByKey(long pcl_id) {
        return selectByKey(pcl_id, TABLENAME);
    }

    //根据主键查询
    public Park_carport_log selectByKey(long pcl_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pcl_id,pi_id,area_code,carport_total,carport_yet,carport_space,park_type,data_flag,ctime,stime,note FROM "+TABLENAME2+" WHERE pcl_id=:pcl_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pcl_id", pcl_id);
            List<Park_carport_log> list =  _np.query(sql, param, new BeanPropertyRowMapper<Park_carport_log>(Park_carport_log.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey pcl_id="+pcl_id,e);
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
    public List<Park_carport_log> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Park_carport_log> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT pcl_id,pi_id,area_code,carport_total,carport_yet,carport_space,park_type,data_flag,ctime,stime,note FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Park_carport_log>(Park_carport_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Park_carport_log>();
        }
    }

    //修改数据
    public int updateByKey(Park_carport_log bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Park_carport_log bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=:pi_id,area_code=:area_code,carport_total=:carport_total,carport_yet=:carport_yet,carport_space=:carport_space,park_type=:park_type,data_flag=:data_flag,ctime=:ctime,stime=:stime,note=:note WHERE pcl_id=:pcl_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_carport_log> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_carport_log> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=?,area_code=?,carport_total=?,carport_yet=?,carport_space=?,park_type=?,data_flag=?,ctime=?,stime=?,note=? WHERE pcl_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_carport_log bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setString(2, bean.area_code);
                    ps.setInt(3, bean.carport_total);
                    ps.setInt(4, bean.carport_yet);
                    ps.setInt(5, bean.carport_space);
                    ps.setInt(6, bean.park_type);
                    ps.setInt(7, bean.data_flag);
                    ps.setTimestamp(8, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(9, new Timestamp(bean.stime.getTime()));
                    ps.setString(10, bean.note);
                    ps.setLong(11, bean.pcl_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long pcl_id) throws SQLException{
        return deleteByKey(pcl_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long pcl_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE pcl_id=:pcl_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pcl_id", pcl_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE pcl_id=?";
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
                 "	`pcl_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场主键ID'," +
                 "	`area_code`  VARCHAR(30) COMMENT '//varchar(30)    停车场地址区域编码'," +
                 "	`carport_total`  INT(11) COMMENT '//int(11)    车位总数'," +
                 "	`carport_yet`  INT(11) COMMENT '//int(11)    已停车位数'," +
                 "	`carport_space`  INT(11) COMMENT '//int(11)    空余车位数'," +
                 "	`park_type`  INT(11) COMMENT '//int(11)    停车场类型(停车场类型0：道闸停车场1：占道车场2：露天立体车库停车场)'," +
                 "	`data_flag`  INT(11) COMMENT '//int(11)    上传来源1：普通占道停车场2：地磁占道停车场3：道闸停车场'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    停车场本地时间'," +
                 "	`stime`  DATETIME COMMENT '//datetime    接收数据时间'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	PRIMARY KEY (`pcl_id`)" +
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
