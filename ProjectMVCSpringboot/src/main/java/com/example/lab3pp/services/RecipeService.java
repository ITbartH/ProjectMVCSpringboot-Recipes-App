package com.example.lab3pp.services;

import com.example.lab3pp.models.Recipe;
import com.example.lab3pp.repositories.CategoryRepository;
import com.example.lab3pp.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepo;
    private final CategoryRepository categoryRepo;

    public List<Recipe> findAll() {return recipeRepo.findAll();}
    public List<Recipe> recipeList(String keyword, String filterX, String filterY){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

        if(filterX != null){
            int choice_int = Integer.parseInt(filterX);
            if(filterY != null) {
                if(Integer.parseInt(filterY) != 3) choice_int = 1;
            }
            switch(choice_int){
                case 0:
                    return recipeRepo.findByName2("%" + keyword + "%");

                case 1:
                    keyword = categoryRepo.getById(Long.parseLong(filterY) + 1L).getName();
                    return recipeRepo.findByCategory2("%" + keyword + "%");

                case 2:
                    String[] result = keyword.split(":");
                    return recipeRepo.findByCookingTime2(Integer.parseInt(result[0]),Integer.parseInt(result[1]));

                case 3:
                    return recipeRepo.findByDate2(LocalDate.parse(keyword, formatter));

                case 4:
                    String[] results = keyword.split(" - ");
                    if(results.length > 1){
                        return recipeRepo.findBetweenCost(Double.parseDouble(results[0]), Double.parseDouble(results[1]));
                    }else
                        return recipeRepo.findByCost2(Double.parseDouble(keyword));

                case 5:
                    return recipeRepo.findByIngredients(keyword);
                default: break;
            }
        }

        return recipeRepo.findAll();
    }
    public void deleteById(Long id){recipeRepo.deleteById(id);}
    public void add(Recipe recipe){recipeRepo.save(recipe);}
    public Optional<Recipe> findById(Long id){

        return recipeRepo.findById(id);
    }
    public boolean isRecipeWithThisCategoryExist(String catName){

        if(recipeRepo.findByCategory2(catName).isEmpty())
        {
            return false;
        }
        return true;
    }

    public boolean isRecipeWithThisIngredientExist(String ingName){
        if(recipeRepo.findByIngredients(ingName).isEmpty()){
            return false;
        }
        return true;
    }

    public Recipe getById(Long id){ return recipeRepo.getById(id);}
    public List<Recipe> getMostCommentedRecipes(){

        return recipeRepo.getMostCommented().stream().limit(3).collect(Collectors.toList());
    }

    public List<Recipe> getMostCommentedRecipesSort(){

        return recipeRepo.getMostCommentedSort().stream().limit(3).collect(Collectors.toList());
    }

    public void saveAll(List<Recipe> recipeList) {
        recipeRepo.saveAll(recipeList);
    }
}
