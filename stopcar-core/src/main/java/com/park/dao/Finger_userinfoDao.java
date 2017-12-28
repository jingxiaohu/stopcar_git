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

//finger_userinfo

@Repository("finger_userinfoDao")
public class Finger_userinfoDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Finger_userinfoDao.class);



    private  String TABLE = "finger_userinfo";

    private  String TABLENAME = "finger_userinfo";

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


    private  String[] carrays ={"fu_id","fu_nd","ui_tel","ui_id","ui_nd","name","sfz_number","sfz_img_url","fingerprint","bank_card_number","bank_type","orderid","verify_sign","state","is_del","ctime","utime","mac","gather_id","is_sign","sign_ip","signtime","discard_time","is_default","note","car_code","finger_veno","fingerprint_hash","finger_veno_hash"};
    private  String coulmns ="fu_id,fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,fingerprint,bank_card_number,bank_type,orderid,verify_sign,state,is_del,ctime,utime,mac,gather_id,is_sign,sign_ip,signtime,discard_time,is_default,note,car_code,finger_veno,fingerprint_hash,finger_veno_hash";
    private  String coulmns2 ="fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,fingerprint,bank_card_number,bank_type,orderid,verify_sign,state,is_del,ctime,utime,mac,gather_id,is_sign,sign_ip,signtime,discard_time,is_default,note,car_code,finger_veno,fingerprint_hash,finger_veno_hash";

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
    public int insert(Finger_userinfo bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Finger_userinfo bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,fingerprint,bank_card_number,bank_type,orderid,verify_sign,state,is_del,ctime,utime,mac,gather_id,is_sign,sign_ip,signtime,discard_time,is_default,note,car_code,finger_veno,fingerprint_hash,finger_veno_hash) VALUES (:fu_nd,:ui_tel,:ui_id,:ui_nd,:name,:sfz_number,:sfz_img_url,:fingerprint,:bank_card_number,:bank_type,:orderid,:verify_sign,:state,:is_del,:ctime,:utime,:mac,:gather_id,:is_sign,:sign_ip,:signtime,:discard_time,:is_default,:note,:car_code,:finger_veno,:fingerprint_hash,:finger_veno_hash)";
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
    public int insert_primarykey(Finger_userinfo bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Finger_userinfo bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (fu_id,fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,fingerprint,bank_card_number,bank_type,orderid,verify_sign,state,is_del,ctime,utime,mac,gather_id,is_sign,sign_ip,signtime,discard_time,is_default,note,car_code,finger_veno,fingerprint_hash,finger_veno_hash) VALUES (:fu_id,:fu_nd,:ui_tel,:ui_id,:ui_nd,:name,:sfz_number,:sfz_img_url,:fingerprint,:bank_card_number,:bank_type,:orderid,:verify_sign,:state,:is_del,:ctime,:utime,:mac,:gather_id,:is_sign,:sign_ip,:signtime,:discard_time,:is_default,:note,:car_code,:finger_veno,:fingerprint_hash,:finger_veno_hash)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Finger_userinfo> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Finger_userinfo> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,fingerprint,bank_card_number,bank_type,orderid,verify_sign,state,is_del,ctime,utime,mac,gather_id,is_sign,sign_ip,signtime,discard_time,is_default,note,car_code,finger_veno,fingerprint_hash,finger_veno_hash) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Finger_userinfo bean = beans.get(i);
                    ps.setString(1, bean.fu_nd);
                    ps.setString(2, bean.ui_tel);
                    ps.setLong(3, bean.ui_id);
                    ps.setString(4, bean.ui_nd);
                    ps.setString(5, bean.name);
                    ps.setString(6, bean.sfz_number);
                    ps.setString(7, bean.sfz_img_url);
                    ps.setString(8, bean.fingerprint);
                    ps.setString(9, bean.bank_card_number);
                    ps.setInt(10, bean.bank_type);
                    ps.setString(11, bean.orderid);
                    ps.setInt(12, bean.verify_sign);
                    ps.setInt(13, bean.state);
                    ps.setInt(14, bean.is_del);
                    ps.setTimestamp(15, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(16, new Timestamp(bean.utime.getTime()));
                    ps.setString(17, bean.mac);
                    ps.setLong(18, bean.gather_id);
                    ps.setInt(19, bean.is_sign);
                    ps.setString(20, bean.sign_ip);
                    ps.setTimestamp(21, new Timestamp(bean.signtime.getTime()));
                    ps.setTimestamp(22, new Timestamp(bean.discard_time.getTime()));
                    ps.setInt(23, bean.is_default);
                    ps.setString(24, bean.note);
                    ps.setString(25, bean.car_code);
                    ps.setString(26, bean.finger_veno);
                    ps.setString(27, bean.fingerprint_hash);
                    ps.setString(28, bean.finger_veno_hash);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Finger_userinfo> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Finger_userinfo> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT fu_id,fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,fingerprint,bank_card_number,bank_type,orderid,verify_sign,state,is_del,ctime,utime,mac,gather_id,is_sign,sign_ip,signtime,discard_time,is_default,note,car_code,finger_veno,fingerprint_hash,finger_veno_hash FROM "+TABLENAME2+" ORDER BY fu_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Finger_userinfo>(Finger_userinfo.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Finger_userinfo>();
        }
    }

    //查询最新数据
    public List<Finger_userinfo> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Finger_userinfo> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT fu_id,fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,fingerprint,bank_card_number,bank_type,orderid,verify_sign,state,is_del,ctime,utime,mac,gather_id,is_sign,sign_ip,signtime,discard_time,is_default,note,car_code,finger_veno,fingerprint_hash,finger_veno_hash FROM "+TABLENAME2+" ORDER BY fu_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Finger_userinfo>(Finger_userinfo.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Finger_userinfo>();
        }
    }

    //根据主键查询
    public List<Finger_userinfo> selectGtKey(long fu_id) {
        return selectGtKey(fu_id, TABLENAME);
    }

    //根据主键查询
    public List<Finger_userinfo> selectGtKey(long fu_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT fu_id,fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,fingerprint,bank_card_number,bank_type,orderid,verify_sign,state,is_del,ctime,utime,mac,gather_id,is_sign,sign_ip,signtime,discard_time,is_default,note,car_code,finger_veno,fingerprint_hash,finger_veno_hash FROM "+TABLENAME2+" WHERE fu_id>:fu_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("fu_id", fu_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Finger_userinfo>(Finger_userinfo.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Finger_userinfo>();
        }
    }

    //根据主键查询
    public Finger_userinfo selectByKey(long fu_id) {
        return selectByKey(fu_id, TABLENAME);
    }

    //根据主键查询
    public Finger_userinfo selectByKey(long fu_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT fu_id,fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,fingerprint,bank_card_number,bank_type,orderid,verify_sign,state,is_del,ctime,utime,mac,gather_id,is_sign,sign_ip,signtime,discard_time,is_default,note,car_code,finger_veno,fingerprint_hash,finger_veno_hash FROM "+TABLENAME2+" WHERE fu_id=:fu_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("fu_id", fu_id);
            List<Finger_userinfo> list =  _np.query(sql, param, new BeanPropertyRowMapper<Finger_userinfo>(Finger_userinfo.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey fu_id="+fu_id,e);
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
    public List<Finger_userinfo> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Finger_userinfo> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT fu_id,fu_nd,ui_tel,ui_id,ui_nd,name,sfz_number,sfz_img_url,fingerprint,bank_card_number,bank_type,orderid,verify_sign,state,is_del,ctime,utime,mac,gather_id,is_sign,sign_ip,signtime,discard_time,is_default,note,car_code,finger_veno,fingerprint_hash,finger_veno_hash FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Finger_userinfo>(Finger_userinfo.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Finger_userinfo>();
        }
    }

    //修改数据
    public int updateByKey(Finger_userinfo bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Finger_userinfo bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET fu_nd=:fu_nd,ui_tel=:ui_tel,ui_id=:ui_id,ui_nd=:ui_nd,name=:name,sfz_number=:sfz_number,sfz_img_url=:sfz_img_url,fingerprint=:fingerprint,bank_card_number=:bank_card_number,bank_type=:bank_type,orderid=:orderid,verify_sign=:verify_sign,state=:state,is_del=:is_del,ctime=:ctime,utime=:utime,mac=:mac,gather_id=:gather_id,is_sign=:is_sign,sign_ip=:sign_ip,signtime=:signtime,discard_time=:discard_time,is_default=:is_default,note=:note,car_code=:car_code,finger_veno=:finger_veno,fingerprint_hash=:fingerprint_hash,finger_veno_hash=:finger_veno_hash WHERE fu_id=:fu_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Finger_userinfo> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Finger_userinfo> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET fu_nd=?,ui_tel=?,ui_id=?,ui_nd=?,name=?,sfz_number=?,sfz_img_url=?,fingerprint=?,bank_card_number=?,bank_type=?,orderid=?,verify_sign=?,state=?,is_del=?,ctime=?,utime=?,mac=?,gather_id=?,is_sign=?,sign_ip=?,signtime=?,discard_time=?,is_default=?,note=?,car_code=?,finger_veno=?,fingerprint_hash=?,finger_veno_hash=? WHERE fu_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Finger_userinfo bean = beans.get(i);
                    ps.setString(1, bean.fu_nd);
                    ps.setString(2, bean.ui_tel);
                    ps.setLong(3, bean.ui_id);
                    ps.setString(4, bean.ui_nd);
                    ps.setString(5, bean.name);
                    ps.setString(6, bean.sfz_number);
                    ps.setString(7, bean.sfz_img_url);
                    ps.setString(8, bean.fingerprint);
                    ps.setString(9, bean.bank_card_number);
                    ps.setInt(10, bean.bank_type);
                    ps.setString(11, bean.orderid);
                    ps.setInt(12, bean.verify_sign);
                    ps.setInt(13, bean.state);
                    ps.setInt(14, bean.is_del);
                    ps.setTimestamp(15, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(16, new Timestamp(bean.utime.getTime()));
                    ps.setString(17, bean.mac);
                    ps.setLong(18, bean.gather_id);
                    ps.setInt(19, bean.is_sign);
                    ps.setString(20, bean.sign_ip);
                    ps.setTimestamp(21, new Timestamp(bean.signtime.getTime()));
                    ps.setTimestamp(22, new Timestamp(bean.discard_time.getTime()));
                    ps.setInt(23, bean.is_default);
                    ps.setString(24, bean.note);
                    ps.setString(25, bean.car_code);
                    ps.setString(26, bean.finger_veno);
                    ps.setString(27, bean.fingerprint_hash);
                    ps.setString(28, bean.finger_veno_hash);
                    ps.setLong(29, bean.fu_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long fu_id) throws SQLException{
        return deleteByKey(fu_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long fu_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE fu_id=:fu_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("fu_id", fu_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE fu_id=?";
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
                 "	`fu_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`fu_nd`  VARCHAR(100) COMMENT '//varchar(100)    唯一标识符'," +
                 "	`ui_tel`  VARCHAR(30) COMMENT '//varchar(30)    用户手机号码'," +
                 "	`ui_id`  BIGINT(20) COMMENT '//bigint(20)    平台用户ID'," +
                 "	`ui_nd`  VARCHAR(100) COMMENT '//varchar(100)    平台用户唯一标识符'," +
                 "	`name`  VARCHAR(30) COMMENT '//varchar(30)    用户真实姓名'," +
                 "	`sfz_number`  VARCHAR(30) COMMENT '//varchar(30)    用户真实身份证号码'," +
                 "	`sfz_img_url`  VARCHAR(150) COMMENT '//varchar(150)    用户身份证图片'," +
                 "	`fingerprint`  TEXT COMMENT '//text    用户指纹特征信息'," +
                 "	`bank_card_number`  VARCHAR(30) COMMENT '//varchar(30)    用户银行卡卡号'," +
                 "	`bank_type`  INT(11) COMMENT '//int(11)    银行类型（0：建行银行）'," +
                 "	`orderid`  VARCHAR(70) COMMENT '//varchar(70)    签约验证支付订单号(user_pay表订单号)'," +
                 "	`verify_sign`  INT(11) COMMENT '//int(11)    是否签约支付验证成功0：未验证1：成功2：失败'," +
                 "	`state`  INT(11) COMMENT '//int(11)    有效状态(0：无效1：有效)'," +
                 "	`is_del`  INT(11) COMMENT '//int(11)    是否删除0:没有1：删除'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    修改时间'," +
                 "	`mac`  VARCHAR(100) COMMENT '//varchar(100)    采集数据提交设备MAC'," +
                 "	`gather_id`  BIGINT(20) COMMENT '//bigint(20)    采集数据提交设备基本信息表主键ID'," +
                 "	`is_sign`  INT(11) COMMENT '//int(11)    是否签约成功（0：没有签约1：签约成功2：签约失败3：解除签约）'," +
                 "	`sign_ip`  VARCHAR(100) COMMENT '//varchar(100)    签约地IP'," +
                 "	`signtime`  DATETIME COMMENT '//datetime    签约时间'," +
                 "	`discard_time`  DATETIME COMMENT '//datetime    解除签约时间'," +
                 "	`is_default`  INT(11) COMMENT '//int(11)    是否是默认ETC支付卡0:不是1：是'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`car_code`  VARCHAR(60) COMMENT '//varchar(60)    车牌号'," +
                 "	`finger_veno`  TEXT COMMENT '//text    用户指静脉特征信息'," +
                 "	`fingerprint_hash`  VARCHAR(100) COMMENT '//varchar(100)    用户指纹图片hashcode'," +
                 "	`finger_veno_hash`  VARCHAR(100) COMMENT '//varchar(100)    用户指静脉图片hashcode'," +
                 "	PRIMARY KEY (`fu_id`)" +
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
