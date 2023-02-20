package com.example.lab3pp.controllers;


import com.example.lab3pp.models.ProfileNames;
import com.example.lab3pp.models.Recipebook;
import com.example.lab3pp.models.User;
import com.example.lab3pp.services.*;
import com.example.lab3pp.validators.CustomValidator;
import com.example.lab3pp.validators.RangeFormat;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@Log4j2
@Controller
@AllArgsConstructor
@Profile(ProfileNames.Controller)
public class RecipeBookController {

    private final RecipeService recipeService;
    private final RecipeBookService recipeBookService;

    @RolesAllowed("USER, ADMIN")
    @PostMapping("/recipeBook")
    public String recipeBookForm(Model m, @Valid @ModelAttribute("recipeBook") Recipebook recipeBook, BindingResult result, @RequestParam(value = "userId") User user){

        if(result.hasErrors()){
            m.addAttribute("user", user);
            m.addAttribute("recipeList", recipeService.findAll());
            m.addAttribute("recipeBooks", recipeBookService.findByUserName(user.getUsername()));
            return "recipeBook";
        }
        recipeBookService.add(recipeBook);
        m.addAttribute("user", user);
        m.addAttribute("recipeList", recipeService.findAll());
        m.addAttribute("recipeBooks", recipeBookService.findByUserName(user.getUsername()));
        m.addAttribute("recipeBook", new Recipebook());
        return "recipeBook";
    }

    @RolesAllowed("USER, ADMIN")
    @GetMapping("/recipeBook")
    public String recipeBook(Model m, @RequestParam(value="userId") User user){
        m.addAttribute("user", user);
        m.addAttribute("recipeList", recipeService.findAll());
        m.addAttribute("recipeBooks", recipeBookService.findByUserName(user.getUsername()));
        m.addAttribute("recipeBook", new Recipebook());
        return "recipeBook";
    }

    @RolesAllowed("USER, ADMIN")
    @GetMapping("/recipeBook-delete")
    public String deleteRecipe(Model m, @RequestParam(value = "did") Optional<Long> did, @RequestParam(value="userId") User user) throws Exception {
        if (did.isPresent()) {
            recipeBookService.deleteById(did.get());
        }

        m.addAttribute("user", user);
        m.addAttribute("recipeList", recipeService.findAll());
        m.addAttribute("recipeBooks", recipeBookService.findByUserName(user.getUsername()));
        m.addAttribute("recipeBook", new Recipebook());
        return "recipeBook";
    }

    @InitBinder({"recipe", "recipes", "recipeList"})
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new CustomValidator());
        binder.addCustomFormatter(new RangeFormat());
    }
}
