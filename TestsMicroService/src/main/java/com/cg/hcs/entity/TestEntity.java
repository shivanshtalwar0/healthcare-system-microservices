package com.cg.hcs.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="TestList")
public class TestEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="test_id")
	long testId;
	
	@Column(name = "test_Name",nullable=false)
	String testName;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="test_id",referencedColumnName="test_id")
	private List<TestAttributesEntity> testAttributes;
	
	
	
	

	public List<TestAttributesEntity> getTestAttributes() {
		return testAttributes;
	}
	public void setTestAttributes(List<TestAttributesEntity> testAttributes) {
		this.testAttributes = testAttributes;
	}
	public long getTestId() {
		return testId;
	}
	public void setTestId(long l) {
		this.testId = l;
	}
	
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	
	
}
