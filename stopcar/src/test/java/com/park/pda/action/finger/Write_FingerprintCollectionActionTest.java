package com.park.pda.action.finger;

import com.park.action.BaseWebTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Created by zzy on 2017/7/21.
 */
public class Write_FingerprintCollectionActionTest extends BaseWebTest {
    /**
     * 指纹系统--用户资料新增
     * 请求数据：
     * {"dtype":["2"],"verify_code":["994081"],"verify_list":["941b48661260c1e0d7589ed6007d75cf"],"vclass":["1"],"ui_tel":["13688052700"],"car_code":["川A11111"],"name":["张三"],"sfz_number":["421182197504264466"],"sfz_img_url":[""],"bank_card_number":["6217111111111111231"],"bank_type":["0"],"mac":["abc-12912883893-abddffd"],"sign_ip":["192.168.1.1"],"gather_id":["123"],"ctime":["1500863643008"],"sign":["7fce17b167ef15e3e2807f1c242c21ee"]}
     *
     * 响应数据：
     * {"data":{"lzf_info":{"act_type":1,"car_order_id":"","ctime":1500866107568,"escape_orderids":"","etime":1500866107568,"id":1261,"ip":"192.168.1.1","money":1,"note":"","order_id":"2017072411150779196","referer":"","return_url":"","state":0,"subject":"吾泊充值","system_type":1,"tel":"13688052700","transaction_id":"","type":5,"ui_id":397,"ui_nd":"2017051711552394829","utime":1500866107568,"version_code":1},"finger_userinfo":{"bank_card_number":"6217111111111111231","bank_type":0,"car_code":"川A11111","ctime":1500863643008,"discard_time":1500866107605,"finger_veno":"","fingerprint":"","fu_id":2,"fu_nd":"20170724111507g0zJP4hc2PsX0YzdBj","gather_id":123,"is_default":0,"is_del":0,"is_sign":0,"mac":"abc-12912883893-abddffd","name":"张三","note":"初始化数据","orderid":"2017072411150779196","sfz_img_url":"","sfz_number":"421182197504264466","sign_ip":"192.168.1.1","signtime":1500866107605,"state":1,"ui_id":397,"ui_nd":"2017051711552394829","ui_tel":"13688052700","utime":1500866107605,"verify_sign":0}},"errorcode":"","errormsg":"成功","errorno":"0"}
     *
     * Y表示可以为空，其他均为必传
     * @throws Exception
     */
    @Test
    public void saveFingerUserinfoTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("dtype","2");
        //业务参数
        params.add("verify_code","317573");
        params.add("verify_list","3a91ce59c8d7f93c16f76d08e32d4f65");
        params.add("vclass","5");
        params.add("ui_tel","13990135040");
        params.add("car_code","川E12345");
        params.add("name","邓孟1");
        params.add("sfz_number","51132419920309107X");
        params.add("sfz_img_url","");       //Y
        params.add("bank_card_number","6217003810023141206");
        params.add("bank_type","0");
        params.add("mac","869270028529492");
        //params.add("sign_ip","192.168.1.1");gi
        //params.add("gather_id","123");
        //params.add("ctime","1500863643008");

        sign(params,"dtype","ui_tel","sfz_number","bank_card_number");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/finger_userinfo_add").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println("响应内容："+mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void saveFingerUserinfoTest1() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("dtype","2");
        //业务参数
        params.add("verify_code","540891");
        params.add("verify_list","1a9ade2ac1c052ee02d8f451b5bc86dc");
        params.add("vclass","5");
        params.add("ui_tel","13688052700");
        params.add("car_code","川E11111");
        params.add("name","陈晶晶");
        params.add("sfz_number","510112198711066020");
        params.add("sfz_img_url","");       //Y
        params.add("bank_card_number","6217003810052463968");
        params.add("bank_type","0");
        params.add("mac","869612026680605");
        //params.add("sign_ip","192.168.1.1");
        //params.add("gather_id","123");
        //params.add("ctime","1500863643008");

        sign(params,"dtype","ui_tel","sfz_number","bank_card_number");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("http://223.85.163.38:88/stopcar/v1/finger_userinfo_add").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println("响应内容："+mvcResult.getResponse().getContentAsString());
    }

    /**
     * 支付一分钱查询并签约；支付验证银行卡是否为本人操作，参考etc，验证成功后，签约成功
     * 请求数据：
     * {"dtype":["2"],"fu_id":["2"],"fu_nd":["20170724111507g0zJP4hc2PsX0YzdBj"],"sign":["008e93c74e9d4ebc64a317f1242e1ebc"]}
     *
     * 响应数据：
     * {"data":"","errorcode":"","errormsg":"查询成功","errorno":"0"}
     *
     * @throws Exception
     */
    @Test
    public void fingerCheckSignTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("dtype","2");
        //业务参数
        params.add("fu_id","2");
        params.add("fu_nd","20170724111507g0zJP4hc2PsX0YzdBj");

        sign(params,"dtype","fu_id");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/finger_userinfo_check_sign").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println("响应内容："+mvcResult.getResponse().getContentAsString());
    }

    /**
     * 指纹系统---修改用户资料信息
     * 请求数据：
     *
     *
     * 响应数据：
     *
     *
     * @throws Exception
     */
    @Test
    public void updateFingerUserInfoTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("dtype","2");
        //业务参数
        params.add("fu_id","35");
        params.add("car_code","川E12345");
        //params.add("name","张三");
        //params.add("sfz_number","421182197504264466");
        params.add("sfz_img_url","");
        params.add("fingerprint","uoruewouroejlfjdsljowiuerow");
        params.add("bank_type","0");
        //params.add("bank_card_number","6217111111111111230");
        //params.add("mac","jlfadjslafdjs");
        //params.add("gather_id","1233");
        //params.add("sign_ip","192.168.1.5");
        params.add("fingerprint_vein_str","[{\"vein\": \"用户静脉333322\",\"fingerprint\": \"用户指纹特征信息\",\"vein_hash\": \"用户静脉图片hashcode\",\"fingerprint_hash\": \"用户指纹图片hashcode\"},{\"vein\": \"用户静脉特征信息\",\"fingerprint\": \"用户指纹特征信息\",\"vein_hash\": \"用户静脉图片hashcode\",\"fingerprint_hash\": \"用户指纹图片hashcode\"}]\n");

        sign(params,"dtype","fu_id");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/finger_userinfo_update").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.err.println(mvcResult.getResponse().getContentAsString());
    }

    /**
     * 指纹系统--用户资料解除签约操作
     * 请求数据：
     *
     *
     * 响应数据：
     *
     *
     * @throws Exception
     */
    @Test
    public void fingerRemoveSignedTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("dtype","2");
        //业务参数
        params.add("fu_id","74");

        sign(params,"dtype","fu_id");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/finger_removesigned").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    /**
     * {"dtype":["0"],"tel":["18215650714"],"vclass":["5"],"sign":["4caf9180cbc8767004ae71c40fc59701"]}
     */
    @Test
    public void sendCodeTest() throws Exception{
        MultiValueMap<String,String> params = new LinkedMultiValueMap<String,String>();
        params.add("dtype","0");
        //业务参数
        params.add("tel","18215650714");
        params.add("vclass","5");

        sign(params,"dtype","tel","vclass");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/sendcode").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
