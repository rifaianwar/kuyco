package com.r3s.kuyco.model.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class CustomerRq {
    @NotBlank(message = "email cannot be empty")
    @NotNull(message = "email cannot be null")
    @Email(message = "Invalid Email")
    private String email;
    @NotBlank(message = "name cannot be empty")
    @NotNull(message = "name cannot be null")
    private String name;
    @NotBlank(message = "phone cannot be empty")
    @NotNull(message = "phone cannot be null")
    @Size(min = 9, message = "Phone number Min 9 Digit")
    private String phone;
    @NotBlank(message = "Address cannot be empty")
    @NotNull(message = "Address cannot be null")
    @Size(min = 10, message = "Address Min 9 Character")
    private String address;
}
