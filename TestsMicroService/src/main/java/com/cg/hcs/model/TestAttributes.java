package com.cg.hcs.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestAttributes {
	private String testAttributeName;
	private long id;

	public TestAttributes(String attributeName) {
		this.testAttributeName=attributeName;
	}
}
