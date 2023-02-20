package com.example.lab3pp.validators;

import com.example.lab3pp.models.CookingTime;
import org.springframework.format.Formatter;

import javax.persistence.Embeddable;
import java.text.ParseException;
import java.util.Locale;

public class RangeFormat implements Formatter<CookingTime> {
    @Override//zamiana obiektu na tekst
    public String print(CookingTime range, Locale locale) {
        if (range == null) {
            return "";
        }
        return String.format("%01d:%01d", range.getH(), range.getMin());
    }
    @Override//zamiana tekstu na obiekt
    public CookingTime parse(String formatted, Locale locale) throws ParseException {
        if (formatted.length() == 0) {
            return null;
        }
        var tokens = formatted.split(":");
        if(tokens.length>1){
            return new CookingTime(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        }
        return null;
    }
}

