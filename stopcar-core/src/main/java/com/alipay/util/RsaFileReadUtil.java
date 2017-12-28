/**
 * @Title: RsaFileReadUtil.java
 * @Package com.alipay.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 敬小虎
 * @date 2015年3月16日 上午11:54:02
 * @version V1.0
 */
package com.alipay.util;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.commons.io.FileUtils;


/**
 * @ClassName: RsaFileReadUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 敬小虎
 * @date 2015年3月16日 上午11:54:02
 *
 */
public class RsaFileReadUtil {

  /**
   * 读取密钥字符串
   * @Title: ReadPrivateKey
   * @Description: TODO(这里用一句话描述这个方法的作用)
   * @param @param filepath
   * @param @return
   * @param @throws Exception    设定文件
   * @return String    返回类型
   * @throws
   */
  public static String ReadPrivateKey(String filepath) throws Exception {
    StringBuilder sb = new StringBuilder();
    File file = new File(filepath);
    List<String> list = FileUtils.readLines(file, Charset.forName("UTF-8"));
    if (list != null && list.size() > 0) {
      for (String str : list) {
        if (!str.contains("BEGIN PRIVATE KEY") && !str.contains("END PRIVATE KEY")) {
          sb.append(str);
        }
      }
    }

    return sb.toString();
  }

//   public static void main(String[] args) throws Exception {
//	   System.out.println(ReadPrivateKey("F:\\敬小虎工作目录\\intimes平台数据库设计DB\\doc\\支付宝密钥\\rsa_private_key_pkcs8.txt"));
//   }

}
