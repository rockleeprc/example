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
		User user = userService.selectByID(19);
		System.out.println(user);
	}

	/**
	 * 查询
	 * 
	 * @throws IOException
	 */
	@Test
	public void selectCache() throws IOException {
		User user = userService.selectByID(1);
		System.out.println(user);
		user = userService.selectByID(1);
		System.out.println(user);
		System.in.read();
	}

	/**
	 * 更新
	 */
	@Test
	public void updateCache() {
		User user = userService.selectByID(1);
		System.out.println(user);
		user.setUserName("刘雪娇");
		int result = userService.update(user);
		System.out.println(result);
		user = userService.selectByID(1);
		System.out.println(user);
	}
	
	@Test
	public void insetCache(){
		User u = new User();
		u.setUserName("bb");
		int result = userService.insert(u);
		System.out.println(result);
	}

}
