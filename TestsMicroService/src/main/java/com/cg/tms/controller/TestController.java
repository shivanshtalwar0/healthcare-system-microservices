package com.cg.tms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.tms.model.TestDataModel;
import com.cg.tms.service.TestService;

@RestController
@RequestMapping("/tests")
public class TestController {
	@Autowired
	TestService service;
	
	@GetMapping
	public ResponseEntity<List<TestDataModel>> findAll(){
		return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<TestDataModel> add(@RequestBody TestDataModel model) {
		model=service.add(model);
		return new ResponseEntity<>(model,HttpStatus.CREATED);
	
	}

}
