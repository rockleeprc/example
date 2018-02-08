package exam.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import exam.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class MybatisCacheTest {
	@Autowired
	IUserService userService;

	@Test
	public void delete(){
		int id = 19;
		User user = userService.selectByID(id);
		System.out.println(user);
		user = userService.selectByID(id);
		System.out.println(user);
		
		int result = userService.delete(id);
		System.out.println("delete() result="+result);
		
		user = userService.selectByID(id);
		System.out.println(user);
		
	}
	
	@Test
	public void update() {
		User user = userService.selectByID(1); 
		System.out.println(user);
		user.setUserName("孙艺珍");
		int result = userService.update(user);
		System.out.println("update() result = "+result);
		user = userService.selectByID(1);
		System.out.println(user);
		
	}

	@Test
	public void select() {
		User user = userService.selectByID(2);
		System.out.println(user);
		user = userService.selectByID(2);
		System.out.println(user);
	}
}
