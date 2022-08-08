package com.metaphorce.metaphorceApi.repository;

import com.metaphorce.metaphorceApi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query(value = "SELECT * FROM tbl_employees as e where e.is_active = true;", nativeQuery = true)
    List<Employee> findActiveEmployees();
    @Query(value = "select * from tbl_employees where tax_id_number = :taxId limit 1;", nativeQuery = true)
    Employee findByTaxIdNumber(@Param("taxId") String taxId);
}
