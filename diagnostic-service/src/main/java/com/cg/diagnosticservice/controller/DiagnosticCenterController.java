package com.cg.diagnosticservice.controller;

import com.cg.diagnosticservice.dao.DiagnosticCenterTestsRepository;
import com.cg.diagnosticservice.entity.DiagnosticCenter;
import com.cg.diagnosticservice.dao.DiagnosticCenterRepository;
import com.cg.diagnosticservice.dto.DiagnosticCenterDto;
import com.cg.diagnosticservice.dto.RestResponse;
import com.cg.diagnosticservice.entity.DiagnosticCenterTests;
import com.cg.diagnosticservice.exceptions.DiagnosticCenterNotFoundException;
import com.cg.diagnosticservice.model.TestModel;
import com.cg.diagnosticservice.services.TestServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("diagnosticCenter")
public class DiagnosticCenterController {

    @Autowired
    DiagnosticCenterRepository diagnosticCenterRepository;
    @Autowired
    DiagnosticCenterTestsRepository diagnosticCenterTestsRepository;

    @Autowired
    TestServiceClient testServiceClient;


    @PostMapping("/{centerId}/test/{testId}")
    ResponseEntity<?> addTestToDiagnosticCenter(@PathVariable(name = "centerId") long centerId, @PathVariable(name = "testId") long testId) {
        DiagnosticCenterTests diagnosticCenterTests = new DiagnosticCenterTests();
        diagnosticCenterTests.setCenterId(centerId);
        diagnosticCenterTests.setTestId(testId);
        return ResponseEntity.ok(diagnosticCenterTestsRepository.save(diagnosticCenterTests));
    }

    @GetMapping("/{centerId}/test")
    ResponseEntity<?> getTestForDiagnosticCenter(HttpServletRequest request, @PathVariable(name = "centerId") long centerId) {
        List<TestModel> testModelList = testServiceClient.getTest(request.getHeader("Authorization"));
        Optional<List<DiagnosticCenterTests>> diagnosticCenterTests = diagnosticCenterTestsRepository.findByCenterId(centerId);
        if (diagnosticCenterTests.isPresent()) {
            List<Long> testIdList = diagnosticCenterTests.get().stream().map(DiagnosticCenterTests::getTestId).collect(Collectors.toList());
            return ResponseEntity.ok(testModelList.stream().filter((testModel -> testIdList.contains(testModel.getTestId()))).collect(Collectors.toList()));
        } else {
            return ResponseEntity.badRequest().body("something went wrong");

        }


    }


    @GetMapping
    ResponseEntity<?> getCenters() {
        List<DiagnosticCenter> diagnosticCenters = new ArrayList<>();
        diagnosticCenterRepository.findAll().forEach((diagnosticCenters::add));
        return ResponseEntity.ok(new RestResponse<>(diagnosticCenters, true, null));
    }


    @PutMapping("/{id}")
    ResponseEntity<?> updateCenter(@PathVariable(value = "id") long id, @RequestBody DiagnosticCenterDto diagnosticCenterDto) throws DiagnosticCenterNotFoundException {
        Optional<DiagnosticCenter> optionalDiagnosticCenter = diagnosticCenterRepository.findById(id);

        if (optionalDiagnosticCenter.isPresent()) {
            DiagnosticCenter v = optionalDiagnosticCenter.get();
            try {
                v.setCenterName(diagnosticCenterDto.getCenterName());
                v.setAddress(diagnosticCenterDto.getAddress());
                v.setContactNo(diagnosticCenterDto.getContactNo());
                diagnosticCenterRepository.save(v);
                return ResponseEntity.ok(new RestResponse<>("updated", true, null));
            } catch (Exception e) {
                return ResponseEntity.ok(new RestResponse<>(null, false, e.getCause().getMessage()));
            }
        } else {
            return ResponseEntity.ok(new RestResponse<>(null, false, "Not found by " + id));
        }

    }

    @DeleteMapping("/{centerId}")
    ResponseEntity<?> deleteCenter(@PathVariable(name = "centerId") long centerId) {
        try {
            diagnosticCenterRepository.deleteById(centerId);
            return ResponseEntity.ok(new RestResponse<>("deleted", true, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new RestResponse<>(null, false, e.getCause().getMessage()));
        }
    }


    @PostMapping
    ResponseEntity<?> addCenter(@Valid @RequestBody DiagnosticCenterDto diagnosticCenterDto) {
        try {
            DiagnosticCenter d = new DiagnosticCenter();
            d.setCenterName(diagnosticCenterDto.getCenterName());
            d.setAddress(diagnosticCenterDto.getAddress());
            d.setContactNo(diagnosticCenterDto.getContactNo());
            if (diagnosticCenterDto.getId() != null)
                d.setId(diagnosticCenterDto.getId());
            diagnosticCenterRepository.save(d);
            return ResponseEntity.ok(new RestResponse<>("Data added", true, null));
        } catch (Exception e) {
            return ResponseEntity.ok(new RestResponse<>(null, false, e.getCause().getMessage()));
        }


    }


}
