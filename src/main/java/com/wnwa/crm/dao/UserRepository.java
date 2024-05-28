package com.wnwa.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wnwa.crm.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>{

    @Query("SELECT u.emailId FROM User u " +
           "WHERE u.state.stateId = :stateId AND u.county.countyId = :countyId AND u.department.departmentId = :departmentId")
    List<String> findEmailByStateCountyDepartment(@Param("stateId") Integer stateId,
                                            @Param("countyId") Integer countyId,
                                            @Param("departmentId") Integer departmentId);

}