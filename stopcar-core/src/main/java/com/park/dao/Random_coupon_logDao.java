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

//random_coupon_log

@Repository("random_coupon_logDao")
public class Random_coupon_logDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Random_coupon_logDao.class);



    private  String TABLE = "random_coupon_log";

    private  String TABLENAME = "random_coupon_log";

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


    private  String[] carrays ={"rcl_id","nd","car_code","money","upc_id","type","pi_id","pi_name","area_code","orderid","order_type","partner","ctime","note","ai_id","ui_id","ui_nd","ae_id","send_unit","act_type","pay_source"};
    private  String coulmns ="rcl_id,nd,car_code,money,upc_id,type,pi_id,pi_name,area_code,orderid,order_type,partner,ctime,note,ai_id,ui_id,ui_nd,ae_id,send_unit,act_type,pay_source";
    private  String coulmns2 ="nd,car_code,money,upc_id,type,pi_id,pi_name,area_code,orderid,order_type,partner,ctime,note,ai_id,ui_id,ui_nd,ae_id,send_unit,act_type,pay_source";

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
    public int insert(Random_coupon_log bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Random_coupon_log bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (nd,car_code,money,upc_id,type,pi_id,pi_name,area_code,orderid,order_type,partner,ctime,note,ai_id,ui_id,ui_nd,ae_id,send_unit,act_type,pay_source) VALUES (:nd,:car_code,:money,:upc_id,:type,:pi_id,:pi_name,:area_code,:orderid,:order_type,:partner,:ctime,:note,:ai_id,:ui_id,:ui_nd,:ae_id,:send_unit,:act_type,:pay_source)";
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
    public int insert_primarykey(Random_coupon_log bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Random_coupon_log bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (rcl_id,nd,car_code,money,upc_id,type,pi_id,pi_name,area_code,orderid,order_type,partner,ctime,note,ai_id,ui_id,ui_nd,ae_id,send_unit,act_type,pay_source) VALUES (:rcl_id,:nd,:car_code,:money,:upc_id,:type,:pi_id,:pi_name,:area_code,:orderid,:order_type,:partner,:ctime,:note,:ai_id,:ui_id,:ui_nd,:ae_id,:send_unit,:act_type,:pay_source)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Random_coupon_log> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Random_coupon_log> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (nd,car_code,money,upc_id,type,pi_id,pi_name,area_code,orderid,order_type,partner,ctime,note,ai_id,ui_id,ui_nd,ae_id,send_unit,act_type,pay_source) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Random_coupon_log bean = beans.get(i);
                    ps.setString(1, bean.nd);
                    ps.setString(2, bean.car_code);
                    ps.setInt(3, bean.money);
                    ps.setLong(4, bean.upc_id);
                    ps.setInt(5, bean.type);
                    ps.setLong(6, bean.pi_id);
                    ps.setString(7, bean.pi_name);
                    ps.setString(8, bean.area_code);
                    ps.setString(9, bean.orderid);
                    ps.setInt(10, bean.order_type);
                    ps.setString(11, bean.partner);
                    ps.setTimestamp(12, new Timestamp(bean.ctime.getTime()));
                    ps.setString(13, bean.note);
                    ps.setLong(14, bean.ai_id);
                    ps.setLong(15, bean.ui_id);
                    ps.setString(16, bean.ui_nd);
                    ps.setLong(17, bean.ae_id);
                    ps.setInt(18, bean.send_unit);
                    ps.setInt(19, bean.act_type);
                    ps.setInt(20, bean.pay_source);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Random_coupon_log> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Random_coupon_log> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT rcl_id,nd,car_code,money,upc_id,type,pi_id,pi_name,area_code,orderid,order_type,partner,ctime,note,ai_id,ui_id,ui_nd,ae_id,send_unit,act_type,pay_source FROM "+TABLENAME2+" ORDER BY rcl_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Random_coupon_log>(Random_coupon_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Random_coupon_log>();
        }
    }

    //查询最新数据
    public List<Random_coupon_log> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Random_coupon_log> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT rcl_id,nd,car_code,money,upc_id,type,pi_id,pi_name,area_code,orderid,order_type,partner,ctime,note,ai_id,ui_id,ui_nd,ae_id,send_unit,act_type,pay_source FROM "+TABLENAME2+" ORDER BY rcl_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Random_coupon_log>(Random_coupon_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Random_coupon_log>();
        }
    }

    //根据主键查询
    public List<Random_coupon_log> selectGtKey(long rcl_id) {
        return selectGtKey(rcl_id, TABLENAME);
    }

    //根据主键查询
    public List<Random_coupon_log> selectGtKey(long rcl_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT rcl_id,nd,car_code,money,upc_id,type,pi_id,pi_name,area_code,orderid,order_type,partner,ctime,note,ai_id,ui_id,ui_nd,ae_id,send_unit,act_type,pay_source FROM "+TABLENAME2+" WHERE rcl_id>:rcl_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("rcl_id", rcl_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Random_coupon_log>(Random_coupon_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Random_coupon_log>();
        }
    }

    //根据主键查询
    public Random_coupon_log selectByKey(long rcl_id) {
        return selectByKey(rcl_id, TABLENAME);
    }

    //根据主键查询
    public Random_coupon_log selectByKey(long rcl_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT rcl_id,nd,car_code,money,upc_id,type,pi_id,pi_name,area_code,orderid,order_type,partner,ctime,note,ai_id,ui_id,ui_nd,ae_id,send_unit,act_type,pay_source FROM "+TABLENAME2+" WHERE rcl_id=:rcl_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("rcl_id", rcl_id);
            List<Random_coupon_log> list =  _np.query(sql, param, new BeanPropertyRowMapper<Random_coupon_log>(Random_coupon_log.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey rcl_id="+rcl_id,e);
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
    public List<Random_coupon_log> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Random_coupon_log> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT rcl_id,nd,car_code,money,upc_id,type,pi_id,pi_name,area_code,orderid,order_type,partner,ctime,note,ai_id,ui_id,ui_nd,ae_id,send_unit,act_type,pay_source FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Random_coupon_log>(Random_coupon_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Random_coupon_log>();
        }
    }

    //修改数据
    public int updateByKey(Random_coupon_log bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Random_coupon_log bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET nd=:nd,car_code=:car_code,money=:money,upc_id=:upc_id,type=:type,pi_id=:pi_id,pi_name=:pi_name,area_code=:area_code,orderid=:orderid,order_type=:order_type,partner=:partner,ctime=:ctime,note=:note,ai_id=:ai_id,ui_id=:ui_id,ui_nd=:ui_nd,ae_id=:ae_id,send_unit=:send_unit,act_type=:act_type,pay_source=:pay_source WHERE rcl_id=:rcl_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Random_coupon_log> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Random_coupon_log> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET nd=?,car_code=?,money=?,upc_id=?,type=?,pi_id=?,pi_name=?,area_code=?,orderid=?,order_type=?,partner=?,ctime=?,note=?,ai_id=?,ui_id=?,ui_nd=?,ae_id=?,send_unit=?,act_type=?,pay_source=? WHERE rcl_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Random_coupon_log bean = beans.get(i);
                    ps.setString(1, bean.nd);
                    ps.setString(2, bean.car_code);
                    ps.setInt(3, bean.money);
                    ps.setLong(4, bean.upc_id);
                    ps.setInt(5, bean.type);
                    ps.setLong(6, bean.pi_id);
                    ps.setString(7, bean.pi_name);
                    ps.setString(8, bean.area_code);
                    ps.setString(9, bean.orderid);
                    ps.setInt(10, bean.order_type);
                    ps.setString(11, bean.partner);
                    ps.setTimestamp(12, new Timestamp(bean.ctime.getTime()));
                    ps.setString(13, bean.note);
                    ps.setLong(14, bean.ai_id);
                    ps.setLong(15, bean.ui_id);
                    ps.setString(16, bean.ui_nd);
                    ps.setLong(17, bean.ae_id);
                    ps.setInt(18, bean.send_unit);
                    ps.setInt(19, bean.act_type);
                    ps.setInt(20, bean.pay_source);
                    ps.setLong(21, bean.rcl_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long rcl_id) throws SQLException{
        return deleteByKey(rcl_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long rcl_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE rcl_id=:rcl_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("rcl_id", rcl_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE rcl_id=?";
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
                 "	`rcl_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`nd`  VARCHAR(100) COMMENT '//varchar(100)    唯一标识符ND'," +
                 "	`car_code`  VARCHAR(60) COMMENT '//varchar(60)    车牌号'," +
                 "	`money`  INT(11) COMMENT '//int(11)    减免金额'," +
                 "	`upc_id`  BIGINT(20) COMMENT '//bigint(20)    返券主键ID'," +
                 "	`type`  INT(11) COMMENT '//int(11)    减免类型(1：返券2：随机减免)'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场主键ID'," +
                 "	`pi_name`  VARCHAR(100) COMMENT '//varchar(100)    停车场名称'," +
                 "	`area_code`  VARCHAR(60) COMMENT '//varchar(60)    停车场地区区域编码'," +
                 "	`orderid`  VARCHAR(100) COMMENT '//varchar(100)    对应的订单ID'," +
                 "	`order_type`  INT(11) COMMENT '//int(11)    订单类型(0:临停1：租赁)'," +
                 "	`partner`  VARCHAR(100) COMMENT '//varchar(100)    合作商家(例如：建行龙支付)'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`ai_id`  BIGINT(20) COMMENT '//bigint(20)    活动主键ID(外键-活动基本信息表主键ID)'," +
                 "	`ui_id`  BIGINT(20) COMMENT '//bigint(20)    用户ID'," +
                 "	`ui_nd`  VARCHAR(100) COMMENT '//varchar(100)    用户唯一标识'," +
                 "	`ae_id`  BIGINT(20) COMMENT '//bigint(20)    事件表主键ID'," +
                 "	`send_unit`  INT(11) COMMENT '//int(11)    赠送单位(0:吾泊平台1：龙支付)'," +
                 "	`act_type`  INT(11) COMMENT '//int(11)    事件行为（1：充值2：支付）'," +
                 "	`pay_source`  INT(11) COMMENT '//int(11)    支付类型1:支付宝2：微信3：银联4：钱包5:龙支付'," +
                 "	PRIMARY KEY (`rcl_id`)" +
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
