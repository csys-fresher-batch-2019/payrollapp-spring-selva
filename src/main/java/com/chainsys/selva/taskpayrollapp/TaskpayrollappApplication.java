package com.chainsys.selva.taskpayrollapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
@ServletComponentScan("com.chainsys.selva")
public class TaskpayrollappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskpayrollappApplication.class, args);
	}

}
