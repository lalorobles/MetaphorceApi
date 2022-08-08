package com.metaphorce.metaphorceApi.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
public class ContractDto {
    private BigInteger ContractId;
    private Integer EmployeeId;
    private Short ContractTypeId;
    private Date DateFrom;
    private Date DateTo;
    private BigDecimal SalaryPerDay;
    public Boolean IsActive;
    public Date DateCreated;
}
