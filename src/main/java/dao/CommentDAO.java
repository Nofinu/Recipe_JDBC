package dao;

import jdk.jshell.spi.ExecutionControl;
import model.Comment;
import model.Step;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CommentDAO extends BaseDAO<Comment> {

    public CommentDAO(Connection _connection) {
        super(_connection);
    }

    @Override
    public boolean save(Comment element) throws SQLException {
        request = "INSERT INTO comments(text_comment,id_recipe) VALUES (?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,element.getTextComment());
        statement.setInt(2,element.getIdRecipe());
        int rows = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()){
            element.setId(resultSet.getInt(1));
        }
        return rows ==1;
    }

    @Override
    public boolean delete(Comment element) throws SQLException {
        request = "DELETE FROM comments WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,element.getId());
        int rows = statement.executeUpdate();
        return rows ==1;
    }

    @Override
    public boolean update(Comment element) throws SQLException {
        request = "UPDATE comments SET text_comment = ? WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setString(1,element.getTextComment());
        statement.setInt(2,element.getId());
        int rows = statement.executeUpdate();
        return rows ==1;
    }

    @Override
    public Comment findById(int id) throws SQLException {
        request = "SELECT id_recipe,text_comment FROM comments WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,id);
        resultSet = statement.executeQuery();
        if(resultSet.next()){
            return new Comment(id,resultSet.getString("text_comment"),resultSet.getInt("id_recipe"));
        }
        return null;
    }

    @Override
    public List<Comment> findAll() throws SQLException {
        List<Comment> comments = null;
        request = "SELECT id,id_recipe, text_comment FROM comments";
        resultSet = _connection.prepareStatement(request).executeQuery();
        while(resultSet.next()){
            comments.add(new Comment(resultSet.getInt("id"),resultSet.getString("text_comment"),resultSet.getInt("id_recipe")));
        }
        return comments;
    }

    @Override
    public List<Comment> findAllByRecipeId(int recipeId) throws SQLException {
        List<Comment> comments = null;
        request = "SELECT id,text_comment FROM comments WHERE id_recipe = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,recipeId);
        resultSet = statement.executeQuery();
        while(resultSet.next()){
            comments.add(new Comment(resultSet.getInt("id"),resultSet.getString("text_comment"),resultSet.getInt(recipeId)));
        }
        return comments;
    }
}
