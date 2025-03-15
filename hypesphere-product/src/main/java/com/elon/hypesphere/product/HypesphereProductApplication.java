package com.elon.hypesphere.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.elon.hypesphere.product.mapper")
@EnableDiscoveryClient
public class HypesphereProductApplication {
	public static void main(String[] args) {
		SpringApplication.run(HypesphereProductApplication.class, args);
	}

}
