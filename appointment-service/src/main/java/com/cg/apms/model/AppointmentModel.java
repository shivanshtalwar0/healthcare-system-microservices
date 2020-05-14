package com.cg.apms.model;



//import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppointmentModel {
    private Long patientid;
	private String  date;
	private String centername;
	private String testName;
//	 private List<DiagnosticCenterModel> CenterList;
//	private List<TesetDataModel> testList;
}
