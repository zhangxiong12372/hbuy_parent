package com.java.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *   后台管理模块启动类
 */
@SpringBootApplication(scanBasePackages = "com.java.web.*")  //启动此模块的注解
@EnableEurekaClient  //启用注册中心客户端
@MapperScan("com.java.web.mapper")
@ServletComponentScan(basePackages = "com.java.web.filter")  //扫描到过滤器的包
public class WebStart {

    public static void main(String[] args) {
        SpringApplication.run(WebStart.class);
    }
}
