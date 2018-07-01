package exam.ioc.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component("exam.ioc.beanlife")
public class ProPostConfig {

	@Bean(initMethod = "init", destroyMethod = "destroy")
	public BeanLife beanLife() {
		return new BeanLife();
	}

	@Bean
	public JSR250Life jsr250Life() {
		return new JSR250Life();
	}

}
