package activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicPersistenteReceiver {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.33.11:61616");
		Connection connection = null;
		try {
			connection = connectionFactory.createConnection();
			//注册consumer，需要先运行一次，完成注册
			connection.setClientID("DUBBO-ORDER"); 

			Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

			Topic topic = session.createTopic("first-topic");
			TopicSubscriber consumer = session.createDurableSubscriber(topic, "randomName");
			connection.start();
			TextMessage textMessage = (TextMessage) consumer.receive();
			System.out.println(textMessage.getText());

			session.commit();
			session.close();
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
