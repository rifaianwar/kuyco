package com.r3s.kuyco.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ItemRq {
    @NotBlank(message = "name cannot be empty")
    @NotNull(message = "name cannot be null")
    private String name;
    private String desc;
    @NotNull(message = "price cannot be null")
    private int price;
    @NotNull(message = "categories cannot be null")
    private String[] categories;
}
