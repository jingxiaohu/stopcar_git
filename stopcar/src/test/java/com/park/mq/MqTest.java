package com.park.mq;

import com.park.action.BaseWebTest;
import java.util.HashMap;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Peter Wu
 */
public class MqTest extends BaseWebTest {

  @Autowired
  RabbitTemplate rabbitTemplate;
  @Autowired
  RabbitPublisher rabbitPublisher;

  @Test
  public void name() throws Exception {
    HashMap<Object, Object> map = new HashMap<>();
    map.put("type", "1");
    map.put("message", "msggggg");
    Object o = rabbitTemplate.convertSendAndReceive("pda1333", map, new CorrelationData("1234"));
    System.err.println(o);
    Thread.sleep(10 * 1000);
  }

  /**
   * gate.510101.172
   */
  @Test
  public void publish() throws Exception {
    HashMap<Object, Object> map = new HashMap<>();
    map.put("type", "1");
    map.put("data", "msggggg");
    rabbitPublisher.publish2Gate("510101", 172, map, false);
  }
}
