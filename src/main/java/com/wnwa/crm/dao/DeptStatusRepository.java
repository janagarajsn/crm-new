package com.wnwa.crm.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wnwa.crm.entity.DepartmentStatus;


public interface DeptStatusRepository extends JpaRepository<DepartmentStatus, Integer>{

}