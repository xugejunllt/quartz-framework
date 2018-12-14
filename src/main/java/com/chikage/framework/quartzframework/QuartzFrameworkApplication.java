package com.chikage.framework.quartzframework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.chikage.framework.quartzframework.**.mapper")
@ComponentScan("com.chikage.framework.quartzframework")
public class QuartzFrameworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzFrameworkApplication.class, args);
    }

}

