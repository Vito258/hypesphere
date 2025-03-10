package com.elon.hypesphere.coupon;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.elon.hypesphere.coupon.mapper")
public class HypesphereCouponApplication {

	public static void main(String[] args) {
		SpringApplication.run(HypesphereCouponApplication.class, args);
	}

}
