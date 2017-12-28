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

//park_userinfo

@Repository("park_userinfoDao")
public class Park_userinfoDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Park_userinfoDao.class);



    private  String TABLE = "park_userinfo";

    private  String TABLENAME = "park_userinfo";

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


    private  String[] carrays ={"pu_id","nd","name","tel","bank_no","bank_name","bank_sub_name","pda_num","money","ctime","utime","loginname","password","note","money_online","money_offline","kickback","privilege_id","error_count","error_date","clearing_type","clearing_percentage","clearing_base"};
    private  String coulmns ="pu_id,nd,name,tel,bank_no,bank_name,bank_sub_name,pda_num,money,ctime,utime,loginname,password,note,money_online,money_offline,kickback,privilege_id,error_count,error_date,clearing_type,clearing_percentage,clearing_base";
    private  String coulmns2 ="nd,name,tel,bank_no,bank_name,bank_sub_name,pda_num,money,ctime,utime,loginname,password,note,money_online,money_offline,kickback,privilege_id,error_count,error_date,clearing_type,clearing_percentage,clearing_base";

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
    public int insert(Park_userinfo bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Park_userinfo bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (nd,name,tel,bank_no,bank_name,bank_sub_name,pda_num,money,ctime,utime,loginname,password,note,money_online,money_offline,kickback,privilege_id,error_count,error_date,clearing_type,clearing_percentage,clearing_base) VALUES (:nd,:name,:tel,:bank_no,:bank_name,:bank_sub_name,:pda_num,:money,:ctime,:utime,:loginname,:password,:note,:money_online,:money_offline,:kickback,:privilege_id,:error_count,:error_date,:clearing_type,:clearing_percentage,:clearing_base)";
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
    public int insert_primarykey(Park_userinfo bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Park_userinfo bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (pu_id,nd,name,tel,bank_no,bank_name,bank_sub_name,pda_num,money,ctime,utime,loginname,password,note,money_online,money_offline,kickback,privilege_id,error_count,error_date,clearing_type,clearing_percentage,clearing_base) VALUES (:pu_id,:nd,:name,:tel,:bank_no,:bank_name,:bank_sub_name,:pda_num,:money,:ctime,:utime,:loginname,:password,:note,:money_online,:money_offline,:kickback,:privilege_id,:error_count,:error_date,:clearing_type,:clearing_percentage,:clearing_base)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Park_userinfo> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Park_userinfo> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (nd,name,tel,bank_no,bank_name,bank_sub_name,pda_num,money,ctime,utime,loginname,password,note,money_online,money_offline,kickback,privilege_id,error_count,error_date,clearing_type,clearing_percentage,clearing_base) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_userinfo bean = beans.get(i);
                    ps.setString(1, bean.nd);
                    ps.setString(2, bean.name);
                    ps.setString(3, bean.tel);
                    ps.setString(4, bean.bank_no);
                    ps.setString(5, bean.bank_name);
                    ps.setString(6, bean.bank_sub_name);
                    ps.setInt(7, bean.pda_num);
                    ps.setInt(8, bean.money);
                    ps.setTimestamp(9, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(10, new Timestamp(bean.utime.getTime()));
                    ps.setString(11, bean.loginname);
                    ps.setString(12, bean.password);
                    ps.setString(13, bean.note);
                    ps.setLong(14, bean.money_online);
                    ps.setLong(15, bean.money_offline);
                    ps.setDouble(16, bean.kickback);
                    ps.setInt(17, bean.privilege_id);
                    ps.setInt(18, bean.error_count);
                    ps.setString(19, bean.error_date);
                    ps.setInt(20, bean.clearing_type);
                    ps.setDouble(21, bean.clearing_percentage);
                    ps.setInt(22, bean.clearing_base);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Park_userinfo> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Park_userinfo> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pu_id,nd,name,tel,bank_no,bank_name,bank_sub_name,pda_num,money,ctime,utime,loginname,password,note,money_online,money_offline,kickback,privilege_id,error_count,error_date,clearing_type,clearing_percentage,clearing_base FROM "+TABLENAME2+" ORDER BY pu_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_userinfo>(Park_userinfo.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Park_userinfo>();
        }
    }

    //查询最新数据
    public List<Park_userinfo> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Park_userinfo> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT pu_id,nd,name,tel,bank_no,bank_name,bank_sub_name,pda_num,money,ctime,utime,loginname,password,note,money_online,money_offline,kickback,privilege_id,error_count,error_date,clearing_type,clearing_percentage,clearing_base FROM "+TABLENAME2+" ORDER BY pu_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Park_userinfo>(Park_userinfo.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Park_userinfo>();
        }
    }

    //根据主键查询
    public List<Park_userinfo> selectGtKey(long pu_id) {
        return selectGtKey(pu_id, TABLENAME);
    }

    //根据主键查询
    public List<Park_userinfo> selectGtKey(long pu_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pu_id,nd,name,tel,bank_no,bank_name,bank_sub_name,pda_num,money,ctime,utime,loginname,password,note,money_online,money_offline,kickback,privilege_id,error_count,error_date,clearing_type,clearing_percentage,clearing_base FROM "+TABLENAME2+" WHERE pu_id>:pu_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pu_id", pu_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Park_userinfo>(Park_userinfo.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Park_userinfo>();
        }
    }

    //根据主键查询
    public Park_userinfo selectByKey(long pu_id) {
        return selectByKey(pu_id, TABLENAME);
    }

    //根据主键查询
    public Park_userinfo selectByKey(long pu_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT pu_id,nd,name,tel,bank_no,bank_name,bank_sub_name,pda_num,money,ctime,utime,loginname,password,note,money_online,money_offline,kickback,privilege_id,error_count,error_date,clearing_type,clearing_percentage,clearing_base FROM "+TABLENAME2+" WHERE pu_id=:pu_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pu_id", pu_id);
            List<Park_userinfo> list =  _np.query(sql, param, new BeanPropertyRowMapper<Park_userinfo>(Park_userinfo.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey pu_id="+pu_id,e);
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
    public List<Park_userinfo> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Park_userinfo> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT pu_id,nd,name,tel,bank_no,bank_name,bank_sub_name,pda_num,money,ctime,utime,loginname,password,note,money_online,money_offline,kickback,privilege_id,error_count,error_date,clearing_type,clearing_percentage,clearing_base FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Park_userinfo>(Park_userinfo.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Park_userinfo>();
        }
    }

    //修改数据
    public int updateByKey(Park_userinfo bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Park_userinfo bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET nd=:nd,name=:name,tel=:tel,bank_no=:bank_no,bank_name=:bank_name,bank_sub_name=:bank_sub_name,pda_num=:pda_num,money=:money,ctime=:ctime,utime=:utime,loginname=:loginname,password=:password,note=:note,money_online=:money_online,money_offline=:money_offline,kickback=:kickback,privilege_id=:privilege_id,error_count=:error_count,error_date=:error_date,clearing_type=:clearing_type,clearing_percentage=:clearing_percentage,clearing_base=:clearing_base WHERE pu_id=:pu_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_userinfo> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Park_userinfo> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET nd=?,name=?,tel=?,bank_no=?,bank_name=?,bank_sub_name=?,pda_num=?,money=?,ctime=?,utime=?,loginname=?,password=?,note=?,money_online=?,money_offline=?,kickback=?,privilege_id=?,error_count=?,error_date=?,clearing_type=?,clearing_percentage=?,clearing_base=? WHERE pu_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Park_userinfo bean = beans.get(i);
                    ps.setString(1, bean.nd);
                    ps.setString(2, bean.name);
                    ps.setString(3, bean.tel);
                    ps.setString(4, bean.bank_no);
                    ps.setString(5, bean.bank_name);
                    ps.setString(6, bean.bank_sub_name);
                    ps.setInt(7, bean.pda_num);
                    ps.setInt(8, bean.money);
                    ps.setTimestamp(9, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(10, new Timestamp(bean.utime.getTime()));
                    ps.setString(11, bean.loginname);
                    ps.setString(12, bean.password);
                    ps.setString(13, bean.note);
                    ps.setLong(14, bean.money_online);
                    ps.setLong(15, bean.money_offline);
                    ps.setDouble(16, bean.kickback);
                    ps.setInt(17, bean.privilege_id);
                    ps.setInt(18, bean.error_count);
                    ps.setString(19, bean.error_date);
                    ps.setInt(20, bean.clearing_type);
                    ps.setDouble(21, bean.clearing_percentage);
                    ps.setInt(22, bean.clearing_base);
                    ps.setLong(23, bean.pu_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long pu_id) throws SQLException{
        return deleteByKey(pu_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long pu_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE pu_id=:pu_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("pu_id", pu_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE pu_id=?";
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
                 "	`pu_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    '," +
                 "	`nd`  VARCHAR(100) COMMENT '//varchar(100)    商户编号'," +
                 "	`name`  VARCHAR(100) COMMENT '//varchar(100)    PDA老板姓名'," +
                 "	`tel`  VARCHAR(11) COMMENT '//varchar(11)    PDA老板手机号码'," +
                 "	`bank_no`  VARCHAR(60) COMMENT '//varchar(60)    PDA老板银行卡卡号'," +
                 "	`bank_name`  VARCHAR(150) COMMENT '//varchar(150)    PDA老板开户行名称'," +
                 "	`bank_sub_name`  TINYTEXT COMMENT '//varchar(255)    PDA老板开户行支行名称'," +
                 "	`pda_num`  INT(11) COMMENT '//int(11)    PDA老板拥有的PDA个数'," +
                 "	`money`  INT(11) COMMENT '//int(11)    商户平台总金额（单位分）'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`loginname`  VARCHAR(60) COMMENT '//varchar(60)    账号'," +
                 "	`password`  VARCHAR(60) COMMENT '//varchar(60)    密码'," +
                 "	`note`  TINYTEXT COMMENT '//varchar(255)    备注'," +
                 "	`money_online`  BIGINT(20) COMMENT '//bigint(20)    线上累计金额'," +
                 "	`money_offline`  BIGINT(20) COMMENT '//bigint(20)    线下累计金额'," +
                 "	`kickback`  DOUBLE COMMENT '//double    打款百分比'," +
                 "	`privilege_id`  INT(11) COMMENT '//int(11)    商户系统菜单权限映射ID'," +
                 "	`error_count`  INT(11) COMMENT '//int(11)    登录错误次数'," +
                 "	`error_date`  VARCHAR(60) COMMENT '//varchar(60)    登录错误发生的日期例如2017-08-07'," +
                 "	`clearing_type`  INT(11) COMMENT '//int(11)    结算方案(0：不结算1：全额结算2：按基数结算)'," +
                 "	`clearing_percentage`  DOUBLE COMMENT '//double    结算比例'," +
                 "	`clearing_base`  INT(11) COMMENT '//int(11)    结算基数(整数单位分)'," +
                 "	PRIMARY KEY (`pu_id`)" +
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
