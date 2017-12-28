package com.park.server;

import com.park.action.BaseWebTest;
import com.park.service.CxfServerXHelper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

/**
 * 文通图像识别辅助类测试
 *
 * @author Peter Wu
 */
public class CxfServerXHelperTest extends BaseWebTest {

  @Autowired
  CxfServerXHelper cxfServerXHelper;

  @Test
  public void doFaceRecon() throws Exception {
    String path = new ClassPathResource("face1.jpg").getFile().getAbsolutePath();
    System.err.println(path);
    Object result = cxfServerXHelper.doFaceRecon("123", path, path);
    System.err.println(result);
  }

  @Test
  public void faceplusplusCompare() throws Exception {
    String path = new ClassPathResource("face1.jpg").getFile().getAbsolutePath();
    System.err.println(path);
    Object result = cxfServerXHelper.faceplusplusCompare(path, path);
    System.err.println(result);
    Assert.assertEquals(true, result);
  }

  @Test
  public void faceplusplusCompareFalse() throws Exception {
    String path1 = new ClassPathResource("face/face1.jpg").getFile().getAbsolutePath();
    String path2 = new ClassPathResource("face/face2.jpg").getFile().getAbsolutePath();
    System.err.println(path1);
    System.err.println(path2);
    Object result = cxfServerXHelper.faceplusplusCompare(path1, path2);
    System.err.println(result);
    Assert.assertEquals(false, result);
  }
}
