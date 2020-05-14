package com.cg.tms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TestsMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestsMicroServiceApplication.class, args);
	}

}
