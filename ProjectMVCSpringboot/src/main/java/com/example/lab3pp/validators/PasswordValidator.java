package com.example.lab3pp.validators;

import com.example.lab3pp.models.Recipe;
import com.example.lab3pp.models.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordValidator implements Validator {

    @Override//Wspierana klasa
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override//Logika związana z poprawnością danych w obiekcie
    public void validate(Object u, Errors errors) {

        var user = (User) u;

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            System.out.println("TEST");
            errors.rejectValue("password", "PasswordValidator.user.password");
            errors.rejectValue("passwordConfirm", "PasswordValidator.user.passwordConfirm");
        }
    }
}
