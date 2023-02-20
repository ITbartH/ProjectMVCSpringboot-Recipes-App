package com.example.lab3pp;

import com.example.lab3pp.models.*;
import com.example.lab3pp.repositories.*;
import com.example.lab3pp.services.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.*;

@Configuration
@AllArgsConstructor
public class InitRepositories {

    private final RecipeService recipeService;
    private final CategoryService categoryService;
    private final IngredientService ingredientService;
    private final UserServiceImpl userService;
    private final RoleService roleService;
    private final CommentService commentService;

    List<Category> categoryList = new ArrayList<Category>();
    List<Recipe> recipeList = new ArrayList<Recipe>();
    List<Ingredient> ingredientList = new ArrayList<Ingredient>();
    List<User> userList = new ArrayList<User>();
    List<Role> roleList = new ArrayList<Role>();
    List<Comment> commentList = new ArrayList<Comment>();

    private final PasswordEncoder passwordEncoder;

    @Bean
    InitializingBean init() {

        return () -> {
            if (categoryService.findAll().isEmpty()) {

                categoryList.add(new Category("Desery"));
                categoryList.add(new Category("Makarony"));
                categoryList.add(new Category("Zupy"));
                categoryService.saveAll(categoryList);
            }
            if (ingredientService.findAll().isEmpty()) {
                ingredientList.add(new Ingredient("Cebula"));
                ingredientList.add(new Ingredient("Szpinak"));
                ingredientList.add(new Ingredient("Marchewka"));
                ingredientList.add(new Ingredient("Ziemniak"));
                ingredientList.add(new Ingredient("Pomidor"));
                ingredientList.add(new Ingredient("Brokuł"));

                ingredientList.add(new Ingredient("Wywar"));
                ingredientList.add(new Ingredient("Masa krówkowa"));
                ingredientList.add(new Ingredient("Powidła"));
                ingredientList.add(new Ingredient("Przecier pomidorowy"));
                ingredientList.add(new Ingredient("Śmietana"));
                ingredientList.add(new Ingredient("Dżem"));
                ingredientList.add(new Ingredient("Mąka"));

                ingredientList.add(new Ingredient("Makaron"));
                ingredientList.add(new Ingredient("Mięso"));
                ingredientList.add(new Ingredient("Ser"));
                ingredientList.add(new Ingredient("Jajka"));
                ingredientList.add(new Ingredient("Kurczak"));
                ingredientList.add(new Ingredient("Mleko"));

                ingredientService.saveAll(ingredientList);
            }
            if (recipeService.findAll().isEmpty()) {
                recipeList.add(new Recipe("Makaron ze szpinakiem",
                        "Makaron ugotować al dente w osolonej wodzie. Szpinak opłukać i odcedzić. \n" +
                                "\n" +
                                "Na patelni na oliwie i maśle zeszklić pokrojoną w kosteczkę cebulę (ok. 5 - 7 minut). Doprawić solą, dodać przeciśnięty przez praskę czosnek i smażyć jeszcze przez ok. 2 minuty. \n" +
                                "\n" +
                                "Dodać szpinak i mieszając podgrzewać przez ok. 1 minutę aż zwiędnie. \n" +
                                "\n" +
                                "Wlać śmietankę, doprawić świeżo zmielonym pieprzem i solą, całość zagotować. Dodać odcedzony makaron i wymieszać. \n" +
                                "\n" +
                                "Połączyć z 1/3 ilości sera, resztę wykorzystać do posypania makaronu. ",
                        LocalDate.now(), false, 28.50, categoryList.get(1), new CookingTime(1, 15), "easy"));
                recipeList.add(new Recipe("Spaghetti",
                        "Cebulę i czosnek obrać i poszatkować. \n" +
                                "\n" +
                                "Marchewkę umyć, obrać i pokroić w drobną kostkę. \n" +
                                "\n" +
                                "Cebulę i czosnek chwilkę podsmażyć na oleju. Dodać mięso mielone i marchewkę. Wszystko razem chwilę podsmażyć, aż mięso straci kolor różowy. Dodać pomidory z puszki (razem z sosem z puszki), przecier pomidorowy i bulion. Całość dusić pod przykryciem na niskiej mocy palnika ok. 20 minut. Przyprawić solą, pieprzem i oregano. \n" +
                                "\n" +
                                "Spaghetti ugotować w osolonej wodzie według przepisu na opakowaniu. Odcedzić na durszlaku. (Nie przelewać zimną wodą). \n" +
                                "\n" +
                                "Spaghetti rozłożyć na talerzach. Polać sosem z mięsem i posypać parmezanem. ",
                        LocalDate.now(), true, 24.00, categoryList.get(1), new CookingTime(1, 15), "easy"));
                recipeList.add(new Recipe("Makaron z serem",
                        "Wstawiam garnek z wodą na gaz. \n" +
                                "\n" +
                                "Cebule obieram i kroję w drobną kostkę. Rozgrzewam na głębokiej patelni masło i delikatnie przysmażam cebule. \n" +
                                "\n" +
                                "Ser ścieram na tarce o grubych oczkach. \n" +
                                "\n" +
                                "Do garnka z gotującą się wodą wrzucam 2 łyżeczki soli oraz makaron. Gotuję zgodnie z instrukcją na opakowaniu. \n" +
                                "\n" +
                                "Do cebuli dodaję śmietanę, pieprz biały i ser. Na małym gazie zagotowuję wszystko, tak żeby ser się rozpuścił. W razie potrzeby doprawiam jeszcze do smaku. \n" +
                                "\n" +
                                "Z garnka z makaronem odlewam 1 szklankę wody, makaron przecedzam przez sito i wrzucam na patelnię. Mieszam dokładnie. Jeśli sos jest zbyt gęsty dolewam wodę z \tgotowania. Mieszam ponownie. \n" +
                                "\n" +
                                "Od razu rozkładam na talerze. ",
                        LocalDate.now(), false, 30.00, categoryList.get(1), new CookingTime(1, 15), "medium"));
                recipeList.add(new Recipe("Naleśniki z dżemem",
                        "Mleko wlej do miski. Dodaj wodę gazowaną, jajka, cukier wanilinowy, sól i wszystko zmiksuj. Na koniec dosyp przesianą mąkę. \n" +
                                "\n" +
                                "Gdy ciasto będzie gładkie i bez grudek, dodaj olej i jeszcze chwilę miksuj. \n" +
                                "\n" +
                                "Rozgrzej patelnię delikatnie przetartą olejem i smaż cienkie naleśniki z obu stron na złoty kolor. \n" +
                                "\n" +
                                "Gotowe naleśniki przekładaj na talerz. Jeśli nie zamierzasz ich od razu podawać przykryj drugim talerzem lub przykrywką, aby nie wyschły. \n" +
                                "\n" +
                                "Naleśniki smaruj ulubionym dżemem i zawijaj w rulony, ćwiartki, bądź koperty. ",
                        LocalDate.now(), false, 23.00, categoryList.get(0), new CookingTime(1, 0), "easy"));
                recipeList.add(new Recipe("Zupa pomidorowa",
                        "Do garnka wkładamy udka i wlewamy wodę (tak, aby je zakryła). Dodajemy ziele angielskie i liść laurowy oraz trzy obrane i umyte marchewki. \n" +
                                "\n" +
                                "Gotujemy na małym ogniu przez około godzinę. \n" +
                                "\n" +
                                "Kiedy marchewka zmięknie, wyjmuje ją, kroimy w talarki i ponownie dodaje do zupy. \n" +
                                "\n" +
                                "Dodajemy przecier pomidorowy i bulion drobiowy WINIARY do smaku. \n" +
                                "\n" +
                                "Gotujemy jeszcze przez chwilę na małym ogniu i dodajemy śmietanę. \n" +
                                "\n" +
                                "Zupę podajemy razem z ugotowanym makaronem. ",
                        LocalDate.now(), true, 26.40, categoryList.get(2), new CookingTime(1, 45), "medium"));
                recipeList.add(new Recipe("Wafle z kajmakiem",
                        "Wafle smarujemy zawsze od strony, gdzie kratka jest mniejsza. Jest wydajniejszy sposób, a wafle tak szybko nie zmiękną. \n" +
                                "\n" +
                                "Pierwszą warstwę posmarować dżemem z czarnej porzeczki, przyłożyć drugim waflem, docisnąć, posmarować go kajmakiem, przykryć kolejnym waflem – docisnąć. Następny posmarować powidłami śliwkowymi i znów przykryć waflem, posmarować go kajmakiem i przykryć ostatnim waflem. \n" +
                                "\n" +
                                "Przełożone wafle przekładamy ponownie do papieru, zawijamy i dociskamy np. książkami lub ciężką deską do krojenia. Można przenieść je w chłodne miejsce. \n" +
                                "\n" +
                                "Po godzinie są gotowe do jedzenia. ",
                        LocalDate.now(), false, 16.80, categoryList.get(0), new CookingTime(0, 45), "easy"));
            }
            if (roleService.findAll().isEmpty()) {
                roleList.add(new Role(Role.Types.ROLE_USER));
                roleList.add(new Role(Role.Types.ROLE_ADMIN));

                roleService.saveAll(roleList);
            }
            if (userService.findAll().isEmpty()) {
                Random rand = new Random();

                User a = new User("admin", true);
                a.setRoles(new HashSet<>(Arrays.asList(roleList.get(1))));
                a.setPassword(passwordEncoder.encode("admin"));
                a.setFirstname("Bartosz");
                a.setLastname("Hrycaj");
                a.setEmail("bh83464@stud.uph.edu.pl");
                userList.add(a);

                User ua = new User("superadmin", true);
                ua.setRoles(new HashSet<>(Arrays.asList(roleList.get(0), roleList.get(1))));
                ua.setPassword(passwordEncoder.encode("superadmin"));
                userList.add(ua);

                User u = new User("user", true);
                u.setRoles(new HashSet<>(Arrays.asList(roleList.get(0))));
                u.setPassword(passwordEncoder.encode("user"));
                userList.add(u);

                String[] listNicknames = {"Lovey", "ThunderRajhs32", "BabyLoco42", "Pop Tart", "DillyDally33",
                        "Fiesta", "Munchkin", "Poraro", "Bambino", "Rufio16",
                        "Cheddar", "Bumpkin", "Birdyx54", "MiniMaxMex32", "Dork",
                        "Boo Bear", "Twix", "Pinkie", "Cricket", "Bossy",
                        "Chefior4", "Rosield32", "Doll", "Bumblebee", "CheetLol32",
                        "Coxach5", "Joker", "Hawk", "BabyBird18", "Smirk"};

                String[] listNames =  {"Leland Boyer dwendlan@att.net", "Harry Mckinney quantaman@outlook.com", "Raina Cooley qrczak@yahoo.com", "Yaritza Wood murty@msn.com", "Abbie Jones thassine@me.com",
                        "Skye Ellis draper@hotmail.com", "Ryleigh Huff skythe@hotmail.com", "Atticus Herrera jmorris@sbcglobal.net", "Dennis Kaufman aprakash@optonline.net", "Yadiel Woods dcoppit@verizon.net",
                        "Ashtyn Morgan petersen@live.com", "Remington Vance aukjan@me.com", "Micah Schmitt jfmulder@optonline.net", "Evie Barron zeller@sbcglobal.net", "Tori Callahan jshirley@mac.com",
                        "Nikhil Fitzpatrick joehall@outlook.com", "Kassidy Sweeney jamuir@me.com", "Hanna Stein shrapnull@outlook.com", "Amare Ford research@me.com", "Jaylee Hanson mcraigw@msn.com",
                        "Gwendolyn Stout blixem@verizon.net", "Brayan Callahan gbacon@yahoo.com", "Devon Andrade schwaang@hotmail.com", "Cristal Guzman crimsane@hotmail.com", "Kaylynn Cochran shawnce@outlook.com",
                        "Ben Berger sagal@comcast.net", "Malakai Guerra marnanel@yahoo.com", "Zain Luna dvdotnet@me.com", "Broderick Barber damian@outlook.com", "Maritza Kelly catalog@live.com"};
                for (int i = 0; i < 30; i++) {
                    u = new User(listNicknames[i], true);
                    u.setRoles(new HashSet<>(Arrays.asList(roleList.get(0))));
                    u.setPassword(passwordEncoder.encode(listNicknames[i]));
                    int los = rand.nextInt(2);
                    if (los == 1) {
                        u.setFirstname(listNames[i].split(" ")[0]);
                        u.setLastname(listNames[i].split(" ")[1]);
                        u.setEmail(listNames[i].split(" ")[2]);
                    }
                    userList.add(u);
                }


                userService.saveAll(userList);
            }
            if(commentService.findAll().isEmpty()){
                commentList.add(new Comment("Bardzo dobry przepis. Polecam spróbować każdemu!", LocalDate.now(), 1, recipeList.get(0).getName(), userList.get(5).getUsername()));
                commentList.add(new Comment("Ale pyszności!!! Szybki, smaczny i tani przepis, polecam!", LocalDate.now(), 1, recipeList.get(0).getName(), userList.get(8).getUsername()));
                commentList.add(new Comment("Na pewno użyje tego przepisu nie raz i nie dwa! :)", LocalDate.now(), 1, recipeList.get(0).getName(), userList.get(12).getUsername()));
                recipeList.get(0).setCommentsCount(3);

                commentList.add(new Comment("Bardzo dobry przepis. Polecam spróbować każdemu! Dodaje do listy ulubionych przepisów :D", LocalDate.now(), 2, recipeList.get(1).getName(), userList.get(24).getUsername()));
                recipeList.get(1).setCommentsCount(1);

                commentList.add(new Comment("Bardzo dobry przepis. Polecam spróbować każdemu!", LocalDate.now(), 3, recipeList.get(2).getName(), userList.get(7).getUsername()));
                commentList.add(new Comment("Ale pyszności!!! Szybki, smaczny i tani przepis, polecam!", LocalDate.now(), 3, recipeList.get(2).getName(), userList.get(6).getUsername()));
                commentList.add(new Comment("Na pewno użyje tego przepisu nie raz i nie dwa! :)", LocalDate.now(), 3, recipeList.get(2).getName(), userList.get(9).getUsername()));
                recipeList.get(2).setCommentsCount(3);

                commentList.add(new Comment("Bardzo dobry przepis. Polecam spróbować każdemu!", LocalDate.now(), 4, recipeList.get(3).getName(), userList.get(16).getUsername()));
                commentList.add(new Comment("Ale pyszności!!! Szybki, smaczny i tani przepis, polecam!", LocalDate.now(), 4, recipeList.get(3).getName(), userList.get(26).getUsername()));
                recipeList.get(3).setCommentsCount(2);

                commentList.add(new Comment("Bardzo dobry przepis. Polecam spróbować każdemu! Dodaje do mojej książki <3", LocalDate.now(), 5, recipeList.get(4).getName(), userList.get(17).getUsername()));
                commentList.add(new Comment("Ale pyszności!!! Szybki, smaczny i tani przepis, polecam!", LocalDate.now(),5, recipeList.get(4).getName(), userList.get(22).getUsername()));
                recipeList.get(4).setCommentsCount(2);


                commentList.add(new Comment("Bardzo dobry przepis. Polecam spróbować każdemu!", LocalDate.now(), 6, recipeList.get(5).getName(), userList.get(4).getUsername()));
                commentList.add(new Comment("Ale pyszności!!! Szybki, smaczny i tani przepis, polecam!", LocalDate.now(), 6, recipeList.get(5).getName(), userList.get(28).getUsername()));
                recipeList.get(5).setCommentsCount(2);

                commentService.saveAll(commentList);
                recipeService.saveAll(recipeList);



            }
        };

    }
}


