package com.sampleorg.employeedetails.service;

import com.sampleorg.employeedetails.model.Employee;
import com.sampleorg.employeedetails.repositry.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
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
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee createdEmployee = employeeService.createEmployee(employee);

        assertNotNull(createdEmployee);
        assertEquals("John Doe", createdEmployee.getName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testGetEmployeeById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> foundEmployee = employeeService.getEmployeeById(1L);

        assertTrue(foundEmployee.isPresent());
        assertEquals("John Doe", foundEmployee.get().getName());
    }

    @Test
    public void testGetEmployeeByIdNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Employee> foundEmployee = employeeService.getEmployeeById(1L);

        assertFalse(foundEmployee.isPresent());
    }

    @Test
    public void testUpdateEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);

        employee.setName("John Smith");
        Employee updatedEmployee = employeeService.updateEmployee(1L, employee);

        assertNotNull(updatedEmployee);
        assertEquals("John Smith", updatedEmployee.getName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testUpdateEmployeeNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            employeeService.updateEmployee(1L, employee);
        });

        assertEquals("Employee not found", exception.getMessage());
    }

    @Test
    public void testDeleteEmployee() {
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteEmployeeNotFound() {
        doThrow(new RuntimeException("Employee not found")).when(employeeRepository).deleteById(1L);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            employeeService.deleteEmployee(1L);
        });

        assertEquals("Employee not found", exception.getMessage());
    }
}