package com.park.pda.action.finger;

import apidoc.jxh.cn.InterfaceUtil;
import com.park.action.BaseWebTest;
import com.park.gate.action.v1.park.param.Param_park_auth;
import com.park.pda.action.v1.fingerprintvein.param.*;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Created by zzy on 2017/8/30.
 */
public class Write_FingerPrintVeinCollectActionTest extends BaseWebTest{

    /**
     * 手机短信验证
     * @throws Exception
     */
    @Test
    public void smsCodeVerifyTest() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("dtype","2");
        //业务参数

        params.add("ui_tel","13990135041");
        params.add("verify_code","906775");
        params.add("verify_list","1faa74a8e2acc7f2809ebec12d7fe7b8");
        params.add("vclass","4");

        sign(params,"dtype","ui_tel","verify_code");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/smscodeverify").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("响应内容："+result);

        String path = this.getClass().getResource(".").getPath();
        path = path + "smscodeverify.md";
        InterfaceUtil.AddInterfacePred(path, "用户指纹静脉采集模块新版",
                "手机短信验证",
                "dtype+ui_tel+verify_code",
                "/v1/smscodeverify.php",
                1,
                params,
                SmsCodeVerifyReq.class,
                result);
    }

    /**
     * 采集用户身份信息，
     * @throws Exception
     */
    @Test
    public void collectUserInfoTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("dtype","2");
        //业务参数

        params.add("ui_tel","18180889220");
        params.add("name","张三");
        params.add("sfz_number","111111111111111");
        params.add("sfz_img_url","");
        params.add("bank_card_number","6217900100000000");
        params.add("bank_type","0");
        params.add("mac","abcfds");
        params.add("gather_id","111");
        params.add("sign_ip","127.0.0.1");

        sign(params,"dtype","ui_tel","sfz_number","bank_card_number");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/collect_userinfo").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("响应内容："+result);

        String path = this.getClass().getResource(".").getPath();
        path = "C://Users/zzy/Desktop/EA/" + "collectUserinfo.md";
        InterfaceUtil.AddInterfacePred(path, "用户指纹静脉采集模块新版",
                "采集用户身份信息",
                "dtype+ui_tel+sfz_number+bank_card_number",
                "/v1/collect_userinfo.php",
                1,
                params,
                UserInfoReq.class,
                result);
    }

    /**
     * 支付一分钱验证银行卡是否为本人操作，参考etc，验证成功后，去进行绑卡操作
     * @throws Exception
     */
    @Test
    public void fingerCheckSignTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("dtype","2");
        //业务参数

        params.add("fu_id","2");
        params.add("fu_nd","20170831112344pLTyxobjSLZcKgaR5s");
        params.add("fub_id","2");
        sign(params,"dtype","fu_id","fub_id");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/fingerprintveion_checksign").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("响应内容："+result);

        String path = this.getClass().getResource(".").getPath();
        path = "C://Users/zzy/Desktop/EA/" + "fingerCheckSign.md";
        InterfaceUtil.AddInterfacePred(path, "用户指纹静脉采集模块新版",
                "支付一分钱验证并绑卡",
                "dtype+fu_id+fub_id",
                "/v1/fingerprintveion_checksign.php",
                1,
                params,
                FingerPrintVeionCheckSignReq.class,
                result);
    }

    /**
     * 手指信息采集测试
     * @throws Exception
     */
    @Test
    public void fingerCollectTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("dtype","2");
        //业务参数

        params.add("fu_id","2");
        params.add("fingerprint_img_name","2");
        params.add("finger_veno_img_name","2");
        params.add("vein","ljfldsaljfdlsajlljl");
        params.add("vein_hash","vein_hash");
        params.add("fingerprint","fingerprint");
        params.add("fingerprint_hash","fingerprint_hash");


        sign(params,"dtype","fu_id");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/finger_collect").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("响应内容："+result);

        String path = this.getClass().getResource(".").getPath();
        path = "C://Users/zzy/Desktop/EA/" + "Parkinfo.md";
        InterfaceUtil.AddInterfacePred(path, "用户指纹静脉采集模块新版",
                "手指信息采集",
                "dtype+fu_id",
                "/v1/finger_collect.php",
                1,
                params,
                FingerCollectReq.class,
                result);
    }

    /**
     * 用户手指信息删除
     * @throws Exception
     */
    @Test
    public void fingerDeleteTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("dtype","2");
        //业务参数

        params.add("fur_id","11");
        params.add("fu_id","2");

        sign(params,"dtype","fu_id","fur_id");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/fingerdel").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("响应内容："+result);

        String path = this.getClass().getResource(".").getPath();
        path = "C://Users/zzy/Desktop/EA/" + "fingerdel.md";
        InterfaceUtil.AddInterfacePred(path, "用户指纹静脉采集模块新版",
                "用户手指信息删除",
                "dtype+fu_id+fur_id",
                "/v1/fingerdel.php",
                1,
                params,
                FingerDelReq.class,
                result);
    }


    /**
     * 用户指纹静脉车牌绑定
     * @throws Exception
     */
    @Test
    public void bindCarCodeTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("dtype","2");
        //业务参数

        params.add("fu_id","2");
        params.add("car_code","川A12345");
        params.add("sfz_number","51132419920309107X");

        sign(params,"dtype","fu_id");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/finger_carcode_bind").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("响应内容："+result);

        String path = this.getClass().getResource(".").getPath();
        path = path + "fingerbindcar.md";
        InterfaceUtil.AddInterfacePred(path, "用户指纹静脉采集模块新版",
                "用户指纹静脉车牌绑定",
                "dtype+fu_id",
                "/v1/finger_carcode_bind.php",
                1,
                params,
                UserCarCodeBindReq.class,
                result);
    }


    /**
     * 用户指纹静脉车牌解绑
     * @throws Exception
     */
    @Test
    public void unBindCarCodeTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("dtype","2");
        //业务参数

        params.add("fuc_id","1");

        sign(params,"dtype","fuc_id");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/finger_carcode_unbind").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("响应内容："+result);

        String path = this.getClass().getResource(".").getPath();
        path = "C://Users/zzy/Desktop/EA/" + "fingerunbindcar.md";
        InterfaceUtil.AddInterfacePred(path, "用户指纹静脉采集模块新版",
                "用户指纹静脉车牌解绑",
                "dtype+fuc_id",
                "/v1/finger_carcode_unbind.php",
                1,
                params,
                UserCarCodeUnBindReq.class,
                result);
    }

}
