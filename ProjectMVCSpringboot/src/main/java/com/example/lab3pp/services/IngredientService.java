package com.example.lab3pp.services;

import com.example.lab3pp.models.Ingredient;
import com.example.lab3pp.repositories.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;
import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepo;

    public List<Ingredient> findALl(){
        return ingredientRepo.findAll();
    }

    public void add(Ingredient ing){
        ingredientRepo.save(ing);
    }
    public void deleteById(Long id){
        ingredientRepo.deleteById(id);
    }

    public void saveAll(List<Ingredient> ingredientList) {
        ingredientRepo.saveAll(ingredientList);
    }

    public List<Ingredient> findAll() {
        return ingredientRepo.findAll();
    }

    public Ingredient getById(Long did) {
        return ingredientRepo.getById(did);
    }
}
