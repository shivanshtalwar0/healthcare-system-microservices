package com.cg.hcs.services;

import java.sql.SQLException;
import java.util.List;

import com.cg.hcs.entity.TestEntity;
import com.cg.hcs.exception.TestManagementException;
import com.cg.hcs.model.TestModel;







public interface TestManagementService {
	
	TestModel add(TestModel test) throws SQLException;
	
	void deleteById(Long testId ) throws TestManagementException;

	TestModel findById(Long testId);
	
	List<TestModel> findAll();
	
	TestModel save(Long testId,TestModel model) throws TestManagementException;
	TestModel findByName(String testName);
}
