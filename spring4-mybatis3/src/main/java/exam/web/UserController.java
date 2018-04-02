package exam.web;

import java.util.List;

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

	@RequestMapping("/findById")
	public ModelAndView findById(Integer id) {
		User user = userService.selectByID(id);
		ModelAndView modelAndView = new ModelAndView("user_edit");
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	@RequestMapping("/toInsertPage")
	public ModelAndView toInsertPage(){
		ModelAndView modelAndView = new ModelAndView("user_insert");
		return modelAndView;
	}

	@RequestMapping("/deleteById")
	public ModelAndView deleteById(Integer id) {
		userService.delete(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/user/findAll.action");
		return modelAndView;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(User user) {
		int result = userService.insert(user);
		ModelAndView modelAndView = new ModelAndView(result > 0 ? "redirect:/user/findAll.action" : "user_insert");
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(User user) {
		int result = userService.update(user);
		ModelAndView modelAndView = new ModelAndView(result > 0 ? "redirect:/user/findAll.action" : "user_edit");
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public ModelAndView findAll() {
		ModelAndView modelAndView = new ModelAndView("user_manager");
		List<User> users = userService.findAll();
		modelAndView.addObject("users", users);
		return modelAndView;
	}
}