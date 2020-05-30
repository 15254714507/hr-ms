package com.hrms.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 静态资源映射
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //因为存在了项目那，所以映射的是绝对路径
        registry.addResourceHandler("/head_shot/**").addResourceLocations("file:D:\\IDEAworkspace\\hr-ms\\webapp\\src\\main\\resources\\static\\head_shot\\");
    }
 
}