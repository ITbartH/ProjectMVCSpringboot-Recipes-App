package com.example.lab3pp.controllers;

import com.example.lab3pp.controllers.filters.RecipeFilter;
import com.example.lab3pp.models.ProfileNames;
import com.example.lab3pp.services.RecipeService;
import com.example.lab3pp.validators.CustomValidator;
import com.example.lab3pp.validators.RangeFormat;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;


@Log4j2
@Controller
@AllArgsConstructor
@Profile(ProfileNames.Controller)
public class HomeController {

    private final RecipeService recipeService;

    @GetMapping("/")
    public String getMostCommentedRecipes(Model m){
        m.addAttribute("mostCommentedRecipes", recipeService.getMostCommentedRecipesSort());
        return "index";
    }
    @GetMapping("/recipe-list")
    public String recipeList(Model m, RecipeFilter recipeFilter){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        m.addAttribute("recipes", recipeService.recipeList(recipeFilter.getKeyword(), recipeFilter.getFilterX(), recipeFilter.getFilterY()));

        return "recipe-list";
    }
    @GetMapping("/shutdown")
    public String shutdown() throws SQLException {
        Connection conn = DriverManager.getConnection ("jdbc:h2:mem:recipedb", "przepisypl","admin");
        conn.createStatement().execute("SHUTDOWN");
        conn.close();
        return "errorConnection";
    }

    @InitBinder({"recipe", "recipes"})
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new CustomValidator());
        binder.addCustomFormatter(new RangeFormat());
    }
}
