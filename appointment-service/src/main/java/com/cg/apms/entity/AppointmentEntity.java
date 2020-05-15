package com.cg.apms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Appointment_Info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentEntity {
	@Id
	@Column(name="appointmentId")
	private Long patientid;
	@Column(name="appointment_Date")
	private String date;
	@Column(name="center_name")
	private String centername;
	@Column(name="test_name")
	private String testName;
	@Column(name="Center_ID")
	private long centerId;
	@Column(name="Test_Id")
	private long testId;public Long getCenterId() {
		return centerId;
	}
	public void setCenterId(Long centerId) {
		this.centerId = centerId;
	}
	public Long getTestId() {
		return testId;
	}
	public void setTestId(Long testId) {
		this.testId = testId;
	}
}
