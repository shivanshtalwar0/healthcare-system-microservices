package com.cg.diagnosticservice.services;

import com.cg.diagnosticservice.model.TestModel;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("test-management-micro-service")
@RibbonClient("test-management-micro-service")
public interface TestServiceClient {
    @GetMapping("/testoperations/view")
    public List<TestModel> getTestList(
            @RequestHeader("Authorization") String authorizationToken);

    @GetMapping("/testoperations/{testName}")
    public TestModel getTestByName(
            @PathVariable(name = "testName") String testName,
            @RequestHeader("Authorization") String authorizationToken);


    @PostMapping("/testoperations/add")
    public TestModel createTest(
            @RequestHeader("Authorization") String authorizationToken,TestModel testModel);

}
