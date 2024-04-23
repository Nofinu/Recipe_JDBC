package org.example.dao;

import org.example.Util.DatabaseManager;
import jdk.jshell.spi.ExecutionControl;
import org.example.model.Comment;
import org.example.model.Step;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO extends BaseDAO<Comment> {

    public CommentDAO() {
    }

    @Override
    public boolean save(Comment element) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            request = "INSERT INTO comments(text_comment,id_recipe) VALUES (?,?)";
            statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, element.getTextComment());
            statement.setInt(2, element.getIdRecipe());
            int rows = statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                element.setId(resultSet.getInt(1));
            }
            return rows == 1;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally {
            close();
        }
    }

    @Override
    public boolean delete(Comment element) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            request = "DELETE FROM comments WHERE id = ?";
            statement = _connection.prepareStatement(request);
            statement.setInt(1, element.getId());
            int rows = statement.executeUpdate();
            return rows == 1;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally {
            close();
        }
    }

    @Override
    public boolean update(Comment element) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            request = "UPDATE comments SET text_comment = ? WHERE id = ?";
            statement = _connection.prepareStatement(request);
            statement.setString(1, element.getTextComment());
            statement.setInt(2, element.getId());
            int rows = statement.executeUpdate();
            return rows == 1;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally {
            close();
        }
    }

    @Override
    public Comment findById(int id) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            request = "SELECT id_recipe,text_comment FROM comments WHERE id = ?";
            statement = _connection.prepareStatement(request);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Comment(id, resultSet.getString("text_comment"), resultSet.getInt("id_recipe"));
            }
            return null;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }finally {
            close();
        }
    }

    @Override
    public List<Comment> findAll() throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            List<Comment> comments = new ArrayList<>();
            request = "SELECT id,id_recipe, text_comment FROM comments";
            resultSet = _connection.prepareStatement(request).executeQuery();
            while(resultSet.next()){
                comments.add(new Comment(resultSet.getInt("id"),resultSet.getString("text_comment"),resultSet.getInt("id_recipe")));
            }
            return comments;
            }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }finally {
            close();
        }
    }

    public List<Comment> findAllByRecipeId(int recipeId) throws SQLException {
        try {
            _connection = DatabaseManager.getConnection();
            List<Comment> comments = new ArrayList<>();
            request = "SELECT id,text_comment FROM comments WHERE id_recipe = ?";
            statement = _connection.prepareStatement(request);
            statement.setInt(1, recipeId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comments.add(new Comment(resultSet.getInt("id"), resultSet.getString("text_comment"), recipeId));
            }
            return comments;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }finally {
            close();
        }

    }
}
