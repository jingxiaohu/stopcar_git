package stopcar.test.rabbitmq;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitmqTest {
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


	public static void doInit2() throws Throwable {
		// 创建一个新的连接
		Connection connection = getFactory().newConnection();
		// 创建一个通道
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(Exchange_NAME, BuiltinExchangeType.FANOUT,true);
		
//		channel.queueDeclare(QUEUE_NAME1, durable, false, false, null); //声明消息队列，且为可持久化的
//		channel.queueBind(QUEUE_NAME1, Exchange_NAME, routingKey); 
//		
//		channel.queueDeclare(QUEUE_NAME2, durable, false, false, null); //声明消息队列，且为可持久化的
//		channel.queueBind(QUEUE_NAME2, Exchange_NAME, routingKey); 
		
		for (int i = 0; i < 1; i++) {
			// 声明一个队列 channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			String message = "Hello RabbitMQ jingxiaohu"+" i==="+i;
			// 发送消息到队列中
			BasicProperties messageProperties = new BasicProperties();
			messageProperties.getContentType();
			channel.basicPublish(Exchange_NAME, routingKey, messageProperties, message.getBytes("UTF-8"));
			System.out.println("Producer Send +'" + message + "'" +" i==="+i);
		}
		
		
		// 关闭通道和连接
		channel.close();
		connection.close();
//		Thread.currentThread().join();
	}
	
	public static void main(String[] args) throws Throwable {
		String hostName = "223.85.163.38";
		int portNumber = 5672;
		String userName = "admin";
		String password = "123456";
		String virtualHost = "/";
		doInit( hostName,  portNumber, userName,  password, virtualHost);
		doInit2();
	}

	public final static String QUEUE_NAME1 = "rabbitM123";
	public final static String QUEUE_NAME2 = "rabbitM128";

	public final static String Exchange_NAME = "daozha";
	public static String routingKey = "";
}
