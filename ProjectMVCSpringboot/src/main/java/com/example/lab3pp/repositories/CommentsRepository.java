package com.example.lab3pp.repositories;

import com.example.lab3pp.models.Comment;
import com.example.lab3pp.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where c.commentRecipeId = :id")
    List<Comment> findByRecipeId(long id);
    @Query("select c from Comment c where c.commentUserName like %:name")
    List<Comment> findByUserName(String name);
    @Query("select c from Comment c where c.id = :id")
    Comment findById(long id);
}
