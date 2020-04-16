package com.java.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *   注册中心1的启动类
 */
@SpringBootApplication  //springboot的启动注解
@EnableEurekaServer  //启动注册中的服务
public class EurekaStart1 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaStart1.class);
    }
}
