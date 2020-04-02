package com.hrms.webapp;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * @author 孔超
 * @date 2020/4/2 19:07
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.hrms.api.service")
public class WebappApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebappApplication.class, args);
    }

}
