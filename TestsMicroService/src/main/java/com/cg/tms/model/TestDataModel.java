package com.cg.tms.model;

public class TestDataModel {
	
     private Long testId;
	
	
	private String testName;

	public Long getTestId() {
		return testId;
	}
  public TestDataModel() {
	  
  } 
	public TestDataModel(Long testId, String testName) {
		super();
		this.testId = testId;
		this.testName = testName;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}


}
