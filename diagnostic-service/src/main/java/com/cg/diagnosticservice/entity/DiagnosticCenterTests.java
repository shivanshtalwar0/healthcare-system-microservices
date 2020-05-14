package com.cg.diagnosticservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosticCenterTests {
    @Id
    long id;
    long centerId;
    long testId;

}
