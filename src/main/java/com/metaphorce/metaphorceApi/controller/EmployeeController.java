package com.metaphorce.metaphorceApi.controller;

import com.metaphorce.metaphorceApi.model.dto.EmployeeDto;
import com.metaphorce.metaphorceApi.model.Employee;
import com.metaphorce.metaphorceApi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // Get active Employees.
    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getActiveEmployees(){
        List<Employee> employees = employeeService.getActiveEmployees();
        List<EmployeeDto> employeesDto = employees.stream().
                map(em -> {
                    EmployeeDto dto = new EmployeeDto();
                    dto.setFullName(em.getName()+" "+em.getLastName());
                    dto.setTaxIdNumber(em.getTaxIdNumber());
                    dto.setEmail(em.getEmail());
                    if (!em.getContractList().isEmpty()){
                        dto.setContractType(em.getContractList().get(0).getContractType().getName());
                        dto.setDateFrom(em.getContractList().get(0).getDateFrom());
                        dto.setDateTo(em.getContractList().get(0).getDateTo());
                        dto.setSalaryPerDay(em.getContractList().get(0).getSalaryPerDay());
                    }
                    return dto;
                }).collect(Collectors.toList());

        return new ResponseEntity<List<EmployeeDto>>(employeesDto, HttpStatus.OK);
    }

    // Create Employee.
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    // Update Employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(id,employee), HttpStatus.CREATED);
    }
}
