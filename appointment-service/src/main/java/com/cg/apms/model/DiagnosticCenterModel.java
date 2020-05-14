package com.cg.apms.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticCenterModel {
    //    private Long centerId;
//    private String centerName;
//    private String address;
//    private String contactInfo;
   private List<TestDataModel> testList;
    Long id;
    String centerName;
    String address;
    String contactNo;
}
