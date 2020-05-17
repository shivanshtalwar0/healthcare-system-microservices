package com.cg.apms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosticCenterDto implements Serializable {
    @NotEmpty(message = "First name must not be empty")
    String centerName;
    @NotNull
    long id;
    @NotNull(message = "address is required")
    String address;
    @NotNull(message = "contact No is required")
    String contactNo;
}
