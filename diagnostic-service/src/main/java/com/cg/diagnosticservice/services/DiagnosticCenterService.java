package com.cg.diagnosticservice.services;

import com.cg.diagnosticservice.dao.DiagnosticCenterRepository;
import com.cg.diagnosticservice.dao.DiagnosticCenterTestsRepository;
import com.cg.diagnosticservice.dto.DiagnosticCenterDto;
import com.cg.diagnosticservice.dto.DiagnosticCenterTestsDto;
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

    public DiagnosticCenterTestsDto addTestToDiagnosticCenter(long centerId, long testId) {
        DiagnosticCenterTests diagnosticCenterTests = new DiagnosticCenterTests();
        diagnosticCenterTests.setCenterId(centerId);
        diagnosticCenterTests.setTestId(testId);
        return DiagnosticCenterTestsDto.fromEntity(diagnosticCenterTestsRepository.save(diagnosticCenterTests));
    }

    public List<TestModel> getTestsForDiagnosticCenter(String token, long centerId) {
        List<TestModel> testModelList = testServiceClient.getTestList(token);
        Optional<List<DiagnosticCenterTests>> diagnosticCenterTests = diagnosticCenterTestsRepository.findByCenterId(centerId);
        if (diagnosticCenterTests.isPresent()) {
            List<Long> testIdList = diagnosticCenterTests.get().stream().map(DiagnosticCenterTests::getTestId).collect(Collectors.toList());
            return testModelList.stream().filter((testModel -> testIdList.contains(testModel.getTestId()))).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public List<DiagnosticCenterDto> getCenterList() {
        List<DiagnosticCenter> diagnosticCenters = new ArrayList<>();
        diagnosticCenterRepository.findAll().forEach((diagnosticCenters::add));
        return DiagnosticCenterDto.fromEntity(diagnosticCenters);
    }

    public DiagnosticCenterDto updateCenter(long id, DiagnosticCenterDto diagnosticCenterDto) throws DiagnosticCenterNotFoundException {
        Optional<DiagnosticCenter> optionalDiagnosticCenter = diagnosticCenterRepository.findById(id);

        if (optionalDiagnosticCenter.isPresent()) {
            DiagnosticCenter v = optionalDiagnosticCenter.get();
            v.setCenterName(diagnosticCenterDto.getCenterName());
            v.setAddress(diagnosticCenterDto.getAddress());
            v.setContactNo(diagnosticCenterDto.getContactNo());
            return DiagnosticCenterDto.fromEntity(diagnosticCenterRepository.save(v));

        } else {
            return null;
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

    public boolean deleteCenter(long centerId) {
        try {
            diagnosticCenterRepository.deleteById(centerId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public DiagnosticCenter addCenter(String token, DiagnosticCenterDto diagnosticCenterDto) {
        try {
            DiagnosticCenter d = new DiagnosticCenter();
            d.setCenterName(diagnosticCenterDto.getCenterName());
            d.setAddress(diagnosticCenterDto.getAddress());
            d.setContactNo(diagnosticCenterDto.getContactNo());
            if (diagnosticCenterDto.getId() != null)
                d.setId(diagnosticCenterDto.getId());
            TestModel t1;
            try{
                t1 = addDefaultTest(token, "Diabetes", new String[]{"blood glucose", "oxygen level"});
            }
            catch (Exception e){
                t1=testServiceClient.getTestByName("Diabetes",token);
            }
            DiagnosticCenter d1 = diagnosticCenterRepository.save(d);
            diagnosticCenterTestsRepository.save(new DiagnosticCenterTests(d1.getId(), t1.getTestId()));
            return d1;
        } catch (Exception e) {
            return null;
        }


    }


}
