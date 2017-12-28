package com.park.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 消息队列发布工具
 *
 * @author Peter Wu
 */
@Component
public class RabbitPublisher {

  private Logger log = LoggerFactory.getLogger(RabbitPublisher.class);
  @Value("${mq.exchange.gate}")
  private String gateExchange;
  @Value("${mq.exchange.pda}")
  private String pdaExchange;
  @Autowired(required = false)
  private RabbitTemplate rabbitTemplate;


  /**
   * 发布消息到停车场
   *
   * @param area_code 省市县编号
   * @param pi_id 停车场id
   * @param msg 消息对象
   * @param persistent 是否持久化
   */
  public void publish2Gate(String area_code, long pi_id, Object msg, final boolean persistent) {
    if (rabbitTemplate != null) {
      log.info("推送MQ消息：{}|{}|{}:{}", gateExchange, area_code, pi_id, msg);
      rabbitTemplate.convertAndSend(gateExchange, getGateRoutingKey(area_code, pi_id), msg,
          new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
              if (persistent) {
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
              }
              return message;
            }
          });
    }
  }

  /**
   * 道闸路由KEY
   */

  private String getGateRoutingKey(String area_code, long pi_id) {
    return "gate." + area_code + "." + pi_id;
  }


  /**
   * 停车场 RPC调用
   *
   * @param area_code 省市县编号
   * @param pi_id 停车场id
   * @param msg 消息对象
   * @param persistent 是否持久化
   * @return 停车场返回信息
   */
  public Object rpc2Gate(String area_code, long pi_id, Object msg, final boolean persistent) {
    if (rabbitTemplate != null) {
      log.info("推送MQ消息：{}|{}|{}:{}", gateExchange, area_code, pi_id, msg);
      return rabbitTemplate
          .convertSendAndReceive(gateExchange, getGateRoutingKey(area_code, pi_id), msg,
              new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                  if (persistent) {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                  }
                  return message;
                }
              });
    } else {
      return null;
    }
  }

  /**
   * 发布消息到停车场
   *
   * @param area_code 省市县编号
   * @param pi_id 停车场id
   * @param msg 消息对象
   * @param msgId 消息ID
   * @param persistent 是否持久化
   */
  public void publish2Gate(String area_code, long pi_id, Object msg, String msgId,
      final boolean persistent) {
    if (rabbitTemplate != null) {
      log.info("推送MQ消息：{}|{}|{}:{}", gateExchange, area_code, pi_id, msg);
      rabbitTemplate.convertAndSend(gateExchange, getGateRoutingKey(area_code, pi_id), msg,
          new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
              if (persistent) {
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
              }
              return message;
            }
          }, new CorrelationData(msgId));
    }
  }

  /**
   * 停车场 RPC调用
   *
   * @param area_code 省市县编号
   * @param pi_id 停车场id
   * @param msg 消息对象
   * @param msgId 消息ID
   * @param persistent 是否持久化
   * @return 停车场返回信息
   */
  public Object rpc2Gate(String area_code, long pi_id, Object msg, String msgId,
      final boolean persistent) {
    if (rabbitTemplate != null) {
      log.info("推送MQ消息：{}|{}|{}:{}", gateExchange, area_code, pi_id, msg);
      return rabbitTemplate
          .convertSendAndReceive(gateExchange, getGateRoutingKey(area_code, pi_id), msg,
              new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                  if (persistent) {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                  }
                  return message;
                }
              }, new CorrelationData(msgId));
    } else {
      return null;
    }
  }

  /**
   * 发布消息到PDA
   *
   * @param mac PDA唯一标识符
   * @param msg 消息对象
   * @param persistent 是否持久化
   */
  public void publish2PDA(String mac, Object msg, final boolean persistent) {
    if (rabbitTemplate != null) {
      log.info("推送MQ消息：{}|{}:{}", pdaExchange, mac, msg);
      rabbitTemplate.convertAndSend(pdaExchange, "pda." + mac, msg, new MessagePostProcessor() {
        @Override
        public Message postProcessMessage(Message message) throws AmqpException {
          if (persistent) {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
          }
          return message;
        }
      });
    }
  }

  /**
   * PDA RPC调用
   *
   * @param mac PDA唯一标识符
   * @param msg 消息对象
   * @param persistent 是否持久化
   * @return PDA返回信息
   */
  public Object rpc2PDA(String mac, Object msg, final boolean persistent) {
    if (rabbitTemplate != null) {
      log.info("推送MQ消息：{}|{}:{}", pdaExchange, mac, msg);
      return rabbitTemplate
          .convertSendAndReceive(pdaExchange, "pda." + mac, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
              if (persistent) {
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
              }
              return message;
            }
          });
    }
    return null;
  }

  /**
   * 发布消息到PDA
   *
   * @param mac PDA唯一标识符
   * @param msg 消息对象
   * @param persistent 是否持久化
   * @param msgId 消息ID
   */
  public void publish2PDA(String mac, Object msg, String msgId, final boolean persistent) {
    if (rabbitTemplate != null) {
      log.info("推送MQ消息：{}|{}:{}", pdaExchange, mac, msg);
      rabbitTemplate.convertAndSend(pdaExchange, "pda." + mac, msg, new MessagePostProcessor() {
        @Override
        public Message postProcessMessage(Message message) throws AmqpException {
          if (persistent) {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
          }
          return message;
        }
      }, new CorrelationData(msgId));
    }
  }

  /**
   * PDA RPC调用
   *
   * @param mac PDA唯一标识符
   * @param msg 消息对象
   * @param msgId 消息ID
   * @param persistent 是否持久化
   * @return PDA返回信息
   */
  public Object rpc2PDA(String mac, Object msg, String msgId, final boolean persistent) {
    if (rabbitTemplate != null) {
      log.info("推送MQ消息：{}|{}:{}", pdaExchange, mac, msg);
      return rabbitTemplate
          .convertSendAndReceive(pdaExchange, "pda." + mac, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
              if (persistent) {
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
              }
              return message;
            }
          }, new CorrelationData(msgId));
    } else {
      return null;
    }
  }
}
