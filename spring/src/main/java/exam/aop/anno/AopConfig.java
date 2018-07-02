package exam.aop.anno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("exam.aop.anno")
@EnableAspectJAutoProxy//开启对AspectJ支持
public class AopConfig {

	
}
