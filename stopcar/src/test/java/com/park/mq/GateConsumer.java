package com.park.mq;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;

public class GateConsumer {


  public static void main(String[] argv) throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("223.85.163.38");
    factory.setUsername("admin");
    factory.setPassword("123456");

    factory.setAutomaticRecoveryEnabled(true);
    factory.setNetworkRecoveryInterval(10000);

    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    channel.exchangeDeclare("stopcar.gate", BuiltinExchangeType.DIRECT, true); //direct fanout topic
//    channel.exchangeDeclare("fExchange", BuiltinExchangeType.FANOUT, true); //direct fanout topic


    channel.queueDeclare("gate.510101.172",true,false,false,null);
    channel.queueBind("gate.510101.172", "stopcar.gate", "gate.510101.172");
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope,
          BasicProperties properties, byte[] body) throws IOException {
//        	super.handleDelivery(consumerTag, envelope, properties, body);
        System.err.println(consumerTag);
        System.err.println(envelope.getDeliveryTag());
        String message = new String(body, "UTF-8");
        System.out.println("Customer Received '" + message + "'");

      }


    };
    channel.basicConsume("gate.510101.172", true, consumer);

  }
}  