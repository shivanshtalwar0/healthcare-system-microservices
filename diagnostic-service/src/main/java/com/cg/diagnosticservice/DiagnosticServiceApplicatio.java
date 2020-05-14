package com.cg.diagnosticservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("com.cg.diagnosticservice.services")
public class DiagnosticServiceApplicatio {
    public static void main(String[] args) {
        SpringApplication.run(DiagnosticServiceApplicatio.class, args);
    }
}
