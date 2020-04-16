package com.java.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *   单点登录启动类
 */
@SpringBootApplication(scanBasePackages = "com.java.sso.*")  //启动此模块的注解
@EnableEurekaClient  //启用注册中心客户端
@MapperScan("com.java.sso.mapper")
@ServletComponentScan(basePackages = "com.java.sso.filter")
public class SsoStart {

    public static void main(String[] args) {
        SpringApplication.run(SsoStart.class);
    }
}
