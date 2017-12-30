package bootstrap;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bootstrap {
	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"spring/applicationContext.xml");
			context.start();
			String[] arr = context.getBeanDefinitionNames();
			for (String s : arr) {
				System.out.println(s);
			}
			System.out.println(".............................");
			System.out.println("Provider startup");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
