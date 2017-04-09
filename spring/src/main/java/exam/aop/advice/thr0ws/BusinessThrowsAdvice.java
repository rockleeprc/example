package exam.aop.advice.thr0ws;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

/**
 * ThrowsAdvice 没有定义任何方法，只是一个标识接口
 *
 * @author Rock.Lee
 *
 */
public class BusinessThrowsAdvice implements ThrowsAdvice {

	/**
	 * 方法必须这样定义
	 * @param method
	 * @param args
	 * @param target 可选
	 * @param ex
	 */
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
		System.out.println("方法名称：" + method.getName());
		System.out.println("异常信息：" + ex.getMessage());
	}
}
