package com.park.mq;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.BasicProperties.Builder;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.HashMap;

public class MqConsumer {


  public static void main(String[] argv) throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    final Channel channel = connection.createChannel();
    channel.exchangeDeclare("amqpExchange", BuiltinExchangeType.DIRECT, true); //direct fanout topic
//    channel.exchangeDeclare("fExchange", BuiltinExchangeType.FANOUT, true); //direct fanout topic
    channel.queueDeclare("pda.1",true,false,false,null);
    channel.queueBind("pda.1", "amqpExchange", "pda1");
    channel.queueBind("pda.1", "amqpExchange", "pda");
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope,
          BasicProperties properties, byte[] body) throws IOException {
//        	super.handleDelivery(consumerTag, envelope, properties, body);
        System.err.println(consumerTag);
        String message = new String(body, "UTF-8");
        System.out.println("Customer Received '" + message + "'");

        //处理结果
        System.err.println(properties.toString());
        System.err.println(JSON.toJSONString(properties));
        BasicProperties builder = new Builder().contentType("application/json").build();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("message", "客户端处理成功");

        String retrunmsg = JSON.toJSONString(map);
        channel.basicPublish("", properties.getReplyTo(), false,builder, retrunmsg.getBytes());

      }
    };
    channel.basicConsume("pda.1", true, consumer);

  }
}  