package com.r3s.kuyco.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table
@JsonIgnoreProperties(value = { "created_date", "updated_date", "deleted_date" })
@SQLRestriction(value = "deleted_date is null")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSq")
    @SequenceGenerator(name = "itemSq", sequenceName = "itemSq", allocationSize = 1)
    private Long id;
    private String name;
    private String description;
    private int price;
    private Date createdDate;
    private Date updatedDate;
    private Date deletedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "item_category", joinColumns = {
            @JoinColumn(name = "item_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private List<Category> categories = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "items")
    private List<Transaction> transcations;
}


