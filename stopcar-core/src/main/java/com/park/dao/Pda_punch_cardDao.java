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

//pda_punch_card

@Repository("pda_punch_cardDao")
public class Pda_punch_cardDao extends BaseDao{

    Logger log = LoggerFactory.getLogger(Pda_punch_cardDao.class);



    private  String TABLE = "pda_punch_card";

    private  String TABLENAME = "pda_punch_card";

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


    private  String[] carrays ={"ppc_id","nd","pi_id","area_code","pu_id","pu_nd","mac","type","ontime","offtime","loginname","is_clearing","ctime","utime","worker_name","worker_no","pda_user_id","note"};
    private  String coulmns ="ppc_id,nd,pi_id,area_code,pu_id,pu_nd,mac,type,ontime,offtime,loginname,is_clearing,ctime,utime,worker_name,worker_no,pda_user_id,note";
    private  String coulmns2 ="nd,pi_id,area_code,pu_id,pu_nd,mac,type,ontime,offtime,loginname,is_clearing,ctime,utime,worker_name,worker_no,pda_user_id,note";

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
    public int insert(Pda_punch_card bean) throws SQLException{
        return insert(bean, TABLENAME);
    }

    //添加数据
    public int insert(Pda_punch_card bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (nd,pi_id,area_code,pu_id,pu_nd,mac,type,ontime,offtime,loginname,is_clearing,ctime,utime,worker_name,worker_no,pda_user_id,note) VALUES (:nd,:pi_id,:area_code,:pu_id,:pu_nd,:mac,:type,:ontime,:offtime,:loginname,:is_clearing,:ctime,:utime,:worker_name,:worker_no,:pda_user_id,:note)";
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
    public int insert_primarykey(Pda_punch_card bean) throws SQLException{
        return insert_primarykey(bean, TABLENAME);
    }

    //添加数据
    public int insert_primarykey(Pda_punch_card bean, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (ppc_id,nd,pi_id,area_code,pu_id,pu_nd,mac,type,ontime,offtime,loginname,is_clearing,ctime,utime,worker_name,worker_no,pda_user_id,note) VALUES (:ppc_id,:nd,:pi_id,:area_code,:pu_id,:pu_nd,:mac,:type,:ontime,:offtime,:loginname,:is_clearing,:ctime,:utime,:worker_name,:worker_no,:pda_user_id,:note)";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("insert_primarykey", e);
            throw new SQLException("insert2 is error", e);
        }
    }

    //批量添加数据
    public int[] insert(List<Pda_punch_card> beans) throws SQLException{
        return insert(beans, TABLENAME);
    }

