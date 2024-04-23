package org.example.dao;

import org.example.Util.DatabaseManager;
import jdk.jshell.spi.ExecutionControl;
import org.example.model.Step;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StepDAO extends BaseDAO<Step> {
    public StepDAO() {
    }

    @Override
    public boolean save(Step element) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            request = "INSERT INTO step(text_step,id_recipe) VALUES (?,?)";
            statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, element.getTextStep());
            statement.setInt(2, element.getIdRecipe());
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
    public boolean delete(Step element) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            request = "DELETE FROM step WHERE id = ?";
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
    public boolean update(Step element) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            request = "UPDATE step SET text_step = ? WHERE id = ?";
            statement = _connection.prepareStatement(request);
            statement.setString(1, element.getTextStep());
            statement.setInt(2, element.getId());
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
    public Step findById(int id) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            request = "SELECT id_recipe,text_step FROM step WHERE id = ?";
            statement = _connection.prepareStatement(request);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Step(id, resultSet.getString("text_step"), resultSet.getInt("id_recipe"));
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
    public List<Step> findAll() throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            List<Step> steps = new ArrayList<>();
            request = "SELECT id,id_recipe, text_step FROM step";
            resultSet = _connection.prepareStatement(request).executeQuery();
            while (resultSet.next()) {
                steps.add(new Step(resultSet.getInt("id"), resultSet.getString("text_step"), resultSet.getInt("id_recipe")));
            }
            return steps;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }finally {
            close();
        }

    }

    public List<Step> findAllByRecipeId(int recipeId) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            List<Step> steps = new ArrayList<>();
            request = "SELECT id,text_step FROM step WHERE id_recipe = ?";
            statement = _connection.prepareStatement(request);
            statement.setInt(1, recipeId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                steps.add(new Step(resultSet.getInt("id"), resultSet.getString("text_step"),recipeId));
            }
            return steps;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }finally {
            close();
        }
    }
}
