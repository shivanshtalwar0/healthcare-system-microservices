package com.cg.diagnosticservice.services;

import com.cg.diagnosticservice.model.TestModel;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient("test-management-micro-service")
@RibbonClient("test-management-micro-service")
public interface TestServiceClient {
    @GetMapping("/testoperations/view")
    public List<TestModel> getTest(
            @RequestHeader("Authorization") String authorizationToken);

}
