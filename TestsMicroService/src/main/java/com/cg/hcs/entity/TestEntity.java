package com.cg.hcs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name="TestList")
public class TestEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="test_id")
	long testId;
	
	@Column(name = "test_Name",nullable=false,unique = true)
	String testName;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="test_id",referencedColumnName="test_id")
	private List<TestAttributesEntity> testAttributes;
	
}
