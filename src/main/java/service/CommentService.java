package service;

import Util.DatabaseManager;
import dao.CommentDAO;
import dao.StepDAO;
import model.Comment;
import model.Step;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CommentService {
    private Connection connection;
    private CommentDAO commentDAO;

    public CommentService(){
        try{
            connection = DatabaseManager.getDatabaseManager().getConnection();
            commentDAO = new CommentDAO(connection);
        }catch (SQLException e){
           throw new RuntimeException(e);
        }
    }

    public boolean addComment(String commentText,int idRecipe) {
        Comment comment = new Comment(commentText,idRecipe);
        try{
            if(commentDAO.save(comment)){
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean deleteComment(int id) {
        Comment comment = null;
        try{
            comment = commentDAO.findById(id);
            if(comment != null){
                if(commentDAO.delete(comment)){
                    return true;
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean editComment(int id, String commentText,int idRecipe) {
        Comment comment = new Comment(id,commentText,idRecipe);
        try{
            if(commentDAO.update(comment)){
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Comment> findAllComment(){
        try{
            return commentDAO.findAll();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Comment findCommentById(int id) {
        try{
            return commentDAO.findById(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
