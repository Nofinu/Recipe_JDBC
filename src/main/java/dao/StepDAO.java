package dao;

import jdk.jshell.spi.ExecutionControl;
import model.Step;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class StepDAO extends BaseDAO<Step> {
    public StepDAO(Connection _connection) {
        super(_connection);
    }

    @Override
    public boolean save(Step element) throws SQLException {
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

    @Override
    public boolean delete(Step element) throws SQLException {
        request = "DELETE FROM step WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, element.getId());
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    @Override
    public boolean update(Step element) throws SQLException {
        request = "UPDATE step SET text_step = ? WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getTextStep());
        statement.setInt(2,element.getId());
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    @Override
    public Step findById(int id) throws SQLException {
        request = "SELECT id_recipe,text_step FROM step WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,id);
        resultSet = statement.executeQuery();
        if(resultSet.next()){
            return new Step(id,resultSet.getString("text_step"),resultSet.getInt("id_recipe"));
        }
        return null;
    }

    @Override
    public List<Step> findAll() throws SQLException {
        List<Step> steps = null;
        request = "SELECT id,id_recipe, text_step FROM step";
        resultSet = _connection.prepareStatement(request).executeQuery();
        while(resultSet.next()){
            steps.add(new Step(resultSet.getInt("id"),resultSet.getString("text_step"),resultSet.getInt("id_recipe")));
        }
        return steps;
    }

    @Override
    public List<Step> findAllByRecipeId(int recipeId) throws SQLException {
        List<Step> steps = null;
        request = "SELECT id,text_step FROM step WHERE id_recipe = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,recipeId);
        resultSet = statement.executeQuery();
        while(resultSet.next()){
            steps.add(new Step(resultSet.getInt("id"),resultSet.getString("text_step"),resultSet.getInt(recipeId)));
        }
        return steps;
    }
}
