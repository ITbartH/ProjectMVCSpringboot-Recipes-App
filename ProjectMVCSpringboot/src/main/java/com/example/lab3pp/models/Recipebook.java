package com.example.lab3pp.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "recipebooks")
@Getter
@Setter

public class Recipebook {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "book_name", nullable = false)
    private String bookName;

    @NotBlank
    @Column(name = "book_description", nullable = false)
    private String bookDescription;
    @Column(name="book_username", nullable = false)
    private String bookUserName;
    @Column(name = "book_recipes", nullable = false)
    @ManyToMany
    private List<Recipe> recipes;

}
