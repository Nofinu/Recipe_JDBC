package org.example.service;

import org.example.dao.IngredienRecipesDAO;
import org.example.dao.IngredientDAO;
import org.example.model.Ingredient;

import java.sql.SQLException;
import java.util.List;

public class IngredientRecipeService {

    private final IngredienRecipesDAO ingredienRecipesDAO;

    public IngredientRecipeService() {
        ingredienRecipesDAO = new IngredienRecipesDAO();
    }

    public boolean addIngredientRecipe(int idIngredient, int idRecipe, String quantity) {
        Ingredient ingredient = Ingredient.builder().idRecipe(idRecipe).id(idIngredient).quantity(quantity).build();
        try {
            if (ingredienRecipesDAO.save(ingredient)) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Boolean deleteIngredientRecipe(int idIngredient, int idRecipe) {
        Ingredient ingredient = null;
        try {
            ingredient = ingredienRecipesDAO.findByIdIngredientIdRecipe(idIngredient,idRecipe);
            if (ingredient != null) {
                if (ingredienRecipesDAO.delete(ingredient)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Ingredient> findAllIngredientRecipe(int idRecipe) {
        try {
            return ingredienRecipesDAO.findAllByRecipeId(idRecipe);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
