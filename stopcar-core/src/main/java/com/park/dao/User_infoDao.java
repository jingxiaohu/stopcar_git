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

//user_info

@Repository("user_infoDao")
public class User_infoDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(User_infoDao.class);



    private  String TABLE = "user_info";

    private  String TABLENAME = "user_info";

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


    private  String[] carrays ={"ui_id","uuid","ui_tel","ui_password","ui_nickname","ui_avatar","ui_sex","driving_licence","ui_name","ui_address","bind_tel","ui_vc","ui_rmb","coupon_num","ui_state","ui_autopay","pay_source","ui_level","ui_score","ui_mood","ui_intro","ui_age","ui_token","ui_email","ui_high","ui_degree","ctime","utime","ui_flag","ui_online","ui_integrity","note","lock_money","lock_expect_money","lock_rent_money","eu_id","eu_nd","etc_autopay","bank_no","ui_logo","ui_face_imgs","ui_face_feature","ui_finger_imgs","ui_finger_feature","ui_face_state","ui_finger_state"};
    private  String coulmns ="ui_id,uuid,ui_tel,ui_password,ui_nickname,ui_avatar,ui_sex,driving_licence,ui_name,ui_address,bind_tel,ui_vc,ui_rmb,coupon_num,ui_state,ui_autopay,pay_source,ui_level,ui_score,ui_mood,ui_intro,ui_age,ui_token,ui_email,ui_high,ui_degree,ctime,utime,ui_flag,ui_online,ui_integrity,note,lock_money,lock_expect_money,lock_rent_money,eu_id,eu_nd,etc_autopay,bank_no,ui_logo,ui_face_imgs,ui_face_feature,ui_finger_imgs,ui_finger_feature,ui_face_state,ui_finger_state";
    private  String coulmns2 ="uuid,ui_tel,ui_password,ui_nickname,ui_avatar,ui_sex,driving_licence,ui_name,ui_address,bind_tel,ui_vc,ui_rmb,coupon_num,ui_state,ui_autopay,pay_source,ui_level,ui_score,ui_mood,ui_intro,ui_age,ui_token,ui_email,ui_high,ui_degree,ctime,utime,ui_flag,ui_online,ui_integrity,note,lock_money,lock_expect_money,lock_rent_money,eu_id,eu_nd,etc_autopay,bank_no,ui_logo,ui_face_imgs,ui_face_feature,ui_finger_imgs,ui_finger_feature,ui_face_state,ui_finger_state";

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
    public int insert(User_info bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(User_info bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (uuid,ui_tel,ui_password,ui_nickname,ui_avatar,ui_sex,driving_licence,ui_name,ui_address,bind_tel,ui_vc,ui_rmb,coupon_num,ui_state,ui_autopay,pay_source,ui_level,ui_score,ui_mood,ui_intro,ui_age,ui_token,ui_email,ui_high,ui_degree,ctime,utime,ui_flag,ui_online,ui_integrity,note,lock_money,lock_expect_money,lock_rent_money,eu_id,eu_nd,etc_autopay,bank_no,ui_logo,ui_face_imgs,ui_face_feature,ui_finger_imgs,ui_finger_feature,ui_face_state,ui_finger_state) VALUES (:uuid,:ui_tel,:ui_password,:ui_nickname,:ui_avatar,:ui_sex,:driving_licence,:ui_name,:ui_address,:bind_tel,:ui_vc,:ui_rmb,:coupon_num,:ui_state,:ui_autopay,:pay_source,:ui_level,:ui_score,:ui_mood,:ui_intro,:ui_age,:ui_token,:ui_email,:ui_high,:ui_degree,:ctime,:utime,:ui_flag,:ui_online,:ui_integrity,:note,:lock_money,:lock_expect_money,:lock_rent_money,:eu_id,:eu_nd,:etc_autopay,:bank_no,:ui_logo,:ui_face_imgs,:ui_face_feature,:ui_finger_imgs,:ui_finger_feature,:ui_face_state,:ui_finger_state)";
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
    public int insert_primarykey(User_info bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(User_info bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (ui_id,uuid,ui_tel,ui_password,ui_nickname,ui_avatar,ui_sex,driving_licence,ui_name,ui_address,bind_tel,ui_vc,ui_rmb,coupon_num,ui_state,ui_autopay,pay_source,ui_level,ui_score,ui_mood,ui_intro,ui_age,ui_token,ui_email,ui_high,ui_degree,ctime,utime,ui_flag,ui_online,ui_integrity,note,lock_money,lock_expect_money,lock_rent_money,eu_id,eu_nd,etc_autopay,bank_no,ui_logo,ui_face_imgs,ui_face_feature,ui_finger_imgs,ui_finger_feature,ui_face_state,ui_finger_state) VALUES (:ui_id,:uuid,:ui_tel,:ui_password,:ui_nickname,:ui_avatar,:ui_sex,:driving_licence,:ui_name,:ui_address,:bind_tel,:ui_vc,:ui_rmb,:coupon_num,:ui_state,:ui_autopay,:pay_source,:ui_level,:ui_score,:ui_mood,:ui_intro,:ui_age,:ui_token,:ui_email,:ui_high,:ui_degree,:ctime,:utime,:ui_flag,:ui_online,:ui_integrity,:note,:lock_money,:lock_expect_money,:lock_rent_money,:eu_id,:eu_nd,:etc_autopay,:bank_no,:ui_logo,:ui_face_imgs,:ui_face_feature,:ui_finger_imgs,:ui_finger_feature,:ui_face_state,:ui_finger_state)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<User_info> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<User_info> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (uuid,ui_tel,ui_password,ui_nickname,ui_avatar,ui_sex,driving_licence,ui_name,ui_address,bind_tel,ui_vc,ui_rmb,coupon_num,ui_state,ui_autopay,pay_source,ui_level,ui_score,ui_mood,ui_intro,ui_age,ui_token,ui_email,ui_high,ui_degree,ctime,utime,ui_flag,ui_online,ui_integrity,note,lock_money,lock_expect_money,lock_rent_money,eu_id,eu_nd,etc_autopay,bank_no,ui_logo,ui_face_imgs,ui_face_feature,ui_finger_imgs,ui_finger_feature,ui_face_state,ui_finger_state) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    User_info bean = beans.get(i);
                    ps.setString(1, bean.uuid);
                    ps.setString(2, bean.ui_tel);
                    ps.setString(3, bean.ui_password);
                    ps.setString(4, bean.ui_nickname);
                    ps.setString(5, bean.ui_avatar);
                    ps.setString(6, bean.ui_sex);
                    ps.setString(7, bean.driving_licence);
                    ps.setString(8, bean.ui_name);
                    ps.setString(9, bean.ui_address);
                    ps.setString(10, bean.bind_tel);
                    ps.setLong(11, bean.ui_vc);
                    ps.setLong(12, bean.ui_rmb);
                    ps.setLong(13, bean.coupon_num);
                    ps.setInt(14, bean.ui_state);
                    ps.setInt(15, bean.ui_autopay);
                    ps.setInt(16, bean.pay_source);
                    ps.setInt(17, bean.ui_level);
                    ps.setLong(18, bean.ui_score);
                    ps.setString(19, bean.ui_mood);
                    ps.setString(20, bean.ui_intro);
                    ps.setInt(21, bean.ui_age);
                    ps.setString(22, bean.ui_token);
                    ps.setString(23, bean.ui_email);
                    ps.setInt(24, bean.ui_high);
                    ps.setString(25, bean.ui_degree);
                    ps.setTimestamp(26, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(27, new Timestamp(bean.utime.getTime()));
                    ps.setInt(28, bean.ui_flag);
                    ps.setLong(29, bean.ui_online);
                    ps.setInt(30, bean.ui_integrity);
                    ps.setString(31, bean.note);
                    ps.setInt(32, bean.lock_money);
                    ps.setInt(33, bean.lock_expect_money);
                    ps.setInt(34, bean.lock_rent_money);
                    ps.setLong(35, bean.eu_id);
                    ps.setString(36, bean.eu_nd);
                    ps.setInt(37, bean.etc_autopay);
                    ps.setString(38, bean.bank_no);
                    ps.setInt(39, bean.ui_logo);
                    ps.setString(40, bean.ui_face_imgs);
                    ps.setString(41, bean.ui_face_feature);
                    ps.setString(42, bean.ui_finger_imgs);
                    ps.setString(43, bean.ui_finger_feature);
                    ps.setInt(44, bean.ui_face_state);
                    ps.setInt(45, bean.ui_finger_state);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<User_info> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<User_info> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT ui_id,uuid,ui_tel,ui_password,ui_nickname,ui_avatar,ui_sex,driving_licence,ui_name,ui_address,bind_tel,ui_vc,ui_rmb,coupon_num,ui_state,ui_autopay,pay_source,ui_level,ui_score,ui_mood,ui_intro,ui_age,ui_token,ui_email,ui_high,ui_degree,ctime,utime,ui_flag,ui_online,ui_integrity,note,lock_money,lock_expect_money,lock_rent_money,eu_id,eu_nd,etc_autopay,bank_no,ui_logo,ui_face_imgs,ui_face_feature,ui_finger_imgs,ui_finger_feature,ui_face_state,ui_finger_state FROM "+TABLENAME2+" ORDER BY ui_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<User_info>(User_info.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<User_info>();
        }
    }

    //查询最新数据
    public List<User_info> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<User_info> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT ui_id,uuid,ui_tel,ui_password,ui_nickname,ui_avatar,ui_sex,driving_licence,ui_name,ui_address,bind_tel,ui_vc,ui_rmb,coupon_num,ui_state,ui_autopay,pay_source,ui_level,ui_score,ui_mood,ui_intro,ui_age,ui_token,ui_email,ui_high,ui_degree,ctime,utime,ui_flag,ui_online,ui_integrity,note,lock_money,lock_expect_money,lock_rent_money,eu_id,eu_nd,etc_autopay,bank_no,ui_logo,ui_face_imgs,ui_face_feature,ui_finger_imgs,ui_finger_feature,ui_face_state,ui_finger_state FROM "+TABLENAME2+" ORDER BY ui_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<User_info>(User_info.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<User_info>();
        }
    }

    //根据主键查询
    public List<User_info> selectGtKey(long ui_id) {
        return selectGtKey(ui_id, TABLENAME);
    }

    //根据主键查询
    public List<User_info> selectGtKey(long ui_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT ui_id,uuid,ui_tel,ui_password,ui_nickname,ui_avatar,ui_sex,driving_licence,ui_name,ui_address,bind_tel,ui_vc,ui_rmb,coupon_num,ui_state,ui_autopay,pay_source,ui_level,ui_score,ui_mood,ui_intro,ui_age,ui_token,ui_email,ui_high,ui_degree,ctime,utime,ui_flag,ui_online,ui_integrity,note,lock_money,lock_expect_money,lock_rent_money,eu_id,eu_nd,etc_autopay,bank_no,ui_logo,ui_face_imgs,ui_face_feature,ui_finger_imgs,ui_finger_feature,ui_face_state,ui_finger_state FROM "+TABLENAME2+" WHERE ui_id>:ui_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("ui_id", ui_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<User_info>(User_info.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<User_info>();
        }
    }

    //根据主键查询
    public User_info selectByKey(long ui_id) {
        return selectByKey(ui_id, TABLENAME);
    }

    //根据主键查询
    public User_info selectByKey(long ui_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT ui_id,uuid,ui_tel,ui_password,ui_nickname,ui_avatar,ui_sex,driving_licence,ui_name,ui_address,bind_tel,ui_vc,ui_rmb,coupon_num,ui_state,ui_autopay,pay_source,ui_level,ui_score,ui_mood,ui_intro,ui_age,ui_token,ui_email,ui_high,ui_degree,ctime,utime,ui_flag,ui_online,ui_integrity,note,lock_money,lock_expect_money,lock_rent_money,eu_id,eu_nd,etc_autopay,bank_no,ui_logo,ui_face_imgs,ui_face_feature,ui_finger_imgs,ui_finger_feature,ui_face_state,ui_finger_state FROM "+TABLENAME2+" WHERE ui_id=:ui_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("ui_id", ui_id);
            List<User_info> list =  _np.query(sql, param, new BeanPropertyRowMapper<User_info>(User_info.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey ui_id="+ui_id,e);
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
    public List<User_info> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<User_info> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT ui_id,uuid,ui_tel,ui_password,ui_nickname,ui_avatar,ui_sex,driving_licence,ui_name,ui_address,bind_tel,ui_vc,ui_rmb,coupon_num,ui_state,ui_autopay,pay_source,ui_level,ui_score,ui_mood,ui_intro,ui_age,ui_token,ui_email,ui_high,ui_degree,ctime,utime,ui_flag,ui_online,ui_integrity,note,lock_money,lock_expect_money,lock_rent_money,eu_id,eu_nd,etc_autopay,bank_no,ui_logo,ui_face_imgs,ui_face_feature,ui_finger_imgs,ui_finger_feature,ui_face_state,ui_finger_state FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<User_info>(User_info.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<User_info>();
        }
    }

    //修改数据
    public int updateByKey(User_info bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(User_info bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET uuid=:uuid,ui_tel=:ui_tel,ui_password=:ui_password,ui_nickname=:ui_nickname,ui_avatar=:ui_avatar,ui_sex=:ui_sex,driving_licence=:driving_licence,ui_name=:ui_name,ui_address=:ui_address,bind_tel=:bind_tel,ui_vc=:ui_vc,ui_rmb=:ui_rmb,coupon_num=:coupon_num,ui_state=:ui_state,ui_autopay=:ui_autopay,pay_source=:pay_source,ui_level=:ui_level,ui_score=:ui_score,ui_mood=:ui_mood,ui_intro=:ui_intro,ui_age=:ui_age,ui_token=:ui_token,ui_email=:ui_email,ui_high=:ui_high,ui_degree=:ui_degree,ctime=:ctime,utime=:utime,ui_flag=:ui_flag,ui_online=:ui_online,ui_integrity=:ui_integrity,note=:note,lock_money=:lock_money,lock_expect_money=:lock_expect_money,lock_rent_money=:lock_rent_money,eu_id=:eu_id,eu_nd=:eu_nd,etc_autopay=:etc_autopay,bank_no=:bank_no,ui_logo=:ui_logo,ui_face_imgs=:ui_face_imgs,ui_face_feature=:ui_face_feature,ui_finger_imgs=:ui_finger_imgs,ui_finger_feature=:ui_finger_feature,ui_face_state=:ui_face_state,ui_finger_state=:ui_finger_state WHERE ui_id=:ui_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<User_info> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<User_info> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET uuid=?,ui_tel=?,ui_password=?,ui_nickname=?,ui_avatar=?,ui_sex=?,driving_licence=?,ui_name=?,ui_address=?,bind_tel=?,ui_vc=?,ui_rmb=?,coupon_num=?,ui_state=?,ui_autopay=?,pay_source=?,ui_level=?,ui_score=?,ui_mood=?,ui_intro=?,ui_age=?,ui_token=?,ui_email=?,ui_high=?,ui_degree=?,ctime=?,utime=?,ui_flag=?,ui_online=?,ui_integrity=?,note=?,lock_money=?,lock_expect_money=?,lock_rent_money=?,eu_id=?,eu_nd=?,etc_autopay=?,bank_no=?,ui_logo=?,ui_face_imgs=?,ui_face_feature=?,ui_finger_imgs=?,ui_finger_feature=?,ui_face_state=?,ui_finger_state=? WHERE ui_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    User_info bean = beans.get(i);
                    ps.setString(1, bean.uuid);
                    ps.setString(2, bean.ui_tel);
                    ps.setString(3, bean.ui_password);
                    ps.setString(4, bean.ui_nickname);
                    ps.setString(5, bean.ui_avatar);
                    ps.setString(6, bean.ui_sex);
                    ps.setString(7, bean.driving_licence);
                    ps.setString(8, bean.ui_name);
                    ps.setString(9, bean.ui_address);
                    ps.setString(10, bean.bind_tel);
                    ps.setLong(11, bean.ui_vc);
                    ps.setLong(12, bean.ui_rmb);
                    ps.setLong(13, bean.coupon_num);
                    ps.setInt(14, bean.ui_state);
                    ps.setInt(15, bean.ui_autopay);
                    ps.setInt(16, bean.pay_source);
                    ps.setInt(17, bean.ui_level);
                    ps.setLong(18, bean.ui_score);
                    ps.setString(19, bean.ui_mood);
                    ps.setString(20, bean.ui_intro);
                    ps.setInt(21, bean.ui_age);
                    ps.setString(22, bean.ui_token);
                    ps.setString(23, bean.ui_email);
                    ps.setInt(24, bean.ui_high);
                    ps.setString(25, bean.ui_degree);
                    ps.setTimestamp(26, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(27, new Timestamp(bean.utime.getTime()));
                    ps.setInt(28, bean.ui_flag);
                    ps.setLong(29, bean.ui_online);
                    ps.setInt(30, bean.ui_integrity);
                    ps.setString(31, bean.note);
                    ps.setInt(32, bean.lock_money);
                    ps.setInt(33, bean.lock_expect_money);
                    ps.setInt(34, bean.lock_rent_money);
                    ps.setLong(35, bean.eu_id);
                    ps.setString(36, bean.eu_nd);
                    ps.setInt(37, bean.etc_autopay);
                    ps.setString(38, bean.bank_no);
                    ps.setInt(39, bean.ui_logo);
                    ps.setString(40, bean.ui_face_imgs);
                    ps.setString(41, bean.ui_face_feature);
                    ps.setString(42, bean.ui_finger_imgs);
                    ps.setString(43, bean.ui_finger_feature);
                    ps.setInt(44, bean.ui_face_state);
                    ps.setInt(45, bean.ui_finger_state);
                    ps.setLong(46, bean.ui_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long ui_id) throws SQLException{
        return deleteByKey(ui_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long ui_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE ui_id=:ui_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("ui_id", ui_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE ui_id=?";
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
                 "	`ui_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    '," +
                 "	`uuid`  VARCHAR(65) COMMENT '//varchar(65)    用户uuid'," +
                 "	`ui_tel`  VARCHAR(11) COMMENT '//varchar(11)    手机号码/用户账号'," +
                 "	`ui_password`  VARCHAR(80) COMMENT '//varchar(80)    用户密码(MD5散列)'," +
                 "	`ui_nickname`  VARCHAR(30) COMMENT '//varchar(30)    用户昵称'," +
                 "	`ui_avatar`  VARCHAR(200) COMMENT '//varchar(200)    用户头像'," +
                 "	`ui_sex`  VARCHAR(30) COMMENT '//varchar(30)    用户性别:male男women女no未知'," +
                 "	`driving_licence`  TINYTEXT COMMENT '//varchar(255)    驾驶证号码'," +
                 "	`ui_name`  VARCHAR(60) COMMENT '//varchar(60)    姓名'," +
                 "	`ui_address`  VARCHAR(100) COMMENT '//varchar(100)    住址'," +
                 "	`bind_tel`  VARCHAR(20) COMMENT '//varchar(20)    绑定手机号码'," +
                 "	`ui_vc`  BIGINT(20) COMMENT '//bigint(20)    爱泊币（100爱泊=1元）'," +
                 "	`ui_rmb`  BIGINT(20) COMMENT '//bigint(20)    爱泊币（100爱泊=1元）'," +
                 "	`coupon_num`  BIGINT(20) COMMENT '//bigint(20)    优费卷数量'," +
                 "	`ui_state`  INT(11) COMMENT '//int(11)    用户状态:0：正常1：禁用'," +
                 "	`ui_autopay`  INT(11) COMMENT '//int(11)    是否自动支付：0：不是1：是'," +
                 "	`pay_source`  INT(11) COMMENT '//int(11)    默认支付类型1:支付宝2：微信3：银联4：钱包'," +
                 "	`ui_level`  INT(11) COMMENT '//int(11)    用户等级'," +
                 "	`ui_score`  BIGINT(20) COMMENT '//bigint(20)    用户积分'," +
                 "	`ui_mood`  VARCHAR(100) COMMENT '//varchar(100)    用户心情'," +
                 "	`ui_intro`  TINYTEXT COMMENT '//varchar(255)    个人简介'," +
                 "	`ui_age`  INT(11) COMMENT '//int(11)    用户年龄'," +
                 "	`ui_token`  VARCHAR(60) COMMENT '//varchar(60)    用户授权码'," +
                 "	`ui_email`  VARCHAR(60) COMMENT '//varchar(60)    用户邮箱'," +
                 "	`ui_high`  INT(11) COMMENT '//int(11)    用户身高'," +
                 "	`ui_degree`  VARCHAR(60) COMMENT '//varchar(60)    '," +
                 "	`ctime`  DATETIME COMMENT '//datetime    '," +
                 "	`utime`  DATETIME COMMENT '//datetime    '," +
                 "	`ui_flag`  INT(11) COMMENT '//int(11)    注册来源0:android1:ios2:web'," +
                 "	`ui_online`  BIGINT(20) COMMENT '//bigint(20)    l累计在线时长(分钟)'," +
                 "	`ui_integrity`  INT(11) COMMENT '//int(11)    用户诚信度按100来计算'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`lock_money`  INT(11) COMMENT '//int(11)    锁定金额'," +
                 "	`lock_expect_money`  INT(11) COMMENT '//int(11)    锁定预约金额'," +
                 "	`lock_rent_money`  INT(11) COMMENT '//int(11)    锁定租赁金额'," +
                 "	`eu_id`  BIGINT(20) COMMENT '//bigint(20)    ETC基本信息表主键ID'," +
                 "	`eu_nd`  VARCHAR(70) COMMENT '//varchar(70)    ETC唯一标识符'," +
                 "	`etc_autopay`  INT(11) COMMENT '//int(11)    是否开启ETC支付0:没有开启1：开启'," +
                 "	`bank_no`  VARCHAR(60) COMMENT '//varchar(60)    选择的默认银行卡卡号'," +
                 "	`ui_logo`  INT(11) COMMENT '//int(11)    吾泊加V标志(0:没有1：显示W)'," +
                 "	`ui_face_imgs`  TINYTEXT COMMENT '//varchar(255)    用户人脸图片地址集合(逗号分割)'," +
                 "	`ui_face_feature`  TINYTEXT COMMENT '//varchar(255)    用户人脸图片特征数据集合'," +
                 "	`ui_finger_imgs`  TINYTEXT COMMENT '//varchar(255)    用户指纹图片地址集合(逗号分割)'," +
                 "	`ui_finger_feature`  TINYTEXT COMMENT '//varchar(255)    用户指纹特征数据集合'," +
                 "	`ui_face_state`  INT(11) COMMENT '//int(11)    人脸识别状态（0:未采集到人脸1：已经采集到人脸且关闭人脸支付2：开启人脸支付）'," +
                 "	`ui_finger_state`  INT(11) COMMENT '//int(11)    指纹识别状态（0:未采集到指纹1：已经采集到指纹且关闭指纹支付2：开启指纹支付）'," +
                 "	PRIMARY KEY (`ui_id`)" +
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
