package model;

import lombok.Builder;

@Builder
public class Ingredient {
    private int id;
    private int idRecipe;
    private int idIngredientRecipe;
    private String name;
    private String quantity;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    public int getIdIngredientRecipe() {
        return idIngredientRecipe;
    }

    public void setIdIngredientRecipe(int idIngredientRecipe) {
        this.idIngredientRecipe = idIngredientRecipe;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
