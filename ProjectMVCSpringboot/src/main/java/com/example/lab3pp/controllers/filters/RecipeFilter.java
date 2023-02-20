package com.example.lab3pp.controllers.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeFilter {

    private String filterX; //szukaj po:
    private String keyword;  //fraza
    private String filterY; //kategoria:

}
