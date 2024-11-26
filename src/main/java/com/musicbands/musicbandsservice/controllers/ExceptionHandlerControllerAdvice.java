package com.musicbands.musicbandsservice.controllers;

import com.musicbands.musicbandsservice.exceptions.NotFoundException;
import com.musicbands.musicbandsservice.exceptions.ServiceUnavailableException;
import com.musicbands.musicbandsservice.schemas.exceptions.Http404Schema;
import com.musicbands.musicbandsservice.schemas.exceptions.Http422Schema;
import com.musicbands.musicbandsservice.schemas.exceptions.Http503Schema;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Hidden
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NotFoundException.class})
    public Http404Schema handleNotFoundExceptions(NotFoundException ex) {
        return new Http404Schema(ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class,
            jakarta.validation.ConstraintViolationException.class})
    public Http422Schema handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new Http422Schema(errors);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(value = ServiceUnavailableException.class)
    public Http503Schema handleServiceUnavailableException(ServiceUnavailableException ex) {
        return new Http503Schema(ex.getMessage());
    }

}
