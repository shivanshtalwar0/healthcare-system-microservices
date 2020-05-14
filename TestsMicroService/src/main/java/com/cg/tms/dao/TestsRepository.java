package com.cg.tms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.tms.entity.TestEntity;

@Repository
public interface TestsRepository extends JpaRepository<TestEntity,Long> {

}
