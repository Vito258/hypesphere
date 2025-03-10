package com.elon.hypesphere.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.elon.hypesphere.order.mapper")
public class HypesphereOrderApplication {
	public static void main(String[] args) {
		SpringApplication.run(HypesphereOrderApplication.class, args);
	}
}
