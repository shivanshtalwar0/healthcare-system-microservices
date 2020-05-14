package com.cg.hcs.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.hcs.entity.TestEntity;


@Repository
public interface TestManagementDao extends CrudRepository<TestEntity,Long> {

	
	//boolean existsByCenterId(long centerId);
	//public List<TestEntity> findAllByCenterId(long centerId);

	//https://docs.spring.io/spring-data/jpa/docs/2.0.6.RELEASE/api/org/springframework/data/jpa/repository/JpaRepository.html
}
