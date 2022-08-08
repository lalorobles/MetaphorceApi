package com.metaphorce.metaphorceApi.service;

import com.metaphorce.metaphorceApi.model.ContractType;

import java.util.Optional;

public interface ContractTypeService {
    Optional<ContractType> getContractType(Short id);
}
