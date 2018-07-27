package exam.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import exam.domain.User;

@Controller
@RequestMapping("/item")
public class ItemController {

	@ModelAttribute
	public void before() {
		System.out.println("before");
	}

	/**
	 * 通过声明HttpServletResponse类型的方法入参，来使用HttpServletResponse对象。
	 * 注意：在Controller中，@RequestMapping注解的方法，在调用这个方法时候，
	 * 如果有定义HttpServletResponse类型的入参，Spring
	 * MVC框架会自动传入一个HttpServletResponse对象作为方法参数；
	 * 如果有定义HttpServletRequest类型的入参，Spring
	 * MVC框架会自动传入一个HttpServletRequest对象作为方法参数。
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/list")
	public void handle1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String auth = request.getHeader("Authorization");
		System.out.println(auth);
		System.out.println("exam.controller.ItemController.m1()");
		response.getWriter().println("<h1>Hello World</h1>");
	}

	/**
	 * 错误的方式：void方法不定义HttpServletResponse类型的入参，HttpServletResponse对象通过RequestContextHolder上下文获取
	 * 注意：这种方式是不可行的，void方法不定义HttpServletResponse类型的入参， Spring
	 * MVC会认为@RequestMapping注解中指定的路径就是要返回的视图name，在本案例中， 页面上访问
	 * http://127.0.0.1:8080/springmvc/item/demo2，接着会将
	 * http://127.0.0.1:8080/springmvc/item/demo2.jsp
	 * 作为此次请求的响应内容
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/demo2", method = RequestMethod.GET)
	private void test2(HttpServletRequest request) {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		try {
			response.getWriter().print("<h1>Hello World</h1>");
		} catch (IOException e) {
		}
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
