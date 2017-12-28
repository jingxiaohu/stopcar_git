
package stopcar.test.action;

import com.park.constants.Constants;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户 ETC注册  修改ETC注册信息
 *
 * @author jingxiaohu
 */
public class Write_ETC_UserRegisterActionTest extends BaseActionTest {

  /**
   * 扈米斯(478335382)  11:00:28 ETC项目采用第二种方案，就是钱包和银行卡快捷支付相互融合 另外如果钱包里面用费用就先使用钱包里面的费用，然后才使用银行卡快捷支付
   *
   * 用户ETC注册
   *
   * 响应示例：
   * <pre>
   * </pre>
   */
  @Test
  public void etc_user_reg() throws Exception {
    String url = BaseUrl + "etc_user_reg.php";
    PostMethod post = new UTF8PostMethod(url);
    //设置公共参数 '/^([1-9]{1})(\d{14}|\d{18})$/
    setPublicParam(post);
    String tel = "15882345446";
    ;//用户手机号码
    String name = "王小刚";//ETC用户真实姓名
    String sfz_number = "510324198832497533";//ETC用户真实身份证号码
    String bank_card_number = "6228482938412183773";//ETC用户银行卡卡号
    Integer bank_type = 0;//银行类型（0：建行银行）

//    //用户身份证图片文件
//    MultipartFile cardimg;
//    //提交过来的file的名字
//    String cardimgFileName = "sdfs.jpg";
//    //提交过来的file的MIME类型
//    String cardimgContentType = "jpg";

	 int pay_type=5;//支付类型 1:支付宝  2：微信  3：建行银联  4：钱包 5:龙支付 
	 int pay_price=1;//充值金额 单位 分
	 long t = System.currentTimeMillis();
	 int version=1;//当前版本编号
	 String subject="建行签约";//商品名称
	 int system_type=1;//操作系统类型（IOS Android web） 1:android 2:IOS 3:web   4:PDA
	 String token="0b5427ae6874b1a3e2dd9f1e30172c16";//令牌
	//收银台页面上，商品展示的超链接，必填
	 String show_url ="";
	// 页面跳转同步通知页面路径
	 String return_url = "";
	 int type=1;//是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付
	
    sign = getSignature(Constants.SYSTEM_KEY, dtype, ui_id, sfz_number, bank_card_number);

    post.addParameter("tel", tel);
    post.addParameter("name", name);
    post.addParameter("sfz_number", sfz_number);
    post.addParameter("bank_card_number", bank_card_number);
    post.addParameter("bank_type", String.valueOf(bank_type));
    
    post.addParameter("pay_type", pay_type+"");
	post.addParameter("pay_price", pay_price+"");
	post.addParameter("version", version+"");
	post.addParameter("subject", subject+"");
	post.addParameter("system_type", system_type+"");
	post.addParameter("t", t+"");
	post.addParameter("token", token);
	post.addParameter("show_url", show_url);
	post.addParameter("return_url", return_url);
	post.addParameter("type", type+"");

    post.addParameter("sign", sign);

    List<Part> params = new ArrayList<>();
    for (NameValuePair nameValuePair : post.getParameters()) {
      params.add(new StringPart(nameValuePair.getName(), nameValuePair.getValue(), "UTF-8"));
    }
//    params.add(new FilePart("cardimg", new ClassPathResource("image.png").getFile()));
    post.setRequestEntity(
        new MultipartRequestEntity(params.toArray(new Part[params.size()]), post.getParams()));
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
      log.error("change_pass is error" + e.getMessage(), e);
    } finally {
      if (post != null) {
        post.releaseConnection();
        //释放链接
        HC.getHttpConnectionManager().closeIdleConnections(0);
      }
    }
  }

  /**
   * 用户修改ETC资料
   * 响应例子：
   * <pre>
   *   {"data":{"bank_card_number":"6228482938412183773","bank_type":0,"ctime":1493102652000,"discard_time":1493102652000,"eu_id":3,"eu_nd":"2017042514445156","is_default":1,"is_sign":0,"name":"王小刚2","note":"","sfz_img_url":"http://app.qc-wbo.com/file/img/card/2017/head15882345446_63732.png","sfz_number":"510324198832497533","sign_ip":"","signtime":1493102652000,"ui_id":142,"ui_nd":"2017022015142561","ui_tel":"15882345446","utime":1493102652000},"errorcode":"","errormsg":"记录ETC用户基本信息成功","errorno":"0"}
   * </pre>
   */
  @Test
  public void etc_user_update() throws Exception {
    String url = BaseUrl + "etc_user_update.php";
    PostMethod post = new UTF8PostMethod(url);
    //设置公共参数 '/^([1-9]{1})(\d{14}|\d{18})$/
    setPublicParam(post);
    String tel = "15882345446";
    ;//用户手机号码
    String name = "王小刚2";//ETC用户真实姓名
    String sfz_number = "510324198832497533";//ETC用户真实身份证号码
    String bank_card_number = "6228482938412183773";//ETC用户银行卡卡号
    Integer bank_type = 0;//银行类型（0：建行银行）

    //用户身份证图片文件
    MultipartFile cardimg;
    //提交过来的file的名字
    String cardimgFileName = "sdfs.jpg";
    //提交过来的file的MIME类型
    String cardimgContentType = "jpg";

    String eu_id = "3";

    sign = getSignature(Constants.SYSTEM_KEY, dtype, ui_id, eu_id);

    post.addParameter("eu_id", eu_id);
//    post.addParameter("tel", tel);
    post.addParameter("name", name);
//    post.addParameter("sfz_number", sfz_number);
//    post.addParameter("bank_card_number", bank_card_number);
//    post.addParameter("bank_type", String.valueOf(bank_type));
//    post.addParameter("cardimgFileName", cardimgFileName);
//    post.addParameter("cardimgContentType", cardimgContentType);
    post.addParameter("sign", sign);

    List<Part> params = new ArrayList<>();
    for (NameValuePair nameValuePair : post.getParameters()) {
      params.add(new StringPart(nameValuePair.getName(), nameValuePair.getValue(), "UTF-8"));
    }
    params.add(new FilePart("cardimg", new ClassPathResource("image.png").getFile()));
    post.setRequestEntity(
        new MultipartRequestEntity(params.toArray(new Part[params.size()]), post.getParams()));
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
      log.error("change_pass is error" + e.getMessage(), e);
    } finally {
      if (post != null) {
        post.releaseConnection();
        //释放链接
        HC.getHttpConnectionManager().closeIdleConnections(0);
      }
    }
  }


  /**
   * 用户退签ETC
   * 响应例子：
   * <pre>
   *   {"data":{"bank_card_number":"6228482938412183773","bank_type":0,"ctime":1493102652000,"discard_time":1493102652000,"eu_id":3,"eu_nd":"2017042514445156","is_default":1,"is_sign":3,"name":"王小刚2","note":"","sfz_img_url":"http://app.qc-wbo.com/file/img/card/2017/head15882345446_63732.png","sfz_number":"510324198832497533","sign_ip":"","signtime":1493102652000,"ui_id":142,"ui_nd":"2017022015142561","ui_tel":"15882345446","utime":1493102652000},"errorcode":"","errormsg":"删除银行卡签约成功","errorno":"0"}
   * </pre>
   */
  @Test
  public void etc_user_del() throws Exception {
    String url = BaseUrl + "etc_user_del.php";
    PostMethod post = new UTF8PostMethod(url);
    setPublicParam(post);
    String eu_id = "1";
    sign = getSignature(Constants.SYSTEM_KEY, dtype, ui_id, eu_id);

    post.addParameter("eu_id", eu_id);
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
      log.error("etc_user_del is error" + e.getMessage(), e);
    } finally {
      if (post != null) {
        post.releaseConnection();
        //释放链接
        HC.getHttpConnectionManager().closeIdleConnections(0);
      }
    }
  }

  /**
   * 检查ETC用户 是否签约成功
   * 响应例子：
   * <pre>
   * {"data":{"state":0},"errorcode":"","errormsg":"查询成功","errorno":"0"}
   * </pre>
   */
  @Test
  public void etc_checksign() throws Exception {
    String url = BaseUrl + "etc_checksign.php";
    PostMethod post = new UTF8PostMethod(url);
    setPublicParam(post);
    String eu_id = "4";
    sign = getSignature(Constants.SYSTEM_KEY, dtype, ui_id, eu_id);

    post.addParameter("eu_id", eu_id);
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
      log.error("etc_user_del is error" + e.getMessage(), e);
    } finally {
      if (post != null) {
        post.releaseConnection();
        //释放链接
        HC.getHttpConnectionManager().closeIdleConnections(0);
      }
    }
  }
  
}
