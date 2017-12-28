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

//activity_info

@Repository("activity_infoDao")
public class Activity_infoDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Activity_infoDao.class);



    private  String TABLE = "activity_info";

    private  String TABLENAME = "activity_info";

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


    private  String[] carrays ={"ai_id","title","intro","money","type","starttime","endtime","ctime","utime","admin_loginname","admin_id","partner","people_num","note","state","pi_id","area_code","pc_id","weight","pi_name","img","img_jump_url","coupon_endtime"};
    private  String coulmns ="ai_id,title,intro,money,type,starttime,endtime,ctime,utime,admin_loginname,admin_id,partner,people_num,note,state,pi_id,area_code,pc_id,weight,pi_name,img,img_jump_url,coupon_endtime";
    private  String coulmns2 ="title,intro,money,type,starttime,endtime,ctime,utime,admin_loginname,admin_id,partner,people_num,note,state,pi_id,area_code,pc_id,weight,pi_name,img,img_jump_url,coupon_endtime";

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
    public int insert(Activity_info bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Activity_info bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (title,intro,money,type,starttime,endtime,ctime,utime,admin_loginname,admin_id,partner,people_num,note,state,pi_id,area_code,pc_id,weight,pi_name,img,img_jump_url,coupon_endtime) VALUES (:title,:intro,:money,:type,:starttime,:endtime,:ctime,:utime,:admin_loginname,:admin_id,:partner,:people_num,:note,:state,:pi_id,:area_code,:pc_id,:weight,:pi_name,:img,:img_jump_url,:coupon_endtime)";
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
    public int insert_primarykey(Activity_info bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Activity_info bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (ai_id,title,intro,money,type,starttime,endtime,ctime,utime,admin_loginname,admin_id,partner,people_num,note,state,pi_id,area_code,pc_id,weight,pi_name,img,img_jump_url,coupon_endtime) VALUES (:ai_id,:title,:intro,:money,:type,:starttime,:endtime,:ctime,:utime,:admin_loginname,:admin_id,:partner,:people_num,:note,:state,:pi_id,:area_code,:pc_id,:weight,:pi_name,:img,:img_jump_url,:coupon_endtime)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Activity_info> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Activity_info> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (title,intro,money,type,starttime,endtime,ctime,utime,admin_loginname,admin_id,partner,people_num,note,state,pi_id,area_code,pc_id,weight,pi_name,img,img_jump_url,coupon_endtime) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Activity_info bean = beans.get(i);
                    ps.setString(1, bean.title);
                    ps.setString(2, bean.intro);
                    ps.setLong(3, bean.money);
                    ps.setInt(4, bean.type);
                    ps.setTimestamp(5, new Timestamp(bean.starttime.getTime()));
                    ps.setTimestamp(6, new Timestamp(bean.endtime.getTime()));
                    ps.setTimestamp(7, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(8, new Timestamp(bean.utime.getTime()));
                    ps.setString(9, bean.admin_loginname);
                    ps.setLong(10, bean.admin_id);
                    ps.setString(11, bean.partner);
                    ps.setInt(12, bean.people_num);
                    ps.setString(13, bean.note);
                    ps.setInt(14, bean.state);
                    ps.setLong(15, bean.pi_id);
                    ps.setString(16, bean.area_code);
                    ps.setLong(17, bean.pc_id);
                    ps.setInt(18, bean.weight);
                    ps.setString(19, bean.pi_name);
                    ps.setString(20, bean.img);
                    ps.setString(21, bean.img_jump_url);
                    ps.setTimestamp(22, new Timestamp(bean.coupon_endtime.getTime()));
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Activity_info> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Activity_info> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT ai_id,title,intro,money,type,starttime,endtime,ctime,utime,admin_loginname,admin_id,partner,people_num,note,state,pi_id,area_code,pc_id,weight,pi_name,img,img_jump_url,coupon_endtime FROM "+TABLENAME2+" ORDER BY ai_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Activity_info>(Activity_info.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Activity_info>();
        }
    }

    //查询最新数据
    public List<Activity_info> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Activity_info> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT ai_id,title,intro,money,type,starttime,endtime,ctime,utime,admin_loginname,admin_id,partner,people_num,note,state,pi_id,area_code,pc_id,weight,pi_name,img,img_jump_url,coupon_endtime FROM "+TABLENAME2+" ORDER BY ai_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Activity_info>(Activity_info.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Activity_info>();
        }
    }

    //根据主键查询
    public List<Activity_info> selectGtKey(long ai_id) {
        return selectGtKey(ai_id, TABLENAME);
    }

    //根据主键查询
    public List<Activity_info> selectGtKey(long ai_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT ai_id,title,intro,money,type,starttime,endtime,ctime,utime,admin_loginname,admin_id,partner,people_num,note,state,pi_id,area_code,pc_id,weight,pi_name,img,img_jump_url,coupon_endtime FROM "+TABLENAME2+" WHERE ai_id>:ai_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("ai_id", ai_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Activity_info>(Activity_info.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Activity_info>();
        }
    }

    //根据主键查询
    public Activity_info selectByKey(long ai_id) {
        return selectByKey(ai_id, TABLENAME);
    }

    //根据主键查询
    public Activity_info selectByKey(long ai_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT ai_id,title,intro,money,type,starttime,endtime,ctime,utime,admin_loginname,admin_id,partner,people_num,note,state,pi_id,area_code,pc_id,weight,pi_name,img,img_jump_url,coupon_endtime FROM "+TABLENAME2+" WHERE ai_id=:ai_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("ai_id", ai_id);
            List<Activity_info> list =  _np.query(sql, param, new BeanPropertyRowMapper<Activity_info>(Activity_info.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey ai_id="+ai_id,e);
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
    public List<Activity_info> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Activity_info> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT ai_id,title,intro,money,type,starttime,endtime,ctime,utime,admin_loginname,admin_id,partner,people_num,note,state,pi_id,area_code,pc_id,weight,pi_name,img,img_jump_url,coupon_endtime FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Activity_info>(Activity_info.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Activity_info>();
        }
    }

    //修改数据
    public int updateByKey(Activity_info bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Activity_info bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET title=:title,intro=:intro,money=:money,type=:type,starttime=:starttime,endtime=:endtime,ctime=:ctime,utime=:utime,admin_loginname=:admin_loginname,admin_id=:admin_id,partner=:partner,people_num=:people_num,note=:note,state=:state,pi_id=:pi_id,area_code=:area_code,pc_id=:pc_id,weight=:weight,pi_name=:pi_name,img=:img,img_jump_url=:img_jump_url,coupon_endtime=:coupon_endtime WHERE ai_id=:ai_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Activity_info> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Activity_info> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET title=?,intro=?,money=?,type=?,starttime=?,endtime=?,ctime=?,utime=?,admin_loginname=?,admin_id=?,partner=?,people_num=?,note=?,state=?,pi_id=?,area_code=?,pc_id=?,weight=?,pi_name=?,img=?,img_jump_url=?,coupon_endtime=? WHERE ai_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Activity_info bean = beans.get(i);
                    ps.setString(1, bean.title);
                    ps.setString(2, bean.intro);
                    ps.setLong(3, bean.money);
                    ps.setInt(4, bean.type);
                    ps.setTimestamp(5, new Timestamp(bean.starttime.getTime()));
                    ps.setTimestamp(6, new Timestamp(bean.endtime.getTime()));
                    ps.setTimestamp(7, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(8, new Timestamp(bean.utime.getTime()));
                    ps.setString(9, bean.admin_loginname);
                    ps.setLong(10, bean.admin_id);
                    ps.setString(11, bean.partner);
                    ps.setInt(12, bean.people_num);
                    ps.setString(13, bean.note);
                    ps.setInt(14, bean.state);
                    ps.setLong(15, bean.pi_id);
                    ps.setString(16, bean.area_code);
                    ps.setLong(17, bean.pc_id);
                    ps.setInt(18, bean.weight);
                    ps.setString(19, bean.pi_name);
                    ps.setString(20, bean.img);
                    ps.setString(21, bean.img_jump_url);
                    ps.setTimestamp(22, new Timestamp(bean.coupon_endtime.getTime()));
                    ps.setLong(23, bean.ai_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long ai_id) throws SQLException{
        return deleteByKey(ai_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long ai_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE ai_id=:ai_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("ai_id", ai_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE ai_id=?";
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
                 "	`ai_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`title`  VARCHAR(150) COMMENT '//varchar(150)    活动标题'," +
                 "	`intro`  TINYTEXT COMMENT '//text    活动简介'," +
                 "	`money`  BIGINT(20) COMMENT '//bigint(20)    活动金额(单位分)'," +
                 "	`type`  INT(11) COMMENT '//int(11)    活动类型（例如1：返券2：减免）'," +
                 "	`starttime`  DATETIME COMMENT '//datetime    活动开始时间'," +
                 "	`endtime`  DATETIME COMMENT '//datetime    活动结束时间'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`admin_loginname`  VARCHAR(100) COMMENT '//varchar(100)    创建者账号'," +
                 "	`admin_id`  BIGINT(20) COMMENT '//bigint(20)    创建者主键IDadmin_id'," +
                 "	`partner`  VARCHAR(150) COMMENT '//varchar(150)    活动合作伙伴'," +
                 "	`people_num`  INT(11) COMMENT '//int(11)    活动参与人数'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`state`  INT(11) COMMENT '//int(11)    是否关闭0:正常1：关闭'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    停车场主键ID'," +
                 "	`area_code`  VARCHAR(100) COMMENT '//varchar(100)    停车场区域地址编码'," +
                 "	`pc_id`  BIGINT(20) COMMENT '//bigint(20)    优惠券基本信息表主键ID（外键优惠券基本信息表主键ID）'," +
                 "	`weight`  INT(11) COMMENT '//int(11)    权重比(排序)'," +
                 "	`pi_name`  VARCHAR(100) COMMENT '//varchar(100)    停车场名称'," +
                 "	`img`  TINYTEXT COMMENT '//varchar(255)    活动图片地址'," +
                 "	`img_jump_url`  TINYTEXT COMMENT '//varchar(255)    活动图片跳转地址'," +
                 "	`coupon_endtime`  DATETIME COMMENT '//datetime    送券到期时间'," +
                 "	PRIMARY KEY (`ai_id`)" +
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
