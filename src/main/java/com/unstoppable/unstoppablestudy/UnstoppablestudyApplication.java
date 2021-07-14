package com.unstoppable.unstoppablestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableTransactionManagement
@EnableOpenApi
public class UnstoppablestudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnstoppablestudyApplication.class, args);
	}

}
