package com.park.action;

import apidoc.jxh.cn.InterfaceUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.param.BaseParam;
import com.park.mvc.service.UserPayBiz;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zzy on 2017/7/5.
 */
public class Write_RentDeferByAPPActionTest extends BaseWebTest {

    @Autowired
    private UserPayBiz userPayBiz;

    /**
     * 租赁订单续租信息校验
     * 响应数据：
     * {
     * "data": "",
     * "errorcode": "",
     * "errormsg": "成功",
     * "errorno": "0"
     *  }
     * @throws Exception
     */
    @Test
    public void checkRentDeferInfoTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("ui_id","54");
        params.add("ui_nd","2016122117450316");
        params.add("dtype","1");

        params.add("cpr_id","4");   //用户车牌--停车场租赁映射表主键id
        params.add("pi_id","36");   //停车场主键id
        params.add("area_code","510107");    //区域代码
        params.add("client_ruleid", "d425aa1dcb1a444fbfa9a32ebafa30ea");    //客户端的规则ID
        params.add("unit_price", "2");
        params.add("car_code","川A00000");     //车牌号码
        sign(params,"dtype","ui_id","cpr_id");
        MvcResult mvcResult = mockMvc.perform(post("/v1/rentOrderInfoCheck").params(params))
                .andExpect(status().isOk()).andReturn();
        String resultStr = mvcResult.getResponse().getContentAsString();
        System.out.println("检查结果:\n"+resultStr);
    }


    /**
     * 手机APP 租赁订单续约
     * 响应数据：
     * {"data":{"allege_state":0,"area_code":"510107","car_code":"川A12348","client_order_id":"","client_rule_id":"d425aa1dcb1a444fbfa9a32ebafa30ea","ctime":1500271460630,"defer_state":1,"endtime":1499586372000,"father_order_id":"","flag":2,"is_del":0,"is_expire":0,"money":10,"month_num":1,"mq_state":0,"note":"APP续租租赁信息","pay_source":2,"pay_state":0,"permit_time":"8：00-23：00","pi_id":36,"pi_name":"天府三街停车场","pu_id":0,"pu_nd":"","rd_id":40,"rent_order_id":"5101072000000364201707171404206h","rent_type":1,"son_order_id":"","starttime":1496994372000,"stime":1500271460630,"ui_id":132,"ui_nd":"1","unit_price":10,"up_orderid":"","utime":1500271460630},"errorcode":"","errormsg":"租赁订单续约新增成功","errorno":"0"}
     * @throws Exception
     */
    @Test
    public void rentDeferTest() throws Exception{
        System.out.println("记录续约信息");
        //1.记录续约信息
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("ui_id","54");
        params.add("ui_nd","2016122117450316");
        params.add("dtype","1");

        //params.add("rent_order_id","2017070713341300010");
        params.add("cpr_id","4");   //用户车牌--停车场租赁映射表主键id
        params.add("pi_id","36");   //停车场主键id
        params.add("area_code","510107");    //区域代码
        params.add("month_num","1");    //续约月份个数
        params.add("client_ruleid", "d425aa1dcb1a444fbfa9a32ebafa30ea");    //客户端的规则ID

        params.add("car_code","川A00000");     //车牌号码
        params.add("permit_time","8：00-23：00");    //允许时间段（8：00-23：00）
        params.add("pay_source","4");   //支付类型 0：现金 1:支付宝  2：微信  3：银联  4：钱包 5:龙支付  6:ETC快捷支付
        //params.add("ctime",System.currentTimeMillis()+"");
        params.add("rent_type","1");     //租赁类型（0：普通时间段  1：早半天  2：晚半天  3：全天）
        sign(params,"dtype","ui_id","cpr_id");
        MvcResult mvcResult = mockMvc.perform(post("/v1/rentOrderDefer").params(params))
                .andExpect(status().isOk()).andReturn();
        String resultStr = mvcResult.getResponse().getContentAsString();
        System.out.println("记录续约信息:\n"+resultStr);

        String filepath = this.getClass().getResource(".").getPath()+"test.md";
        System.out.println(filepath);
        InterfaceUtil.AddInterfacePred(
                filepath,
                "测试模块",
                "APP租赁续租",
                "dtype+ui_id+cpr_id",
                "/v1/rentOrderDefer.php",1,params, BaseParam.class,resultStr);

        //GSON
        JsonObject obj = new JsonParser().parse(resultStr).getAsJsonObject();

        if(!"0".equals(obj.get("errorno").getAsString())){
            System.out.println("记录续约信息失败");
            return;
        }

        String orderid = obj.get("data").getAsJsonObject().get("rent_order_id").getAsString();
        System.out.println("租赁订单号："+orderid);

        //2.支付下单
        System.out.println("支付下单");
        MultiValueMap<String, String> pay = new LinkedMultiValueMap<>();
        pay.add("dtype",dtype);
        pay.add("ui_id","132");
        pay.add("orderid",orderid);
        pay.add("pay_type","2");
        pay.add("pay_price","100");
        pay.add("version","1");
        pay.add("subject","微信支付");
        pay.add("system_type","1");
        pay.add("t",System.currentTimeMillis()+"");
        pay.add("token","7f5b426090c9603a2b633197cac80e97");
        pay.add("type","4");   //是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付   4:租赁订单续约支付
        pay.add("show_url","");
        pay.add("return_url","");
        sign(pay,"dtype","ui_id","pay_type","pay_price","t","token");
        MvcResult resultPay = mockMvc.perform(post("/v1/weixin_charge").params(pay))
                .andExpect(status().isOk()).andReturn();
        String payResult = resultPay.getResponse().getContentAsString();
        System.out.println("支付下单结果：\n"+payResult);

        if(!"0".equals(new JsonParser().parse(payResult).getAsJsonObject().get("errorno").getAsString())){
            System.out.println("支付下单失败");
            return;
        }

        //第三方流水号
        String orderIdSyn = new JsonParser().parse(payResult).getAsJsonObject().get("data").getAsJsonObject().get("order_id").getAsString();

        //String orderIdSyn = "2017070513522862529";

        //模拟微信通知回调
        System.out.println("模拟微信通知回调成功");
        ReturnDataNew returnDataNew = new ReturnDataNew();
        userPayBiz.notify_weixin(returnDataNew,orderIdSyn,"","4",100);

        System.out.println("通知结果响应："+returnDataNew);
    }

    /**
     * 删除数据
     * @throws Exception
     * 响应数据：
     * {"data":"","errorcode":"","errormsg":"操作成功","errorno":"0"}
     */
    @Test
    public void rentDeferDel() throws Exception {

        Long pi_id = 36L;   //停车场主键id
        Long rd_id = 3L;  //用户车牌--停车场租赁映射表主键id
        String area_code = "510107";  //区域代码
        String car_code = "川A11111";   //车牌号码

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("ui_id","132");
        params.add("ui_nd","1");
        params.add("dtype",dtype);
        params.add("pi_id",pi_id+"");
        params.add("area_code",area_code);
        params.add("car_code",car_code);
        params.add("rd_id",rd_id+"");

        sign(params,"dtype","rd_id","ui_nd");
        MvcResult mvcResult = mockMvc.perform(post("/v1/rentdeferdel.php").params(params))
                .andExpect(status().isOk()).andReturn();
        String resultStr = mvcResult.getResponse().getContentAsString();
        System.out.println("响应结果："+resultStr);

    }

    @Test
    public void tttt() throws Exception{
        //String str = "{\"pi_id\":[\"405\"],\"dtype\":[\"4\"],\"car_code\":[\"川L9229A\"],\"final_time\":[\"2\"],\"car_type\":[\"1\"],\"car_code_color\":[\"1\"],\"money\":[\"200\"],\"create_time\":[\"1502949857100\"],\"order_type\":[\"2\"],\"order_id\":[\"510106000000405420170817140417v3\"],\"area_code\":[\"510106\"],\"sign\":[\"e731af511d79640284e497fb2eba10f0\"]}";
        String str = "{\"ui_token\":[\"67493b7a19c8bdc1bc11155d69ef2690\"],\"pi_id\":[\"287\"],\"ui_nd\":[\"2017051711552394829\"],\"sign\":[\"4b5b9d7971ce407abcc589c872dd14a5\"],\"unit_price\":[\"1\"],\"ui_id\":[\"397\"],\"car_code\":[\"川Q55555\"],\"month_num\":[\"1\"],\"pay_source\":[\"4\"],\"cpr_id\":[\"184\"],\"rent_type\":[\"3\"],\"dtype\":[\"0\"]}";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        Map map = null;

        map = new ObjectMapper().readValue(str,Map.class);
       // System.out.println(map.get("pi_id"));

        params.putAll(map);

        //sign(params,"dtype","cpr_id","ui_id");


        MvcResult mvcResult = mockMvc.perform(post("/v1/rentOrderDefer").params(params))
                .andExpect(status().isOk()).andReturn();
        String resultStr = mvcResult.getResponse().getContentAsString();
        System.out.println("结果:\n"+resultStr);
    }
}
