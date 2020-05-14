package com.cg.diagnosticservice.controller;
import com.cg.diagnosticservice.dto.DiagnosticCenterDto;
import com.cg.diagnosticservice.exceptions.DiagnosticCenterNotFoundException;
import com.cg.diagnosticservice.services.DiagnosticCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("diagnosticCenter")
public class DiagnosticCenterController {

    @Autowired
    DiagnosticCenterService diagnosticCenterService;

    @PostMapping("/{centerId}/test/{testId}")
    ResponseEntity<?> addTestToDiagnosticCenter(@PathVariable(name = "centerId") long centerId, @PathVariable(name = "testId") long testId) {
        return this.diagnosticCenterService.addTestToDiagnosticCenter(centerId, testId);
    }

    @GetMapping("/{centerId}/test")
    ResponseEntity<?> getTestsForDiagnosticCenter(HttpServletRequest request, @PathVariable(name = "centerId") long centerId) {
        return this.diagnosticCenterService.getTestsForDiagnosticCenter(request, centerId);
    }

    @GetMapping
    ResponseEntity<?> getCenters() {
        return this.diagnosticCenterService.getCenterList();
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateCenter(@PathVariable(value = "id") long id, @RequestBody DiagnosticCenterDto diagnosticCenterDto) throws DiagnosticCenterNotFoundException {
        return this.diagnosticCenterService.updateCenter(id, diagnosticCenterDto);
    }

    @DeleteMapping("/{centerId}")
    ResponseEntity<?> deleteCenter(@PathVariable(name = "centerId") long centerId) {
        return this.diagnosticCenterService.deleteCenter(centerId);
    }


    @PostMapping
    ResponseEntity<?> addCenter(HttpServletRequest request, @Valid @RequestBody DiagnosticCenterDto diagnosticCenterDto) {
        return this.diagnosticCenterService.addCenter(request, diagnosticCenterDto);
    }


}
