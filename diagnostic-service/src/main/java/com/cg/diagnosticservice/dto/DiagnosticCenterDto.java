package com.cg.diagnosticservice.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Data
public class DiagnosticCenterDto implements Serializable {
    @NotEmpty(message = "First name must not be empty")
    String centerName;
    @Null
    Long id;
    @NotNull(message = "address is required")
    String address;
    @NotNull(message = "contact No is required")
    String contactNo;
}
