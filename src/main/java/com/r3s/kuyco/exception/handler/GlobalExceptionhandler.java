package com.r3s.kuyco.exception.handler;

import com.r3s.kuyco.exception.BadRequestException;
import com.r3s.kuyco.exception.NotFoundException;
import com.r3s.kuyco.model.response.ErrorResponse;
import com.r3s.kuyco.model.response.GenericResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionhandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    FieldError fieldError = (FieldError) error;
                    return fieldError.getField() + ": " + error.getDefaultMessage();
                })
                .collect(Collectors.toList());


        ErrorResponse errorRs = new ErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                errors
        );

        return new ResponseEntity<>(errorRs, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFound(NotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(Instant.now(),HttpStatus.NOT_FOUND.value(),"NOT_FOUND",e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> dataIntegrityViolation(DataIntegrityViolationException e) {
        ErrorResponse errorResponse = new ErrorResponse(Instant.now(),HttpStatus.CONFLICT.value(),"DATA_INTEGRITY_VIOLATION",e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequest(BadRequestException e) {
        ErrorResponse errorResponse = new ErrorResponse(Instant.now(),HttpStatus.BAD_REQUEST.value(),"BAD_REQUEST",e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgument(IllegalArgumentException e) {
        ErrorResponse errorResponse = new ErrorResponse(Instant.now(),HttpStatus.BAD_REQUEST.value(),"Illegal Argument",e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
