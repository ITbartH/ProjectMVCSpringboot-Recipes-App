package com.example.lab3pp.repositories;

import com.example.lab3pp.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByNameContainingIgnoreCase(String name);

    // Zapytania nazywane //

    List<Recipe> findByName(String name);
    List<Recipe> findByCategory(String category);
    List<Recipe> findByCookingTime(int cookingTimeH, int cookingTimeM);
    List<Recipe> findByDate(LocalDate date);
    List<Recipe> findByCost(double cost);
    List<Recipe> findByIngredients(String ingredient);

    // Zapytania Query //
    @Query("select r from Recipe r where r.name like %:name")
    List<Recipe> findByName2(String name);
    @Query("select r from Recipe r where r.category.name like %:category")
    List<Recipe> findByCategory2(String category);
    @Query("select r from Recipe r where r.cookingTime.h = ?1 AND r.cookingTime.min = ?2")
    List<Recipe> findByCookingTime2(int cookingTimeH, int cookingTimeM);
    @Query("select r from Recipe r where r.date = :date")
    List<Recipe> findByDate2(LocalDate date);
    @Query("select r from Recipe r where r.cost = :cost")
    List<Recipe> findByCost2(double cost);

    @Query("select r from Recipe r where r.cost >= ?1")
    List<Recipe> findMoreThanCost(double cost);

    @Query("select r from Recipe r where r.cost < ?1")
    List<Recipe> findLessThanCost(double cost);

    @Query("select r from Recipe r where r.cost > ?1 AND r.cost < ?2")
    List<Recipe> findBetweenCost(double costmin, double costmax);

    @Query("select r from Recipe r where r.commentsCount in (SELECT MAX(x.commentsCount) from Recipe x)")
    List<Recipe> getMostCommented();

    @Query("select r from Recipe r order by r.commentsCount DESC")
    List<Recipe> getMostCommentedSort();

}

