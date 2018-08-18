package com.springdemo.springrestdochateos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Arrays;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.springdemo.springrestdochateos.repositories")
@EntityScan(basePackages = "com.springdemo.springrestdochateos.entities")
@EnableAsync
@EnableCaching
@EnableSwagger2
public class SpringRestDocHateosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestDocHateosApplication.class, args);
	}
}
