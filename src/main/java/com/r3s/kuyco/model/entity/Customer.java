package com.r3s.kuyco.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;

import java.util.Date;

@Data
@Entity
@Table
@JsonIgnoreProperties(value = { "created_date", "updated_date", "deleted_date" })
@SQLRestriction(value = "deleted_date is null")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSq")
    @SequenceGenerator(name = "customerSq", sequenceName = "customerSq", allocationSize = 1)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    private String name;
    private String phone;
    private String address;
    private Date createdDate;
    private Date updatedDate;
    private Date deletedDate;
}
