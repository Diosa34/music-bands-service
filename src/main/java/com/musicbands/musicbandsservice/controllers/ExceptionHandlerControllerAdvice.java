package com.musicbands.musicbandsservice.controllers;

import com.musicbands.musicbandsservice.exceptions.NotFoundException;
import com.musicbands.musicbandsservice.exceptions.schemas.BadRequestSchema;
import com.musicbands.musicbandsservice.exceptions.schemas.InternalServerErrorSchema;
import com.musicbands.musicbandsservice.exceptions.schemas.ObjectNotFoundSchema;
import com.musicbands.musicbandsservice.exceptions.schemas.ValidationExceptionSchema;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.rmi.ServerException;

@Hidden
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public BadRequestSchema handleBadRequest(IllegalArgumentException exception) {
        return new BadRequestSchema(exception);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    public ObjectNotFoundSchema handleNotFoundExceptions(NotFoundException exception) {
        return new ObjectNotFoundSchema(exception);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class,
            jakarta.validation.ConstraintViolationException.class})
    public ValidationExceptionSchema handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ValidationExceptionSchema(ex);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = ServerException.class)
    public InternalServerErrorSchema handleBadRequest(ServerException exception) {
        return new InternalServerErrorSchema(exception);
    }
}
