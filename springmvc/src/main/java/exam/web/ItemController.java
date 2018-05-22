package exam.web;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import exam.domain.User;

@Controller
@RequestMapping("/item")
public class ItemController {

	@RequestMapping("/list")
	public void handle1() {
		System.out.println("exam.controller.ItemController.m1()");
	}

	/**
	 * url参数绑定
	 * 
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/{itemId}")
	public ModelAndView handle2(@PathVariable("itemId") String itemId) {
		System.out.println(itemId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("item");
		return mav;
	}

	/**
	 * 向客户端写出出文件
	 * 
	 * @param os
	 * @throws IOException
	 */
	@RequestMapping("/image")
	public void handle3(OutputStream os) throws IOException {
		ClassPathResource resource = new ClassPathResource("log4j2.xml");
		System.out.println(resource.getPath());
		FileCopyUtils.copy(resource.getInputStream(), os);
	}

	/**
	 * 返回json数据，需要配置json转换器
	 * 
	 * @return
	 */
	@RequestMapping("/json")
	@ResponseBody
	public User handle4() {
		User u = new User();
		u.setName("liyan");
		u.setAge(18);
		return u;
	}

	/**
	 * @ModelAttribute将pojo添加到reqeust.setAttribute()中 @param user
	 * @return
	 */
	@RequestMapping(value = "/view1")
	public String view1(@ModelAttribute("user") User user) {
		user.setName("aa");
		user.setAge(18);
		return "item";
	}

	@RequestMapping(value = "/view2")
	public String view2(ModelMap map) {
		User user = new User();
		user.setName("aa");
		user.setAge(18);
		map.put("user", user);
		return "item";
	}

}
