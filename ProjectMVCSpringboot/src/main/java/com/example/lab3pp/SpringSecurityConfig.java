package com.example.lab3pp;

import com.example.lab3pp.models.ProfileNames;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Profile(ProfileNames.USERS_IN_MEMORY)
    public UserDetailsService userDetailsService(
            PasswordEncoder passwordEncoder) {


        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        User.UserBuilder userBuilder = User.builder();

        UserDetails user = userBuilder
                .username("user1")
                .password(passwordEncoder.encode("user1"))
                .roles("USER")
                .build();
        manager.createUser(user);

        user = userBuilder
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();
        manager.createUser(user);

        user = userBuilder
                .username("superadmin")
                .password(passwordEncoder.encode("superadmin"))
                .roles("ADMIN","USER")
                .build();
        manager.createUser(user);

        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()//uwierzytelnienie poprzez formularz logowania
                .loginPage("/login")//formularz dostępny jest pod URL
                .permitAll();//przyznaj dostęp wszystkim użytkownikom

        http
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers( "/", "/login" ,"/recipe-list","/user-form").permitAll()
                .antMatchers("/recipe-list/recipe-delete/**", "/recipe-list/recipe-form", "/recipe-list/recipe-details/**", "/userDetails/**", "/userList").denyAll()
                .antMatchers("/logout", "/recipe-list/recipe-details/**", "/recipeBook/**", "/recipeBook-delete/**", "/userDetails/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/recipe-list/recipe-delete/**", "/recipe-list/recipe-form", "/userList").hasRole("ADMIN")

                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .anyRequest().authenticated();

        http.exceptionHandling()
                .accessDeniedPage("/error403");






    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }


}


