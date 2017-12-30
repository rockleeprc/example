package com.foobar;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bar.api.IBarFacade;
import com.foo.api.IFooFacade;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dubbo/applicationDubbo.xml")
public class AppMain {

	@Autowired
	@Qualifier("fooFacade")
	private IFooFacade fooFacade;

	@Autowired
	@Qualifier("barFacade")
	private IBarFacade barFacade;

	@Test
	public void testFooFacade() {
		fooFacade.foo();
	}

	@Test
	public void testBarFacade() {
		barFacade.bar();
	}

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/applicationDubbo.xml");
		String[] arr = context.getBeanDefinitionNames();
		System.out.println("-----------");
		for (String s : arr) {
			System.out.println(s);
		}
		System.out.println("-----------");

		IFooFacade fooFacade = (IFooFacade) context.getBean("fooFacade");
		fooFacade.foo();
		System.out.println(fooFacade);

		IBarFacade barFacade = (IBarFacade) context.getBean("barFacade");
		barFacade.bar();
		System.out.println(barFacade);

	}

}
