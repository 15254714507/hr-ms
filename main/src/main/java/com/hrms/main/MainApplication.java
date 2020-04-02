package com.hrms.main;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.hrms.support.service.impl.UserServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


/**
 * @author 孔超
 * @date 2020/4/2 19:07
 */

@SpringBootApplication(scanBasePackages = {"com.hrms.support.*"})
@EnableDubbo(scanBasePackages = "com.hrms.support.service.impl")
@MapperScan(value = "com.hrms.support.dao")
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
