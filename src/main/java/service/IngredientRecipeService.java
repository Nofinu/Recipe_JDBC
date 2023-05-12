package service;

import Util.DatabaseManager;
import dao.IngredienRecipesDAO;
import model.Ingredient;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class IngredientRecipeService {

    private Connection connection;
    private IngredienRecipesDAO ingredienRecipesDAO;

    public IngredientRecipeService(){
        try{
            connection = DatabaseManager.getDatabaseManager().getConnection();
            ingredienRecipesDAO = new IngredienRecipesDAO(connection);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean addIngredientRecipe(int idIngredient, int idRecipe,String quantity) {
        Ingredient ingredient = Ingredient.builder().idRecipe(idRecipe).id(idIngredient).quantity(quantity).build();
        try{
            if(ingredienRecipesDAO.save(ingredient)){
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public Boolean deleteIngredientRecipe (int id){
        Ingredient ingredient = null;
        try{
            ingredient = ingredienRecipesDAO.findById(id);
            if(ingredient != null){
                if(ingredienRecipesDAO.delete(ingredient)){
                    return  true;
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Ingredient> findAllIngredientRecipe (int idRecipe){
        try{
            return ingredienRecipesDAO.findAllByRecipeId(idRecipe);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

}
