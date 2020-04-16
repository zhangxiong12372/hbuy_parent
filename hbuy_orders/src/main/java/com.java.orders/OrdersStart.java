package com.java.orders;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *   后台管理模块启动类
 */
@SpringBootApplication(scanBasePackages = "com.java.orders.*")  //启动此模块的注解
@EnableEurekaClient  //启用注册中心客户端
@MapperScan("com.java.orders.mapper")
@EnableScheduling //开启任务调度
public class OrdersStart {

    public static void main(String[] args) {
        SpringApplication.run(OrdersStart.class);
    }
}
