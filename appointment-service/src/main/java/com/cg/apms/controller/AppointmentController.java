package com.cg.apms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.apms.model.AppointmentModel;
import com.cg.apms.service.AppointmentService;
import com.cg.apms.service.DiagnosticCenterProxyService;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/appointments")
public class AppointmentController {
	@Autowired
	AppointmentService service;
	@Autowired
	DiagnosticCenterProxyService dcProxyServ;

	@GetMapping
	public ResponseEntity<List<AppointmentModel>> findAll(HttpServletRequest request){
		String bearerTokenString=request.getHeader("Authorization");
		dcProxyServ.getAllCenters(bearerTokenString.replace("Bearer ",""));
		return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<AppointmentModel> add(@RequestBody AppointmentModel model){
		
		model=service.add(model);
		
		return new ResponseEntity<>(model,HttpStatus.CREATED);
	}
	@GetMapping("/{patientid}")
	public ResponseEntity<AppointmentModel> findById(@PathVariable("patientid")Long patientid){
		ResponseEntity<AppointmentModel> result;
		AppointmentModel  model=service.findById(patientid);
		if(model==null) {
			result=new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			result=new ResponseEntity<>(model,HttpStatus.OK);
		}
		return result;
	}
	
	@DeleteMapping("/{patientid}")
	public ResponseEntity<AppointmentModel> deleteById(@PathVariable("patientid")Long patientid){
		  ResponseEntity<AppointmentModel> result;
		  AppointmentModel  model= service.findById(patientid);
		  if(model==null) {
			  result=new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }else {
			  service.deleteById(patientid);
			  result= new ResponseEntity<>(HttpStatus.OK);
		  }
		  return result;
	}
	
	

}
