package com.example.fund;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FundManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundManagementApplication.class, args);
	}

}
