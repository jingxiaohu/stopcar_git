package com.park.service;

import com.park.exception.QzException;
import java.util.List;
import java.util.Map;

public interface CoreService {

  /**
   * 执行一条SQL语句
   */
  public String executeSQL(String sql) throws QzException;

  /**
   * 执行一条不需要返回结果的sql语句
   */
  public void executeNoRsSQL(String sql) throws QzException;

  /**
   * 批量执行多条SQL
   */
  public void executeBatchSQL(List<String> sqls) throws QzException;


  public List<Map<String, Object>> executeSQLForList(String sql) throws QzException;
}
