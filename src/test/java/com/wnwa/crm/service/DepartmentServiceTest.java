package com.wnwa.crm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.wnwa.crm.dao.DepartmentRepository;
import com.wnwa.crm.dao.DeptStatusRepository;
import com.wnwa.crm.entity.County;
import com.wnwa.crm.entity.Department;
import com.wnwa.crm.entity.DepartmentStatus;
import com.wnwa.crm.entity.State;

public class DepartmentServiceTest {
    @Mock
    private DepartmentRepository deptRepositoryMock;
    @Mock
    private DeptStatusRepository deptStatusRepoMock;

    @InjectMocks
    private DepartmentService deptService;
    private Department dept;
    private DepartmentStatus departmentStatus;

    @BeforeEach
    public void init() {

        State state = new State();
        state.setStateId(1);

        County county = new County();
        county.setCountyId(1);

        DepartmentStatus status = new DepartmentStatus();
        status.setStatusId(1);

        dept = new Department();
        dept.setDepartmentId(1);
        dept.setState(state);
        dept.setCounty(county);
        dept.setDepartmentName("HR");
        dept.setStatus(status);
        dept.setComments("Human Resources Department");
        dept.setReminderDate(LocalDateTime.now().plusDays(30));
        dept.setCreationDate(LocalDateTime.now());

    }

    @Test
    public void createDepartmentTest(Department dept) {

        when(deptRepositoryMock.save(any(Department.class))).thenReturn(dept);
        Department createdDept = deptService.createDepartment(dept);
        assertNotNull(createdDept);
        assertEquals(dept.getDepartmentId(), createdDept.getDepartmentId());
        verify(deptRepositoryMock, times(1)).save(dept);

    }

    @Test
    public void createDepartmentStatusTest(DepartmentStatus depts) {
        when(deptStatusRepoMock.save(any(DepartmentStatus.class))).thenReturn(depts);
        DepartmentStatus createdDeptStatus = deptService.createDepartmentStatus(depts);
        assertNotNull(createdDeptStatus);
        assertEquals(depts.getStatusId(), createdDeptStatus.getStatusId());
        verify(deptStatusRepoMock, times(1)).save(depts);

    }

    @Test
    public void testGetStatus() {
        // Given
        List<DepartmentStatus> statuses = Arrays.asList(departmentStatus);
        when(deptStatusRepoMock.findAll()).thenReturn(statuses);

        // When
        List<DepartmentStatus> result = deptService.getStatus();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(departmentStatus, result.get(0));
    }

    @Test
    public void testGetDepartmentById() {
        // Given
        int id = 1;
        when(deptRepositoryMock.findById(id)).thenReturn(Optional.of(dept));

        // When
        Department result = deptService.getDepartmentById(id);

        // Then
        assertNotNull(result);
        assertEquals(dept, result);
    }

    @Test
    public void testGetDepartmentByIdNotFound() {
        // Given
        int id = 1;
        when(deptRepositoryMock.findById(id)).thenReturn(Optional.empty());
        // When
        Department result = deptService.getDepartmentById(id);
        // Then
        assertNull(result);
    }

    @Test
    public void testGetDepartments() {
        // Given
        List<Department> departments = Arrays.asList(dept);
        when(deptRepositoryMock.findAll()).thenReturn(departments);
        // When
        List<Department> result = deptService.getDepartments();
        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dept, result.get(0));
    }


    @Test
    public void testDeleteDeptByID() {
        // When
        String result = deptService.deleteDeptByID(dept.getDepartmentId());

        // Then
        assertEquals("Department Deleted", result);
        verify(deptRepositoryMock, times(1)).deleteById(dept.getDepartmentId());
    }

    @Test
    public void testGetById() {
        // Given
        when(deptRepositoryMock.findById(dept.getDepartmentId())).thenReturn(Optional.of(dept));

        // When
        Department result = deptService.getById(dept.getDepartmentId());

        // Then
        assertNotNull(result);
        assertEquals(dept, result);
        verify(deptRepositoryMock, times(1)).findById(dept.getDepartmentId());
    }

    @Test
    public void testGetByIdNotFound() {
        // Given
        int nonExistentId = 2;
        when(deptRepositoryMock.findById(nonExistentId)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            deptService.getById(nonExistentId);
        });
        assertEquals("Department not found for id : " + nonExistentId, exception.getMessage());
        verify(deptRepositoryMock, times(1)).findById(nonExistentId);
    }

    @Test
    public void testGetSelectedDepts() {
        // Given
        int selectionCriteria = 1;
        List<Department> departments = Arrays.asList(dept);
        when(deptRepositoryMock.getSelectedDepts(selectionCriteria)).thenReturn(departments);

        // When
        List<Department> result = deptService.getSelectedDepts(selectionCriteria);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dept, result.get(0));
        verify(deptRepositoryMock, times(1)).getSelectedDepts(selectionCriteria);
    }

    @Test
    public void testGetDeptListOnCounty() {
        // Given
        Integer stateId = 1;
        Integer countyId = 1;
        List<Department> departments = Arrays.asList(dept);
        when(deptRepositoryMock.findDeptByStateDept(stateId, countyId)).thenReturn(departments);

        // When
        List<Department> result = deptService.getDeptListOnCounty(stateId, countyId);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dept, result.get(0));
        verify(deptRepositoryMock, times(1)).findDeptByStateDept(stateId, countyId);
    }
}
