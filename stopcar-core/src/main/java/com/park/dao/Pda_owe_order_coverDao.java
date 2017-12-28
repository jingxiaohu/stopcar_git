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

//pda_owe_order_cover

@Repository("pda_owe_order_coverDao")
public class Pda_owe_order_coverDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Pda_owe_order_coverDao.class);



    private  String TABLE = "pda_owe_order_cover";

    private  String TABLENAME = "pda_owe_order_cover";

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


    private  String[] carrays ={"pooc_id","pooc_nd","car_code","order_id","old_pi_id","old_area_code","old_pu_id","old_pu_nd","old_mac","now_pi_id","now_area_code","now_pu_id","now_pu_nd","now_mac","money","ctime","utime","state","old_ui_id","old_uuid","now_ui_id","now_uuid","note","pay_source"};
    private  String coulmns ="pooc_id,pooc_nd,car_code,order_id,old_pi_id,old_area_code,old_pu_id,old_pu_nd,old_mac,now_pi_id,now_area_code,now_pu_id,now_pu_nd,now_mac,money,ctime,utime,state,old_ui_id,old_uuid,now_ui_id,now_uuid,note,pay_source";
    private  String coulmns2 ="pooc_nd,car_code,order_id,old_pi_id,old_area_code,old_pu_id,old_pu_nd,old_mac,now_pi_id,now_area_code,now_pu_id,now_pu_nd,now_mac,money,ctime,utime,state,old_ui_id,old_uuid,now_ui_id,now_uuid,note,pay_source";

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
    public int insert(Pda_owe_order_cover bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Pda_owe_order_cover bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pooc_nd,car_code,order_id,old_pi_id,old_area_code,old_pu_id,old_pu_nd,old_mac,now_pi_id,now_area_code,now_pu_id,now_pu_nd,now_mac,money,ctime,utime,state,old_ui_id,old_uuid,now_ui_id,now_uuid,note,pay_source) VALUES (:pooc_nd,:car_code,:order_id,:old_pi_id,:old_area_code,:old_pu_id,:old_pu_nd,:old_mac,:now_pi_id,:now_area_code,:now_pu_id,:now_pu_nd,:now_mac,:money,:ctime,:utime,:state,:old_ui_id,:old_uuid,:now_ui_id,:now_uuid,:note,:pay_source)";
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
    public int insert_primarykey(Pda_owe_order_cover bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Pda_owe_order_cover bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pooc_id,pooc_nd,car_code,order_id,old_pi_id,old_area_code,old_pu_id,old_pu_nd,old_mac,now_pi_id,now_area_code,now_pu_id,now_pu_nd,now_mac,money,ctime,utime,state,old_ui_id,old_uuid,now_ui_id,now_uuid,note,pay_source) VALUES (:pooc_id,:pooc_nd,:car_code,:order_id,:old_pi_id,:old_area_code,:old_pu_id,:old_pu_nd,:old_mac,:now_pi_id,:now_area_code,:now_pu_id,:now_pu_nd,:now_mac,:money,:ctime,:utime,:state,:old_ui_id,:old_uuid,:now_ui_id,:now_uuid,:note,:pay_source)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Pda_owe_order_cover> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Pda_owe_order_cover> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pooc_nd,car_code,order_id,old_pi_id,old_area_code,old_pu_id,old_pu_nd,old_mac,now_pi_id,now_area_code,now_pu_id,now_pu_nd,now_mac,money,ctime,utime,state,old_ui_id,old_uuid,now_ui_id,now_uuid,note,pay_source) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Pda_owe_order_cover bean = beans.get(i);
                    ps.setString(1, bean.pooc_nd);
                    ps.setString(2, bean.car_code);
                    ps.setString(3, bean.order_id);
                    ps.setLong(4, bean.old_pi_id);
                    ps.setString(5, bean.old_area_code);
                    ps.setLong(6, bean.old_pu_id);
                    ps.setString(7, bean.old_pu_nd);
                    ps.setString(8, bean.old_mac);
                    ps.setLong(9, bean.now_pi_id);
                    ps.setString(10, bean.now_area_code);
                    ps.setLong(11, bean.now_pu_id);
                    ps.setString(12, bean.now_pu_nd);
                    ps.setString(13, bean.now_mac);
                    ps.setLong(14, bean.money);
                    ps.setTimestamp(15, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(16, new Timestamp(bean.utime.getTime()));
                    ps.setInt(17, bean.state);
                    ps.setLong(18, bean.old_ui_id);
                    ps.setString(19, bean.old_uuid);
                    ps.setLong(20, bean.now_ui_id);
                    ps.setString(21, bean.now_uuid);
                    ps.setString(22, bean.note);
                    ps.setInt(23, bean.pay_source);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Pda_owe_order_cover> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Pda_owe_order_cover> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pooc_id,pooc_nd,car_code,order_id,old_pi_id,old_area_code,old_pu_id,old_pu_nd,old_mac,now_pi_id,now_area_code,now_pu_id,now_pu_nd,now_mac,money,ctime,utime,state,old_ui_id,old_uuid,now_ui_id,now_uuid,note,pay_source FROM "+TABLENAME2+" ORDER BY pooc_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Pda_owe_order_cover>(Pda_owe_order_cover.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Pda_owe_order_cover>();
        }
    }

    //查询最新数据
    public List<Pda_owe_order_cover> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Pda_owe_order_cover> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pooc_id,pooc_nd,car_code,order_id,old_pi_id,old_area_code,old_pu_id,old_pu_nd,old_mac,now_pi_id,now_area_code,now_pu_id,now_pu_nd,now_mac,money,ctime,utime,state,old_ui_id,old_uuid,now_ui_id,now_uuid,note,pay_source FROM "+TABLENAME2+" ORDER BY pooc_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Pda_owe_order_cover>(Pda_owe_order_cover.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Pda_owe_order_cover>();
        }
    }

    //根据主键查询
    public List<Pda_owe_order_cover> selectGtKey(long pooc_id) {
        return selectGtKey(pooc_id, TABLENAME);
    }

    //根据主键查询
    public List<Pda_owe_order_cover> selectGtKey(long pooc_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pooc_id,pooc_nd,car_code,order_id,old_pi_id,old_area_code,old_pu_id,old_pu_nd,old_mac,now_pi_id,now_area_code,now_pu_id,now_pu_nd,now_mac,money,ctime,utime,state,old_ui_id,old_uuid,now_ui_id,now_uuid,note,pay_source FROM "+TABLENAME2+" WHERE pooc_id>:pooc_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pooc_id", pooc_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Pda_owe_order_cover>(Pda_owe_order_cover.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Pda_owe_order_cover>();
        }
    }

    //根据主键查询
    public Pda_owe_order_cover selectByKey(long pooc_id) {
        return selectByKey(pooc_id, TABLENAME);
    }

    //根据主键查询
    public Pda_owe_order_cover selectByKey(long pooc_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pooc_id,pooc_nd,car_code,order_id,old_pi_id,old_area_code,old_pu_id,old_pu_nd,old_mac,now_pi_id,now_area_code,now_pu_id,now_pu_nd,now_mac,money,ctime,utime,state,old_ui_id,old_uuid,now_ui_id,now_uuid,note,pay_source FROM "+TABLENAME2+" WHERE pooc_id=:pooc_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pooc_id", pooc_id);
            List<Pda_owe_order_cover> list =  _np.query(sql, param, new BeanPropertyRowMapper<Pda_owe_order_cover>(Pda_owe_order_cover.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey pooc_id="+pooc_id,e);
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
    public List<Pda_owe_order_cover> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Pda_owe_order_cover> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT pooc_id,pooc_nd,car_code,order_id,old_pi_id,old_area_code,old_pu_id,old_pu_nd,old_mac,now_pi_id,now_area_code,now_pu_id,now_pu_nd,now_mac,money,ctime,utime,state,old_ui_id,old_uuid,now_ui_id,now_uuid,note,pay_source FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Pda_owe_order_cover>(Pda_owe_order_cover.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Pda_owe_order_cover>();
        }
    }

    //修改数据
    public int updateByKey(Pda_owe_order_cover bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Pda_owe_order_cover bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pooc_nd=:pooc_nd,car_code=:car_code,order_id=:order_id,old_pi_id=:old_pi_id,old_area_code=:old_area_code,old_pu_id=:old_pu_id,old_pu_nd=:old_pu_nd,old_mac=:old_mac,now_pi_id=:now_pi_id,now_area_code=:now_area_code,now_pu_id=:now_pu_id,now_pu_nd=:now_pu_nd,now_mac=:now_mac,money=:money,ctime=:ctime,utime=:utime,state=:state,old_ui_id=:old_ui_id,old_uuid=:old_uuid,now_ui_id=:now_ui_id,now_uuid=:now_uuid,note=:note,pay_source=:pay_source WHERE pooc_id=:pooc_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Pda_owe_order_cover> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Pda_owe_order_cover> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET pooc_nd=?,car_code=?,order_id=?,old_pi_id=?,old_area_code=?,old_pu_id=?,old_pu_nd=?,old_mac=?,now_pi_id=?,now_area_code=?,now_pu_id=?,now_pu_nd=?,now_mac=?,money=?,ctime=?,utime=?,state=?,old_ui_id=?,old_uuid=?,now_ui_id=?,now_uuid=?,note=?,pay_source=? WHERE pooc_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Pda_owe_order_cover bean = beans.get(i);
                    ps.setString(1, bean.pooc_nd);
                    ps.setString(2, bean.car_code);
                    ps.setString(3, bean.order_id);
                    ps.setLong(4, bean.old_pi_id);
                    ps.setString(5, bean.old_area_code);
                    ps.setLong(6, bean.old_pu_id);
                    ps.setString(7, bean.old_pu_nd);
                    ps.setString(8, bean.old_mac);
                    ps.setLong(9, bean.now_pi_id);
                    ps.setString(10, bean.now_area_code);
                    ps.setLong(11, bean.now_pu_id);
                    ps.setString(12, bean.now_pu_nd);
                    ps.setString(13, bean.now_mac);
                    ps.setLong(14, bean.money);
                    ps.setTimestamp(15, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(16, new Timestamp(bean.utime.getTime()));
                    ps.setInt(17, bean.state);
                    ps.setLong(18, bean.old_ui_id);
                    ps.setString(19, bean.old_uuid);
                    ps.setLong(20, bean.now_ui_id);
                    ps.setString(21, bean.now_uuid);
                    ps.setString(22, bean.note);
                    ps.setInt(23, bean.pay_source);
                    ps.setLong(24, bean.pooc_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long pooc_id) throws SQLException{
        return deleteByKey(pooc_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long pooc_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE pooc_id=:pooc_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pooc_id", pooc_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE pooc_id=?";
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
                 "	`pooc_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`pooc_nd`  VARCHAR(70) COMMENT '//varchar(70)    ND'," +
                 "	`car_code`  VARCHAR(30) COMMENT '//varchar(30)    车牌号'," +
                 "	`order_id`  VARCHAR(70) COMMENT '//varchar(70)    欠费订单ID'," +
                 "	`old_pi_id`  BIGINT(20) COMMENT '//bigint(20)    原欠费订单停车场ID'," +
                 "	`old_area_code`  VARCHAR(30) COMMENT '//varchar(30)    原欠费订单停车场区域编码'," +
                 "	`old_pu_id`  BIGINT(20) COMMENT '//bigint(20)    原欠费订单所属商户ID'," +
                 "	`old_pu_nd`  VARCHAR(70) COMMENT '//varchar(70)    原欠费订单所属商户ND'," +
                 "	`old_mac`  VARCHAR(70) COMMENT '//varchar(70)    原PDA欠费设备唯一标识符MAC'," +
                 "	`now_pi_id`  BIGINT(20) COMMENT '//bigint(20)    PDA补交费用所在停车场ID'," +
                 "	`now_area_code`  VARCHAR(30) COMMENT '//varchar(30)    PDA补交费用停车场区域编码'," +
                 "	`now_pu_id`  BIGINT(20) COMMENT '//bigint(20)    PDA补交费用所属商户ID'," +
                 "	`now_pu_nd`  VARCHAR(70) COMMENT '//varchar(70)    PDA补交费用所属商户ND(商户编号)'," +
                 "	`now_mac`  VARCHAR(70) COMMENT '//varchar(70)    PDA补交设备唯一标识MAC'," +
                 "	`money`  BIGINT(20) COMMENT '//bigint(20)    PDA补交费用(单位分)'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`state`  INT(11) COMMENT '//int(11)    补交是否完成（0：未完成1：完成）'," +
                 "	`old_ui_id`  BIGINT(20) COMMENT '//bigint(20)    原欠费订单所属用户主键ID'," +
                 "	`old_uuid`  VARCHAR(70) COMMENT '//varchar(70)    原欠费订单所属用户ND'," +
                 "	`now_ui_id`  BIGINT(20) COMMENT '//bigint(20)    当前补交欠费用户主键ID'," +
                 "	`now_uuid`  VARCHAR(70) COMMENT '//varchar(70)    当前补交欠费用户ND'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`pay_source`  INT(11) COMMENT '//int(11)    支付类型0：现金支付1:支付宝2：微信3：银联4：钱包5:龙支付6:ETC快捷支付7：扫脸支付8：指纹支付9：指静脉支付'," +
                 "	PRIMARY KEY (`pooc_id`)" +
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
