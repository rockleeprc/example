package activemq.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsAsynConsumer {

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
			
			MessageConsumer messageConsumer = session.createConsumer(destination);
			//这种异步接受“貌似”是ActiveMQ主动的推送消息给消费者，其本质还是消费者轮询消息服务器导致的，只不过这个过程被封装了
			messageConsumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					
					if(message instanceof TextMessage){
						TextMessage textMessage  = (TextMessage) message;
						try {
							System.out.println(textMessage.getText());
						} catch (JMSException e) {
							e.printStackTrace();
						}
					}
				}
			});
			
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
