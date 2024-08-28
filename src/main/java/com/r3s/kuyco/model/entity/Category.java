package com.r3s.kuyco.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table
@JsonIgnoreProperties(value = { "created_date", "updated_date", "deleted_date" })
@SQLRestriction(value = "deleted_date is null")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySq")
    @SequenceGenerator(name = "categorySq", sequenceName = "categorySq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories")
    private List<Item> items;

    private Date createdDate;
    private Date updatedDate;
    private Date deletedDate;
}
