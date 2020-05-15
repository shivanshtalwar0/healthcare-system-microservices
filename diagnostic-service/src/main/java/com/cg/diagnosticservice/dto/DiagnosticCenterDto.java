package com.cg.diagnosticservice.dto;

import com.cg.diagnosticservice.entity.DiagnosticCenter;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor()
public class DiagnosticCenterDto implements Serializable {

    Long id;
    @NotNull(message = "First name must not be empty")
    @NonNull
    String centerName;
    @NotNull(message = "address is required")
    @NonNull
    String address;
    @NotNull(message = "contact No is required")
    @NonNull
    String contactNo;

    public static DiagnosticCenterDto fromEntity(DiagnosticCenter diagnosticCenter) {
        return new DiagnosticCenterDto(diagnosticCenter.getId(),diagnosticCenter.getCenterName(), diagnosticCenter.getAddress(), diagnosticCenter.getContactNo());
    }

    public static List<DiagnosticCenterDto> fromEntity(List<DiagnosticCenter> diagnosticCenterList) {
        List<DiagnosticCenterDto> diagnosticCenterDtoList = new ArrayList<>();
        diagnosticCenterList.forEach((v) -> diagnosticCenterDtoList.add(new DiagnosticCenterDto(v.getId(), v.getCenterName(), v.getAddress(), v.getContactNo())));
        return diagnosticCenterDtoList;
    }


}
