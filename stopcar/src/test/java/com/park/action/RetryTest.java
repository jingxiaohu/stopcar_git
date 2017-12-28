package com.park.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.park.bean.Carcode_park_rent;
import com.park.dao.User_payDao;
import com.park.mvc.action.v1.notify.Notify_WeiXinction;
import com.park.mvc.service.common.PayParkPB;
import com.park.service.MySelfService;
import com.park.transaction.PayTransaction;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Peter Wu
 */
public class RetryTest extends BaseWebTest {
  @Resource(name = "MySelfService")
  private MySelfService mySelfService;
  @Test
  public void name() throws Exception {
    List<Carcode_park_rent> carcode_park_rents = mySelfService.queryListT(
        "SELECT * FROM carcode_park_rent o WHERE o.car_code=? AND o.endtime>? AND o.is_del=0",
        Carcode_park_rent.class, "川RE1234", new Date());
    System.err.println(carcode_park_rents);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void retry() throws Exception {
    Map map = new ObjectMapper().readValue(
        "{\"pu_nd\":[\"20170629144247\"],\"dtype\":[\"2\"],\"pi_id\":[\"247\"],\"pay_type\":[\"4\"],\"type\":[\"0\"],\"area_code\":[\"510116\"],\"money\":[\"3101\"],\"orderid\":[\"510116200000247020170712155920N0\"],\"car_code\":[\"川EX929B\"],\"escape_orderids\":[\"\"],\"sign\":[\"53a532ff1d6b47b1afde461dc9960c39\"]}",
        Map.class);
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.putAll(map);
    MvcResult mvcResult = mockMvc.perform(post("/v1/pda_sure").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void notify_lzf() throws Exception {
    Map map = new ObjectMapper().readValue(
        "{\"SIGN\":[\"5afcfbf0b5f4a0d1d7625afa032e856835b380509843336e482f4f1b435d1b4c77668e22d015a7ca50cdf0e1ee35d7083fb3ad9cc5ea4838b1ffc2d3e6d6b792a628326ec9c4716586a356aa1df05c2298711908ed64757a4d08633255c088c28c91cdca0b2820a3df7cafaf9f1018ebc3f6bbc6f82c0f441e705a77e32cbc17\"],\"REMARK1\":[\"\"],\"CLIENTIP\":[\"222.209.35.145\"],\"BRANCHID\":[\"510000000\"],\"REMARK2\":[\"\"],\"SUCCESS\":[\"Y\"],\"ACC_TYPE\":[\"02\"],\"CURCODE\":[\"01\"],\"ORDERID\":[\"2017061413305965534\"],\"POSID\":[\"426295203\"],\"REFERER\":[\"\"],\"PAYMENT\":[\"2.40\"],\"TYPE\":[\"1\"]}",
        Map.class);
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.putAll(map);
    MvcResult mvcResult = mockMvc.perform(post("/v1/notify_lzf").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());
  }

  @Test
  public void notify_url() throws Exception {
    String payNotify = ControllerLinkBuilder.linkTo(Notify_WeiXinction.class,
        Notify_WeiXinction.class.getMethod("notify_weixin", HttpServletRequest.class, HttpServletResponse.class)).withSelfRel().getHref();
    System.err.println(payNotify);
  }

  @Autowired
  PayTransaction payTransaction;
  @Autowired
  User_payDao user_payDao;

  @Test
  public void notifyUser() throws Exception {
    payTransaction.NotifyUserPay(user_payDao.selectByKey(1554));
  }

  @Autowired
  PayParkPB payParkPB;
  @Test
  public void orderid() throws Exception {
    System.err.println(payParkPB.returnNewOrderId("510107", 0, 23, 1));
    System.err.println(payParkPB.returnNewOrderId("510107", 0, 23, 1).length());
    System.err.println(payParkPB.returnNewOrderId("510107", 0, 33333333, 1));
    System.err.println(payParkPB.returnNewOrderId("510107", 0, 33333333, 1).length());
    System.err.println(payParkPB.returnNewOrderId("510107", 0, 333333337777L, 1));
    System.err.println(payParkPB.returnNewOrderId("510107", 0, 333333337777L, 1).length());
  }
}
