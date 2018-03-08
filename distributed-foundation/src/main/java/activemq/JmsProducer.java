package activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsProducer {

	public static void main(String[] args) {
		// activemq.xml中配置指定的用户、密码才能访问ActiveMQ
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
				ActiveMQConnectionFactory.DEFAULT_PASSWORD, ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL);
		
		Connection connection = null;
		try {
			//创建连接，默认是关闭状态
			connection= connectionFactory.createConnection();
			connection.start();
			
			//创建session，单线程
			Session session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
			
			//消息发送和接受的地点，要么queue，要么topic
			Destination destination = session.createQueue("mq-queue");
			
			//创建MessageProducer，并设置持久化方式，默认持久
			MessageProducer messageProducer = session.createProducer(destination);
			messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			//jms规范定义5中消息类型StreamMessage、MapMessage、TextMessage、ObjectMessage、BytesMessage
			//定义messsage类型，并发送
			TextMessage message  = session.createTextMessage();
			message.setText("this is TextMessage");
			messageProducer.send(message);
		} catch (JMSException e) {
			e.printStackTrace();
		}finally{
			if(connection!=null)
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
		}

	}

	public static void defaultField() {
		System.out.println(ActiveMQConnectionFactory.DEFAULT_USER);
		System.out.println(ActiveMQConnectionFactory.DEFAULT_PASSWORD);
		System.out.println(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
		System.out.println(ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL);
		System.out.println(ActiveMQConnectionFactory.DEFAULT_PRODUCER_WINDOW_SIZE);
	}

}
