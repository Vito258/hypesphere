package com.elon.hypesphere.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.elon.hypesphere.ware.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.elon.hypesphere.ware.feign")
public class HypesphereWareApplication {
    public static void main(String[] args) {
        SpringApplication.run(HypesphereWareApplication.class, args);
    }

}
