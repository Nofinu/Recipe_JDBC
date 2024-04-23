package org.example.dao;

import org.example.Util.DatabaseManager;
import jdk.jshell.spi.ExecutionControl;
import org.example.model.Ingredient;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IngredienRecipesDAO extends BaseDAO<Ingredient> {

    public IngredienRecipesDAO() {
    }

    @Override
    public boolean save(Ingredient element) throws SQLException {
        try{
            _connection = DatabaseManager.getConnection();
            request = "INSERT INTO ingredient_recipe(id_ingredient,id_recipe,quantity) VALUE (?,?,?)";
            statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,element.getId());
            statement.setInt(2, element.getIdRecipe());
            statement.setString(3, element.getQuantity());
            int rows = statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                element.setIdIngredientRecipe(resultSet.getInt(1));
            }
            return rows == 1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally {
            close();
        }
    }

    @Override
    public boolean delete(Ingredient element) throws SQLException {
        try{
            _connection = DatabaseManager.getConnection();
            request = "DELETE FROM ingredient_recipe WHERE id_ingredient = ? AND id_recipe = ?";
            statement = _connection.prepareStatement(request);
            statement.setInt(1,element.getId());
            statement.setInt(2,element.getIdRecipe());
            int rows = statement.executeUpdate();
            return rows == 1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally {
            close();
        }
    }

    @Override
    public boolean update(Ingredient element) throws ExecutionControl.NotImplementedException{
        throw new ExecutionControl.NotImplementedException("ingredient_recipe");
    }

    @Override
    public Ingredient findById(int id) throws SQLException{
        try{
            _connection = DatabaseManager.getConnection();
            Ingredient ingredient = null;
            request = "SELECT id FROM ingredient_recipe WHERE id = ?";
            statement = _connection.prepareStatement(request);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                ingredient = Ingredient.builder().idIngredientRecipe(resultSet.getInt("id")).build();
            }
            return ingredient;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }finally {
            close();
        }
    }

    public Ingredient findByIdIngredientIdRecipe (int idIngredient, int idRecipe) throws SQLException{
        try {
            _connection = DatabaseManager.getConnection();
            request = "SELECT id FROM ingredient_recipe WHERE id_ingredient = ? AND id_recipe = ?";
            statement = _connection.prepareStatement(request);
            statement.setInt(1, idIngredient);
            statement.setInt(2, idRecipe);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Ingredient.builder().id(idIngredient).idRecipe(idRecipe).idIngredientRecipe(resultSet.getInt("id")).build();
            }
            return null;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }finally {
            close();
        }
    }

    @Override
    public List<Ingredient> findAll() throws ExecutionControl.NotImplementedException{
        throw new ExecutionControl.NotImplementedException("ingredient_recipe");
    }

    public List<Ingredient> findAllByRecipeId(int recipeId) throws SQLException{
        try{
            _connection = DatabaseManager.getConnection();
            List<Ingredient> ingredients = new ArrayList<>();
            request = "SELECT i.id AS ingredientId, i.name AS ingredientName, ir.quantity AS quantity, ir.id_recipe AS id_recipe, ir.id AS id_ingredient_recipe " +
                    "FROM ingredient AS i " +
                    "INNER JOIN ingredient_recipe AS ir ON ir.id_ingredient = i.id " +
                    "WHERE ir.id_recipe = ?";
            statement = _connection.prepareStatement(request);
            statement.setInt(1,recipeId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                ingredients.add(Ingredient.builder()
                        .name(resultSet.getString("ingredientName"))
                        .id(resultSet.getInt("ingredientId"))
                        .quantity(resultSet.getString("quantity"))
                        .idRecipe(resultSet.getInt("id_recipe"))
                        .idIngredientRecipe(resultSet.getInt("id_ingredient_recipe"))
                        .build());
            }
            return ingredients;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }finally {
            close();
        }
    }

}
