package service;

import Util.DatabaseManager;
import dao.RecipeDAO;
import dao.StepDAO;
import model.Recipe;
import model.Step;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RecipeService {
    private Connection connection;
    private RecipeDAO recipeDAO;

    public RecipeService(){
        try{
            connection = DatabaseManager.getDatabaseManager().getConnection();
            recipeDAO = new RecipeDAO(connection);
        }catch (SQLException e){
           throw new RuntimeException(e);
        }
    }

    public boolean addRecipe(String name,int prepTime, int cookTime, float difficulty) {
        Recipe recipe = Recipe.builder()
                .name(name)
                .prepTime(prepTime)
                .cookTime(cookTime)
                .difficulty(difficulty)
                .build();
        try{
            if(recipeDAO.save(recipe)){
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean deleteRecipe(int id) {
        Recipe recipe = null;
        try{
            recipe = recipeDAO.findById(id);
            if(recipe != null){
                if(recipeDAO.delete(recipe)){
                    return true;
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean editRecipe(int id, String name,int prepTime, int cookTime, float difficulty) {
        Recipe recipe = Recipe.builder()
                .name(name)
                .prepTime(prepTime)
                .cookTime(cookTime)
                .difficulty(difficulty)
                .id(id)
                .build();
        try{
            if(recipeDAO.update(recipe)){
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Recipe> findAllRecipe(){
        try{
            return recipeDAO.findAll();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Recipe findRecipeById(int id) {
        try{
            return recipeDAO.findById(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
