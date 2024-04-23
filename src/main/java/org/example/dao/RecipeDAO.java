package org.example.dao;

import org.example.Util.DatabaseManager;
import jdk.jshell.spi.ExecutionControl;
import org.example.model.Recipe;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO extends BaseDAO<Recipe> {
    private IngredienRecipesDAO ingredienRecipesDAO;
    private  StepDAO stepDAO;

    public RecipeDAO() {
        ingredienRecipesDAO = new IngredienRecipesDAO();
        stepDAO = new StepDAO();
    }

    @Override
    public boolean save(Recipe element) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            request = "INSERT INTO recipe(recipe_name,prep_time,cook_time,difficulty) VALUES (?,?,?,?)";
            statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, element.getName());
            statement.setInt(2, element.getPrepTime());
            statement.setInt(3, element.getCookTime());
            statement.setFloat(4, element.getDifficulty());
            int rows = statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                element.setId(resultSet.getInt(1));
            }
            return rows == 1;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally {
            close();
        }
    }

    @Override
    public boolean delete(Recipe element) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            request = "DELETE FROM recipe WHERE id = ?";
            statement = _connection.prepareStatement(request);
            statement.setInt(1, element.getId());
            int rows = statement.executeUpdate();
            return rows == 1;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally {
            close();
        }
    }

    @Override
    public boolean update(Recipe element) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            request = "UPDATE recipe SET recipe_name = ?,prep_time = ?,cook_time = ?,difficulty = ? WHERE id = ?";
            statement = _connection.prepareStatement(request);
            statement.setString(1, element.getName());
            statement.setInt(2, element.getPrepTime());
            statement.setInt(3, element.getCookTime());
            statement.setInt(4, element.getDifficulty());
            statement.setInt(5,element.getId());
            int rows = statement.executeUpdate();
            return rows == 1;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally {
            close();
        }
    }

    @Override
    public Recipe findById(int id) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            request = "SELECT recipe_name,prep_time,cook_time,difficulty FROM recipe WHERE id = ?";
            statement = _connection.prepareStatement(request);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Recipe.builder()
                        .id(id)
                        .name(resultSet.getString("recipe_name"))
                        .prepTime(resultSet.getInt("prep_time"))
                        .cookTime(resultSet.getInt("cook_time"))
                        .difficulty(resultSet.getInt("difficulty"))
                        .ingredients(ingredienRecipesDAO.findAllByRecipeId(id))
                        .steps(stepDAO.findAllByRecipeId(id))
                        .build();
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
    public List<Recipe> findAll() throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            List<Recipe> recipes = new ArrayList<>();
            request = "SELECT id,recipe_name,prep_time,cook_time,difficulty FROM recipe";
            statement = _connection.prepareStatement(request);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                recipes.add(Recipe.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("recipe_name"))
                        .prepTime(resultSet.getInt("prep_time"))
                        .cookTime(resultSet.getInt("cook_time"))
                        .difficulty(resultSet.getInt("difficulty"))
                        .ingredients(ingredienRecipesDAO.findAllByRecipeId(resultSet.getInt("id")))
                        .steps(stepDAO.findAllByRecipeId(resultSet.getInt("id")))
                        .build());
            }
            return recipes;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }finally {
            close();
        }

    }

}
