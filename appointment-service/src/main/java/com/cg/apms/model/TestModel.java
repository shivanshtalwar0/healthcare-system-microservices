package com.cg.apms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestModel {

    long testId;
    String testName;

    List<TestAttributes> testAttributes;


}
