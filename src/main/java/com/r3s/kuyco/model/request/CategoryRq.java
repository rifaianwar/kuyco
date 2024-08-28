package com.r3s.kuyco.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.r3s.kuyco.model.entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryRq {
    @NotBlank(message = "category name cannot be empty")
    @NotNull(message = "category name cannot be null")
    private String name;
}
