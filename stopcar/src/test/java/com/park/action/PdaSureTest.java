package com.park.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Peter Wu
 */
public class PdaSureTest  extends BaseWebTest{


  @Test
  @SuppressWarnings("unchecked")
  public void pda_sure() throws Exception {
    Map map = new ObjectMapper().readValue(
        "{\"dtype\":[\"2\"],\"pi_id\":[\"68\"],\"pay_type\":[\"7\"],\"type\":[\"0\"],\"area_code\":[\"510124\"],\"money\":[\"1\"],\"orderid\":[\"510124200000068020170707180557H3\"],\"car_code\":[\"京A23456\"],\"escape_orderids\":[\"\"],\"faceFileName\":[\"face.jpg\"],\"sign\":[\"ce2f97911b95f864f54569afba65de4e\"],\"pu_nd\":[\"20170315105816\"]}",
        Map.class);
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.putAll(map);
    MvcResult mvcResult = mockMvc.perform(fileUpload("/v1/pda_sure").file(new MockMultipartFile("face",new ClassPathResource("image.png").getInputStream())).params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());
  }
  
}
