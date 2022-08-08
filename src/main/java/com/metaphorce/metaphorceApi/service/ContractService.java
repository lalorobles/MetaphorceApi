package com.metaphorce.metaphorceApi.service;

import com.metaphorce.metaphorceApi.model.Contract;

import java.math.BigInteger;
import java.util.List;

public interface ContractService {
    public Contract saveContract(Contract contract);
    public Contract deleteContract(BigInteger id);
    public List<Contract> getContracts();
}
