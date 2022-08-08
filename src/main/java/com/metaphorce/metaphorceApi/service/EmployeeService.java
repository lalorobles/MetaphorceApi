package com.metaphorce.metaphorceApi.service;

import com.metaphorce.metaphorceApi.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    Optional<Employee> getEmployee(Integer id);
    Employee updateEmployee(Integer id, Employee employee);
    List<Employee> getActiveEmployees();
}