    //批量添加数据
    public int[] insert(final List<Pda_punch_card> beans, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "INSERT INTO "+TABLENAME2+" (nd,pi_id,area_code,pu_id,pu_nd,mac,type,ontime,offtime,loginname,is_clearing,ctime,utime,worker_name,worker_no,pda_user_id,note) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Pda_punch_card bean = beans.get(i);
                    ps.setString(1, bean.nd);
                    ps.setLong(2, bean.pi_id);
                    ps.setString(3, bean.area_code);
                    ps.setLong(4, bean.pu_id);
                    ps.setString(5, bean.pu_nd);
                    ps.setString(6, bean.mac);
                    ps.setInt(7, bean.type);
                    ps.setString(8, bean.ontime);
                    ps.setString(9, bean.offtime);
                    ps.setString(10, bean.loginname);
                    ps.setInt(11, bean.is_clearing);
                    ps.setTimestamp(12, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(13, new Timestamp(bean.utime.getTime()));
                    ps.setString(14, bean.worker_name);
                    ps.setString(15, bean.worker_no);
                    ps.setLong(16, bean.pda_user_id);
                    ps.setString(17, bean.note);
                }
            });
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("int[] insert", e);
            throw new SQLException("insert is error", e);
        }
    }

    //查询所有数据
    public List<Pda_punch_card> selectAll() {
        return selectAll(TABLENAME);
    }

    //查询所有数据
    public List<Pda_punch_card> selectAll(String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT ppc_id,nd,pi_id,area_code,pu_id,pu_nd,mac,type,ontime,offtime,loginname,is_clearing,ctime,utime,worker_name,worker_no,pda_user_id,note FROM "+TABLENAME2+" ORDER BY ppc_id";
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Pda_punch_card>(Pda_punch_card.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectAll", e);
            return new ArrayList<Pda_punch_card>();
        }
    }

    //查询最新数据
    public List<Pda_punch_card> selectLast(int num) {
        return selectLast(num, TABLENAME);
    }

    //查询所有数据
    public List<Pda_punch_card> selectLast(int num ,String TABLENAME2) {
        String sql;
        try{
            sql = "SELECT ppc_id,nd,pi_id,area_code,pu_id,pu_nd,mac,type,ontime,offtime,loginname,is_clearing,ctime,utime,worker_name,worker_no,pda_user_id,note FROM "+TABLENAME2+" ORDER BY ppc_id DESC LIMIT "+num+"" ;
            return _np.getJdbcOperations().query(sql, new BeanPropertyRowMapper<Pda_punch_card>(Pda_punch_card.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectLast", e);
            return new ArrayList<Pda_punch_card>();
        }
    }

    //根据主键查询
    public List<Pda_punch_card> selectGtKey(long ppc_id) {
        return selectGtKey(ppc_id, TABLENAME);
    }

    //根据主键查询
    public List<Pda_punch_card> selectGtKey(long ppc_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT ppc_id,nd,pi_id,area_code,pu_id,pu_nd,mac,type,ontime,offtime,loginname,is_clearing,ctime,utime,worker_name,worker_no,pda_user_id,note FROM "+TABLENAME2+" WHERE ppc_id>:ppc_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("ppc_id", ppc_id);
            return _np.query(sql, param, new BeanPropertyRowMapper<Pda_punch_card>(Pda_punch_card.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectGtKey", e);
            return new ArrayList<Pda_punch_card>();
        }
    }

    //根据主键查询
    public Pda_punch_card selectByKey(long ppc_id) {
        return selectByKey(ppc_id, TABLENAME);
    }

    //根据主键查询
    public Pda_punch_card selectByKey(long ppc_id, String TABLENAME2) {
        String sql;
        try{
            sql="SELECT ppc_id,nd,pi_id,area_code,pu_id,pu_nd,mac,type,ontime,offtime,loginname,is_clearing,ctime,utime,worker_name,worker_no,pda_user_id,note FROM "+TABLENAME2+" WHERE ppc_id=:ppc_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("ppc_id", ppc_id);
            List<Pda_punch_card> list =  _np.query(sql, param, new BeanPropertyRowMapper<Pda_punch_card>(Pda_punch_card.class));
            return (list == null || list.size() == 0) ? null : list.get(0);
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByKey ppc_id="+ppc_id,e);
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
    public List<Pda_punch_card> selectByPage(int begin, int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    //分页查询
    public List<Pda_punch_card> selectByPage(int begin, int num, String TABLENAME2) {
        try{
            String sql;
            sql = "SELECT ppc_id,nd,pi_id,area_code,pu_id,pu_nd,mac,type,ontime,offtime,loginname,is_clearing,ctime,utime,worker_name,worker_no,pda_user_id,note FROM "+TABLENAME2+" LIMIT "+begin+", "+num+"";
            return _np.getJdbcOperations().query(sql,new BeanPropertyRowMapper<Pda_punch_card>(Pda_punch_card.class));
        }catch(Exception e){
            //createTable(TABLENAME2);
            log.error("selectByPage",e);
            return new ArrayList<Pda_punch_card>();
        }
    }

    //修改数据
    public int updateByKey(Pda_punch_card bean) {
        return updateByKey(bean, TABLENAME);
    }

    //修改数据
    public int updateByKey(Pda_punch_card bean, String TABLENAME2) {
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET nd=:nd,pi_id=:pi_id,area_code=:area_code,pu_id=:pu_id,pu_nd=:pu_nd,mac=:mac,type=:type,ontime=:ontime,offtime=:offtime,loginname=:loginname,is_clearing=:is_clearing,ctime=:ctime,utime=:utime,worker_name=:worker_name,worker_no=:worker_no,pda_user_id=:pda_user_id,note=:note WHERE ppc_id=:ppc_id";
            SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
            return _np.update(sql, ps);
        }catch(Exception e){
            log.error("updateByKey",e);
            return 0;
        }
    }

    //批量修改数据
    public int[] updateByKey (final List<Pda_punch_card> beans) throws SQLException{
        return updateByKey(beans, TABLENAME);
    }

    //批量修改数据
    public int[] updateByKey (final List<Pda_punch_card> beans, String TABLENAME2) throws SQLException{
        try{
            String sql;
            sql = "UPDATE "+TABLENAME2+" SET nd=?,pi_id=?,area_code=?,pu_id=?,pu_nd=?,mac=?,type=?,ontime=?,offtime=?,loginname=?,is_clearing=?,ctime=?,utime=?,worker_name=?,worker_no=?,pda_user_id=?,note=? WHERE ppc_id=?";
            return _np.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
                //@Override
                public int getBatchSize() {
                    return beans.size();
                }
                //@Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Pda_punch_card bean = beans.get(i);
                    ps.setString(1, bean.nd);
                    ps.setLong(2, bean.pi_id);
                    ps.setString(3, bean.area_code);
                    ps.setLong(4, bean.pu_id);
                    ps.setString(5, bean.pu_nd);
                    ps.setString(6, bean.mac);
                    ps.setInt(7, bean.type);
                    ps.setString(8, bean.ontime);
                    ps.setString(9, bean.offtime);
                    ps.setString(10, bean.loginname);
                    ps.setInt(11, bean.is_clearing);
                    ps.setTimestamp(12, new Timestamp(bean.ctime.getTime()));
                    ps.setTimestamp(13, new Timestamp(bean.utime.getTime()));
                    ps.setString(14, bean.worker_name);
                    ps.setString(15, bean.worker_no);
                    ps.setLong(16, bean.pda_user_id);
                    ps.setString(17, bean.note);
                    ps.setLong(18, bean.ppc_id);
                }
            });
        }catch(Exception e){
            log.error("int[] updateByKey",e);
            throw new SQLException("updateByKey is error", e);
        }
    }

    //删除单条数据
    public int deleteByKey(long ppc_id) throws SQLException{
        return deleteByKey(ppc_id, TABLENAME);
    }

    //删除单条数据
    public int deleteByKey(long ppc_id, String TABLENAME2) throws SQLException{
        String sql;
        try{
            sql = "DELETE FROM "+TABLENAME2+" WHERE ppc_id=:ppc_id";
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("ppc_id", ppc_id);
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
            sql = "DELETE FROM "+TABLENAME2+" WHERE ppc_id=?";
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
                 "	`ppc_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '//bigint(20)    主键ID'," +
                 "	`nd`  VARCHAR(80) COMMENT '//varchar(80)    唯一标识符ND'," +
                 "	`pi_id`  BIGINT(20) COMMENT '//bigint(20)    PDA停车场主键ID'," +
                 "	`area_code`  VARCHAR(30) COMMENT '//varchar(30)    地区地址编码'," +
                 "	`pu_id`  BIGINT(20) COMMENT '//bigint(20)    商户主键ID'," +
                 "	`pu_nd`  VARCHAR(80) COMMENT '//varchar(80)    商户ND'," +
                 "	`mac`  VARCHAR(100) COMMENT '//varchar(100)    PDA的MAC地址'," +
                 "	`type`  INT(11) COMMENT '//int(11)    打卡类型0：上班打卡1：下班打卡'," +
                 "	`ontime`  VARCHAR(30) COMMENT '//varchar(30)    上班时间（9：00）'," +
                 "	`offtime`  VARCHAR(30) COMMENT '//varchar(30)    下班时间（20：00）'," +
                 "	`loginname`  VARCHAR(100) COMMENT '//varchar(100)    当前PDA登录帐号'," +
                 "	`is_clearing`  INT(11) COMMENT '//int(11)    是否进行了清算（如果是上班则是上班清算如果是下班则是下班清算）0:未清算1：已经清算'," +
                 "	`ctime`  DATETIME COMMENT '//datetime    创建时间'," +
                 "	`utime`  DATETIME COMMENT '//datetime    更新时间'," +
                 "	`worker_name`  VARCHAR(60) COMMENT '//varchar(60)    PDA管理人姓名'," +
                 "	`worker_no`  VARCHAR(60) COMMENT '//varchar(60)    PDA管理人编号'," +
                 "	`pda_user_id`  BIGINT(20) COMMENT '//bigint(20)    PDA商户旗下员工管理表主键ID'," +
                 "	`note`  VARCHAR(100) COMMENT '//varchar(100)    备注'," +
                 "	PRIMARY KEY (`ppc_id`)" +
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
