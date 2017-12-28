package com.park.pda.action.finger;

import com.park.action.BaseWebTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Created by zyy on 2017/9/1.
 */
public class Read_Fingerprint_Veno_furIdsActionTest extends BaseWebTest {

    /**
     * 指纹系统-通过用户ID查询指纹、指静脉ID列表
     *
     * 请求参数：
     *  "parameter":{
     *        "dtype":["2"],
     *        "fu_id":["1"],
     *        "sign":["fb5d13984435076c12f60e3b0f80138b"]
     *   }
     *
     * 响应数据：
     * {"data":[{"fur_id":1},{"fur_id":8},{"fur_id":9}],"errorcode":"","errormsg":"查询成功","errorno":"0"}
     * @throws Exception
     */
    @Test
    public void queryFingerVenofurIds() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("dtype","2");
        //业务参数
        params.add("fu_id","1");
        sign(params, "dtype", "fu_id");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/read_finger_veno_furids").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
