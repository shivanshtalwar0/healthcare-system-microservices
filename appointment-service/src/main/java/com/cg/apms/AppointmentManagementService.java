package com.cg.apms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("com.cg.apms.service")
public class AppointmentManagementService {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentManagementService.class, args);
	}
//
//	@Bean 
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
}
