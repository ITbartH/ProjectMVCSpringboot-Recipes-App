package com.example.lab3pp.controllers.rest;

import com.example.lab3pp.models.ProfileNames;
import com.example.lab3pp.models.Recipe;
import com.example.lab3pp.models.User;
import com.example.lab3pp.repositories.CategoryRepository;
import com.example.lab3pp.repositories.RecipeRepository;
import com.example.lab3pp.repositories.UserRepository;
import com.example.lab3pp.validators.CustomValidator;
import com.example.lab3pp.validators.PasswordValidator;
import com.example.lab3pp.validators.RangeFormat;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Profile(ProfileNames.RestController)
public class RecipeRestController {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @GetMapping("/recipe-list")
    public List<Recipe> recipeList(String keyword, String filterX, String filterY, Boolean more, Boolean less){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

        if(filterX != null){
            int choice_int = Integer.parseInt(filterX);
            if(filterY != null) {
                if(Integer.parseInt(filterY) != 3) choice_int = 1;
            }
            switch(choice_int){
                case 0:
                    return recipeRepository.findByName2("%" + keyword + "%");
                case 1:
                    keyword = categoryRepository.getById(Long.parseLong(filterY) + 1L).getName();

                    return recipeRepository.findByCategory2("%" + keyword + "%");

                case 2:
                    String[] result = keyword.split(":");

                    return recipeRepository.findByCookingTime2(Integer.parseInt(result[0]),Integer.parseInt(result[1]));

                case 3:

                    return recipeRepository.findByDate2(LocalDate.parse(keyword, formatter));

                case 4:
                    String[] results = keyword.split(" - ");
                    if(more != null) {
                        if (more == true) {

                            return recipeRepository.findMoreThanCost(Double.parseDouble(keyword));
                        }
                    }
                    else if(less != null) {
                        if (less == true) {

                            return recipeRepository.findLessThanCost(Double.parseDouble(keyword));
                        }
                    }
                    else if(results.length > 1){

                        return recipeRepository.findBetweenCost(Double.parseDouble(results[0]), Double.parseDouble(results[1]));
                    }else

                        return recipeRepository.findByCost2(Double.parseDouble(keyword));

                case 5:

                    return recipeRepository.findByIngredients(keyword);

                default: break;
            }
        }
            return recipeRepository.findAll();
    }

    @GetMapping("/getList")
    public List<Recipe> getList(){
        return recipeRepository.findAll();
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView( "login");
    }

    @GetMapping("/shutdown")
    public String shutdown() throws SQLException {
        Connection conn = DriverManager.getConnection ("jdbc:h2:mem:recipedb", "przepisypl","admin");
        conn.createStatement().execute("SHUTDOWN");
        conn.close();
        return "errorConnection";
    }
    @GetMapping("/user-form")
    public User userFormGet(@RequestParam(value="id") Optional<User> user)throws Exception{

        return user.orElse(new User());
    }
    @PostMapping("/user-form")
    public User userFormPost(@Valid @ModelAttribute("user") User user, BindingResult result) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);

        return user;
    }


    @InitBinder({"recipe", "recipes"})
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new CustomValidator());
        binder.addCustomFormatter(new RangeFormat());
    }

    @InitBinder("user")
    public void initBinderUser(WebDataBinder binder){
        binder.addValidators(new PasswordValidator());
    }

}