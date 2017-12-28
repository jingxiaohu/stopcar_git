package com.park.interceptor;

import com.park.bean.Request_params_log;
import com.park.dao.Request_params_logDao;
import java.sql.SQLException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RequestBiz {

  private Logger log = LoggerFactory.getLogger(RequestBiz.class);
  @Autowired
  Request_params_logDao request_params_logDao;

  /**
   * 记录客户端请求日志和服务器端写回的数据
   */
  @Async
  public void insertRequestParams(long duration, String RequestURI, String requestParam,
      String responseData, String ip) {
    /**
     * 这里记录该次请求参数
     */
    try {
      if (log.isDebugEnabled()) {
        log.debug("记录该次请求参数---");
      }
      Request_params_log request_params_log = new Request_params_log();
      request_params_log.setCtime(new Date());
      request_params_log.setDuration(duration);
      request_params_log.setMethod_name(RequestURI);
      request_params_log.setParams_json(requestParam);
      request_params_log.setReponse_json(responseData);
      request_params_log.setNote(ip);
      request_params_logDao.insert(request_params_log);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
    }
  }
}
