package com.elon.hypesphere.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.elon.hypesphere.common.config"  // 显式包含配置类所在包
})
@MapperScan("com.elon.hypesphere.product.mapper")
@EnableDiscoveryClient
public class HypesphereProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(HypesphereProductApplication.class, args);
    }

}
