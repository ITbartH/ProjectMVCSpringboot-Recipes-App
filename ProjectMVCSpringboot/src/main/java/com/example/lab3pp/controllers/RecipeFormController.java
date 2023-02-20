package com.example.lab3pp.controllers;

import com.example.lab3pp.exceptions.RecipeNotFoundException;
import com.example.lab3pp.models.*;
import com.example.lab3pp.repositories.*;
import com.example.lab3pp.services.*;
import com.example.lab3pp.validators.CustomValidator;
import com.example.lab3pp.validators.RangeFormat;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;

import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Log4j2
@Controller
@SessionAttributes(value = {"recipe", "category"})
@RequestMapping("/recipe-list")
@AllArgsConstructor
@Profile(ProfileNames.Controller)
public class RecipeFormController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;
    private final IngredientService ingredientService;

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/recipe-delete")
    public String deleteRecipe(Model m, @RequestParam(value = "did") Optional<Long> did) throws Exception {
        if (did.isPresent()) {
            recipeService.deleteById(did.get());
        }

        return "redirect:/recipe-list";
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/recipe-form")
    public String recipeList(Model m, @RequestParam(value = "id") Optional<Recipe> recipe) throws Exception {

        m.addAttribute("recipe", recipe.orElse(new Recipe()));

        return "recipe-form";
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/recipe-form")
    public String successForm(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult result, Model m, MultipartFile multipartFile) throws IOException {

        if (result.hasErrors()) {
            return "recipe-form";
        }
        if (multipartFile.isEmpty() == false) {
            recipe.setFileName(multipartFile.getOriginalFilename());
            recipe.setFileContent(multipartFile.getBytes());
            var base64Content = Base64Utils.encodeToString(recipe.getFileContent());
        }
        recipe.setDate(LocalDate.now());
        recipeService.add(recipe);
        m.addAttribute("recipe", recipe);
        return "confirmForm";
    }

    @InitBinder({"recipe", "recipes"})
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new CustomValidator());
        binder.addCustomFormatter(new RangeFormat());
    }

    @ModelAttribute(value = "category")
    public List<Category> loadDependenciesCategory() {
        List<Category> categoryList = returnCategory();

        return categoryList;
    }

    @ModelAttribute(value = "ingredient")
    public List<Ingredient> loadDependenciesIngredient() {
        List<Ingredient> ingredientList = returnIngredient();

        return ingredientList;
    }

    @ModelAttribute("categoryList")
    public List<Category> returnCategory() {

        return categoryService.findAll();
    }

    @ModelAttribute("ingredientList")
    public List<Ingredient> returnIngredient() {

        return ingredientService.findALl();
    }

    public String findNameById(long id) {
        return returnCategory().get((int) id).getName();
    }

}
