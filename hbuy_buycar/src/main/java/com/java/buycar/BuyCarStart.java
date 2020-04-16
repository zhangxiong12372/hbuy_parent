package com.java.buycar;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *   购物车模块启动类
 */
@SpringBootApplication(scanBasePackages = "com.java.buycar.*")  //启动此模块的注解
@EnableEurekaClient  //启用注册中心客户端
@ServletComponentScan(basePackages = "com.java.buycar.filter")
@MapperScan("com.java.buycar.mapper")
public class BuyCarStart {

    public static void main(String[] args) {
        SpringApplication.run(BuyCarStart.class);
    }
}
