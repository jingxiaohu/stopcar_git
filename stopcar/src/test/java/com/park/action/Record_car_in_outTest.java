package com.park.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import apidoc.jxh.cn.InterfaceUtil;
import com.park.gate.action.v1.car.param.Param_OpenSigno;
import com.park.mvc.action.v1.param.BaseParam;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Peter Wu
 */
public class Record_car_in_outTest extends BaseWebTest {

  /**
   * 车辆入库出库记录
   *
   * <pre>
   * {"data":{"car_code":"川A4M99B","cio_id":1,"ctime":1470297823327,"in_out":"enter","in_out_code":"A","is_enter":0,"note":"","pd_id":1,"pi_id":1,"ui_id":1,"utime":1470297823327},"errorno":"0"}
   * </pre>
   */
  @Test
  public void record_car_in_out() throws Exception {

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("dtype", dtype);
    params.add("ui_id", "142");

    long pi_id = 36;//场地主键ID
    long pd_id = 1;//出入口设备主键ID
    String car_code = "沪F00001";//车牌号 (第一期：车牌号---对应一个人)
    int is_enter = 0;//入库或者出库 ：0：   入库   1：出库
    String in_out = "enter";//出口或者入口 入口：enter  出口：exit
    String in_out_code = "A";//出入口编号 A /B/C
    int car_type = 1;//车牌类型:车牌类型 0：未知车牌:、1：蓝牌小汽车、2：: 黑牌小汽车、3：单排黄牌、4：双排黄牌、 5： 警车车牌、6：武警车牌、7：个性化车牌、8：单 排军车牌、9：双排军车牌、10：使馆车牌、11： 香港进出中国大陆车牌、12：农用车牌、13：教 练车牌、14：澳门进出中国大陆车牌、15：双层 武警车牌、16：武警总队车牌、17：双层武警总 队车牌
    int car_code_color = 1;//车牌颜色 0：未知、1：蓝色、2：黄色、3：白色、 4：黑色、5：绿色
    String area_code = "510107";//area_code;//省市县编号 510122
    int out_type = 0;//出库类型 0:正常出库 1：现金支付出库 2：异常出库
    int is_local_month = 0;//是否是道闸本地包月车辆 0:不是 1：是

    params.add("area_code", area_code);
    params.add("pi_id", pi_id + "");
    params.add("pd_id", pd_id + "");
    params.add("car_code", car_code);
    params.add("is_enter", is_enter + "");
    params.add("in_out", in_out);
    params.add("in_out_code", in_out_code);
    params.add("car_type", car_type + "");
    params.add("car_code_color", car_code_color + "");
    params.add("out_type", out_type + "");
    params.add("is_local_month", is_local_month + "");
    params.add("gov_num", "123");
//    params.add("magnetic_force", "1");

    sign(params, "dtype", "pi_id", "in_out");

    MvcResult mvcResult = mockMvc.perform(post("/v1/record_car_in_out").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());

  }

  /**
   * 车辆出库开闸记录
   *
   * <pre>
   * {"data":{"car_code":"川A4M99B","cio_id":1,"ctime":1470297823327,"in_out":"enter","in_out_code":"A","is_enter":0,"note":"","pd_id":1,"pi_id":1,"ui_id":1,"utime":1470297823327},"errorno":"0"}
   * </pre>
   */
  @Test
  public void open_signo() throws Exception {

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("dtype", dtype);
    long pi_id = 36;//场地主键ID
    String area_code = "510107";//area_code;//省市县编号 510122
    int is_cash=0;//是否现金支付 0：在线支付  1：现金支付
    String order_id="2016122714386536";//订单ID
    Long sync_time=System.currentTimeMillis();//离线端入库时间或者出库时间  13位毫秒数
    Integer is_sync=1;// 如果是异步上传  is_sync=1
    Integer is_open=2;//是否开闸 0:未开闸 1：已正常放行开闸 2: 道闸手动放行

    params.add("area_code", area_code);
    params.add("pi_id", pi_id + "");
    params.add("is_cash", is_cash + "");
    params.add("order_id", order_id + "");
    params.add("sync_time", sync_time+"");
    params.add("is_sync", is_sync+"");
    params.add("is_open", is_open + "");

    sign(params, "dtype", "pi_id", "is_cash");

    MvcResult mvcResult = mockMvc.perform(post("/v1/open_signo").params(params))
            .andExpect(status().isOk()).andReturn();
    String result = mvcResult.getResponse().getContentAsString();
    System.err.println(result);

    String filepath = this.getClass().getResource(".").getPath()+"car_open.md";
    InterfaceUtil.AddInterfacePred(
            filepath,
            "车辆管理模块",
            "车辆出库开闸记录",
            "dtype+pi_id+is_cash",
            "/v1/open_signo.php",1,params, Param_OpenSigno.class,result);

  }
}
