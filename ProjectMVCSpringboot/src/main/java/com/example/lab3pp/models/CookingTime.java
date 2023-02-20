package com.example.lab3pp.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Embeddable;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CookingTime {

    @NotNull
    private int h;

    @NotNull
    private int min;

    public String toString(){
        return String.format("%02d:%02d", this.getH(), this.getMin());
    }
}
