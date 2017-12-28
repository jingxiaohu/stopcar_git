package com.park.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.park.exception.QzException;

@Repository("coreDao")
public class CoreDaoImpl implements CoreDao {
	//定义JDBC
	@Resource(name="jdbc")
	public  NamedParameterJdbcTemplate sqlSessionFactory;

	/**
	 * 执行一条SQL语句
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public String executeSQL(String sql) throws QzException {
		try {
			List<Map<String, Object>> list = this.executeSQLForList(sql);
			return JSONArray.toJSONString(list);
		} catch (Exception e) {
			throw new QzException("BaseDaoImpl.executeSQL", e);
		}
	}

	/**
	 * 执行一条SQL语句
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> executeSQLForList(String sql)
			throws QzException {
		try {
			return sqlSessionFactory.getJdbcOperations().queryForList(sql); 
		} catch (Exception e) {
			throw new QzException("BaseDaoImpl.executeSQLForList", e);
		}
	}

	/**
	 * 执行一条不需要返回结果的sql语句
	 */
	public void executeNoRsSQL(String sql) throws QzException {
		try {
			sqlSessionFactory.getJdbcOperations().execute(sql);
		} catch (Exception e) {
			throw new QzException("BaseDaoImpl.executeNoRsSQL", e);
		}
	}
	/**
	 * 批量执行多条SQL
	 * @param sqls
	 * @throws QzException
	 */
	public void executeBatchSQL(List<String> sqls) throws QzException {
		if (sqls == null || sqls.size() == 0) {
			return;
		}
		try {
			String[] sql_array = new String[sqls.size()];
			sqlSessionFactory.getJdbcOperations().batchUpdate(sqls.toArray(sql_array));
		} catch (Exception e) {
			throw new QzException("BaseDaoImpl.executeBatchSQL", e);
		}
	}


	/**
	 * 根据表名获取字段名
	 */
	public List<String> getFields(String table) throws QzException {
		List<String> fields =new ArrayList<String>();
		try {
			Map<String,Object> map = sqlSessionFactory.getJdbcOperations().queryForMap("select * from "+table+" limit 1");
			for(Map.Entry<String, Object> st:map.entrySet()){
				fields.add(st.getKey());
			}
		} catch (Exception e) {
			throw new QzException("BaseDaoImpl.getFields", e);
		}
		return fields;
	}
	
	/*@SuppressWarnings("unchecked")
	public void insertTable(String table,HttpServletRequest request) throws QzException{
		SqlSession sqlSession = null;
		try {
			List<String> fields = new ArrayList<String>();//= this.getFields(table);
			sqlSession = sqlSessionFactory.openSession();
			Connection con = sqlSession.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select * from "+table+" limit 1");
			ResultSet rs = pstmt.executeQuery();
			//元数据
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i=1;i<=rsmd.getColumnCount();i++){
				//获取字段名
				String field = rsmd.getColumnName(i);
				fields.add(field);
			}
			Map<String,String> rMap = request.getParameterMap();
			String keys="";
			String values="";
			for(String field :fields){
				if(!"".equals(keys))
					keys+=",";
				keys+=field;
				if(!"".equals(values))
					values+=",";
				if(rMap.containsKey(field)){
					values+="'"+rMap.get(field)+"'";
				}else{
					values+="null";
				}
			}
			String sql = "insert into "+table+" ("+keys+") values ("+values+")";
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			throw new QzException("BaseDaoImpl.getFields", e);
		}finally{
			if(sqlSession != null)
				sqlSession.close();
		}
	}*/
}
