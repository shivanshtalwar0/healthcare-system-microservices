package com.cg.hcs.model;



public class TestAttributes {
	
	private String testAttributeName;
	private long id;
	
	public TestAttributes(){}

	public String getTestAttributeName() {
		return testAttributeName;
	}

	public void setTestAttributeName(String testAttributeName) {
		this.testAttributeName = testAttributeName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TestAttributes [testAttributeName=" + testAttributeName + ", id=" + id + "]";
	}
	
     }
