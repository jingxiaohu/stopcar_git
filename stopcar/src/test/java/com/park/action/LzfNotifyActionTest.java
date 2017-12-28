package com.park.action;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import stopcar.test.action.BaseActionTest;

/**
 * @author Peter Wu
 */
public class LzfNotifyActionTest extends BaseActionTest {

  @Test
  public void lzfnotify() throws Exception {
    String object = new RestTemplate().postForObject(
        BaseUrl+"notify_lzf.php?SIGN=5afcfbf0b5f4a0d1d7625afa032e856835b380509843336e482f4f1b435d1b4c77668e22d015a7ca50cdf0e1ee35d7083fb3ad9cc5ea4838b1ffc2d3e6d6b792a628326ec9c4716586a356aa1df05c2298711908ed64757a4d08633255c088c28c91cdca0b2820a3df7cafaf9f1018ebc3f6bbc6f82c0f441e705a77e32cbc17&REMARK1=&CLIENTIP=222.209.35.145&BRANCHID=510000000&REMARK2="+BaseUrl+"notify_lzf_test.php"+"&SUCCESS=Y&ACC_TYPE=02&CURCODE=01&ORDERID=2017061413305965534&POSID=426295203&REFERER=&PAYMENT=2.40&TYPE=1",
       "", String.class);
    System.err.println(object);
  }
}
