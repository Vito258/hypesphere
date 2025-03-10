package com.elon.hypesphere.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.elon.hypesphere.member.mapper")
public class HypesphereMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(HypesphereMemberApplication.class, args);
    }

}
