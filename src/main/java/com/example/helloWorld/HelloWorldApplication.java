package com.example.helloWorld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

/*
* 组合注解 ： 包含
* @EnableAutoConfiguration
* @ComponentScan
* @SpringBootConfiguration
*
* */
@SpringBootApplication

/**
 * 支持servlet filter listener 自动注册
 */
@ServletComponentScan

/**
 *
 */
@ImportResource(locations = {"classpath:spring-mvc.xml"})
// 开启异步调用
@EnableAsync
// 开启重试
@EnableRetry
public class HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

}
