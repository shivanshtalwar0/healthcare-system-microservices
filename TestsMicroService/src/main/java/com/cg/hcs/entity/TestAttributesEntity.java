package com.cg.hcs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
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


	public TestAttributesEntity(String testAttributeName) {
		this.attributeName=testAttributeName;
	}
}
