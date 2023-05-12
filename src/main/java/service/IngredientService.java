package service;

import Util.DatabaseManager;
import dao.IngredientDAO;
import jdk.jshell.spi.ExecutionControl;
import model.Ingredient;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class IngredientService {
    private Connection connection;
    private IngredientDAO ingredientDAO;

    public IngredientService(){
        try{
            connection = DatabaseManager.getDatabaseManager().getConnection();
            ingredientDAO = new IngredientDAO(connection);
        }catch (SQLException e){
           throw new RuntimeException(e);
        }
    }

    public boolean addIngredient(String name) {
        Ingredient ingredient = Ingredient.builder().name(name).build();
        try{
            if(ingredientDAO.save(ingredient)){
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean deleteIngredient(int id) {
        Ingredient ingredient = null;
        try{
            ingredient = ingredientDAO.findById(id);
            if(ingredient != null){
                if(ingredientDAO.delete(ingredient)){
                    return true;
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean editIngredient(int id, String name) {
        Ingredient ingredient = Ingredient.builder().id(id).name(name).build();
        try{
            if(ingredientDAO.update(ingredient)){
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Ingredient> findAllIngredient(){
        try{
            return ingredientDAO.findAll();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Ingredient findIngredientById(int id) {
        try{
            return ingredientDAO.findById(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
