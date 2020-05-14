package com.cg.apms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.apms.entity.AppointmentEntity;

@Repository
public interface AppointmentRepository  extends JpaRepository<AppointmentEntity,Long>{
}

