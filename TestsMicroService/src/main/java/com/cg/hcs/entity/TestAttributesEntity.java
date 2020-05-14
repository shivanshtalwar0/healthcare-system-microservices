package com.cg.hcs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TestAttributes")
public class TestAttributesEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="attribute_id")
	private long testAttributeId;
	
	private String attributeName;
	
	@Column(name="test_id")
	private long id;
	
	
	
	public TestAttributesEntity(String attributeName){
		this.attributeName=attributeName;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTestAttributeId() {
		return testAttributeId;
	}

	public void setTestAttributeId(long testAttributeId) {
		this.testAttributeId = testAttributeId;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	

	
	
	
}
