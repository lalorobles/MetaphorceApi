package com.metaphorce.metaphorceApi.service.impl;

import com.metaphorce.metaphorceApi.model.Employee;
import com.metaphorce.metaphorceApi.repository.EmployeeRepository;
import com.metaphorce.metaphorceApi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public static final Pattern VALID_TAX_ID_REGEX =
            Pattern.compile("^([A-ZÑ\\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1]))((-)?([A-Z\\d]{3}))?$", Pattern.CASE_INSENSITIVE);

    public static boolean validateTaxId(String emailStr) {
        Matcher matcher = VALID_TAX_ID_REGEX.matcher(emailStr);
        return matcher.find();
    }
    @Override
    public Optional<Employee> getEmployee(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent())
            throw new NoSuchElementException("Employee not found");

        return  employee;
    }
    @Override
    public Employee saveEmployee(Employee employee) {

        Boolean taxIdValid = validateTaxId(employee.getTaxIdNumber());
        if (!taxIdValid)
            throw new IllegalArgumentException("TaxId has bad format.");

        Employee employeeExist = employeeRepository.findByTaxIdNumber(employee.getTaxIdNumber());
        if (employeeExist!=null)
            throw new DuplicateKeyException("There is already an Employee with the same TaxId");

        return employeeRepository.save(employee);
    }
    @Override
    public Employee updateEmployee(Integer id, Employee employee) {

        Employee employeeExist = employeeRepository.findById(id).get();
        if (employeeExist==null)
            throw new NoSuchElementException("Employee not found");

        Boolean taxIdValid = validateTaxId(employee.getTaxIdNumber());
        if (!taxIdValid)
            throw new IllegalArgumentException("TaxId has bad format.");

        Employee taxIdExist = employeeRepository.findByTaxIdNumber(employee.getTaxIdNumber());
        if (taxIdExist!=null)
            throw new DuplicateKeyException("There is already an Employee with the same TaxId");

        employeeExist.setTaxIdNumber(employee.getTaxIdNumber());
        employeeExist.setName(employee.getName());
        employeeExist.setLastName(employee.getLastName());
        employeeExist.setBirthDate(employee.getBirthDate());
        employeeExist.setEmail(employee.getEmail());
        employeeExist.setCellPhone(employee.getCellPhone());
        employeeExist.setIsActive(employee.getIsActive());

        return employeeRepository.save(employeeExist);
    }
    @Override
    public List<Employee> getActiveEmployees() {
        return employeeRepository.findActiveEmployees();
    }
}
