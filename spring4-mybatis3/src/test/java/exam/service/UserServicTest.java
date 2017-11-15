package exam.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import exam.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class UserServicTest {
	@Autowired
	IUserService userService;
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	private static final Logger LOG = LogManager.getLogger(UserServicTest.class);

	/*
	 * log4j2方式 private static final org.apache.logging.log4j.Logger LOG =
	 * org.apache.logging.log4j.LogManager.getLogger(Logstrap.class);
	 * 
	 * slf4j方式 private static final org.slf4j.Logger LOG =
	 * org.slf4j.LoggerFactory.getLogger(LoggerFactory.class);
	 */
	@Test
	public void sqlSession() {
		LOG.info("aa");
		System.out.println(sqlSessionTemplate);
	}

	@Test
	public void select() {
		User user = userService.selectByID(1);
		System.out.println(user);
	}

	@Test
	public void aop() {
		// ： 是否是代理对象；
		System.out.println(AopUtils.isAopProxy(userService));
		// ： 是否是CGLIB方式的代理对象；
		System.out.println(AopUtils.isCglibProxy(userService));
		// ： 是否是JDK动态代理方式的代理对象；
		System.out.println(AopUtils.isJdkDynamicProxy(userService));
	}

}
