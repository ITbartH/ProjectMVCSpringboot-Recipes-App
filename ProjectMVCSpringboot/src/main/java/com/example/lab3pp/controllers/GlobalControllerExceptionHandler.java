package com.example.lab3pp.controllers;

import com.example.lab3pp.exceptions.RecipeNotFoundException;
import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecipeNotFoundException.class)
    public String handleRecipeNotFound() {
        return "errorRecipeNotFound";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({JDBCConnectionException.class, JdbcSQLSyntaxErrorException.class})
    public String handleJDBCConnectionException() {
        return "errorConnection";
    }





}
