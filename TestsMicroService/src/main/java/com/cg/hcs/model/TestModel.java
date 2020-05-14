package com.cg.hcs.model;

import java.util.List;

public class TestModel {
	
	long testId;
	String testName;
	
	List<TestAttributes> testAttributes;
	
	public TestModel()
	{}
	@Override
	public String toString() {
		return "TestModel [testId=" + testId + ", testName=" + testName + ", testAttributes=" + testAttributes + "]";
	}
	public List<TestAttributes> getTestAttributes() {
		return testAttributes;
	}
	public void setTestAttributes(List<TestAttributes> testAttributes) {
		this.testAttributes = testAttributes;
	}
	public long getTestId() {
		return testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public void setTestId(long testId) {
		this.testId = testId;
	}
	   
 
     }
