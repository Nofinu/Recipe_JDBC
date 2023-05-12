package dao;

import jdk.jshell.spi.ExecutionControl;
import model.Recipe;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RecipeDAO extends BaseDAO<Recipe> {
    public RecipeDAO(Connection _connection) {
        super(_connection);
    }

    @Override
    public boolean save(Recipe element) throws SQLException {
        request = "INSERT INTO recipe(recipe_name,prep_time,cook_time,difficulty) VALUES (?,?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,element.getName());
        statement.setInt(2,element.getPrepTime());
        statement.setInt(3,element.getCookTime());
        statement.setFloat(4,element.getDifficulty());
        int rows = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()){
            element.setId(resultSet.getInt(1));
        }
        return rows ==1;
    }

    @Override
    public boolean delete(Recipe element) throws SQLException {
        request = "DELETE FROM recipe WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,element.getId());
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    @Override
    public boolean update(Recipe element) throws SQLException {
        request = "UPDATE recipe SET recipe_name = ?,prep_time = ?,cook_time = ?,difficulty = ? WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setString(1,element.getName());
        statement.setInt(2,element.getPrepTime());
        statement.setInt(3,element.getCookTime());
        statement.setFloat(4,element.getDifficulty());
        int rows = statement.executeUpdate();
        return rows ==1;
    }

    @Override
    public Recipe findById(int id) throws SQLException {
        request = "SELECT recipe_name,prep_time,cook_time,difficulty FROM recipe WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,id);
        resultSet = statement.executeQuery();
        if (resultSet.next()){
            return Recipe.builder()
                    .id(id)
                    .name(resultSet.getString("recipe_name"))
                    .prepTime(resultSet.getInt("prep_time"))
                    .cookTime(resultSet.getInt("cook_time"))
                    .difficulty(resultSet.getFloat("difficulty"))
                    .build();
        }
        return null;
    }

    @Override
    public List<Recipe> findAll() throws SQLException {
        List<Recipe> recipes = null;
        request = "SELECT id,recipe_name,prep_time,cook_time,difficulty FROM recipe";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while (resultSet.next()){
            recipes.add( Recipe.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("recipe_name"))
                    .prepTime(resultSet.getInt("prep_time"))
                    .cookTime(resultSet.getInt("cook_time"))
                    .difficulty(resultSet.getFloat("difficulty"))
                    .build());
        }
        return recipes;
    }

    @Override
    public List<Recipe> findAllByRecipeId(int recipeId) throws ExecutionControl.NotImplementedException{
        throw new ExecutionControl.NotImplementedException("recipe");
    }
}
