package com.metaphorce.metaphorceApi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "tbl_contracts")
@Data
@EqualsAndHashCode(callSuper=false)
public class Contract extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private BigInteger ContractId;

    @JsonBackReference(value = "employee-contract")
    public Employee getEmployee() {
        return Employee;
    }

    @JsonBackReference(value = "contractType-contract")
    public ContractType getContractType() {
        return ContractType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "fk_employee_id")
    private Employee Employee;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "fk_contract_type_id")
    private ContractType ContractType;

    @Column(nullable = false)
    private Date DateFrom;
    @Column(nullable = false)
    private Date DateTo;
    @Column(nullable = false)
    private BigDecimal SalaryPerDay;
}
