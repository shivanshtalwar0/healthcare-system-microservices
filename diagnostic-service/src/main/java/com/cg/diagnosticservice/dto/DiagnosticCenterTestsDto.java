package com.cg.diagnosticservice.dto;

import com.cg.diagnosticservice.entity.DiagnosticCenterTests;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class DiagnosticCenterTestsDto {
    @Id
    @GeneratedValue
    long id;
    @NonNull
    long centerId;
    @NonNull
    long testId;

    public static DiagnosticCenterTestsDto fromEntity(DiagnosticCenterTests diagnosticCenterTests){
        return new DiagnosticCenterTestsDto(diagnosticCenterTests.getId(),diagnosticCenterTests.getCenterId(),diagnosticCenterTests.getTestId());
    }
}
