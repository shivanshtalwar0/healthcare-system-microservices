package com.cg.diagnosticservice.controller;

import com.cg.diagnosticservice.dto.DiagnosticCenterDto;
import com.cg.diagnosticservice.dto.DiagnosticCenterTestsDto;
import com.cg.diagnosticservice.dto.RestResponse;
import com.cg.diagnosticservice.entity.DiagnosticCenter;
import com.cg.diagnosticservice.exceptions.DiagnosticCenterNotFoundException;
import com.cg.diagnosticservice.model.TestModel;
import com.cg.diagnosticservice.services.DiagnosticCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("diagnosticCenter")
public class DiagnosticCenterController {

    @Autowired
    DiagnosticCenterService diagnosticCenterService;

    @PostMapping("/{centerId}/test/{testId}")
    public ResponseEntity<?> addTestToDiagnosticCenter(@PathVariable(name = "centerId") long centerId, @PathVariable(name = "testId") long testId) {
        DiagnosticCenterTestsDto diagnosticCenterTestsDto =
                this.diagnosticCenterService.addTestToDiagnosticCenter(centerId, testId);
        if (diagnosticCenterTestsDto != null) {
            return ResponseEntity.ok(diagnosticCenterTestsDto);
        }
        return ResponseEntity.badRequest().body("failed to add Test to center");
    }

    @GetMapping("/{centerId}/test")
    public ResponseEntity<?> getTestsForDiagnosticCenter(HttpServletRequest request, @PathVariable(name = "centerId") long centerId) {
        String token = request.getHeader("Authorization");
        List<TestModel> testModelList = this.diagnosticCenterService.getTestsForDiagnosticCenter(token, centerId);
        if (testModelList != null) {
            return ResponseEntity.ok(testModelList);
        }
        return ResponseEntity.badRequest().body("failed to fetch tests for given test");
    }

    @GetMapping
    public ResponseEntity<?> getCenters() {
        List<DiagnosticCenterDto> diagnosticCenterDtoList = this.diagnosticCenterService.getCenterList();
        if (diagnosticCenterDtoList != null) {
            return ResponseEntity.ok(diagnosticCenterDtoList);
        }
        return ResponseEntity.badRequest().body("Something went wrong");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCenter(@PathVariable(value = "id") long id, @RequestBody DiagnosticCenterDto diagnosticCenterDto) throws DiagnosticCenterNotFoundException {
        DiagnosticCenterDto diagnosticCenterDtoResult = this.diagnosticCenterService.updateCenter(id, diagnosticCenterDto);
        if (diagnosticCenterDto != null) {
            return ResponseEntity.ok(diagnosticCenterDtoResult);
        } else {
            return ResponseEntity.badRequest().body("updation failed!");
        }
    }

    @DeleteMapping("/{centerId}")
    public ResponseEntity<?> deleteCenter(@PathVariable(name = "centerId") long centerId) {
        if (this.diagnosticCenterService.deleteCenter(centerId)) {
            return ResponseEntity.ok(new RestResponse<>("delete success",true,null));
        }
        else {
            return ResponseEntity.badRequest().body("failed to delete!");
        }
    }

    @PostMapping
    public ResponseEntity<?> addCenter(HttpServletRequest request, @Valid @RequestBody DiagnosticCenterDto diagnosticCenterDto) {
        DiagnosticCenter result = this.diagnosticCenterService.addCenter(request.getHeader("Authorization"), diagnosticCenterDto);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body("something went wrong");
        }

    }


}
