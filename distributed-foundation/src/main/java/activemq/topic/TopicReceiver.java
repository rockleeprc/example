package activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicReceiver {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.33.11:61616");
		Connection connection = null;
		try {
			connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createTopic("first-topic");

			MessageConsumer consumer = session.createConsumer(destination);
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
