package com.metaphorce.metaphorceApi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_contract_types")
@Data
@EqualsAndHashCode(callSuper=false)
public class ContractType extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short ContractTypeId;
    @Column(length=80, nullable = false)
    private String Name;
    private String Description;

    @JsonManagedReference(value = "contractType-contract")
    @OneToMany(mappedBy = "ContractType", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "contract_type_id")
    private List<Contract> ContractList = new ArrayList<>();
}
