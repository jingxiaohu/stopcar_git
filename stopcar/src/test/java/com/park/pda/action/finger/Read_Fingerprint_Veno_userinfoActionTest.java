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
public class Read_Fingerprint_Veno_userinfoActionTest extends BaseWebTest {

    /**
     * 指纹系统-通过手机号码查询用户采集的信息
     *
     * 请求参数：
     *  "parameter":{
     *       "dtype":["2"],
     *       "ui_tel":["15828671698"],
     *       "verify_code":["123456"],
     *       "verify_list":["73d8ec709ccdcc39c844b25d13650ec0"],
     *       "vclass":["5"],
     *       "sign":["659d984c9c050f3c009327bfaa7b1947"]
     *   }
     *
     *
     * 响应数据：
     * {"data":{"carCodeList":[{"car_code":"京A26666","ctime":1504145160000,"fu_id":1,"fu_nd":"","fuc_id":1,"is_run":0,"isi_del":0,"note":"","sfz_number":"510111199202017524","utime":1504145162000},{"car_code":"京A36666","ctime":1504146315000,"fu_id":1,"fu_nd":"","fuc_id":2,"is_run":0,"isi_del":0,"note":"","sfz_number":"510111199202017524","utime":1504146317000}],"finger_userinfo_new":{"ctime":1501062842000,"finger_veno_state":1,"fingerprint_state":1,"fu_id":1,"fu_nd":"20170726175402UCatCDYEnke95uzXEQ","gather_id":0,"is_del":0,"mac":"869612024544688","name":"sss","note":"新增用户","sfz_img_back_url":"","sfz_img_url":"","sfz_number":"510111199202017524","state":1,"ui_id":399,"ui_nd":"2017060810555385508","ui_tel":"15828671698","utime":1501062842000},"finger_userinfo_bank":{"bank_card_number":"6227003813660822374","bank_type":0,"ctime":1501062842000,"discard_time":1504151229366,"fu_id":1,"fu_nd":"20170726175402UCatCDYEnke95uzXEQ","fub_id":1,"is_default":1,"is_del":0,"is_sign":1,"name":"sss","note":"新增用户","orderid":"1503887022344","sfz_number":"510111199202017524","sign_ip":"192.168.12.47","signtime":1504144950000,"state":1,"utime":1501062842000,"verify_sign":1}},"errorcode":"","errormsg":"查询成功","errorno":"0"}
     * @throws Exception
     */
    @Test
    public void getFingerVenoUserinfoTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("dtype","2");
        //业务参数
        params.add("ui_tel","15828671698");
        sign(params,"dtype","ui_tel");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/read_finger_veno_userinfo").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
