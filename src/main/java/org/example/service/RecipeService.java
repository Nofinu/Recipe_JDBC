package org.example.service;

import org.example.Exception.NotFoundException;
import org.example.dao.RecipeDAO;
import org.example.model.Recipe;

import java.sql.SQLException;
import java.util.List;

public class RecipeService implements IService<Recipe> {
    private final RecipeDAO recipeDAO;

    public RecipeService() {
        recipeDAO = new RecipeDAO();
    }

    public boolean addRecipe(String name, int prepTime, int cookTime, int difficulty) {
        Recipe recipe = Recipe.builder()
                .name(name)
                .prepTime(prepTime)
                .cookTime(cookTime)
                .difficulty(difficulty)
                .build();
        try {
            if (recipeDAO.save(recipe)) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean deleteRecipe(int id) {
        Recipe recipe = null;
        try {
            recipe = recipeDAO.findById(id);
            if (recipe != null) {
                if (recipeDAO.delete(recipe)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean editRecipe(int id, String name, int prepTime, int cookTime, int difficulty) {
        Recipe recipe = Recipe.builder()
                .name(name)
                .prepTime(prepTime)
                .cookTime(cookTime)
                .difficulty(difficulty)
                .id(id)
                .build();
        try {
            if (recipeDAO.update(recipe)) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Recipe> findAllRecipe() {
        try {
            return recipeDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Recipe findById(int id) throws NotFoundException {
        try {
            Recipe recipeFound = recipeDAO.findById(id);
            if(recipeFound != null){
                return recipeFound;
            }
            throw new NotFoundException();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
