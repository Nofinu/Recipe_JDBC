package org.example.Util;

import org.example.model.Ingredient;

import java.text.MessageFormat;
import java.util.List;

public class IngredientTable {
    public static void table (List<Ingredient> ingredients, boolean withId){
        int modulo = ingredients.size()%4;

        for(int i =0; i<ingredients.size(); i+=4) {
            if (i + 3 < ingredients.size()) {
                System.out.println(MessageFormat.format("{0}  |  {1}   |  {2}   |  {3}  |", withId ? ingredients.get(i) : ingredients.get(i).toStringWithoutId(), withId ? ingredients.get(i +1) : ingredients.get(i + 1).toStringWithoutId(), withId ? ingredients.get(i + 2) : ingredients.get(i + 2).toStringWithoutId(), withId ? ingredients.get(i + 3) : ingredients.get(i + 3).toStringWithoutId()));
            } else {
                switch (modulo){
                    case 3:
                        System.out.println(MessageFormat.format("{0}  |  {1}   |  {2}  |", withId ? ingredients.get(i) : ingredients.get(i).toStringWithoutId(), withId ? ingredients.get(i + 1) : ingredients.get(i + 1).toStringWithoutId(), withId ? ingredients.get(i + 2) : ingredients.get(i + 2).toStringWithoutId()));
                        break;
                    case 2:
                        System.out.println(MessageFormat.format("{0}  |  {1}  |", withId ? ingredients.get(i) : ingredients.get(i).toStringWithoutId(), withId ? ingredients.get(i + 1) : ingredients.get(i + 1).toStringWithoutId()));
                        break;
                    case 1:
                        System.out.println(MessageFormat.format("{0}  |", withId ? ingredients.get(i) : ingredients.get(i).toStringWithoutId()));
                        break;
                }
            }
        }
    }
}
