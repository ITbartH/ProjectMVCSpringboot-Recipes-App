package com.example.lab3pp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Nie znaleziono przepisu")
public class RecipeNotFoundException extends RuntimeException {
}
