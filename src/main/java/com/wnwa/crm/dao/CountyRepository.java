package com.wnwa.crm.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnwa.crm.entity.County;



public interface CountyRepository extends JpaRepository<County, Integer>{
    List<County> findByState_StateId(Integer stateId);

}