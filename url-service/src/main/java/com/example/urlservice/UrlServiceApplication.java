package com.example.urlservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication

@EnableDubbo

public class UrlServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlServiceApplication.class, args);

	}

}
