package com.metaphorce.metaphorceApi.repository.dataseed;

import com.metaphorce.metaphorceApi.model.ContractType;
import com.metaphorce.metaphorceApi.repository.ContractTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ContractTypeDataLoader implements CommandLineRunner {
    @Autowired
    ContractTypeRepository contractTypeRepository;
    @Override
    public void run(String... args) throws Exception {
        loadContractTypeData();
    }
    private void loadContractTypeData() {
        if (contractTypeRepository.count() == 0) {
            ContractType contractType1 = new ContractType();
            contractType1.setName("Permanent");
            contractType1.setDateCreated(new Date());
            contractType1.setIsActive(true);
            ContractType contractType2 = new ContractType();
            contractType1.setName("Fixed-Term");
            contractType1.setDateCreated(new Date());
            contractType1.setIsActive(true);
            ContractType contractType3 = new ContractType();
            contractType1.setName("External");
            contractType1.setDateCreated(new Date());
            contractType1.setIsActive(true);

            contractTypeRepository.save(contractType1);
            contractTypeRepository.save(contractType2);
            contractTypeRepository.save(contractType3);
        }
        System.out.println(contractTypeRepository.count());
    }
}
