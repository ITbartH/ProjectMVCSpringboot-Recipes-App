package com.example.lab3pp.controllers;

import com.example.lab3pp.exceptions.RecipeNotFoundException;
import com.example.lab3pp.models.Comment;
import com.example.lab3pp.models.ProfileNames;
import com.example.lab3pp.models.Recipebook;
import com.example.lab3pp.models.User;
import com.example.lab3pp.services.*;
import com.example.lab3pp.validators.CustomValidator;
import com.example.lab3pp.validators.RangeFormat;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Log4j2
@Controller
@SessionAttributes(value= {"recipe", "category"})
@RequestMapping("/recipe-list")
@AllArgsConstructor
@Profile(ProfileNames.Controller)
public class RecipeDetailsController {

    private final RecipeService recipeService;
    private final UserServiceImpl userService;
    private final CommentService commentService;

    private final JavaMailSender emailSender;
    @RolesAllowed("ROLE_USER")
    @GetMapping("/recipe-details")
    public String recipeDetails(Model m, @RequestParam(value = "recipeId") Long id){

        m.addAttribute("recipe", recipeService.findById(id).orElseThrow(() -> new RecipeNotFoundException()));
        m.addAttribute("comments", commentService.findByRecipeId(id));
        m.addAttribute("comment", new Comment());

        return "recipe-details";
    }

    @PostMapping("/recipe-details")
    public String successCommentForm(@Valid @ModelAttribute("comment") Comment comment, BindingResult result, @RequestParam(value = "recipeId") Long id, Model m){

        if (result.hasErrors()) {
            m.addAttribute("recipe", recipeService.findById(id).orElseThrow(() -> new RecipeNotFoundException()));
            m.addAttribute("comments", commentService.findByRecipeId(id));
            return "recipe-details";
        }

        comment.setCommentDate(LocalDate.now());
        comment.setCommentUserName(SecurityContextHolder.getContext().getAuthentication().getName());

        String email = userService.getById(1L).getEmail();

        String text =
                "Witaj administratorze! \n" +
                        "Użytkownik o nazwie (" + comment.getCommentUserName() + ") właśnie skomentował Twój wpis! \n \n" +
                        "Treść komentarza: \n" +
                        comment.getCommentText() + "\n \n" +
                        "Dodany pod wpisem: " + comment.getCommentRecipeName() + "\n";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("przepisyplsystem@gmail.com");
        message.setTo(email);
        message.setSubject("Przepisy.pl - " + comment.getCommentUserName() + " własnie skomentował Twój wpis!!!");
        message.setText(text);
        emailSender.send(message);
        commentService.add(comment);
        recipeService.getById(id).setCommentsCount(recipeService.getById(id).getCommentsCount() + 1);
        recipeService.add(recipeService.getById(id));
        m.addAttribute("recipe", recipeService.findById(id).orElseThrow(() -> new RecipeNotFoundException()));
        m.addAttribute("comments", commentService.findByRecipeId(id));
        m.addAttribute("comment", new Comment());

        return "recipe-details";
    }

    @GetMapping("/recipe-details-delete")
    public String recipeCommentDelete(Model m, @RequestParam(value = "did") Long did, @ModelAttribute("comment") Comment comment, @RequestParam(value = "recipeId") Long id){

        recipeService.getById(id).setCommentsCount(recipeService.getById(id).getCommentsCount() - 1);
        commentService.deleteById(did);
        m.addAttribute("recipe", recipeService.findById(id).orElseThrow(() -> new RecipeNotFoundException()));
        m.addAttribute("comments", commentService.findByRecipeId(id));
        m.addAttribute("comment", new Comment());
        return "recipe-details";
    }

    @InitBinder({"recipe", "recipes"})
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new CustomValidator());
        binder.addCustomFormatter(new RangeFormat());
    }
}
