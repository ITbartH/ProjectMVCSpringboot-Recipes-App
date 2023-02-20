package com.example.lab3pp.controllers;

import com.example.lab3pp.exceptions.RecipeNotFoundException;
import com.example.lab3pp.models.Category;
import com.example.lab3pp.models.Ingredient;
import com.example.lab3pp.models.ProfileNames;
import com.example.lab3pp.services.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Log4j2
@Controller
@AllArgsConstructor
@Profile(ProfileNames.Controller)
public class IngredientController {
    private final UserServiceImpl userService;
    private final RoleService roleService;
    private final CategoryService categoryService;
    private final IngredientService ingredientService;
    private final RecipeService recipeService;


    @RolesAllowed("ADMIN")
    @PostMapping("/addIngredient")
    public String addIngredient(Model m, @Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult result){

        if (result.hasErrors()) {
            m.addAttribute("usersList", userService.findAll());
            m.addAttribute("rolesList", roleService.findAll());

            m.addAttribute("categoryList",categoryService.findAll());
            m.addAttribute("category", new Category());

            m.addAttribute("ingredientList",ingredientService.findALl());
            return "userList";
        }
        ingredientService.add(ingredient);
        m.addAttribute("usersList", userService.findAll());
        m.addAttribute("rolesList", roleService.findAll());

        m.addAttribute("categoryList",categoryService.findAll());
        m.addAttribute("category", new Category());

        m.addAttribute("ingredientList",ingredientService.findALl());
        m.addAttribute("ingredient", new Ingredient());
        return "redirect:/userList";

    }

    @RolesAllowed("ADMIN")
    @GetMapping("/deleteIngredient")
    public String deleteIngredient(Model m, @RequestParam(value="did") Long did){

        if(!recipeService.isRecipeWithThisIngredientExist(ingredientService.getById(did).getIng_name()))
        {
            ingredientService.deleteById(did);
        }

        m.addAttribute("usersList", userService.findAll());
        m.addAttribute("rolesList", roleService.findAll());

        m.addAttribute("categoryList",categoryService.findAll());
        m.addAttribute("category", new Category());

        m.addAttribute("ingredientList",ingredientService.findALl());
        m.addAttribute("ingredient", new Ingredient());
        return "redirect:/userList";
    }
}
