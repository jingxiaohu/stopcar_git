package com.park.dao;

import java.util.List;
import java.util.Map;

import com.park.exception.QzException;

public interface CoreDao{
	/**
	 * 执行一条SQL语句
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public String executeSQL(String sql) throws QzException;
	/**
	 * 执行一条不需要返回结果的sql语句
	 */
	public void executeNoRsSQL(String sql) throws QzException;
	/**
	 * 批量执行多条SQL
	 * @param sqls
	 * @throws QzException
	 */
	public void executeBatchSQL(List<String> sqls) throws QzException;
	
	public List<Map<String, Object>> executeSQLForList(String sql) throws QzException;
	
	public List<String> getFields(String table) throws QzException;
}
