package com.park.action;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Peter Wu
 */
@RestController
public class TestContrl {

  @RequestMapping(value = "/notify_lzf_test", method = RequestMethod.POST)
  public Object test(HttpServletRequest request) {
    System.err.println("params:"+request.getQueryString());
    return "请求成功";
  }
}
