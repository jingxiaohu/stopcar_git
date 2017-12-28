package com.park.pda.action.finger;

import com.park.action.BaseWebTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Date;

/**
 * Created by zzy on 2017/7/21.
 */
public class Read_Fingerprint_Veno_infoActionTest extends BaseWebTest {

    /**
     * 指纹系统-通过车牌查询用户指纹、指静脉信息
     *
     * 请求参数：
     *  "parameter":{
     *       "dtype":["2"],
     *       "car_code":["京A36666"]
     *    }
     *
     * 响应数据：
     * {"data":{"fingerVenoFlag":1,"fingerPrintFlag":1,"car_code":"京A36666"},"errorcode":"","errormsg":"查询成功","errorno":"0"}
     * @throws Exception
     */
    @Test
    public void getFingerVenoInfoTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("dtype","2");
        //业务参数
        params.add("car_code","京A36666");
//        sign(params, "dtype", "car_code");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/read_finger_veno_info").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
