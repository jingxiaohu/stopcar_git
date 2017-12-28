package com.park.server;

import com.alibaba.fastjson.JSON;
import com.park.BaseTest;
import com.park.service.LZFHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试验证订单支付账号
 *
 * @author Peter Wu
 */
public class LZFHelperTest extends BaseTest {

  @Autowired
  LZFHelper lzfHelper;

  /**
   * 测试验证订单支付账号
   */
  @Test
  public void accountCheck() throws Exception {
    boolean accountCheck = lzfHelper.accountCheck("2017042715007541", "ddd");
    System.err.println(accountCheck);
  }

  /**
   * 查询订单信息
   */
  @Test
  public void info() throws Exception {
    System.err.println(JSON.toJSONString(lzfHelper.info("2017062715503536763"),true));
  }
}
