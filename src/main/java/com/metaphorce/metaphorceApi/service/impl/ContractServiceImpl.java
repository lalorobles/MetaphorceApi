package com.metaphorce.metaphorceApi.service.impl;

import com.metaphorce.metaphorceApi.model.Contract;
import com.metaphorce.metaphorceApi.repository.ContractRepository;
import com.metaphorce.metaphorceApi.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    ContractRepository contractRepository;
    @Override
    public Contract saveContract(Contract contract) {
        Contract contractExist = contractRepository.findByEmployeeId(contract.getEmployee().getEmployeeId());
        if (contractExist != null) {
            // Update contract, set IsActive = false.
            contractExist.setIsActive(false);
            contractExist.setDateTo(new Date());
            contractRepository.save(contractExist);
        }
        return contractRepository.save(contract);
    }

    @Override
    public Contract deleteContract(BigInteger id) {
        Optional<Contract> contract = contractRepository.findById(id);

        if(!contract.isPresent())
            throw new NoSuchElementException("Contract not found");

        Contract c = contract.get();

        if(!c.getIsActive())
            throw new NoSuchElementException("The contract its already inactive");
        c.setIsActive(false);
        c.setDateTo(new Date());

        contractRepository.save(c);
        return c;
    }

    @Override
    public List<Contract> getContracts() {
        return contractRepository.findAll();
    }
}
