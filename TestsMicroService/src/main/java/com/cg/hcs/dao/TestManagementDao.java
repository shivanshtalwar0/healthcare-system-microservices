package com.cg.hcs.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.hcs.entity.TestEntity;


@Repository
public interface TestManagementDao extends CrudRepository<TestEntity,Long> {
}
