package com.java.solr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *   搜索模块启动类
 */
@SpringBootApplication(scanBasePackages = "com.java.solr.*")  //启动此模块的注解
@EnableEurekaClient  //启用注册中心客户端
@MapperScan("com.java.solr.mapper")
@EnableScheduling
public class SolrStart {

    public static void main(String[] args) {
        SpringApplication.run(SolrStart.class);
    }
}
