package com.cognizant.accountservice;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.cognizant.accountservice.controller.AccountController;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class AccountserviceApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	public static void main(String[] args) { 
		SpringApplication.run(AccountserviceApplication.class, args);
		LOGGER.info("Account microservice started....");
	}
	
}
