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

//rent_defer

@Repository("rent_deferDao")
public class Rent_deferDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Rent_deferDao.class);



    private  String TABLE = "rent_defer";

    private  String TABLENAME = "rent_defer";

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


    private  String[] carrays ={"rd_id","rent_order_id","pi_id","area_code","pi_name","money","unit_price","starttime","endtime","month_num","ctime","stime","utime","ui_id","ui_nd","car_code","pu_id","pu_nd","permit_time","pay_state","defer_state","up_orderid","flag","pay_source","mq_state","rent_type","is_del","note","father_order_id","son_order_id","is_expire","client_order_id","client_rule_id","allege_state","cpr_id"};
    private  String coulmns ="rd_id,rent_order_id,pi_id,area_code,pi_name,money,unit_price,starttime,endtime,month_num,ctime,stime,utime,ui_id,ui_nd,car_code,pu_id,pu_nd,permit_time,pay_state,defer_state,up_orderid,flag,pay_source,mq_state,rent_type,is_del,note,father_order_id,son_order_id,is_expire,client_order_id,client_rule_id,allege_state,cpr_id";
    private  String coulmns2 ="rent_order_id,pi_id,area_code,pi_name,money,unit_price,starttime,endtime,month_num,ctime,stime,utime,ui_id,ui_nd,car_code,pu_id,pu_nd,permit_time,pay_state,defer_state,up_orderid,flag,pay_source,mq_state,rent_type,is_del,note,father_order_id,son_order_id,is_expire,client_order_id,client_rule_id,allege_state,cpr_id";

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
    public int insert(Rent_defer bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Rent_defer bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (rent_order_id,pi_id,area_code,pi_name,money,unit_price,starttime,endtime,month_num,ctime,stime,utime,ui_id,ui_nd,car_code,pu_id,pu_nd,permit_time,pay_state,defer_state,up_orderid,flag,pay_source,mq_state,rent_type,is_del,note,father_order_id,son_order_id,is_expire,client_order_id,client_rule_id,allege_state,cpr_id) VALUES (:rent_order_id,:pi_id,:area_code,:pi_name,:money,:unit_price,:starttime,:endtime,:month_num,:ctime,:stime,:utime,:ui_id,:ui_nd,:car_code,:pu_id,:pu_nd,:permit_time,:pay_state,:defer_state,:up_orderid,:flag,:pay_source,:mq_state,:rent_type,:is_del,:note,:father_order_id,:son_order_id,:is_expire,:client_order_id,:client_rule_id,:allege_state,:cpr_id)";
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
    public int insert_primarykey(Rent_defer bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Rent_defer bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (rd_id,rent_order_id,pi_id,area_code,pi_name,money,unit_price,starttime,endtime,month_num,ctime,stime,utime,ui_id,ui_nd,car_code,pu_id,pu_nd,permit_time,pay_state,defer_state,up_orderid,flag,pay_source,mq_state,rent_type,is_del,note,father_order_id,son_order_id,is_expire,client_order_id,client_rule_id,allege_state,cpr_id) VALUES (:rd_id,:rent_order_id,:pi_id,:area_code,:pi_name,:money,:unit_price,:starttime,:endtime,:month_num,:ctime,:stime,:utime,:ui_id,:ui_nd,:car_code,:pu_id,:pu_nd,:permit_time,:pay_state,:defer_state,:up_orderid,:flag,:pay_source,:mq_state,:rent_type,:is_del,:note,:father_order_id,:son_order_id,:is_expire,:client_order_id,:client_rule_id,:allege_state,:cpr_id)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Rent_defer> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Rent_defer> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (rent_order_id,pi_id,area_code,pi_name,money,unit_price,starttime,endtime,month_num,ctime,stime,utime,ui_id,ui_nd,car_code,pu_id,pu_nd,permit_time,pay_state,defer_state,up_orderid,flag,pay_source,mq_state,rent_type,is_del,note,father_order_id,son_order_id,is_expire,client_order_id,client_rule_id,allege_state,cpr_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Rent_defer bean = beans.get(i);
                    ps.setString(1, bean.rent_order_id);
                    ps.setLong(2, bean.pi_id);
                    ps.setString(3, bean.area_code);
                    ps.setString(4, bean.pi_name);
                    ps.setInt(5, bean.money);
                    ps.setInt(6, bean.unit_price);
                    ps.setTimestamp(7, new Timestamp(bean.starttime.getTime()));
                    ps.setTimestamp(8, new Timestamp(bean.endtime.getTime()));
                    ps.setInt(9, bean.month_num);
                    ps.setTimestamp(10, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(11, new Timestamp(bean.stime.getTime()));
                    ps.setTimestamp(12, new Timestamp(bean.utime.getTime()));
                    ps.setLong(13, bean.ui_id);
                    ps.setString(14, bean.ui_nd);
                    ps.setString(15, bean.car_code);
                    ps.setLong(16, bean.pu_id);
                    ps.setString(17, bean.pu_nd);
                    ps.setString(18, bean.permit_time);
                    ps.setInt(19, bean.pay_state);
                    ps.setInt(20, bean.defer_state);
                    ps.setString(21, bean.up_orderid);
                    ps.setInt(22, bean.flag);
                    ps.setInt(23, bean.pay_source);
                    ps.setInt(24, bean.mq_state);
                    ps.setInt(25, bean.rent_type);
                    ps.setInt(26, bean.is_del);
                    ps.setString(27, bean.note);
                    ps.setString(28, bean.father_order_id);
                    ps.setString(29, bean.son_order_id);
                    ps.setInt(30, bean.is_expire);
                    ps.setString(31, bean.client_order_id);
                    ps.setString(32, bean.client_rule_id);
                    ps.setInt(33, bean.allege_state);
                    ps.setLong(34, bean.cpr_id);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Rent_defer> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Rent_defer> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT rd_id,rent_order_id,pi_id,area_code,pi_name,money,unit_price,starttime,endtime,month_num,ctime,stime,utime,ui_id,ui_nd,car_code,pu_id,pu_nd,permit_time,pay_state,defer_state,up_orderid,flag,pay_source,mq_state,rent_type,is_del,note,father_order_id,son_order_id,is_expire,client_order_id,client_rule_id,allege_state,cpr_id FROM "+TABLENAME2+" ORDER BY rd_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Rent_defer>(Rent_defer.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Rent_defer>();
        }
    }

    //查询最新数据
    public List<Rent_defer> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Rent_defer> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT rd_id,rent_order_id,pi_id,area_code,pi_name,money,unit_price,starttime,endtime,month_num,ctime,stime,utime,ui_id,ui_nd,car_code,pu_id,pu_nd,permit_time,pay_state,defer_state,up_orderid,flag,pay_source,mq_state,rent_type,is_del,note,father_order_id,son_order_id,is_expire,client_order_id,client_rule_id,allege_state,cpr_id FROM "+TABLENAME2+" ORDER BY rd_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Rent_defer>(Rent_defer.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Rent_defer>();
        }
    }

    //根据主键查询
    public List<Rent_defer> selectGtKey(long rd_id) {
        return selectGtKey(rd_id, TABLENAME);
    }

    //根据主键查询
    public List<Rent_defer> selectGtKey(long rd_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT rd_id,rent_order_id,pi_id,area_code,pi_name,money,unit_price,starttime,endtime,month_num,ctime,stime,utime,ui_id,ui_nd,car_code,pu_id,pu_nd,permit_time,pay_state,defer_state,up_orderid,flag,pay_source,mq_state,rent_type,is_del,note,father_order_id,son_order_id,is_expire,client_order_id,client_rule_id,allege_state,cpr_id FROM "+TABLENAME2+" WHERE rd_id>:rd_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("rd_id", rd_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Rent_defer>(Rent_defer.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Rent_defer>();
        }
    }

    //根据主键查询
    public Rent_defer selectByKey(long rd_id) {
        return selectByKey(rd_id, TABLENAME);
    }

    //根据主键查询
    public Rent_defer selectByKey(long rd_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT rd_id,rent_order_id,pi_id,area_code,pi_name,money,unit_price,starttime,endtime,month_num,ctime,stime,utime,ui_id,ui_nd,car_code,pu_id,pu_nd,permit_time,pay_state,defer_state,up_orderid,flag,pay_source,mq_state,rent_type,is_del,note,father_order_id,son_order_id,is_expire,client_order_id,client_rule_id,allege_state,cpr_id FROM "+TABLENAME2+" WHERE rd_id=:rd_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("rd_id", rd_id);
            List<Rent_defer> list =  _np.query(sql, param, new BeanPropertyRowMapper<Rent_defer>(Rent_defer.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey rd_id="+rd_id,e);
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
    public List<Rent_defer> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Rent_defer> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT rd_id,rent_order_id,pi_id,area_code,pi_name,money,unit_price,starttime,endtime,month_num,ctime,stime,utime,ui_id,ui_nd,car_code,pu_id,pu_nd,permit_time,pay_state,defer_state,up_orderid,flag,pay_source,mq_state,rent_type,is_del,note,father_order_id,son_order_id,is_expire,client_order_id,client_rule_id,allege_state,cpr_id FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Rent_defer>(Rent_defer.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Rent_defer>();
        }
    }

    //修改数据
    public int updateByKey(Rent_defer bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Rent_defer bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET rent_order_id=:rent_order_id,pi_id=:pi_id,area_code=:area_code,pi_name=:pi_name,money=:money,unit_price=:unit_price,starttime=:starttime,endtime=:endtime,month_num=:month_num,ctime=:ctime,stime=:stime,utime=:utime,ui_id=:ui_id,ui_nd=:ui_nd,car_code=:car_code,pu_id=:pu_id,pu_nd=:pu_nd,permit_time=:permit_time,pay_state=:pay_state,defer_state=:defer_state,up_orderid=:up_orderid,flag=:flag,pay_source=:pay_source,mq_state=:mq_state,rent_type=:rent_type,is_del=:is_del,note=:note,father_order_id=:father_order_id,son_order_id=:son_order_id,is_expire=:is_expire,client_order_id=:client_order_id,client_rule_id=:client_rule_id,allege_state=:allege_state,cpr_id=:cpr_id WHERE rd_id=:rd_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Rent_defer> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Rent_defer> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET rent_order_id=?,pi_id=?,area_code=?,pi_name=?,money=?,unit_price=?,starttime=?,endtime=?,month_num=?,ctime=?,stime=?,utime=?,ui_id=?,ui_nd=?,car_code=?,pu_id=?,pu_nd=?,permit_time=?,pay_state=?,defer_state=?,up_orderid=?,flag=?,pay_source=?,mq_state=?,rent_type=?,is_del=?,note=?,father_order_id=?,son_order_id=?,is_expire=?,client_order_id=?,client_rule_id=?,allege_state=?,cpr_id=? WHERE rd_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Rent_defer bean = beans.get(i);
                    ps.setString(1, bean.rent_order_id);
                    ps.setLong(2, bean.pi_id);
                    ps.setString(3, bean.area_code);
                    ps.setString(4, bean.pi_name);
                    ps.setInt(5, bean.money);
                    ps.setInt(6, bean.unit_price);
                    ps.setTimestamp(7, new Timestamp(bean.starttime.getTime()));
                    ps.setTimestamp(8, new Timestamp(bean.endtime.getTime()));
                    ps.setInt(9, bean.month_num);
                    ps.setTimestamp(10, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(11, new Timestamp(bean.stime.getTime()));
                    ps.setTimestamp(12, new Timestamp(bean.utime.getTime()));
                    ps.setLong(13, bean.ui_id);
                    ps.setString(14, bean.ui_nd);
                    ps.setString(15, bean.car_code);
                    ps.setLong(16, bean.pu_id);
                    ps.setString(17, bean.pu_nd);
                    ps.setString(18, bean.permit_time);
                    ps.setInt(19, bean.pay_state);
                    ps.setInt(20, bean.defer_state);
                    ps.setString(21, bean.up_orderid);
                    ps.setInt(22, bean.flag);
                    ps.setInt(23, bean.pay_source);
                    ps.setInt(24, bean.mq_state);
                    ps.setInt(25, bean.rent_type);
                    ps.setInt(26, bean.is_del);
                    ps.setString(27, bean.note);
                    ps.setString(28, bean.father_order_id);
                    ps.setString(29, bean.son_order_id);
                    ps.setInt(30, bean.is_expire);
                    ps.setString(31, bean.client_order_id);
                    ps.setString(32, bean.client_rule_id);
                    ps.setInt(33, bean.allege_state);
                    ps.setLong(34, bean.cpr_id);
                    ps.setLong(35, bean.rd_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long rd_id) throws SQLException{
        return deleteByKey(rd_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long rd_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE rd_id=:rd_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("rd_id", rd_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE rd_id=?";
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
                 "	`rd_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`rent_order_id`  VARCHAR(80) COMMENT '//varchar(80)    租赁订单号'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场主键ID'," +
                 "	`area_code`  VARCHAR(30) COMMENT '//varchar(30)    停车场地址编码'," +
                 "	`pi_name`  VARCHAR(80) COMMENT '//varchar(80)    停车场名称'," +
                 "	`money`  INT(11) COMMENT '//int(11)    租赁续期金额（单位分）'," +
                 "	`unit_price`  INT(11) COMMENT '//int(11)    租赁每个月单价（单位分）'," +
                 "	`starttime`  DATETIME COMMENT '//datetime    开始时间'," +
                 "	`endtime`  DATETIME COMMENT '//datetime    到期时间'," +
                 "	`month_num`  INT(11) COMMENT '//int(11)    续约月份个数'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    客户端创建时间'," +
                 "	`stime`  DATETIME COMMENT '//datetime    服务器端接收时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`ui_id`  BIGINT(20) COMMENT '//bigint(20)    用户主键ID'," +
                 "	`ui_nd`  VARCHAR(80) COMMENT '//varchar(80)    用户ND'," +
                 "	`car_code`  VARCHAR(80) COMMENT '//varchar(80)    用户车牌号'," +
                 "	`pu_id`  BIGINT(20) COMMENT '//bigint(20)    商户主键ID'," +
                 "	`pu_nd`  VARCHAR(80) COMMENT '//varchar(80)    商户ND'," +
                 "	`permit_time`  VARCHAR(80) COMMENT '//varchar(80)    允许时间段（8：00-23：00）'," +
                 "	`pay_state`  INT(11) COMMENT '//int(11)    支付状态（0：未支付1：支付成功2：支付失败）'," +
                 "	`defer_state`  INT(11) COMMENT '//int(11)    续约状态（0：未续约1：续约中2：续约成功3：续约失败4：续约失败-退款钱包）'," +
                 "	`up_orderid`  VARCHAR(80) COMMENT '//varchar(80)    平台支付流水单号'," +
                 "	`flag`  INT(11) COMMENT '//int(11)    续约来源（0：未知1：线下道闸租赁2：线上3：线下道闸续租）'," +
                 "	`pay_source`  INT(11) COMMENT '//int(11)    支付类型0：现金支付1:支付宝2：微信3：银联4：钱包5:龙支付6:ETC快捷支付7：扫脸支付8：指纹支付9：指静脉支付'," +
                 "	`mq_state`  INT(11) COMMENT '//int(11)    是否MQ推送（0：没有1：推送成功2：推送失败）'," +
                 "	`rent_type`  INT(11) COMMENT '//int(11)    租赁类型（0：未指定1：普通分时间段租赁2：垮天分时间段租赁3：全天）'," +
                 "	`is_del`  INT(11) COMMENT '//int(11)    是否逻辑删除(0:正常1：删除)'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`father_order_id`  VARCHAR(80) COMMENT '//varchar(80)    父亲订单（父订单）ID'," +
                 "	`son_order_id`  VARCHAR(80) COMMENT '//varchar(80)    子续约订单ID（目前废弃）'," +
                 "	`is_expire`  INT(11) COMMENT '//int(11)    是否已经到期（0：未到期1：已经到期）'," +
                 "	`client_order_id`  VARCHAR(80) COMMENT '//varchar(80)    客户端的租赁订单号'," +
                 "	`client_rule_id`  VARCHAR(80) COMMENT '//varchar(80)    客户端的规则ID'," +
                 "	`allege_state`  INT(11) COMMENT '//int(11)    申述状态0:未申述1：申述中2：申述失败3：申述成功'," +
                 "	`cpr_id`  BIGINT(20) COMMENT '//bigint(20)    外键carcode_park_rent表主键ID'," +
                 "	PRIMARY KEY (`rd_id`)" +
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
