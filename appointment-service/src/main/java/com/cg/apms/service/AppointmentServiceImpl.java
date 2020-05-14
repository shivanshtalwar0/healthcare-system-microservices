package com.cg.apms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.apms.dao.AppointmentRepository;
import com.cg.apms.entity.AppointmentEntity;
import com.cg.apms.model.AppointmentModel;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	
	@Autowired
	private AppointmentRepository repo;
	

	
	public AppointmentModel of(AppointmentEntity entity) {
                    AppointmentModel model =null;
                    if(entity!=null)
                    {
                     	model=new AppointmentModel();
                     	model.setPatientid(entity.getPatientid());
                     	model.setCentername(entity.getCentername());
                     	model.setTestName(entity.getTestName());
                     	model.setDate(entity.getDate());

                    }
                    return model;
}
	public AppointmentEntity of(AppointmentModel model) {
		AppointmentEntity entity=null;
		if(model!=null) {
			entity=new AppointmentEntity();
			entity.setPatientid(model.getPatientid());
			entity.setCentername(model.getCentername());
			entity.setTestName(model.getTestName());
			entity.setDate(model.getDate());
			
		}
	
	return entity;
	}
	@Override
	public List<AppointmentModel> findAll(){

                      return   repo.findAll().stream().map(e  ->  of(e)).collect(Collectors.toList());	
	}
	
	@Override
	public AppointmentModel findById(Long patientid) {
		 AppointmentModel model=null;
		 AppointmentEntity entity=repo.findById(patientid).orElse(null);
		 if(entity!=null) {
			 model=of(entity);
		 }
		return  model;
	}
	@Override
	public AppointmentModel add(AppointmentModel model) {
		
		return of(repo.save(of( model)));
	}
	@Override
	public void deleteById(Long patientid) {
           repo.deleteById(patientid);
	
	}
	}
	
