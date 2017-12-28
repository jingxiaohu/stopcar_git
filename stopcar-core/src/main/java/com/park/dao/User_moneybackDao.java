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

//user_moneyback

@Repository("user_moneybackDao")
public class User_moneybackDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(User_moneybackDao.class);



    private  String TABLE = "user_moneyback";

    private  String TABLENAME = "user_moneyback";

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


    private  String[] carrays ={"um_id","ui_id","order_id","pi_id","ctime","utime","um_money","car_code","run_url","um_state","check_state","admin_userid","note","area_code","type","content","is_rent","check_content","pi_name"};
    private  String coulmns ="um_id,ui_id,order_id,pi_id,ctime,utime,um_money,car_code,run_url,um_state,check_state,admin_userid,note,area_code,type,content,is_rent,check_content,pi_name";
    private  String coulmns2 ="ui_id,order_id,pi_id,ctime,utime,um_money,car_code,run_url,um_state,check_state,admin_userid,note,area_code,type,content,is_rent,check_content,pi_name";

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
    public int insert(User_moneyback bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(User_moneyback bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (ui_id,order_id,pi_id,ctime,utime,um_money,car_code,run_url,um_state,check_state,admin_userid,note,area_code,type,content,is_rent,check_content,pi_name) VALUES (:ui_id,:order_id,:pi_id,:ctime,:utime,:um_money,:car_code,:run_url,:um_state,:check_state,:admin_userid,:note,:area_code,:type,:content,:is_rent,:check_content,:pi_name)";
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
    public int insert_primarykey(User_moneyback bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(User_moneyback bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (um_id,ui_id,order_id,pi_id,ctime,utime,um_money,car_code,run_url,um_state,check_state,admin_userid,note,area_code,type,content,is_rent,check_content,pi_name) VALUES (:um_id,:ui_id,:order_id,:pi_id,:ctime,:utime,:um_money,:car_code,:run_url,:um_state,:check_state,:admin_userid,:note,:area_code,:type,:content,:is_rent,:check_content,:pi_name)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<User_moneyback> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<User_moneyback> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (ui_id,order_id,pi_id,ctime,utime,um_money,car_code,run_url,um_state,check_state,admin_userid,note,area_code,type,content,is_rent,check_content,pi_name) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    User_moneyback bean = beans.get(i);
                    ps.setLong(1, bean.ui_id);
                    ps.setString(2, bean.order_id);
                    ps.setLong(3, bean.pi_id);
                    ps.setTimestamp(4, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(5, new Timestamp(bean.utime.getTime()));
                    ps.setInt(6, bean.um_money);
                    ps.setString(7, bean.car_code);
                    ps.setString(8, bean.run_url);
                    ps.setInt(9, bean.um_state);
                    ps.setInt(10, bean.check_state);
                    ps.setLong(11, bean.admin_userid);
                    ps.setString(12, bean.note);
                    ps.setString(13, bean.area_code);
                    ps.setInt(14, bean.type);
                    ps.setString(15, bean.content);
                    ps.setInt(16, bean.is_rent);
                    ps.setString(17, bean.check_content);
                    ps.setString(18, bean.pi_name);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<User_moneyback> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<User_moneyback> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT um_id,ui_id,order_id,pi_id,ctime,utime,um_money,car_code,run_url,um_state,check_state,admin_userid,note,area_code,type,content,is_rent,check_content,pi_name FROM "+TABLENAME2+" ORDER BY um_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<User_moneyback>(User_moneyback.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<User_moneyback>();
        }
    }

    //查询最新数据
    public List<User_moneyback> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<User_moneyback> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT um_id,ui_id,order_id,pi_id,ctime,utime,um_money,car_code,run_url,um_state,check_state,admin_userid,note,area_code,type,content,is_rent,check_content,pi_name FROM "+TABLENAME2+" ORDER BY um_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<User_moneyback>(User_moneyback.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<User_moneyback>();
        }
    }

    //根据主键查询
    public List<User_moneyback> selectGtKey(long um_id) {
        return selectGtKey(um_id, TABLENAME);
    }

    //根据主键查询
    public List<User_moneyback> selectGtKey(long um_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT um_id,ui_id,order_id,pi_id,ctime,utime,um_money,car_code,run_url,um_state,check_state,admin_userid,note,area_code,type,content,is_rent,check_content,pi_name FROM "+TABLENAME2+" WHERE um_id>:um_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("um_id", um_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<User_moneyback>(User_moneyback.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<User_moneyback>();
        }
    }

    //根据主键查询
    public User_moneyback selectByKey(long um_id) {
        return selectByKey(um_id, TABLENAME);
    }

    //根据主键查询
    public User_moneyback selectByKey(long um_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT um_id,ui_id,order_id,pi_id,ctime,utime,um_money,car_code,run_url,um_state,check_state,admin_userid,note,area_code,type,content,is_rent,check_content,pi_name FROM "+TABLENAME2+" WHERE um_id=:um_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("um_id", um_id);
            List<User_moneyback> list =  _np.query(sql, param, new BeanPropertyRowMapper<User_moneyback>(User_moneyback.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey um_id="+um_id,e);
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
    public List<User_moneyback> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<User_moneyback> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT um_id,ui_id,order_id,pi_id,ctime,utime,um_money,car_code,run_url,um_state,check_state,admin_userid,note,area_code,type,content,is_rent,check_content,pi_name FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<User_moneyback>(User_moneyback.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<User_moneyback>();
        }
    }

    //修改数据
    public int updateByKey(User_moneyback bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(User_moneyback bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET ui_id=:ui_id,order_id=:order_id,pi_id=:pi_id,ctime=:ctime,utime=:utime,um_money=:um_money,car_code=:car_code,run_url=:run_url,um_state=:um_state,check_state=:check_state,admin_userid=:admin_userid,note=:note,area_code=:area_code,type=:type,content=:content,is_rent=:is_rent,check_content=:check_content,pi_name=:pi_name WHERE um_id=:um_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<User_moneyback> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<User_moneyback> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET ui_id=?,order_id=?,pi_id=?,ctime=?,utime=?,um_money=?,car_code=?,run_url=?,um_state=?,check_state=?,admin_userid=?,note=?,area_code=?,type=?,content=?,is_rent=?,check_content=?,pi_name=? WHERE um_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    User_moneyback bean = beans.get(i);
                    ps.setLong(1, bean.ui_id);
                    ps.setString(2, bean.order_id);
                    ps.setLong(3, bean.pi_id);
                    ps.setTimestamp(4, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(5, new Timestamp(bean.utime.getTime()));
                    ps.setInt(6, bean.um_money);
                    ps.setString(7, bean.car_code);
                    ps.setString(8, bean.run_url);
                    ps.setInt(9, bean.um_state);
                    ps.setInt(10, bean.check_state);
                    ps.setLong(11, bean.admin_userid);
                    ps.setString(12, bean.note);
                    ps.setString(13, bean.area_code);
                    ps.setInt(14, bean.type);
                    ps.setString(15, bean.content);
                    ps.setInt(16, bean.is_rent);
                    ps.setString(17, bean.check_content);
                    ps.setString(18, bean.pi_name);
                    ps.setLong(19, bean.um_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long um_id) throws SQLException{
        return deleteByKey(um_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long um_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE um_id=:um_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("um_id", um_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE um_id=?";
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
                 "	`um_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`ui_id`  BIGINT(20) COMMENT '//bigint(20)    用户ID'," +
                 "	`order_id`  VARCHAR(70) COMMENT '//varchar(70)    停车下订单ID'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    场地表主键ID'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`um_money`  INT(11) COMMENT '//int(11)    退款金额(单位分)'," +
                 "	`car_code`  VARCHAR(60) COMMENT '//varchar(60)    退款车牌号'," +
                 "	`run_url`  VARCHAR(150) COMMENT '//varchar(150)    用户行驶证图片'," +
                 "	`um_state`  INT(11) COMMENT '//int(11)    退款状态0：未退款1：已退款'," +
                 "	`check_state`  INT(11) COMMENT '//int(11)    审核状态0：未审核1：审核未通过2:审核通过'," +
                 "	`admin_userid`  BIGINT(20) COMMENT '//bigint(20)    审核人后台管理表主键ID'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    '," +
                 "	`area_code`  VARCHAR(30) COMMENT '//varchar(30)    区域地址编码'," +
                 "	`type`  INT(11) COMMENT '//int(11)    申诉类型0：临停扣款问题1：预约超时扣费问题2：其它'," +
                 "	`content`  TINYTEXT COMMENT '//varchar(255)    申诉原因'," +
                 "	`is_rent`  INT(11) COMMENT '//int(11)    是否是租赁订单0：临停订单1：预约临停订单2：租赁包月订单3：租赁续租'," +
                 "	`check_content`  TEXT COMMENT '//text    审核结果'," +
                 "	`pi_name`  VARCHAR(100) COMMENT '//varchar(100)    停车场名称'," +
                 "	PRIMARY KEY (`um_id`)" +
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
