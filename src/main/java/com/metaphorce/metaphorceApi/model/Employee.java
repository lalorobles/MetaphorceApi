package com.metaphorce.metaphorceApi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_employees", uniqueConstraints = {@UniqueConstraint(columnNames = {"TaxIdNumber"})})
@Data
@EqualsAndHashCode(callSuper=false)
public class Employee extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer EmployeeId;
    @Column(length=13, nullable = false)
    private String TaxIdNumber;
    @Column(length=60, nullable = false)
    private String Name;
    @Column(length=120, nullable = false)
    private String LastName;
    @Column(nullable = false)
    private Date BirthDate;
    @Column(length=60, nullable = false)
    private String Email;
    @Column(length=20, nullable = false)
    private String CellPhone;

    @OneToMany(mappedBy = "Employee", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "employee_id")
    @Where(clause = "is_active = 1")
    private List<Contract> ContractList = new ArrayList<>();

    @JsonManagedReference(value = "employee-contract")
    public List<Contract> getContractList() {
        return ContractList;
    }

}
