package com.hrms.webapp;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;


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
    @Autowired
    private MongoDbFactory mongoDbFactory;

    @Bean
    public GridFSBucket getGridFSBuckets() {
        MongoDatabase db = mongoDbFactory.getDb();
        return GridFSBuckets.create(db);
    }
}
