package com.metaphorce.metaphorceApi.controller;

import com.metaphorce.metaphorceApi.model.dto.ContractDto;
import com.metaphorce.metaphorceApi.model.Contract;
import com.metaphorce.metaphorceApi.service.ContractService;
import com.metaphorce.metaphorceApi.service.ContractTypeService;
import com.metaphorce.metaphorceApi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {
    @Autowired
    private ContractService contractService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ContractTypeService contractTypeService;

    // create Contract
    @PostMapping()
    public ResponseEntity<Contract> saveContract(@RequestBody ContractDto contract){
        Contract newContract = new Contract();
        newContract.setEmployee(employeeService.getEmployee(contract.getEmployeeId()).get());
        newContract.setContractType(contractTypeService.getContractType(contract.getContractTypeId()).get());
        newContract.setDateFrom(contract.getDateFrom());
        newContract.setDateTo(contract.getDateTo());
        newContract.setSalaryPerDay(contract.getSalaryPerDay());
        newContract.setIsActive(contract.getIsActive());
        newContract.setDateCreated(contract.getDateCreated());

        return new ResponseEntity<Contract>(contractService.saveContract(newContract), HttpStatus.CREATED);
    }

    // Delete contract
    @DeleteMapping("/{id}")
    public ResponseEntity<Contract> deleteContract(@PathVariable BigInteger id){
        return new ResponseEntity<Contract>(contractService.deleteContract(id), HttpStatus.OK);
    }
}
