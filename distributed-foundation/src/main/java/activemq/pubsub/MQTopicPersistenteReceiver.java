package activemq.pubsub;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MQTopicPersistenteReceiver {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.33.11:61616");
		Connection connection = null;
		try {
			// 创建连接
			connection = connectionFactory.createConnection();
			connection.setClientID("DUBBO-ORDER"); // 设置持久订阅
			connection.start();

			Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

			// 创建队列（如果队列已经存在则不会创建， first-queue是队列名称）
			// destination表示目的地
			Topic topic = session.createTopic("first-topic");
			// 创建消息接收者
			MessageConsumer consumer = session.createDurableSubscriber(topic, "DUBBO-ORDER");
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
