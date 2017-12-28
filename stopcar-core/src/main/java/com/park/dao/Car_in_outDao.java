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

//car_in_out

@Repository("car_in_outDao")
public class Car_in_outDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Car_in_outDao.class);



    private  String TABLE = "car_in_out";

    private  String TABLENAME = "car_in_out";

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


    private  String[] carrays ={"cio_id","pi_id","pd_id","ui_id","car_code","is_enter","in_out","in_out_code","ctime","utime","area_code","note","car_type","car_code_color","order_id","out_type","is_rent","rent_remain_time","is_local_month","ui_nd","ui_tel","is_sync","gov_num","stime"};
    private  String coulmns ="cio_id,pi_id,pd_id,ui_id,car_code,is_enter,in_out,in_out_code,ctime,utime,area_code,note,car_type,car_code_color,order_id,out_type,is_rent,rent_remain_time,is_local_month,ui_nd,ui_tel,is_sync,gov_num,stime";
    private  String coulmns2 ="pi_id,pd_id,ui_id,car_code,is_enter,in_out,in_out_code,ctime,utime,area_code,note,car_type,car_code_color,order_id,out_type,is_rent,rent_remain_time,is_local_month,ui_nd,ui_tel,is_sync,gov_num,stime";

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
    public int insert(Car_in_out bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Car_in_out bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,pd_id,ui_id,car_code,is_enter,in_out,in_out_code,ctime,utime,area_code,note,car_type,car_code_color,order_id,out_type,is_rent,rent_remain_time,is_local_month,ui_nd,ui_tel,is_sync,gov_num,stime) VALUES (:pi_id,:pd_id,:ui_id,:car_code,:is_enter,:in_out,:in_out_code,:ctime,:utime,:area_code,:note,:car_type,:car_code_color,:order_id,:out_type,:is_rent,:rent_remain_time,:is_local_month,:ui_nd,:ui_tel,:is_sync,:gov_num,:stime)";
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
    public int insert_primarykey(Car_in_out bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Car_in_out bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (cio_id,pi_id,pd_id,ui_id,car_code,is_enter,in_out,in_out_code,ctime,utime,area_code,note,car_type,car_code_color,order_id,out_type,is_rent,rent_remain_time,is_local_month,ui_nd,ui_tel,is_sync,gov_num,stime) VALUES (:cio_id,:pi_id,:pd_id,:ui_id,:car_code,:is_enter,:in_out,:in_out_code,:ctime,:utime,:area_code,:note,:car_type,:car_code_color,:order_id,:out_type,:is_rent,:rent_remain_time,:is_local_month,:ui_nd,:ui_tel,:is_sync,:gov_num,:stime)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Car_in_out> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Car_in_out> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pi_id,pd_id,ui_id,car_code,is_enter,in_out,in_out_code,ctime,utime,area_code,note,car_type,car_code_color,order_id,out_type,is_rent,rent_remain_time,is_local_month,ui_nd,ui_tel,is_sync,gov_num,stime) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Car_in_out bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setLong(2, bean.pd_id);
                    ps.setLong(3, bean.ui_id);
                    ps.setString(4, bean.car_code);
                    ps.setInt(5, bean.is_enter);
                    ps.setString(6, bean.in_out);
                    ps.setString(7, bean.in_out_code);
                    ps.setTimestamp(8, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(9, new Timestamp(bean.utime.getTime()));
                    ps.setString(10, bean.area_code);
                    ps.setString(11, bean.note);
                    ps.setInt(12, bean.car_type);
                    ps.setInt(13, bean.car_code_color);
                    ps.setString(14, bean.order_id);
                    ps.setInt(15, bean.out_type);
                    ps.setInt(16, bean.is_rent);
                    ps.setLong(17, bean.rent_remain_time);
                    ps.setInt(18, bean.is_local_month);
                    ps.setString(19, bean.ui_nd);
                    ps.setString(20, bean.ui_tel);
                    ps.setInt(21, bean.is_sync);
                    ps.setString(22, bean.gov_num);
                    ps.setTimestamp(23, new Timestamp(bean.stime.getTime()));
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Car_in_out> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Car_in_out> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT cio_id,pi_id,pd_id,ui_id,car_code,is_enter,in_out,in_out_code,ctime,utime,area_code,note,car_type,car_code_color,order_id,out_type,is_rent,rent_remain_time,is_local_month,ui_nd,ui_tel,is_sync,gov_num,stime FROM "+TABLENAME2+" ORDER BY cio_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Car_in_out>(Car_in_out.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Car_in_out>();
        }
    }

    //查询最新数据
    public List<Car_in_out> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Car_in_out> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT cio_id,pi_id,pd_id,ui_id,car_code,is_enter,in_out,in_out_code,ctime,utime,area_code,note,car_type,car_code_color,order_id,out_type,is_rent,rent_remain_time,is_local_month,ui_nd,ui_tel,is_sync,gov_num,stime FROM "+TABLENAME2+" ORDER BY cio_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Car_in_out>(Car_in_out.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Car_in_out>();
        }
    }

    //根据主键查询
    public List<Car_in_out> selectGtKey(long cio_id) {
        return selectGtKey(cio_id, TABLENAME);
    }

    //根据主键查询
    public List<Car_in_out> selectGtKey(long cio_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT cio_id,pi_id,pd_id,ui_id,car_code,is_enter,in_out,in_out_code,ctime,utime,area_code,note,car_type,car_code_color,order_id,out_type,is_rent,rent_remain_time,is_local_month,ui_nd,ui_tel,is_sync,gov_num,stime FROM "+TABLENAME2+" WHERE cio_id>:cio_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("cio_id", cio_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Car_in_out>(Car_in_out.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Car_in_out>();
        }
    }

    //根据主键查询
    public Car_in_out selectByKey(long cio_id) {
        return selectByKey(cio_id, TABLENAME);
    }

    //根据主键查询
    public Car_in_out selectByKey(long cio_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT cio_id,pi_id,pd_id,ui_id,car_code,is_enter,in_out,in_out_code,ctime,utime,area_code,note,car_type,car_code_color,order_id,out_type,is_rent,rent_remain_time,is_local_month,ui_nd,ui_tel,is_sync,gov_num,stime FROM "+TABLENAME2+" WHERE cio_id=:cio_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("cio_id", cio_id);
            List<Car_in_out> list =  _np.query(sql, param, new BeanPropertyRowMapper<Car_in_out>(Car_in_out.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey cio_id="+cio_id,e);
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
    public List<Car_in_out> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Car_in_out> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT cio_id,pi_id,pd_id,ui_id,car_code,is_enter,in_out,in_out_code,ctime,utime,area_code,note,car_type,car_code_color,order_id,out_type,is_rent,rent_remain_time,is_local_month,ui_nd,ui_tel,is_sync,gov_num,stime FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Car_in_out>(Car_in_out.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Car_in_out>();
        }
    }

    //修改数据
    public int updateByKey(Car_in_out bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Car_in_out bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=:pi_id,pd_id=:pd_id,ui_id=:ui_id,car_code=:car_code,is_enter=:is_enter,in_out=:in_out,in_out_code=:in_out_code,ctime=:ctime,utime=:utime,area_code=:area_code,note=:note,car_type=:car_type,car_code_color=:car_code_color,order_id=:order_id,out_type=:out_type,is_rent=:is_rent,rent_remain_time=:rent_remain_time,is_local_month=:is_local_month,ui_nd=:ui_nd,ui_tel=:ui_tel,is_sync=:is_sync,gov_num=:gov_num,stime=:stime WHERE cio_id=:cio_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Car_in_out> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Car_in_out> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pi_id=?,pd_id=?,ui_id=?,car_code=?,is_enter=?,in_out=?,in_out_code=?,ctime=?,utime=?,area_code=?,note=?,car_type=?,car_code_color=?,order_id=?,out_type=?,is_rent=?,rent_remain_time=?,is_local_month=?,ui_nd=?,ui_tel=?,is_sync=?,gov_num=?,stime=? WHERE cio_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Car_in_out bean = beans.get(i);
                    ps.setLong(1, bean.pi_id);
                    ps.setLong(2, bean.pd_id);
                    ps.setLong(3, bean.ui_id);
                    ps.setString(4, bean.car_code);
                    ps.setInt(5, bean.is_enter);
                    ps.setString(6, bean.in_out);
                    ps.setString(7, bean.in_out_code);
                    ps.setTimestamp(8, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(9, new Timestamp(bean.utime.getTime()));
                    ps.setString(10, bean.area_code);
                    ps.setString(11, bean.note);
                    ps.setInt(12, bean.car_type);
                    ps.setInt(13, bean.car_code_color);
                    ps.setString(14, bean.order_id);
                    ps.setInt(15, bean.out_type);
                    ps.setInt(16, bean.is_rent);
                    ps.setLong(17, bean.rent_remain_time);
                    ps.setInt(18, bean.is_local_month);
                    ps.setString(19, bean.ui_nd);
                    ps.setString(20, bean.ui_tel);
                    ps.setInt(21, bean.is_sync);
                    ps.setString(22, bean.gov_num);
                    ps.setTimestamp(23, new Timestamp(bean.stime.getTime()));
                    ps.setLong(24, bean.cio_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long cio_id) throws SQLException{
        return deleteByKey(cio_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long cio_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE cio_id=:cio_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("cio_id", cio_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE cio_id=?";
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
                 "	`cio_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    场地车库主键ID'," +
                 "	`pd_id`  BIGINT(20) COMMENT '//bigint(20)    出入口设备主键ID'," +
                 "	`ui_id`  BIGINT(20) COMMENT '//bigint(20)    用户ID'," +
                 "	`car_code`  VARCHAR(60) COMMENT '//varchar(60)    车牌号'," +
                 "	`is_enter`  INT(11) COMMENT '//int(11)    入库或者出库：0：入库1：出库'," +
                 "	`in_out`  VARCHAR(60) COMMENT '//varchar(60)    出口或者入口入口：enter出口：exit'," +
                 "	`in_out_code`  VARCHAR(60) COMMENT '//varchar(60)    出入口编号A/B/C'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`area_code`  VARCHAR(60) COMMENT '//varchar(60)    省市县编号'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`car_type`  INT(11) COMMENT '//int(11)    车牌类型:车牌类型0：未知车牌:、1：蓝牌小汽车、2：:黑牌小汽车、3：单排黄牌、4：双排黄牌、5：警车车牌、6：武警车牌、7：个性化车牌、8：单排军车牌、9：双排军车牌、10：使馆车牌、11：香港进出中国大陆车牌、12：农用车牌、13：教练车牌、14：澳门进出中国大陆车牌、15：双层武警车牌、16：武警总队车牌、17：双层武警总队车牌'," +
                 "	`car_code_color`  INT(11) COMMENT '//int(11)    车牌颜色0：未知、1：蓝色、2：黄色、3：白色、4：黑色、5：绿色'," +
                 "	`order_id`  VARCHAR(80) COMMENT '//varchar(80)    订单编号'," +
                 "	`out_type`  INT(11) COMMENT '//int(11)    入库/出库类型:(0:正常出入库1：道闸本地临停出入库2：道闸本地包月出入库3：异常出入库)4:异步数据上传出入库（网络异常）'," +
                 "	`is_rent`  INT(11) COMMENT '//int(11)    是否是租赁车辆0:不是1：是'," +
                 "	`rent_remain_time`  BIGINT(20) COMMENT '//bigint(20)    租赁车辆距离结束时间还有多少时长（单位毫秒）'," +
                 "	`is_local_month`  INT(11) COMMENT '//int(11)    是否是道闸本地包月车辆0:不是1：是'," +
                 "	`ui_nd`  VARCHAR(100) COMMENT '//varchar(100)    用户唯一标识uuid'," +
                 "	`ui_tel`  VARCHAR(100) COMMENT '//varchar(100)    用户电话号码'," +
                 "	`is_sync`  INT(11) COMMENT '//int(11)    是否是网络异常导致的数据上传：0不是1：是'," +
                 "	`gov_num`  VARCHAR(60) COMMENT '//varchar(60)    地磁停车场车位编号（政府部门统一分配）'," +
                 "	`stime`  DATETIME COMMENT '//datetime    服务器端接收数据创建时间'," +
                 "	PRIMARY KEY (`cio_id`)" +
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
