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

//merchant_parkinfo_monthfree_log

@Repository("merchant_parkinfo_monthfree_logDao")
public class Merchant_parkinfo_monthfree_logDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Merchant_parkinfo_monthfree_logDao.class);



    private  String TABLE = "merchant_parkinfo_monthfree_log";

    private  String TABLENAME = "merchant_parkinfo_monthfree_log";

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


    private  String[] carrays ={"id","pu_id","pi_id","area_code","car_code","money","car_type","car_code_color","car_color","start_time_str","end_time_str","type","ctime","utime","note"};
    private  String coulmns ="id,pu_id,pi_id,area_code,car_code,money,car_type,car_code_color,car_color,start_time_str,end_time_str,type,ctime,utime,note";
    private  String coulmns2 ="pu_id,pi_id,area_code,car_code,money,car_type,car_code_color,car_color,start_time_str,end_time_str,type,ctime,utime,note";

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
    public int insert(Merchant_parkinfo_monthfree_log bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Merchant_parkinfo_monthfree_log bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pu_id,pi_id,area_code,car_code,money,car_type,car_code_color,car_color,start_time_str,end_time_str,type,ctime,utime,note) VALUES (:pu_id,:pi_id,:area_code,:car_code,:money,:car_type,:car_code_color,:car_color,:start_time_str,:end_time_str,:type,:ctime,:utime,:note)";
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
    public int insert_primarykey(Merchant_parkinfo_monthfree_log bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Merchant_parkinfo_monthfree_log bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (id,pu_id,pi_id,area_code,car_code,money,car_type,car_code_color,car_color,start_time_str,end_time_str,type,ctime,utime,note) VALUES (:id,:pu_id,:pi_id,:area_code,:car_code,:money,:car_type,:car_code_color,:car_color,:start_time_str,:end_time_str,:type,:ctime,:utime,:note)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Merchant_parkinfo_monthfree_log> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Merchant_parkinfo_monthfree_log> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pu_id,pi_id,area_code,car_code,money,car_type,car_code_color,car_color,start_time_str,end_time_str,type,ctime,utime,note) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Merchant_parkinfo_monthfree_log bean = beans.get(i);
                    ps.setLong(1, bean.pu_id);
                    ps.setLong(2, bean.pi_id);
                    ps.setString(3, bean.area_code);
                    ps.setString(4, bean.car_code);
                    ps.setLong(5, bean.money);
                    ps.setInt(6, bean.car_type);
                    ps.setInt(7, bean.car_code_color);
                    ps.setInt(8, bean.car_color);
                    ps.setString(9, bean.start_time_str);
                    ps.setString(10, bean.end_time_str);
                    ps.setInt(11, bean.type);
                    ps.setTimestamp(12, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(13, new Timestamp(bean.utime.getTime()));
                    ps.setString(14, bean.note);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Merchant_parkinfo_monthfree_log> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Merchant_parkinfo_monthfree_log> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,pu_id,pi_id,area_code,car_code,money,car_type,car_code_color,car_color,start_time_str,end_time_str,type,ctime,utime,note FROM "+TABLENAME2+" ORDER BY id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Merchant_parkinfo_monthfree_log>(Merchant_parkinfo_monthfree_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Merchant_parkinfo_monthfree_log>();
        }
    }

    //查询最新数据
    public List<Merchant_parkinfo_monthfree_log> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Merchant_parkinfo_monthfree_log> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT id,pu_id,pi_id,area_code,car_code,money,car_type,car_code_color,car_color,start_time_str,end_time_str,type,ctime,utime,note FROM "+TABLENAME2+" ORDER BY id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Merchant_parkinfo_monthfree_log>(Merchant_parkinfo_monthfree_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Merchant_parkinfo_monthfree_log>();
        }
    }

    //根据主键查询
    public List<Merchant_parkinfo_monthfree_log> selectGtKey(long id) {
        return selectGtKey(id, TABLENAME);
    }

    //根据主键查询
    public List<Merchant_parkinfo_monthfree_log> selectGtKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,pu_id,pi_id,area_code,car_code,money,car_type,car_code_color,car_color,start_time_str,end_time_str,type,ctime,utime,note FROM "+TABLENAME2+" WHERE id>:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Merchant_parkinfo_monthfree_log>(Merchant_parkinfo_monthfree_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Merchant_parkinfo_monthfree_log>();
        }
    }

    //根据主键查询
    public Merchant_parkinfo_monthfree_log selectByKey(long id) {
        return selectByKey(id, TABLENAME);
    }

    //根据主键查询
    public Merchant_parkinfo_monthfree_log selectByKey(long id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT id,pu_id,pi_id,area_code,car_code,money,car_type,car_code_color,car_color,start_time_str,end_time_str,type,ctime,utime,note FROM "+TABLENAME2+" WHERE id=:id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("id", id);
            List<Merchant_parkinfo_monthfree_log> list =  _np.query(sql, param, new BeanPropertyRowMapper<Merchant_parkinfo_monthfree_log>(Merchant_parkinfo_monthfree_log.class));
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
    public List<Merchant_parkinfo_monthfree_log> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Merchant_parkinfo_monthfree_log> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT id,pu_id,pi_id,area_code,car_code,money,car_type,car_code_color,car_color,start_time_str,end_time_str,type,ctime,utime,note FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Merchant_parkinfo_monthfree_log>(Merchant_parkinfo_monthfree_log.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Merchant_parkinfo_monthfree_log>();
        }
    }

    //修改数据
    public int updateByKey(Merchant_parkinfo_monthfree_log bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Merchant_parkinfo_monthfree_log bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pu_id=:pu_id,pi_id=:pi_id,area_code=:area_code,car_code=:car_code,money=:money,car_type=:car_type,car_code_color=:car_code_color,car_color=:car_color,start_time_str=:start_time_str,end_time_str=:end_time_str,type=:type,ctime=:ctime,utime=:utime,note=:note WHERE id=:id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Merchant_parkinfo_monthfree_log> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Merchant_parkinfo_monthfree_log> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pu_id=?,pi_id=?,area_code=?,car_code=?,money=?,car_type=?,car_code_color=?,car_color=?,start_time_str=?,end_time_str=?,type=?,ctime=?,utime=?,note=? WHERE id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Merchant_parkinfo_monthfree_log bean = beans.get(i);
                    ps.setLong(1, bean.pu_id);
                    ps.setLong(2, bean.pi_id);
                    ps.setString(3, bean.area_code);
                    ps.setString(4, bean.car_code);
                    ps.setLong(5, bean.money);
                    ps.setInt(6, bean.car_type);
                    ps.setInt(7, bean.car_code_color);
                    ps.setInt(8, bean.car_color);
                    ps.setString(9, bean.start_time_str);
                    ps.setString(10, bean.end_time_str);
                    ps.setInt(11, bean.type);
                    ps.setTimestamp(12, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(13, new Timestamp(bean.utime.getTime()));
                    ps.setString(14, bean.note);
                    ps.setLong(15, bean.id);
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
                 "	`pu_id`  BIGINT(20) COMMENT '//bigint(20)    商户ID'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场ID'," +
                 "	`area_code`  VARCHAR(100) COMMENT '//varchar(100)    地址编号'," +
                 "	`car_code`  VARCHAR(100) COMMENT '//varchar(100)    车牌号'," +
                 "	`money`  BIGINT(20) COMMENT '//bigint(20)    该次缴纳费用(单位分)'," +
                 "	`car_type`  INT(11) COMMENT '//int(11)    车牌类型0：未知车牌:、1：蓝牌小汽车、2：:黑牌小汽车、3：单排黄牌、4：双排黄牌、5：警车车牌、6：武警车牌、7：个性化车牌、8：单排军车牌、9：双排军车牌、10：使馆车牌、11：香港进出中国大陆车牌、12：农用车牌、13：教练车牌、14：澳门进出中国大陆车牌、15：双层武警车牌、16：武警总队车牌、17：双层武警总队车牌'," +
                 "	`car_code_color`  INT(11) COMMENT '//int(11)    车牌颜色0：未知、1：蓝色、2：黄色、3：白色、4：黑色、5：绿色'," +
                 "	`car_color`  INT(11) COMMENT '//int(11)    车辆颜色BLUE('蓝色',1),YELLOW('黄色',2),WHITE('白色',3),BLACK('黑色',4),GREEN('绿色',5)'," +
                 "	`start_time_str`  VARCHAR(60) COMMENT '//varchar(60)    开始包月时间(2017-03-22:10:00:00)'," +
                 "	`end_time_str`  VARCHAR(60) COMMENT '//varchar(60)    包月到期时间(2017-03-22:10:00:00)'," +
                 "	`type`  INT(11) COMMENT '//int(11)    类型(0:包月车辆1:免费车辆)'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`note`  TINYTEXT COMMENT '//varchar(255)    备注'," +
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
