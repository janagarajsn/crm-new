package com.wnwa.crm.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wnwa.crm.dao.DepartmentRepository;
import com.wnwa.crm.dao.DeptStatusRepository;
import com.wnwa.crm.entity.Department;
import com.wnwa.crm.entity.DepartmentStatus;



@Service
public class DepartmentService {

   @Autowired
   private DepartmentRepository deptRepository;
   @Autowired
   private DeptStatusRepository deptStatusRepo;
  
   public Department createDepartment(Department dept) {
      if (dept != null)
      return deptRepository.save(dept);
      else
      return null;

   }

   public DepartmentStatus createDepartmentStatus(DepartmentStatus depts) {
      if (depts != null)
      return deptStatusRepo.save(depts);
      else
      return null;

   }

   public List<DepartmentStatus> getStatus() {
      return deptStatusRepo.findAll();
   }

   public Department getDepartmentById(int id) {
      return deptRepository.findById(id).orElse(null);
   }

   public List<Department> getDepartments() {
      return deptRepository.findAll();
   }

   public String deleteDeptByID(int id) {
      deptRepository.deleteById(id);
      return "Department Deleted";
   }

   public Department getById (int id)
    {
        Optional<Department> optional = deptRepository.findById(id);
        Department dept = null;
        if (optional.isPresent())
            dept = optional.get();
        else
            throw new RuntimeException(
                "Department not found for id : " + id);
        return dept;
    }

     public List<Department> getSelectedDepts(int i) {
     List<Department> deptDetails = deptRepository.getSelectedDepts(i);
      return deptDetails;
   }

   public List<Department> getDeptListOnCounty(Integer stateId, Integer countyId) {
      List<Department> deptDetails = deptRepository.findDeptByStateDept(stateId,countyId);
      return deptDetails;
   } 

}