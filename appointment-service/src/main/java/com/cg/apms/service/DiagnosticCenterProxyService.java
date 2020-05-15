package com.cg.apms.service;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.apms.model.DiagnosticCenterDto;
import com.cg.apms.model.TestModel;

// import com.cg.apms.model.DiagnosticCenterModel;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="diagnostic-service")
@RibbonClient(name="diagnostic-service")
public interface DiagnosticCenterProxyService {
        @GetMapping("/diagnosticCenter")
        public List<DiagnosticCenterDto> getAllCenters(
                @RequestHeader("Authorization") String authorizationToken);
                
        @GetMapping("/diagnosticCenter/{centerId}/test")
        public List<TestModel> getAllTest(@RequestHeader("Authorization") String authorizationToken,@PathVariable(name = "centerId") long centerId);
}
