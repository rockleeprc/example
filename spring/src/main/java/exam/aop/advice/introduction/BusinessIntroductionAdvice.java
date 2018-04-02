package exam.aop.advice.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import exam.aop.interfaces.IIntroductionMethod;

/**
 * BusinessIntroductionAdvice创建代理时，cglib创建代理的时间较长，
 * 保持BusinessIntroductionAdvice为singleton，类中变量用ThreadLocal实现，效率更高
 */
public class BusinessIntroductionAdvice extends DelegatingIntroductionInterceptor implements IIntroductionMethod {
	private static final long serialVersionUID = -8213962377263745715L;

	/**
	 * 通过实现接口方法，在目标类织入新的方法
	 */
	@Override
	public void thisMethod() {
		System.out.println("引介通知的方法");
	}

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		System.out.println("BusinessIntroductionAdvice starting ...");
		return super.invoke(mi);
	}

}
