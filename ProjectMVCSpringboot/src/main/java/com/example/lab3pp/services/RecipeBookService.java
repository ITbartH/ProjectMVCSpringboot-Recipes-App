package com.example.lab3pp.services;

import com.example.lab3pp.models.Recipebook;
import com.example.lab3pp.repositories.RecipeBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipeBookService {

    private final RecipeBookRepository recipeBookRepo;

    public void add(Recipebook recipeBook){recipeBookRepo.save(recipeBook);}
    public List<Recipebook> findByUserName(String username){return recipeBookRepo.findByUserName(username);}
    public void deleteById(Long id){recipeBookRepo.deleteById(id);}
}
