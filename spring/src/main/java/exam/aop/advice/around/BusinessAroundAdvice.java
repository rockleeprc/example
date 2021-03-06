package exam.aop.advice.around;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * MethodInterceptor 来之aopalliance，不在spring包中
 *
 * @author Rock.Lee
 *
 */
public class BusinessAroundAdvice implements MethodInterceptor {

	/**
	 * @param 封装目标类所有信息
	 */
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// 方法参数
		Object[] arguments = invocation.getArguments();
		String name = (String) arguments[0];
		System.out.println("参数:" + name);
		System.out.println("环绕前");
		Object obj = invocation.proceed();
		System.out.println("环绕后");
		return obj;
	}

}
