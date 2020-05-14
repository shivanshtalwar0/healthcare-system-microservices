package com.cg.diagnosticservice.dao;

import com.cg.diagnosticservice.entity.DiagnosticCenterTests;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DiagnosticCenterTestsRepository extends CrudRepository<DiagnosticCenterTests,Long> {
Optional<List<DiagnosticCenterTests>> findByCenterId(long centerId);
}
