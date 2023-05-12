package service;

import Util.DatabaseManager;
import dao.IngredientDAO;
import dao.StepDAO;
import model.Ingredient;
import model.Step;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StepService {
    private Connection connection;
    private StepDAO stepDAO;

    public StepService(){
        try{
            connection = DatabaseManager.getDatabaseManager().getConnection();
            stepDAO = new StepDAO(connection);
        }catch (SQLException e){
           throw new RuntimeException(e);
        }
    }

    public boolean addStep(String stepText,int idRecipe) {
        Step step = new Step(stepText,idRecipe);
        try{
            if(stepDAO.save(step)){
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean deleteStep(int id) {
        Step step = null;
        try{
            step = stepDAO.findById(id);
            if(step != null){
                if(stepDAO.delete(step)){
                    return true;
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean editStep(int id, String stepText,int idRecipe) {
        Step step = new Step(id,stepText,idRecipe);
        try{
            if(stepDAO.update(step)){
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Step> findAllStep(){
        try{
            return stepDAO.findAll();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Step findStepById(int id) {
        try{
            return stepDAO.findById(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
