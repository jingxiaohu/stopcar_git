package com.park.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MqSender {


  public static void main(String[] argv) throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
//    channel.exchangeDeclare("amqpExchange", BuiltinExchangeType.DIRECT, true); //direct fanout topic
//    channel.exchangeDeclare("fExchange", BuiltinExchangeType.FANOUT, true); //direct fanout topic

    String message = "dddd";
    channel.basicPublish("amqpExchange", "pda1", false,null, message.getBytes());
//    channel.basicPublish("fExchange", "", false,null, message.getBytes());
    channel.close();
    connection.close();
  }
}  