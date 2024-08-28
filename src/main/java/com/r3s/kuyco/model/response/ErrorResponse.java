package com.r3s.kuyco.model.response;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;

@Setter
@Getter
public class ErrorResponse {
    private Instant timestamp;
    private int status;
    private String message;
    private Object error;

    public ErrorResponse(Instant timestamp, int httpStatus, String message, Object error) {
        this.timestamp = timestamp;
        this.status = httpStatus;
        this.message = message;
        this.error = error;
    }
}
