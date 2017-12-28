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

//order_abnormal_log

@Repository("order_abnormal_logDao")
public class Order_abnormal_logDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Order_abnormal_logDao.class);



    private  String TABLE = "order_abnormal_log";

    private  String TABLENAME = "order_abnormal_log";

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


    private  String[] carrays ={"id","order_id","server_type","client_type","money","ctime","pi_id","area_code","pi_name","is_asyn","note"};
    private  String coulmns ="id,order_id,server_type,client_type,money,ctime,pi_id,area_code,pi_name,is_asyn,note";
    private  String coulmns2 ="order_id,server_type,client_type,money,ctime,pi_id,area_code,pi_name,is_asyn,note";

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
    public int insert(Order_abnormal_log bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Order_abnormal_log bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (order_id,server_type,client_type,money,ctime,pi_id,area_code,pi_name,is_asyn,note) VALUES (:order_id,:server_type,:client_type,:money,:ctime,:pi_id,:area_code,:pi_name,:is_asyn,:note)";
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
    public int insert_primarykey(Order_abnormal_log bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Order_abnormal_log bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (id,order_id,server_type,client_type,money,ctime,pi_id,area_code,pi_name,is_asyn,note) VALUES (:id,:order_id,:server_type,:client_type,:money,:ctime,:pi_id,:area_code,:pi_name,:is_asyn,:note)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Order_abnormal_log> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Order_abnormal_log> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (order_id,server_type,client_type,money,ctime,pi_id,area_code,pi_name,is_asyn,note) VALUES (?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Order_abnormal_log bean = beans.get(i);
                    ps.setString(1, bean.order_id);
                    ps.setInt(2, bean.server_type);
                    ps.setInt(3, bean.client_type);
                    ps.setLong(4, bean.money);
                    ps.setTimestamp(5, new Timestamp(bean.ctime.getTime()));
                    ps.setLong(6, bean.pi_id);
                    ps.setString(7, bean.area_code);
                    ps.setString(8, bean.pi_name);
                    ps.setInt(9, bean.is_asyn);
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
    public List<Order_abnormal_log> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Order_abnormal_log> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,order_id,server_type,client_type,money,ctime,pi_id,area_code,pi_name,is_asyn,note FROM "+TABLENAME2+" ORDER BY id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Order_abnormal_log>(Order_abnormal_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Order_abnormal_log>();
        }
    }

    //查询最新数据
    public List<Order_abnormal_log> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Order_abnormal_log> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,order_id,server_type,client_type,money,ctime,pi_id,area_code,pi_name,is_asyn,note FROM "+TABLENAME2+" ORDER BY id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Order_abnormal_log>(Order_abnormal_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Order_abnormal_log>();
        }
    }

    //根据主键查询
    public List<Order_abnormal_log> selectGtKey(long id) {
        return selectGtKey(id, TABLENAME);
    }

    //根据主键查询
    public List<Order_abnormal_log> selectGtKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,order_id,server_type,client_type,money,ctime,pi_id,area_code,pi_name,is_asyn,note FROM "+TABLENAME2+" WHERE id>:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Order_abnormal_log>(Order_abnormal_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Order_abnormal_log>();
        }
    }

    //根据主键查询
    public Order_abnormal_log selectByKey(long id) {
        return selectByKey(id, TABLENAME);
    }

    //根据主键查询
    public Order_abnormal_log selectByKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,order_id,server_type,client_type,money,ctime,pi_id,area_code,pi_name,is_asyn,note FROM "+TABLENAME2+" WHERE id=:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            List<Order_abnormal_log> list =  _np.query(sql, param, new BeanPropertyRowMapper<Order_abnormal_log>(Order_abnormal_log.class));
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
    public List<Order_abnormal_log> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Order_abnormal_log> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT id,order_id,server_type,client_type,money,ctime,pi_id,area_code,pi_name,is_asyn,note FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Order_abnormal_log>(Order_abnormal_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Order_abnormal_log>();
        }
    }

    //修改数据
    public int updateByKey(Order_abnormal_log bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Order_abnormal_log bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET order_id=:order_id,server_type=:server_type,client_type=:client_type,money=:money,ctime=:ctime,pi_id=:pi_id,area_code=:area_code,pi_name=:pi_name,is_asyn=:is_asyn,note=:note WHERE id=:id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Order_abnormal_log> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Order_abnormal_log> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET order_id=?,server_type=?,client_type=?,money=?,ctime=?,pi_id=?,area_code=?,pi_name=?,is_asyn=?,note=? WHERE id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Order_abnormal_log bean = beans.get(i);
                    ps.setString(1, bean.order_id);
                    ps.setInt(2, bean.server_type);
                    ps.setInt(3, bean.client_type);
                    ps.setLong(4, bean.money);
                    ps.setTimestamp(5, new Timestamp(bean.ctime.getTime()));
                    ps.setLong(6, bean.pi_id);
                    ps.setString(7, bean.area_code);
                    ps.setString(8, bean.pi_name);
                    ps.setInt(9, bean.is_asyn);
                    ps.setString(10, bean.note);
                    ps.setLong(11, bean.id);
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
                 "	`order_id`  VARCHAR(100) COMMENT '//varchar(100)    订单号'," +
                 "	`server_type`  INT(11) COMMENT '//int(11)    订单服务器端支付类型0：未指定1:支付宝2：微信3：银联4：钱包5:龙支付6:ETC快捷支付7：扫脸支付8：指纹支付9：指静脉支付'," +
                 "	`client_type`  INT(11) COMMENT '//int(11)    订单接收到客户端支付类型0：未指定1：现金支付2：线上支付'," +
                 "	`money`  BIGINT(20) COMMENT '//bigint(20)    订单金额单位分'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场主键ID'," +
                 "	`area_code`  VARCHAR(60) COMMENT '//varchar(60)    地址区域编码'," +
                 "	`pi_name`  VARCHAR(100) COMMENT '//varchar(100)    停车场名称'," +
                 "	`is_asyn`  INT(11) COMMENT '//int(11)    是否是异步传输(0:同步1：异步)'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
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
