package exam.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import exam.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {  "classpath:spring/applicationContext.xml" })
public class UserServicTest {
	@Autowired
	IUserService userService;

	@Test
	public void select() {
		User user = userService.selectByID(1);
		System.out.println(user);
	}

}
