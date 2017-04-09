package exam.aop.advice.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import exam.aop.interfaces.IIntroductionMethod;

public class BusinessIntroductionAdvice extends DelegatingIntroductionInterceptor implements IIntroductionMethod {

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