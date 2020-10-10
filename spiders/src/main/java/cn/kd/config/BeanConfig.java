package cn.kd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class BeanConfig {

    @Bean
    public ThreadPoolExecutor threadPool() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                3,
                3,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024));
        return threadPool;
    }
}
