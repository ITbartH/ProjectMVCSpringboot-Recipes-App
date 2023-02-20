package com.example.lab3pp.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    public Category(String name) {

        this.name = name;
    }

}
