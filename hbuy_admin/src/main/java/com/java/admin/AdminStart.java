package com.java.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *   后台管理模块启动类
 */
@SpringBootApplication  //启动此模块的注解
@EnableEurekaClient  //启用注册中心客户端
@MapperScan("com.java.admin.mapper") //mapper接口扫描
@ServletComponentScan(basePackages = "com.java.admin.filter")  //扫描到过滤器的包
public class AdminStart {

    public static void main(String[] args) {
        SpringApplication.run(AdminStart.class);
    }
}
