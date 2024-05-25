package com.wnwa.crm.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wnwa.crm.entity.Department;;


public interface DepartmentRepository extends JpaRepository<Department, Integer>{

    @Query(value = "SELECT * FROM Department WHERE status_Id IN (:i)",nativeQuery = true)
    List<Department> getSelectedDepts(int i); 

    @Query(value = "SELECT * FROM Department WHERE state_Id = :stateId and county_Id = :countyId",nativeQuery = true)
    List<Department> findDeptByStateDept(Integer stateId, Integer countyId);

}