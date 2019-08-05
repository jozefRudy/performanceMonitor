package com.quantconsult.performanceReview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class PerformanceMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerformanceMonitorApplication.class, args);
	}

}
