package exam.aop.advice;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import exam.aop.advice.before.BusinessBeforeAdvice;
import exam.aop.interfaces.IBusiness;
import exam.aop.interfaces.IIntroductionMethod;
import exam.aop.interfaces.impl.BusinessImpl;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:aop/applicationContext-advice.xml")
public class AdviceAOPTest {
	@Autowired
	@Qualifier("businessIntroductionAdviceProxy")
	IIntroductionMethod businessIntroductionAdviceProxy;

	/**
	 * ThrowsAdvice 基于配置
	 */
	@Test
	public void businessIntroductionAdviceProxy4Conf() {
		businessIntroductionAdviceProxy.thisMethod();
		System.out.println(businessIntroductionAdviceProxy.getClass());
		// businessAroundAdviceProxy.hello("Lee");
	}

	@Autowired
	@Qualifier("businessThrowsAdviceProxy")
	IBusiness businessThrowsAdviceProxy;

	/**
	 * ThrowsAdvice 基于配置
	 */
	@Test
	public void businessThrowsAdviceProxy4Conf() {
		businessThrowsAdviceProxy.thr0ws();
	}

	@Autowired
	@Qualifier("businessAroundAdviceProxy")
	IBusiness businessAroundAdviceProxy;

	/**
	 * MethodInterceptor 基于配置
	 */
	@Test
	public void aroundAdviceProxy4Conf() {
		/*
		 * 配置 p:proxyTargetClass="true" 使用cglib创建代理
		 */
		System.out.println(businessAroundAdviceProxy.getClass());
		businessAroundAdviceProxy.hello("Rock.lee");
	}

	@Autowired
	@Qualifier("afterReturningAdviceProxy")
	IBusiness afterReturningAdviceProxy;

	/**
	 * AfterReturningAdvice 基于配置
	 */
	@Test
	public void afterReturningAdviceProxy4Conf() {
		afterReturningAdviceProxy.doReturn();
	}

	@Autowired
	@Qualifier("beforeAdviceProxy")
	IBusiness beforeAdviceProxy;

	/**
	 * BeforeAdvice 基于配置
	 * 
	 * 使用ProxyFactoryBean implements FactoryBean<Object>
	 * 负责为其它bean创建代理对象，内部使用ProxyFactory
	 */
	@Test
	public void beforeAdvice4Conf() {
		beforeAdviceProxy.doSomething();
	}

	/**
	 * BeforeAdvice 基于jdk代理，硬编码
	 * 
	 * JdkDynamicAopProxy创建
	 * 
	 * Spring配置见ProxyFactoryBean
	 */
	@Test
	public void beforeAdvice4Jdk() {
		IBusiness bus = new BusinessImpl();
		BeforeAdvice advice = new BusinessBeforeAdvice();

		// spring提供的代理工厂
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(bus);
		pf.addAdvice(advice);
		pf.setInterfaces(bus.getClass().getInterfaces());// 使用jdk代理，指定接口

		IBusiness proxy = (IBusiness) pf.getProxy();
		proxy.doSomething();
		System.out.println(proxy.getClass().getName());
	}

	/**
	 * BeforeAdvice 基于cglib代理，硬编码
	 * 
	 * CglibAopProxy创建代理
	 */
	@Test
	public void beforeAdvice4Cglib() {
		IBusiness bus = new BusinessImpl();
		BeforeAdvice advice = new BusinessBeforeAdvice();

		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(bus);
		pf.addAdvice(advice);
		pf.setOptimize(true);// 针对代理进行优化，接口也会使用cglib

		IBusiness proxy = (IBusiness) pf.getProxy();
		proxy.doSomething();
		System.out.println(proxy.getClass().getName());
	}
	
}
