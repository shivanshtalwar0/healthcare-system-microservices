package com.cg.hcs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.hcs.entity.TestEntity;
import com.cg.hcs.exception.TestManagementException;
import com.cg.hcs.model.TestModel;
import com.cg.hcs.services.TestManagementService;



@Controller
@CrossOrigin(origins="*")
@RequestMapping("/testoperations")
public class TestManagementController {
	
	@Autowired
	private TestManagementService testservice;
	
	@PostMapping("/add")
	public ResponseEntity<TestModel> addTest(@RequestBody TestModel model) throws TestManagementException{
		model=testservice.add(model);
		 System.out.println(model);
		 return new ResponseEntity<>(model,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/view")
	public ResponseEntity<List<TestModel>> findAll(){
		return new ResponseEntity<>(testservice.findAll(),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{testId}")
	public ResponseEntity<Void> delete(@PathVariable("testId") Long testId) throws TestManagementException {

		ResponseEntity<Void> response = null;

	TestModel testTemp = testservice.findById(testId);

		if (testTemp == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			testservice.deleteById(testId);
			response = new ResponseEntity<>(HttpStatus.OK);
		}

		return response;
	}
	
	
	@PutMapping("/update/{testId}")
	public ResponseEntity<TestModel> updateEmployee(@PathVariable(value="testId") Long testId,@RequestBody TestModel model) throws TestManagementException {
		TestModel testModel = testservice.save(testId,model);
		return new ResponseEntity<>(testModel, HttpStatus.OK);
	}

}
