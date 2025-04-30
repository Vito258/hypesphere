package com.elon.hypesphere.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.elon.hypesphere.product.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.elon.hypesphere.product.feign")
public class HypesphereProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(HypesphereProductApplication.class, args);
    }

}
