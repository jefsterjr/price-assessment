package org.capitole.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BindException.class)
    public ErrorMessage resourceNotFoundException(BindException exception) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getFieldError().getDefaultMessage());
    }
}
