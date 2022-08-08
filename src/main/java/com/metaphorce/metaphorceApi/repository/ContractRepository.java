package com.metaphorce.metaphorceApi.repository;

import com.metaphorce.metaphorceApi.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;

public interface ContractRepository extends JpaRepository<Contract, BigInteger> {
    @Query(value = "select * from tbl_contracts where fk_employee_id = :employeeId and is_active = 1 limit 1;", nativeQuery = true)
    Contract findByEmployeeId(@Param("employeeId") Integer employeeId);
}
