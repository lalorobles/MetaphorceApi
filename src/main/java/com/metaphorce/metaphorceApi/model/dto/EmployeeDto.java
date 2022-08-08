package com.metaphorce.metaphorceApi.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class EmployeeDto {
    private String FullName;
    private String TaxIdNumber;
    private String Email;
    private String ContractType;
    private Date DateFrom;
    private Date DateTo;
    private BigDecimal SalaryPerDay;
}
