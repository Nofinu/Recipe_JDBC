package dao;


import jdk.jshell.spi.ExecutionControl;
import model.Ingredient;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class IngredientDAO extends BaseDAO<Ingredient> {

    public IngredientDAO(Connection _connection) {
        super(_connection);
    }

    @Override
    public boolean save(Ingredient element) throws SQLException {
        request = "INSERT INTO ingredient(name) VALUES (?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, element.getName());
        int rows = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            element.setId(resultSet.getInt(1));
        }
        return rows == 1;
    }

    @Override
    public boolean delete(Ingredient element) throws SQLException {
        request = "DELETE FROM ingredient WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, element.getId());
        int rows = statement.executeUpdate();
        return rows == 1;

    }

    @Override
    public boolean update(Ingredient element) throws SQLException {
        request = "UPDATE ingredient SET name = ? WHERE id =?";
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getName());
        statement.setInt(2, element.getId());
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    @Override
    public Ingredient findById(int id) throws SQLException {
        request = "SELECT name FROM ingredient WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,id);
        resultSet = statement.executeQuery();
        if(resultSet.next()){
            return Ingredient.builder().id(id).name(resultSet.getString("name")).build();
        }
        return null;
    }

    @Override
    public List<Ingredient> findAll() throws SQLException {
        List<Ingredient> ingredients = null;
        request = "SELECT id,name FROM ingredient";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while(resultSet.next()){
            ingredients.add(Ingredient.builder().id(resultSet.getInt("id")).name("name").build());
        }
        return ingredients;
    }

    @Override
    public List<Ingredient> findAllByRecipeId(int recipeId) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("ingredient");
    }
}
