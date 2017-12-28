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

public class MqConsumer2 {


  public static void main(String[] argv) throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    channel.exchangeDeclare("amqpExchange", BuiltinExchangeType.DIRECT, true); //direct fanout topic
//    channel.exchangeDeclare("fExchange", BuiltinExchangeType.FANOUT, true); //direct fanout topic
    channel.queueDeclare("pda.2",true,false,false,null);
    channel.queueBind("pda.2", "amqpExchange", "pda2");
    channel.queueBind("pda.2", "amqpExchange", "pda");
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
    channel.basicConsume("pda.2", false, consumer);

  }
}  