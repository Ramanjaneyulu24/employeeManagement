package com.sampleorg.employeedetails.controller;

import com.sampleorg.employeedetails.model.Employee;
import com.sampleorg.employeedetails.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Create Employee
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    // Get All Employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get Employee by ID
    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long empId) {
        Optional<Employee> employee = employeeService.getEmployeeById(empId);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Update Employee
    @PutMapping("/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long empId, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(empId, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Delete Employee
    @DeleteMapping("/{empId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long empId) {
        employeeService.deleteEmployee(empId);
        return ResponseEntity.noContent().build();
    }
}