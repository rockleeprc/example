package exam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import exam.pojo.User;
import exam.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping("/findUser")
	public void findUser() {
		User user = userService.selectByID(1);
		System.out.println(user);
	}

	@RequestMapping("/register")
	public String register() {
		System.out.println("UserController.register()");
		return "user/register";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(User user) {
		System.out.println(user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/suc");
		mav.addObject("user", user);
		return mav;

	}
}