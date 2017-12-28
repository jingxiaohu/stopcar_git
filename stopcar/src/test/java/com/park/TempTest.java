package com.park;

import com.park.util.IDCardValidator;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.RandomStringUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Peter Wu
 */
public class TempTest {

  @Test
  public void notify_url() throws Exception {
    String notify_url="notify_lzf.php?SIGN=5afcfbf0b5f4a0d1d7625afa032e856835b380509843336e482f4f1b435d1b4c77668e22d015a7ca50cdf0e1ee35d7083fb3ad9cc5ea4838b1ffc2d3e6d6b792a628326ec9c4716586a356aa1df05c2298711908ed64757a4d08633255c088c28c91cdca0b2820a3df7cafaf9f1018ebc3f6bbc6f82c0f441e705a77e32cbc17&REMARK1=&CLIENTIP=222.209.35.145&BRANCHID=510000000&REMARK2=http://127.0.0.1:8080/stopcar/v1/notify_lzf_test.php&SUCCESS=Y&ACC_TYPE=02&CURCODE=01&ORDERID=2017061413305965534&POSID=426295203&REFERER=&PAYMENT=2.40&TYPE=1";
    System.err.println(notify_url.replaceAll(notify_url.replaceAll("^.*&REMARK2=(.*?)&.*$","$1"),""));
  }

  @Test
  public void name() throws Exception {
    String tt = "<?xml version='1.0' encoding='UTF-8'?><data><message><status>0</status><value>比对完成</value></message><cardsinfo><card type=\"21\"><item desc=\"判定值\"><![CDATA[0.98207855]]></item><item desc=\"判定结果\"><![CDATA[是]]></item></card></cardsinfo></data>";
    Document document = new SAXReader().read(new StringReader(tt));

    if (document != null) {
      String status = document.selectSingleNode(
          "//status").getText();
      System.err.println(status);
      String item1 = document.selectSingleNode(
          "//item[1]").getText();
      System.err.println(item1);
      String item2 = document.selectSingleNode(
          "//item[2]").getText();
      System.err.println(item2);

    }
    // do something with name
    System.err.println();
  }

  @Test
  public void randomNumericOrderNo() throws Exception {
    final ExecutorService service = Executors.newFixedThreadPool(1000);
    final ConcurrentLinkedDeque<String> arr = new ConcurrentLinkedDeque<>();
    for (int i = 0; i < 10000000; i++) {
      if (service.isShutdown()) {
        break;
      }
      service.execute(new Runnable() {
        @Override
        public void run() {
          String yyyymmdd =
              new SimpleDateFormat("yyyy").format(new Date()) + RandomStringUtils.randomNumeric(12);
//          String yyyymmdd = System.currentTimeMillis() + RandomStringUtils.randomNumeric(3);
          if (arr.contains(yyyymmdd)) {
            System.err.println(arr.size() + ":" + yyyymmdd);
            service.shutdownNow();
          }
          arr.add(yyyymmdd);
        }
      });
    }
  }

  /**
   * 验证身份证号
   *
   * 23237219670916321X
   * 513840196401287624
   */
  @Test
  public void idcardV() throws Exception {
    Assert.assertEquals(true, IDCardValidator.validate("23237219670916321X"));
    Assert.assertEquals(true, IDCardValidator.validate("513840196401287624"));
    Assert.assertEquals(false, IDCardValidator.validate("4208041981051264991"));
  }

  @Test
  public void testCarCode() throws Exception {
    String carcode = "川EB0197";
    char[] chars = carcode.toCharArray();
    StringBuilder ss = new StringBuilder();
    for (char c : chars) {
      if (Integer.toHexString(c).length() > 2) {

      }
      System.err.println();
    }
  }
}
