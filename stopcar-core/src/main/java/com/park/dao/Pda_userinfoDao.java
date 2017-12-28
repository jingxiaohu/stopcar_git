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

//pda_userinfo

@Repository("pda_userinfoDao")
public class Pda_userinfoDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Pda_userinfoDao.class);



    private  String TABLE = "pda_userinfo";

    private  String TABLENAME = "pda_userinfo";

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


    private  String[] carrays ={"pui_id","name","age","sex","address","tel","link_tel","pi_id","area_code","pda_id","mac","ctime","utime","note","loginname","password","state"};
    private  String coulmns ="pui_id,name,age,sex,address,tel,link_tel,pi_id,area_code,pda_id,mac,ctime,utime,note,loginname,password,state";
    private  String coulmns2 ="name,age,sex,address,tel,link_tel,pi_id,area_code,pda_id,mac,ctime,utime,note,loginname,password,state";

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
    public int insert(Pda_userinfo bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Pda_userinfo bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (name,age,sex,address,tel,link_tel,pi_id,area_code,pda_id,mac,ctime,utime,note,loginname,password,state) VALUES (:name,:age,:sex,:address,:tel,:link_tel,:pi_id,:area_code,:pda_id,:mac,:ctime,:utime,:note,:loginname,:password,:state)";
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
    public int insert_primarykey(Pda_userinfo bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Pda_userinfo bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pui_id,name,age,sex,address,tel,link_tel,pi_id,area_code,pda_id,mac,ctime,utime,note,loginname,password,state) VALUES (:pui_id,:name,:age,:sex,:address,:tel,:link_tel,:pi_id,:area_code,:pda_id,:mac,:ctime,:utime,:note,:loginname,:password,:state)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Pda_userinfo> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Pda_userinfo> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (name,age,sex,address,tel,link_tel,pi_id,area_code,pda_id,mac,ctime,utime,note,loginname,password,state) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Pda_userinfo bean = beans.get(i);
                    ps.setString(1, bean.name);
                    ps.setInt(2, bean.age);
                    ps.setInt(3, bean.sex);
                    ps.setString(4, bean.address);
                    ps.setString(5, bean.tel);
                    ps.setString(6, bean.link_tel);
                    ps.setLong(7, bean.pi_id);
                    ps.setString(8, bean.area_code);
                    ps.setLong(9, bean.pda_id);
                    ps.setString(10, bean.mac);
                    ps.setTimestamp(11, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(12, new Timestamp(bean.utime.getTime()));
                    ps.setString(13, bean.note);
                    ps.setString(14, bean.loginname);
                    ps.setString(15, bean.password);
                    ps.setInt(16, bean.state);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Pda_userinfo> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Pda_userinfo> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pui_id,name,age,sex,address,tel,link_tel,pi_id,area_code,pda_id,mac,ctime,utime,note,loginname,password,state FROM "+TABLENAME2+" ORDER BY pui_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Pda_userinfo>(Pda_userinfo.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Pda_userinfo>();
        }
    }

    //查询最新数据
    public List<Pda_userinfo> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Pda_userinfo> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pui_id,name,age,sex,address,tel,link_tel,pi_id,area_code,pda_id,mac,ctime,utime,note,loginname,password,state FROM "+TABLENAME2+" ORDER BY pui_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Pda_userinfo>(Pda_userinfo.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Pda_userinfo>();
        }
    }

    //根据主键查询
    public List<Pda_userinfo> selectGtKey(long pui_id) {
        return selectGtKey(pui_id, TABLENAME);
    }

    //根据主键查询
    public List<Pda_userinfo> selectGtKey(long pui_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pui_id,name,age,sex,address,tel,link_tel,pi_id,area_code,pda_id,mac,ctime,utime,note,loginname,password,state FROM "+TABLENAME2+" WHERE pui_id>:pui_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pui_id", pui_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Pda_userinfo>(Pda_userinfo.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Pda_userinfo>();
        }
    }

    //根据主键查询
    public Pda_userinfo selectByKey(long pui_id) {
        return selectByKey(pui_id, TABLENAME);
    }

    //根据主键查询
    public Pda_userinfo selectByKey(long pui_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pui_id,name,age,sex,address,tel,link_tel,pi_id,area_code,pda_id,mac,ctime,utime,note,loginname,password,state FROM "+TABLENAME2+" WHERE pui_id=:pui_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pui_id", pui_id);
            List<Pda_userinfo> list =  _np.query(sql, param, new BeanPropertyRowMapper<Pda_userinfo>(Pda_userinfo.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey pui_id="+pui_id,e);
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
    public List<Pda_userinfo> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Pda_userinfo> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT pui_id,name,age,sex,address,tel,link_tel,pi_id,area_code,pda_id,mac,ctime,utime,note,loginname,password,state FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Pda_userinfo>(Pda_userinfo.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Pda_userinfo>();
        }
    }

    //修改数据
    public int updateByKey(Pda_userinfo bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Pda_userinfo bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET name=:name,age=:age,sex=:sex,address=:address,tel=:tel,link_tel=:link_tel,pi_id=:pi_id,area_code=:area_code,pda_id=:pda_id,mac=:mac,ctime=:ctime,utime=:utime,note=:note,loginname=:loginname,password=:password,state=:state WHERE pui_id=:pui_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Pda_userinfo> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Pda_userinfo> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET name=?,age=?,sex=?,address=?,tel=?,link_tel=?,pi_id=?,area_code=?,pda_id=?,mac=?,ctime=?,utime=?,note=?,loginname=?,password=?,state=? WHERE pui_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Pda_userinfo bean = beans.get(i);
                    ps.setString(1, bean.name);
                    ps.setInt(2, bean.age);
                    ps.setInt(3, bean.sex);
                    ps.setString(4, bean.address);
                    ps.setString(5, bean.tel);
                    ps.setString(6, bean.link_tel);
                    ps.setLong(7, bean.pi_id);
                    ps.setString(8, bean.area_code);
                    ps.setLong(9, bean.pda_id);
                    ps.setString(10, bean.mac);
                    ps.setTimestamp(11, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(12, new Timestamp(bean.utime.getTime()));
                    ps.setString(13, bean.note);
                    ps.setString(14, bean.loginname);
                    ps.setString(15, bean.password);
                    ps.setInt(16, bean.state);
                    ps.setLong(17, bean.pui_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long pui_id) throws SQLException{
        return deleteByKey(pui_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long pui_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE pui_id=:pui_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pui_id", pui_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE pui_id=?";
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
                 "	`pui_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`name`  VARCHAR(100) COMMENT '//varchar(100)    姓名'," +
                 "	`age`  INT(11) COMMENT '//int(11)    年龄'," +
                 "	`sex`  INT(11) COMMENT '//int(11)    性别0:保密1：男2：女'," +
                 "	`address`  VARCHAR(100) COMMENT '//varchar(100)    住址'," +
                 "	`tel`  VARCHAR(20) COMMENT '//varchar(20)    手机号码'," +
                 "	`link_tel`  VARCHAR(20) COMMENT '//varchar(20)    紧急联系人手机号码'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    管理的停车场主键ID'," +
                 "	`area_code`  VARCHAR(60) COMMENT '//varchar(60)    停车场地址区域编码'," +
                 "	`pda_id`  BIGINT(20) COMMENT '//bigint(20)    管理的PDA基本信息表主键ID'," +
                 "	`mac`  VARCHAR(60) COMMENT '//varchar(60)    管理的PDA物理地址'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	`loginname`  VARCHAR(100) COMMENT '//varchar(100)    登录帐号'," +
                 "	`password`  VARCHAR(100) COMMENT '//varchar(100)    密码'," +
                 "	`state`  INT(11) COMMENT '//int(11)    是否锁定(0:正常1：锁定关闭登录)'," +
                 "	PRIMARY KEY (`pui_id`)" +
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
