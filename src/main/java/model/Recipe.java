package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Getter
@Setter
public class Recipe {
    private int id;
    private String name;
    private int prepTime;
    private int cookTime;
    private float difficulty;
    private List<Ingredient> ingredients;
    private List<Step> steps;
    private List<Comment> comments;

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
}
