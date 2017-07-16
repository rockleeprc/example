package exam.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import exam.domain.User;


@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/register")
	public String register() {
		return "user/register";
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ModelAndView add(User user) {
		System.out.println(user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/suc");
		mav.addObject("user", user);
		return mav;

	}
}
