package com.kaishengit.hystrixdashbord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixdashbordApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixdashbordApplication.class, args);
	}
}
