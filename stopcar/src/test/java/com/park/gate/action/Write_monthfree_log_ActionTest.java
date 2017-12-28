
package com.park.gate.action;

import com.alibaba.fastjson.JSONArray;
import com.park.bean.Merchant_parkinfo_monthfree_log;
import com.park.constants.Constants;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import stopcar.test.action.BaseActionTest;

/**
 * 包月/免费车记录 测试
 *
 * @author jingxiaohu
 */
public class Write_monthfree_log_ActionTest extends BaseActionTest {

  /**
   * 包月/免费车记录
   *
   * month_log/free_log参数例子：
   * {
   *"ctime":1491549557891,//创建时间
   *"utime":1491549557891,//更新时间
   "car_code": "川A2359", //车牌号
   "car_code_color": 1, //车牌颜色0：未知、1：蓝色、2：黄色、3：白色、4：黑色、5：绿色
   "car_color": 3, //车辆颜色BLUE("蓝色",1),YELLOW("黄色",2),WHITE("白色",3),BLACK("黑色",4),GREEN("绿色",5)
   "car_type": 1, //车牌类型0：未知车牌:、1：蓝牌小汽车、2：:黑牌小汽车、3：单排黄牌、4：双排黄牌、5：警车车牌、6：武警车牌、7：个性化车牌、8：单排军车牌、9：双排军车牌、10：使馆车牌、11：香港进出中国大陆车牌、12：农用车牌、13：教练车牌、14：澳门进出中国大陆车牌、15：双层武警车牌、16：武警总队车牌、17：双层武警总队车牌
   "end_time_str": "2017-03-22 10:00:00", // 开始包月时间(2017-03-22 10:00:00)
   "money": 20, //该次缴纳费用(单位分)
   "start_time_str": "2017-03-22 10:00:00" // 结束包月时间(2017-03-22 10:00:00)
   }
   *
   * {"data":"","errorcode":"","errormsg":"更新停车场包月/免费车记录成功","errorno":"0"}
   */
  @Test
  public void record_monthfree_log() throws Exception {
    String url = BaseUrl + "record_monthfree_log.php";
    PostMethod post = new UTF8PostMethod(url);
    //设置公共参数
    setPublicParam(post);

    long pi_id = 295;//场地主键ID
    String area_code = "5100000";//area_code;//省市县编号 510122

    sign = getSignature(Constants.SYSTEM_KEY, dtype, pi_id, area_code);

    post.addParameter("area_code", area_code);
    post.addParameter("pi_id", pi_id + "");
    post.addParameter("month_log", getMonthfree_logTestData() + "");
//    post.addParameter("free_log", getMonthfree_logTestData(pi_id,area_code) + "");

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
      log.error("read_myinfo is error" + e.getMessage(), e);
    } finally {
      if (post != null) {
        post.releaseConnection();
        //释放链接
        HC.getHttpConnectionManager().closeIdleConnections(0);
      }
    }
  }

  /**
   * {
   * "ctime":1491549557891,//创建时间
   *"utime":1491549557891,//更新时间
   "car_code": "川A2359", //车牌号
   "car_code_color": 1, //车牌颜色0：未知、1：蓝色、2：黄色、3：白色、4：黑色、5：绿色
   "car_color": 3, //车辆颜色BLUE("蓝色",1),YELLOW("黄色",2),WHITE("白色",3),BLACK("黑色",4),GREEN("绿色",5)
   "car_type": 1, //车牌类型0：未知车牌:、1：蓝牌小汽车、2：:黑牌小汽车、3：单排黄牌、4：双排黄牌、5：警车车牌、6：武警车牌、7：个性化车牌、8：单排军车牌、9：双排军车牌、10：使馆车牌、11：香港进出中国大陆车牌、12：农用车牌、13：教练车牌、14：澳门进出中国大陆车牌、15：双层武警车牌、16：武警总队车牌、17：双层武警总队车牌
   "end_time_str": "2017-03-22 10:00:00", // 开始包月时间(2017-03-22 10:00:00)
   "money": 20, //该次缴纳费用(单位分)
   "start_time_str": "2017-03-22 10:00:00" // 结束包月时间(2017-03-22 10:00:00)
   }
   */
  @Test
  public void name() throws Exception {
    System.err.println(getMonthfree_logTestData());
  }

  /**
   * public String car_code="";//varchar(100)    车牌号 public long money;//bigint(20)    该次缴纳费用(单位分)
   * public int car_type;//int(11)    车牌类型0：未知车牌:、1：蓝牌小汽车、2：:黑牌小汽车、3：单排黄牌、4：双排黄牌、5：警车车牌、6：武警车牌、7：个性化车牌、8：单排军车牌、9：双排军车牌、10：使馆车牌、11：香港进出中国大陆车牌、12：农用车牌、13：教练车牌、14：澳门进出中国大陆车牌、15：双层武警车牌、16：武警总队车牌、17：双层武警总队车牌
   * public int car_code_color;//int(11)    车牌颜色0：未知、1：蓝色、2：黄色、3：白色、4：黑色、5：绿色 public int
   * car_color;//int(11)    车辆颜色BLUE("蓝色",1),YELLOW("黄色",2),WHITE("白色",3),BLACK("黑色",4),GREEN("绿色",5)
   * public String start_time_str="";//varchar(60)    开始包月时间(2017-03-22 10:00:00) public String
   * end_time_str=""
   */
  private String getMonthfree_logTestData() {
    List<Merchant_parkinfo_monthfree_log> monthfree_logs = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Merchant_parkinfo_monthfree_log log = new Merchant_parkinfo_monthfree_log();
      log.setCar_code("川A235" + i);
      log.setMoney(20);
      log.setCar_type(1);
      log.setCar_code_color(1);
      log.setCar_color(3);
      log.setStart_time_str("2017-03-22 10:00:00");
      log.setEnd_time_str("2017-03-22 10:00:00");
      monthfree_logs.add(log);
    }

    return JSONArray.toJSONString(monthfree_logs);
  }

}
