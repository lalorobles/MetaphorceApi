package com.metaphorce.metaphorceApi.service.impl;

import com.metaphorce.metaphorceApi.model.ContractType;
import com.metaphorce.metaphorceApi.repository.ContractTypeRepository;
import com.metaphorce.metaphorceApi.service.ContractTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractTypeServiceImpl implements ContractTypeService {

    @Autowired
    ContractTypeRepository contractTypeRepository;
    @Override
    public Optional<ContractType> getContractType(Short id) {
        return contractTypeRepository.findById(id);
    }
}
