
package stopcar.test.action;

import com.park.constants.Constants;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

/**
 * 检查PDA版本
 *
 * @author jingxiaohu
 */
public class Read_PDA_VersionActionTest extends BaseActionTest {

  /**
   * 检查PDA版本
   *
   * {"data":{"content":"更新了XXX","is_forced":0,"md5":"ewr3134e1","type":"pda","update":1,"url":"http://inner.xx.com/file/xxx-release.apk","version":"v2.0","versioncode":2},"errorcode":"","errorno":"0"}
   */
  @Test
  public void pda_gainupgrade() throws Exception {
    String url = BaseUrl + "pda_gainupgrade.php";
    PostMethod post = new UTF8PostMethod(url);
    //设置公共参数 川A4M99B
    setPublicParam(post);

    int versioncode = 3;//版本内部编号
    String mac = "869612021322674";
    sign = getSignature(Constants.SYSTEM_KEY, mac, versioncode);

    post.addParameter("versioncode", versioncode + "");
    post.addParameter("mac", mac);

    post.addParameter("sign", sign);
    int xx = HC.executeMethod(post);
    System.out.println("*请求状态code=" + xx);
    try {
      String ds = post.getResponseBodyAsString();
      if (ds == null) {
        System.out.println("无数据返回");
      } else {
        System.out.println(new String(post.getResponseBodyAsString().getBytes("utf-8"), "utf-8"));
      }
    } catch (Exception e) {
      e.printStackTrace();
      log.error("read_expect_order is error" + e.getMessage(), e);
    } finally {
      if (post != null) {
        post.releaseConnection();
        //释放链接
        HC.getHttpConnectionManager().closeIdleConnections(0);
      }
    }
  }


}
