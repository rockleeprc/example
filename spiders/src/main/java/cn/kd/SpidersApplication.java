package cn.kd;

import cn.kd.service.WholeProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpidersApplication {
    private static Logger logger = LoggerFactory.getLogger(SpidersApplication.class);

    public static void main(String[] args) {
        /*
        SpringApplication.run(SpidersApplication.class, args);

        SpringApplication application = new SpringApplication(SpidersApplication.class);
        application.setBannerMode(Banner.Mode.CONSOLE);
        application.run(args);
        */

        ConfigurableApplicationContext context = new SpringApplicationBuilder(SpidersApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        WholeProcessService wholeProcessService = context.getBean(WholeProcessService.class);
        wholeProcessService.invokeWholeProcessTask();


        context.close();

    }
}
