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

//etc_userpay_record

@Repository("etc_userpay_recordDao")
public class Etc_userpay_recordDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Etc_userpay_recordDao.class);



    private  String TABLE = "etc_userpay_record";

    private  String TABLENAME = "etc_userpay_record";

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


    private  String[] carrays ={"eur_id","eur_nd","eu_id","eu_nd","ui_id","ui_nd","pu_id","pu_nd","pi_id","area_code","order_id","order_type","money","state","trade_time","refund_state","refund_time","ctime","note","pi_name","is_over","pay_orderid"};
    private  String coulmns ="eur_id,eur_nd,eu_id,eu_nd,ui_id,ui_nd,pu_id,pu_nd,pi_id,area_code,order_id,order_type,money,state,trade_time,refund_state,refund_time,ctime,note,pi_name,is_over,pay_orderid";
    private  String coulmns2 ="eur_nd,eu_id,eu_nd,ui_id,ui_nd,pu_id,pu_nd,pi_id,area_code,order_id,order_type,money,state,trade_time,refund_state,refund_time,ctime,note,pi_name,is_over,pay_orderid";

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
    public int insert(Etc_userpay_record bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Etc_userpay_record bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (eur_nd,eu_id,eu_nd,ui_id,ui_nd,pu_id,pu_nd,pi_id,area_code,order_id,order_type,money,state,trade_time,refund_state,refund_time,ctime,note,pi_name,is_over,pay_orderid) VALUES (:eur_nd,:eu_id,:eu_nd,:ui_id,:ui_nd,:pu_id,:pu_nd,:pi_id,:area_code,:order_id,:order_type,:money,:state,:trade_time,:refund_state,:refund_time,:ctime,:note,:pi_name,:is_over,:pay_orderid)";
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
    public int insert_primarykey(Etc_userpay_record bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Etc_userpay_record bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (eur_id,eur_nd,eu_id,eu_nd,ui_id,ui_nd,pu_id,pu_nd,pi_id,area_code,order_id,order_type,money,state,trade_time,refund_state,refund_time,ctime,note,pi_name,is_over,pay_orderid) VALUES (:eur_id,:eur_nd,:eu_id,:eu_nd,:ui_id,:ui_nd,:pu_id,:pu_nd,:pi_id,:area_code,:order_id,:order_type,:money,:state,:trade_time,:refund_state,:refund_time,:ctime,:note,:pi_name,:is_over,:pay_orderid)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Etc_userpay_record> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Etc_userpay_record> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (eur_nd,eu_id,eu_nd,ui_id,ui_nd,pu_id,pu_nd,pi_id,area_code,order_id,order_type,money,state,trade_time,refund_state,refund_time,ctime,note,pi_name,is_over,pay_orderid) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Etc_userpay_record bean = beans.get(i);
                    ps.setString(1, bean.eur_nd);
                    ps.setLong(2, bean.eu_id);
                    ps.setString(3, bean.eu_nd);
                    ps.setLong(4, bean.ui_id);
                    ps.setString(5, bean.ui_nd);
                    ps.setLong(6, bean.pu_id);
                    ps.setString(7, bean.pu_nd);
                    ps.setLong(8, bean.pi_id);
                    ps.setString(9, bean.area_code);
                    ps.setString(10, bean.order_id);
                    ps.setInt(11, bean.order_type);
                    ps.setLong(12, bean.money);
                    ps.setInt(13, bean.state);
                    ps.setTimestamp(14, new Timestamp(bean.trade_time.getTime()));
                    ps.setInt(15, bean.refund_state);
                    ps.setTimestamp(16, new Timestamp(bean.refund_time.getTime()));
                    ps.setTimestamp(17, new Timestamp(bean.ctime.getTime()));
                    ps.setString(18, bean.note);
                    ps.setString(19, bean.pi_name);
                    ps.setInt(20, bean.is_over);
                    ps.setString(21, bean.pay_orderid);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Etc_userpay_record> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Etc_userpay_record> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT eur_id,eur_nd,eu_id,eu_nd,ui_id,ui_nd,pu_id,pu_nd,pi_id,area_code,order_id,order_type,money,state,trade_time,refund_state,refund_time,ctime,note,pi_name,is_over,pay_orderid FROM "+TABLENAME2+" ORDER BY eur_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Etc_userpay_record>(Etc_userpay_record.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Etc_userpay_record>();
        }
    }

    //查询最新数据
    public List<Etc_userpay_record> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Etc_userpay_record> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT eur_id,eur_nd,eu_id,eu_nd,ui_id,ui_nd,pu_id,pu_nd,pi_id,area_code,order_id,order_type,money,state,trade_time,refund_state,refund_time,ctime,note,pi_name,is_over,pay_orderid FROM "+TABLENAME2+" ORDER BY eur_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Etc_userpay_record>(Etc_userpay_record.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Etc_userpay_record>();
        }
    }

    //根据主键查询
    public List<Etc_userpay_record> selectGtKey(long eur_id) {
        return selectGtKey(eur_id, TABLENAME);
    }

    //根据主键查询
    public List<Etc_userpay_record> selectGtKey(long eur_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT eur_id,eur_nd,eu_id,eu_nd,ui_id,ui_nd,pu_id,pu_nd,pi_id,area_code,order_id,order_type,money,state,trade_time,refund_state,refund_time,ctime,note,pi_name,is_over,pay_orderid FROM "+TABLENAME2+" WHERE eur_id>:eur_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("eur_id", eur_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Etc_userpay_record>(Etc_userpay_record.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Etc_userpay_record>();
        }
    }

    //根据主键查询
    public Etc_userpay_record selectByKey(long eur_id) {
        return selectByKey(eur_id, TABLENAME);
    }

    //根据主键查询
    public Etc_userpay_record selectByKey(long eur_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT eur_id,eur_nd,eu_id,eu_nd,ui_id,ui_nd,pu_id,pu_nd,pi_id,area_code,order_id,order_type,money,state,trade_time,refund_state,refund_time,ctime,note,pi_name,is_over,pay_orderid FROM "+TABLENAME2+" WHERE eur_id=:eur_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("eur_id", eur_id);
            List<Etc_userpay_record> list =  _np.query(sql, param, new BeanPropertyRowMapper<Etc_userpay_record>(Etc_userpay_record.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey eur_id="+eur_id,e);
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
    public List<Etc_userpay_record> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Etc_userpay_record> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT eur_id,eur_nd,eu_id,eu_nd,ui_id,ui_nd,pu_id,pu_nd,pi_id,area_code,order_id,order_type,money,state,trade_time,refund_state,refund_time,ctime,note,pi_name,is_over,pay_orderid FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Etc_userpay_record>(Etc_userpay_record.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Etc_userpay_record>();
        }
    }

    //修改数据
    public int updateByKey(Etc_userpay_record bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Etc_userpay_record bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET eur_nd=:eur_nd,eu_id=:eu_id,eu_nd=:eu_nd,ui_id=:ui_id,ui_nd=:ui_nd,pu_id=:pu_id,pu_nd=:pu_nd,pi_id=:pi_id,area_code=:area_code,order_id=:order_id,order_type=:order_type,money=:money,state=:state,trade_time=:trade_time,refund_state=:refund_state,refund_time=:refund_time,ctime=:ctime,note=:note,pi_name=:pi_name,is_over=:is_over,pay_orderid=:pay_orderid WHERE eur_id=:eur_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Etc_userpay_record> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Etc_userpay_record> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET eur_nd=?,eu_id=?,eu_nd=?,ui_id=?,ui_nd=?,pu_id=?,pu_nd=?,pi_id=?,area_code=?,order_id=?,order_type=?,money=?,state=?,trade_time=?,refund_state=?,refund_time=?,ctime=?,note=?,pi_name=?,is_over=?,pay_orderid=? WHERE eur_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Etc_userpay_record bean = beans.get(i);
                    ps.setString(1, bean.eur_nd);
                    ps.setLong(2, bean.eu_id);
                    ps.setString(3, bean.eu_nd);
                    ps.setLong(4, bean.ui_id);
                    ps.setString(5, bean.ui_nd);
                    ps.setLong(6, bean.pu_id);
                    ps.setString(7, bean.pu_nd);
                    ps.setLong(8, bean.pi_id);
                    ps.setString(9, bean.area_code);
                    ps.setString(10, bean.order_id);
                    ps.setInt(11, bean.order_type);
                    ps.setLong(12, bean.money);
                    ps.setInt(13, bean.state);
                    ps.setTimestamp(14, new Timestamp(bean.trade_time.getTime()));
                    ps.setInt(15, bean.refund_state);
                    ps.setTimestamp(16, new Timestamp(bean.refund_time.getTime()));
                    ps.setTimestamp(17, new Timestamp(bean.ctime.getTime()));
                    ps.setString(18, bean.note);
                    ps.setString(19, bean.pi_name);
                    ps.setInt(20, bean.is_over);
                    ps.setString(21, bean.pay_orderid);
                    ps.setLong(22, bean.eur_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long eur_id) throws SQLException{
        return deleteByKey(eur_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long eur_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE eur_id=:eur_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("eur_id", eur_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE eur_id=?";
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
                 "	`eur_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`eur_nd`  VARCHAR(100) COMMENT '//varchar(100)    唯一标识符nd'," +
                 "	`eu_id`  BIGINT(20) COMMENT '//bigint(20)    ETC用户资料表主键ID'," +
                 "	`eu_nd`  VARCHAR(100) COMMENT '//varchar(100)    ETC用户资料表唯一标识ND'," +
                 "	`ui_id`  BIGINT(20) COMMENT '//bigint(20)    平台用户ID'," +
                 "	`ui_nd`  VARCHAR(100) COMMENT '//varchar(100)    平台用户唯一标识ND'," +
                 "	`pu_id`  BIGINT(20) COMMENT '//bigint(20)    商户主键ID'," +
                 "	`pu_nd`  VARCHAR(100) COMMENT '//varchar(100)    商户唯一标识ND'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场ID'," +
                 "	`area_code`  VARCHAR(30) COMMENT '//varchar(30)    地址编码'," +
                 "	`order_id`  VARCHAR(100) COMMENT '//varchar(100)    车辆订单号'," +
                 "	`order_type`  INT(11) COMMENT '//int(11)    订单类型（0：临停订单1：租赁订单）'," +
                 "	`money`  BIGINT(20) COMMENT '//bigint(20)    ETC支付金额单位分'," +
                 "	`state`  INT(11) COMMENT '//int(11)    是否支付成功(0:未支付1：支付成功2：支付失败)'," +
                 "	`trade_time`  DATETIME COMMENT '//datetime    支付交易时间'," +
                 "	`refund_state`  INT(11) COMMENT '//int(11)    是否进行了退款（0：没有退款1：退款成功2：退款失败）'," +
                 "	`refund_time`  DATETIME COMMENT '//datetime    退款行为发生时间'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`pi_name`  VARCHAR(80) COMMENT '//varchar(80)    停车场名称'," +
                 "	`is_over`  INT(11) COMMENT '//int(11)    订单事件是否完成ETC支付（0：未完成1：完成）'," +
                 "	`pay_orderid`  VARCHAR(60) NOT NULL COMMENT '//varchar(60)    支付订单号（本地事物单号）'," +
                 "	PRIMARY KEY (`eur_id`)" +
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
