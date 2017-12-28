package stopcar.test.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class RabbitmqCustomer2Test {
	// 创建连接工厂
	private static ConnectionFactory factory = null;
	public static final boolean durable=true; //消息队列持久化
	
	public static ConnectionFactory getFactory() {
		if(factory == null ){
			factory = new ConnectionFactory();
		}
		return factory;
	}

	public  static void doInit(String hostName, int portNumber,String userName, String password,String virtualHost) throws Throwable {
		// 设置RabbitMQ相关信息
		getFactory().setUsername(userName);
		getFactory().setPassword(password);
		if(virtualHost != null){
			getFactory().setVirtualHost(virtualHost);
		}
		getFactory().setHost(hostName);
		if(portNumber > 0){
			getFactory().setPort(portNumber);
		}
	}


	public static void customer() throws Throwable{
		 //创建一个新的连接
        Connection connection = getFactory().newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
		channel.exchangeDeclare(Exchange_NAME, BuiltinExchangeType.FANOUT,true);
        //声明要关注的队列
//        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
//        channel.queueBind(QUEUE_NAME, Exchange_NAME, routingKey); 
        System.out.println("Customer_2 Waiting Received messages");
        //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer = new DefaultConsumer(channel) {
        @Override
        public void handleDelivery(String consumerTag, Envelope envelope,
        		BasicProperties properties, byte[] body) throws IOException {
        	// TODO Auto-generated method stub
//        	super.handleDelivery(consumerTag, envelope, properties, body);
        	 String message = new String(body, "UTF-8");
             System.out.println(consumerTag+"Customer_2:Customer Received '" + message + "'");
        }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制
        channel.basicConsume(QUEUE_NAME, true, consumer);
	}
	
	public static void main(String[] args) throws Throwable {
		String hostName = "223.85.163.38";
		int portNumber = 5670;
		String userName = "admin";
		String password = "123456";
		String virtualHost = "/";
		doInit( hostName,  portNumber, userName,  password, virtualHost);
		customer();
	}

	public final static String QUEUE_NAME = "rabbitM124";

	public final static String Exchange_NAME = "daozha";
	public static String routingKey = "";

}
