package com.metaphorce.metaphorceApi.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {
    @Column(nullable = false)
    public Boolean IsActive;
    @Column(nullable = false)
    public Date DateCreated;
}
