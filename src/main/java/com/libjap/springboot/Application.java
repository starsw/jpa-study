package com.libjap.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by coupang on 15. 10. 13..
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

	public static void main(String[] args){
		SpringApplication.run(Application.class,args);
		System.out.print("Spring Boot Application Start !");
	}
}
