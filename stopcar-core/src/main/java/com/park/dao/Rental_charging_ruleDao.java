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

//rental_charging_rule

@Repository("rental_charging_ruleDao")
public class Rental_charging_ruleDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Rental_charging_ruleDao.class);



    private  String TABLE = "rental_charging_rule";

    private  String TABLENAME = "rental_charging_rule";

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


    private  String[] carrays ={"rcr_id","pi_id","start_price","start_time","charging","charging_time","month_price","month_time","permit_time","timeout_info","rcr_type","rcr_state","rcr_discount","car_displacement","car_type","car_code_color","is_time_bucket","time_bucket","ctime","utime","rcr_md5","is_default","area_code","note","free_minute","is_free_minute","roadside_type"};
    private  String coulmns ="rcr_id,pi_id,start_price,start_time,charging,charging_time,month_price,month_time,permit_time,timeout_info,rcr_type,rcr_state,rcr_discount,car_displacement,car_type,car_code_color,is_time_bucket,time_bucket,ctime,utime,rcr_md5,is_default,area_code,note,free_minute,is_free_minute,roadside_type";
    private  String coulmns2 ="pi_id,start_price,start_time,charging,charging_time,month_price,month_time,permit_time,timeout_info,rcr_type,rcr_state,rcr_discount,car_displacement,car_type,car_code_color,is_time_bucket,time_bucket,ctime,utime,rcr_md5,is_default,area_code,note,free_minute,is_free_minute,roadside_type";

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
    public int insert(Rental_charging_rule bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Rental_charging_rule bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,start_price,start_time,charging,charging_time,month_price,month_time,permit_time,timeout_info,rcr_type,rcr_state,rcr_discount,car_displacement,car_type,car_code_color,is_time_bucket,time_bucket,ctime,utime,rcr_md5,is_default,area_code,note,free_minute,is_free_minute,roadside_type) VALUES (:pi_id,:start_price,:start_time,:charging,:charging_time,:month_price,:month_time,:permit_time,:timeout_info,:rcr_type,:rcr_state,:rcr_discount,:car_displacement,:car_type,:car_code_color,:is_time_bucket,:time_bucket,:ctime,:utime,:rcr_md5,:is_default,:area_code,:note,:free_minute,:is_free_minute,:roadside_type)";
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
    public int insert_primarykey(Rental_charging_rule bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Rental_charging_rule bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (rcr_id,pi_id,start_price,start_time,charging,charging_time,month_price,month_time,permit_time,timeout_info,rcr_type,rcr_state,rcr_discount,car_displacement,car_type,car_code_color,is_time_bucket,time_bucket,ctime,utime,rcr_md5,is_default,area_code,note,free_minute,is_free_minute,roadside_type) VALUES (:rcr_id,:pi_id,:start_price,:start_time,:charging,:charging_time,:month_price,:month_time,:permit_time,:timeout_info,:rcr_type,:rcr_state,:rcr_discount,:car_displacement,:car_type,:car_code_color,:is_time_bucket,:time_bucket,:ctime,:utime,:rcr_md5,:is_default,:area_code,:note,:free_minute,:is_free_minute,:roadside_type)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Rental_charging_rule> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Rental_charging_rule> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,start_price,start_time,charging,charging_time,month_price,month_time,permit_time,timeout_info,rcr_type,rcr_state,rcr_discount,car_displacement,car_type,car_code_color,is_time_bucket,time_bucket,ctime,utime,rcr_md5,is_default,area_code,note,free_minute,is_free_minute,roadside_type) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Rental_charging_rule bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setInt(2, bean.start_price);
                    ps.setInt(3, bean.start_time);
                    ps.setInt(4, bean.charging);
                    ps.setInt(5, bean.charging_time);
                    ps.setInt(6, bean.month_price);
                    ps.setInt(7, bean.month_time);
                    ps.setString(8, bean.permit_time);
                    ps.setString(9, bean.timeout_info);
                    ps.setInt(10, bean.rcr_type);
                    ps.setInt(11, bean.rcr_state);
                    ps.setInt(12, bean.rcr_discount);
                    ps.setString(13, bean.car_displacement);
                    ps.setInt(14, bean.car_type);
                    ps.setInt(15, bean.car_code_color);
                    ps.setInt(16, bean.is_time_bucket);
                    ps.setString(17, bean.time_bucket);
                    ps.setTimestamp(18, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(19, new Timestamp(bean.utime.getTime()));
                    ps.setString(20, bean.rcr_md5);
                    ps.setInt(21, bean.is_default);
                    ps.setString(22, bean.area_code);
                    ps.setString(23, bean.note);
                    ps.setInt(24, bean.free_minute);
                    ps.setInt(25, bean.is_free_minute);
                    ps.setInt(26, bean.roadside_type);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Rental_charging_rule> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Rental_charging_rule> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT rcr_id,pi_id,start_price,start_time,charging,charging_time,month_price,month_time,permit_time,timeout_info,rcr_type,rcr_state,rcr_discount,car_displacement,car_type,car_code_color,is_time_bucket,time_bucket,ctime,utime,rcr_md5,is_default,area_code,note,free_minute,is_free_minute,roadside_type FROM "+TABLENAME2+" ORDER BY rcr_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Rental_charging_rule>(Rental_charging_rule.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Rental_charging_rule>();
        }
    }

    //查询最新数据
    public List<Rental_charging_rule> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Rental_charging_rule> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT rcr_id,pi_id,start_price,start_time,charging,charging_time,month_price,month_time,permit_time,timeout_info,rcr_type,rcr_state,rcr_discount,car_displacement,car_type,car_code_color,is_time_bucket,time_bucket,ctime,utime,rcr_md5,is_default,area_code,note,free_minute,is_free_minute,roadside_type FROM "+TABLENAME2+" ORDER BY rcr_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Rental_charging_rule>(Rental_charging_rule.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Rental_charging_rule>();
        }
    }

    //根据主键查询
    public List<Rental_charging_rule> selectGtKey(long rcr_id) {
        return selectGtKey(rcr_id, TABLENAME);
    }

    //根据主键查询
    public List<Rental_charging_rule> selectGtKey(long rcr_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT rcr_id,pi_id,start_price,start_time,charging,charging_time,month_price,month_time,permit_time,timeout_info,rcr_type,rcr_state,rcr_discount,car_displacement,car_type,car_code_color,is_time_bucket,time_bucket,ctime,utime,rcr_md5,is_default,area_code,note,free_minute,is_free_minute,roadside_type FROM "+TABLENAME2+" WHERE rcr_id>:rcr_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("rcr_id", rcr_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Rental_charging_rule>(Rental_charging_rule.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Rental_charging_rule>();
        }
    }

    //根据主键查询
    public Rental_charging_rule selectByKey(long rcr_id) {
        return selectByKey(rcr_id, TABLENAME);
    }

    //根据主键查询
    public Rental_charging_rule selectByKey(long rcr_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT rcr_id,pi_id,start_price,start_time,charging,charging_time,month_price,month_time,permit_time,timeout_info,rcr_type,rcr_state,rcr_discount,car_displacement,car_type,car_code_color,is_time_bucket,time_bucket,ctime,utime,rcr_md5,is_default,area_code,note,free_minute,is_free_minute,roadside_type FROM "+TABLENAME2+" WHERE rcr_id=:rcr_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("rcr_id", rcr_id);
            List<Rental_charging_rule> list =  _np.query(sql, param, new BeanPropertyRowMapper<Rental_charging_rule>(Rental_charging_rule.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey rcr_id="+rcr_id,e);
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
    public List<Rental_charging_rule> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Rental_charging_rule> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT rcr_id,pi_id,start_price,start_time,charging,charging_time,month_price,month_time,permit_time,timeout_info,rcr_type,rcr_state,rcr_discount,car_displacement,car_type,car_code_color,is_time_bucket,time_bucket,ctime,utime,rcr_md5,is_default,area_code,note,free_minute,is_free_minute,roadside_type FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Rental_charging_rule>(Rental_charging_rule.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Rental_charging_rule>();
        }
    }

    //修改数据
    public int updateByKey(Rental_charging_rule bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Rental_charging_rule bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=:pi_id,start_price=:start_price,start_time=:start_time,charging=:charging,charging_time=:charging_time,month_price=:month_price,month_time=:month_time,permit_time=:permit_time,timeout_info=:timeout_info,rcr_type=:rcr_type,rcr_state=:rcr_state,rcr_discount=:rcr_discount,car_displacement=:car_displacement,car_type=:car_type,car_code_color=:car_code_color,is_time_bucket=:is_time_bucket,time_bucket=:time_bucket,ctime=:ctime,utime=:utime,rcr_md5=:rcr_md5,is_default=:is_default,area_code=:area_code,note=:note,free_minute=:free_minute,is_free_minute=:is_free_minute,roadside_type=:roadside_type WHERE rcr_id=:rcr_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Rental_charging_rule> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Rental_charging_rule> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=?,start_price=?,start_time=?,charging=?,charging_time=?,month_price=?,month_time=?,permit_time=?,timeout_info=?,rcr_type=?,rcr_state=?,rcr_discount=?,car_displacement=?,car_type=?,car_code_color=?,is_time_bucket=?,time_bucket=?,ctime=?,utime=?,rcr_md5=?,is_default=?,area_code=?,note=?,free_minute=?,is_free_minute=?,roadside_type=? WHERE rcr_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Rental_charging_rule bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setInt(2, bean.start_price);
                    ps.setInt(3, bean.start_time);
                    ps.setInt(4, bean.charging);
                    ps.setInt(5, bean.charging_time);
                    ps.setInt(6, bean.month_price);
                    ps.setInt(7, bean.month_time);
                    ps.setString(8, bean.permit_time);
                    ps.setString(9, bean.timeout_info);
                    ps.setInt(10, bean.rcr_type);
                    ps.setInt(11, bean.rcr_state);
                    ps.setInt(12, bean.rcr_discount);
                    ps.setString(13, bean.car_displacement);
                    ps.setInt(14, bean.car_type);
                    ps.setInt(15, bean.car_code_color);
                    ps.setInt(16, bean.is_time_bucket);
                    ps.setString(17, bean.time_bucket);
                    ps.setTimestamp(18, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(19, new Timestamp(bean.utime.getTime()));
                    ps.setString(20, bean.rcr_md5);
                    ps.setInt(21, bean.is_default);
                    ps.setString(22, bean.area_code);
                    ps.setString(23, bean.note);
                    ps.setInt(24, bean.free_minute);
                    ps.setInt(25, bean.is_free_minute);
                    ps.setInt(26, bean.roadside_type);
                    ps.setLong(27, bean.rcr_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long rcr_id) throws SQLException{
        return deleteByKey(rcr_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long rcr_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE rcr_id=:rcr_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("rcr_id", rcr_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE rcr_id=?";
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
                 "	`rcr_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场主键ID（外键）'," +
                 "	`start_price`  INT(11) COMMENT '//int(11)    起步价(RMB单位分)'," +
                 "	`start_time`  INT(11) COMMENT '//int(11)    起步价(RMB单位分)'," +
                 "	`charging`  INT(11) COMMENT '//int(11)    计费价(RMB单位分)'," +
                 "	`charging_time`  INT(11) COMMENT '//int(11)    计费时长(分钟)'," +
                 "	`month_price`  INT(11) COMMENT '//int(11)    包月价格(单位分)'," +
                 "	`month_time`  INT(11) COMMENT '//int(11)    包月时长(天)'," +
                 "	`permit_time`  VARCHAR(100) COMMENT '//varchar(100)    准入时间段（17:00-08:30）'," +
                 "	`timeout_info`  VARCHAR(150) COMMENT '//varchar(150)    计费信息(首停2小时3元，之后1元/小时)'," +
                 "	`rcr_type`  INT(11) NOT NULL COMMENT '//int(11)    停车收费类型0：普通车位停车1：时间段包月停车'," +
                 "	`rcr_state`  INT(11) COMMENT '//int(11)    是否有效0：有效1：无效'," +
                 "	`rcr_discount`  INT(11) COMMENT '//int(11)    是否可以使用优费券:0：可以使用1：无法使用'," +
                 "	`car_displacement`  VARCHAR(60) COMMENT '//varchar(60)    车辆排量'," +
                 "	`car_type`  INT(11) COMMENT '//int(11)    车牌类型0：未知车牌:、1：蓝牌小汽车、2：:黑牌小汽车、3：单排黄牌、4：双排黄牌、5：警车车牌、6：武警车牌、7：个性化车牌、8：单排军车牌、9：双排军车牌、10：使馆车牌、11：香港进出中国大陆车牌、12：农用车牌、13：教练车牌、14：澳门进出中国大陆车牌、15：双层武警车牌、16：武警总队车牌、17：双层武警总队车牌'," +
                 "	`car_code_color`  INT(11) COMMENT '//int(11)    车牌颜色0：未知、1：蓝色、2：黄色、3：白色、4：黑色、5：绿色'," +
                 "	`is_time_bucket`  INT(11) COMMENT '//int(11)    是否按时间段收费0:不按时间段收费1：按时间段收费'," +
                 "	`time_bucket`  VARCHAR(100) NOT NULL COMMENT '//varchar(100)    收费时间段文字说明例如：白天9：00-12：00每小时2元'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`rcr_md5`  VARCHAR(80) COMMENT '//varchar(80)    规则MD5校验pi_id+start_price+start_time+charging+charging_time+rcr_type+rcr_discount+car_type+car_sign_type+is_time_bucket'," +
                 "	`is_default`  INT(11) COMMENT '//int(11)    是否是默认规则0:不是1：是'," +
                 "	`area_code`  VARCHAR(20) COMMENT '//varchar(20)    省市区代码'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`free_minute`  INT(11) COMMENT '//int(11)    多少分钟之内进出免费'," +
                 "	`is_free_minute`  INT(11) COMMENT '//int(11)    多少分钟之内进出免费是否开启0:不开启1：开启'," +
                 "	`roadside_type`  INT(11) COMMENT '//int(11)    占道停车是否按次数收费0:按时间收费1：按次数收费'," +
                 "	PRIMARY KEY (`rcr_id`)" +
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
