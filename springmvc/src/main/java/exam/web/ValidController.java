package exam.web;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import exam.domain.User;

@Controller
@RequestMapping(value = "/valid")
public class ValidController {
	@RequestMapping("/valid")
	public String register(@ModelAttribute("user") User user) {
		return "valid/regist";
	}
	
	@RequestMapping(value="/regist")
	public String regist(@Valid @ModelAttribute("user") User user,BindingResult bindingResult ){
		if( bindingResult.hasErrors()){
			List<ObjectError> list = bindingResult.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
				System.out.println(error.getCode());
				System.out.println(Arrays.toString(error.getArguments()));
			}
			return "valid/regist";
		}else{
			return "valid/result";
		}
	}
}
