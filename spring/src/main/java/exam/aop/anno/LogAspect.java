package exam.aop.anno;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect//声明切面
@Component
public class LogAspect {
	@Pointcut("@annotation(exam.aop.anno.Action)")//配置切点
	public void annoationPointcut() {

	}

	@After("annoationPointcut()")
	public void after(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Action annotation = method.getAnnotation(Action.class);
		System.out.println("注解拦截--->" + annotation.name());
	}

	@Before("execution(* exam.aop.anno.MethodService.*(..))")
	public void before(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		System.out.println("方法拦截===>" + method.getName());
	}
}
