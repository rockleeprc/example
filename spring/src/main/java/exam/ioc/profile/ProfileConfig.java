package exam.ioc.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {

	@Bean
	@Profile("dev")
	public InfoBean dev() {
		return new InfoBean("dev");
	}

	@Bean
	@Profile("prod")
	public InfoBean prod() {
		return new InfoBean("prod");
	}
}
