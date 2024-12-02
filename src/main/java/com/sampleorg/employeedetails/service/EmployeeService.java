package com.sampleorg.employeedetails.service;

import com.sampleorg.employeedetails.model.Employee;
import com.sampleorg.employeedetails.repositry.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long empId) {
        return employeeRepository.findById(empId);
    }

    public Employee updateEmployee(Long empId, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setName(employeeDetails.getName());
        employee.setAge(employeeDetails.getAge());
        employee.setGender(employeeDetails.getGender());
        employee.setCity(employeeDetails.getCity());
        employee.setPinCode(employeeDetails.getPinCode());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
    }
}