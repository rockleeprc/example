package activemq.queue;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueSender {
	public static void main(String[] args) {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.33.11:61616");
		Connection connection = null;
		try {
			// 创建连接
			connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

			// 创建队列（如果队列已经存在则不会创建， first-queue是队列名称）
			// destination表示目的地
			Destination destination = session.createQueue("first-queue");
			// 创建消息发送者
			MessageProducer producer = session.createProducer(destination);

			TextMessage textMessage = session.createTextMessage("hello, 菲菲,我是帅帅的mic:");
			textMessage.setStringProperty("key", "value");
			producer.send(textMessage);

			// for (int i = 0; i < 10; i++) {
			// TextMessage textMessage = session.createTextMessage("hello,
			// 菲菲,我是帅帅的mic:" + i);
			// producer.send(textMessage);
			// }

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
