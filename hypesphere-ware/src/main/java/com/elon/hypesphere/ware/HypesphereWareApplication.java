package com.elon.hypesphere.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.elon.hypesphere.ware.mapper")
public class HypesphereWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(HypesphereWareApplication.class, args);
    }

}
