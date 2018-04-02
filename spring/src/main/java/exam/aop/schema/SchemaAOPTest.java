package exam.aop.schema;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import exam.aop.aspectj.aspect.GreetingBeforeAspect;
import exam.aop.interfaces.IWaiter;
import exam.aop.interfaces.impl.NaiveWaiter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:aop/applicationContext-schema2.xml")
public class SchemaAOPTest {

	@Qualifier("waiter")
	@Autowired
	IWaiter waiter;

	@Test
	public void schemaProxy4Config() {

		// waiter.greetTo("jack");
//		waiter.greetTo("tom");
		waiter.serveTo("marry");
	}

}
