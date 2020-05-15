package com.cg.diagnosticservice.services;

import com.cg.diagnosticservice.dao.DiagnosticCenterRepository;
import com.cg.diagnosticservice.dao.DiagnosticCenterTestsRepository;
import com.cg.diagnosticservice.dto.DiagnosticCenterDto;
import com.cg.diagnosticservice.dto.RestResponse;
import com.cg.diagnosticservice.entity.DiagnosticCenter;
import com.cg.diagnosticservice.entity.DiagnosticCenterTests;
import com.cg.diagnosticservice.exceptions.DiagnosticCenterNotFoundException;
import com.cg.diagnosticservice.model.TestAttributes;
import com.cg.diagnosticservice.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiagnosticCenterService {
    @Autowired
    DiagnosticCenterRepository diagnosticCenterRepository;
    @Autowired
    DiagnosticCenterTestsRepository diagnosticCenterTestsRepository;

    @Autowired
    TestServiceClient testServiceClient;

    public ResponseEntity<?> addTestToDiagnosticCenter(long centerId, long testId) {
        DiagnosticCenterTests diagnosticCenterTests = new DiagnosticCenterTests();
        diagnosticCenterTests.setCenterId(centerId);
        diagnosticCenterTests.setTestId(testId);
        return ResponseEntity.ok(diagnosticCenterTestsRepository.save(diagnosticCenterTests));
    }
    public ResponseEntity<?> getTestsForDiagnosticCenter(HttpServletRequest request,long centerId) {
        List<TestModel> testModelList = testServiceClient.getTestList(request.getHeader("Authorization"));
        Optional<List<DiagnosticCenterTests>> diagnosticCenterTests = diagnosticCenterTestsRepository.findByCenterId(centerId);
        if (diagnosticCenterTests.isPresent()) {
            List<Long> testIdList = diagnosticCenterTests.get().stream().map(DiagnosticCenterTests::getTestId).collect(Collectors.toList());
            return ResponseEntity.ok(testModelList.stream().filter((testModel -> testIdList.contains(testModel.getTestId()))).collect(Collectors.toList()));
        } else {
            return ResponseEntity.badRequest().body("something went wrong");

        }
    }

    public ResponseEntity<?> getCenterList() {
        List<DiagnosticCenter> diagnosticCenters = new ArrayList<>();
        diagnosticCenterRepository.findAll().forEach((diagnosticCenters::add));
        return ResponseEntity.ok(diagnosticCenters);
    }

    public ResponseEntity<?> updateCenter( long id,DiagnosticCenterDto diagnosticCenterDto) throws DiagnosticCenterNotFoundException {
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
    public TestModel addDefaultTest(String token, String testName, String[] testAttributeArray) {
        TestModel testModel = new TestModel();
        testModel.setTestName(testName);
        List<TestAttributes> testAttributes = new ArrayList<>();
        for (String a : testAttributeArray) {
            testAttributes.add(new TestAttributes(a));
        }
        testModel.setTestAttributes(testAttributes);
        return this.testServiceClient.createTest(token, testModel);
    }

    public ResponseEntity<?> deleteCenter(long centerId) {
        try {
            diagnosticCenterRepository.deleteById(centerId);
            return ResponseEntity.ok(new RestResponse<>("deleted", true, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new RestResponse<>(null, false, e.getCause().getMessage()));
        }
    }

    public ResponseEntity<?> addCenter(HttpServletRequest request, DiagnosticCenterDto diagnosticCenterDto) {
        try {
            DiagnosticCenter d = new DiagnosticCenter();
            d.setCenterName(diagnosticCenterDto.getCenterName());
            d.setAddress(diagnosticCenterDto.getAddress());
            d.setContactNo(diagnosticCenterDto.getContactNo());
            if (diagnosticCenterDto.getId() != null)
                d.setId(diagnosticCenterDto.getId());
            TestModel t1 = addDefaultTest(request.getHeader("Authorization"), "Diabetes", new String[]{"blood glucose", "oxygen level"});
            DiagnosticCenter d1 = diagnosticCenterRepository.save(d);
            diagnosticCenterTestsRepository.save(new DiagnosticCenterTests(d1.getId(), t1.getTestId()));
            return ResponseEntity.ok(d1);
        } catch (Exception e) {
            return ResponseEntity.ok(new RestResponse<>(null, false, e.getCause().getMessage()));
        }


    }


}
