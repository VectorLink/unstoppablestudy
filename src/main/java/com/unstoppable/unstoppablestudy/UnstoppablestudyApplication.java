package com.unstoppable.unstoppablestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableTransactionManagement
@EnableOpenApi
@EnableDiscoveryClient
@EnableConfigurationProperties
public class UnstoppablestudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnstoppablestudyApplication.class, args);
	}

}
