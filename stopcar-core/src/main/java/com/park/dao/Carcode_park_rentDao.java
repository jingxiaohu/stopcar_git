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

//carcode_park_rent

@Repository("carcode_park_rentDao")
public class Carcode_park_rentDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Carcode_park_rentDao.class);



    private  String TABLE = "carcode_park_rent";

    private  String TABLENAME = "carcode_park_rent";

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


    private  String[] carrays ={"cpr_id","pi_id","area_code","pi_name","unit_price","starttime","endtime","stime","utime","ui_id","ui_nd","car_code","permit_time","rent_type","is_del","is_expire","note","client_rule_id","address_name","rd_id","client_father_orderid"};
    private  String coulmns ="cpr_id,pi_id,area_code,pi_name,unit_price,starttime,endtime,stime,utime,ui_id,ui_nd,car_code,permit_time,rent_type,is_del,is_expire,note,client_rule_id,address_name,rd_id,client_father_orderid";
    private  String coulmns2 ="pi_id,area_code,pi_name,unit_price,starttime,endtime,stime,utime,ui_id,ui_nd,car_code,permit_time,rent_type,is_del,is_expire,note,client_rule_id,address_name,rd_id,client_father_orderid";

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
    public int insert(Carcode_park_rent bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Carcode_park_rent bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,area_code,pi_name,unit_price,starttime,endtime,stime,utime,ui_id,ui_nd,car_code,permit_time,rent_type,is_del,is_expire,note,client_rule_id,address_name,rd_id,client_father_orderid) VALUES (:pi_id,:area_code,:pi_name,:unit_price,:starttime,:endtime,:stime,:utime,:ui_id,:ui_nd,:car_code,:permit_time,:rent_type,:is_del,:is_expire,:note,:client_rule_id,:address_name,:rd_id,:client_father_orderid)";
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
    public int insert_primarykey(Carcode_park_rent bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Carcode_park_rent bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (cpr_id,pi_id,area_code,pi_name,unit_price,starttime,endtime,stime,utime,ui_id,ui_nd,car_code,permit_time,rent_type,is_del,is_expire,note,client_rule_id,address_name,rd_id,client_father_orderid) VALUES (:cpr_id,:pi_id,:area_code,:pi_name,:unit_price,:starttime,:endtime,:stime,:utime,:ui_id,:ui_nd,:car_code,:permit_time,:rent_type,:is_del,:is_expire,:note,:client_rule_id,:address_name,:rd_id,:client_father_orderid)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Carcode_park_rent> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Carcode_park_rent> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,area_code,pi_name,unit_price,starttime,endtime,stime,utime,ui_id,ui_nd,car_code,permit_time,rent_type,is_del,is_expire,note,client_rule_id,address_name,rd_id,client_father_orderid) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Carcode_park_rent bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setString(2, bean.area_code);
                    ps.setString(3, bean.pi_name);
                    ps.setInt(4, bean.unit_price);
                    ps.setTimestamp(5, new Timestamp(bean.starttime.getTime()));
                    ps.setTimestamp(6, new Timestamp(bean.endtime.getTime()));
                    ps.setTimestamp(7, new Timestamp(bean.stime.getTime()));
                    ps.setTimestamp(8, new Timestamp(bean.utime.getTime()));
                    ps.setLong(9, bean.ui_id);
                    ps.setString(10, bean.ui_nd);
                    ps.setString(11, bean.car_code);
                    ps.setString(12, bean.permit_time);
                    ps.setInt(13, bean.rent_type);
                    ps.setInt(14, bean.is_del);
                    ps.setInt(15, bean.is_expire);
                    ps.setString(16, bean.note);
                    ps.setString(17, bean.client_rule_id);
                    ps.setString(18, bean.address_name);
                    ps.setLong(19, bean.rd_id);
                    ps.setString(20, bean.client_father_orderid);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Carcode_park_rent> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Carcode_park_rent> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT cpr_id,pi_id,area_code,pi_name,unit_price,starttime,endtime,stime,utime,ui_id,ui_nd,car_code,permit_time,rent_type,is_del,is_expire,note,client_rule_id,address_name,rd_id,client_father_orderid FROM "+TABLENAME2+" ORDER BY cpr_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Carcode_park_rent>(Carcode_park_rent.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Carcode_park_rent>();
        }
    }

    //查询最新数据
    public List<Carcode_park_rent> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Carcode_park_rent> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT cpr_id,pi_id,area_code,pi_name,unit_price,starttime,endtime,stime,utime,ui_id,ui_nd,car_code,permit_time,rent_type,is_del,is_expire,note,client_rule_id,address_name,rd_id,client_father_orderid FROM "+TABLENAME2+" ORDER BY cpr_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Carcode_park_rent>(Carcode_park_rent.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Carcode_park_rent>();
        }
    }

    //根据主键查询
    public List<Carcode_park_rent> selectGtKey(long cpr_id) {
        return selectGtKey(cpr_id, TABLENAME);
    }

    //根据主键查询
    public List<Carcode_park_rent> selectGtKey(long cpr_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT cpr_id,pi_id,area_code,pi_name,unit_price,starttime,endtime,stime,utime,ui_id,ui_nd,car_code,permit_time,rent_type,is_del,is_expire,note,client_rule_id,address_name,rd_id,client_father_orderid FROM "+TABLENAME2+" WHERE cpr_id>:cpr_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("cpr_id", cpr_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Carcode_park_rent>(Carcode_park_rent.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Carcode_park_rent>();
        }
    }

    //根据主键查询
    public Carcode_park_rent selectByKey(long cpr_id) {
        return selectByKey(cpr_id, TABLENAME);
    }

    //根据主键查询
    public Carcode_park_rent selectByKey(long cpr_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT cpr_id,pi_id,area_code,pi_name,unit_price,starttime,endtime,stime,utime,ui_id,ui_nd,car_code,permit_time,rent_type,is_del,is_expire,note,client_rule_id,address_name,rd_id,client_father_orderid FROM "+TABLENAME2+" WHERE cpr_id=:cpr_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("cpr_id", cpr_id);
            List<Carcode_park_rent> list =  _np.query(sql, param, new BeanPropertyRowMapper<Carcode_park_rent>(Carcode_park_rent.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey cpr_id="+cpr_id,e);
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
    public List<Carcode_park_rent> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Carcode_park_rent> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT cpr_id,pi_id,area_code,pi_name,unit_price,starttime,endtime,stime,utime,ui_id,ui_nd,car_code,permit_time,rent_type,is_del,is_expire,note,client_rule_id,address_name,rd_id,client_father_orderid FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Carcode_park_rent>(Carcode_park_rent.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Carcode_park_rent>();
        }
    }

    //修改数据
    public int updateByKey(Carcode_park_rent bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Carcode_park_rent bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=:pi_id,area_code=:area_code,pi_name=:pi_name,unit_price=:unit_price,starttime=:starttime,endtime=:endtime,stime=:stime,utime=:utime,ui_id=:ui_id,ui_nd=:ui_nd,car_code=:car_code,permit_time=:permit_time,rent_type=:rent_type,is_del=:is_del,is_expire=:is_expire,note=:note,client_rule_id=:client_rule_id,address_name=:address_name,rd_id=:rd_id,client_father_orderid=:client_father_orderid WHERE cpr_id=:cpr_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Carcode_park_rent> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Carcode_park_rent> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=?,area_code=?,pi_name=?,unit_price=?,starttime=?,endtime=?,stime=?,utime=?,ui_id=?,ui_nd=?,car_code=?,permit_time=?,rent_type=?,is_del=?,is_expire=?,note=?,client_rule_id=?,address_name=?,rd_id=?,client_father_orderid=? WHERE cpr_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Carcode_park_rent bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setString(2, bean.area_code);
                    ps.setString(3, bean.pi_name);
                    ps.setInt(4, bean.unit_price);
                    ps.setTimestamp(5, new Timestamp(bean.starttime.getTime()));
                    ps.setTimestamp(6, new Timestamp(bean.endtime.getTime()));
                    ps.setTimestamp(7, new Timestamp(bean.stime.getTime()));
                    ps.setTimestamp(8, new Timestamp(bean.utime.getTime()));
                    ps.setLong(9, bean.ui_id);
                    ps.setString(10, bean.ui_nd);
                    ps.setString(11, bean.car_code);
                    ps.setString(12, bean.permit_time);
                    ps.setInt(13, bean.rent_type);
                    ps.setInt(14, bean.is_del);
                    ps.setInt(15, bean.is_expire);
                    ps.setString(16, bean.note);
                    ps.setString(17, bean.client_rule_id);
                    ps.setString(18, bean.address_name);
                    ps.setLong(19, bean.rd_id);
                    ps.setString(20, bean.client_father_orderid);
                    ps.setLong(21, bean.cpr_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long cpr_id) throws SQLException{
        return deleteByKey(cpr_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long cpr_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE cpr_id=:cpr_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("cpr_id", cpr_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE cpr_id=?";
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
                 "	`cpr_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场主键ID'," +
                 "	`area_code`  VARCHAR(30) COMMENT '//varchar(30)    停车场地址编码'," +
                 "	`pi_name`  VARCHAR(80) COMMENT '//varchar(80)    停车场名称'," +
                 "	`unit_price`  INT(11) COMMENT '//int(11)    租赁每个月单价上次成功续租或者租赁金额（单位分)'," +
                 "	`starttime`  DATETIME COMMENT '//datetime    开始时间'," +
                 "	`endtime`  DATETIME COMMENT '//datetime    到期时间'," +
                 "	`stime`  DATETIME COMMENT '//datetime    服务器端接收时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`ui_id`  BIGINT(20) COMMENT '//bigint(20)    用户主键ID'," +
                 "	`ui_nd`  VARCHAR(80) COMMENT '//varchar(80)    用户ND'," +
                 "	`car_code`  VARCHAR(80) COMMENT '//varchar(80)    用户车牌号'," +
                 "	`permit_time`  VARCHAR(80) COMMENT '//varchar(80)    允许时间段（8：00-23：00）上次成功续租或者租赁允许时间段'," +
                 "	`rent_type`  INT(11) COMMENT '//int(11)    租赁类型（0：未指定1：普通分时间段租赁2：垮天分时间段租赁3：全天）'," +
                 "	`is_del`  INT(11) COMMENT '//int(11)    是否逻辑删除(0:正常1：删除)'," +
                 "	`is_expire`  INT(11) COMMENT '//int(11)    是否已经到期（0：未到期1：已经到期）'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`client_rule_id`  VARCHAR(80) COMMENT '//varchar(80)    客户端的规则ID'," +
                 "	`address_name`  TINYTEXT COMMENT '//varchar(255)    停车场地址'," +
                 "	`rd_id`  BIGINT(20) COMMENT '//bigint(20)    rent_defer表主键ID（最后一次修改carcode_park_rent表对应的ID）'," +
                 "	`client_father_orderid`  VARCHAR(80) COMMENT '//varchar(80)    客户端根节点订单号'," +
                 "	PRIMARY KEY (`cpr_id`)" +
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
