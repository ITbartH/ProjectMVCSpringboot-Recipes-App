package com.example.lab3pp.repositories;

import com.example.lab3pp.models.Recipebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeBookRepository extends JpaRepository<Recipebook, Long> {

    @Query("select r from Recipebook r where r.bookUserName like %:name")
    List<Recipebook> findByUserName(String name);
}
