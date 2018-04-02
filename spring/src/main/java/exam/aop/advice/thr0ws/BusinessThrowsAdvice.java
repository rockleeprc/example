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
	 * ThrowsAdvice没有定义任何方法，此接口为标记接口<br/> 
	 * 方法名必须定义为 afterThrowing()
	 * 可选[Method method, Object[] args, Object target]
	 * 最后一个参数必须为Throwable或子类
	 * 
	 * @param method
	 * @param args
	 * @param target
	 * @param ex
	 */
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
		System.out.println("方法名称：" + method.getName());
		System.out.println("异常信息：" + ex.getMessage());
	}
}
