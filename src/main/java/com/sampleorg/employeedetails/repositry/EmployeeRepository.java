package com.sampleorg.employeedetails.repositry;

import com.sampleorg.employeedetails.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}