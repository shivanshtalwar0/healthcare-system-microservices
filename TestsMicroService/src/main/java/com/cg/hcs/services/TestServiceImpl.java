package com.cg.hcs.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cg.hcs.model.TestAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hcs.dao.TestManagementDao;
import com.cg.hcs.entity.TestAttributesEntity;
import com.cg.hcs.entity.TestEntity;
import com.cg.hcs.exception.TestManagementException;
import com.cg.hcs.model.TestModel;


@Service
public class TestServiceImpl implements TestManagementService {

    @Autowired
    private TestManagementDao testrepo;


    private TestEntity toEntity(TestModel source) {
        TestEntity result = new TestEntity();
        result.setTestName(source.getTestName());
        result.setTestAttributes(source.getTestAttributes().stream().map((v) -> new TestAttributesEntity(v.getTestAttributeName())).collect(Collectors.toList()));
        return result;
    }

    private TestModel toModel(TestEntity source) {
        TestModel result = null;
        if (source != null) {
            result = new TestModel();
            result.setTestAttributes(source.getTestAttributes().stream().map((v) -> new TestAttributes(v.getAttributeName())).collect(Collectors.toList()));
            result.setTestId(source.getTestId());
            result.setTestName(source.getTestName());

        }
        return result;
    }


    @Override
    public TestModel add(TestModel test) throws TestManagementException {
        return toModel(testrepo.save(toEntity(test)));
    }


    @Override
    public TestModel findById(Long testId) {
        return toModel(testrepo.findById(testId).orElse(null));
    }


    @Override
    public void deleteById(Long testId) throws TestManagementException {

        testrepo.deleteById(testId);
    }


    @Override
    public List<TestModel> findAll() {


        List<TestEntity> list = (List<TestEntity>) testrepo.findAll();
        return list.stream().map(this::toModel).collect(Collectors.toList());

    }


    @Override
    public TestModel save(Long testId, TestModel model) throws TestManagementException {

        Optional<TestEntity> testList = testrepo.findById(testId);
        TestModel testModel = null;
        if (testList.isPresent()) {
            TestEntity v = testList.get();
            v.setTestName(model.getTestName());
            v.setTestAttributes(model.getTestAttributes().stream().map((aa) -> new TestAttributesEntity(aa.getTestAttributeName())).collect(Collectors.toList()));
            testModel = toModel(testrepo.save(v));
        } else
            throw new TestManagementException("Updation not possible");
        return testModel;
    }


}
