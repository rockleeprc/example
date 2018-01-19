package exam.cache;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import exam.pojo.User;
import exam.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class AutoLoadCacheTest {
	@Autowired
	IUserService userService;
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Test
	public void select() throws IOException {
		User user = userService.selectByID(1);
		System.out.println(user);
	}

	@Test
	public void selectCache() throws IOException {
		User user = userService.selectByID(1);
		System.out.println(user);
		user = userService.selectByID(1);
		System.out.println(user);
		System.in.read();
	}

}
