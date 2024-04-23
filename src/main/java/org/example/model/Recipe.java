package org.example.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.Util.IngredientTable;

import java.text.MessageFormat;
import java.util.List;
@Builder
@Data
public class Recipe {
    private int id;
    private String name;
    private int prepTime;
    private int cookTime;
    private int difficulty;
    private List<Ingredient> ingredients;
    private List<Step> steps;
    private List<Comment> comments;

    public void showRecipe (boolean withIngredientId,boolean withStepId){
        System.out.println("Recipe : "+name + "     Id : "+ id);
        System.out.println("Preparation Time : "+prepTime);
        System.out.println("Cooking time : "+cookTime);
        switch (difficulty){
            case 1:
                System.out.println("Difficulty : "+Difficulty.Easy);
                break;
            case 2:
                System.out.println("Difficulty : "+Difficulty.Medium);
                break;
            case 3:
                System.out.println("Difficulty : "+Difficulty.Hard);
                break;
        }
        if(ingredients != null && !ingredients.isEmpty()){
            IngredientTable.table(ingredients,withIngredientId);
        }

        if(steps != null && !steps.isEmpty()){
            int cpt = 1;
            for(Step step : steps){
                System.out.println(MessageFormat.format("{0} / {1} {2}",cpt++,step.getTextStep(),withStepId? " Id : "+step.getId() : ""));
            }
        }

    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prepTime=" + prepTime +
                ", cookTime=" + cookTime +
                ", Difficulty=" + difficulty +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                ", comments=" + comments +
                '}';
    }

    public enum Difficulty {
        Easy,
        Medium,
        Hard
    }
}

