package com.park.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Peter Wu
 * @since
 */
public class RetryHttpTest {

  @Test
  @SuppressWarnings("unchecked")
  public void retryHttp() throws Exception {
    Map map = new ObjectMapper().readValue(
        "{\"rcr_id\":[\"27\"],\"dtype\":[\"2\"],\"pi_id\":[\"25\"],\"month_time\":[\"30\"],\"permit_time\":[\"0:00-24:00\"],\"rcr_type\":[\"0\"],\"rcr_state\":[\"0\"],\"rcr_discount\":[\"1\"],\"car_displacement\":[\"1.6\"],\"start_price\":[\"600\"],\"start_time\":[\"60\"],\"charging\":[\"100\"],\"charging_time\":[\"60\"],\"month_price\":[\"35000\"],\"car_type\":[\"1\"],\"car_code_color\":[\"1\"],\"timeout_info\":[\"起步1小时6.00元，之后1.00元/1小时\"],\"is_time_bucket\":[\"1\"],\"time_bucket\":[\"0:00-24:00\"],\"area_code\":[\"510502\"],\"sign\":[\"c406991d2e246414e77846d96464690f\"]}",
        Map.class);
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.putAll(map);
    String result = new RestTemplate()
        .postForObject("http://app.qc-wbo.com/v1/update_charging_rule.php", params, String.class);
    System.err.println(result);
  }
}
