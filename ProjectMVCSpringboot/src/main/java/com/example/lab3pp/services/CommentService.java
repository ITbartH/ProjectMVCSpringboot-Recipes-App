package com.example.lab3pp.services;

import com.example.lab3pp.models.Comment;
import com.example.lab3pp.repositories.CommentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentsRepository commentRepo;

    public List<Comment> findByUserName(String username){
        return commentRepo.findByUserName(username);
    }
    public List<Comment> findByRecipeId(Long id){return commentRepo.findByRecipeId(id);}

    public void add(Comment comment){commentRepo.save(comment);}

    public void deleteById(Long id){
        commentRepo.deleteById(id);
    }

    public void saveAll(List<Comment> commentList) {
        commentRepo.saveAll(commentList);
    }

    public List<Comment> findAll() {
        return commentRepo.findAll();
    }
}
