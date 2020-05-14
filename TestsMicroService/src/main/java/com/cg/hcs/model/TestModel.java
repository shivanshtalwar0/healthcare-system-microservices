package com.cg.hcs.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestModel {
	
	long testId;
	@NonNull
	String testName;
	@NonNull
	List<TestAttributes> testAttributes;
     }
