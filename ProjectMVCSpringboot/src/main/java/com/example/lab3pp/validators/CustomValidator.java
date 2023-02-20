package com.example.lab3pp.validators;

import com.example.lab3pp.models.Recipe;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CustomValidator implements Validator {
    
    @Override//Wspierana klasa
    public boolean supports(Class<?> aClass) {
        return Recipe.class.isAssignableFrom(aClass);
    }

    @Override//Logika związana z poprawnością danych w obiekcie
    public void validate(Object o, Errors errors) {

        var recipe = (Recipe) o;

        if (recipe.getName().equals(recipe.getMain_recipe())) {
            System.out.println("TEST");
            errors.rejectValue("name", "CustomError.recipe.name");
            errors.rejectValue("main_recipe", "CustomError.recipe.main_recipe");
        }
    }


}
