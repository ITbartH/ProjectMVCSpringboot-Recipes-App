package com.example.lab3pp.controllers;

import com.example.lab3pp.models.*;
import com.example.lab3pp.services.*;
import com.example.lab3pp.validators.PasswordValidator;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Log4j2
@Controller
@AllArgsConstructor
@Profile(ProfileNames.Controller)
public class UserController {


    private final UserServiceImpl userService;
    private final CommentService commentService;
    private final RoleService roleService;
    private final CategoryService categoryService;
    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView( "login");
    }

    @GetMapping("/user-form")
    public String userFormGet(Model m, @RequestParam(value="id") Optional<User> user)throws Exception{

        m.addAttribute("user", user.orElse(new User()));

        return "user-form";
    }

    @PostMapping("/user-form")
    public String userFormPost(@Valid @ModelAttribute("user") User user, BindingResult result, Model m) {

        if(result.hasErrors()){
            m.addAttribute("usersList", userService.findAll());
            return "user-form";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userService.add(user);

        m.addAttribute("user", user);
        return "confirmUserForm";
    }

    @GetMapping("/userDetails")
    public String userDetails(Model m, @RequestParam(value="id") User user) throws IOException {

        m.addAttribute("user", user);
        m.addAttribute("comments", commentService.findByUserName(user.getUsername()));
        return "userDetails";
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/userDelete")
    public String userDelete(Model m, @RequestParam(value="did") Long did){
        userService.deleteById(did);
        m.addAttribute("usersList", userService.findAll());
        return "redirect:/userList";
    }

    @PostMapping("/userDetails")
    public String succesUserForm(Model m, @ModelAttribute(value="user") User user, Optional<MultipartFile> multipartFile) throws IOException {

        if(multipartFile.isPresent()) {
            if (multipartFile.get().isEmpty() == false) {
                user.setFileName(multipartFile.get().getOriginalFilename());
                user.setFileContent(multipartFile.get().getBytes());
                var base64Content = Base64Utils.encodeToString(user.getFileContent());
            }else{
                user.setFileName(userService.getById(user.getId()).getFileName());
                user.setFileContent(userService.getById(user.getId()).getFileContent());
            }
        }
        userService.add(user);
        m.addAttribute("user", user);
        m.addAttribute("comments", commentService.findByUserName(user.getUsername()));

        return "userDetails";
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/userList")
    public String userList(Model m){
        m.addAttribute("usersList", userService.findAll());
        m.addAttribute("rolesList", roleService.findAll());
        m.addAttribute("categoryList",categoryService.findAll());
        m.addAttribute("category", new Category());
        m.addAttribute("ingredientList",ingredientService.findALl());
        m.addAttribute("ingredient", new Ingredient());
        return "userList";
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/editUser")
    public String userEdit(Model m, @RequestParam(value="id") User user){

        m.addAttribute("user", user);
        return"editUser";
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/editUser")
    public String userEditForm(@ModelAttribute("user") User user, @RequestParam(value="id") Long id, BindingResult result, Model m){
        if(result.hasErrors()){
            m.addAttribute("user", user);
            return "editUser";
        }
        if(user.getFirstname().isEmpty()){
            user.setFirstname(null);
        }
        if(user.getLastname().isEmpty()){
                user.setLastname(null);
        }
        if(user.getEmail().isEmpty()){
             user.setEmail(null);
        }
        userService.add(user);
        m.addAttribute("user", user);
        return "editUser";
    }

    @InitBinder("user")
    public void initBinderUser(WebDataBinder binder){
        binder.addValidators(new PasswordValidator());
    }

    @ModelAttribute("rolesList")
    public List<Role> returnRole() {

        return roleService.findAll();
    }
    @ModelAttribute(value = "role")
    public List<Role> loadDependenciesIngredient() {
        List<Role> roleList = returnRole();

        return roleList;
    }


}
