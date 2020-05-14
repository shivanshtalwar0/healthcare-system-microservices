package com.cg.tms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tms.dao.TestsRepository;
import com.cg.tms.entity.TestEntity;
import com.cg.tms.model.TestDataModel;
@Service
public class TestServiceImpl implements TestService {
	@Autowired
	TestsRepository repo;
	
	public TestDataModel of(TestEntity entity) {
		TestDataModel model =null;
        if(entity!=null)
        {
         	model=new TestDataModel();
         	model.setTestId(entity.getTestId());
         	model.setTestName(entity.getTestName());
         
        }
        return model;
}
public TestEntity of(TestDataModel model) {
	TestEntity entity=null;
if(model!=null) {
entity=new TestEntity();
entity.setTestId(model.getTestId());
entity.setTestName(model.getTestName());
}

return entity;
}

 @Override
 public List<TestDataModel> findAll(){
      return repo.findAll().stream().map(e  ->  of(e)).collect(Collectors.toList());	
}

//@Override
//public DiagnosticCenterModel findById(Long centerId) {
//	DiagnosticCenterModel model=null;
//	DiagnosticCenterEntity entity=repo.findById(centerId).orElse(null);
//     if(entity!=null) {
//      model=of(entity);
//    }
//return  model;
//}

@Override
public TestDataModel add( TestDataModel model) {

return of(repo.save(of( model)));
}


}
