package com.park.service;

import com.park.dao.CoreDao;
import com.park.exception.QzException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoreServiceImpl implements CoreService {

  @Autowired
  private CoreDao coreDao;

  /**
   * 执行一条SQL语句
   */
  public String executeSQL(String sql) throws QzException {
    return this.coreDao.executeSQL(sql);
  }

  /**
   * 执行一条不需要返回结果的sql语句
   */
  public void executeNoRsSQL(String sql) throws QzException {
    this.coreDao.executeNoRsSQL(sql);
  }

  public void executeBatchSQL(List<String> sqls) throws QzException {
    this.coreDao.executeBatchSQL(sqls);
  }

  public List<Map<String, Object>> executeSQLForList(String sql)
      throws QzException {
    return this.coreDao.executeSQLForList(sql);
  }
}
