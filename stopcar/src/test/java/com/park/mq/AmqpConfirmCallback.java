package com.park.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * @author Peter Wu
 */
public class AmqpConfirmCallback implements ConfirmCallback {
  private Logger log= LoggerFactory.getLogger(AmqpConfirmCallback.class);

  @Override
  public void confirm(CorrelationData correlationData, boolean ack, String cause) {
    log.debug("correlationData:"+correlationData+",ack:"+ack+",cause:"+cause);
  }
}
