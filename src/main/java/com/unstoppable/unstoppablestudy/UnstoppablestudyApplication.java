package com.unstoppable.unstoppablestudy;

import com.alibaba.fastjson.JSON;
import com.unstoppable.unstoppablestudy.constants.FileProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

@SpringBootApplication
@EnableTransactionManagement
@EnableOpenApi
//@EnableDiscoveryClient
@EnableConfigurationProperties
@Slf4j
public class UnstoppablestudyApplication {

	public static ApplicationContext applicationContext;
	public static StandardEnvironment standardEnvironment;

	public static void main(String[] args) throws IOException {
		SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(UnstoppablestudyApplication.class);
		Properties properties = getProperteis();
		StandardEnvironment environment = new StandardEnvironment();
		environment.getPropertySources().addLast(new PropertiesPropertySource("system", properties));
		springApplicationBuilder.environment(environment);
		standardEnvironment = environment;
		springApplicationBuilder.run(args);
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	public static StandardEnvironment getStandardEnvironment() {
		return standardEnvironment;
	}

	public static Properties getProperteis() throws FileNotFoundException {
		String url="D://test.properties";
		Properties props = new Properties();
		FileInputStream fileInputStream=new FileInputStream(url);
		try {
			props.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info(JSON.toJSONString(props));
		return props;


	}
}
