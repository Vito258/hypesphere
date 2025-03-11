package com.elon.hypesphere.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.elon.hypesphere.member.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.elon.hypesphere.member.feign")
public class HypesphereMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(HypesphereMemberApplication.class, args);
    }

}
