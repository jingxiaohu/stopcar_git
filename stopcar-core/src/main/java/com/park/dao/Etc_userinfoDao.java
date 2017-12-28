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

//etc_userinfo

@Repository("etc_userinfoDao")
public class Etc_userinfoDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Etc_userinfoDao.class);



    private  String TABLE = "etc_userinfo";

    private  String TABLENAME = "etc_userinfo";

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


    private  String[] carrays ={"eu_id","eu_nd","ui_id","ui_nd","name","sfz_number","sfz_img_url","bank_card_number","bank_type","is_sign","sign_ip","ctime","utime","signtime","discard_time","note","ui_tel","is_default","orderid","verify_sign","is_del"};
    private  String coulmns ="eu_id,eu_nd,ui_id,ui_nd,name,sfz_number,sfz_img_url,bank_card_number,bank_type,is_sign,sign_ip,ctime,utime,signtime,discard_time,note,ui_tel,is_default,orderid,verify_sign,is_del";
    private  String coulmns2 ="eu_nd,ui_id,ui_nd,name,sfz_number,sfz_img_url,bank_card_number,bank_type,is_sign,sign_ip,ctime,utime,signtime,discard_time,note,ui_tel,is_default,orderid,verify_sign,is_del";

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
    public int insert(Etc_userinfo bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Etc_userinfo bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (eu_nd,ui_id,ui_nd,name,sfz_number,sfz_img_url,bank_card_number,bank_type,is_sign,sign_ip,ctime,utime,signtime,discard_time,note,ui_tel,is_default,orderid,verify_sign,is_del) VALUES (:eu_nd,:ui_id,:ui_nd,:name,:sfz_number,:sfz_img_url,:bank_card_number,:bank_type,:is_sign,:sign_ip,:ctime,:utime,:signtime,:discard_time,:note,:ui_tel,:is_default,:orderid,:verify_sign,:is_del)";
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
    public int insert_primarykey(Etc_userinfo bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Etc_userinfo bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (eu_id,eu_nd,ui_id,ui_nd,name,sfz_number,sfz_img_url,bank_card_number,bank_type,is_sign,sign_ip,ctime,utime,signtime,discard_time,note,ui_tel,is_default,orderid,verify_sign,is_del) VALUES (:eu_id,:eu_nd,:ui_id,:ui_nd,:name,:sfz_number,:sfz_img_url,:bank_card_number,:bank_type,:is_sign,:sign_ip,:ctime,:utime,:signtime,:discard_time,:note,:ui_tel,:is_default,:orderid,:verify_sign,:is_del)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Etc_userinfo> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Etc_userinfo> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (eu_nd,ui_id,ui_nd,name,sfz_number,sfz_img_url,bank_card_number,bank_type,is_sign,sign_ip,ctime,utime,signtime,discard_time,note,ui_tel,is_default,orderid,verify_sign,is_del) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Etc_userinfo bean = beans.get(i);
                    ps.setString(1, bean.eu_nd);
                    ps.setLong(2, bean.ui_id);
                    ps.setString(3, bean.ui_nd);
                    ps.setString(4, bean.name);
                    ps.setString(5, bean.sfz_number);
                    ps.setString(6, bean.sfz_img_url);
                    ps.setString(7, bean.bank_card_number);
                    ps.setInt(8, bean.bank_type);
                    ps.setInt(9, bean.is_sign);
                    ps.setString(10, bean.sign_ip);
                    ps.setTimestamp(11, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(12, new Timestamp(bean.utime.getTime()));
                    ps.setTimestamp(13, new Timestamp(bean.signtime.getTime()));
                    ps.setTimestamp(14, new Timestamp(bean.discard_time.getTime()));
                    ps.setString(15, bean.note);
                    ps.setString(16, bean.ui_tel);
                    ps.setInt(17, bean.is_default);
                    ps.setString(18, bean.orderid);
                    ps.setInt(19, bean.verify_sign);
                    ps.setInt(20, bean.is_del);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Etc_userinfo> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Etc_userinfo> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT eu_id,eu_nd,ui_id,ui_nd,name,sfz_number,sfz_img_url,bank_card_number,bank_type,is_sign,sign_ip,ctime,utime,signtime,discard_time,note,ui_tel,is_default,orderid,verify_sign,is_del FROM "+TABLENAME2+" ORDER BY eu_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Etc_userinfo>(Etc_userinfo.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Etc_userinfo>();
        }
    }

    //查询最新数据
    public List<Etc_userinfo> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Etc_userinfo> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT eu_id,eu_nd,ui_id,ui_nd,name,sfz_number,sfz_img_url,bank_card_number,bank_type,is_sign,sign_ip,ctime,utime,signtime,discard_time,note,ui_tel,is_default,orderid,verify_sign,is_del FROM "+TABLENAME2+" ORDER BY eu_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Etc_userinfo>(Etc_userinfo.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Etc_userinfo>();
        }
    }

    //根据主键查询
    public List<Etc_userinfo> selectGtKey(long eu_id) {
        return selectGtKey(eu_id, TABLENAME);
    }

    //根据主键查询
    public List<Etc_userinfo> selectGtKey(long eu_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT eu_id,eu_nd,ui_id,ui_nd,name,sfz_number,sfz_img_url,bank_card_number,bank_type,is_sign,sign_ip,ctime,utime,signtime,discard_time,note,ui_tel,is_default,orderid,verify_sign,is_del FROM "+TABLENAME2+" WHERE eu_id>:eu_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("eu_id", eu_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Etc_userinfo>(Etc_userinfo.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Etc_userinfo>();
        }
    }

    //根据主键查询
    public Etc_userinfo selectByKey(long eu_id) {
        return selectByKey(eu_id, TABLENAME);
    }

    //根据主键查询
    public Etc_userinfo selectByKey(long eu_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT eu_id,eu_nd,ui_id,ui_nd,name,sfz_number,sfz_img_url,bank_card_number,bank_type,is_sign,sign_ip,ctime,utime,signtime,discard_time,note,ui_tel,is_default,orderid,verify_sign,is_del FROM "+TABLENAME2+" WHERE eu_id=:eu_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("eu_id", eu_id);
            List<Etc_userinfo> list =  _np.query(sql, param, new BeanPropertyRowMapper<Etc_userinfo>(Etc_userinfo.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey eu_id="+eu_id,e);
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
    public List<Etc_userinfo> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Etc_userinfo> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT eu_id,eu_nd,ui_id,ui_nd,name,sfz_number,sfz_img_url,bank_card_number,bank_type,is_sign,sign_ip,ctime,utime,signtime,discard_time,note,ui_tel,is_default,orderid,verify_sign,is_del FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Etc_userinfo>(Etc_userinfo.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Etc_userinfo>();
        }
    }

    //修改数据
    public int updateByKey(Etc_userinfo bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Etc_userinfo bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET eu_nd=:eu_nd,ui_id=:ui_id,ui_nd=:ui_nd,name=:name,sfz_number=:sfz_number,sfz_img_url=:sfz_img_url,bank_card_number=:bank_card_number,bank_type=:bank_type,is_sign=:is_sign,sign_ip=:sign_ip,ctime=:ctime,utime=:utime,signtime=:signtime,discard_time=:discard_time,note=:note,ui_tel=:ui_tel,is_default=:is_default,orderid=:orderid,verify_sign=:verify_sign,is_del=:is_del WHERE eu_id=:eu_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Etc_userinfo> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Etc_userinfo> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET eu_nd=?,ui_id=?,ui_nd=?,name=?,sfz_number=?,sfz_img_url=?,bank_card_number=?,bank_type=?,is_sign=?,sign_ip=?,ctime=?,utime=?,signtime=?,discard_time=?,note=?,ui_tel=?,is_default=?,orderid=?,verify_sign=?,is_del=? WHERE eu_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Etc_userinfo bean = beans.get(i);
                    ps.setString(1, bean.eu_nd);
                    ps.setLong(2, bean.ui_id);
                    ps.setString(3, bean.ui_nd);
                    ps.setString(4, bean.name);
                    ps.setString(5, bean.sfz_number);
                    ps.setString(6, bean.sfz_img_url);
                    ps.setString(7, bean.bank_card_number);
                    ps.setInt(8, bean.bank_type);
                    ps.setInt(9, bean.is_sign);
                    ps.setString(10, bean.sign_ip);
                    ps.setTimestamp(11, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(12, new Timestamp(bean.utime.getTime()));
                    ps.setTimestamp(13, new Timestamp(bean.signtime.getTime()));
                    ps.setTimestamp(14, new Timestamp(bean.discard_time.getTime()));
                    ps.setString(15, bean.note);
                    ps.setString(16, bean.ui_tel);
                    ps.setInt(17, bean.is_default);
                    ps.setString(18, bean.orderid);
                    ps.setInt(19, bean.verify_sign);
                    ps.setInt(20, bean.is_del);
                    ps.setLong(21, bean.eu_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long eu_id) throws SQLException{
        return deleteByKey(eu_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long eu_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE eu_id=:eu_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("eu_id", eu_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE eu_id=?";
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
                 "	`eu_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`eu_nd`  VARCHAR(100) COMMENT '//varchar(100)    etc用户唯一标识符'," +
                 "	`ui_id`  BIGINT(20) COMMENT '//bigint(20)    平台用户ID'," +
                 "	`ui_nd`  VARCHAR(100) COMMENT '//varchar(100)    平台用户唯一标识符'," +
                 "	`name`  VARCHAR(30) COMMENT '//varchar(30)    ETC用户真实姓名'," +
                 "	`sfz_number`  VARCHAR(30) COMMENT '//varchar(30)    ETC用户真实身份证号码'," +
                 "	`sfz_img_url`  VARCHAR(150) COMMENT '//varchar(150)    ETC用户身份证图片'," +
                 "	`bank_card_number`  VARCHAR(30) COMMENT '//varchar(30)    ETC用户银行卡卡号'," +
                 "	`bank_type`  INT(11) COMMENT '//int(11)    银行类型（0：建行银行）'," +
                 "	`is_sign`  INT(11) COMMENT '//int(11)    是否签约成功（0：没有签约1：签约成功2：签约失败3：解除签约）'," +
                 "	`sign_ip`  VARCHAR(100) COMMENT '//varchar(100)    签约地IP'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    修改时间'," +
                 "	`signtime`  DATETIME COMMENT '//datetime    签约时间'," +
                 "	`discard_time`  DATETIME COMMENT '//datetime    解除签约时间'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`ui_tel`  VARCHAR(30) COMMENT '//varchar(30)    用户手机号码'," +
                 "	`is_default`  INT(11) COMMENT '//int(11)    是否是默认ETC支付卡0:不是1：是'," +
                 "	`orderid`  VARCHAR(70) COMMENT '//varchar(70)    签约验证支付订单号(user_pay表订单号)'," +
                 "	`verify_sign`  INT(11) COMMENT '//int(11)    是否签约支付验证成功0：未验证1：成功2：失败'," +
                 "	`is_del`  INT(11) COMMENT '//int(11)    是否删除0:没有1：删除'," +
                 "	PRIMARY KEY (`eu_id`)" +
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
