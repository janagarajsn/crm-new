package com.wnwa.crm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wnwa.crm.entity.County;
import com.wnwa.crm.entity.Department;
import com.wnwa.crm.entity.DepartmentStatus;
import com.wnwa.crm.entity.State;
import com.wnwa.crm.service.CountyService;
import com.wnwa.crm.service.DepartmentService;
import com.wnwa.crm.service.StateService;

@Controller
public class DeptController {
    @Autowired
    private DepartmentService deptService;
    @Autowired
    private CountyService countyService;
    @Autowired
    private StateService stateService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("status", new DepartmentStatus());
        List<DepartmentStatus> statusList = deptService.getStatus();
        Integer statusID = 0;
        if (statusList != null && !statusList.isEmpty()) {
             statusID = statusList.get(0).getStatusId();
        }
       
        model.addAttribute("pageTitle", "Departments Status Report");
        model.addAttribute("department", new Department());
        model.addAttribute("allDepartmentList", deptService.getDepartments());
        model.addAttribute("selectedDepartmentList", deptService.getSelectedDepts(statusID));
        return "index";
    }
    
    @PostMapping("/getDepartmentDetails")
    public String getDepartmentDetails(Model model, @ModelAttribute("department") Department department) {
        model.addAttribute("deptStatus", deptService.getStatus());
        // model.addAttribute("allDepartmentList", deptService.getDepartments());
        model.addAttribute("selectedDepartmentList",
                deptService.getSelectedDepts(department.getStatus().getStatusId()));
        return "index";
    }

    @GetMapping("/addDepartment")
    public String addNewDepartment(Model model) {
        Department dept = new Department();
        model.addAttribute("pageTitle", "Add Department");
        model.addAttribute("allDepartmentList", deptService.getDepartments());
        model.addAttribute("department", dept);
        return "newDepartment";
    }

    @PostMapping("/addDepartment")
    public String addDepartment(@ModelAttribute("department") Department department, Model model, RedirectAttributes redirectAttributes) {
        deptService.createDepartment(department);
        model.addAttribute("allDepartmentList", deptService.getDepartments());
        redirectAttributes.addFlashAttribute("successMessage", "Department Added/Updated!");
        return "redirect:/addDepartment";
    }

    @PostMapping("/addStatus")
    public String addDept(@ModelAttribute("departmentStatus") DepartmentStatus departmentStatus, Model model, RedirectAttributes redirectAttributes) {
        deptService.createDepartmentStatus(departmentStatus);
        model.addAttribute("allDepartmentList", deptService.getDepartments());
        model.addAttribute("department", new Department());
        model.addAttribute("deptStatus", deptService.getStatus());    
        redirectAttributes.addFlashAttribute("successMessage", "New Status Added!");
        return "redirect:/addDepartment";
    }

    @GetMapping("/getDepartments")
    public List<Department> getAllDepartments() {
        return deptService.getDepartments();
    }

    @GetMapping("/updateDept/{id}")
    public String updateForm(@PathVariable(value = "id") int id, Model model) {
        Department department = deptService.getById(id);
        model.addAttribute("pageTitle", "Update Department");
        model.addAttribute("department", department);
        return "updateDept";
    }

    @GetMapping("/deleteDept/{id}")
    public String deleteThroughId(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes) {
        deptService.deleteDeptByID(id);
        model.addAttribute("allDepartmentList", deptService.getDepartments());
        model.addAttribute("department", new Department());
        redirectAttributes.addFlashAttribute("successMessage", "Department Deleted!");
        return "redirect:/addDepartment";
    }

    @ModelAttribute("states")
    public List<State> populateStates(Model model) {
        List<State> state = new ArrayList<State>();
        state = stateService.getStates();
        model.addAttribute("states", state);
        return state;
    }

    @GetMapping("/counties")
    @ResponseBody
    public List<County> populateList(@RequestParam("stateId") Integer stateId) {
        return countyService.getCountiesByStateId(stateId);
    }

    @ModelAttribute("deptStatus")
    public List<DepartmentStatus> populateStatusList(Model model) {
        List<DepartmentStatus> status = new ArrayList<DepartmentStatus>();
        status = deptService.getStatus();
        model.addAttribute("deptStatus", status);
        return status;
    }

}