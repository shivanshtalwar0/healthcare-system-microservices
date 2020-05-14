package com.cg.diagnosticservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class DiagnosticCenterTests {
    @Id
    @GeneratedValue
    long id;
    @NonNull
    long centerId;
    @NonNull
    long testId;
}
