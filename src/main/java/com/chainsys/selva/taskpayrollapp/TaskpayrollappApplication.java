package com.chainsys.selva.taskpayrollapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
@ServletComponentScan("com.chainsys.selva")
public class TaskpayrollappApplication extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TaskpayrollappApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(TaskpayrollappApplication.class, args);
	}

}
