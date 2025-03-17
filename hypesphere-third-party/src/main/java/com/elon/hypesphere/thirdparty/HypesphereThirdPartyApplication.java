package com.elon.hypesphere.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@ComponentScan(basePackages = {
		"com.elon.hypesphere.common.config",  // 显式包含配置类所在包
		"com.elon.hypesphere.thirdparty.controller" // 显式包含控制器所在包
})
@SpringBootApplication
public class HypesphereThirdPartyApplication {
	public static void main(String[] args) {
		SpringApplication.run(HypesphereThirdPartyApplication.class, args);
	}

}
