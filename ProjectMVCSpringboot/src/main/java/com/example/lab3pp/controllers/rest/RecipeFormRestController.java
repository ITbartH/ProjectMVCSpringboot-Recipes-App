package com.example.lab3pp.controllers.rest;

import com.example.lab3pp.exceptions.RecipeNotFoundException;
import com.example.lab3pp.models.Category;
import com.example.lab3pp.models.Ingredient;
import com.example.lab3pp.models.ProfileNames;
import com.example.lab3pp.models.Recipe;
import com.example.lab3pp.repositories.CategoryRepository;
import com.example.lab3pp.repositories.IngredientRepository;
import com.example.lab3pp.repositories.RecipeRepository;
import com.example.lab3pp.validators.CustomValidator;
import com.example.lab3pp.validators.RangeFormat;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.context.annotation.Profile;
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
import java.util.List;
import java.util.Optional;

@Log4j2
@SessionAttributes(value = {"recipe","category"})
@RequestMapping("/recipe-list")
@AllArgsConstructor
@RestController
@Profile(ProfileNames.RestController)
public class RecipeFormRestController {
    private final RecipeRepository recipeRepo;
    private final CategoryRepository categoryRepo;
    private final IngredientRepository ingredientRepo;

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/recipe-delete")
    public String deleteRecipe(Model m, @RequestParam(value="did") Optional<Long> did)throws Exception{
        if(did.isPresent()) {
            recipeRepo.deleteById(did.get());
        }
        log.log(Level.DEBUG, "Jestem w /recipe-delete");
        return "redirect:/recipe-list";
    }
    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/recipe-form")
    public Recipe recipeList(@RequestParam(value="id") Optional<Recipe> recipe)throws Exception{

        return recipe.orElse(new Recipe());
    }
    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/recipe-form")
    public Recipe successForm(@Valid @ModelAttribute("recipe") Recipe recipe, MultipartFile multipartFile) throws IOException {

        if(multipartFile.isEmpty() == false) {
            recipe.setFileName(multipartFile.getOriginalFilename());
            recipe.setFileContent(multipartFile.getBytes());
            var base64Content = Base64Utils.encodeToString(recipe.getFileContent());

        }

        recipeRepo.save(recipe);

        return recipe;
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping("/recipe-details")
    public Recipe recipeDetails(@RequestParam(value="id") Optional<Long> id) throws Exception{

        return recipeRepo.findById(id.get()).orElseThrow(() -> new RecipeNotFoundException());
    }

    @InitBinder({"recipe", "recipes"})
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new CustomValidator());
        binder.addCustomFormatter(new RangeFormat());
    }
    @ModelAttribute(value = "category")
    public List<Category> loadDependenciesCategory()
    {
        List<Category> categoryList = returnCategory();

        return categoryList;
    }
    @ModelAttribute(value = "ingredient")
    public List<Ingredient> loadDependenciesIngredient()
    {
        List<Ingredient> ingredientList = returnIngredient();

        return ingredientList;
    }
    @ModelAttribute("categoryList")
    public List<Category> returnCategory(){

        return categoryRepo.findAll();
    }
    @ModelAttribute("ingredientList")
    public List<Ingredient> returnIngredient(){

        return ingredientRepo.findAll();
    }
    public String findNameById(long id)
    {
        return returnCategory().get((int)id).getName();
    }

}
