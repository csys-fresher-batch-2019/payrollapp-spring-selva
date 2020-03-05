package com.chainsys.taskpayrollapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan("com.chainsys.taskpayrollapp.servlet")
//@ComponentScan("com.chainsys.selva")
public class TaskpayrollappApplication extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TaskpayrollappApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(TaskpayrollappApplication.class, args);
	}

}
