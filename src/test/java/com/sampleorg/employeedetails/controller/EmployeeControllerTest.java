package com.sampleorg.employeedetails.controller;

import com.sampleorg.employeedetails.model.Employee;
import com.sampleorg.employeedetails.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeControllerTest {
    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee();
        employee.setEmpId(1L);
        employee.setName("John Doe");
        employee.setAge(30);
        employee.setGender("Male");
        employee.setCity("New York");
        employee.setPinCode("10001");
    }

    @Test
    public void testCreateEmployee() {
        when(employeeService.createEmployee(employee)).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.createEmployee(employee);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    public void testGetEmployeeById() {
        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(employee));

        ResponseEntity<Employee> response = employeeController.getEmployeeById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    public void testGetEmployeeByIdNotFound() {
        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Employee> response = employeeController.getEmployeeById(1L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testUpdateEmployee() {
        when(employeeService.updateEmployee(1L, employee)).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.updateEmployee(1L, employee);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    public void testDeleteEmployee() {
        doNothing().when(employeeService).deleteEmployee(1L);

        ResponseEntity<Void> response = employeeController.deleteEmployee(1L);

        assertEquals(204, response.getStatusCodeValue());
    }
}