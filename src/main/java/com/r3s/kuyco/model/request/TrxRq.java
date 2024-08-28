package com.r3s.kuyco.model.request;

import com.r3s.kuyco.model.entity.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class TrxRq {
    @NotBlank(message = "email cannot be empty")
    @NotNull(message = "email cannot be null")
    private String email;
    @NotNull(message = "item cannot be null")
    private TrxItemRq item;
}
