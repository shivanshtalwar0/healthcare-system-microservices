package com.cg.apms.service;

import java.util.List;

import com.cg.apms.model.AppointmentModel;

public interface AppointmentService {
	 List< AppointmentModel> findAll();
//	 AppointmentModel findById();
	 AppointmentModel add(AppointmentModel model);
	 void deleteById(Long patientId);
	AppointmentModel findById(Long patientId);

}
