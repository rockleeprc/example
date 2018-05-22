package exam.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import exam.domain.User;

@Controller
@RequestMapping(value = "/param")
public class ParamController {

	@RequestMapping(value = "/param")
	public String param() {
		return "param/param";
	}

	/*
	 * required=true 默认值,参数不存在,会抛异常
	 */
	@RequestMapping(value = "handle1")
	public ModelAndView handle1(@RequestParam(value = "age", required = true) Integer age,
			@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", name);
		mav.addObject("password", password);
		mav.setViewName("param/result");
		return mav;

	}

	/**
	 * 获取http请求信息
	 * @param sessionId
	 * @param encoding
	 * @param language
	 * @return
	 */
	@RequestMapping(value = "handle2")
	public ModelAndView handle2(@CookieValue(value = "JSESSIONID") String sessionId,
			@RequestHeader(value = "Accept-Encoding") String encoding,
			@RequestHeader(value = "Accept-Language") String language,
			@RequestHeader(value="Referer") String referer) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("sessionId", sessionId);
		mav.addObject("encoding", encoding);
		mav.addObject("language", language);
		mav.addObject("referer", referer);
		mav.setViewName("param/result");
		return mav;

	}

	/**
	 * 使用原生HttpServetAPI获取参数
	 * @param reqeust
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "handle3")
	public ModelAndView handle3(HttpServletRequest reqeust, HttpServletResponse response,HttpSession session) {
		String name = WebUtils.findParameterValue(reqeust, "name");
		String password = WebUtils.findParameterValue(reqeust, "password");
		session.setAttribute("encoding","encoding");
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", name);
		mav.addObject("password", password);
		mav.setViewName("param/result");
		return mav;
	}
	
	/**
	 * 使用代理Servlet原生API接口获取参数
	 * @param request
	 * @return
	 */
	@RequestMapping(value="handle4")
	public ModelAndView handle4(WebRequest request){
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", name);
		mav.addObject("password", password);
		mav.setViewName("param/result");
		return mav;
	}
	
	@RequestMapping(value="handle5")
	public ModelAndView handle4(User user){
		System.out.println(user.getDate());
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", user.getName());
		mav.addObject("password", user.getPassword());
		mav.setViewName("param/result");
		return mav;
	}

}
