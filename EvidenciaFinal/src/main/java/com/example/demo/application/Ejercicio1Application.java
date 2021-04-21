package com.example.demo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages= {"com.example.demo.application.controller"})
public class Ejercicio1Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Ejercicio1Application.class, args);
	}

}
