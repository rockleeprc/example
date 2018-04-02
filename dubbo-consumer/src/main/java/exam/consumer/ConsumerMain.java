package exam.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import exam.api.IProvider;

public class ConsumerMain {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");

		IProvider privider = (IProvider) context.getBean("provider");

		int result = privider.plus(1, 2);

		System.out.println(result);

	}
}
