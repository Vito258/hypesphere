package com.elon.hypesphere.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.elon.hypesphere.product.mapper")
public class HypesphereProductApplication {
	public static void main(String[] args) {
		SpringApplication.run(HypesphereProductApplication.class, args);
	}

}
