package com.example.lab3pp.services;

import com.example.lab3pp.models.Category;
import com.example.lab3pp.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepo;

    public List<Category> findAll(){
        return categoryRepo.findAll();
    }
    public void add(Category category){ categoryRepo.save(category); }

    public void deleteById(Long did) {
        categoryRepo.deleteById(did);
    }

    public Category getById(Long id) {
        return categoryRepo.getById(id);
    }

    public void saveAll(List<Category> categoryList) {
        categoryRepo.saveAll(categoryList);
    }
}
