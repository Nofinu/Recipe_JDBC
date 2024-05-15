package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.text.MessageFormat;

@Builder
@Data
public class Ingredient extends BaseShowMethod {
    private int id;
    private int idRecipe;
    private int idIngredientRecipe;
    private String name;
    private String quantity;

    public String toStringWithoutId (){
        return MessageFormat.format("{0}, {1}",
                name,quantity!= null? "Quantity : "+quantity : "");
    }


    @Override
    public String toString() {
        return MessageFormat.format("{0} Id : {1} {2}",
                name, id ,quantity!= null? "Quantity : "+quantity : "");
    }
}
