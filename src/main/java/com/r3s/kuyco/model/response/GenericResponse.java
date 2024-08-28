package com.r3s.kuyco.model.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Setter
@Getter
public class GenericResponse<T> {
    private int statusCode;
    private String message;
    private T data;

    public GenericResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }


}
