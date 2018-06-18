package exam.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import exam.domain.User;

@Controller
@RequestMapping("/user")
public class UserController implements EnvironmentAware {

	private Environment environment;

	@RequestMapping("/register")
	public String register(HttpServletRequest reqeust) {
		/**
		 * org.springframework.web.servlet.FrameworkServlet.initWebApplicationContext()
		 * WebApplicationContext wac = (WebApplicationContext) reqeust.getServletContext().getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcherServlet");
		 */
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

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
}
