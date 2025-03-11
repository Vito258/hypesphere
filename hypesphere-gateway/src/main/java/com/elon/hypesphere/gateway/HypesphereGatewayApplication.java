package com.elon.hypesphere.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


// 网关项目不需要数据库，直接排除
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class HypesphereGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HypesphereGatewayApplication.class, args);
	}

}
