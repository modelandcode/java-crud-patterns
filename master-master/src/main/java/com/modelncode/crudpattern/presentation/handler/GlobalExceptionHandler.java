package com.modelncode.crudpattern.presentation.handler;

import com.modelncode.crudpattern.domain.exception.EmptyListException;
import com.modelncode.crudpattern.domain.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by g on 2017-06-12.
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    public String handleNotFoundException(NotFoundException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(value = EmptyListException.class)
    public String handleEmptyListException(EmptyListException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e){
        return e.getMessage();
    }
}
