
### Jointpoint，连接点
程序执行的某个特定位置，如类初始化前、初始化后，方法前、方法后

Spring仅支持方法类型的Jointpoint

### Pintcut，切点
一组Jointpoint，Spring通过org.springframework.aop.Pointcut接口进行描述，通过Pointcut表达式描述一组Jointpoint，所以一个Pointcut可以匹配多个Jointpoint

### Advice，增强/通知
对Jointpoint要做的行为，一段程序代码，Spring提供了如BeforeAdvice、AfterReturningAdvice带有方位名称的Advice

### Target，目标对象
实施Advice的对象，被代理的目标类

### Intruduction，引介
一种特殊的Advice，为Target添加一些属性和方法

### Weaving，织入
将Advice应用到Target上的Joinpoint的过程

Spring采用动态代理织入（生成子类），AspectJ采用编译器织入（特殊Java编译器）和装在期织入（特殊的装载器）

### Proxy，代理
被AOP Weaving后的结果类

### Aspect，切面
横切逻辑的定义，有Jointpoint、Advice组成



## ProxyFactoryBean
	
### Advice
	org.springframework.aop.MethodBeforeAdvice
	org.springframework.aop.AfterReturningAdvice
	org.aopalliance.intercept.MethodInterceptor
	org.springframework.aop.ThrowsAdvice
	org.springframework.aop.support.DelegatingIntroductionInterceptor

### Advisor
	
	1. Advisor：代表一个advice
	2. PointcutAdvisor：具有切点的切面，包含Advice和Pointcut
		1. org.springframework.aop.support.DefaultPointcutAdvisor
		2. org.springframework.aop.support.NameMatchMethodPointcutAdvisor
		3. org.springframework.aop.support.RegexpMethodPointcutAdvisor
		4. org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor
		5. org.springframework.aop.aspectj.AspectJPointcutAdvisor
	3. IntroductionAdvisor：引介

### 配置
	<!-- 
		target-ref：被代理的对象，也就是target对象
		proxyInterfaces：被代理对象的接口，也就是target接口
		interfaces：proxyInterfaces的别名
		interceptorNames：advice、advisor实现类，具体的横切逻辑实现
		proxyTargetClass：true=对target进行cglib代理，不使用jdk
		optimize：true：强制使用cglib
	 -->
	<bean id="xxx" class="org.springframework.aop.framework.ProxyFactoryBean"
		p:proxyInterfaces="target interface" p:interceptorNames="xxxadvice"
		p:target-ref="target object" p:proxyTargetClass="true" optimize="true"></bean>


## AutoProxyCreator
	
	org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator
	org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator