package com.cg.tms.service;

import java.util.List;

import com.cg.tms.model.TestDataModel;

public interface TestService {
	List<TestDataModel> findAll(); 
	TestDataModel add(TestDataModel model);

}
