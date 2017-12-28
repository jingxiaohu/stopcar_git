package com.park.server;

import com.park.BaseTest;
import com.park.service.ETCHelper;
import com.park.service.etc.etcapi.ETCResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试ETC
 *
 * 测试数据：
 * <pre>
 * 姓名	  身份证号	            银行卡号	            用户号	          备注
 * 苏二五	23237219670916321X	6227001149010179148	100000000011289	帐户控制状态不允许支取，圈存失败
 * 李八二	513840196401287624	6227003813650083625	100000000011290	能交易成功
 * </pre>
 *
 * @author Peter Wu
 */
public class ETCHelperTest extends BaseTest {

  private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
  @Autowired
  ETCHelper etcHelper;

  /**
   * @return 生成16位UUID
   */
  public String return16UUID() {
    return dateFormat.format(new Date()) + RandomStringUtils.randomNumeric(12);
  }

  /**
   * 测试签约
   */
  @Test
  public void toregister() throws Exception {
    ETCResponse response = etcHelper
        .toregister(return16UUID(), 0, "100000000011289", "苏二五", "23237219670916321X",
            "6227001149010179148");
    System.err.println(response);
    Assert.assertEquals(true, response.isSucceed());
  }


  /**
   * 测试解约
   */
  @Test
  public void tounregister() throws Exception {
    ETCResponse response = etcHelper
        .toregister(return16UUID(), 1, "100000000011289", "苏二五", "23237219670916321X",
            "6227001149010179148");
    System.err.println(response);
    Assert.assertEquals(true, response.isSucceed());
  }

  /**
   * 测试签约
   */
  @Test
  public void toregister2() throws Exception {
    ETCResponse response = etcHelper
        .toregister(return16UUID(), 0, "100000000011290", "李八二", "513840196401287624",
            "6227003813650083625");
    System.err.println(response);
    Assert.assertEquals(true, response.isSucceed());
  }

  /**
   * 测试交易 预计失败
   */
  @Test
  public void toropefail() throws Exception {
    ETCResponse response = etcHelper
        .torope(return16UUID(), "100000000011289", "苏二五", "23237219670916321X",
            "6227001149010179148", 1);
    System.err.println(response);
    Assert.assertEquals(false, response.isSucceed());
  }

  /**
   * 测试交易 预计失败
   */
  @Test
  public void toropefail2() throws Exception {
    ETCResponse toregister = etcHelper
        .toregister(return16UUID(), 1, "100000000011290", "李八二", "513840196401287624",
            "6227003813650083625");
    System.err.println(toregister);
    ETCResponse response = etcHelper
        .torope(return16UUID(), "100000000011290", "李八二", "513840196401287624",
            "6227003813650083625", 1);
    System.err.println(response);
    Assert.assertEquals(false, response.isSucceed());
  }

  /**
   * 测试交易 预计成功
   */
  @Test
  public void toropesuccess() throws Exception {
//    toregister2();
    ETCResponse response = etcHelper
        .torope(return16UUID(), "100000000011290", "李八二", "513840196401287624",
            "6227003813650083625", 1);
    System.err.println(response);
    Assert.assertEquals(true, response.isSucceed());
  }
  /**
   * 测试冲正
   */
  @Test
  public void strikebal() throws Exception {
//    toregister2();
    ETCResponse response = etcHelper
        .strikebal(return16UUID(), "20170525", "2017610708375856", 1);
    System.err.println(response);
    Assert.assertEquals(true, response.isSucceed());
  }
}
